package hibernate.hibernate;

import java.util.logging.Level;
import java.util.logging.LogManager;

public class UseHibernateEnterprise {
	public static void main(String[] args) {

		LogManager.getLogManager().getLogger("").setLevel(Level.SEVERE);
		HibernateEnterprise h = new HibernateEnterprise();
		// SQL PRÁCTICA 3
		// System.out.println("");
		// h.addProduct("monitor",170);
		// System.out.println("");
		// h.showProducts();
		// System.out.println("");
		// h.findProductById(3);
		// System.out.println("");
		// h.deleteProductById(7);
		// h.showProducts();
		// System.out.println("");
		// h.updateProductById(5,"ssd",105);
		// h.updateProductById(8,"ssd",155);
		// h.close();

		// HQL PRÁCTICA 3.3
		// h.mostrarProductos();
		// h.mostrarPorNombre("monitor");
		// h.productosOrdenadosPorPrecio();
		// h.precioDe("monitor");
		// h.buscaProducto(1);

		// HQL PRÁCTICA 3.1
		   h.mostrarClientes();
		// h.agregarCliente("alejandro", "españa");
		// h.borrarCliente(8);
		// h.actualizarCliente(8);
		// h.borrarCliente("alejandro");
		// h.mostrarPorPais("españa");
		// h.buscarPaisDe("luisa");
	}
}
