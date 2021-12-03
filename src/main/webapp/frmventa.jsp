<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

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
            <h1>Formulario de Ventas</h1>
            <jsp:include page="WEB-INF/menu.jsp">
                <jsp:param name="opcion" value="ventas"/>
            </jsp:include>
            <br>
            <form action="VentaControlador" method="post">
                <input type="hidden" name="id" value="${venta.id}" >

                <div class="mb-3">
                    <label for="" class="form-label">Cliente:</label>
                    <!--<input type="text" class="form-control" name="cli_id" value="${venta.cli_id}" placeholder="Esc22riba id del Producto">   -->    
                    <select name="cli_id" class="form-control">
                        <option value="">-- Selecinoar opcion --</option>
                        <c:forEach var="item" items="${lista_clientes}">
                            <option value="${item.id}" 
                                    <c:if test="${venta.cli_id ==item.id}">
                                        selected
                                    </c:if> 
                                    >${item.nombre}</option>
                        </c:forEach>
                    </select>
                </div>

                <div class="mb-3">
                    <label for="" class="form-label">Producto</label>
                    <select name="prod_id" class="form-control">
                        <option value="">-- Selecinoar opcion --</option>
                        <c:forEach var="item" items="${lista_productos}">
                            <option value="${item.id}"
                                    <c:if test="${venta.prod_id ==item.id}">
                                        selected
                                    </c:if> 
                                    >${item.nombre}</option>
                        </c:forEach>
                    </select>

                </div>

                <div class="mb-3">
                    <label for="" class="form-label">Cantidad</label>
                    <input type="text" class="form-control" name="cantidad" value="${venta.cantidad}" placeholder="Ingrese Cantidad">       
                </div>
                <div class="mb-3">
                    <label for="" class="form-label">Precio</label>
                    <input type="text" class="form-control" name="precio" value="${venta.precio}" placeholder="Escriba id del Producto">       
                </div>
                <div class="mb-3">
                    <label for="" class="form-label">Fecha</label>
                    <input type="date" class="form-control" name="fecha" value="${venta.fecha}" placeholder="Escriba la fehca">       
                </div>

                <button type="submit" class="btn btn-primary">Enviar</button>
            </form>


        </div>

        <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.1.3/dist/js/bootstrap.bundle.min.js" integrity="sha384-ka7Sk0Gln4gmtz2MlQnikT1wXgYsOg+OMhuP+IlRH9sENBO0LRn5q+8nbTov4+1p" crossorigin="anonymous"></script>
    </body>
</html>
