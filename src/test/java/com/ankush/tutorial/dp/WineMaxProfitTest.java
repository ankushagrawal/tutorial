package com.ankush.tutorial.dp;

import org.junit.BeforeClass;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

/**
 * Created by ankush.a on 28/08/17.
 */
public class WineMaxProfitTest {


    @BeforeClass
    public static void testSetup() {
    }

    @Test
    public void test1() {
        WineMaxProfit wineMaxProfit = new WineMaxProfit(5);
        int[] price = new int[]{2, 3, 5, 1, 4};
        wineMaxProfit.setPrice(price);
        int profit = wineMaxProfit.maxProfit(0, price.length - 1);
        assertEquals(50, profit);
    }

    @Test
    public void test2() {
        WineMaxProfit wineMaxProfit = new WineMaxProfit(4);
        int[] price = new int[]{1, 4, 2, 3};
        wineMaxProfit.setPrice(price);
        int profit = wineMaxProfit.maxProfit(0, price.length - 1);
        assertEquals(29, profit);
    }

}
