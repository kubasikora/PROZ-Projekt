package photoelectriceffectsimulator.view;

import java.awt.Color;
import javax.swing.*;
import photoelectriceffectsimulator.utilities.MetalInfo;
import photoelectriceffectsimulator.utilities.MetalType;


/**
 * Klasa okna wyświetlającego informację o wybranym pierwiastku.
 * Oparta na wzorcu Singleton, możliwe jest uzyskanie tylko jednej instancji tej
 * klasy w programie.
 * 
 * @author Jakub Sikora
 */
public class ChildFrame extends JFrame {
    
    /** Statyczna instancja programu*/
    private static ChildFrame instance = null;
    
    /** Ostatnio wyświetlony typ metalu */
    private static MetalType lastType;
    
    /** Referencja do wyświetlanej informacji o metalu*/
    private JLabel icon;
    
    /** Statyczna inicjalizacja ostatniego typu wartością domyślną*/
    static {
        lastType = MetalType.FERRUM;
    }
    /**
     * Statyczna funkcja parametryzowana typem pierwiastka o którym informacje mają
     * zostać wyświetlone, zwraca jedyną dozwoloną instancje okna.
     * Jeśli nie została jeszcze utworzona instancji z danym typem, funkcja zabija
     * stare okno i tworzy nowe. Gdy użytkownik chce otworzyć ponownie okno z 
     * informacjami o tym samym pierwiastku, zostanie mu zwrócone to samo okno.
     * @param type typ pierwiastka o którym informacje należy wyświetlić
     * @return referencja do okna z informacją o pierwiastku
     */
    public static ChildFrame getInstance(MetalType type){
        if(instance == null ) instance = new ChildFrame(type);
        else{
            if(lastType != type){ 
                instance.dispose();
                instance = new ChildFrame(type);
                lastType = type;
            }
        }
        return instance;
    }
    /**
     * Konstruktor z ograniczonym dostępem, możliwy do wywołania tylko z 
     * statycznej funkcji
     * @param type typ pierwiastka którego informacje mają zostać wyświetlone na
     * ekranie
     */
    protected ChildFrame(MetalType type){
        super.setFocusable(true);
        
        JPanel contentPane = new JPanel();
        contentPane.setBackground(Color.WHITE);
        super.setResizable(false);
        
        MetalInfo info = MetalType.getMetalInfo(type);
        super.setTitle(info.getElementName());
        initializeChildComponents(type);
        
        contentPane.add(icon);
        super.add(contentPane);
        
        super.setSize(icon.getIcon().getIconWidth() + 10, icon.getIcon().getIconHeight() + 40);
        super.setLocationRelativeTo(null);
        
        super.setVisible(true);
    }
    
    /**
     * Inicjalizuje infografikę z informacjami o pierwiastku
     * @param type 
     */
    private void initializeChildComponents(MetalType type){
        String path = MetalType.getMetalIconPath(type);
        icon = new JLabel();
        try{
            icon.setIcon(new ImageIcon(getClass().getResource(path)));
        }
        catch(Exception e){
            System.exit(0);
        } 
    }
}