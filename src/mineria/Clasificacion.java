/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mineria;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Hashtable;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.util.Pair;
import weka.classifiers.Classifier;
import weka.classifiers.bayes.NaiveBayesUpdateable;
import weka.core.*;
import weka.core.converters.ArffLoader;

public class Clasificacion{
    
    Classifier ClasificadorCristina = null;
    Classifier ClasificadorMassa = null;
    Classifier ClasificadorCambiemos = null;
    Classifier ClasificadorIzquierda = null;
    Classifier ClasificadorRandazzo = null;
    Classifier ClasificadorGeneral = null;
    int umbral=0;
    Preprocesamiento pre=null;
    Postprocesamiento post=null;
    Hashtable<String,Double> tablaDeTerminosCristina=null;
    Hashtable<String,Double> tablaDeTerminosMassa=null;
    Hashtable<String,Double> tablaDeTerminosRandazzo=null;
    Hashtable<String,Double> tablaDeTerminosCambiemos=null;
    Hashtable<String,Double> tablaDeTerminosIzquierda=null;
    Hashtable<String,Double> tablaDeTerminosGlobal=null;
    
    public Clasificacion(){
        try {
            ClasificadorCristina = (Classifier) weka.core.SerializationHelper.read("modelos/CFK.model");
            ClasificadorMassa = (Classifier) weka.core.SerializationHelper.read("modelos/Massa.model");
            ClasificadorCambiemos = (Classifier) weka.core.SerializationHelper.read("modelos/Cambiemos.model");
            ClasificadorIzquierda = (Classifier) weka.core.SerializationHelper.read("modelos/Izquierda.model");
            ClasificadorRandazzo = (Classifier) weka.core.SerializationHelper.read("modelos/Randazzo.model");
            ClasificadorGeneral = (Classifier) weka.core.SerializationHelper.read("modelos/Global.model");
            pre= new Preprocesamiento();
            post= new Postprocesamiento();

            //debo preparar las tablas de terminos con los atributos de cada clasificador
            tablaDeTerminosCristina=new Hashtable<String,Double>();
            llenarTabla(tablaDeTerminosCristina, "headers/headerCristina.arff");
            tablaDeTerminosMassa=new Hashtable<String,Double>();
            llenarTabla(tablaDeTerminosMassa, "headers/headerMassa.arff");
            tablaDeTerminosRandazzo=new Hashtable<String,Double>();
            llenarTabla(tablaDeTerminosRandazzo, "headers/headerRandazzo.arff");
            tablaDeTerminosCambiemos=new Hashtable<String,Double>();
            llenarTabla(tablaDeTerminosCambiemos, "headers/headerCambiemos.arff");
            tablaDeTerminosIzquierda=new Hashtable<String,Double>();
            llenarTabla(tablaDeTerminosIzquierda, "headers/headerIzquierda.arff");
            tablaDeTerminosGlobal=new Hashtable<String,Double>();
            llenarTabla(tablaDeTerminosGlobal, "headers/headerGlobal.arff");
        } catch (Exception e1) {
            e1.printStackTrace();
        }
    }
    
