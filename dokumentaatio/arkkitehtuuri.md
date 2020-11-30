# Arkkitehtuurikuvaus

## Rakenne

Ohjelman pakkaus- ja luokkarakenne (ennen tiedon pysyväistallennustoiminnallisuutta) on seuraava:

<img src="https://github.com/jenkarper/YahtzeeDesktop/blob/master/dokumentaatio/kuvat/luokka-pakkauskaavio.png" width="600">

## Toimintalogiikka

Uuden pelin alkaessa pääohjelma luo uuden Game-olion, joka puolestaan luo uudet Roll-, Checker- ja Scorecard-oliot. Roll-olio luo viisi uutta Die-oliota. Oheiseen sekvenssikaavioon on kuvattu tämä uuden pelin alku, ensimmäinen heitto ja heiton pisteytys. Yksinkertaisuuden vuoksi graafisen käyttöliittymän toiminnot on kuvattu vain siltä osin kuin ne liittyvät itse pelilogiikkaan.

<img src="https://github.com/jenkarper/YahtzeeDesktop/blob/master/dokumentaatio/kuvat/sekvenssikaavio_heitto-ja-pisteytys.png" width="1000">
