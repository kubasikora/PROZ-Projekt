package photoelectriceffectsimulator.view;

import javax.swing.*;
import java.awt.*;

/**
 * Kontener obsługujący wyświetlaną animacje na ekranie 
 * 
 * @author Jakub Sikora
 */
public class CathodePanel extends JPanel {
    
    /**
     * Konstruktor panelu grafiki z fotokomórką
     * @param dimension rozmiar panelu  
     */
    CathodePanel(Dimension dimension){
        super.setBackground(Color.YELLOW);
        super.setMinimumSize(dimension);
        super.setPreferredSize(dimension);
        super.setMaximumSize(dimension);
    }
}
