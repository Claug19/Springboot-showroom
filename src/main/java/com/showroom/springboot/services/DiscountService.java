package com.showroom.springboot.services;

import com.showroom.springboot.Utils;
import com.showroom.springboot.model.Discount;
import org.springframework.stereotype.Service;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

import javax.annotation.PostConstruct;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@Service
public class DiscountService {

    private List<Discount> discounts;

    @PostConstruct
    private void iniDataForTesting() {
        discounts = new ArrayList<Discount>();
        getAllDiscounts();
    }

    public List<Discount> getDiscountsList() {
        return getAllDiscounts();
    }

    // getFunctions
    public List<Discount> getAllDiscounts() {
        List<Discount> discounts = new ArrayList<>();
        DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
        try {
            DocumentBuilder builder = factory.newDocumentBuilder();
            Document doc = builder.parse(Utils.SHOWROOM_XML_PATH);
            NodeList discountList = doc.getElementsByTagName("Discount");
            for (int i = 0; i < discountList.getLength(); i++) {
                Discount discountObj = new Discount();
                Node node = discountList.item(i);
                Element discount = (Element) node;
                String idRef = discount.getAttribute("idRef");
                discountObj.setIdRef(Integer.parseInt(idRef));
                NodeList infos = discount.getChildNodes();
                for (int j = 0; j < infos.getLength(); j++) {
                    Node nodeInfo = infos.item(j);
                    if (nodeInfo.getNodeType() == Node.ELEMENT_NODE) {
                        Element info = (Element) nodeInfo;
                        if (info.getTagName().equalsIgnoreCase("expirationDate"))
                            discountObj.setExpirationDate(info.getTextContent());
                        else {
                            if (info.getTagName().equalsIgnoreCase("value"))
                                discountObj.setValue(Integer.parseInt(info.getTextContent()));
                        }
                    }
                }
                discounts.add(discountObj);
            }
        } catch (ParserConfigurationException | IOException | SAXException e) {
            e.printStackTrace();
        }
        return discounts;
    }

}
