<%@page import="java.util.Date"%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%! Date currDate = null; %>
<% currDate = new Date(); %>
<html>
<head>
    <title>Jakarta Server Page Example</title>
</head>
<body>
Hello World!<br/>
The current date is: <%= currDate %>
</body>
</html>
