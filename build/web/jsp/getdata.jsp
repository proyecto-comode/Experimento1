<%-- 
    Document   : getdata
    Created on : 8/03/2011, 02:26:07 PM
    Author     : Daniel
--%>
<%@page import="java.util.Iterator"%>
<%@page import="java.util.List"%>
<%@page import="comode.wordrecom.termsList"%>

<%
    termsList db = new termsList("exp"+session.getAttribute("StringExp"),session.getAttribute("SactualItem").toString());

    //Obtains the query text
    String query = request.getParameter("q");
    
    //Obtains the matched terms list
    List<String> items = db.getData(query);

    //Prints the matched terms list
    Iterator<String> iterator = items.iterator();
    while(iterator.hasNext()) {
        String item = (String)iterator.next();
        out.println(item);
    }
%>
