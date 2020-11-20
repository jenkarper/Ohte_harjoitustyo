# Ohjelmistotekniikka, syksy 2020

Tämä repositorio on luotu Helsingin yliopiston Ohjelmistotekniikan kurssille harjoitussovellusta ja harjoitustehtäviä varten.

## Harjoitustyö: YahtzeeDesktop

YahtzeeDesktop on paikallisella koneella pelattava jatsi-noppapeli.

(Viikottaista tarkistusta varten: pääohjelman sisältävän luokan täydellinen nimi on yahtzee.ui.Main.)

### Dokumentaatio

[Vaatimusmäärittely](dokumentaatio/vaatimusmaarittely.md)

[Tuntikirjanpito](dokumentaatio/tuntikirjanpito.md)

### Komentorivitoiminnot

#### Testaus
Testit suoritetaan komennolla `mvn test`, ja testikattavuusraportti luodaan komennolla `mvn jacoco:report`. Raportti muodostuu tiedostoon _target/site/jacoco/index.html_, ja sen voi avata selaimessa esimerkiksi komennolla `chromium-browser target/site/jacoco/index.html`.
