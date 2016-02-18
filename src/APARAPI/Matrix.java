package APARAPI;

import java.util.Random;


public class Matrix extends matrixTransformation{

    private int result_GPU1D[];
    private int result_GPU2D[][];
    private int result_CPU2D[][];
    long time;

    
    
    /*
     * Generate random numbers from 0-100
     *
     */
    public static int[][] random(int[][] p_matrix){ //initialize matrix with random numbers
    	Timer.start();
        for (int row = 0; row < p_matrix.length; row++){         //initialize matrix1
            for (int col = 0; col < p_matrix[0].length; col++){
                p_matrix[row][col] = new Random().nextInt(100);
            }
        }
        long time = Timer.end();
      	System.out.println("Matrix with size " + p_matrix.length + " by " + p_matrix[0].length + " has been initialized with random values: " + time + " ns. / " + time/1000000 + " ms.");
      	
        return p_matrix;
    }

    
    /*
     * Identity	
     * 
     * return n-by-n identity matrix I
     */
    public static int[][] identity(int n) {
        int[][] I = new int[n][n];
        for (int i = 0; i < n; i++){
            I[i][i] = 1;
        }
        return I;
    }

    
    /*
     * Transpose
     *  
     *  return C = A^T
     */
    public static int[][] transpose(int[][] A) {
        int m = A.length;
        int n = A[0].length;
        int[][] C = new int[n][m];
        for (int i = 0; i < m; i++){
            for (int j = 0; j < n; j++){
                C[j][i] = A[i][j];
            }
        }
        return C;
    }
    
    
    /*
     * GPU Matrix Multiplication using APARAPI
     *
     * Input: 2D array
     * */
    public int[][] Multiply(int[][] matA, int[][] matB, String str){
    	System.out.println();
    	System.out.println("----------------------------------------------------------------------------------");
    	System.out.println("APARAPI Matrix Multiplication Execution of " + str);
    	matrixTransformation.return1D(matA);
    	matrixTransformation.return1D(matB);
        System.out.println();
        long totalTime = 0;
        matrixTransformation.return1D(matA);
        Timer.start();
    	int matA1D[] = matrixTransformation.return1D(matA);

    	time  = Timer.end();
    	totalTime += time;
    	System.out.println("Time taken in transforming Matrix A from 2D to 1D: " + time + " ns. / " + time/1000000 + " ms.");

        Timer.start();
    	int matB1D[] = matrixTransformation.return1D(matB);
    	time = Timer.end();
    	totalTime += time;
    	System.out.println("Time taken in transforming Matrix B from 2D to 1D: " + time + " ns. / " + time/1000000 + " ms.");
        
        Timer.start();
        result_GPU1D = Kernel_MatMul(matA1D, matB1D, matA[0].length, matA.length, matB[0].length, str);   //matA[0].length == matB.length
        time = Timer.end();
        totalTime += time;
        System.out.println("Time taken in Matrix Multiplication GPU mode: " + time + " ns. / " + time/1000000 + " ms.");
        
        Timer.start();
        result_GPU2D = matrixTransformation.return2D(result_GPU1D, matA);
        time = Timer.end();
        totalTime += time;
        System.out.println("Time taken in transforming Matrix C from 1D to 2D: " + time + " ns. / " + time/1000000 + " ms.");
	     
	    System.out.println("---");	
        System.out.println("Total execution time from "+ str +" "+ totalTime + " ns. / " + totalTime/1000000 + " ms. / " + (totalTime/1000000)/1000 + " s.");
        System.out.println("----------------------------------------------------------------------------------");
	     
	     
        return result_GPU2D;
    }
    



    /*
     * GPU Matrix Addition
     *
     * Input: 2d Array
     * */
    public int[][] Add(int[][] matA, int[][] matB, String str){
    	int matAA[] = matrixTransformation.return1D(matA);
    	int matBB[] = matrixTransformation.return1D(matB);


        long startTime = System.currentTimeMillis();

        result_GPU1D = Kernel_MatAdd(matAA, matBB, matA[0].length, matA.length, matB[0].length,str);   //matA[0].length == matB.length

        System.out.println("Time taken for kernel execution in " + str  +" "+" mode is :"+ (System.currentTimeMillis() - startTime) + " ms");
        result_GPU2D = matrixTransformation.return2D(result_GPU1D, matA);

        return result_GPU2D;
    }


