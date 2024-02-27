package AccesoOracle.AccesoOracle;

public class UsaAccesoOracle 
{
    public static void main( String[] args )
    {
        AccesoOracle a = new AccesoOracle();
        a.abrirConexion();
        a.mostrarContactos();
        a.cerrarConexion();
    }
}
