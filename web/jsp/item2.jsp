<%--
    Document   : item.jsp
    Created on : 8/03/2011, 02:09:05 PM
    Author     : Daniel
    Function   : Shows an item to the user
<%@include file='../js/itemvalid.js' %>
--%>
<%@page import="comode.common.COMoDEconstants"%>
<%@page import="comode.items.XperItemSelect"%>
<script>
    var keypresses = 0
</script>

<script type="text/javascript">
        function ValidateItem(queryString){
        var queryItem=document.showItem.querytext
        if (queryItem.value != queryString){
            alert("Wrong data, please enter the right information, you entered: "+queryItem.value+" and should be: "+queryString)
            return false
        }
        return true
    }
</script>
  <!-- prevents the ENTER keypress -->
  <script language="javascript" src="<%=COMoDEconstants.JS_PATH%>jquery.js"></script> 
 <!-- Validates the sliders -->
  <script type="text/javascript">
    function validate()
        {
            return false
        }
  </script>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!-- Aquí va el include de itemvalid -->
    <script src="http://www.google.com/jsapi"></script>
    <script>
        google.load("jquery", "1");
    </script>

    <!-- Autocomplete -->
    <script src="<%=COMoDEconstants.JS_PATH%>jquery.autocomplete.js"></script>
    <link rel="stylesheet" type="text/css" href="<%=COMoDEconstants.CSS_PATH%>jquery.autocomplete.css" />

    <!-- Virtual keyboard -->
    <!--
    <script type="text/javascript" src="<%=COMoDEconstants.JS_PATH%>keyboard.js" charset="UTF-8"></script>
    <link rel="stylesheet" type="text/css" href="<%=COMoDEconstants.CSS_PATH%>keyboard.css" >
    -->
    <form name="showItem" method="post" action="<%=COMoDEconstants.SERVLET_PATH%>XperControl" onSubmit="return validate()" >
        <%
                //String validateText = session.getAttribute("StringItem");
                String validateText = "Daniel";
        %>
        <!-- <p><%=COMoDEconstants.COMoDE_StrItemQuery%>: <b> <%= session.getAttribute( "StringItem" ) %> </b></p> -->
        <div align="center">
            <!--<input size="40" type="text" id="querytext" name="query" class="keyboardInput"/>-->
            <input size="40" type="text" id="querytext" name="query" />
            <input type="button" value="Continue" />
            <!--</form>-->
        </div>
            <script>
                $("#querytext").autocomplete("<%=COMoDEconstants.JSP_PATH%>getdata.jsp", {delay:100, maxCacheLength:100});
                // $("#querytext").autocomplete("<%=COMoDEconstants.SERVLET_PATH%>termsList.java", {delay:100, maxCacheLength:100});
            </script>
            
    </form>