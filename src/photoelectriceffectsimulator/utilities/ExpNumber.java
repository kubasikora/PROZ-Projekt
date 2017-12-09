package photoelectriceffectsimulator.utilities;

/**
 * Klasa pozwalająca na zapis liczby w notacji inżynierskiej (wykładniczej)
* @author kuba
 */
public class ExpNumber {
    /** Podstawa liczby zapisanej w notacji wykładniczej*/
    private double base;
    /** Wykładnik liczby zapisanej w notacji wykładniczej */
    private int index;
    
    /** Konstruktor bezparametrowy przyjmujący wartości domyślne */
    public ExpNumber(){
        base = 1;
        index = 0;
    }
    
    /**
     * Konstruktor parametryzowany który pobiera wartość podstawy i wykładnika,
     * sprawdza czy są poprawnymi wartościami i ewentualnie doprowadza do 
     * odpowiedniej postaci.
     * @param base podstawa liczby zapisanej w notacji wykładniczej
     * @param index wykładnik liczby zapisanej w notacji wykładniczej
     */
    public ExpNumber(double base, int index){
        if(base == 0) {
            this.base = 0.0;
            this.index = 0;
        }
        else{
            if(Math.abs(base) >= 10){
                while(Math.abs(base) >= 10){
                    base /= 10;
                    index++;
                }
            }
            if(Math.abs(base) < 1){
                while(Math.abs(base) < 1){
                    base *= 10;
                    index--;
                }
            }
            this.base = base;
            this.index = index;
        }
    }
    
    /**
     * Ustawia podstawę liczby
     * @param base podstawa liczby
     */
    public void setBase(double base){
        if(base == 0) {
            this.base = 0.0;
            this.index = 0;
        }
        else{
            if(Math.abs(base) >= 10){
                while(Math.abs(base) >= 10){
                    base /= 10;
                    index++;
                }
            }
            if(Math.abs(base) < 1){
                while(Math.abs(base) < 1){
                    base *= 10;
                    index--;
                }
            }
        }
    }   
    
    /**
     * Ustawia wykładnik liczby
     * @param index wykładnik liczby
     */
    public void setIndex(int index){
        if(base == 0) {
            this.base = 0.0;
            this.index = 0;
        }
        else{
            if(Math.abs(base) >= 10){
                while(Math.abs(base) >= 10){
                    base /= 10;
                    index++;
                }
            }
            if(Math.abs(base) < 1){
                while(Math.abs(base) < 1){
                    base *= 10;
                    index--;
                }
            }
        }
        this.index = index;
    }
    
    /**
     * Zwraca podstawe liczby
     * @return podstawa liczby 
     */
    public double getBase(){
        return base;
    }
    
    /**
     * Zwraca wykładnik liczby
     * @return wykładnik liczby 
     */
    public int getIndex(){
        return index;
    }
    
    /**
     * Funkcja dodaje dwie liczby zapisane w notacji wykładniczej,
     * zgodnie z zasadami dodawania takich liczb, sprowadza je do
     * równego wykładnika, dodaje i ponownie normuje
     * @param e1 pierwszy składnik
     * @param e2 drugi składnik
     * @return suma dwóch składników 
     */
    public static ExpNumber add(ExpNumber e1, ExpNumber e2){ 
        if(e1.getIndex() == e2.getIndex()) {
            return new ExpNumber(e1.getBase()+e2.getBase(), e1.getIndex());
        }
        else{
            if(e1.getIndex() > e2.getIndex()){
                double e1Base = e1.getBase();
                int e1Index = e1.getIndex();
                while(e1Index != e2.getIndex()){
                    e1Base *=10;
                    e1Index--;
                }
                return new ExpNumber(e1Base+e2.getBase(), e2.getIndex());
            }
            else{
                double e2Base = e2.getBase();
                int e2Index = e2.getIndex();
                while(e2Index != e1.getIndex()){
                    e2Base *=10;
                    e2Index--;
                }
                return new ExpNumber(e2Base+e1.getBase(), e1.getIndex());
            }
        }
    }
    
    /**
     * Funkcja odejmuje dwie liczby zapisane w notacji wykładniczej,
     * zgodnie z zasadami odejmowania takich liczb, sprowadza je do
     * równego wykładnika, odejmuje i ponownie normuje
     * @param e1 odejmna
     * @param e2 odjemnik
     * @return różnica
     */   
    public static ExpNumber subtract(ExpNumber e1, ExpNumber e2){ 
        if(e1.getIndex() == e2.getIndex()) {
            return new ExpNumber(e1.getBase()-e2.getBase(), e1.getIndex());
        }
        else{
            if(e1.getIndex() > e2.getIndex()){
                double e1Base = e1.getBase();
                int e1Index = e1.getIndex();
                while(e1Index != e2.getIndex()){
                    e1Base *=10;
                    e1Index--;
                }
                return new ExpNumber(e1Base-e2.getBase(), e2.getIndex());
            }
            else{
                double e2Base = e2.getBase();
                int e2Index = e2.getIndex();
                while(e2Index != e1.getIndex()){
                    e2Base *=10;
                    e2Index--;
                }
                return new ExpNumber(e2Base-e1.getBase(), e1.getIndex());
            }
        }
    }
    
    /**
     * Funkcja mnoży dwie liczby zapisane w notacji wykładniczej,
     * @param e1 pierwszy czynnik
     * @param e2 drugi czynnik
     * @return iloczyn dwóch czynników 
     */
    public static ExpNumber multiply(ExpNumber e1, ExpNumber e2){
        return new ExpNumber(e1.getBase() * e2.getBase(), e1.getIndex() + e2.getIndex());
    }
    
    /**
     * Funkcja dzieli dwie liczby zapisane w notacji wykładniczej,
     * @param e1 licznik
     * @param e2 mianownik
     * @return iloraz 
     */
    public static ExpNumber divide(ExpNumber e1, ExpNumber e2) throws ArithmeticException{
        return new ExpNumber(e1.getBase() / e2.getBase(), e1.getIndex() - e2.getIndex());
    }
    
    /**
     * Funckja konwertuje liczbę zapisaną w notacji wykładniczej do typu double
     * @param e liczba w notacji wykładniczej
     * @return wartość typu double
     */
    public static double getDouble(ExpNumber e){
        double eBase = e.getBase();
        int eIndex = e.getIndex();
        if(eIndex > 0){
            while(eIndex != 0){
                eBase *=10;
                eIndex--;
            }
        }
        else{
            while(eIndex != 0){
                eBase /=10;
                eIndex++;
            }
        }
        return eBase;
    }
}
