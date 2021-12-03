package com.emergentes.controlador;

import com.emergentes.dao.ClienteDAO;
import com.emergentes.dao.ClienteDAOimpl;
import com.emergentes.dao.ProductoDAO;
import com.emergentes.dao.ProductoDAOimpl;
import com.emergentes.dao.VentaDAO;
import com.emergentes.dao.VentaDAOimpl;
import com.emergentes.modelo.Cliente;
import com.emergentes.modelo.Producto;
import com.emergentes.modelo.Venta;
import java.io.IOException;
import java.sql.Date;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet(name = "VentaControlador", urlPatterns = {"/VentaControlador"})
public class VentaControlador extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        System.out.println("ventas Agus");
        try {
            VentaDAO dao = new VentaDAOimpl();
            ProductoDAO daoProducto = new ProductoDAOimpl();
            ClienteDAO daoCliente = new ClienteDAOimpl();
            int id;
            List<Producto> lista_productos = null;
            List<Cliente> lista_clientes = null;
            Venta venta = new Venta();
            String action = (request.getParameter("action") != null) ? request.getParameter("action") : "view";

            switch (action) {
                case "add":
                    //System.out.println("ventasssssssssssss");
                    lista_productos = daoProducto.getAll();
                    lista_clientes = daoCliente.getAll();
                    request.setAttribute("lista_productos", lista_productos);
                    request.setAttribute("lista_clientes", lista_clientes);
                    //////////////////////////
                    
                    request.setAttribute("venta", venta);
                    request.getRequestDispatcher("frmventa.jsp").forward(request, response);
                    break;
                case "edit":
                    System.out.println("Edicionnnnnnnnn");
                    id = Integer.parseInt(request.getParameter("id"));
                    venta = dao.getById(id);

                    //para mostarr en un select las opciones de producto
                    lista_productos = daoProducto.getAll();
                    lista_clientes = daoCliente.getAll();
                    request.setAttribute("lista_productos", lista_productos);
                    request.setAttribute("lista_clientes", lista_clientes);
                    ////////////////////////
                    
                    request.setAttribute("venta", venta);
                    request.getRequestDispatcher("frmventa.jsp").forward(request, response);
                    break;
                case "delete":
                    id = Integer.parseInt(request.getParameter("id"));
                    dao.delete(id);
                    response.sendRedirect("VentaControlador");
                    break;
                case "view":
                    List<Venta> lista = dao.getAll();
                    request.setAttribute("ventas", lista);
                    request.getRequestDispatcher("ventas.jsp").forward(request, response);
                    break;
            }
        } catch (Exception ex) {
            System.out.println("Error " + ex.getMessage());
        }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        System.out.println("Do GETTTTTTTTTTTTTTTTTTTTTTT");
        int id = Integer.parseInt(request.getParameter("id"));
        int cli_id = Integer.parseInt(request.getParameter("cli_id"));
        int prod_id = Integer.parseInt(request.getParameter("prod_id"));
        int cantidad = Integer.parseInt(request.getParameter("cantidad"));
        float precio = Float.parseFloat(request.getParameter("precio"));
        String fecha = request.getParameter("fecha");

        System.out.println(id + "cli = " + cli_id + "pro = " + prod_id + "cant = " + cantidad + "precio = " + precio + fecha);

        Venta venta = new Venta();

        venta.setId(id);
        venta.setProd_id(prod_id);
        venta.setCli_id(cli_id);
        venta.setCantidad(cantidad);
        venta.setPrecio(precio);
        venta.setFecha(convierteFecha(fecha));

        if (id == 0) {
            //nuevo
            VentaDAO dao = new VentaDAOimpl();
            try {
                dao.insert(venta);
                response.sendRedirect("VentaControlador");
            } catch (Exception ex) {
                Logger.getLogger(VentaControlador.class.getName()).log(Level.SEVERE, null, ex);
            }
        } else {
            //editar
            VentaDAO dao = new VentaDAOimpl();
            try {
                dao.update(venta);
                response.sendRedirect("VentaControlador");
            } catch (Exception ex) {
                Logger.getLogger(VentaControlador.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }

    public Date convierteFecha(String fecha) {
        Date fechaDB = null;
        SimpleDateFormat formato = new SimpleDateFormat("yyyy-MM-dd");

        java.util.Date fechaTMP;
        try {
            fechaTMP = formato.parse(fecha);
            fechaDB = new Date(fechaTMP.getTime());
        } catch (ParseException ex) {
            Logger.getLogger(VentaControlador.class.getName()).log(Level.SEVERE, null, ex);
        }
        return fechaDB;
    }

}
