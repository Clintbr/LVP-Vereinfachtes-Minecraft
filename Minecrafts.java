import java.time.LocalTime;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Random;
import java.util.Scanner;
import java.util.stream.Collector;
import java.util.stream.Collectors;

// InnerMinecrafts
interface InnerMinecrafts {
    // Methods that define the functionalities of the Minecraft game 
    public List<Elements> allElements();

    public void weatherForecast();
     
    public void realTime();
  
    public void rotateLeft();

    public void rotateRight();
  
    public void go();
  
    public void goFloor();
  
    public void goForest();
  
    public void play();
  
    public void play(int red, int green, int blue);
  
    // Other methods related to the Minecraft game functionality
  
    public void unzoom();
  
    public void zoom();
  
    public void tree(double posX, double posY, double posZ);
  
    public boolean controlBuildingPosition(double x, double y, double z);
  
    public boolean controlBuildingPositionDown(double x, double y, double z);
  
    public boolean controlBounds(double width, double depth);

    // Method for visualising everythings
    public void drawWorld();
    // Methods for moving the player an regulate the speed
    public void speedUp();

    public void sppedDown();

    public void leftMove();
  
    public void rightMove();
  
    public void forwardMove();
  
    public void backwardMove();

    //Methode for coming back to a blanck field
    default void endGame() {
        allElements().clear();
        drawWorld();
    } 

    // Methode for choosing a mission
    public void mission(int missionNummer);
}
 
// InnerMinecrafts
 
Clerk.markdown(
    Text.fillOut(
"""
# <span style="color: orange;">Vereinfachtes MineCraft mit neuer View (als “Clint” benannt) </span>

<span style="color: blue;">_Clint Bryan NGUENA_, _Technische Hochschule Mittelhessen_, _Projekt für Programmieren2_</span>

## <span style="color: orange;">Integration der Canva-API für 3D-Modellierung </span>
    `   
        Zu der Realisation dieses Spiels wurde erstmal eine neue "View" zur Visualisation des Spielsverfahren.

        a. Nutzung einer JavaScript-Bibliothek zur Anbindung von Canva-
        Funktionalitäten.
        b. Erstellung eines Java-Wrappers, um Java mit der JavaScript-Datei
        zu verbinden.
        c. Projektion von 2D-Objekten auf eine 3D-Ebene zur Erzeugung
        realistischer 3D-Strukturen.
    `

Die Schildkröte kennt die folgenden Kommandos:

Befehl | Bedeutung
-------|----------
`draw2D(double angle, double distance, double width, double height, double depth, int red, int green, int blue)`  | `Zeichnet ein flaches Object(genauer gesagt ein Quadrat)`
`draw3D(int cubeNumber, double angle, double distance, double width, double height, double depth, int red, int green, int blue)` | `Zeichnet ein drei dimensionales Object in dem Spielfeld`
`colorStroke(boolean color)`     | `Zeichnet eine Wettersimulation für die Welt`
`left(double degrees)`      | `Drehe dich um die Gradzahl _degrees_ nach links`
`color(int red, int green, int blue)` | `Setze Stiftfarbe mit den RGB-Farbanteilen _red_, _green_ und _blue_`
`color(int rgb)`            | `Setze Stiftfarbe auf den kodierten RGB-Farbwert _rgb_`
`reset()`                   | `Lösche ZeichenRaum`


Mit diesen Kommandos wird die Schildkröte über die Zeichenfläche geschickt und das Zeichnen gesteuert. Damit kann man jetzt Das Minecraft-Spiel programmieren.

## <span style="color: orange;"> Das Spiel in der Tiefe </span>

### 1.Konzeption des Interface
    
    `Was es im Wesentlichen ermöglicht:
        -Welt erschaffen: Es ermöglicht das Erstellen von 3D-Welten mit verschiedenen Elementen wie Blöcken, Bäumen und Spielern.
        -Interaktion: Spieler können sich in dieser Welt bewegen, Blöcke platzieren und entfernen, sowie verschiedene Aktionen ausführen wie das Bauen von Häusern oder Brücken.
        -Zeit und Wetter: Die Welt simuliert Zeit und Wetter.
        -Rotation und Zoom: Die Perspektive kann gedreht und gezoomt werden, um die Welt aus verschiedenen Blickwinkeln zu betrachten.
    
    Wie es funktioniert:
    
        -Klassen und Objekte: Das Interface verwendet Klassen wie Elements (für Blöcke und Objekte) und Clint (für die Darstellung) um die Welt aufzubauen.
        -Methoden: Es gibt zahlreiche Methoden, um verschiedene Aktionen auszuführen, wie z.B. das Erstellen von Bäumen (tree), das Bewegen des Spielers (leftMove, rightMove usw.) oder das Bauen von Strukturen (buildHouse, buildSkyscraper).
        -Listen: Listen werden verwendet, um die Elemente der Welt zu speichern und zu verwalten.
        -Zeichnen: Die Methode drawWorld sorgt dafür, dass die Welt auf dem Bildschirm dargestellt wird.
    `

Das interface sieht dann so aus:

```java
${interface}
```

### 2. Das Enum Type

    `Es enthält alle verschieden Typen, die ein Objekt von Minecraft übernehmen kann:
    `
```java
${enum}
```

### 3. Die Klasse WorldLayer

    `Sie erlaubt es flache Objekte im drei dimensionalen Raum zu zeichnen und wird zur umfarbung einer Blocksoberfläche benutzt.
    `
```java
${layer}
``` 

### 4. Die Klasse Elements

    `Sie ist quasi den Esckstein von Minecraft, indem sie den Datentyp von allen in dem Spiel enthaltenen Objekten repräsentiert. So ist es konzipiert:
        1. X, Y und Z Koordinatoren zur Positionnierung von Objekten
        2. red, green, blue für die Farbe.
        3. layer von Typ WorldLayer für die Oberfläche von Objekten.
        4. eine Variable von Typ LocaltTime, um sich die Erstellungszeit von Objekten zu merken.
    `

### 5. Die Klasse Minecraft

    `Diese Klasse erstellt eine Minecraft-Welt mit den folgenden Funktionen bzw Szenarien:

    1. Eine Welt kann mit gewünschten Parametern(Farbe,Größe, Tiefe und Breite) durch eine Instanz der Klasse kreiert werden;

    2. Spieler kann sich in der Welt bewegen und seine Geschwindigkeit ändern;

    3. Die Welt besteht aus Blöcken:    - Spieler können neue Blöcke bauen.
                                        - Spieler können dann weiterhin : Baume, Häuser und Gebäude sowie Brücken und Treppen bauen

    4. Spieler kann jedes erzeugte Element abbauen

    5. Die Welt kann erweitert werden: Die Weltgröße kann in Echtzeit angepasst werden, durch Hinzufügen von neuen Landabschnitten.

    6. Umgebende Funktionen:    - Die Welt hat Jahreszeit (Sommer oder Winter).
                                - Die Welt hat ein Zeitsystem (Jedes Element hat ein Erstelldatum)
                                - Spieler können in die Welt hinein- und herauszoomen.
                                - Spieler können die Welt rotieren.

    7. zusätztliche Szenarien:  a- Zeitreise
                                b- Missionen zu erfüllen                           

    `Der Code von Minecrafts verwendet die folgenden Klassen und Interface aus Java-Bibliothek java.util:`

    <span style="color: blue;"> ArrayList(Klasse); List(Interface); LocalTime, Random(Klasse), Stream(Interface), Collector(Interface), Collectors(Klasse), Comparator(Klasse), LocalTime(Klasse) </span>

Die Klasse Minecraft hat als zentrale Instanzvariablen:

    ein Objekt "clint" der Typ Clint zur Zeichnung für das Live-View;

Der Code ist in mehrere Methoden unterteilt, die jeweils eine spezifische Aufgabe erfüllen. Hier ist eine Zusammenfassung der Methoden:

    - Minecrafts(int width, int height, int depth): Dies ist der Konstruktor der Minecrafts-Klasse. Er nimmt drei Argumente entgegen: die Breite, Höhe und Tiefe der Welt.

    - Minecraft(): Dies ist der Konstruktor bei keinem Parameter. Beim Aufruf wird er mit vordefinierten Parametern eine Instanz der Klasse Minecrafts erstellen

    - weatherForecast(): Diese Methode bestimmt das Wetter (Sommer oder Winter) für die Welt.

    - realTime(): Diese Methode zeigt die aktuelle Zeit an.

    - rotateLeft() und rotateRight: Diese Methoden rotiert die Welt links beziehungsweise rechts.

    - go(): Diese Methode erstellt die Welt.

    - goFloor(): Diese Methode erstellt den Boden der Welt.

    - goForest(): Diese Methode erstellt einen Wald in der Welt.

    - play(): Diese Methode fügt der Welt einen Spieler hinzu.

    - play(int red, int green, int blue): Diese Methode ermöglicht es dem Benutzer, die Farbe des Spielers festzulegen.

    - stairsLeft(), stairsRight(), stairsBackward(), stairsForward(): Diese Methoden erstellen Treppen in der angegebenen Richtung.

    - unzoom(): Diese Methode zoomt aus der Welt heraus.

    - zoom(): Diese Methode zoomt in die Welt hinein.

    - tree(double posX, double posY, double posZ): Diese Methode erstellt einen Baum an der angegebenen Position.

    - controlBuildingPosition1(), controlBuildingPosition2(): Diese Methoden überprüfen, ob der Spieler in der Nähe eines Gebäudes oder ein Objekt ist.

    - controlBounds(): Diese Methode überprüft, ob sich der Spieler innerhalb der Grenzen der Welt befindet.
    
    - speedUp() und speedDown() reguliert die Geschwindigkeit von dem Spieler

    - leftMove(), rightMove(), forwardMove(), backwardMove(): Diese Methoden bewegen den Spieler in die angegebene Richtung.

    - upMove(), downMove(): Diese Methoden bewegen den Spieler nach oben oder unten.
    
    - drawWorld(): Diese Methode erlaubt es den aktuellen Zustand der Welt zu zeichnen.

    -verifyPlaceForHouse(baseWidth, baseDepth)                                                  |
                                                                                                |
    - verifyPlaceForSkyscraper(baseWidth, baseDepth)                                            |
                                                                                                |
    - verifyPlaceForPyramid(baseWidth, baseDepth)                                               | => prüft ob einen Leeren Raum für den Aufbau eines Gebäudes vorhanden ist anhand von stream().anyMatch()
                                                                                                |
    - verifyPlaceForBridgeLeft(baseWidth, baseHeight, baseDepth)                                |
                                                                                                |
    - verifyPlaceForBridgeForward(baseWidth, baseHeight, baseDepth)                             |

    - buildSkyscraper(), buildBridgeForward(), buildBridgeLeft(), buildHouse(), buildPyramid(): Diese Methoden erlauben es, ein building oder einen Baum zu bauen bzw einzupflanzen.

    - destructBackward(), destructForward(), destructLeft(), destructRight(), megaDestruction(): Diese Methoden erlauben es, irgendwelches Element abzubauen.

    - mission() : Diese Methode erlaubt es, einen Auftrag zu nehmen, den dann erfüllt werden sollte.

    - drawHelloInMineCraft(): eine einmalig benutzte Methode für die Begrüßung zum Spielbeginn          |
                                                                                                        |
    - drawHello(double startX, double startY, double startZ, int red, int green, int blue)              |
                                                                                                        |
    - drawMine(double startX, double startY, double startZ, int red, int green, int blue)               |
                                                                                                        |
    - drawCraft(double startX, double startY, double startZ, int red, int green, int blue)              | => wird von drawHelloInMinecraft benutzt, um Buchstaben und Worte zu zeichnen
                                                                                                        |
    - drawVerticalLine(double x, double y, double z, int length, int red, int green, int blue)          |
                                                                                                        |
    - drawHorizontalLine(double x, double y, double z, int length, int red, int green, int blue)        |
                                                                                                        | 
    - drawDiagonalLine(double x, double y, double z, int length, int red, int green, int blue)          |
                                                                                                        |
    - drawReverseDiagonaleLine(double x, double y, double z, int length, int red, int green, int blue)  |
`
""", Map.of("interface", Text.cutOut("./Minecrafts.java", "// InnerMinecrafts"),
"enum", Text.cutOut("./Minecrafts.java", "// Type"),
"layer", Text.cutOut("./Minecrafts.java", "// layer"))));;
 
