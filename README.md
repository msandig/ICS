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
    * Testen von VO's :x:
    * Implementierung von DAO's (Data Access Objects) :white_check_mark:
    * Testen der DAO's :ballot_box_with_check:
        * CRUD-Tests :ballot_box_with_check:
        * Dependency-Tests :x:
* Manager
    * Entwerfen der Verarbeitungslogik :x:
    * Implementierung der Verarbeitungslogik :x:
    * Testen der Verarbeitungslogik :x:
* REST-Schnittstellen :x:
    * Entwerfen von REST-Schnittstellenmuster :x:
    * Implementieren der REST-Schnittstellen :x:
    * Testen der REST-Schnittstellen :x:
    
### Frontend



