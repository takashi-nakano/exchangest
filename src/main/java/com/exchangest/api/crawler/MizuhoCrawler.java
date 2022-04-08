package com.exchangest.api.crawler;

import com.exchangest.api.Rate;
import com.gargoylesoftware.htmlunit.BrowserVersion;
import com.gargoylesoftware.htmlunit.WebClient;
import com.gargoylesoftware.htmlunit.html.HtmlPage;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.select.Elements;

import java.math.BigDecimal;
import java.util.Currency;
import java.util.HashMap;
import java.util.Locale;
import java.util.Map;

public class MizuhoCrawler  implements RateCrawler {

    private static final String URL = "https://www.mizuhobank.co.jp/market/quote/index.html";

    @Override
    public Map<Currency, Rate> getCurrency() {
        Map<Currency, Rate> currencyRateMap = new HashMap<>();

        try {
            WebClient webClient = new WebClient(BrowserVersion.CHROME);
            webClient.getOptions().setJavaScriptEnabled(true);
            webClient.getOptions().setCssEnabled(false);
            webClient.getOptions().setThrowExceptionOnScriptError(false);
            webClient.getOptions().setThrowExceptionOnFailingStatusCode(false);
            HtmlPage page = webClient.getPage(URL);
            webClient.waitForBackgroundJavaScript(3000);

            String pageAsXml = page.asXml();
            Document document = Jsoup.parse(pageAsXml, URL);
            Elements elements = document.getElementsMatchingOwnText("CNY").first().parent().children();

            String tts = elements.get(2).html();
            String ttb = elements.get(3).html();
            Rate rate = Rate.create()
                    .setTtsRate(new BigDecimal(tts))
                    .setTtbRate(new BigDecimal(ttb))
                    .setUnit(100).build();

            currencyRateMap.put(Currency.getInstance(Locale.CHINA), rate);
            return currencyRateMap;

        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    public static void main(String[] args) {
        Map<Currency, Rate> currencyRateMap = new MizuhoCrawler().getCurrency();
        currencyRateMap.keySet().forEach(System.out::println);
    }
}
