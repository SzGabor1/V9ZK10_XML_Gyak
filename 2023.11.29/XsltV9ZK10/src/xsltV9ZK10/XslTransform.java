package xsltV9ZK10;

import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.stream.StreamResult;
import javax.xml.transform.stream.StreamSource;

public class XslTransform {

	public static void main(String[] args) {
		try {
			// 1. feladat
			String xmlInput = "2023.11.29\\XsltV9ZK10\\hallgatoV9ZK10.xml";
			String xsltInputHTML = "2023.11.29\\XsltV9ZK10\\hallgatoV9ZK10.xsl";
			String xsltInputXML = "2023.11.29\\XsltV9ZK10\\hallgatoV9ZK10xml.xsl";
			String outputResult = "2023.11.29\\XsltV9ZK10\\hallgatoV9ZK10.html";
			String outputResultXML = "2023.11.29\\XsltV9ZK10\\hallgatoV9ZK10.out.xml";

			TransformerFactory transformerFactory = TransformerFactory.newInstance();

			Transformer transformer = transformerFactory.newTransformer(new StreamSource(xsltInputHTML));
			transformer.transform(new StreamSource(xmlInput), new StreamResult(outputResult));

			transformer = transformerFactory.newTransformer(new StreamSource(xsltInputXML));
			transformer.transform(new StreamSource(xmlInput), new StreamResult(outputResultXML));

			// 2. feladat
			xmlInput = "orarendV9ZK10.xml";
			xsltInputHTML = "orarendV9ZK10.xsl";
			xsltInputXML = "orarendV9ZK10Lxml.xsl";
			outputResult = "orarendV9ZK10.html";
			outputResultXML = "orarendV9ZK10.out.xml";

			transformer = transformerFactory.newTransformer(new StreamSource(xsltInputHTML));
			transformer.transform(new StreamSource(xmlInput), new StreamResult(outputResult));

			transformer = transformerFactory.newTransformer(new StreamSource(xsltInputXML));
			transformer.transform(new StreamSource(xmlInput), new StreamResult(outputResultXML));

			System.out.println("Sikeres XSLT transzformáció, eredmény mentve.");
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
