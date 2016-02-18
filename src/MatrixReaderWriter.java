


import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.PrintWriter;

public class MatrixReaderWriter {
	/*
	 * A function that read and store the data in file to 2D array 
	 * 
	 */
    public static int[][] store2DMatrixFromFile(String filename) throws Exception {
        int[][] matrix = null;

        // If in the same directory - Probably in your case...
        // Just comment out the 2 lines above this and uncomment the line
        // that follows.
        @SuppressWarnings("resource")
		BufferedReader buffer = new BufferedReader(new FileReader(filename));

        String line;
        int row = 0;
        int size = 0;

        while ((line = buffer.readLine()) != null) {
            String[] vals = line.trim().split("\\s+");

            // Lazy instantiation.
            if (matrix == null) {
                size = vals.length;
                matrix = new int[size][size];
            }

            for (int col = 0; col < size; col++) {
                matrix[row][col] = Integer.parseInt(vals[col]);
            }
            row++;
        }

        return matrix;
    }
    
    
    /*
     * A function that will write the 2D array data to file
     * 
     */
    public static void write2DMatrixFile(int[][] matData) throws Exception {
    	
    	/*
    	 * Result from Sequential matrix multiplication data files
    	 * from 1k to 8k 
    	 */
    	// matrix C result of 1k by 1k
    	File file = new File("C:\\Users\\MirAL\\Desktop\\Thesis Workspace\\GPUMatrixOperations\\tests\\Data\\Result Data\\matC1k.txt");
    	
    	// matrix C result of 2k by 2k
    	//File file = new File("C:\\Users\\MirAL\\Desktop\\Thesis Workspace\\GPUMatrixOperations\\tests\\Data\\Result Data\\matC2k.txt");
    	
    	// matrix C result of 3k by 3k
    	//File file = new File("C:\\Users\\MirAL\\Desktop\\Thesis Workspace\\GPUMatrixOperations\\tests\\Data\\Result Data\\matC3k.txt");
    	
    	// matrix C result of 4k by 4k
    	//File file = new File("C:\\Users\\MirAL\\Desktop\\Thesis Workspace\\GPUMatrixOperations\\tests\\Data\\Result Data\\matC4k.txt");
    	
    	// matrix C result of 5k by 5k
    	//File file = new File("C:\\Users\\MirAL\\Desktop\\Thesis Workspace\\GPUMatrixOperations\\tests\\Data\\Result Data\\matC5k.txt");
    	
    	// matrix C result of 6k by 6k
    	//File file = new File("C:\\Users\\MirAL\\Desktop\\Thesis Workspace\\GPUMatrixOperations\\tests\\Data\\Result Data\\matC6k.txt");
    	
    	// matrix C result of 7k by 7k
    	//File file = new File("C:\\Users\\MirAL\\Desktop\\Thesis Workspace\\GPUMatrixOperations\\tests\\Data\\Result Data\\matC7k.txt");
    	
    	// matrix C result of 8k by 8k
    	//File file = new File("C:\\Users\\MirAL\\Desktop\\Thesis Workspace\\GPUMatrixOperations\\tests\\Data\\Result Data\\matC8k.txt");
    	
    	
    	PrintWriter fw = new PrintWriter(file);
    	
    	String data;
     	for (int i = 0; i < matData.length; i++) {
             data = "";
     		for (int j=0; j < matData[i].length; j++){
                     data = data + matData[i][j];                    
                     if (!(j+1 >= matData[i].length)) data = data + "  ";                        
     		}
            fw.write(data);
     		if (!(i + 1 >= matData.length)) fw.println(); 
     	}
    		
    	fw.flush();
    	fw.close();
    }
}
