Reitinhakualgoritmien vertailu
==============================

Tämän ohjelman tarkoituksena on graafisen käyttöliittymän avulla havainnollistaa eri reitinhakualgoritmien tehokkuutta. Harjoitustyössä joudun implementoimaan ainakin seuraavia tietorakenteita ja algoritmeja

0. A*
0. BFS & DFS
0. Arraylist, Linkedlist, Queues

graafisen käyttöliittymän ensimmäinen versio
--------------------------------------------

lopullinen versio tulee olemaan paintin kaltainen työkalu, jossa voi piirtää esteitä, valita alku ja päätepisteet, käytettävän algoritmin sekä myös valita valmiiksi luoduista labyrinteistä mieleisensä.

![alt tag](sample.png)

viikkoraportti 2
----------------

tämän viikon deadline pääsi yllättämään, olin varautunut, että se on tiistaina kuten ensimmäiselläkin viikolla. my bad. ohjelmaa on kuitenkin tehty ja luokat kommentoitu, testit eivät valitettavasti ehtineet vielä mukaan. Ohjelmarunko on hyvällä mallilla ja aloitinkin jo tekemään muutamia javan valmiita tietorakenteita korvaavia jutskia, esim prioriteettijonon jota ainakin a* tarvitsee. aikavaativuudet yritän minimoida kurssin aikana, tällä hetkellä hexpriorityqueue käyttää bubblesorttia joka on vain proof of conseptina siellä.

Jatkan nyt projektin kehittämistä, luomalla ihan aluksi testit, toivottavasti ne ehtivät paikalle ennen kuin ohjaaja tämän tarkistaa.

viikkoraportti 3
----------------

A* toimii nyt kunnolla ja ohjelman runko sekä muoto ovat alkaneet saamaan lopullisia piirteitä, testejä on vitaaleille luokille. Prioriteettijono toimii nyt testatusti, joskin sitä voisi hieman viritellä. Joistakin luokista olen luopunut. Omia tietorakenteita tulee ensi viikon aikana ainakin luoda: lista lisäys ja poisto operaatioilla sekä 0(1) ajassa toimiva hajautuskartta.

![alt tag](astar.png)

viikkoraportti 4
----------------

vertaisarviointi tehty, omaan koodiin tullut lisää testejä sekä NodeQueue luokka korvaa nyt javan arraylistin sekä HexMap HashMapin. tunteja tällä viikolla on tullut liian vähän, yritän tällä viikolla antaa muilta tekemisiltä tälle projektille enemmän aikaa.

viikkoraportti 5
----------------

mazegenerator toimii nyt alustavasti ja astar ratkoo sen nätisti.
![alt tag](mazev1.png)

viikkoraportti 6
----------------

demotilaisuus meni onnistuneesti, pieni demoefekti oli havaittavissa.

lopullinen palautus
-------------------

![alt tag](finaldemo.png)

Ohjelma on nyt valmis. Siitä muodostui animaatiotyylinen demo. Reitinhakualgoritmit käyttävät omia tietorakenteita, mutta esteikön muodostamisessa on tällä hetkellä javan valmiit tietorakenteet, tämä johtuu siitä että omat listani olisi tullut toteuttaa set array hybrideiksi joissa kaikki operaatiot olisivat olleet lähelle O(1), en kuitenkaan nähnyt tätä tarkoituksenmukaiseksi sillä suuri joukko parta-ukkoja on miettinyt tätä ongelmaa vuosikymmeniä.

testausdokumentti
-----------------
ohjelmaa on testattu "development driven testing" menetelmällä, eli koodattu niin pitkään kunnes bugi on pysäyttänyt, sitten testien avulla on etsitty bugi. olen todennut, että tämän kokoisissa ohjelmistoissa tämä on tehokkain tapa työskennellä. siksi testejä on ehkä verrattaen vähän ja lähinnä vain omat tietorakenteeni on testattu kattavasti. ohjelman yleinen toiminta on todettu manuaalisesti toimivaksi.

käyttöohje
----------

./tiralabra.java <-- run and enjoy