Clerk.markdown(
    Text.fillOut(
"""
## <span style="color: orange;"> Szenarien Veranschaulichung </span>

### Szenario 1 – Neue Welt kreieren mit zufälliger Jahreszeitx                  

    `Mit folgenden Aufrufen kann man eine neue Welt (anhand goFloor(), die von go() aufgeruft wird) kreieren(einen Wald wird dann auch der Welt hinzugefügt anhand der Methode goForest, die von go() aufgeruft wird, falls die Welt genug groß ist):
    Da hat man die Möglichkeit Parametern für die Welt auszuwählen(Tiefe, Größe, Farbe, ...)
    Idealealerweise würde man sich bezüglich des Höhe der Welt für einen Wert zwischen 9 und 12. Da habe ich 10 ausgewählt.
    Die Jahres wird immer zufällig entscheidet.

```java
${0}
```

""",  Text.cutOut("./Minecrafts.java", "// minecraft1")));
// minecraft1 
Minecrafts minecraft1 = new Minecrafts(30, 10, 30, 50, 50, 50);
minecraft1.go();
// minecraft1


Clerk.markdown(
    Text.fillOut(
"""

### 1. Szenario 2 – Spieler bewegen                 

`   Mit folgenden Aufrufen kann man ein Player in die Welt rein lassen und bewegen:
`
```java
${0}
```

""",  Text.cutOut("./Minecrafts.java", "// minecraft2")));
// minecraft2
Minecrafts minecraft2 = new Minecrafts(30, 10, 30);
minecraft2.go();
minecraft2.play(200, 200, 200); // Auswahl der Farbe vom Player
minecraft2.leftMove();
minecraft2.leftMove();
minecraft2.rightMove();
minecraft2.forwardMove();
minecraft2.forwardMove();
minecraft2.upMove();
minecraft2.drawWorld();
// minecraft2

Clerk.markdown(
    Text.fillOut(
"""

### Szenario 3 – Häuser, Gebäude(Skyscraper und Pyramide), Brücken und Treppen bauen                 

`   Mit folgenden Aufrufen kann man irgendwelches Gebäude oder Baum bauen beziehunsweise einpflanzen:
`
```java
${0}
```

""",  Text.cutOut("./Minecrafts.java", "// minecraft3")));

// minecraft3
Minecrafts minecraft3 = new Minecrafts(30, 10, 30);
minecraft3.go();
minecraft3.play(200, 100, 200);
minecraft3.buildHouse(4, 4, 10, 0, 50);
minecraft3.stairsForward(1);
minecraft3.stairsForward(1);
minecraft3.stairsForward(1);
minecraft3.stairsForward(1);
minecraft3.buildSkyscraper(4, 10, 70,10,40);
Thread.sleep(2000);
minecraft3.buildPyramid(20, 2, 20,10,50);
Thread.sleep(2000);
minecraft3.buildBridgeLeft(-1, -8, 1.5, 10,10,10);
Thread.sleep(2000);
minecraft3.tree(-3, -7.5, 12);
minecraft3.drawWorld();
// minecraft3


Clerk.markdown(
    Text.fillOut(
"""

### Szenario 4 – Häuser, Gebäude(Skyscraper und Pyramide), Brücken und Treppen abbauen                 

`   Mit folgenden Aufrufen kann man alle erzeugte Elemente abbauen anhand der methode megaDestruction()
    (wenn man gezielt abbauen will gibt es auch destructLeft, destructRight, destructForward und destructBackward):
`
```java
${0}
```

""",  Text.cutOut("./Minecrafts.java", "// minecraft4")));
// minecraft4
Minecrafts minecraft4 = new Minecrafts(30, 10, 30)
minecraft4.go();
minecraft4.play(200, 100, 100);
minecraft4.buildHouse(4, 4, 10, 0, 50);
Thread.sleep(2000);
minecraft4.buildPyramid(20, 2, 20,10,50);
Thread.sleep(2000);
minecraft4.buildBridgeLeft(-1, -8, 1.5, 10,10,10);
minecraft4.drawWorld();
Thread.sleep(5000);
minecraft4.megaDestruction();
// minecraft4
 
Clerk.markdown(
    Text.fillOut(
"""

### Szenario 5 - Welt erweitern, rotieren und zoomen                 

    `Mit folgenden Aufrufen kann man der Welt einen neuen Landabschnitt hinzufügen:
    hier werden wir eine blaue Land einfügen und zoomen.

`
```java
${0}
```

""",  Text.cutOut("./Minecrafts.java", "// minecraft5")));

// minecraft5
Minecrafts minecraft5 = new Minecrafts(20, 10, 20);
minecraft5.go();
minecraft5.play(100, 100, 200);
Thread.sleep(5000);
minecraft5.extendsWorld(5, -8, 5, 10, 10, 60);
minecraft5.zoom();
minecraft5.zoom();
minecraft5.zoom();
minecraft5.zoom();
minecraft5.rotateLeft();
Thread.sleep(2000);
minecraft5.rotateLeft();
Thread.sleep(2000);
minecraft5.rotateLeft();
Thread.sleep(2000);
minecraft5.rotateLeft();
Thread.sleep(2000);
minecraft5.rotateLeft();
minecraft5.drawWorld();
// minecraft5

Clerk.markdown(
    Text.fillOut(
"""
## <span style="color: orange;"> Zusätztliche Szenarien </span>

### Szenario 6 - Zeitreise                

    `Die Zeitreise ist hier anhand LocalTime ermöglicht. Jedes Element hat ein Parameter von dem Typ LocalTime.
    Der Code merkt sich dadurch das Erstelldatum von Jedem Block und wenn zum Beispiel ein Haus abbgebaut wurde,
    kannst du zu dem Zeitpunkt zurückgehen wo dieses Haus noch vorhanden war. 
    Es gibt jedoch ein "aber" man kann in der Vergagenheit nicht leben, sobald sie eine Aktion durchführen würden,
    würde dir plötzlich in aktuellen Zeitraum zurückgebracht.
    
    Die nützlichen Aufrufe sehen dann so aus.

    `
```java
${0}
```

""",  Text.cutOut("./Minecrafts.java", "// minecraft6")));

// minecraft6
Minecrafts minecraft6 = new Minecrafts(20, 10, 20);
minecraft6.go();
minecraft6.play(200, 200, 100);
Thread.sleep(30000);
minecraft6.buildHouse(4, 4, 10, 0, 50);
Thread.sleep(100000);
minecraft6.megaDestruction();
Thread.sleep(30000);
minecraft6.returnInThePast(2);
// minecraft6

