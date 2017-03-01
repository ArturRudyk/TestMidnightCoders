package com.arudyk;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
public class HtmlParser {

    String[] forbiddenSymbols = {"0", "1", "2", "3", "4", "5", "6", "7", "8", "9", "!", "?", "&"};
    String[] ignoredTags = {"<script", "<style", "<img", "<object"};
    String htmlDocument;
    String url;

    public String getHtmlDocument() {
        return htmlDocument;
    }
    public void setHtmlDocument(String htmlDocument) {
        this.htmlDocument = htmlDocument;
    }

    HtmlParser(String url, String htmlDocument) {
        this.url = url;
        this.htmlDocument = htmlDocument;
    }

    public List<String> parse() {
        if (isHtmlDocument()) {
            int leftPosition = htmlDocument.indexOf(">", htmlDocument.indexOf("<body"));
            int rightPosition = 0;
            List<String> parsedList = new ArrayList<String>();
            while (leftPosition < htmlDocument.length() - 1) {
                rightPosition = htmlDocument.indexOf("<", leftPosition);
                if (leftPosition + 1 != rightPosition && !isIgnoredTag(leftPosition)) {
                    String[] text = htmlDocument.substring(leftPosition + 1, rightPosition).split("[-,.;:/\\s+()]");
                    for (String t : text) {
                        parsedList.add(t);
                    }
                }
                leftPosition = htmlDocument.indexOf(">", rightPosition);
            }
            deleteInvalidElements(parsedList);
            return parsedList;
        } else {
            throw new RuntimeException("This URL - " + url + "does not contain html content");
        }
    }

    private boolean isIgnoredTag(int leftPosition) {
        int beginOfTag = htmlDocument.substring(0, leftPosition).lastIndexOf("<");
        for (int i = 0; i < ignoredTags.length; i++) {
            if (htmlDocument.substring(beginOfTag).startsWith(ignoredTags[i])) {
                return true;
            }
        }
        return false;
    }

    private void deleteInvalidElements(List<String> listWithInvalidElements) {
        Iterator iterator = listWithInvalidElements.iterator();
        while (iterator.hasNext()) {
            String currentString = (String) iterator.next();
            if  (currentString.equals("") || !isValid(currentString)) {
                iterator.remove();
            }
        }
    }

    private boolean isValid(String stringForValidation) {
        for (int i =0; i < forbiddenSymbols.length; i++) {
            if (stringForValidation.contains(forbiddenSymbols[i])) {
                return false;
            }
        }
        return true;
    }

    private boolean isHtmlDocument() {
        if (htmlDocument.contains("<!DOCTYPE html") || htmlDocument.contains("<!doctype html")
                || htmlDocument.contains("<!DOCTYPE HTML")) {
            return true;
        } else {
            return false;
        }
    }
}