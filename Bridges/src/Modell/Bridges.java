package Modell;


import java.util.ArrayList;


/**
 * Die `Bridges`-Klasse repräsentiert eine Brücke zwischen zwei Inseln.
 * Sie enthält Informationen über die Positionen der beiden Inseln, den Brückentyp und die Verbindungen.
 */
public class Bridges {
	
    // Die X- und Y-Koordinaten der ersten Insel
    private int xFirst, yFirst;
    
    // Die X- und Y-Koordinaten der zweiten Insel
    private int xSecond, ySecond;
    
    // Die Brücke: 0 für einfache Brücke, 1 für doppelte Brücke
    private int dBridge;
    
    // Die IDs der ersten und zweiten Insel
    private int fID, sID;
    
    // Eine Liste zur Speicherung von Verbindungsinformationen
    private ArrayList<Integer> connection = new ArrayList<Integer>();
    
    /**
     * Konstruktor für die Bridges-Klasse.
     *
     * @param firstID Die ID der ersten Insel.
     * @param firstX  Die X-Koordinate der ersten Insel.
     * @param firstY  Die Y-Koordinate der ersten Insel.
     * @param secondID Die ID der zweiten Insel.
     * @param secondX  Die X-Koordinate der zweiten Insel.
     * @param secondY  Die Y-Koordinate der zweiten Insel.
     * @param doubleBridge  Eine Flagge für die Art der Brücke (0 für einfach, 1 für doppelt).
     * @param connection Eine Liste von Verbindungsinformationen.
     */
    public Bridges(int firstID, int firstX, int firstY, int secondID, int secondX, int secondY,  int doubleBridge, 
            ArrayList<Integer> connection) { 
        xFirst = firstX;
        yFirst = firstY;
        xSecond = secondX;
        ySecond = secondY;
        setdBridge(doubleBridge);
        fID = firstID;
        sID = secondID;
        setConnection(connection);
    }

    /**
     * Gibt die X-Koordinate der ersten Insel zurück.
     *
     * @return Die X-Koordinate der ersten Insel.
     */
    public int getxFirst() {
        return xFirst;
    }

    /**
     * Setzt die X-Koordinate der ersten Insel.
     *
     * @param xFirst Die zu setzende X-Koordinate der ersten Insel.
     */
    public void setxFirst(int xFirst) {
        this.xFirst = xFirst;
    }

    /**
     * Gibt die Y-Koordinate der ersten Insel zurück.
     *
     * @return Die Y-Koordinate der ersten Insel.
     */
    public int getyFirst() {
        return yFirst;
    }

    /**
     * Setzt die Y-Koordinate der ersten Insel.
     *
     * @param yFirst Die zu setzende Y-Koordinate der ersten Insel.
     */
    public void setyFirst(int yFirst) {
        this.yFirst = yFirst;
    }

    /**
     * Gibt die X-Koordinate der zweiten Insel zurück.
     *
     * @return Die X-Koordinate der zweiten Insel.
     */
    public int getxSecond() {
        return xSecond;
    }

    /**
     * Setzt die X-Koordinate der zweiten Insel.
     *
     * @param xSecond Die zu setzende X-Koordinate der zweiten Insel.
     */
    public void setxSecond(int xSecond) {
        this.xSecond = xSecond;
    }

    /**
     * Gibt die Y-Koordinate der zweiten Insel zurück.
     *
     * @return Die Y-Koordinate der zweiten Insel.
     */
    public int getySecond() {
        return ySecond;
    }

    /**
     * Setzt die Y-Koordinate der zweiten Insel.
     *
     * @param ySecond Die zu setzende Y-Koordinate der zweiten Insel.
     */
    public void setySecond(int ySecond) {
        this.ySecond = ySecond;
    }

    /**
     * Gibt den Brückentyp zurück (0 für einfach, 1 für doppelt).
     *
     * @return Der Brückentyp.
     */
    public int getdBridge() {
        return dBridge;
    }

    /**
     * Setzt den Brückentyp (0 für einfach, 1 für doppelt).
     *
     * @param dBridge Der zu setzende Brückentyp.
     */
    public void setdBridge(int dBridge) {
        this.dBridge = dBridge;
    }

    /**
     * Gibt die ID der ersten Insel zurück.
     *
     * @return Die ID der ersten Insel.
     */
    public int getfID() {
        return fID;
    }

    /**
     * Setzt die ID der ersten Insel.
     *
     * @param fID Die zu setzende ID der ersten Insel.
     */
    public void setfID(int fID) {
        this.fID = fID;
    }

    /**
     * Gibt die ID der zweiten Insel zurück.
     *
     * @return Die ID der zweiten Insel.
     */
    public int getsID() {
        return sID;
    }

    /**
     * Setzt die ID der zweiten Insel.
     *
     * @param sID Die zu setzende ID der zweiten Insel.
     */
    public void setsID(int sID) {
        this.sID = sID;
    }

    /**
     * Gibt die Verbindungsinformationen zurück.
     *
     * @return Die Verbindungsinformationen als ArrayList.
     */
    public ArrayList<Integer> getConnection() {
        return connection;
    }

    /**
     * Setzt die Verbindungsinformationen.
     *
     * @param connection Die zu setzenden Verbindungsinformationen als ArrayList.
     */
    public void setConnection(ArrayList<Integer> connection) {
        this.connection = connection;
    }
    

}
