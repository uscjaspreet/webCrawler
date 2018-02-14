package com.jjava;

public class Main {

    public static void main(String[] args) {
	// write your code here
        webCrawler crawler = new webCrawler();

        String root = "http://www.bbc.com";

        crawler.discoverWeb(root);
    }
}
