package APARAPI;


public class Timer {
	 private static long startTime;
	 private static long endTime;
	 private static long totalTime = 0;
	 public static void start() {
	     startTime = System.nanoTime();
	 }
	    
	 public static long end() {
	     endTime = System.nanoTime();
	     totalTime = (endTime-startTime);
	    
	     return totalTime;
	 }
	 
	  
	 public static void resetTime() {
	     totalTime = 0;
	 }
	    
	 public static void print() {
	     System.out.println("Time in nanoseconds = \t" + totalTime+" ns");	   
	 }	
}
