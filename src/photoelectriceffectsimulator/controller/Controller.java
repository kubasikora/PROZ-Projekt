package photoelectriceffectsimulator.controller;

import photoelectriceffectsimulator.utilities.ExpNumber;
import photoelectriceffectsimulator.model.*;
import photoelectriceffectsimulator.view.*;


/**
 * Obiekt kontrolera, zajmuje się zbieraniem poleceń z {@link View}, przekazywaniu ich
 * do {@link Model}, odbieraniu od niego wyników obliczeń i wysyłaniu ich do prezentacji
 * użytkownikowi 
 * 
 * Implementuje interfejsy komunikacji kontroler-model i kontroler-widok, w celu
 * wymienności modeli i widoków.
 * 
 * @author Jakub Sikora
 */
public class Controller implements ControllerModelCommunicator,
                                   ControllerViewCommunicator {
    /**
     * Referencja do obiektu modelu 
     * @see Model
     */
    private final AbstractModel model;
    /**
     * Referencja do obiektu widoku
     * @see View
     */
    private final AbstractView view;
    
    /**
     * Konstruktor parametryzowany obiektami {@link View} i {@link Model}
     * @param view obiekt prezentacji danych
     * @param model obiekt modelowania aplikacj
     */
    public Controller(View view, Model model){
        this.view = view;
        this.model = model;
    }
    
    
    //Metody interfesju ControllerViewCommunicator
    /**
     * Obsługuje zmianę długości fali przez użytkownika     
     * @param newValue nowa długość fali
     */
    @Override
    public void waveLengthChange(ExpNumber newValue){
        model.setWaveLength(newValue);
    }
    
    /**
     * Obsługuje zmianę natężenia światła przez użytkownika 
     * @param newValue nowe natężenie światła
     */
    @Override
    public void intensityChange(int newValue){
        model.setIntensity(newValue);
    }
    
    /**
     * Obsługuje zmianę napięcia przyłożonego przez użytkownika
     * @param newValue nowe napięcie
     */
    @Override
    public void voltageChange(double newValue){
        model.setVoltage(newValue);
    }
    
    /**
     * Obsługuje zmianę typu metalu przez użytkownika
     * @param newMetalType nowy typ metalu
     */
    @Override
    public void metalTypeChange(String newMetalType){
        view.setElementType(newMetalType);
        model.setMetalType(newMetalType);
    }
    
    
    //Metody interfejsu ControllerModelCommunicator
    /**
     * Funkcja zmienia wartość wyświetlanej na ekranie pracy wyjścia 
     * @param exitEnergy zaktualizowana praca wyjścia
     */
    @Override
    public void printOutcomeExitEnergy(double exitEnergy){
        view.setExitEnergy(exitEnergy);
    }
    
    /**
     * Funkcja zmienia wartość wyświetlanej na ekranie wartości natężenia prądu
     * @param current zaktualizowane natężenie prądu
     */
    @Override
    public void printOutcomeCurrent(ExpNumber current){
        view.setOutcomeCurrent(current);
    }
    
    /**
     * Funkcja zmienia wartość wyświetlanej na ekranie wartośc energii fotonu
     * @param photonEnergy zaktualizowana energia fotonu
     */
    @Override
    public void printOutcomePhotonEnergy(double photonEnergy){
        view.setPhotonEnergy(photonEnergy);
    }
    
    //gettery modelu i widoku 
    
    /**
     * Zwraca referencje do obiektu modelu
     * @return referencja do modelu
     */
    public AbstractModel getModel(){
        return model;
    }
    
    /**
     * Zwraca referencje do obiektu widoku
     * @return referencja do widoku
     */
    public AbstractView getView(){
        return view;
    }
      
}
