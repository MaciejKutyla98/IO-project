package com.ioproject.CashUp.data.model;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

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
            String transaction = new String();
            String data = incomeArrray.getJSONObject(i).getString("data");
            String category = incomeArrray.getJSONObject(i).getString("category");
            String cost = incomeArrray.getJSONObject(i).getString("cost");
            transaction = data+"\n"+category+"\n"+cost+" złoty";
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
            transaction = data+"\n"+category+"\n-"+cost+" złoty";
            income.add(transaction);
        }

        return income;
    }

    public Integer fromJSONBalanceSheet() throws JSONException {
        Integer balanceSheet = json.getInt("summary");
        return balanceSheet;
    }


}