Clerk.markdown(
    Text.fillOut(
"""
### Szenario 7 - Missionen               

    `Um das Spiel noch spannender zu machen habe ich die Spielsituatuation um 02 Aufträge erweitert.
    So gelangen wir zum Datensatzt Mission. Darin wurde 02 Missionen festgelegt:

#### <span style="color: orange;"> record Mission </span>

    Der Construktor hat ein Parameter "nummer" zur Wahl der Mission und ein Parameter vom Typ "Minecraft".
    1. die Methode ***setName()*** weist der Mission einen Namen je nach der Nummer zu (anhand von "switch" ).
    2. die Methode ***setMissionGuide()*** weist je nach dem Name den Spieler daraufhin, was in der Mission gemacht wird (anhand von "switch").
    3. die Methode ***setMission()*** stellt das Spielfeld für die Mission bereit (anhand von "switch" ).
    4. die Methode ***setSuccessCondition()*** gibt an, welche Konditionen erfüllt werden soll damit die Mission als "completed" betrachtet werden kann (anhand von "switch").
    `
    so sieht den genannten Datensatzt aus:

```java
${0}
```

""",  Text.cutOut("./Minecrafts.java", "// minecraft7")));

Clerk.markdown(
    Text.fillOut(
"""                   
` Die 02 verschiedenen Missionen sind: 
`
##### <span style="color: orange;"> 1.destroy the Forest </span>
    
    ` Du solltest einen Wald total abholzen.
    `
    so sieht die Aufruffolge aus:

```java
${0}
```

""",  Text.cutOut("./Minecrafts.java", "// minecraft8")));

// minecraft8
Minecrafts minecraft8 = new Minecrafts(20, 10, 20);
minecraft8.go();
minecraft8.play(200, 200, 100);
Thread.sleep(5000);
minecraft8.mission(1);
// minecraft8

Clerk.markdown(
    Text.fillOut(
"""   
##### <span style="color: orange;"> 2.find your lost friend </span>

    ` Du solltest deinen verlorenen Freund finden.
      Player sollte sich in der Welt bewegen und seinen Freund suchen.
    `
    so sieht die Aufruffolge aus:

```java
${0}
```
""",  Text.cutOut("./Minecrafts.java", "// minecraft9")));
// minecraft9
Minecrafts minecraft9 = new Minecrafts(20, 10, 20);
minecraft9.go();
minecraft9.play(200, 200, 100);
Thread.sleep(5000);
minecraft9.mission(2);
// minecraft9

Clerk.markdown(
    Text.fillOut(
"""   
## <span style="color: orange;"> Interaktivität und Vereinfachung der Funktionnalitätsanwendung : die Klasse manageMinecraft</span>

    Benutzte Bibliothek: Scanner
    Instanzvariablen: minecraft vom Typ Minecraft und mission vom Typ Mission
    Die Methoden werden immerhin aufgerufen aber, der gewünschte Parameter werden nach und nach über die Console eingegeben, was Die Interaktion erleichtert.
    Diese Methoden basieren auf die von der Klasse Minecraft.

    Hier ist ein Übersicht der Methoden von der Klasse manageMinecraft:

    - startGame(): begrüßt und beginnt ein neues Spiel (nutzt go(), play() und drawHelloInMinecraft() aus der Klasse Minecrafts)

    - plantTree(): um einen einzupflanzen (nutzt tree())

    - extendsWorld(): erweitert die Welt (nutzt extendsWorld() aus Minecrafts)

    - zoom(): erlaubt es hinein oder hinaus zu zoomen (nutzt zoom() und unzoom() aus Minecrafts)

    - move(): erlaubt es den Spieler in die gewünschte Richtung zu bewegen ( nutzt moveL )

    - rotateWorl(): rotiert die Welt links oder rechts je nach Wünsch( nutzt rotateLeft() unf reotateRight())

    - manageTime(): erlaubt ein Zeitreise und Rückreise(nutzt realtime() und returnInThePast())

    - buildBlock(): einen Block in die Welt setzen

    - buildSomething(): erlaubt es irgendwelche Infrastruktur(Hause, Pyramid,...) der Welt zu bauen.
    
    - buildStairs(): erlaubt es Treppen in die gewünschte Richtung zu bauen (nutzt stairsLeft(), -Right(), Forward() und Backward());

    - destructSomething(): erlaubt es entweder in eine gewünschte Richtung oder in alle vier Richtungen zu zerstören(nutzt destructLeft(), -Right(), -Forward() and -Backward() aus Minecrafts)

    - mission(): erlaubt es eine Mission auszuwählen und zu erfüllen(nutzt mission() aus Minecrafts)

Die erste Methode startGame() öffnet das Spiel mit einer Begrüßung: folglich sind die Aufrufe

```java
${0}
```

""",  Text.cutOut("./Minecrafts.java", "//minecraft10")));
//minecraft10
Minecrafts minecraft10 = new Minecrafts(20, 10, 20);
ManageMinecraft manage = new ManageMinecraft(minecraft10);
Thread.sleep(5000);
manage.startGame();
//minecraft10

public class Minecrafts implements InnerMinecrafts{

    Clint clint = new Clint(2000, 1300);
    List<Elements> worldElements = new ArrayList<>();
    private List<Elements> destroyedElements = new ArrayList<>();
    List<Double> universHeighList = new ArrayList<>();
    final int red, green, blue;
    double baseHeight, baseWidth, baseDepth;
    private boolean started = false; // prüft ob die Welt gezeichnet wurde
    private boolean player = false; // prüft od der Spieler bereits auf dem Spielfeld ist
    double angle = 4;
    double worldDistance = 10;
    final double worldWidth, worldHeight, worldDepth;
    double speed = 0.5;
    LocalTime currentTime;
    boolean weather = false; // false ist Winter and true ist Sommer 

    Minecrafts(int width, int height, int depth, int red, int green, int blue) {
        assert width > 0 : "es muss eine Länge > 0 gesetzt werden";
        assert depth > 0 : "es muss eine Tiefe > 0 gesetzt werden";
        this.worldWidth = baseWidth = width;
        this.worldHeight = baseHeight = height - 18;
        this.worldDepth = baseDepth = depth;
        this.red = red;
        this.green = green;
        this.blue = blue;
        currentTime = LocalTime.now();
        this.universHeighList.add(baseHeight);
    }
    
    Minecrafts(int width, int height, int depth) {
        this(width, height, depth, 0, 120, 60);
    }

    Minecrafts() {
        this(30,10,30);
    }

    public List<Elements> allElements() {
        return worldElements;
    }
     
    public void weatherForecast() {
        Random rand = new Random();
        weather = rand.nextBoolean();
        clint.weather = weather;
        if(weather == false) {
            System.out.println("We are in Winter");
        } else {
            System.out.println("We are in Summer");
        }
    }

    public void realtime() {
        this.currentTime = LocalTime.now();
        System.out.println("Aktuelle Zeit: " + currentTime);
    }

    public void rotateLeft() {
        assert angle > 3.5 && angle < 4.5;
        this.angle-= 0.1;
        drawWorld();
    }

    public void rotateRight() {
        assert angle > 3.5 && angle < 4.5;
        this.angle+= 0.1;
        drawWorld();
    }

    public void go() {
        weatherForecast();
        if(!started && !player) {
            goFloor();
            if(worldWidth >= 30 && worldDepth >= 20) goForest();
            this.started = true;
            drawWorld();
        } else
        System.out.println("The world is already started");
    }
    
    public void goFloor() {
        Random rand = new Random();
        for(double i = worldWidth; i > -7; i-= 0.5) {          
            for(double j = worldDepth; j > -7; j-= 0.5) {
                int color3 = rand.nextInt(blue);
                worldElements.add(new Elements(i, baseHeight, j, 0, 0, 0, Type.BLOCK, LocalTime.now()).newLayer(i, baseHeight + 0.5, j, red, green, color3));
            }       
        }
    }

    public void goForest() {
        Random rand = new Random();
        for(double i = 0; i <= 6; i++) {
            int width0 = (int) worldWidth;
            int depth0 = (int) worldDepth;
            double posX = width0 - 20 + rand.nextInt(20);
            double posZ = depth0 - 20 + rand.nextInt(20);
            tree(posX, baseHeight + 0.5, posZ);
        }
        //drawWorld();
    }

    public void play() {
        play(0, 0, 0);   
    }

    public void play(int red, int green, int blue) {
        if(player == false && started == true) {
            worldElements.add(new Elements(1, baseHeight + 0.1, 1, red, green, blue, Type.PLAYER, LocalTime.now()));
            clint.draw3D(1, angle, worldDistance, 1, baseHeight, 1, red, green, blue);
            System.out.println("The player is ready.");
            player = true;
        }
        else {
            System.out.println("there is already a player on the field");
        }
    }
    
    void stairsLeft(double numbers) throws InterruptedException {
        stairsLeft(numbers, 10, 150, 10);
    }
    void stairsRight(double numbers) throws InterruptedException {
        stairsRight(numbers, 10, 150, 10);
    }
    void stairsBackward(double numbers) throws InterruptedException {
        stairsBackward(numbers, 10, 150, 10);
    }
    void stairsForward(double numbers) throws InterruptedException {
        stairsForward(numbers, 10, 150, 10);
    }

