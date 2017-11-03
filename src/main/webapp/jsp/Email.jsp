<%--
  Created by IntelliJ IDEA.
  User: Alex
  Date: 09.10.2017
  Time: 19:57
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0-beta/css/bootstrap.min.css"
          integrity="sha384-/Y6pD6FV/Vv2HJnA6t+vslU6fwYXjCFtcEpHbNJ0lyAFsXTsjBbfaDjzALeQsN6M" crossorigin="anonymous">
    <script src="https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0-beta/js/bootstrap.min.js"
            integrity="sha384-h0AbiXch4ZDo7tp9hKZ4TsHbi047NrKGLO3SEJAg45jXxnGIfYzk4Si90RDIqNm1"
            crossorigin="anonymous"></script>
    <script src="/js/email.js"></script>
    <link href="/css/styles.css" rel="stylesheet">
    <title>Sending emails</title>
</head>
<body>
<div class="container">
    <form action="/FrontController" method="post" role="form">
        <input type="hidden" name="action" value="SEND_EMAILS"/>
        <h2 class="margintopemailh">Sending emails</h2>
        <div class="form-group col-xs-4">
            <c:choose>
                <c:when test="${not empty emails}">
                    <label for="Emails" class="control-label col-xs-4">To: </label>
                    <table class="table table-bordered mail" id="Emails">
                        <c:forEach var="email" items="${emails}">
                            <tr>
                                <td><input type="hidden" name="mail" value="${email}">${email}</td>
                            </tr>
                        </c:forEach>
                    </table>
                    <label for="Subject" class="control-label col-xs-4">Subject: </label>
                    <input type="text" name="Subject" id="Subject" class="form-control mail"/>
                    <label for="Template" class="control-label col-xs-4">Template: </label>
                    <select name="Template" id="Template" class="form-control form-control-sm mail"
                            onclick="takeTemplate()">
                        <option></option>
                        <option>Birthday</option>
                        <option>New Year</option>
                    </select>
                    <div id="templates">
                        <input id="Birthday" type="hidden" value="${Birthday}"/>
                        <input id="NewYear" type="hidden" value="${NewYear}"/>
                    </div>
                    <div id="listOfNames">
                        <c:forEach var="name" items="${names}">
                            <input type="hidden" name="name" value="${name}">
                        </c:forEach>
                    </div>
                    <label for="Message" class="control-label col-xs-4">Message: </label>
                    <div>
                        <textarea rows="7" cols="60" id="Message" name="Message" value=""></textarea>
                    </div>
                    <br>
                    <br>
                    <div>
                        <button type="submit" name="action" value="LIST_OF_CONTACTS" class="btn btn-danger"
                                formnovalidate>Cancel
                        </button>
                        <button type="submit" class="btn btn-info">Send</button>
                    </div>
                </c:when>
                <c:otherwise>
                    <br>
                    <div class="alert alert-info">
                        There is no chosen contacts to send emails
                    </div>
                    <div>
                        <button type="submit" name="action" value="LIST_OF_CONTACTS" class="btn btn-danger"
                                formnovalidate>Cancel
                        </button>
                    </div>
                </c:otherwise>
            </c:choose>
        </div>
    </form>
</div>
</body>
</html>
