<%--
  Created by IntelliJ IDEA.
  User: Alex
  Date: 08.10.2017
  Time: 17:56
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=utf-8">
    <!-- Latest compiled and minified CSS -->
    <link rel="stylesheet" href="/css/bootstrap.min.css">

    <!-- Optional theme -->
    <link rel="stylesheet" href="/css/bootstrap-theme.min.css">

    <link href="/css/styles.css?new" rel="stylesheet">
    <link rel="stylesheet" type="text/css" href="/css/popupstyles.css?new">

    <meta name="viewport" content="width=device-width, height=device-height, initial-scale=1">
</head>
<body>
<div class="container">
    <form enctype="multipart/form-data"
          action="/FrontController?action=ADD_OR_EDIT_CONTACT"
          method="post" role="form">
        <input type="hidden" id="idcontact" name="idcontact" value="${contact.idcontact}"/>
        <header>
            <h2 class="margintopcontacth">Contact</h2>
        </header>
        <div class="form-group">
            <div class="photo">
                <label for="avatar">
                    <img src="/FrontController?action=GET_AVATAR&image=${contact.urlAvatar}" alt="avatar">
                </label>
            </div>
            <div class="name">
                <label for="FirstName" class="control-label">First Name:</label>
                <input type="text" name="FirstName" id="FirstName" class="form-control"
                       value="${contact.firstname}" required="true"
                       pattern="^[a-zA-Zа-яА-ЯЁё '-]+$" placeholder="Required field"
                       title="This is required field. Please, fulfill it."/>

                <label for="Surname" class="control-label">Last Name:</label>
                <input type="text" name="Surname" id="Surname" class="form-control"
                       value="${contact.surname}" required="true"
                       pattern="^[a-zA-Zа-яА-ЯЁё '-]+$" placeholder="Required field"
                       title="This is required field. Please, fulfill it."/>

                <label for="MiddleName" class="control-label">Middle Name:</label>
                <input type="text" name="MiddleName" id="MiddleName" class="form-control"
                       value="${contact.middlename}" pattern="^[a-zA-Zа-яА-ЯЁё '-]+$"
                       title="This field should contain latin and cyrillic symbols."/>

                <label for="Birthdate" class="control-label">Birthday Date:</label>
                <input type="date" name="Birthdate" id="Birthdate" class="form-control"
                       value="${contact.birthdate}" max="${today}"/>
            </div>
            <div class="additionalinformation">
                <div class="leftinfo">
                    <label for="Gender" class="control-label">Gender:</label>
                    <select name="Gender" id="Gender" class="form-control form-control-sm">
                        <c:forEach var="gender" items="${genders}">
                            <c:if test="${gender==contact.gender}">
                                <option selected>${gender}</option>
                            </c:if>
                            <c:if test="${gender!=contact.gender}">
                                <option>${gender}</option>
                            </c:if>
                        </c:forEach>
                    </select>

                    <label for="Marital" class="control-label">Marital status:</label>
                    <select name="Marital" id="Marital" class="form-control form-control-sm">
                        <c:forEach var="marital" items="${maritals}">
                            <c:if test="${marital==contact.marital}">
                                <option selected>${marital}</option>
                            </c:if>
                            <c:if test="${marital!=contact.marital}">
                                <option>${marital}</option>
                            </c:if>
                        </c:forEach>
                    </select>

                    <label for="Citizenship" class="control-label">Citizenship:</label>
                    <input type="text" name="Citizenship" id="Citizenship" class="form-control"
                           value="${contact.citizenship}" pattern="^[a-zA-Zа-яА-ЯЁё '-]+$"
                           title="This field should contain latin and cyrillic symbols."/>

                    <label for="Workplace" class="control-label">Workplace:</label>
                    <input type="text" name="Workplace" id="Workplace" class="form-control"
                           value="${contact.workplace}"
                           title="This field should contain latin, cyrillic symbols and some numbers."/>

                    <label for="Email" class="control-label">E-mail:</label>
                    <input type="text" name="Email" id="Email" class="form-control" value="${contact.email}"
                           pattern="^[a-z0-9._%+-]+@[a-z0-9.-]+\.[a-z]{2,4}$" placeholder="@mail.com"
                           title="This field should contain your email."/>

                    <label for="Website" class="control-label">Website:</label>
                    <input type="text" name="Website" id="Website" class="form-control"
                           value="${contact.website}"/>
                </div>
                <div class="rightinfo">
                    <label for="Country" class="control-label">Country:</label>
                    <input type="text" name="Country" id="Country" class="form-control"
                           value="${address.country}" pattern="[a-zA-Zа-яА-ЯЁё '-]+"
                           title="This field should contain latin and cyrillic symbols."/>

                    <label for="City" class="control-label">City:</label>
                    <input type="text" name="City" id="City" class="form-control"
                           value="${address.city}" pattern="[a-zA-Zа-яА-ЯЁё0-9 '-]+"
                           title="This field should contain latin and cyrillic symbols."/>

                    <label for="Street" class="control-label">Street:</label>
                    <input type="text" name="Street" id="Street" class="form-control"
                           value="${address.street}" pattern="[a-zA-Z0-9а-яА-ЯЁё '-]+"
                           title="This field should contain latin, cyrillic symbols and some numbers."/>

                    <label for="House" class="control-label">House:</label>
                    <input type="text" name="House" id="House" class="form-control"
                           value="${address.house}" pattern="[0-9]+"
                           title="This field should contain only numbers."/>

                    <label for="Apartment" class="control-label">Apartment:</label>
                    <input type="text" name="Apartment" id="Apartment" class="form-control"
                           value="${address.apartment}" pattern="[0-9]+"
                           title="This field should contain only numbers."/>

                    <label for="Postcode" class="control-label">Postcode:</label>
                    <input type="text" name="Postcode" id="Postcode" class="form-control"
                           value="${address.postcode}" pattern="[0-9]+"
                           title="This field should contain only numbers."/>

                    <div hidden>
                        <input class="upload-image" type="file" id="avatar" name="avatar"
                               accept="image/jpeg, image/png, image/jpg">
                    </div>
                </div>
            </div>
        </div>
        <br>
        <br>
        <br>

        <div class="btn-toolbar" role="toolbar" aria-label="Toolbar with button groups">
            <button type="button" onclick="addPhone()" style="width:auto;" class="btn btn-info">
                Add
            </button>
            <button type="button" id="deletePhoneButton" onclick="deletePhone()" class="btn btn-info" disabled>
                Delete
            </button>
        </div>

        <br>
        <table class="table table-hover">
            <thead>
            <tr>
                <td></td>
                <td>Phone Number</td>
                <td>Phone Type</td>
                <td>Commentary</td>
                <td></td>
            </tr>
            </thead>
            <tbody id="phoneTable">
            <c:forEach var="telephone" items="${telephones}">
                <tr id="${telephone.id}">
                    <input type="hidden" name="Dialing_Prefix" value="${telephone.dialingPrefix}">
                    <input type="hidden" name="Provider_Code" value="${telephone.providerCode}">
                    <input type="hidden" name="Phone_Number" value="${telephone.phoneNumber}">
                    <input type="hidden" name="Number_Type" value="${telephone.numberType}">
                    <input type="hidden" name="Phone_Commentary" value="${telephone.commentary}">
                    <input type="hidden" name="Phone_ID" value="${telephone.id}">
                    <input type="hidden" name="Phone_Action" value="none">
                    <td><input type="checkbox" name="phnbox" value="${telephone.id}" onchange="checkDeletePhones()">
                    </td>
                    <td>+${telephone.dialingPrefix}-${telephone.providerCode}-${telephone.phoneNumber}</td>
                    <td>${telephone.numberType}</td>
                    <td>${telephone.commentary}</td>
                    <td>
                        <button type="button" onclick="editPhone(this)" style="width:auto" class="btn btn-info">
                            Edit
                        </button>
                    </td>
                </tr>
            </c:forEach>
            </tbody>
        </table>
        <br>

        <br>
        <br>
        <div class="btn-toolbar" role="toolbar" aria-label="Toolbar with button groups">
            <button type="button" onclick="addAttachment()" style="width:auto;" class="btn btn-info">
                Add
            </button>
            <button type="button" id="deleteAttachmentButton" onclick="deleteAttachment()" class="btn btn-info"
                    disabled>
                Delete
            </button>
        </div>
        <br>
        <table class="table table-hover">
            <thead>
            <tr>
                <td></td>
                <td>Attachment Name</td>
                <td>Upload Date</td>
                <td>Commentary</td>
                <td></td>
            </tr>
            </thead>
            <tbody id="attachmentTable">
            <c:forEach var="attachment" items="${attachments}">
                <tr id="${attachment.idattachment}">
                    <input type="hidden" name="atchAction" value="none">
                    <input type="hidden" name="Commentary" value="${attachment.attachmentComment}">
                    <input type="hidden" name="PathFile" value="${attachment.attachmentPath}">
                    <input type="hidden" name="atchId" value="${attachment.idattachment}">
                    <input type="hidden" name="atchName" value="${attachment.attachmentName}">
                        <%--<input type="hidden" name="UploadDate" value="${attachment.UploadDate}">--%>
                    <td hidden=""><input type="file" name="File"></td>
                    <td><input type="checkbox" name="atchbox" value="${attachment.idattachment}"
                               onchange="checkDeleteAttachments()"></td>
                    <td><a class="doc_save" onclick="getFile(this)">${attachment.attachmentName}</a></td>
                    <td>${attachment.dateUpload}</td>
                    <td>${attachment.attachmentComment}</td>
                    <td>
                        <button type="button" onclick="editAttachment(this)" style="width:auto" class="btn btn-info">
                            Edit
                        </button>
                    </td>
                </tr>
            </c:forEach>
            </tbody>
        </table>
        <br>
        <div>
            <a href="/FrontController?action=LIST_OF_CONTACTS">
                <button type="button" class="btn btn-danger">Cancel</button>
            </a>
            <button type="submit" class="btn btn-primary  btn-md">Save</button>
        </div>
    </form>
