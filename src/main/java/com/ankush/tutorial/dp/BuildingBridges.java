package com.ankush.tutorial.dp;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

public class BuildingBridges {

    private int findBridges(List<CityPair> cityPairs) {
        //sort pair based on city2;
        Collections.sort(cityPairs);
        System.out.println(cityPairs);
        //use LIS in city1 list
        return getLIS(cityPairs.stream().map(a -> a.city1).collect(Collectors.toList()));

    }

    private int getLIS(List<Integer> values) {
        System.out.println(values);
        int[] dp = new int[values.size()];
        for (int i = 0; i < values.size(); i++) {
            dp[i] = 1;
        }
        for (int i = 1; i < values.size(); i++) {
            for (int j = 0; j < i; j++) {
                if (values.get(i) > values.get(j)) {
                    dp[i] = Math.max(dp[i], dp[j] + 1);
                }
            }
        }

        int max = dp[0];
        for (int i = 1; i < dp.length; i++) {
            if (max < dp[i]) {
                max = dp[i];
            }
        }
        return max;

    }

    public static void main(String[] args) {
        List<CityPair> cityPairs = new ArrayList<>();
        cityPairs.add(new CityPair(6,2));
        cityPairs.add(new CityPair(4,3));
        cityPairs.add(new CityPair(2,6));
        cityPairs.add(new CityPair(1,5));
        System.out.println(new BuildingBridges().findBridges(cityPairs));
    }


    private static class CityPair implements Comparable<CityPair> {
        int city1;

        public CityPair(int city1, int city2) {
            this.city1 = city1;
            this.city2 = city2;
        }

        int city2;

        @Override
        public int compareTo(CityPair anotherCityPair) {
            return this.city2 > anotherCityPair.city2 ? 1 : -1;
        }

        @Override
        public String toString() {
            return "CityPair{" +
                    "city1=" + city1 +
                    ", city2=" + city2 +
                    '}';
        }
    }


}
