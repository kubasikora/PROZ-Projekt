package photoelectriceffectsimulator.view;

import photoelectriceffectsimulator.controller.ControllerViewCommunicator;
import javax.swing.*;
import java.awt.*;
import javax.swing.BorderFactory;
import photoelectriceffectsimulator.utilities.ExpNumber;

/**
 * Kontener osługujący wyniki wyświetlane na ekranie
 * @author kuba
 */
public class OutcomePanel extends JPanel{
    
    private JLabel exitEnergyDisplay;
    private JLabel exitEnergyLabel;
    
    private JLabel currentDisplay;
    private JLabel currentLabel;
    private JLabel currentUnit;
    
    private JLabel photonEnergyDisplay;
    private JLabel photonEnergyLabel;
    
    private final ControllerViewCommunicator controller;
    
    /**
     * Konstruktor 
     * @param dimension rozmiar panelu  
     */
    OutcomePanel(Dimension dimension, ControllerViewCommunicator controller){
        this.controller = controller;
        setLayout(new BoxLayout(this, BoxLayout.PAGE_AXIS));
        setBackground(Color.LIGHT_GRAY);
        setMinimumSize(dimension);
        setPreferredSize(dimension);
        setMaximumSize(dimension);     
        
        JPanel exitEnergyPanel = createExitEnergyPanel();
        JPanel currentPanel = createCurrentPanel();
        JPanel photonEnergyPanel = createPhotonEnergyPanel();
        setAlignmentX(CENTER_ALIGNMENT);
        add(Box.createRigidArea(new Dimension(0,15)));
        add(exitEnergyPanel);
        add(Box.createRigidArea(new Dimension(0,1)));
        add(photonEnergyPanel);
        add(Box.createRigidArea(new Dimension(0,1)));
        add(currentPanel);
        add(Box.createGlue());
        
    }
    
    /**
     * Funkcja ustawia wartość pracy wyjścia wyświetlanej na ekranie
     * @param newExitEnergy 
     */
    public void setExitEnergyDisplay(double newExitEnergy){
        exitEnergyDisplay.setText(String.valueOf(newExitEnergy));
    }
    
    /**
     * Funkcja ustawia wartość pracy wyjścia wyświetlanej na ekranie
     * @param newCurrent 
     */
    public void setCurrentDisplay(ExpNumber newCurrent){
        validateCurrentUnit(newCurrent.getIndex());
        double validCurrent = validateCurrent(newCurrent);
        String current = Double.toString(validCurrent);
        char[] temp = current.toCharArray();
        if(temp.length > 5){
            String toDisplay = String.copyValueOf(temp, 0, 5);
            currentDisplay.setText(toDisplay);
        }
        else 
            currentDisplay.setText(current);
    }
    
    private void validateCurrentUnit(int currentIndex){
        int index = Math.abs(currentIndex);
        if(index == 0) currentUnit.setText("A");
        if(index > 0 && index <= 3) currentUnit.setText("mA");
        if(index > 3 && index <= 6) currentUnit.setText("uA");
        if(index > 6 && index <= 9) currentUnit.setText("nA");
        if(index > 9 && index <= 12) currentUnit.setText("pA");
        if(index > 12 && index <= 15) currentUnit.setText("fA");

    }
    
    private double validateCurrent(ExpNumber current){
        double index = current.getIndex();
        double nonValidCurrent = current.getBase();
        while(Math.IEEEremainder(index, 3) != 0){
            index -=1;
            nonValidCurrent *=10;
        }
        return nonValidCurrent;
    }
    
    public void setPhotonEnergyDisplay(double photonEnergy){
        String energy = String.valueOf(photonEnergy);
        char[] temp = energy.toCharArray();
        if(temp.length > 5){
            String toDisplay = String.copyValueOf(temp, 0, 5);
            photonEnergyDisplay.setText(toDisplay);
        }
        else 
            photonEnergyDisplay.setText(energy);
    }
    
