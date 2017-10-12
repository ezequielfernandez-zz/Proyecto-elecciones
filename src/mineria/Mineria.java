/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mineria;

import twitter4j.*;
import twitter4j.conf.ConfigurationBuilder;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.URL;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Enumeration;
import java.util.Hashtable;
import java.util.Iterator;
import java.util.PriorityQueue;
import java.util.Queue;
/**
 *
 * @author ezee
 */
public class Mineria {



    private List<String> listaTweets = new ArrayList();
    private List<String> listaTweetsSinSW = new ArrayList();
    private Hashtable<String,Double> tablaDeTerminos;
    private double[][] matrizTF;
    private ConexionTwitter ct;
    private Preprocesamiento preprocesador;
    private Postprocesamiento postprocesador;
    private String Texto;
    private Frame f;
    private boolean escribio=false;
    private List<Par> listaPar;
    
    public Mineria(Frame f){
        ct=new ConexionTwitter();
        preprocesador=new Preprocesamiento();
        postprocesador= new Postprocesamiento();
        tablaDeTerminos=new Hashtable<String,Double>();
        this.f=f;
        f.Configurar(this);
        listaPar=new ArrayList();
        Clasificacion c=new Clasificacion();
        c.clasificarMacri("fuerza macri sos la mejor");
        //Practico0();
    }
    
    public void recibir(List<Par> lis){
        listaPar=lis;
    }
    
    
    public void obtenerTweetsArchivo(List<String> listaTweets){
        //Obtengo la lista de tweets del archivo
        int cant=0;
        try{
            FileReader fichero = new FileReader("./ListaDeTweets.txt");
            BufferedReader pw = new BufferedReader(fichero);
            boolean seguir=true;
            while(seguir){
                String aux=pw.readLine();
                if(aux!=null){
                    listaTweets.add(aux);
                    System.out.println("tweet : "+cant+" : "+aux);
                    cant++;
                }
                else
                    seguir=false;
            }
        }
        catch(Exception e){}
        System.out.println("Cantidad: "+cant);
    }
    
    public void obtenerTweetsApi(List<String> listaTweets){
       
        listaTweets=ct.restAPI("esteban bullrich");    
        int cant=0;
        //Escribo la lista resultante en un archivo
        try{
            FileWriter fichero = new FileWriter("./ListaDeTweetsBullrich.txt");
            PrintWriter pw = new PrintWriter(fichero);
            for(String p:listaTweets){
                System.out.println("tweet : "+cant+" : "+p);
                p=p.replaceAll("\n", " ");
                cant++;
                pw.println(p);
            }
            pw.close();
            fichero.close();
        }
        catch(Exception e){}
        System.out.println("Cantidad: "+cant);
        
        
    }
    
    public void GenerarLista(){
        Clasificador clasi=new Clasificador(listaTweets,this);
        while(listaPar==null){
            try{
                Thread.sleep(2000);
            }
            catch(Exception e){}
        }
        
        //Escribo la lista resultante en un archivo
        try{
            FileWriter fichero = new FileWriter("./ConjuntoDeEntrenamiento.txt");
            PrintWriter pw = new PrintWriter(fichero);
            for(Par p:listaPar){
                pw.println(p.tweet);
                pw.println(p.cos);
            }
        }
        catch(Exception e){}
    }
    
    
    public void obtenerLista(List<String> listaTweets){
        int cant=0;
        try{
            FileReader fichero = new FileReader("./ConjuntoDeEntrenamiento.txt");
            BufferedReader pw = new BufferedReader(fichero);
            boolean seguir=true;
            while(seguir){
                String aux=pw.readLine();
                if(aux!=null){
                    String valorS=pw.readLine();System.out.println(aux);
                    while(valorS==null) valorS=pw.readLine();
                    Double valor=Double.valueOf(valorS);
                    listaTweets.add(aux);
                    listaPar.add(new Par(aux,valor));
                    System.out.println("tweet : "+cant+" : "+aux+", "+valor);
                    cant++;
                }
                else{
                    seguir=false;System.out.println("Entro al else");
                }
            }
            System.out.println("Salgo");
        }
        catch(Exception e){e.printStackTrace();}
        System.out.println("Cantidad: "+cant);
    }
    
    
    
