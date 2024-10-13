package modelo;

import java.util.List;

public interface IProductos {
    
    public List<Productos> listarProductos();
    public Productos mostrarProducto(int id); 
    public boolean insertarProducto(Productos pro);
    public boolean modificarProducto(Productos pro);
    public boolean eliminarProducto(int id);
    public boolean actualizarStock(Productos pro);
    
}