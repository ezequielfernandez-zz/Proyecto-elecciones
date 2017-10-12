/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mineria;

import java.util.ArrayList;
import java.util.List;
import twitter4j.FilterQuery;
import twitter4j.Query;
import twitter4j.QueryResult;
import twitter4j.StallWarning;
import twitter4j.Status;
import twitter4j.StatusDeletionNotice;
import twitter4j.StatusListener;
import twitter4j.Twitter;
import twitter4j.TwitterException;
import twitter4j.TwitterFactory;
import twitter4j.TwitterStream;
import twitter4j.TwitterStreamFactory;
import twitter4j.conf.Configuration;
import twitter4j.conf.ConfigurationBuilder;

/**
 *
 * @author ezee
 */
public class ConexionTwitter {
    private TwitterFactory tf;
    private Twitter twitter;
    private ConfigurationBuilder cb;
    private TwitterStream twitterStream;
    private List<String> listaTweets = new ArrayList();
    private Configuration conf;
    
    public ConexionTwitter(){
            //Autentico la aplicacion con twitter, con las claves obtenidas
        cb = new ConfigurationBuilder();
        cb.setDebugEnabled(true)
          .setOAuthConsumerKey("iVtlSSONvcQ2CHfs55j4ILBPs")
          .setOAuthConsumerSecret("qMbT4PrLNVRZCNQpCeE9NEhwPG9sUld0hGbAO0XzUgTAZpTOCi")
          .setOAuthAccessToken("1699786572-qVAP1rD4vRv44JrRPFDYl2L3KC3jcR7ZKV9kLb1")
          .setOAuthAccessTokenSecret("55Dk6T3MvSNhkRhPcgP8yZ1E9tB2W5SsgEsevPtB1Cy8E");
        listaTweets=new ArrayList<String>();
         conf=cb.build();
    }
    
        public void searchTweets(String claves){
        try { 
            // The factory instance is re-useable and thread safe.
            tf = new TwitterFactory(cb.build());
            twitter = tf.getInstance();
            Query query = new Query(claves);
            QueryResult result = twitter.search(query);

            for (Status status : result.getTweets()) {
                System.out.println("@" + status.getUser().getScreenName() + ":" + status.getText());
            }

            System.out.println(result);
        }catch(TwitterException e){}
    }
        
        
    public List<String> streamingAPI(String[] keywords){
        StatusListener listener = new StatusListener(){
            int i=1;
            public void onStatus(Status status) {
                //Agrego los tweets a la lista de tweets y los muestro por pantalla
                listaTweets.add(status.getText());
                System.out.println(i+") "+status.getUser().getName() + " : " + status.getText());
                i++;
            }
            public void onDeletionNotice(StatusDeletionNotice statusDeletionNotice) {}
            public void onTrackLimitationNotice(int numberOfLimitedStatuses) {}
            public void onException(Exception ex) {
                ex.printStackTrace();
            }

            @Override
            public void onScrubGeo(long l, long l1) {
                throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
            }

            @Override
            public void onStallWarning(StallWarning sw) {
                throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
            }
        };
        twitterStream = new TwitterStreamFactory(conf).getInstance();
        twitterStream.addListener(listener);
        // sample() method internally creates a thread which manipulates TwitterStream and calls these adequate listener methods continuously.
        twitterStream.sample();
        FilterQuery tweetFilterQuery = new FilterQuery(); // See 
        tweetFilterQuery.track(keywords); // OR on keywords 
        tweetFilterQuery.language(new String[]{"es"}); //Solo tweets en español
        twitterStream.filter(tweetFilterQuery); 
        
        return listaTweets;
    }
    
    public List<String> pararRecopilacion(){
        twitterStream.cleanUp();
        List<String> l=listaTweets;
        listaTweets=new ArrayList();
        return l;
    }
    
    
    public List<String> restAPI(String texto){
        listaTweets=new ArrayList();
        twitter = new TwitterFactory(conf).getInstance();
        int i=0;
        try {
            Query query = new Query(texto);
            QueryResult result;
            do {
                result = twitter.search(query);
                List<Status> tweets = result.getTweets();
                for (Status tweet : tweets) {
                    //System.out.println(tweet.getLang());
                    if(tweet.getLang().equals("es")){
                        listaTweets.add("@" + tweet.getUser().getScreenName() + " - " + tweet.getText());
                    }
                }
            } while ((query = result.nextQuery()) != null);
        } catch (TwitterException te) {
            te.printStackTrace();
            System.out.println("Failed to search tweets: " + te.getMessage());
            System.exit(-1);
        }
        return listaTweets;
    }
    
}
