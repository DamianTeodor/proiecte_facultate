package Model;

import Repository.Detection;
import Repository.Monomial;
import Repository.Polynomial;

import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

public class CalculatorModel {

    private Detection detector;
    int tester;

    public CalculatorModel(){
        detector = new Detection();
    }
    public Polynomial subTwoPolynomials(String pol1, String pol2){
        Polynomial polinom1 = new Polynomial();
        Polynomial polinom2 = new Polynomial();
        Polynomial verf;
        if(pol1.charAt(0) != '-' && pol1.charAt(0) != '+'){
            pol1 = "+" + pol1;
        }
        if(pol2.charAt(0) != '-' && pol2.charAt(0) != '+'){
            pol2 = "+" + pol2;
        }
        tester = detector.converter(pol2.toUpperCase(Locale.ROOT), polinom2);
        if(tester == -1){
            return new Polynomial();
        }
        tester = detector.converter(pol1.toUpperCase(Locale.ROOT), polinom1);
        if(tester == -1){
            return new Polynomial();
        }
        else {
            verf = polinom1.substractPols(polinom2);
            if(verf.getLista().isEmpty()){
                return Polynomial.setFirstMonome(new Monomial(0,0));
            }
            else{
                return verf;
            }
        }
    }
    public Polynomial addTwoPolynomials(String pol1, String pol2){
        Polynomial polinom1 = new Polynomial();
        Polynomial polinom2 = new Polynomial();
        if(pol1.charAt(0) != '-' && pol1.charAt(0) != '+'){
            pol1 = "+" + pol1;
        }
        if(pol2.charAt(0) != '-' && pol2.charAt(0) != '+'){
            pol2 = "+" + pol2;
        }
        tester = detector.converter(pol2.toUpperCase(Locale.ROOT), polinom2);
        if(tester == -1){
            return new Polynomial(new Monomial(0,0));
        }
        tester = detector.converter(pol1.toUpperCase(Locale.ROOT), polinom1);
        if(tester == -1){
            return new Polynomial(new Monomial(0,0));
        }
        else{
            return polinom1.addPols(polinom2);
        }
    }
    public Polynomial derivative(String pol){
        Polynomial polinom = new Polynomial();
        if(pol.charAt(0) != '-' && pol.charAt(0) != '+'){
            pol = "+" + pol;
        }
        tester = detector.converter(pol.toUpperCase(Locale.ROOT), polinom);
        if(tester == -1){
            return new Polynomial(new Monomial(0,0));
        }
        else {
            return polinom.derivat();
        }
    }
    public Polynomial integral(String pol){
        Polynomial polinom = new Polynomial();
        if(pol.charAt(0) != '-' && pol.charAt(0) != '+'){
            pol = "+" + pol;
        }
        tester = detector.converter(pol.toUpperCase(Locale.ROOT), polinom);
        if(tester == -1){
            return new Polynomial(new Monomial(0,0));
        }
        else {
            return polinom.integrat();
        }
    }
    public Polynomial multi(String pol1, String pol2){
        Polynomial polinom1 = new Polynomial();
        Polynomial polinom2 = new Polynomial();
        if(pol1.charAt(0) != '-' && pol1.charAt(0) != '+'){
            pol1 = "+" + pol1;
        }
        if(pol2.charAt(0) != '-' && pol2.charAt(0) != '+'){
            pol2 = "+" + pol2;
        }
        tester = detector.converter(pol2.toUpperCase(Locale.ROOT), polinom2);
        if(tester == -1){
            return new Polynomial(new Monomial(0,0));
        }
        tester = detector.converter(pol1.toUpperCase(Locale.ROOT), polinom1);
        if(tester == -1){
            return new Polynomial(new Monomial(0,0));
        }
        else {
            return polinom1.multiplicare(polinom2);
        }
    }
    public List<Polynomial> divide(String pol1, String pol2){
        Polynomial polinom1 = new Polynomial();
        Polynomial polinom2 = new Polynomial();
        List<Polynomial> catSiRest = new ArrayList<Polynomial>();
        if(pol1.charAt(0) != '-' && pol1.charAt(0) != '+'){
            pol1 = "+" + pol1;
        }
        if(pol2.charAt(0) != '-' && pol2.charAt(0) != '+'){
            pol2 = "+" + pol2;
        }
        if(pol2.charAt(1) == '0'){
            catSiRest.add(new Polynomial());
            return catSiRest;
        }
        tester = detector.converter(pol2.toUpperCase(Locale.ROOT), polinom2);
        if(tester == -1){
            catSiRest.add(new Polynomial());
            catSiRest.add(new Polynomial());
            catSiRest.add(new Polynomial());
            return catSiRest;
        }
        tester = detector.converter(pol1.toUpperCase(Locale.ROOT), polinom1);
        if(tester == -1){
            catSiRest.add(new Polynomial());
            catSiRest.add(new Polynomial());
            catSiRest.add(new Polynomial());
            return catSiRest;
        }
        else {
            return polinom1.divide(polinom2);
        }
    }
}
