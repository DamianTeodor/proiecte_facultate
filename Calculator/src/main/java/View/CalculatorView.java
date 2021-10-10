package View;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;
import java.awt.event.MouseListener;

public class CalculatorView extends JFrame {

    private JLabel calculator = new JLabel("Polynomial Calculator");
    private JLabel firstPol = new JLabel("First polynomial =");
    private JLabel secondPol = new JLabel("Second polynomial =");
    private JLabel resultPol = new JLabel("Resulted polynomial =");
    private JLabel catPol = new JLabel("Catul =");
    private JLabel restPol = new JLabel("Restul =");

    private JTextField firstPolField = new JTextField();
    private JTextField secondPolField = new JTextField();
    private JTextField resultPolField = new JTextField();
    private JTextField catPolField = new JTextField();
    private JTextField restPolField = new JTextField();


    private JButton multiplicate = new JButton("Multiplicate");
    private JButton substract = new JButton("Substract");
    private JButton divide = new JButton("Divide");
    private JButton derivative = new JButton("Derivative");
    private JButton integral = new JButton("Integral");
    private JButton add = new JButton("Add");
    private JButton exit = new JButton("Exit");
    private JButton buton1 = new JButton("1");
    private JButton buton2 = new JButton("2");
    private JButton buton3 = new JButton("3");
    private JButton buton4= new JButton("4");
    private JButton buton5 = new JButton("5");
    private JButton buton6 = new JButton("6");
    private JButton buton7 = new JButton("7");
    private JButton buton8 = new JButton("8");
    private JButton buton9 = new JButton("9");
    private JButton buton0 = new JButton("0");
    private JButton butonPlus = new JButton("+");
    private JButton butonMinus = new JButton("-");
    private JButton butonPutere = new JButton("^");
    private JButton butonX = new JButton("X");
    private JButton butonDelete = new JButton("del");

