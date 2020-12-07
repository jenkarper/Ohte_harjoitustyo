# Arkkitehtuurikuvaus

## Rakenne

Ohjelman pakkausrakenne on kolmitasoinen:

<img src="https://github.com/jenkarper/YahtzeeDesktop/blob/master/dokumentaatio/kuvat/pakkauskaavio.png" width="300">

Pakkaus _yahtzee.ui_ sisältää graafisen käyttöliittymän rakentavan koodin, pakkaus _yahtzee.dao_ sisältää tietokannan käsittelyyn liittyvän koodin, ja varsinainen sovelluslogiikka on pakkauksessa _yahtzee.domain_. Sekä pelilogiikka että tiedon pysyväistallennukseen liittyvä toiminnallisuus tapahtuu _domain_-pakkauksessa olevan [Game](https://github.com/jenkarper/YahtzeeDesktop/blob/master/Yahtzee/src/main/java/yahtzee/domain/Game.java)-luokan kautta. Tarkempi pakkaus- ja luokkakaavio on seuraavanlainen:

<img src="https://github.com/jenkarper/YahtzeeDesktop/blob/master/dokumentaatio/kuvat/luokka-pakkauskaavio.png" width="600">

## Käyttöliittymä

Käyttöliittymä on luotu ohjelmallisesti. Käyttöliittymässä on neljä näkymää, joita kutakin vastaa oma luokkansa:

* [StartView](https://github.com/jenkarper/YahtzeeDesktop/blob/master/Yahtzee/src/main/java/yahtzee/ui/StartView.java)
* [PlayView](https://github.com/jenkarper/YahtzeeDesktop/blob/master/Yahtzee/src/main/java/yahtzee/ui/PlayView.java)
* [ScoreView](https://github.com/jenkarper/YahtzeeDesktop/blob/master/Yahtzee/src/main/java/yahtzee/ui/ScoreView.java)
* [GameOverView](https://github.com/jenkarper/YahtzeeDesktop/blob/master/Yahtzee/src/main/java/yahtzee/ui/GameOverView.java)

Sovellus aukeaa avausnäkymään (_StartView_), jossa käyttäjä syöttää nimensä ja aloittaa uuden pelin. Tämän jälkeen pääikkunassa avautuu varsinainen pelinäkymä (_PlayView_), joka pysyy pääikkunassa ohjelman sulkemiseen asti. Kierroksen päätteeksi avautuu pisteytysnäkymä (_ScoreView_) uuteen ikkunaan, ja koko pelin lopuksi avautuu lopetusnäkymä (_GameOverView_) uuteen ikkunaan. Jos käyttäjä aloittaa uuden pelin, pelinäkymän pöytäkirja nollautuu.

Näiden luokkien lisäksi käyttöliittymässä on kokoava luokka [YahtzeeGUI](https://github.com/jenkarper/YahtzeeDesktop/blob/master/Yahtzee/src/main/java/yahtzee/ui/YahtzeeGUI.java), pelkästään tietoa välittävien Alert-olioiden määrittelystä vastaava luokka [GameAlert](https://github.com/jenkarper/YahtzeeDesktop/blob/master/Yahtzee/src/main/java/yahtzee/ui/GameAlert.java) sekä pääohjelmaluokka [Main](https://github.com/jenkarper/YahtzeeDesktop/blob/master/Yahtzee/src/main/java/yahtzee/ui/Main.java), jonka tarkoituksena on välttää JavaFX:n käyttöön liittyvä ongelma jar-tiedoston generoinnissa: _Main_-luokan ainoa tehtävä on kutsua _YahtzeeGUI_-luokan main-metodia.

## Pelilogiikka

Pelilogiikasta vastaava koodi sijaitsee luokassa _yahtzee.domain_. Uuden pelin alkaessa pääohjelma luo uuden _Game_-olion, joka puolestaan luo uudet _Roll_-, _Checker_- ja _Scorecard_-oliot. _Roll_-olio luo viisi uutta _Die_-oliota. Nämä luokat yhdessä toteuttavat pelilogiikan.

_Game_-oliolla on oliomuuttujina heittojen ja kierrosten laskennasta huolehtivat _rollCounter_ ja _roundCounter_, joiden arvoja _Game_-olion metodit päivittävät tarpeen mukaan. Metodi [_reset_](https://github.com/jenkarper/YahtzeeDesktop/blob/master/Yahtzee/src/main/java/yahtzee/domain/Game.java#L120) alustaa nämä laskurit sekä pöytäkirjan uutta peliä varten.

## Tietokanta

Tietokannan käsittelystä vastaava koodi sijaitsee pakkauksessa _yahtzee.dao_. Pakkauksessa on kaksi rajapintaa, _UserDao_ ja _HighscoreDao_, jotka vastaavat tietokannan kahta taulua. Rajapinnat implementoi luokka _Database_, jonka metodit myös alustavat tietokannan. Tietokanta käyttää _domain_-pakkauksen luokkaa _User_ apuna tietokannan lukemisessa ja sinne kirjoittamisessa. Sovelluksessa tietokanta on _Game_-olion oliomuuttuja, joten _Game_-olio kokoaa pelilogiikan ja tiedon pysyväistallennuksen, ja käyttöliittymä pääsee näihin käsiksi _Game_-luokan metodien kautta.

Taulut ovat rakenteeltaan seuraavanlaisia:

<img src="https://github.com/jenkarper/YahtzeeDesktop/blob/master/dokumentaatio/kuvat/tietokantataulut.png" width="300">

Tietokantaan tallennetaan pelaajan avausnäkymässä syöttämä pelaajanimi, ja pelin päätyttyä tallennetaan tähän pelaajanimeen liittyvät yhteispisteet siten, että tietokannassa ovat aina korkeimmat ja matalimmat yhteispisteet. Lisäksi tallennetaan pelattujen pelien määrä. Pelin päättyessä tietokannasta luetaan kymmenen parhaan pistemäärän lista.

Tietokanta on toteutettu [SQLite](https://www.sqlitetutorial.net/what-is-sqlite/)-kirjaston avulla.

## Päätoiminnallisuudet

Oheiseen sekvenssikaavioon on kuvattu uuden pelin alku, ensimmäinen heitto ja heiton pisteytys. Yksinkertaisuuden vuoksi graafisen käyttöliittymän toiminnot on kuvattu vain siltä osin kuin ne liittyvät itse pelilogiikkaan, ja tietokantaan liittyvä toiminnallisuus on rajattu pois.

<img src="https://github.com/jenkarper/YahtzeeDesktop/blob/master/dokumentaatio/kuvat/sekvenssikaavio_heitto-ja-pisteytys.png" width="1000">
