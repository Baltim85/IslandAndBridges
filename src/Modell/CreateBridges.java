package Modell;

/**
 * Die Klasse CreateBridges stellt eine Darstellung von Brücken zwischen zwei Inseln im Brückenrätsel dar. Sie enthält 
 * Informationen über die eindeutige ID und die Koordinaten der beteiligten Inseln sowie die Anzahl der Brücken zwischen ihnen.
 *
 * Attribute:
 * - firstIslandID: Die eindeutige ID der ersten Insel.
 * - secondIslandID: Die eindeutige ID der zweiten Insel.
 * - firstIslandX: Die X-Koordinate der ersten Insel.
 * - secondIslandX: Die X-Koordinate der zweiten Insel.
 * - firstIslandY: Die Y-Koordinate der ersten Insel.
 * - secondIslandY: Die Y-Koordinate der zweiten Insel.
 * - numberOfBridges: Die Anzahl der Brücken zwischen den Inseln.
 *
 * Konstruktoren:
 * - CreateBridges(int firstIslandID, int firstIslandX, int firstIslandY, int secondIslandID, int secondIslandX, int secondIslandY, 
 * int numberOfBridges): Erzeugt ein Objekt zur Darstellung einer Brücke zwischen zwei Inseln mit einer angegebenen Anzahl von 
 * Brücken und den Koordinaten der Inseln.
 * - CreateBridges(int firstIslandID, int firstIslandX, int firstIslandY, int secondIslandID, int secondIslandX, int secondIslandY): 
 * Erzeugt ein Objekt zur Darstellung einer Brücke zwischen zwei Inseln mit einer Standardanzahl von Brücken und den Koordinaten 
 * der Inseln.
 *
 * Methoden:
 * - getFirstIslandID(): Gibt die ID der ersten Insel zurück.
 * - setFirstIslandID(int firstIslandID): Setzt die ID der ersten Insel.
 * - getSecondIslandID(): Gibt die ID der zweiten Insel zurück.
 * - setSecondIslandID(int secondIslandID): Setzt die ID der zweiten Insel.
 * - getFirstIslandX(): Gibt die X-Koordinate der ersten Insel zurück.
 * - setFirstIslandX(int firstIslandX): Setzt die X-Koordinate der ersten Insel.
 * - getSecondIslandX(): Gibt die X-Koordinate der zweiten Insel zurück.
 * - setSecondIslandX(int secondIslandX): Setzt die X-Koordinate der zweiten Insel.
 * - getFirstIslandY(): Gibt die Y-Koordinate der ersten Insel zurück.
 * - setFirstIslandY(int firstIslandY): Setzt die Y-Koordinate der ersten Insel.
 * - getSecondIslandY(): Gibt die Y-Koordinate der zweiten Insel zurück.
 * - setSecondIslandY(int secondIslandY): Setzt die Y-Koordinate der zweiten Insel.
 * - getNumberOfBridges(): Gibt die Anzahl der Brücken zwischen den Inseln zurück.
 * - setNumberOfBridges(int numberOfBridges): Setzt die Anzahl der Brücken zwischen den Inseln.
 */
public class CreateBridges {

	
    private int firstIslandID; // Eindeutige ID der ersten Insel
    private int secondIslandID; // Eindeutige ID der zweiten Insel
    private int firstIslandX; // X-Koordinate der ersten Insel
    private int secondIslandX; // X-Koordinate der zweiten Insel
    private int firstIslandY; // Y-Koordinate der ersten Insel
    private int secondIslandY; // Y-Koordinate der zweiten Insel
    private int numberOfBridges; // Anzahl der Brücken zwischen den Inseln


	
	/**
	 * Erzeugt ein Objekt zur Darstellung einer Brücke zwischen zwei Inseln mit einer angegebenen Anzahl von Brücken
	 * und einer Liste von Verbindungen von welcher Insel zur welcher Insel. Dieser Konstruktor erfordert zusätzliche
	 * Informationen über die Brücken.
	 *
	 * @param firstIslandID   Die eindeutige ID der ersten Insel.
	 * @param firstIslandX    Die X-Koordinate der ersten Insel.
	 * @param firstIslandY    Die Y-Koordinate der ersten Insel.
	 * @param secondIslandID  Die eindeutige ID der zweiten Insel.
	 * @param secondIslandX   Die X-Koordinate der zweiten Insel.
	 * @param secondIslandY   Die Y-Koordinate der zweiten Insel.
	 * @param numberOfBridges Die Anzahl der Brücken zwischen den Inseln.
	 * @param connectionFromTo Eine Liste von Verbindungen von welcher Insel zur welcher Insel.
	 */
	public CreateBridges(int firstIslandID, int firstIslandX, int firstIslandY, int secondIslandID, 
			int secondIslandX, int secondIslandY, int numberOfBridges) {
		this.firstIslandID = firstIslandID;
		this.firstIslandX = firstIslandX;
		this.firstIslandY = firstIslandY;
		
		this.secondIslandID = secondIslandID;
		this.secondIslandX = secondIslandX;
		this.secondIslandY = secondIslandY;
		this.numberOfBridges = numberOfBridges;
		
		
		
	}

	
	/**
	 * Erzeugt ein Objekt zur Darstellung einer Brücke zwischen zwei Inseln, ohne zusätzliche Informationen über
	 * die Brücken. Dieser Konstruktor erzeugt eine Standardbrücke zwischen den beiden Inseln.
	 *
	 * @param firstIslandID   Die eindeutige ID der ersten Insel.
	 * @param firstIslandX    Die X-Koordinate der ersten Insel.
	 * @param firstIslandY    Die Y-Koordinate der ersten Insel.
	 * @param secondIslandID  Die eindeutige ID der zweiten Insel.
	 * @param secondIslandX   Die X-Koordinate der zweiten Insel.
	 * @param secondIslandY   Die Y-Koordinate der zweiten Insel.
	 */
	public CreateBridges(int firstIslandID, int firstIslandX, int firstIslandY,
			int secondIslandID, int secondIslandX, int secondIslandY) {
		this.firstIslandID = firstIslandID;
		this.firstIslandX = firstIslandX;
		this.firstIslandY = firstIslandY;
		
		this.secondIslandID = secondIslandID;
		this.secondIslandX = secondIslandX;
		this.secondIslandY = secondIslandY;
	}



