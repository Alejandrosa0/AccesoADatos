package adejerciciolecturaescritura;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

public class ADEjercicioLecturaEscritura {

    public static void main(String[] args) {
        int nota1, nota2, nota3;
        double media;
        String alumno;
        
        try {
            BufferedReader br = new BufferedReader(new FileReader("alumnosNotas.txt"));
            while ((alumno = br.readLine()) != null) {
                String[] valores = alumno.split(":");
                nota1 = Integer.parseInt(valores[1]);
                nota2 = Integer.parseInt(valores[2]);
                nota3 = Integer.parseInt(valores[3]);
                media = (nota1 + nota2 + nota3) / 3;
                
                System.out.println("Alumnos: " + valores[0]);
                System.out.println("Nota Media: " + media);
            }
            
            br.close();
            
        } catch (FileNotFoundException ex) {
            System.out.println(ex.getMessage());
            
        } catch (IOException ex) {
            System.out.println(ex.getMessage());
        }
    }
}
