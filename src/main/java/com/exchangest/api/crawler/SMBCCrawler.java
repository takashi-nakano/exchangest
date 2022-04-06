package com.exchangest.api.crawler;

import com.exchangest.api.Rate;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.select.Elements;

import java.math.BigDecimal;
import java.util.Currency;
import java.util.HashMap;
import java.util.Locale;
import java.util.Map;

public class SMBCCrawler implements RateCrawler {

    private static final String URL = "https://www.smbc.co.jp/ex/ExchangeServlet?ScreenID=tento";

    @Override
    public Map<Currency, Rate> getCurrency() {
        Map<Currency, Rate> currencyRateMap = new HashMap<>();

        try {
            Document document = Jsoup.connect(URL).get();
            Elements elements = document.getElementsMatchingOwnText("中国人民元").last().parent().children();
            String tts = elements.get(1).html();
            String ttb = elements.get(2).html();
            Rate rate = Rate.create()
                    .setTtsRate(new BigDecimal(tts))
                    .setTtbRate(new BigDecimal(ttb))

                    // SMBCは１００円単位で外貨レートを掲載されます。つまり、１００円でいくら外貨が買える
                    //　ですが、中国銀行の場合は外貨のいくらで１００元が買えるって掲載される。
                    // どうやってモデルを作るか、まだ考え中
                    //　とりあえずunitみたいなものが必要だと感じる
                    //　一緒に考えましょう
                    .setUnit(100).build();
            currencyRateMap.put(Currency.getInstance(Locale.CHINA), rate);
            return currencyRateMap;

        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    public static void main(String[] args) {
        Map<Currency, Rate> currencyRateMap = new SMBCCrawler().getCurrency();
        currencyRateMap.keySet().forEach(System.out::println);
    }
}
