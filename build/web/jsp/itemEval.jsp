<%-- 
    Document   : itemEval
    Created on : 29/03/2011, 01:22:54 PM
    Author     : Daniel
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
  <title>COMoDE Project | Item's Evaluation</title>
  <link href="http://ajax.googleapis.com/ajax/libs/jqueryui/1.8/themes/base/jquery-ui.css" rel="stylesheet" type="text/css"/>
  <script src="http://ajax.googleapis.com/ajax/libs/jquery/1.5/jquery.min.js"></script>
  <script src="http://ajax.googleapis.com/ajax/libs/jqueryui/1.8/jquery-ui.min.js"></script>
    <style type="text/css">
    #Item1 { width: 200px; }
    #Item2 { width: 200px; }
    #Item3 { width: 200px; }
  </style>
  <script>
    //Defining variables and slider's characteristics
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
  

  <script type="text/javascript">
    
    function validate()
        {
            //Obtaning the slider's values
            var I1 = $("#Item1").slider("value");
            var I2 = $("#Item2").slider("value");
            var I3 = $("#Item3").slider("value");
            if (I1Changed && I2Changed && I3Changed){
                //User introduced values on the sliders
                //Need to save the data in a session attribute...
                alert("Values of items are-> I1:"+I1+" I2:"+I2+" I3:"+I3);
            }
            else
            {
                //Some slider doesn't move!
                alert ("You must use the three sliders to choose your selection");
            }
        }
  </script>
</head>
<body>
    <div align="center" id="eval">
    <h3>Evaluation of the query entering task</h3>
    
        
    <p>Please help us answering the following questions,
    you must use the sliders to indicate your feelings about the task you just finished.
    <p>
    <p>
    <p>
    <form name="showItem" method="post" action="../servlet/XperControl">
    How do you feel about the time required to introduce the requested query?
    <table border="0" width="270">
        <tr>
            <td align="left">Very little</td>
            <td align="center">Moderate</td>
            <td align="right">Extensive</td>
        </tr>
    </table>
    <div id="Item1" class="slider"></div>
    <p>
    <p>
    <p>
    <p>How mentally difficult was to introduce the requested query?</p>
    <table border="0" width="270">
        <tr>
            <td align="left">Very little</td>
            <td align="center">Moderate</td>
            <td align="right">Extensive</td>
        </tr>
    </table>
    <div id="Item2"></div>
    <p>
    <p>
    <p>
    <p>How psychologically stressed do you feel when entering the requested query?</p>
    <table border="0" width="420">
        <tr>
            <td align="center" width="140"><font size="2">Very little confused</font></td>
            <td align="center" width="140"><font size="2">Moderate stress due to confusion, frustration or anxiety</font></td>
            <td align="center" width="140"><font size="2">High or very intense stress due to confusion, frustration or anxiety</font></td>
        </tr>
    </table>
    <div id="Item3"></div>
    <p>
    <p>
    <p>
    <p>

    <input type=submit name="next" value="Next" onclick="validate()"
    </form>
    </div>
</body>
</html>