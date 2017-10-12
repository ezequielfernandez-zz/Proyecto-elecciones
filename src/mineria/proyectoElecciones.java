/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mineria;

//import facebook4j.Facebook;
//import facebook4j.FacebookFactory;
//import facebook4j.Post;
//import facebook4j.Reading;
//import facebook4j.auth.AccessToken;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.Hashtable;
import java.util.List;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.util.Pair;
import twitter4j.Query;
import twitter4j.QueryResult;
import twitter4j.ResponseList;
import twitter4j.Status;
import twitter4j.Twitter;
import twitter4j.TwitterException;
import twitter4j.TwitterFactory;
import twitter4j.TwitterStream;
import twitter4j.TwitterStreamFactory;
import twitter4j.User;
import twitter4j.conf.Configuration;
import twitter4j.conf.ConfigurationBuilder;




/**
 *
 * @author Santiago
 */
public class proyectoElecciones {
    
    private TwitterFactory tf;
    private Twitter twitter;
    private ConfigurationBuilder cb;
    private Configuration cfg;
    private TwitterStream twitterStream;
    private Clasificacion clasif=new Clasificacion();
    
    private static final String candidatos1="kirchner/unidad ciudadana";
    private static final String candidatos2="esteban bullrich/cambiemos";
    private static final String candidatos3="massa/1pais";
    private static final String candidatos4="randazzo/frente justicialista";
    private static final String candidatos5="pitrola/fit";
    
    public proyectoElecciones(){
        cb = new ConfigurationBuilder();
        cb.setDebugEnabled(true)
          .setOAuthConsumerKey("iVtlSSONvcQ2CHfs55j4ILBPs")
          .setOAuthConsumerSecret("qMbT4PrLNVRZCNQpCeE9NEhwPG9sUld0hGbAO0XzUgTAZpTOCi")
          .setOAuthAccessToken("1699786572-qVAP1rD4vRv44JrRPFDYl2L3KC3jcR7ZKV9kLb1")
          .setOAuthAccessTokenSecret("55Dk6T3MvSNhkRhPcgP8yZ1E9tB2W5SsgEsevPtB1Cy8E");
        cfg=cb.build();
        tf = new TwitterFactory(cfg);
        twitter = tf.getInstance();
        twitterStream = new TwitterStreamFactory(cfg).getInstance();
        
        /*
        Facebook facebook = new FacebookFactory().getInstance();  
        facebook.setOAuthAppId("1981520725402919", "fd270f189cf3890ed9edf2adfab87753");
        String accessTokenString = "1981520725402919|cOyd5TORn1riVGoa22iYW54w_jY";
        facebook.setOAuthPermissions("public_profile");
        AccessToken at = new AccessToken(accessTokenString);
        facebook.setOAuthAccessToken(at);


        try{
            ResponseList<Post> results = (ResponseList<Post>) facebook.searchPosts("watermelon");
        }
        catch(Exception e){e.printStackTrace();}
*/
    }
    
