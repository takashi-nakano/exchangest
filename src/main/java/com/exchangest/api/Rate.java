package com.exchangest.api;

import java.math.BigDecimal;
import java.util.Objects;

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

    @Override
    public String toString() {
        return "Rate{" +
                "ttsRate=" + ttsRate +
                ", ttbRate=" + ttbRate +
                ", unit=" + unit +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Rate rate = (Rate) o;
        return getUnit() == rate.getUnit() && Objects.equals(getTtsRate(), rate.getTtsRate()) && Objects.equals(getTtbRate(), rate.getTtbRate());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getTtsRate(), getTtbRate(), getUnit());
    }
}
