<%@page import="com.emergentes.Persona"%>
<%@page import="java.util.ArrayList"%>
<%-- 
control+shift+fecha abajo ->para copiar en la misma altura
--%>
<%
ArrayList<Persona> lista=(ArrayList<Persona>)session.getAttribute("listaper");


%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
     <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
          <link  href="css/estilo.css" type="text/css" rel="stylesheet" > 
        <title>Registro</title
    </head>
    
    <body>
         <h1>Listado de personas</h1>
        <div>
       
        <a id="nuevo" href="MainController?op=nuevo">Nuevo registro</a>
        <table border="1">
            <tr>
                <th>Id</th>
                <th>Nombres</th>
                <th>Apellidos</th>
                <th>Edad</th>
                <th></th>
                <th></th>
           </tr>
            <%
               if(lista!=null)
               {
                   for(Persona p:lista){
            %>
           <tr>
               <td><%=p.getId()%></td>
               <td><%=p.getNombres()%></td>
               <td><%=p.getApellidos()%></td>
               <td><%=p.getEdad()%></td>
               <td><a href="MainController?op=editar&id=<%=p.getId()%>">Moficar</a></td>
               <td><a href="MainController?op=eliminar&id=<%=p.getId()%>"
             onclick="return confirm('Esta seguro de eliminar el registro?');">Eliminar</a></td>
               
           </tr>
           <%
               }
              }
           %>
        </table>
       </div> 
  </body>
 
</html>
