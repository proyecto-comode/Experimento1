/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package comode.items;

import comode.common.COMoDEconstants;
import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author Daniel
 */
public class XperFinal extends HttpServlet {
    String title = "¡Gracias!";
    /** 
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code> methods.
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        PrintWriter out = response.getWriter();
        try {
            
        out.println("<html>");
        out.println("<body>");
        out.println("<head>");
        out.println("<link rel=\"stylesheet\" type=\"text/css\" href="+COMoDEconstants.CSS_PATH+"styles_1.css />");
        out.println("<link href=\"http://ajax.googleapis.com/ajax/libs/jqueryui/1.8/themes/base/jquery-ui.css\" rel=\"stylesheet\" type=\"text/css\"/>");
        out.println("<script src=\"http://ajax.googleapis.com/ajax/libs/jquery/1.5/jquery.min.js\"></script>");
        out.println("<script src=\"http://ajax.googleapis.com/ajax/libs/jqueryui/1.8/jquery-ui.min.js\"></script>");
        out.println("<title>" + title +"</title>");
        out.println("</head>");

        //Page header, using the project's style sheet
        out.println("<div class=\"section\" id=\"page\">");
        out.println("<div class=\"header\">");
        out.println("<img src="+COMoDEconstants.IMAGES_PATH+"COMoDELogo.png width=\"170\" height=\"100\" alt=\"COMoDE Logo\" />");
        out.println("<h2>Context Ontology for Mobile Devices</h2>");
            out.println("<div class=\"nav clear\" id=\"sessionemail\">");
            out.println("<ul>");
                // shows the user's email all the session
                out.println("<li>- sesión terminada -</li>");
                //out.println("<li>"+UEmail+"</li>");
            out.println("</ul>");
            out.println("</div>");
            
        out.println("</div>");
        out.println("<div class=\"section\" id=\"articles\">");
        //out.println("<div class=\"line\"></div> ");
        out.println("</div>");
        
        //"Article" class for the item presentation
        out.println("<div class=\"article\" id=\"article1\">");
        out.println("<h3>" + title + "</h3>");
        out.println("<div class=\"line\"></div>");
        //This div will contain all the dianmic elements in the experiment
        out.println("<div class=\"articleBody clear\" id=\"maintext\">");
        
            //Thank you message
            out.println("<p>Acabas de terminar tu participación en el experimento del proyecto COMoDE, muchas gracias por tu esfuerzo y tiempo dedicados, tu valioso apoyo nos ayudará a mejorar este proyecto.</p>");
            out.println("<p>Si deseas conocer más sobre el proyecto o información relacionadas puedes escribirnos a: vdaniel@cusur.udg.mx</p>");
            out.println("<p align='right'> Daniel Aréchiga</p>");
            out.println("<p></p>");
            out.println("<p align='center'><a href="+COMoDEconstants.ROOT_PATH+"index.html>COMoDE Home</a></p>");

        out.println("</div>");
        out.println("</div>");
        
        out.println("</body>");
        out.println("</html>");
        } finally {            
            out.close();
        }
    }

    // <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the + sign on the left to edit the code.">
    /** 
     * Handles the HTTP <code>GET</code> method.
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    /** 
     * Handles the HTTP <code>POST</code> method.
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    /** 
     * Returns a short description of the servlet.
     * @return a String containing servlet description
     */
    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>
}
