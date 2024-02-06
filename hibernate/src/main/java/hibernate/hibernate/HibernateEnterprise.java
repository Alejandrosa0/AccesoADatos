package hibernate.hibernate;

import java.util.Iterator;
import java.util.List;
import org.hibernate.HibernateException;
import org.hibernate.ObjectNotFoundException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;
import java.util.Scanner;
import org.hibernate.query.Query;

public class HibernateEnterprise {

//SQL PRÁCTICA 3
	private static SessionFactory sf; // this SessionFactory will be created once and used for all the connections
	private static Productos p;

	HibernateEnterprise() { // constructor
		// sf = HibernateUtil.getSessionFactory();
		sf = new Configuration().configure().buildSessionFactory(); // also works
	}

	public void close() {
		sf.close();
	}

	public void addProduct(String name, double price) {
		Session session = sf.openSession(); // session es la variable que tiene el método save para guardar productos
		Transaction tx = null;
		// create the product with the parameters in the method
		Productos p = new Productos();
		p.setNombre(name);
		p.setPrecio(price);
		// keep it in the database=session.save(p)
		try {
			System.out.println("======================================");
			System.out.printf("Insertando la Fila en la Base de Datos: %s, %s\n", name, price);
			System.out.println("======================================");
			tx = session.beginTransaction();
			session.save(p); // we INSERT p into the table PRODUCTS
			tx.commit(); // if session.save doesnt produce an exception, we commit; the transaction
		} catch (Exception e) { // if there is any exception, we "rollback" and close safely
			if (tx != null) {
				tx.rollback();
			}
		} finally {
			session.close();
		}
	}

	public void showProducts() {
		Session session = sf.openSession();
		Transaction tx = null;
		try {
			tx = session.beginTransaction();
			List allproducts = session.createQuery("From Productos").list();
			Iterator it = allproducts.iterator();
			System.out.println("======================================");
			System.out.println("Buscando Productos...");
			System.out.println("======================================");
			while (it.hasNext()) {
				// for (Iterator iterator = allproducts.iterator(); iterator.hasNext();){
				Productos p = (Productos) it.next();
				System.out.println("======================================");
				System.out.println("Id: " + p.getId());
				System.out.println("Nombre: " + p.getNombre());
				System.out.println("Precio: " + p.getPrecio());
				System.out.println("======================================");
			}
			tx.commit();
			System.out.println("======================================");
			System.out.println("Finalizada la Busqueda...");
			System.out.println("======================================");
		} catch (HibernateException e) {
			if (tx != null)
				tx.rollback();
			e.printStackTrace();
		} finally {
			session.close();
		}
	}

	public Productos findProductById(int id) {
		Session session = sf.openSession();
		Transaction tx = null;
		Productos p = new Productos();
		try {
			System.out.println("======================================");
			System.out.println("Cargando Producto de la Base de Datos...");
			System.out.println("======================================");
			tx = session.beginTransaction();
			p = (Productos) session.load(Productos.class, id);
			tx.commit();
			System.out.println("======================================");
			System.out.println("Producto con ID -> " + id);
			System.out.println("Su Nombre es -> " + p.getNombre());
			System.out.println("======================================");
		} catch (ObjectNotFoundException e) {
			if (tx != null) {
				System.out.println(e);
				System.out.println("Product not found");
			}
		} catch (Exception e) {
			if (tx != null) {
				System.out.println(e);
				tx.rollback();
			}
		} finally {
			session.close();
		}
		return p;
	}

