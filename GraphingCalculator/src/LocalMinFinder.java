//TCHUNG 2019 Code

import sun.java2d.cmm.lcms.LcmsServiceProvider;

import java.util.Arrays;
import java.util.Scanner;
import java.util.ArrayList;
//smaller to bigger to calculate inflexion points
// 5x^5-30x case //5x^4+-3x^2
//5x^{4}-3x^{2} same -.35 problem //not finding negative value

public class LocalMinFinder {

    public static double interval, m, m2, y1, y2, y3, y4, x2, x1, finalx, finaly, xint, xint2;
    public static int counter = 0;
    public static boolean found = false;
    public static boolean MultiZero = false;
    public static ArrayList<Term> terms = new ArrayList<Term>();

//    public static void main(String[] args) {
//        interval = .15;
//        x2 = 100; //could change domain/range
//        Scanner scan = new Scanner(System.in);
//        String equation = scan.nextLine();
//        split(equation);
//        LocalMinFinder.MaxMin(terms);
//        //for (int i = 0; i < terms.get(0).power; i++) {
//        LocalMinFinder.secondMax(terms);
//        LocalMinFinder.secondMax(terms);
//        //}
//        interval = .10;
//        x2 = 100;
//        LocalMinFinder.zero(terms);
//        for (int i = 0; i < terms.get(0).power; i++) {
//            LocalMinFinder.multiZero(terms);
//        }
//    }

    public static double[] findFirstMax(ArrayList<Integer> coefficients){
        interval = .5;
        x2 = 100; //could change domain/range
        ArrayList<Term> inputTerms = new ArrayList<Term>();
        for(int i = 0; i < coefficients.size(); i++){
            inputTerms.add(new Term(coefficients.get(i), coefficients.size()-(i+1)));
            System.out.print(coefficients.get(i) + "x^" + (coefficients.size()-(i+1)) + "+");
        }
        double [] result = LocalMinFinder.MaxMin(inputTerms);
        return new double[] {result[0], result[1]};
    }

    public static double[] findSecondMax(ArrayList<Integer> coefficients){
        interval = .5;
        x2 = 100; //could change domain/range
        ArrayList<Term> inputTerms = new ArrayList<Term>();
        for(int i = 0; i < coefficients.size(); i++){
            inputTerms.add(new Term(coefficients.get(i), coefficients.size()-(i+1)));
            System.out.print(coefficients.get(i) + "x^" + (coefficients.size()-(i+1)) + "+");
        }
        double [] result = LocalMinFinder.secondMax(inputTerms);
        return new double[] {result[0], result[1]};
    }

    public static double[] findFirstZero(ArrayList<Integer> coefficients){
        interval = .5;
        x2 = 100; //could change domain/range
        ArrayList<Term> inputTerms = new ArrayList<Term>();
        for(int i = 0; i < coefficients.size(); i++){
            inputTerms.add(new Term(coefficients.get(i), coefficients.size()-(i+1)));
            System.out.print(coefficients.get(i) + "x^" + (coefficients.size()-(i+1)) + "+");
        }
        double [] result = LocalMinFinder.zero(inputTerms);
        return new double[] {result[0], result[1]};
    }


