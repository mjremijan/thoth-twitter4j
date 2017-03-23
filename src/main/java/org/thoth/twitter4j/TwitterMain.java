package org.thoth.twitter4j;

import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.util.Arrays;
import java.util.Collections;
import java.util.LinkedList;
import java.util.List;
import java.util.Properties;
import java.util.stream.Collectors;
import org.apache.commons.lang3.builder.ToStringBuilder;
import twitter4j.GeoLocation;
import twitter4j.HashtagEntity;
import twitter4j.Status;
import twitter4j.Twitter;
import twitter4j.TwitterFactory;
import twitter4j.conf.ConfigurationBuilder;

/**
 * http://twitter.com/oauth_clients/new
 *
 * @author Michael Remijan mjremijan@yahoo.com @mjremijan Remijan mjremijan@yahoo.com @mjremijan
 */
public class TwitterMain {

    public static final void main(String[] args) throws Exception {

        Properties properties = new Properties();
        properties.load(new FileReader("twitter.properties"));
        //System.out.printf("PROPERTIES%n%s%n", properties.toString());

        ConfigurationBuilder cb = new ConfigurationBuilder();
        cb.setDebugEnabled(true).setOAuthConsumerKey(properties.getProperty("oAuthConsumerKey"))
            .setOAuthConsumerSecret(properties.getProperty("oAuthConsumerSecret"))
            .setOAuthAccessToken(properties.getProperty("oAuthAccessToken"))
            .setOAuthAccessTokenSecret(properties.getProperty("oAuthAccessTokenSecret"));

        TwitterFactory tf = new TwitterFactory(cb.build());

        Twitter twitter = tf.getInstance();
        // Query query = new Query("source:twitter4j mjremijan");
        // Query query = new Query("babylon5");
        // QueryResult result = twitter.search(query);
        // List<Status> statuses = result.getTweets();
        // print(statuses);
        List<Status> statuses
            // = twitter.getHomeTimeline();
            = new LinkedList<>();

        // 844661380821209089
        // ...  3 dot elipsis
        // 844939775173808129
        // pirate character
        // 810880536528977920
        // The fancy double-quote displays as ?
        // The fancy single-quote displays as ?
        // The video preview does not appear
        Collections.addAll(statuses, twitter.showStatus(810880536528977920L));

        print(statuses);

    }

    private static void print(List<Status> statuses) throws Exception {
        System.out.printf("satuses size=%d\n", statuses.size());

        for (Status s : statuses) {
            System.out.println("======================================================================================");

            print(s);

            /*

            // Profile image
            String profileImageUrl = s.getUser().getProfileImageURL();
            System.out.println(profileImageUrl);

            // Name
            String name = s.getUser().getName();
            // Screen name
            String screenName = s.getUser().getScreenName();
            // Time since tweet
            SimpleDateFormat sdf = new SimpleDateFormat("(EEE, dd MMM yyyy, hh:mm:ss a)");
            System.out.println(String.format("%s @%s  %s", name, screenName, sdf.format(s.getCreatedAt())));

            // Text
            String text = s.getText();
            {
                // Hyperlinks
                URLEntity[] urlEntities = s.getURLEntities();
                if (urlEntities != null) {
                    for (URLEntity urlEntity : urlEntities) {
                        text = text.replaceAll(urlEntity.getURL(), urlEntity.getExpandedURL());
                    }
                }
            }
            System.out.println(text);

            // User mentions
            UserMentionEntity[] userMentionEntities = s.getUserMentionEntities();
            if (userMentionEntities != null) {
                System.out.println("[USER MENTIONS]");
                for (UserMentionEntity entity : userMentionEntities) {
                    System.out.println(ToStringBuilder.reflectionToString(entity));
                }
            }

            // URL Entities
            URLEntity[] urlEntities = s.getURLEntities();
            if (urlEntities != null) {
                System.out.println("[URL ENTITIES]");
                for (URLEntity entity : urlEntities) {
                    System.out.println(ToStringBuilder.reflectionToString(entity));
                }
            }
             */
//            System.out.println("--------------------------------------------------------------------------------------");
//            System.out.println(ToStringBuilder.reflectionToString(s));
            System.out.println("======================================================================================");
        }
    }

