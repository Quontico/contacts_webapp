function refreshPage() {
    var action = document.createElement('input');
    action.type = 'hidden';
    action.name = 'action';
    if (document.getElementById("IsSearch").value === "SEARCH_CONTACTS") {
        action.value = "SEARCH_CONTACTS";
    } else {
        action.value = 'LIST_OF_CONTACTS';
    }

    document.forms[0].appendChild(action);
    document.forms[0].submit();
}

function changeRecCount() {
    select = document.getElementById('Page');
    select.options[0].selected = true;
    refreshPage();
}

function deleteContacts() {
    var table = document.getElementById('tableContact');
    var inputs = table.getElementsByTagName('input');
    for (var inputi = inputs.length; inputi-- > 0;) {
        var input = inputs[inputi];

        if (input.type === 'checkbox' && input.checked) {
            document.getElementById("deleteButton").disabled = false;
            break;
        }
        else {
            document.getElementById("deleteButton").disabled = true;
        }
    }
}
