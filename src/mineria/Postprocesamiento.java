/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mineria;

import java.io.FileWriter;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Enumeration;
import java.util.Hashtable;
import java.util.List;

/**
 *
 * @author ezee
 */
public class Postprocesamiento {
    
    private double[][] matrizTF,matrizTFIDF;
    private Hashtable<String,Double> tablaDeTerminos;
    private List<String> listaTweetsSinSW,listaTweets;
    private int topeN=0;
    private int topeP=0;
    private int topeNeutro=0;
    
    public Postprocesamiento(){
        
    }
    
    
    public void setNPN(int [] arreglo){
        topeN=arreglo[0];
        topeP=arreglo[1];
        topeNeutro=arreglo[2];
    }
    
        public double[][] crearArchivoARFFsparse(List<String> listaTweets,List<String> listaTweetsSinSW,Hashtable<String,Double> tablaDeTerminos){
        this.listaTweetsSinSW=listaTweetsSinSW;
        this.tablaDeTerminos=tablaDeTerminos;
        this.listaTweets=listaTweets;
        double[][] matrizTFIDF = this.calcularFrecuenciaTFIDF();
        FileWriter fichero = null;
        PrintWriter pw = null;
        try
        {
            fichero = new FileWriter("./MatrizTFIDF-arff.txt");
            pw = new PrintWriter(fichero);
            pw.println("@RELATION matriztfidf");pw.println();
            Enumeration claves=tablaDeTerminos.keys();
            int atributos=0;
            while(claves.hasMoreElements()){
                pw.println("@ATTRIBUTE "+claves.nextElement().toString()+" NUMERIC");
                atributos++;
            }
            pw.println("@ATTRIBUTE Clase {'P','N','Neutro'}");
            pw.println();pw.println();
            
            pw.println("@DATA");
            boolean primero = false; 
            for (int x=0; x < matrizTFIDF.length; x++) {
                primero = false;   
                for (int y=0; y < matrizTFIDF[x].length; y++) {
                  if(matrizTFIDF[x][y]!=0){
                      if(!primero){
                          pw.print("{");
                          pw.print(y+" "+matrizTFIDF[x][y]);
                          primero=true;
                      }
                      else{
                          pw.print(", "+y+" "+matrizTFIDF[x][y]);
                      }
                  }
                  
                }
                
                pw.println("}");
            }

        } catch (Exception e) {
            e.printStackTrace();
        } finally {
           try {
           if (null != fichero)
              fichero.close();
           } catch (Exception e2) {
              e2.printStackTrace();
           }
        }
        return matrizTFIDF;
    }
    
