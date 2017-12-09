package photoelectriceffectsimulator.model;

import photoelectriceffectsimulator.utilities.ExpNumber;

/**
 * Interfejs obsługujący funkcjonalność modelu. Udostępnione metody pozwalają
 * na modyfikację stanu modelu, dzięki czemu możliwe jest wykonywanie obliczeń.
 * 
 * @author Jakub Sikora
 */
public interface AbstractModel {
    /**
     * Metoda pozwala ustawić wartość natężenia światła
     * @param newValue nowa wartość natężenia światła
     */
    public void setIntensity(int newValue);
    /**
     * Metoda pozwala na ustawienie wartości długości fali
     * @param newValue nowa wartość długości fali
     */
    public void setWaveLength(ExpNumber newValue);
    /**
     * Metoda pozwala na ustawienie wartości napięcia przyłożonego
     * @param newVoltage nowa wartość napięcia przyłożonego
     */
    public void setVoltage(double newVoltage);
    /**
     * Metoda pozwala na ustawienie typu metalu na podstawie przekazanej nazwy
     * @param newType nowy typ metalu 
     */
    public void setMetalType(String newType);
}
