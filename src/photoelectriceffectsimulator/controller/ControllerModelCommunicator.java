package photoelectriceffectsimulator.controller;

import photoelectriceffectsimulator.utilities.ExpNumber;

/**
 * Interfejs obsługi komunikacji kontrolera z modelem
 * @author kuba
 */
public interface ControllerModelCommunicator {
    /**
     * Funkcja wywoływana z poziomu modelu do zaktualizowania wyświetlanej 
     * pracy wyjścia
     * @param exitEnergy zaktualizowana praca wyjścia 
     */
    public void printOutcomeExitEnergy(double exitEnergy);
    
    /**
     * Funkcja wywoływana z poziomu modelu do zaktualizowania wyświetlanego
     * prądu przyrządu
     * @param current zaktualizowany prąd przyrządu
     */
    public void printOutcomeCurrent(ExpNumber current);
    
    /**
     * Funkcja wywoływana z poziomu modelu do zaktualizowania wyświetlanej 
     * maksymalnej energii fotonu
     * @param photonEnergy energia fotonu 
     */
    public void printOutcomePhotonEnergy(double photonEnergy);
}