    void stairsLeft(double numbers, int red, int green, int blue) throws InterruptedException {
        double[] playerPosition = verifyPosition();
        double playerHeigth = playerPosition[1];
        double width = playerPosition[0] - 1;
        double depth = playerPosition[2] + 0.5;
        for(double k = 0; k < 0.5; k+= 0.5) {
            for(double i = width + numbers; i > width; i-= 0.5) {
                for(double j = depth + k ; j >= depth + k; j-= 0.5) {
                    worldElements.add(new Elements( i, playerHeigth + 0.5, j, 0, 0, 0, Type.BLOCK, LocalTime.now()).newLayer(i, playerHeigth + 1, j, red, green, blue));
                }  
            }
        }
        upMove();
        leftMove();
        drawWorld();
    }

    void stairsRight(double numbers,int red, int green, int blue) throws InterruptedException {
        double[] playerPosition = verifyPosition();
        double playerHeigth = playerPosition[1];
        double width = playerPosition[0] - 1;
        double depth = playerPosition[2] - 0.5;
        for(double k = 0; k < 0.5; k+= 0.5) {
            for(double i = width + numbers; i > width; i-= 0.5) {
                for(double j = depth - k ; j <= depth - k; j+= 0.5) {
                    worldElements.add(new Elements( i, playerHeigth + 0.5, j, 0, 0, 0, Type.BLOCK, LocalTime.now()).newLayer(i, playerHeigth + 1 , j, red, green, blue));
                }  
            }
        }
        upMove();
        rightMove();
        drawWorld();
    }

    void stairsForward(double numbers, int red, int green, int blue) throws InterruptedException {
        double[] playerPosition = verifyPosition();
        double playerHeigth = playerPosition[1];
        double depth = playerPosition[0] + 0.5;
        double width = playerPosition[2] - 1;
        for(double k = 0; k < 0.5; k+= 0.5) {
            for(double j = width + numbers; j > width; j-= 0.5) {
                for(double i = depth + k ; i >= depth + k; i-= 0.5) {
                    worldElements.add(new Elements( i, playerHeigth + 0.5, j, 0, 0, 0, Type.BLOCK, LocalTime.now()).newLayer(i, playerHeigth + 1, j, red, green, blue));
                }  
            }
        }
        upMove();
        forwardMove();
        drawWorld();
    }

    void stairsBackward(double numbers, int red, int green, int blue) throws InterruptedException {
        double[] playerPosition = verifyPosition();
        double playerHeigth = playerPosition[1];
        double depth = playerPosition[0] - 0.5;
        double width = playerPosition[2] - 1;
        for(double k = 0; k < 0.5; k+= 0.5) {
            for(double j = width + numbers; j > width; j-= 0.5) {
                for(double i = depth - k ; i <= depth - k; i+= 0.5) {
                    if(checkPosition1(i, playerHeigth + 0.5, j) == true) {
                        System.out.println("building is allowed");
                        worldElements.add(new Elements( i, playerHeigth + 0.5, j, 0, 0, 0, Type.BLOCK, LocalTime.now()).newLayer(i, playerHeigth + 1, j, red, green, blue));
                    }
                   
                }  
            }
        }
        upMove();
        backwardMove();
        drawWorld();
    }

    public void unzoom() {
        assert this.worldDistance < 20 : "The world is max zoomed";
        this.worldDistance += 0.5;
        clint.reset();
        this.drawWorld();
        System.out.println("The world is unzoomed " + this.worldDistance);
    }

    public void zoom() {
        assert this.worldDistance >= 8 : "The world is max unzoomed";
        this.worldDistance -= 0.5;
        clint.reset();
        this.drawWorld();
        System.out.println("The world is zoomed " + this.worldDistance);
    }

    public void tree(double posX, double posY, double posZ) {
        double height = 0;
        Random rand = new Random();
        int treeLength = rand.nextInt(3);
        for(double i = posY; i <= posY + treeLength + 3; i += 0.5) {
            worldElements.add(new Elements( posX, i, posZ, 89, 38, 11, Type.BLOCK, LocalTime.now()).newLayer(posX, i + 0.5, posZ, 0, 0, 0));
            height = i;
        }

        for(double j = posX + 2; j > posX - 2; j-= 0.5) {          
            for(double k = posZ + 2; k > posZ - 2; k-= 0.5) {
                for(double z = height + 0.5; z < height + 3.5; z += 0.5) {
                    worldElements.add(new Elements(j, z, k, 0, 50, 0, Type.BLOCK, LocalTime.now()).newLayer(j, z + 0.5, k,  0, 100, 0));
                }
            }  
        }
    }

    public boolean controlBuildingPosition(double x, double y, double z) {
        boolean actionNotPossible = worldElements.stream().anyMatch((Elements e) -> e.x == x && e.z == z && ((e.y >= y + 0.4 && e.y <= y + 0.9) || (e.y >= y - 0.4 && e.y <= y - 0.9)));
        if (actionNotPossible) {
            System.out.println("Action not possible you are near a building");
        }
        return actionNotPossible;
    }

    public boolean controlBuildingPositionDown(double x, double y, double z) {
        boolean actionNotPossible = worldElements.stream().anyMatch((Elements e) -> e.x == x && e.z == z && (e.y <= y - 0.4 && e.y >= y - 0.9));
        if (actionNotPossible) {
            System.out.println("Action not possible you are near a building");
        }
        return actionNotPossible;
    }

    public boolean controlBounds(double width, double depth) {
        boolean actionPossible = worldElements.stream().anyMatch((Elements e) -> e.x == width && e.z == depth && universHeighList.contains(e.y));
        if (!actionPossible) {
            System.out.println("Action not possible you are out of bounds");
        }
        return actionPossible;
    }

    public void speedUp() {
        this.speed += 0.25;
    }

    public void sppedDown() {
        this.speed -= 0.25;
    }

    public void leftMove() {
        double position[] = verifyPosition();
        if(!controlBounds(position[0], position[2] + 0.5)){
            throw new IllegalArgumentException("The player is already at the left-edge of the world");
        }
        if(controlBuildingPosition(position[0], position[1], position[2] + 0.5) || controlBuildingPosition(position[0], position[1], position[2] + 1)) {
            throw new IllegalArgumentException("The player is near a building");
        }
        List<Elements> sortedElements = worldElements.stream().map((Elements e) -> {
            if(e.type == Type.PLAYER) {
                e.z += speed;
            }
            return e;
        }).collect(Collectors.toList());
        worldElements = sortedElements;
    }

    public void rightMove() {
        double position[] = verifyPosition();
        if(!controlBounds(position[0], position[2] - 0.5)){
            throw new IllegalArgumentException("The player is already at the right-edge of the world");
        }
        if(controlBuildingPosition(position[0], position[1], position[2] - 0.5) || controlBuildingPosition(position[0], position[1], position[2] - 1)) {
            throw new IllegalArgumentException("The player is near a building");
        }
        List<Elements> sortedElements = worldElements.stream().map((Elements e) -> {
            if(e.type == Type.PLAYER) {
                e.z -= speed;
            }
            return e;
        }).collect(Collectors.toList());
        worldElements = sortedElements;
    }

    public void forwardMove() {
        double position[] = verifyPosition();
        if(!controlBounds(position[0] + 0.5, position[2])){
            throw new IllegalArgumentException("The player is already at the for-edge of the world");
        }
        if(controlBuildingPosition(position[0] + 0.5, position[1], position[2])) {
            throw new IllegalArgumentException("The player is near a building");
        }
        List<Elements> sortedElements = worldElements.stream().map((Elements e) -> {
            if(e.type == Type.PLAYER) {
                e.x += speed;
            }
            return e;
        }).collect(Collectors.toList());
        worldElements = sortedElements;
    }

    public void backwardMove() {
        double position[] = verifyPosition();
        if(!controlBounds(position[0] - 0.5, position[2])){
            throw new IllegalArgumentException("The player is already at the back-edge of the world");
        }
        if(controlBuildingPosition(position[0] - 0.5, position[1], position[2])) {
            throw new IllegalArgumentException("The player is near a building");
        }
        List<Elements> sortedElements = worldElements.stream().map((Elements e) -> {
            if(e.type == Type.PLAYER) {
                e.x -= speed;
            }
            return e;
        }).collect(Collectors.toList());
        worldElements = sortedElements;
    }

    public void upMove() {
        double position[] = verifyPosition();
        if(position[1] > 8) {
            throw new IllegalArgumentException("The player is already at the top-edge of the world");
        }
        if(controlBuildingPosition(position[0], position[1] + 0.5, position[2])) {
            throw new IllegalArgumentException("The player is near a building");
        }
        List<Elements> sortedElements = worldElements.stream().map((Elements e) -> {
            if(e.type == Type.PLAYER) {
                e.y += speed;
            }
            return e;
        }).collect(Collectors.toList());
        worldElements = sortedElements;
    }

    public void downMove() {
        double position[] = verifyPosition();
        if(!controlBounds(position[0], position[2])){
            throw new IllegalArgumentException("The player is already at the left-edge of the world");
        }
        if(controlBuildingPositionDown(position[0], position[1] - 0.5, position[2])) {
            throw new IllegalArgumentException("The player is near a building");
        }
        List<Elements> sortedElements = worldElements.stream().map((Elements e) -> {
            if(e.type == Type.PLAYER) {
                e.y -= speed;
            }
            return e;
        }).collect(Collectors.toList());
        worldElements = sortedElements;
    }

    public double[] verifyPosition() {
        int player = this.searchPlayerIndex();
        double[] position = {worldElements.get(player).x, worldElements.get(player).y, worldElements.get(player).z};
        System.out.println("The player is on the position: x: " + position[0] + ", y: " +  position[1] + ", z: " + position[2]);
        return position;
    }

