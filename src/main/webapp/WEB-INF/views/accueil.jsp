<!DOCTYPE html>
<html>
    <head>
        <meta charset="utf-8" />
        <link 
        	href="https://cdn.jsdelivr.net/npm/bootstrap@5.0.2/dist/css/bootstrap.min.css" 
       		rel="stylesheet" 
       		integrity="sha384-EVSTQN3/azprG1Anm3QDgpJLIm9Nao0Yz1ztcQTwFspd3yD65VohhpuuCOmLASjC" 
       		crossorigin="anonymous">
        <title>Test</title>
    </head>
    <body class="bg-primary text-light">
	   	<p>YOUPI !!!</p>
	   	<p>Le futur !!!</p>
	   	<ul>
	        <c:forEach var="user" items="${ users }">
	            <li><c:out value="${ user.username }" /> <c:out value="${ user.password }" /></li>
	        </c:forEach>
	    </ul> 
    </body>
</html>