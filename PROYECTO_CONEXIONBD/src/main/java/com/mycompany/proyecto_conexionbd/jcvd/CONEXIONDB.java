package com.mycompany.proyecto_conexionbd.jcvd;

import java.sql.*;
import java.util.Scanner;

public class CONEXIONDB {

    static final String DB_URL = "jdbc:mysql://localhost:3306/jcvd";
    static final String USER = "Usuario1";
    static final String PASS = "1234";

    public static void main(String[] args) {

        try {
            System.out.println("PROYECTO CONEXIÓNBD:");
            //Busqueda por nombre
            System.out.println("\nbuscaNombre:");
            System.out.println(buscaNombre("Star Wars Jedi Fallen Order"));
            //Ejecuta una consulta
            System.out.println("\nlanzaConsulta:");
            lanzaConsulta("SELECT * FROM videojuegos");
            //Añade un nuevo registro utilizando parámetros
            System.out.println("\nnuevoRegistro (Por parámetros):");
            nuevoRegistro("Mario Kart 8", "Carreras", Date.valueOf("2014-05-29"), "Nintendo", 60);
            //Añade un nuevo registro utilizando entrada por teclado
            System.out.println("\nnuevoRegistro (Por teclado):"); //F1 23, Carreras, 2023-04-28, Codemasters, 60 Datos de un videojuego para añadir por teclado
            nuevoRegistro();
            //Elimina un registro
            System.out.println("\neliminarRegistro:");
            eliminarRegistro("Mario Kart 8");
  

        } catch (Exception e) { //Excepción Exception e 
            e.printStackTrace(); //Muestra la excepción en caso de error
        }
    }

    public static boolean buscaNombre(String nombre) { //Método para buscar un videojuego por nombre
        boolean existe = false;
        String Query = "SELECT COUNT(*) FROM videojuegos WHERE nombre = ?";

        try (Connection conn = DriverManager.getConnection(DB_URL, USER, PASS);  
            PreparedStatement pstmt = conn.prepareStatement(Query)) {
            pstmt.setString(1, nombre);
            ResultSet rs = pstmt.executeQuery();

            if (rs.next()) {
                int count = rs.getInt(1);
                if (count > 0) {
                    existe = true; //Si el nombre existe, establece el valor de existe a true
                }
            }

            conn.close(); //Cierra la conexión a la base de datos

        } catch (SQLException e) { //Excepción SQLException e
            e.printStackTrace(); //Muestra la excepción en caso de error
            return false; //Devuelve false en caso de excepción
        }

        return existe; //Devuelve el valor de existe que indica si el nombre existe en la base de datos
    }

    public static void lanzaConsulta(String consulta) { //Método que lanza una consulta y muestra los datos pasados como parámetro
        try (Connection conn = DriverManager.getConnection(DB_URL, USER, PASS);  
            Statement stmt = conn.createStatement();  
            ResultSet rs = stmt.executeQuery(consulta)) {

            while (rs.next()) {
                //Imprime los detalles de cada registro recuperado de la base de datos
                System.out.println("ID: " + rs.getInt("id"));
                System.out.println("NOMBRE: " + rs.getString("nombre"));
                System.out.println("GÉNERO: " + rs.getString("genero"));
                System.out.println("FECHA DE LANZAMIENTO: " + rs.getDate("fechalanzamiento"));
                System.out.println("COMPAÑÍA: " + rs.getString("compañia"));
                System.out.println("PRECIO: " + rs.getFloat("precio") + "\n");
            }

            conn.close(); //Cierra la conexión a la base de datos

        } catch (SQLException e) { //Excepción SQLException e
            e.printStackTrace(); //Muestra la excepción en caso de error
        }
    }

