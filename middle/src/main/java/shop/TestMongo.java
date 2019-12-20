package shop;

import com.mongodb.MongoClient;
import com.mongodb.ServerAddress;
import com.mongodb.MongoCredential;
import com.mongodb.MongoClientOptions;
import com.mongodb.client.MongoDatabase;


import java.util.Arrays;

public class TestMongo {
    public static void main(String[] args) {
        connectMongo();
        System.out.println("Mongo connected");

    }

    private static void connectMongo() {
        MongoCredential credential = MongoCredential.createCredential("user", "datebase", "pwd".toCharArray());
        MongoClientOptions options = MongoClientOptions.builder().sslEnabled(true).build();
        MongoClient mongoClient = new MongoClient(new ServerAddress("mongodb://host:27017/?replicaSet=replicaSet"),
                Arrays.asList(credential), options);
        MongoDatabase database = mongoClient.getDatabase("database");

    }
}
