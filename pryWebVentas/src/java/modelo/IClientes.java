
package modelo;
import java.util.List;

public interface IClientes {
    public List<Clientes> listarClientes();
    public Clientes mostrarCliente(int id);
    public boolean insertarCliente(Clientes clie);
    public boolean modificarCliente(Clientes clie);
    public boolean eliminarCliente(int id); 
    
}
