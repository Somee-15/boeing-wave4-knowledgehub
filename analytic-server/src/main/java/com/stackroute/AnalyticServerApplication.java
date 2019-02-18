package com.stackroute;

import com.stackroute.NlpService.NlpServiceImpl;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class AnalyticServerApplication {

    public static void main(String[] args) {
        SpringApplication.run(AnalyticServerApplication.class, args);
        NlpServiceImpl nlpService = new NlpServiceImpl();
        nlpService.setParagraph("The Spring Framework Inversion of Control (IoC) component " +
                "addresses this concern by providing a formalized means of composing disparate components into a" +
                " fully working application ready for use. The Spring Framework codifies formalized design patterns as" +
                " first-class objects that you can integrate into your own application(s). Numerous organizations and institutions" +
                " use the Spring Framework in this manner to engineer robust, maintainable applications.");
        nlpService.showAllResults();
    }
}