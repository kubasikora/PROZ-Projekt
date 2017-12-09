package photoelectriceffectsimulator.model;

import photoelectriceffectsimulator.utilities.ExpNumber;
import photoelectriceffectsimulator.utilities.MetalType;
import photoelectriceffectsimulator.controller.ControllerModelCommunicator;


/**
 * Obiekt modelu zjawiska, który analizuje dane wejściowe, przechowuje
 * stan i symuluje zjawisko na podstawie przyjętego wcześniej modelu
 * @author Jakub Sikora
 */
public class Model implements AbstractModel{
    /**
     * Referencja do obiektu kontrolera z którym model ma się komunikować
     * @see Controller
     */
    private ControllerModelCommunicator controller;
    
    /**
     * Obiekt przechowujący dane pobrane z obiektu widoku
     */
    private ModelState modelState;
    
    /**
     * Obiekt wykonujący obliczenia 
     */
    private ModelMathUnit mathUnit;
    
    /**
     * ustaw wartość długości fali
     * @param newValue długość fali
     */
    public void setWaveLength(ExpNumber newValue){
        modelState.setWaveLength(newValue);
    }
    
    /**
     * ustaw wartość natężenia światła
     * @param newValue natężenie światła
     */
    public void setIntensity(int newValue){
        modelState.setIntensity(newValue);
    }
    
    /**
     * ustaw wartość napięcia przyłożonego
     * @param newVoltage nowe napięcie 
     */
    public void setVoltage(double newVoltage){
        modelState.setVoltage(newVoltage);
    }
    
    /**
     * wybierz typ metalu 
     * @param newType typ metalu
     */
    public void setMetalType(String newType){
        modelState.setActiveMetalType(MetalType.convertFromString(newType));
    }
    
    public void informController(ModelResult newResult){
        controller.printOutcomeExitEnergy(newResult.getExitEnergy());
        controller.printOutcomePhotonEnergy(newResult.getPhotonEnergy());
        controller.printOutcomeCurrent(newResult.getCurrent());
    }
    
    /**
     * Funkcja inicjalizująca obiekt modelu
     */
    private void initializeModel(){
        modelState = new ModelState();
        mathUnit = new ModelMathUnit(this);
        modelState.addObserver(mathUnit);
    }
    
    /**
     * Zwraca referencji do stanu modelu - struktury danych przechowującej stan
     * @return modelState stan modelu
     */
    public ModelState getModelState(){
        return modelState;
    }
    
    /**
     * Zwraca referencje do aktywnego kontrolera
     * @return referencja do aktywnego kontrolera 
     */
    public ControllerModelCommunicator getController(){
        return controller;
    }
    
    /**
     * Ustaw referencje do obiektu kontrolera
     * @param controller referencja do obiektu kontrolera
     */
    public void setController(ControllerModelCommunicator controller){
        this.controller = controller;
        initializeModel();
    }
}
