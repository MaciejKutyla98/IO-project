package com.ioproject.CashUp.data.model.server_connection;

import java.io.IOException;

//class Repository - the only way to get to data from database, another actions like handling server connection etc are placed
// in separated classes to make code more clear

public class Repository {
    private static RequestsHandler requestsHandler = new RequestsHandler();
    private static String result;

    public static String getResult() {
        return result;
    }

    public static void setResult(String result) {
        Repository.result = result;
    }

    public static RequestsHandler getRequestsHandler() {
        return requestsHandler;
    }

    public static void setRequestsHandler(RequestsHandler requestsHandler) {
        Repository.requestsHandler = requestsHandler;
    }

    public static String addNewUser(String name, String surname, String email, String login, String password, String birthday_date) throws IOException {
        setResult(requestsHandler.sendRequestAndGetResponse("https://cash-success.appspot.com", Requests.addNewUser(name, surname, email, login, password, birthday_date)));
        return result;
    }

    public static String loginUser(String login, String password) throws IOException {
        setResult(requestsHandler.sendRequestAndGetResponse("https://cash-success.appspot.com", Requests.loginUser(login, password)));
        return result;
    }

    public static void addNewOutgo(String clientId, String category, String cost, String description) throws IOException {
        setResult(requestsHandler.sendRequestAndGetResponse("https://cash-success.appspot.com", Requests.addNewOutgo(clientId, category, cost, description)));
    }

    public static void addNewIncome(String clientId, String category, String cost, String description) throws IOException {
        setResult(requestsHandler.sendRequestAndGetResponse("https://cash-success.appspot.com", Requests.addNewIncome(clientId, category, cost, description)));
    }

    public static String showAllTransactions(String userId) throws IOException {
        setResult(requestsHandler.sendRequestAndGetResponse("https://cash-success.appspot.com", Requests.showAllTransactions(userId)));
        return result;
    }
}
