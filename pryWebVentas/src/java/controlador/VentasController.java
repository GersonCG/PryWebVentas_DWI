package controlador;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import modelo.CabeceraVenta;
import modelo.CabeceraVentaDAO;
import modelo.Clientes;
import modelo.ClientesDAO;
import modelo.DetalleVenta;
import modelo.DetalleVentaDAO;
import modelo.ProductosDAO;
import modelo.Productos;
import modelo.Ventas;
import modelo.VentasDAO;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

/**
 *
 * @author gerson
 */
@WebServlet(name = "VentasController", urlPatterns = {"/VentasController"})
public class VentasController extends HttpServlet {

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        try {

            ProductosDAO proDao = new ProductosDAO();
            ClientesDAO clienteDao = new ClientesDAO();

            String accion;
            RequestDispatcher rd = null;
            accion = request.getParameter("accion");

            if (accion == null || accion.isEmpty()) {
                rd = request.getRequestDispatcher("ventas/index.jsp");
                List<Productos> listaProductos = proDao.listarProductos();
                request.setAttribute("listaProductos", listaProductos);

                List<Clientes> listarClientes = clienteDao.listarClientes();
                request.setAttribute("listarClientes", listarClientes);

            } else {

                rd = request.getRequestDispatcher("ventas/index.jsp");
                List<Productos> listaProductos = proDao.listarProductos();
                request.setAttribute("listaProductos", listaProductos);

                List<Clientes> listarClientes = clienteDao.listarClientes();
                request.setAttribute("listarClientes", listarClientes);

            }


            if ("obtenerDatosProducto".equals(accion)) {
                int idProducto = Integer.parseInt(request.getParameter("id"));
                Productos producto = proDao.mostrarProducto(idProducto);
                response.setContentType("application/json");
                response.getWriter().write("{\"stock\":" + producto.getCantidad() + ", \"precio\":" + producto.getPrecio() + "}");

                return; 
            }

            rd.forward(request, response);

        } catch (ClassNotFoundException | ServletException | IOException ex) {
            Logger.getLogger(ProductosController.class.getName()).log(Level.SEVERE, null, ex);
        }


        StringBuilder sb = new StringBuilder();
        String line;
        try (BufferedReader reader = request.getReader()) {
            while ((line = reader.readLine()) != null) {
                sb.append(line);
            }
        }


        try {
            JSONObject jsonRequest = new JSONObject(sb.toString());
            int idCliente = jsonRequest.getInt("idCliente");
            String fechaGuia = jsonRequest.getString("fechaGuia");
            JSONArray productosArray = jsonRequest.getJSONArray("productos");
            
            // ------------------------ CNUNJA DATOS AGREGADOS ------------------------
            VentasDAO ventDao = new VentasDAO();
            String accion;
            RequestDispatcher rd = null;
            accion = request.getParameter("accion");
            // ------------------------ CNUNJA DATOS AGREGADOS ------------------------
            
            // Registrar cabecera de la venta
            CabeceraVentaDAO cabeceraVentaDAO = new CabeceraVentaDAO();
            CabeceraVenta cabeceraVenta = new CabeceraVenta();
            cabeceraVenta.setCli_id(idCliente);
            cabeceraVenta.setCab_cantidad(productosArray.length()); 
            cabeceraVenta.setCab_fecha(new java.util.Date());


            int cabeceraRegistrada = cabeceraVentaDAO.registrarCabecera(cabeceraVenta);
            
            if (cabeceraRegistrada > 0) {

                DetalleVentaDAO detalleVentaDAO = new DetalleVentaDAO();

                for (int i = 0; i < productosArray.length(); i++) {
                    JSONObject producto = productosArray.getJSONObject(i);
                    int idProducto = producto.getInt("idProducto");
                    int cantidad = producto.getInt("cantidad");
                    double precioUnitario = producto.getDouble("precioUnitario");
                    double total = producto.getDouble("total");


                    DetalleVenta detalleVenta = new DetalleVenta();
                    detalleVenta.setDet_cantidad(cantidad);
                    detalleVenta.setDet_precio(precioUnitario);
                    detalleVenta.setDet_total(total);
                    detalleVenta.setPrd_id(idProducto);
                    detalleVenta.setCab_id(cabeceraRegistrada); 

                    
                    detalleVentaDAO.registrarVenta(detalleVenta);

                    boolean stockActualizado = detalleVentaDAO.actualizarStock(idProducto, cantidad);
                    if (!stockActualizado) {
                        response.getWriter().write("{\"status\":\"error\", \"message\":\"Error al actualizar el stock del producto\"}");
                        return; 
                    }
                }


                response.getWriter().write("{\"status\":\"success\", \"message\":\"Pedido procesado correctamente\"}");
            
                
            // ------------------------ CNUNJA DATOS AGREGADOS ------------------------    
            } else if (accion.equals("procesar")) {
                
                rd = request.getRequestDispatcher("ventas/impresion.jsp");
                List<Ventas> listarVentas = ventDao.listarVentas();
                request.setAttribute("listarVentas", listarVentas);
                
            // ------------------------ CNUNJA DATOS AGREGADOS ------------------------
                
            } else {
                response.getWriter().write("{\"status\":\"error\", \"message\":\"Error al registrar cabecera de venta\"}");    
                    }

        } catch (JSONException e) {
            response.getWriter().write("{\"status\":\"error\", \"message\":\"Error al procesar los datos\"}");
            e.printStackTrace();
        } catch (Exception e) {
            response.getWriter().write("{\"status\":\"error\", \"message\":\"Error inesperado\"}");
            e.printStackTrace();
        }

    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        doGet(request, response);

    }

}
