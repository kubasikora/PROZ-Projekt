package photoelectriceffectsimulator;

import photoelectriceffectsimulator.model.Model;
import photoelectriceffectsimulator.view.View;
import photoelectriceffectsimulator.controller.Controller;

/**
 * Główna klasa porgramu symulującego
 * zjawisko fotoelektryczne zewnętrzne 
 * stworzonego w ramach projektu
 * z programowania zdarzeniowego PROZ.A w semestrze 17Z
 * <p>
 * Autor projektu: Jakub Sikora
 * 
 * @author Jakub Sikora
 */
public class PhotoelectricEffectSimulator {

    /**
     * Funkcja main programu która tworzy oraz przekazuje referencje
     * do obiektów modelu, kontrolera i widoku zgodnych z 
     * architekturą MVC
     * 
     * @param args argumenty wiersza poleceń
     */
    public static void main(String[] args) {
        Model model = new Model();
        View view = new View();
        Controller controller = new Controller(view, model);
        
        model.setController(controller);
        view.setController(controller);
    }
    
}
