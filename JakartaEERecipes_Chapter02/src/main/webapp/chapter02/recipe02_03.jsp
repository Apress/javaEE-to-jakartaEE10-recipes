<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Recipe 2-3: Separating Business Logic from View Code</title>
</head>
<body>
<jsp:useBean id="randomBean" scope="application"
             class="org.jakartaeerecipe.chapter02.recipe02_03.RandomBean"/>
Display a Random Number!<br/>
Your random number is ${randomBean.randomNumber}. Refresh page to see another!

</body>
</html>
