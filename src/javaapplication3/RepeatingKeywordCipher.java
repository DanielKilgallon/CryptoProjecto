package javaapplication3;

import java.util.ArrayList;

/**
 * Created by dpk12149 on 4/9/2018.
 */
public class RepeatingKeywordCipher {

    public static String encode(String msg, String keyWord) {
        msg = msg.toLowerCase();
        StringBuilder stringBuilder = new StringBuilder("");
        for (int i = 0; i < msg.length(); i++)
            stringBuilder.append(ShiftCipher.encode(msg.charAt(i) + "", keyWord.charAt(i % keyWord.length())));
        return stringBuilder.toString();
    }

    public static String decode(String msg, String keyWord) {
        msg = msg.toLowerCase();
        StringBuilder stringBuilder = new StringBuilder("");
        for (int i = 0; i < msg.length(); i++)
            stringBuilder.append(ShiftCipher.decode(msg.charAt(i) + "", keyWord.charAt(i % keyWord.length())));
        return stringBuilder.toString();
    }

    public static String breakCode(String msg) {
        //Friedman Test - done
        //Find GCD
        //Frequency Analysis
        //Guess and Check
        ArrayList<Integer> nums = new ArrayList<>();

        for (int i = 3; i < 7; i++) {
            for (int j = 0; j + i < msg.length(); j++) {
                String toCompare = msg.substring(j, j + i);
                for (int k = j + i; k + i < msg.length(); k++) {
                    String secondToCompare = msg.substring(k, k + i);
                    if (toCompare.compareTo(secondToCompare) == 0) {
                        nums.add(k - j);
                    }
                }
            }
        }


        return nums.toString();
    }
}