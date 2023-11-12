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

                        NodeList vasarlokList = doc.getElementsByTagName("Vasarlo");

                        for (int temp = 0; temp < vasarlokList.getLength(); temp++) {
                                Node vasarloNode = vasarlokList.item(temp);

                                System.out.println("\n" + vasarloNode.getNodeName());

                                if (vasarloNode.getNodeType() == Node.ELEMENT_NODE) {
                                        Element vasarloElement = (Element) vasarloNode;

                                        System.out.println(" Jogkód: " + vasarloElement.getAttribute("jogkod"));
                                        System.out.println(
                                                        " Email cím: " + vasarloElement
                                                                        .getElementsByTagName("email_cim").item(0)
                                                                        .getTextContent());

                                        Element cimElement = (Element) vasarloElement.getElementsByTagName("cim")
                                                        .item(0);
                                        System.out
                                                        .println(" Ország: " + cimElement.getElementsByTagName("orszag")
                                                                        .item(0).getTextContent());
                                        System.out.println(" Isz: " + cimElement.getElementsByTagName("isz").item(0)
                                                        .getTextContent());
                                        System.out.println(" Város: " + cimElement.getElementsByTagName("varos").item(0)
                                                        .getTextContent());
                                        System.out.println(
                                                        " Utca és házszám: " + cimElement.getElementsByTagName("u_hsz")
                                                                        .item(0).getTextContent());

                                        System.out.println(" Név: " + vasarloElement.getElementsByTagName("nev").item(0)
                                                        .getTextContent());
                                        System.out.println(" Telefonszám: "
                                                        + vasarloElement.getElementsByTagName("Telefonszam").item(0)
                                                                        .getTextContent());

                                        // Extract information from Autos_ceg elements
                                        NodeList autosCegList = doc.getElementsByTagName("Autos_ceg");
                                        for (int i = 0; i < autosCegList.getLength(); i++) {
                                                Node autosCegNode = autosCegList.item(i);

                                                System.out.println("\nAutos_ceg elem: " + autosCegNode.getNodeName());

                                                if (autosCegNode.getNodeType() == Node.ELEMENT_NODE) {
                                                        Element autosCegElement = (Element) autosCegNode;

                                                        System.out.println(" Ceg kod: "
                                                                        + autosCegElement.getAttribute("ceg_kod"));
                                                        System.out.println(" Cegnev: "
                                                                        + autosCegElement.getElementsByTagName("cegnev")
                                                                                        .item(0).getTextContent());
                                                        System.out.println(" Helyrajzi szam: "
                                                                        + autosCegElement.getElementsByTagName(
                                                                                        "Helyrajzi_szam").item(0)
                                                                                        .getTextContent());
                                                        System.out.println(" Dolgozok szama: "
                                                                        + autosCegElement.getElementsByTagName(
                                                                                        "Dolgozok_szama").item(0)
                                                                                        .getTextContent());
                                                }
                                        }

                                        NodeList autosAdatokList = doc.getElementsByTagName("Autos_adatok");
                                        for (int i = 0; i < autosAdatokList.getLength(); i++) {
                                                Node autosAdatokNode = autosAdatokList.item(i);

                                                System.out.println("\nAutos_adatok elem: "
                                                                + autosAdatokNode.getNodeName());

                                                if (autosAdatokNode.getNodeType() == Node.ELEMENT_NODE) {
                                                        Element autosAdatokElement = (Element) autosAdatokNode;

                                                        System.out.println(" Forgalmi kod: " + autosAdatokElement
                                                                        .getAttribute("forgalmi_kod"));
                                                        System.out.println(" Ceg kod: "
                                                                        + autosAdatokElement.getAttribute("ceg_kod"));
                                                        System.out.println(
                                                                        " Ar: " + autosAdatokElement
                                                                                        .getElementsByTagName("ar")
                                                                                        .item(0).getTextContent());
                                                        System.out.println(" Km ora: "
                                                                        + autosAdatokElement
                                                                                        .getElementsByTagName("km_ora")
                                                                                        .item(0).getTextContent());
                                                        System.out.println(" Statusz: "
                                                                        + autosAdatokElement
                                                                                        .getElementsByTagName("statusz")
                                                                                        .item(0).getTextContent());
                                                }
                                        }

                                        // Extract information from Autos_tipus elements
                                        NodeList autosTipusList = doc.getElementsByTagName("Autos_tipus");
                                        for (int i = 0; i < autosTipusList.getLength(); i++) {
                                                Node autosTipusNode = autosTipusList.item(i);

                                                System.out.println(
                                                                "\nAutos_tipus elem: " + autosTipusNode.getNodeName());

                                                if (autosTipusNode.getNodeType() == Node.ELEMENT_NODE) {
                                                        Element autosTipusElement = (Element) autosTipusNode;

                                                        System.out.println(" Tipus kod: "
                                                                        + autosTipusElement.getAttribute("tipus_kod"));
                                                        System.out.println(" Forgalmi kod: " + autosTipusElement
                                                                        .getAttribute("forgalmi_kod"));
                                                        System.out.println(" Gyartasi ev: "
                                                                        + autosTipusElement
                                                                                        .getElementsByTagName("Gy_ev")
                                                                                        .item(0).getTextContent());
                                                        System.out.println(" Marka: "
                                                                        + autosTipusElement
                                                                                        .getElementsByTagName("marka")
                                                                                        .item(0).getTextContent());
                                                        System.out.println(
                                                                        " Nev: " + autosTipusElement
                                                                                        .getElementsByTagName("nev")
                                                                                        .item(0).getTextContent());
                                                }
                                        }

                                        // Extract information from Vasarlas elements
                                        NodeList vasarlasList = doc.getElementsByTagName("Vasarlas");
                                        for (int i = 0; i < vasarlasList.getLength(); i++) {
                                                Node vasarlasNode = vasarlasList.item(i);

                                                System.out.println("\nVasarlas elem: " + vasarlasNode.getNodeName());

                                                if (vasarlasNode.getNodeType() == Node.ELEMENT_NODE) {
                                                        Element vasarlasElement = (Element) vasarlasNode;

                                                        System.out.println(" Datumkod: "
                                                                        + vasarlasElement.getAttribute("datumkod"));
                                                        System.out.println(" Ceg kod: "
                                                                        + vasarlasElement.getAttribute("ceg_kod"));
                                                        System.out.println(" Vasarlo kod: "
                                                                        + vasarlasElement.getAttribute("vasarlo_kod"));
                                                        System.out.println(" Kezdo datum: "
                                                                        + vasarlasElement
                                                                                        .getElementsByTagName(
                                                                                                        "kezd_datum")
                                                                                        .item(0).getTextContent());
                                                }
                                        }

                                        // Extract information from Szamlazas elements
                                        NodeList szamlazasList = doc.getElementsByTagName("Szamlazas");
                                        for (int i = 0; i < szamlazasList.getLength(); i++) {
                                                Node szamlazasNode = szamlazasList.item(i);

                                                System.out.println("\nSzamlazas elem: " + szamlazasNode.getNodeName());

                                                if (szamlazasNode.getNodeType() == Node.ELEMENT_NODE) {
                                                        Element szamlazasElement = (Element) szamlazasNode;

                                                        System.out.println(" Szamla kod: "
                                                                        + szamlazasElement.getAttribute("szamlakod"));
                                                        System.out.println(" Vasarlo kod: "
                                                                        + szamlazasElement.getAttribute("vasarlo_kod"));
                                                        System.out.println(" Szamla datum: "
                                                                        + szamlazasElement
                                                                                        .getElementsByTagName(
                                                                                                        "szamla_datum")
                                                                                        .item(0).getTextContent());
                                                        System.out.println(" Vegosszeg: "
                                                                        + szamlazasElement
                                                                                        .getElementsByTagName(
                                                                                                        "vegosszeg")
                                                                                        .item(0).getTextContent());
                                                        System.out.println(" Jogsiszam: "
                                                                        + szamlazasElement
                                                                                        .getElementsByTagName(
                                                                                                        "jogsiszam")
                                                                                        .item(0).getTextContent());
                                                }
                                        }
                                }
                        }
                } catch (Exception e) {
                        e.printStackTrace();
                }
        }
}
