package photoelectriceffectsimulator.utilities;
import java.awt.Color;

/**
 *
 * @author kuba
 */
public class MetalInfo {
    private final int atomicNumber;
    private final String elementShortcut;
    private final String elementName;
    private final double atomicMass;
    private final Color color;
    
    MetalInfo(int atomicNumber, String elementShortcut, String elementName, double atomicMass, Color color){
        this.atomicNumber = atomicNumber;
        this.elementShortcut = elementShortcut;
        this.elementName = elementName;
        this.atomicMass = atomicMass;
        this.color = color;
    }
    
    public int getAtomicNumber(){
        return atomicNumber;
    }
    
    public String getElementShortcut(){
        return elementShortcut;
    }
    
    public String getElementName(){
        return elementName;
    }
    
    public double getAtomicMass(){
        return atomicMass;
    }
    
    public Color getColor(){
        return color;
    }
}

