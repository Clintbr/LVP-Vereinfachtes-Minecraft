# (Minecraft)Clerk – Java Prototype

Ein vereinfachtes **Minecraft-inspiriertes Spiel** mit eigener 3D-View (Codename *Clint*).  
Entwickelt als Projekt für *Programmieren 2* an der THM von **Clint Bryan Nguena**.

---

## 🚀 Features

- **3D-Welt generieren** mit Blöcken, Wäldern, Bäumen und Strukturen  
- **Spielerbewegung**: laufen, springen, klettern, Treppen benutzen  
- **Interaktion**: Blöcke platzieren, abbauen, Strukturen (Haus, Pyramide, Brücke, Skyscraper) errichten  
- **Simulation**: Jahreszeiten, Wetter, Echtzeit-Uhr  
- **Rotation & Zoom** für verschiedene Blickwinkel  
- **Missionen**:
  1. *Destroy the Forest* 🌲🔥  
  2. *Find your lost friend* 🧑‍🤝‍🧑  
- **Zeitreise**: Zurück in die Vergangenheit springen und die Welt im alten Zustand sehen  
- **Konsolen-Steuerung** über die Klasse `ManageMinecraft` (Interaktivität mit Scanner)  

---

## 🛠️ Technologien

- **JDK 21**  
- **JavaFX/Custom Rendering via Canva API Wrapper**  
- **OOP-Prinzipien**: Interfaces, Enums, Klassenhierarchien  
- Nutzung von **java.util**: `ArrayList`, `List`, `Random`, `LocalTime`, `Stream`, `Collectors` usw.  

---

## 🎮 Beispiele
1. Welt erstellen ```Minecrafts minecraft = new Minecrafts(30, 10, 30);
minecraft.go();

2. Spieler hinzufügen und bewegen: ``minecraft.play(200, 200, 200); // Spielerfarbe
minecraft.leftMove();
minecraft.forwardMove();
minecraft.drawWorld();``

3. Häuser & Strukturen bauen: ``minecraft.buildHouse(4, 4, 10, 0, 50);
minecraft.buildPyramid(20, 2, 20, 10, 50);
minecraft.buildBridgeLeft(-1, -8, 1.5, 10, 10, 10);``

4. Alles zerstören: ``minecraft.megaDestruction();``

5. Mission starten: ``minecraft.mission(1); // Mission 1: Destroy the Forest <br>
minecraft.mission(2); // Mission 2: Find your lost friend``

## 🎯 Lernziele

OOP-Design in Java (Klassen, Interfaces, Enums, Records)

3D-Modellierung & Rendering via API-Anbindung

Event-Handling, Zustandsverwaltung und Simulation

Datenstrukturen (List, Stream, etc.) praxisnah einsetzen

## 👤 Autor

Clint Bryan Nguena
Technische Hochschule Mittelhessen – Projekt Programmieren 2

