package proyecto.pkg1.xml.java.dom;

import java.io.File;
import java.io.IOException;
import javax.xml.parsers.ParserConfigurationException;
import org.xml.sax.SAXException;

public class UsaAccesoDOM {

    public static void main(String[] args) throws ParserConfigurationException, SAXException, IOException {
        
        AccesoDOM a = new AccesoDOM();
        File f = new File("books.xml");

        a.abrirXMLaDOM(f);
        a.recorrerDOMyMuestra();
        a.insertarLibroEnDOM();
        a.deleteNode(""); //Poner el nombre del titulo nuevo que queramos añadir o uno existente.
        a.guardarDOMcomoArchivo("newbooks.xml");
    }
}
