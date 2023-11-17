package practica1_5_books;

import org.xml.sax.Attributes;
import org.xml.sax.helpers.DefaultHandler;
import org.xml.sax.*;

public class LibrosSAXhandler extends DefaultHandler {

    private int bookCount = 0;

    public LibrosSAXhandler() {
    }

    //Método invocado al iniciar el análisis del documento XML
    public void startDocument() throws SAXException {
        System.out.print("LISTADO DE LIBROS:" + "\n-----------------");
    }

    @Override
    public void startElement(String uri, String localName, String qName, Attributes atts) throws SAXException {
        if (qName.equals("book")) {
            bookCount++;
            System.out.println("ID: " + atts.getValue("id"));
        } else if (qName.equals("author")) {
            System.out.print("Autor: ");
        } else if (qName.equals("title")) {
            System.out.print("Título: ");
        } else if (qName.equals("genre")) {
            System.out.print("Género: ");
        } else if (qName.equals("price")) {
            System.out.print("Precio: ");
        } else if (qName.equals("publish_date")) {
            System.out.print("Fecha de publicación: ");
        } else if (qName.equals("description")) {
            System.out.print("Descripción: ");   
        }
    }

    @Override
    //Método invocado al encontrar un elemento de cierre en el documento XML
    public void endElement(String uri, String localName, String qName) throws SAXException {
        if (qName.equals("book")) {
            System.out.print("-----------------");
        }
    }

    @Override
    public void characters(char[] ch, int start, int length) throws SAXException {
        String car = new String(ch, start, length);
        car = car.replaceAll("\t", ""); // Elimina todos los caracteres de tabulación
        car = car.replaceAll("\n", ""); // Elimina todos los caracteres de nueva línea
        System.out.println(car);
    }
}
