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

    var action = document.getElementById('popupAction').value;
    //----------popup Inputs----------------------
    var dialingPrefix = document.getElementById("DialingPrefix").value;
    var providerCode = document.getElementById("ProviderCode").value;
    var phoneNumber = document.getElementById("PhoneNumber").value;
    var numberType = document.getElementById("NumberType").value;
    var commentary = document.getElementById("phoneCommentary").value;
    var phoneID = document.getElementById("phoneId").value;

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
        td1.innerHTML = '<input type="checkbox" name="phnbox">';
        td2 = document.createElement('td');
        td3 = document.createElement('td');
        td4 = document.createElement('td');
        td5 = document.createElement('td');
        td5.innerHTML = '<button type="button" onclick="editPhone(this)" style="width:auto" class="btn btn-info"><span class="glyphicon glyphicon-edit"></span></button>';

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

    /*
    if (idphone === null) {
        row = document.createElement('tr');
        table.appendChild(row);
        // row = table.insertRow(rowCount);
    } else {
        var rows = table.getElementsByTagName('tr');
        for (var i = 0; i<rows.length; i++) {
            if(rows[i].id == idphone.value) {
                row = rows[i];
                break;
            }
        }
        var pos = row.rowIndex;
        table.deleteRow(pos);
        row = table.insertRow(pos);
        row.id = idphone.value;
    }

    var t1 = document.createElement("input");
        t1.type = 'hidden';
        t1.id = 'Dialing_Prefix';
        t1.value = document.getElementById("DialingPrefix").value;
        row.appendChild(t1);
    var t2 = document.createElement("input");
        t2.id = 'Provider_Code';
        t2.type = 'hidden';
        t2.value = document.getElementById("ProviderCode").value;
        row.appendChild(t2);
    var t3 = document.createElement("input");
        t3.id = 'Phone_Number';
        t3.type = 'hidden';
        t3.value = document.getElementById("PhoneNumber").value;
        row.appendChild(t3);
    var t4 = document.createElement("input");
        t4.id = 'Number_Type';
        t4.type = 'hidden';
        t4.value = document.getElementById("NumberType").value;
        row.appendChild(t4);
    var t5 = document.createElement("input");
        t5.id = 'phone_Commentary';
        t5.type = 'hidden';
        t5.value = document.getElementById("phoneCommentary").value;
        row.appendChild(t5);
    var cell1 = row.insertCell(0);
        var t6 = document.createElement("input");
        t6.type = 'checkbox';
        t6.id = 'phnbox';
        t6.value = document.getElementById("phoneId").value;
        cell1.appendChild(t6);
    var cell2 = row.insertCell(1);
        cell2.innerHTML = "+"+document.getElementById("DialingPrefix").value+"-"+document.getElementById("ProviderCode").value+"-"+document.getElementById("PhoneNumber").value;
    var cell3 = row.insertCell(2);
        cell3.innerHTML = document.getElementById("NumberType").value;
    var cell4 = row.insertCell(3);
        cell4.innerHTML = document.getElementById("phoneCommentary").value;
    var cell5 = row.insertCell(4);
        cell5.innerHTML = '<button type="button" onclick="editPhone(this)" style="width:auto" class="btn btn-info"><span class="glyphicon glyphicon-edit"></span></button>';
*/
    document.getElementById("DialingPrefix").value = '';
    document.getElementById("ProviderCode").value = '';
    document.getElementById("PhoneNumber").value = '';
    document.getElementById("NumberType").value = '';
    document.getElementById("phoneCommentary").value = '';
    document.getElementById("phoneId").value = '';

    document.getElementById('idFormTel').style.display = 'none';
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
}

function closePhoneForm() {

    document.getElementById("DialingPrefix").value = '';
    document.getElementById("ProviderCode").value = '';
    document.getElementById("PhoneNumber").value = '';
    document.getElementById("NumberType").value = '';
    document.getElementById("phoneCommentary").value = '';
    document.getElementById("phoneId").value = '';

    document.getElementById('idFormTel').style.display = 'none';
}