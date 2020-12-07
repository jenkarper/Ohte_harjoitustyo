# Ohjelmistotekniikka, syksy 2020

Tämä repositorio on luotu Helsingin yliopiston Ohjelmistotekniikan kurssille harjoitussovellusta ja harjoitustehtäviä varten.

## Harjoitustyö: YahtzeeDesktop

YahtzeeDesktop on paikallisella koneella pelattava jatsi-noppapeli.

(Viikottaista tarkistusta varten: pääohjelman sisältävän luokan täydellinen nimi on yahtzee.ui.Main.)

### Dokumentaatio

[Käyttöohje](dokumentaatio/kayttoohje.md)

[Vaatimusmäärittely](dokumentaatio/vaatimusmaarittely.md)

[Arkkitehtuurikuvaus](dokumentaatio/arkkitehtuuri.md)

[Tuntikirjanpito](dokumentaatio/tuntikirjanpito.md)

### Releaset

[Viikko 5](https://github.com/jenkarper/YahtzeeDesktop/releases/tag/viikko5)

### Komentorivitoiminnot

#### Testaus
Testit suoritetaan komennolla `mvn test`. Testikattavuusraportti luodaan komennolla `mvn jacoco:report`, ja se muodostuu tiedostoon _target/site/jacoco/index.html_. Raportin voi avata selaimessa esimerkiksi komennolla `chromium-browser target/site/jacoco/index.html`.

#### Checkstyle
Projektissa on käytössä koodin laatua valvova työkalu Checkstyle. Checkstyle-tarkastus suoritetaan komentoriviltä komennolla `mvn jxr:jxr checkstyle:checkstyle`, ja sen generoimaa raporttia voi tarkastella selaimessa komennolla `chromium-browser target/site/checkstyle.html`. Käyttöliittymän rakentava koodi (pakkaus _yahtzee.ui_) on jätetty Checkstylen ulkopuolelle.

#### Suoritettavan jarin generointi
Komennolla `mvn package` voi luoda suoritettavan jar-tiedoston. Se ilmestyy hakemistoon _target_ nimellä _Yahtzee-1.0-SNAPSHOT.jar_.
