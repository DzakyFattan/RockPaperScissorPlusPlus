

<h1 align="center">
  <br>
  <a><img src="https://media.discordapp.net/attachments/893484082275708980/969594967880241152/icon.png" width="200"></a>
  <br>
 RockPaperScissors++
  <br>
</h1>

<h4 align="center">A 2-player, minecraft themed, turn based card game</h4>

<p align="center">
  <a href="#description">Description</a> •
  <a href="#code-structure">Code Structure</a> •
  <a href="#screenshots">Screenshots</a> <br>
  <a href="#compiling-&-running">Compiling & Running</a> •
  <a href="#credits">Credits</a> •
  <a href="#footnote">Footnote</a> 
</p>

## Description
Sebuah Aplikasi  game kartu turn based untuk 2 pemain. Pemain bermain secara bergantian pada 1 layar yang sama. Tujuan dari game ini adalah menghabiskan health points (HP) musuh. HP dapat berkurang apabila terkena serangan dari kartu karakter yang diletakkan di board. HP  Pemain bisa dilindungi dari serangan musuh oleh karakter yang dikeluarkan pemain sehingga secara efektif HP yang turun pertama kali ketika musuh menyerang merupakan karakter pemain tersebut. Pemain bisa mengambil sebuah kartu setiap ronde berakhir dengan maksimal kartu yang disimpan 5. Pemain dapat mengeluarkan kartu-kartu tersebut berdasarkan mana yang perlu dibayar untuk mengeluarkan kartu tersebut serta mana yang dimiliki pemain. Mana akan di-reset setiap ronde berakhir.

## Code Structure
```
└─src
    └─main
        ├─java
        │  └─com
        │      └─aetherwars
        │          ├─battle
        │          ├─model
        │          ├─player
        │          ├─slot
        │          ├─spells
        │          └─util
        └─resources
            └─com
                └─aetherwars
                    └─card
                        ├─data
                        └─image
                            ├─character
                            └─spell
                                ├─level
                                ├─morph
                                ├─potion
                                └─swap
```

## Screenshots
![Main Screen](https://media.discordapp.net/attachments/893484082275708980/969597536925024276/unknown.png?width=1177&height=701)![Player 1 picks a card](https://media.discordapp.net/attachments/893484082275708980/969597554557865994/unknown.png?width=1188&height=702)![Player 1 picked a card](https://media.discordapp.net/attachments/893484082275708980/969597580214411264/unknown.png?width=1180&height=700)
## Compiling & Running
1. Install [java 8](https://www.oracle.com/java/technologies/javase/javase8-archive-downloads.html)
2. Install [gradle](https://gradle.org/)
3. Download source code dari repository ini
4. Pergi ke folder yang mengandung source code repository ini
5. Buka terminal pada folder ini
6. Jalankan program melalui command berikut
```bash
# Jalankan Program
./gradlew run
```

## Credits
<table>
    <tr>
      <td><b>Nama</b></td>
      <td><b>NIM</b></td>
    </tr>
    <tr>
      <td><b>Dzaky Fattan Rizqullah</b></td>
      <td><b>13520003</b></td>
        <tr>
      <td><b>Bariza Haqi</b></td>
      <td><b>13520018</b></td>
    </tr>
            <tr>
      <td><b>Jeremy S.O.N. Simbolon</b></td>
      <td><b>13520042</b></td>
    </tr>
            <tr>
      <td><b>Farrel Farandieka Fibriyanto</b></td>
      <td><b>13520054</b></td>
    </tr>
            <tr>
      <td><b>Zayd Muhammad Kawakibi Zuhri</b></td>
      <td><b>13520144</b></td>
    </tr>
</table>

## Footnote




