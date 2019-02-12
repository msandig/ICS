////////////////////////////////////////////////////// Backend Data for movieProgramm.html

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

/////////////////////index.html////////////////////////////////////////////////////////////////////////////////////

            if (document.URL.includes("index.html") == true || document.URL == "http://localhost:8080/") {

                oMe.presentation.map(function (oAttribute) {
                    if (oAttribute.movie.title == "Aquaman Collection") {
                        if (document.getElementById("titleAquaman").innerText == "") {
                            document.getElementById("titleAquaman").innerText = oAttribute.movie.title;
                            document.getElementById("titleAquaman2").innerText = oAttribute.movie.title;
                            document.getElementById("textAquaman2").innerText = oAttribute.movie.description;

                        }
                    } else if (oAttribute.movie.title == "Bumblebee") {
                        if (document.getElementById("titleBumblebee").innerText == "") {
                            document.getElementById("titleBumblebee").innerText = oAttribute.movie.title;
                            document.getElementById("titleBumblebee2").innerText = oAttribute.movie.title;
                            document.getElementById("textBumblebee2").innerText = oAttribute.movie.description;

                        }
                    } else if (oAttribute.movie.title == "Bird Box") {
                        if (document.getElementById("titleBird").innerText == "") {
                            document.getElementById("titleBird").innerText = oAttribute.movie.title;
                            document.getElementById("titleBird2").innerText = oAttribute.movie.title;
                            document.getElementById("textBird2").innerText = oAttribute.movie.description;

                        }
                    } else if (oAttribute.movie.title == "Mary Poppins Returns") {
                        if (document.getElementById("titleMary").innerText == "") {
                            document.getElementById("titleMary").innerText = oAttribute.movie.title;
                            document.getElementById("titleMary2").innerText = oAttribute.movie.title;
                            document.getElementById("textMary2").innerText = oAttribute.movie.description;

                        }
                    } else if (oAttribute.movie.title == "Venom") {
                        if (document.getElementById("titleVenom").innerText == "") {
                            document.getElementById("titleVenom").innerText = oAttribute.movie.title;
                            document.getElementById("titleVenom").innerText = oAttribute.movie.title;
                            document.getElementById("textVenom2").innerText = oAttribute.movie.description;

                        }
                    } else if (oAttribute.movie.title == "Phantastische Tierwesen: Grindelwalds Verbrechen") {
                        if (document.getElementById("titleTierwesen").innerText == "") {
                            document.getElementById("titleTierwesen").innerText = oAttribute.movie.title;
                            document.getElementById("titleTierwesen").innerText = oAttribute.movie.title;
                            document.getElementById("textTierwesen2").innerText = oAttribute.movie.description;

                        }

                    } else if (oAttribute.movie.title == "Spider-Man: A New Universe") {
                        if (document.getElementById("titleSpider").innerText == "") {
                            document.getElementById("titleSpider").innerText = oAttribute.movie.title;
                            document.getElementById("titleSpider2").innerText = oAttribute.movie.title;
                            document.getElementById("textSpider2").innerText = oAttribute.movie.description;

                        }

                    } else if (oAttribute.movie.title == "Robin Hood") {
                        if (document.getElementById("titleRobin").innerText == "") {
                            document.getElementById("titleRobin").innerText = oAttribute.movie.title;
                            document.getElementById("titleRobin2").innerText = oAttribute.movie.title;
                            document.getElementById("textRobin2").innerText = oAttribute.movie.description;

                        }

                    }



                });

            } else {

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

                            var id = oAttribute.movie.title.split(" ");
                            oMovie.cells[1].children[0].children[0].innerText = oAttribute.movie.title;
                            oMovie.cells[0].children[0].childNodes[1].id = id[0];
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

                            if (date == 0) {
                                date = 6;
                            } else {
                                date--;
                            }

                            var minutes = new Date(oAttribute.date).getMinutes();
                            if (minutes == 0) {
                                minutes = "0" + minutes;
                            }
                            var hours = new Date(oAttribute.date).getHours();

                            if (oMovie.cells[1].children[1].children[0].children[1].children[0].cells[date].innerText == "") {
                                oMovie.cells[1].children[1].children[0].children[1].children[0].cells[date].children[0].innerText = hours + ":" + minutes;
                                oMovie.cells[1].children[1].children[0].children[1].children[0].cells[date].children[0].id = oAttribute.uuid;
                            } else if (oMovie.cells[1].children[1].children[0].children[1].children[1].cells[date].innerText == "") {
                                oMovie.cells[1].children[1].children[0].children[1].children[1].cells[date].children[0].innerText = hours + ":" + minutes;
                                oMovie.cells[1].children[1].children[0].children[1].children[1].cells[date].children[0].id = oAttribute.uuid;
                            } else if (oMovie.cells[1].children[1].children[0].children[1].children[2].cells[date].innerText == "") {
                                oMovie.cells[1].children[1].children[0].children[1].children[2].cells[date].children[0].innerText = hours + ":" + minutes;
                                oMovie.cells[1].children[1].children[0].children[1].children[2].cells[date].children[0].id = oAttribute.uuid;
                            } else if (oMovie.cells[1].children[1].children[0].children[1].children[3].cells[date].innerText == "") {
                                oMovie.cells[1].children[1].children[0].children[1].children[3].cells[date].children[0].innerText = hours + ":" + minutes;
                                oMovie.cells[1].children[1].children[0].children[1].children[3].cells[date].children[0].id = oAttribute.uuid;
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
                                    if (date == 0) {
                                        date = 6;
                                    } else {
                                        date--;
                                    }

                                    var minutes = new Date(oAttribute.date).getMinutes();
                                    if (minutes == 0) {
                                        minutes = "0" + minutes;
                                    }
                                    var hours = new Date(oAttribute.date).getHours()


                                    if (row.cells[1].children[1].children[0].children[1].children[0].cells[date].innerText == "") {
                                        row.cells[1].children[1].children[0].children[1].children[0].cells[date].children[0].innerText = hours + ":" + minutes;
                                        row.cells[1].children[1].children[0].children[1].children[0].cells[date].children[0].id = oAttribute.uuid;
                                    } else if (row.cells[1].children[1].children[0].children[1].children[1].cells[date].innerText == "") {
                                        row.cells[1].children[1].children[0].children[1].children[1].cells[date].children[0].innerText = hours + ":" + minutes;
                                        row.cells[1].children[1].children[0].children[1].children[1].cells[date].children[0].id = oAttribute.uuid;
                                    } else if (row.cells[1].children[1].children[0].children[1].children[2].cells[date].innerText == "") {
                                        row.cells[1].children[1].children[0].children[1].children[2].cells[date].children[0].innerText = hours + ":" + minutes;
                                        row.cells[1].children[1].children[0].children[1].children[2].cells[date].children[0].id = oAttribute.uuid;
                                    } else if (row.cells[1].children[1].children[0].children[1].children[3].cells[date].innerText == "") {
                                        row.cells[1].children[1].children[0].children[1].children[3].cells[date].children[0].innerText = hours + ":" + minutes;
                                        row.cells[1].children[1].children[0].children[1].children[3].cells[date].children[0].id = oAttribute.uuid;
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

                                    if(oAttribute.movie.title == "Spider-Man: A New Universe"){
                                        var id = oAttribute.movie.title.split("-")
                                    } else {
                                        var id = oAttribute.movie.title.split(" ");
                                    }
                                    row.cells[0].children[0].childNodes[1].id = id[0];
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
                                    if (date == 0) {
                                        date = 6;
                                    } else {
                                        date--;
                                    }

                                    var minutes = new Date(oAttribute.date).getMinutes();
                                    if (minutes == 0) {
                                        minutes = "0" + minutes;
                                    }
                                    var hours = new Date(oAttribute.date).getHours()

                                    if (row.cells[1].children[1].children[0].children[1].children[0].cells[date].innerText == "") {
                                        row.cells[1].children[1].children[0].children[1].children[0].cells[date].children[0].innerText = hours + ":" + minutes;
                                        row.cells[1].children[1].children[0].children[1].children[0].cells[date].children[0].id = oAttribute.uuid;
                                    } else if (row.cells[1].children[1].children[0].children[1].children[1].cells[date].innerText == "") {
                                        row.cells[1].children[1].children[0].children[1].children[1].cells[date].children[0].innerText = hours + ":" + minutes;
                                        row.cells[1].children[1].children[0].children[1].children[1].cells[date].children[0].id = oAttribute.uuid;
                                    } else if (row.cells[1].children[1].children[0].children[1].children[2].cells[date].innerText == "") {
                                        row.cells[1].children[1].children[0].children[1].children[2].cells[date].children[0].innerText = hours + ":" + minutes;
                                        row.cells[1].children[1].children[0].children[1].children[2].cells[date].children[0].id = oAttribute.uuid;
                                    } else if (row.cells[1].children[1].children[0].children[1].children[3].cells[date].innerText == "") {
                                        row.cells[1].children[1].children[0].children[1].children[3].cells[date].children[0].innerText = hours + ":" + minutes;
                                        row.cells[1].children[1].children[0].children[1].children[3].cells[date].children[0].id = oAttribute.uuid;
                                    }
                                    return;
                                }
                            } else {
                                i++;

                                row = tMovieProgram.insertRow(i + 1);
                                row.appendChild(childTitle);
                                row.appendChild(childTable);
                                row.appendChild(childEnd);

                                var id = oAttribute.movie.title.split(" ");
                                row.cells[0].children[0].childNodes[1].id = id[0];
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

                                if (date == 0) {
                                    date = 6;
                                } else {
                                    date--;
                                }

                                var minutes = new Date(oAttribute.date).getMinutes();
                                if (minutes == 0) {
                                    minutes = "0" + minutes;
                                }
                                var hours = new Date(oAttribute.date).getHours()

                                if (row.cells[1].children[1].children[0].children[1].children[0].cells[date].innerText == "") {
                                    row.cells[1].children[1].children[0].children[1].children[0].cells[date].children[0].innerText = hours + ":" + minutes;
                                    row.cells[1].children[1].children[0].children[1].children[0].cells[date].children[0].id = oAttribute.uuid;
                                } else if (row.cells[1].children[1].children[0].children[1].children[1].cells[date].innerText == "") {
                                    row.cells[1].children[1].children[0].children[1].children[1].cells[date].children[0].innerText = hours + ":" + minutes;
                                    row.cells[1].children[1].children[0].children[1].children[1].cells[date].children[0].id = oAttribute.uuid;
                                } else if (row.cells[1].children[1].children[0].children[1].children[2].cells[date].innerText == "") {
                                    row.cells[1].children[1].children[0].children[1].children[2].cells[date].children[0].innerText = hours + ":" + minutes;
                                    row.cells[1].children[1].children[0].children[1].children[2].cells[date].children[0].id = oAttribute.uuid;
                                } else if (row.cells[1].children[1].children[0].children[1].children[3].cells[date].innerText == "") {
                                    row.cells[1].children[1].children[0].children[1].children[3].cells[date].children[0].innerText = hours + ":" + minutes;
                                    row.cells[1].children[1].children[0].children[1].children[3].cells[date].children[0].id = oAttribute.uuid;
                                }
                                return;
                            }
                        }
                    }); // oPresentation

                    return;
                }); // oTable
                    $('#Aquaman').attr("src", "img/movies/aquaman.jpg");
                    $('#Bumblebee').attr("src", "img/movies/bumblebee.jpg");
                    $('#Bird').attr("src", "img/movies/birdbox.jpg");
                    $('#Mary').attr("src", "img/movies/marypoppins.jpg");
                    $('#Venom').attr("src", "img/movies/venom.jpg");
                    $('#Phantastische').attr("src", "img/movies/grindelwalds_verbrechen.jpg");
                    $('#Spider').attr("src", "img/movies/spiderman_into_the_spider_verse.jpg");
                    $('#Robin').attr("src", "img/movies/robin-hood.jpg");

            }

        }, 2); //Timeout

    }   //if

}, 500); // TimeOut





