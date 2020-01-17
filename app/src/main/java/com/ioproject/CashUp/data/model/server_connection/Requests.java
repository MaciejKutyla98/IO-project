package com.ioproject.CashUp.data.model.server_connection;

//class is handling converting app requests to JSONs matching with REST API shared by server

public class Requests {
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

    public static String addNewOutgo(String clientId, String category, String cost, String description){
        String data = "{" + "\"command\": " + "\"new_outgo\"";
        data += ", " + "\"id\": \"" + clientId + "\"";
        data += ", " + "\"category\": \"" + category + "\"";
        data += ", " + "\"cost\": \"" + cost + "\"";
        data += ", " + "\"description\": \"" + description + "\"}";
        return data;
    }

    public static String addNewIncome(String clientId, String category, String cost, String description){
        String data = "{" + "\"command\": " + "\"new_income\"";
        data += ", " + "\"id\": \"" + clientId + "\"";
        data += ", " + "\"category\": \"" + category + "\"";
        data += ", " + "\"cost\": \"" + cost + "\"";
        data += ", " + "\"description\": \"" + description + "\"}";
        return data;
    }

    public static String showAllTransactions(String userId){
        String data = "{" + "\"command\": " + "\"show_all\"";
        data += ", " + "\"id\": \"" + userId + "\"}";
        return data;
    }
}
