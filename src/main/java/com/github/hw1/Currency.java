package com.github.hw1;

public class Currency {
    public char sign;

    public double exchange;

    public Currency(char sign, double exchange) {
        this.sign = sign;
        this.exchange = exchange;
    }

    public void setSign(char sign) {
        this.sign = sign;
    }

    public char getSign() {
        return sign;
    }

    public void setExchange(double exchange) {
        this.exchange = exchange;
    }

    public double getExchange() {
        return exchange;
    }
}