	public void deleteProductById(int id) {
		Productos p = new Productos();
		Session session = sf.openSession();
		Transaction tx = null;
		try {
			System.out.println("======================================");
			System.out.println("Buscando Producto con ID -> " + id);
			System.out.println("======================================");
			tx = session.beginTransaction();
			p = (Productos) session.get(Productos.class, id);
			if (p != null) {
				System.out.println("======================================");
				System.out.println("Borrando Producto de la Base de Datos...");
				System.out.println("======================================");
				session.delete(p);
				tx.commit();
				System.out.println("======================================");
				System.out.printf(
						"Producto Borrado de la Base de Datos ..." + "\n ID -> %s\n Nombre -> %s\n Precio -> %s",
						p.getId(), p.getNombre(), p.getPrecio());
				System.out.println("\n======================================");
			} else {
				System.out.println("======================================");
				System.out.println("No Se Encontro Ningun Producto con ID -> " + id);
				System.out.println("======================================");
			}
		} catch (Exception e) {
			if (tx != null) {
				tx.rollback();
			}
		} finally {
			session.close();
		}
	}

	public void updateProductById(int id, String newName, double newPrice) {
		Productos p = new Productos();
		Session session = sf.openSession();
		Transaction tx = null;
		try {
			System.out.println("======================================");
			System.out.println("Modificando el Producto de la Base de Datos...");
			System.out.println("Con los Siguientes Datos...");
			System.out.println("ID -> " + id);
			System.out.println("Nombre -> " + newName);
			System.out.println("Precio -> " + newPrice);
			System.out.println("======================================");
			tx = session.beginTransaction();
			p = (Productos) session.load(Productos.class, id); // we load the product
			System.out.println("======================================");
			System.out.println("Datos del Producto en la Base de Datos...");
			System.out.println("======================================");
			System.out.printf(" ID -> %s\n Nombre -> %s\n Precio -> %s", p.getId(), p.getNombre(), p.getPrecio());
			System.out.println("\n======================================");
			p.setPrecio(newPrice); // we change the properties
			p.setNombre(newName);
			session.update(p); // we update the values in the database
			tx.commit();
			System.out.println("======================================");
			System.out.println("Producto Modificado");
			System.out.println("======================================");
			System.out.printf("Datos del Producto Modificado..." + "\n ID -> %s\n Nombre -> %s\n Precio -> %s",
					p.getId(), p.getNombre(), p.getPrecio());
			System.out.println("\n======================================");
		} catch (Exception e) {
			System.out.println("======================================");
			System.out.println("No Se Encontro el Producto con ID -> " + id);
			System.out.println("======================================");
			if (tx != null) {
				tx.rollback();
			}
		} finally {
			session.close();
		}
	}

//HQL PRÁCTICA 3.3
	public void mostrarProductos() { //Muestra todos los productos
		Session session = sf.openSession();
		Transaction tx = null;
		try {
			tx = session.beginTransaction();
			List allproducts = session.createQuery("FROM Productos").list();
			Iterator it = allproducts.iterator();
			while (it.hasNext()) {
				// for (Iterator iterator = allproducts.iterator(); iterator.hasNext();){
				Productos p = (Productos) it.next();
				System.out.print("Id: " + p.getId());
				System.out.print("   , Nombre: " + p.getNombre());
				System.out.println("   , Precio: " + p.getPrecio());
			}
			tx.commit();
		} catch (HibernateException e) {
			if (tx != null)
				tx.rollback();
			e.printStackTrace();
		} finally {
			session.close();
		}
	}

	public void mostrarPorNombre(String texto) { //Muestra los productos según el nombre
		Session session = sf.openSession();
		Transaction tx = null;
		try {
			tx = session.beginTransaction();
			String hql = "FROM Productos P WHERE P.nombre LIKE :texto";
			List<Productos> productos = session.createQuery(hql).setParameter("texto", "%" + texto + "%").list();
			for (Productos p : productos) {
				System.out.print("Id: " + p.getId());
				System.out.print("   , Nombre: " + p.getNombre());
				System.out.println("   , Precio: " + p.getPrecio());
			}
			tx.commit();
		} catch (HibernateException e) {
			if (tx != null)
				tx.rollback();
			e.printStackTrace();
		} finally {
			session.close();
		}
	}

