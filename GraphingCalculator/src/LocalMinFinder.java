import java.util.Scanner;
import java.util.ArrayList;
//smaller to bigger to calculate inflexion points

//Credit to TChung21 for this class (made some edits)

public class LocalMinFinder {

    public static double interval, m, m2, y1, y2, y3, y4, x2, x1, finalx, finaly;
    public static boolean found = false;
    public static ArrayList<Term> terms = new ArrayList<Term>();

//    public static void main(String[] args) {
//        interval = .5;
//        x2 = 100; //could change domain/range
//        Scanner scan = new Scanner(System.in);
//        String equation = scan.nextLine();
//        split(equation);
//        LocalMinFinder.MaxMin(terms);
//        LocalMinFinder.secondMax(terms);
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

    public static double[] MaxMin(ArrayList<Term> input) {
        for (int i = 0; i < (2 / interval * 100); i++) {
            found = false;
            x1 = x2;
            x2 = x2 - interval;
            //System.out.println("x1, x2: "+ x1 + ", "+ x2);
            for (int j = 0; j < input.size(); j++) {
                //System.out.println(y1);
                if (j == 0) {
                    y1 = (input.get(j).coefficient * Math.pow(x1, input.get(j).power));
                    y2 = (input.get(j).coefficient * Math.pow(x2, input.get(j).power));
                    y3 = (input.get(j).coefficient * Math.pow((x1 + interval), input.get(j).power));
                    y4 = (input.get(j).coefficient * Math.pow((x2 + interval), input.get(j).power));
                } else {
                    y1 = y1 + (input.get(j).coefficient * Math.pow(x1, input.get(j).power));
                    y2 = y2 + (input.get(j).coefficient * Math.pow(x2, input.get(j).power));
                    y3 = y3 + (input.get(j).coefficient * Math.pow((x1 + interval), input.get(j).power));
                    y4 = y4 + (input.get(j).coefficient * Math.pow((x2 + interval), input.get(j).power));
                }
            }
            m = (y2 - y1) / (x2 - x1);
            m2 = (y4 - y3) / ((x1) - (x1 + interval));

            // System.out.println("y1, y2: " + y1+ ", " + y2);
            //System.out.println("x1, x2: " + x1 + ", " + x2 + ", " + y1 + ", " + y2);
            //System.out.println("m, m2: " + m + ", " + m2);

            if (m > .000001 || m < -.000001) {
                if (m / m2 < 0) {
                    found = false;
                    interval = interval / 10;
                    x2 = x1;
                    //System.out.println("different sign" + interval);
                }
            }
            //System.out.println(m + ", " + m2);

            if (m < .000001 && m > -.000001) {
                found = true;
                finalx = (x1 + x2) / 2;
                finaly = (y1 + y2) / 2;
                System.out.println("Max/Min Coordinate: (" + finalx + ", " + finaly + ")");
                break;
            }
        }
        return new double[] {finalx, finaly};
    }

    public static void secondMax(ArrayList<Term> input2) {
        if (found) {
            interval = .5;
            x2 = finalx - (interval / 2);
            MaxMin(input2);
        }
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
                terms.add(new Term(coefficient[counter - 1], power[counter - 1]));
                System.out.println("Term Coefficient is " + coefficient[counter - 1]);
                System.out.println("Term Power is " + power[counter - 1]);
            }
        }
    }
}