    public void llenarTabla(Hashtable<String,Double> tablaDeTerminos, String nombre){
        try{
            FileReader fichero = new FileReader(nombre);
            BufferedReader pw = new BufferedReader(fichero);
            boolean seguir=true;
            while(seguir){
                String aux=pw.readLine();
                if(aux!=null){
                    if(aux.contains("ATTRIBUTE")){
                        String terminos[] = aux.split(" ");
                        tablaDeTerminos.put(terminos[1], 0.0);
                    }
                }
                else
                    seguir=false;
            }
        }
        catch(Exception e){e.printStackTrace();}
    }
    
    
    public int clasificarCristina(String tweet){
        int aDevolver=0;
        tweet = pre.eliminarStopwords(tweet);
        tweet = pre.eliminarLinks(tweet);
        tweet = pre.sinTildes(tweet);
        tweet = pre.eliminarSimbolos(tweet);
        tweet = pre.quitaEspaciosExtra(tweet);
        tweet = pre.eliminarPorAscii(tweet);
        String auxiliar=tweet.replaceAll(" ", "");
        if(!auxiliar.equals("")){
            List<String> aux=new ArrayList<String>();
            aux.add(tweet);
            List<String> lista=pre.AumentarSensibilidad(aux);
            post.crearARFF(aux, lista, tablaDeTerminosCristina,"tweetCFK.arff");

            Instances unlabeled=null;
            try{
            // load unlabeled data
             unlabeled = new Instances(
                                     new BufferedReader(
                                       new FileReader("tweetCFK.arff")));

            // set class attribute
            unlabeled.setClassIndex(unlabeled.numAttributes() - 1);
            }
            catch(Exception e){e.printStackTrace();}
            double clsLabel=0;
            try {
                clsLabel = ClasificadorCristina.classifyInstance(unlabeled.firstInstance());
                String prediction=unlabeled.classAttribute().value((int)clsLabel);
                System.out.println("p: "+prediction);
            } catch (Exception ex) {
                ex.printStackTrace();
            }
            System.out.println(clsLabel);
            unlabeled.get(0).setClassValue(clsLabel);
            if(clsLabel==0.0) clsLabel=3.0;
            

            aDevolver=(int)clsLabel-2;
            int polaridad=0;
            for(int i : CalcularSensibilidad(tweet, "cristina")){
                if(i!=2) polaridad+=i;
            }
            if(clsLabel == 2.0){
                if(polaridad>umbral) aDevolver=1;
                if(polaridad<-umbral) aDevolver=-1;
            }
        }
        return aDevolver;
    }
    
    public int clasificarMacri(String tweet){
        int aDevolver=0;
        tweet = pre.eliminarStopwords(tweet);
        tweet = pre.eliminarLinks(tweet);
        tweet = pre.sinTildes(tweet);
        tweet = pre.eliminarSimbolos(tweet);
        tweet = pre.quitaEspaciosExtra(tweet);
        tweet = pre.eliminarPorAscii(tweet);
        String auxiliar=tweet.replaceAll(" ", "");
        if(!auxiliar.equals("")){
            List<String> aux=new ArrayList<String>();
            aux.add(tweet);
            List<String> lista=pre.AumentarSensibilidad(aux);
            post.crearARFF(aux, lista, tablaDeTerminosCambiemos,"tweetCambiemos.arff");

            Instances unlabeled=null;
            try{
            // load unlabeled data
             unlabeled = new Instances(
                                     new BufferedReader(
                                       new FileReader("tweetCambiemos.arff")));

            // set class attribute
            unlabeled.setClassIndex(unlabeled.numAttributes() - 1);
            }
            catch(Exception e){e.printStackTrace();}
            double clsLabel=0;
            try {
                clsLabel = ClasificadorCambiemos.classifyInstance(unlabeled.firstInstance());
                String prediction=unlabeled.classAttribute().value((int)clsLabel);
                System.out.println("p: "+prediction);
            } catch (Exception ex) {
                ex.printStackTrace();
            }
            unlabeled.get(0).setClassValue(clsLabel);
            System.out.println(clsLabel);
            if(clsLabel==0.0) clsLabel=3.0;
            System.out.println(unlabeled.get(0).toString());

            aDevolver=(int)clsLabel-2;
            int polaridad=0;
            for(int i : CalcularSensibilidad(tweet, "macri")){
                if(i!=2) polaridad+=i;
            }
            System.out.println(polaridad);
            if(clsLabel == 2.0){
                if(polaridad>umbral) aDevolver=1;
                if(polaridad<-umbral) aDevolver=-1;
            }
        }
        return aDevolver;
    }
        
