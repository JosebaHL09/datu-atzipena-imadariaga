/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

import java.io.File;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.collections.ObservableList;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.OutputKeys;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

/**
 *
 * @author hernandez.joseba
 */
public class XMLFitxategia {

    public static ObservableList<Ikaslea> datuakMemorianKargatu(String f) {
        ObservableList<Ikaslea> ikasleak = null;
        String xmlFile = f;
        Ikaslea ik;
        try {
            DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
            DocumentBuilder builder = factory.newDocumentBuilder();
            Document document = builder.parse(new File(f));

            NodeList liburuNodoak = document.getElementsByTagName("liburu");
            for (int i = 0; i < liburuNodoak.getLength(); i++) {
                Node nodoa = liburuNodoak.item(i);
                Element elemLiburua = (Element) nodoa;

                int kodea = Integer.parseInt(elemLiburua.getAttribute("zenbakia"));
                String izena = elemLiburua.getElementsByTagName("izenburua").item(0).getTextContent();
                String egilea = elemLiburua.getElementsByTagName("egilea").item(0).getTextContent();
                ik = new Ikaslea(kodea, izena, egilea);
            }
        } catch (SAXException ex) {
            ex.getStackTrace();
        } catch (ParserConfigurationException ex) {
            Logger.getLogger(XMLFitxategia.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException ex) {
            Logger.getLogger(XMLFitxategia.class.getName()).log(Level.SEVERE, null, ex);
        }

        return ikasleak;
    }

    public static boolean datuakFitxatganGorde(ObservableList<Ikaslea> l, String f) throws IOException {
        String izena,abizena,zenbakia;
        
        try {

            DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
            DocumentBuilder builder = factory.newDocumentBuilder();
            Document document = builder.parse(new File(f));
            NodeList ikasleNodoak = document.getElementsByTagName("ikaslea");

            Element liburua = document.createElement("liburu");
            liburua.setAttribute("zenbakia", zenbakia);
            document.getDocumentElement().appendChild(liburua);

            Element izenburu = document.createElement("izenburua");
            Element egile = document.createElement("egilea");

            izenburu.appendChild(document.createTextNode(izenburua));
            egile.appendChild(document.createTextNode(egilea));

            liburua.appendChild(izenburu);
            liburua.appendChild(egile);

            //no se como funcionan pero se que hace falta esto para crear.
            DOMSource source = new DOMSource(document);
            StreamResult result = new StreamResult(new java.io.File("Liburuak.xml"));
            Transformer transformer = TransformerFactory.newInstance().newTransformer();
            transformer.setOutputProperty(OutputKeys.INDENT, "yes");
            transformer.transform(source, result);
        } catch (SAXException ex) {
            Logger.getLogger(XMLFitxategia.class.getName()).log(Level.SEVERE, null, ex);
        } catch (ParserConfigurationException ex) {
            Logger.getLogger(XMLFitxategia.class.getName()).log(Level.SEVERE, null, ex);
        }

        return true;
    }
}
