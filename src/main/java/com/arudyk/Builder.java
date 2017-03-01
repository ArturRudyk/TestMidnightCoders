package com.arudyk;

import java.io.IOException;
import java.util.List;

public class Builder {

    String url;

    public Builder(String url) {
        this.url = url;
    }

    public void viewResult() throws IOException {
        String htmlFile = UrlProcessor.getPageAsString(url);
        HtmlParser htmlParser = new HtmlParser(url, htmlFile);
        List<String> unsortedList = htmlParser.parse();
        SorterOfStrings sorterOfStrings = new SorterOfStrings(unsortedList);
        sorterOfStrings.displayResults();
    }
}
