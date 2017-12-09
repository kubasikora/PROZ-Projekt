package photoelectriceffectsimulator.model;

import java.util.Observable;
import java.util.Observer;
import photoelectriceffectsimulator.utilities.ExpNumber;

/**
 * Obiekt tej klasy wykonuje obliczenia na podstawie wyników znajdujących się 
 * w obiekcie typu {@link ModelState} i jest on przez ten obiekt informowany że zmieniły
 * się dane i należy obliczenia ponowić.
 * Implementuje interfejs obserwatora, gdy zmieni się obiekt obserwowany ponownie
 * oblicza wartości prądu przyrządu.
 * 
 * @author Jakub Sikora
 */
public class ModelMathUnit implements Observer {

    /** Referencja do modelu nadrzędnego*/
    private final Model activeModel;
    
    /** Stała efektywności kwantowej - określa stosunek prądu nasycenia do natężenia
        oświetlenia podana w A/lm */
    private final ExpNumber QUANTUM_EFF = new ExpNumber(5,-4); 
    
    /** Stała plancka - określa zjawiska kwantowe podana w eV*s*/
    private final ExpNumber PLANCK_CONST = new ExpNumber(4.1356, -15);
    
    /** Stała prędkości światła podana w m/s*/
    private final ExpNumber LIGHT_SPEED = new ExpNumber(3,8);
    
    /** bStały ładunek elementarny podany w C*/
    private final ExpNumber ELECTRON_CHARGE = new ExpNumber(-1.602, -19); 
    
    /**
     * Konstruktor parametryzowany obiektem nadrzędnym
     * @param activeModel model nadrzędny
     */
    ModelMathUnit(Model activeModel){
        this.activeModel = activeModel;
    }
    
    /**
     * Funkcja wywoływana w momencie gdy zmieni się stan obiektu.
     * Oblicza nowe wartości na podstawie zaktualizowanego stanu modelu.
     * Wynik przedstawia w postaci obiektu {@link ModelResult}.
     * @param o obiekt zmieniony 
     * @param obj argument przekazany przez obiekt obserwowany 
     */
    @Override
    public void update(Observable o, Object obj) { 
        ModelState changed = (ModelState)o;
        double exitEnergy = changed.getActiveMetalType().getExitEnergy();
        double photonEnergy = evaluatePhotonEnergy(changed);
        ExpNumber current;
        
        double maxKinEnergy = evaluateMaxKineticEnergy(changed);
        if(maxKinEnergy > 0){
            ExpNumber saturationCurrent = this.evaluateSaturationCurrent(changed);
            current = this.evaluateCurrent(saturationCurrent, changed.getVoltage(), maxKinEnergy);
        }
        else {
            current = new ExpNumber(0,0);
        }
        
        activeModel.informController(new ModelResult(current, photonEnergy, exitEnergy));
        
        //funkcja do debugowania 
        printChangedState(changed);
    }
    
    /**
     * Funkcja oblicza prąd nasycenia na podstawie przekazanych danych
     * @param state dane modelu 
     * @return prąd saturacji (nasycenia)
     */
    private ExpNumber evaluateSaturationCurrent(ModelState state){
        ExpNumber expIntensity = new ExpNumber(state.getIntensity(),0);
        return ExpNumber.multiply(QUANTUM_EFF, expIntensity);
    }
    
    /**
     * Funkcja oblicza prąd płynący przez fotoprzyrząd
     * @param electronEnergy maksymalna energia elektronu
     * @param saturationCurrent prąd nasycenia
     * @return  
     */
    private ExpNumber evaluateCurrent(ExpNumber saturationCurrent, double voltage, double kineticEnergy){
        double satCurrent = ExpNumber.getDouble(saturationCurrent);
        double result;
        result = satCurrent*(0.5*(Math.tanh(1.3*(voltage + kineticEnergy)) + 1));
        return new ExpNumber(result,0);
    }
    
    /**
     * Funkcja oblicza maksymalną energię kinetyczną wybitych elektronów
     * na podstawie przekazanych danych`
     * @param state dane modelu
     * @return maksymalna energia kinetyczna wybitego elektronu w elektronowoltach
     */
    private double evaluateMaxKineticEnergy(ModelState state){
        if(state.getIntensity() == 0) return 0.0;
        double photonEnergy = evaluatePhotonEnergy(state);
        double result = photonEnergy - state.getActiveMetalType().getExitEnergy();
        System.out.println("Energia elektronu: " + result);
        if (result > 0) return result;
        else return  0.0;
    }
    
    /**
     * Funkcja oblicza energie fotonu padającego na katodę
     * @param state stan modelu
     * @return energia fotonu podana w elektronowoltach
     */
    private double evaluatePhotonEnergy(ModelState state){
        ExpNumber temp = ExpNumber.multiply(PLANCK_CONST, LIGHT_SPEED);
        return ExpNumber.getDouble(ExpNumber.divide(temp, state.getWaveLength()));
        
    }
    
    /**
     * Funkcja do debugowania, wyświetla aktualny stan obiektu
     * @param changed stan modelu który ma zostać wyświetlony 
     */
    private void printChangedState(ModelState changed){
       System.out.println("Pobrane z gui: ");
       System.out.println(changed.getWaveLength().getBase() + "e" + changed.getWaveLength().getIndex());
       System.out.println(changed.getIntensity());
       System.out.println(changed.getVoltage());
       System.out.println(changed.getActiveMetalType().getExitEnergy());
    }
    
}