var AttachmentRow;

function addAttachment() {
    document.getElementById('idFormAtch').style.display = 'block';
    document.getElementById('popupAction').value = 'add';
}

function editAttachment(btn) {
    document.getElementById('popupAction').value = 'edit';
    var row = btn.parentElement.parentElement;
    var elems = row.getElementsByTagName("input");
    var result = new Array(elems.length);
    for (var i = 0; i < elems.length; i++) {
        result[i] = elems[i].value;
    }
    AttachmentRow = row;

    document.getElementById("attachmentCommentary").value = result[1];

    document.getElementById("file-container").style.display = 'none';
    document.getElementById("AttachmentFile").style.display = 'block';
    document.getElementById("FileName").innerHTML = result[4];

    document.getElementById('idFormAtch').style.display = 'block';
}

function checkDeleteAttachments() {
    var table = document.getElementById('attachmentTable');
    var inputs = table.getElementsByTagName('input');
    for (var inputi = inputs.length; inputi-- > 0;) {
        var input = inputs[inputi];

        if (input.type === 'checkbox' && input.checked) {
            document.getElementById("deleteAttachmentButton").disabled = false;
            break;
        }
        else {
            document.getElementById("deleteAttachmentButton").disabled = true;
        }
    }
}

function deleteAttachment() {
    var table = document.getElementById('attachmentTable');
    var rows = table.getElementsByTagName('tr');
    for (var rowi = rows.length; rowi-- > 0;) {
        var row = rows[rowi];
        var inputs = row.getElementsByTagName('input');
        for (var inputi = inputs.length; inputi-- > 0;) {
            var input = inputs[inputi];

            if (input.type === 'checkbox' && input.checked) {
                var action = row.getElementsByTagName('input')[0];
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
    document.getElementById("deleteAttachmentButton").disabled = true;
}

function saveAttachment() {
    var table = document.getElementById("attachmentTable");

    var action = document.getElementById('popupAction').value;
    //----------------Attachments Inputs-------------------
    var attachmentCommentary, attachmentAction, attachmentFile;
    var file = document.getElementById('File');
    //----------------Row inputs-------------------------
    var tr;
    var inputAA, inputAC, inputAP, inputAFN;
    var td0, td1, td2, td3, td4, td5;

    if (action === 'add') {
        var inputAID = document.createElement('input');
        inputAID.name = 'atchId';
        inputAID.type = 'hidden';

        inputAA = document.createElement('input');
        inputAA.name = 'atchAction';
        inputAA.type = 'hidden';
        inputAA.value = action;

        inputAC = document.createElement('input');
        inputAC.name = 'Commentary';
        inputAC.type = 'hidden';

        inputAP = document.createElement('input');
        inputAP.name = 'PathFile';
        inputAP.type = 'hidden';

        inputAFN = document.createElement('input');
        inputAFN.name = 'atchName';
        inputAFN.type = 'hidden';

        tr = document.createElement('tr');

        td0 = document.createElement('td');
        td0.hidden = 'true';
        td1 = document.createElement('td');
        td1.innerHTML = '<input type="checkbox" name="atchbox" value="${attachment.idattachment}" onchange="checkDeleteAttachments()">';
        td2 = document.createElement('td');
        td3 = document.createElement('td');
        td4 = document.createElement('td');
        td5 = document.createElement('td');

        tr.appendChild(inputAA);
        tr.appendChild(inputAC);
        tr.appendChild(inputAP)
        tr.appendChild(inputAID);
        tr.appendChild(inputAFN);
        tr.appendChild(td0);
        tr.appendChild(td1);
        tr.appendChild(td2);
        tr.appendChild(td3);
        tr.appendChild(td4);
        tr.appendChild(td5);

        table.appendChild(tr);
    } else {
        tr = AttachmentRow;

        inputAA = tr.getElementsByTagName('input')[0];
        inputAC = tr.getElementsByTagName('input')[1];
        inputAP = tr.getElementsByTagName('input')[2];
        inputAFN = tr.getElementsByTagName('input')[4];

        if (inputAA.value !== 'add') {
            inputAA.value = 'edit';
        }

        td0 = tr.getElementsByTagName('td')[0];
        // td1 = tr.getElementsByTagName('td')[1];
        td2 = tr.getElementsByTagName('td')[2];
        td3 = tr.getElementsByTagName('td')[3];
        td4 = tr.getElementsByTagName('td')[4];
        td5 = tr.getElementsByTagName('td')[5];

        if (document.getElementById("file-container").style.display === 'none') {
            inputAC.value = document.getElementById('attachmentCommentary').value;
            td3.innerHTML = formatDate(new Date());
            td4.innerHTML = inputAC.value;
            closeAtchForm();
            return;
        }
    }

    inputAC.value = document.getElementById('attachmentCommentary').value;
    //inputAP.value = file.value;
    inputAFN.value = file.value.substring(file.value.lastIndexOf('\\') + 1);

    td0.innerHTML = '';
    td0.appendChild(file);
    file.removeAttribute('id');


    document.getElementById('file-container').innerHTML = '<input type="file" id="File" name="File">';

    td2.innerHTML = inputAFN.value;
    td3.innerHTML = formatDate(new Date());
    td4.innerHTML = inputAC.value;
    td5.innerHTML = '<button type="button" onclick="editAttachment(this)" style="width:auto" class="btn btn-info">Edit</button>'

    closeAtchForm();
}

function formatDate(date) {

    var day = date.getDate();
    var month = date.getMonth();
    var year = date.getFullYear();

    return year + '-' + month + '-' + day;
}

function closeAtchForm() {
    document.getElementById('idFormAtch').style.display = 'none';

    document.getElementById('attachmentCommentary').value = '';
    document.getElementById('File').value = '';
    document.getElementById('FileName').innerHTML = '';

    document.getElementById("file-container").style.display = 'block';
    document.getElementById("AttachmentFile").style.display = 'none';
}

function getFile(btn) {
    var form = document.createElement('form');

    var row = btn.parentElement.parentElement;

    var action = document.createElement('input');
    action.type = 'hidden';
    action.name = 'action';
    action.value = 'GET_FILE';

    var id = document.createElement('input');
    id.type = 'hidden';
    id.name = 'attachmentId';
    id.value = "" + row.id;

    form.method = 'GET';
    // form.action = '/FrontController?action=GET_FILE&attachmentId=' + row.id;
    form.action = '/FrontController';

    form.appendChild(action);
    form.appendChild(id);

    document.body.appendChild(form);

    form.submit();
}

function deleteFile() {
    document.getElementById("AttachmentFile").style.display = 'none';
    document.getElementById("FileName").innerHTML = '';

    document.getElementById("file-container").style.display = 'block';
}