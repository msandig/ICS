# HOW TO USE GIT
* Vor dem arbeiten --> erst die neusten Änderungen per PULL ziehen
* Vor einem Commit bzw. beim Commit (je nach IDE) einen Codescan durchführen und auf Fehler prüfen
* Achtet darauf, was ihr Commited
  * Unnütze Dateien können in das .gitignore File geschrieben werden
  * Beispiele sind Konfigurationsdateien für Projekte euerer IDE
* Wenn euch eure IDE darauf hinweißt, dass es im Origin/Remote Projekt Mergekonflikte entstehen, dann führte den PUSH nicht zwanghaft aus sondern versucht die Probleme lokal zu lösen!
* Bei größeren Arbeitsschritten commitet zwischendrin und synchronisiert die Projekte

# Informationen zum Projekt
* Beim normalen Start der Anwendung wird (falls keine Umgebungsvariable gesetzt ist) ein Verzeichnis tmp auf eurem Root-Path der Festplatte angelegt. In diesem wird die Datenbank erzeugt.
* Falls ihr Probleme wegen der Datenbank habt löscht einfach diesen Ordner ( ein Fix muss noch geschrieben werden ) oder setzt in der Spring-Konfiguration (applicationContext.xml) den Wert im Constuctor-Arg beim Bean "dataSource" auf true. Dann wird die Datenbank in Memory erzeugt.

# Fallstudie 

## Status
Incomplete :construction_worker:

## Aufgabe
Entwicklung eines Prototyps für die Online-Reservierung von Kino-Tickets

## Projektergebnisse
* Objektorientierte Analyse & Entwurf
* Abschlusspräsentation
* Seminararbeit

## Abgabe
11.02.2019 

## TODO'S
### Backend
* Datenbank
    * Modell :white_check_mark:
    * Erzeugen der Datenbankstruktur bei erstem Projektstartup :white_check_mark:
    * Implementierung von VO (Value Object) - Klassen :white_check_mark:
    * Testen von VO's :ballot_box_with_check:
    * Implementierung von DAO's (Data Access Objects) :white_check_mark:
    * Testen der DAO's :ballot_box_with_check:
        * CRUD-Tests :ballot_box_with_check:
        * Dependency-Tests :x:
* Manager
    * Entwerfen der Verarbeitungslogik :white_check_mark:
    * Implementierung der Verarbeitungslogik :white_check_mark:
    * Testen der Verarbeitungslogik :ballot_box_with_check:
* REST-Schnittstellen :x:
    * Entwerfen von REST-Schnittstellenmuster:white_check_mark:
    * Implementieren der REST-Schnittstellen :white_check_mark:
    * Testen der REST-Schnittstellen :ballot_box_with_check:
    
### Frontend

## Backend Services
 Usage | Method   |  Endpoint | Parameters  |  Body | Response | Beschreibung 
 ---| --- | --- | --- | --- | ---  | --- 
 Vorstellungen | GET |  /service/get/presentations/{id} | ID (String)| - | Vorstellungsobjekt | Abfrage einer einzelnen, spezifischen Vorstellung.
| | DELETE | /service/get/presentations/{id} | ID (String) | - | SUCCES / ERROR  |Zum Löschen einer Vorstellung.
| | POST | /service/get/presentations | - | JSON - Vorstellungsobjekt | das angelegte Vorstellungsobjekt | Abspeichern einer neuen Vorstellung.
| | GET | /service/get/presentations |  start (long) - frühstes Startdatum <br> <br>  end(long) - spätestes Enddatum <br><br>  title (String) - Suchstring für Filmtitel <br> <br> movie (String) - ID für konkreten Film | - | Liste von Vorstellungsobjekten | Abfrage mehrerer Vorstellungen. Parameter als Requestparameter (?param=...&param=...) an der URL.
Sitze Sperren | POST |  /service/get/seats/lock | presentation (String) - Vorstellungs-ID <br> <br> locked (boolean) - Sperren oder Entsperren | JSON - Liste von Sitzen | Liste von Sitzobjekten | Endpoint zum sperren oder entsperren von Sitzen vor der eigentlichen Reservierung 
Filme | GET | /service/get/movies | start (long) - frühstes Startdatum <br> <br>  end(long) - spätestes Enddatum <br><br>  title (String) - Suchstring für Filmtitel <br> <br> | - | Liste von Filmobjekten | Zum Suchen eines Films während eines bestimmten Zeitintervalls
Preiskategorien | GET | service/get/categories/price | - | - | Liste von Preiskategorieobjekten | Zum Abfragen aller vorhanden Preiskategorien.
Benutzer | GET | /service/get/users/{email} | Email (String) - Email des Benutzers | - | Benutzerobjekt | Zum Abfragen eines Benutzers von der Datenbank (Passwörter werden noch nicht 100% unterstützt --> Gast)
| | POST | /service/get/users | - | JSON - Benutzerobjekt | Benutzerobjekt | Zum Erstellen eines neuen Benutzers
Reservierungen | GET | /service/get/reservations/{id} | id (int) - Nummer der Reservierung <br> <br> email (String) - Email des Benutzers | - | Reservierngsobjekt | Zum Abfragen einer Reservierung
| | DELETE |  /service/get/reservations/{id} | id (int) - Nummer der Reservierung <br> <br> email (String) - Email des Benutzers | - | SUCCES / ERROR  | Zum Löschen einer Reservierung
| | POST | /service/get/reservations | - | JSON - Reservierungsobjekt | Reservierungsobjekt | Zum Anlegen einer neuen Reservierung



