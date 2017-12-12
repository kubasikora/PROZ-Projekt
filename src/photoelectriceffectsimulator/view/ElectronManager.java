package photoelectriceffectsimulator.view;

import java.util.concurrent.Executors;
import java.util.concurrent.ExecutorService;


/**
 * Klasa obsługuje i zarządza poruszaniem się elektronów na ekranie
 * @author kuba
 */
public class ElectronManager{
    private final ExecutorService executor;
    int numberOfElectrons;
    
    static final int MAX_NUMBER_OF_ELECTRONS = 4;
    
    ElectronManager(){
        executor = Executors.newFixedThreadPool(MAX_NUMBER_OF_ELECTRONS);
        numberOfElectrons = 0;
    }
    
    public void setNumberOfElectrons(int electronsToDisplay){
        if(electronsToDisplay > MAX_NUMBER_OF_ELECTRONS) 
            numberOfElectrons = MAX_NUMBER_OF_ELECTRONS;
        if(electronsToDisplay < 0)
            numberOfElectrons = 0;
        else
            numberOfElectrons = electronsToDisplay;
    }

    public void addElectron(double energy){
        
    }
}