    /*
     * GPU Matrix Subtraction
     *
     * Input: 2d Array
     * */
    public int[][] Subtract(int[][] matA, int[][] matB, String str){
    	int matAA[] = matrixTransformation.return1D(matA);
    	int matBB[] = matrixTransformation.return1D(matB);


        long startTime = System.currentTimeMillis();

        result_GPU1D = Kernel_MatSub(matAA, matBB, matA[0].length, matA.length, matB[0].length, str);   //matA[0].length == matB.length

        System.out.println("Time taken for kernel execution in " + str +" "+ " mode is :"+ (System.currentTimeMillis() - startTime) + " ms");
        result_GPU2D = matrixTransformation.return2D(result_GPU1D, matA);

        return result_GPU2D;
    }


    /*
     * Iterative/Sequential Matrix Multiplication on CPU
     *
     * Input: 2D array
     * */
   	public int[][] SequentialMatMul(int[][] matA, int[][] matB){
        System.out.println();
        System.out.println("Sequential Matrix Multiplication Execution on CPU");
        result_CPU2D = new int[matA.length][matB.length];
        Timer.start();
        int[][] result_CPU2D = new int[matA.length][matB.length];
        for (int i = 0; i < matA.length; i++)  {
            for (int j = 0; j < matB.length; j++) {
                for (int k = 0; k < matB[0].length; k++)  {
                	result_CPU2D[i][j] += matA[i][k] * matB[k][j];
                }
            }
        }
        long time = Timer.end();
        
        System.out.println("Time taken for kernel execution in Sequential CPU mode is :" + time + " ns. / " + time/1000000 + " ms. / " + (time/1000000)/1000 +" s.");

        return result_CPU2D;
    }


	/*
     * Iterative/Sequential Matrix Addition on CPU
     *
     * Input: 2D array
     * */
	public int[][] SequentialMatAdd(int[][] matA, int[][] matB){
        System.out.println();
        System.out.println("Sequential Matrix Addition Execution on CPU");
        result_CPU2D = new int[matA.length][matB.length];

        for (int i = 0; i < matA.length; i++){
            for (int j = 0; j < matA[0].length; j++){
                result_CPU2D[i][j] = matA[i][j] + matB[i][j];
            }
        }

        return result_CPU2D;
    }


	/*
     * Iterative/Sequential Matrix Subtraction on CPU
     *
     * Input: 2D array
     * */
	public int[][] SequentialMatSub(int[][] matA, int[][] matB){
		System.out.println();
        System.out.println("Sequential matrix subtraction Execution on CPU");
        result_CPU2D = new int[matA.length][matB.length];

        for (int i = 0; i < matA.length; i++){
            for (int j = 0; j < matA[0].length; j++){
                result_CPU2D[i][j] = matA[i][j] - matB[i][j];
            }
        }

        return result_CPU2D;
    }


	/*
	 * Compare Matrix Operations Results
	 *
	 * GPU vs CPU result
	 * */
	public static void compareResults(int[][] GPUResult, int[][]CPUResult ){
        boolean equal = true;
        for(int i = 0; i < CPUResult.length ; i++){
        	for(int j = 0; j < CPUResult[0].length; j++){
        		if(GPUResult[i][j] != CPUResult[i][j]) {
        			equal = false;
        			break;
        		}
        	}
        }

        System.out.println("--------");
        if(equal == false)
            System.out.println("Results of GPU execution and CPU execution are not equal. :(");
        else
            System.out.println("Results of GPU execution and CPU execution are equal! :)");
        System.out.println("--------");
    }


   


    /*
     * Print Matrix in 2D
     *
     */
    public void printMatrix2D( int[][] matrix){
    	int a = matrix.length;
    	int b = matrix[0].length;
        for ( int i=0; i < a; i++ ){
            for ( int j=0; j<b; j++ ){
            	System.out.print(matrix[i][j] + " ");
            }
            System.out.println("");
        }
    }


    /*
     * Print matrix in 1D
     *
     */
    public static void printMatrix1D( int [] m ){
    	System.out.println("Elements of Matrix " + " are");
        for (int i = 0; i < 5; i++) {
            for (int j = 0; j < 5; j++) {
                System.out.print(m[i * 5 + j] + "\t");
            }
            System.out.println("");
        }
    }
}
