var phoneRow

function addPhone() {
    document.getElementById('idFormTel').style.display = 'block';
    document.getElementById('popupAction').value = 'add';
}

function editPhone(btn) {
    document.getElementById('idFormTel').style.display = 'block';
    var td = btn.parentElement;
    var row = td.parentElement;
    var elems = row.getElementsByTagName("input");
    var result = new Array(elems.length);
    for (var i = 0; i < elems.length; i++) {
        result[i] = elems[i].value;
    }
    phoneRow = row;

    document.getElementById("DialingPrefix").value = result[0];
    document.getElementById("ProviderCode").value = result[1];
    document.getElementById("PhoneNumber").value = result[2];
    document.getElementById("NumberType").value = result[3];
    document.getElementById("phoneCommentary").value = result[4];
    document.getElementById("phoneId").value = result[5];

    document.getElementById('popupAction').value = 'edit';
}

function savePhone() {
    var table = document.getElementById("phoneTable");
    var validPhone = true;
    var action = document.getElementById('popupAction').value;
    //----------popup Inputs----------------------
    var dialingPrefix = document.getElementById("DialingPrefix").value;
    var providerCode = document.getElementById("ProviderCode").value;
    var phoneNumber = document.getElementById("PhoneNumber").value;
    var numberType = document.getElementById("NumberType").value;
    var commentary = document.getElementById("phoneCommentary").value;
    var phoneID = document.getElementById("phoneId").value;


    //----------------validation------------------
    document.getElementById("DialingPrefix").style.borderColor = '';
    document.getElementById("ProviderCode").style.borderColor = '';
    document.getElementById("PhoneNumber").style.borderColor = '';

    document.getElementById("helpDialingPrefix").style.display = 'none';
    document.getElementById("helpProviderCode").style.display = 'none';
    document.getElementById("helpPhoneNumber").style.display = 'none';

    if ((/\D/.test(dialingPrefix)) || dialingPrefix.length < 1 || dialingPrefix.length > 4) {
        validPhone = false;
        document.getElementById("DialingPrefix").style.borderColor = 'red';
        document.getElementById("helpDialingPrefix").style.display = 'block';
    }
    if ((/\D/.test(providerCode)) || providerCode.length < 1 || providerCode.length > 4) {
        validPhone = false;
        document.getElementById("ProviderCode").style.borderColor = 'red';
        document.getElementById("helpProviderCode").style.display = 'block';
    }
    if ((/\D/.test(phoneNumber)) || phoneNumber.length < 7) {
        validPhone = false;
        document.getElementById("PhoneNumber").style.borderColor = 'red';
        document.getElementById("helpPhoneNumber").style.display = 'block';
    }
    if (!validPhone) {
        return false;
    }

    //--------------row Inputs--------------------
    var row;
    var inputDP, inputPC, inputPN, inputNT, inputC, inputPID, inputPA;
    var td1, td2, td3, td4, td5;

    if (action === 'add') {
        row = document.createElement('tr');

        inputDP = document.createElement('input');
        inputDP.type = 'hidden';
        inputDP.name = 'Dialing_Prefix';
        inputPC = document.createElement('input');
        inputPC.type = 'hidden';
        inputPC.name = 'Provider_Code';
        inputPN = document.createElement('input');
        inputPN.type = 'hidden';
        inputPN.name = 'Phone_Number';
        inputNT = document.createElement('input');
        inputNT.type = 'hidden';
        inputNT.name = 'Number_Type';
        inputC = document.createElement('input');
        inputC.type = 'hidden';
        inputC.name = 'Phone_Commentary';
        inputPID = document.createElement('input');
        inputPID.type = 'hidden';
        inputPID.name = 'Phone_ID';
        inputPA = document.createElement('input');
        inputPA.type = 'hidden';
        inputPA.name = 'Phone_Action';
        inputPA.value = 'add';

        td1 = document.createElement('td');
        td1.innerHTML = '<input type="checkbox" name="phnbox" value="${telephone.id}" onchange="checkDeletePhones()">';
        td2 = document.createElement('td');
        td3 = document.createElement('td');
        td4 = document.createElement('td');
        td5 = document.createElement('td');
        td5.innerHTML = '<button type="button" onclick="editPhone(this)" style="width:auto" class="btn btn-info">Edit</button>';

        row.appendChild(inputDP);
        row.appendChild(inputPC);
        row.appendChild(inputPN);
        row.appendChild(inputNT);
        row.appendChild(inputC);
        row.appendChild(inputPID);
        row.appendChild(inputPA);
        row.appendChild(td1);
        row.appendChild(td2);
        row.appendChild(td3);
        row.appendChild(td4);
        row.appendChild(td5);


        table.appendChild(row);
    } else {
        row = phoneRow;

        inputDP = row.getElementsByTagName('input')[0];
        inputPC = row.getElementsByTagName('input')[1];
        inputPN = row.getElementsByTagName('input')[2];
        inputNT = row.getElementsByTagName('input')[3];
        inputC = row.getElementsByTagName('input')[4];
        inputPID = row.getElementsByTagName('input')[5];
        inputPA = row.getElementsByTagName('input')[6];

        if (inputPA.value !== 'add') {
            inputPA.value = 'edit';
        }

        td1 = row.getElementsByTagName('td')[0];
        td2 = row.getElementsByTagName('td')[1];
        td3 = row.getElementsByTagName('td')[2];
        td4 = row.getElementsByTagName('td')[3];
        td5 = row.getElementsByTagName('td')[4];
    }

    inputDP.value = dialingPrefix;
    inputPC.value = providerCode;
    inputPN.value = phoneNumber;
    inputNT.value = numberType;
    inputC.value = commentary;
    inputPID.value = phoneID;

    td2.innerHTML = "+" + document.getElementById("DialingPrefix").value + "-" + document.getElementById("ProviderCode").value + "-" + document.getElementById("PhoneNumber").value;
    td3.innerHTML = numberType;
    td4.innerHTML = commentary;

    document.getElementById("DialingPrefix").value = '';
    document.getElementById("ProviderCode").value = '';
    document.getElementById("PhoneNumber").value = '';
    document.getElementById("NumberType").value = '';
    document.getElementById("phoneCommentary").value = '';
    document.getElementById("phoneId").value = '';

    document.getElementById('idFormTel').style.display = 'none';
}

