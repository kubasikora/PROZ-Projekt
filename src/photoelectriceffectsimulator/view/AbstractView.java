package photoelectriceffectsimulator.view;

import photoelectriceffectsimulator.utilities.ExpNumber;

/**
 * Interfejs obsługujący funkcjonalność widoku. Udostępnione metody pozwalają
 * na prezentację informacji na ekranie.
 * 
 * @author Jakub Sikora
 */

public interface AbstractView {
    /** Wyświetl typ pierwiastka */
    public void setElementType(String newType);
    
    /** Ustaw wyświetlaną pracę wyjścia*/
    public void setExitEnergy(double exitEnergy);
    
    /** Ustaw wyświetlaną wartośc prądu */
    public void setOutcomeCurrent(ExpNumber current);
    
    /** Ustaw wyświetlaną wartość energii fotonu*/
    public void setPhotonEnergy(double photonEnergy);
}
