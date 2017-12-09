package photoelectriceffectsimulator.model;

import photoelectriceffectsimulator.utilities.ExpNumber;

/**
 * Klasa przechowująca wyniki obliczone przez model, które zostają przesłane do 
 * kontrolera
 * @author kuba
 */

public class ModelResult {
    private final ExpNumber current;
    
    private final double photonEnergy;
    
    private final double exitEnergy;
    
    ModelResult(ExpNumber current, double photonEnergy, double exitEnergy){
        this.current = current;
        this.photonEnergy = photonEnergy;
        this.exitEnergy = exitEnergy;
        
    }
    
    public ExpNumber getCurrent(){
        return current;
    }
    
    public double getPhotonEnergy(){
        return photonEnergy;
    }
    
    public double getExitEnergy(){
        return exitEnergy;
    }
    
}