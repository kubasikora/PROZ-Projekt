package photoelectriceffectsimulator.model;

import java.util.Observable;
import photoelectriceffectsimulator.utilities.ExpNumber;
import photoelectriceffectsimulator.utilities.MetalType;

/**
 * Obiekt przechowuje informacje o wybranych przez użytkownika ustawieniach,
 * na podstawie których będą przeprowadzane obliczenia
 * @author Jakub Sikora
 */
public class ModelState extends Observable {
    /**
     * Typ metalu oraz powiązana z nim praca wyjścia w elektronowoltach
     */
    private MetalType activeMetalType;
    /**
     * Długośc fali padającej
     */
    private ExpNumber waveLength;
    /**
     * Napięcie przyłożone do płytek w woltach
     */
    private double voltage;
    /**
     * Natężenie światła padającego
     */
    private double intensity;
    
    
    
    /**
     * Konstruktor bezparametrowy
     */
    ModelState(){
        activeMetalType = MetalType.FERRUM;
        waveLength = new ExpNumber(600,-9);
        intensity = 0.0;
        voltage = 0.0;
        
    }
    
    //getttery i settery
    /**
     * Zwraca wybrany typ metalu
     * @return aktywny typ metalu
     */
    public MetalType getActiveMetalType(){
        return activeMetalType;
    }
    
    /**
     * Ustawia nowy typ metalu
     * @param metalType typ metalu
     */
    public void setActiveMetalType(MetalType metalType){
        activeMetalType = metalType;
        this.setChanged();
        this.notifyObservers();
    }
    
    /**
     * Zwraca częstotliwość fali padającej
     * @return częstotliwość fali padającej
     */
    public ExpNumber getWaveLength(){
        return waveLength;
    }
    
    /**
     * Ustawia częstotliwość fali padającej
     * @param waveLength długość fali padającej
     */
    public void setWaveLength(ExpNumber waveLength){
        this.waveLength = waveLength;
        this.setChanged();
        this.notifyObservers();
    }
    
    /**
     * Zwraca napięcie między okładkami
     * @return napięcie między okładkami
     */
    public double getVoltage(){
        return voltage;
    }
    
    /**
     * Ustawia napięcie między okładkami
     * @param voltage napięcie między okładkami
     */
    public void setVoltage(double voltage){
        this.voltage = voltage;
        this.setChanged();
        this.notifyObservers();
    }
    
    
    /**
     * Zwraca natężenie fali padającej
     * @return natężenie fali padającej
     */
    public double getIntensity(){
        return intensity;
    }
    
    /**
     * Ustawia natężenie fali padającej
     * @param intensity natężenie fali padającej
     */
    public void setIntensity(double intensity){
        this.intensity = intensity;
        this.setChanged();
        this.notifyObservers();
    }
    
}
