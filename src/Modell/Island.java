package Modell;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Random;
import java.util.Set;

/**
 * Die Klasse "Island" repräsentiert eine Insel im Spiel. Sie speichert Informationen über die Position der Insel,
 * ihre eindeutige ID, die Anzahl der mit ihr verbundenen Brücken und die Richtungen, in denen Brücken zu benachbarten
 * Inseln führen können. Diese Klasse ermöglicht die Erstellung und Verwaltung von Inseln im Spiel.
 */
public class Island {

	// X- und Y-Koordinaten der aktuellen Insel
	private int x, y;

	// Eindeutige ID für die Insel
	private int id;

	// Zähler für die Anzahl der Brücken, die mit dieser Insel verbunden sind
	private int bridgeCount;

	// Index für das Verfolgen der Inseln
	private int index = 1;

	// Richtungen, in denen Brücken von dieser Insel aus gebaut werden können
	private boolean north, east, south, west;

	// X- und Y-Koordinaten der Insel (könnte redundant sein)
	private int islandX, islandY;

	// Ein Flag, das angibt, ob die Insel korrekt erstellt wurde
	private boolean isOk = false;

	// Gesamtanzahl der Inseln im Spiel
	private int totalIslands;

	// Liste der Inseln im Spiel
	private ArrayList<Island> listofIslands;

	// Eine HashMap zur Zuordnung von Insel-IDs zu Insel-Objekten
	private HashMap<Integer, Island> islandMap = new HashMap<Integer, Island>();

	// Liste der möglichen Inseln, die erstellt werden können
	private ArrayList<Island> possibleIslands = new ArrayList<Island>();

	// Liste der erstellten Brücken im Spiel
	private ArrayList<CreateBridges> listOfBridges = new ArrayList<CreateBridges>();

	// Ein Random-Objekt zur Generierung von Zufallszahlen
	private static final Random random = new Random();

	// X- und Y-Koordinaten des Zentrums (könnte redundant sein)
	private int centerX, centerY;
	
	


	/**
	 * Dieser Konstruktor erstellt ein Objekt der Klasse Island mit den angegebenen Koordinaten, einer ID,
	 * einer Liste von Inseln, einer Brückenzahl und den Richtungen, in denen Brücken zu benachbarten Inseln führen.
	 *
	 * @param x             Die x-Koordinate der Insel.
	 * @param y             Die y-Koordinate der Insel.
	 * @param id            Die eindeutige ID der Insel.
	 * @param listofIslands Eine Liste von Inseln, zu der diese Insel gehört.
	 * @param bridgeCount   Die Anzahl der Brücken, die mit dieser Insel verbunden sind.
	 * @param north         True, wenn eine Brücke in nördlicher Richtung führt, andernfalls False.
	 * @param east          True, wenn eine Brücke in östlicher Richtung führt, andernfalls False.
	 * @param south         True, wenn eine Brücke in südlicher Richtung führt, andernfalls False.
	 * @param west          True, wenn eine Brücke in westlicher Richtung führt, andernfalls False.
	 */
	public Island(int x, int y, int id, ArrayList<Island> listofIslands, int bridgeCount, boolean north, boolean east,
	              boolean south, boolean west, int centerX, int centerY) {
	    this.x = x;
	    this.y = y;
	    this.id = id;
	    this.bridgeCount = bridgeCount;
	    this.north = north;
	    this.east = east;
	    this.south = south;
	    this.west = west;
	    this.listofIslands = listofIslands;
	    this.centerX = centerX;
	    this.centerY = centerY;

	}
	
	
	/**
	 * Dieser Konstruktor erstellt ein Objekt der Klasse Island mit den angegebenen Koordinaten, einer ID und einer
	 * Anzahl von Brücken.
	 *
	 * @param x           Die x-Koordinate der Insel.
	 * @param y           Die y-Koordinate der Insel.
	 * @param id          Die eindeutige ID der Insel.
	 * @param bridgeCount Die Anzahl der Brücken, die mit dieser Insel verbunden sind.
	 */
	public Island(int x, int y, int id, int bridgeCount) {
	    this.x = x;
	    this.y = y;
	    this.id = id;
	    this.bridgeCount = bridgeCount;
	    
	    
	}

	/**
	 * Diese Methode erstellt die erste Insel im Rätsel. Sie setzt den Index auf 1, initialisiert verschiedene Listen und
	 * Karten, und ruft dann die Methode "initFirstIsland" auf, um die erste Insel zu erstellen. Anschließend werden
	 * weitere Inseln erstellt, bis die Anzahl der erstellten Inseln der Gesamtanzahl entspricht.
	 *
	 * @param totalIslands Die Gesamtanzahl der Inseln, die erstellt werden sollen.
	 */
	public void createFirstIsland(int totalIslands) {
	    // Setze den Index auf 1 und initialisiere Listen und Karten
	    setIndex(1);
	    this.totalIslands = totalIslands;
	    listofIslands.clear();
	    possibleIslands.clear();
	    listOfBridges.clear();
	    islandMap.clear();

	    // Initialisiere die erste Insel
	    initFirstIsland();

	    // Erstelle weitere Inseln, bis die gewünschte Gesamtanzahl erreicht ist
	    createNextIsland();

	    // Überprüfe, ob die Anzahl der erstellten Inseln der Gesamtanzahl entspricht
	    if (listofIslands.size() == totalIslands) {
	        setOk(true);
	    } else {
	        setOk(false);
	    }
	}
	
	/**
	 * Diese Methode initialisiert die erste Insel im Rätsel. Sie wählt zufällige Koordinaten innerhalb des gegebenen
	 * Bereichs aus und erstellt die erste Insel mit diesen Koordinaten. Die Insel wird dann den Listen "listofIslands",
	 * "possibleIslands" und der Karte "islandMap" hinzugefügt.
	 */
	private void initFirstIsland() {
	    // Wähle zufällige Koordinaten für die erste Insel
	    islandX = 1 + random.nextInt(x);
	    islandY = 1 + random.nextInt(y);
	    setIslandX(islandX);
	    setIslandY(islandY);

	    // Erstelle die erste Insel und füge sie den Listen und der Karte hinzu
	    Island firstIsland = new Island(islandX, islandY, 0, listofIslands, 0, isBridgeNorthPossible(),
	            isBridgeEastPossible(), isBridgeSouthPossible(), isBridgeWestPossible(),0,0);
	    listofIslands.add(firstIsland);
	    islandMap.put(0, firstIsland);
	    possibleIslands.add(firstIsland);
	}

	/**
	 * Diese Methode erstellt die nächsten Inseln im Rätsel. Sie verwendet eine Schleife, um Inseln zu erstellen, bis die
	 * gewünschte Anzahl erreicht ist. Sie wählt eine Insel aus der Liste "possibleIslands" aus, überprüft mögliche
	 * Richtungen für Brücken und erstellt die nächste Insel entsprechend. Dies wird wiederholt, bis die Anzahl der
	 * erstellten Inseln der Gesamtanzahl entspricht oder keine weiteren Inseln erstellt werden können.
	 */
	private void createNextIsland() {
	    while (getIndex() < totalIslands) {
	        if (possibleIslands.isEmpty() || listofIslands.size() > totalIslands) {
	            setOk(false);
	            return;
	        }
	        // Wähle eine Insel aus der Liste "possibleIslands" aus
	        Island chooseIsland = randomIsland(possibleIslands, islandMap);
	        // Überprüfe mögliche Richtungen für Brücken und erstelle die nächste Insel
	        checkDirection(chooseIsland);
	    }
	    setOk(true);
	}
	
	
	

