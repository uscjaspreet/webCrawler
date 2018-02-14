package com.jjava;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class webCrawler {

    private Queue<String> queue;
    private List<String> discoveredWebsiteList;

    public webCrawler() {
        this.queue = new LinkedList<String>();
        this.discoveredWebsiteList = new ArrayList<String>();
    }

    public void discoverWeb(String root){
        this.queue.add(root);
        this.discoveredWebsiteList.add(root);

        while (!queue.isEmpty()){

            String v = this.queue.remove();
            String rawHtml = readURL(v);

            String regExpr = "http://(\\w+\\.)*(\\w+)";
            Pattern pattern = Pattern.compile(regExpr);
            Matcher macther = pattern.matcher(rawHtml);

            while (macther.find()){
                String actualURL = macther.group();
                if (!discoveredWebsiteList.contains(actualURL)){
                    discoveredWebsiteList.add(actualURL);
                    System.out.println("Website has been found with url: " + actualURL);
                    queue.add(actualURL);
                }
            }
        }

    }

    private String readURL(String v){

        String rawHtml = "";

        try{
            URL url = new URL(v);
            BufferedReader in = new BufferedReader(new InputStreamReader(url.openStream()));

            String inputLine = "";

            while ((inputLine = in.readLine()) != null){
                rawHtml += inputLine;
            }

            in.close();

        } catch (MalformedURLException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

        return rawHtml;
    }
}
