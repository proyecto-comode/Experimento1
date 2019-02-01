package comode.items;
//** Daniel Aréchiga 2011
//** Experiment's control servlet
import comode.common.COMoDEconstants;
import comode.session.SessionControl;
import java.io.*;
import java.io.FileWriter.*;
import java.sql.SQLException;
import java.text.*;
//import java.awt.*;
import java.util.*;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.*;
import javax.servlet.http.*;
import comode.common.COMoDEconstants.*;
import util.HTMLFilter;
//import comode.wordrecom.*;

public class XperControl extends HttpServlet {
    
    public void doGet(HttpServletRequest request,
                      HttpServletResponse response)
        throws IOException, ServletException
    {
        //Creation of the item experiment page, will show the user the item
        response.setContentType("text/html");
        PrintWriter out = response.getWriter();

        // Identifying and initializing the session
        HttpSession session = request.getSession(true);
        
        // Is it the initial call?
        if (request.getParameter("emailField") != null){
            // First servlet call, create the session identifiers
            SessionControl initSession = new SessionControl();
            initSession.sessionInit(request, session);
        } else 
            // Is it a page reload?
            if (request.getParameter("EItem1")!=null) 
            {
            // Servlet recall, store the received info in the session attributes...
            SessionControl itemSave = new SessionControl();
            itemSave.saveItemData(request, session);            
            // Validate when the limit is reached or increase item counter
            if (Integer.parseInt(session.getAttribute("SactualItem").toString()) < COMoDEconstants.MAX_ITEMS) {
                // increase counter, we're in a "between" item
                session.setAttribute("SactualItem",Integer.parseInt(session.getAttribute("SactualItem").toString()) + 1);
                }
            else {
                // That was the final item, now save data and close session to 
                // allow multiple users in the same computer/browser
                
                // Saving the data in the file
                SessionControl savingData = new SessionControl();
                savingData.saveSessionData(session);

                // redirect to the thank you page
                RequestDispatcher rd = request.getRequestDispatcher("XperFinal"); 
                rd.forward(request,response);
                
                //String urlWithSessionID = response.encodeRedirectURL("XperFinal");
                //response.sendRedirect( urlWithSessionID );

            }
        }
        
        // Some variables...
        String title = "Elemento "+Integer.parseInt(session.getAttribute("SactualItem").toString());
        //String instructions = "Here will be the "+Integer.parseInt(session.getAttribute("SactualItem").toString())+" item instructions.</p>";

        // Initial HTML tags and style sheet definition
        out.println("<html>");
        out.println("<body>");
        out.println("<head>");
        if ("mobile".equals(session.getAttribute("ClientType").toString())){
            out.println("<link rel=\"stylesheet\" type=\"text/css\" href="+COMoDEconstants.CSS_PATH+COMoDEconstants.COMoDE_MOBILESTYLE+" />");
        } else {
            out.println("<link rel=\"stylesheet\" type=\"text/css\" href="+COMoDEconstants.CSS_PATH+COMoDEconstants.COMoDE_STYLE+" />");
        }
        out.println("<link href=\"http://ajax.googleapis.com/ajax/libs/jqueryui/1.8/themes/base/jquery-ui.css\" rel=\"stylesheet\" type=\"text/css\"/>");
        out.println("<link href=\"/experiment/favicon.ico\" type=\"image/x-icon\" rel=\"shortcut icon\" />");
        out.println("<script src=\"http://ajax.googleapis.com/ajax/libs/jquery/1.5/jquery.min.js\"></script>");
        out.println("<script src=\"http://ajax.googleapis.com/ajax/libs/jqueryui/1.8/jquery-ui.min.js\"></script>");
        out.println("<title> COMoDE Experiment " + title +"/"+ COMoDEconstants.MAX_ITEMS +"</title>");
        out.println("</head>");

        //Page header, using the project's style sheet
        out.println("<div class=\"section\" id=\"page\">");
        out.println("<div class=\"header\">");
        out.println("<img src="+COMoDEconstants.IMAGES_PATH+"COMoDELogo.png width=\"170\" height=\"100\" alt=\"COMoDE Logo\" />");
        out.println("<h2>Context Ontology for Mobile Devices</h2>");
            out.println("<div class=\"nav clear\" id=\"sessionemail\">");
            out.println("<ul>");
                // shows the user's email all the session
                out.println("<li>"+session.getAttribute("SUEMail")+"</li>");
            out.println("</ul>");
            out.println("</div>");
            //@to-do: eliminate this session data displaying fields
            out.println(session.getAttribute("SDate"));
            out.println("conectado desde: "+session.getAttribute("SIPAddress"));
            
        out.println("</div>");
        out.println("<div class=\"section\" id=\"articles\">");
        out.println("</div>");
        
        //"Article" class for the item presentation
        out.println("<div class=\"article\" id=\"article1\">");
        
        out.println("<table width='100%' border='0'><tr><td>");
        out.println("<h3>" + title +"/"+ COMoDEconstants.MAX_ITEMS +"</h3>");
        
        out.println("</td><td><table border='0' align='right'>"
                +"<tr><td align='center' colspan='3'>Tipo de Recomendación de Palabras</td></tr><tr>"
                +"<td id='cell1' align='center' bordercolor='#333333' bgcolor='#755800'>&nbsp;Sin RP&nbsp;</td>"
                +"<td id='cell2' align='center' bordercolor='#333333' bgcolor='#755800'>&nbsp;RP Simple&nbsp;</td>"
                +"<td id='cell3' align='center' bordercolor='#333333' bgcolor='#755800'>&nbsp;RP basada en contexto&nbsp;</td>"
                +"</tr></table></td></tr></table>");
        out.println("<script type='text/javascript'>");
        switch (Integer.parseInt(COMoDEconstants.ITM_TYPE[Integer.parseInt(session.getAttribute("SactualItem").toString())-1])){
            case 0: out.println("document.getElementById('cell1').style.backgroundColor = '#009124' ") ; break;
            case 1: out.println("document.getElementById('cell2').style.backgroundColor = '#009124' "); break;
            case 2: out.println("document.getElementById('cell3').style.backgroundColor = '#009124' "); break;
            }
        out.println("</script>");
        
        
        out.println("<div class=\"line\"></div>");
        //This div will contain all the dianmic elements in the experiment
        out.println("<div class=\"articleBody clear\" id=\"maintext\">");
        
           //Randomize!!
           XperItemSelect Item = new XperItemSelect();
           List<String> SelectedItem = null;
           try {
               SelectedItem = Item.ItemSelect(session);
           } catch (SQLException ex) {
               Logger.getLogger(XperControl.class.getName()).log(Level.SEVERE, null, ex);
           }
           session.setAttribute("StringTitle",SelectedItem.get(1));
           session.setAttribute("StringItem",SelectedItem.get(2));
           session.setAttribute("StringExp", SelectedItem.get(3));
           // @to-do: erase the item and the ontology experiment ID 
           //out.println("DBItem: "+SelectedItem.get(0)+" exp"+SelectedItem.get(3));
           
           //Calling the item jsp
            out.println("<p>"+COMoDEconstants.COMoDE_StrItemIns+"</p>");
            out.println("<p><i>"+session.getAttribute("StringTitle") +"</i></p>");
            out.println("<center><p>"+COMoDEconstants.COMoDE_StrItemQuery+": <b>"+session.getAttribute("StringItem") +"</b></p></center>");
            // Inserts the JSP
            RequestDispatcher dispatcher = getServletContext().getRequestDispatcher("/jsp/item.jsp");
            if (dispatcher != null){
                dispatcher.include(request, response);
            }
        out.println("</div>");
        out.println("</div>");
        out.println("</body>");
        out.println("</html>");
    }

    public void doPost(HttpServletRequest request,
                      HttpServletResponse response)
        throws IOException, ServletException
    {
        doGet(request, response);
    }

}