	/**
	 * Erstellt eine Insel im Norden der ausgewählten Insel (chosenIsland) und verbindet sie mit einer Brücke.
	 *
	 * @param chosenIsland Die ausgewählte Insel, von der aus die Verbindung erstellt wird.
	 */
	private void createNorthIsland(Island chosenIsland) {
	    // Zufällige Anzahl von Brücken zwischen 1 und 2 erstellen
	    int bridgeCount = 1 + (random.nextInt(2));

	    // Bestimmen der neuen Y-Position für die Insel im Norden
	    int newPosY = possibleValuesNW(chosenIsland, y, chosenIsland.getY(), Directions.NORTH);

	    // Überprüfen, ob der Bau der Brücke nicht möglich ist
	    if (newPosY <= 1 || !checkForAnotherIsland(chosenIsland, newPosY, Directions.NORTH) || !checkDirectedNeighboursWE(chosenIsland, newPosY, Directions.NORTH)) {
	        // Setze die Richtung "Nord" auf false und beende die Methode
	        chosenIsland.setNorth(false);
	        return;
	    } else {
	    	
	        // Die aktuellen Koordinaten der ausgewählten Insel speichern
	        int chosenX = chosenIsland.getX();
	        int chosenY = chosenIsland.getY();

	        // Wenn die Liste der Brücken nicht leer ist
	        if (!listOfBridges.isEmpty()) {
	            // Schleife über die Y-Koordinaten von der aktuellen Insel bis zur neuen Position
	            for (int i = chosenY; i > newPosY - 1; i--) {
	                // Schleife über vorhandene Brücken
	                for (CreateBridges bridge : listOfBridges) {
	                    int firstX = bridge.getFirstIslandX();
	                    int secondX = bridge.getSecondIslandX();
	                    int y = bridge.getFirstIslandY();

	                    // Überprüfen, ob eine Brücke über der aktuellen Insel existiert
	                    if (y == chosenY - 1 && (firstX < chosenX && secondX > chosenX)) {
	                        // Setze die Richtung "Nord" auf false und beende die Methode
	                        chosenIsland.setNorth(false);
	                        return;
	                    }

	                    // Überprüfen, ob eine Brücke über der geplanten Position der neuen Insel existiert
	                    if (y < chosenY - 1 && i == y && (firstX < chosenX - 1 && secondX > chosenX + 1)) {
	                        // Überprüfen, ob die neue Insel mit benachbarten Inseln kollidiert
	                    	if(checkDirectedNeighboursWE(chosenIsland, i, Directions.NORTH)) {
	                    		chosenIsland.setNorth(false);
                                return;
	                    	}
	                    	// Verbinde die aktuelle Insel mit der neuen Insel im Norden
	                        connectNorthIsland(chosenIsland, i, firstX, secondX, y, bridge);
	                        return;
	                    }
	                }
	            }
	        }

	        // Wenn keine vorhandenen Brücken den Bau verhindern, erstelle die neue Insel
	        Island nextIsland = createNextIsland(chosenIsland.getX(), newPosY, bridgeCount);

	        // Füge die Brücke hinzu
	        listOfBridges.add(new CreateBridges(nextIsland.getId(), nextIsland.getX(), nextIsland.getY(), chosenIsland.getId(), chosenIsland.getX(), chosenIsland.getY(), bridgeCount, null));

	        // Aktualisiere Brückenzähler und Richtungen der Inseln
	        chosenIsland.setBridgeCount(chosenIsland.getBridgeCount() + bridgeCount);
	        chosenIsland.setNorth(false);
	        nextIsland.setSouth(false);

	        // Füge die neue Insel zur Liste hinzu
	        listofIslands.add(nextIsland);
	        possibleIslands.add(nextIsland);

	        // Debug-Ausgaben
	        System.out.println("North FirstID: " + nextIsland.getId() + "Second ID: " + chosenIsland.getId());
	        System.out.println(newPosY);
	    }
	    return;
	}


