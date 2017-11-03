<%--
  Created by IntelliJ IDEA.
  User: Alex
  Date: 08.10.2017
  Time: 12:44
  To change this template use File | Settings | File Templates.
--%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8" %>
<!DOCTYPE html>
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=utf-8">
    <!-- Latest compiled and minified CSS -->
    <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css"
          integrity="sha384-BVYiiSIFeK1dGmJRAkycuHAHRg32OmUcww7on3RYdg4Va+PmSTsz/K68vbdEjh4u" crossorigin="anonymous">

    <!-- Optional theme -->
    <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap-theme.min.css"
          integrity="sha384-rHyoN1iRsVXV4nD0JutlnGaslCJuC7uwjduW9SVrLvRYooPp2bWYgmgJQIXwl/Sp" crossorigin="anonymous">

    <!-- Latest compiled and minified JavaScript -->
    <script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/js/bootstrap.min.js"
            integrity="sha384-Tc5IQib027qvyjSMfHjOMaLkfuWVxZxUPnCJA7l2mCWNIpG9mGCD8wGNIcPD7Txa"
            crossorigin="anonymous"></script>
    <link rel="stylesheet" href="/css/Frontpage.css">
    <link rel="stylesheet" href="/css/styles.css?newversion">
    <script src="/js/listOfContacts.js"></script>
    <title>Show All Users</title>
</head>
<body>
<div class="container containermargin">

    <!--Employees List-->
    <form action="/FrontController" method="get">
        <input type="hidden" id="IsSearch" value="${action}">
        <div class="headerfixed">
            <div class="container">
                <div class="namefixed">
                    <a href="/FrontController?action=LIST_OF_CONTACTS">
                        <h2>List of Contacts</h2>
                    </a>
                </div>
                <div class="buttonsfixed">
                    <div class="btn-toolbar" role="toolbar" aria-label="Toolbar with button groups">
                        <button type="submit" name="action" value="SEARCH_FORM" class="btn btn-info">
                            <span class="glyphicon glyphicon-search"></span> Search
                        </button>
                        <button type="submit" name="action" value="CONTACT_FORM" class="btn btn-info">
                            <span class="glyphicon glyphicon-plus"></span> New contact
                        </button>
                        <button type="submit" name="action" value="DELETE_CONTACT" class="btn btn-info">
                            <span class="glyphicon glyphicon-trash"></span> Delete
                        </button>
                        <button type="submit" name="action" value="MAIL_FORM" class="btn btn-info">
                            <span class="glyphicon glyphicon-envelope"></span> Email
                        </button>
                    </div>
                </div>
            </div>
        </div>
        <br>
        <br>

        <table class="table table-hover">
            <thead>
            <tr>
                <td></td>
                <td>First Name</td>
                <td>Last name</td>
                <td>Gender</td>
                <td>Workplace</td>
                <td>E-mail</td>
                <td></td>
            </tr>
            </thead>
            <c:forEach var="contact" items="${contacts}">
                <tr>
                    <td><input type="checkbox" name="chkbox" value="${contact.idcontact}"></td>
                    <td>${contact.firstname}</td>
                    <td>${contact.surname}</td>
                    <td>${contact.gender}</td>
                    <td>${contact.workplace}</td>
                    <td>${contact.email}</td>
                    <td>
                        <a href="FrontController?action=CONTACT_FORM&idcontact=<c:out value="${contact.idcontact}"/>">
                            <span class="glyphicon glyphicon-edit"/>
                        </a>
                    </td>
                </tr>
            </c:forEach>
        </table>

        <br>
        <div>
            <div class="pagination">
                <%--<label for="Number" class="control-label">Amount of contacts on the page:</label>--%>
                <select id="Number" name="Number" class="form-control form-control-sm" onchange="changeRecCount()">
                    <c:forEach var="num" begin="10" end="20" step="10">
                        <option>${num}</option>
                    </c:forEach>
                </select>
            </div>
            <div class="pagination">
                <select id="Page" name="Page" class="form-control form-control-sm" onchange="refreshPage()">
                    <c:forEach var="i" begin="1" end="${pages}">
                        <option>${i}</option>
                    </c:forEach>
                </select>
            </div>
            <input type="hidden" name="records" value="${records}">
        </div>
        <br>
        <br>
    </form>
</div>
<script type="text/javascript">
    var select = document.getElementById('Number');

    for (var i = 0; i < select.length; i++) {
        if (+select.options[i].innerHTML === +${number}) {
            select.options[i].selected = true;
            break;
        }
    }

    select = document.getElementById('Page');

    for (var i = 0; i < select.length; i++) {
        if (+select.options[i].innerHTML === +${page}) {
            select.options[i].selected = true;
            break;
        }
    }
</script>
</body>
</html>