    public static double[] findMultipleZeroes(ArrayList<Integer> coefficients){
        interval = .5;
        x2 = 100; //could change domain/range
        ArrayList<Term> inputTerms = new ArrayList<Term>();
        for(int i = 0; i < coefficients.size(); i++){
            inputTerms.add(new Term(coefficients.get(i), coefficients.size()-(i+1)));
            System.out.print(coefficients.get(i) + "x^" + (coefficients.size()-(i+1)) + "+");
        }
        double [] result = LocalMinFinder.multiZero(inputTerms);
        return new double[] {result[0], result[1]};
    }
    public static double[] MaxMin(ArrayList<Term> input) { //ArrayList<Term> input
        for (int i = 0; i < (2 / interval * 100); i++) {
            found = false;
            x1 = x2;
            x2 = x2 - interval;
            for (int j = 0; j < input.size(); j++) {
                if (j == 0) {
                    y1 = (input.get(j).coefficient * Math.pow(x1, input.get(j).power));
                    y2 = (input.get(j).coefficient * Math.pow(x2, input.get(j).power));
                    y3 = (input.get(j).coefficient * Math.pow((x1 - interval), input.get(j).power));
                    y4 = (input.get(j).coefficient * Math.pow((x2 - interval), input.get(j).power));
                } else {
                    y1 = y1 + (input.get(j).coefficient * Math.pow(x1, input.get(j).power));
                    y2 = y2 + (input.get(j).coefficient * Math.pow(x2, input.get(j).power));
                    y3 = y3 + (input.get(j).coefficient * Math.pow((x1 - interval), input.get(j).power));
                    y4 = y4 + (input.get(j).coefficient * Math.pow((x2 - interval), input.get(j).power));
                }
            }
            m = (y2 - y1) / (x2 - x1);
            m2 = (y4 - y3) / ((x1) - (x1 + interval));
            //System.out.println(x1 + ", " + x2 + ", " + y1 + ", " + y2 + ", " + m + ", " + m2 + ", " + (m / m2));

            if ((m > .0001 || m2 < -.0001) || (m < -.0001 || m2 > .0001)) { //m > .0001 || m < -.0001
                if ((m / m2) < 0) {
                    interval = interval / 10;
                    x2 = x1;
                    found = false;
                }
            }
            if ((m < .0001 && m > 0 && m2 > -.0001 && m2 < 0) || (m2 < .0001 && m2 > 0 && m > -.0001 && m < 0)) { //m < .0001 && m > -.0001
                found = true;
                finalx = (x1 + x2) / 2;
                finaly = (y1 + y2) / 2;
                System.out.println("Max/Min Coordinate: (" + finalx + ", " + finaly + ")");
                return (new double[]{finalx, finaly});
            }
        }
        return (new double[]{420, 69});
    }

    public static double[] secondMax(ArrayList<Term> input2) {
        if (found) {
            interval = .15;
            x2 = finalx - (interval / 4);
            return MaxMin(input2);
        }
        return (new double[]{420, 69});
    }

    public static double[] zero(ArrayList<Term> input) {
        //when y is 0, x is...

        for (int i = 0; i < (2 / interval * 100); i++) {
            MultiZero = false;
            x1 = x2;
            x2 = x2 - interval;
            for (int j = 0; j < input.size(); j++) {
                if (j == 0) {
                    y1 = (input.get(j).coefficient * Math.pow(x1, input.get(j).power));
                    y2 = (input.get(j).coefficient * Math.pow(x2, input.get(j).power));
                } else {
                    y1 = y1 + (input.get(j).coefficient * Math.pow(x1, input.get(j).power));
                    y2 = y2 + (input.get(j).coefficient * Math.pow(x2, input.get(j).power));
                }
            }
            //System.out.println(x1 + ", " + x1 + ", " + y1 + ", " + y2);

            if ((y1 > .000001 || y2 < -.000001) || (y1 < -.000001 || y2 > .000001)) {
                if (y1 / y2 < 0) {
                    interval = interval / 10;
                    x2 = x1;
                }
            }

            if ((y1 < .000001 && y1 > 0 && y2 > -.000001 && y2 < 0) || (y2 < .000001 && y2 > 0 && y1 > -.000001 && y1 < 0)) { //need above too? works so that y1 can't be negative and y2 be positive and still fit the condition
                //System.out.println("inside condition: " + x1 + ", " + x1 + ", " + y1 + ", " + y2);
                MultiZero = true;
                counter++;
                xint = x1; //better not to average here
                System.out.println("zero " + counter + ": (" + xint + ", 0.0)");
                return (new double[]{xint, 0.0});
            }

        }

        return (new double[]{420, 69});
    }

    public static double[] multiZero(ArrayList<Term> input) {

        if (MultiZero) {
            interval = .10;
            x2 = xint - (interval / 2);
            return zero(input);
        }

        return (new double[]{69, 420});

    }

