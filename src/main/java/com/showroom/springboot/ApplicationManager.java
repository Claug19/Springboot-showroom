package com.showroom.springboot;

public class ApplicationManager {
    public void start() throws Exception {
        Utils camel = new Utils();
        camel.withXSLT();
        camel.camelRest();
    }
}