	/**
	 * Verbindet die aktuelle Insel (chosenIsland) mit einer neuen Insel in Richtung Norden.
	 *
	 * @param chosenIsland Die aktuelle Insel, von der aus die Verbindung erstellt wird.
	 * @param newY         Die neue Y-Position der Insel in Richtung Norden.
	 * @param firstX       X-Position der ersten Insel der ursprünglichen Brücke.
	 * @param secondX      X-Position der zweiten Insel der ursprünglichen Brücke.
	 * @param y            Y-Position der ersten Insel der ursprünglichen Brücke.
	 * @param bridge       Die ursprüngliche Brücke, die ersetzt wird.
	 */
	private void connectNorthIsland(Island chosenIsland, int newY, int firstX, int secondX, int y, CreateBridges bridge) {
	    // Erzeuge die neue Insel
	    Island nextIsland = new Island(chosenIsland.getX(), newY, getIndex(), listofIslands, bridgeCount, isBridgeNorthPossible(), false, false, false, 0, 0);

	    // Aktualisiere die Insel-Map und den Index
	    islandMap.put(getIndex(), nextIsland);
	    setIndex(getIndex() + 1);

	    // Setze die Richtung "Nord" auf false
	    chosenIsland.setNorth(false);

	    // Berechne die Brückenzähler
	    chosenIsland.setBridgeCount(chosenIsland.getBridgeCount() + bridgeCount);
	    nextIsland.setBridgeCount(nextIsland.getBridgeCount() + 2 * bridge.getNumberOfBridges());

	    // Erzeuge und füge die neuen Brücken hinzu
	    listOfBridges.add(new CreateBridges(nextIsland.getId(), nextIsland.getX(), nextIsland.getY(),
	            chosenIsland.getId(), chosenIsland.getX(), chosenIsland.getY(), bridgeCount, null));
	    listOfBridges.add(new CreateBridges(bridge.getFirstIslandID(), firstX, y,
	            nextIsland.getId(), nextIsland.getX(), nextIsland.getY(), bridge.getNumberOfBridges(), null));
	    listOfBridges.add(new CreateBridges(nextIsland.getId(), nextIsland.getX(), nextIsland.getY(),
	            bridge.getSecondIslandID(), secondX, y, bridge.getNumberOfBridges(), null));

	    // Entferne die ursprüngliche Brücke aus der Liste
	    listOfBridges.remove(bridge);

	    // Füge die neue Insel zur Liste hinzu
	    listofIslands.add(nextIsland);
	    possibleIslands.add(nextIsland);

	    // Ausgabe von Debug-Informationen
	    System.out.println(chosenIsland.getId() + "id1: " + bridge.getFirstIslandID() + "id2: " + bridge.getSecondIslandID());
	    System.out.println("North New ID: " + nextIsland.getId());
	}
	

	
	/**
	 * Erstellt eine Insel im Süden der ausgewählten Insel (chosenIsland) und verbindet sie mit einer Brücke.
	 *
	 * @param chosenIsland Die ausgewählte Insel, von der aus die Verbindung erstellt wird.
	 */
	private void createSouthIsland(Island chosenIsland) {
	    // Zufällige Anzahl von Brücken zwischen 1 und 2 erstellen
	    int bridgeCount = 1 + (random.nextInt(2));

	    // Bestimmen der neuen Y-Position für die Insel im Süden
	    int newPosY = possibleValuesSE(chosenIsland, y, chosenIsland.getY(), Directions.SOUTH);

	    // Überprüfen, ob der Bau der Brücke nicht möglich ist
	    if (newPosY <= 1 || !checkForAnotherIsland(chosenIsland, newPosY, Directions.SOUTH) || !checkDirectedNeighboursWE(chosenIsland, newPosY, Directions.SOUTH)) {
	        // Setze die Richtung "Süden" auf false und beende die Methode
	        chosenIsland.setSouth(false);
	        return;
	    } else {
	        // Falls die neue Y-Position kleiner ist als die aktuelle Y-Position,
	        // addiere die aktuelle Y-Position zu newPosY
	        if (newPosY < chosenIsland.getY())
	            newPosY += chosenIsland.getY();
	        System.out.println("Neue Y Süden: " + newPosY);

	        // Wenn die Liste der Brücken nicht leer ist
	        if (!listOfBridges.isEmpty()) {
	            int chosenX = chosenIsland.getX();
	            int chosenY = chosenIsland.getY();

	            // Schleife über die Y-Koordinaten von der aktuellen Insel bis zur neuen Position
	            for (int i = chosenIsland.getY(); i < newPosY + 1; i++) {
	                // Schleife über vorhandene Brücken
	                for (CreateBridges bridge : listOfBridges) {
	                    int firstX = bridge.getFirstIslandX();
	                    int secondX = bridge.getSecondIslandX();
	                    int bridgeX = bridge.getFirstIslandY();

	                    // Überprüfen, ob eine Brücke über der aktuellen Insel existiert
	                    if (bridgeX == chosenIsland.getY() + 1 && (firstX < chosenX && secondX > chosenX)) {
	                        // Setze die Richtung "Süden" auf false und beende die Methode
	                        chosenIsland.setSouth(false);
	                        return;
	                    }

	                    // Überprüfen, ob eine Brücke über der geplanten Position der neuen Insel existiert
	                    if (bridgeX > chosenY + 1 && i == bridgeX && (firstX < chosenX - 1 && secondX > chosenX + 1)) {
	                        // Überprüfen, ob die neue Insel mit benachbarten Inseln kollidiert
	                        if (checkDirectedNeighboursWE(chosenIsland, i, Directions.SOUTH)) {
	                            chosenIsland.setSouth(false);
	                            return;
	                        }
	                        System.out.println("Neue Y Süden: " + newPosY + " i: " + i);

	                        // Verbinde die aktuelle Insel mit der neuen Insel im Süden
	                        connectSouthIsland(chosenIsland, i, firstX, secondX, bridgeX, bridge);
	                        return;
	                    }
	                }
	            }
	        }

	        System.out.println("Neue Y Süden: " + newPosY);
	        // Wenn keine vorhandenen Brücken den Bau verhindern, erstelle die neue Insel
	        Island nextIsland = createNextIsland(chosenIsland.getX(), newPosY, bridgeCount);

	        // Setze die Richtung "Norden" und "Süden" auf false
	        nextIsland.setNorth(false);
	        chosenIsland.setSouth(false);

	        // Aktualisiere Brückenzähler der ausgewählten Insel
	        chosenIsland.setBridgeCount(chosenIsland.getBridgeCount() + bridgeCount);

	        // Füge die Brücke hinzu
	        listOfBridges.add(new CreateBridges(chosenIsland.getId(), chosenIsland.getX(), chosenIsland.getY(),
	                nextIsland.getId(), nextIsland.getX(), nextIsland.getY(), bridgeCount, null));

	        // Debug-Ausgaben
	        System.out.println("South FirstID: " + chosenIsland.getId() + "Second ID: " + nextIsland.getId());
	        listofIslands.add(nextIsland);
	        possibleIslands.add(nextIsland);
	    }
	    return;
	}

	
	/**
	 * Verbindet die aktuelle Insel (chosenIsland) mit einer neuen Insel in Richtung Süden.
	 *
	 * @param chosenIsland Die aktuelle Insel, von der aus die Verbindung erstellt wird.
	 * @param newY         Die neue Y-Position der Insel in Richtung Süden.
	 * @param firstX       X-Position der ersten Insel der ursprünglichen Brücke.
	 * @param secondX      X-Position der zweiten Insel der ursprünglichen Brücke.
	 * @param y            Y-Position der ersten Insel der ursprünglichen Brücke.
	 * @param bridge       Die ursprüngliche Brücke, die ersetzt wird.
	 */
	private void connectSouthIsland(Island chosenIsland, int newY, int firstX, int secondX, int bridgeY, CreateBridges bridge) {
	    // Erzeuge die neue Insel
	    Island nextIsland = new Island(chosenIsland.getX(), newY, getIndex(), listofIslands, bridgeCount, false, false, isBridgeSouthPossible(), false, 0, 0);

	    // Aktualisiere die Insel-Map und den Index
	    islandMap.put(getIndex(), nextIsland);
	    setIndex(getIndex() + 1);

	    // Setze die Richtung "Süden" auf false
	    chosenIsland.setSouth(false);

	    chosenIsland.setBridgeCount(chosenIsland.getBridgeCount() + bridgeCount);
	    nextIsland.setBridgeCount(nextIsland.getBridgeCount() + 2 * bridge.getNumberOfBridges());

	    // Erzeuge und füge die neuen Brücken hinzu
	    listOfBridges.add(new CreateBridges(chosenIsland.getId(), chosenIsland.getX(), chosenIsland.getY(),
	            nextIsland.getId(), nextIsland.getX(), nextIsland.getY(), bridgeCount, null));
	    listOfBridges.add(new CreateBridges(bridge.getFirstIslandID(), firstX, bridgeY,
	            nextIsland.getId(), nextIsland.getX(), nextIsland.getY(), bridge.getNumberOfBridges(), null));
	    listOfBridges.add(new CreateBridges(nextIsland.getId(), nextIsland.getX(), nextIsland.getY(),
	            bridge.getSecondIslandID(), secondX, bridgeY, bridge.getNumberOfBridges(), null));

	    // Entferne die ursprüngliche Brücke aus der Liste
	    listOfBridges.remove(bridge);

	    // Füge die neue Insel zur Liste hinzu
	    listofIslands.add(nextIsland);
	    possibleIslands.add(nextIsland);

	    // Ausgabe von Debug-Informationen
	    System.out.println(chosenIsland.getId() + "id1: " + bridge.getFirstIslandID() + "id2: " + bridge.getSecondIslandID());
	    System.out.println("South New ID: " + nextIsland.getId());
	}



