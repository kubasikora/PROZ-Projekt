package photoelectriceffectsimulator.model;

import photoelectriceffectsimulator.controller.AbstractModel;
import photoelectriceffectsimulator.utilities.ExpNumber;
import photoelectriceffectsimulator.utilities.MetalType;
import photoelectriceffectsimulator.controller.ControllerModelCommunicator;


/**
 * Obiekt modelu zjawiska, który analizuje dane wejściowe, przechowuje
 * stan i symuluje zjawisko na podstawie przyjętego wcześniej modelu
 * 
 * @author Jakub Sikora
 */
public class Model implements AbstractModel{
    /** Referencja do obiektu kontrolera z którym model ma się komunikować */
    private ControllerModelCommunicator controller;
    
    /** Obiekt przechowujący dane pobrane z obiektu widoku */
    private ModelState modelState;
    
    /** Obiekt wykonujący obliczenia */
    private ModelMathUnit mathUnit;
    
    /**
     * ustaw wartość długości fali
     * @param newValue długość fali
     */
    @Override
    public void setWaveLength(ExpNumber newValue){
        modelState.setWaveLength(newValue);
    }
    
    /**
     * ustaw wartość natężenia światła
     * @param newValue natężenie światła
     */
    @Override
    public void setIntensity(int newValue){
        modelState.setIntensity(newValue);
    }
    
    /**
     * ustaw wartość napięcia przyłożonego
     * @param newVoltage nowe napięcie 
     */
    @Override
    public void setVoltage(double newVoltage){
        modelState.setVoltage(newVoltage);
    }
    
    /**
     * wybierz typ metalu 
     * @param newType typ metalu
     */
    @Override
    public void setMetalType(String newType){
        modelState.setActiveMetalType(MetalType.convertFromString(newType));
    }
    
    /**
     * W momencie otrzymania wyników, przekazuje je do aktywnego kontrolera za 
     * pomocą metod z interfejsu {@link ControllerModelCommunicator}
     * @param newResult zagregowany wynik obliczeń
     */
    public void informController(ModelResult newResult){
        controller.printOutcomeExitEnergy(newResult.getExitEnergy());
        controller.printOutcomePhotonEnergy(newResult.getPhotonEnergy());
        controller.printOutcomeCurrent(newResult.getCurrent());
    }
    
    /**
     * Funkcja inicjalizująca obiekt modelu. Tworzy podrzędne obiekty stanu i 
     * obliczeń.
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
     * Ustaw referencje do obiektu kontrolera i inicjalizuje obiekt.
     * Model nie powinien zostać zainicjalizowany bez kontrolera, jego 
     * inicjalizacja następuje dopiero w momencie otrzymania referencji do 
     * kontrolera.
     * 
     * @param controller referencja do obiektu kontrolera
     */
    public void setController(ControllerModelCommunicator controller){
        this.controller = controller;
        initializeModel();
    }
}