    public List<String> AumentarSensibilidad(List<String> lista){
      FileReader fichero = null;
      ArrayList<String> aDevolver=new ArrayList();
      ArrayList<String> Intermedia=new ArrayList();
      for(String s : lista){
          aDevolver.add(s);
          Intermedia.add(s);
      }
      try {
        fichero = new FileReader("palabrasPositivas.txt");
        BufferedReader br = new BufferedReader(fichero);
         String linea;
         int pos=0;
         while((linea=br.readLine())!=null){
             for(String tweet : Intermedia){
                tweet = tweet.toLowerCase();
                String [] aux = tweet.split(" ");
                String e="";
                String nuevoTweet=tweet;
                for(String palabra : aux){
                    if(palabra.equals(linea)){
                        String [] cortada=tweet.split(" ");
                        nuevoTweet="";
                        for(String p : cortada){
                            nuevoTweet=nuevoTweet+" "+p;
                            if(p.equals(linea)){
                                nuevoTweet=nuevoTweet+" "+linea;
                            }
                        }
                    }
                }
                aDevolver.remove(pos);
                aDevolver.add(pos,nuevoTweet);
                pos++;
             }
             pos=0;
             for(String i : aDevolver){
                 Intermedia.remove(pos);
                 Intermedia.add(pos,i);
                 pos++;
             }
             pos=0;
         }
        fichero = new FileReader("palabrasNegativas.txt");
        br = new BufferedReader(fichero);
        pos=0;
         while((linea=br.readLine())!=null){
             for(String tweet : aDevolver){
                tweet = tweet.toLowerCase();
                String [] aux = tweet.split(" ");
                String e="";
                String nuevoTweet=tweet;
                for(String palabra : aux){
                    if(palabra.equals(linea)){
                        e=linea+" "+linea;
                        String [] cortada=tweet.split(" ");
                        nuevoTweet="";
                        for(String p : cortada){
                            nuevoTweet=nuevoTweet+" "+p;
                            if(p.equals(linea)){
                                nuevoTweet=nuevoTweet+" "+linea;
                            }
                        }
                    }
                }
                Intermedia.remove(pos);
                Intermedia.add(pos,nuevoTweet);
                pos++;
             }
             pos=0;
             for(String i : Intermedia){
                 aDevolver.remove(pos);
                 aDevolver.add(pos,i);
                 pos++;
             }
             pos=0;
         }
      }
      catch(Exception e){
         e.printStackTrace();
      }finally{
         // En el finally cerramos el fichero, para asegurarnos
         // que se cierra tanto si todo va bien como si salta 
         // una excepcion.
         try{                    
               fichero.close();                     
         }catch (Exception e2){ 
            e2.printStackTrace();
         }
      }
      return aDevolver;
    }
        
