<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%
    if (session.getAttribute("login") != "OK") {
        response.sendRedirect("login.jsp");
    }
%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!doctype html>
<html lang="es">
    <head>
        <!-- Required meta tags -->
        <meta charset="utf-8">
        <meta name="viewport" content="width=device-width, initial-scale=1">
        <link href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/6.0.0-beta3/css/all.min.css" rel="stylesheet">
        <!-- Bootstrap CSS -->
        <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.1.3/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-1BmE4kWBq78iYhFldvKuhfTAU6auU8tT94WrHftjDbrCEXSU1oBoqyl2QvZ6jIW3" crossorigin="anonymous">

        <title>Principal</title>
    </head>
    <body>
        <div class="container">
            <h1>Categorias</h1>
            <jsp:include page="WEB-INF/menu.jsp">
                <jsp:param name="opcion" value="categorias"/>
            </jsp:include>
            <a href="CategoriaControlador?action=add" class="btn btn-primary btn-sm"><i class="fas fa-plus-circle"></i> Nuevo</a>
            <a href="Logout" class="btn btn-danger btn-sm" align="right"><i class="far fa-window-close"></i> Cerrar Sesion</a>
            <table class="table table-dark table-hover">
                <tr>

                    <th>Id</th>
                    <th>Nombre</th>
                    <th colspan="2">Acciones</th>
                </tr>
                <c:forEach var="item" items="${categorias}">
                    <tr>
                        <td>${item.id}</td>
                        <td>${item.nombre}</td>
                        <td><a href="CategoriaControlador?action=edit&id=${item.id}"><i class="fas fa-edit"></i></a></td>
                        <td><a href="CategoriaControlador?action=delete&id=${item.id}" onclick="return(confirm('Esta seguro de eliminar el registro ??'))">
                                <i class="fas fa-trash-alt"></i>
                            </a>
                        </td>
                    </tr>
                </c:forEach>
            </table>            
        </div>

        <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.1.3/dist/js/bootstrap.bundle.min.js" integrity="sha384-ka7Sk0Gln4gmtz2MlQnikT1wXgYsOg+OMhuP+IlRH9sENBO0LRn5q+8nbTov4+1p" crossorigin="anonymous"></script>
    </body>
</html>

