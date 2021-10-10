package MVC;

import Controller.CalculatorController;
import Model.CalculatorModel;
import View.CalculatorView;

public class CalculatorMVC {

    public static void main(String[] args){

        CalculatorModel model = new CalculatorModel();
        CalculatorView view = new CalculatorView();
        CalculatorController controller = new CalculatorController(model, view);
        view.setVisible(true);

    }

}
