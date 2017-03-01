package com.arudyk;

import java.io.IOException;

public class Main {

    public static void main(String[] args) throws IOException {
           Builder builder= new  Builder(args[0]);
           builder.viewResult();
    }

}