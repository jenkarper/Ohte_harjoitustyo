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

Testipakkauksessa _dao_ olevat testiluokat [HighscoreDaoDbTest](https://github.com/jenkarper/YahtzeeDesktop/blob/master/Yahtzee/src/test/java/dao/HighscoreDaoDbTest.java) ja [UserDaoDbTest](https://github.com/jenkarper/YahtzeeDesktop/blob/master/Yahtzee/src/test/java/dao/UserDaoDbTest.java) käyttävät testaukseen erillistä testitietokantaa _yahtzeeTest.db_, joka luodaan projektin juureen kuten varsinainen tietokantakin. (DAO-luokissa _UserDaoDb_ ja _HighscoreDaoDb_ olevat, tietokannasta tietoa poistavat delete-metodit ovat tällä hetkellä käytössä ainoastaan vastaavissa testiluokissa testauksen helpottamiseksi.)

### Testikattavuus

Käyttöliittymän rakentava koodi on rajattu kokonaan automatisoidun testauksen ulkopuolelle. Testatuista luokista yksinkertaiset _get_- ja _set_-metodit on jätetty testaamatta. Sellaiset _Game_-luokan metodit, jotka ainoastaan kutsuvat jotakin _UserDaoDb_- tai _HighscoreDaoDb_-luokan metodia, ei myöskään ole testattu. Testauksen rivikattavuudeksi tulee näin 90 % ja haaraumakattavuudeksi 87 %:

<img src="https://github.com/jenkarper/YahtzeeDesktop/blob/master/dokumentaatio/kuvat/testikattavuus.png" width="1000">

## Järjestelmätestaus

Järjestelmätasolla sovellusta on testattu manuaalisesti.

### Asennus

_Viittaus käyttöohjeeseen_

### Toiminnallisuudet

_Viitaus määrittelydokumenttiin_

## Sovellukseen jääneet laadulliset puutteet
