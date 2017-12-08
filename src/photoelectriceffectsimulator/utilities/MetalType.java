package photoelectriceffectsimulator.utilities;

import java.awt.Color;

/**
 * Klasa dostępnych typów metali które aplikacja obsługuje,
 * wraz z ich pracami wyjścia. 
 * <p>Praca wyjścia podana jest w elektronowoltach
 * @author Jakub Sikora
 */
public enum MetalType {
    //lista dostepnych metali
    FERRUM(4.67),
    ALUMINIUM(4.06),
    CAESIUM(2.14),
    WOLFRAMIUM(4.53),
    PLATINUM(5.12),
    SODIUM(2.36);
    //TODO zwiększyć ilość dostępnych metali
    
   /**
    * Praca wyjścia podana w elektronowoltach
    */ 
    private final double exitEnergy;
    
    private final static Color TRANSITION_METAL = new Color(102,255,102);
    private final static Color ALKAINE_METAL = new Color(255,255,77);
    private final static Color P_TYPE_METAL = new Color(77,148,255);
    
    /**
     * Konstruktor parametryzowany pracą wyjścia
     * @param exitEnergy Praca wyjścia metalu
     */
    MetalType(double exitEnergy){
    this.exitEnergy = exitEnergy;
    }
    
    /**
     * Uzyskaj pracę wyjścia tego metalu
     * @return Praca wyjścia 
     */
    public double getExitEnergy(){
        return exitEnergy;
    }
    
    /**
     * Zwraca tablice wszystkich dostępnych metali 
     * @return tablica dostępnych metali
     */
    public static String[] getAllMetalTypes(){
        String[] metalTypes = {"Żelazo", "Glin", "Cez", "Wolfram", 
            "Platyna", "Sód"};
        return metalTypes;
        }       
    
    /**
     * Funkcja na podstawie przekazanego identyfikatora zwraca odpowiedni
     * obiekt typu MetalType
     * @param metalName identyfikator tekstowy
     * @return Obiekt typu MetalType
     */
    public static MetalType convertFromString(String metalName){
        if("Żelazo".equals(metalName)) return MetalType.FERRUM;
        if("Glin".equals(metalName)) return MetalType.ALUMINIUM;
        if("Cez".equals(metalName)) return MetalType.CAESIUM;
        if("Wolfram".equals(metalName)) return MetalType.WOLFRAMIUM;
        if("Platyna".equals(metalName)) return MetalType.PLATINUM;
        if("Sód".equals(metalName)) return MetalType.SODIUM;
        return MetalType.FERRUM;
    }
    
    public static MetalInfo getMetalInfo(MetalType metalType){
        switch(metalType){
            case FERRUM: return new MetalInfo(26, "Fe", "Żelazo", 55.845, TRANSITION_METAL);
            case ALUMINIUM: return new MetalInfo(13, "Al", "Glin", 26.982, P_TYPE_METAL);
            case CAESIUM: return new MetalInfo(55, "Cs", "Cez", 132.91, ALKAINE_METAL);
            case WOLFRAMIUM: return new MetalInfo(74, "W", "Wolfram", 183.84, TRANSITION_METAL);
            case PLATINUM: return new MetalInfo(78, "Pt", "Platyna", 195.08, TRANSITION_METAL);
            case SODIUM: return new MetalInfo(11, "Na", "Sód", 22.991, ALKAINE_METAL);
        }
        //throw new Exception();
        return new MetalInfo(26, "Fe", "Żelazo", 55.845, new Color(102,255,102));
    }
}