    public List<String> TrabajarNo(List<String> lista){
        ArrayList<String> aDevolver=new ArrayList();
        String nuevo="";
        boolean cambiar=false;
        for(String tweet: lista){
            String [] palabras=tweet.split(" ");
            for(String p:palabras){
                if(cambiar){
                    nuevo=nuevo+" "+"no-"+p;
                    cambiar=false;
                }
                else{
                    if(!p.equals("no")) nuevo=nuevo+" "+p;
                    else cambiar=true;
                }
            }
            aDevolver.add(nuevo);
            nuevo="";
        }
        
        return aDevolver;
    }
    public void Practico0(){
        List<String> listaTweets=new ArrayList<String>();
        List<String> listaTweetsSinSW=new ArrayList<String>();
        String[] keywords= new String[3];
        
        
        Preprocesamiento  preprocesador=new Preprocesamiento();
        Postprocesamiento postprocesador= new Postprocesamiento();
        Hashtable<String,Double> tablaDeTerminos=new Hashtable<String,Double>();
        
       //Para obtener lista de tweets de archivo
       //obtenerTweetsArchivo(listaTweets);

       //Para obtener lista de tweets de API
       //obtenerTweetsApi(listaTweets);
        

        //Obtengo la lista de Pares
        obtenerLista(listaTweets);
        
        
        for (String tweet : listaTweets) {
            tweet = preprocesador.eliminarStopwords(tweet);
            tweet = preprocesador.eliminarLinks(tweet);
            tweet = preprocesador.sinTildes(tweet);
            tweet = preprocesador.eliminarSimbolos(tweet);
            tweet = preprocesador.quitaEspaciosExtra(tweet);
            tweet = preprocesador.eliminarPorAscii(tweet);
            
            listaTweetsSinSW.add(tweet);
            for (String termino : tweet.split(" ")) {
                tablaDeTerminos.put(termino, 0.0);
            }
        }

        //Por cada tweet, duplico sus palabras con contenido de sensibilidad
        listaTweetsSinSW=AumentarSensibilidad(listaTweetsSinSW);

        //listaTweetsSinSW=TrabajarNo(listaTweetsSinSW);
        //for(String v : listaTweetsSinSW) System.out.println("Quedo: "+v);
        //GenerarLista();
        
        double[][] MatrizTFIDF=postprocesador.crearArchivoARFFsparse1(listaTweets,listaTweetsSinSW, tablaDeTerminos,listaPar);
        MatrizTFIDF=postprocesador.getMatrizTF();

        try{
            FileWriter fichero = new FileWriter("./tweets.txt");
            PrintWriter pw = new PrintWriter(fichero);
            
            pw.println("Palabras de busqueda utilizadas: "+keywords.toString());
            pw.println();
            pw.println();
            pw.println("Lista de tweets recopilados:");
            pw.println();
            pw.println();
            int num=1;
            for(String tw: listaTweets){
               pw.println(num+": "+tw);
               num++;
            }
            pw.println();
            pw.println();
            pw.println("Lista de tweets recopilados sin sus stopword:");
            pw.println();
            pw.println();
            num=1;
            for(String tw: listaTweetsSinSW){
               pw.println(num+": "+tw);
               num++;
            }
            fichero.close();
        }
            catch(Exception e){}

        //Procedo a calcular y escribir la matriz de cosenos
        double[][] matS= postprocesador.calcularSimilaridadCoseno(MatrizTFIDF);
        int tweetX=-1;
        int tweetY=-1;
        double coseno=0;
        try
        {
            FileWriter fichero = new FileWriter("./MatrizSimilaridadCoseno-arff.txt");
            PrintWriter pw = new PrintWriter(fichero);
            pw.println("@RELATION SimilaridadCoseno");pw.println();
            int i=1;
            for(String tweet : listaTweetsSinSW){
                tweet=tweet.replace(' ', '-');
                pw.println("@ATTRIBUTE "+i+")-"+ tweet +" NUMERIC");
                i++;
            }
            
            
            pw.println();
            pw.println("@DATA");
            for (int x=0; x < matS.length; x++) {
                
                for (int y=0; y < matS[x].length; y++) {
                  if(y==0){
                      pw.print(matS[x][y]);
                  }
                  else{
                      pw.print(", "+matS[x][y]);
                  }
                  if(x!=y && matS[x][y]>coseno && matS[x][y]<1){
                      tweetX=x;
                      tweetY=y;
                      coseno=matS[x][y];
                  }
                }
                pw.println();
                

            }
            if(tweetX>0 && tweetY>0){
                FileWriter resu = new FileWriter("./MasSimilares.txt");
                PrintWriter pw1 = new PrintWriter(resu);
                pw1.println("Los dos twits mas parecidos son: "+tweetX+" y "+tweetY);
                pw1.println(listaTweets.get(tweetX));
                pw1.println(listaTweets.get(tweetY)); 
                pw1.println("el coseno del angulo entre sus vectores es: "+coseno);
                resu.close();
            }
            fichero.close();
        }
        catch(Exception e){}
        
    }

