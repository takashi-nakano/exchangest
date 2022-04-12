package com.exchangest.api.crawler;

import org.junit.jupiter.api.Test;

import java.net.URI;
import java.net.URISyntaxException;


class BOCCrawlerTest {

    @Test
    void getCurrency() throws URISyntaxException {
        BOCCrawler bocCrawler = new BOCCrawler(new URI("https://www.boc.cn/sourcedb/whpj/"));
        bocCrawler.getCurrency().forEach((k, v) -> System.out.println(k + ": " + v));
    }
}