    private JPanel createPhotonEnergyPanel(){
        photonEnergyLabel = new JLabel("Energia fotonu: ");
        changeFontSize(photonEnergyLabel, 20);
        
        photonEnergyDisplay = new JLabel("2.067");
        changeFontSize(photonEnergyDisplay, 20);
        
        JLabel photonEnergyUnit = new JLabel("eV");
        changeFontSize(photonEnergyUnit, 20);
        
        JPanel photonEnergyPanel = new JPanel();
        photonEnergyPanel.setAlignmentX(LEFT_ALIGNMENT);
        photonEnergyPanel.setBackground(Color.WHITE);
        photonEnergyPanel.setLayout(new BoxLayout(photonEnergyPanel, BoxLayout.LINE_AXIS));
        photonEnergyPanel.setBorder(BorderFactory.createEtchedBorder());
        photonEnergyPanel.add(Box.createRigidArea(new Dimension(50, 0)));
        photonEnergyPanel.add(photonEnergyLabel);
        photonEnergyPanel.add(photonEnergyDisplay);
        photonEnergyPanel.add(photonEnergyUnit);
        photonEnergyPanel.add(Box.createGlue());
        
        return photonEnergyPanel;
    }
    
    /**
     * Funkcja inicjalizuje panel z etykietami obsługującymi wyświetlanie 
     * pracy wyjścia
     * @return 
     */
    private JPanel createExitEnergyPanel(){
        exitEnergyLabel = new JLabel("Praca wyjścia katody: ");
        changeFontSize(exitEnergyLabel, 20);
        
        exitEnergyDisplay = new JLabel("4.67");
        changeFontSize(exitEnergyDisplay, 20);
        
        JLabel exitEnergyUnit = new JLabel("eV");
        changeFontSize(exitEnergyUnit, 20);
        
        JPanel exitEnergyPanel = new JPanel();
        exitEnergyPanel.setAlignmentX(LEFT_ALIGNMENT);
        exitEnergyPanel.setBackground(Color.WHITE);
        exitEnergyPanel.setLayout(new BoxLayout(exitEnergyPanel, BoxLayout.LINE_AXIS));
        exitEnergyPanel.setBorder(BorderFactory.createEtchedBorder());
        exitEnergyPanel.add(Box.createRigidArea(new Dimension(50, 0)));
        exitEnergyPanel.add(exitEnergyLabel);
        exitEnergyPanel.add(exitEnergyDisplay);
        exitEnergyPanel.add(exitEnergyUnit);
        exitEnergyPanel.add(Box.createGlue());
        
        return exitEnergyPanel;
    }
    
    private JPanel createCurrentPanel(){
        currentLabel = new JLabel("Prąd płynący przez przyrząd: ");
        changeFontSize(currentLabel, 20);
        
        currentDisplay = new JLabel("0.0");
        changeFontSize(currentDisplay, 20);
        
        currentUnit = new JLabel("A");
        changeFontSize(currentUnit, 20);
        
        JPanel currentPanel = new JPanel();
        currentPanel.setAlignmentX(LEFT_ALIGNMENT);
        currentPanel.setBackground(Color.WHITE);
        currentPanel.setLayout(new BoxLayout(currentPanel, BoxLayout.LINE_AXIS));
        currentPanel.setBorder(BorderFactory.createEtchedBorder());
        currentPanel.add(Box.createRigidArea(new Dimension(50,0)));
        currentPanel.add(currentLabel);
        currentPanel.add(currentDisplay);
        currentPanel.add(currentUnit);
        currentPanel.add(Box.createGlue());
        return currentPanel;
    }
    
    /**
     * Funkcja pomocnicza służy do zmiany rozmiaru czcionki JLabelu
     * @param label JLabel którego rozmiar czcionki ma zostać zmieniony
     * @param size docelowy rozmiar
     */
    private void changeFontSize(JLabel label, int size){
        Font font = label.getFont();
        label.setFont(font.deriveFont(Font.PLAIN, size));
    }
}

