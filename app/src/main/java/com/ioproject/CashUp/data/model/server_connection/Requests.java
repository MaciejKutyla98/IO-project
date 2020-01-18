package com.ioproject.CashUp.data.model.server_connection;

//class is handling converting app requests to JSONs matching with REST API shared by server

import java.util.ArrayList;

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

    public static String addNewGroup(String name, String description, ArrayList<String> members){
        StringBuilder membersString = new StringBuilder();
        for(String i: members){
            if(!members.get(0).equals(i)){
                membersString.append("\", \"");
            }
            membersString.append(i);
        }
        String data = "{" + "\"command\": " + "\"group_add\"";
        data += ", " + "\"group_name\": \"" + name + "\"";
        data += ", " + "\"group_description\": \"" + description + "\"";
        data += ", " + "\"group_members\": [\"" + membersString + "\"]}";
        System.out.println(data);
        return data;
    }

    public static String addNewGroupOutgo(String groupId, String price, String category, String description, String payer){
        String data = "{" + "\"command\": " + "\"new_group_payment\"";
        data += ", " + "\"group_name\": \"" + groupId + "\"";
        data += ", " + "\"quota\": " + price;
        data += ", " + "\"category\": \"" + category + "\"";
        data += ", " + "\"description\": \"" + description + "\"";
        data += ", " + "\"person_pay\": \"" + payer + "\"}";
        return data;
    }

    public static String showUserBalance(String login, String groudId){
        String data = "{" + "\"command\": " + "\"show_balance\"";
        data += ", " + "\"login\": \"" + login + "\"";
        data += ", " + "\"group_name\": \"" + groudId + "\"}";
        System.out.println(data);
        return data;
    }

    public static String getAllUserGroups(String login){
        String data = "{" + "\"command\": " + "\"get_groups\"";
        data += ", " + "\"login\": \"" + login + "\"}";
        System.out.println(data);
        return data;
    }

    public static String doesUserExist(String login){
        String data = "{" + "\"command\": " + "\"user_exist\"";
        data += ", " + "\"login\": \"" + login + "\"}";
        return data;
    }

    public static String showUserHistory(String login, String groudId){
        String data = "{" + "\"command\": " + "\"show_history\"";
        data += ", " + "\"login\": \"" + login + "\"";
        data += ", " + "\"group_name\": \"" + groudId + "\"}";
        System.out.println(data);
        return data;
    }
}