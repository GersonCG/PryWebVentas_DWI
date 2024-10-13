package controlador;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

import modelo.Clientes; 
import modelo.ClientesDAO; 

@WebServlet(name = "ClientesController", urlPatterns = {"/ClientesController"})
public class ClientesController extends HttpServlet {

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
    }
    
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        
        try {
            ClientesDAO clienteDao = new ClientesDAO(); 
            String accion;
            RequestDispatcher rd = null;
            accion = request.getParameter("accion");
            
            if (accion == null || accion.isEmpty()) {
                rd = request.getRequestDispatcher("clientes/index.jsp");
                List<Clientes> listaClientes = clienteDao.listarClientes(); 
                request.setAttribute("listaClientes", listaClientes);
                
            } else if (accion.equals("nuevo")) {
                rd = request.getRequestDispatcher("clientes/nuevo.jsp");
                
            } else if (accion.equals("insertar")) {
                
                int idcliente = Integer.parseInt(request.getParameter("txtIDCli"));
                String clnombre = request.getParameter("txtNomCli");
                String clapellido = request.getParameter("txtApeCli");
                String clDNI = request.getParameter("txtDNICli");
                int cltelefono = Integer.parseInt(request.getParameter("txtTelCli"));
                String clcorreo = request.getParameter("txtEmailCli");
                String cldireccion = request.getParameter("txtDireCli");
                
                Clientes cliente = new Clientes(idcliente, clnombre, clapellido,clDNI,cltelefono,clcorreo,cldireccion);  
                clienteDao.insertarCliente(cliente);  
                
                rd = request.getRequestDispatcher("clientes/index.jsp");
                List<Clientes> listaClientes = clienteDao.listarClientes();  
                request.setAttribute("listaClientes", listaClientes);
                
            } else if (accion.equals("modificar")) {
                rd = request.getRequestDispatcher("clientes/modificar.jsp");
                int idCliente = Integer.parseInt(request.getParameter("txtIDCli"));  
                Clientes cliente = clienteDao.mostrarCliente(idCliente);  
                request.setAttribute("cliente", cliente);
                
            } else if (accion.equals("actualizar")) {
                int idcliente = Integer.parseInt(request.getParameter("txtIDCli"));
                String clnombre = request.getParameter("txtNomCli");
                String clapellido = request.getParameter("txtApeCli");
                String clDNI = request.getParameter("txtDNICli");
                int cltelefono = Integer.parseInt(request.getParameter("txtTelCli"));
                String clcorreo = request.getParameter("txtEmailCli");
                String cldireccion = request.getParameter("txtDireCli");
                
                Clientes cliente = new Clientes(idcliente, clnombre, clapellido,clDNI,cltelefono,clcorreo,cldireccion);  
                clienteDao.modificarCliente(cliente);  
                rd = request.getRequestDispatcher("clientes/index.jsp");
                List<Clientes> listaClientes = clienteDao.listarClientes(); 
                request.setAttribute("listaClientes", listaClientes);
                
            } else if (accion.equals("eliminar")) {
                int idCliente = Integer.parseInt(request.getParameter("txtIDCliente"));
                clienteDao.eliminarCliente(idCliente); 
                rd = request.getRequestDispatcher("clientes/index.jsp");
                List<Clientes> listaClientes = clienteDao.listarClientes();  
                request.setAttribute("listaClientes", listaClientes);
                
            } else {
                rd = request.getRequestDispatcher("clientes/index.jsp");
                List<Clientes> listaClientes = clienteDao.listarClientes();  
                request.setAttribute("listaClientes", listaClientes);
            }
            
            rd.forward(request, response);
            
        } catch (ClassNotFoundException | ServletException | IOException ex) {
            Logger.getLogger(ClientesController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        doGet(request, response);
    }
}