    public double[][] crearArchivoARFFsparse1(List<String> listaTweets,List<String> listaTweetsSinSW,Hashtable<String,Double> tablaDeTerminos,List<Par> listaPares){
        this.listaTweetsSinSW=listaTweetsSinSW;
        this.tablaDeTerminos=tablaDeTerminos;
        this.listaTweets=listaTweets;
        double[][] matrizTFIDF = this.calcularFrecuenciaTFIDF();
        matrizTFIDF=this.getMatrizTF();
        FileWriter fichero = null;
        PrintWriter pw = null;
        try
        {
            fichero = new FileWriter("./unlabeledMassa.arff");
            pw = new PrintWriter(fichero);
            pw.println("@RELATION matriztfidf");pw.println();
            Enumeration claves=tablaDeTerminos.keys();
            int atributos=0;
            while(claves.hasMoreElements()){
                pw.println("@ATTRIBUTE "+claves.nextElement().toString()+" NUMERIC");
                atributos++;
            }
           // pw.println("@ATTRIBUTE CadidatoAClasificar NUMERIC");
            //pw.println("@ATTRIBUTE Clase {'P','N','Neutro'}");
            pw.println();pw.println();
           // atributos++;
            int candidato=atributos-1;
            pw.println("@DATA");
            boolean primero = false; 
            for (int x=0; x < matrizTFIDF.length; x++) {
                primero = false;   
                for (int y=0; y < matrizTFIDF[x].length; y++) {
                  if(matrizTFIDF[x][y]!=0){
                      if(!primero){
                          pw.print("{");
                          pw.print(y+" "+matrizTFIDF[x][y]);
                          primero=true;
                      }
                      else{
                          pw.print(", "+y+" "+matrizTFIDF[x][y]);
                      }
                  }
                  
                }
               /* if(x<=154) pw.print(", "+candidato+" 1");
                if(x>154 && x<=319) pw.print(", "+candidato+" 2");
                    if(x>319 && x<=479) pw.print(", "+candidato+" 3");
                    if(x>479 && x<=639) pw.print(", "+candidato+" 4");
                    if(x>639 && x<=804) pw.print(", "+candidato+" 5");*/
               /* if(listaPares.get(x).getCos()==1.00){
                    pw.print(", "+atributos+" "+"'P'");
                }
                else{
                    if(listaPares.get(x).getCos()==-1.00){
                        pw.print(", "+atributos+" "+"'N'");
                    }
                    else{
                        if(listaPares.get(x).getCos()==0.00){
                            pw.print(", "+atributos+" "+"'Neutro'");
                        }
                    }
                }*/
                pw.println("}");
            }

        } catch (Exception e) {
            e.printStackTrace();
        } finally {
           try {
           if (null != fichero)
              fichero.close();
           } catch (Exception e2) {
              e2.printStackTrace();
           }
        }
        return matrizTFIDF;
    }
    
    
    public double[][] crearARFF(List<String> listaTweets,List<String> listaTweetsSinSW,Hashtable<String,Double> tablaDeTerminos, String nombre){
        this.listaTweetsSinSW=listaTweetsSinSW;
        this.tablaDeTerminos=tablaDeTerminos;
        this.listaTweets=listaTweets;
        double[][] matrizTFIDF = this.calcularFrecuenciaTFIDF();
        matrizTFIDF=this.getMatrizTF();
        double suma=0;
        for(double[] a: matrizTFIDF){
            for(double b: a){
                suma+=b;
            }
        }
        if(suma==0){
            matrizTFIDF[0][matrizTFIDF.length-1]=1.0;
        }
        FileWriter fichero = null;
        PrintWriter pw = null;
        try
        {
            fichero = new FileWriter(nombre);
            pw = new PrintWriter(fichero);
            pw.println("@RELATION matriztfidf");pw.println();
            Enumeration claves=tablaDeTerminos.keys();
            int atributos=0;
            while(claves.hasMoreElements()){
                pw.println("@ATTRIBUTE "+claves.nextElement().toString()+" NUMERIC");
                atributos++;
            }
            pw.println("@ATTRIBUTE Clase {'P','N','Neutro'}");
            pw.println();pw.println();
           // atributos++;
            pw.println("@DATA");
            boolean primero = false; 
            for (int x=0; x < matrizTFIDF.length; x++) {
                primero = false;   
                for (int y=0; y < matrizTFIDF[x].length; y++) {
                  if(matrizTFIDF[x][y]!=0){
                      if(!primero){
                          pw.print("{");
                          pw.print(y+" "+matrizTFIDF[x][y]);
                          primero=true;
                      }
                      else{
                          pw.print(", "+y+" "+matrizTFIDF[x][y]);
                      }
                  }
                  else{
                  }
                }
                pw.println("}");
            }

        } catch (Exception e) {
            e.printStackTrace();
        } finally {
           try {
           if (null != fichero)
              fichero.close();
           } catch (Exception e2) {
              e2.printStackTrace();
           }
        }
        return matrizTFIDF;
    }
    
    public double [][] getMatrizTF(){
        return matrizTF;
    }
    
