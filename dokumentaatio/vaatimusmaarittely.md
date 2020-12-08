# Vaatimusmäärittely

## Sovelluksen tarkoitus

YahtzeeDesktop-sovelluksella voi pelata noppapeli Jatsia yksinpelinä.

## Käyttäjät

Sovelluksella on vain yksi käyttäjärooli, *peruskäyttäjä*.

## Käyttöliittymäluonnos

Graafinen käyttöliittymä koostuu neljästä näkymästä: Avausnäkymässä käyttäjä pääsee syöttämään pelaajanimensä ja aloittamaan uuden pelin, jonka jälkeen aukeaa varsinainen pelinäkymä, jossa pääasiallisesti pysytään. Heiton pisteyttäminen avaa uuden ikkunan, jossa pelaaja pääsee valitsemaan, mihin pöytäkirjan kategoriaan haluaa pisteyttää heiton. Pelin päättyessä (eli viidennentoista kierroksen pisteytyksen jälkeen) avautuu loppunäkymä, jossa näkyvät pelaajan saamat kokonaispisteet sekä kymmenen parasta tulosta niihin liittyvine pelaajanimineen.

<img src="https://github.com/jenkarper/YahtzeeDesktop/blob/master/dokumentaatio/kuvat/gui_luonnos.png" wifth="800">

## Perusversion tarjoama toiminnallisuus

Sovellus simuloi viiden nopan heittämistä. Sovellus tarjoaa myös pöytäkirjan, johon heittojen tulokset merkitään, sekä pistelaskutoiminnallisuuden. Käyttäjä voi

* heittää viittä d6-noppaa
* merkitä nopan lukituksi, jolloin sen silmäluku säilyy heitosta toiseen
* vapauttaa lukitun nopan
* laskea heiton pisteet jatsin pistesääntöjen mukaan
* merkitä heiton pisteet haluamaansa kohtaan pöytäkirjassa
* laskea yhteispisteet
* tarkastella kymmenen parhaan tuloksen listaa (_tehty_)
* tarkastella omaa tilastoaan (paras tulos, huonoin tulos ja pelattujen pelien määrä) (_tehty_)

Pelinäkymässä on ajantasainen pöytäkirja. Pisteytysnäkymässä pelaaja näkee sellaiset pöytäkirjan kategoriat, joihin heitto on mahdollista pisteyttää (eli vielä käyttämättä olevat kategoriat). Pelilogiikka kontrolloi heittojen määrää jatsin sääntöjen mukaan.

## Mahdollinen laajennettu toiminnallisuus

Sovellukseen voisi lisätä mahdollisuuden moninpeliin. Tietokantaan tallennettavaa tietoa voisi jalostaa niin, että pelkkien pisteiden sijasta tallennettaisiin koko pöytäkirja omaan tietokantatauluunsa. Pöytäkirjataulusta voisi sitten laskea erilaisia tilastoja, kuten keskimääräisen tuloksen, miten jatsi-heitot jakaantuvat nopan silmälukujen kesken ja kuinka suuressa osassa peleistä välisumma on tuonut bonuksen.
