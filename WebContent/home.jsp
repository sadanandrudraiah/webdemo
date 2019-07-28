<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Home</title>
</head>
<body>

	<%
		// scriptlet
		String username = (String) request.getAttribute("USERNAME");
		String msg = (String) request.getAttribute("MESSAGE");
	%>

	<h2>Home page</h2>

	<p>  <%= username %> </p>
	<br/>
	<p>  <%= msg %> </p>

</body>
</html>