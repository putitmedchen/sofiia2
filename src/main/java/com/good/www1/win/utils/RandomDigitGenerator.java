package com.good.www1.win.utils;

import java.util.Random;

public class RandomDigitGenerator {

    private static String randomNumber;

    public static String generateRandomEventNumber(){
        Random random = new Random();
        randomNumber = String.valueOf(random.nextInt(2000));
        return randomNumber;
    }

}
