<%--
  Created by IntelliJ IDEA.
  User: Alex
  Date: 09.10.2017
  Time: 19:57
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0-beta/css/bootstrap.min.css"
          integrity="sha384-/Y6pD6FV/Vv2HJnA6t+vslU6fwYXjCFtcEpHbNJ0lyAFsXTsjBbfaDjzALeQsN6M" crossorigin="anonymous">
    <script src="https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0-beta/js/bootstrap.min.js"
            integrity="sha384-h0AbiXch4ZDo7tp9hKZ4TsHbi047NrKGLO3SEJAg45jXxnGIfYzk4Si90RDIqNm1"
            crossorigin="anonymous"></script>
    <title>Search</title>
    <link rel="stylesheet" href="/css/styles.css">
</head>
<body>
<div class="container">
    <form action="/FrontController" method="post" role="form">
        <input type="hidden" id="idcontact" name="idcontact" value="${contact.idcontact}"/>
        <input type="hidden" name="action" value="SEARCH_CONTACTS"/>
        <input type="hidden" name="action" value="search"/>
        <h2 class="margintopsearch">Search</h2>
        <div class="form-group col-md-12">
            <div class="search">
                <label for="FirstName" class="control-label labelsize">First Name:</label>
                <input type="text" name="FirstName" id="FirstName" class="form-control"
                       value="${contact.firstname}"/>

                <label for="Surname" class="control-label labelsize">Last Name:</label>
                <input type="text" name="Surname" id="Surname" class="form-control"
                       value="${contact.surname}"/>

                <label for="MiddleName" class="control-label labelsize">Middle Name:</label>
                <input type="text" name="MiddleName" id="MiddleName" class="form-control"
                       value="${contact.middlename}"/>

                <div id="Birthday">
                    <div id="FirstDate">
                        <label for="FirstDate" class="control-label labelsize">From</label>
                        <input type="date" name="FirstDate" class="form-control"
                               value="${contact.firstdate}"/>
                    </div>
                    <div id="LastDate">
                        <label for="LastDate" class="control-label labelsize">To</label>
                        <input type="date" name="LastDate" class="form-control"
                               value="${contact.lastdate}"/>
                    </div>
                </div>
            </div>
            <div class="search searchmargin">

                <label for="Gender" class="control-label labelsize">Gender:</label>
                <select name="Gender" id="Gender" class="form-control form-control">
                    <option selected>${contact.gender}</option>
                    <option>Male</option>
                    <option>Female</option>
                </select>

                <label for="Marital" class="control-label labelsize">Marital status:</label>
                <select name="Marital" id="Marital" class="form-control form-control">
                    <option selected>${contact.marital}</option>
                    <option>Single</option>
                    <option>Married</option>
                </select>

                <label for="Citizenship" class="control-label labelsize">Citizenship:</label>
                <input type="text" name="Citizenship" id="Citizenship" class="form-control"
                       value="${contact.citizenship}"/>

                <label for="Country" class="control-label">Country:</label>
                <input type="text" name="Country" id="Country" class="form-control"
                       value="${address.country}"/>

                <label for="City" class="control-label">City:</label>
                <input type="text" name="City" id="City" class="form-control"
                       value="${address.city}"/>

            </div>
            <div class="search">

                <label for="Street" class="control-label">Street:</label>
                <input type="text" name="Street" id="Street" class="form-control"
                       value="${address.street}"/>

                <label for="House" class="control-label">House:</label>
                <input type="text" name="House" id="House" class="form-control"
                       value="${address.house}"/>

                <label for="Apartment" class="control-label">Apartment:</label>
                <input type="text" name="Apartment" id="Apartment" class="form-control"
                       value="${address.apartment}"/>

                <label for="Postcode" class="control-label">Postcode:</label>
                <input type="text" name="Postcode" id="Postcode" class="form-control"
                       value="${address.postcode}"/>
            </div>
        </div>

        <div class="searchbutton">
            <button type="submit" name="action" value="LIST_OF_CONTACTS" class="btn btn-danger" formnovalidate>Cancel
            </button>
            <button type="submit" class="btn btn-primary  btn-md">Search</button>
        </div>
    </form>
</div>
</body>
</html>
