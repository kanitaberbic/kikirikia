<%@ page import="java.time.LocalDateTime" %>
<%@ page import="java.time.LocalTime" %>
<%@ page import="java.util.Random" %><%--
  Created by IntelliJ IDEA.
  User: Grupa 1
  Date: 4/16/2024
  Time: 8:07 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Moj Prvi JSP page</title>
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.0.2/dist/css/bootstrap.min.css" rel="stylesheet"
          integrity="sha384-EVSTQN3/azprG1Anm3QDgpJLIm9Nao0Yz1ztcQTwFspd3yD65VohhpuuCOmLASjC" crossorigin="anonymous">
</head>
<body>
<div style="padding: 20px">
    <h1>Hello from Java Server Pages</h1>
    <h2>4 važne stvari kada je u pitanju JSP tehnologija</h2>
    <ol>
        <li>Izrazi</li>
        <li>Skripleti</li>
        <li>Deklaracije</li>
        <li>Direktive</li>
    </ol>
    <hr>
    <h1>Demonstracija jednog IZRAZA</h1>
    <p>Trenutno vrijeme <%=LocalTime.now()%>
    </p>
    <hr>
    <h1>Demonstracija upotrebe SKRIPLETA</h1>
    <%
        Random random = new Random();
        int slucajanBroj = random.nextInt(10);
        if (slucajanBroj <= 5) {
    %> <p>Mnogo sam NESRETAN</p>
    <%} else {%>
    <p>Mnogo sam SRETAN</p>
    <%}%>
    <hr>
    <h1>Demonstracija upotrebe DEKLARACIJA</h1>
    <%! int counter = new Random().nextInt(10);%>
    <p>Vrijednost counter: <%=counter%></p>
    <p>Vrijednost slučajnog broja: <%=slucajanBroj%></p>
    <hr>
    <h1>Demonstracija upotrebe DIREKTIVA</h1>
    <%@ include file="index.html" %>
</div>
</body>
</html>
