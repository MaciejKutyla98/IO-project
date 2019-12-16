package com.ioproject.CashUp.ServerConnection;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;

public class DataBaseRequests {

    // Create connect Metod
    public static String connect(String data) throws IOException {

        // Create data variable for sent values to server

        String text = "";
        BufferedReader reader = null;

        System.out.println(data);

        URL url = null;
        try {
            url = new URL("https://cash-success.appspot.com");
        } catch (MalformedURLException e) {
            e.printStackTrace();
        }

        // Send POST data request

        URLConnection conn = null;
        try {
            conn = (HttpURLConnection) url.openConnection();

        } catch (IOException e) {
            e.printStackTrace();
        }

        ((HttpURLConnection) conn).setRequestMethod("POST");

        conn.setRequestProperty("Content-Type", "application/json");
        conn.setRequestProperty("Content-Length", "" + data.getBytes().length);

        conn.setUseCaches(false);//set true to enable Cache for the req
        conn.setDoOutput(true);//enable to write data to output stream
        OutputStream os = conn.getOutputStream();
        os.write(data.getBytes());
        os.flush();
        os.close();

        conn.connect();
            // Get the server response

        try {
            reader = new BufferedReader(new InputStreamReader(conn.getInputStream()));

            StringBuilder sb = new StringBuilder();
            String line = null;

            // Read Server Response
            while ((line = reader.readLine()) != null) {
                // Append server response in string
                sb.append(line + "\n");
            }


            text = sb.toString();

            return text;
        } catch (IOException e) {
            e.printStackTrace();
        }

        // Show response on activity
        return "";

    }

    public static String addNewUser (String name, String surname, String email, String login, String password, String birthday_date){
        String data = "{" + "\"command\": " + "\"add_new_user\"";
        data += ", " + "\"name\": \"" + name + "\"";
        data += ", " + "\"surname\": \"" + surname + "\"";
        data += ", " + "\"email\": \"" + email + "\"";
        data += ", " + "\"login\": \"" + login + "\"";
        data += ", " + "\"password\": \"" + password + "\"";
        data += ", " + "\"birth_date\": \"" + birthday_date + "\"}";
        return data;
    }

    public static String loginUser (String login, String password){
        String data = "{" + "\"command\": " + "\"check_user\"";
        data += ", " + "\"login\": \"" + login + "\"";
        data += ", " + "\"password\": \"" + password + "\"" + "}";
        return data;
    }

    public static String addNewOutcome(String clientId, String category, String cost){
        String data = "{" + "\"command\": " + "\"new_outgo\"";
        data += ", " + "\"id\": \"" + clientId + "\"";
        data += ", " + "\"category\": \"" + category + "\"";
        data += ", " + "\"cost\": \"" + cost + "\"}";
        return data;
    }

    

}