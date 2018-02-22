package photoelectriceffectsimulator.view;

import static java.awt.Component.CENTER_ALIGNMENT;
import java.util.Random;
import javax.swing.ImageIcon;
import javax.swing.JLabel;

/**
 * Klasa reprezentująca elektron wyświetlany na ekranie. 
 * Wyświetlanie elektronu jest zrealizowane za pomocą obiektów JLabel z przypisanymi
 * ikonami 
 * @author Jakub Sikora
 */
public class Electron extends JLabel {
    /** Aktualna pozycja elektronu w poziomie */
    private int xPos;
    /** Aktualna pozycja elektronu w pionie */
    private final int yPos;
    /** Aktualna prędkość elektronu */
    private int velocity;
    /** Stała określająca początkową pozycję w płaszczyźnie poziomej*/
    private final int STARTING_POSITION = 140;
    
    /**
     * Konstruktor parametryzowany energią elektronu na podstawie której obliczana
     * jest prędkość poruszania się elektronu na ekranie.
     * Energia obliczana jest na podstawie energii wybitego elektronu i w celu 
     * lepszej prezentacji wyniku, do predkosci jest dodawana losowa wartość z przedziału
     * od 0 do 3
     * @param energy energia elektronu
     */
    Electron(double energy){
       xPos = STARTING_POSITION;
       super.setAlignmentX(CENTER_ALIGNMENT);
       super.setIcon(new ImageIcon(getClass().getResource("electron.png")));
       Random generator = new Random();
       yPos = generator.nextInt(390) + 40;
       velocity = evaluateEnergy(energy);
    }
    
    /**
     * Funkcja oblicza prędkość poruszania się elektronu na ekranie 
     * na podstawie przekazanej wartości energii.
     * Do wyniku dodawana jest losowo wartość z przedziału od 0 do 3
     * @param energy energia elektronu
     * @return prędkość poruszania się na ekranie
     */
    private int evaluateEnergy(double energy){
       int temporaryVelocity = 0;
       if(energy < 1) temporaryVelocity = 1;
       else temporaryVelocity = (int)energy;
       
       Random generator = new Random();
       temporaryVelocity+=generator.nextInt(3);
       return temporaryVelocity;
    }

    /** 
     * Zwraca aktualną pozycję w osi X elektronu
     * @return pozycja w osi X
     */
    public int getXPos(){
        return xPos;
    }
    
    /**
     * Zwraca aktualną pozycję w osi Y elektronu
     * @return pozycja w osi Y
     */
    public int getYPos(){
        return yPos;
    }
    
    /**
     * Zwraca aktualną prędkość elektronu 
     * @return prędkość elektronu
     */
    public int getVelocity(){
        return velocity;
    }
    
    /** Przemieszcza elektron o wartość prędkości w przód w osi X. */
    public void moveOneUnit(){
        xPos+=velocity;
        super.setBounds(xPos, yPos, 5, 5);
    }
}