</div>

<!-----------------------------------POP UPS----------------------------->
<input type="hidden" id="popupAction">
<div id="idFormTel" class="modal">
    <form id="PhoneForm" class="modal-content animate">
        <div class="window">
            <input type="hidden" id="phoneId" name="id" value="${telephone.id}">

            <div>
                <label><b>Dialing Prefix</b></label>
                <input type="text" id="DialingPrefix" class="form-control dialingprefix" name="DialingPrefix"
                       value="${telephone.dialingPrefix}" pattern="[0-9]{1,4}"
                       title="This field should contain only numbers.">
                <span id="helpDialingPrefix" class="help-block" style="color: red; display: none">This field should contain 1-4 numbers.</span>
            </div>
            <div>
                <label><b>Provider Code</b></label>
                <input type="text" id="ProviderCode" class="form-control providercode" name="ProviderCode"
                       value="${telephone.providerCode}" pattern="[0-9]{1,4}"
                       title="This field should contain only numbers.">
                <span id="helpProviderCode" class="help-block" style="color: red; display: none">This field should contain 1-4 numbers.</span>
            </div>
            <div>
                <label><b>Phone Number</b></label>
                <input type="text" id="PhoneNumber" class="form-control numbertype" name="PhoneNumber"
                       value="${telephone.phoneNumber}" pattern="[0-9]{7,}"
                       title="This field should contain only numbers.">
                <span id="helpPhoneNumber" class="help-block" style="color: red; display: none">This field should contain at least 7 numbers.</span>
            </div>
            <div>
                <label><b>Number Type</b></label>
                <input type="text" id="NumberType" class="form-control phonenumber" name="NumberType"
                       value="${telephone.numberType}">
            </div>

            <div>
                <label><b>Commentary</b></label>
            </div>

            <div>
                <textarea name="phoneCommentary" id="phoneCommentary" class="textarea form-control"
                          rows="4">${telephone.commentary}</textarea>
            </div>
            <br>
            <div class="save">
                <button type="button" onclick="closePhoneForm()" class="btn btn-danger">Cancel</button>
                <button type="button" id="phoneButton" class="btn btn-success" onclick="savePhone()">Save</button>
            </div>
        </div>
    </form>
</div>
<div id="idFormAtch" class="modal">
    <form id="AtchForm" class="modal-content animate" action="/FrontController" method="post">
        <div class="window">
            <div id="file-container">
                <input type="file" id="File" name="File">
            </div>

            <div id="AttachmentFile" style="display: none">
                <div id="FileName">
                </div>
                <br>
                <button type="button" onclick="deleteFile()" style="width:auto" class="btn btn-info">
                    Delete
                </button>
            </div>

            <div>
                <label><b>Commentary</b></label>
            </div>

            <div>
                <textarea name="attachmentCommentary" id="attachmentCommentary" class="textarea form-control"
                          rows="6">${attachment.commentary}</textarea>
            </div>
            <br>
            <div class="clearfix">
                <button type="button" onclick="closeAtchForm()" class="btn btn-danger">Cancel</button>
                <button type="button" class="btn btn-success" onclick="saveAttachment()">Save</button>
            </div>
        </div>
    </form>
</div>
<!-- Latest compiled and minified JavaScript -->
<script src="/js/bootstrap.min.js"></script>

<script src="/js/phone.js?new"></script>

<script src="/js/attachment.js?new"></script>
</body>
</html>
