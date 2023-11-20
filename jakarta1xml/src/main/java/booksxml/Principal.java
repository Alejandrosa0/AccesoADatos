package booksxml;

import jakarta.xml.bind.JAXBContext;
import jakarta.xml.bind.JAXBException;
import jakarta.xml.bind.Unmarshaller;
import java.io.File;
import java.util.List;

public class Principal {

    public static void main(String[] args) {
        try {
            // Crear el contexto JAXB
            JAXBContext jaxbContext = JAXBContext.newInstance(Catalog.class);

            // Crear un objeto Unmarshaller
            Unmarshaller jaxbUnmarshaller = jaxbContext.createUnmarshaller();

            // Especificar la ubicación del archivo XML a leer
            File XMLfile = new File("books.xml");

            // Convertir el archivo XML en un objeto Catalog
            Catalog catalog = (Catalog) jaxbUnmarshaller.unmarshal(XMLfile);

            // Obtener la lista de libros del catálogo
            System.out.println("<catalog>");
            for (book book : catalog.getBook()) {
                System.out.println("\t<book>");
                System.out.println("\t\t<id>" + book.getId() + "</id>");
                System.out.println("\t\t<title>" + book.getTitle() + "</title>");
                System.out.println("\t\t<author>" + book.getAuthor() + "</author>");
                System.out.println("\t\t<genre>" + book.getGenre() + "</genre>");
                System.out.println("\t\t<price>" + book.getPrice() + "</price>");
                System.out.println("\t\t<publish_date>" + book.getPublish_date() + "</publish_date>");
                System.out.println("\t\t<description>" + book.getDescription() + "</description>");   
                System.out.println("\t</book>");
            }
            System.out.println("</catalog>");
        } catch (JAXBException e) {
            e.printStackTrace();

        }
    }
}
