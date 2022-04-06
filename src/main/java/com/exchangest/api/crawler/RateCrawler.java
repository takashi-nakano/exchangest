package com.exchangest.api.crawler;

import com.exchangest.api.Rate;

import java.util.Currency;
import java.util.Map;

public interface RateCrawler {
    public Map<Currency, Rate> getCurrency();
}