    public void setTFIDF(double[][] MatrizTFIDFnueva){
        matrizTFIDF=MatrizTFIDFnueva;
    }
    public double[][] calcularFrecuenciaTFIDF(){
        
        //creo la matriz de cantidadTweets x terminos
        matrizTFIDF = new double[listaTweets.size()][tablaDeTerminos.size()];
        matrizTF = new double[listaTweets.size()][tablaDeTerminos.size()];
        for (int documentos=0;documentos<listaTweets.size();documentos++) {
            for (int palabras = 0; palabras < tablaDeTerminos.size(); palabras++) {
                matrizTFIDF[documentos][palabras] = 0;
            }
        }
        String clave="";
        int i=0;
        int j;
        String elemento;
        int frecTF;
        for (String tweet : listaTweetsSinSW) {
            j=0;
            Enumeration claves=tablaDeTerminos.keys();
            while(claves.hasMoreElements()){
                elemento = claves.nextElement().toString();
                frecTF=this.calcularTF(tweet, elemento);
                matrizTFIDF[i][j]=frecTF;
                matrizTF[i][j]=frecTF;
                //guardo en el value de tablaDeTerminos la cantidad de documentos que contienen ese termino
                if(frecTF!=0) tablaDeTerminos.put(elemento, tablaDeTerminos.get(elemento)+1);
                j++;
            }
            i++;
        }
        //ahora guardo en el value de tablaDeTerminos el IDF corresp a cada termino
        int N=listaTweets.size();
        Double[] vectorConIDFs = new Double[tablaDeTerminos.size()];
        i=0;
        Double IDF;
        Enumeration claves=tablaDeTerminos.keys();
        while(claves.hasMoreElements()){
            elemento = claves.nextElement().toString();
            IDF=(double)Math.round( Math.log10(N/tablaDeTerminos.get(elemento))*1000000)/1000000;
            vectorConIDFs[i]=IDF;
            i++;
        }
        //en este punto tengo la matriz TF y el hashtable con cada termino y su corresp IDF
        //procedo a calcular TFIDF sobre la matriz
        for (int x=0; x < matrizTFIDF.length; x++) {
            for (int y=0; y < matrizTFIDF[x].length; y++) {
              matrizTFIDF[x][y]=matrizTFIDF[x][y]*vectorConIDFs[y];
            }
        }
        return matrizTFIDF;
    }
    
    

    public double[][] calcularSimilaridadCoseno(double[][] matrizTF){
        double[][] matrizSimilaridad = new double[matrizTF.length][matrizTF.length];
        int i=0;
        int j=0;
        for (int x=0; x < matrizSimilaridad.length; x++) {
            j=0;
            for (int y=0; y < matrizSimilaridad[x].length; y++) {
              matrizSimilaridad[x][y]=(double)Math.round((this.cosineSimilarity(matrizTFIDF[x], matrizTFIDF[y]))*1000)/1000;
              j++;
            }
            i++;
        }
        return matrizSimilaridad;
    }
    
    public double cosineSimilarity(double[] docVector1, double[] docVector2) {
        double dotProduct = 0.0;
        double magnitude1 = 0.0;
        double magnitude2 = 0.0;
        double cosineSimilarity = 0.0;

        for (int i = 0; i < docVector1.length; i++) //docVector1 and docVector2 must be of same length
        {
            dotProduct += docVector1[i] * docVector2[i];  //a.b
            magnitude1 += Math.pow(docVector1[i], 2);  //(a^2)
            magnitude2 += Math.pow(docVector2[i], 2); //(b^2)
        }

        magnitude1 = Math.sqrt(magnitude1);//sqrt(a^2)
        magnitude2 = Math.sqrt(magnitude2);//sqrt(b^2)

        if (magnitude1 != 0.0 && magnitude2 != 0.0) {
            cosineSimilarity = dotProduct / (magnitude1 * magnitude2);
        } else {
            return 0.0;
        }
        return cosineSimilarity;
    }

    

    public int calcularTF(String tweet, String palabra){
        int result = 0;
        for (String palabraTweet : tweet.split(" ")) {
           if (palabra.equalsIgnoreCase(palabraTweet))
                  result++;
        }
        return result;
    }
}
