package practica1_5_books;

import java.io.File;
import javax.xml.parsers.SAXParser;
import javax.xml.parsers.SAXParserFactory;

public class AccesoXMLSAX {

    SAXParser parser;

    //Método para parsear/analizar un archivo XML utilizando el manejador de libros SAX
    public int parsearXMLconLibrosSAXhandler(File f) {
        try {
            SAXParserFactory factory = SAXParserFactory.newInstance();
            parser = factory.newSAXParser();
            LibrosSAXhandler sh = new LibrosSAXhandler(); //Crea un nuevo manejador de libros SAX
            parser.parse(f, sh); //Realiza el análisis del archivo XML utilizando el manejador de libros SAX
            return 0; //Devuelve 0 si el análisis se realiza con éxito
        } catch (Exception e) {
            e.printStackTrace(); //Imprime la excepción en caso de error
            return -1; //Devuelve -1 si ocurre un error durante el análisis
        }
    }
}
