package AccesoOracle.AccesoOracle;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;

public class AccesoOracle {
	private Connection con;
	void abrirConexion() {
		try {
			Class.forName("oracle.jdbc.driver.OracleDriver");
			
			con=DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:XE","SYS AS SYSDBA","1234");
			
			System.out.println("Conexion OK");			
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	void cerrarConexion() {
		try {
			System.out.print("Conexion cerrada");
			con.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	void mostrarContactos() {
		try {
			Statement st = con.createStatement();
			ResultSet resul = st.executeQuery("SELECT c.nombre, c.telefono FROM contactos c");
			
			System.out.println("INFORMACION DE CONTACTOS----------");
			
			while (resul.next()) {
				System.out.printf("\nNOMBRE: %s\nTELEFONO: %s",resul.getString(1),resul.getString(2));
			}
			
			System.out.println("\n----------");
			resul.close();
			st.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

}
