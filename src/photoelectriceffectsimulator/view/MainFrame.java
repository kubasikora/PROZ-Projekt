package photoelectriceffectsimulator.view;

import photoelectriceffectsimulator.controller.ControllerViewCommunicator;
import javax.swing.*;
import java.awt.*;
import javax.swing.BoxLayout;

/**
 * Klasa głównego okna aplikacji, wykorzystująca bibliotekę Swing
 * <p>
 * MainFrame dziedziczy po JFrame, klasie okna w Swingu
 * 
 * @author Jakub Sikora
 */
public class MainFrame extends JFrame { 
    /** Główny kontener komponentów*/
    private final JPanel mainPanel;
    
    /**Kontener przechowujący komponenty z górnej części interfejsu*/
    private final JPanel upperPanel;
    
    /**Kontener przechowujący komponenty z dolnej części interfejsu*/
    private final JPanel bottomPanel;
    
    /**  Kontener obsługujący animacje lampy katodowej */
    private final CathodePanel cathodePanel;
    
    /** Kontener obsługujący elementy obsługi aplikacji */
    private final ControlPanel controlPanel;
    
    /**Kontener obsługujący elementy wypisujące obliczone wyniki*/
    private final OutcomePanel outcomePanel;
    
    /**Kontener obsługujący wykres w dolnej części ekranu*/
    private final InfoPanel infoPanel;
    
    /**
     * Konstruktor parametryzowany
     * @param controller aktywny kontroller
     */
    MainFrame(ControllerViewCommunicator controller){
        initializeFrame(); //inicjalizacja okna 
        mainPanel = new JPanel();
        mainPanel.setLayout(new BoxLayout(mainPanel,BoxLayout.PAGE_AXIS));
        
        cathodePanel = new CathodePanel(new Dimension(500,500), this); //panel animacji
        controlPanel = new ControlPanel(new Dimension(300,500), controller, this); //panel suwaków
        
        //górny panel
        upperPanel = new JPanel();
        upperPanel.setLayout(new BoxLayout(upperPanel, BoxLayout.LINE_AXIS));
        upperPanel.add(cathodePanel);
        upperPanel.add(Box.createGlue());
        upperPanel.add(controlPanel);  
      
        outcomePanel = new OutcomePanel(new Dimension(500,300)); //panel wynikowy
        infoPanel = new InfoPanel(new Dimension(300,300)); //panel wykresu
        
        //dolny panel
        bottomPanel = new JPanel();
        bottomPanel.setLayout(new BoxLayout(bottomPanel, BoxLayout.LINE_AXIS));
        bottomPanel.add(outcomePanel);
        //bottomPanel.add(Box.createGlue());
        bottomPanel.add(infoPanel);
    
        mainPanel.add(upperPanel);
        //mainPanel.add(Box.createGlue());
        mainPanel.add(bottomPanel);
        
        super.add(mainPanel);
        //super.setVisible(true);
    }
    
    /** Funkcja wykonuje czynności tworzące okno główne aplikacji */
    private void initializeFrame(){
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(800,800);
        setLocationRelativeTo(null);
        setTitle("Zjawisko fotoelektryczne");      
        setResizable(false);
    }
    
    /**
     * Zwraca referencje do panelu kontrolnego z suwakami
     * @return panel kontrolny
     */
    public ControlPanel getControlPanel(){
        return controlPanel;
    }
    
    /**
     * Zwraca referencje do panelu wynikowego 
     * @return panel wynikowy
     */
    public OutcomePanel getOutcomePanel(){
        return outcomePanel;
    }
    
    /**
     * Zwraca referencję do panelu informacyjnego
     * @return panel informacyjny
     */
    public InfoPanel getInfoPanel(){
        return infoPanel;
    }
    
    /**
     * Zwraca referencję do panelu katody
     * @return panel katody
     */
    public CathodePanel getCathodePanel(){
        return cathodePanel;
    }
}
