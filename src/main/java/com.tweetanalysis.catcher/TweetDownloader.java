package com.tweetanalysis.catcher;

import twitter4j.*;
import twitter4j.conf.ConfigurationBuilder;

import java.io.*;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.List;

/**
 * Created by da on 7/21/17.
 */
public class TweetDownloader {
    /**
     * Usage: java twitter4j.examples.search.SearchTweets [query]
     *
     * @param args search query
     */
    public static void main(String[] args) {
//        if (args.length < 1) {
//            System.out.println("java twitter4j.examples.search.SearchTweets [query]");
//            System.exit(-1);
//        }
        ConfigurationBuilder cb = new ConfigurationBuilder();
        cb.setDebugEnabled(true)
                .setOAuthConsumerKey("*") //hard code for now
                .setOAuthConsumerSecret("*")
                .setOAuthAccessToken("*")
                .setOAuthAccessTokenSecret("*");
        TwitterFactory tf = new TwitterFactory(cb.build());
        Twitter twitter = tf.getInstance();


        // hard coded for right now
        tweetDownloader("$AAPL", twitter);
    }

    public static void tweetDownloader(String queryName, Twitter twitter) {
        DateFormat formatter = new SimpleDateFormat("yyMMddHHmmssZ");

        String filename = "/home/da/src/TweetAnalysisForStock/src/main/resources/input/" + queryName + formatter.format(new java.util.Date()) + ".txt";
        File file = new File(filename);
        try {

            FileOutputStream fos = new FileOutputStream(file);
            DataOutputStream dos = new DataOutputStream(fos);
            Query query = new Query(queryName);
            query.setCount(100);
            int count = 0;
            QueryResult result = null;
    //        byte[] buff = new byte[1024];
            try {
                do {
                    result = twitter.search(query);
                    List<Status> tweets = result.getTweets();
                    for (Status tweet : tweets) {
                        TweetWritable temp = new TweetWritable(tweet.getId(), tweet.getUser(), tweet.getText(), tweet.getCreatedAt());
                        temp.write(dos);
            //            buff = temp.toString().getBytes();
                 //       fos.write(buff);
                        count++;
                    }
                } while ((query = result.nextQuery()) != null);
                System.out.println("Total Message Saved:" + count + "\n" + filename);
                System.exit(0);
            } catch (TwitterException e) {
                e.printStackTrace();
                System.out.println("Failed to search tweets: " + e.getMessage());
                System.exit(-1);
            }
            dos.close();
            fos.close();

        } catch (FileNotFoundException e) {
            e.printStackTrace();
            System.out.println("Failed to create the output file: " + e.getMessage());
            System.exit(-1);
        } catch (IOException e) {
            e.printStackTrace();
            System.out.println("Failed to close the output file: " + e.getMessage());
            System.exit(-1);
        }

    }

}
