package practica1_1_1_2;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import org.w3c.dom.*;//For Document
import org.w3c.dom.Document;
import java.util.*;
import java.io.*;//Clase File
import static javax.management.Query.lt;
import javax.xml.parsers.ParserConfigurationException;
import org.xml.sax.SAXException;

public class AccesoDOM {

    Document doc;

    public int abriXMLaDOM(File f) throws ParserConfigurationException, SAXException, IOException {
        try {
            System.out.println("Abriendo archivo XML file y generando DOM....");
            //Creamos nuevo objeto DocumentBuilder al que apunta la variable factory
            DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
            //Ignorar comentarios y espacios blancos
            factory.setIgnoringComments(true);
            factory.setIgnoringElementContentWhitespace(true);
            //DocumentBuilder tiene el método parse que es el que genera DOM en memoria
            DocumentBuilder builder = factory.newDocumentBuilder();
            doc = builder.parse(f);
            System.out.println("DOM creado con éxito.");
            return 0;//si el método funciona
        } catch (Exception e) {
            System.out.println(e);
            return -1;//If the method aborta en algún punto
        }
    }//Fin clase

    public void recorreDOMyMuestra() {
        String[] datos = new String[3];// Lo usamos para la información de cada libro
        Node nodo = null;
        Node root = doc.getFirstChild();
        NodeList nodelist = root.getChildNodes();//(1)Ver dibujo del árbol
        //Recorrer el árbol DOM. El 1er nivel de nodos hijos del raíz
        for (int i = 0; i < nodelist.getLength(); i++) {
            nodo = nodelist.item(i);//Node toma el valor de los hijos de raíz
            if (nodo.getNodeType() == Node.ELEMENT_NODE) {//miramos nodos de tipo Element
                Node ntemp = null;
                int contador = 1;
                //Sacamos el valor del atributo publicado
                datos[0] = nodo.getAttributes().item(0).getNodeValue();
                //Sacamos los valores de los hijos de nodo, Titulo y Autor
                NodeList nl2 = nodo.getChildNodes();//Obtenemos lista de hijos (2)
                for (int j = 0; j < nl2.getLength(); j++) {//Iteramos en esa lista ntemp=nl2.item(j);
                    ntemp = nl2.item(j);
                    if (ntemp.getNodeType() == Node.ELEMENT_NODE) {
                        //Para conseguir el texto de titulo y autor, se puedo hacer con getNodeValue(), también con getTextContent() si es ELEMENT
                        datos[contador] = ntemp.getTextContent();
                        //También datos[contador]=ntemp.getChildNodes().item(0).getNodeValue();
                        contador++;
                    }
                }
                System.out.println(datos[0] + " -- " + datos[2] + " -- " + datos[1]);
            }
        }
    }
}
