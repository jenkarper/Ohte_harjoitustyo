# Vaatimusmäärittely

## Sovelluksen tarkoitus

YahtzeeDesktop-sovelluksella voi pelata noppapeli Jatsia.

## Käyttäjät

Sovelluksella on vain yksi käyttäjärooli, *peruskäyttäjä*.

## Käyttöliittymäluonnos

Alkuvaiheessa sovelluksella on vain tekstikäyttöliittymä. Mahdollinen graafinen käyttöliittymä koostuu kolmesta päänäkymästä: Avausnäkymässä käyttäjä pääsee aloittamaan uuden pelin, jonka jälkeen siirrytään pelaajanimen syöttöön. Tämän jälkeen aukeaa varsinainen pelinäkymä, jossa pysytään koko pelin ajan. Jos pelitulosten tallennus otetaan käyttöön, neljäntenä näkymänä voisi olla Parhaat tulokset -listaus, jossa näkyvät parhaat kokonaispisteet ja niihin liittyvät pelaajanimet.

<img src="https://github.com/jenkarper/YahtzeeDesktop/blob/master/dokumentaatio/kuvat/gui_luonnos.png" width="800">

## Perusversion tarjoama toiminnallisuus

Sovellus simuloi viiden nopan heittämistä. Sovellus tarjoaa myös pöytäkirjan, johon heittojen tulokset merkitään, sekä pistelaskutoiminnallisuuden. Käyttäjä voi

* heittää viittä d6-noppaa
* merkitä nopan lukituksi, jolloin sen silmäluku säilyy heitosta toiseen
* vapauttaa lukitun nopan
* laskea heiton pisteet jatsin pistesääntöjen mukaan
* merkitä heiton pisteet haluamaansa kohtaan pöytäkirjassa
* laskea yhteispisteet

Tekstikäyttöliittymässä pelaaja voi heiton jälkeen aina tulostaa senhetkisen pöytäkirjan näkyviin ruudulle. Graafisessa käyttöliittymässä ajantasainen pöytäkirja on koko ajan näkyvissä.

## Mahdollinen laajennettu toiminnallisuus

Perusversiossa sovellus ei kontrolloi heittojen määrää. Laajemmassa toteutuksessa voitaisiin jatsin sääntöjen mukaisesti rajoittaa uusintaheittojen määrää kahteen ja kierrosten määrää viiteentoista. Sovellukseen voisi myös liittää pistetietokannan, johon pelaaja voi halutessaan tallentaa yhteispisteensä. Tietokannasta voisi hakea Top 10 -listan sovelluksen lopetusnäkymään. Sovellus voisi myös tarjota mahdollisuuden moninpeliin.
