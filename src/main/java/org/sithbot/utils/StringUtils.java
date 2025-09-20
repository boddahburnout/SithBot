package org.sithbot.utils;

import darth.leaflyscrape.DataFetch;
import org.json.simple.JSONObject;

import java.util.ArrayList;
import java.util.List;

public class StringUtils {
    public String ListToString(List<String> list) {
        StringBuilder sb = new StringBuilder();
        for (String string : list) {
            sb.append(string+"\n");
        }
        return sb.toString();
    }
    public List GetCardsFromEmote(String string) {
        int i = 0;
        List cards = new ArrayList();
        List pos = new ArrayList();
        for (char c : string.toCharArray()) {
            i++;
            if (String.valueOf(c).equalsIgnoreCase(":")) {
                pos.add(i);
            }
        }
        for (int m = 0; m < pos.size(); m++) {
            cards.add(string.substring(Integer.parseInt(pos.get(m).toString()), Integer.parseInt(pos.get(m+1).toString())-1));
            m = m+1;
        }
        return cards;
    }
    public String UpdateScore(String string, int score) {
        int i = 0;
        List pos = new ArrayList();
        for (char c : string.toCharArray()) {
            i++;
            if (String.valueOf(c).equalsIgnoreCase("(")) {
                pos.add(i);
            }
            if (String.valueOf(c).equalsIgnoreCase(")")) {
                pos.add(i);
            }
        }
        string = string.replaceAll(string.substring(Integer.parseInt(pos.get(0).toString()), Integer.parseInt(pos.get(1).toString())-1), String.valueOf(score));
        return string;
    }
    public String FormatWeedData(JSONObject strain) {
        DataFetch dataFetch = new DataFetch();
        //       Object[] PopularIn = leaflyApi.getPopularIn(strain);
        //Map<Object, Object> Cannabinoids = dataFetch.getCannabinoids(strain);
        int rating;
        try {
            rating = Double.valueOf((Double) dataFetch.getAverageRating(strain)).intValue();
        } catch (ClassCastException e) {
            rating = 0;
        }
        //String weedmessage = leaflyApi.getDescriptionPlain(strain) + "\n\nPopular in: " + PopularIn[0] + "\n" + "\nCategory: " + leaflyApi.getCategory(strain) + "\n\nChemotype: " + leaflyApi.getChemotype(strain) + "\n\nTop Effects: " + leaflyApi.getTopEffect(strain) + "\n\nRating: " + rating;
        StringBuilder stringBuilder1 = new StringBuilder();

        try {
            stringBuilder1.append(dataFetch.getDescriptionPlain(strain)+"\n");
        } catch (NullPointerException e) {

        }

        try {
            stringBuilder1.append("\n\nPopular in: " + dataFetch.getPopularIn(strain) [0]+"\n");
        } catch (NullPointerException e) {

        }

        try {
            stringBuilder1.append("\nCategory: "+ dataFetch.getCategory(strain));
        } catch (NullPointerException e) {

        }


        try {
            stringBuilder1.append("\n\nTop Effects: " + dataFetch.getTopEffect(strain));
        } catch (NullPointerException e) {

        }

        stringBuilder1.append("\n\nRating: " + rating);

        if (dataFetch.getThcPotency(strain) != null) {
            Object thc = dataFetch.getThcPotency(strain);
            stringBuilder1.append("\nTHC: " + thc.toString() + "\\%");
        }

        return stringBuilder1.toString().replaceAll("null", "0");
    }
}