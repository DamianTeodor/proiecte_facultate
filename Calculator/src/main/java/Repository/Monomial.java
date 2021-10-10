package Repository;

import java.util.Comparator;

public class Monomial {

    private double coeficient;
    private int exponent;

    public Monomial(double coeficient, int exponent){

        this.coeficient = coeficient;
        this.exponent = exponent;

    }
    @Override
    public String toString() {
        if(this.coeficient == 0) {
            return "0";
        }
        else {
            if (this.exponent == 1) {
                if (this.coeficient > 0) {
                    return "+" + this.coeficient + "X";
                } else {
                    return this.coeficient + "X";
                }
            } else if (this.exponent == 0) {
                if (this.coeficient > 0) {
                    return "+" + this.coeficient;
                } else {
                    return "" + this.coeficient;
                }
            } else {
                if (this.coeficient > 0) {
                    return "+" + this.coeficient + "X^" + this.exponent;
                } else {
                    return this.coeficient + "X^" + this.exponent;
                }
            }
        }
    }
    public double getCoeficient(){
        return this.coeficient;
    }
    public int getExponent(){
        return this.exponent;
    }
    public static class sorter implements Comparator<Monomial>{

        @Override
        public int compare(Monomial mon1, Monomial mon2) {
            return mon2.getExponent() - mon1.getExponent();
        }
    }
}
