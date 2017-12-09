package photoelectriceffectsimulator.controller;

import photoelectriceffectsimulator.utilities.ExpNumber;

/**
 * Interfejs obsługi komunikacji kontrolera z widokiem.
 * Udostępnia metody zwracające nowe wartości zadane przez użytkownika.
 * @see Controller
 * 
 * @author Jakub Sikora
 */
public interface ControllerViewCommunicator {
    /**
     * Funkcja obsługująca zmianę długości fali w widoku
     * @param newValue nowa wartość długości fali
     */
    public void waveLengthChange(ExpNumber newValue);
    
    /**
     * Funkcja obsługująca zmianę natężenia światła w widoku
     * @param newValue nowa wartość natężenia światła
     */
    public void intensityChange(int newValue);
    
    /**
     * Funkcja obsługująca zmianę napięcia przyłożonego do fotokatody w widoku
     * @param newValue nowa wartość napięcia przyłożonego do fotokatody
     */
    public void voltageChange(double newValue);
    
    /**
     * Funkcja obsługująca zmianę typu metalu w widoku
     * @param newMetalType nowy typ metalu
     */
    public void metalTypeChange(String newMetalType);
}
