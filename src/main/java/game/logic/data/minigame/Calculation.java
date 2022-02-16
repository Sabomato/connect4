package game.logic.data.minigame;


import java.io.Serializable;

public class Calculation implements Serializable {

    private static final long serialVersionUID =1L;

    private static final char [] operators = {'+','-','x','/'};
    int result;
    int operand2 ;
    int operand1 ;
    char operator;
    StringBuffer sb;
    public Calculation() {

        sb = new StringBuffer(9);

        operator = operators[getRandNumber(0,4)];


         setResult();

        sb.append(operand1).append(' ').append(operator).append(' ').append(operand2).append(" = ");
    }
    private void setResult(){

        switch (operator) {
            case '+' -> {
                operand2 = getRandNumber(1,100);
                operand1 = getRandNumber(1,100);
                result = operand1 + operand2;
            }

            case '-' -> {
                operand2 = getRandNumber(1,100);
                operand1 = getRandNumber(1,100);
                result = operand1 - operand2;
            }

            case 'x' -> {

                operand2 = getRandNumber(1,30);
                operand1 = getRandNumber(1,10);
                result = (operand1) * (operand2);

            }

            case '/' -> {
                while (true) {
                    double d =(double) operand1 / operand2;
                    if (Double.compare(d, (int) d) == 0) break;
                    operand1 = getRandNumber(0, 100);
                    operand2 = getRandNumber(1, 100);
                }
                result = operand1 / operand2;
            }
        }

    }

    public int getResult() {
        return result;
    }

    public static int getRandNumber(int x1,int x2){
        double f = Math.random()/Math.nextDown(1.0);
        return (int)( x1*(1.0 - f) + x2*f);
    }

    @Override
    public String toString() {
        return sb.toString();
    }
}