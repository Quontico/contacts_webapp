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