    int searchPlayerIndex() {
        int i = 0;
        for(Elements w: worldElements) {
            if(w.type == Type.PLAYER) {
               for(int j = 0; j <= i; j++) {
                    if(worldElements.get(i) != w) {
                        i++;
                    }
               }
            }
        }
        return i;
    }

    public boolean checkPosition1(double x, double y, double z) {
        for (Elements e : worldElements) {
            if (e.x == x && e.y == y && e.z == z) {
                System.out.println("The place is already occupied");
                return false;
            }
        }
        return true;
    }

    void isOutOfBounds(double x, double y, double z) {
        if(x < -6 || x > worldWidth || y < baseHeight || y > 10 || z < -6 || z > worldDepth) {
            throw new IllegalArgumentException("your block ist out of worldsbounds");
        }
    }

    public void drawBlock(double x, double y, double z, int red, int green, int blue) {    
        isOutOfBounds(x, y, z);
        worldElements.stream().forEach((Elements e) -> {
            if(e.x == x && e.y == y && e.z == z) {
                System.out.println("The place is already occupied");
                if(e.type == Type.PLAYER) System.out.println("The player is already on this place");
                throw new IllegalArgumentException("The place is already occupied");
            }
        });
        worldElements.add(new Elements(x, y, z, red, green, blue, Type.BLOCK, LocalTime.now()));
    } 

    public List<Elements> resetWorld(List<Elements> list) {
        List<Elements> sortedElements = list.stream()
            .sorted(Comparator
                .comparingDouble(Elements::getX)
                .thenComparingDouble(Elements::getY)
                .thenComparingDouble(Elements::getZ))
            .collect(Collectors.toList());
        // Ausgabe
        return sortedElements;
    }
    
    public List<Elements> finalWorld(List<Elements> list) {
        List<Elements> sortedElements = resetWorld(list);
        List<Elements> finalElements = new ArrayList<>();
        for(int i = sortedElements.size() - 1; i >= 0; i--) {
            finalElements.add(sortedElements.get(i));
        }
        return finalElements;
    }

    public void drawWorld() {
        clint.reset();
        this.worldElements = finalWorld(worldElements);
        worldElements.forEach((Elements e) -> {
            if(e.type == Type.BLOCK && e.x + worldDistance >= 4 && e.z + worldDistance >= 2) {
                clint.draw3D(0, angle, worldDistance, e.x, e.y, e.z, e.red, e.green, e.blue);
                if(e.layer != null) {
                    clint.draw2D(angle, worldDistance, e.layer.x, e.layer.y, e.layer.z, e.layer.red + 30, e.layer.green, e.layer.blue);
                } 
            } else
            if(e.type == Type.PLAYER || e.type == Type.FRIEND) {
                clint.draw3D(1, angle, worldDistance, e.x, e.y, e.z, e.red, e.green, e.blue);
            }    
        });
    }

    public void returnInThePast(int time) {
        LocalTime goTime = LocalTime.now().minusMinutes(time);
        clint.reset();
        List<Elements> combinedElements = new ArrayList<>();
        destroyedElements.forEach(e -> combinedElements.add(e));
        worldElements.forEach(e -> combinedElements.add(e));
        List<Elements> finalCombinedElements = combinedElements;
        finalCombinedElements = finalWorld(finalCombinedElements);
        finalCombinedElements.stream()
                    .filter(e -> e.creationTime.isBefore(goTime))
                    .forEach(e -> {
                        if(e.type == Type.BLOCK) {
                            clint.draw3D(0, angle, worldDistance, e.x, e.y, e.z, e.red, e.green, e.blue);
                            if(e.layer != null) clint.draw2D(angle, worldDistance, e.layer.x, e.layer.y, e.layer.z, e.layer.red + 30, e.layer.green, e.layer.blue);
                        } else 
                        if(e.type == Type.PLAYER) {
                            clint.draw3D(1, angle, worldDistance, e.x, e.y, e.z, e.red, e.green, e.blue);
                        }    
                    });
    }

    public void realTime() {
        drawWorld();
    }

    public void verifyPlaceForHouse(double width, double depth) {
       boolean cannotBuild = worldElements.stream().anyMatch(e -> e.x <= width + 2 && e.x >= width + 2 && e.z <= depth + 2 && e.z >= depth - 2 && e.y > baseHeight && e.y <= baseHeight + 4);
       if(cannotBuild) throw new IllegalArgumentException("You cannot build here. There is already something");
    }

    public void verifyPlaceForSkyscraper(double width, double depth) {
        boolean cannotBuild = worldElements.stream().anyMatch(e -> e.x <= width + 3 && e.x >= width + 3 && e.z <= depth + 2 && e.z >= depth - 2 && e.y > baseHeight && e.y <= baseHeight + 13);
       if(cannotBuild) throw new IllegalArgumentException("You cannot build here. There is already something");
    }

    public void verifyPlaceForPyramid(double width, double depth) {
        boolean cannotBuild = worldElements.stream().anyMatch(e -> e.x <= width + 3.5 && e.x >= width + 3.5 && e.z <= depth + 3.5 && e.z >= depth - 3.5 && e.y > baseHeight && e.y <= baseHeight + 4);
       if(cannotBuild) throw new IllegalArgumentException("You cannot build here. There is already something");
    }

    public void buildHouse(double width, double depth, int red, int green, int blue) {
        verifyPlaceForHouse(width, depth);
        // right side
        for (double i = width - 2; i < width + 2; i += 0.5) {
            for (double j = baseHeight; j < baseHeight + 4; j += 0.5) {
                    worldElements.add(new Elements(i, j, depth - 2, red, green, blue, Type.BLOCK, LocalTime.now()).newLayer(i, j + 0.5, depth - 2, red, green, blue));
            }
        }
        // left side
        for (double i = width - 2; i < width + 2; i += 0.5) {
            for (double j = baseHeight + 0.5; j < baseHeight + 4; j += 0.5) {
                    worldElements.add(new Elements(i, j, depth + 2, red, green, blue, Type.BLOCK, LocalTime.now()).newLayer(i, j + 0.5, depth + 2, red, green, blue));
            }
        }
        // front side
        for (double i = depth - 2; i < depth; i += 0.5) {
            for (double j = baseHeight; j < baseHeight + 4; j += 0.5) {
                    worldElements.add(new Elements(width - 2, j, i, red, green, blue, Type.BLOCK, LocalTime.now()).newLayer(width - 2, j + 0.5, i, red, green, blue));
            }
        }
        // back side
        for (double i = depth - 2; i < depth + 2; i += 0.5) {
            for (double j = baseHeight; j < baseHeight + 4; j += 0.5) {
                    worldElements.add(new Elements(width + 2, j, i, red, green, blue, Type.BLOCK, LocalTime.now()).newLayer(width + 2, j + 0.5, i, red, green, blue));
            }
        }
        // roof
        for (double i = width - 2.5; i < width + 2.5; i += 0.5) {
            for (double j = depth - 2.5; j < depth + 2.5; j += 0.5) {
                    worldElements.add(new Elements(i, baseHeight + 4, j, red, green, blue, Type.BLOCK, LocalTime.now()).newLayer(i, baseHeight + 4.5, j, red, green, blue));
            }
        }
        drawWorld();
    }

    public void buildSkyscraper(double width, double depth, int red, int green, int blue) {
        verifyPlaceForSkyscraper(width, depth);
        // right side
        for (double i = width - 3; i < width + 3; i += 0.5) {
            for (double j = baseHeight; j < baseHeight + 13; j += 0.5) {
                    worldElements.add(new Elements(i, j + 0.5, depth - 2, red, green, blue, Type.BLOCK, LocalTime.now()).newLayer(i, j + 1, depth - 2, red, green, blue));
            }
        }
        // left side
        for (double i = width - 3; i < width + 3; i += 0.5) {
            for (double j = baseHeight; j < baseHeight + 13; j += 0.5) {
                    worldElements.add(new Elements(i, j + 0.5, depth + 2, red, green, blue, Type.BLOCK, LocalTime.now()).newLayer(i, j + 1, depth + 2, red, green, blue));
            }
        }
        // front side
        for (double i = depth - 3; i < depth + 3; i += 0.5) {
            for (double j = baseHeight; j < baseHeight + 13; j += 0.5) {
                    worldElements.add(new Elements(width - 2, j + 0.5, i, red, green, blue, Type.BLOCK, LocalTime.now()).newLayer(width - 2, j + 1, i, red, green, blue));
            }
        }
        // back side
        for (double i = depth - 3; i < depth + 3; i += 0.5) {
            for (double j = baseHeight; j < baseHeight + 13; j += 0.5) {
                    worldElements.add(new Elements(width + 2, j + 0.5, i, red, green, blue, Type.BLOCK, LocalTime.now()).newLayer(width + 2, j + 1, i, red, green, blue));
            }
        }
        // roof
        for (double i = width - 3.5; i < width + 3.5; i += 0.5) {
            for (double j = depth - 2.5; j < depth + 2.5; j += 0.5) {
                    worldElements.add(new Elements(i, baseHeight + 0.5 + 13, j, red, green, blue, Type.BLOCK, LocalTime.now()).newLayer(i, baseHeight + 14, j, red, green, blue));
            }
        }
        drawWorld();
    }

