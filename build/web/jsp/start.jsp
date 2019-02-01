<%-- 
    Author     : Daniel
    Function   : Initial experiment page, ask's for user's email to control the session
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@page import="comode.common.COMoDEconstants"%>
<%@page import="comode.tools.*" %>
<%@include file='../js/emailvalid.js' %>

<html>
    <body>
    <head>
        
       <%-- create an instance of MobileDeviceDetector --%>
       <% MobileDeviceDetector deviceDetector = new MobileDeviceDetector(); %> 
       <% if(deviceDetector.isMobile(request)) {
           out.println("<link rel='stylesheet' type='text/css' href="+ COMoDEconstants.CSS_PATH+COMoDEconstants.COMoDE_MOBILESTYLE +">");
           %>
           <script type="text/javascript">
               document.getElementById("clientType").value = "mobile";
           </script>
           <%
       } else {
           out.println("<link rel='stylesheet' type='text/css' href="+ COMoDEconstants.CSS_PATH+COMoDEconstants.COMoDE_STYLE +">");
           %>
           <script type="text/javascript">
               document.emailCollect.clientType = "desktop";
           </script>
           <%
       } %> 

    <title>COMoDE | Initial Information</title>
    <link href="/experiment/favicon.ico" type="image/x-icon" rel="shortcut icon" />
    </head>
    <div class="section" id="page">
        <div class="header">
            <img src="<%=COMoDEconstants.IMAGES_PATH%>COMoDELogo.png" width="170" height="100" alt="COMoDE Logo" />
            <h2>Context Ontology for Mobile Devices</h2>
            <div class="nav clear" id="sessionemail">
                <ul>
                    <li>Bienvenido</li>
                </ul>
            </div>
        </div>
        <div class="section" id="articles"></div>
        <div class="article" id="article1">
            <h2>Descripción del Experimento</h2>Por favor lea las instrucciones detalladamente
            <div class="line"></div>
            <p>En este experimento le mostraremos 12 elementos, uno a uno. Cada
                Elemento le mostrará una situación imaginaria en la que le será
                requerido que introduzca una cadena de texto que representa una 
                posible búsqueda de información. Esta cadena debe ser introducida
                en la caja de texto, una vez que se ha hecho se presiona el botón
                de "continue". Por ejemplo, aquí se le pide que introduzca la palabra "hotel"
                </p>
            <p align="center"><img src="<%=COMoDEconstants.IMAGES_PATH%>box.jpg" width="313" height="88" alt="Query text box"/></p>
            <p>los textos a introducir son en inglés por las características del proyecto, se deben escribir tal y como los pide el elemento en cuestión.</p>
            <p></p>
            <dl>
                <dt>Existen tres tipos de elementos:</dt>
                <li><i>Sin recomendación de palabras (Sin RP):</i> Es necesario introducir completamente toda la cadena de texto, ya que <b>ningún tipo de ayuda será mostrada</b>.</li>
                <li><i>Recomendación de palabras simple (RP Simple):</i> Al comenzar a escribir la cadena de texto solicitada en la caja de texto <b>una lista de recomendaciones de palabras le será mostrada</b>, podrá elegir de las palabras que le aparezcan.</li>
                <li><i>Recomendación de palabras basada en contexto (RP basada en contexto):</i> Cuando comience a escribir la cadena de texto solicitada <b>aparecerá una lista de recomendaciones de palabras, pero estas serán generadas de acuerdo al contexto imaginario</b>, igualmente podrá seleccionar de esa lista.</li>
            </dl>
            <p></p>
            <p>En cada elemento siempre será mostrado un indicador que permite saber que tipo de elemento se está mostrado, en este ejemplo se muestra un elemento del tipo Sin RP (sin recomendación de palabras):</p>
            <p align="center"><img src="<%=COMoDEconstants.IMAGES_PATH%>wrt.jpg" width="313" height="88" alt="Word recommendation item type"/></p>
            <p></p>
            <p>Una vez introducido el texto solicitado correctamente y presionado el botón "Continue"
                una sección de evaluación aparecerá en la parte inferior de la página. En esta
                sección aparecerán 3 botones deslizantes, los que se utilizarán para evaluar este elemento
                en particular.</p>
            <p align="center"><img src="<%=COMoDEconstants.IMAGES_PATH%>slides.jpg" width="313" height="88" alt="Word recommendation item type"/></p>
            
        </div>
        <div class="article" id="article1">
            <h2>Información inicial</h2>
            <div class="line"></div>
            <div class="articleBody clear" id="maintext">
                <p>Para tener un contro de la sesión de este experimento es necesario que introduzca
                    su dirección de correo electrónico, esta informaicón será usada solamente
                    para el control de la sesión.</p>
                <p>Si desea recibir más información sobre el experimento o el proyecto COMoDE escriba un correo
                    electrónico a: vdaniel@cusur.udg.mx.</p>
                <form name="emailCollect" method="post" action="<%=COMoDEconstants.SERVLET_PATH%>XperControl" onSubmit="return ValidateEmail()">
                    Introduce tu dirección de correo electrónico:
                    <input size=40 type=text name=emailField>
                    <% if(deviceDetector.isMobile(request)) {
                        out.println("<input type='hidden' name=clientType value='mobile' class='hidden'>");
                        } else {
                        out.println("<input type='hidden' name=clientType value='desktop' class='hidden'>");
                    } %> 
                <input type=submit value=Submit />
                </form>
            </div>
        </div>
    </body>
</html>