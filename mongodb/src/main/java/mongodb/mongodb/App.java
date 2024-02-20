package mongodb.mongodb;

import com.mongodb.ConnectionString;
import com.mongodb.client.*;
import org.bson.Document;
import com.mongodb.MongoException;


public class App 
{
    public static void main( String[] args )
    {
    	//String connectionString = "mongodb+srv://admin:joLK5NGqpMM7PV9a@cluster0.2yshilc.mongodb.net/?retryWrites=true&w=majority";
    	
    	String connectionString = "mongodb://localhost:27017";
    	
    	try (MongoClient mongoClient = MongoClients.create(new ConnectionString(connectionString))) {
    		
    		//MongoDatabase database = mongoClient.getDatabase("mi_base_de_datos");
    		
    		MongoDatabase database = mongoClient.getDatabase("databasemongodb1");
    		
    		//MongoCollection<Document> collection = database.getCollection("mi_coleccion");
    		
    		MongoCollection<Document> collection = database.getCollection("collectionmongodb1");
    		
    		//INSERTAR DATOS
    		Document document = new Document("Nombre", "Alejandro")
    								.append("edad", 19)
    								.append("ciudad", "Albacete");
    		collection.insertOne(document);
    		
    		//ACTUALIZAR DATOS
    		/*Document query = new Document("Nombre", "Alejandro");
    	    Document update = new Document("$set", new Document("Nombre", "NuevoNombre")
    	            .append("edad", 25)
    	            .append("ciudad", "NuevaCiudad"));
    	    actualizarDatos(collection, query, update);*/
    		
    		//ELIMINAR DATOS
    		/*String nombreAEliminar = "NuevoNombre";
    	    eliminarDatos(collection, nombreAEliminar);*/
  		
    		MongoCursor<Document> cursor = collection.find().iterator();
    		try {
    			while (cursor.hasNext()) {
    				System.out.println(cursor.next().toJson());		
    			}
    		} finally {
    			cursor.close();
    		}	
    	}
    }
    
    public static void actualizarDatos(MongoCollection<Document> collection, Document query, Document update) {
        try {
            collection.updateOne(query, update);
        } catch (MongoException e) {
            System.err.println("Error al actualizar el documento: " + e.getMessage());
        } catch (Exception e) {
            System.err.println("Ocurrió un error: " + e.getMessage());
        }
    }
    
    public static void eliminarDatos(MongoCollection<Document> collection, String nombre) {
        try {
            Document query = new Document("Nombre", nombre);
            collection.deleteOne(query);
        } catch (MongoException e) {
            System.err.println("Error al eliminar el documento: " + e.getMessage());
        } catch (Exception e) {
            System.err.println("Ocurrió un error: " + e.getMessage());
        }
    }
}
