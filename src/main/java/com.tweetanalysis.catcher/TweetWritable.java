package com.tweetanalysis.catcher;

import java.io.DataInput;
import java.io.DataOutput;
import java.io.IOException;
import java.util.Date;
import twitter4j.*;
import org.apache.hadoop.io.WritableComparable;


/**
 * Created by da on 7/21/17.
 */
public class TweetWritable implements WritableComparable<TweetWritable> {


     private long tweetId;
     private long tweetUserId;
     private String tweetText;
     private String tweetTimestamp;

     public TweetWritable(long tweetId, User tweetUser, String tweetText, Date tweetTimestamp) {
          super();
          this.tweetId = tweetId;
          this.tweetUserId = tweetUser.getId(); // get user ID for now
          this.tweetText = tweetText; // to string for now
          this.tweetTimestamp = tweetTimestamp.toString(); //store string for now
     }

     public TweetWritable(){

     }

     // setter  // builder pattern

     public long getTweetId() {
          return tweetId;
     }

     public long getTweetUserId() {
          return tweetUserId;
     }

     public String getTweetText() {
          return tweetText;
     }

     public String getTweetTimestamp() {
          return tweetTimestamp;
     }



     //read files
     public void readFields(DataInput input) throws IOException {
          this.tweetId = input.readLong();
          this.tweetUserId = input.readLong();
          this.tweetText = input.readUTF();
          this.tweetTimestamp = input.readUTF();
     }

     // write fields
     public void write(DataOutput output) throws IOException {
          output.writeLong(tweetId);
          output.writeLong(tweetUserId);
          output.writeUTF(tweetText);
          output.writeUTF(tweetTimestamp);
     }


     @Override
     public String toString(){
          return tweetUserId + "\t" + tweetUserId + "\t" + tweetText + "\t" + tweetTimestamp;
     }

     // compare
     // under construction
     public int compareTo(TweetWritable tweetWritable) {
          return 0;
     }

}
