package adejerciciolecturaescritura;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

public class ADEjercicioLecturaEscritura {

    public static void main(String[] args) {
        int nota1, nota2, nota3; //Variables
        double media;
        String alumno;
        
        try {
            BufferedReader br = new BufferedReader(new FileReader("alumnosNotas.txt")); //Apertura del archivo para su lectura
            while ((alumno = br.readLine()) != null) { //Bucle que recorre el archivo línea a línea
                String[] valores = alumno.split(":"); //Se separan los valores con :
                nota1 = Integer.parseInt(valores[1]); //Se parsean/convierten las notas a numeros enteros
                nota2 = Integer.parseInt(valores[2]);
                nota3 = Integer.parseInt(valores[3]);
                media = (nota1 + nota2 + nota3) / 3; //Media de las notas
                
                System.out.println("Alumnos: " + valores[0]);
                System.out.println("Nota Media: " + media);
            }
            
            br.close(); //Cierre del archivo una vez leído
            
        } catch (FileNotFoundException ex) { //Excepción FileNotFoundException y su mensaje de error
            System.out.println(ex.getMessage());
            
        } catch (IOException ex) { //Excepción IOException y su mensaje de error
            System.out.println(ex.getMessage());
        }
    }
}
