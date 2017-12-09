package photoelectriceffectsimulator.utilities;
import java.awt.Color;

/**
 * Klasa pomocnicza agregująca informację o metalu z którego wykonany jest przyrząd
 * 
 * @author Jakub Sikora
 */
public class MetalInfo {
    /** Liczba atomowa pierwiastka */
    private final int atomicNumber;
    
    /** Symbol pierwiastka */
    private final String elementShortcut;
    
    /** Pełna nazwa pierwiastka */
    private final String elementName;
    
    /** Liczba atomowa pierwiastka */
    private final double atomicMass;
    
    /** Kolor przypisany danej grupie pierwiastków*/
    private final Color color;
    
    /**
     * Konstruktor parametryzowany informacjami które ma zagregować
     * @param atomicNumber liczba atomowa
     * @param elementShortcut symbol 
     * @param elementName nazwa
     * @param atomicMass masa atomowa 
     * @param color przypisany kolor
     */
    MetalInfo(int atomicNumber, String elementShortcut, String elementName, double atomicMass, Color color){
        this.atomicNumber = atomicNumber;
        this.elementShortcut = elementShortcut;
        this.elementName = elementName;
        this.atomicMass = atomicMass;
        this.color = color;
    }
    
    /**
     * Zwraca liczbę atomową pierwiastka
     * @return liczba atomowa
     */
    public int getAtomicNumber(){
        return atomicNumber;
    }
    
    /**
     * Zwraca symbol pierwiastka
     * @return symbol pierwiastka
     */
    public String getElementShortcut(){
        return elementShortcut;
    }
    
    /**
     * Zwraca nazwę pierwiastka
     * @return nazwa pierwiastka
     */
    public String getElementName(){
        return elementName;
    }
    
    /**
     * Zwraca liczbę atomową 
     * @return liczba atomowa
     */
    public double getAtomicMass(){
        return atomicMass;
    }
    
    /**
     * Zwraca przypisany kolor
     * @return przypisany kolor
     */
    public Color getColor(){
        return color;
    }
}

