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
    // validates if the entered query corresponds to requested text, if it's right,
    // then shows the evaluation sliders and stores the keypresses value.
        function ValidateItem(queryString){
        var queryItem=document.showItem.querytext
        if (queryItem.value != queryString){
            alert("Datos incorrectos, por favor introduzca la información solicitada, usted introdujo : "+queryItem.value+" y debe ser: "+queryString)
            return false
        }
        document.getElementById('eval').style.visibility = 'visible';
        document.getElementById('KSPQ').value = keypresses;
        return true
    }
</script>

  <style type="text/css">
    #Item1 { width: 200px; }
    #Item2 { width: 200px; }
    #Item3 { width: 200px; }
  </style>
  
  <script>
    //Defines variables and slider's characteristics
    var I1Changed=false, I2Changed=false, I3Changed=false;
    $(document).ready(function() {
    $("#Item1").slider({ 
        max: 100,
        change: function(event, ui) { I1Changed=true}});
    $("#Item2").slider({ 
        max: 100,
        change: function(event, ui) { I2Changed=true}});
    $("#Item3").slider({ 
        max: 100,
        change: function(event, ui) { I3Changed=true}});
  });
  </script>
  <script language="javascript" src="<%=COMoDEconstants.JS_PATH%>jquery.js"></script> 
  <script language="javascript">
      // prevents the ENTER keypress and has the keypresses account
      $(document).ready(function() {
          $('form').keypress(function(e){
              if(e == 13){return false;}
          });
          $('input').keydown(function(e){
              // invalidate if user presses the enter
              if(e.which == 13){return false;}
              // determinate if users presses an "accountable" key
              // Keys: arrows, insert, del, 0..9, a..z, backspace
              else if ((e.which>=37 && e.which<=90) || e.which == 8)
              {
                  // increase the keypress counter
                  keypresses = keypresses + 1;
              }
          });
      }); 
  </script> 
  <script type="text/javascript">
      // Validates the sliders
      function validate()
      {
          //Obtaning and validating the slider's values
          var I1 = $("#Item1").slider("value");
          var I2 = $("#Item2").slider("value");
          var I3 = $("#Item3").slider("value");
          if (I1Changed) {
              document.getElementById('EItem1').value = I1;
          } else {
              document.getElementById('EItem1').value = 0;
          }
          if (I2Changed) {
              document.getElementById('EItem2').value = I2;
          } else {
              document.getElementById('EItem2').value = 0;
          }
          if (I3Changed) {
              document.getElementById('EItem3').value = I3;
          } else {
              document.getElementById('EItem3').value = 0;
          }          
            <%--
          if (I1Changed && I2Changed && I3Changed){
              //Prepare to post the slider's value's
              document.getElementById('EItem1').value = I1;
              document.getElementById('EItem2').value = I2;
              document.getElementById('EItem3').value = I3;
              document.getElementById('KSPQ').value = keypresses;
              return true
          }
          else {
              //Some slider doesn't move!
              alert ("You must use the three sliders to choose your selection");
              return false
          }--%>
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
    <script type="text/javascript" src="<%=COMoDEconstants.JS_PATH%>keyboard.js" charset="UTF-8"></script>
    <link rel="stylesheet" type="text/css" href="<%=COMoDEconstants.CSS_PATH%>keyboard.css" >
    <form name="showItem" method="post" action="<%=COMoDEconstants.SERVLET_PATH%>XperControl" onSubmit="return validate()" >
        <%
                //String validateText = session.getAttribute("StringItem");
                String validateText = "Daniel";
        %>
        <!-- <p><%=COMoDEconstants.COMoDE_StrItemQuery%>: <b> <%= session.getAttribute( "StringItem" ) %> </b></p> -->
        <div align="center">
            <!--<input size="40" type="text" id="querytext" name="query" class="keyboardInput"/>-->
            <input size="40" type="text" id="querytext" name="query" />
            <input type="button" value="Continue" onclick="ValidateItem('<%= session.getAttribute( "StringItem" ) %>')" />
            <!--</form>-->
        </div>
            <script>
                $("#querytext").autocomplete("<%=COMoDEconstants.JSP_PATH%>getdata.jsp", {delay:100, maxCacheLength:100});
            </script>
            
            <!-- Item evaluation code starts -->
            <div align="center" id="eval" style="visibility:hidden">
                <div class="line"></div>
                <h3>Evaluación de la tarea de introducción de texto</h3>
                
                <p>Por favor ayúdenos evaluando esta actividad, utilice los botones deslizantes
                    para indicarnos su sentir.
                <p><p><p>
                ¿Que le pareció el tiempo requerido para introducir el texto?
                <table border="0" width="270">
                    <tr>
                        <td align="left">Muy poco</td>
                        <td align="center">Moderado</td>
                        <td align="right">Excesivo</td>
                    </tr>
                </table>
                
                <link href="http://ajax.googleapis.com/ajax/libs/jqueryui/1.8/themes/base/jquery-ui.css" rel="stylesheet" type="text/css"/>
                <script src="http://ajax.googleapis.com/ajax/libs/jquery/1.5/jquery.min.js"></script>
                <script src="http://ajax.googleapis.com/ajax/libs/jqueryui/1.8/jquery-ui.min.js"></script>
                <div id="Item1" class="slider ui-slider ui-slider-horizontal ui-widget ui-widget-content ui-corner-all">
                    <a class="ui-slider-handle ui-state-default ui-corner-all" href="#" style="left: 0%; "></a>
                </div>
                <p><p><p><p>
                ¿Como consideras la dificultad para introducir el texto solicitado?</p>
                <table border="0" width="270">
                    <tr>
                        <td align="left">Muy poca</td>
                        <td align="center">Moderada</td>
                        <td align="right">Excesiva</td>
                    </tr>
                </table>
                <div id="Item2" class="slider ui-slider ui-slider-horizontal ui-widget ui-widget-content ui-corner-all">
                    <a class="ui-slider-handle ui-state-default ui-corner-all" href="#" style="left: 0%; "></a></div>
                <p><p><p><p>
                    ¿Como consideras la angustia psicológica que te implica introducir el texto?</p>
                <table border="0" width="420">
                    <tr>
                        <td align="center" width="140"><font size="2">Poco o nada confuso</font></td>
                        <td align="center" width="140"><font size="2">Estrés moderado; algo de frustración, confusión o ansiedad</font></td>
                        <td align="center" width="140"><font size="2">Estrés alto o intenso; confusion, frustración o ansiedad</font></td>
                    </tr>
                </table>
                <div id="Item3" class="slider ui-slider ui-slider-horizontal ui-widget ui-widget-content ui-corner-all">
                    <a class="ui-slider-handle ui-state-default ui-corner-all" href="#" style="left: 0%; "></a></div>
                <p><p><p><p>
                    <!-- hidden fields for servlet use -->
                    <input type="hidden" id="KSPQ" name="KSPQ" class="hidden" />
                    <input type="hidden" id="EItem1" name="EItem1" class="hidden" />
                    <input type="hidden" id="EItem2" name="EItem2" class="hidden" />
                    <input type="hidden" id="EItem3" name="EItem3" class="hidden" />
                    <!-- final button -->
                    <input type=submit name="next" value="Siguiente" />
            </div>
            <!-- Item evaluation code ends -->
    </form>