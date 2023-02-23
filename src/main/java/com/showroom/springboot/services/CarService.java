package com.showroom.springboot.services;

import com.showroom.springboot.Utils;
import com.showroom.springboot.model.Car;
import com.showroom.springboot.model.CarCompact;
import com.showroom.springboot.model.ReservedCar;
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
import javax.xml.transform.OutputKeys;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class CarService {

    private List<Car> cars;

    @PostConstruct
    private void iniDataForTesting() {
        cars = new ArrayList<Car>();
        getAllCars();
    }

    public List<Car> getCarList() {
        return getAllCars();
    }

    public List<Car> findCarsByBrand(String carBrand) {
        List<Car> result = getAllCars()
                .stream()
                .filter(x -> x.getCarBrand().equalsIgnoreCase(carBrand))
                .collect(Collectors.toList());
        return result;
    }

    public List<CarCompact> findCarsThatStartWith(String carBrandStartWith) {
        List<CarCompact> result = getAllCarsCompact().stream()
                .filter(x -> x.getCarBrand().startsWith(carBrandStartWith))
                .collect(Collectors.toList());
        return result;
    }

    public List<Car> findCarsByModel(String model) {
        List<Car> result = getAllCars()
                .stream()
                .filter(x -> x.getModel().equalsIgnoreCase(model))
                .collect(Collectors.toList());
        return result;
    }

    public List<CarCompact> findCarsByModelAndYear(String model, int year) {
        List<CarCompact> result = getAllCarsCompact()
                .stream()
                .filter(x -> x.getModel().equalsIgnoreCase(model))
                .filter(x -> x.getYear() == year)
                .collect(Collectors.toList());
        return result;
    }

    public List<CarCompact> findCarsByModelAndPrice(String model, int price) {
        List<CarCompact> result = getAllCarsCompact()
                .stream()
                .filter(x -> x.getModel().equalsIgnoreCase(model))
                .filter(x -> x.getPrice() <= price)
                .collect(Collectors.toList());
        return result;
    }

    // getFunctions
    public List<Car> getAllCars() {
        List<Car> cars = new ArrayList<>();
        DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
        try {
            DocumentBuilder builder = factory.newDocumentBuilder();
            Document doc = builder.parse(Utils.SHOWROOM_XML_PATH);
            NodeList carsList = doc.getElementsByTagName("Car");
            String infoMessage;
            for (int i = 0; i < carsList.getLength(); i++) {
                List<String> features = new ArrayList<>();
                Car carObj = new Car();
                Node node = carsList.item(i);
                Element car = (Element) node;
                String id = car.getAttribute("carId");
                carObj.setCarId(Integer.parseInt(id));
                NodeList infos = car.getChildNodes();
                for (int j = 0; j < infos.getLength(); j++) {
                    Node nodeInfo = infos.item(j);
                    if (nodeInfo.getNodeType() == Node.ELEMENT_NODE) {
                        Element info = (Element) nodeInfo;
                        if (info.getTagName().equalsIgnoreCase("carBrand"))
                            carObj.setCarBrand(info.getTextContent());
                        else {
                            if (info.getTagName().equalsIgnoreCase("model"))
                                carObj.setModel(info.getTextContent());
                            else {
                                if (info.getTagName().equalsIgnoreCase("type")) {
                                    carObj.setType(info.getTextContent());
                                } else {
                                    if (info.getTagName().equalsIgnoreCase("consumption")) {
                                        carObj.setConsumption(info.getTextContent());
                                    } else {
                                        if (info.getTagName().equalsIgnoreCase("year"))
                                            carObj.setYear(Integer.parseInt(info.getTextContent()));
                                        else {
                                            if (info.getTagName().equalsIgnoreCase("price")){
                                                carObj.setPrice(Integer.parseInt(info.getTextContent()));
                                                carObj.setCurrency(info.getAttribute("currency"));
                                            }
                                            else {
                                                if (info.getTagName().equalsIgnoreCase("features"))
                                                    setFeatures(info, features);
                                            }
                                        }
                                    }
                                }
                            }
                        }
                    }
                }
                carObj.setFeatures(features);
                cars.add(carObj);
            }
        } catch (ParserConfigurationException | IOException | SAXException e) {
            e.printStackTrace();
        }
        return cars;
    }

    public void setFeatures(Element info, List<String> features)
    {
        NodeList featuresList = info.getChildNodes();
        for (int k = 0; k < featuresList.getLength(); k++) {
            Node featureNodeInfo = featuresList.item(k);
            if (featureNodeInfo.getNodeType() == Node.ELEMENT_NODE) {
                {
                    Element featureInfo = (Element) featureNodeInfo;
                    if (featureInfo.getTagName().equalsIgnoreCase("feature")) {
                        features.add(featureInfo.getTextContent());
                    }
                }
            }
        }
    }

    public List<CarCompact> getAllCarsCompact() {
        List<CarCompact> cars = new ArrayList<>();
        DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
        try {
            DocumentBuilder builder = factory.newDocumentBuilder();
            Document doc = builder.parse(Utils.SHOWROOM_XML_PATH);
            NodeList carList = doc.getElementsByTagName("Car");
            String infoMessage;
            for (int i = 0; i < carList.getLength(); i++) {
                CarCompact carObj = new CarCompact();
                Node node = carList.item(i);
                Element car = (Element) node;
                String id = car.getAttribute("id");
                NodeList infos = car.getChildNodes();
                for (int j = 0; j < infos.getLength(); j++) {
                    Node nodeInfo = infos.item(j);
                    if (nodeInfo.getNodeType() == Node.ELEMENT_NODE) {
                        Element info = (Element) nodeInfo;
                        if (info.getTagName().equalsIgnoreCase("carBrand"))
                            carObj.setCarBrand(info.getTextContent());
                        else {
                            if (info.getTagName().equalsIgnoreCase("model")) {
                                carObj.setModel(info.getTextContent());
                            }
                        }
                    }
                }
                cars.add(carObj);
            }
        } catch (ParserConfigurationException | IOException | SAXException e) {
            e.printStackTrace();
        }
        return cars;
    }

    public List<ReservedCar> getAllReservedCars() {
        List<ReservedCar> reservedCars = new ArrayList<>();
        DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
        try {
            DocumentBuilder builder = factory.newDocumentBuilder();
            Document doc = builder.parse(Utils.SHOWROOM_XML_PATH);
            NodeList reservedCarList = doc.getElementsByTagName("ReservedCar");
            String infoMessage;
            for (int i = 0; i < reservedCarList.getLength(); i++) {
                ReservedCar reservedCarObj = new ReservedCar();
                Node node = reservedCarList.item(i);
                Element reservedCar = (Element) node;
                NodeList infos = reservedCar.getChildNodes();
                for (int j = 0; j < infos.getLength(); j++) {
                    Node nodeInfo = infos.item(j);
                    if (nodeInfo.getNodeType() == Node.ELEMENT_NODE) {
                        Element info = (Element) nodeInfo;
                        if (info.getTagName().equalsIgnoreCase("clientId"))
                            reservedCarObj.setClientId(Integer.parseInt(info.getTextContent()));
                        else {
                            if (info.getTagName().equalsIgnoreCase("carId"))
                                reservedCarObj.setCarId(Integer.parseInt(info.getTextContent()));
                        }
                    }
                }
                reservedCars.add(reservedCarObj);
            }
        } catch (ParserConfigurationException | SAXException | IOException e) {
            e.printStackTrace();
        }
        return reservedCars;
    }

    public void addReservedCar(ReservedCar car) {
        DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
        try {
            DocumentBuilder builder = factory.newDocumentBuilder();
            Document doc = builder.parse(Utils.SHOWROOM_XML_PATH);
            NodeList cars = doc.getElementsByTagName("ReservedCar");

            Element root = doc.getDocumentElement();
            NodeList rootElement = doc.getElementsByTagName("ReservedCars");

            Element newUser = rootElement.item(0).getFirstChild().getOwnerDocument().createElement("ReservedCar");

            Element clientId = doc.createElement("clientId");
            clientId.appendChild(doc.createTextNode(String.valueOf(car.getClientId())));
            newUser.appendChild(clientId);

            Element model = doc.createElement("carId");
            model.appendChild(doc.createTextNode(String.valueOf(car.getCarId())));
            newUser.appendChild(model);

            rootElement.item(rootElement.getLength()-1).appendChild(newUser);

            DOMSource source = new DOMSource(doc);

            TransformerFactory transformerFactory = TransformerFactory.newInstance();
            Transformer transformer = transformerFactory.newTransformer();
            StreamResult result = new StreamResult(Utils.SHOWROOM_XML_PATH);
            transformer.transform(source, result);


        } catch (ParserConfigurationException | SAXException | IOException | TransformerException e) {
            e.printStackTrace();
        }
    }

    public void deleteReservedCar(ReservedCar car) {
        String xmlFile = Utils.SHOWROOM_XML_PATH;
        try {
            DocumentBuilderFactory documentBuilderFactory = DocumentBuilderFactory.newInstance();
            DocumentBuilder documentBuilder = documentBuilderFactory.newDocumentBuilder();
            Document document = documentBuilder.parse(xmlFile);
            NodeList reservedCars = document.getElementsByTagName("ReservedCar");
            for (int i = 0; i < reservedCars.getLength(); i++) {
                Element reservedCar = (Element) reservedCars.item(i);
                Element clientId = (Element) reservedCar.getElementsByTagName("clientId").item(0);
                Element carId = (Element) reservedCar.getElementsByTagName("carId").item(0);
                if (clientId.getTextContent().equalsIgnoreCase(String.valueOf(car.getClientId())) &&
                        carId.getTextContent().equalsIgnoreCase(String.valueOf(car.getCarId()))) {
                    clientId.getParentNode().getParentNode().removeChild(reservedCars.item(i));
                }
            }
            saveXMLContent(document, xmlFile);
        } catch (Exception e) {
            System.err.println(e.getMessage());
        }
    }

    private static void saveXMLContent(Document document, String xmlFile) {
        try {
            TransformerFactory transformerFactory = TransformerFactory.newInstance();
            Transformer transformer = transformerFactory.newTransformer();
            transformer.setOutputProperty(OutputKeys.INDENT, "yes");
            DOMSource domSource = new DOMSource(document);
            StreamResult streamResult = new StreamResult(xmlFile);
            transformer.transform(domSource, streamResult);
        } catch (Exception ex) {
            System.out.println(ex.getMessage());
        }
    }

    public void delete(String id) {
        String xmlFile = Utils.SHOWROOM_XML_PATH;
        try {
            DocumentBuilderFactory documentBuilderFactory = DocumentBuilderFactory.newInstance();
            DocumentBuilder documentBuilder = documentBuilderFactory.newDocumentBuilder();
            Document document = documentBuilder.parse(xmlFile);
            NodeList products = document.getElementsByTagName("ReservedCar");
            for (int i = 0; i < products.getLength(); i++) {
                Element product = (Element) products.item(i);
                Element idTag = (Element) product.getElementsByTagName("clientId").item(0);
                if (idTag.getTextContent().equalsIgnoreCase(id)) {
                    idTag.getParentNode().getParentNode().removeChild(products.item(i));
                    break;
                }
            }
            saveXMLContent(document, xmlFile);
        } catch (Exception e) {
            System.err.println(e.getMessage());
        }
    }

    public void addCar(Car car) {
        DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
        try {
            DocumentBuilder builder = factory.newDocumentBuilder();
            Document doc = builder.parse(Utils.SHOWROOM_XML_PATH);
            NodeList cars = doc.getElementsByTagName("Car");
            NodeList rootElement = doc.getElementsByTagName("AvailableCars");

            Element lastElement = (Element) cars.item(cars.getLength()-1);  // get the last car
            int lastId = Integer.parseInt(lastElement.getAttribute("carId").replaceAll("[^0-9]", ""));  // obtain id
            String newId = String.valueOf(lastId+1);

            Element newCar = cars.item(0).getFirstChild().getOwnerDocument().createElement("Car");
            newCar.setAttribute("carId", newId);

            Element carBrand = doc.createElement("carBrand");
            carBrand.appendChild(doc.createTextNode(car.getCarBrand()));
            newCar.appendChild(carBrand);

            Element model = doc.createElement("model");
            model.appendChild(doc.createTextNode(car.getModel()));
            newCar.appendChild(model);

            Element type = doc.createElement("type");
            type.appendChild(doc.createTextNode(car.getType()));
            newCar.appendChild(type);

            Element consumption = doc.createElement("consumption");
            consumption.appendChild(doc.createTextNode(car.getConsumption()));
            newCar.appendChild(consumption);

            Element year = doc.createElement("year");
            year.appendChild(doc.createTextNode(String.valueOf(car.getYear())));
            newCar.appendChild(year);

            Element price = doc.createElement("price");
            price.appendChild(doc.createTextNode(String.valueOf(car.getPrice())));
            price.setAttribute("currency", car.getCurrency());
            newCar.appendChild(price);

            rootElement.item(rootElement.getLength()-1).appendChild(newCar);

            DOMSource source = new DOMSource(doc);

            TransformerFactory transformerFactory = TransformerFactory.newInstance();
            Transformer transformer = transformerFactory.newTransformer();
            StreamResult result = new StreamResult(Utils.SHOWROOM_XML_PATH);
            transformer.transform(source, result);

        } catch (ParserConfigurationException | SAXException | IOException | TransformerException e) {
            e.printStackTrace();
        }
    }
}