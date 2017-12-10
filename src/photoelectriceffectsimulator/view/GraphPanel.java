package photoelectriceffectsimulator.view;

import javax.swing.*;
import java.awt.*;
import javax.swing.BoxLayout;
import javax.swing.BorderFactory;
import java.awt.Font;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.Toolkit;
import photoelectriceffectsimulator.utilities.MetalInfo;

/**
 * Kontener obsługujący wyświetlanie informacji o metalu z którego jest zrobiony przyrząd
 * 
 * @author Jakub Sikora
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
        super.setLayout(new BoxLayout(this,BoxLayout.PAGE_AXIS));
        setBackground(Color.LIGHT_GRAY);
        super.setMinimumSize(dimension);
        super.setPreferredSize(dimension);
        super.setMaximumSize(dimension);
        super.setAlignmentX(CENTER_ALIGNMENT);
        super.setAlignmentY(CENTER_ALIGNMENT);
        createElementPanel();
        elementPanel = createElementPanel();
        super.add(Box.createRigidArea(new Dimension(0,30)));
        super.add(elementPanel);
    }
    
    /**
     * Funkcja inicjalizuje panel wyświetlający informację o pierwiastku
     * @return panel wyświetlający informację o pierwiastku
     */
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
        
        elemPanel.addMouseListener(new MouseListener(){
            @Override
            public void mouseClicked(MouseEvent e) {
                Toolkit tk = Toolkit.getDefaultToolkit();
                tk.beep();
                JOptionPane.showMessageDialog(elementPanel, 
                                              elementNameLabel.getText(), 
                                              "Materiał fotokatody", 
                                              HEIGHT);
             }

            @Override
            public void mousePressed(MouseEvent e){}

            @Override
            public void mouseReleased(MouseEvent e){}

            @Override
            public void mouseEntered(MouseEvent e){}

            @Override
            public void mouseExited(MouseEvent e){}
        });
        
        return elemPanel;
    }
    
    /** 
     * Funkcja pomocnicza zmienia rozmiar czcionki w JLabelu
     * @param label label którego czcionka ma zostać zmieniona
     * @param resizer wartość wielkości jaka ma zostać ustawiona
     */
    private void changeFontSize(JLabel label, int resizer){
        Font font;
        font = label.getFont();
        label.setFont(new Font(font.getFontName(), font.getStyle(), resizer*font.getSize()));
    }
 
    /**
     * Funkcja zmienia informacje które są wyświetlane na ekranie
     * @param metalInfo obiekt agregujący informację które mają być wyświetlane 
     */
    public void changeElement(MetalInfo metalInfo){
        this.elementPanel.setBackground(metalInfo.getColor());
        this.atomicNumberLabel.setText(String.valueOf(metalInfo.getAtomicNumber()));
        this.elementShortcutLabel.setText(metalInfo.getElementShortcut());
        this.elementNameLabel.setText(metalInfo.getElementName());
        this.atomicMassLabel.setText(String.valueOf(metalInfo.getAtomicMass()));
    }
}
