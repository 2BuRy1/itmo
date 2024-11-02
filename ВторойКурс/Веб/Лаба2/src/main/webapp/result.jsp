<%@ page import="java.util.List" %>
<%@ page import="data.Dot" %>
<%@ page import="java.text.SimpleDateFormat" %>
<%@ page import="java.util.Date" %>
<%@ page import="java.util.Locale" %>
<%@ page import="java.util.ArrayList" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Results</title>

<style>
    td:hover, th:hover{
        transform: scale(1.3, 1.3);
    }
    table {
        width: 300px;
        height:fit-content;
        max-height: 50px;
        overflow: scroll;
    }



    td, th {
        opacity: 0;
        animation: ani 1s forwards;
        padding: 0;
        text-align: center;
        height: 70px;
        border: solid black;
        border-radius: 4px;
        transition: ease-in-out .2s;

    }
</style>
</head>
<body>
<table id="table-sector">
    <thead id="table-header">
    <tr>
        <th>x</th>
        <th>y</th>
        <th>R</th>
        <th>status</th>
    </tr>
    </thead>
    <tbody id = "table_body">
    <%
        List<Dot> points = (List<Dot>) request.getSession().getAttribute("result");
    %>
    <%
        if(points!=null){
            for (Dot point : points) {
    %>
    <tr>
        <td><%= String.valueOf(point.getX()) %></td>
        <td><%= String.valueOf(point.getY()) %></td>
        <td><%= String.valueOf( point.getR()) %></td>
        <td><%= point.isStatus() ? "Попадание" : "Промах" %></td>
    </tr>
    <%
            }
        }

    %>
    </tbody>
</table>
</body>
</html>