	/**
	 * Erstellt eine Insel im Osten der ausgewählten Insel (chosenIsland) und verbindet sie mit einer Brücke.
	 *
	 * @param chosenIsland Die ausgewählte Insel, von der aus die Verbindung erstellt wird.
	 */
	private void createEastIsland(Island chosenIsland) {
	    // Zufällige Anzahl von Brücken zwischen 1 und 2 erstellen
	    int bridgeCount = 1 + (random.nextInt(2));

	    // Bestimmen der neuen X-Position für die Insel im Osten
	    int newPosX = possibleValuesSE(chosenIsland, x, chosenIsland.getX(), Directions.EAST);

	    // Überprüfen, ob der Bau der Brücke nicht möglich ist
	    if (newPosX <= 1 || !checkForAnotherIsland(chosenIsland, newPosX, Directions.EAST) || !checkDirectedNeighboursWE(chosenIsland, newPosX, Directions.EAST)) {
	        // Setze die Richtung "Osten" auf false und beende die Methode
	        chosenIsland.setEast(false);
	        return;
	    } else {
	        // Falls die neue X-Position kleiner ist als die aktuelle X-Position,
	        // addiere die aktuelle X-Position zu newPosX
	        if (newPosX < chosenIsland.getX())
	            newPosX += chosenIsland.getX();

	        // Wenn die Liste der Brücken nicht leer ist
	        if (!listOfBridges.isEmpty()) {
	            int chosenX = chosenIsland.getX();
	            int chosenY = chosenIsland.getY();

	            // Schleife über die X-Koordinaten von der aktuellen Insel bis zur neuen Position
	            for (int i = chosenIsland.getX(); i < newPosX + 1; i++) {
	                // Schleife über vorhandene Brücken
	                for (CreateBridges bridge : listOfBridges) {
	                    int firstY = bridge.getFirstIslandY();
	                    int secondY = bridge.getSecondIslandY();
	                    int bridgeX = bridge.getFirstIslandX();

	                    // Überprüfen, ob eine Brücke über der aktuellen Insel existiert
	                    if (bridgeX == chosenIsland.getY() + 1 && (firstY < chosenY && secondY > chosenY)) {
	                        // Setze die Richtung "Osten" auf false und beende die Methode
	                        chosenIsland.setEast(false);
	                        return;
	                    }

	                    // Überprüfen, ob eine Brücke über der geplanten Position der neuen Insel existiert
	                    if (x > chosenX + 1 && i == bridgeX && (firstY < chosenY - 1 && secondY > chosenY + 1)) {
	                        // Überprüfen, ob die neue Insel mit benachbarten Inseln kollidiert
	                        if (checkDirectedNeighboursWE(chosenIsland, i, Directions.EAST)) {
	                            chosenIsland.setEast(false);
	                            return;
	                        }
	                        for (Island island : listofIslands) {
	                            int islandX = island.getX();
	                            int islandY = island.getY();
	                            if (islandY == chosenY && (islandX == i + 1 || islandX == i || islandY == chosenY - 1 || islandY == chosenY + 1 || islandX == i - 1)) {
	                                chosenIsland.setEast(false);
	                                return;
	                            }
	                        }
	                        // Verbinde die aktuelle Insel mit der neuen Insel im Osten
	                        connectEastIsland(chosenIsland, i, firstY, secondY, bridgeX, bridge);
	                        return;
	                    }
	                }
	            }
	        }

	        // Wenn keine vorhandenen Brücken den Bau verhindern, erstelle die neue Insel
	        Island nextIsland = createNextIsland(newPosX, chosenIsland.getY(), bridgeCount);

	        // Setze die Richtung "Osten" und "Westen" auf false
	        chosenIsland.setEast(false);
	        nextIsland.setWest(false);

	        // Aktualisiere Brückenzähler der ausgewählten Insel
	        chosenIsland.setBridgeCount(chosenIsland.getBridgeCount() + bridgeCount);

	        // Füge die Brücke hinzu
	        listOfBridges.add(new CreateBridges(chosenIsland.getId(), chosenIsland.getX(), chosenIsland.getY(),
	                nextIsland.getId(), nextIsland.getX(), nextIsland.getY(),
	                bridgeCount, null));

	        // Debug-Ausgaben
	        System.out.println("East FirstID: " + chosenIsland.getId() + "Second ID: " + nextIsland.getId());

	        // Füge die neue Insel zur Liste hinzu
	        listofIslands.add(nextIsland);
	        possibleIslands.add(nextIsland);
	    }
	    return;
	}


	
	/**
	 * Verbindet die ausgewählte Insel (chosenIsland) im Osten mit einer neuen Insel durch eine Brücke.
	 *
	 * @param chosenIsland Die ausgewählte Insel, von der aus die Verbindung erstellt wird.
	 * @param newX         Die X-Koordinate der neuen Insel im Osten.
	 * @param firstY       Die Y-Koordinate des ersten Inselendes der Brücke.
	 * @param secondY      Die Y-Koordinate des zweiten Inselendes der Brücke.
	 * @param bridgeX      Die X-Koordinate des Brückenmittelpunkts.
	 * @param bridge       Die Brücke, die verwendet wird, um die Verbindung herzustellen.
	 */
	private void connectEastIsland(Island chosenIsland, int newX, int firstY, int secondY, int bridgeX, CreateBridges bridge) {
	    // Erstelle die neue Insel im Osten
	    Island nextIsland = new Island(newX, chosenIsland.getY(), getIndex(), listofIslands, bridgeCount, false, isBridgeEastPossible(),
	            false, false, 0, 0);

	    // Füge die neue Insel zur Karte hinzu
	    islandMap.put(getIndex(), nextIsland);
	    setIndex(getIndex() + 1);

	    // Setze die Richtung "Osten" auf false
	    chosenIsland.setEast(false);

	    // Aktualisiere die Brückenzähler für die Inseln
	    chosenIsland.setBridgeCount(chosenIsland.getBridgeCount() + bridgeCount);
	    nextIsland.setBridgeCount(nextIsland.getBridgeCount() + 2 * bridge.getNumberOfBridges());

	    // Füge die Brücke zur Liste der Brücken hinzu
	    listOfBridges.add(new CreateBridges(chosenIsland.getId(), chosenIsland.getX(), chosenIsland.getY(),
	            nextIsland.getId(), nextIsland.getX(), nextIsland.getY(), bridgeCount, null));
	    listOfBridges.add(new CreateBridges(bridge.getFirstIslandID(), bridgeX, firstY,
	            nextIsland.getId(), nextIsland.getX(), nextIsland.getY(), bridge.getNumberOfBridges(), null));
	    listOfBridges.add(new CreateBridges(nextIsland.getId(), nextIsland.getX(), nextIsland.getY(),
	            bridge.getSecondIslandID(), bridgeX, secondY, bridge.getNumberOfBridges(), null));

	    // Entferne die ursprüngliche Brücke aus der Liste der Brücken
	    listOfBridges.remove(bridge);

	    // Füge die neue Insel zur Liste der Inseln hinzu
	    listofIslands.add(nextIsland);
	    possibleIslands.add(nextIsland);

	    // Debug-Ausgaben
	    System.out.println(chosenIsland.getId() + "id1: " + bridge.getFirstIslandID() + "id2: " + bridge.getSecondIslandID());
	    System.out.println("East New ID: " + nextIsland.getId());
	}

		
	