    public void buildPyramid(double width, double depth, int red, int green, int blue) {
        verifyPlaceForPyramid(width, depth);
        
        for(double i = 3.5; i > 0; i -= 0.5) {
            for(double j = width - i; j < width + i; j += 0.5) {
                for(double k = depth - i; k < depth + i; k += 0.5) {
                    worldElements.add(new Elements(j, baseHeight + 4 - i, k, red, green, blue, Type.BLOCK, LocalTime.now()).newLayer(j, baseHeight + 4 - i + 0.5, k, red, green, blue));
                }
            }
        }
        drawWorld();
    }

    public void verifyPlaceForBridgeForward(double width, double height, double depth) {
        boolean cannotBuild = worldElements.stream().anyMatch(e -> e.z <= width + 2 && e.z >= width && e.x <= depth + 6 && e.x >= depth && e.y > height && e.y <= height + 2);
       if(cannotBuild) throw new IllegalArgumentException("You cannot build here. There is already something");
    }

    public void verifyPlaceForBridgeLeft(double width, double height, double depth) {
        boolean cannotBuild = worldElements.stream().anyMatch(e -> e.x <= width + 2 && e.x >= width && e.z <= depth + 6 && e.z >= depth && e.y > height && e.y <= height + 2);
       if(cannotBuild) throw new IllegalArgumentException("You cannot build here. There is already something");
    }

    public void buildBridgeForward(double width, double height, double depth, int red, int green, int blue) {
        verifyPlaceForBridgeForward(width, height, depth);
        //front side
        for(double t = 0; t < 2; t += 0.5) {
            for(double j = width + 2; j > width; j-= 0.5) {
                    worldElements.add(new Elements( depth + t, height + t, j, 0, 0, 0, Type.BLOCK, LocalTime.now()).newLayer(depth + t, height + t + 0.5 , j, red, green, blue));
            }
        }
        //up side
        for(double t = 0; t < 2; t += 0.5) {
            for(double j = width + 2; j > width; j-= 0.5) {
                for(double i = depth + 2 ; i >= depth + 2; i-= 0.5) {
                    worldElements.add(new Elements( i + t, height + 1.5, j, 0, 0, 0, Type.BLOCK, LocalTime.now()).newLayer(i + t, height + 2, j, red, green, blue));
                }  
            }
        }
        //back side
        for(double t = 0; t < 2; t += 0.5) {
            for(double j = width + 2; j > width; j-= 0.5) {
                for(double i = depth + 5.5 ; i <= depth + 5.5; i+= 0.5) {
                    worldElements.add(new Elements( i - t, height + t, j, 0, 0, 0, Type.BLOCK, LocalTime.now()).newLayer(i - t, height + t + 0.5, j, red, green, blue));
                } 
            }
        }
        drawWorld();
    }

    public void buildBridgeLeft(double width, double height, double depth, int red, int green, int blue) {
        verifyPlaceForBridgeLeft(width, height, depth);
        // left side
        for(double t = 0; t < 2; t += 0.5) {
            for(double i = width + 2; i > width; i-= 0.5) {
                for(double j = depth; j >= depth; j-= 0.5) {
                    worldElements.add(new Elements( i, height + t, j + t, 0, 0, 0, Type.BLOCK, LocalTime.now()).newLayer(i, height + t + 0.5, j + t, red, green, blue));
                }  
            }
        }
        // top side
        for(double t = 0; t < 2; t += 0.5) {
            for(double i = width + 2; i > width; i-= 0.5) {
                for(double j = depth + 2; j >= depth + 2; j-= 0.5) {
                    worldElements.add(new Elements( i, height + 1.5, j + t, 0, 0, 0, Type.BLOCK, LocalTime.now()).newLayer(i, height + 2, j + t, red, green, blue)); 
                }
            }
        }
        // right side
        for(double t = 0; t < 2; t += 0.5) {
            for(double i = width + 2 ; i > width; i-= 0.5) {
                for(double j = depth + 5.5; j >= depth + 5.5; j-= 0.5) {
                    worldElements.add(new Elements( i, height + t, j - t, 0, 0, 0, Type.BLOCK, LocalTime.now()).newLayer(i, height + t + 0.5, j - t, red, green, blue));
                }  
            }
        }
        drawWorld();
    }
    
    public void resetGame() {
        this.worldElements.clear();
        clint.reset();
        started = player = false;
        go();
    }

    void destructForward() {
        double[] playerPosition = verifyPosition();
        worldElements.stream().filter(e -> e.x > playerPosition[0] && e.x <= playerPosition[0] + 2 &&  e.y >= playerPosition[1] + 0.25 && e.y <= playerPosition[1] + 1.5 && e.z >= playerPosition[2] - 1 && e.z <= playerPosition[2] + 1).forEach(e -> destroyedElements.add(e));
        worldElements.removeIf(e -> e.x > playerPosition[0] && e.x <= playerPosition[0] + 2 &&  e.y >= playerPosition[1] + 0.25 && e.y <= playerPosition[1] + 1.5 && e.z >= playerPosition[2] - 1 && e.z <= playerPosition[2] + 1);
        drawWorld();
    }

    void destructBackward() {
        double[] playerPosition = verifyPosition();
        worldElements.stream().filter(e -> e.x >= playerPosition[0] - 2 && e.x < playerPosition[0] &&  e.y >= playerPosition[1] + 0.25 && e.y <= playerPosition[1] + 1.5 && e.z >= playerPosition[2] - 1 && e.z <= playerPosition[2] + 1).forEach(e -> destroyedElements.add(e));
        worldElements.removeIf(e -> e.x >= playerPosition[0] - 2 && e.x < playerPosition[0] &&  e.y >= playerPosition[1] + 0.25 && e.y <= playerPosition[1] + 1.5 && e.z >= playerPosition[2] - 1 && e.z <= playerPosition[2] + 1);
        drawWorld();
    }

    void destructRight() {
        double[] playerPosition = verifyPosition();
        worldElements.stream().filter(e -> e.x >= playerPosition[0] - 1 && e.x <= playerPosition[0] + 1 &&  e.y >= playerPosition[1] + 0.25 && e.y <= playerPosition[1] + 1.5 && e.z >= playerPosition[2] - 2 && e.z < playerPosition[2]).forEach(e -> destroyedElements.add(e));
        worldElements.removeIf(e -> e.x >= playerPosition[0] - 1 && e.x <= playerPosition[0] + 1 &&  e.y >= playerPosition[1] + 0.25 && e.y <= playerPosition[1] + 1.5 && e.z >= playerPosition[2] - 2 && e.z < playerPosition[2]);
        drawWorld();
    }

    void destructLeft() {
        double[] playerPosition = verifyPosition();
        worldElements.stream().filter(e -> e.x >= playerPosition[0] - 1 && e.x <= playerPosition[0] + 1 &&  e.y >= playerPosition[1] + 0.25 && e.y <= playerPosition[1] + 1.5 && e.z > playerPosition[2] && e.z <= playerPosition[2] + 2).forEach(e -> destroyedElements.add(e));
        worldElements.removeIf(e -> e.x >= playerPosition[0] - 1 && e.x <= playerPosition[0] + 1 &&  e.y >= playerPosition[1] + 0.25 && e.y <= playerPosition[1] + 1.5 && e.z > playerPosition[2] && e.z <= playerPosition[2] + 2);
        drawWorld();
    }

    void megaDestruction() {
        destructLeft();
        destructRight();
        destructForward();
        destructBackward();
    }
    
    Mission mission;
    boolean inMission = false;

    public void mission(int missionNumber) {
        assert missionNumber == 1 || missionNumber == 2 : "number == 1 || number == 2";
        if(!inMission) {
            mission = new Mission(missionNumber, this).setName()
                .setMissionGuide()
                .setMission();
            inMission = true;
            System.out.println("Good luck");
        } else {
            inMission = !mission.setSuccessCondition();
            if(inMission) System.out.println("you are still in mission.");
        }
        
    }

    void drawHello(double startX, double startY, double startZ, int red, int green, int blue) throws InterruptedException {
        // H
        drawVerticalLine(startX, startY, startZ, 3, red, green, blue);
        drawVerticalLine(startX + 2, startY, startZ, 3, red, green, blue);
        drawHorizontalLine(startX, startY + 1, startZ, 2, red, green, blue);
    
        // E
        drawVerticalLine(startX + 4, startY, startZ, 3, red, green, blue);
        drawHorizontalLine(startX + 4, startY, startZ, 2, red, green, blue);
        drawHorizontalLine(startX + 4, startY + 1, startZ, 1, red, green, blue);
        drawHorizontalLine(startX + 4, startY + 2.5, startZ, 2, red, green, blue);
    
        // L
        drawVerticalLine(startX + 8, startY, startZ, 3, red, green, blue);
        drawHorizontalLine(startX + 8, startY, startZ, 2, red, green, blue);
    
        // L
        drawVerticalLine(startX + 12, startY, startZ, 3, red, green, blue);
        drawHorizontalLine(startX + 12, startY, startZ, 2, red, green, blue);
    
        // O
        drawVerticalLine(startX + 16, startY, startZ, 3, red, green, blue);
        drawVerticalLine(startX + 18, startY, startZ, 3, red, green, blue);
        drawHorizontalLine(startX + 16, startY, startZ, 2, red, green, blue);
        drawHorizontalLine(startX + 16, startY + 2.5, startZ, 2, red, green, blue);
    }