	public void productosOrdenadosPorPrecio() { //Muestra los productos ordenados por el precio
		Session session = sf.openSession();
		Transaction tx = null;
		try {
			tx = session.beginTransaction();
			List<Productos> productos = session.createQuery("FROM Productos P ORDER BY P.precio ASC").list();
			for (Productos p : productos) {
				System.out.print("Id: " + p.getId());
				System.out.print("   , Nombre: " + p.getNombre());
				System.out.println("   , Precio: " + p.getPrecio());
			}
			tx.commit();
		} catch (HibernateException e) {
			if (tx != null)
				tx.rollback();
			e.printStackTrace();
		} finally {
			session.close();
		}
	}

	public void precioDe(String nombre) { //Muestra el precio de un producto según su nombre
		Session session = sf.openSession();
		Transaction tx = null;
		try {
			tx = session.beginTransaction();
			String hql = "SELECT P.precio FROM Productos P WHERE P.nombre = :nombre";
			List<Double> precios = session.createQuery(hql).setParameter("nombre", nombre).list();
			for (Double precio : precios) {
				System.out.println(
						"El Precio de todos los productos con ese nombre " + "(" + nombre + ")" + " es: " + precio);
			}
			tx.commit();
		} catch (HibernateException e) {
			if (tx != null)
				tx.rollback();
			e.printStackTrace();
		} finally {
			session.close();
		}
	}

	public void buscaProducto(int id) { //Busca un producto según el id
		Session session = sf.openSession();
		Transaction tx = null;
		try {
			tx = session.beginTransaction();
			String hql = "FROM Productos P WHERE P.id = :id";
			Productos producto = (Productos) session.createQuery(hql).setParameter("id", id).uniqueResult();
			System.out.print("Id: " + producto.getId());
			System.out.print("   , Nombre: " + producto.getNombre());
			System.out.println("   , Precio: " + producto.getPrecio());
			tx.commit();
		} catch (HibernateException e) {
			if (tx != null)
				tx.rollback();
			e.printStackTrace();
		} finally {
			session.close();
		}
	}

//HQL PRÁCTICA 3.1
	public void mostrarClientes() { //Muestra todos los clientes
		Session session = sf.openSession();
		Transaction tx = null;
		try {
			tx = session.beginTransaction();
			List<Clientes> clientes = session.createQuery("FROM Clientes").list();
			for (Clientes c : clientes) {
				System.out.print("Id: " + c.getId());
				System.out.print("   , Nombre: " + c.getNombre());
				System.out.println("   , País: " + c.getPais());
			}
			tx.commit();
		} catch (HibernateException e) {
			if (tx != null)
				tx.rollback();
			e.printStackTrace();
		} finally {
			session.close();
		}
	}

	public void agregarCliente(String nombre, String pais) { //Agrega un nuevo cliente
		Session session = sf.openSession();
		Transaction tx = null;
		try {
			tx = session.beginTransaction();
			Clientes nuevoCliente = new Clientes();
			nuevoCliente.setNombre(nombre);
			nuevoCliente.setPais(pais);
			session.save(nuevoCliente);
			tx.commit();
			System.out.println("Cliente " + nombre + " ha sido añadido correctamente.");
		} catch (HibernateException e) {
			if (tx != null)
				tx.rollback();
			e.printStackTrace();
		} finally {
			session.close();
		}
	}

	public void borrarCliente(int id) { //Borra un cliente según el id
		Session session = sf.openSession();
		Transaction tx = null;
		try {
			tx = session.beginTransaction();
			Clientes cliente = session.get(Clientes.class, id);
			if (cliente != null) {
				session.delete(cliente);
				tx.commit();
				System.out.println("El cliente con ID " + id + " ha sido borrado exitosamente.");
			} else {
				System.out.println("No se encontró ningún cliente con ID " + id + ".");
			}
		} catch (HibernateException e) {
			if (tx != null)
				tx.rollback();
			e.printStackTrace();
		} finally {
			session.close();
		}
	}

