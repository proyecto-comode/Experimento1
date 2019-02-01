/*
 * Servlet to test the db classes
 * Daniel Aréchiga 2011
 */
package comode.db;

import comode.common.COMoDEconstants;
import comode.db.DBAdmin;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class DBTest extends HttpServlet {

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        PrintWriter out = response.getWriter();
        try {
            out.println("<html>");
            out.println("<head>");
            out.println("<title>COMoDE experiment - DB Read Servlet</title>");  
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Displaying COMoDE experiment result's</h1>");
            DBAdmin db = new DBAdmin();
            try {
                db.DBItemsRead(response, COMoDEconstants.DB_RESULTS_NAME);
                out.println("<p><hr></p>");
                db.DBItemsRead(response, COMoDEconstants.DB_ITEMS_NAME);
            } catch (SQLException ex) {
                Logger.getLogger(DBTest.class.getName()).log(Level.SEVERE, null, ex);
                out.println("Error trying to introducing data in the DB:"+ex);
            }
            out.println("<hr><p>Daniel Aréchiga 2012</p>");
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
