package practica1_5_libros;

import java.io.File;
import javax.xml.parsers.SAXParser;
import javax.xml.parsers.SAXParserFactory;

public class AccesoXMLSAX {

    SAXParser parser;

    //Método para analizar un archivo XML utilizando el manejador de libros SAX
    public int parsearXMLconLibrosSAXhandler(File f) {
        try {
            SAXParserFactory factory = SAXParserFactory.newInstance();
            parser = factory.newSAXParser();
            LibrosSAXhandler sh = new LibrosSAXhandler(); //Crea un nuevo manejador de libros SAX
            parser.parse(f, sh); //Realiza el análisis del archivo XML utilizando el manejador de libros SAX
            return 0; //Retorna 0 si el análisis se realiza con éxito
        } catch (Exception e) {
            e.printStackTrace(); //Imprime la traza de la excepción en caso de error
            return -1; //Retorna -1 si ocurre un error durante el análisis
        }
    }

    //Método para analizar un archivo XML utilizando el manejador de títulos SAX
    public int parsearXMLconTitulosSAXhandler(File f) {
        try {
            SAXParserFactory factory = SAXParserFactory.newInstance();
            parser = factory.newSAXParser();
            TitulosSAXhandler sh = new TitulosSAXhandler(); //Crea un nuevo manejador de títulos SAX
            parser.parse(f, sh); //Realiza el análisis del archivo XML utilizando el manejador de títulos SAX
            return 0; //Retorna 0 si el análisis se realiza con éxito
        } catch (Exception e) {
            e.printStackTrace(); //Imprime la traza de la excepción en caso de error
            return -1; //Retorna -1 si ocurre un error durante el análisis
        }
    }
}
