<%-- 
Author   : Daniel Aréchiga 2011
Function : validates the query text upon a given string
--%>
<script type="text/javascript">
        function ValidateItem(queryString){
        var queryItem=document.showItem.querytext
        if (queryItem.value != queryString){
            alert("Wrong data, please enter the right information, you entered: "+queryItem.value+" and should be: "+queryString)
            return false
        }
        document.getElementById('eval').style.visibility = 'visible'
        return true
    }
    </script>