    void drawMine(double startX, double startY, double startZ, int red, int green, int blue) throws InterruptedException {
        // M
        drawVerticalLine(startX, startY, startZ, 3, red, green, blue);
        drawDiagonalLine(startX, startY + 2, startZ, 1, red, green, blue);
        drawDiagonalLine(startX + 1, startY + 2, startZ, 1, red, green, blue);
        drawVerticalLine(startX + 2, startY, startZ, 3, red, green, blue);
    
        // I
        drawVerticalLine(startX + 4, startY, startZ, 3, red, green, blue);
    
        // N
        drawVerticalLine(startX + 6, startY, startZ, 3, red, green, blue);
        drawReverseDiagonalLine(startX + 8, startY, startZ, 3, red, green, blue);
        drawVerticalLine(startX + 8, startY, startZ, 3, red, green, blue);
    
        // E
        drawVerticalLine(startX + 10, startY, startZ, 3, red, green, blue);
        drawHorizontalLine(startX + 10, startY, startZ, 2, red, green, blue);
        drawHorizontalLine(startX + 10, startY + 1, startZ, 1, red, green, blue);
        drawHorizontalLine(startX + 10, startY + 2.5, startZ, 2, red, green, blue);
    }

    void drawCraft(double startX, double startY, double startZ, int red, int green, int blue) throws InterruptedException {
        // C
        drawVerticalLine(startX + 13, startY, startZ, 3, red, green, blue);
        drawHorizontalLine(startX + 13, startY, startZ, 2, red, green, blue);
        drawHorizontalLine(startX + 13, startY + 2.5, startZ, 2, red, green, blue);
    
        // R
        drawVerticalLine(startX + 16, startY, startZ, 3, red, green, blue);
        drawVerticalLine(startX + 17.5, startY + 1.5, startZ, 1, red, green, blue);
        drawReverseDiagonalLine(startX + 18, startY, startZ, 1, red, green, blue);
        drawHorizontalLine(startX + 16, startY + 2.5, startZ, 2, red, green, blue);
        drawHorizontalLine(startX + 16, startY + 1, startZ, 2, red, green, blue);
        //drawDiagonalLine(startX + 16, startY + 1, startZ, 2, red, green, blue);
    
        // A
        drawVerticalLine(startX + 19, startY, startZ, 3, red, green, blue);
        drawReverseDiagonalLine(startX + 22, startY, startZ, 3, red, green, blue);
        drawHorizontalLine(startX + 19, startY + 1, startZ, 2, red, green, blue);
    
        // F
        drawVerticalLine(startX + 23, startY, startZ, 3, red, green, blue);
        drawHorizontalLine(startX + 23, startY + 2.5, startZ, 2, red, green, blue);
        drawHorizontalLine(startX + 23, startY + 1, startZ, 1, red, green, blue);
    
        // T
        drawHorizontalLine(startX + 26, startY + 2.5, startZ, 3, red, green, blue);
        drawVerticalLine(startX + 27, startY, startZ, 3, red, green, blue);
    }

    void drawHelloInMinecraft(double startX, double startY, double startZ, int red, int green, int blue) throws InterruptedException {
        for(double i = -12; i < 20; i += 0.5) {
            for(double j = -12; j < 20; j += 0.5) {
                clint.draw3D(0, 3.4, 10, i, j, 1, (200 - (int)(5*i)), 200, 200 + (int)(5*j));
            }
        }

        drawHello(startX, startY, startZ, red, green, blue);
        // Move to next line
        startY -= 4;
        // I
        drawVerticalLine(startX, startY, startZ, 3, red, green, blue);
        // N
        drawVerticalLine(startX + 2, startY, startZ, 3, red, green, blue);
        drawReverseDiagonalLine(startX + 4, startY, startZ, 3, red, green, blue);
        drawVerticalLine(startX + 4, startY, startZ, 3, red, green, blue);    
        // Move to next line
        startY -= 4;
        drawMine(startX, startY, startZ, red, green, blue);
        drawCraft(startX, startY, startZ, red, green, blue);
    }
    
    void drawVerticalLine(double x, double y, double z, int length, int red, int green, int blue) throws InterruptedException {
        for (double i = 0; i < length; i+= 0.5) {
            clint.draw3D(0, 3.4, 10, x, y + i, z, red, green, blue);
        }
        Thread.sleep(300);
    }
    
    void drawHorizontalLine(double x, double y, double z, int length, int red, int green, int blue) throws InterruptedException {
        for (double i = 0; i < length; i+= 0.5) {
            clint.draw3D(0, 3.4, 10, x + i, y, z, red, green, blue);
        }
        Thread.sleep(300);
    }
    
    void drawDiagonalLine(double x, double y, double z, int length, int red, int green, int blue) throws InterruptedException {
        for (double i = 0; i < length; i+= 0.5) {
            clint.draw3D(0, 3.4, 10, x + i, y + i, z, red, green, blue);
        }
        Thread.sleep(300);
    }

    void drawReverseDiagonalLine(double x, double y, double z, int length, int red, int green, int blue) throws InterruptedException {
        for (double i = 0; i < length; i+= 0.5) {
            clint.draw3D(0, 3.4, 10, x - i, y + i, z, red, green, blue);
        }
        Thread.sleep(300);
    }

    void extendsWorld(double newWidth, double newHeight, double newDepth, int red, int green, int blue) {  
        assert newWidth > 0 : "The width must be greater than the world width";
        assert newHeight >= worldHeight : "The height must be greater than the world height";
        assert newDepth > 0 : "The depth must be greater than 0";
        Random rand = new Random();
        universHeighList.add(newHeight);
        for(double i = newWidth + baseWidth; i > baseWidth; i-= 0.5) {          
            for(double j = newDepth; j > -7; j-= 0.5) {
                int color3 = rand.nextInt(60);
                worldElements.add(new Elements(i, newHeight, j, 0, 0, 0, Type.BLOCK, LocalTime.now()).newLayer(i, newHeight + 0.5, j, red, green, blue + color3));
            }       
        }
        baseWidth += newWidth;
        baseDepth += newDepth;
        this.drawWorld();
    }
}

class Elements {
    double x, y, z;
    int red, green, blue;
    Type type;
    WorldLayer layer = null;
    LocalTime creationTime = LocalTime.now();

    Elements (double x, double y, double z, int red, int green, int blue, Type type, LocalTime time) {
        this.x = x;
        this.y = y;
        this.z = z;
        this.red = red;
        this.green = green;
        this.blue = blue;
        this.type = type;
        creationTime = time;
    }

    public Elements newLayer(double layerX, double layerY, double layerZ, int layerR, int layerG, int layerB) {
        this.layer = new WorldLayer(layerX, layerY, layerZ,  layerR,  layerG,  layerB);
        return this;
    }

    public double getX() {
        return x;
    }

    public double getY() {
        return -y;
    }

    public double getZ() {
        return z;
    }
}

// layer
class WorldLayer {
    double x, y, z;
    int red, green, blue;
    
    WorldLayer(double x, double y, double z, int red, int green, int blue) {
        this.x = x;
        this.y = y;
        this.z = z;
        this.red = red;
        this.green = green;
        this.blue = blue;
    }
}
// layer

// minecraft7
record Mission(int number, Minecrafts minecrafts) {
    public Mission {
        if(number < 1) {
            throw new IllegalArgumentException("The number must be greater than 0");
        }
    }
    public static String name;

    Mission setName(){
        switch (number) {
            case 1:
                name = "destroy the Forest";
                break;
            case 2:
                name = "find your lost friend";
                break;
            default:
                break;
        }
        return this;
    }

    Mission setMissionGuide() {
        switch (name) {
            case "destroy the Forest":
                System.out.println("You landed in a dense forest. \n The mission is to destroy each tree of the forest\n to prepare your world to be modernise.\n Complete this Mission!");  
                break;
            case "find your lost friend":
                System.out.println("You and your friend landed in an area.\n He is wanted to explore the world but never came back.\n The mission is to find your lost friend in the forest");
            default:
                break;
        }
        return this;
    }

    Mission setMission() {
        Random rand = new Random();
        double friendsPositionX = 0;
        double friendsPositionZ = 0;
        minecrafts.worldElements.removeIf(e -> (!minecrafts.universHeighList.contains(e.y) && e.type == Type.BLOCK));
        switch (number) {
            case 1: 
                for(double i = 0; i < 30; i+= 2) {
                    int treePlaceX = rand.nextInt((int)(minecrafts.worldWidth - 2)) + 2;
                    int treePlaceZ = rand.nextInt((int)(minecrafts.worldWidth - 2)) + 2;
                    minecrafts.tree(treePlaceX, minecrafts.worldHeight + 0.5, treePlaceZ);
                }
                minecrafts.drawWorld();
                break;
            case 2: 
                for(double i = 0; i < 50; i+= 2) {
                    int treePlaceX = rand.nextInt((int)(minecrafts.worldWidth - 2)) + 2;
                    int treePlaceZ = rand.nextInt((int)(minecrafts.worldWidth - 2)) + 2;
                    minecrafts.tree(treePlaceX, minecrafts.worldHeight + 0.5, treePlaceZ);
                }
                boolean hide = false;
                while (hide == false){
                    double friendPositionX = friendsPositionX = rand.nextInt((int)(minecrafts.worldWidth - 5)) + 5;
                    double friendPositionZ = friendsPositionZ = rand.nextInt((int)(minecrafts.worldWidth - 5)) + 5;
                    hide = !minecrafts.worldElements.stream().anyMatch(e -> (e.x == friendPositionX && e.z == friendPositionZ && e.y == minecrafts.worldHeight + 0.5));
                }
                minecrafts.worldElements.add(new Elements(friendsPositionX, minecrafts.baseHeight, friendsPositionZ, 20, 20, 255, Type.FRIEND, LocalTime.now()));
                minecrafts.drawWorld();
                break;
        
            default:
                break;
        }
        return this;
    }

