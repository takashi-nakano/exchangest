package com.exchangest.api.crawler;

import com.exchangest.api.Rate;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.io.IOException;
import java.math.BigDecimal;
import java.net.URI;
import java.util.*;

public class BOCCrawler implements RateCrawler {
    private final static int TTB_RATE_INDEX = 89;
    private final static int TTS_RATE_INDEX = 91;
    private final URI uri;

    public BOCCrawler(URI uri) {
        this.uri = uri;
    }

    @Override
    public Map<Currency, Rate> getCurrency() {
        Document doc = null;
        try {
            doc = Jsoup.connect(uri.toString()).get();
        } catch (IOException e) {
            e.printStackTrace();
        }
        Element table = Objects.requireNonNull(doc).select("table").get(1); //select the first table.
        Elements tbody = table.select("tbody");
        Elements rows = tbody.select("tr");
        Elements rate = rows.select("td");
        Element yenTTBRate = rate.get(TTB_RATE_INDEX);
        Element yenTTSRate = rate.get(TTS_RATE_INDEX);
        Map<Currency, Rate> map = new HashMap<>();
        map.put(Currency.getInstance(Locale.JAPAN),
                Rate.create()
                        .setTtsRate(new BigDecimal(yenTTSRate.text()))
                        .setTtbRate(new BigDecimal(yenTTBRate.text()))
                        .build());
        return map;
    }
}
