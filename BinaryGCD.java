
//package line only neeeded by netbeans
//package binarygcd;

import java.util.*;

/*
 * @author Nick Carlson
 */

public class BinaryGCD {

    public static void main(String[] args) {
        //get two integers, A and B, from the user and compute both GCDs
        getInputAndFindGCDs();
    }
    
    //get user input and, if acceptable, find GCDs
    public static void getInputAndFindGCDs(){
        int A = 0;
        int B = 0;
        boolean flag = true;
        Scanner input1 = new Scanner(System.in);
        Scanner input2 = new Scanner(System.in);
        
        //user inputs A
        System.out.println("Please enter a positive integer A:");
        try{
        A = input1.nextInt();
        }catch(InputMismatchException e){
            flag = false;
            System.out.println("You must enter a positive integer!");
            System.exit(0);
        }
        
        //user inputs B
        System.out.println("Please enter a positive integer B:");
        try{
        B = input2.nextInt();
        }catch(InputMismatchException e){
            flag = false;
            System.out.println("You must enter a positive integer!");
        }
        
        //if inputs are integers >= 0, compute GCDs
        if(flag == true){
            if((A >= 0) && (B >= 0)){
                System.out.println("");
                runBinaryGCD(A,B);
                System.out.println("");
                runEuclideanGCD(A,B);
            }else{
                //else, quit
                System.out.println("Both inputs must be a positive integer!");
                System.exit(0);
            }
        }
    }
    
    //find Binary GCD using remainders, report execution time
    public static void runBinaryGCD(int A, int B){
        long startTime = System.nanoTime();
        int gcd = binaryGCD(A,B);
        long endTime = System.nanoTime();
        long duration = endTime - startTime;
        double elapsedTime = (double)duration/1000000.0;
        
        System.out.println("Binary GCD = " + gcd);
        System.out.println("Binary GCD computed in " + elapsedTime + " milliseconds");
    }
    
    //find GCD using binary method
    public static int binaryGCD(int u, int v) {
        //simple termination cases
        if (v == 0) return u;
        if (u == 0) return v;

        //look for factors of 2
        // u and v even
        // If u and v are both even, then gcd(u, v) = 2·gcd(u/2, v/2), because 2 is a common divisor
        if ((u & 1) == 0 && (v & 1) == 0){
            return binaryGCD(u >> 1, v >> 1) << 1;
        }

        // u is even, v is odd
        // If u is even and v is odd, then gcd(u, v) = gcd(u/2, v), because 2 is not a common divisor
        else if ((u & 1) == 0){
            return binaryGCD(u >> 1, v);
        }

        // u is odd, v is even
        // If u is odd and v is even, then gcd(u, v) = gcd(u, v/2)
        else if ((v & 1) == 0){
            return binaryGCD(u, v >> 1);
        }

        // u and v odd, u >= v
        // If u and v are both odd, and u ≥ v, then gcd(u, v) = gcd((u − v)/2, v)
        else if (u >= v){
            return binaryGCD((u-v) >> 1, v);
        }

        // u and v odd, u < v
        // If both are odd and u < v, then gcd(u, v) = gcd((v − u)/2, u)
        else{
            return binaryGCD(u, (v-u) >> 1);
        }
    }
    
    //find Euclidean GCD using remainders, report execution time
    public static void runEuclideanGCD(int A, int B){
        long startTime = System.nanoTime();
        int gcd = euclideanGCD(A,B);
        long endTime = System.nanoTime();
        long duration = endTime - startTime;
        double elapsedTime = (double)duration/1000000.0;
        
        System.out.println("Euclidean GCD = " + gcd);
        System.out.println("Euclidean GCD computed in " + elapsedTime + " milliseconds");
    }
    
    //find Euclidean GCD
    public static int euclideanGCD(int A, int B){
        int a = A;
        int b = B;
        
        //while neither a nor b is 0
        while((a != 0) && (b != 0)){
            if(a > b){
                //a = a mod b
                a = a % b;
            }else{
                //b = b mod a
                b = b % a;
            }
        }
        //use non-zero value as GCD
        return Math.max(a,b);
    }
    
}
