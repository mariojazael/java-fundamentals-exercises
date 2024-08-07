package com.bobocode.se;

import com.bobocode.util.ExerciseNotCompletedException;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * {@link CrazyRegex} is an exercise class. Each method returns Pattern class which
 * should be created using regex expression. Every method that is not implemented yet
 * throws {@link ExerciseNotCompletedException}
 * <p>
 * TODO: remove exception and implement each method of this class using {@link Pattern}
 *
 * @author Andriy Paliychuk
 */
public class CrazyRegex {

    /**
     * A Pattern that that finds all words "Curiosity" in text
     *
     * @return a pattern that looks for the word "Curiosity"
     */
    public Pattern findSpecificWord() {
        return Pattern.compile("Curiosity");
    }

    /**
     * A Pattern that finds first word in text
     *
     * @return a pattern that looks for the first word in text
     */
    public Pattern findFirstWord() {
        return Pattern.compile("^\\w+");
    }

    /**
     * A Pattern that finds last word in text
     *
     * @return a pattern that looks for the last word in text
     */
    public Pattern findLastWord() {
        return Pattern.compile("\\w+$");
    }

    /**
     * A Pattern that finds all numbers in text. When we have "555-555", "(555)555" and "30th" in text
     * our pattern must grab all that numbers:
     * "555" - four times, and one "30"
     *
     * @return a pattern that looks for numbers
     */
    public Pattern findAllNumbers() {
        return Pattern.compile("\\d+");
    }

    /**
     * A Pattern that finds all dates. For instance: "1971-11-23"
     *
     * @return a pattern that looks for dates
     */
    public Pattern findDates() {
        return Pattern.compile("\\d{4}-\\d{2}-\\d{2}");
    }

    /**
     * A Pattern that finds different variations of word "color".
     * We are looking for: "color", "colour", "colors", "colours"
     *
     * @return a pattern that looks for different variations of word "color"
     */
    public Pattern findDifferentSpellingsOfColor() {
        return Pattern.compile("colo[ur][rs]s?");
    }

    /**
     * A Pattern that finds all zip codes in text.
     * Zip code is a 5-digit number without any characters or special symbols.
     * For example: 72300
     *
     * @return a pattern that looks for zip codes
     */
    public Pattern findZipCodes() {
        return Pattern.compile(" \\d{5} ");
    }

    /**
     * A Pattern that finds different variations of word "link".
     * We are looking for: "lynk", "link", "l nk", "l(nk"
     *
     * @return a pattern that looks for different variations of word "link"
     */
    public Pattern findDifferentSpellingsOfLink() {
        return Pattern.compile("l[yi( ]nk");
    }

    /**
     * A Pattern that finds phone numbers.
     * For example: "555-555-5555"
     *
     * @return a pattern that looks for phone numbers
     */
    public Pattern findSimplePhoneNumber() {
        return Pattern.compile("(\\d{3}-\\d{3}-\\d{4})");
    }

    /**
     * A Pattern that finds numbers with following requirements:
     * - inside the number can be only digits from 0 to 5
     * - length 3
     *
     * @return a pattern that looks for numbers with length 3 and digits from 0 to 5 in the middle
     */
    public Pattern findNumbersFromZeroToFiveWithLengthThree() {
        return Pattern.compile("[0-5][0-5][0-5]");
    }

    /**
     * A Pattern that finds all words in text that have length 5
     *
     * @return a pattern that looks for the words that have length 5
     */
    public Pattern findAllWordsWithFiveLength() {
        return Pattern.compile("\\b[A-Za-z]{5}\\b");
    }

    /**
     * A Pattern that finds words and numbers with following constraints:
     * - not shorter than two symbols
     * - not longer than three symbols
     *
     * @return a pattern that looks for words and numbers that not shorter 2 and not longer 3
     */
    public Pattern findAllLettersAndDigitsWithLengthThree() {
        return Pattern.compile("\\b[0-9a-zA-Z][0-9a-zA-Z][0-9a-zA-Z]?\\b");
    }

    /**
     * A Pattern that finds all words that begin with capital letter
     *
     * @return a pattern that looks for the words that begin with capital letter
     */
    public Pattern findAllWordsWhichBeginWithCapitalLetter() {
        return Pattern.compile("\\b[A-Z][a-z]+\\b");
    }

    /**
     * A Pattern that finds only the following abbreviation:
     * - AK, AL, AR, AZ, CA, CO, CT, PR, PA, PD
     *
     * @return a pattern that looks for the abbreviations above
     */
    public Pattern findAbbreviation() {
        return Pattern.compile("[ACP][KLRZAOTD]");
    }

    /**
     * A Pattern that finds all open braces
     *
     * @return a pattern that looks for all open braces
     */
    public Pattern findAllOpenBraces() {
        return Pattern.compile("\\{+");
    }

    /**
     * A Pattern that finds everything inside []
     *
     * @return a pattern that looks for everything inside []
     */
    public Pattern findOnlyResources() {
        return Pattern.compile("(?<=\\[)\\w+(?=])");
    }

