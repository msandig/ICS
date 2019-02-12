var oMe = this;
var url = "http://localhost:8080/service/get/presentations/";
this.movie = "";
this.date = "";
this.presentationUuid = "";
this.room = "";
this.BusySeat = [];

var price = 10; //price
var id1 = [];
var id2 = [];
var id3 = [];
var id4 = [];
var id5 = [];
var id6 = [];
var id7 = [];
var id8 = [];
var id9 = [];
var id10 = [];
var id11 = [];
var id12 = [];
var id13 = [];
var seatCategory1 = [];
var seatCategory2 = [];
var seatCategory3 = [];
var seatCategory4 = [];
var seatCategory5 = [];
var seatCategory6 = [];
var seatCategory7 = [];
var seatCategory8 = [];
var seatCategory9 = [];
var seatCategory10 = [];
var seatCategory11 = [];
var seatCategory12 = [];
var seatCategory13 = [];

$(document).ready(function () {

    var urlMovie = localStorage.getItem("movie");


    url = url + urlMovie;

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
                    oMe.movie = xhr.response;
                }
            }

            oMe.movie = xhr.response;
        }, 0);

    window.setTimeout(function () {

        var movie = JSON.parse(oMe.movie);
        var title = movie.movie.title;
        this.date = movie.date;
        this.presentationUuid = movie.uuid;
        this.room = movie.room;
        var date = new Date(movie.date).getDate();
        var month = new Date(movie.date).getMonth();
        if (month == 0) {
            month = "Januar";
        } else if (month == 1) {
            month = "Februar";
        } else if (month == 2) {
            month = "März";
        } else if (month == 2) {
            month = "April";
        } else if (month == 2) {
            month = "Mai";
        } else if (month == 2) {
            month = "Juni";
        } else if (month == 2) {
            month = "Juli";
        } else if (month == 2) {
            month = "August";
        } else if (month == 2) {
            month = "September";
        } else if (month == 2) {
            month = "Oktober";
        } else if (month == 2) {
            month = "November";
        } else if (month == 2) {
            month = "Dezember";
        }
        document.getElementById("title").innerText = title;
        document.getElementById("date").innerText = "   " + date + ". " + month;
        var seats = Object.values(movie.room.seats);
        var row1 = [];
        var row2 = [];
        var row3 = [];
        var row4 = [];
        var row5 = [];
        var row6 = [];
        var row7 = [];
        var row8 = [];
        var row9 = [];
        var row10 = [];
        var row11 = [];
        var row12 = [];
        var row13 = [];


        seats.map(function (oAttribute) {
            oAttribute;
            if (oAttribute.row == 1) {
                if (oAttribute.seatInformation != null) {
                    if (oAttribute.seatInformation.locked == false && oAttribute.seatInformation.busy == false) {
                        row1[oAttribute.number] = "a";
                    } else {
                        row1[oAttribute.number] = "c";
                    }
                } else {
                    row1[oAttribute.number] = "a"
                }
                id1[oAttribute.number] = oAttribute.uuid;
                seatCategory1[oAttribute.number] = oAttribute.seatCategory;
                return;
            } else if (oAttribute.row == 2) {
                if (oAttribute.seatInformation != null) {
                    if (oAttribute.seatInformation.locked == false && oAttribute.seatInformation.busy == false) {
                        row2[oAttribute.number] = "a";
                    } else {
                        row2[oAttribute.number] = "c";
                    }
                } else {
                    row2[oAttribute.number] = "a"
                }
                id2[oAttribute.number] = oAttribute.uuid;
                seatCategory2[oAttribute.number] = oAttribute.seatCategory;
                return;
            } else if (oAttribute.row == 3) {
                if (oAttribute.seatInformation != null) {
                    if (oAttribute.seatInformation.locked == false && oAttribute.seatInformation.busy == false) {
                        row3[oAttribute.number] = "a";
                    } else {
                        row3[oAttribute.number] = "c";
                    }
                } else {
                    row3[oAttribute.number] = "a"
                }
                id3[oAttribute.number] = oAttribute.uuid;
                seatCategory3[oAttribute.number] = oAttribute.seatCategory;
                return;
            } else if (oAttribute.row == 4) {
                if (oAttribute.seatInformation != null) {
                    if (oAttribute.seatInformation.locked == false && oAttribute.seatInformation.busy == false) {
                        row4[oAttribute.number] = "a";
                    } else {
                        row4[oAttribute.number] = "c";
                    }
                } else {
                    row4[oAttribute.number] = "a"
                }
                id4[oAttribute.number] = oAttribute.uuid;
                seatCategory4[oAttribute.number] = oAttribute.seatCategory;
                return;
            } else if (oAttribute.row == 5) {
                if (oAttribute.seatInformation != null) {
                    if (oAttribute.seatInformation.locked == false && oAttribute.seatInformation.busy == false) {
                        row5[oAttribute.number] = "a";
                    } else {
                        row5[oAttribute.number] = "c";
                    }
                } else {
                    row5[oAttribute.number] = "a"
                }
                id5[oAttribute.number] = oAttribute.uuid;
                seatCategory5[oAttribute.number] = oAttribute.seatCategory;
                return;
            } else if (oAttribute.row == 6) {
                if (oAttribute.seatInformation != null) {
                    if (oAttribute.seatInformation.locked == false && oAttribute.seatInformation.busy == false) {
                        row6[oAttribute.number] = "a";
                    } else {
                        row6[oAttribute.number] = "c";
                    }
                } else {
                    row6[oAttribute.number] = "a"
                }
                id6[oAttribute.number] = oAttribute.uuid;
                seatCategory6[oAttribute.number] = oAttribute.seatCategory;
                return;
            } else if (oAttribute.row == 7) {
                if (oAttribute.seatInformation != null) {
                    if (oAttribute.seatInformation.locked == false && oAttribute.seatInformation.busy == false) {
                        row7[oAttribute.number] = "a";
                    } else {
                        row7[oAttribute.number] = "c";
                    }
                } else {
                    row7[oAttribute.number] = "a"
                }
                id7[oAttribute.number] = oAttribute.uuid;
                seatCategory7[oAttribute.number] = oAttribute.seatCategory;
                return;
            } else if (oAttribute.row == 8) {
                if (oAttribute.seatInformation != null) {
                    if (oAttribute.seatInformation.locked == false && oAttribute.seatInformation.busy == false) {
                        row8[oAttribute.number] = "a";
                    } else {
                        row8[oAttribute.number] = "c";
                    }
                } else {
                    row8[oAttribute.number] = "a"
                }
                id8[oAttribute.number] = oAttribute.uuid;
                seatCategory8[oAttribute.number] = oAttribute.seatCategory;
                return;
            } else if (oAttribute.row == 9) {
                if (oAttribute.seatInformation != null) {
                    if (oAttribute.seatInformation.locked == false && oAttribute.seatInformation.busy == false) {
                        row9[oAttribute.number] = "a";
                    } else {
                        row9[oAttribute.number] = "c";
                    }
                } else {
                    row9[oAttribute.number] = "a"
                }
                id9[oAttribute.number] = oAttribute.uuid;
                seatCategory9[oAttribute.number] = oAttribute.seatCategory;
                return;
            } else if (oAttribute.row == 10) {
                if (oAttribute.seatInformation != null) {
                    if (oAttribute.seatInformation.locked == false && oAttribute.seatInformation.busy == false) {
                        row10[oAttribute.number] = "a";
                    } else {
                        row10[oAttribute.number] = "c";
                    }
                } else {
                    row10[oAttribute.number] = "a"
                }
                id10[oAttribute.number] = oAttribute.uuid;
                seatCategory10[oAttribute.number] = oAttribute.seatCategory;
                return;
            } else if (oAttribute.row == 11) {
                if (oAttribute.seatInformation != null) {
                    if (oAttribute.seatInformation.locked == false && oAttribute.seatInformation.busy == false) {
                        row11[oAttribute.number] = "a";
                    } else {
                        row11[oAttribute.number] = "c";
                    }
                } else {
                    row11[oAttribute.number] = "a"
                }
                id11[oAttribute.number] = oAttribute.uuid;
                seatCategory11[oAttribute.number] = oAttribute.seatCategory;
                return;
            } else if (oAttribute.row == 12) {
                if (oAttribute.seatInformation != null) {
                    if (oAttribute.seatInformation.locked == false && oAttribute.seatInformation.busy == false) {
                        row12[oAttribute.number] = "a";
                    } else {
                        row12[oAttribute.number] = "c";
                    }
                } else {
                    row12[oAttribute.number] = "a"
                }
                id12[oAttribute.number] = oAttribute.uuid;
                seatCategory12[oAttribute.number] = oAttribute.seatCategory;
                return;
            } else if (oAttribute.row == 13) {
                if (oAttribute.seatInformation != null) {
                    if (oAttribute.seatInformation.locked == false && oAttribute.seatInformation.busy == false) {
                        row13[oAttribute.number] = "a";
                    } else {
                        row13[oAttribute.number] = "c";
                    }
                } else {
                    row13[oAttribute.number] = "a"
                }
                id13[oAttribute.number] = oAttribute.uuid;
                seatCategory13[oAttribute.number] = oAttribute.seatCategory;
                return;
            }
        });

        var $cart = $('#selected-seats'), //Sitting Area
            $counter = $('#counter'), //Votes
            $total = $('#total'); //Total money


        var sc = $('#seat-map').seatCharts({
            map: [
                row1.toString(),
                row2.toString(),
                row3.toString(),
                row4.toString(),
                row5.toString(),
                row6.toString(),
                row7.toString(),
                row8.toString(),
                row9.toString(),
                row10.toString(),
                row11.toString(),
                row12.toString(),
                row13.toString(),
            ],
            naming: {
                top: false,
                getLabel: function (character, row, column) {
                    return column;
                }
            },
            legend: { //Definition legend
                node: $('#legend'),
                items: [
                    ['a', 'available', 'Option'],
                    ['c', 'unavailable', 'Sold']
                ]
            },


            click: function () { //Click event
                if (this.status() == 'available') { //optional seat
                    $('<li>R' + (this.settings.row + 1) + ' S' + this.settings.label + '</li>')
                        .attr('id', 'cart-item-' + this.settings.id)
                        .data('seatId', this.settings.id)
                        .appendTo($cart);

                    $counter.text(sc.find('selected').length + 1);
                    $total.text(recalculateTotal(sc) + price);

                    return 'selected';
                } else if (this.status() == 'selected') { //Checked
                    //Update Number
                    $counter.text(sc.find('selected').length - 1);
                    //update totalnum
                    $total.text(recalculateTotal(sc) - price);

                    //Delete reservation
                    $('#cart-item-' + this.settings.id).remove();
                    //optional
                    return 'available';
                } else if (this.status() == 'unavailable') { //sold
                    return 'unavailable';
                } else {
                    return this.style();
                }
            }
        });

        sc.find('c.available').status('unavailable');


    }, 300);


});

