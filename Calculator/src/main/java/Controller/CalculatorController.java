package Controller;

import Model.CalculatorModel;
import Repository.Polynomial;
import View.CalculatorView;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.List;

public class CalculatorController {

    private CalculatorView view;
    private CalculatorModel model;
    private Polynomial result;
    private static boolean mousePressedFirstPol;
    private static boolean mousePressedSecondPol;

    public CalculatorController(CalculatorModel model, CalculatorView view){

        this.view = view;
        this.model = model;

        view.addButon1Listener(new AdaugareButon1Listener());
        view.addButon2Listener(new AdaugareButon2Listener());
        view.addButon3Listener(new AdaugareButon3Listener());
        view.addButon4Listener(new AdaugareButon4Listener());
        view.addButon5Listener(new AdaugareButon5Listener());
        view.addButon6Listener(new AdaugareButon6Listener());
        view.addButon7Listener(new AdaugareButon7Listener());
        view.addButon8Listener(new AdaugareButon8Listener());
        view.addButon9Listener(new AdaugareButon9Listener());
        view.addButon0Listener(new AdaugareButon0Listener());
        view.addButonPlusListener(new AdaugareButonPlusListener());
        view.addButonMinusListener(new AdaugareButonMinusListener());
        view.addButonPutereListener(new AdaugareButonPutereListener());
        view.addButonXListener(new AdaugareButonXListener());
        view.addButonDeleteListener(new AdaugareButonDeleteListener());
        view.addButonExitListener(new AdaugareButonExitListener());
        view.addMultiListener(new AdaugareMultListener());
        view.addSubstractListener(new AdaugareSubListener());
        view.addDivideListener(new AdaugareDivListener());
        view.addDerivativeListener(new AdaugareDerivListener());
        view.addIntegralListener(new AdaugareIntegratListener());
        view.addSumListener(new AdaugareAddListener());
        view.addPol1MouseListener(new AdaugarePol1MouseListener());
        view.addPol2MouseListener(new AdaugarePol2MouseListener());
    }
    public static class AdaugarePol1MouseListener implements MouseListener{
        @Override
        public void mouseClicked(MouseEvent e) {}
        @Override
        public void mousePressed(MouseEvent e) {
            CalculatorController.mousePressedFirstPol = true;
            CalculatorController.mousePressedSecondPol = false;
        }
        @Override
        public void mouseReleased(MouseEvent e) {}
        @Override
        public void mouseEntered(MouseEvent e) {}
        @Override
        public void mouseExited(MouseEvent e) {}
    }
    public static class AdaugarePol2MouseListener implements MouseListener{
        @Override
        public void mouseClicked(MouseEvent e) {}
        @Override
        public void mousePressed(MouseEvent e) {
            CalculatorController.mousePressedFirstPol = false;
            CalculatorController.mousePressedSecondPol = true;
        }
        @Override
        public void mouseReleased(MouseEvent e) {}
        @Override
        public void mouseEntered(MouseEvent e) {}
        @Override
        public void mouseExited(MouseEvent e) {}
    }
    public class AdaugareButon1Listener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            if (e.getSource().equals(view.getButon1())) {
                if (CalculatorController.mousePressedFirstPol && !CalculatorController.mousePressedSecondPol) {
                    String sir = view.getTextField1(false);
                    sir = sir + "1";
                    view.setTextField1(sir);
                }
                if (!CalculatorController.mousePressedFirstPol && CalculatorController.mousePressedSecondPol) {
                    String sir = view.getTextField2(false);
                    sir = sir + "1";
                    view.setTextField2(sir);
                }
            }
        }
    }
    public class AdaugareButon2Listener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            if (e.getSource().equals(view.getButon2())) {
                if (CalculatorController.mousePressedFirstPol && !CalculatorController.mousePressedSecondPol) {
                    String sir = view.getTextField1(false);
                    sir = sir + "2";
                    view.setTextField1(sir);
                }
                if (!CalculatorController.mousePressedFirstPol && CalculatorController.mousePressedSecondPol) {
                    String sir = view.getTextField2(false);
                    sir = sir + "2";
                    view.setTextField2(sir);
                }
            }
        }
    }
    public class AdaugareButon3Listener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            if (e.getSource().equals(view.getButon3())) {
                if (CalculatorController.mousePressedFirstPol && !CalculatorController.mousePressedSecondPol) {
                    String sir = view.getTextField1(false);
                    sir = sir + "3";
                    view.setTextField1(sir);
                }
                if (!CalculatorController.mousePressedFirstPol && CalculatorController.mousePressedSecondPol) {
                    String sir = view.getTextField2(false);
                    sir = sir + "3";
                    view.setTextField2(sir);
                }
            }
        }
    }
    public class AdaugareButon4Listener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            if (e.getSource().equals(view.getButon4())) {
                if (CalculatorController.mousePressedFirstPol && !CalculatorController.mousePressedSecondPol) {
                    String sir = view.getTextField1(false);
                    sir = sir + "4";
                    view.setTextField1(sir);
                }
                if (!CalculatorController.mousePressedFirstPol && CalculatorController.mousePressedSecondPol) {
                    String sir = view.getTextField2(false);
                    sir = sir + "4";
                    view.setTextField2(sir);
                }
            }
        }
    }
    public class AdaugareButon5Listener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            if (e.getSource().equals(view.getButon5())) {
                if (CalculatorController.mousePressedFirstPol && !CalculatorController.mousePressedSecondPol) {
                    String sir = view.getTextField1(false);
                    sir = sir + "5";
                    view.setTextField1(sir);
                }
                if (!CalculatorController.mousePressedFirstPol && CalculatorController.mousePressedSecondPol) {
                    String sir = view.getTextField2(false);
                    sir = sir + "5";
                    view.setTextField2(sir);
                }
            }
        }
    }
    public class AdaugareButon6Listener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            if (e.getSource().equals(view.getButon6())) {
                if (CalculatorController.mousePressedFirstPol && !CalculatorController.mousePressedSecondPol) {
                    String sir = view.getTextField1(false);
                    sir = sir + "6";
                    view.setTextField1(sir);
                }
                if (!CalculatorController.mousePressedFirstPol && CalculatorController.mousePressedSecondPol) {
                    String sir = view.getTextField2(false);
                    sir = sir + "6";
                    view.setTextField2(sir);
                }
            }
        }
    }
    public class AdaugareButon7Listener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            if (e.getSource().equals(view.getButon7())) {
                if (CalculatorController.mousePressedFirstPol && !CalculatorController.mousePressedSecondPol) {
                    String sir = view.getTextField1(false);
                    sir = sir + "7";
                    view.setTextField1(sir);
                }
                if (!CalculatorController.mousePressedFirstPol && CalculatorController.mousePressedSecondPol) {
                    String sir = view.getTextField2(false);
                    sir = sir + "7";
                    view.setTextField2(sir);
                }
            }
        }
    }
    public class AdaugareButon8Listener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            if (e.getSource().equals(view.getButon8())) {
                if (CalculatorController.mousePressedFirstPol && !CalculatorController.mousePressedSecondPol) {
                    String sir = view.getTextField1(false);
                    sir = sir + "8";
                    view.setTextField1(sir);
                }
                if (!CalculatorController.mousePressedFirstPol && CalculatorController.mousePressedSecondPol) {
                    String sir = view.getTextField2(false);
                    sir = sir + "8";
                    view.setTextField2(sir);
                }
            }
        }
    }
    public class AdaugareButon9Listener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            if (e.getSource().equals(view.getButon9())) {
                if (CalculatorController.mousePressedFirstPol && !CalculatorController.mousePressedSecondPol) {
                    String sir = view.getTextField1(false);
                    sir = sir + "9";
                    view.setTextField1(sir);
                }
                if (!CalculatorController.mousePressedFirstPol && CalculatorController.mousePressedSecondPol) {
                    String sir = view.getTextField2(false);
                    sir = sir + "9";
                    view.setTextField2(sir);
                }
            }
        }
    }
    public class AdaugareButon0Listener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            if (e.getSource().equals(view.getButon0())) {
                if (CalculatorController.mousePressedFirstPol && !CalculatorController.mousePressedSecondPol) {
                    String sir = view.getTextField1(false);
                    sir = sir + "0";
                    view.setTextField1(sir);
                }
                if (!CalculatorController.mousePressedFirstPol && CalculatorController.mousePressedSecondPol) {
                    String sir = view.getTextField2(false);
                    sir = sir + "0";
                    view.setTextField2(sir);
                }
            }
        }
    }
    public class AdaugareButonPlusListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            if (e.getSource().equals(view.getButonPlus())) {
                if (CalculatorController.mousePressedFirstPol && !CalculatorController.mousePressedSecondPol) {
                    String sir = view.getTextField1(false);
                    sir = sir + "+";
                    view.setTextField1(sir);
                }
                if (!CalculatorController.mousePressedFirstPol && CalculatorController.mousePressedSecondPol) {
                    String sir = view.getTextField2(false);
                    sir = sir + "+";
                    view.setTextField2(sir);
                }
            }
        }
    }
    public class AdaugareButonMinusListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            if (e.getSource().equals(view.getButonMinus())) {
                if (CalculatorController.mousePressedFirstPol && !CalculatorController.mousePressedSecondPol) {
                    String sir = view.getTextField1(false);
                    sir = sir + "-";
                    view.setTextField1(sir);
                }
                if (!CalculatorController.mousePressedFirstPol && CalculatorController.mousePressedSecondPol) {
                    String sir = view.getTextField2(false);
                    sir = sir + "-";
                    view.setTextField2(sir);
                }
            }
        }
    }
    public class AdaugareButonPutereListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            if (e.getSource().equals(view.getButonPutere())) {
                if (CalculatorController.mousePressedFirstPol && !CalculatorController.mousePressedSecondPol) {
                    String sir = view.getTextField1(false);
                    sir = sir + "^";
                    view.setTextField1(sir);
                }
                if (!CalculatorController.mousePressedFirstPol && CalculatorController.mousePressedSecondPol) {
                    String sir = view.getTextField2(false);
                    sir = sir + "^";
                    view.setTextField2(sir);
                }
            }
        }
    }
    public class AdaugareButonXListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            if (e.getSource().equals(view.getButonX())) {
                if (CalculatorController.mousePressedFirstPol && !CalculatorController.mousePressedSecondPol) {
                    String sir = view.getTextField1(false);
                    sir = sir + "X";
                    view.setTextField1(sir);
                }
                if (!CalculatorController.mousePressedFirstPol && CalculatorController.mousePressedSecondPol) {
                    String sir = view.getTextField2(false);
                    sir = sir + "X";
                    view.setTextField2(sir);
                }
            }
        }
    }
    public class AdaugareButonDeleteListener implements  ActionListener{
        @Override
        public void actionPerformed(ActionEvent e) {
            if (e.getSource().equals(view.getButonDelete())) {
                if (CalculatorController.mousePressedFirstPol && !CalculatorController.mousePressedSecondPol) {
                    String sir = view.getTextField1(false);
                    if (sir != null && sir.length() > 0) {
                        sir = sir.substring(0, sir.length() - 1);
                    }
                    view.setTextField1(sir);
                }
                if (!CalculatorController.mousePressedFirstPol && CalculatorController.mousePressedSecondPol) {
                    String sir = view.getTextField2(false);
                    if (sir != null && sir.length() > 0) {
                        sir = sir.substring(0, sir.length() - 1);
                    }
                    view.setTextField2(sir);
                }
            }
        }
    }
    public class AdaugareButonExitListener implements ActionListener{
        @Override
        public void actionPerformed(ActionEvent e) {
            if (e.getSource().equals(view.getExit())) {
                System.exit(0);
            }
        }
    }
    public class AdaugareMultListener implements ActionListener{
        @Override
        public void actionPerformed(ActionEvent e) {
            if(e.getSource().equals(view.getMultiplicate())){
                String pol1 = view.getTextField1(true);
                String pol2 = view.getTextField2(true);
                result = model.multi(pol1, pol2);
                if(result.getMonom(0).getExponent() == result.getMonom(0).getCoeficient() && result.getMonom(0).getCoeficient() == 0) {
                    view.ShowInvalidInputError();
                }
                else {
                    view.setResultTextField(result.toString());
                    view.setCatTextField("");
                    view.setRestTextField("");
                }
            }
        }
    }
    public class AdaugareSubListener implements ActionListener{
        @Override
        public void actionPerformed(ActionEvent e) {
            if(e.getSource().equals(view.getSubstract())){
                String pol1 = view.getTextField1(true);
                String pol2 = view.getTextField2(true);
                result = model.subTwoPolynomials(pol1, pol2);
                if(result.getLista().isEmpty()){
                    view.ShowInvalidInputError();
                }
                else{
                    view.setResultTextField(result.toString());
                    view.setCatTextField("");
                    view.setRestTextField("");
                }
            }
        }
    }
    public class AdaugareDivListener implements ActionListener{
        @Override
        public void actionPerformed(ActionEvent e) {
            if(e.getSource().equals(view.getDivide())){
                List<Polynomial> cr;
                String pol1 = view.getTextField1(true);
                String pol2 = view.getTextField2(true);
                cr = model.divide(pol1,pol2);
                if(cr.size() == 0){
                    view.ShowExpDiff();
                }
                else if(cr.size() == 1){
                    view.ShowZeroDivError();
                }
                else if(cr.size() == 3){
                    view.ShowInvalidInputError();
                }
                else{
                    view.setCatTextField(cr.get(0).toString());
                    view.setRestTextField(cr.get(1).toString());
                    view.setResultTextField("");
                }
            }
        }
    }
    public class AdaugareDerivListener implements ActionListener{
        @Override
        public void actionPerformed(ActionEvent e) {
            if(e.getSource().equals(view.getderivative())){
                String pol = view.getTextField1(true);
                result = model.derivative(pol);
                if(result.getMonom(0).getExponent() == result.getMonom(0).getCoeficient() && result.getMonom(0).getCoeficient() == 0) {
                    view.ShowInvalidInputError();
                }
                else {
                    view.setResultTextField(result.toString());
                    view.setCatTextField("");
                    view.setRestTextField("");
                }
            }
        }
    }
    public class AdaugareIntegratListener implements ActionListener{
        @Override
        public void actionPerformed(ActionEvent e) {
            if(e.getSource().equals(view.getIntegral())){
                String pol = view.getTextField1(true);
                result = model.integral(pol);
                if(result.getMonom(0).getExponent() == result.getMonom(0).getCoeficient() && result.getMonom(0).getCoeficient() == 0) {
                    view.ShowInvalidInputError();
                }
                else {
                    view.setResultTextField(result.toString());
                    view.setCatTextField("");
                    view.setRestTextField("");
                }
            }
        }
    }
    public class AdaugareAddListener implements ActionListener{
        @Override
        public void actionPerformed(ActionEvent e) {
            if(e.getSource().equals(view.getAdd())){
                String pol1 = view.getTextField1(true);
                String pol2 = view.getTextField2(true);
                result = model.addTwoPolynomials(pol1, pol2);
                if(result.getMonom(0).getExponent() == result.getMonom(0).getCoeficient() && result.getMonom(0).getCoeficient() == 0) {
                    view.ShowInvalidInputError();
                }
                else{
                    view.setResultTextField(result.toString());
                    view.setCatTextField("");
                    view.setRestTextField("");
                }
            }
        }
    }
}