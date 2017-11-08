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
    <link rel="stylesheet" href="/css/bootstrap.min.css">

    <!-- Optional theme -->
    <link rel="stylesheet" href="/css/bootstrap-theme.min.css">

    <link rel="stylesheet" href="/css/Frontpage.css?new">

    <link rel="stylesheet" href="/css/styles.css?new">

    <script src="/js/listOfContacts.js?newversion"></script>

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
                            Search
                        </button>
                        <button type="submit" name="action" value="CONTACT_FORM" class="btn btn-info">
                            New contact
                        </button>
                        <button id="deleteButton" type="submit" name="action" value="DELETE_CONTACT"
                                class="btn btn-info" disabled>
                            Delete
                        </button>
                        <button type="submit" name="action" value="MAIL_FORM" class="btn btn-info">
                            Email
                        </button>
                    </div>
                </div>
            </div>
        </div>
        <br>
        <br>

        <table id="tableContact" class="table table-hover">
            <thead>
            <tr>
                <td></td>
                <td>Full Name</td>
                <td>Bithday Date</td>
                <td>Country</td>
                <td>City</td>
                <td>Street</td>
                <td>House</td>
                <td>Apartment</td>
                <td>Workplace</td>
            </tr>
            </thead>
            <tbody>
            <c:forEach var="contact" items="${contacts}">
                <tr>
                    <td><input type="checkbox" name="chkbox" value="${contact.key.idcontact}"
                               onchange="deleteContacts()"></td>
                    <td>
                        <a href="FrontController?action=CONTACT_FORM&idcontact=<c:out value="${contact.key.idcontact}"/>">
                            <span>${contact.key.firstname} ${contact.key.surname} ${contact.key.middlename}</span>
                        </a>
                    </td>
                    <td>${contact.key.birthdate}</td>
                    <td>${contact.value.country}</td>
                    <td>${contact.value.city}</td>
                    <td>${contact.value.street}</td>
                    <td>${contact.value.house}</td>
                    <td>${contact.value.apartment}</td>
                    <td>${contact.key.workplace}</td>
                </tr>
            </c:forEach>
            </tbody>
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

<!-- Latest compiled and minified JavaScript -->
<script src="/js/bootstrap.min.js"></script>

</body>
</html>