//sum total money
function recalculateTotal(sc) {
    var total = 0;
    sc.find('selected').each(function () {
        total += price;
    });

    return total;
}

function block() {
    var seats = document.getElementById("selected-seats");
    var children = Array.prototype.slice.call(seats.children);
    var reservations = [];
    var number2 = [];
    var row2 = [];
    var i = 0;
    reservations = children.map(function (oAttribute) {
        var text = oAttribute.innerText;
        var info = text.split(" ");
        var row = info[0].substring(1);
        var number = info[1].substring(1);
        var id = "";
        var seatCategory = "";
        if (row == 1) {
            id = id1[number];
            seatCategory = seatCategory1[number];
        } else if (row == 2) {
            id = id2[number];
            seatCategory = seatCategory2[number];
        } else if (row == 3) {
            id = id3[number];
            seatCategory = seatCategory3[number];
        } else if (row == 4) {
            id = id4[number];
            seatCategory = seatCategory4[number];
        } else if (row == 5) {
            id = id5[number];
            seatCategory = seatCategory5[number];
        } else if (row == 6) {
            id = id6[number];
            seatCategory = seatCategory6[number];
        } else if (row == 7) {
            id = id7[number];
            seatCategory = seatCategory7[number];
        } else if (row == 8) {
            id = id8[number];
            seatCategory = seatCategory8[number];
        } else if (row == 9) {
            id = id9[number];
            seatCategory = seatCategory9[number];
        } else if (row == 10) {
            id = id10[number];
            seatCategory = seatCategory10[number];
        } else if (row == 11) {
            id = id11[number];
            seatCategory = seatCategory11[number];
        } else if (row == 12) {
            id = id12[number];
            seatCategory = seatCategory12[number];
        } else if (row == 13) {
            id = id13[number];
            seatCategory = seatCategory13[number];
        }

        this.BusySeat[i] = {
            "uuid": id,
            "number": parseInt(number),
            "row": parseInt(row),
            "seatCategory": seatCategory
        };

        row2[i] = row;
        number2[i] = number;
        i++;
        return id;
    });

    var string = JSON.stringify(this.BusySeat);

    var uuid = localStorage.getItem("movie");

    var request = new XMLHttpRequest();
    request.open("POST", "http://localhost:8080/service/get/seats/lock?presentation=" + uuid + "&locked=" + true);
    request.setRequestHeader("Content-Type", "application/json;charset=UTF-8")
    request.send(string);
};

