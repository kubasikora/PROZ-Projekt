package photoelectriceffectsimulator.model;

import photoelectriceffectsimulator.utilities.ExpNumber;

/**
 * Klasa przechowująca wyniki obliczone przez model, które zostają przesłane do 
 * kontrolera
 * @author kuba
 */
public class ModelResult {
    private final ExpNumber current;
    
    private final double maxKineticEnergy;
    
    ModelResult(ExpNumber current, double maxKineticEnergy){
        this.current = current;
        this.maxKineticEnergy = maxKineticEnergy;
    }
    
    public ExpNumber getCurrent(){
        return current;
    }
    
    public double getMaxKineticEnergy(){
        return maxKineticEnergy;
    }
    
}
