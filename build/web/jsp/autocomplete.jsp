<%-- 
    Document   : autocomplete
    Created on : 5/04/2011, 11:46:08 AM
    Author     : Daniel

Test file, should be deleted at project finish...
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"
   "http://www.w3.org/TR/html4/loose.dtd">
<html lang="en">
<head>
    <meta charset="utf-8">
    <title>jQuery UI Autocomplete - Default functionality</title>
    <link rel="stylesheet" href="http://jqueryui.com/themes/base/jquery.ui.all.css">
    <script src="../jquery/jquery-1.5.1.js"></script>
    <script src="../jquery/jquery.ui.core.js"></script>
    <script src="../jquery/jquery.ui.widget.js"></script>
    <script src="../jquery/jquery.ui.position.js"></script>
    <script src="../jquery/jquery.ui.autocomplete.js"></script>
    <script>
    $(function() {
        var availableTags = ["ActionScript","AppleScript","Asp","BASIC","C",
            "C++","Clojure","COBOL","ColdFusion","Erlang","Fortran","Groovy",
            "Haskell","Java","JavaScript","Lisp","Perl","PHP","Python","Ruby",
            "Scala","Scheme"];
	$( "#tags" ).autocomplete({source: availableTags});
	});
	</script>

    <!-- Virtual keyboard -->
    <script type="text/javascript" src="../js/keyboard.js" charset="UTF-8"></script>
    <link rel="stylesheet" type="text/css" href="../js/keyboard.css">

    <!-- Project stylesheet -->
    <link rel="stylesheet" type="text/css" href="../js/styles.css" />
</head>
<body>
<div class="ui-widget">
	<label for="tags">Query string: </label>
	<input size="40" id="tags" class="keyboardInput"/>
</div>

</body>
</html> 