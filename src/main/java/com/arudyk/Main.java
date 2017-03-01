package com.arudyk;

import java.io.IOException;
import java.util.*;

public class Main {

    public static void main(String[] args) throws IOException {
        if (args[0] != null) {
            String htmlFile = UrlProcessor.getPageAsString(args[0]);
            HtmlParser htmlParser = new HtmlParser(args[0], htmlFile);
            List<String> unsortedList = htmlParser.parse();
            SorterOfStrings sorterOfStrings = new SorterOfStrings(unsortedList);
            sorterOfStrings.displayResults();
        } else {
            System.out.println("URL has not been entered");
        }
    }
}