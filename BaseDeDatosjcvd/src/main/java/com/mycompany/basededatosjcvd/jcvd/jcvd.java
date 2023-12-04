package com.mycompany.basededatosjcvd.jcvd;

import java.sql.*;

public class jcvd {

    static final String DB_URL = "jdbc:mysql://localhost:3306/jcvd";
    static final String USER = "Usuario1";
    static final String PASS = "1234";
    static final String QUERY = "SELECT * FROM videojuegos";

    public static void main(String[] args) {
        try {
            Connection conn = DriverManager.getConnection(DB_URL, USER, PASS); //Conexión con la base de datos
            Statement stmt = conn.createStatement();
            ResultSet rs = stmt.executeQuery(QUERY);

            while (rs.next()) {
                System.out.println("ID: " + rs.getInt("id")); //Muestra los datos
                System.out.println("NOMBRE: " + rs.getString("nombre"));
                System.out.println("GÉNERO: " + rs.getString("genero"));
                System.out.println("FECHA DE LANZAMIENTO: " + rs.getDate("fechalanzamiento"));
                System.out.println("COMPAÑÍA: " + rs.getString("compañia"));
                System.out.println("PRECIO: " + rs.getFloat("precio") + "\n");
            }
        
            stmt.close();

        } catch (SQLException e) { //Excepción SQLException e y su mensaje de error
            e.printStackTrace();
        }

        /*try { //Para insertar datos en la tabla videojuegos
            Connection conn = DriverManager.getConnection(DB_URL, USER, PASS);
            String insertQuery = "INSERT INTO videojuegos (id, nombre, genero, fechalanzamiento, compañia, precio) "
                    + "VALUES (?, ?, ?, ?, ?, ?)";
            PreparedStatement pstmt = conn.prepareStatement(insertQuery);
            pstmt.setInt(1, 6); // ID
            pstmt.setString(2, "Mario Kart 8"); // Nombre
            pstmt.setString(3, "Carreras"); // Género
            pstmt.setDate(4, java.sql.Date.valueOf("2014-05-29")); // Fecha de lanzamiento
            pstmt.setString(5, "Nintendo"); // Compañía
            pstmt.setFloat(6, 60); // Precio
            pstmt.executeUpdate();
            System.out.println("¡Juego insertado correctamente!.");
        } catch (SQLException e) {
            e.printStackTrace();
        } */
        
        /*try { //Para borrar datos de la tabla videjuegos
            Connection conn = DriverManager.getConnection(DB_URL, USER, PASS);
            String deleteQuery = "DELETE FROM videojuegos WHERE id = ?";
            PreparedStatement pstmt = conn.prepareStatement(deleteQuery);
            pstmt.setInt(1, 6); // ID del juego a eliminar
            pstmt.executeUpdate();
            System.out.println("¡Juego eliminado correctamente!.");
        } catch (SQLException e) {
            e.printStackTrace();
        }*/
        
        /*try { //Actualizar datos de la tabla videojuegos
            Connection conn = DriverManager.getConnection(DB_URL, USER, PASS);
            String updateQuery = "UPDATE videojuegos SET nombre = ?, genero = ?, fechalanzamiento = ?, compañia = ?, precio = ? WHERE id = ?";
            PreparedStatement pstmt = conn.prepareStatement(updateQuery);
            pstmt.setString(1, "Nuevo nombre"); // Nuevo nombre del juego
            pstmt.setString(2, "Nuevo género"); // Nuevo género del juego
            pstmt.setDate(3, java.sql.Date.valueOf("2023-11-20")); // Nueva fecha de lanzamiento del juego
            pstmt.setString(4, "Nueva compañía"); // Nueva compañía del juego
            pstmt.setFloat(5, 50); // Nuevo precio del juego
            pstmt.setInt(6, 6); // ID del juego a editar
            pstmt.executeUpdate();
            System.out.println("¡Juego editado correctamente!.");
        } catch (SQLException e) {
            e.printStackTrace();
        }*/
    } 
}
