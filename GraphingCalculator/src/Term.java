import java.util.ArrayList;
import java.util.Arrays;

public class Term {
    public double coefficient;
    public double power;

    public Term (double coef, double expo){
        coefficient=coef;
        power=expo;
    }


    public static ArrayList<Double> split(String d) {
        ArrayList<Term> termAL = new ArrayList<>();
        int counter = 1;
        String[] arrOfStr = d.split("\\+");
        counter = arrOfStr.length;
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

        for(int i = 0; i < termAL.size(); i++){
            if(termAL.get(i).power>highestExponent){
                highestExponent = termAL.get(i).power;
            }
        }

        ArrayList<Double> coefficients = new ArrayList<>((int)highestExponent+1);

        for(int i = 0; i < coefficients.size(); i++){
            for(int x = 0; x < termAL.size(); x++){
                if(termAL.get(i).power == coefficients.size()-i-1){
                    coefficients.set(i, termAL.get(i).power);
                }
                else{
                    coefficients.set(i, 0.0);
                }
            }
            System.out.print(coefficients.get(i) + ", ");
        }

        return coefficients;
    }

}