    /**
     * A Pattern that finds all https links in note.txt
     *
     * @return a pattern that looks for all https links in note.txt
     */
    public Pattern findOnlyLinksInNote() {
        return Pattern.compile("https://\\w+\\.(\\w+\\.)?com");
    }

    /**
     * A Pattern that finds all http links in nasa.json
     *
     * @return a pattern that looks for all http links in nasa.json
     */
    public Pattern findOnlyLinksInJson() {
        return Pattern.compile("http://[\\w./_-]+");
    }

    /**
     * A Pattern that finds all .com, .net and .edu emails
     *
     * @return a pattern that looks for all .com, .net and .edu emails
     */
    public Pattern findAllEmails() {
        return Pattern.compile("[\\w.]+@\\w+\\.(com|net|edu)");
    }

    /**
     * A Pattern that finds the following examples of phone numbers:
     * -  555-555-5555
     * -  555.555.5555
     * -  (555)555-5555
     *
     * @return a pattern that looks for phone numbers patterns above
     */
    public Pattern findAllPatternsForPhoneNumbers() {
        return Pattern.compile("[(]?\\d{3}[.\\-)]\\d{3}[.\\-]\\d{4}");
    }

    /**
     * A Pattern that finds only duplicates
     *
     * @return a pattern that looks for duplicates
     */
    public Pattern findOnlyDuplicates() {
        return Pattern.compile("\\b(\\w+)\\b\\s+\\1\\b");
    }

    /**
     * You have a text where all names recorded as first name, last name.
     * Create matcher and use method replaceAll to record that names as:
     * - last name first name
     *
     * @return String where all names recorded as last name first name
     */
    public String replaceFirstAndLastNames(String names) {
        Pattern pattern = Pattern.compile("\\b[a-zA-Z]+\\b");
        Matcher matcher = pattern.matcher(names);
        return switchNames(matcher);
    }

    public String switchNames(Matcher matcher) {
        int flag = 0;
        StringBuilder result = new StringBuilder();
        String tempName = "";
        while(matcher.find()) {
            if(flag == 0) tempName = matcher.group();
            else if(flag == 1) {
                String lastName = matcher.group();
                result.append(lastName).append(" ").append(tempName).append(" ... ");
                flag = 0;
                continue;
            }
            flag++;
        }
        return result.substring(0, result.toString().length() - 5);
    }

    /**
     * You have a text with phone numbers.
     * Create matcher and use method replaceAll to replace last digits:
     * -  555-XXX-XXXX
     *
     * @return String where in all phone numbers last 7 digits replaced to X
     */
    public String replaceLastSevenDigitsOfPhoneNumberToX(String phones) {
        Pattern pattern = Pattern.compile("[(]?\\d{3}[-.)]\\d{3}[-.]\\d{4}");
        Matcher matcher = pattern.matcher(phones);
        String result = "";
        final String replacement = "XXX-XXXX";
        if(matcher.find()) {
            result = matcher.replaceAll(matchResult -> matchResult.group().replaceAll("\\d{3}[-.]\\d{4}", replacement));
            Pattern firstPartPattern = Pattern.compile("[(]?\\d+[).]?");
            matcher = firstPartPattern.matcher(result);
            StringBuilder tempResult = new StringBuilder();
            while(matcher.find()) {
                String group = matcher.group();
                Pattern firstDigitsPattern = Pattern.compile("\\d+");
                Matcher firstDigitsMatcher = firstDigitsPattern.matcher(group);
                String firstThreeDigits = "";
                if(firstDigitsMatcher.find()) firstThreeDigits = matcher.group();
                if(firstThreeDigits.startsWith("(")) firstThreeDigits = firstThreeDigits.substring(1, firstThreeDigits.length() - 1);
                if(firstThreeDigits.endsWith(".")) firstThreeDigits = firstThreeDigits.substring(0, firstThreeDigits.length() - 1);
                tempResult.append(matcher.group().replaceFirst("[(]?\\d+[).]?", firstThreeDigits + "-"));
                tempResult.append(replacement).append(" ");
            }
            result = tempResult.substring(0, tempResult.toString().length() - 1);
        }
        return result;
    }

    /**
     * You have a text with resources and links to those resources:
     * - [Bobocode](https://www.bobocode.com)
     * Create matcher and use method replaceAll to get the following result:
     * - <a href="https://www.bobocode.com">Bobocode</a>
     *
     * @return String where all resources embraced in href
     */
    public String insertLinksAndResourcesIntoHref(String links) {
        Pattern urlPattern = Pattern.compile("(?<=\\().+(?=\\))");
        Pattern namePattern = Pattern.compile("(?<=\\[).+(?=])");
        Matcher urlMatcher = urlPattern.matcher(links);
        Matcher nameMatcher = namePattern.matcher(links);
        List<String> urls = new ArrayList<>();
        Queue<String> names = new LinkedList<>();
        while(urlMatcher.find()) {
            urls.add(urlMatcher.group());
        }
        while(nameMatcher.find()) {
            names.add(nameMatcher.group());
        }
        StringBuilder result = new StringBuilder();
        for(String url : urls) {
            result.append("<a href=\"").append(url).append("\">").append(names.poll()).append("</a>").append("\n");
        }
        return result.substring(0, result.toString().length() - 1);
    }
}
