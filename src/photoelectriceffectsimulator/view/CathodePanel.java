package photoelectriceffectsimulator.view;

import javax.swing.*;
import java.awt.*;
import java.awt.geom.*;

/**
 * Kontener obsługujący wyświetlaną animacje na ekranie 
 * 
 * @author Jakub Sikora
 */
public class CathodePanel extends JPanel {
    /** Przechowuje informację o aktualnej wartości natężenia światła */
    private int lightIntensity;
    
    /** Przechowuje informację o aktualnej wartości długości światła*/
    private int waveLength;
    
    /**
     * Konstruktor panelu grafiki z fotokomórką
     * @param dimension rozmiar panelu  
     */
    CathodePanel(Dimension dimension){
        lightIntensity = 0;
        waveLength = 500;
        
        super.setMinimumSize(dimension);
        super.setPreferredSize(dimension);
        super.setMaximumSize(dimension);
    }
    
    /**
     * Nadpisana metoda paintComponent tak aby nna ekranie znajdowała się fotokatoda
     * @param g obiekt grafiki
     */
    @Override
    protected void paintComponent(Graphics g){
        super.paintComponent(g);
        Graphics2D graph2d = (Graphics2D) g;
        graph2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING, 
                                 RenderingHints.VALUE_ANTIALIAS_ON);
        
        drawPhotodevice(graph2d);
        drawLight(graph2d, Color.RED, this.evaluateIntensity());
    }
    
    /**
     * Funkcja rysuje na ekranie fotoprzyrząd
     * @param graph2d obiekt grafiki komponentu który ma narysować przyrząd
     */
    private void drawPhotodevice(Graphics2D graph2d){
        graph2d.setStroke(new BasicStroke(10));
        
        //katoda
        Shape cathode = new Line2D.Float(130,40,130,430);
        graph2d.draw(cathode);

        //anoda 
        Shape anode = new Line2D.Float(370,40,370,430);
        graph2d.draw(anode);

        //kabel katody 
        Shape cathodeWire = new Line2D.Float(15,230,130,230);
        graph2d.draw(cathodeWire);

        //kabel anody
        Shape anodeWire = new Line2D.Float(370,230,480,230);
        graph2d.draw(anodeWire);
    }
    
    /**
     * Funkcja rysuje snop światła o odpowiednim kolorze i jasności
     * @param graph2d obiekt grafiki który rysuje komponent
     * @param color kolor snopu światła
     * @param intensity jasność snopu światła
     */
    private void drawLight(Graphics2D graph2d, Color color, float intensity){
        graph2d.setStroke(new BasicStroke(0));
        int xPoints[] = {135,135,270};
        int yPoints[] = {37,435,5};
        Polygon lightBeam = new Polygon(xPoints, yPoints, 3);
        graph2d.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_OVER, intensity));
        graph2d.draw(lightBeam);
        graph2d.setColor(color);
        graph2d.fill(lightBeam);
        graph2d.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_OVER, 1.0F));
    }
    
    /**
     * Funkcja obsługuje zmianę jasności przez użytkownika
     * @param newIntensity nowa jasność
     */
    public void setLightIntensity(int newIntensity){
        lightIntensity = newIntensity;
        this.repaint();
    }
    
    /**
     * Funkcja zwraca odpowiednią wartość jasności
     * @return odpowiednio przeskalowana jasność 
     */
    private float evaluateIntensity(){
        return ((float)lightIntensity)/100;
    }

    
}
