<html>
<head>

</head>
<body>
<form action="FindMatchingData" method="post" style="margin-left: 50px;    margin-bottom: 15px;">
<label>Put Order Id: </label>
<input type="text" name="order_id" id="order_id" onblur="valid();"><br>
<input type="submit" value="Get Order Data">
<span id="error"></span>

</form>

<script type="text/javascript">

function checkValidData(str)
{
	//var regex = "^[^<>'\"/;`%]*$";
if(str.match("^[a-zA-Z0-9הצüִײÜ]*$"))
	{
	alert("special character exist");
	}
else
	{
	alert("special character not exist");
	}
	
	}

function valid()
{

 var s = "!@#$%^&*()+=-[]\\\';,./{}|\":<>?";
 str=document.getElementById('order_id').value;
 for (var i = 0; i < str.length; i++)
 {
    if (s.indexOf(str.charAt(i)) != -1)
  {
     /* alert ("The box has special characters. \nThese are not allowed.\n"); */
    document.getElementById('error').innerHTML = 'The box has special characters. \nThese are not allowed.\n'; 
    document.getElementById('order_id').value="";
     return false;
  }
}
}


</script>

</body>

</html>