    boolean setSuccessCondition() {
        boolean succeeded = false;
        switch (number) {
            case 1:
                succeeded = !minecrafts.worldElements.stream().anyMatch(e -> e.type != Type.PLAYER && !minecrafts.universHeighList.contains(e.y));
                if(succeeded) System.out.println("Mission completed. You cleaned the area");
                break;
            case 2:
                double[] playerPos = minecrafts.verifyPosition();
                succeeded = minecrafts.worldElements.stream().filter(e -> e.type == Type.FRIEND).anyMatch(e -> (playerPos[0] == e.x || playerPos[0] == e.x + 0.5 || playerPos[0] == e.x -0.5) && (playerPos[2] == e.z || playerPos[2] == e.z + 0.5 || playerPos[2] == e.z -0.5));
                if(succeeded) System.out.println("Mission completed. You found your friend");
                break;
            default :
                break;
        }
        return succeeded;
    }
}
// minecraft7

class ManageMinecraft {
    Minecrafts minecraft;
    Mission mission;
    boolean inMission = false;
    public ManageMinecraft(Minecrafts minecraft) {
        this.minecraft = minecraft;
    }

    void startGame() throws InterruptedException {
        minecraft.drawHelloInMinecraft(-8.5, 0, 10, 0, 0, 0);
        Thread.sleep(5000);
        minecraft.go();
        minecraft.play(180, 180, 180);
    }

    void plantTree() {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Enter the x position: ");
        double x = scanner.nextDouble();
        System.out.println("Enter the y position: ");
        double y = scanner.nextDouble();
        System.out.println("Enter the z position: ");
        double z = scanner.nextDouble();
        minecraft.tree(x, y, z);
    }

    void extendsWorld() {
        System.out.println("You want to extend the world:");
        Scanner scanner = new Scanner(System.in);
        System.out.println("Choose the dimension of the additional world:");
        System.out.println("Width:");
        double nextWorldWidth = scanner.nextDouble();
        System.out.println("Depth");
        double nextWorldDepth = scanner.nextDouble();
        System.out.println("Heigth");
        double nextWorldHeigth = scanner.nextDouble();
        System.out.println("Choose the world color");
        System.out.println("red");
        int red = scanner.nextInt();
        System.out.println("green");
        int green = scanner.nextInt();
        System.out.println("blue");
        int blue = scanner.nextInt();
        minecraft.extendsWorld(nextWorldWidth, nextWorldHeigth, nextWorldDepth, red, green, blue);
    }

    void zoom() {
        System.out.println("You want to zoom in the world in or out? 1.in//2.out");
        Scanner scanner = new Scanner(System.in);
        int choice = scanner.nextInt();
        if(choice == 1) {
            minecraft.zoom();
        } else {
            minecraft.unzoom();
        }
    }

    void move() {
        Scanner scanner = new Scanner(System.in);
        int choice = 2;
        while (choice > 0 && choice < 7) {
            System.out.println("Choose the direction of the player: 1.left/2.right/3.up/4.down/5.forward/6.backward)");
            if(scanner.hasNextInt()) choice = scanner.nextInt();
            if(choice == 1) {
                minecraft.leftMove();
            } else if(choice == 2) {
                minecraft.rightMove();
            } else if(choice == 3) {
                minecraft.upMove();
            } else if(choice == 4) {
                minecraft.downMove();
            } else if(choice == 5) {
                minecraft.forwardMove();
            } else if(choice == 6){
                minecraft.backwardMove();
            }
            minecraft.drawWorld();
        }
    }

    void rotateWorld() {
        System.out.println("How do yo want to rotate: 1.Left/ 2.Right");
        Scanner scanner = new Scanner(System.in);
        int choice = scanner.nextInt();
        switch (choice) {
            case 1:
                minecraft.rotateLeft();
                break;
            case 2:
                minecraft.rotateRight();
                break;
            default:
                break;
        }
    }

    void manageTime() {
        Scanner scanner = new Scanner(System.in);
        System.out.println("You want to go back in time? 1.yes//2.no");
        int choice = scanner.nextInt();
        if(choice == 1) {
            System.out.println("Enter the time you want to go back in minutes: ");
            int time = scanner.nextInt();
            minecraft.returnInThePast(time);
        } else {
            System.out.println("You want to see the world in real time? 1.yes//2.no");
            int choice2 = scanner.nextInt();
            if(choice2 == 1) minecraft.realTime();
        }
        System.out.println("Time management is done");
    }

    void buildBlock() {
        Scanner scanner = new Scanner(System.in);
        System.out.println("You want to build a Block in the world. Choose the color:(red, green, blue)");
        System.out.println("Red: ");
        int red = scanner.nextInt();
        System.out.println("Green: ");
        int green = scanner.nextInt();
        System.out.println("Blue: ");
        int blue = scanner.nextInt();
        System.out.println("Enter the x position: ");
        double x = scanner.nextDouble();
        System.out.println("Enter the y position: ");
        double y = scanner.nextDouble();
        System.out.println("Enter the z position: ");
        double z = scanner.nextDouble();
        minecraft.drawBlock(x, y, z, red, green, blue);
    }

    void buildSomething() throws Exception{
        double[] position = minecraft.verifyPosition();
        Scanner scanner = new Scanner(System.in);
        System.out.println("You want to build something in the world. Choose the color:(red, green, blue)");
        System.out.println("Red: ");
        int red = scanner.nextInt();
        System.out.println("Green: ");
        int green = scanner.nextInt();
        System.out.println("Blue: ");
        int blue = scanner.nextInt();
        System.out.println("What do you want to build? (1: House, 2: Skyscraper, 3: Pyramid, 4: Bridge Forward, 5: Bridge Left)");
        int choice = scanner.nextInt();
        switch (choice) {
            case 1:
                minecraft.buildHouse(position[0] + 3, position[2] + 3, red, green, blue);
                break;
            case 2:
                minecraft.buildSkyscraper(position[0] + 3, position[2] + 3, red, green, blue);
                break;
            case 3:
                minecraft.buildPyramid(position[0] + 5, position[2] + 3, red, green, blue);
                break;
            case 4:
                minecraft.buildBridgeForward(position[0] - 2, position[1] + 0.4, position[2] + 1, red, green, blue);
                break;
            case 5:
                minecraft.buildBridgeLeft(position[0] - 2, position[1] + 0.4, position[2] + 1, red, green, blue);
                break;
            default:
                break;
        }
    }

    void buildStairs() throws Exception {
        Scanner scanner = new Scanner(System.in);
        int choice = 1;
        System.out.println("Which side do you want to build the stairs? (1.left/2.right/3.backward/4.forward)");
        if(scanner.hasNextInt()) choice = scanner.nextInt();
        while (choice > 0 && choice < 5) {
            switch (choice) {
                case 1: 
                    System.out.println("Choose the lenght of the stair: (1.yes/2.no)");
                    int choice1 = 0;
                    if(scanner.hasNextInt()) choice1 = scanner.nextInt();
                    if(choice1 > 0) minecraft.stairsLeft(choice);
                    break;
                case 2: 
                    System.out.println("Choose the lenght of the stair: (1.yes/2.no)");
                    int choice2 = 0;
                    if(scanner.hasNextInt()) choice2 = scanner.nextInt();
                    if(choice2 > 0) minecraft.stairsRight(choice2);
                    break;
                case 3:                   
                    System.out.println("Choose the lenght of the stair: (1.yes/2.no)");
                    int choice3 = 0;
                    if(scanner.hasNextInt()) choice3 = scanner.nextInt();
                    if(choice3 > 0) minecraft.stairsBackward(choice3);
                    break;
                case 4: 
                    System.out.println("Choose the lenght of the stair: (1.yes/2.no)");
                    int choice4 = 0;
                    if(scanner.hasNextInt()) choice4 = scanner.nextInt();
                    if(choice4 > 0) minecraft.stairsForward(choice4);
                    break;
                default:
                    break;
            }
            System.out.println("Which side do you want to build the next stairs? (1.left/2.right/3.backward/4.forward)");
            if(scanner.hasNextInt()) choice = scanner.nextInt();
        }
    }


    void destructSomething() {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Which side do you want to destruct? (1: Forward, 2: Backward, 3: Right, 4: Left, 5: Mega destruction)");
        int choice = scanner.nextInt();
        switch (choice) {
            case 1:
                minecraft.destructForward();
                break;
            case 2:
                minecraft.destructBackward();
                break;
            case 3:
                minecraft.destructRight();
                break;
            case 4:
                minecraft.destructLeft();
                break;
            case 5:
                minecraft.megaDestruction();
            default:
                break;
        }
    }

    void mission() {
        if(!inMission) {
            Scanner scanner = new Scanner(System.in);
            System.out.println("Choose a mission: (1: Destroy the Forest, 2: Find your lost friend)");
            int missionNumber = scanner.nextInt();
            mission = new Mission(missionNumber, minecraft).setName()
                .setMissionGuide()
                .setMission();
            inMission = true;
            System.out.println("Good luck");
        } else {
            inMission = !mission.setSuccessCondition();
            if(inMission) System.out.println("you are still in mission.");
        }
        
    }
    

}

// Type
enum Type {
    BLOCK,
    PLAYER,
    FRIEND,
}
// Type