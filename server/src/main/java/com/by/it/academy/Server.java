package com.by.it.academy;

import java.io.DataInputStream;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.logging.Logger;

public class Server {
    public static void main(String[] args) {

        try {
            ServerSocket serverSocket=new ServerSocket(3036);
            ExecutorService service= Executors.newCachedThreadPool();
            do {
                Socket socket = serverSocket.accept();
                final DataInputStream dataInputStream =
                        new DataInputStream(socket.getInputStream());
                service.submit(()->{
                    try {

                        String input = "";
                        while (!"END".equals(input)) {
                            input = dataInputStream.readUTF();
                            System.out.println("server input" + input);
                        }
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                });

            }
            while (true);
        }
        catch (IOException e) {
            e.printStackTrace();
        }
    }
}
