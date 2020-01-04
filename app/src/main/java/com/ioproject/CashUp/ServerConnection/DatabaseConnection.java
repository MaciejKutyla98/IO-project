package com.ioproject.CashUp.ServerConnection;

import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.ProtocolException;
import java.net.URL;
import java.net.URLConnection;

public class DatabaseConnection {
    private URL urlAddress = null;
    private URLConnection connection = null;

    public URL getUrlAddress() {
        return urlAddress;
    }

    public void setUrlAddress(String _urlAddress) {
        try {
            urlAddress = new URL(_urlAddress);
        } catch (MalformedURLException e) {
            e.printStackTrace();
        }
    }

    public URLConnection getConnection() {
        return connection;
    }

    public void openConnection(URL address) {
        try {
            connection = (HttpURLConnection) address.openConnection();
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    public void connect(String urlAddress) throws ProtocolException {
        setUrlAddress(urlAddress);
        openConnection(getUrlAddress());
        ((HttpURLConnection) getConnection()).setRequestMethod("POST");
        getConnection().setUseCaches(false);
        getConnection().setDoOutput(true);
    }
}
