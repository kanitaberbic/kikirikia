<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Brojač posjeta</title>
</head>
<body>
<%!
    int counter = 0;

    synchronized int incrementCount() {
        counter++;
        return counter;
    }
%>
<h1>Brojač koji voli brojat posjete</h1>
<h3>Posjetili ste ovu stranicu <%=incrementCount()%>
    <p>Thread koji obrađuje request <%=Thread.currentThread().getName()%>
    </p>
</h3>
</body>
</html>
