# xml-ws-siit-2016-project-service
Predmetni projekat iz predmeta XML i web servisi.

### Članovi tima:
- SW3-2013  Stefan Ristanović
- SW9-2013  Bojan Blagojević
- SWF-2013  Dragutin Marjanović


### Konfigurisanje MarkLogic Server-a

Potrebno je skinuti instalacioni fajl sa linka http://developer.marklogic.com/products, a zatim i pokrenuti instalaciju. Nakon što se instalacija završi potrebno je pokrenuti MarkLogic Server.  
Server se pokreće sljedećom komandom: **Select Start > Programs > MarkLogic Server > Start MarkLogic Server**.  
Za zaustavaljanje servera koristi se komanda: **Select Start > Programs > MarkLogic Server > Stop MarkLogic Server**.

Nakon što se server pokrenuo, u browser-u ukucati sljedeći url: http://localhost:8001, a zatim kliknuti na **OK**, pa **skip** dugme da bi se nastavilo sa konfiguracijom. Nakon toga se korisniku nudi forma za kreiranje korisnika sa **admin** privilegijama. Odabrati proizvoljno ime i lozinku (Dato ime i lozinku iskoristiti za logovanje koje se prikaze u alert dijalogu).

Zatim kreirati novog korisnika preko čijih kredencijala će se klijentska aplikacija konektovati na bazu podataka. Odabrati **Security > Users > Create**. Odabrati sljedeće kredencijale **username:xml-ws-user**, **password:xml-ws-pass**. 
Navedenom korisniku dodijeliti sljedeće uloge:
 * admin 
 * rest-admin
 * rest-reader 
 * rest-writer

Nakon kreiranja novog korisnika, kreirati novu bazu. U browser-u ukucati http://localhost:8000/appservices i ulogovati se kao kreirani korisnik.

Odabrati opciju ***+ New database*** i u polje ***Database name*** ukucati **xmlDB**.  

Nakon toga, kliknuti na dugme ***Configure***. Selektovati opciju **Collection Lexicon**.  
Kliknuti na dugme ***new REST API instances***.  
Za polje ***Server Name*** odabrati **xmlDBServer**, a za ***Port*** podesiti **8011**. 
 
    
    
### Mapiranje XML elemenata na Java klase

#### elementi.xsd
| XML element    | JAVA klasa    |
| -------------- | ------------- |
| dio            | Chapter       |
| glava          | Part          |
| odjeljak       | Section       |
| pododjeljak    | Subsection    |
| clan           | Article       |
| stav           | Paragraph     |
| tačka          | Clause        |
| podtačka       | Subclause     |
| alineja        | Item          |

#### korisnici.xsd
| XML element    | JAVA klasa    |
| -------------- | ------------- |
| adresa         | Address       |
| TLice          | Person        |
| TFizicko_lice  | Individual    |
| TPravno_lice   | LegalEntity   |
| TKorisnik      | User          |

#### propis.xsd
| XML element    | JAVA klasa    |
| -------------- | ------------- |
| propis         | Law           |

#### amandman.xsd
| XML element    | JAVA klasa    |
| -------------- | ------------- |
| amandman       | Amendment     |
| amandmani      | Amendments    |
| obrazlozenje   | Explanation   |


# Smjernice pisanja **ref** atributa iz klijenta
###_Preface_
Generalna ideja je da iz __amandmana__ imamo referencu na __zakone__. Kako idu ti zakoni? U amandmanu će se 
pojavljivati link koji gađa na naš URL - __localhost:9000/api/laws/XXX#YYY__ gdje __XXX__ predstavlja __ID zakona__, a __YYY__ ID konkretnog elementa.
Ovo implicira da:
- referenciranje na neki zakon/član/stav (bilo šta) iz amandmana mora biti __apsolutna putanja__ (dakle, počinje se od __ID zakona__)
- za ovo referenciranje meni generalno nije bitno šta je u sredini - bitno je da je na početku __ID zakona__, a na kraju __ID elementa__.
- iz ovoga slijedi da bi prilikom pisanja __zakona__ bilo poželjno za svaki element generisati jedinstven id da bismo se u __XHTML__-u mogli pozicionirati na njega.
- ovo još nije odrađeno u .pdf-u. 
- za pisanje referenici prilikom __PATCHING__-a koristiti __relativne putanje__ u odnosu na _propis:body_ (ne uključivati _propis:body_ u putanju :smile: )