	/**
	 * Erstellt eine Insel im Westen der ausgewählten Insel (chosenIsland) und verbindet sie durch eine Brücke.
	 *
	 * @param chosenIsland Die ausgewählte Insel, von der aus die Verbindung erstellt wird.
	 */
	private void createWestIsland(Island chosenIsland) {
	    // Zufällige Anzahl von Brücken zwischen 1 und 2 erstellen
	    int bridgeCount = 1 + (random.nextInt(2));

	    // Bestimmen der neuen X-Position für die Insel im Westen
	    int newPosX = possibleValuesNW(chosenIsland, x, chosenIsland.getX(), Directions.WEST);

	    // Überprüfen, ob der Bau der Brücke nicht möglich ist
	    if (newPosX <= 1 || !checkForAnotherIsland(chosenIsland, newPosX, Directions.WEST) ||
	            !checkDirectedNeighboursWE(chosenIsland, newPosX, Directions.WEST)) {
	        // Setze die Richtung "Westen" auf false und beende die Methode
	        chosenIsland.setWest(false);
	        return;
	    } else {
	        // Wenn die Liste der Brücken nicht leer ist
	        if (!listOfBridges.isEmpty()) {
	            int chosenX = chosenIsland.getX();
	            int chosenY = chosenIsland.getY();

	            // Schleife über die X-Koordinaten von der aktuellen Insel bis zur neuen Position
	            for (int i = chosenX; i > newPosX - 1; i--) {
	                // Schleife über vorhandene Brücken
	                for (CreateBridges bridge : listOfBridges) {
	                    int firstY = bridge.getFirstIslandY();
	                    int secondY = bridge.getSecondIslandY();
	                    int bridgeX = bridge.getFirstIslandX();

	                    // Überprüfen, ob eine Brücke unterhalb der aktuellen Insel existiert
	                    if (bridgeX == chosenIsland.getY() - 1 && (firstY < chosenY && secondY > chosenY)) {
	                        // Setze die Richtung "Westen" auf false und beende die Methode
	                        chosenIsland.setWest(false);
	                        return;
	                    }

	                    // Überprüfen, ob eine Brücke unterhalb der geplanten Position der neuen Insel existiert
	                    if (bridgeX < chosenX - 1 && i == bridgeX && (firstY < chosenY - 1 && secondY > chosenY + 1)) {
	                        // Überprüfen, ob die neue Insel mit benachbarten Inseln kollidiert
	                        if (checkDirectedNeighboursWE(chosenIsland, i, Directions.WEST)) {
	                            chosenIsland.setWest(false);
	                            return;
	                        }
	                        // Verbinde die Inseln durch eine Brücke
	                        connectWestIsland(chosenIsland, i, firstY, secondY, bridgeX, bridge);
	                        return;
	                    }
	                }
	            }
	        }
	        // Wenn keine vorhandenen Brücken den Bau verhindern, erstelle die neue Insel im Westen
	        Island nextIsland = createNextIsland(newPosX, chosenIsland.getY(), bridgeCount);

	        // Setze die Richtungen und Brückenzähler für die Inseln
	        chosenIsland.setWest(false);
	        nextIsland.setEast(false);
	        chosenIsland.setBridgeCount(chosenIsland.getBridgeCount() + bridgeCount);

	        // Füge die Brücke zur Liste der Brücken hinzu
	        listOfBridges.add(new CreateBridges(nextIsland.getId(), nextIsland.getX(), nextIsland.getY(),
	                chosenIsland.getId(), chosenIsland.getX(), chosenIsland.getY(),
	                bridgeCount, null));

	        // Debug-Ausgaben
	        System.out.println("West FirstID: " + nextIsland.getId() + "Second ID: " + chosenIsland.getId());

	        // Füge die neue Insel zur Liste hinzu
	        listofIslands.add(nextIsland);
	        possibleIslands.add(nextIsland);
	    }
	    return;
	}

	
	
	/**
	 * Verbindet die ausgewählte Insel (chosenIsland) im Westen mit einer neuen Insel durch eine Brücke.
	 *
	 * @param chosenIsland Die ausgewählte Insel, von der aus die Verbindung erstellt wird.
	 * @param newX Die X-Koordinate der neuen Insel.
	 * @param firstY Die Y-Koordinate des ersten Brückenendpunkts.
	 * @param secondY Die Y-Koordinate des zweiten Brückenendpunkts.
	 * @param bridgeX Die X-Koordinate des Brückenverlaufs.
	 * @param bridge Die Brücke, die ersetzt wird.
	 */
	private void connectWestIsland(Island chosenIsland, int newX, int firstY, int secondY, int bridgeX, CreateBridges bridge) {
	    // Erstelle eine neue Insel im Westen der ausgewählten Insel
	    Island nextIsland = new Island(newX, chosenIsland.getY(), getIndex(), listofIslands, bridgeCount, false, false, false, isBridgeWestPossible(), 0, 0);

	    // Füge die neue Insel zur Inselkarte hinzu und erhöhe den Index
	    islandMap.put(getIndex(), nextIsland);
	    setIndex(getIndex() + 1);

	    // Setze die Richtung "Westen" der ausgewählten Insel auf false
	    chosenIsland.setWest(false);

	    // Aktualisiere die Brückenzähler für die beteiligten Inseln
	    chosenIsland.setBridgeCount(chosenIsland.getBridgeCount() + bridgeCount);
	    nextIsland.setBridgeCount(nextIsland.getBridgeCount() + 2 * bridge.getNumberOfBridges());

	    // Füge die neue Brücke zur Liste der Brücken hinzu
	    listOfBridges.add(new CreateBridges(nextIsland.getId(), nextIsland.getX(), nextIsland.getY(),
	            chosenIsland.getId(), chosenIsland.getX(), chosenIsland.getY(), bridgeCount, null));

	    listOfBridges.add(new CreateBridges(bridge.getFirstIslandID(), bridgeX, firstY,
	            nextIsland.getId(), nextIsland.getX(), nextIsland.getY(), bridge.getNumberOfBridges(), null));

	    listOfBridges.add(new CreateBridges(nextIsland.getId(), nextIsland.getX(), nextIsland.getY(),
	            bridge.getSecondIslandID(), bridgeX, secondY, bridge.getNumberOfBridges(), null));

	    // Entferne die ursprüngliche Brücke aus der Liste der Brücken
	    listOfBridges.remove(bridge);

	    // Füge die neue Insel zur Liste der Inseln hinzu
	    listofIslands.add(nextIsland);
	    possibleIslands.add(nextIsland);

	    // Debug-Ausgaben
	    System.out.println(chosenIsland.getId() + "id1: " + bridge.getFirstIslandID() + "id2: " + bridge.getSecondIslandID());
	    System.out.println("West New ID: " + nextIsland.getId());
	}

	
	
	
	/**
	 * Überprüft, ob auf der angegebenen Position in einer bestimmten Richtung bereits eine Insel existiert.
	 *
	 * @param choosenIsland Die aktuelle Insel, von der aus die Überprüfung durchgeführt wird.
	 * @param newPosition Die neue Position, die überprüft werden soll.
	 * @param direction Die Richtung, in der die Überprüfung erfolgen soll (NORD, OST, SÜD oder WEST).
	 * @return true, wenn die Position frei ist und keine andere Insel vorhanden ist, sonst false.
	 */
	private boolean checkForAnotherIsland(Island choosenIsland, int newPosition, Directions direction) {
	    // Eine Menge, um die bereits besetzten Positionen zu verfolgen
	    Set<Integer> occupiedPositions = new HashSet<Integer>();

	    // Abhängig von der Richtung, in der überprüft wird
	    if (direction == Directions.WEST) {
	        // Durchlaufe alle Inseln in der Liste der Inseln
	        for (Island island : listofIslands) {
	            // Wenn die Insel auf derselben Y-Koordinate wie die aktuelle Insel liegt
	            if (island.getY() == choosenIsland.getY()) {
	                // Füge die X-Koordinate der besetzten Positionen hinzu
	                occupiedPositions.add(island.getX());
	            }
	        }
	        // Überprüfe, ob die gewünschte Position bereits besetzt ist
	        for (int i = choosenIsland.getX() - 1; i > newPosition - 1; i--) {
	            if (occupiedPositions.contains(i)) {
	                return false; // Die Position ist bereits besetzt
	            }
	        }
	    }  
	    
	    // Abhängig von der Richtung, in der überprüft wird
	    else if (direction == Directions.EAST) {
	        // Durchlaufe alle Inseln in der Liste der Inseln
	        for (Island island : listofIslands) {
	            // Wenn die Insel auf derselben Y-Koordinate wie die aktuelle Insel liegt
	            if (island.getY() == choosenIsland.getY()) {
	                // Füge die X-Koordinate der besetzten Positionen hinzu
	            	occupiedPositions .add(island.getX());
	            }
	        }
	        // Überprüfe, ob die gewünschte Position bereits besetzt ist
	        for (int i = choosenIsland.getX() + 1; i < newPosition + 1; i++) {
	            if (occupiedPositions .contains(i)) {
	                return false; // Die Position ist bereits besetzt
	            }
	        }
	    } 
	    
	    // Abhängig von der Richtung, in der überprüft wird
	    else if (direction == Directions.SOUTH) {
	        // Durchlaufe alle Inseln in der Liste der Inseln
	        for (Island island : listofIslands) {
	            // Wenn die Insel auf derselben X-Koordinate wie die aktuelle Insel liegt
	            if (island.getX() == choosenIsland.getX()) {
	                // Füge die Y-Koordinate der besetzten Positionen hinzu
	            	occupiedPositions .add(island.getY());
	            }
	        }
	        // Überprüfe, ob die gewünschte Position bereits besetzt ist
	        for (int i = choosenIsland.getY() + 1; i < newPosition + 1; i++) {
	            if (occupiedPositions .contains(i)) {
	                return false; // Die Position ist bereits besetzt
	            }
	        }
	    } 

	    // Abhängig von der Richtung, in der überprüft wird
	    else if (direction == Directions.NORTH) {
	        // Durchlaufe alle Inseln in der Liste der Inseln
	        for (Island island : listofIslands) {
	            // Wenn die Insel auf derselben X-Koordinate wie die aktuelle Insel liegt
	            if (island.getX() == choosenIsland.getX()) {
	                // Füge die Y-Koordinate der besetzten Positionen hinzu
	            	occupiedPositions .add(island.getY());
	            }
	        }
	        // Überprüfe, ob die gewünschte Position bereits besetzt ist
	        for (int i = choosenIsland.getY() - 1; i > newPosition - 1; i--) {
	            if (occupiedPositions .contains(i)) {
	                return false; // Die Position ist bereits besetzt
	            }
	        }
	    }

	    return true;
	}




	
	/**
	 * Erstellt eine neue Insel mit den angegebenen Koordinaten und Brückenzählerwerten.
	 *
	 * @param x Die X-Koordinate der neuen Insel.
	 * @param y Die Y-Koordinate der neuen Insel.
	 * @param bridgeCount Die Anzahl der Brücken, die mit der neuen Insel verbunden sind.
	 * @return Die neu erstellte Insel.
	 */
	private Island createNextIsland(int x, int y, int bridgeCount) {
	    // Erstelle eine neue Insel mit den angegebenen Koordinaten, Brückenzählern und möglichen Brückenrichtungen
	    Island nextIsland = new Island(x, y, getIndex(), listofIslands, bridgeCount, isBridgeNorthPossible(),
	            isBridgeEastPossible(), isBridgeSouthPossible(), isBridgeWestPossible(), 0, 0);

	    // Füge die neue Insel zur Inselkarte hinzu und erhöhe den Index
	    islandMap.put(getIndex(), nextIsland);
	    setIndex(getIndex() + 1);

	    // Gib die neu erstellte Insel zurück
	    return nextIsland;
	}
	

	
	/**
	 * Überprüft, ob es benachbarte Inseln in der angegebenen Richtung gibt.
	 *
	 * @param chosenIsland Die ausgewählte Insel, von der aus die Überprüfung erfolgt.
	 * @param newPosition Die neue Position in der angegebenen Richtung.
	 * @param direction Die Richtung (EAST, WEST, NORTH oder SOUTH).
	 * @return true, wenn keine benachbarten Inseln vorhanden sind, sonst false.
	 */
	private boolean checkDirectedNeighboursWE(Island chosenIsland, int newPosition, Directions direction) {
	    // Schleife über alle Inseln in der Liste der Inseln
	    for (Island island : listofIslands) {
	        int x = island.getX();
	        int y = island.getY();
	        
	        // Überprüfen, ob die Richtung EAST oder WEST ist
	        if ((direction == Directions.EAST || direction == Directions.WEST) &&
	            (x == newPosition && (y == chosenIsland.getY() || y == chosenIsland.getY() + 1 || y == chosenIsland.getY() - 1) ||
	            (y == chosenIsland.getY() && (x == newPosition + 1 || x == newPosition - 1)))) {
	            return false; // Es gibt eine benachbarte Insel in dieser Richtung
	        }
	        
	        // Überprüfen, ob die Richtung NORTH oder SOUTH ist
	        else if ((direction == Directions.NORTH || direction == Directions.SOUTH) &&
	            (y == newPosition && (x == chosenIsland.getX() || x == chosenIsland.getX() + 1 || x == chosenIsland.getX() - 1) ||
	            (x == chosenIsland.getX() && (y == newPosition + 1 || y == newPosition - 1)))) {
	            return false; // Es gibt eine benachbarte Insel in dieser Richtung
	        }
	    }

	    return true; // Keine benachbarten Inseln in der angegebenen Richtung gefunden
	}

	

		




