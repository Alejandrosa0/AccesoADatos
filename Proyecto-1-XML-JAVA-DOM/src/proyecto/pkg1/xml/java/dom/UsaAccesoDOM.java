package proyecto.pkg1.xml.java.dom;

import java.io.File;
import java.io.IOException;
import javax.xml.parsers.ParserConfigurationException;
import org.xml.sax.SAXException;

public class UsaAccesoDOM {

    public static void main(String[] args) throws ParserConfigurationException, SAXException, IOException {
        
        AccesoDOM a = new AccesoDOM(); //Creación de instancia de la clase AccesoDOM y objeto File con el nombre del archivo xml ("books.xml")
        File f = new File("books.xml"); //Necesitamos Libros.xml en la ruta correcta

        a.abrirXMLaDOM(f); //Llamamos a los métodos para abrir y recorrer el archivo XML y mostramos el contenido del mismo
        a.recorrerDOMyMuestra();
        
        a.insertarLibroEnDOM(); //Llamamos a los métodos para insertar un libro en el archivo XML y eliminar un libro (nodo) del archivo XML
        a.deleteNode(""); //Poner el nombre del titulo nuevo que queramos añadir o uno existente.
        a.guardarDOMcomoArchivo("newbooks.xml"); //Guardará el documento modificado en un archivo llamado ("newbooks.xml")
    } 
}
