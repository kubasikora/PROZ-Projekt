package photoelectriceffectsimulator.model;

import photoelectriceffectsimulator.utilities.ExpNumber;

/**
 * Klasa agregująca wyniki obliczone przez model. 
 * Służy do przekazywania wyników z obiektu liczącego do obiektu nadrzędnego.
 * 
 * @author Jakub Sikora
 */

public class ModelResult {
    /** Obliczony prąd przyrządu */
    private final ExpNumber current;
    
    /** Obliczona energia fotonu */
    private final double photonEnergy;
    
    /** Obliczona wartość pracy wyjścia */
    private final double exitEnergy;
    
    /**
     * Konstruktor parametryzowany wartościami które ma zagregować
     * @param current prąd przyrządu
     * @param photonEnergy energia fotonu
     * @param exitEnergy praca wyjścia z katody
     */
    ModelResult(ExpNumber current, double photonEnergy, double exitEnergy){
        this.current = current;
        this.photonEnergy = photonEnergy;
        this.exitEnergy = exitEnergy;  
    }
    
    /**
     * Zwraca obliczony prąd
     * @return prąd katody
     */
    public ExpNumber getCurrent(){
        return current;
    }
    
    /**
     * Zwraca obliczoną energię fotonu
     * @return energia fotonu
     */
    public double getPhotonEnergy(){
        return photonEnergy;
    }
    
    /**
     * Zwraca obliczoną pracę wyjścia
     * @return praca wyjścia z katody
     */
    public double getExitEnergy(){
        return exitEnergy;
    }
    
}