    public static void nuevoRegistro(String nombre, String genero, Date fechaLanzamiento, String compañia, float precio) { //Método para crear un videojuego de la tabla videojuegos con datos pasados como parámetro
        String insertQuery = "INSERT INTO videojuegos (nombre, genero, fechalanzamiento, compañia, precio) VALUES (?, ?, ?, ?, ?)";

        try (Connection conn = DriverManager.getConnection(DB_URL, USER, PASS);  
            PreparedStatement pstmt = conn.prepareStatement(insertQuery)) {
            
            pstmt.setString(1, nombre); //Establece el nombre en la consulta preparada
            pstmt.setString(2, genero); //Establece el género en la consulta preparada
            pstmt.setDate(3, fechaLanzamiento); //Establece la fechalanzamiento en la consulta preparada
            pstmt.setString(4, compañia); //Establece la compañia en la consulta preparada
            pstmt.setFloat(5, precio); //Establece el precio en la consulta preparada
            pstmt.executeUpdate();

            System.out.println("¡Juego insertado correctamente!");
            
            conn.close(); //Cierra la conexión a la base de datos
            
        } catch (SQLException e) { //Excepción SQLException e
            e.printStackTrace(); //Muestra la excepción en caso de error
        }
    }

    public static void nuevoRegistro() {  //Método para crear un videojuego de la tabla videojuegos con datos pasados por teclado
        Scanner scanner = new Scanner(System.in);

        System.out.println("Nuevo registro de videojuego:");  //Pide los datos y deja escribirlos por teclado
        System.out.println("Ingrese el nombre del videojuego:");
        String nombre = scanner.nextLine();

        System.out.println("Ingrese el género del videojuego:");
        String genero = scanner.nextLine();

        System.out.println("Ingrese la fecha de lanzamiento del videojuego (Formato: yyyy-MM-dd):");
        String fechalanzamiento = scanner.nextLine();
        Date fechaLanzamiento = Date.valueOf(fechalanzamiento);

        System.out.println("Ingrese la compañía del videojuego:");
        String compañia = scanner.nextLine();

        System.out.println("Ingrese el precio del videojuego:");
        float precio = scanner.nextFloat();

        String insertQuery = "INSERT INTO videojuegos (nombre, genero, fechalanzamiento, compañia, precio) VALUES (?, ?, ?, ?, ?)";

        try (Connection conn = DriverManager.getConnection(DB_URL, USER, PASS);  
            PreparedStatement pstmt = conn.prepareStatement(insertQuery)) {

            pstmt.setString(1, nombre); //Establece el nombre en la consulta preparada
            pstmt.setString(2, genero); //Establece el género en la consulta preparada
            pstmt.setDate(3, fechaLanzamiento); //Establece la fechalanzamiento en la consulta preparada
            pstmt.setString(4, compañia); //Establece la compañia en la consulta preparada
            pstmt.setFloat(5, precio); //Establece el precio en la consulta preparada
            pstmt.executeUpdate();

            System.out.println("¡Juego insertado correctamente!");
            
            conn.close(); //Cierra la conexión a la base de datos

        } catch (SQLException e) { //Excepción SQLException e
            e.printStackTrace(); //Muestra la excepción en caso de error
        }
    }

    public static boolean eliminarRegistro(String nombre) { //Método que elimina un videojuego con el nombre pasado como parámetro
        String deleteQuery = "DELETE FROM videojuegos WHERE nombre = ?";

        try (Connection conn = DriverManager.getConnection(DB_URL, USER, PASS);  
            PreparedStatement pstmt = conn.prepareStatement(deleteQuery)) {

            pstmt.setString(1, nombre);

            int rowsAffected = pstmt.executeUpdate();

            if (rowsAffected > 0) {
                System.out.println("¡Videojuego eliminado correctamente!");
                return true;
            } else {
                System.out.println("No se encontró el videojuego con el nombre especificado.");
                return false;
            }          

        } catch (SQLException e) { //Excepcióm SQLException e
            e.printStackTrace(); //Muestra la excepción en caso de error
            return false;
        }
    }
}
