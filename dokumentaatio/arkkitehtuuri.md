# Arkkitehtuurikuvaus

## Rakenne

Ohjelman pakkausrakenne on kolmitasoinen. Pakkaus _yahtzee.ui_ sisältää graafisen käyttöliittymän rakentavan koodin, pakkaus _yahtzee.dao_ sisältää tietokannan käsittelyyn liittyvän koodin, ja varsinainen sovelluslogiikka on pakkauksessa _yahtzee.domain_. Sekä pelilogiikka että tiedon pysyväistallennukseen liittyvä toiminnallisuus tapahtuu _domain_-pakkauksessa olevan [Game](https://github.com/jenkarper/YahtzeeDesktop/blob/master/Yahtzee/src/main/java/yahtzee/domain/Game.java)-luokan kautta. Pakkausten ja niiden sisältämien luokkien keskinäisiä suhteita on havainnollistettu pakkaus- ja luokkakaaviossa:

<img src="https://github.com/jenkarper/YahtzeeDesktop/blob/master/dokumentaatio/kuvat/luokka-pakkauskaavio.png" width="600">

## Käyttöliittymä

Käyttöliittymä on luotu ohjelmallisesti. Käyttöliittymässä on neljä näkymää, joita kutakin vastaa oma luokkansa:

* [StartView](https://github.com/jenkarper/YahtzeeDesktop/blob/master/Yahtzee/src/main/java/yahtzee/ui/StartView.java)
* [PlayView](https://github.com/jenkarper/YahtzeeDesktop/blob/master/Yahtzee/src/main/java/yahtzee/ui/PlayView.java)
* [ScoreView](https://github.com/jenkarper/YahtzeeDesktop/blob/master/Yahtzee/src/main/java/yahtzee/ui/ScoreView.java)
* [GameOverView](https://github.com/jenkarper/YahtzeeDesktop/blob/master/Yahtzee/src/main/java/yahtzee/ui/GameOverView.java)

Sovellus aukeaa avausnäkymään (_StartView_), jossa käyttäjä syöttää nimensä ja aloittaa uuden pelin. Tämän jälkeen pääikkunassa avautuu varsinainen pelinäkymä (_PlayView_), joka pysyy pääikkunassa ohjelman sulkemiseen asti. Kierroksen päätteeksi avautuu pisteytysnäkymä (_ScoreView_) uuteen ikkunaan, ja koko pelin lopuksi avautuu lopetusnäkymä (_GameOverView_) uuteen ikkunaan. Jos käyttäjä aloittaa uuden pelin, pelinäkymän pöytäkirja nollautuu. Käyttöliittymä on kokonaisuudessaan toteutettu ohjelmallisesti.

Yllä esiteltyjen luokkien lisäksi käyttöliittymässä on kokoava luokka [YahtzeeGUI](https://github.com/jenkarper/YahtzeeDesktop/blob/master/Yahtzee/src/main/java/yahtzee/ui/YahtzeeGUI.java), pelkästään tietoa välittävien Alert-olioiden määrittelystä vastaava luokka [GameAlert](https://github.com/jenkarper/YahtzeeDesktop/blob/master/Yahtzee/src/main/java/yahtzee/ui/GameAlert.java) sekä pääohjelmaluokka [Main](https://github.com/jenkarper/YahtzeeDesktop/blob/master/Yahtzee/src/main/java/yahtzee/ui/Main.java), jonka tarkoituksena on välttää JavaFX:n käyttöön liittyvä ongelma jar-tiedoston generoinnissa: _Main_-luokan ainoa tehtävä on kutsua _YahtzeeGUI_-luokan main-metodia.

## Pelilogiikka

Pelilogiikasta vastaava koodi sijaitsee luokassa _yahtzee.domain_. Uuden pelin alkaessa pääohjelma luo uuden _Game_-olion, joka puolestaan luo uudet _Roll_-, _Checker_- ja _Scorecard_-oliot. _Roll_-olio luo viisi uutta _Die_-oliota. Nämä luokat yhdessä toteuttavat pelilogiikan.

_Game_-oliolla on oliomuuttujina heittojen ja kierrosten laskennasta huolehtivat _rollCounter_ ja _roundCounter_, joiden arvoja _Game_-olion metodit päivittävät tarpeen mukaan. Metodi [reset()](https://github.com/jenkarper/YahtzeeDesktop/blob/master/Yahtzee/src/main/java/yahtzee/domain/Game.java#L143) alustaa nämä laskurit sekä pöytäkirjan uutta peliä varten.

Pelin kuluessa _Game_-olion metodit huolehtivat heitoista ja pisteytysestä. Tärkeimmät toiminnallisuutta toteuttavat metodit ovat

* [roll()](https://github.com/jenkarper/YahtzeeDesktop/blob/master/Yahtzee/src/main/java/yahtzee/domain/Game.java#L60)
* [checkScore(int type, int[] dice)](https://github.com/jenkarper/YahtzeeDesktop/blob/master/Yahtzee/src/main/java/yahtzee/domain/Game.java#L135)
* [scoreRoll(int category, int[] dice)](https://github.com/jenkarper/YahtzeeDesktop/blob/master/Yahtzee/src/main/java/yahtzee/domain/Game.java#L73)

Ne kutsuvat muita _domain_-pakkauksen luokkien metodeita ja välittävät lopuksi tarpeelliset tiedot käyttöliittymälle ja tietokannalle.

## Tietokanta

Tietokannan käsittelystä vastaava koodi sijaitsee pakkauksessa _yahtzee.dao_. Pakkauksessa on kaksi rajapintaa, _UserDao_ ja _HighscoreDao_, jotka vastaavat tietokannan kahta taulua. Rajapintojen määrittelemien metodien toteutus tehdään implementoivissa luokissa _UserDaoDb_ ja _HighscoreDaoDb_. _Db_-luokkien metodit huolehtivat myös tietokantataulujen luomisesta. Ne käyttävät _domain_-pakkauksen luokkaa _User_ apuna tietokannan lukemisessa ja sinne kirjoittamisessa. Sovelluksessa _Db_-luokkien instanssit ovat _Game_-olion oliomuuttujia, joten _Game_-olio kokoaa pelilogiikan ja tiedon pysyväistallennuksen, ja käyttöliittymä pääsee näihin käsiksi _Game_-luokan metodien kautta. _Game_-olio luo tietokannan oletusarvoisesti projektin juureen nimellä _yahtzee.db_, mutta testejä varten luodaan erillinen testitietokanta nimellä _yahtzeeTest.db_, samoin projektin juureen.

Taulut ovat rakenteeltaan seuraavanlaisia:

<img src="https://github.com/jenkarper/YahtzeeDesktop/blob/master/dokumentaatio/kuvat/tietokantataulut.png" width="600">

Tietokantaan tallennetaan pelaajan avausnäkymässä syöttämä pelaajanimi, ja pelin päätyttyä tallennetaan tähän pelaajanimeen liittyvät yhteispisteet siten, että tietokannassa ovat aina korkeimmat ja matalimmat yhteispisteet. Lisäksi tallennetaan pelattujen pelien määrä. Pelin päättyessä tietokannasta luetaan kymmenen parhaan pistemäärän lista.

Tietokanta on toteutettu [SQLite](https://www.sqlitetutorial.net/what-is-sqlite/)-kirjaston avulla.

## Päätoiminnallisuudet

Pelaajan käytettävissä olevat toiminnallisuudet ovat nopan lukitseminen ja vapauttaminen, noppien heittäminen ja heiton pisteytys. Nämä toiminnallisuudet on kuvattu tarkemmin oheisissa sekvenssikaavioissa.

### Nopan lukitseminen ja vapauttaminen

Käyttäjä voi lukita tai vapauttaa nopan klikkaamalla sitä kuvaavaa nappia graafisessa käyttöliittymässä. Kun noppa lukitaan, napin ympärille ilmestyy kapea reunus. Sekvenssikaaviossa kaikkia viittä noppaa kuvaa selvyyden vuoksi yksi olio, Dice. Skenaariossa käyttäjä on klikannut kolmosnoppaa, joka on ollut vapaana ja jonka hän haluaa lukita. Kun sitä vastoin lukittuna ollutta noppaa klikataan, kontrolli etenee samoin, mutta lukitusta ilmaisevat boolean-arvot ovat päinvastaiset.

Ensin [tapahtumankäsittelijä](https://github.com/jenkarper/YahtzeeDesktop/blob/master/Yahtzee/src/main/java/yahtzee/ui/PlayView.java#L195) tarkistaa _Game_-oliolta, onko kolmosnoppa nyt lukittuna vai ei. _Game_-olio tekee tarkistuksen ja palauttaa kyseisen nopan _hold_-arvon. Sitten tapahtumankäsittelijä pyytää _Game_-oliota muuttamaan lukitusarvon ja päivittää lopuksi noppaa kuvaavan napin ulkoasua.

<img src="https://github.com/jenkarper/YahtzeeDesktop/blob/master/dokumentaatio/kuvat/sekvenssikaavio_noppa.png" width="700">

### Heitto

Käyttäjä heittää kaikkia vapaita noppia klikkaamalla nappia 'Heitä'. [Tapahtumankäsittelijä](https://github.com/jenkarper/YahtzeeDesktop/blob/master/Yahtzee/src/main/java/yahtzee/ui/PlayView.java#L207) tarkistaa ensin _Game_-luokan heittojen lukumäärästä kirjaa pitävän _rollCounter_-muuttujan arvon, ja jos uusi heitto sallitaan, kutsutaan _Game_-luokan metodia roll(). _Game_-olio kutsuu oman oliomuuttujansa _Roll_-olion metodia roll(). _Roll_-olio tarkistaa vuorollaan jokaisen viiden nopan _hold_-arvon, ja jos arvo on _false_, pyytää nopalta uutta arvoa. _Roll_-olio päivittää uudet arvot _values_-taulukkoon ja palauttaa taulukon _Game_-oliolle. _Game_-olio välittää saman taulukon käyttöliittymälle, joka päivittää käyttäjälle näkyvät noppien silmäluvut.

<img src="https://github.com/jenkarper/YahtzeeDesktop/blob/master/dokumentaatio/kuvat/sekvenssikaavio_heitto.png" width="700">

### Pisteytys

Kun käyttäjä haluaa pisteyttää heiton, hän klikkaa päänäkymässä nappia 'Pisteytä'. Tämä avaa uuden ikkunan, jossa käyttäjä näkee ne kategoriat, joihin heitto on mahdollista pisteyttää. Kun hän on valinnut haluamansa kategorian ja klikkaa nappia 'OK', tähän klikkaukseen reagoiva [tapahtumankäsittelijä](https://github.com/jenkarper/YahtzeeDesktop/blob/master/Yahtzee/src/main/java/yahtzee/ui/ScoreView.java#L73) käynnistää pisteytysprosessin. Sekvenssikaavion kuvaamassa skenaariossa käyttäjä on heittänyt arvot 3, 6, 6, 2 ja 6 ja valinnut kategoriaksi 'Kuutoset'.

Kun tapahtumankäsitelijä on varmistanut, että käyttäjä on todella valinnut jonkun kategorian, se pyytää _Game_-oliolta noppien silmäluvut ja pöytäkirjan kategorian näkymän päivitystä varten. Sitten se kutsuu _Game_-luokan scoreRoll()-metodia ja antaa parametreina käyttäjän valitseman kategorian numeron sekä silmäluvut taulukkona. _Game_-olio kutsuu ensin tarkistajaluokka _Checkerin_ metodia check(category, dice) ja saa paluuarvona heiton tuoman pistemäärän valitussa kategoriassa (tässä tapauksessa 18). Sitten _Game_-olio kutsuu _Scorecard_-olion metodia markRoll(category, points), joka päivittää pöytäkirjaan kategoriaa (tässä 6) vastaavaan kohtaan parametrina saadun pistemäärän (eli 18). Lopuksi _Game_-olio päivittää kierrosten lukumäärästä kirjaa pitävää _roundCounter_-muuttujaa ja palauttaa merkityt pisteet käyttöliittymälle. Käyttöliittymä päivittää oman pöytäkirjanäkymänsä ja pyytää sitten _Game_-oliota resetoimaan _rollCounter_-muuttujan kierroksen päättymisen merkiksi. Lopuksi se tarkistaa _Game_-olion _roundCounter_-muuttujan arvon. Jos muuttuja on 0, se tarkoittaa, että viimeinenkin kierros on ohi ja peli on päättynyt. Tässä skenaariossa ollaan kierroksella kuusi, eli jäljellä on vielä yhdeksän kierrosta. Tapahtumankäsittelijä sulkee ikkunan, ja peli jatkuu pelinäkymässä.

<img src="https://github.com/jenkarper/YahtzeeDesktop/blob/master/dokumentaatio/kuvat/sekvenssikaavio_pisteytys.png" width="700">

## Sovellukseen jääneet rakenteelliset puutteet

Ohjelman _dao_- ja _domain_-pakkauksissa on tällä hetkellä jonkin verran kovakoodausta: esimerkiksi tietokannan sijainti määritellään koodissa, samoin noppien, heittojen ja kierrosten määrä. Ohjelmasta voisi tehdä näiltä osin konfiguroitavan, niin että käyttäjä voisi halutessaan muuttaa tietokannan oletussijaintia ja valita käyttöliittymässä haluamansa sääntömuunnelman pelin alussa. Tämä parantaisi ohjelman laajennettavuutta.

Käyttäjän syötettä (pelaajanimen syöttäminen pelin alussa) validoidaan hyvin kevyesti: tietokannasta tarkistetaan, onko kyseinen käyttäjänimi jo tietokannassa, ja jos on, oletetaan, että kyseessä on sama pelaaja. Jos käyttäjänimeä ei löydy tietokannasta, tauluun luodaan uusi rivi. Tämä mahdollistaa esimerkiksi lyöntivirheen takia muodostuvan uuden käyttäjän. Lisäksi itse syötettyä merkkijonoa tarkastellaan vain pituuden osalta, eikä mitään muita rajoituksia aseteta. Käyttäjänimeksi kelpaavat esimerkiksi tyhjä merkkijono tai kolme kysymysmerkkiä. Mielekkään top ten -listauksen nimissä käyttäjänimeltä voisi vaatia esimerkiksi vähimmäismerkkimäärää.

Pelaajan vaihtaminen kahden pelin välillä ei nyt ole mahdollista, vaan ohjelman käynnistämisen jälkeen syötetty pelaajanimi on käytössä, kunnes ohjelma suljetaan.

Ohjelman rakenne on joiltakin osin turhan monimutkainen. Esimerkiksi heittotoiminto vaatii paljon edestakaista vuoropuhelua käyttöliittymän ja _domain_-luokan olioiden kanssa. Rakennetta voisi suoraviivaistaa ja selkiyttää.
