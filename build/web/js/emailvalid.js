<%-- 
desc: email validation script, must be included in the JSP file
source: http://javascript.internet.com/forms/e-mail-address-validation2.html
Function : validates the email field to contain a validly formed email address
--%>
<script type="text/javascript">
        function echeck(str) {
        var at = "@"
        var dot = "."
        var lat = str.indexOf(at)
        var lstr = str.length
        var ldot = str.indexOf(dot)
        if (str.indexOf(at)==-1){
            alert("Direcci\u00f3n de correo electr\u00f3nico inv\u00e1lida")
            return false
        }
        if (str.indexOf(at)==-1 || str.indexOf(at)==0 || str.indexOf(at)==lstr){
            alert("Direcci\u00f3n de correo electr\u00f3nico inv\u00e1lida")
            return false
        }
        if (str.indexOf(dot)==-1 || str.indexOf(dot)==0 || str.indexOf(dot)==lstr){
            alert("Direcci\u00f3n de correo electr\u00f3nico inv\u00e1lida")
            return false
        }
        if (str.indexOf(at,(lat+1))!=-1){
            alert("Direcci\u00f3n de correo electr\u00f3nico inv\u00e1lida")
            return false
        }
        if (str.substring(lat-1,lat)==dot || str.substring(lat+1,lat+2)==dot){
            alert("Direcci\u00f3n de correo electr\u00f3nico inv\u00e1lida")
            return false
        }
        if (str.indexOf(dot,(lat+2))==-1){
            alert("Direcci\u00f3n de correo electr\u00f3nico inv\u00e1lida")
            return false
        }
        if (str.indexOf(" ")!=-1){
            alert("Direcci\u00f3n de correo electr\u00f3nico inv\u00e1lida")
            return false
        }
        return true
        }

        function ValidateEmail(){
        var emailID=document.emailCollect.emailField
  
        if ((emailID.value==null)||(emailID.value=="")){
            alert("Por favor introduzca su direcci\u00f3n de correo electr\u00f3nico")
            emailID.focus()
            return false
        }
        if (echeck(emailID.value)==false){
            emailID.value=""
            emailID.focus()
            return false
        }
        UEmail = emailID.value
        document.getElementById('sessionemail').innerHTML = "current session: "+UEmail
        
        return true
    }
    

    </script>

