# Vaatimusmäärittely

## Sovelluksen tarkoitus

YahtzeeDesktop-sovelluksella voi pelata noppapeli jatsia yksinpelinä.

## Käyttäjät

Sovelluksella on vain yksi käyttäjärooli, *peruskäyttäjä*.

## Käyttöliittymä

Graafinen käyttöliittymä koostuu neljästä näkymästä: Avausnäkymässä käyttäjä pääsee syöttämään pelaajanimensä ja aloittamaan uuden pelin, jonka jälkeen aukeaa varsinainen pelinäkymä. Pelin ajan pysytään pääasiallisesti tässä pelinäkymässä. Heiton pisteyttäminen avaa uuden ikkunan, jossa pelaaja pääsee valitsemaan, mihin pöytäkirjan kategoriaan haluaa pisteyttää heiton. Pelin päättyessä (eli viidennentoista kierroksen pisteytyksen jälkeen) avautuu loppunäkymä, jossa näkyvät pelaajan saamat kokonaispisteet sekä kymmenen parasta tulosta niihin liittyvine pelaajanimineen. Käyttöliittymän visuaalinen esitys löytyy [käyttöohjeista](kayttoohje.md#uuden-pelin-aloittaminen).

## Perusversion tarjoama toiminnallisuus

Sovellus simuloi viiden nopan heittämistä. Sovellus tarjoaa myös pöytäkirjan, johon heittojen tulokset merkitään, sekä pistelaskutoiminnallisuuden. Käyttäjä voi

* heittää viittä d6-noppaa
* merkitä nopan lukituksi, jolloin sen silmäluku säilyy heitosta toiseen
* vapauttaa lukitun nopan
* laskea heiton pisteet jatsin pistesääntöjen mukaan
* merkitä heiton pisteet haluamaansa kohtaan pöytäkirjassa
* tarkastella omaa tilastoaan (paras tulos, huonoin tulos ja pelattujen pelien määrä)

Sovellus laskee pelaajan yhteispisteet automaattisesti pelin päätyttyä ja lisää ne tietokantaan. Lopetusnäkymässä pelaaja näkee kymmenen parhaan tuloksen listan. Pelinäkymässä on ajantasainen pöytäkirja. Pisteytysnäkymässä pelaaja näkee sellaiset pöytäkirjan kategoriat, joihin heitto on mahdollista pisteyttää (eli vielä käyttämättä olevat kategoriat). Pelilogiikka kontrolloi heittojen määrää jatsin sääntöjen mukaan.

## Mahdollinen laajennettu toiminnallisuus

* Sovellukseen voisi lisätä mahdollisuuden moninpeliin.
* Tietokantaan tallennettavaa tietoa voisi laajentaa niin, että pelkkien pisteiden sijasta tallennettaisiin koko pöytäkirja omaan tietokantatauluunsa. Pöytäkirjataulusta voisi sitten laskea erilaisia tilastoja, kuten pisteiden keskiarvon, miten jatsi-heitot jakaantuvat nopan silmälukujen kesken ja kuinka suuressa osassa peleistä välisumma on tuonut bonuksen. Highscore-taululle ei tällöin olisi tarvetta, mutta pelaajatiedot tallentavan User-taulun ja pöytäkirjat tallentavat Scorecard-taulun väliin tarvittaisiin liitostaulu.
* Jatsin säännöistä on olemassa erilaisia versioita, esimerkiksi niin kutsutussa "pakko-jatsissa" pöytäkirjan kategoriat on täytettävä järjestyksessä ykkösistä alkaen. Peliä voisi laajentaa niin, että pelaaja voi avausnäkymässä valita, millä säännöillä haluaa pelata.
* Perusversion käyttäjärooleja voisi lisätä siten, että anonyymi käyttäjä voi pelata peliä ja nähdä parhaiden tulosten listan, mutta omien pelitietojen tallennus ja niiden tarkastelu vaatisi käyttäjänimen ja salasanan.
