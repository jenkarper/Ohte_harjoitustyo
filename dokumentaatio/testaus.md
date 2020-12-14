# Testausdokumentti

Ohjelmaa on testattu automatisoidusti niin yksikkö- kuin integraatiotasolla. Yksikkötestit testaavat erityisesti _yahtzee.domain_-pakkauksen luokkia ja niiden metodeita. Luokkien ja pakkausten yhteentoimivuutta testaavat integraatiotestit tarkastelevat _domain_-pakkauksen kokoavaa luokkaa _Game_ sekä tietokannan käsittelyn hoitavia luokkia pakkauksessa _yahtzee.dao_.

## Yksikkö- ja integraatiotestaus

Automatisoidut testit on rakennettu [JUnit](https://junit.org/junit4/)-testauskehyksen avulla (versio 4.12). Testiluokkien pakkausrakenne heijastaa sovelluksen pakkaustasoa siten, että _domain_-luokkia testaavat testit ovat testipakkauksessa _domain_ ja _dao_-luokkien testit testipakkauksessa _dao_.

### Sovelluslogiikka

Pakkauksen _yahtzee.domain_ luokkien testit _Game_-luokkaa lukuunottamatta ovat pääosin yksittäisiä metodeita testaavia testejä. Vastaavat testiluokat ovat

* [CheckerTest](https://github.com/jenkarper/YahtzeeDesktop/blob/master/Yahtzee/src/test/java/domain/CheckerTest.java)
* [DieTest](https://github.com/jenkarper/YahtzeeDesktop/blob/master/Yahtzee/src/test/java/domain/DieTest.java)
* [RollTest](https://github.com/jenkarper/YahtzeeDesktop/blob/master/Yahtzee/src/test/java/domain/RollTest.java)
* [ScorecardTest](https://github.com/jenkarper/YahtzeeDesktop/blob/master/Yahtzee/src/test/java/domain/ScorecardTest.java)

Näistä _RollTest_-luokassa on myös integraatiotason testejä, koska _Roll_-luokka käyttää oliomuuttujina viittä _Die_-luokan oliota. Varsinainen sovelluslogiikan yhteentoimimisen testaus tehdään luokassa [_GameTest_](https://github.com/jenkarper/YahtzeeDesktop/blob/master/Yahtzee/src/test/java/domain/GameTest.java). _Game_-luokka käyttää tietokantaan kirjoittamiseen oliomuuttujaansa _User_, johon tallennetaan sen hetkisen käyttäjän tiedot. Testiluokassa luodaan käyttäjään liittyvien toimintojen testausta varten _User_-olioita, mutta varsinaisen tietokannan käsittelyn testaus tehdään toisessa testipakkauksessa. [_UserTest_](https://github.com/jenkarper/YahtzeeDesktop/blob/master/Yahtzee/src/test/java/domain/UserTest.java)-luokan testit testaavat ainoastaan muuhun sovellukseen liittymättömiä _User_-luokan metodeita.

### Tietokanta

Testipakkauksessa _dao_ olevat testiluokat [_HighscoreDaoDbTest_](https://github.com/jenkarper/YahtzeeDesktop/blob/master/Yahtzee/src/test/java/dao/HighscoreDaoDbTest.java) ja [_UserDaoDbTest_](https://github.com/jenkarper/YahtzeeDesktop/blob/master/Yahtzee/src/test/java/dao/UserDaoDbTest.java) käyttävät testaukseen erillistä testitietokantaa _yahtzeeTest.db_, joka luodaan projektin juureen kuten varsinainen tietokantakin. (DAO-luokissa _UserDaoDb_ ja _HighscoreDaoDb_ olevat, tietokannasta tietoa poistavat delete-metodit ovat tällä hetkellä käytössä ainoastaan vastaavissa testiluokissa testauksen helpottamiseksi.)

### Testikattavuus

Käyttöliittymän rakentava koodi on rajattu kokonaan automatisoidun testauksen ulkopuolelle. Testatuista luokista yksinkertaiset _get_- ja _set_-metodit on jätetty testaamatta. Sellaisia _Game_-luokan metodeita, jotka ainoastaan kutsuvat jotakin _UserDaoDb_- tai _HighscoreDaoDb_-luokan metodia, ei myöskään ole testattu. Testauksen rivikattavuudeksi tulee näin 90 % ja haaraumakattavuudeksi 87 %:

<img src="https://github.com/jenkarper/YahtzeeDesktop/blob/master/dokumentaatio/kuvat/testikattavuus.png" width="1000">

## Järjestelmätestaus

Järjestelmätasolla sovellusta on testattu manuaalisesti koko kehitystyön ajan niin paikallisesti (Linux-ympäristössä) kuin yliopiston tarjoamalla etätyöpöydällä. Molemmissa ympäristöissä testaus on suoritettu hakemalla projekti [käyttöohjeen](kayttoohje.md) kuvaamalla tavalla GitHubista ja ajamalla se komentoriviltä. Myös _jar_-tiedoston muodostus ja suoritus sekä releasessa olevan _jar_-tiedoston lataus ja suoritus on testattu käyttöohjeen mukaisesti. Paikallisesti sovellusta on testattu myös NetBeansissa.

Sovellusta on testattu sekä uudella että tietokannassa jo olevalla pelaajanimellä. Tietokannan päivittymistä on testattu pelaamalla useita pelejä samalla pelaajanimellä. Kaikki [määrittelydokumentissa](vaatimusmaarittely.md) kuvatut toiminnallisuudet on testattu.

Testauksella on pyritty varmistamaan, ettei ohjelma kaadu missään tilanteessa. Tätä varten peliä on koetettu pelata myös sääntöjen vastaisesti esimerkiksi yrittämällä heittää noppia enemmän kuin kolme kertaa vuorossa. Avausnäkymässä pelaajanimen syöttöä on testattu erilaisilla, myös virheellisillä syötteillä.

## Sovellukseen jääneet laadulliset puutteet

Yhdessä testaustapauksessa ohjelma näytti virheellisen _Alert_-viestin, kun yritettiin heittää neljännen kerran saman kierroksen aikana: uudessa ikkunassa näkyi halutun viestin sijaan peliohjeet näyttävä viesti. Myös virheellinen viesti esti laittoman heiton, mutta käytettävyyden kannalta se voi aiheuttaa sekaannusta. Toistaiseksi kyseinen virhe ei ole esiintynyt uudelleen, eikä sille ole löytynyt syytä.
