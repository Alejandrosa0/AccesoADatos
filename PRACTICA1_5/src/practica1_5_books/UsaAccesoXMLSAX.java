package practica1_5_books;

import java.io.File;

public class UsaAccesoXMLSAX {

    public static void main(String[] args) {
        File f = new File("books.xml"); //Creación de instancia de la clase AccesoXMLSAX y objeto File con el nombre del archivo xml ("books.xml")
        AccesoXMLSAX a = new AccesoXMLSAX();
        
        a.parsearXMLconLibrosSAXhandler(f); //Se llama al método para parsear/analizar el archivo XML utilizando el manejador de libros SAX
    }
}
