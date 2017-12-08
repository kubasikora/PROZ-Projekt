package photoelectriceffectsimulator.controller;

import javax.swing.JOptionPane;
import photoelectriceffectsimulator.utilities.ExpNumber;
import photoelectriceffectsimulator.model.Model;
import photoelectriceffectsimulator.view.View;


/**
 * Obiekt kontrolera zajmuje się zbieraniem przygotowanych 
 * informacji od obiektu widoku i wydawaniem poleceń 
 * do obiektu modelu, implementuje interfejsy komunikacji model-kontroler
 * i kontroler-widok
 * @author Jakub Sikora
 */
public class Controller implements ControllerModelCommunicator,
                                   ControllerViewCommunicator {
    /**
     * Referencja do obiektu modelu 
     * @see Model
     */
    private final Model model;
    /**
     * Referencja do obiektu widoku
     * @see View
     */
    private final View view;
    
    /**
     * Konstruktor bezparametrowy 
     */
    public Controller(View view, Model model){
        this.view = view;
        this.model = model;
    }
    
    ////////////////Zmiany w VIEW które należy obsłużyć
    
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
    
    
    //////////////////zmiany wywowaływane w MODELU aby wpłynąć na widok
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
    
    /////////////gettery modelu i widoku
    
    /**
     * Zwraca referencje do obiektu modelu
     * @return referencja do modelu
     */
    public Model getModel(){
        return model;
    }
    
    /**
     * Zwraca referencje do obiektu widoku
     * @return referencja do widoku
     */
    public View getView(){
        return view;
    }
      
}
