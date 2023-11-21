package domparse;

import org.w3c.dom.*;
import javax.xml.parsers.*;
import java.io.*;
import java.util.StringJoiner;

public class DomReadV9ZK10 {
        public static void main(String[] args) {
                try {
                        File inputFile = new File("XMLTaskV9ZK10\\ERV9ZK10.xml");

                        DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
                        DocumentBuilder dBuilder = dbFactory.newDocumentBuilder();
                        Document doc = dBuilder.parse(inputFile);
                        doc.getDocumentElement().normalize();

                        File outputFile = new File("XMLTaskV9ZK10\\ERV9ZK10_1.xml");
                        PrintWriter writer = new PrintWriter(new FileWriter(outputFile, true));

                        // kiirja az XML prológust
                        System.out.print("<?xml version=\"1.0\" encoding=\"UTF-8\"?>\n");
                        writer.print("<?xml version=\"1.0\" encoding=\"UTF-8\"?>\n");

                        // Gyökér elem kiiratása
                        Element rootElement = doc.getDocumentElement();
                        String rootName = rootElement.getTagName();
                        StringJoiner rootAttributes = new StringJoiner(" ");
                        NamedNodeMap rootAttributeMap = rootElement.getAttributes();

                        for (int i = 0; i < rootAttributeMap.getLength(); i++) {
                                Node attribute = rootAttributeMap.item(i);
                                rootAttributes.add(attribute.getNodeName() + "=\"" + attribute.getNodeValue() + "\"");
                        }

                        System.out.print("<" + rootName + " " + rootAttributes.toString() + ">\n");
                        writer.print("<" + rootName + " " + rootAttributes.toString() + ">\n");

                        // kiirja az az elemek nevét
                        printNodeList(doc.getElementsByTagName("Vasarlo"), writer);
                        printNodeList(doc.getElementsByTagName("AutosCeg"), writer);
                        printNodeList(doc.getElementsByTagName("AutosAdatok"), writer);
                        printNodeList(doc.getElementsByTagName("AutosTipus"), writer);
                        printNodeList(doc.getElementsByTagName("Vasarlas"), writer);
                        printNodeList(doc.getElementsByTagName("Szamlazas"), writer);

                        // Lezárja az elemet
                        System.out.println("</" + rootName + ">");
                        writer.append("</" + rootName + ">");

                        writer.close();
                } catch (Exception e) {
                        e.printStackTrace();
                }
        }

        private static void printNodeList(NodeList nodeList, PrintWriter writer) {
                for (int i = 0; i < nodeList.getLength(); i++) {
                        Node node = nodeList.item(i);
                        printNode(node, 1, writer);
                        System.out.println("");
                        writer.println("");
                }
        }

        private static void printNode(Node node, int indent, PrintWriter writer) {
                if (node.getNodeType() == Node.ELEMENT_NODE) {
                        Element element = (Element) node;
                        String nodeName = element.getTagName();
                        StringJoiner attributes = new StringJoiner(" ");
                        NamedNodeMap attributeMap = element.getAttributes();

                        for (int i = 0; i < attributeMap.getLength(); i++) {
                                Node attribute = attributeMap.item(i);
                                attributes.add(attribute.getNodeName() + "=\"" + attribute.getNodeValue() + "\"");
                        }

                        System.out.print(getIndentString(indent));
                        System.out.print("<" + nodeName + " " + attributes.toString() + ">");
                        writer.print(getIndentString(indent));
                        writer.print("<" + nodeName + " " + attributes.toString() + ">");

                        NodeList children = element.getChildNodes();

                        if (children.getLength() == 1 && children.item(0).getNodeType() == Node.TEXT_NODE) {
                                System.out.print(children.item(0).getNodeValue());
                                writer.print(children.item(0).getNodeValue());
                        } else {
                                System.out.println();
                                writer.println();
                                for (int i = 0; i < children.getLength(); i++) {
                                        printNode(children.item(i), indent + 1, writer);
                                }
                                System.out.print(getIndentString(indent));
                                writer.print(getIndentString(indent));
                        }

                        System.out.println("</" + nodeName + ">");
                        writer.println("</" + nodeName + ">");
                }
        }

        private static String getIndentString(int indent) {
                StringBuilder sb = new StringBuilder();
                for (int i = 0; i < indent; i++) {
                        sb.append("  ");
                }
                return sb.toString();
        }
}
