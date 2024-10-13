<%-- 
    Document   : impresion
    Created on : Oct 12, 2024, 12:12:03 PM
    Author     : Cesar
--%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="c"  uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Boleta de Venta</title>
    
</head>
<body>

<div class="boleta">
    <div class="cabecera">
        <div class="izquierda">
            <h1>Título</h1>
            <h3>Subtítulo</h3>
            <p>Dirección: Calle Ficticia 123</p>
            <p>Teléfono: (01) 234-5678</p>
        </div>
        
        <form name="frmMod" action="VentasController?accion=procesar" method="post" autocomplete="off" class="row g-3">
        
        <div class="derecha">
            <p>RUC: 12345678901</p>
            <h3>Boleta de Venta</h3>
            <p>N°: <input type="text" name="numero_boleta" /></p>
                <c:forEach var="pro" items="${listarVentas}">
                <label for="txtCabFecha" class="form-label">Cabecera Fecha</label>
                <input type="text" class="form-control" id="txtCabFecha" name="txtCabFecha" value="<c:out value='${vent.cab_fecha}' />" readonly>
            </c:forEach>
        

        </div>
        </form>
    </div>

    <div class="contenido">
        <p>Cliente: <input type="text" name="cliente" /></p>
        <p>DNI: <input type="text" name="dni" /></p>

        <table class="tabla">
            <tr>
                <th>Cabecera</th>
                <th>Producto</th>
                <th>Precio Unitario</th>
                <th>Total</th>
                <th>Total</th>
                <th>Total</th>
                <th>Total</th>
            </tr>
            <% for(int i = 0; i < 8; i++) { %>
            <tr>
                <td>

                </td>
                <td>
                </td>
                <td>

                </td>
                <td>

                </td>
            </tr>
            <% } %>
        </table>

        <div class="total">
            <div class="izquierda">
                <p>Total Costo: <input type="text" name="total_costo" /></p>
            </div>
        </div>
    </div>
</div>

</body>
</html>

