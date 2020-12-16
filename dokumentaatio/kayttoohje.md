# Käyttöohje

## Sovelluksen suorittaminen

Saat sovelluksen käyttöösi lataamalla uusimmasta releasesta tiedoston yahtzee.jar omalle koneellesi. Latauksen jälkeen navigoi tiedoston tallennushakemistoon komentorivillä ja aja komento `java -jar yahtzee.jar`.

Vaihtoehtoisesti voit ladata projektin itsellesi zip-tiedostona tai kloonata koko repositorion, jolloin saat suoritettua ohjelman komentoriviltä navigoimalla ensin juurihakemistoon _Yahtzee_ ja ajamalla komennon `mvn compile exec:java -Dexec.mainClass=yahtzee.ui.Main`.

Sovellusta voi käyttää myös NetBeans-ympäristössä, jolloin se käynnistetään työkalupalkin Run Project -napista (vihreä kolmio) tai ylävalikosta Run > Run Project.

Tietokannan rakentamiseen käytetty SQLite-kirjasto luo uuden paikallisen tietokannan käyttöösi.

## Uuden pelin aloittaminen

Sovellus avautuu ikkunaan, jossa pääset syöttämään pelaajanimesi:

<img src="https://github.com/jenkarper/YahtzeeDesktop/blob/master/dokumentaatio/kuvat/gui_avaus.png" width=500>

Jos syöttämäsi nimi ei entuudestaan ole tietokannassa, se lisätään sinne tässä vaiheessa automaattisesti. Jos taas pelaat samalla nimellä, jolla olet pelannut jo aikaisemmin, sinut kirjataan sisään aiemmilla tiedoillasi.

Kun olet syöttänyt nimesi, klikkaa nappia __Aloita uusi peli__. Nyt pääset varsinaiseen pelinäkymään.

## Pelaaminen

Peliä pelataan tutuilla [jatsin säännöillä](https://www.lautapeliopas.fi/saannot/yatzy/) muutamin muunnelmin:

* neljä samaa silmälukua saa kirjata neliluvuksi tai kahdeksi pariksi
* samoin viisi samaa silmälukua saa kirjata jatsiksi tai mökiksi
* jatsi tuottaa paitsi noppien silmälukujen summan, myös 50 lisäpistettä: siten viisi kuutosta tuo jatsina 86 pistettä, viisi ykköstä 55 pistettä (kyllä, se on minustakin epäreilua, mutta tämä on meidän perheen house rule)

Nopat näkyvät ikkunan yläreunassa, pöytäkirja vasemmassa laidassa, pelaajatiedot oikeassa laidassa ja pelaamiseen käytettävät napit keskellä. Ikkunan alalaidassa on lisäksi napit __Ohje__, __Aloita uusi peli__ ja __Omat tiedot__, joista pääset tarkastelemaan peliohjeita tai omia tietojasi sekä aloittamaan uuden pelin. Nopat ovat peliä aloitettaessa vapaina, ja silmälukujen paikalla ovat sanan JATSI-kirjaimet.

<img src="https://github.com/jenkarper/YahtzeeDesktop/blob/master/dokumentaatio/kuvat/gui_peli.png" width=500>

#### Heittäminen

Pääset heittämään noppia klikkaamalla __Heitä__. Vuorossa voi heittää enintään kolme kertaa, ja osan nopista voi heittojen välillä lukita. Tämä tapahtuu klikkaamalla kyseistä noppaa: yllä olevassa kuvassa nopat 1, 2 ja 5 (kaikissa silmäluku 5) ovat lukittuina, muut vapaina. Saman nopan voi taas vapauttaa kesken kierroksen.

#### Pisteyttäminen

Kun haluat pisteyttää heiton, klikkaa __Pisteytä__. Aukeavassa ikkunassa näet ne kategoriat, joihin heiton voi pisteyttää, eli kaikki sellaiset kohdat pöytäkirjassa, joita ei vielä ole täytetty.

<img src="https://github.com/jenkarper/YahtzeeDesktop/blob/master/dokumentaatio/kuvat/gui_pisteytys.png" width=700>

Pääset vielä takaisin pelinäkymään ilman pisteytystä klikkaamalla __Peruuta__, mutta kun kierroksen kaikki kolme heittoa on käytetty, et voi heittää uudelleen ennen kuin edellinen kierros on pisteytetty.

#### Pelin lopetus

Kun koko pöytäkirja on täysi, ohjelma laskee yhteispisteet. Jos pääset kymmenen parhaan joukkoon, pelaajanimesi ja pistemääräsi lisätään lopetusnäkymässä olevaan parhaat tulokset -listaukseen.

<img src="https://github.com/jenkarper/YahtzeeDesktop/blob/master/dokumentaatio/kuvat/gui_loppu.png" width=550>

#### Uuden pelin aloitus

Voit aloittaa uuden pelin klikkaamalla päänäkymän nappia __Aloita uusi peli__ tai sulkea sovelluksen sulkemalla ikkunan. Pelaajaa ei voi vaihtaa kesken ohjelman suorituksen, vaan ohjelma on suljettava, jos peliin haluaa kirjautua toisella pelaajanimellä.