	private void checkDirection(Island chooseIsland) {
		int direction = 0;
		// true, false, false, false
		if(chooseIsland.isNorth() && !chooseIsland.isEast() && !chooseIsland.isSouth() && !chooseIsland.isWest()) {
			createNorthIsland(chooseIsland);				
			return;
		}
		// false, true, false, false
		if(!chooseIsland.isNorth() && chooseIsland.isEast() && !chooseIsland.isSouth() && !chooseIsland.isWest()) {
			//System.out.println("East");	
			createEastIsland(chooseIsland);
			return;
		}
		// false, false, true, false
		if(!chooseIsland.isNorth() && !chooseIsland.isEast() && chooseIsland.isSouth() && !chooseIsland.isWest()) {
			//System.out.println("South");
			createSouthIsland(chooseIsland);
			return;
		}
		// false, false, false, true
		if(!chooseIsland.isNorth() && !chooseIsland.isEast() && !chooseIsland.isSouth() && chooseIsland.isWest()) {
			//System.out.println("west");
			createWestIsland(chooseIsland);
			return;
		}

		// true, true, false, false
		if(chooseIsland.isNorth() && chooseIsland.isEast() && !chooseIsland.isSouth() && !chooseIsland.isWest()) {
			//System.out.println("North, east");
			direction = 1 + (new Random().nextInt(2));
			if(direction == 1) {
				createEastIsland(chooseIsland);
				return;
			} else {
				createNorthIsland(chooseIsland);
				return;
			}
		}

		// true, false, true, false
		if(chooseIsland.isNorth() && !chooseIsland.isEast() && chooseIsland.isSouth() && !chooseIsland.isWest()) {
			//System.out.println("North, south");
			direction = 1 + (new Random().nextInt(2));
			if(direction == 1) {
				createNorthIsland(chooseIsland);
				return;
			} else {
				createSouthIsland(chooseIsland);
				return;
			}
		}

		// true, false, false, true
		if(chooseIsland.isNorth() && !chooseIsland.isEast() && !chooseIsland.isSouth() && chooseIsland.isWest()) {
			//System.out.println("north, west");
			direction = 1 + (new Random().nextInt(2));
			if(direction == 1) {
				createNorthIsland(chooseIsland);
				return;
			} else {
				createWestIsland(chooseIsland);
				return;
			}
		}



		// false, true, false, true
		if(!chooseIsland.isNorth() && chooseIsland.isEast() && !chooseIsland.isSouth() && chooseIsland.isWest()) {
			//System.out.println("East, West");
			direction = 1+ (new Random().nextInt(2));
			if (direction == 1){
				createEastIsland(chooseIsland);
				return;
			} else {
				createWestIsland(chooseIsland);
				return;
			}
		}

		// false, false, true, true
		if(!chooseIsland.isNorth() && !chooseIsland.isEast() && chooseIsland.isSouth() && chooseIsland.isWest()) {
			//System.out.println("South, West");
			direction = 1+ (new Random().nextInt(2));
			if(direction == 1) {
				createSouthIsland(chooseIsland);
				return;
			} else {
				createWestIsland(chooseIsland);
				return;
			}
		}



		// true, false, true, true
		if(chooseIsland.isNorth() && !chooseIsland.isEast() && chooseIsland.isSouth() && chooseIsland.isWest()) {
			//System.out.println("Norht, South, West");
			direction = 1 + (new Random().nextInt(3));
			if(direction == 1) {
				createNorthIsland(chooseIsland);
				return;
			} else if(direction == 2) {
				createSouthIsland(chooseIsland);
				return;
			} else  {
				createWestIsland(chooseIsland);
				return;
			}

		}

		// true, true, false, true
		if(chooseIsland.isNorth() && chooseIsland.isEast() && !chooseIsland.isSouth() && chooseIsland.isWest()) {
			//System.out.println("Nort, East, West");
			direction = 1+ (new Random().nextInt(3));
			if(direction == 1) {
				createNorthIsland(chooseIsland);
				return;
			} else if(direction == 2) {
				createEastIsland(chooseIsland);
				return;
			} else  {
				createWestIsland(chooseIsland);
				return;
			}

		}

		// false, true, true, true
		if(!chooseIsland.isNorth() && chooseIsland.isEast() && chooseIsland.isSouth() && chooseIsland.isWest()) {
			//System.out.println("East, South, West");
			direction = 1+ (new Random().nextInt(3));
			if(direction == 1) {
				createEastIsland(chooseIsland);
				return;
			} else if(direction == 2) {
				createSouthIsland(chooseIsland);
				return;
			} else  {
				createWestIsland(chooseIsland);
				return;
			}

		}

		// true, true, true, true
		if(chooseIsland.isNorth() && chooseIsland.isEast() && chooseIsland.isSouth() && chooseIsland.isWest()) {
			//System.out.println("North, East, South, West");
			direction = 1+ (new Random().nextInt(4));
			if(direction == 1) {
				createNorthIsland(chooseIsland);
				return;
			} else if(direction == 2) {
				createEastIsland(chooseIsland);
				return;
			} else if(direction == 3) {
				createSouthIsland(chooseIsland);
				return;
			} else  {
				createWestIsland(chooseIsland);
				return;
			}

		}

		// false, false, false, false
		if(!chooseIsland.isNorth() && !chooseIsland.isEast() && !chooseIsland.isSouth() && !chooseIsland.isWest()) {
			for(int i =0; i < possibleIslands.size(); i++) {
				if(possibleIslands.get(i).getId() == chooseIsland.getId()) {
					possibleIslands.remove(i);
					return;
				}
			}
			return;
		}

		// false, true, true, false
		if(!chooseIsland.isNorth() && chooseIsland.isEast() && chooseIsland.isSouth() && !chooseIsland.isWest()) {
			//System.out.println("East, South");
			direction = 1+ (new Random().nextInt(2));
			if(direction == 1) {
				createEastIsland(chooseIsland);
				return;
			} else  {
				createSouthIsland(chooseIsland);
				return;
			}

		}
		// true, true, true, false
		if(chooseIsland.isNorth() && chooseIsland.isEast() && chooseIsland.isSouth() && !chooseIsland.isWest()) {
			//System.out.println("North, East, South");	
			direction = 1+ (new Random().nextInt(3));
			if(direction == 1) {
				createNorthIsland(chooseIsland);
				return;
			} else if(direction == 2) {
				createEastIsland(chooseIsland);
				return;
			} else  {
				createSouthIsland(chooseIsland);
				return;
			}

		}

	}



	
	/**
	 * Sucht eine neue Zufällige Insel aus der Anzahl der möglichen Inseln.
	 * 
	 * @param possibleIslands die Menge der möglichen Inseln
	 * @param islandM Eine HashMap mit allen möglichen Inseln
	 * @return liefert eine zufällige Insel zurück
	 */
	private Island randomIsland(ArrayList<Island>possibleIslands, HashMap<Integer, Island> islandM) {		
		Island item = possibleIslands.get(random.nextInt(possibleIslands.size()));
		return islandM.get(item.getId());

	}


