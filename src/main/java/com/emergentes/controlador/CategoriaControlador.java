package com.emergentes.controlador;

import com.emergentes.dao.CategoriaDAO;
import com.emergentes.dao.CategoriaDAOimpl;
import com.emergentes.modelo.Categoria;
import java.io.IOException;
import java.util.List;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet(name = "CategoriaControlador", urlPatterns = {"/CategoriaControlador"})
public class CategoriaControlador extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
            try {
            Categoria  cat = new Categoria();
            int id;
            CategoriaDAO dao = new CategoriaDAOimpl();
            String action = (request.getParameter("action") != null) ? request.getParameter("action") : "view";

            switch (action) {
                case "add":
                    request.setAttribute("categoria", cat);
                    request.getRequestDispatcher("frmcategoria.jsp").forward(request, response);
                    break;
                case "edit":
                    id = Integer.parseInt(request.getParameter("id")); 
                    cat = dao.getById(id);
                    request.setAttribute("categoria", cat);
                    request.getRequestDispatcher("frmcategoria.jsp").forward(request, response);
                    break;
                case "delete":
                    id = Integer.parseInt(request.getParameter("id")); 
                    dao.delete(id);
                    response.sendRedirect("CategoriaControlador");
                    break;
                case "view":
                    //Obtner la lista de registros
                    List<Categoria> lista = dao.getAll();
                    request.setAttribute("categorias", lista);
                    request.getRequestDispatcher("categorias.jsp").forward(request, response);
                    break;
            }
        } catch (Exception ex) {
            System.out.println("Error " + ex.getMessage());
        }    
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
            int id = Integer.parseInt(request.getParameter("id"));
        
        String nombre = request.getParameter("nombre");
      
        System.out.println(id + nombre);
        Categoria cat = new Categoria();
        
        cat.setId(id);
        cat.setNombre(nombre);

        
        CategoriaDAO dao = new  CategoriaDAOimpl();
        if (id == 0) {
            try {
                //nuevo registro
                dao.insert(cat);
            } catch (Exception ex) {
                //Logger.getLogger(ClienteControlador.class.getName()).log(Level.SEVERE, null, ex);
                System.out.println("Error al insertar "+ ex.getMessage());
            }
        }
        else{
            try {
                //edicion de registro
                dao.update(cat);
            } catch (Exception ex) {
                //Logger.getLogger(ClienteControlador.class.getName()).log(Level.SEVERE, null, ex);
                System.out.println("Error al editar " + ex.getMessage());
            }
        }
        response.sendRedirect("CategoriaControlador");
    }        
                
}
