package photoelectriceffectsimulator.view;

import photoelectriceffectsimulator.controller.ControllerViewCommunicator;
import javax.swing.*;
import java.awt.*;
import javax.swing.BoxLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;
import java.util.Hashtable;
import photoelectriceffectsimulator.utilities.ExpNumber;
import photoelectriceffectsimulator.utilities.MetalType;

/**
 * Kontener obsługujący pobieranie od użytkownika ustawień
 * 
 * @author Jakub Sikora
 */
public class ControlPanel extends JPanel {
    /** Slider zbierający od użytkownika wartość długości fali*/
    private JSlider waveLengthSlider;
    
    /** Slider zbierający od użytkownika wartość natężenia światła*/
    private JSlider intensitySlider;
    
    /** Slider zbierający od użytkownika wartość przyłożonego napięcia
     * między okładkami*/
    private JSlider voltageSlider;
    
    /** Label wyświetlający wybraną przez użytkownika wartość długości fali*/
    private JLabel waveLengthLabel; 
    
    /** Label wyświetlający wybraną przez użytkownika wartość natężenia światła*/
    private JLabel intensityLabel; 
    
    /** Label wyświetlający wybraną przez użytkownika wartość przyłożonego 
     *  napięcia między okładkami */
    private JLabel voltageLabel;
    
    /** Panel pomocniczy do formatowania labeli przy suwaku długości światł*/
    private JPanel waveLengthLabelsPanel;
    
    /** Panel pomocniczny do formatowania labeli przy suwaku natężenia światła */
    private JPanel intensityLabelsPanel;
    
    /** Panel pomocniczy do formatowania labeli przy suwaku napięcia
     *  przyłożonego między okładkami */
    private JPanel voltageLabelsPanel;
    
    /** JComboBox służący do wybrania przez użytkownika typu metalu z którego
     *  wykonana jest katoda */
    private JComboBox<String> metalTypeComboBox;
    
    /**Referencja do aktywnego kontrolera, potrzebna do wysyłania informacji do 
     * kontrolera */
    private final ControllerViewCommunicator activeController;
    
    /**Referencja do okna w którym znajduje się panel*/
    private final MainFrame root;
    
    /**
     * Konstruktor parametryzowany
     * @param dimension rozmiar panelu  
     * @param activeController kontroller do którego mają być wysyłane zmiany 
     */
    ControlPanel(Dimension dimension, ControllerViewCommunicator activeController, MainFrame root){
        this.activeController = activeController;
        super.setLayout(new BoxLayout(this, BoxLayout.PAGE_AXIS));
        super.setMinimumSize(dimension);
        super.setPreferredSize(dimension);       
        super.setMaximumSize(dimension); 
        super.setAlignmentX(LEFT_ALIGNMENT);
        this.root = root;
        createControlPanelComponents();
    }
    
    /** Funkcja tworzy komponenty podległe panelowi*/
    private void createControlPanelComponents(){
        //inicjalizuj komponenty obsługujące długość fali
        initializeWaveLengthSlider();       
        initializeWaveLengthLabels();
        
        //inicjalizuj komponenty obsługujące natężenie światła
        initializeIntensitySlider();      
        initializeIntensityLabels();
        
        //inicjalizuj komponenty obsługujące napięcie przyłożone
        initializeVoltageSlider();
        initializeVoltageLabels();
        
        //inicjalizuj kompontenty obsługujące wybór metalu katody
        JLabel metalLabel = new JLabel("Materiał katody: ");
        metalLabel.setAlignmentX(CENTER_ALIGNMENT);
        initializeMetalTypeComboBox();
        
        //inicjalizuj logo 
        JLabel logo = new JLabel();
        logo.setAlignmentX(CENTER_ALIGNMENT);
        logo.setIcon(new ImageIcon(getClass().getResource("title.png")));     
        
        //dodaj wszystko do panelu
        add(logo);
        add(Box.createRigidArea(new Dimension(0,20)));
        add(waveLengthLabelsPanel);
        add(waveLengthSlider);
        add(Box.createRigidArea(new Dimension(0,20)));
        add(intensityLabelsPanel);
        add(intensitySlider);
        add(Box.createRigidArea(new Dimension(0,20)));
        add(voltageLabelsPanel);
        add(voltageSlider);   
        add(Box.createRigidArea(new Dimension(0,20)));
        add(metalLabel);
        add(metalTypeComboBox);
        add(Box.createGlue());
    }
    
    //inicjalizatory labelów i suwaków 
    
