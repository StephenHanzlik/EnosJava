package com.enos;

import com.enos.model.Observation;
import com.enos.model.Station;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;
import java.util.Map;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;


// https://github.com/FasterXML/jackson-databind/
//https://mkyong.com/java/how-to-read-and-parse-csv-file-in-java/
public class CSVUtils {

    private static final char DEFAULT_SEPARATOR = ',';
    private static final char DEFAULT_QUOTE = '"';

    public static ArrayList convertCSVToJSON(String file) throws JsonProcessingException {

    Scanner scanner = new Scanner(file);

    //TODO: May want to increase this if pulling 5+ years of data
    ArrayList observationsJson = new ArrayList(500);

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
            observation.setAirTemperatureAverage(line.get(5));
            observation.setAirTemperatureMin(line.get(6));
            observation.setAirTemperatureMax(line.get(7));
            observation.setAirTemperatureObserved(line.get(8));

            //This needs to loop through each line and create objects of each line

            ObjectMapper mapper = new ObjectMapper(); // create once, reuse
            Map<String, Object> observationMap = mapper.convertValue(observation, Map.class);

                observationsJson.add(observationMap);
        }
    }
    scanner.close();
    return observationsJson;
    }

    public String arrayListToJSON(List mylist) {
        Gson gson = new Gson();
        String jsonArray = gson.toJson(mylist);
        return jsonArray;
    }

    public Station jsonToStation(String json){
        Gson gson = new Gson();
        Station station = gson.fromJson(json, Station.class);
        return station;
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