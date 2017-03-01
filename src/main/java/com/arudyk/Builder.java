package com.arudyk;

import java.io.IOException;
import java.util.List;

public class Builder {

    public static void viewResult(String url) throws IOException {
        String htmlFile = UrlProcessor.getPageAsString(url);
        HtmlParser htmlParser = new HtmlParser(url, htmlFile);
        List<String> unsortedList = htmlParser.parse();
        SorterOfStrings sorterOfStrings = new SorterOfStrings(unsortedList);
        sorterOfStrings.displayResults();
    }
}
