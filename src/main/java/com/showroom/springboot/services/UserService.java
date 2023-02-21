package com.showroom.springboot.services;

import com.showroom.springboot.Utils;
import com.showroom.springboot.model.User;
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
import java.util.stream.Collectors;

@Service
public class UserService {

    private List<User> users;

    public List<User> findByUserNameOrEmail(String username) {
        List<User> result = users
                .stream()
                .filter(x -> x.getUsername().equalsIgnoreCase(username))
                .collect(Collectors.toList());

        return result;
    }

    @PostConstruct
    private void iniDataForTesting() {
        users = new ArrayList<User>();
        getAllUsers();
    }

    public void getAllUsers() {
        DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
        try {
            DocumentBuilder builder = factory.newDocumentBuilder();
            Document doc = builder.parse(Utils.RESOURCES_FOLDER + "\\user.xml");
            NodeList usersInfo = doc.getElementsByTagName("user_data");
            String infoMessage;
            for (int i = 0; i < usersInfo.getLength(); i++) {
                Node node = usersInfo.item(i);
                Element userInfo = (Element) node;
                String id = userInfo.getAttribute("id");
                String name = userInfo.getElementsByTagName("user_name").item(0).getFirstChild().getNodeValue().toString();
                String pass = userInfo.getElementsByTagName("user_password").item(0).getFirstChild().getNodeValue().toString();
                String email = userInfo.getElementsByTagName("user_email").item(0).getFirstChild().getNodeValue().toString();

                User user = new User();
                user.setUsername(name);
                user.setPassword(pass);
                user.setEmail(email);
                user.setId(id);

                users.add(user);
                System.out.println();
            }
        } catch (ParserConfigurationException e) {
            e.printStackTrace();
        } catch (SAXException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }


}