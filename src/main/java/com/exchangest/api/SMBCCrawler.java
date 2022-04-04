package com.exchangest.api;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.select.Elements;

@RestController
public class SMBCCrawler {

    private static final String URL = "https://www.smbc.co.jp/ex/ExchangeServlet?ScreenID=tento";

    @GetMapping("SMBC")
    public String crawle(){

        String tts = "" ;
        String ttb = "";

        try {
            Document document = Jsoup.connect(URL).get();
            Elements elements = document.getElementsMatchingOwnText("中国人民元").last().parent().children();

            tts = elements.get(1).html();
            ttb = elements.get(2).html();

            System.out.println();
        }catch(Exception e){
            System.out.println(e);
        }

        return "smbc : "+tts+" : "+ttb;
    }

}
