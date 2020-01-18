package com.ioproject.CashUp.data.model;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

//conversion from JSON shared by server with REST API

public class FromJSONToString {
    private JSONObject json;

    public FromJSONToString(String json) throws JSONException {
        this.json = new JSONObject(json);
    }

    public ArrayList<String> fromJSONTOStringIncome() throws JSONException {
        ArrayList<String> income = new ArrayList<>();
        JSONArray incomeArrray = json.getJSONArray("income");
        for (int i = 0; i < incomeArrray.length(); i++)
        {
            String transaction;
            String data = incomeArrray.getJSONObject(i).getString("data");
            String category = incomeArrray.getJSONObject(i).getString("category");
            String cost = incomeArrray.getJSONObject(i).getString("cost");
            transaction = data+"\n"+category+"\n"+cost+" zł";
            income.add(transaction);
        }
        return income;
    }

    public ArrayList<String> fromJSONTOStringOutgo() throws JSONException {
        ArrayList<String> income = new ArrayList<>();
        JSONArray outgoArrray = json.getJSONArray("outgo");
        for (int i = 0; i < outgoArrray.length(); i++)
        {
            String transaction = new String();
            String data = outgoArrray.getJSONObject(i).getString("data");
            String category = outgoArrray.getJSONObject(i).getString("category");
            String cost = outgoArrray.getJSONObject(i).getString("cost");
            transaction = data+"\n"+category+"\n-"+cost+" zł";
            income.add(transaction);
        }
        return income;
    }

    public Integer fromJSONBalanceSheet() throws JSONException {
        Integer balanceSheet = json.getInt("summary");
        return balanceSheet;
    }

    public ArrayList<String> fromJSONToStringGroupBalance() throws JSONException {
        ArrayList<String> groupBalance = new ArrayList<>();
        JSONArray balance = json.getJSONArray("balance");
        for(int i = 0; i < balance.length(); i++){
            String userWithBalance;
            userWithBalance = balance.getJSONArray(i).getString(0) + ": " + String.valueOf(balance.getJSONArray(i).getInt(1));
            groupBalance.add(userWithBalance);
        }
        return groupBalance;
    }

    public ArrayList<String> fromJSONToStringGetAllUserGroups() throws JSONException {
        ArrayList<String> allGroups = new ArrayList<>();
        JSONArray groups = json.getJSONArray("groups");
        for(int i = 0; i< groups.length(); i++){
            allGroups.add(groups.getString(i));
        }
        System.out.println(allGroups);
        return allGroups;
    }

    public ArrayList<String> fromJSONToStringGetAllUserInGroup() throws JSONException {
        ArrayList<String> allGroups = new ArrayList<>();
        JSONArray groups = json.getJSONArray("logins");
        for(int i = 0; i< groups.length(); i++){
            allGroups.add(groups.getString(i));
        }
        System.out.println(allGroups);
        return allGroups;
    }

    public ArrayList<String> fromJSONToStringGroupHistory() throws JSONException {
        ArrayList<String> groupBalance = new ArrayList<>();
        JSONArray balance = json.getJSONArray("history");
        for(int i = 0; i < balance.length(); i++){
            String userWithBalance;
            userWithBalance = balance.getJSONArray(i).getString(0) + "\n" + balance.getJSONArray(i).getString(1) + "\n" + balance.getJSONArray(i).getInt(2);
            groupBalance.add(userWithBalance);
        }
        return groupBalance;
    }
}
