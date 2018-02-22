package photoelectriceffectsimulator.view;

import photoelectriceffectsimulator.controller.ControllerViewCommunicator;
import javax.swing.*;
import java.awt.*;
import javax.swing.BorderFactory;
import photoelectriceffectsimulator.utilities.ExpNumber;

/**
 * Kontener osługujący wyświetlanie wyników na ekranie
 * @author Jakub Sikora
 */
public class OutcomePanel extends JPanel{
    
    /** Label opisujący wyświetlanie pracy wyjścia  */
    private JLabel exitEnergyDisplay;
    /** Label wyświetlający wartość pracy wyjścia  */
    private JLabel exitEnergyLabel;
    
    /** Label opisujący wyświetlanie prądu przyrządu */
    private JLabel currentDisplay;
    /** Label wyświetlający wartość prądu przyrządu */
    private JLabel currentLabel;
    /** Label wyświetlający jednostkę w których wyrazony jest prąd przyrządu*/
    private JLabel currentUnit;
    
    /** Label opisujący wyświetlanie energii fotonu */
    private JLabel photonEnergyDisplay;
    /** Label wyświetlający wartość energii fotonu */
    private JLabel photonEnergyLabel;
    
    
    /**
     * Konstruktor parametryzowany rozmiarem panelu 
     * @param dimension rozmiar panelu  
     */
    OutcomePanel(Dimension dimension){
        //tworzenie panelu
        super.setLayout(new BoxLayout(this, BoxLayout.PAGE_AXIS));
        super.setBackground(Color.LIGHT_GRAY);
        super.setMinimumSize(dimension);
        super.setPreferredSize(dimension);
        super.setMaximumSize(dimension);    
        
        //tworzenie paneli pomocniczych
        JPanel exitEnergyPanel = createExitEnergyPanel();
        JPanel currentPanel = createCurrentPanel();
        JPanel photonEnergyPanel = createPhotonEnergyPanel();
        
        //dodawanie panelu
        super.setAlignmentX(CENTER_ALIGNMENT);
        super.add(Box.createRigidArea(new Dimension(0,15)));
        super.add(exitEnergyPanel);
        super.add(Box.createRigidArea(new Dimension(0,1)));
        super.add(photonEnergyPanel);
        super.add(Box.createRigidArea(new Dimension(0,1)));
        super.add(currentPanel);
        super.add(Box.createGlue());
        
    }
    
    /**
     * Funkcja ustawia wartość pracy wyjścia wyświetlanej na ekranie
     * @param newExitEnergy nowa wartość pracy wyjścia 
     */
    public void setExitEnergyDisplay(double newExitEnergy){
        exitEnergyDisplay.setText(String.valueOf(newExitEnergy));
    }
    
    /**
     * Funkcja ustawia wartość prądu wyświetlanego na ekranie
     * @param newCurrent nowa wartość prądu
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
    
    /**
     * Funkcja ustawia odpowiednią jednostkę 
     * @param currentIndex wartość wykładnika prąy
     */
    private void validateCurrentUnit(int currentIndex){
        int index = Math.abs(currentIndex);
        if(index == 0) currentUnit.setText("A");
        if(index > 0 && index <= 3) currentUnit.setText("mA");
        if(index > 3 && index <= 6) currentUnit.setText("uA");
        if(index > 6 && index <= 9) currentUnit.setText("nA");
        if(index > 9 && index <= 12) currentUnit.setText("pA");
        if(index > 12 && index <= 15) currentUnit.setText("fA");
    }
    
    /**
     * Funkcja doprowadza otrzymany obiekt do odpowiedniej postaci aby można było
     * go wyświetlić na ekranie. 
     * @param current prąd do wyświetlenia
     * @return prąd typu double 
     */
    private double validateCurrent(ExpNumber current){
        double index = current.getIndex();
        double validCurrent = current.getBase();
        while(Math.IEEEremainder(index, 3) != 0){
            index -=1;
            validCurrent *=10;
        }
        return validCurrent;
    }
    
    /**
     * Wyświetla energię fotonu na ekranie
     * @param photonEnergy wartość energii fotonu do wyświetlenia
     */
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
    /**
     * Tworzy panel do wyświetlania energii fotonu
     * @return panel energii fotonu
     */
    private JPanel createPhotonEnergyPanel(){
        photonEnergyLabel = new JLabel("Energia fotonu: ");
        changeFontSize(photonEnergyLabel, 20);
        
        photonEnergyDisplay = new JLabel("2.481");
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
     * @return panel pracy wyjścia
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
    
    /**
     * Tworzy panel obsługujący wyświetlanie prądu
     * @return panel wyświetlający prąd
     */
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

