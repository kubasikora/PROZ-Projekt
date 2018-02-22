package photoelectriceffectsimulator.controller;

import photoelectriceffectsimulator.utilities.ExpNumber;

/**
 * Interfejs obsługujący funkcjonalność widoku. Udostępnione metody pozwalają
 * na prezentację informacji na ekranie.
 * 
 * @author Jakub Sikora
 */

public interface AbstractView {
    /** 
    * Wyświetl typ pierwiastka 
    * @param newType typ metalu do wyświetlenia
     */
    public void setElementType(String newType);
    
    /** 
     * Ustaw wyświetlaną pracę wyjścia
     * @param exitEnergy wartość pracy wyjścia do wyświetlenia
     */
    public void setExitEnergy(double exitEnergy);
    
    /** 
     * Ustaw wyświetlaną wartośc prądu 
     * @param current wartośc prądu do wyświetlenia
     */
    public void setOutcomeCurrent(ExpNumber current);
    
    /** 
     * Ustaw wyświetlaną wartość energii fotonu
     * @param photonEnergy wartość energii fotonu do wyświetlenia
     */
    public void setPhotonEnergy(double photonEnergy);
}
