package practica1_1_1_2;

import java.io.File;
import java.io.IOException;
import javax.xml.parsers.ParserConfigurationException;
import org.xml.sax.SAXException;

public class UsaAccesoDOM {

    public static void main(String[] args) throws ParserConfigurationException, SAXException, IOException {
        AccesoDOM a = new AccesoDOM(); //Creación de instancia de la clase AccesoDOM y objeto File con el nombre del archivo xml ("Libros.xml")
        File f = new File("Libros.xml"); //Necesitamos Libros.xml en la ruta correcta

        a.abriXMLaDOM(f); //Llamamos a los métodos para abrir y recorrer el archivo XML y mostramos el contenido del mismo
        a.recorreDOMyMuestra();
    }
}
