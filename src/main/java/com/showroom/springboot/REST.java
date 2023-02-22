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

        // 1. http://localhost:1234/api/camel/carBrand/Dacia
        rest("/api/camel").get("/carBrand/{brand}")
                .produces(TEXT_HTML)
                .route()
                .process(PROCESSOR)
                .to("xslt:file:" + Utils.PROJECT_FOLDER + "\\requestStylesheet1.xsl");

        //2. http://localhost:1234/api/camel/carBrandStartsWith/2010
        rest("/api/camel").get("/carBrandStartsWith/{year}")
                .produces(TEXT_HTML)
                .route()
                .process(PROCESSOR)
                .to("xslt:file:" + Utils.PROJECT_FOLDER + "\\requestStylesheet2.xsl");

        // 3. http://localhost:1234/api/camel/booksStartWithA
        rest("/api/camel").get("/booksStartWithA")
                .produces(TEXT_HTML)
                .route()
                .process(PROCESSOR)
                .to("xslt:file:" + Utils.PROJECT_FOLDER + "\\requestStylesheet3.xsl");

        // 4. http://localhost:1234/api/camel/booksContainsWord/Arta
        rest("/api/camel").get("/booksContainsWord/{word}")
                .produces(TEXT_HTML)
                .route()
                .process(PROCESSOR)
                .to("xslt:file:" + Utils.PROJECT_FOLDER + "\\requestStylesheet4.xsl");

        // 5. http://localhost:1234/api/camel/chapterNumber
        rest("/api/camel").get("/chapterNumber")
                .produces(TEXT_HTML)
                .route()
                .process(PROCESSOR)
                .to("xslt:file:" + Utils.PROJECT_FOLDER + "\\requestStylesheet5.xsl");

        // 6. http://localhost:1234/api/camel/authorName/Jeff%20Keller
        rest("/api/camel").get("/authorName/{name}")
                .produces(TEXT_HTML)
                .route()
                .process(PROCESSOR)
                .to("xslt:file:" + Utils.PROJECT_FOLDER + "\\requestStylesheet6.xsl");


        rest("/api/camel").get("/books/{bookId}")
                .produces(TEXT_XML)
                .route()
                .process(PROCESSOR)
                .to("xslt:file:" + Utils.PROJECT_FOLDER + "\\requestStylesheet7.xsl");

//        rest("/books").get("/{bookId}")
//                .produces("text/xml")
//                .route()
//                .process(PROCESSOR)
//                .transform().xquery("resource:classpath:myxquery.txt", String.class);
//                .to("xslt:file:C:\\\\Users\\\\marin\\\\Projects\\\\xml\\\\xmlProject\\\\src\\\\main\\\\java\\\\com\\\\library_project\\\\booksById.xsl");
    }
}