	/**
	 * Berechnet eine zufällige Anzahl von Brücken in südöstlicher Richtung zur nächsten Insel.
	 * SE steht für Südosten.
	 * Wenn die berechnete Anzahl kleiner oder gleich Null ist, gibt die Methode 0 zurück, was bedeutet, dass keine Brücken erstellt werden können.
	 * Andernfalls gibt die Methode eine zufällige Anzahl von Brücken zurück, die zur Verbindung mit der nächsten Insel verwendet werden sollen.
	 * 
	 * @param chosenIsland Die ausgewählte Insel, von der aus die Brücken gelegt werden sollen.
	 * @param xy Die aktuelle Position auf dem Spielfeld.
	 * @param islandXY Die Position der nächsten Insel auf dem Spielfeld.
	 * @return 0, wenn keine Brücken möglich sind, oder eine zufällige Anzahl von Brücken, die gelegt werden können.
	 */
	private int possibleValuesSE(Island chosenIsland, int xy, int islandXY, Directions direction ) {
	    int possibleValues = xy - (islandXY + 1);
	    if (possibleValues <= 0) {
	        return 0;
	    } else {
	        possibleValues = (random.nextInt(possibleValues));
	        if (possibleValues == 0) {
	        	if(direction == Directions.SOUTH && (2+ islandXY) > getY() )
	        		return possibleValues = 0;
	        	if(direction == Directions.EAST && (2+ islandXY) > getX()) 
	        		return possibleValues = 0;
	        	else
	        		return possibleValues = 2 + islandXY;
	            //return possibleValues;
	        }
	    }
	    //possibleValues += 1 + islandXY;
	    //return possibleValues;
	    return 2 + islandXY;
	}





	/**
	 * possibleValuesNW gibt eine Zufallszahl zurück, abhängig von der Richtung zur nächsten Insel
	 * NW steht für Nordwesten
	 * Wenn der berechnete Wert kleiner oder gleich Null ist, gibt die Methode 0 zurück
	 * Andernfalls gibt die Methode eine Zufallszahl zurück, die angibt, wo die nächste Insel auf dem Spielfeld platziert werden sollte
	 * 
	 * @param chosenIsland 
	 * @param xy
	 * @param inselXY
	 * @return 0 als falsch oder eine Zufallszahl als wahr
	 */
	private int possibleValuesNW(Island chosenIsland, int xy, int islandXY, Directions direction) {
		int possibleValues = 0;
		if(direction == Directions.NORTH)
			possibleValues = xy + (islandXY - y-2);
		else
			possibleValues = xy + (islandXY - x-2);
		if(possibleValues <= 0) {			
			return 0;
		} else {
			possibleValues = (random.nextInt(possibleValues));
			if(possibleValues == 0) {
				if((islandXY - 2) <= 0)
					return possibleValues =0;
				else
					return possibleValues = islandXY- 2;
				

				//return possibleValues;
			}
		}
		return possibleValues;	
	}

	
	
	/*****************************************************************************************************************************/
	/*****************************************************GETTER******************************************************************/
	/*****************************************************************************************************************************/

	/**
	 * @return the islandX
	 */
	public int getIslandX() {
		return islandX;
	}


	/**
	 * @param islandX the islandX to set
	 */
	public void setIslandX(int islandX) {
		this.islandX = islandX;
	}




	/**
	 * @return the islandY
	 */
	public int getIslandY() {
		return islandY;
	}




	/**
	 * Setzt den Y-Wert der Insel.
	 *
	 * @param islandY der Y-Wert, der gesetzt werden soll
	 */
	public void setIslandY(int islandY) {
	    this.islandY = islandY;
	}