    public static void split(String d) {
        int counter = 1;
        String[] arrOfStr = d.split("\\+");
        for (String a : arrOfStr) counter++;
        double[] coefficient;
        double[] power;
        coefficient = new double[counter];
        power = new double[counter];

        for (String a : arrOfStr) {
            //System.out.println("Term:" + a);
            String[] arrOfStr2 = a.split("\\^");
            String[] arrOfStr3 = arrOfStr2[0].split("x");
            for (String c : arrOfStr3) {
                //System.out.println("Cofficient: " + c);
                coefficient[counter - 1] = Double.parseDouble(c);
                if (arrOfStr2.length == 2) {
                    //System.out.println("Power:" + arrOfStr2[1]);
                    power[counter - 1] = Double.parseDouble(arrOfStr2[1]);
                } else {
                    power[counter - 1] = 1;
                }
                terms.add(new Term(coefficient[counter - 1], power[counter - 1]));
                //System.out.println("Term Coefficient is " + coefficient[counter - 1]);
                //System.out.println("Term Power is " + power[counter - 1]);
            }
        }
    }

    public static ArrayList<Integer> splitString(String d) {
        ArrayList<Term> termAL = new ArrayList<>();
        String[] arrOfStr = d.split("\\+");
        int counter = arrOfStr.length;
        double[] coefficient;
        double[] power;
        coefficient = new double[counter];
        power = new double[counter];

        for (String a : arrOfStr) {
            System.out.println("Term:" + a);
            String[] arrOfStr2 = a.split("\\^");
            String[] arrOfStr3 = arrOfStr2[0].split("x");
            for (String c : arrOfStr3) {
                System.out.println("Cofficient: " + c);
                coefficient[counter - 1] = Double.parseDouble(c);
                if (arrOfStr2.length == 2) {
                    System.out.println("Power:" + arrOfStr2[1]);
                    power[counter - 1] = Double.parseDouble(arrOfStr2[1]);
                } else {
                    power[counter - 1] = 1;
                }
                termAL.add(new Term(coefficient[counter - 1], power[counter - 1]));
                System.out.println("Term Coefficient is " + coefficient[counter - 1]);
                System.out.println("Term Power is " + power[counter - 1]);
            }
        }

        double highestExponent = 0;

        System.out.println(termAL.get(0).power + " is the first input term");

//        System.out.println(termAL.get(1).power + " is the second input term in an al with length " + termAL.size());
        for(int i = 0; i<termAL.size(); i++) {
            System.out.print((int)termAL.get(i).coefficient+"x^" +(int)termAL.get(i).power + "+");
        }
        for(int i = 0; i<termAL.size(); i++){
            if(termAL.get(i).power>highestExponent){
                highestExponent = termAL.get(i).power;
                System.out.println(highestExponent + " is the new highest exponent " +
                        "and therefore the coefficient array will be this long:  " + (highestExponent+1));
            }
        }


        double[] coefficientArray = new double[(int)(highestExponent+1)];

//        for(int i = 0; i < coefficientArray.length; i++){
//            for(int x = 0; x < termAL.size(); x++){
//                if(termAL.get(x).power == coefficientArray.length-i-1){//If the power of the term that it's on fits in the right place, set the coefficient to that one. switch to x
//                    coefficientArray[i] = termAL.get(x).coefficient;
//                }
//                else if(termAL.get(x).power == highestExponent){
//                    coefficientArray[0] = termAL.get(x).coefficient;
//                    System.out.println(termAL.get(x).coefficient + "is the coefficient of the highest power");
//                }
//                else{
//                    coefficientArray[i] = 0;
////                    System.out.println("coefficient[" + i + "] is not in the array.");
//                }
//            }
//            System.out.print(coefficientArray[i] + ", ");
//        }
        for(int i = 0; i < termAL.size(); i++){
            coefficientArray[coefficientArray.length-(int)termAL.get(i).power-1] = termAL.get(i).coefficient;
        }

        ArrayList<Integer> coefficients = new ArrayList<>();
        for (double co : coefficientArray) {
            coefficients.add((int) co);
        }

        return coefficients;
    }
}