    private Hashtable<String,Hashtable<User,List<String>>> buscarTweets(String[] candidatos){
        Hashtable<String,Hashtable<User,List<String>>> tabla = new Hashtable();
        for(int i=0;i<candidatos.length;i++){
            try {
                tabla.put(candidatos[i], this.searchTweets(candidatos[i]));
            } catch (TwitterException ex) {
                Logger.getLogger(proyectoElecciones.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        return tabla;
    }
    
    private Hashtable<User,List<String>> searchTweets(String candidatos) throws TwitterException{
        Hashtable<User,List<String>> tabla = new Hashtable();
        for(String candidato : candidatos.split("/")){
            Query query = new Query(candidato);
            query.lang("es");
            query.resultType(Query.ResultType.mixed);
            query.count(2000000);
            query.locale("argentina");
            QueryResult result = twitter.search(query);
            for (Status status : result.getTweets()) {
                User usuario=status.getUser();
                if(!tabla.containsKey(usuario))
                    tabla.put(usuario, new ArrayList<>());
                tabla.get(usuario).add(status.getText());
            }
            System.out.println("usuarios "+candidato+" :"+tabla.size());
        }
        
        return tabla;
    }
    
    //retorna una matriz [2][5] donde las filas son intencion de voto e imagen
    //y las columnas son los candidatos (0:Cfk, 1:Macri, 2:Massa, 3:Randazzo, 4:Pitrola)
    public float[][] calcularIntencionVotoEimagen(String[] candidatos){
        Hashtable<String,Hashtable<User,List<String>>> tabla = this.buscarTweets(candidatos);
        Hashtable<User,Integer[]> puntajes = new Hashtable();
        //0:Cfk, 1:Macri, 2:Massa, 3:Randazzo, 4:Pitrola
        float[] votosCandidatos=new float[]{0,0,0,0,0};
        
        //para cada cantidato tengo una tabla con K:User y 
        //V:puntajeDelCandidato (0:Cfk, 1:Macri, 2:Massa, 3:Randazzo, 4:Pitrola)
        for (int i=0; i<candidatos.length;i++)
            this.calcularPuntajes(candidatos[i], tabla, puntajes);
        

        
        float[] imagenCandidatos=new float[]{0,0,0,0,0};
        //tengo la tabla de usuarios y para cada usuario el arreglo de puntajes de candidatos
        Enumeration<User> usuarios=puntajes.keys();
        while(usuarios.hasMoreElements()){
            User usuario = usuarios.nextElement();
            Integer[] punt = puntajes.get(usuario);
            for(int i=0; i<punt.length;i++){
                if(punt[i]>0)
                    imagenCandidatos[i]++;
                else
                    if(punt[i]<0)
                        imagenCandidatos[i]--;
            }
        }        
        
        
        //tengo la tabla de usuarios y para cada usuario el arreglo de puntajes de candidatos
        usuarios=puntajes.keys();
        while(usuarios.hasMoreElements()){
            User usuario = usuarios.nextElement();
            List<Integer> mayores = this.calcularMayor(puntajes.get(usuario));
            int pos=0;
            
            if(mayores.size()>1)
                pos=this.desempatarMayor(mayores, usuario, tabla);
            else
                pos=mayores.get(0);
            
            if(puntajes.get(usuario)[pos]>0)
                votosCandidatos[pos]++;
        }
        float[][] tor= new float[2][5];
        tor[0]=this.porcentajesVotos(votosCandidatos);
        tor[1]=this.normalizarVotos(imagenCandidatos);
        return tor;
    }
    
    
    private float[] normalizarVotos(float[] votos){
        float[] tor = new float[]{0,0,0,0,0};
        float mayorAbsoluto=0;
        for (int i=0; i<votos.length;i++){
            if(Math.abs(votos[i])>mayorAbsoluto)
                mayorAbsoluto=Math.abs(votos[i]);
        }
        for(int i=0; i<tor.length;i++){
            float res=votos[i]/mayorAbsoluto;
            tor[i]=res;
        }
        return tor;
    }
    
    private float[] porcentajesVotos(float[] votos){
        float[] tor = new float[5];
        float cantVotos=0;
        for (int i=0; i<votos.length;i++){
            cantVotos+=votos[i];
        }

        for(int i=0; i<tor.length;i++){
            double porcentaje = votos[i]/cantVotos*100;
            tor[i]=(float) (Math.round(porcentaje*10d)/10d);
            
        }
        return tor;
    }
    
    private int desempatarMayor(List<Integer> mayores, User usuario, Hashtable<String,Hashtable<User,List<String>>> tabla){
        int pos=0;
        List<Pair<String,List<String>>> listaCandidatosADesempatar = new ArrayList();
        List<String> listaTweets=new ArrayList();
        for(Integer candidato : mayores){
            switch(candidato){
                case 0: 
                    if(tabla.get(candidatos1).get(usuario)!=null) listaTweets = tabla.get(candidatos1).get(usuario);
                    listaCandidatosADesempatar.add(new Pair(candidatos1,listaTweets));
                        break;
                case 1: 
                    if(tabla.get(candidatos2).get(usuario)!=null) listaTweets = tabla.get(candidatos2).get(usuario);
                    listaCandidatosADesempatar.add(new Pair(candidatos2,listaTweets));
                        break;
                case 2: 
                    if(tabla.get(candidatos3).get(usuario)!=null) listaTweets = tabla.get(candidatos3).get(usuario);
                    listaCandidatosADesempatar.add(new Pair(candidatos3,listaTweets));
                        break;
                case 3: 
                    if(tabla.get(candidatos4).get(usuario)!=null) listaTweets = tabla.get(candidatos4).get(usuario);
                    listaCandidatosADesempatar.add(new Pair(candidatos4,listaTweets));
                        break;
                case 4:
                    if(tabla.get(candidatos5).get(usuario)!=null) listaTweets = tabla.get(candidatos5).get(usuario);
                    listaCandidatosADesempatar.add(new Pair(candidatos5,listaTweets));
                        break;
                default:
                    throw new IllegalArgumentException("se fue del switch");
            }
        }
        
        String candidatoMayor=clasif.desempatar(listaCandidatosADesempatar);
        switch(candidatoMayor){
            case candidatos1: pos=0; break;
            case candidatos2: pos=1; break;
            case candidatos3: pos=2; break;
            case candidatos4: pos=3; break;
            case candidatos5: pos=4; break;
            default:
                throw new IllegalArgumentException("se fue del switch");
        }
        return pos;
    }
    
    private List<Integer> calcularMayor(Integer[] puntajes){
        List<Integer> mayores = new ArrayList();
        int mayor=-100;
        for(int i=0; i<puntajes.length;i++){
            if(puntajes[i]>mayor){
                mayor=puntajes[i];
            }      
        }
        for(int i=0; i<puntajes.length;i++){
            if(puntajes[i]==mayor){
                mayores.add(i);
            }      
        }
        return mayores;
    }
    
    private Hashtable<User,Integer[]> calcularPuntajes(String candidato, Hashtable<String,Hashtable<User,List<String>>> tabla, Hashtable<User,Integer[]> puntajes){
        Enumeration<User> usuarios=tabla.get(candidato).keys();
        while(usuarios.hasMoreElements()){
            User usuario = usuarios.nextElement();
            if(!puntajes.containsKey(usuario))
                puntajes.put(usuario, new Integer[]{0,0,0,0,0});
            
            int puntaje=0;
            for(String tweet : tabla.get(candidato).get(usuario)){
                switch(candidato){
                    case candidatos1: puntaje+=clasif.clasificarCristina(tweet); break;
                    case candidatos2: puntaje+=clasif.clasificarMacri(tweet); break;
                    case candidatos3: puntaje+=clasif.clasificarMassa(tweet); break;
                    case candidatos4: puntaje+=clasif.clasificarRandazzo(tweet); break;
                    case candidatos5: puntaje+=clasif.clasificar(tweet); break;
                    default: throw new IllegalArgumentException("se fue del switch");
                }

            }
            Integer p[]=puntajes.get(usuario);
            switch(candidato){
                case candidatos1:
                    p[0]=puntaje;
                    //puntajes.put(usuario, p);
                    break;
                case candidatos2:
                    p[1]=puntaje;
                    //puntajes.put(usuario, p);
                    break;
                case candidatos3:
                    p[2]=puntaje;
                    //puntajes.put(usuario, p);
                    break;
                case candidatos4:
                    p[3]=puntaje;
                    //puntajes.put(usuario, p);
                    break;
                case candidatos5:
                    p[4]=puntaje;
                    //puntajes.put(usuario, p);
                    break;
                default:
                    throw new IllegalArgumentException("se fue del switch");
            }
        }
        return puntajes;
    }
   
    
    
    
       
    
    public static void main(String[] args) {
        proyectoElecciones pe=new proyectoElecciones();
        graficaV1 grafica= new graficaV1();
        grafica.inicial();
        float[][] votoImagen=pe.calcularIntencionVotoEimagen(new String[]{candidatos1, candidatos2, candidatos3, candidatos4, candidatos5});
        for(int i =0;i<votoImagen.length;i++){
            System.out.println();
            for(int j=0; j<votoImagen[i].length;j++){
                System.out.print(votoImagen[i][j]+ " ");
            }
        }     
        grafica.setPBullrich(votoImagen[0][1]);
        grafica.setPKirchner(votoImagen[0][0]);
        grafica.setPMassa(votoImagen[0][2]);
        grafica.setPPitrola(votoImagen[0][4]);
        grafica.setPRandazzo(votoImagen[0][3]);
        
        grafica.setIBullrich(votoImagen[1][1]);
        grafica.setIKirchner(votoImagen[1][0]);
        grafica.setIMassa(votoImagen[1][2]);
        grafica.setIPitrola(votoImagen[1][4]);
        grafica.setIRandazzo(votoImagen[1][3]);
        
        int cantDiputados=35;
        
        float votosCristina=votoImagen[0][0];
        float votosCambiemos=votoImagen[0][1];
        float votosMassa=votoImagen[0][2];
        float votosRandazzo=votoImagen[0][3];
        float votosIzquierda=votoImagen[0][4];
        
        int diputadosCristina=(int)(votosCristina*cantDiputados/100);
        int diputadosCambiemos=(int)(votosCambiemos*cantDiputados/100);
        int diputadosMassa=(int)(votosMassa*cantDiputados/100);
        int diputadosRandazzo=(int)(votosRandazzo*cantDiputados/100);
        int diputadosIzquierda=(int)(votosIzquierda*cantDiputados/100);
        

        
        int total=diputadosCristina+diputadosCambiemos+diputadosMassa+diputadosRandazzo+diputadosIzquierda;
        while(total<cantDiputados){
            float mayor=0;
            int ganador=0;
            for(int i=0;i<5;i++){
                if((votoImagen[0][i]-(int)(votoImagen[0][i]))>mayor){
                    mayor=(votoImagen[0][i]-(int)(votoImagen[0][i]));
                    ganador=i;
                }
            }
            switch (ganador){
                case 0: diputadosCristina++;break;
                case 1: diputadosCambiemos++;break;
                case 2: diputadosMassa++;break;
                case 3: diputadosRandazzo++;break;
                case 4: diputadosIzquierda++;break;
            }
            votoImagen[0][ganador]=(int)votoImagen[0][ganador];
            total++;
            mayor=0;
        }
        
        grafica.setDKirchner(diputadosCristina);
        grafica.setDBullrich(diputadosCambiemos);
        grafica.setDMassa(diputadosMassa);
        grafica.setDRandazzo(diputadosRandazzo);
        grafica.setDPitrola(diputadosIzquierda);
        grafica.setDOtros(0);
        
        
        System.out.println("mostrando diputados");
        System.out.println(diputadosCristina);
        System.out.println(diputadosCambiemos);
        System.out.println(diputadosMassa);
        System.out.println(diputadosRandazzo);
        System.out.println(diputadosIzquierda);

        //Para senadores
        int cantSenadores=3;
        
        int senadoresCristina=(int)(votosCristina*cantSenadores/100);
        votosCristina=votosCristina-senadoresCristina*33;
        int senadoresCambiemos=(int)(votosCambiemos*cantSenadores/100);
        votosCambiemos=votosCambiemos-senadoresCambiemos;
        int senadoresMassa=(int)(votosMassa*cantSenadores/100);
        votosMassa=votosMassa-senadoresMassa;
        int senadoresRandazzo=(int)(votosRandazzo*cantSenadores/100);
        votosRandazzo-=senadoresRandazzo;
        int senadoresIzquierda=(int)(votosIzquierda*cantSenadores/100);
        votosIzquierda-=senadoresIzquierda;

        int[] actuales=new int[]{(int)votosCristina,(int)votosCambiemos,(int)votosMassa,(int)votosRandazzo,(int)votosIzquierda};
        int total1=senadoresCristina+senadoresCambiemos+senadoresMassa+senadoresRandazzo+senadoresIzquierda;
        
        while(total1<cantSenadores){
            float mayor=0;
            int ganador=0;
            for(int i=0;i<4;i++){
                if((actuales[i])>mayor){
                    mayor=actuales[i];
                    ganador=i;
                }
            }
            switch (ganador){
                case 0: senadoresCristina++;actuales[ganador]-=33;break;
                case 1: senadoresCambiemos++;actuales[ganador]-=33;break;
                case 2: senadoresMassa++;actuales[ganador]-=33;break;
                case 3: senadoresRandazzo++;actuales[ganador]-=33;break;
            }
            total1++;
            mayor=0;
        }
        
        grafica.setSCiudadana(senadoresCristina);
        grafica.setSCambiemos(senadoresCambiemos);
        grafica.setSRenovador(senadoresMassa);
        grafica.setSJusticialista(senadoresRandazzo);
        grafica.setSFPV(0);
        grafica.setSOtros(0);
        
        
        
        System.out.println("mostrando senadores");
        System.out.println(senadoresCristina);
        System.out.println(senadoresCambiemos);
        System.out.println(senadoresMassa);
        System.out.println(senadoresRandazzo);
                
        
        grafica.mostrarResultados();
        grafica.repaint();
            
    }
}
