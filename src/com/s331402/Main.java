package com.s331402;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.Scanner;

public class Main {

    public static void main(String[] args) {
        String hostName = "localhost";
        if (args.length > 0)
        {
            hostName = args[0];
                if (args.length > 1)
                {
                    System.err.println("Usage: java EchoClientTCP [<host name>]");
                    System.exit(1);
                }
        }

        try (Socket socket = new Socket(hostName, 6666);
             BufferedReader input = new BufferedReader(
                     new InputStreamReader(socket.getInputStream()));
             PrintWriter output = new PrintWriter(socket.getOutputStream(), true);
             Scanner scanner = new Scanner(System.in)
        ) {
            String url;
            String response;

            do {
                System.out.println("Enter URL to extract from: ");
                url = scanner.nextLine();

                output.println(url);
                response = input.readLine();
                System.out.println("Response from server: " + response);

                response = input.readLine();

                System.out.println("Response code: " + response);

                if(response == null){
                    System.out.println("Tomt svar fra server");
                }else if(response.equals("0")){
                    while(!(response = input.readLine()).equals("3")) {
                        System.out.println(response);
                    }
                }else if(response.equals("1")){
                    System.out.println("No email address found on the page!");
                }else if(response.equals("2")){
                    System.out.println("Server couldn't find the web page!");
                }



            } while(!url.equalsIgnoreCase("exit"));
        } catch (IOException e) {

        }
    }
}