    public int clasificarRandazzo(String tweet){
        int aDevolver=0;
        tweet = pre.eliminarStopwords(tweet);
        tweet = pre.eliminarLinks(tweet);
        tweet = pre.sinTildes(tweet);
        tweet = pre.eliminarSimbolos(tweet);
        tweet = pre.quitaEspaciosExtra(tweet);
        tweet = pre.eliminarPorAscii(tweet);
        List<String> aux=new ArrayList<String>();
        String auxiliar=tweet.replaceAll(" ", "");
        if(!auxiliar.equals("")){
            aux.add(tweet);
            List<String> lista=pre.AumentarSensibilidad(aux);
            post.crearARFF(aux, lista, tablaDeTerminosRandazzo,"tweetRandazzo.arff");

            Instances unlabeled=null;
            try{
            // load unlabeled data
             unlabeled = new Instances(
                                     new BufferedReader(
                                       new FileReader("tweetRandazzo.arff")));

            // set class attribute
            unlabeled.setClassIndex(unlabeled.numAttributes() - 1);
            }
            catch(Exception e){e.printStackTrace();}
            double clsLabel=0;
            try {
                clsLabel = ClasificadorRandazzo.classifyInstance(unlabeled.firstInstance());
                String prediction=unlabeled.classAttribute().value((int)clsLabel);
                System.out.println("p: "+prediction);
            } catch (Exception ex) {
                ex.printStackTrace();
            }
            System.out.println(clsLabel);
            unlabeled.get(0).setClassValue(clsLabel);
            if(clsLabel==0.0) clsLabel=3.0;
            System.out.println(unlabeled.get(0).toString());

            aDevolver=(int)clsLabel-2;
            int polaridad=0;
            for(int i : CalcularSensibilidad(tweet, "randazzo")){
                if(i!=2) polaridad+=i;
            }
            System.out.println(polaridad);
            if(clsLabel == 2.0){
                if(polaridad>umbral) aDevolver=1;
                if(polaridad<-umbral) aDevolver=-1;
            }
        }
        return aDevolver;
    }
    
    public int clasificarPitrola(String tweet){
        int aDevolver=0;
        tweet = pre.eliminarStopwords(tweet);
        tweet = pre.eliminarLinks(tweet);
        tweet = pre.sinTildes(tweet);
        tweet = pre.eliminarSimbolos(tweet);
        tweet = pre.quitaEspaciosExtra(tweet);
        tweet = pre.eliminarPorAscii(tweet);
        String auxiliar=tweet.replaceAll(" ", "");
        if(!auxiliar.equals("")){
            List<String> aux=new ArrayList<String>();
            aux.add(tweet);
            List<String> lista=pre.AumentarSensibilidad(aux);
            post.crearARFF(aux, lista, tablaDeTerminosIzquierda,"tweetIzquierda.arff");

            Instances unlabeled=null;
            try{
            // load unlabeled data
             unlabeled = new Instances(
                                     new BufferedReader(
                                       new FileReader("tweetIzquierda.arff")));

            // set class attribute
            unlabeled.setClassIndex(unlabeled.numAttributes() - 1);
            }
            catch(Exception e){e.printStackTrace();}
            double clsLabel=0;
            try {
                clsLabel = ClasificadorIzquierda.classifyInstance(unlabeled.firstInstance());
                String prediction=unlabeled.classAttribute().value((int)clsLabel);
                System.out.println("p: "+prediction);
            } catch (Exception ex) {
                ex.printStackTrace();
            }
            System.out.println(clsLabel);
            unlabeled.get(0).setClassValue(clsLabel);
            if(clsLabel==0.0) clsLabel=3.0;
            System.out.println(unlabeled.get(0).toString());

            aDevolver=(int)clsLabel-2;
            int polaridad=0;
            for(int i : CalcularSensibilidad(tweet, "pitrola")){
                if(i!=2) polaridad+=i;
            }
            System.out.println(polaridad);
            if(clsLabel == 2.0){
                if(polaridad>umbral) aDevolver=1;
                if(polaridad<-umbral) aDevolver=-1;
            }
        }
        return aDevolver;
    }
    
