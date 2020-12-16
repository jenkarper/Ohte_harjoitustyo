# Ohjelmistotekniikka, syksy 2020

Tämä repositorio on luotu Helsingin yliopiston Ohjelmistotekniikan kurssille harjoitussovellusta ja harjoitustehtäviä varten.

## Harjoitustyö: YahtzeeDesktop

YahtzeeDesktop on paikallisella koneella pelattava jatsi-noppapeli. Ohjelma simuloi viiden nopan heittämistä, tarjoaa automaattisen pistelaskun ja pöytäkirjanpidon ja tallentaa pelaajakohtaisia pelituloksia tietokantaan.

### Dokumentaatio

[Käyttöohje](dokumentaatio/kayttoohje.md)

[Vaatimusmäärittely](dokumentaatio/vaatimusmaarittely.md)

[Arkkitehtuurikuvaus](dokumentaatio/arkkitehtuuri.md)

[Testausdokumentti](dokumentaatio/testaus.md)

[Tuntikirjanpito](dokumentaatio/tuntikirjanpito.md)

### Releaset

[Viikko 5](https://github.com/jenkarper/YahtzeeDesktop/releases/tag/viikko5)

[Viikko 6](https://github.com/jenkarper/YahtzeeDesktop/releases/tag/viikko6)

[Loppupalautus](https://github.com/jenkarper/YahtzeeDesktop/releases/tag/viikko7)

### Komentorivitoiminnot

#### Testaus
Yksikkötestit on kirjoitettu sovelluslogiikasta ja tietojen pysyväistallennuksesta huolehtivien pakkausten luokille, ja ne suoritetaan komennolla `mvn test`. Testikattavuusraportti luodaan komennolla `mvn jacoco:report`. Raportti muodostuu tiedostoon _target/site/jacoco/index.html_, ja sen voi avata selaimessa esimerkiksi komennolla `chromium-browser target/site/jacoco/index.html`. Käyttöliittymän rakentavaa koodia (pakkaus _yahtzee.ui_) on testattu manuaalisesti koekäyttämällä sovellusta.

#### Checkstyle
Projektissa on käytössä koodin laatua valvova työkalu Checkstyle. Checkstyle-tarkastus suoritetaan komentoriviltä komennolla `mvn jxr:jxr checkstyle:checkstyle`, ja sen generoimaa raporttia voi tarkastella selaimessa komennolla `chromium-browser target/site/checkstyle.html`. Käyttöliittymän rakentava koodi on jätetty Checkstylen ulkopuolelle.

#### JavaDoc
Sovelluksen lähdekoodi on dokumentoitu JavaDoc-työkalun avulla. JavaDoc-raportin voi generoida komennolla `mvn javadoc:javadoc`, ja se ilmestyy hakemistoon _target/site/apidocs_ nimellä _index.html_.

#### Suoritettavan jarin generointi
Komennolla `mvn package` voi luoda suoritettavan jar-tiedoston. Se ilmestyy hakemistoon _target_ nimellä _Yahtzee-1.0-SNAPSHOT.jar_.

