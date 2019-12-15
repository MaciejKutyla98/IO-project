package com.ioproject.CashUp.ServerConnection;

import android.widget.EditText;
import android.widget.Spinner;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;

public class AddNewUser {

    // Create GetText Metod
    public static String GetText(EditText name, EditText surname, EditText email,
                        EditText login, EditText password,
                        Spinner b_day, Spinner b_month, Spinner b_year) throws IOException {

        String Name, Surname, Email, Login, Password, B_Date;


        // Get user defined values
//        Name = name.getText().toString();
//        Surname = surname.getText().toString();  // TODO: Set surname in DB and script
//        Email = email.getText().toString();
//        Login = login.getText().toString();
//        Password = password.getText().toString();
//        B_Date = b_year.getSelectedItem().toString() + "-";
//        B_Date += b_month.getSelectedItem().toString() + "-";
//        B_Date += b_day.getSelectedItem().toString();

        Name = "test";
        Surname = "test";
        Email = "test";
        Login = "test";
        Password = "test";
        B_Date = "01-01-1997";


        // Create data variable for sent values to server


        String data = "{" + "\"command\": " + "\"add_new_user\"";
        data += ", " + "\"name\": \"" + Name + "\"";
        data += ", " + "\"email\": \"" + Email + "\"";
        data += ", " + "\"login\": \"" + Login + "\"";
        data += ", " + "\"password\": \"" + Password + "\"";
        data += ", " + "\"birth_date\": \"" + B_Date + "\"";
        data += ", " + "\"sex\": \"" + "M" + "\"";
        data += ", " + "\"reset_date\": \"" + "1" + "\"}";


        String text = "";
        BufferedReader reader = null;


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

}