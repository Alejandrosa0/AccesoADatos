package practica1_3;

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

    public int insertarLibroEnDOM(String titulo, String autor, String fecha) {
        try {
            System.out.println("Añadir libro al árbol DOM: " + titulo + " ; " + autor + " ; " + fecha);
            //Crea los nodos=&gt;los añade al padre desde las hojas a la raíz
            //CREATE TITULO con el texto en medio
            Node ntitulo = doc.createElement("Titulo"); //Crea etiquetas <Titulo>...</Titulo>
            Node ntitulo_text = doc.createTextNode(titulo); //Crea el nodo texto para el Titulo
            ntitulo.appendChild(ntitulo_text); //Añade el titulo a las etiquetas <Titulo> titulo </Titulo>
            //Node nautor=doc.createElement("Autor").appendChild(doc.createTextNode(autor)); //One line doesn't work
            //CREA AUTOR
            Node nautor = doc.createElement("Autor");
            Node nautor_text = doc.createTextNode(autor);
            nautor.appendChild(nautor_text);
            //CREA LIBRO, con atributo y nodos Título y Autor
            Node nLibro = doc.createElement("Libro");
            ((Element) nLibro).setAttribute("publicado", fecha);
            nLibro.appendChild(ntitulo);
            nLibro.appendChild(nautor);
            //APPEND LIBRO TO THE ROOT
            nLibro.appendChild(doc.createTextNode("\n")); //Para insertar saltos de línea
            Node raiz = doc.getFirstChild(); //Tb. doc.getChildNodes().item(0)
            raiz.appendChild(nLibro);
            System.out.println("Libro insertado en DOM.");
            return 0;
        } catch (Exception e) {
            System.out.println(e);
            return -1;
        }
    }

    public int deleteNode(String tit) {
        System.out.println("Buscando el Libro " + tit + " para borrarlo.");
        try {
            //Node root = doc.getFirstChild();
            Node raiz = doc.getDocumentElement();
            NodeList nl1 = doc.getElementsByTagName("Titulo");
            Node n1;
            for (int i = 0; i < nl1.getLength(); i++) {
                n1 = nl1.item(i);
                if (n1.getNodeType() == Node.ELEMENT_NODE) { //Redundante por getElementsByTagName, no lo es si buscamos getChildNodes()
                    if (n1.getChildNodes().item(0).getNodeValue().equals(tit)) {
                        System.out.println("Borrando el nodo <Libro> con título " + tit + ".");
                        //n1.getParentNode().removeChild(n1);
                        //Borra <Titulo> tit </Titulo>, pero deja Libro y Autor
                        n1.getParentNode().getParentNode().removeChild(n1.getParentNode());
                    }
                }
            }
            System.out.println("Nodo borrado.");
            //Guardar el arbol DOM en un nuevo archivo para mantener nuestro archivo original
            //GuardarDOMcomoArchivo("LibrosBorrados.xml");
            return 0;
        } catch (Exception e) {
            System.out.println(e);
            e.printStackTrace();
            return -1;
        }
    }
}
