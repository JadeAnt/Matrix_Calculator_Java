import java.util.Scanner;
import java.util.*;
import static java.lang.System.exit;


public class main {


    public static double[][] matrixAddition(double[][] a, double[][] b, int length, int width){
        double[][] result = new double[length][width];

        for(int i = 0; i < length; i++){
            for(int j = 0; j < width; j++){
                result[i][j] = a[i][j] + b[i][j];
            }
        }

        return result;
    }

    public static double[][] matrixSubtraction(double[][] a, double[][] b, int length, int width){
        double[][] result = new double[length][width];

        for(int i = 0; i < length; i++){
            for(int j = 0; j < width; j++){
                result[i][j] = a[i][j] - b[i][j];
            }
        }

        return result;
    }

    public static double[][] matrixMultiplication(double[][] a, double[][] b, int length, int width){
        double[][] result = new double[length][width];

        for(int i = 0; i < length; i++){
            for(int j = 0; j < width; j++){
                for(int k = 0; k < width; k++) {
                    result[i][j] += a[i][k] * b[k][j];
                }
            }
        }

        return result;
    }

    public static void printMatrix(double[][] print_array){
        for(double[] row : print_array){
            System.out.println(Arrays.toString(row));
        }
    }

    public static double[][] inputMatrix(int length, int width){
        Scanner stdin = new Scanner(System.in);
        double[][] input = new double[length][width];

        for(int i = 0; i < length; i++){
            for(int j = 0; j < width; j++){
                input[i][j] = stdin.nextDouble();
            }
        }

        return input;
    }

    public static void printMessage(String message, boolean printTop, boolean printBottom){
        if(printTop){
            System.out.println("+---------------------------------+");
            System.out.print("|");
        }
        else{
            System.out.print("|");
        }

        boolean front = true;

        for(int i = message.length(); i < 33; i++){
            if(front){
                message = " " + message;
            }
            else{
                message = message + " ";
            }
            front = !front;
        }
        System.out.print(message);


        if(printBottom){
            System.out.println("|");
            System.out.println("+---------------------------------+");
        }
        else{
            System.out.println("|");
        }

    }

    public static void mainPage(){
        printMessage("Matrix Calculator", true, true);
        printMessage("1. Input Matrix 1 Values", false, false);
        printMessage("2. Input Matrix 2 Values", false, false);
        printMessage("3. Display Matrices", false, false);
        printMessage("4. Select Operation to perform", false, false);
        printMessage("Q. Exit Program", false, true);
    }

    public static boolean verifyMatrix(int column, int row){
        if (column != row) {
            System.out.println("Operations are not possible as the columns of the 1st matrix is not equal to the rows of the 2nd matrix.");
            return false; // if operations are not possible, we exit the calculator
        }
        else{
            return true;
        }
    }
    public static void clearScreen() {
        System.out.print("\033[H\033[2J");
    }

    public static void main(String[] args){
        Scanner op = new Scanner(System.in);
        double[][] a = {}, b = {};
        char operation;
        int rows_1 = 0, columns_1 = 0, rows_2 = 0, columns_2 = 0, val;

        //Selections Screen
        do{
            mainPage();
            System.out.println("What would you like to do?: "); operation = op.next().charAt(0);

            clearScreen();//For command console clearing (does not work in log)

            switch(operation) {
                case '1':
                    //Input values for matrix 1
                    System.out.println("Input dimensions for matrix 1: ");
                    System.out.print("Length: ");
                    rows_1 = op.nextInt();
                    System.out.print("Width: ");
                    columns_1 = op.nextInt();
                    System.out.println("Input values for matrix 1: ");
                    a = inputMatrix(rows_1, columns_1);
                    break;


                case '2':
                    //Input values for matrix 2
                    System.out.println("Input dimensions for matrix 2: ");
                    System.out.print("Length: ");
                    rows_2 = op.nextInt();
                    System.out.print("Width: ");
                    columns_2 = op.nextInt();
                    System.out.println("Input values for matrix 2: ");
                    b = inputMatrix(rows_2, columns_2);
                    break;

                case '3':
                    //Displays Matrices
                    printMatrix(a);
                    System.out.println();
                    printMatrix(b);
                    break;

                case '4':
                    System.out.print("Input Operation to perform (+, -, *) or (Q:quit): ");
                    operation = op.next().charAt(0);

                    // Checking if operations are possible

                    if(verifyMatrix(columns_1, rows_2)) {

                        System.out.println("Result: ");
                        switch (operation) {
                            case '+':
                                printMatrix(matrixAddition(a, b, rows_2, columns_1));
                                break;

                            case '-':
                                printMatrix(matrixSubtraction(a, b, rows_2, columns_1));
                                break;

                            case '*':
                                printMatrix(matrixMultiplication(a, b, rows_2, columns_1));
                                break;

                        }
                    }
                    break;
            }
        } while(operation != 'Q' && operation != 'q');

         System.out.println("GoodBye");
        op.close();

    }

}
