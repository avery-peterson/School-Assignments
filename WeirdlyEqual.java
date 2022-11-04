import java.io.*;
import java.util.*;

public class Solution {
    
    static boolean isWeirdEqual(int[] a, int[] b) {
		int n = a.length;
		if (Arrays.equals(a, b)) {
			return true;
		} else if (n%2 == 0) {
			int[] a1 = Arrays.copyOfRange(a, 0, n/2);
			int[] a2 = Arrays.copyOfRange(a, n/2, n);
			int[] b1 = Arrays.copyOfRange(b, 0, n/2);
			int[] b2 = Arrays.copyOfRange(b, n/2, n);
			if (isWeirdEqual(a1, b1) && isWeirdEqual(a2, b2)) {
				return true;
			} else if (isWeirdEqual(a1, b1) && isWeirdEqual(a1, b2)) {
				return true;
			} else if (isWeirdEqual(a2, b2) && isWeirdEqual(a2, b1)) {
				return true;
			}
		} 
		return false;
    }
    
    public static void main(String[] args) {
        /* Read input from STDIN. Print output to STDOUT. Your class should be named Solution. 

	You should be able to compile your program with the command:
   
		javac Solution.java
	
   	To conveniently test your algorithm, you can run your solution with any of the tester input files using:
   
		java Solution inputXX.txt
	
	where XX is 00, 01, ..., 13.
	*/

   	Scanner s;
	if (args.length > 0){
		try{
			s = new Scanner(new File(args[0]));
		} catch(java.io.FileNotFoundException e){
			System.out.printf("Unable to open %s\n",args[0]);
			return;
		}
		System.out.printf("Reading input values from %s.\n",args[0]);
	}else{
		s = new Scanner(System.in);
		System.out.printf("Reading input values from stdin.\n");
	}     
  
        int n = s.nextInt();
        int[] a = new int[n];
        int[] b = new int[n];
        
        for(int j = 0; j < n; j++){
            a[j] = s.nextInt();
        }
        
        for(int j = 0; j < n; j++){
            b[j] = s.nextInt();
        }
        
        System.out.println((isWeirdEqual(a, b) ? "YES" : "NO"));
    }
}
