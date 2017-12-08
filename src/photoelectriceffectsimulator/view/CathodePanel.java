package photoelectriceffectsimulator.view;

import javax.swing.*;
import java.awt.*;
import javax.swing.BoxLayout;

/**
 * Kontener obsługujący wyświetlaną animacje na ekranie 
 * @author kuba
 */
public class CathodePanel extends JPanel {
    
    /**
     * Konstruktor panelu grafiki z fotokomórką
     * @param dimension rozmiar panelu  
     */
    CathodePanel(Dimension dimension){
        setBackground(Color.YELLOW);
        setMinimumSize(dimension);
        setPreferredSize(dimension);
        setMaximumSize(dimension);
    }
}
