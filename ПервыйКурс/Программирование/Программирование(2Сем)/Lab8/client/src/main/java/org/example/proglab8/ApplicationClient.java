package org.example.proglab8;

import Network.Client;

public class ApplicationClient {
   private static Client client = new Client("localhost", 2448, 5000, 5);

    public static Client getClient() {
        return client;
    }
}
