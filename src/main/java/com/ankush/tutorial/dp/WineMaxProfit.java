package com.ankush.tutorial.dp;

/**
 * Created by ankush.a on 28/08/17.
 */
public class WineMaxProfit {

    /*
    "Imagine you have a collection of N wines placed next to each other on a shelf.
    For simplicity, let's number the wines from left to right as they are standing on the shelf
    with integers from 1 to N, respectively. The price of the ith wine is pi. (prices of different wines can be different).
    Because the wines get better every year, supposing today is the year 1,
    on year y the price of the ith wine will be y*pi, i.e. y-times the value that current year.
    You want to sell all the wines you have, but you want to sell exactly one wine per year,
    starting on this year. One more constraint - on each year you are allowed to sell only either the leftmost
    or the rightmost wine on the shelf and you are not allowed to reorder the wines on the
    shelf (i.e. they must stay in the same order as they are in the beginning).
    You want to find out, what is the maximum profit you can get, if you sell the wines in optimal order?"
     */

    int n; //n wines
    int price[]; //price of n wines
    int dp[][];

    public WineMaxProfit() {
    }

    public WineMaxProfit(int n) {
        this.n = n;
        price = new int[n];
        dp = new int[n][n];
        for (int i = 0; i < n; i++)
            for (int j = 0; j < n; j++)
                dp[i][j] = -1;
    }

    public int getN() {
        return n;
    }

    public void setN(int n) {
        this.n = n;
    }

    public int[] getPrice() {
        return price;
    }

    public void setPrice(int[] price) {
        this.price = price;
    }

    public int maxProfit(int start, int end) {
        if (start > end)
            return 0;

        if (dp[start][end] != -1)
            return dp[start][end];

        int year = n - (end - start + 1) + 1;

        dp[start][end] = Math.max((maxProfit(start + 1, end) + year * price[start]), maxProfit(start, end - 1) + year * price[end]);
        return dp[start][end];
    }
}
