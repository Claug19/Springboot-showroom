package com.showroom.springboot;

import org.apache.camel.Exchange;
import org.apache.camel.Processor;
import org.apache.camel.builder.RouteBuilder;

import java.io.FileInputStream;
import java.io.InputStream;

public class REST extends RouteBuilder {
    private static final String TEXT_HTML = "text/html";
    private static final String TEXT_XML = "text/xml";

    private static final Processor PROCESSOR = new Processor() {
        @Override
        public void process(Exchange exchange) throws Exception {
            final StringBuilder stBuilder = new StringBuilder();
            int buf;
            try(InputStream is = new FileInputStream(Utils.SHOWROOM_XML_PATH)){
                while((buf = is.read()) != -1) {
                    stBuilder.append((char) buf);
                }
                exchange.getIn().setBody(stBuilder.toString());
            }
        }
    };

    @Override
    public void configure() throws Exception {
        restConfiguration().component("restlet").host("localhost").port(1234).enableCORS(true);

        // 1. http://localhost:1234/api/camel/cars
        rest("/api/camel").get("/cars")
                .produces(TEXT_HTML)
                .route()
                .process(PROCESSOR)
                .to("xslt:file:" + Utils.PROJECT_FOLDER + "\\requestStylesheet1.xsl");

        //2. http://localhost:1234/api/camel/cars/{CarBrand}
        rest("/api/camel").get("/cars/{CarBrand}")
                .produces(TEXT_HTML)
                .route()
                .process(PROCESSOR)
                .to("xslt:file:" + Utils.PROJECT_FOLDER + "\\requestStylesheet2.xsl");

        // 3. http://localhost:1234/api/camer/cars/{CarBrand}/{consumption}
        rest("/api/camel").get("cars/{CarBrand}/{consumption}")
                .produces(TEXT_HTML)
                .route()
                .process(PROCESSOR)
                .to("xslt:file:" + Utils.PROJECT_FOLDER + "\\requestStylesheet3.xsl");

        // 4. http://localhost:1234/api/camel/carModelStartsWith/{string}
        rest("/api/camel").get("/carModelStartsWith/{string}")
                .produces(TEXT_HTML)
                .route()
                .process(PROCESSOR)
                .to("xslt:file:" + Utils.PROJECT_FOLDER + "\\requestStylesheet4.xsl");

        // 5. http://localhost:1234/api/camel/clients
        rest("/api/camel").get("/clients")
                .produces(TEXT_HTML)
                .route()
                .process(PROCESSOR)
                .to("xslt:file:" + Utils.PROJECT_FOLDER + "\\requestStylesheet5.xsl");

        // 6. http://localhost:1234/api/camel/discounts
        rest("/api/camel").get("/discounts")
                .produces(TEXT_HTML)
                .route()
                .process(PROCESSOR)
                .to("xslt:file:" + Utils.PROJECT_FOLDER + "\\requestStylesheet6.xsl");


        // 7. http://localhost:1234/api/camel/carsByLowerPrice/{lowerPrice}
        rest("/api/camel").get("/carsByLowerPrice/{lowerPrice}")
                .produces(TEXT_HTML)
                .route()
                .process(PROCESSOR)
                .to("xslt:file:" + Utils.PROJECT_FOLDER + "\\requestStylesheet7.xsl");
    }
}