	public void actualizarCliente(int id) { //Actualiza los datos de un cliente según el id
		Session session = sf.openSession();
		Transaction tx = null;
		try {
			tx = session.beginTransaction();
			Clientes cliente = session.get(Clientes.class, id);
			if (cliente != null) {
				System.out.println(
						"ID: " + cliente.getId() + ", Nombre: " + cliente.getNombre() + ", País: " + cliente.getPais());
				Scanner scanner = new Scanner(System.in);
				System.out.println("¿Desea ingresar un nuevo nombre? (s/n)");
				String respuestaNombre = scanner.nextLine();
				if (respuestaNombre.equalsIgnoreCase("s")) {
					System.out.println("Ingrese el nuevo nombre: ");
					String nuevoNombre = scanner.nextLine();
					cliente.setNombre(nuevoNombre);
				}
				System.out.println("¿Desea ingresar un nuevo país? (s/n)");
				String respuestaPais = scanner.nextLine();
				if (respuestaPais.equalsIgnoreCase("s")) {
					System.out.println("Ingrese el nuevo país: ");
					String nuevoPais = scanner.nextLine();
					cliente.setPais(nuevoPais);
				}
				session.update(cliente);
				tx.commit();
				System.out.println("Cliente actualizado exitosamente.");
			} else {
				System.out.println("No se encontró ningún cliente con ID " + id + ".");
			}
		} catch (HibernateException e) {
			if (tx != null)
				tx.rollback();
			e.printStackTrace();
		} finally {
			session.close();
		}
	}

	public void borrarCliente(String nombre) { //Borra un cliente según el nombre
		Session session = sf.openSession();
		Transaction tx = null;
		try {
			tx = session.beginTransaction();
			Query query = session.createQuery("DELETE FROM Clientes WHERE nombre = :nombre");
			query.setParameter("nombre", nombre);
			int result = query.executeUpdate();
			if (result > 0) {
				System.out.println("Se ha borrado el cliente con el nombre " + nombre + ".");
			} else {
				System.out.println("No se encontró ningún cliente con el nombre " + nombre + ".");
			}
			tx.commit();
		} catch (HibernateException e) {
			if (tx != null)
				tx.rollback();
			e.printStackTrace();
		} finally {
			session.close();
		}
	}

	public void mostrarPorPais(String pais) { //Muestra los clientes y sus datos según el país al que pertenecen y hace un recuento de cuantos hay en ese país
		Session session = sf.openSession();
		Transaction tx = null;
		try {
			tx = session.beginTransaction();
			Query countQuery = session.createQuery("SELECT count(*) FROM Clientes WHERE pais = :pais");
			countQuery.setParameter("pais", pais);
			Long count = (Long) countQuery.uniqueResult();
			System.out.println("Número de clientes en " + pais + ": " + count);

			Query dataQuery = session.createQuery("FROM Clientes WHERE pais = :pais");
			dataQuery.setParameter("pais", pais);
			List<Clientes> clientes = dataQuery.list();
			for (Clientes c : clientes) {
				System.out.print("Id: " + c.getId());
				System.out.print(", Nombre: " + c.getNombre());
				System.out.println(", País: " + c.getPais());
			}
			tx.commit();
		} catch (HibernateException e) {
			if (tx != null)
				tx.rollback();
			e.printStackTrace();
		} finally {
			session.close();
		}
	}

	public String buscarPaisDe(String nombre) { //Muestra el país según el nombre del cliente.
	    Session session = sf.openSession();
	    Transaction tx = null;
	    String pais = "";
	    try {
	        tx = session.beginTransaction();
	        Query query = session.createQuery("SELECT pais FROM Clientes WHERE nombre = :nombre");
	        query.setParameter("nombre", nombre);
	        pais = (String) query.uniqueResult();
	        System.out.println("País: " + pais);
	        tx.commit();
	    } catch (HibernateException e) {
	        if (tx != null)
	            tx.rollback();
	        e.printStackTrace();
	    } finally {
	        session.close();
	    }
	    return pais;
	}
}