	/**
	 * Überprüft, ob eine Brücke im Norden dieser Insel möglich ist.
	 *
	 * @return true, wenn eine Brücke im Norden möglich ist, sonst false
	 */
	public boolean isBridgeNorthPossible() {
	    if (getIslandY() == 1 || getIslandY() - 1 == 1) {
	        return false;
	    }
	    return true;
	}

	/**
	 * Überprüft, ob eine Brücke im Osten dieser Insel möglich ist.
	 *
	 * @return true, wenn eine Brücke im Osten möglich ist, sonst false
	 */
	public boolean isBridgeEastPossible() {
	    if (getIslandX() == getX() || getIslandX() + 1 == getX()) {
	        return false;
	    }
	    return true;
	}

	/**
	 * Überprüft, ob eine Brücke im Süden dieser Insel möglich ist.
	 *
	 * @return true, wenn eine Brücke im Süden möglich ist, sonst false
	 */
	public boolean isBridgeSouthPossible() {
	    if (getIslandY() == getY() || getIslandY() + 1 == getY()) {
	        return false;
	    }
	    return true;
	}

	/**
	 * Überprüft, ob eine Brücke im Westen dieser Insel möglich ist.
	 *
	 * @return true, wenn eine Brücke im Westen möglich ist, sonst false
	 */
	public boolean isBridgeWestPossible() {
	    if (getIslandX() == 1 || getIslandX() - 1 == 1) {
	        return false;
	    }
	    return true;
	}

	/**
	 * Gibt eine Zeichenfolge zurück, die die X-Koordinate der Insel darstellt.
	 *
	 * @return eine Zeichenfolge mit der X-Koordinate der Insel
	 */
	public String toString() {
	    String text = "X: " + x;
	    return text;
	}

	/**
	 * @return die Anzahl der Brücken, die mit dieser Insel verbunden sind
	 */
	public int getBridgeCount() {
	    return bridgeCount;
	}




	/**
	 * Setzt die Anzahl der Brücken, die mit dieser Insel verbunden sind.
	 *
	 * @param bridgeCount die Anzahl der Brücken, die gesetzt werden soll
	 */
	public void setBridgeCount(int bridgeCount) {
	    this.bridgeCount = bridgeCount;
	}

	/**
	 * Überprüft, ob eine Brücke im Norden dieser Insel existiert.
	 *
	 * @return true, wenn eine Brücke im Norden existiert, sonst false
	 */
	public boolean isNorth() {
	    return north;
	}

	/**
	 * Setzt den Wert, ob eine Brücke im Norden dieser Insel existiert.
	 *
	 * @param north true, wenn eine Brücke im Norden existiert, sonst false
	 */
	public void setNorth(boolean north) {
	    this.north = north;
	}

	/**
	 * Überprüft, ob eine Brücke im Osten dieser Insel existiert.
	 *
	 * @return true, wenn eine Brücke im Osten existiert, sonst false
	 */
	public boolean isEast() {
	    return east;
	}

	/**
	 * Setzt den Wert, ob eine Brücke im Osten dieser Insel existiert.
	 *
	 * @param east true, wenn eine Brücke im Osten existiert, sonst false
	 */
	public void setEast(boolean east) {
	    this.east = east;
	}

	/**
	 * Überprüft, ob eine Brücke im Süden dieser Insel existiert.
	 *
	 * @return true, wenn eine Brücke im Süden existiert, sonst false
	 */
	public boolean isSouth() {
	    return south;
	}

	/**
	 * Setzt den Wert, ob eine Brücke im Süden dieser Insel existiert.
	 *
	 * @param south true, wenn eine Brücke im Süden existiert, sonst false
	 */
	public void setSouth(boolean south) {
	    this.south = south;
	}

	/**
	 * Überprüft, ob eine Brücke im Westen dieser Insel existiert.
	 *
	 * @return true, wenn eine Brücke im Westen existiert, sonst false
	 */
	public boolean isWest() {
	    return west;
	}




	/**
	 * Setzt den Wert, ob eine Brücke im Westen dieser Insel existiert.
	 *
	 * @param west true, wenn eine Brücke im Westen existiert, sonst false
	 */
	public void setWest(boolean west) {
	    this.west = west;
	}

	/**
	 * Gibt die Liste der Inseln zurück.
	 *
	 * @return die Liste der Inseln
	 */
	public ArrayList<Island> getListofIslands() {
	    return listofIslands;
	}

	/**
	 * Setzt die Liste der Inseln.
	 *
	 * @param listofIslands die Liste der Inseln, die gesetzt werden soll
	 */
	public void setListofIslands(ArrayList<Island> listofIslands) {
	    this.listofIslands = listofIslands;
	}

	/**
	 * Gibt die X-Koordinate dieser Insel zurück.
	 *
	 * @return die X-Koordinate der Insel
	 */
	public int getX() {
	    return x;
	}

	/**
	 * Setzt die X-Koordinate dieser Insel.
	 *
	 * @param x die X-Koordinate, die gesetzt werden soll
	 */
	public void setX(int x) {
	    this.x = x;
	}


	/**
	 * Gibt die Y-Koordinate dieser Insel zurück.
	 *
	 * @return die Y-Koordinate der Insel
	 */
	public int getY() {
	    return y;
	}

	/**
	 * Setzt die Y-Koordinate dieser Insel.
	 *
	 * @param y die Y-Koordinate, die gesetzt werden soll
	 */
	public void setY(int y) {
	    this.y = y;
	}

	/**
	 * Gibt die ID dieser Insel zurück.
	 *
	 * @return die ID der Insel
	 */
	public int getId() {
	    return id;
	}

	/**
	 * Setzt die ID dieser Insel.
	 *
	 * @param id die ID, die gesetzt werden soll
	 */
	public void setId(int id) {
	    this.id = id;
	}

	/**
	 * Gibt den Index dieser Insel zurück.
	 *
	 * @return der Index der Insel
	 */
	public int getIndex() {
	    return index;
	}

	/**
	 * Setzt den Index dieser Insel.
	 *
	 * @param index der Index, der gesetzt werden soll
	 */
	public void setIndex(int index) {
	    this.index = index;
	}


	/**
	 * Überprüft, ob diese Insel korrekt platziert ist.
	 *
	 * @return true, wenn die Insel korrekt platziert ist, andernfalls false
	 */
	public boolean isOk() {
	    return isOk;
	}

	/**
	 * Setzt den Zustand, ob diese Insel korrekt platziert ist oder nicht.
	 *
	 * @param isOk true, wenn die Insel korrekt platziert ist, andernfalls false
	 */
	public void setOk(boolean isOk) {
	    this.isOk = isOk;
	}

	/**
	 * Gibt die X-Koordinate des Zentrums dieser Insel zurück.
	 *
	 * @return die X-Koordinate des Zentrums der Insel
	 */
	public int getCenterX() {
	    return centerX;
	}

	/**
	 * Setzt die X-Koordinate des Zentrums dieser Insel.
	 *
	 * @param centerX die X-Koordinate des Zentrums der Insel
	 */
	public void setCenterX(int centerX) {
	    this.centerX = centerX;
	}

	/**
	 * Gibt die Y-Koordinate des Zentrums dieser Insel zurück.
	 *
	 * @return die Y-Koordinate des Zentrums der Insel
	 */
	public int getCenterY() {
	    return centerY;
	}

	/**
	 * Setzt die Y-Koordinate des Zentrums dieser Insel.
	 *
	 * @param centerY die Y-Koordinate des Zentrums der Insel
	 */
	public void setCenterY(int centerY) {
	    this.centerY = centerY;
	}



}