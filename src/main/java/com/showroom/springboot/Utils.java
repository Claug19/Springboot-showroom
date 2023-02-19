package com.showroom.springboot;
import java.io.FileInputStream;
import java.io.InputStream;

import org.apache.camel.CamelContext;

import org.apache.camel.ProducerTemplate;
import org.apache.camel.builder.RouteBuilder;
import org.apache.camel.impl.DefaultCamelContext;
import org.apache.camel.main.Main;

public class Utils {

    public static final String MAIN_FOLDER = "..\\..";
    public static final String RESOURCES_FOLDER = MAIN_FOLDER + "\\resources";
    public static final String SHOWROOM_XML_PATH = RESOURCES_FOLDER + "\\showroom.xml";
    public static final String PROJECT_FOLDER = MAIN_FOLDER + "\\java\\com\\showroom\\springboot";

    public void withXSLT() throws Exception {
        final CamelContext camelContext = new DefaultCamelContext();

        try(final InputStream is = new FileInputStream(SHOWROOM_XML_PATH)) {
            camelContext.start();
            camelContext.addRoutes(new XSLTRoute());
            final ProducerTemplate template = camelContext.createProducerTemplate();
            template.sendBody("direct:start", is);
        } finally {
            camelContext.stop();
        }
    }

    public void camelRest() throws Exception {
        final Main camel = new Main();
        camel.addRouteBuilder(new REST());
        camel.run();
    }

    private static class XSLTRoute extends RouteBuilder {
        @Override
        public void configure() throws Exception {
            from("direct:start")
                    .to("xslt:file:" + PROJECT_FOLDER + "\\replace_xsl_library.xsl")
                    .to("stream:out");
        }
    }
}