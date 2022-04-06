package com.exchangest.api;

import java.math.BigDecimal;

public final class Rate {
    private final BigDecimal ttsRate;
    private final BigDecimal ttbRate;
    private final int unit;

    private Rate(RateBuilder rateBuilder) {
        this.ttsRate = rateBuilder.ttsRate;
        this.ttbRate = rateBuilder.ttbRate;
        this.unit = rateBuilder.unit;
    }

    public static RateBuilder create() {
        return new RateBuilder();
    }

    public static class RateBuilder {
        private BigDecimal ttsRate;
        private BigDecimal ttbRate;
        private int unit;

        private RateBuilder() {
        }

        public RateBuilder setTtsRate(BigDecimal ttsRate) {
            this.ttsRate = ttsRate;
            return this;
        }

        public RateBuilder setTtbRate(BigDecimal ttbRate) {
            this.ttbRate = ttbRate;
            return this;
        }

        public RateBuilder setUnit(int unit) {
            this.unit = unit;
            return this;
        }

        public Rate build() {
            return new Rate(this);
        }
    }

    public BigDecimal getTtsRate() {
        return ttsRate;
    }

    public BigDecimal getTtbRate() {
        return ttbRate;
    }

    public int getUnit() {
        return unit;
    }
}
