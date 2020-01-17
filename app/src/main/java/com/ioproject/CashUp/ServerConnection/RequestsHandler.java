package com.ioproject.CashUp.ServerConnection;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStream;

//class handles requests sending and receiving answers

public class RequestsHandler {
    private DatabaseConnection databaseConnection;

    public DatabaseConnection getDatabaseConnection() {
        return databaseConnection;
    }

    public void setDatabaseConnection(DatabaseConnection databaseConnection) {
        this.databaseConnection = databaseConnection;
    }

    public String sendRequestAndGetResponse(String urlAddress, String request) throws IOException {
        String response = new String();
        databaseConnection = new DatabaseConnection();
        databaseConnection.connect(urlAddress);
        databaseConnection.getConnection().setRequestProperty("Content-Type", "application/json");
        databaseConnection.getConnection().setRequestProperty("Content-Length", "" + request.getBytes().length);
        OutputStream os = databaseConnection.getConnection().getOutputStream();
        os.write(request.getBytes());
        os.flush();
        os.close();
        databaseConnection.getConnection().connect();

        BufferedReader bufferedResponse = new BufferedReader(new InputStreamReader(databaseConnection.getConnection().getInputStream()));
        StringBuilder stringBuilder = new StringBuilder();
        String line = new String();
        while ((line = bufferedResponse.readLine()) != null) {
            stringBuilder.append(line + "\n");
        }
        response = stringBuilder.toString();
        return response;
    }
}
