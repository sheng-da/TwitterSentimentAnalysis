package com.tweetanalysis.catcher;

import twitter4j.*;
import twitter4j.conf.ConfigurationBuilder;

import java.io.*;
import java.util.List;

/**
 * Created by da on 7/21/17.
 */
public class TweetSourceReader {
    public static void main(String[] args) {
        String filename = "/home/da/src/TweetAnalysisForStock/src/main/resources/input/" + "$AAPL" + ".txt"; //hard coded for now
        File file = new File(filename);
        int count = 0;
        TweetWritable buff = new TweetWritable();
        try {

            FileInputStream fis = new FileInputStream(file);
            DataInputStream dis = new DataInputStream(fis);

            try {
                while(true) {
                    buff.readFields(dis);
                    System.out.println(buff.toString());
                    count++;
                }

            } catch (EOFException e) {
                System.out.println("Total Message Read:" + count + "\n" + filename);
                System.exit(0);
            }
            dis.close();
            fis.close();

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
