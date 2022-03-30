package com.emergentes;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
@WebServlet(name = "MainController", urlPatterns = {"/MainController"})
public class MainController extends HttpServlet {
  
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
   HttpSession ses=request.getSession();     
        if(ses.getAttribute("listaper")==null){
          ArrayList<Persona> listaaux=new ArrayList<Persona>();  
          ses.setAttribute("listaper", listaaux);
        }
   ArrayList<Persona> lista=  (ArrayList<Persona>)ses.getAttribute("listaper");
   int id,pos;
   String op=request.getParameter("op");
   String opcion= (op!=null)?op : "view"; 
   Persona p=new Persona();
switch (opcion){
    case "nuevo" : 
        request.setAttribute("miPersona", p);
        request.getRequestDispatcher("editar.jsp").forward(request,response);
        break;
    case "editar" :  
      id=Integer.parseInt(request.getParameter("id"));
      pos=buscarIndice(request,id);  
      p=lista.get(pos);
      request.setAttribute("miPersona",p);
      request.getRequestDispatcher("editar.jsp").forward(request, response);
      break;
    case "eliminar":
      id=Integer.parseInt(request.getParameter("id"));
      pos=buscarIndice(request,id);  
      lista.remove(pos);
      ses.setAttribute("listaper", lista);
      response.sendRedirect("index.jsp");
      break;
    case "view":response.sendRedirect("index.jsp");
    break;
     }
   
    }
 @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        HttpSession ses=request.getSession();
        ArrayList<Persona> lista=(ArrayList<Persona>)ses.getAttribute("listaper");
        Persona p=new Persona();
        p.setId(Integer.parseInt(request.getParameter("id")));
        p.setNombres(request.getParameter("nombres"));
        p.setApellidos(request.getParameter("apellidos"));
        p.setEdad(Integer.parseInt(request.getParameter("edad")));
        
       int id=p.getId();
       if(id==0){
           //nuevo
           //Actualizar el ultimo id
           int ultId;
          ultId=UltimoId(request);
          p.setId(ultId);
           lista.add(p);
       }else{
           //edicion
           lista.set(buscarIndice(request,id),p);
       }
       ses.setAttribute("listaper", lista);
       response.sendRedirect("index.jsp");
    }

   private int buscarIndice(HttpServletRequest request,int id){
    HttpSession ses=request.getSession();
      ArrayList<Persona> lista=( ArrayList<Persona>) ses.getAttribute("listaper");
       int i=0;
       
        if(lista.size() >0){
            while(i< lista.size()){
                if(lista.get(i).getId() ==id){
                    break;
                }else {
                    i++;
                }
            }
        }
   return i;    
   }
   
   private int UltimoId(HttpServletRequest request){
    HttpSession ses=request.getSession();
      ArrayList<Persona> lista=( ArrayList<Persona>) ses.getAttribute("listaper");    
     
       int idaux=0;
       for(Persona item:lista){
          idaux=item.getId(); 
           
       }
       
   return idaux+1;    
   }
   
}//class
