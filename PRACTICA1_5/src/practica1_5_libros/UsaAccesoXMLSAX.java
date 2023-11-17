package practica1_5_libros;

import java.io.File;

public class UsaAccesoXMLSAX {

    public static void main(String[] args) {
        File f = new File("Libros.xml");
        AccesoXMLSAX a = new AccesoXMLSAX();
        a.parsearXMLconLibrosSAXhandler(f); //Se llama al método para analizar el archivo XML utilizando el manejador de libros SAX
        a.parsearXMLconTitulosSAXhandler(f); //Se llama al método para analizar el archivo XML utilizando el manejador de títulos SAX
    }
}
