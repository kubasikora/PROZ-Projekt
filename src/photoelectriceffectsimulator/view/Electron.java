package photoelectriceffectsimulator.view;

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.Shape;
import java.awt.geom.Ellipse2D;
import java.lang.reflect.InvocationTargetException;
import java.util.Random;
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
        Graphics2D graph2d = (Graphics2D)root.getGraphics();
        Color background = root.getBackground();
        while(true){
            try {
                Shape body = new Ellipse2D.Float(xPos, yPos, 10,10);
                SwingUtilities.invokeAndWait(new Runnable(){
                    public void run(){
                        graph2d.setColor(background);
                        graph2d.draw(body);
                        graph2d.fill(body);  
                        parent.repaint(xPos, yPos, 15,15);
                    }});
                
                xPos+=10;
                Shape newBody = new Ellipse2D.Float(xPos, yPos, 10,10);
                SwingUtilities.invokeAndWait(new Runnable(){
                    @Override
                    public void run(){
                        graph2d.setColor(Color.BLUE);
                        graph2d.draw(newBody);
                        graph2d.fill(newBody);
                        root.repaint(xPos, yPos, 15,15);
                    }
                });

                if(xPos > 340) {
                    SwingUtilities.invokeAndWait(new Runnable(){
                        @Override
                        public void run(){
                            graph2d.setColor(background);
                            graph2d.draw(newBody);
                            graph2d.fill(newBody); 
                        }});
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
