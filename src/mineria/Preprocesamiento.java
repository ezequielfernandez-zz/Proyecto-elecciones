/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mineria;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.InputStreamReader;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 *
 * @author ezee
 */
public class Preprocesamiento {
    
    public Preprocesamiento(){
        
    }
    
    public String sinTildes(String texto) {
        String original = "ÀÁÂÃÄÅÆÇÈÉÊËÌÍÎÏÐÑÒÓÔÕÖØÙÚÛÜÝßàáâãäåæçèéêëìíîïðñòóôõöøùúûüýÿ";
        // Cadena de caracteres ASCII que reemplazarán los originales.
        String ascii = "AAAAAAACEEEEIIIIDNOOOOOOUUUUYBaaaaaaaceeeeiiiionoooooouuuuyy";
        String output = texto;
            for (int i=0; i<original.length(); i++) {
            // Reemplazamos los caracteres especiales.
                output = output.replace(original.charAt(i), ascii.charAt(i));
            }//for i
        
        
        return output;
    }
    public String eliminarSimbolos(String tweet){
        String resultado=tweet;

        resultado=resultado.replace(':', ' ');
        resultado=resultado.replace('.', ' ');
        resultado=resultado.replace(',', ' ');
        resultado=resultado.replace('%', ' ');
        resultado=resultado.replace('!', ' ');
        resultado=resultado.replace('-', ' ');  
        resultado=resultado.replace('_', ' ');
        resultado=resultado.replace('"', ' ');
        resultado=resultado.replace('(', ' ');
        resultado=resultado.replace(')', ' ');
        resultado=resultado.replace('?', ' ');
        resultado=resultado.replace('¿', ' ');
        resultado=resultado.replace('/', ' ');
        resultado=resultado.replace('”', ' ');
        resultado=resultado.replace('“', ' ');
        resultado=resultado.replace('¡', ' ');

        return resultado;
    }
    
    public String eliminarStopwords(String tweet){
      File archivo = null;
      FileReader fr = null;
      BufferedReader br = null;

      try {
         // Apertura del fichero y creacion de BufferedReader para poder
         // hacer una lectura comoda (disponer del metodo readLine()).
        ClassLoader classLoader = Mineria.class.getClassLoader();
                //Escribo el generador.pl en la carpeta del sistema
        URL url1= classLoader.getResource("mineria/stopwords.txt");
        br = new BufferedReader(new InputStreamReader(url1.openStream()));
        

         // Lectura del fichero
         String linea;
         //ver de hacer stemming con esto
         //Pattern patron = Pattern.compile("@*|http*");
         while((linea=br.readLine())!=null){
            tweet = tweet.toLowerCase();
            tweet = tweet.replaceAll("\\b"+linea+"\\b", "");
           
         //   Matcher encaja = patron.matcher(tweet);
          //  tweet = encaja.replaceAll("");
         }
      }
      catch(Exception e){
         e.printStackTrace();
      }finally{
         // En el finally cerramos el fichero, para asegurarnos
         // que se cierra tanto si todo va bien como si salta 
         // una excepcion.
         try{                    
            if( null != fr ){   
               fr.close();     
            }                  
         }catch (Exception e2){ 
            e2.printStackTrace();
         }
      }
      return tweet;
    }
    
    public String quitaEspaciosExtra(String texto) {
        java.util.StringTokenizer tokens = new java.util.StringTokenizer(texto);
        texto = "";
        while(tokens.hasMoreTokens()){
            texto += " "+tokens.nextToken();
        }
        texto = texto.toString();
        texto = texto.trim();
        return texto;
    }
    
    public String eliminarLinks(String texto){
        String aDevolver="";
        String [] palabras=texto.split(" ");
        for(String tweet : palabras){
            if(! (tweet.contains("http") || tweet.contains("https") || tweet.contains("www."))){
                aDevolver=aDevolver+tweet+" ";
            }
        }
        return aDevolver;
    }
    
    public String eliminarPorAscii(String tweet){
        String aDevolver="";
        String tweetSinArroba="";
        for(String aux : tweet.split(" ")){
            if(!(aux.contains("@")) && !aux.equals("rt")){
                tweetSinArroba=tweetSinArroba+aux+" ";
            }
        }
        for(int i=0;i<tweetSinArroba.length();i++){
            char c=tweetSinArroba.charAt(i);
            int numero=c;
            if((numero>=48 && numero<=57) || (numero >=97 && numero<=122) || numero==164 || numero==32 || numero==64){
                aDevolver=aDevolver+c;
            }
        }
        return aDevolver;
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
}
