package photoelectriceffectsimulator.view;

import java.awt.Color;
import static java.awt.Component.CENTER_ALIGNMENT;
import java.awt.Graphics2D;
import java.awt.Shape;
import java.awt.geom.Ellipse2D;
import java.lang.reflect.InvocationTargetException;
import java.util.Random;
import javax.swing.ImageIcon;
import javax.swing.JComponent;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingUtilities;

/**
 *
 * @author kuba
 */
public class Electron implements Runnable{

    private final JPanel root;
    
    private final CathodePanel parent;
    
    private int xPos;
    
    private final int yPos;
    
    private final int velocity;
    
    
    Electron(JPanel panel, CathodePanel parent){
       root = panel;
       xPos = 140;
       Random generator = new Random();
       yPos = generator.nextInt(240) + 130;
       velocity = 1;
       this.parent = parent;
    }
    
    @Override
    public void run() {
        JLabel el = new JLabel();
        el.setAlignmentX(CENTER_ALIGNMENT);
        el.setIcon(new ImageIcon(getClass().getResource("electron.gif")));
        el.setBounds(xPos, yPos, 5, 5);
        parent.add(el);
        
        while(true){
            try {
                SwingUtilities.invokeAndWait(() -> {
                    parent.remove(el);
                    parent.revalidate();
                    parent.repaint();
                });
                 
                xPos+=5;
                
                SwingUtilities.invokeAndWait(() -> {
                    el.setBounds(xPos, yPos, 5, 5);
                    parent.add(el);
                    parent.revalidate();
                    parent.repaint();
                });

                
                
                if(xPos > 340) {
                    SwingUtilities.invokeAndWait(() -> {
                        parent.remove(el);
                        parent.revalidate();
                        parent.repaint();
                    });
                    break;
                }

                try {
                    Thread.sleep(50);
                } catch (InterruptedException ex) {

                }
            } 
            catch (InterruptedException|InvocationTargetException ex) {} 
        }
    }
    
}
