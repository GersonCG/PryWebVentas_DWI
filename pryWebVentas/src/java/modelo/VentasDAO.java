package modelo;

/**
 *
 * @author Cesar
 */

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import config.Conexion;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.Date;
import java.util.List;

public class VentasDAO implements IVenta {
    
    Connection conexion;
    public VentasDAO() throws ClassNotFoundException{
        Conexion cnx = new Conexion();
        conexion = cnx.getConexion();
    }

    @Override
    public List<Ventas> listarVentas() {
        
        PreparedStatement ps;
        ResultSet rs;
        List<Ventas> lista = new ArrayList<>();
        
        try { 
            
            ps = conexion.prepareStatement( "CALL listarVenta");
            rs = ps.executeQuery();
            
            while (rs.next()) {

                Date cab_fecha = rs.getDate("v.cab_fecha");
                int cab_id = rs.getInt(2);
                String clapellido = rs.getString("cli_apellido");
                String cldireccion = rs.getString("cli_direccion");
                String nombre = rs.getString("prd_nombre");
                Double precio = rs.getDouble("prd_precio");
                int det_cantidad = rs.getInt(7);
                Double det_precio = rs.getDouble("det_precio");
                Double det_total = rs.getDouble("det_total");
                int det_id = rs.getInt(10);
                                                                               
                Ventas vent = new Ventas(cab_fecha,cab_id,clapellido,cldireccion,nombre,precio,det_cantidad,det_precio,det_total,det_id);
                lista.add(vent);
            }
            return lista;
            
        } catch (SQLException e) {
            System.out.println(e.toString());
            return null;
        }
        
    }

}
