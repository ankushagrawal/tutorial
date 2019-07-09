package com.ankush.tutorial.greedy;

import java.util.ArrayList;
import java.util.List;

public class Egyptian {

    private static class Fraction{
        Integer numerator;
        Integer denominator;

        public Fraction(int numerator, int denominator) {
            this.numerator = numerator;
            this.denominator = denominator;
        }

        @Override
        public String toString() {
            return "Fraction{" +
                    "numerator=" + numerator +
                    ", denominator=" + denominator +
                    '}';
        }
    }

    public static void getEgyptianFractions(Fraction fraction, List<Fraction> egyptianFractions) {
        if (fraction.denominator == 0 || fraction.numerator == 0)
            return;
        if (fraction.denominator % fraction.numerator == 0) {
            Fraction fraction1 = new Fraction(1,fraction.denominator/fraction.numerator);
            egyptianFractions.add(fraction1);
            return;
        }
        if (fraction.numerator == 1) {
            egyptianFractions.add(fraction);
            return;
        }
        Double ceil = Math.ceil(fraction.denominator.doubleValue()/fraction.numerator);
        Fraction newFraction = new Fraction(1, ceil.intValue());
        egyptianFractions.add(newFraction);
        getEgyptianFractions(new Fraction(fraction.numerator*ceil.intValue()- fraction.denominator,
                fraction.denominator*ceil.intValue()), egyptianFractions);
        }

        public static void main(String[] args) {
            List<Fraction> egyptianFractions = new ArrayList<>();
            getEgyptianFractions(new Fraction(6,14), egyptianFractions);
            System.out.println(egyptianFractions);
        }
}

