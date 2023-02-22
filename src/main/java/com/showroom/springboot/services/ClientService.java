package com.showroom.springboot.services;

import com.showroom.springboot.Utils;
import com.showroom.springboot.model.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@Service
public class ClientService {

    @Autowired
    ClientService clientService;

    private List <Client> clients;

    public List<Client> getClientList() {
        return getAllClients();
    }

    // getFunctions
    public List<Client> getAllClients() {
        List<Client> clients = new ArrayList<>();
        DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
        try {
            DocumentBuilder builder = factory.newDocumentBuilder();
            Document doc = builder.parse(Utils.SHOWROOM_XML_PATH);
            NodeList clientList = doc.getElementsByTagName("Client");
            String infoMessage;
            for (int i = 0; i < clientList.getLength(); i++) {
                List<String> filters = new ArrayList<>();
                Client clientObj = new Client();
                Node node = clientList.item(i);
                Element client = (Element) node;

                String id = client.getAttribute("id");
                String familyId = client.getAttribute("familyId");
                String gender = client.getAttribute("gender");

                clientObj.setId(Integer.parseInt(id));
                clientObj.setFamilyId(Integer.parseInt(familyId));
                clientObj.setGender(gender);
                NodeList infos = client.getChildNodes();
                for (int j = 0; j < infos.getLength(); j++) {
                    Node nodeInfo = infos.item(j);
                    if (nodeInfo.getNodeType() == Node.ELEMENT_NODE) {
                        Element info = (Element) nodeInfo;
                        if (info.getTagName().equalsIgnoreCase("firstName"))
                            clientObj.setFirstName(info.getTextContent());
                        else {
                            if (info.getTagName().equalsIgnoreCase("lastName"))
                                clientObj.setLastName(info.getTextContent());
                            else {
                                if (info.getTagName().equalsIgnoreCase("preferredCar")) {
                                    clientObj.setPreferredCar(info.getTextContent());
                                } else {
                                    if (info.getTagName().equalsIgnoreCase("filter")) {
                                        setFilters(info, filters);
                                    }
                                }
                            }
                        }
                    }
                }
                clients.add(clientObj);
            }
        } catch (ParserConfigurationException | IOException | SAXException e) {
            e.printStackTrace();
        }
        return clients;
    }

    public void setFilters(Element info, List<String> filters)
    {
        NodeList filtersList = info.getChildNodes();
        for (int k = 0; k < filtersList.getLength(); k++) {
            Node filterNodeInfo = filtersList.item(k);
            if (filterNodeInfo.getNodeType() == Node.ELEMENT_NODE) {
                {
                    Element filterInfo = (Element) filterNodeInfo;
                    if (filterInfo.getTagName().equalsIgnoreCase("filter")) {
                        filters.add(filterInfo.getTextContent());
                    }
                }
            }
        }
    }
}
