import APARAPI.Matrix;


public class matMulTests extends MatrixReaderWriter {
	public static void main ( String [] arg ) throws Exception{
   	  	
    	//int[][] matA = 1000;
    	//int[][] matB = 1000;
    	
		int matSize = 1000; //2000,	3000, 4000, 5000, 6000, 7000, 8000
		
    	// for reading data in a text file
    	// matA = store2DMatrixFromFile("C:\\Users\\MirAL\\Desktop\\Thesis Workspace\\GPUMatrixOperations\\tests\\Data\\matA6k-ok.txt");
        // matB = store2DMatrixFromFile("C:\\Users\\MirAL\\Desktop\\Thesis Workspace\\GPUMatrixOperations\\tests\\Data\\matB6k-ok.txt");
       
    	Matrix ap = new Matrix();
        
    	int[][] matA = new int[matSize][matSize];
        int[][] matB = new int[matSize][matSize];
        
        matA = Matrix.random(matA);
        matB = Matrix.random(matB);
        
        System.out.println("---------------- ");
        System.out.println("Matrix C: Result of Matrix A and B with the size of: " +matA.length +" x "+matB.length );
        System.out.println("---------------- ");      
                            
        String JTP = "JTP", SEQ = "SEQ", GPU = "GPU", CPU = "CPU";
				
        		ap.Multiply(matA, matB, GPU); 
        		ap.Multiply(matA, matB, CPU);
        		ap.Multiply(matA, matB, JTP);
				ap.Multiply(matA, matB, SEQ);
        		ap.SequentialMatMul(matA, matB);
    }
}