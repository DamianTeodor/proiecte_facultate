package Repository;

import java.util.*;

public class Polynomial {

    private List<Monomial> polynomial = new ArrayList<Monomial>();

    public Polynomial(Monomial mon){
        this.polynomial.add(mon);
    }
    public List<Monomial> getLista(){
        return this.polynomial;
    }
    public Polynomial(){}
    public void addMonome(Monomial mon){
        polynomial.add(mon);
    }
    public static Polynomial setFirstMonome(Monomial mon){
        Polynomial p = new Polynomial();
        p.polynomial.add(mon);
        return p;
    }
    @Override
    public String toString() {
        if(polynomial.isEmpty()){
            return "0";
        }
        Collections.sort(polynomial, new Monomial.sorter());
        String polinom = "";
        for(Monomial mono: polynomial){
            polinom = polinom + mono.toString();
        }
        return polinom;
    }
    public Polynomial addPols(Polynomial polinom){
        Polynomial rezultat = new Polynomial();
        int i, j;
        i = 0;
        j = 0;
        while(i < this.polynomial.size() && j < polinom.polynomial.size()) {
            if (this.polynomial.get(i).getExponent() == polinom.polynomial.get(j).getExponent()) {
                double coefi = this.polynomial.get(i).getCoeficient() + polinom.polynomial.get(j).getCoeficient();
                if (coefi != 0) {
                    rezultat.polynomial.add(new Monomial(coefi, this.polynomial.get(i).getExponent()));
                }
                i++;
                j++;
            } else if (this.getMonom(i).getExponent() > polinom.getMonom(j).getExponent()) {
                rezultat.polynomial.add(new Monomial(this.getMonom(i).getCoeficient(), this.getMonom(i).getExponent()));
                i++;
            } else {
                rezultat.polynomial.add(new Monomial(polinom.getMonom(j).getCoeficient(), polinom.getMonom(j).getExponent()));
                j++;
            }
        }
        while(i < this.polynomial.size()){
            rezultat.polynomial.add(new Monomial(this.getMonom(i).getCoeficient(), this.getMonom(i).getExponent()));
            i++;
        }
        while(j < polinom.polynomial.size()){
            rezultat.polynomial.add(new Monomial(polinom.getMonom(j).getCoeficient(), polinom.getMonom(j).getExponent()));
            j++;
        }
        return rezultat;
    }
    public Polynomial substractPols(Polynomial polinom){
        Polynomial rezultat = new Polynomial();
        int i, j;
        i = 0;
        j = 0;
        while(i < this.polynomial.size() && j < polinom.polynomial.size()) {
            if (this.polynomial.get(i).getExponent() == polinom.polynomial.get(j).getExponent()) {
                double coefi = this.polynomial.get(i).getCoeficient() - polinom.polynomial.get(j).getCoeficient();
                if (coefi != 0) {
                    rezultat.polynomial.add(new Monomial(coefi, this.polynomial.get(i).getExponent()));
                }
                i++;
                j++;
            } else if (this.getMonom(i).getExponent() > polinom.getMonom(j).getExponent()) {
                rezultat.polynomial.add(new Monomial(this.getMonom(i).getCoeficient(), this.getMonom(i).getExponent()));
                i++;
            } else {
                rezultat.polynomial.add(new Monomial(-polinom.getMonom(j).getCoeficient(), polinom.getMonom(j).getExponent()));
                j++;
            }
        }
        while(i < this.polynomial.size()){
            rezultat.polynomial.add(new Monomial(this.getMonom(i).getCoeficient(), this.getMonom(i).getExponent()));
            i++;
        }
        while(j < polinom.polynomial.size()){
            rezultat.polynomial.add(new Monomial(-polinom.getMonom(j).getCoeficient(), polinom.getMonom(j).getExponent()));
            j++;
        }
        System.out.println(rezultat.toString());
        return rezultat;
    }
    public Monomial getMonom(int poz){
        return polynomial.get(poz);
    }
    public Polynomial derivat(){
        Polynomial rezultat = new Polynomial();
        for(Monomial monom: polynomial){
            if(monom.getExponent() != 0){
                rezultat.polynomial.add(new Monomial(monom.getExponent() * monom.getCoeficient(), monom.getExponent() - 1));
            }
        }
        return rezultat;
    }
    public Polynomial integrat(){
        Polynomial rezultat = new Polynomial();
        for(Monomial monom: polynomial){
            rezultat.polynomial.add(new Monomial(monom.getCoeficient() / (monom.getExponent() + 1), monom.getExponent() + 1));
        }
        return rezultat;
    }
    public Polynomial multiplicare(Polynomial polinom){
        Polynomial rezultat = new Polynomial();
        for(Monomial mon1: polynomial){
            for(Monomial mon2: polinom.polynomial){
                rezultat = rezultat.addPolinom(mon1.getCoeficient() * mon2.getCoeficient(), mon1.getExponent() + mon2.getExponent());
            }
        }
        return rezultat;
    }
    public Polynomial addPolinom(double coeficient, int exponent){
        Polynomial pol = new Polynomial();
        pol.polynomial.add(new Monomial(coeficient, exponent));
        return this.addPols(pol);
    }
    public List<Polynomial> divide(Polynomial polinom){
        List<Polynomial> catSiRest = new ArrayList<Polynomial>();
        Polynomial cat = new Polynomial();
        Polynomial scz;
        Polynomial inter = this;
        Polynomial inter2;
        int i = this.polynomial.get(0).getExponent() - polinom.polynomial.get(0).getExponent(), j = 0;
        Collections.sort(this.polynomial, new Monomial.sorter());
        Collections.sort(polinom.polynomial, new Monomial.sorter());
        if(this.getMonom(0).getExponent() < polinom.getMonom(0).getExponent()){
            return catSiRest;
        }
        do{
            cat.polynomial.add(new Monomial(inter.getMonom(0).getCoeficient() / polinom.getMonom(0).getCoeficient(), inter.getMonom(0).getExponent() - polinom.getMonom(0).getExponent()));
            inter2 = Polynomial.setFirstMonome(cat.polynomial.get(j));
            scz = inter2.multiplicare(polinom);
            inter = inter.substractPols(scz);
            if(inter.polynomial.isEmpty()){
                break;
            }
            j++;
        }while(j < i + 1);
        catSiRest.add(cat);
        catSiRest.add(inter);
        return catSiRest;
    }
}
