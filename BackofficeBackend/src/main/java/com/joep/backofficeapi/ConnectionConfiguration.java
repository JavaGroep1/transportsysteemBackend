package com.joep.backofficeapi;

public class ConnectionConfiguration {
    private static final String mongoConnectionString = "mongodb+srv://joep:KWod9q3qTH2qkvkf@testcluster.o2vcp.azure.mongodb.net/Backoffice?retryWrites=true&w=majority";
    // getting the value from that key which you set in application.properties
    public static String getMongoConnectionString() {
        return mongoConnectionString;
    }
}
