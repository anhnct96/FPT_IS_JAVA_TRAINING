package model.utility;

import jdk.jshell.execution.Util;

import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Random;

public final class Utility {
    static DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
    static final String lowerCaseAlphabet = "abcdefghijklmnopqrstuvwxyz"; // a-z
    static final String upperCaseAlphabet = lowerCaseAlphabet.toUpperCase(); // A-Z
    static final String lowerAndUpperCaseAlphabet = lowerCaseAlphabet+upperCaseAlphabet; //
    static final String numericalCharacter = "0123456789"; // A-Z
    private static Random generator = new Random();

    public static Timestamp unformattedLDTToTimestamp(LocalDateTime ldt) {
        String formattedLDT = ldt.format(formatter);
        return Timestamp.valueOf(formattedLDT);
    }

    public static LocalDateTime unformattedLDTToFormattedLDT(LocalDateTime ldt) {
        String formattedLDT = ldt.format(formatter);
        return LocalDateTime.parse(formattedLDT, formatter);
    }

    public static int randomNumber(int min, int max) {
        return generator.nextInt((max - min) + 1) + min;
    }

    public static String dummya2zA2ZString (int length) {
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < length; i++) {
            int number = randomNumber(0, lowerAndUpperCaseAlphabet.length() - 1);
            char ch = lowerAndUpperCaseAlphabet.charAt(number);
            sb.append(ch);
        }
        return sb.toString();
    }

    public static String dummya2zString (int length) {
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < length; i++) {
            int number = randomNumber(0, lowerCaseAlphabet.length() - 1);
            char ch = lowerCaseAlphabet.charAt(number);
            sb.append(ch);
        }
        return sb.toString();
    }

    public static String dummyA2ZString (int length) {
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < length-1; i++) {
            int number = randomNumber(0, upperCaseAlphabet.length() - 1);
            char ch = upperCaseAlphabet.charAt(number);
            sb.append(ch);
        }
        return sb.toString();
    }

    public static String dummyNumber (int length) {
        StringBuilder sb = new StringBuilder();
        sb.append(Utility.randomNumber(1,9));
        for (int i = 0; i < length-1; i++) {
            int number = randomNumber(0, numericalCharacter.length() - 1);
            char ch = numericalCharacter.charAt(number);
            sb.append(ch);
        }
        return sb.toString();
    }

    public static boolean dummyBoolean() {
        int number = randomNumber(0, 1);
        if (number > 0) {
            return true;
        }
        else {
            return false;
        }
    }

//    public static LocalDateTime dummyLDT() {
//        java.util.Date randomDate = RandomUtil.getRandomDate(new java.util.Date(RandomUtil.getMinimumDate()), new java.util.Date(RandomUtil.getMaximumDate()), false);
//        SimpleDateFormat sdf = new SimpleDateFormat("HH:mm:ss");
//        System.out.println(sdf.format(randomDate));
//    }
}
