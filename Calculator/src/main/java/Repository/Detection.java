package Repository;

import java.util.regex.*;
public class Detection {

    public Detection(){}
    public int converter(String textInput, Polynomial poli){
        int len = 0;
        String monomFormat = "([+-](?:(?:\\d+X\\^\\d+)|(?:\\d+X)|(?:\\d+)|(?:X\\^\\d+)|(?:X)))";
        String monomDigitsFormat = "([+-]?(?:(?:\\d+)|(?:X)))";
        String input = textInput;
        Pattern mainPattern = Pattern.compile(monomFormat);
        Matcher mainMatcher = mainPattern.matcher(input);
        Pattern secondPattern = Pattern.compile(monomDigitsFormat);
        while(mainMatcher.find()){
            String seq = mainMatcher.group();
            double coeficient = 0;
            int exponent = 0;
            boolean findX = false;
            Matcher secondMatcher = secondPattern.matcher(seq);
            len += mainMatcher.group().length();
            while(secondMatcher.find()){
                String mon = secondMatcher.group();
                switch(mon) {
                    case "X":
                        exponent = 1;
                        findX = true;
                        break;
                    case "+X":
                        exponent = 1;
                        coeficient = 1;
                        findX = true;
                        break;
                    case "-X":
                        exponent = 1;
                        coeficient = -1;
                        findX = true;
                        break;
                    default:
                        if (findX) {
                            exponent = Integer.parseInt(mon);
                        } else {
                            coeficient = Integer.parseInt(mon);
                        }
                        break;
                }
            }
            poli.addMonome(new Monomial(coeficient, exponent));
        }
        if(len != input.length()){
            return -1;
        }
        else{
            return 0;
        }
    }
}
