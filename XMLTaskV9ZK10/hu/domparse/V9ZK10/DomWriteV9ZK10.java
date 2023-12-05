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
            File inputFile = new File("XMLTaskV9ZK10\\ERV9ZK10.xml");
            DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
            DocumentBuilder dBuilder = dbFactory.newDocumentBuilder();
            Document doc = dBuilder.parse(inputFile);
            doc.getDocumentElement().normalize();

            printNode(doc.getDocumentElement(), 0);
            writeDocumentToFile(doc, "XMLTaskV9ZK10\\ERV9ZK10_1.xml");

            System.out.print("Output written to the file successfully.");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private static void printNode(Node node, int depth) {
        if (node.getNodeType() == Node.ELEMENT_NODE) {
            String tagName = node.getNodeName();
            NamedNodeMap attributes = node.getAttributes();

            printIndentation(depth);
            System.out.print("<" + tagName);

            // Kiirja az attribútomot ha létezik
            if (attributes.getLength() > 0) {
                for (int i = 0; i < attributes.getLength(); i++) {
                    Node attribute = attributes.item(i);
                    System.out.print(" " + attribute.getNodeName() + "=\"" + attribute.getTextContent() + "\"");
                }
            }

            NodeList children = node.getChildNodes();
            // If there are child elements, print them
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
                // Ha nincs gyerek elem kiirja a tartalmat
                String textContent = node.getTextContent().trim();
                if (!textContent.isEmpty()) {
                    System.out.println(">" + textContent + "</" + tagName + ">");
                } else {
                    System.out.println("/>");
                }
            }
        }
    }

    private static void printIndentation(int depth) {
        for (int i = 0; i < depth; i++) {
            System.out.print("  ");
        }
    }

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
