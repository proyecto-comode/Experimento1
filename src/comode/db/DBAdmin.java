/*
 * Administrates the database interactions
 * Daniel Aréchiga 2011
 */
package comode.db;

import comode.common.COMoDEconstants;
import java.io.IOException;
import java.sql.*;
//import com.mysql.jdbc.*;
import java.util.*;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.http.HttpServletResponse;
import java.io.PrintWriter;
import java.sql.DriverManager;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.ResultSet;
import javax.servlet.http.HttpSession;


public class DBAdmin {
    
    // Inserts in the DB the results for an item, obtained form the session 
    public void DBResultsInsert(HttpSession session) throws IOException, SQLException{
        Connection conn = null;
        int i = 1;
        
        String SQLquery = "INSERT INTO "+ COMoDEconstants.DB_RESULTS_NAME +" (UEMail, UIPAddress, UADate";
        for (i = 1; i< COMoDEconstants.MAX_ITEMS+1; i++){
            SQLquery = SQLquery +","+
                          "KSPQ"+i+","+
                          "IEval"+i+"_0,"+
                          "IEval"+i+"_1,"+
                          "IEval"+i+"_2,"+
                          "IEval"+i+"_3"; //each result
        }
        SQLquery = SQLquery+",ClientType) VALUES('"+session.getAttribute("SUEMail") +"','"+session.getAttribute("SIPAddress") +"','"+session.getAttribute("SDate")+"'";
        for (i = 1; i< COMoDEconstants.MAX_ITEMS+1; i++){
                          SQLquery = SQLquery +","+
                          session.getAttribute("KSPQ"+i) +","+
                          session.getAttribute("ItemNumber"+i) +","+
                          session.getAttribute("ItemEval"+i); //each result
        }
        
        SQLquery = SQLquery+",'"+session.getAttribute("ClientType")+"')";
        System.out.println("Trying select command: "+SQLquery);
        try {
            Class.forName(COMoDEconstants.DB_DRIVER);
            // creates a connection with the DB
            conn = DriverManager.getConnection(COMoDEconstants.DB_URL,COMoDEconstants.DB_USR,COMoDEconstants.DB_PWD);
            System.out.println("trying to open database: "+conn.getCatalog());
            // Prepares the statement to save
            PreparedStatement st = conn.prepareStatement(SQLquery);
            st.executeUpdate();
        } catch (Exception e){
             System.out.println("<p>Got an exception: "); 
             System.out.println(e.getMessage()+"</p>"); 
        } finally {
            // Closes the DB
            conn.close();
        }
    }
    
    // Returns the row contents for the "i" element
    public List DBItemGet(int i) throws SQLException, IOException {
        Connection conn = null;
        ResultSet rs = null;
        List<String> ls=new ArrayList<String>();
        
        String query = "SELECT * FROM "+COMoDEconstants.DB_ITEMS_NAME+" WHERE ID_Item='" +Integer.toString(i)+"'";
        System.out.println("D@N: Searching for: "+ query);
        try {
            try {
                Class.forName(COMoDEconstants.DB_DRIVER);
            } catch (ClassNotFoundException ex) {
                Logger.getLogger(DBAdmin.class.getName()).log(Level.SEVERE, null, ex);
                System.out.println("D@N: DB connection error: "+Level.SEVERE);
            }
            // creates a connection with the DB
            conn = DriverManager.getConnection(COMoDEconstants.DB_URL,COMoDEconstants.DB_USR,COMoDEconstants.DB_PWD);

            // Prepares the SQL statement
            PreparedStatement st = conn.prepareStatement(query);
            
            try {
                if (st.execute()) {
                    rs = st.getResultSet();
                    rs.next();
                    ls.add(Integer.toString(rs.getInt(1)));
                    ls.add(rs.getString(2));
                    ls.add(rs.getString(3));
                    ls.add(Integer.toString(rs.getInt(4)));
                } // end If (st.execute)
            }
            catch (SQLException e) {
                System.out.println("D@N: Error in results list (DBAdmin.java)");
                }
        } 
        finally {
            // Closes the DB
            conn.close();
        }
        return ls;
    }
    
        // Shows in the browser the databases contents.
        // Must be called from the DBTest.java file
        public void DBItemsRead(HttpServletResponse response, String DB) throws SQLException, IOException {
        Connection conn = null;
        ResultSet rs = null;
        PrintWriter out = response.getWriter();
        
        String query = "SELECT * FROM "+ DB;
        try {
            try {
                Class.forName(COMoDEconstants.DB_DRIVER);
            } catch (ClassNotFoundException ex) {
                Logger.getLogger(DBAdmin.class.getName()).log(Level.SEVERE, null, ex);
            }
            // creates a connection with the DB
            conn = DriverManager.getConnection(COMoDEconstants.DB_URL,COMoDEconstants.DB_USR,COMoDEconstants.DB_PWD);
            out.println("<p>Database: <b>"+conn.getCatalog()+"</b></p>");
            out.println("<p>Table: <b>"+DB+"</b></p>");
            out.println("<p>URL: <b>"+COMoDEconstants.DB_URL+"</b></p>");
            out.println("<p>Driver: <b>"+COMoDEconstants.DB_DRIVER+"</b></p>");
            // Prepares the SQL statement
            PreparedStatement st = conn.prepareStatement(query);
            
            //Prepares the displaying result's table
            try {
                if (st.execute()) {
                    out.println("<table border=1 align=center><tr>");
                    rs = st.getResultSet();
                    ResultSetMetaData rsmd = st.getMetaData();
                    int numColumns = rsmd.getColumnCount();
                    // Prints headers
                    for (int i=1; i<numColumns+1; i++) {
                        String columnName = rsmd.getColumnName(i);
                        if ((i % 2) == 0) {
                            out.println("<td><font size='2'>"+columnName+"</td>");
                        } else {
                            out.println("<td bgcolor='#aaaaaa'><b><font size='2'>"+columnName+"</b></td>");
                        }
                    } 
                    out.println("</tr>");

                    while(rs.next()){
                        out.println("<tr>");
                        for (int i = 1; i<numColumns+1; i++){
                            if ((i % 2) == 0) {
                                out.println("<td align=center><font size='2'>"+rs.getString(i)+"</td>");
                            } else {
                                out.println("<td align=center bgcolor='#aaaaaa'><b><font size='2'>"+rs.getString(i)+"</b></td>");
                            }
                        }
                        out.println("</tr>");
                    }//end while loop
                    out.println("</table></font>");
                } // end If (st.execute)
                
            }
            catch (SQLException e) {
                }
        } finally {
            // Closes the DB
            conn.close();
        }
    }
    
}
