package photoelectriceffectsimulator.view;

import javax.swing.*;
import java.awt.*;
import javax.swing.BoxLayout;
import javax.swing.BorderFactory;
import java.awt.Font;
import photoelectriceffectsimulator.utilities.MetalInfo;

/**
 * Kontener obsługujący rysowanie wykresu na ekranie
 * @author kuba
 */
public class GraphPanel extends JPanel{
    
    private JLabel atomicNumberLabel;
    private JLabel elementShortcutLabel;
    private JLabel elementNameLabel;
    private JLabel atomicMassLabel;
    private JPanel elementPanel;
    
    /**
     * Konstruktor 
     * @param dimension rozmiar panelu  
     */
    GraphPanel(Dimension dimension){
        setLayout(new BoxLayout(this,BoxLayout.PAGE_AXIS));
        setBackground(Color.LIGHT_GRAY);
        setMinimumSize(dimension);
        setPreferredSize(dimension);
        setMaximumSize(dimension);
        setAlignmentX(CENTER_ALIGNMENT);
        setAlignmentY(CENTER_ALIGNMENT);
        createElementPanel();
        elementPanel = createElementPanel();
        add(Box.createRigidArea(new Dimension(0,30)));
        add(elementPanel);
    }
    
    
    private JPanel createElementPanel(){
        JPanel elemPanel = new JPanel();
        elemPanel.setMinimumSize(new Dimension(170,200));
        elemPanel.setPreferredSize(new Dimension(170,200));
        elemPanel.setMaximumSize(new Dimension(170,200));
        elemPanel.setLayout(new BoxLayout(elemPanel, BoxLayout.PAGE_AXIS));
        elemPanel.setAlignmentX(CENTER_ALIGNMENT);
        elemPanel.setAlignmentY(CENTER_ALIGNMENT);
        elemPanel.setBackground(new Color(102,255,102));
        
        atomicNumberLabel = new JLabel("26");
        elementShortcutLabel = new JLabel("Fe");
        elementNameLabel = new JLabel("Żelazo");
        atomicMassLabel = new JLabel("55.845");
        
        changeFontSize(atomicNumberLabel, 3);
        changeFontSize(elementShortcutLabel, 6);
        changeFontSize(elementNameLabel, 2);
        changeFontSize(atomicMassLabel, 1);
        
        
        elemPanel.add(atomicNumberLabel);
        elemPanel.add(elementShortcutLabel);
        elemPanel.add(elementNameLabel);
        elemPanel.add(atomicMassLabel);
        elemPanel.setBorder(BorderFactory.createLineBorder(Color.BLACK, 5));
        return elemPanel;
    }
    
    private void changeFontSize(JLabel label, int resizer){
        Font font;
        font = label.getFont();
        label.setFont(new Font(font.getFontName(), font.getStyle(), resizer*font.getSize()));
    }
 
    public void changeElement(MetalInfo metalInfo){
        this.elementPanel.setBackground(metalInfo.getColor());
        this.atomicNumberLabel.setText(String.valueOf(metalInfo.getAtomicNumber()));
        this.elementShortcutLabel.setText(metalInfo.getElementShortcut());
        this.elementNameLabel.setText(metalInfo.getElementName());
        this.atomicMassLabel.setText(String.valueOf(metalInfo.getAtomicMass()));
    }
}