    public void Practico1(String [] keywords,String Contexto,String aBuscarSC){
        List<String> listaTweets=new ArrayList<String>();
        List<String> listaTweetsSinSW=new ArrayList<String>();
   //     String[] keywords= new String[4];
  //     keywords[0]="ganaron";
   //     keywords[1]="depender";
   //     keywords[2]="vuelve";
   //     keywords[3]="river";
         Preprocesamiento      preprocesador=new Preprocesamiento();
        Postprocesamiento postprocesador= new Postprocesamiento();
        Hashtable<String,Double> tablaDeTerminos=new Hashtable<String,Double>();

        ct.streamingAPI(keywords);
        int segundos=10;
        try{
            Thread.sleep(1000*segundos);//Detengo este hilo hasta que el hilo del streaming recopile una cantidad de tweets
        }
        catch(Exception exce){}
        listaTweets= ct.pararRecopilacion();

        for (String tweet : listaTweets) {
            tweet = preprocesador.eliminarStopwords(tweet);
            tweet = preprocesador.eliminarLinks(tweet);
            tweet = preprocesador.sinTildes(tweet);
            tweet = preprocesador.eliminarSimbolos(tweet);
            tweet = preprocesador.quitaEspaciosExtra(tweet);
            tweet = preprocesador.eliminarPorAscii(tweet);
            
            listaTweetsSinSW.add(tweet);
            for (String termino : tweet.split(" ")) {
                tablaDeTerminos.put(termino, 0.0);
            }
        }

        double[][] MatrizTFIDF=postprocesador.crearArchivoARFFsparse(listaTweets,listaTweetsSinSW, tablaDeTerminos);

        try{
            FileWriter fichero = new FileWriter("./tweets.txt");
            PrintWriter pw = new PrintWriter(fichero);
            
            pw.println("Palabras de busqueda utilizadas: "+keywords.toString());
            pw.println();
            pw.println();
            pw.println("Lista de tweets recopilados:");
            pw.println();
            pw.println();
            int num=1;
            for(String tw: listaTweets){
               pw.println(num+": "+tw);
               num++;
            }
            pw.println();
            pw.println();
            pw.println("Lista de tweets recopilados sin sus stopword:");
            pw.println();
            pw.println();
            num=1;
            for(String tw: listaTweetsSinSW){
               pw.println(num+": "+tw);
               num++;
            }
            fichero.close();
        }
            catch(Exception e){}

        //Procedo a calcular y escribir la matriz de cosenos
        double[][] matS= postprocesador.calcularSimilaridadCoseno(MatrizTFIDF);
        int tweetX=-1;
        int tweetY=-1;
        double coseno=0;

        try
        {
            FileWriter fichero = new FileWriter("./MatrizSimilaridadCoseno-arff.txt");
            PrintWriter pw = new PrintWriter(fichero);
            pw.println("@RELATION SimilaridadCoseno");pw.println();
            int i=1;
            for(String tweet : listaTweetsSinSW){
                tweet=tweet.replace(' ', '-');
                pw.println("@ATTRIBUTE "+i+")-"+ tweet +" NUMERIC");
                i++;
            }
            
            
            pw.println();
            pw.println("@DATA");
            for (int x=0; x < matS.length; x++) {
                
                for (int y=0; y < matS[x].length; y++) {
                  if(y==0){
                      pw.print(matS[x][y]);
                  }
                  else{
                      pw.print(", "+matS[x][y]);
                  }
                  if(x!=y && matS[x][y]>coseno && matS[x][y]<1){
                      tweetX=x;
                      tweetY=y;
                      coseno=matS[x][y];
                  }
                }
                pw.println();
                

            }
            if(tweetX>0 && tweetY>0){
                FileWriter resu = new FileWriter("./MasSimilares.txt");
                PrintWriter pw1 = new PrintWriter(resu);
                pw1.println("Los dos twits mas parecidos son: "+tweetX+" y "+tweetY);
                pw1.println(listaTweets.get(tweetX));
                pw1.println(listaTweets.get(tweetY)); 
                pw1.println("el coseno del angulo entre sus vectores es: "+coseno);
                resu.close();
            }
            fichero.close();
        }
        catch(Exception e){e.printStackTrace();}
        PriorityQueue<Par> cola = new PriorityQueue<Par>();
        int fila=matS.length-1;
        for(int j=0;j<matS[0].length;j++){
            if(!listaTweets.get(j).equals(aBuscarSC))
            cola.add(new Par(listaTweets.get(j),1/matS[fila][j]));
        }
        
        f.setTweets(cola.iterator( ));

        
        //f.destruir();
}
        
