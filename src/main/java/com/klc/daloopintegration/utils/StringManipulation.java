package com.klc.daloopintegration.utils;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class StringManipulation {

    public static String removeLastNumberAfterHyphen(String input) {
        // Define the regex pattern to match the last number after a hyphen
        Pattern pattern = Pattern.compile("-(\\d+)$");
        Matcher matcher = pattern.matcher(input);

        // Replace the matched part with an empty string
        if (matcher.find()) {
            return matcher.replaceAll("");
        }
        return input;
    }
}
