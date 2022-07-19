<%@ page contentType="text/html;charset=UTF-8"%>
<html>
    <head>
        <title>Recipe 2-4: Yielding or Setting Values</title>
    </head>
    <body>
        <jsp:useBean id="easyBean" class="org.jakartaeerecipe.chapter02.recipe02_04.EasyBean"/>
        <jsp:setProperty name="easyBean" property="*"/>
        <form method="post">
            Use the input text box below to set the value, and then hit submit.
            <br/><br/>
            <label for="fieldValue">Set the field value: </label>
            <input id="fieldValue" name="fieldValue" type="text" size="30"/>
            <br/>
            The value contained within the field is currently:
            <jsp:getProperty name="easyBean" property="fieldValue"/>
            <input type="submit"/>
        </form>
    </body>
</html>