    /** Funkcja inicjalizuje JPanel z JLabelami opisujące suwak wyboru długości fali*/
    private void initializeWaveLengthLabels(){
        waveLengthLabelsPanel = new JPanel();
        waveLengthLabelsPanel.setLayout(new BoxLayout(waveLengthLabelsPanel, BoxLayout.LINE_AXIS));
        waveLengthLabelsPanel.setAlignmentX(CENTER_ALIGNMENT);       
        JLabel waveLengthPreffixLabel = new JLabel("Długość fali: ");
        waveLengthLabel = new JLabel(String.valueOf((int)waveLengthSlider.getValue()) + "nm");
        waveLengthLabelsPanel.add(waveLengthPreffixLabel);
        waveLengthLabelsPanel.add(waveLengthLabel);
    }
    
    /** Funkcja inicjuje slider zbierający od użytkownika wartość długości
     * fali */
    private void initializeWaveLengthSlider(){
        //zaincjalizuj wyglad slidera
        waveLengthSlider = new JSlider(JSlider.HORIZONTAL, 100, 900 , 500);
        waveLengthSlider.setAlignmentX(CENTER_ALIGNMENT);
        waveLengthSlider.setMaximumSize(new Dimension(250,100));
        waveLengthSlider.setMajorTickSpacing(200);
        waveLengthSlider.setMinorTickSpacing(50);
        waveLengthSlider.setPaintLabels(true);
        waveLengthSlider.setPaintTicks(true);   
        //dodaj changelistenera
        waveLengthSlider.addChangeListener(new ChangeListener(){
            /** Funkcja obsługująca zmiany JSlidera z wartością długości fali */
            @Override
            public void stateChanged(ChangeEvent e){
                int newValue = waveLengthSlider.getValue();
                String newText = String.valueOf(newValue) + "nm";
                getWaveLengthLabel().setText(newText);
                activeController.waveLengthChange(new ExpNumber(newValue,-9));
            }
        });
    }
    
    /** Funkcja inicjalizuje JPanel z JLabelami opisujące suwak wyboru natężenia światła */
    private void initializeIntensityLabels(){
        intensityLabelsPanel = new JPanel();
        intensityLabelsPanel.setLayout(new BoxLayout(intensityLabelsPanel, BoxLayout.LINE_AXIS));
        intensityLabelsPanel.setAlignmentX(CENTER_ALIGNMENT);
        JLabel intensityPreffixLabel = new JLabel("Natężenie światła: ");
        intensityLabel = new JLabel("0%");
        intensityLabelsPanel.add(intensityPreffixLabel);
        intensityLabelsPanel.add(intensityLabel);
    }
    
    /** Funkcja inicjuje slider zbierający od użytkownika wartość natężenia
     * światła */
    private void initializeIntensitySlider(){
        //zaincjalizuj wyglad slidera
        intensitySlider = new JSlider(JSlider.HORIZONTAL, 0 , 100 , 0);
        intensitySlider.setAlignmentX(CENTER_ALIGNMENT);
        intensitySlider.setMaximumSize(new Dimension(250,100));
        intensitySlider.setMajorTickSpacing(20);
        intensitySlider.setMinorTickSpacing(5);
        intensitySlider.setPaintLabels(true);
        intensitySlider.setPaintTicks(true);     
        //dodaj changelistenera
        intensitySlider.addChangeListener(new ChangeListener(){
            /** Funkcja obsługująca zmiany JSlidera z wartością natężenia światła */
            @Override
            public void stateChanged(ChangeEvent e){
                int newValue = intensitySlider.getValue();
                String newText = String.valueOf(newValue) + "%";
                getIntensityLabel().setText(newText);
                root.getCathodePanel().setLightIntensity(newValue);
                activeController.intensityChange(newValue);
            }
        });
    }
    
    /** Funkcja inicjalizuje JPanel z JLabelami opisujące suwak wyboru napięcia 
     * przyłożonego między okładkami */
    private void initializeVoltageLabels(){
        voltageLabelsPanel = new JPanel();
        voltageLabelsPanel.setLayout(new BoxLayout(voltageLabelsPanel, BoxLayout.LINE_AXIS));
        voltageLabelsPanel.setAlignmentX(CENTER_ALIGNMENT);
        JLabel voltagePreffixLabel = new JLabel("Napięcie przyłożone: ");
        voltageLabel = new JLabel("0.0V");
        voltageLabelsPanel.add(voltagePreffixLabel);
        voltageLabelsPanel.add(voltageLabel);
    }
    
