package com.showroom.springboot.utils;

import com.showroom.springboot.Utils;
import com.showroom.springboot.model.User;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class DataUtils {

    public static List<User> getUsersMethod() {
        List<com.showroom.springboot.model.User> users = new ArrayList<>();
        DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
        try {
            DocumentBuilder builder = factory.newDocumentBuilder();
            Document doc = builder.parse(Utils.RESOURCES_FOLDER + "\\user.xml");
            NodeList usersX = doc.getElementsByTagName("user_data");
            for (int i = 0; i < usersX.getLength(); i++) {
                Node node = usersX.item(i);
                Element userX = (Element) node;
                String id = userX.getAttribute("id");
                String name = userX.getElementsByTagName("user_name").item(0).getFirstChild().getNodeValue();
                String pass = userX.getElementsByTagName("user_password").item(0).getFirstChild().getNodeValue();
                String email = userX.getElementsByTagName("user_email").item(0).getFirstChild().getNodeValue();

                User user = new User();
                user.setUsername(name);
                user.setPassword(pass);
                user.setEmail(email);
                user.setId(id);

                users.add(user);
                System.out.println();
            }
        } catch (ParserConfigurationException | SAXException | IOException e) {
            e.printStackTrace();
        }
        return users;
    }


    public static void addUser(User user) {
        DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
        try {
            DocumentBuilder builder = factory.newDocumentBuilder();
            Document doc = builder.parse(Utils.RESOURCES_FOLDER + "\\user.xml");
            NodeList usersX = doc.getElementsByTagName("user_data");

            Element root = doc.getDocumentElement();
            NodeList rootElement = doc.getElementsByTagName("user_list");

            Element newUser = rootElement.item(0).getFirstChild().getOwnerDocument().createElement("user_data");

            Element name = doc.createElement("user_name");
            name.appendChild(doc.createTextNode(user.getUsername()));
            newUser.appendChild(name);

            Element id = doc.createElement("id");
            id.appendChild(doc.createTextNode(user.getId()));
            newUser.appendChild(id);

            Element password = doc.createElement("user_password");
            password.appendChild(doc.createTextNode(user.getPassword()));
            newUser.appendChild(password);

            Element email = doc.createElement("user_email");
            email.appendChild(doc.createTextNode(user.getEmail()));
            newUser.appendChild(email);

            rootElement.item(0).appendChild(newUser);

            DOMSource source = new DOMSource(doc);

            TransformerFactory transformerFactory = TransformerFactory.newInstance();
            Transformer transformer = transformerFactory.newTransformer();
            StreamResult result = new StreamResult(Utils.RESOURCES_FOLDER + "\\user.xml");
            transformer.transform(source, result);


        } catch (SAXException | IOException | ParserConfigurationException | TransformerException e) {
            e.printStackTrace();
        }
    }

}
