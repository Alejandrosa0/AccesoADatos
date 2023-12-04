package practica1_5_libros;

import org.xml.sax.Attributes;
import org.xml.sax.helpers.DefaultHandler;
import org.xml.sax.*;

public class TitulosSAXhandler extends DefaultHandler {

    private String etiqueta;

    public TitulosSAXhandler() {
        etiqueta = ""; //También se puede hacer con int etiqueta
    }

    @Override
    //Método invocado/llamado al iniciar el análisis del documento XML
    public void startDocument() {
        System.out.println("LISTADO DE TITULOS");
        System.out.println("==================");
    }

    @Override
    //Método invocado/llamado al encontrar un elemento de inicio en el documento XML
    public void startElement(String uri, String localName, String qName, Attributes atts) throws SAXException {

        if (qName.equals("Libro")) {
            etiqueta = "Libro";
        } else if (qName.equals("Titulo")) {
            etiqueta = "Titulo";
        } else if (qName.equals("Autor")) {
            etiqueta = "Autor";
        }
    }

    @Override
    //Método invocado/llamado al encontrar datos de caracteres en el documento XML
    public void characters(char[] ch, int start, int length) throws SAXException {

        if (etiqueta.equals("Titulo")) {
            String car = new String(ch, start, length);
            car = car.replaceAll("\t", ""); // Quita todos los tabuladores
            car = car.replaceAll("\n", "");
            System.out.println(car);
        }
    }
}
