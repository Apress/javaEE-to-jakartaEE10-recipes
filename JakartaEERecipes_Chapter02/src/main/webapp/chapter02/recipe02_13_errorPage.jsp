<%@ page contentType="text/html;charset=UTF-8" %>
<html>
<head>
    <title>Jakarta Server Pages - Error Page</title>
</head>
<body>
    <h1>Error Encountered</h1>
    <br/>
    <br/>
    <p>
        The application has encountered the following error:
        <br/>
        <fmt:message key="ServerError"/>: ${pageContext.errorData.statusCode}
    </p>
</body>
</html>
