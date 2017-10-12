/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mineria;

/**
 *
 * @author ezee
 */
public class Par implements Comparable{
    
    String tweet;
    double cos;
    public Par(String Tweet, double coseno){
        tweet=Tweet;
        cos=coseno;
    }
    public double getCos(){
        return cos;
    }
    public String getTweet(){
        return tweet;
    }

    @Override
    public int compareTo(Object o) {
        Par p= (Par)o;
        int toret=0;
        if(cos<p.getCos()) toret=-1;
        else if(cos>p.getCos()) toret=1;
        return toret;
    }
    

}
