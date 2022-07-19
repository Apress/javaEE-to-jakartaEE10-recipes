
<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="/WEB-INF/tlds/functions.tld" prefix="fct" %>
<html>
<head>
    <title>Recipe 2-5: Invoking a Function in an Expression</title>
</head>
<body>
<form method="get">
    <label for="typename">
        Name one of the primitive Java types:
    </label>
    <input type="text" id="typename" name="typename" size="40"/>
    <br/>
    <input type="submit">
</form>
<jsp:useBean id="conditionalBean" scope="page" class="org.jakartaeerecipe.chapter02.recipe02_05.ConditionalClass"/>
<jsp:setProperty name="conditionalBean" property="typename"/>
<c:if test="${fct:isPrimitive(conditionalBean.typename)}">
    ${ conditionalBean.typename } is a primitive type.
</c:if>
<c:if test="${conditionalBean.typename ne null and !fct:isPrimitive(conditionalBean.typename)}">
    ${ conditionalBean.typename } is not a primitive type.
</c:if>

</body>
</html>
