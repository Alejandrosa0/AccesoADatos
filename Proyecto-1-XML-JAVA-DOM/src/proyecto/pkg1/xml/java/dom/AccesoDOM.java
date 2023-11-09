package proyecto.pkg1.xml.java.dom;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import org.w3c.dom.*;
import org.w3c.dom.Document;
import java.util.*;
import java.io.*;
import static javax.management.Query.lt;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.OutputKeys;
import javax.xml.transform.Source;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;
import org.xml.sax.SAXException;

public class AccesoDOM {

    Document doc;

    public int abrirXMLaDOM(File f) throws ParserConfigurationException, SAXException, IOException {
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
            return 0; //Si el método funciona
        } catch (Exception e) {
            System.out.println(e);
            return -1; //Si el método aborta en algún punto
        }
    }

    public void recorrerDOMyMuestra() {
        String[] datos = new String[7]; //Lo usamos para la información de cada libro
        Node nodo = null;
        Node root = doc.getFirstChild();
        NodeList nodelist = root.getChildNodes(); //(1)Ver dibujo del árbol
        //Recorrer el árbol DOM. El leer nivel de nodos hijos del raíz
        for (int i = 0; i < nodelist.getLength(); i++) {
            nodo = nodelist.item(i); //Node toma el valor de los hijos de raíz
            if (nodo.getNodeType() == Node.ELEMENT_NODE) { //Miramos nodos de tipo Element
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
                System.out.println("\t<book id=\"" + datos[0] + "\">\n" + "\t\t<author>" + datos[1] + "</author>\n" + "\t\t<title>" + datos[2] + "</title>\n" + "\t\t<genre>" + datos[3] + "</genre>\n" + "\t\t<price>" + datos[4] + "</price>\n" + "\t\t<publish_date>" + datos[5] + "</publish_date>\n" + "\t\t<description>" + datos[6] + "</description>\n" + "\t</book>");
            }
        }
    }

    public int insertarLibroEnDOM() {
        String id, author, title, genre, price, publish_date, description;
        try {
            Scanner scanner = new Scanner(System.in);
            
            System.out.print("Introduce el ID del nuevo libro: ");
            id = scanner.nextLine();           
            
            System.out.print("Introduce el Autor del nuevo libro: ");
            author = scanner.nextLine();         
            while (author.matches(".*\\d.*")) {
            System.out.println("Autor solo puede contener texto. Por favor, introducelo de nuevo.");
            System.out.print("Introduce el Autor del nuevo libro: ");
            author = scanner.nextLine();
            }
            
            System.out.print("Introduce el Titulo del nuevo libro: ");
            title = scanner.nextLine();
            while (title.matches(".*\\d.*")) {
            System.out.println("Título solo puede contener texto. Por favor, introducelo de nuevo.");
            System.out.print("Introduce el Titulo del nuevo libro: ");
            title = scanner.nextLine();
            }
            
            System.out.print("Introduce el Gérero del nuevo libro: ");
            genre = scanner.nextLine();
            while (genre.matches(".*\\d.*")) {
            System.out.println("Género solo puede contener texto. Por favor, introducelo de nuevo.");
            System.out.print("Introduce el Gérero del nuevo libro: ");
            genre = scanner.nextLine();
            }
            
            System.out.print("Introduce el Precio del nuevo libro: ");
            price = scanner.nextLine();
            
            System.out.print("Introduce la Fecha De Publicación del nuevo libro: ");
            publish_date = scanner.nextLine();
            
            System.out.print("Introduce la Descripción del nuevo libro: ");
            description = scanner.nextLine();      
                       
        } catch (Exception e) {
            System.out.println(e);
            return -1;       
        }
        
        try {
            System.out.println("Añadir libro al árbol DOM: " + author + " ; " + title + " ; " + genre + " ; " + price + " ; " + publish_date + " ; " + description);
            //Crea los nodos => los añade al padre desde las hojas a la raíz //id, author, title, genre, price, publish_date, description
            //CREA AUTOR
            Node nautor = doc.createElement("author");
            Node nautor_text = doc.createTextNode(author);
            nautor.appendChild(nautor_text);
            //CREATE TITULO con el texto en medio
            Node ntitulo = doc.createElement("title"); //Crea etiquetas <title>...</title>
            Node ntitulo_text = doc.createTextNode(title); //Crea el nodo texto para el titulo
            ntitulo.appendChild(ntitulo_text); //Añade el titulo a las etiquetas <title> titulo </title>       
            //CREA GENERO
            Node ngenero = doc.createElement("genre");
            Node ngenero_text = doc.createTextNode(genre);
            ngenero.appendChild(ngenero_text);
            //CREA PRECIO
            Node nprecio = doc.createElement("price");
            Node nprecio_text = doc.createTextNode(price);
            nprecio.appendChild(nprecio_text);
            //CREA FECHA_PUBLICACIÓN
            Node npublish_date = doc.createElement("publish_date");
            Node npublish_date_text = doc.createTextNode(publish_date);
            npublish_date.appendChild(npublish_date_text);
            //CREA DESCRIPCION
            Node ndescripcion = doc.createElement("description");
            Node ndescripcion_text = doc.createTextNode(description);
            ndescripcion.appendChild(ndescripcion_text);
            //CREA LIBRO, con atributo y nodos ID, Titulo, Autor, Genero, Precio, fecha de publicación y Descripcion
            Node nbook = doc.createElement("book");
            ((Element) nbook).setAttribute("id", id);
            nbook.appendChild(ntitulo);
            nbook.appendChild(nautor);
            nbook.appendChild(ngenero);
            nbook.appendChild(nprecio);
            nbook.appendChild(npublish_date);
            nbook.appendChild(ndescripcion);
            //APPEND LIBRO TO THE ROOT IN ORDER
            Node raiz = doc.getFirstChild();
            NodeList nodelist = raiz.getChildNodes();
            boolean inserted = false;
            for (int i = 0; i < nodelist.getLength(); i++) {
                Node nodo = nodelist.item(i);
                if (nodo.getNodeType() == Node.ELEMENT_NODE) {
                    String nodoId = nodo.getChildNodes().item(0).getTextContent();
                    if (nodoId.compareTo(id) > 0) {
                        raiz.insertBefore(nbook, nodo);
                        inserted = true;
                        break;
                    }
                }
            }
            if (!inserted) {
                raiz.appendChild(nbook);
            }
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
    
    void guardarDOMcomoArchivo(String nuevoArchivo) {
        try {
            Source src = new DOMSource(doc); //Definimos el origen
            StreamResult rst = new StreamResult(new File(nuevoArchivo)); //Definimos el resultado
            //Declaramos el Transformer que tiene el método .transform() que necesitamos.
            Transformer transformer = TransformerFactory.newInstance().newTransformer();
            //Opción para indentar el archivo
            transformer.setOutputProperty(OutputKeys.INDENT, "yes");
            transformer.transform(src, (javax.xml.transform.Result) rst);
            System.out.println("Archivo creado del DOM con éxito\n");
        } catch (Exception e) {
            e.printStackTrace();
        }       
    }
}