function checkDeletePhones() {
    var table = document.getElementById('phoneTable');
    var inputs = table.getElementsByTagName('input');
    for (var inputi = inputs.length; inputi-- > 0;) {
        var input = inputs[inputi];

        if (input.type === 'checkbox' && input.checked) {
            document.getElementById("deletePhoneButton").disabled = false;
            break;
        }
        else {
            document.getElementById("deletePhoneButton").disabled = true;
        }
    }
}

function deletePhone() {
    var table = document.getElementById('phoneTable');
    var rows = table.getElementsByTagName('tr');
    for (var rowi = rows.length; rowi-- > 0;) {
        var row = rows[rowi];
        var inputs = row.getElementsByTagName('input');
        for (var inputi = inputs.length; inputi-- > 0;) {
            var input = inputs[inputi];

            if (input.type === 'checkbox' && input.checked) {
                var action = row.getElementsByTagName('input')[6];
                if (action.value === 'add') {
                    row.parentNode.removeChild(row);
                } else {
                    action.value = 'delete';
                    row.style.display = 'none';
                }

                break;
            }
        }
    }
    document.getElementById("deletePhoneButton").disabled = true;
}

function closePhoneForm() {
    document.getElementById("DialingPrefix").style.borderColor = '';
    document.getElementById("ProviderCode").style.borderColor = '';
    document.getElementById("PhoneNumber").style.borderColor = '';

    document.getElementById("helpDialingPrefix").style.display = 'none';
    document.getElementById("helpProviderCode").style.display = 'none';
    document.getElementById("helpPhoneNumber").style.display = 'none';

    document.getElementById("DialingPrefix").value = '';
    document.getElementById("ProviderCode").value = '';
    document.getElementById("PhoneNumber").value = '';
    document.getElementById("NumberType").value = '';
    document.getElementById("phoneCommentary").value = '';
    document.getElementById("phoneId").value = '';

    document.getElementById('idFormTel').style.display = 'none';
}