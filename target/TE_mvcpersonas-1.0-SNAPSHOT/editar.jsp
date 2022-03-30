<%@page import="com.emergentes.Persona"%>
<%-- 
hidden -> significa un valor oculto (un input oculto)
--%>
<%
Persona p=(Persona)request.getAttribute("miPersona");
%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
         <link  href="css/estilo.css" type="text/css" rel="stylesheet" > 
        <title>Formulario</title>
    </head>
    <body id="form">
      
        
        <h1><%= (p.getId()==0) ? "Nuevo Registro":"Editar Registro"%></h1>
          <div >
        <form action="MainController" method="POST">
            <input type="hidden" name="id" value="<%=p.getId()%>">
            <table>
                <tr>
                    <td>Nombre</td>
                    <td><input type="text" name="nombres" value="<%=p.getNombres()%>"></td>
                </tr>
                <tr>
                    <td>Apellidos</td>
                    <td><input type="text" name="apellidos" value="<%=p.getApellidos()%>"></td>
                </tr>
                <tr>
                    <td>Edad</td>
                    <td><input type="number" name="edad" value="<%=p.getEdad()%>"></td>
                </tr>
            </table> 
            <tr>
                    <td></td>
                      <td><input type="submit" value="Enviar"></td>
                </tr>
            
        </form>
        
     </div>   
        
    </body>
</html>
