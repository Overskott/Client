package com.s331402;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.Scanner;

public class Main {

    public static void main(String[] args) {
        try (Socket socket = new Socket("localhost", 6666);
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

                if(response.equals("0")){
                    while(!(response = input.readLine()).equals("3")) {
                        System.out.println(response);
                    }
                }else if(response.equals("1")){
                    System.out.println("No email address found on the page!");
                }else {
                    System.out.println("Server couldn't find the web page!");
                }



            } while(!url.equalsIgnoreCase("exit"));
        } catch (IOException e) {

        }
    }
}