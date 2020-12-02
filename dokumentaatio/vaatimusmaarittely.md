# Vaatimusmäärittely

## Sovelluksen tarkoitus

YahtzeeDesktop-sovelluksella voi pelata noppapeli Jatsia.

## Käyttäjät

Sovelluksella on vain yksi käyttäjärooli, *peruskäyttäjä*.

## Käyttöliittymäluonnos

Graafinen käyttöliittymä koostuu neljästä näkymästä: Avausnäkymässä käyttäjä pääsee aloittamaan uuden pelin, jonka jälkeen siirrytään pelaajanimen syöttöön. Tämän jälkeen aukeaa varsinainen pelinäkymä, jossa pääasiallisesti pysytään. Heiton pisteyttäminen avaa uuden ikkunan, jossa pelaaja pääsee valitsemaan, mihin pöytäkirjan kategoriaan haluaa pisteyttää heiton. Jos pelitulosten tallennus otetaan käyttöön, viidentenä näkymänä voisi olla Parhaat tulokset -listaus, jossa näkyvät parhaat kokonaispisteet ja niihin liittyvät pelaajanimet.

<img src="https://github.com/jenkarper/YahtzeeDesktop/blob/master/dokumentaatio/kuvat/gui_luonnos.png" wifth="800">

## Perusversion tarjoama toiminnallisuus

Sovellus simuloi viiden nopan heittämistä. Sovellus tarjoaa myös pöytäkirjan, johon heittojen tulokset merkitään, sekä pistelaskutoiminnallisuuden. Käyttäjä voi

* heittää viittä d6-noppaa (_tehty_)
* merkitä nopan lukituksi, jolloin sen silmäluku säilyy heitosta toiseen (_tehty_)
* vapauttaa lukitun nopan (_tehty_)
* laskea heiton pisteet jatsin pistesääntöjen mukaan (_tehty_)
* merkitä heiton pisteet haluamaansa kohtaan pöytäkirjassa (_tehty_)
* laskea yhteispisteet (_tehty_)

Pelinäkymässä on ajantasainen pöytäkirja. Pisteytysnäkymässä pelaaja näkee sellaiset pöytäkirjan kategoriat, joihin heitto on mahdollista pisteyttää (eli vielä käyttämättä olevat kategoriat). Pelilogiikka kontrolloi heittojen määrää jatsin sääntöjen mukaan.

## Mahdollinen laajennettu toiminnallisuus

Sovellukseen voisi liittää pelaajatietokannan, johon tallennettaisiin pelaajanimi sekä joitakin tilastotietoja kyseisen pelaajan peleistä, esimerkiksi pelattujen pelien määrä, parhaat pisteet ja huonoimmat pisteet. Tietokannasta voisi lukea Top 10 -pelituloslistan.
