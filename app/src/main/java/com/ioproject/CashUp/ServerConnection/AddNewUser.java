package com.ioproject.CashUp.ServerConnection;

import android.widget.EditText;
import android.widget.Spinner;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.UnsupportedEncodingException;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;
import java.net.URLEncoder;

//public class AddNewUser extends Activity {
//
//    TextView content;
//    EditText name, surname, email, login, password;
//    Spinner b_day, b_month, b_year;
//
//    String Name, Surname, Email, Login, Password, B_Date;
//
//    /**
//     * Called when the activity is first created.
//     */
//    @Override
//    public void onCreate(Bundle savedInstanceState) {
//        super.onCreate(savedInstanceState);
//        setContentView(R.layout.activity_sign_up);
//
//        content = (TextView) findViewById(R.id.content);
//
//        name = (EditText) findViewById(R.id.name);
//        surname = (EditText) findViewById(R.id.surname);
//        email = (EditText) findViewById(R.id.email);
//        login = (EditText) findViewById(R.id.login);
//        password = (EditText) findViewById(R.id.password);
//
//        b_day = (Spinner) findViewById(R.id.spinner_day);
//        b_month = (Spinner) findViewById(R.id.spinner_months);
//        b_year = (Spinner) findViewById(R.id.spinner_years);
//
//
//
//        Button saveme = (Button) findViewById(R.id.registratrion_button);
//
//        saveme.setOnClickListener(new Button.OnClickListener() {
//
//            public void onClick(View v) {
//                try {
//                    GetText();
//                } catch (Exception ex) {
//                    content.setText(" url exeption! ");
//                }
//            }
//        });
//    }


public class AddNewUser {


    // Create GetText Metod
    public static String GetText(EditText name, EditText surname, EditText email,
                        EditText login, EditText password,
                        Spinner b_day, Spinner b_month, Spinner b_year) throws UnsupportedEncodingException {

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
        Name = "test";
        Email = "test";
        Login = "test";
        Password = "test";
        B_Date = "01-01-1997";


        // Create data variable for sent values to server

        String data = URLEncoder.encode("command", "UTF-8")
                + "=" + URLEncoder.encode("add_new_user", "UTF-8");

        data += "&" + URLEncoder.encode("name", "UTF-8") + "="
                + URLEncoder.encode(Name, "UTF-8");

        data += "&" + URLEncoder.encode("email", "UTF-8") + "="
                + URLEncoder.encode(Email, "UTF-8");

        data += "&" + URLEncoder.encode("login", "UTF-8") + "="
                + URLEncoder.encode(Login, "UTF-8");

        data += "&" + URLEncoder.encode("password", "UTF-8")
                + "=" + URLEncoder.encode(Password, "UTF-8");

        data += "&" + URLEncoder.encode("birth_date", "UTF-8")
                + "=" + URLEncoder.encode(B_Date, "UTF-8");


        //  TODO: sex and reset date not in sing up view
        data += "&" + URLEncoder.encode("sex", "UTF-8")
                + "=" + URLEncoder.encode("M", "UTF-8");

        data += "&" + URLEncoder.encode("reset_date", "UTF-8")
                + "=" + URLEncoder.encode("1", "UTF-8");



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
            conn = url.openConnection();
            OutputStreamWriter wr = new OutputStreamWriter(conn.getOutputStream());
            wr.write(data);
            wr.flush();
        } catch (IOException e) {
            e.printStackTrace();
        }
//        conn.setDoOutput(true);



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