function formValidation() {
    var FirstName = document.getElementById("FirstName");
    var Surname = document.getElementById("Surname");
    var MiddleName = document.getElementById("MiddleName");
    var Birthdate = document.getElementById("Birthdate");
    var Gender = document.getElementById("Gender");
    var Marital = document.getElementById("Marital");
    var Citizenship = document.getElementById("Citizenship");
    var Workplace = document.getElementById("Workplace");
    var Email = document.getElementById("Email");
    var Website = document.getElementById("Website");
    var Country = document.getElementById("Country");
    var City = document.getElementById("City");
    var Street = document.getElementById("Street");
    var House = document.getElementById("House");
    var Apartment = document.getElementById("Apartment");
    var Postcode = document.getElementById("Postcode");
    if (allLetter(FirstName)) {
        if (allLetter(Surname)) {
            if (allLetter(MiddleName)) {
                if (validateDate(Birthdate)) {
                    if (validselect(Gender)) {
                        if (validselect(Marital)) {
                            if (allLetter(Citizenship)) {
                                if (allLetter(Workplace)) {
                                    if (ValidateEmail(Email)) {
                                        if (ValidateURL(Website)) {
                                            if (allLetter(Country)) {
                                                if (allLetter(City)) {
                                                    if (alphanumeric(Street)) {
                                                        if (alphanumeric(House)) {
                                                            if (alphanumeric(Apartment)) {
                                                                if (alphanumeric(Postcode)) {

                                                                }
                                                            }
                                                        }
                                                    }
                                                }
                                            }
                                        }
                                    }
                                }
                            }
                        }
                    }
                }
            }
        }
    }
    return false;

}

function allLetter(uname) {
    var letters = [\u00A0 - \uD7FF\uF900 - \uFDCF\uFDF0 - \uFFEF];
    if (uname.value.match(letters)) {
        return true;
    }
    else {
        alert(uname.name + ' must have alphabet characters only');
        uname.focus();
        return false;
    }
}

function alphanumeric(uadd) {
    var letters = /^[0-9a-zA-Z]+$/;
    if (uadd.value.match(letters)) {
        return true;
    }
    else {
        alert(uadd.name + ' must have alphanumeric characters only');
        uadd.focus();
        return false;
    }
}

function validateDate(date) {
    var datereg = /^(19|20)\d\d[- /.](0[1-9]|1[012])[- /.](0[1-9]|[12][0-9]|3[01])$/;
    if (date.value.match(datereg)) {
        return true;
    }
    else {
        alert("You have entered an invalid " + date.name + " !");
        date.focus();
        return false;
    }
}

function ValidateEmail(uemail) {
    var mailformat = /^\w+([\.-]?\w+)*@\w+([\.-]?\w+)*(\.\w{2,3})+$/;
    if (uemail.value.match(mailformat)) {
        return true;
    }
    else {
        alert("You have entered an invalid " + uemail.name + " !");
        uemail.focus();
        return false;
    }
}

function ValidateURL(urlstring) {
    var urlformat = /((http|https)\:\/\/)?[a-zA-Z0-9\.\/\?\:@\-_=#]+\.([a-zA-Z0-9\&\.\/\?\:@\-_=#])*/g;
    if (urlstring.value.match(urlformat)) {
        return true;
    }
    else {
        alert("You have entered an invalid " + urlstring.name + " !");
        urlstring.focus();
        return false;
    }
}

function validselect(select) {
    x = 0;

    if (select.value !== "Male") {
        x++;
    }
    if (select.value !== "Female") {
        x++;
    }
    if (select.value !== "Single") {
        x++;
    }
    if (select.value !== "Married") {
        x++;
    }
    if (select.value !== "") {
        x++;
    }
    if (x === 0) {
        alert('Select right option');
        select.focus();
        return false;
    }
    else {
        alert('Form Succesfully Submitted');
        return true;
    }
}