    private static void print(Status s) throws Exception {
        // TEXT
        if (s == null) {
            System.out.printf("Status parameter is NULL%n%n");
            return;
        }
        System.out.printf("#getAccessLevel()%n%s%n%n", String.valueOf(s.getAccessLevel()));
        System.out.printf("#getContributors()%n%s%n%n", String.valueOf(Arrays.stream(s.getContributors()).boxed().collect(Collectors.toList())));
        System.out.printf("#getCreatedAt()%n%s%n%n", String.valueOf(s.getCreatedAt()));
        System.out.printf("#getCurrentUserRetweetId()%n%s%n%n", String.valueOf(s.getCurrentUserRetweetId()));
        System.out.printf("#getFavoriteCount()%n%s%n%n", String.valueOf(s.getFavoriteCount()));
        printGeolocation(s.getGeoLocation());
        printHashTagEntities(s.getHashtagEntities());
        System.out.printf("#getId()%n%s%n%n", String.valueOf(s.getId()));
        System.out.printf("#getInReplyToScreenName()%n%s%n%n", String.valueOf(s.getInReplyToScreenName()));
        System.out.printf("#getInReplyToStatusId()%n%s%n%n", String.valueOf(s.getInReplyToStatusId()));
        System.out.printf("#getInReplyToUserId()%n%s%n%n", String.valueOf(s.getInReplyToUserId()));
        System.out.printf("#getLang()%n%s%n%n", String.valueOf(s.getLang()));
        //System.out.printf("#XXX()%n%s%n%n", String.valueOf(s.getMediaEntities()));
        //System.out.printf("#XXX()%n%s%n%n", String.valueOf(s.getPlace()));
        //System.out.printf("#XXX()%n%s%n%n", String.valueOf(s.getQuotedStatus()));
        System.out.printf("#getQuotedStatusId()%n%s%n%n", String.valueOf(s.getQuotedStatusId()));
        //System.out.printf("#XXX()%n%s%n%n", String.valueOf(s.getRateLimitStatus()));
        System.out.printf("#getRetweetCount()%n%s%n%n", String.valueOf(s.getRetweetCount()));
        //System.out.printf("#XXX()%n%s%n%n", String.valueOf(s.getRetweetedStatus()));
        //System.out.printf("#XXX()%n%s%n%n", String.valueOf(s.getScopes()));
        System.out.printf("#getSource()%n%s%n%n", String.valueOf(s.getSource()));
        //System.out.printf("#XXX()%n%s%n%n", String.valueOf(s.getSymbolEntities()));
        printText(s);
        //System.out.printf("#XXX()%n%s%n%n", String.valueOf(s.getURLEntities()));
        //System.out.printf("#XXX()%n%s%n%n", String.valueOf(s.getUser()));
        //System.out.printf("#XXX()%n%s%n%n", String.valueOf(s.getUserMentionEntities()));
        System.out.printf("#getWithheldInCountries()%n%s%n%n", String.valueOf(s.getWithheldInCountries()));
        System.out.printf("#isFavorited()%n%s%n%n", String.valueOf(s.isFavorited()));
        System.out.printf("#isPossiblySensitive()%n%s%n%n", String.valueOf(s.isPossiblySensitive()));
        System.out.printf("#isRetweet()%n%s%n%n", String.valueOf(s.isRetweet()));
        System.out.printf("#isRetweeted()%n%s%n%n", String.valueOf(s.isRetweeted()));
        System.out.printf("#isRetweetedByMe()%n%s%n%n", String.valueOf(s.isRetweetedByMe()));
        System.out.printf("#isTruncated()%n%s%n%n", String.valueOf(s.isTruncated()));
    }

    private static final void printText(Status s) throws Exception {
        String rawText = s.getText();
        System.out.printf("#getText()%n%s%n%n", rawText);

//        System.out.printf(">>#toCharArray()%n");
//        char[] chars = rawText.toCharArray();
//        for (char c : chars) {
//            System.out.printf("%d\t%d\t%b\t%s%n",
//                 Character.getNumericValue(c),
//                 (int) c,
//                 Character.isDefined(c),
//                 String.valueOf(c)
//            );
//
//        }
//        System.out.printf("%n");

        OutputStreamWriter osw = new OutputStreamWriter(
            new FileOutputStream("c:\\users\\Michael\\Desktop\\a.html"), "UTF8");
        PrintWriter writer = new PrintWriter(osw);

        writer.println(" <!DOCTYPE html> ");
        writer.println(" <html> <head> <title>Tweial Email</title> ");
        writer.println("<meta http-equiv=\"Content-Type\" content=\"text/html;charset=utf-8\"> ");
        writer.println(" <meta name=\"viewport\" content=\"width=device-width, initial-scale=1.0\"> ");
        writer.println(" </head> ");
        writer.println(" <body> ");
        writer.println("  <p> ");
        writer.println(rawText);
        writer.println("  </p> ");
        writer.println(" </body> ");
        writer.println(" </html> ");
        writer.flush();
        writer.close();
    }

    private static void printGeolocation(GeoLocation g) {
        System.out.printf("#getGeolocation()%n");
        if (g == null) {
            System.out.printf("GeoLocation parameter is NULL%n%n");
            return;
        }
        System.out.printf("#getLatitude()%n%s%n%n", String.valueOf(g.getLatitude()));
        System.out.printf("#getLongitude()%n%s%n%n", String.valueOf(g.getLongitude()));
    }

    private static void printHashTagEntities(HashtagEntity[] hashtagEntities) {
        //System.out.printf("#XXX()%n%s%n%n", String.valueOf(s.getHashtagEntities()));
        // Hashtags
        // https://twitter.com/search?q=%23UnlimitedScreaming
        System.out.printf("#getHashtagEntities()%n");
        if (hashtagEntities == null || hashtagEntities.length == 0 ) {
            System.out.printf("HashtagEntity[] parameter is NULL or empty%n%n");
            return;
        }
        for (HashtagEntity entity : hashtagEntities) {
            System.out.println(ToStringBuilder.reflectionToString(entity));
        }
    }
}
