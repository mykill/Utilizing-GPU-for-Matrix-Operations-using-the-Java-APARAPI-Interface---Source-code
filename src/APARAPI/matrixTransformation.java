package APARAPI;



public class matrixTransformation extends AparapiKernelMatrixOperations {

	/*
     * Matrix Transformation
     *
     * transform 2D array to 1D array
     * returns 1D arrays
     * */
    public static int[] return1D(int[][] p_matrix){
        int[] oneDArray = new int[p_matrix.length * p_matrix[0].length];
        int k = 0;
        for (int row = 0; row < p_matrix.length; row++){
            for (int col = 0; col < p_matrix[0].length; col++){
            	oneDArray[k] = p_matrix[row][col];
                k++;
            }
        }
        return oneDArray;
    }


    /*
     * Matrix Transformation
     *
     * Transform 1D array to 2D array
     * returns 2D arrays
     */
    public static int[][] return2D (int[] A, int[][] B){
    	int[][] twoDArray = new int[B.length][B[0].length];
        int k=0;
        for(int i = 0; i < B.length; i++){
            for(int j = 0; j < B[0].length; j++){
                twoDArray[i][j] = A[k];
                k++;
            }
        }

       return twoDArray;
    }
}
