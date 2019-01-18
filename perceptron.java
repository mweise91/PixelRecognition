//import com.sun.java.util.jar.pack.Instruction;

import java.util.*;
import java.io.*;
import java.text.*;
import java.math.*;
import java.util.List;
public class perceptron {
   
    static int maxIterations = 16;
    static double learningRate = 0.1;
    static int numberOfInstances = 16;
    static int theta = 0;
    public static void main(String args[]) {
        ArrayList<Integer> pictures = new ArrayList<Integer>(16);
        int outputs[] = {-1,1,1,1,1,1,1,1,1,1,1,1,-1,-1,-1,-1};
        int p1[] = {-1,-1,1,-1,1,1,1,-1,1,-1,1,1,-1,-1,1,-1};
        int p2[] = {-1,1,-1,1,1,-1,1,1,-1,-1,1,1,1,-1,-1,-1};
        int p3[] = {-1,1,-1,1,1,1,-1,-1,1,1,-1,1,-1,1,-1,-1};
        int p4[] = {-1,-1,1,1,-1,1,1,1,-1,1,-1,1,-1,-1,-1,1};

        double weights[] = new double[5];
        Random random = new Random();
        weights[0] = random.nextDouble() *2 -1;
        weights[1] = random.nextDouble() *2 -1;
        weights[2] = random.nextDouble() *2 -1;
        weights[3] = random.nextDouble() *2 -1;
        weights[4] = random.nextDouble() *2 -1;
  
        double localError, globalError;
        int iteration = 0;
        int output;
        do {
            iteration++;
            globalError = 0;
            System.out.println("Iteration "+iteration);
           
            for (int p = 0; p < numberOfInstances; p++) {
                
                output = CalculateOutput(theta, weights, p1[p], p2[p], p3[p], p4[p]);
                System.out.println("Given: " + outputs[p] + "\tPrediction: " + output);
       
                localError = outputs[p] - output;
                weights[0] += learningRate * localError * p1[p];
                weights[1] += learningRate * localError * p2[p];
                weights[2] += learningRate * localError * p3[p];
                weights[3] += learningRate * localError * p4[p];
                weights[4] += learningRate * localError * 1;
   
                globalError += (localError * localError);
            }
     
        } while ((globalError != 0) && (iteration <= maxIterations));
        boolean testMode = true;
        Scanner sc = new Scanner(System.in);
        System.out.println("\n\nTraining complete. Time to test. \n\n");
        String response;
        int test;
        while (testMode){
            System.out.println("0: Generate dark image.\n" +
                    "1: Generate light image\n" +
                    "2: Random image\n" +
                    "3: Manually submit image");
            test = sc.nextInt();

            switch(test){
                case 0: test0(weights);
                    break;
                case 1: test1(weights);
                    break;
                case 2: test2(weights);
                    break;
                case 3: test3(weights);
                    break;
                default:
                    break;
            }



            System.out.println("Continue testing? (y/n)");
            response = sc.next();
            if (response.equals("n")){
                testMode = false;
            }


        }


    }

    static int CalculateOutput(int t, double[] w, int p1, int p2, int p3, int p4){
        double sum = p1 * w[0] + p2 * w[1] + p3 * w[2] + p4 * w[3] + 1 * w[4];
        return (sum >= t) ? 1 :-1;
    }

    static void test0(double[] w){
        int p1[] = {-1,-1,1,-1,-1};
        int p2[] = {1,-1,-1,-1,-1};
        int p3[] = {-1,1,-1,-1,-1};
        int p4[] = {-1,-1,-1,1,-1};
        
        Random random = new Random();
        int p = random.nextInt(5);
        int prediction = CalculateOutput(theta,w, p1[p], p2[p], p3[p], p4[p]);
        System.out.println("Generated image: \n" +
                "[" + p1[p] + "|" + p2[p] +"]\n" +
                "[" + p3[p] + "|" + p4[p] +"]\n" +
                "The machine predicted: " + prediction);

    }


    static void test1(double[] w){
        int p1[] = {-1,1,-1,1,1,1,-1,1,-1,1,1};
        int p2[] = {1,-1,1,1,-1,1,1,-1,-1,1,1};
        int p3[] = {1,-1,1,1,1,-1,-1,1,1,-1,1};
        int p4[] = {-1,1,1,-1,1,1,1,-1,1,-1,1};

        Random random = new Random();
        int p = random.nextInt(11);
        int prediction = CalculateOutput(theta,w, p1[p], p2[p], p3[p], p4[p]);
        System.out.println("Generated image: \n" +
                "[" + p1[p] + "|" + p2[p] +"]\n" +
                "[" + p3[p] + "|" + p4[p] +"]\n" +
                "The machine predicted: " + prediction);
    }

    static void test2(double[] w){
        int p1[] = {-1,-1,1,-1,1,1,1,-1,1,-1,1,1,-1,-1,1,-1};
        int p2[] = {-1,1,-1,1,1,-1,1,1,-1,-1,1,1,1,-1,-1,-1};
        int p3[] = {-1,1,-1,1,1,1,-1,-1,1,1,-1,1,-1,1,-1,-1};
        int p4[] = {-1,-1,1,1,-1,1,1,1,-1,1,-1,1,-1,-1,-1,1};
        Random random = new Random();
        int p = random.nextInt(16);
        int prediction = CalculateOutput(theta,w, p1[p], p2[p], p3[p], p4[p]);
        System.out.println("Generated image: \n" +
                "[" + p1[p] + "|" + p2[p] +"]\n" +
                "[" + p3[p] + "|" + p4[p] +"]\n" +
                "The machine predicted: " + prediction);

    }

    static void test3(double[] w){
        System.out.println("Enter -1 for a black pixel and 1 for a white pixel:");
        Scanner sc = new Scanner(System.in);
        int p1 = sc.nextInt();
        int p2 = sc.nextInt();
        int p3 = sc.nextInt();
        int p4 = sc.nextInt();
        int prediction = CalculateOutput(theta, w, p1, p2, p3, p4);
        System.out.println("Entered image: \n" +
                "[" + p1 + "|" + p2 +"]\n" +
                "[" + p3 + "|" + p4 +"]\n" +
                "The machine predicted: " + prediction);
    }
}

