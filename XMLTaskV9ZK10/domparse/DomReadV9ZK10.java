package domparse;

import org.w3c.dom.*;
import javax.xml.parsers.*;
import java.io.*;

public class DomReadV9ZK10 {
    public static void main(String[] args) {
        try {
            File inputFile = new File("XMLTaskV9ZK10\\ERV9ZK10.xml");

            DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
            DocumentBuilder dBuilder = dbFactory.newDocumentBuilder();
            Document doc = dBuilder.parse(inputFile);
            doc.getDocumentElement().normalize();

            System.out.println("Gyökérelem: " + doc.getDocumentElement().getNodeName());

            NodeList vasarlokList = doc.getElementsByTagName("Vásárló");

            for (int temp = 0; temp < vasarlokList.getLength(); temp++) {
                Node vasarloNode = vasarlokList.item(temp);

                System.out.println("\n" + vasarloNode.getNodeName());

                if (vasarloNode.getNodeType() == Node.ELEMENT_NODE) {
                    Element vasarloElement = (Element) vasarloNode;

                    System.out.println(" Jogkód: " + vasarloElement.getAttribute("jogkód"));
                    System.out.println(
                            " Email cím: " + vasarloElement.getElementsByTagName("email_cím").item(0).getTextContent());

                    Element cimElement = (Element) vasarloElement.getElementsByTagName("cím").item(0);
                    System.out
                            .println(" Ország: " + cimElement.getElementsByTagName("ország").item(0).getTextContent());
                    System.out.println(" Isz: " + cimElement.getElementsByTagName("isz").item(0).getTextContent());
                    System.out.println(" Város: " + cimElement.getElementsByTagName("város").item(0).getTextContent());
                    System.out.println(
                            " Utca és házszám: " + cimElement.getElementsByTagName("u_hsz").item(0).getTextContent());

                    System.out.println(" Név: " + vasarloElement.getElementsByTagName("név").item(0).getTextContent());
                    System.out.println(" Telefonszám: "
                            + vasarloElement.getElementsByTagName("Telefonszám").item(0).getTextContent());

                    // Extract information from Autós_cég elements
                    NodeList autosCegList = doc.getElementsByTagName("Autós_cég");
                    for (int i = 0; i < autosCegList.getLength(); i++) {
                        Node autosCegNode = autosCegList.item(i);

                        System.out.println("\nAutós_cég elem: " + autosCegNode.getNodeName());

                        if (autosCegNode.getNodeType() == Node.ELEMENT_NODE) {
                            Element autosCegElement = (Element) autosCegNode;

                            System.out.println(" Cég kód: " + autosCegElement.getAttribute("cég_kód"));
                            System.out.println(" Cégnév: "
                                    + autosCegElement.getElementsByTagName("cégnév").item(0).getTextContent());
                            System.out.println(" Helyrajzi szám: "
                                    + autosCegElement.getElementsByTagName("Helyrajzi_szám").item(0).getTextContent());
                            System.out.println(" Dolgozók száma: "
                                    + autosCegElement.getElementsByTagName("Dolgozók_száma").item(0).getTextContent());
                        }
                    }

                    NodeList autosAdatokList = doc.getElementsByTagName("Autós_adatok");
                    for (int i = 0; i < autosAdatokList.getLength(); i++) {
                        Node autosAdatokNode = autosAdatokList.item(i);

                        System.out.println("\nAutós_adatok elem: " + autosAdatokNode.getNodeName());

                        if (autosAdatokNode.getNodeType() == Node.ELEMENT_NODE) {
                            Element autosAdatokElement = (Element) autosAdatokNode;

                            System.out.println(" Forgalmi kód: " + autosAdatokElement.getAttribute("forgalmi_kód"));
                            System.out.println(" Cég kód: " + autosAdatokElement.getAttribute("cég_kód"));
                            System.out.println(
                                    " Ár: " + autosAdatokElement.getElementsByTagName("ár").item(0).getTextContent());
                            System.out.println(" Km óra: "
                                    + autosAdatokElement.getElementsByTagName("km_óra").item(0).getTextContent());
                            System.out.println(" Státusz: "
                                    + autosAdatokElement.getElementsByTagName("státusz").item(0).getTextContent());
                        }
                    }

                    // Extract information from Autós_típus elements
                    NodeList autosTipusList = doc.getElementsByTagName("Autós_típus");
                    for (int i = 0; i < autosTipusList.getLength(); i++) {
                        Node autosTipusNode = autosTipusList.item(i);

                        System.out.println("\nAutós_típus elem: " + autosTipusNode.getNodeName());

                        if (autosTipusNode.getNodeType() == Node.ELEMENT_NODE) {
                            Element autosTipusElement = (Element) autosTipusNode;

                            System.out.println(" Típus kód: " + autosTipusElement.getAttribute("típus_kód"));
                            System.out.println(" Forgalmi kód: " + autosTipusElement.getAttribute("forgalmi_kód"));
                            System.out.println(" Gyártási év: "
                                    + autosTipusElement.getElementsByTagName("Gy_év").item(0).getTextContent());
                            System.out.println(" Márka: "
                                    + autosTipusElement.getElementsByTagName("márka").item(0).getTextContent());
                            System.out.println(
                                    " Név: " + autosTipusElement.getElementsByTagName("név").item(0).getTextContent());
                        }
                    }

                    // Extract information from Vásárlás elements
                    NodeList vasarlasList = doc.getElementsByTagName("Vásárlás");
                    for (int i = 0; i < vasarlasList.getLength(); i++) {
                        Node vasarlasNode = vasarlasList.item(i);

                        System.out.println("\nVásárlás elem: " + vasarlasNode.getNodeName());

                        if (vasarlasNode.getNodeType() == Node.ELEMENT_NODE) {
                            Element vasarlasElement = (Element) vasarlasNode;

                            System.out.println(" Dátumkód: " + vasarlasElement.getAttribute("dátumkód"));
                            System.out.println(" Cég kód: " + vasarlasElement.getAttribute("cég_kód"));
                            System.out.println(" Vásárló kód: " + vasarlasElement.getAttribute("vásárló_kód"));
                            System.out.println(" Kezdő dátum: "
                                    + vasarlasElement.getElementsByTagName("kezd_dátum").item(0).getTextContent());
                        }
                    }

                    // Extract information from Számlázás elements
                    NodeList szamlazasList = doc.getElementsByTagName("Számlázás");
                    for (int i = 0; i < szamlazasList.getLength(); i++) {
                        Node szamlazasNode = szamlazasList.item(i);

                        System.out.println("\nSzámlázás elem: " + szamlazasNode.getNodeName());

                        if (szamlazasNode.getNodeType() == Node.ELEMENT_NODE) {
                            Element szamlazasElement = (Element) szamlazasNode;

                            System.out.println(" Számla kód: " + szamlazasElement.getAttribute("számlakód"));
                            System.out.println(" Vásárló kód: " + szamlazasElement.getAttribute("vásárló_kód"));
                            System.out.println(" Számla dátum: "
                                    + szamlazasElement.getElementsByTagName("számla_dátum").item(0).getTextContent());
                            System.out.println(" Végösszeg: "
                                    + szamlazasElement.getElementsByTagName("végösszeg").item(0).getTextContent());
                            System.out.println(" Jogsiszám: "
                                    + szamlazasElement.getElementsByTagName("jogsiszám").item(0).getTextContent());
                        }
                    }
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
