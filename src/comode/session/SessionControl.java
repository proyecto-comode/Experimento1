/*
 * Class with control of the session data
 * Daniel Aréchiga 2011
 */
package comode.session;

import comode.db.*;
import java.io.*;
import java.util.*;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.http.*;

public class SessionControl {
    
    /**
     * Saves the initial's experiment data to the session handler. The session will
     * contain all the results from the experiment, which will be stored in the DB
     * at the experiment's end.
     * @param request HTTP servlet request instance variable
     * @param session HTTP session instance variable
     */
    public void sessionInit(HttpServletRequest request, HttpSession session){
        Date rightNow = new Date();
        String day = Integer.toString(rightNow.getDate());
        String month = Integer.toString(rightNow.getMonth()+1);
        String year = Integer.toString(rightNow.getYear()+1900);
        String hour = Integer.toString(rightNow.getHours()+1);
        String minute = Integer.toString(rightNow.getMinutes());
        String testDate = day+"/"+month+"/"+year+"-"+hour+":"+minute;
        
        session.setAttribute("SUEMail", request.getParameter("emailField"));
        session.setAttribute("SIPAddress", request.getRemoteAddr());
        session.setAttribute("SDate", testDate);
        session.setAttribute("SactualItem", "1");
        session.setAttribute("ClientType", request.getParameter("clientType"));
    }
    /**
     * Saves the user's submited item data in the session attributes
     * @param request HTTP servlet request instance variable
     * @param session HTTP session instance variable
     */
    public void saveItemData (HttpServletRequest request, HttpSession session){
        session.setAttribute(
                "ItemEval"+session.getAttribute("SactualItem").toString(),
                request.getParameter("EItem1")+","+
                request.getParameter("EItem2")+","+
                request.getParameter("EItem3"));
        session.setAttribute("KSPQ"+session.getAttribute("SactualItem").toString(),request.getParameter("KSPQ"));
    }
    
    /**
     * Stores all the session data (experiment results) in the database.
     * @param session HTTP session instance variable
     * @throws IOException (fingers crossed)
     */
    public void saveSessionData (HttpSession session) throws IOException{
        // Writing to a database
        DBAdmin db = new DBAdmin();
            try {
                db.DBResultsInsert(session);
                
            } catch (SQLException ex) {
                Logger.getLogger(DBTest.class.getName()).log(Level.SEVERE, null, ex);
                System.out.println("Error trying to introducing data in the DB:"+ex);
            }        
    } // SaveSessionData    

} // final class backet
