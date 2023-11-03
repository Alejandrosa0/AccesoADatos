package practica1_3;

import java.io.File;
import java.io.IOException;
import javax.xml.parsers.ParserConfigurationException;
import org.xml.sax.SAXException;

public class UsaAccesoDOM {

    public static void main(String[] args) throws ParserConfigurationException, SAXException, IOException {
        AccesoDOM a = new AccesoDOM();
        File f = new File("Libros.xml"); //Necesitamos Libros.xml en la ruta correcta

        a.abriXMLaDOM(f);
        a.recorreDOMyMuestra();
        a.insertarLibroEnDOM("Titulo", "Autor", "1992");
        a.deleteNode("Titulo");
    }
}
