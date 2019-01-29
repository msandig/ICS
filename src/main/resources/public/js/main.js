
//Backend Data for movieProgramm.html

this.presentation = "";
var oMe = this;
var url = "http://localhost:8080/service/get/presentations?start=1549843241000&end=1550447981000";

function createCORSRequest(method, url) {
    var xhr = new XMLHttpRequest();
    if ("withCredentials" in xhr) {
        // Check if the XMLHttpRequest object has a "withCredentials" property.
        // "withCredentials" only exists on XMLHTTPRequest2 objects.
        xhr.open(method, url, true);
    } else if (typeof XDomainRequest != "undefined") {
        // Otherwise, check if XDomainRequest.
        // XDomainRequest only exists in IE, and is IE's way of making CORS requests.
        xhr = new XDomainRequest();
        xhr.open(method, url);
    } else {
        // Otherwise, CORS is not supported by the browser.
        xhr = null;
    }
    return xhr;
}

var xhr = createCORSRequest('GET', url);
if (!xhr) {
    throw new Error('CORS not supported');
}

xhr.send();

window.setTimeout(
    function () {
        xhr.onreadystatechange = function () {
            if (this.readyState == 4) {
                oMe.presentation = JSON.parse(xhr.response);
            }
        }
    }, 0);

window.setTimeout(function () {

    if (oMe.presentation != "") {

        window.setTimeout(function () {
            var date = "";
            var row = "";
            var i = 0;
            var childTitleRow = "";
            var childTableRow = "";
            var childEndRow = "";
            var tMovieProgram = document.getElementById("tableMovieProgram");
            var childTitle = document.getElementById("childTitle").cloneNode(true);
            var childTable = document.getElementById("childTable").cloneNode(true);
            var childEnd = document.getElementById("childEnd").cloneNode(true);
            var childTitle1 = document.getElementById("childTitle").cloneNode(true);
            var childTable1 = document.getElementById("childTable").cloneNode(true);
            var childEnd1 = document.getElementById("childEnd").cloneNode(true);
            var childTitle2 = document.getElementById("childTitle").cloneNode(true)
            var childTable2 = document.getElementById("childTable").cloneNode(true)
            var childEnd2 = document.getElementById("childEnd").cloneNode(true);
            var childTitle3 = document.getElementById("childTitle").cloneNode(true)
            var childTable3 = document.getElementById("childTable").cloneNode(true)
            var childEnd3 = document.getElementById("childEnd").cloneNode(true);
            var childTitle4 = document.getElementById("childTitle").cloneNode(true)
            var childTable4 = document.getElementById("childTable").cloneNode(true)
            var childEnd4 = document.getElementById("childEnd").cloneNode(true);
            var childTitle5 = document.getElementById("childTitle").cloneNode(true)
            var childTable5 = document.getElementById("childTable").cloneNode(true)
            var childEnd5 = document.getElementById("childEnd").cloneNode(true);
            var childTitle6 = document.getElementById("childTitle").cloneNode(true)
            var childTable6 = document.getElementById("childTable").cloneNode(true)
            var childEnd6 = document.getElementById("childEnd").cloneNode(true);
            var childTitle7 = document.getElementById("childTitle").cloneNode(true)
            var childTable7 = document.getElementById("childTable").cloneNode(true)
            var childEnd7 = document.getElementById("childEnd").cloneNode(true);

            var aTable = Array.prototype.slice.call(tMovieProgram.rows);
            aTable.map(function (oMovie) {

                if (oMovie.cells.length == 0) {
                    return;
                }

                oMe.presentation.map(function (oAttribute) {

                    if (oMovie.cells[1].children[0].children[0].innerText == "" || oMovie.cells[1].children[0].children[0].innerText == oAttribute.movie.title) {

                        oMovie.cells[0].children[0].childNodes[1].scr = oAttribute.movie.picture;
                        oMovie.cells[1].children[0].children[0].innerText = oAttribute.movie.title;
                        oMovie.cells[1].children[0].children[1].innerText = "Laufzeit: " + oAttribute.movie.runTime;
                        oMovie.cells[1].children[0].children[2].innerText = "FSK: " + oAttribute.movie.fsk;
                        oMovie.cells[1].children[0].children[3].innerText = "Filmstart: " + oAttribute.movie.productionYear;

                        if (new Date(oAttribute.date).getDay() == 0) {
                            date = 0;
                        } else if (new Date(oAttribute.date).getDay() == 1) {
                            date = 1;
                        } else if (new Date(oAttribute.date).getDay() == 2) {
                            date = 2;
                        } else if (new Date(oAttribute.date).getDay() == 3) {
                            date = 3;
                        } else if (new Date(oAttribute.date).getDay() == 4) {
                            date = 4;
                        } else if (new Date(oAttribute.date).getDay() == 5) {
                            date = 5;
                        } else if (new Date(oAttribute.date).getDay() == 6) {
                            date = 6;
                        }

                        if (oMovie.cells[1].children[1].children[0].children[1].children[0].cells[date].innerText == "") {
                            oMovie.cells[1].children[1].children[0].children[1].children[0].cells[date].innerText = new Date(oAttribute.date).getHours() + ":" + new Date(oAttribute.date).getMinutes();
                        } else if (oMovie.cells[1].children[1].children[0].children[1].children[1].cells[date].innerText == "") {
                            oMovie.cells[1].children[1].children[0].children[1].children[1].cells[date].innerText = new Date(oAttribute.date).getHours() + ":" + new Date(oAttribute.date).getMinutes();
                        } else if (oMovie.cells[1].children[1].children[0].children[1].children[2].cells[date].innerText == "") {
                            oMovie.cells[1].children[1].children[0].children[1].children[2].cells[date].innerText = new Date(oAttribute.date).getHours() + ":" + new Date(oAttribute.date).getMinutes();
                        } else if (oMovie.cells[1].children[1].children[0].children[1].children[3].cells[date].innerText == "") {
                            oMovie.cells[1].children[1].children[0].children[1].children[3].cells[date].innerText = new Date(oAttribute.date).getHours() + ":" + new Date(oAttribute.date).getMinutes();
                        }
                        return;
                    }   //if
                    else {
                        if (row != "") {

                            if (row.cells[1].children[0].children[0].innerText == oAttribute.movie.title) {

                                if (new Date(oAttribute.date).getDay() == 0) {
                                    date = 0;
                                } else if (new Date(oAttribute.date).getDay() == 1) {
                                    date = 1;
                                } else if (new Date(oAttribute.date).getDay() == 2) {
                                    date = 2;
                                } else if (new Date(oAttribute.date).getDay() == 3) {
                                    date = 3;
                                } else if (new Date(oAttribute.date).getDay() == 4) {
                                    date = 4;
                                } else if (new Date(oAttribute.date).getDay() == 5) {
                                    date = 5;
                                } else if (new Date(oAttribute.date).getDay() == 6) {
                                    date = 6;
                                }
                                if (row.cells[1].children[1].children[0].children[1].children[0].cells[date].innerText == "") {
                                    row.cells[1].children[1].children[0].children[1].children[0].cells[date].innerText = new Date(oAttribute.date).getHours() + ":" + new Date(oAttribute.date).getMinutes();
                                } else if (row.cells[1].children[1].children[0].children[1].children[1].cells[date].innerText == "") {
                                    row.cells[1].children[1].children[0].children[1].children[1].cells[date].innerText = new Date(oAttribute.date).getHours() + ":" + new Date(oAttribute.date).getMinutes();
                                } else if (row.cells[1].children[1].children[0].children[1].children[2].cells[date].innerText == "") {
                                    row.cells[1].children[1].children[0].children[1].children[2].cells[date].innerText = new Date(oAttribute.date).getHours() + ":" + new Date(oAttribute.date).getMinutes();
                                } else if (row.cells[1].children[1].children[0].children[1].children[3].cells[date].innerText == "") {
                                    row.cells[1].children[1].children[0].children[1].children[3].cells[date].innerText = new Date(oAttribute.date).getHours() + ":" + new Date(oAttribute.date).getMinutes();
                                }
                                return;
                            } else {
                                i++;

                                if (i == 1) {
                                    childTitleRow = childTitle1;
                                    childTableRow = childTable1;
                                    childEndRow = childEnd1;
                                } else if (i == 2) {
                                    childTitleRow = childTitle2;
                                    childTableRow = childTable2;
                                    childEndRow = childEnd2;
                                } else if (i == 3) {
                                    childTitleRow = childTitle3;
                                    childTableRow = childTable3;
                                    childEndRow = childEnd3;
                                } else if (i == 4) {
                                    childTitleRow = childTitle4;
                                    childTableRow = childTable4;
                                    childEndRow = childEnd4;
                                } else if (i == 5) {
                                    childTitleRow = childTitle5;
                                    childTableRow = childTable5;
                                    childEndRow = childEnd5;
                                } else if (i == 6) {
                                    childTitleRow = childTitle6;
                                    childTableRow = childTable6;
                                    childEndRow = childEnd6;
                                } else if (i == 7) {
                                    childTitleRow = childTitle7;
                                    childTableRow = childTable7;
                                    childEndRow = childEnd7;
                                }
                                row = tMovieProgram.insertRow(i + 1);
                                row.appendChild(childTitleRow);
                                row.appendChild(childTableRow);
                                row.appendChild(childEndRow);

                                row.cells[0].children[0].childNodes[1].scr = oAttribute.movie.picture;
                                row.cells[1].children[0].children[0].innerText = oAttribute.movie.title;
                                row.cells[1].children[0].children[1].innerText = "Laufzeit: " + oAttribute.movie.runTime;
                                row.cells[1].children[0].children[2].innerText = "FSK: " + oAttribute.movie.fsk;
                                row.cells[1].children[0].children[3].innerText = "Filmstart: " + oAttribute.movie.productionYear;

                                if (new Date(oAttribute.date).getDay() == 0) {
                                    date = 0;
                                } else if (new Date(oAttribute.date).getDay() == 1) {
                                    date = 1;
                                } else if (new Date(oAttribute.date).getDay() == 2) {
                                    date = 2;
                                } else if (new Date(oAttribute.date).getDay() == 3) {
                                    date = 3;
                                } else if (new Date(oAttribute.date).getDay() == 4) {
                                    date = 4;
                                } else if (new Date(oAttribute.date).getDay() == 5) {
                                    date = 5;
                                } else if (new Date(oAttribute.date).getDay() == 6) {
                                    date = 6;
                                }
                                if (row.cells[1].children[1].children[0].children[1].children[0].cells[date].innerText == "") {
                                    row.cells[1].children[1].children[0].children[1].children[0].cells[date].innerText = new Date(oAttribute.date).getHours() + ":" + new Date(oAttribute.date).getMinutes();
                                } else if (row.cells[1].children[1].children[0].children[1].children[1].cells[date].innerText == "") {
                                    row.cells[1].children[1].children[0].children[1].children[1].cells[date].innerText = new Date(oAttribute.date).getHours() + ":" + new Date(oAttribute.date).getMinutes();
                                } else if (row.cells[1].children[1].children[0].children[1].children[2].cells[date].innerText == "") {
                                    row.cells[1].children[1].children[0].children[1].children[2].cells[date].innerText = new Date(oAttribute.date).getHours() + ":" + new Date(oAttribute.date).getMinutes();
                                } else if (row.cells[1].children[1].children[0].children[1].children[3].cells[date].innerText == "") {
                                    row.cells[1].children[1].children[0].children[1].children[3].cells[date].innerText = new Date(oAttribute.date).getHours() + ":" + new Date(oAttribute.date).getMinutes();
                                }
                                return;
                            }
                        } else {
                            i++;

                            row = tMovieProgram.insertRow(i + 1);
                            row.appendChild(childTitle);
                            row.appendChild(childTable);
                            row.appendChild(childEnd);

                            row.cells[0].children[0].childNodes[1].scr = oAttribute.movie.picture;
                            row.cells[1].children[0].children[0].innerText = oAttribute.movie.title;
                            row.cells[1].children[0].children[1].innerText = "Laufzeit: " + oAttribute.movie.runTime;
                            row.cells[1].children[0].children[2].innerText = "FSK: " + oAttribute.movie.fsk;
                            row.cells[1].children[0].children[3].innerText = "Filmstart: " + oAttribute.movie.productionYear;

                            if (new Date(oAttribute.date).getDay() == 0) {
                                var date = 0;
                            } else if (new Date(oAttribute.date).getDay() == 1) {
                                var date = 1;
                            } else if (new Date(oAttribute.date).getDay() == 2) {
                                var date = 2;
                            } else if (new Date(oAttribute.date).getDay() == 3) {
                                var date = 3;
                            } else if (new Date(oAttribute.date).getDay() == 4) {
                                var date = 4;
                            } else if (new Date(oAttribute.date).getDay() == 5) {
                                var date = 5;
                            } else if (new Date(oAttribute.date).getDay() == 6) {
                                var date = 6;
                            }
                            if (row.cells[1].children[1].children[0].children[1].children[0].cells[date].innerText == "") {
                                row.cells[1].children[1].children[0].children[1].children[0].cells[date].innerText = new Date(oAttribute.date).getHours() + ":" + new Date(oAttribute.date).getMinutes();
                            } else if (row.cells[1].children[1].children[0].children[1].children[1].cells[date].innerText == "") {
                                row.cells[1].children[1].children[0].children[1].children[1].cells[date].innerText = new Date(oAttribute.date).getHours() + ":" + new Date(oAttribute.date).getMinutes();
                            } else if (row.cells[1].children[1].children[0].children[1].children[2].cells[date].innerText == "") {
                                row.cells[1].children[1].children[0].children[1].children[2].cells[date].innerText = new Date(oAttribute.date).getHours() + ":" + new Date(oAttribute.date).getMinutes();
                            } else if (row.cells[1].children[1].children[0].children[1].children[3].cells[date].innerText == "") {
                                row.cells[1].children[1].children[0].children[1].children[3].cells[date].innerText = new Date(oAttribute.date).getHours() + ":" + new Date(oAttribute.date).getMinutes();
                            }
                            return;
                        }
                    }
                }); // oPresentation

                return;
            }); // oTable

        }, 2); //Timeout

    }   //if

}, 300); // TimeOut


