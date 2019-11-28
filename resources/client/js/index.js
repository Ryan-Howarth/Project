function pageLoad() {

    let now = new Date();

    let myHTML = '<div style="text-align:center;">'
        + '<h1>I once caught a fish and it was this big!</h1>'
        + '<img src="/client/img/bush-0710.jpg"  alt="Logo"/>'
        + '<div style="font-style: italic;">'
        + 'Generated at ' + now.toLocaleTimeString()
        + '</div>'
        + '</div>';

    document.getElementById("testDiv").innerHTML = myHTML;

}
