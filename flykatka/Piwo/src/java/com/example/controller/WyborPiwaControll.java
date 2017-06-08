package com.example.controller;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

import com.example.model.*;
import java.io.IOException;
import java.util.*;
import javax.servlet.*;
import javax.servlet.http.*;


/**
 *
 * @author katarzyna_bialach
 */
public class WyborPiwaControll extends HttpServlet {

   @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
     
     String c = request.getParameter("kolor");
     //out.println("<br>Wybrany kolor piwa: " + c);
     EkspertPiwny be = new EkspertPiwny();
     List wynik = be.getMarki(c);
     
    /* response.setContentType("text/html");
     PrintWriter out = response.getWriter();
     out.println("Porada piwna<br>");
     
     Iterator it = wynik.iterator();
     while (it.hasNext()) {
         out.println("<br>Spróbuj: " + it.next());
     } ta część została przeniesiona do jsp */
     
    request.setAttribute("styles", wynik);
    RequestDispatcher view = request.getRequestDispatcher("wynik.jsp");
    view.forward(request, response);
    }   
}