    public int clasificarMassa(String tweet){
        int aDevolver=0;
        tweet = pre.eliminarStopwords(tweet);
        tweet = pre.eliminarLinks(tweet);
        tweet = pre.sinTildes(tweet);
        tweet = pre.eliminarSimbolos(tweet);
        tweet = pre.quitaEspaciosExtra(tweet);
        tweet = pre.eliminarPorAscii(tweet);
        String auxiliar=tweet.replaceAll(" ", "");
        if(!auxiliar.equals("")){
            List<String> aux=new ArrayList<String>();

            aux.add(tweet);
            List<String> lista=pre.AumentarSensibilidad(aux);
            post.crearARFF(aux, lista, tablaDeTerminosMassa,"tweetMassa.arff");

            Instances unlabeled=null;
            try{
            // load unlabeled data
             unlabeled = new Instances(
                                     new BufferedReader(
                                       new FileReader("tweetMassa.arff")));

            // set class attribute
            unlabeled.setClassIndex(unlabeled.numAttributes() - 1);
            }
            catch(Exception e){e.printStackTrace();}
            double clsLabel=0;
            try {
                clsLabel = ClasificadorMassa.classifyInstance(unlabeled.firstInstance());
                String prediction=unlabeled.classAttribute().value((int)clsLabel);
                System.out.println("p: "+prediction);
            } catch (Exception ex) {
                ex.printStackTrace();
            }
            System.out.println(clsLabel);
            unlabeled.get(0).setClassValue(clsLabel);
            if(clsLabel==0) clsLabel=3;
            System.out.println(unlabeled.get(0).toString());

            aDevolver=(int)clsLabel-2;
            int polaridad=0;
            for(int i : CalcularSensibilidad(tweet, "massa")){
                if(i!=2) polaridad+=i;
            }
            System.out.println(polaridad);
            if(clsLabel == 2.0){
                if(polaridad>umbral) aDevolver=1;
                if(polaridad<-umbral) aDevolver=-1;
            }
        }
        return aDevolver;
    }
    
