package domparse;

import java.io.File;
import java.io.IOException;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

public class DomQueryV9ZK10 {
    public static void main(String argv[]) throws SAXException, IOException, ParserConfigurationException {
        File xmlFile = new File("XMLTaskV9ZK10\\ERV9ZK10.xml");

        DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
        DocumentBuilder dBuilder = factory.newDocumentBuilder();

        org.w3c.dom.Document doc = dBuilder.parse(xmlFile);
        doc.getDocumentElement().normalize();

        // Querying Vasarlo elements
        NodeList vasarloList = doc.getElementsByTagName("Vasarlo");
        System.out.println("\nVásárlók:");
        for (int i = 0; i < vasarloList.getLength(); i++) {
            Node node = vasarloList.item(i);
            if (node.getNodeType() == Node.ELEMENT_NODE) {
                Element element = (Element) node;
                String email = element.getElementsByTagName("email_cim").item(0).getTextContent();
                String name = element.getElementsByTagName("nev").item(0).getTextContent();
                String phoneNumber = element.getElementsByTagName("Telefonszam").item(0).getTextContent();
                System.out.println("Email: " + email + ", Name: " + name + ", Phone: " + phoneNumber);
            }
        }

        // Querying Autos_ceg elements
        NodeList autosCegList = doc.getElementsByTagName("Autos_ceg");
        System.out.println("\nAutós cégek:");
        for (int i = 0; i < autosCegList.getLength(); i++) {
            Node node = autosCegList.item(i);
            if (node.getNodeType() == Node.ELEMENT_NODE) {
                Element element = (Element) node;
                String companyCode = element.getAttribute("ceg_kod");
                String companyName = element.getElementsByTagName("cegnev").item(0).getTextContent();
                String employeeCount = element.getElementsByTagName("Dolgozok_szama").item(0).getTextContent();
                System.out.println(
                        "Cég kód: " + companyCode + ", Name: " + companyName + ", Employees: " + employeeCount);
            }
        }

        // Querying Autos_adatok elements
        NodeList autosAdatokList = doc.getElementsByTagName("Autos_adatok");
        System.out.println("\nAutós adatok:");
        for (int i = 0; i < autosAdatokList.getLength(); i++) {
            Node node = autosAdatokList.item(i);
            if (node.getNodeType() == Node.ELEMENT_NODE) {
                Element element = (Element) node;
                String forgalmiKod = element.getAttribute("forgalmi_kod");
                String adatokCompanyCode = element.getAttribute("ceg_kod");
                String price = element.getElementsByTagName("ar").item(0).getTextContent();
                String km = element.getElementsByTagName("km_ora").item(0).getTextContent();
                String status = element.getElementsByTagName("statusz").item(0).getTextContent();
                System.out
                        .println("Forgalmi Kód: " + forgalmiKod + ", Cég kód: " + adatokCompanyCode + ", Price: "
                                + price
                                + ", KM: " + km + ", Status: " + status);
            }
        }

        // Querying Autos_tipus elements
        NodeList autosTipusList = doc.getElementsByTagName("Autos_tipus");
        System.out.println("\nAutós típusok:");
        for (int i = 0; i < autosTipusList.getLength(); i++) {
            Node node = autosTipusList.item(i);
            if (node.getNodeType() == Node.ELEMENT_NODE) {
                Element element = (Element) node;
                String tipusKod = element.getAttribute("tipus_kod");
                String tipusForgalmiKod = element.getAttribute("forgalmi_kod");
                String gyev = element.getElementsByTagName("Gy_ev").item(0).getTextContent();
                String marka = element.getElementsByTagName("marka").item(0).getTextContent();
                String nev = element.getElementsByTagName("nev").item(0).getTextContent();
                System.out
                        .println("Típus Kód: " + tipusKod + ", Forgalmi Kód: " + tipusForgalmiKod + ", Gyártási Év: "
                                + gyev
                                + ", Márka: " + marka + ", Név: " + nev);
            }
        }

        // Querying Vasarlas elements
        NodeList vasarlasList = doc.getElementsByTagName("Vasarlas");
        System.out.println("\nVásárlások:");
        for (int i = 0; i < vasarlasList.getLength(); i++) {
            Node node = vasarlasList.item(i);
            if (node.getNodeType() == Node.ELEMENT_NODE) {
                Element element = (Element) node;
                String vasarlasDatumkod = element.getAttribute("datumkod");
                String vasarlasCompanyCode = element.getAttribute("ceg_kod");
                String vasarlasVasarloKod = element.getAttribute("vasarlo_kod");
                String kezdDatum = element.getElementsByTagName("kezd_datum").item(0).getTextContent();
                System.out.println(
                        "Dátumkód: " + vasarlasDatumkod + ", Cég kód: " + vasarlasCompanyCode + ", Vásárló Kód: "
                                + vasarlasVasarloKod
                                + ", Kezdő Dátum: " + kezdDatum);
            }
        }

        // Querying Szamlazas elements
        NodeList szamlazasList = doc.getElementsByTagName("Szamlazas");
        System.out.println("\nSzámlázások:");
        for (int i = 0; i < szamlazasList.getLength(); i++) {
            Node node = szamlazasList.item(i);
            if (node.getNodeType() == Node.ELEMENT_NODE) {
                Element element = (Element) node;
                String szamlaKod = element.getAttribute("szamlakod");
                String szamlazasVasarloCode = element.getAttribute("vasarlo_kod");
                String szamlaDatum = element.getElementsByTagName("szamla_datum").item(0).getTextContent();
                String vegOsszeg = element.getElementsByTagName("vegosszeg").item(0).getTextContent();
                String jogsiszam = element.getElementsByTagName("jogsiszam").item(0).getTextContent();
                System.out.println(
                        "Számlakód: " + szamlaKod + ", Vásárló Kód: " + szamlazasVasarloCode + ", Számla Dátum: "
                                + szamlaDatum
                                + ", Végösszeg: " + vegOsszeg + ", Jogsiszám: " + jogsiszam);
            }
        }

        // Querying Autós_adatok and Autós_tipus for 1:1 relationship
        NodeList autosAdatokList2 = doc.getElementsByTagName("Autos_adatok");
        System.out.println("\nAutós adatok és az azokhoz tartozó típusok:");
        for (int i = 0; i < autosAdatokList2.getLength(); i++) {
            Node node = autosAdatokList2.item(i);
            if (node.getNodeType() == Node.ELEMENT_NODE) {
                Element autosAdatokElement = (Element) node;
                String forgalmiKod = autosAdatokElement.getAttribute("forgalmi_kod");
                String adatokCompanyCode = autosAdatokElement.getAttribute("ceg_kod");
                String ar = autosAdatokElement.getElementsByTagName("ar").item(0).getTextContent();
                String kmOra = autosAdatokElement.getElementsByTagName("km_ora").item(0).getTextContent();
                String status = autosAdatokElement.getElementsByTagName("statusz").item(0).getTextContent();

                // Querying Autos_tipus for the same forgalmi_kod
                NodeList autosTipusList2 = doc.getElementsByTagName("Autos_tipus");
                for (int j = 0; j < autosTipusList2.getLength(); j++) {
                    Node autosTipusNode = autosTipusList2.item(j);
                    if (autosTipusNode.getNodeType() == Node.ELEMENT_NODE) {
                        Element autosTipusElement = (Element) autosTipusNode;
                        String tipusForgalmiKod = autosTipusElement.getAttribute("forgalmi_kod");
                        if (forgalmiKod.equals(tipusForgalmiKod)) {
                            String gyev = autosTipusElement.getElementsByTagName("Gy_ev").item(0).getTextContent();
                            String marka = autosTipusElement.getElementsByTagName("marka").item(0).getTextContent();
                            String nev = autosTipusElement.getElementsByTagName("nev").item(0).getTextContent();

                            System.out.println("Forgalmi Kód: " + forgalmiKod + ", Cég kód: " + adatokCompanyCode +
                                    ", Ár: " + ar + ", KM óra: " + kmOra + ", Státusz: " + status +
                                    ", Gyártási Év: " + gyev + ", Márka: " + marka + ", Név: " + nev);
                        }
                    }
                }
            }
        }

        // Querying Autos_ceg and Autos_adatok for 1:n relationship
        NodeList autosCegList1 = doc.getElementsByTagName("Autos_ceg");
        System.out.println("\nAutós cégek és az azokhoz tartozó adatok:");
        for (int i = 0; i < autosCegList1.getLength(); i++) {
            Node node = autosCegList1.item(i);
            if (node.getNodeType() == Node.ELEMENT_NODE) {
                Element autosCegElement = (Element) node;
                String companyCode = autosCegElement.getAttribute("ceg_kod");
                String companyName = autosCegElement.getElementsByTagName("cegnev").item(0).getTextContent();

                // Querying Autos_adatok for the same ceg_kod
                NodeList autosAdatokList1 = doc.getElementsByTagName("Autos_adatok");
                for (int j = 0; j < autosAdatokList1.getLength(); j++) {
                    Node autosAdatokNode = autosAdatokList1.item(j);
                    if (autosAdatokNode.getNodeType() == Node.ELEMENT_NODE) {
                        Element autosAdatokElement = (Element) autosAdatokNode;
                        String adatokCompanyCode = autosAdatokElement.getAttribute("ceg_kod");
                        if (companyCode.equals(adatokCompanyCode)) {
                            String ar = autosAdatokElement.getElementsByTagName("ar").item(0).getTextContent();
                            String kmOra = autosAdatokElement.getElementsByTagName("km_ora").item(0).getTextContent();
                            String status = autosAdatokElement.getElementsByTagName("statusz").item(0).getTextContent();

                            System.out.println("Cég kód: " + companyCode + ", Cég név: " + companyName +
                                    ", Ár: " + ar + ", KM óra: " + kmOra + ", Státusz: " + status);
                        }
                    }
                }
            }
        }
        // Querying Vasarlo and Vasarlas for n:m relationship
        NodeList vasarloList1 = doc.getElementsByTagName("Vasarlo");
        System.out.println("\nVásárlók és az azokhoz tartozó vásárlások:");
        for (int i = 0; i < vasarloList1.getLength(); i++) {
            Node node = vasarloList1.item(i);
            if (node.getNodeType() == Node.ELEMENT_NODE) {
                Element vasarloElement = (Element) node;
                String vasarloKod = vasarloElement.getAttribute("jogkod");
                String vasarloEmail = vasarloElement.getElementsByTagName("email_cim").item(0).getTextContent();
                String vasarloName = vasarloElement.getElementsByTagName("nev").item(0).getTextContent();

                // Querying Vasarlas for the same vasarlo_kod
                NodeList vasarlasList1 = doc.getElementsByTagName("Vasarlas");
                for (int j = 0; j < vasarlasList1.getLength(); j++) {
                    Node vasarlasNode = vasarlasList1.item(j);
                    if (vasarlasNode.getNodeType() == Node.ELEMENT_NODE) {
                        Element vasarlasElement = (Element) vasarlasNode;
                        String vasarlasVasarloKod = vasarlasElement.getAttribute("vasarlo_kod");
                        if (vasarloKod.equals(vasarlasVasarloKod)) {
                            String vasarlasDatumkod = vasarlasElement.getAttribute("datumkod");
                            String vasarlasCompanyCode = vasarlasElement.getAttribute("ceg_kod");
                            String kezdDatum = vasarlasElement.getElementsByTagName("kezd_datum").item(0)
                                    .getTextContent();

                            System.out.println("Vásárló Kód: " + vasarloKod + ", Név: " + vasarloName +
                                    ", Email: " + vasarloEmail +
                                    ", Vásárlás Dátumkód: " + vasarlasDatumkod + ", Cég kód: "
                                    + vasarlasCompanyCode +
                                    ", Első vásárlás: " + kezdDatum);
                        }
                    }
                }
            }
        }

    }
}
