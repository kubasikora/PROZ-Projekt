package photoelectriceffectsimulator.view;


import java.util.ArrayList;
import javax.swing.JPanel;
import javax.swing.SwingUtilities;
import photoelectriceffectsimulator.utilities.ExpNumber;

/**
 * Klasa obsługuje i zarządza poruszaniem się elektronów na ekranie
 * @author kuba
 */
public class ElectronManager implements Runnable{
    /** Stała określająca maksymalną liczbę elektronów wyświetlanych na ekranie */
    public static final int MAX_NUMBER_OF_ELECTRONS = 100;
    
    /** 
     * Zadana liczba elektronów która powinna znajdować się na ekranie.
     * Obliczana jest na podstawie prądu przyrządu
     */
    private int numberOfElectrons;
    
    /** Referencja do listy wszystkich obsługiwanych elektronów */
    private final ArrayList<Electron> electrons;
    
    /** Aktualna wartość energii wybijanych elektronów */
    private double electronEnergy;
    
    /** Aktualna wartość prądu przyrządu */
    private ExpNumber current;
    
    /** Liczba aktualnie narysowanych elektronów*/
    private int activeElectrons;
    
    /** Referencja do panelu po którym mają być odrysowywane elektrony */
    private final JPanel root;
    
    /**
     * Konstruktor parametryzowany referencją do panelu po którym manager ma 
     * odrysowywać elektrony. 
     * Nadaje domyślne wartości wszystkim parametrom managera.
     * @param root referencja do panelu głównego
     */
    ElectronManager(JPanel root){
        electrons = new ArrayList<>(MAX_NUMBER_OF_ELECTRONS);
        numberOfElectrons = 0;
        activeElectrons = 0;
        electronEnergy = 0;
        this.root = root;
    }
    
    /**
     * Funkcja obsługuje zmianę prądu przyrządu
     * Informacje o nowym prądzie przekazuje do obliczenia liczby elektronów na 
     * ekranie
     * @param newCurrent nowa wartość prądu
     */
    public void changeCurrent(ExpNumber newCurrent){
        current = newCurrent;
        this.setNumberOfElectrons();
    }
    
    /**
     * Funkcja oblicza ile elektronów powinno zostać wyświetlonych na podstawie 
     * aktualnej wartości prądu płynącego przez przyrząd
     */
    private void setNumberOfElectrons(){
        int index = current.getIndex();
        if(index == 0){
            this.numberOfElectrons = 0;
            return;
        }
        if(View.isBetween(-2, 0, index)) this.numberOfElectrons = (int)(ElectronManager.MAX_NUMBER_OF_ELECTRONS);
        if(View.isBetween(-3, -2, index)) this.numberOfElectrons = (int)(0.8*ElectronManager.MAX_NUMBER_OF_ELECTRONS);
        if(View.isBetween(-6, -3, index)) this.numberOfElectrons = (int)(0.5*ElectronManager.MAX_NUMBER_OF_ELECTRONS);
        if(View.isBetween(-9, -6, index)) this.numberOfElectrons = (int)(0.3*ElectronManager.MAX_NUMBER_OF_ELECTRONS);
        if(View.isBetween(-12, -9, index)) this.numberOfElectrons = (int)(0.1*ElectronManager.MAX_NUMBER_OF_ELECTRONS);
        if(View.isBetween(-15, -12, index)) this.numberOfElectrons = (int)(0.01*ElectronManager.MAX_NUMBER_OF_ELECTRONS);  
    }
    
    /**
     * Funkcja inkrementuje liczbę wyświetlanych elektronów w managerze.
     */
    private void addElectron(){
        Electron newElectron = new Electron(electronEnergy);
        electrons.add(newElectron);
        root.add(newElectron);
    } 
    
    /**
     * Funkcja ustawia nową wartość energii elektronu na podstawie której obliczana
     * jest prędkość elektronów
     * @param newEnergy nowa wartość energii
     */
    public void setElectronEnergy(double newEnergy){
        electronEnergy = newEnergy;
    }
    
    /**
     * Główna metoda wątku, obsługuje poruszanie się elektronów po ekranie
     */
    @Override
    public void run()
    {   
        ArrayList<Electron> toRemove = new ArrayList<>();
        while(true){
            //dodaj elektron jak trzeba
            if(activeElectrons < numberOfElectrons){
                addElectron();
                activeElectrons++;
            }
            
            //przesun wszystkie i zaznacz elektrony do usuniecia
            for(Electron e: electrons){
                e.moveOneUnit();
                if(e.getXPos() > 360)
                {
                    toRemove.add(e);
                    activeElectrons--;
                }
            }
            
            //usun elektrony do usuniecia
            for(Electron e: toRemove){
                electrons.remove(e);
            }
                
            try {
            //rysuj elektrony
                SwingUtilities.invokeAndWait(() -> {
                    synchronized(toRemove){
                        for(Electron e: toRemove){
                            root.remove(e);
                        }
                    }
                    root.revalidate();
                    root.repaint();
                });
            } catch (Exception ex) {} 
            
            //odczekaj jednostkę czasu
            try {
                Thread.sleep(10);
            } 
            catch (InterruptedException ex){
                toRemove.clear(); //usuń listę elektronów do usunięcia
            }
       }
    }
}