    /**
     * Gibt die ID der ersten Insel zurück.
     * 
     * @return Die ID der ersten Insel.
     */
    public int getFirstIslandID() {
        return firstIslandID;
    }

    /**
     * Setzt die ID der ersten Insel.
     * 
     * @param firstIslandID Die ID der ersten Insel.
     */
    public void setFirstIslandID(int firstIslandID) {
        this.firstIslandID = firstIslandID;
    }

    /**
     * Gibt die ID der zweiten Insel zurück.
     * 
     * @return Die ID der zweiten Insel.
     */
    public int getSecondIslandID() {
        return secondIslandID;
    }

    /**
     * Setzt die ID der zweiten Insel.
     * 
     * @param secondIslandID Die ID der zweiten Insel.
     */
    public void setSecondIslandID(int secondIslandID) {
        this.secondIslandID = secondIslandID;
    }

    /**
     * Gibt die X-Koordinate der ersten Insel zurück.
     * 
     * @return Die X-Koordinate der ersten Insel.
     */
    public int getFirstIslandX() {
        return firstIslandX;
    }

    /**
     * Setzt die X-Koordinate der ersten Insel.
     * 
     * @param firstIslandX Die X-Koordinate der ersten Insel.
     */
    public void setFirstIslandX(int firstIslandX) {
        this.firstIslandX = firstIslandX;
    }

    /**
     * Gibt die X-Koordinate der zweiten Insel zurück.
     * 
     * @return Die X-Koordinate der zweiten Insel.
     */
    public int getSecondIslandX() {
        return secondIslandX;
    }

    /**
     * Setzt die X-Koordinate der zweiten Insel.
     * 
     * @param secondIslandX Die X-Koordinate der zweiten Insel.
     */
    public void setSecondIslandX(int secondIslandX) {
        this.secondIslandX = secondIslandX;
    }

    /**
     * Gibt die Y-Koordinate der ersten Insel zurück.
     * 
     * @return Die Y-Koordinate der ersten Insel.
     */
    public int getFirstIslandY() {
        return firstIslandY;
    }

    /**
     * Setzt die Y-Koordinate der ersten Insel.
     * 
     * @param firstIslandY Die Y-Koordinate der ersten Insel.
     */
    public void setFirstIslandY(int firstIslandY) {
        this.firstIslandY = firstIslandY;
    }

    /**
     * Gibt die Y-Koordinate der zweiten Insel zurück.
     * 
     * @return Die Y-Koordinate der zweiten Insel.
     */
    public int getSecondIslandY() {
        return secondIslandY;
    }

    /**
     * Setzt die Y-Koordinate der zweiten Insel.
     * 
     * @param secondIslandY Die Y-Koordinate der zweiten Insel.
     */
    public void setSecondIslandY(int secondIslandY) {
        this.secondIslandY = secondIslandY;
    }

    /**
     * Gibt die Anzahl der Brücken zwischen den Inseln zurück.
     * 
     * @return Die Anzahl der Brücken zwischen den Inseln.
     */
    public int getNumberOfBridges() {
        return numberOfBridges;
    }

    /**
     * Setzt die Anzahl der Brücken zwischen den Inseln.
     * 
     * @param numberOfBridges Die Anzahl der Brücken zwischen den Inseln.
     */
    public void setNumberOfBridges(int numberOfBridges) {
        this.numberOfBridges = numberOfBridges;
    }



}
