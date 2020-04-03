package fci.swe2.onlineshopapi;

import com.sun.net.httpserver.HttpServer;

import java.io.IOException;
import java.net.InetSocketAddress;

public class Server {
    HttpServer server = null;
    public void initserver() throws IOException {
        server = HttpServer.create(new InetSocketAddress(8000), 0);
        server.createContext("/register", new RegisterAPI());
        server.createContext("/accounts", new AccountsAPI());
    }
    public void startserver(){
        server.setExecutor(null); // creates a default executor
        server.start();
    }

    public static void main(String[] args){
        Server S = new Server();
        try {
            S.initserver();
        } catch (IOException e) {
            e.printStackTrace();
        }
        S.startserver();
    }
}
