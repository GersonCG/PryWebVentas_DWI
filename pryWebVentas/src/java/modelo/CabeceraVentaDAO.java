
package modelo;

import config.Conexion;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 *
 * @author gerson
 */
public class CabeceraVentaDAO implements ICabeceraVenta {
    
    Connection conexion;

    public CabeceraVentaDAO() throws ClassNotFoundException {
        Conexion cnx = new Conexion();
        conexion = cnx.getConexion();
    }
    
    @Override
    public int registrarCabecera(CabeceraVenta cvent) {
        PreparedStatement ps;
        ResultSet rs = null; 
        int idGenerado = 0;

        try {

            ps = conexion.prepareStatement(
                "INSERT INTO tb_cabecera_venta (cab_cantidad, cab_fecha, cli_id) VALUES (?, ?, ?)",
                PreparedStatement.RETURN_GENERATED_KEYS 
            );

            ps.setInt(1, cvent.getCab_cantidad());
            ps.setDate(2, new java.sql.Date(cvent.getCab_fecha().getTime()));
            ps.setInt(3, cvent.getCli_id());


            ps.executeUpdate(); 


            rs = ps.getGeneratedKeys(); 
            if (rs.next()) {
                idGenerado = rs.getInt(1); 
            }

        } catch (SQLException e) {
            System.out.println(e.toString());
            return 0; 
        } finally {

            try {
                if (rs != null) {
                    rs.close();
                }
            } catch (SQLException e) {
                System.out.println(e.toString());
            }
        }

        return idGenerado; 
    }
}