    public CalculatorView () {

        JPanel mainPanel = new JPanel();
        mainPanel.setLayout(new BoxLayout(mainPanel,BoxLayout.Y_AXIS));
        mainPanel.add(calculator);

        JPanel secondPanel = new JPanel();
        secondPanel.setLayout(new GridLayout(5,5));
        secondPanel.add(firstPol);
        secondPanel.add(firstPolField);
        secondPanel.add(secondPol);
        secondPanel.add(secondPolField);
        secondPanel.add(resultPol);
        resultPolField.setEditable(false);
        catPolField.setEditable(false);
        restPolField.setEditable(false);
        secondPanel.add(resultPolField);
        secondPanel.add(catPol);
        secondPanel.add(catPolField);
        secondPanel.add(restPol);
        secondPanel.add(restPolField);

        JPanel thirdPanel = new JPanel();
        thirdPanel.setLayout(new GridLayout(2,4));
        thirdPanel.add(multiplicate);
        thirdPanel.add(substract);
        thirdPanel.add(derivative);
        thirdPanel.add(exit);
        thirdPanel.add(divide);
        thirdPanel.add(add);
        thirdPanel.add(integral);

        JPanel lastPanel = new JPanel();
        lastPanel.setLayout(new GridLayout(3,5));
        lastPanel.add(buton1);
        lastPanel.add(buton2);
        lastPanel.add(buton3);
        lastPanel.add(buton0);
        lastPanel.add(butonPlus);
        lastPanel.add(buton4);
        lastPanel.add(buton5);
        lastPanel.add(buton6);
        lastPanel.add(butonMinus);
        lastPanel.add(butonPutere);
        lastPanel.add(buton7);
        lastPanel.add(buton8);
        lastPanel.add(buton9);
        lastPanel.add(butonX);
        lastPanel.add(butonDelete);

        mainPanel.add(secondPanel);
        mainPanel.add(thirdPanel);
        mainPanel.add(lastPanel);
        this.setPreferredSize( new Dimension(700,700));
        this.setContentPane(mainPanel);
        this.pack();
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

    }
    public void addButon1Listener (ActionListener e){
        buton1.addActionListener(e);
    }
    public void addButon2Listener (ActionListener e){
        buton2.addActionListener(e);
    }
    public void addButon3Listener (ActionListener e){
        buton3.addActionListener(e);
    }
    public void addButon4Listener (ActionListener e){
        buton4.addActionListener(e);
    }
    public void addButon5Listener (ActionListener e){
        buton5.addActionListener(e);
    }
    public void addButon6Listener (ActionListener e){
        buton6.addActionListener(e);
    }
    public void addButon7Listener (ActionListener e){
        buton7.addActionListener(e);
    }
    public void addButon8Listener (ActionListener e){
        buton8.addActionListener(e);
    }
    public void addButon9Listener (ActionListener e){
        buton9.addActionListener(e);
    }
    public void addButon0Listener (ActionListener e){
        buton0.addActionListener(e);
    }
    public void addButonPlusListener (ActionListener e){
        butonPlus.addActionListener(e);
    }
    public void addButonMinusListener (ActionListener e){
        butonMinus.addActionListener(e);
    }
    public void addButonPutereListener (ActionListener e){
        butonPutere.addActionListener(e);
    }
    public void addButonXListener (ActionListener e){
        butonX.addActionListener(e);
    }
    public void addButonDeleteListener(ActionListener e){
        butonDelete.addActionListener(e);
    }
    public void addButonExitListener(ActionListener e){
        exit.addActionListener(e);
    }
    public void addMultiListener(ActionListener e){
        multiplicate.addActionListener(e);
    }
    public void addSubstractListener(ActionListener e){
        substract.addActionListener(e);
    }
    public void addDivideListener(ActionListener e){
        divide.addActionListener(e);
    }
    public void addDerivativeListener(ActionListener e){
        derivative.addActionListener(e);
    }
    public void addSumListener(ActionListener e){
        add.addActionListener(e);
    }
    public void addIntegralListener(ActionListener e){
        integral.addActionListener(e);
    }
    public String getTextField1 (boolean op) {
        String input = firstPolField.getText();
        if(input.isEmpty() && op){
            ShowInvalidInputError();
            System.out.println("Invalid Input!");
            System.exit(-1);
        }
        return input;
    }
    public String getTextField2(boolean op){
        String input = secondPolField.getText();
        if(input.isEmpty() && op){
            ShowInvalidInputError();
            System.out.println("Invalid Input!");
            System.exit(-1);
        }
        return input;
    }
    public void ShowInvalidInputError(){
        JOptionPane.showMessageDialog(null, "Invalid Input!", "ERROR ", JOptionPane.ERROR_MESSAGE);
    }
    public void ShowExpDiff(){
        JOptionPane.showMessageDialog(null, "First polynomial needs to have the higher exponent!", "ERROR ", JOptionPane.ERROR_MESSAGE);

    }
    public void ShowZeroDivError(){
        JOptionPane.showMessageDialog(null, "Do not divide by 0!", "ERROR ", JOptionPane.ERROR_MESSAGE);
    }
    public void setResultTextField(String result){ resultPolField.setText(result); }
    public void setTextField1(String str) {
        firstPolField.setText(str);
    }
    public void setTextField2(String str){
        secondPolField.setText(str);
    }
    public void setCatTextField(String str){
        catPolField.setText(str);
    }
    public void setRestTextField(String str){
        restPolField.setText(str);
    }
    public void addPol1MouseListener(MouseListener e){
        firstPolField.addMouseListener(e);
    }
    public void addPol2MouseListener(MouseListener e){
        secondPolField.addMouseListener(e);
    }
    public JButton getButon1(){
        return this.buton1;
    }
    public JButton getButon2(){
        return this.buton2;
    }
    public JButton getButon3(){
        return this.buton3;
    }
    public JButton getButon4(){
        return this.buton4;
    }
    public JButton getButon5(){
        return this.buton5;
    }
    public JButton getButon6(){
        return this.buton6;
    }
    public JButton getButon7(){
        return this.buton7;
    }
    public JButton getButon8(){
        return this.buton8;
    }
    public JButton getButon9(){
        return this.buton9;
    }
    public JButton getButon0(){
        return this.buton0;
    }
    public JButton getButonPlus(){
        return this.butonPlus;
    }
    public JButton getButonMinus(){
        return this.butonMinus;
    }
    public JButton getButonPutere(){
        return this.butonPutere;
    }
    public JButton getButonX(){
        return this.butonX;
    }
    public JButton getButonDelete(){
        return this.butonDelete;
    }
    public JButton getMultiplicate(){
        return this.multiplicate;
    }
    public JButton getSubstract(){
        return this.substract;
    }
    public JButton getDivide(){
        return this.divide;
    }
    public JButton getderivative(){
        return this.derivative;
    }
    public JButton getIntegral(){
        return this.integral;
    }
    public JButton getAdd(){
        return this.add;
    }
    public JButton getExit(){
        return this.exit;
    }
}