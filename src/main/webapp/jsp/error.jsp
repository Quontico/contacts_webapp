<%--
  Created by IntelliJ IDEA.
  User: Alex
  Date: 21.10.2017
  Time: 14:11
  To change this template use File | Settings | File Templates.
--%>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=utf-8">

    <!-- Latest compiled and minified CSS -->
    <link rel="stylesheet" href="/css/bootstrap.min.css">

    <!-- Optional theme -->
    <link rel="stylesheet" href="/css/bootstrap-theme.min.css">

    <title>Error</title>
</head>
<body>
<form method="get" action="/FrontController">
    <div class="alert alert-warning">
        There is some error ! Please, return to main page !
    </div>
    <button type="submit" name="action" class="btn btn-info" value="LIST_OF_CONTACTS">Back to main</button>
</form>
<!-- Latest compiled and minified JavaScript -->
<script src="/js/bootstrap.min.js"></script>
</body>
</html>