    public int clasificar(String tweet){
        int aDevolver=0;
        tweet = pre.eliminarStopwords(tweet);
        tweet = pre.eliminarLinks(tweet);
        tweet = pre.sinTildes(tweet);
        tweet = pre.eliminarSimbolos(tweet);
        tweet = pre.quitaEspaciosExtra(tweet);
        tweet = pre.eliminarPorAscii(tweet);
        String auxiliar=tweet.replaceAll(" ", "");
        if(!auxiliar.equals("")){
            List<String> aux=new ArrayList<String>();
            aux.add(tweet);
            List<String> lista=pre.AumentarSensibilidad(aux);
            post.crearARFF(aux, lista, tablaDeTerminosGlobal,"tweetGlobal.arff");

            Instances unlabeled=null;
            try{
            // load unlabeled data
             unlabeled = new Instances(
                                     new BufferedReader(
                                       new FileReader("tweetGlobal.arff")));

            // set class attribute
            unlabeled.setClassIndex(unlabeled.numAttributes() - 1);
            }
            catch(Exception e){e.printStackTrace();}
            double clsLabel=0;
            try {
                clsLabel = ClasificadorGeneral.classifyInstance(unlabeled.firstInstance());
                String prediction=unlabeled.classAttribute().value((int)clsLabel);
                System.out.println("p: "+prediction);
            } catch (Exception ex) {
                ex.printStackTrace();
            }
            System.out.println(clsLabel);
            unlabeled.get(0).setClassValue(clsLabel);
            if(clsLabel==0) clsLabel=3;
            System.out.println(unlabeled.get(0).toString());
            aDevolver=(int)clsLabel-2;
            int polaridad=0;
            for(int i : CalcularSensibilidad(tweet, "esp")){
                if(i!=2) polaridad+=i;
            }
            System.out.println(polaridad);
            if(clsLabel == 2.0){
                if(polaridad>umbral) aDevolver=1;
                if(polaridad<-umbral) aDevolver=-1;
            }
        }
        return aDevolver;
    }

    
    protected int[] CalcularSensibilidad(String Tweet,String candidato){
      FileReader fichero = null;
      String[] arreglo= Tweet.split(" ");
      int[] aDevolver=new int[arreglo.length];
      for(int i=0;i<arreglo.length;i++){
          aDevolver[i]=0;
      }
      try {
        fichero = new FileReader("palabrasPositivas.txt");
        BufferedReader br = new BufferedReader(fichero);
        String linea=null;
        int pos=0;
        while((linea=br.readLine())!=null){
            for(String s: arreglo){
                if(s.equals(linea)){
                    aDevolver[pos]=1;
                }
                if(s.equals(candidato)){
                    aDevolver[pos]=2;
                }
                pos++;
            }
            pos=0;
         }
        fichero = new FileReader("palabrasNegativas.txt");
        br = new BufferedReader(fichero);
        pos=0;
        while((linea=br.readLine())!=null){
            for(String s: arreglo){
                if(s.equals(linea)){
                    aDevolver[pos]=-1;
                }
                pos++;
            }
            pos=0;
        }
      }
      catch(Exception e){
         e.printStackTrace();
      }finally{
         try{                    
               fichero.close();                     
         }catch (Exception e2){ 
            e2.printStackTrace();
         }
      }
      return aDevolver;
    }
    
    
    protected int CalcularPuntaje(String Tweet){
      FileReader fichero = null;
      String[] arreglo= Tweet.split(" ");
      int puntaje=0;
      try {
        fichero = new FileReader("palabrasPositivas.txt");
        BufferedReader br = new BufferedReader(fichero);
        String linea=null;
        while((linea=br.readLine())!=null){
            for(String s: arreglo){
                if(s.equals(linea)){
                    puntaje++;
                }
            }
         }
        fichero = new FileReader("palabrasNegativas.txt");
        br = new BufferedReader(fichero);
        while((linea=br.readLine())!=null){
            for(String s: arreglo){
                if(s.equals(linea)){
                    puntaje--;
                }
            }
        }
      }
      catch(Exception e){
         e.printStackTrace();
      }finally{
         try{                    
               fichero.close();                     
         }catch (Exception e2){ 
            e2.printStackTrace();
         }
      }
      return puntaje;
    }
    
    
    
    
    public String desempatar(List<Pair<String,List<String>>> lista){
        int puntajeMaximo=-1000;
        int puntajeActual=0;
        String candidatoActual="";
        String ganador="";
        boolean hayEmpate=false;
        int cantEmpatados=0;
        String [] empatados=new String[5];
        for(int p=0;p<5;p++){
            empatados[p]="";
        }
        
        for(Pair<String, List<String>> par : lista){
            candidatoActual=par.getKey();
            for(String tweet : par.getValue()){
                puntajeActual+=CalcularPuntaje(tweet);
            }
            //puntajeActual=sumatoria de palabras positivas-negativas en los tweets acerca del candidato actual
            if(puntajeActual>puntajeMaximo){
                puntajeMaximo=puntajeActual;
                ganador=candidatoActual;
                hayEmpate=false;
            }
            else{
                if(puntajeActual==puntajeMaximo){
                    hayEmpate=true;
                    empatados[cantEmpatados]=candidatoActual;
                    cantEmpatados++;
                }
            }
            puntajeActual=0;
        }
        if(hayEmpate){
            int aleatorio=(int)Math.random()*cantEmpatados;
            ganador=empatados[aleatorio];
        }
        return ganador;
    }
}
