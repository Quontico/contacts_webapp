function takeTemplate() {
    var template = document.getElementById("Template").value;
    if (template === 'Birthday') {
        document.getElementById("Message").value = document.getElementById("Birthday").value;
    }

    if (template === 'New Year') {
        document.getElementById("Message").value = document.getElementById("NewYear").value;
    }
}
