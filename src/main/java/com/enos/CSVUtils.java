package com.enos;

import com.enos.model.Observation;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

// https://github.com/FasterXML/jackson-databind/
//https://mkyong.com/java/how-to-read-and-parse-csv-file-in-java/
public class CSVUtils {

    private static final char DEFAULT_SEPARATOR = ',';
    private static final char DEFAULT_QUOTE = '"';

    public static String convertToJSON(String file) throws JsonProcessingException {

    Scanner scanner = new Scanner(file);

    while (scanner.hasNext()) {
        List<String> line = parseLine(scanner.nextLine());

        String firstItem = line.get(0);
        if(firstItem.indexOf("#") == -1 && firstItem.indexOf("Date") == -1){
            Observation observation = new Observation();
            observation.setDate(line.get(0));
            observation.setSnowWaterEquivalent(line.get(1));
            observation.setChangeInSnowWaterEquivalent(line.get(2));
            observation.setSnowDepth(line.get(3));
            observation.setChangeInSnowDepth(line.get(4));
            //We want to add air temp observe

            ObjectMapper mapper = new ObjectMapper(); // create once, reuse
            String jsonString = mapper.writeValueAsString(observation);

            return jsonString;
        }
    }
    scanner.close();
    //Throw an exception
    return "No Data Found";
    }


    public static List<String> parseLine(String cvsLine) {
        return parseLine(cvsLine, DEFAULT_SEPARATOR, DEFAULT_QUOTE);
    }

    public static List<String> parseLine(String cvsLine, char separators) {
        return parseLine(cvsLine, separators, DEFAULT_QUOTE);
    }

    public static List<String> parseLine(String cvsLine, char separators, char customQuote) {

        List<String> result = new ArrayList<>();

        //if empty, return!
        if (cvsLine == null && cvsLine.isEmpty()) {
            return result;
        }

        if (customQuote == ' ') {
            customQuote = DEFAULT_QUOTE;
        }

        if (separators == ' ') {
            separators = DEFAULT_SEPARATOR;
        }

        StringBuffer curVal = new StringBuffer();
        boolean inQuotes = false;
        boolean startCollectChar = false;
        boolean doubleQuotesInColumn = false;

        char[] chars = cvsLine.toCharArray();

        for (char ch : chars) {

            if (inQuotes) {
                startCollectChar = true;
                if (ch == customQuote) {
                    inQuotes = false;
                    doubleQuotesInColumn = false;
                } else {

                    //Fixed : allow "" in custom quote enclosed
                    if (ch == '\"') {
                        if (!doubleQuotesInColumn) {
                            curVal.append(ch);
                            doubleQuotesInColumn = true;
                        }
                    } else {
                        curVal.append(ch);
                    }

                }
            } else {
                if (ch == customQuote) {

                    inQuotes = true;

                    //Fixed : allow "" in empty quote enclosed
                    if (chars[0] != '"' && customQuote == '\"') {
                        curVal.append('"');
                    }

                    //double quotes in column will hit this!
                    if (startCollectChar) {
                        curVal.append('"');
                    }

                } else if (ch == separators) {

                    result.add(curVal.toString());

                    curVal = new StringBuffer();
                    startCollectChar = false;

                } else if (ch == '\r') {
                    //ignore LF characters
                    continue;
                } else if (ch == '\n') {
                    //the end, break!
                    break;
                } else {
                    curVal.append(ch);
                }
            }

        }

        result.add(curVal.toString());

        return result;
    }

}