    /** Funkcja inicjuje slider zbierający od użytkownika wartość przyłożonego
     * napięcia między okładkami */
    private void initializeVoltageSlider(){
        //zainicjalizuj wyglad slidera 
        voltageSlider = new JSlider(JSlider.HORIZONTAL, -50 , 50 , 0);
        voltageSlider.setAlignmentX(CENTER_ALIGNMENT);
        voltageSlider.setMaximumSize(new Dimension(250,100));
        voltageSlider.setMajorTickSpacing(50);
        voltageSlider.setMinorTickSpacing(10);
        voltageSlider.setPaintLabels(true);
        voltageSlider.setPaintTicks(true);
        voltageSlider.setLabelTable(createSliderDescription());
        
        //dodaj changelistenera
        voltageSlider.addChangeListener(new ChangeListener(){
            @Override
            /** Funkcja obsługująca zmiany JSlidera z wartością napięcia przyłożonego */
            public void stateChanged(ChangeEvent e){
                double newValue = voltageSlider.getValue();
                newValue /= 10; 
                String newText = String.valueOf(newValue) + "V";
                getVoltageLabel().setText(newText);
                activeController.voltageChange(newValue);
            }
        });
    }
    
    /** Funkcja inicjalizuje comboBoxa z typami dostępnych metali*/
    private void initializeMetalTypeComboBox(){
        metalTypeComboBox = new JComboBox<>();
        metalTypeComboBox.setMaximumSize(new Dimension(250,50));
        metalTypeComboBox.setAlignmentX(CENTER_ALIGNMENT);
        for (String type: MetalType.getAllMetalTypes()){
            metalTypeComboBox.addItem(type);
        }
        metalTypeComboBox.addActionListener(new ActionListener(){
            @Override
            /** Funkcja obsługująca zmiany ComboBoxa z typem metali */
            public void actionPerformed(ActionEvent ae) {
                String chosenType = (String)metalTypeComboBox.getSelectedItem();
                activeController.metalTypeChange(chosenType);
                metalTypeComboBox.hidePopup();
            }
        });
    }
    
    /**
     * Funkcja pomocnicza która tworzy mapę która łączy liczby z JLabelami do 
     * poprawnego wyskalowania JSlidera obsługującego napięcie
     * @return mapa łacząca liczby z JLabelami
     */
    private Hashtable<Integer, JLabel> createSliderDescription(){
        Hashtable<Integer, JLabel> ht = new Hashtable<>();
        for(Integer i = -50; i <= 50; i=i+50){
            Double d;
            d = (double)i/10;
            JLabel sliderLabel = new JLabel(Double.toString(d));
            ht.put(i, sliderLabel);
        }
        return ht;
    }
    
    //gettery 
    
    /**
     * Zwraca referencje do slidera zbierającego od użytkownika wartość długości 
     * fali
     * @return waveLengthSlider
     */
    public JSlider getWaveLengthSlider(){
        return waveLengthSlider;
    }
    
    /**
     * Zwraca referencje do labelu wyświetlającego aktualnie wybraną wartość 
     * długości fali
     * @return waveLengthLabel
     */
    public JLabel getWaveLengthLabel(){
        return waveLengthLabel;
    }
    
    /**
     * Zwraca referencje do slidera zbierającego od użytkownika wartość natężenia
     * światła
     * @return intensitySlider
     */
    public JSlider getIntensitySlider(){
        return intensitySlider;
    }
    
    /**
     * Zwraca referencje do labelu wyświetlającego aktualnie wybraną wartość 
     * nateżenia światła
     * @return intensityLabel
     */    
    public JLabel getIntensityLabel(){
        return intensityLabel;
    }
    
    /**
     * Zwraca referencje do slidera zbierającego od użytkownika wartość przyłożonego
     * napięcia miedzy okładkami
     * @return voltageSlider
     */    
    public JSlider getVoltageSlider(){
        return voltageSlider;
    }
    
    /**
     * Zwraca referencje do labelu wyświetlającego aktualnie wybraną wartość 
     * przyłożonego napięcia między okładkami
     * @return voltageLabel
     */    
    public JLabel getVoltageLabel(){
        return voltageLabel;
    }
    
    /**
     * Zwraca referencje do comboboxa w celu pobrania jego stanu
     * @return metalTypeComboBox
     */
    public JComboBox<String> getMetalTypeComboBox(){
        return metalTypeComboBox; 
    }
    
}