function reserve(){
    var vorname = document.getElementById("vorname").value;
    var nachname = document.getElementById("nachname").value;
    var mail = document.getElementById("email").value;
    var date = this.date;
    var presentationUuid = this.presentationUuid;
    var room = this.room;
    this.user = "";
    this.reservation = "";

    var user = {
        "firstName": vorname,
        "lastName": nachname,
        "email": mail
    };

    var stringUser = JSON.stringify(user);

    var requestUser = new XMLHttpRequest();
    requestUser.open("POST", "http://localhost:8080/service/get/users");
    requestUser.setRequestHeader("Content-Type", "application/json;charset=UTF-8")
    requestUser.send(stringUser);

    window.setTimeout(function(){
            if (requestUser.readyState == 4) {
                oMe.user = JSON.parse(requestUser.response);
            }
    }, 700);

    window.setTimeout(function(){

        var tickets = this.BusySeat.map(function(oAttribute){

            return {
                "presentation": {
                    "uuid": presentationUuid,
                    "room": {
                        "uuid": room.uuid,
                        "roomType": room.roomType,
                        "number": room.number,
                        "seats": {
                        },
                        "clean": room.clean,
                        "vip": room.vip
                    }
                },
                "priceCategory": {
                    "uuid": "a4034566-5123-4176-b3c7-17285b52a5ba"
                },
                "seat": {
                    "uuid": oAttribute.uuid
                }
            }
        });

        var Reservation = {
            "user": {
                "uuid": this.user.uuid,
                "firstName": vorname,
                "lastName": nachname,
                "email": mail,
                "password": null,
                "role": {
                    "uuid": "efds8fe2-a1wq5-481f-a12d5-7557085edffe",
                    "title": "Guest",
                    "admin": false
                }
            },
            "date": date,
            "tickets": tickets
        };

        var string = JSON.stringify(Reservation);

        var request = new XMLHttpRequest();
        request.open("POST", "http://localhost:8080/service/get/reservations");
        request.setRequestHeader("Content-Type", "application/json;charset=UTF-8");
        request.send(string);

        window.setTimeout(function(){
             if (request.readyState == 4) {
                    this.reservation = JSON.parse(request.response);
                }
        }, 800);

        window.setTimeout(function(){
            var number = this.reservation.number;
        }, 900);


    }, 1000)

  }