/*
 Colton Kelsey
 Program2 creates methods to complete some basic math operations
 */

public class Program2 {

    // returns the addition of two numbers.
    public static double add(double a, double b) {
        return a + b;
    }

    // returns the subtraction of two numbers.
    public static double subtract(double a, double b) {
        return a - b;
    }

    // returns the produce of two numbers.
    public static double multiply(double a, double b) {
        return a * b;
    }

    // returns the division of two numbers.
    public static double divide(double a, double b) {
        return a / b;
    }

    //
    public static double sineOfAngle(double oppSide, double hyp) {
            return oppSide / hyp;
    }

    // TODO #6: finish the method's implementation - assume right triangle
    public static double hypOfTriangle(double sideA, double sideB) {
        return (sideA * sideA) + (sideB + sideB);
        //needs java.lang.math
    }


    public static void main(String[] args) {
            System.out.println("Addition results in: " + add(5,3));
            System.out.println("Subtraction results in: "+ subtract(5,3));
            System.out.println("Multiply results in:" + multiply(5,3));
            System.out.println("Divide results in:" + divide(5,3));
            System.out.println("Sine of the angle is: " + sineOfAngle(5,20));
            System.out.println("Hyp of the triangle is: " + hypOfTriangle(5,5));
    }
}
