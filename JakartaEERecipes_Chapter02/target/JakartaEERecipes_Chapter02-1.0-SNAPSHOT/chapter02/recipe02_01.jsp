<%@ page contentType="text/html;charset=UTF-8" %>
<html>
<head>
    <title>Jakarta Server Page Example</title>
</head>
<body>
<jsp:useBean id="dateBean" scope="application"
    class="org.jakartaeerecipe.chapter02.recipe02_01.DateBean"/>
Hello World!<br/>
The current date is: ${dateBean.currentDate}!
</body>
</html>
