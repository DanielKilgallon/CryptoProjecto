package javaapplication3;

import java.util.ArrayList;

/**
 * Encodes, Decodes and Breaks a repeating-keyword cipher. Depends on the ShiftCipher class
 */
public class RepeatingKeywordCipher {

    public static String encode(String msg, String keyWord) {
        msg = msg.toLowerCase();
        StringBuilder stringBuilder = new StringBuilder("");
        for (int i = 0; i < msg.length(); i++)
            if (!Character.isLetter(msg.charAt(i)))
                stringBuilder.append(msg.charAt(i));
            else
                stringBuilder.append(ShiftCipher.encode(msg.charAt(i) + "", keyWord.charAt(i % keyWord.length())));
        return stringBuilder.toString();
    }

    public static String decode(String msg, String keyWord) {
        msg = msg.toLowerCase();
        StringBuilder stringBuilder = new StringBuilder("");
        for (int i = 0; i < msg.length(); i++)
            if (!Character.isLetter(msg.charAt(i)))
                stringBuilder.append(msg.charAt(i));
            else
                stringBuilder.append(ShiftCipher.decode(msg.charAt(i) + "", keyWord.charAt(i % keyWord.length())));
        return stringBuilder.toString();
    }

    public static String[] breakCode(String msg) {
        msg = msg.toLowerCase();
        ArrayList<Integer> nums = new ArrayList<>();

        //finds combos in message to assess
        for (int i = 3; i < 10; i++)
            for (int j = 0; j + i < msg.length(); j++) {
                String toCompare = msg.substring(j, j + i);
                for (int k = j + i; k + i < msg.length(); k++) {
                    String secondToCompare = msg.substring(k, k + i);
                    if (toCompare.compareTo(secondToCompare) == 0)
                        nums.add(k - j);
                }
            }

//        //Uncomment if you want to opt into 3 letter combos
//        if (nums.size() < 10)
//            for (int j = 0; j + 3 < msg.length(); j++) {
//                String toCompare = msg.substring(j, j + 3);
//                for (int k = j + 3; k + 3 < msg.length(); k++) {
//                    String secondToCompare = msg.substring(k, k + 3);
//                    if (toCompare.compareTo(secondToCompare) == 0)
//                        nums.add(k - j);
//                }
//            }

        //finds gcd of all numbers compared to all numbers
        ArrayList<Integer> GCDs = new ArrayList<>();
        for (int i = 0; i < nums.size() - 1; i++) {
            for (int j = i + 1; j < nums.size(); j++) {
                GCDs.add(gcd(nums.get(i), nums.get(j)));
            }
        }

        //finds max gcd
        int max = 0;
        for (int i = 0; i < GCDs.size(); i++)
            if (GCDs.get(i) > max)
                max = GCDs.get(i);
        int[] tallies = new int[max + 1];

        //tallies gcds into array to find most common
        if (GCDs.size() == 0)
            return new String[]{};
        for (int i = 0; i < tallies.length; i++)
            tallies[GCDs.get(i)] = tallies[GCDs.get(i)] + 1;

        //frequency analysis
        max = 0;
        int spot = -1; //spot of most common gcd
        for (int i = 0; i < tallies.length; i++) //finds max gcd tally
            if (tallies[i] > max && i != 2 && i != 1) {
                max = tallies[i];
                spot = i;
            }
        int gcd = spot;

        //breaks up message into 'gcd' sections
        String[] segments = new String[gcd];
        for (int i = 0; i < gcd; i++)
            addSegment(msg, segments, gcd, i);

        return segments;
    }

    static int gcd(int a, int b) {
        if (a == 0)
            return b;
        return gcd(b % a, a);
    }

    /**
     * adds the segment of a message using parameters into array for frequency analysis.
     *
     * @param msg    message to parse
     * @param s      array to put section of message into
     * @param gcd    Greatest Common Divisor of keyword
     * @param offset offset for which section of message
     */
    private static void addSegment(String msg, String[] s, int gcd, int offset) {
        int count = offset;
        s[offset] = "";
        while (count < msg.length()) {
            s[offset] += msg.charAt(count);
            count = count + gcd;
        }
    }
}