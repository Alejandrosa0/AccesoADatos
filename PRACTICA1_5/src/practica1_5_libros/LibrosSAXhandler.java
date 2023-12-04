package practica1_5_libros;

import org.xml.sax.Attributes;
import org.xml.sax.helpers.DefaultHandler;
import org.xml.sax.*;

public class LibrosSAXhandler extends DefaultHandler {

    private int bookCount = 0;

    public LibrosSAXhandler() {
    }

    //Método invocado/llamado al iniciar el análisis del documento XML
    public void startDocument() throws SAXException {
        System.out.println("LISTADO DE LIBROS:" + "\n-----------------");
    }

    @Override
    //Método invocado/llamado al encontrar un elemento de inicio en el documento XML
    public void startElement(String uri, String localName, String qName, Attributes atts) throws SAXException {
        if (qName.equals("Libro")) {
            bookCount++;
            System.out.println("Libro nº " + bookCount);
            System.out.println("Publicado en: " + atts.getValue(atts.getQName(0)));
        } else if (qName.equals("Titulo")) {
            System.out.print("El título y autor es: ");
        } else if (qName.equals("Autor")) {

        }
    }

    @Override
    //Método invocado/llamado al encontrar un elemento de cierre en el documento XML
    public void endElement(String uri, String localName, String qName) throws SAXException {
        if (qName.equals("Libro")) {
            System.out.println("\n------------------------------");
        }
    }

    @Override
    //Método invocado/llamado al encontrar datos de caracteres en el documento XML
    public void characters(char[] ch, int start, int length) throws SAXException {
        String car = new String(ch, start, length);
        car = car.replaceAll("\t", ""); //Elimina todos los caracteres de tabulación
        car = car.replaceAll("\n", ""); //Elimina todos los caracteres de nueva línea
        if (car.trim().length() > 0) { //Si la cadena no está vacía
            System.out.print(car + "//"); //Añade "//" entre el título y el autor
        }
    }
}
