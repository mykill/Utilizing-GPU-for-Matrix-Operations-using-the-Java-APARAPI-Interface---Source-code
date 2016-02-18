package APARAPI;

import com.amd.aparapi.Kernel;
import com.amd.aparapi.Range;

public class AparapiKernelMatrixOperations extends Timer{

	
	 /*
     * Kernel code in Matrix Multiplication Using APARAPI
     *
     * Computations done in GPU
     * */
    public static int [] Kernel_MatMul( final int [] matA, final int [] matB, final int a, final int b, final int c, final String mode) {
        final int [] result = new int [a * c];
        Kernel kernel = new Kernel() {
            @Override
            public void run() {
                int k = getGlobalId();
                int row = k/c;
                int column = k%c;
                for ( int i = 0; i < b; i++ ){
                    result[k] += matA[ row*b + i ] * matB[ i*c + column ];
                }
            }
        };
       
        if (mode == "GPU"){
        	 kernel.setExecutionMode( Kernel.EXECUTION_MODE.GPU );
             kernel.execute( result.length );
             kernel.dispose();
             return result;
        } 
        if (mode == "CPU"){
        	kernel.setExecutionMode( Kernel.EXECUTION_MODE.CPU );
            kernel.execute( result.length );
            kernel.dispose();
            return result;
        } 
        if (mode == "JTP") {
        	kernel.setExecutionMode( Kernel.EXECUTION_MODE.JTP );
            kernel.execute( result.length );
            kernel.dispose();
            return result;
        } else {
        	kernel.setExecutionMode( Kernel.EXECUTION_MODE.SEQ );
            Range range = Range.create(result.length, 1);
            kernel.execute(range);
            kernel.dispose();
            return result;
        }  
    }
    


    /*
     * Kernel code in Matrix Addition using APARAPI
     *
     * Computations done in GPU
     * */
    public static int [] Kernel_MatAdd( final int [] matA, final int [] matB, final int a, final int b, final int c, final String mode ) {
        final int [] result = new int [a * c];
        Kernel kernel = new Kernel() {
            @Override
            public void run() {
                int k = getGlobalId();
                int getRow = k/c;
                int getColumn = k%c;
                for ( int i = 0; i < b; i++ ){
                    result[k] = matA[ getRow*b + i ] + matB[ i*c + getColumn ];
                }
            }
        };
        if (mode == "GPU"){
       	 kernel.setExecutionMode( Kernel.EXECUTION_MODE.GPU );
            kernel.execute(result.length );
            kernel.dispose();
            return result;
       } 
       if (mode == "CPU"){
       	kernel.setExecutionMode( Kernel.EXECUTION_MODE.CPU );
           kernel.execute(result.length);
           kernel.dispose();
           return result;
       } 
       if (mode == "JTP") {
       	kernel.setExecutionMode( Kernel.EXECUTION_MODE.JTP );
           kernel.execute(result.length);
           kernel.dispose();
           return result;
       } else {
    	   kernel.setExecutionMode( Kernel.EXECUTION_MODE.SEQ );
           Range range = Range.create(result.length,1);
           kernel.execute(range);
           kernel.dispose();
           return result;
       }
    }


    /*
     * Kernel code in Matrix Subtraction using APARAPI
     *
     * Computations done in GPU
     * */
    public static int [] Kernel_MatSub( final int [] matA, final int [] matB, final int a, final int b, final int c, final String mode ) {
        final int [] result = new int [a * c ];
        Kernel kernel = new Kernel() {
            @Override
            public void run() {
                int k = getGlobalId();
                int getRow = k/c;
                int getColumn = k%c;
                for ( int i = 0; i < b; i++ ){
                    result[k] = matA[ getRow*b + i ] - matB[ i*c + getColumn ];
                }
            }
        };
        
        if (mode == "GPU"){
          	 kernel.setExecutionMode( Kernel.EXECUTION_MODE.GPU );
               kernel.execute( result.length );
               kernel.dispose();
               return result;
          } 
          if (mode == "CPU"){
          	kernel.setExecutionMode( Kernel.EXECUTION_MODE.CPU );
              kernel.execute( result.length );
              kernel.dispose();
              return result;
          } 
          if (mode == "JTP") {
          	kernel.setExecutionMode( Kernel.EXECUTION_MODE.JTP );
              kernel.execute( result.length );
              kernel.dispose();
              return result;
          } else {
       	   kernel.setExecutionMode( Kernel.EXECUTION_MODE.SEQ );
              Range range = Range.create(result.length, 1);
              kernel.execute( range);
              kernel.dispose();
              return result;
          }
    } 
}
