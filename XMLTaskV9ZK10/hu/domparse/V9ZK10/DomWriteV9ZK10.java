package hu.domparse.V9ZK10;

import org.w3c.dom.*;
import javax.xml.parsers.*;
import javax.xml.transform.*;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;
import java.io.*;

public class DomWriteV9ZK10 {

    public static void main(String[] args) {
        try {
            // Bemeneti XML fájl elérési útvonala
            File inputFile = new File("XMLTaskV9ZK10\\ERV9ZK10.xml");

            // DOM létrehozása és inicializálása
            DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
            DocumentBuilder dBuilder = dbFactory.newDocumentBuilder();
            Document doc = dBuilder.parse(inputFile);
            doc.getDocumentElement().normalize();

            // Gyökér elem kiírása és alakítása
            printNode(doc.getDocumentElement(), 0);
            // Átalakított dokumentum kiírása egy új fájlba
            writeDocumentToFile(doc, "XMLTaskV9ZK10\\ERV9ZK10_1.xml");

            System.out.print("A kimenet sikeresen kiírva a fájlba.");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    // Rekurzív metódus az XML elemek kiírására
    private static void printNode(Node node, int depth) {
        if (node.getNodeType() == Node.ELEMENT_NODE) {
            String tagName = node.getNodeName();
            NamedNodeMap attributes = node.getAttributes();

            // Behúzás mértékének kinyomtatása
            printIndentation(depth);
            System.out.print("<" + tagName);

            // Attribútumok kinyomtatása, ha vannak
            if (attributes.getLength() > 0) {
                for (int i = 0; i < attributes.getLength(); i++) {
                    Node attribute = attributes.item(i);
                    System.out.print(" " + attribute.getNodeName() + "=\"" + attribute.getTextContent() + "\"");
                }
            }

            NodeList children = node.getChildNodes();
            // Ha vannak gyerek elemek, azok kiírása
            if (children.getLength() > 1) {
                System.out.println(">");
                for (int i = 0; i < children.getLength(); i++) {
                    Node child = children.item(i);
                    if (child.getNodeType() == Node.ELEMENT_NODE) {
                        printNode(child, depth + 1);
                    }
                }
                printIndentation(depth);
                System.out.println("</" + tagName + ">");
                System.out.println("");
            } else {
                // Ha nincsenek gyerek elemek, a tartalom kiírása
                String textContent = node.getTextContent().trim();
                if (!textContent.isEmpty()) {
                    System.out.println(">" + textContent + "</" + tagName + ">");
                } else {
                    System.out.println("/>");
                }
            }
        }
    }

    // Behúzások kinyomtatása
    private static void printIndentation(int depth) {
        for (int i = 0; i < depth; i++) {
            System.out.print("  ");
        }
    }

    // Átalakított dokumentum kiírása fájlba
    private static void writeDocumentToFile(Document doc, String filename) throws TransformerException {
        TransformerFactory transformerFactory = TransformerFactory.newInstance();
        Transformer transformer = transformerFactory.newTransformer();
        transformer.setOutputProperty(OutputKeys.INDENT, "yes");
        transformer.setOutputProperty("{http://xml.apache.org/xslt}indent-amount", "2"); // Behúzás szabályozása

        DOMSource source = new DOMSource(doc);
        StreamResult result = new StreamResult(new File(filename));
        transformer.transform(source, result);
    }
}
