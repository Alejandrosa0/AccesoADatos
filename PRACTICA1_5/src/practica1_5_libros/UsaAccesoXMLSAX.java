package practica1_5_libros;

import java.io.File;

public class UsaAccesoXMLSAX {

    public static void main(String[] args) {
        File f = new File("Libros.xml"); //Creación de instancia de la clase AccesoXMLSAX y objeto File con el nombre del archivo xml ("Libros.xml")
        AccesoXMLSAX a = new AccesoXMLSAX();
        
        a.parsearXMLconLibrosSAXhandler(f); //Llamada al método para parsear/analizar el archivo XML utilizando el manejador de libros SAX
        a.parsearXMLconTitulosSAXhandler(f); //Llamada al método para parsear/analizar el archivo XML utilizando el manejador de títulos SAX
    }
}