    public void callbackTexto(String t){
        Texto=t;
        escribio=true;
    }
    public void Practico2(){
        
        if(escribio){
            Texto = preprocesador.eliminarStopwords(Texto);
            Texto = preprocesador.eliminarLinks(Texto);
            Texto = preprocesador.sinTildes(Texto);
            Texto = preprocesador.eliminarSimbolos(Texto);
            Texto = preprocesador.quitaEspaciosExtra(Texto);
            Texto = preprocesador.eliminarPorAscii(Texto);

            Hashtable<String,Integer> tablaFrecuencia=new Hashtable<String,Integer>();
            int maxima=0;
            for (String termino : Texto.split(" ")) {
                Integer valor=tablaFrecuencia.get(termino);
                if(valor==null){
                    tablaFrecuencia.put(termino, 1);
                }
                else{
                    tablaFrecuencia.put(termino, valor+1);
                    if(valor+1>maxima){
                        maxima=valor+1;
                    }
                }
            }
            
            String aBuscar="";
            Enumeration<String> enume=tablaFrecuencia.keys();
            while(enume.hasMoreElements()){
            String clave=enume.nextElement();
                if(tablaFrecuencia.get(clave)>=(maxima/2)){
                    aBuscar=aBuscar+clave+" ";
                }
            }
            System.out.println("Mostrando a buscar sin cortar: "+aBuscar);
            String aux=aBuscar;
            aBuscar="";
            
            /*En un texto un autor tiende a nombrar a los protagonistas al principio y luego
              usar sinónimos. Por esto consideramos sólo la primer parte de las palabras clave
              siendo 2 el minimo de palabras a buscar. */

            String [] arreglo=aux.split(" ");
            int tope=arreglo.length;
            boolean dos=false;
            List<String> list=new ArrayList();
            String AB2="";
            if(tope>3){
                tope=4;
                dos=true;
             }
            System.out.println(tope);
            for(int a=0;a<tope;a++){
                if(!dos)
                aBuscar=aBuscar+arreglo[a]+" ";
                else{
                    if(a==2 || a==3){
                        AB2=AB2+arreglo[a]+" ";
                    }
                    else{
                        aBuscar=aBuscar+arreglo[a]+" ";
                    }
                }
            }
            
            listaTweets=ct.restAPI(aBuscar);

            if(dos){
                list=ct.restAPI(AB2);
                listaTweets.addAll(list);
                aBuscar=aBuscar+AB2;
                System.out.println("Dos busquedas realizadas: "+aBuscar);
            }
            System.out.println("mostrando tweets");
            Hashtable<String,Integer> frecuencias=new Hashtable<String,Integer>();
            for(String t : listaTweets){
                System.out.println(t);
                for (String termino : t.split(" ")) {
                    Integer valor=frecuencias.get(termino);
                    if(valor==null){
                        frecuencias.put(termino, 1);
                    }
                    else{
                        frecuencias.put(termino, valor+1);
                    }
                }
            }

            listaTweets.add(aux);
            listaTweetsSinSW=new ArrayList();
            for (String tweet : listaTweets) {
                tweet = preprocesador.eliminarStopwords(tweet);
                tweet = preprocesador.eliminarLinks(tweet);
                tweet = preprocesador.sinTildes(tweet);
                tweet = preprocesador.eliminarSimbolos(tweet);
                tweet = preprocesador.quitaEspaciosExtra(tweet);
                tweet = preprocesador.eliminarPorAscii(tweet);
                listaTweetsSinSW.add(tweet);
                for (String termino : tweet.split(" ")) {
                    tablaDeTerminos.put(termino, 0.0);
                }
            }
            double [][] MatrizTFIDF=postprocesador.crearArchivoARFFsparse(listaTweets, listaTweetsSinSW, tablaDeTerminos);
            double [][] MatrizTF=postprocesador.getMatrizTF();
            

            double [][] similaridad=postprocesador.calcularSimilaridadCoseno(MatrizTF); //Matriz TDIDF ya almacenada en el objeto
            
            int fila=listaTweets.size()-1;
            
            System.out.println("Muestro la matriz: "+similaridad.length);
            for(int a=0;a<similaridad.length;a++){
                for(int b=0;b<similaridad[0].length;b++){
                    System.out.print(similaridad[a][b]+", ");
                }
                System.out.println();
            }

            List<String> masParecidos=new ArrayList();
            for(int i=0;i<fila;i++){
                if(similaridad[fila][i]>0.2){
                    System.out.println("parecido: "+listaTweetsSinSW.get(i));
                    masParecidos.add(listaTweetsSinSW.get(i));
                }
            }
            
            Hashtable<String,Integer> tablaFrecuenciaFinal=new Hashtable<String,Integer>();
            int maximaF=0;
            System.out.println("Muestro los mas parecidos");
            for(String s : masParecidos){
                System.out.println(s);
                for (String termino : s.split(" ")) {
                    boolean esClave=false;
                    for(String clave : tablaFrecuencia.keySet()){
                        esClave=esClave || termino.equals(clave);
                    }
                    if(!esClave){
                        Integer valor=tablaFrecuenciaFinal.get(termino);
                        if(valor==null){
                            tablaFrecuenciaFinal.put(termino, 1);
                        }
                        else{
                            tablaFrecuenciaFinal.put(termino, valor+1);
                            if(valor+1>maximaF){
                                maximaF=valor+1;
                            }
                        }
                    }
                }
            }
            
            
            String Contexto="";
            for(String clave : tablaFrecuenciaFinal.keySet()){
                if(tablaFrecuenciaFinal.get(clave)>(maximaF/2) && !clave.equals("rt")){
                    Contexto=Contexto+clave+" ";
                }
            }
            Contexto=Contexto+aBuscar;
            System.out.println("Contexto: "+Contexto);
            f.setContexto(Contexto);
            String [] keywords= Contexto.split(" ");
            System.out.println("Ahora buscaremos tweets con ese contexto");
            Practico1(keywords,Contexto,aux);
            System.out.println("Terminado");
            f.repaint();
            //f.destruir();
        }
        else{
            try{
                Thread.sleep(1000);
                Practico2();
            }
            catch(Exception e){}
        }
        
    }
    
    
    public static void main(String[] args) throws InterruptedException {
        
        Frame f=new Frame();
        Mineria rp = new Mineria(f);
        
    }
    
}
