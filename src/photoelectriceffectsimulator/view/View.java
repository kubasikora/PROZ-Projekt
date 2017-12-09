package photoelectriceffectsimulator.view;

import photoelectriceffectsimulator.controller.ControllerViewCommunicator;
import photoelectriceffectsimulator.utilities.ExpNumber;
import photoelectriceffectsimulator.utilities.MetalType;


/**
 * Obiekt widoku zajmuje się obsługą graficznego interfejsu użytkownika (GUI)
 * czyli pobieraniem od użytkownika danych,
 * oraz prezentacją obliczeń na ekranie
 * @author Jakub Sikora
 */

public class View implements AbstractView{
    /**
     * Referencja do aktywnego kontrolera
     */
    private ControllerViewCommunicator controller;
    /**
     * Referencja do głównego okna aplikacji
     */
    private MainFrame mainFrame;
    
    /**
     * Funkcja inicjalizuje główne okno aplikacji
     */
    
    //gettery i settery
    /**
     * Zwraca referencje do aktywnego kontrolera
     * @return referencja do aktywnego kontrolera 
     */
    public ControllerViewCommunicator getController(){
        return controller; 
    }
    
    /**
     * Ustaw referencje do obiektu kontrolera
     * @param controller referencja do obiektu kontrolera
     */
    public void setController(ControllerViewCommunicator controller){
        this.controller = controller;
        initializeMainFrame();
    }
    
    private void initializeMainFrame() throws NullPointerException {
        if(controller == null) throw new NullPointerException();
        mainFrame = new MainFrame(controller);
    }
    
    /////////////podpiac pod interfejs
    public void setElementType(String newType){
        mainFrame.getGraphPanel().changeElement(MetalType.getMetalInfo(MetalType.convertFromString(newType)));
    }
    
    public void setExitEnergy(double exitEnergy){
        mainFrame.getOutcomePanel().setExitEnergyDisplay(exitEnergy);
    }
    
    public void setOutcomeCurrent(ExpNumber current){
        mainFrame.getOutcomePanel().setCurrentDisplay(current);
    }
    
    public void setPhotonEnergy(double photonEnergy){
        mainFrame.getOutcomePanel().setPhotonEnergyDisplay(photonEnergy);
    }
    //////////////////
    
    /**
     * Zwraca referencje do głównego okna
     * @return referencja do głównego okna
     */
    public MainFrame getMainFrame(){
        return mainFrame;
    }
}

