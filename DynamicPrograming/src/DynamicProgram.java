
import java.io.*;
import java.util.*;

public class DynamicProgram {
	
	public static void Plan(int p, int n, int[] y, int c, int[] salaries) {
		 // dp[i][j] i:years j:demands
		int sum=0;
		int min_cost=0;
		 for(int i=0;i<y.length;i++) {
			 sum += y[i];
		 }
		
	    int[][] dp = new int[n+1][sum+1];
	    for(int i=0;i<y.length;i++) {
	    	dp[0][i]=salaries[i];
	    }
	    
	    for (int i = 1; i <= n + 1; i++) {
	    	System.out.println();
	    	System.out.println(i + ".year: ");
	    	
	    	for(int j = 0; j <= sum + 1; j++) {
	    		int control=1000;// min control
	    		if(y[i-1]>p) {
	    			
	    			
	    			for(int k=0;k<j+2;k++) {
	    				dp[i][j]=dp[i-1][j-k+1] + k*c;
	    				control=Math.min(control, dp[i][j]);
	    			}
	    			dp[i][j]=control;
	    		}
	    		else if(y[i-1]<=p) {
	    			
	    			for(int k=0;k<j+1;k++) {
	    				dp[i][j]=dp[i-1][j] + k*c;
	    				control=Math.min(control, dp[i][j]);
	    			}
	    			dp[i][j]=control;
	    			
	    		}
	    		
	    		System.out.print(dp[i][j] + " ");
	    	}
	    	
	    	min_cost += dp[i][0];
	    }
	    System.out.println();
	 System.out.println("MÝN COST . " + min_cost);
	}
	
	


	public static void main(String[] args) throws FileNotFoundException {
		// TODO Auto-generated method stub

		
		int p=5;
		int n=50;
		int c=10;
		int[] salaries = new int[310];
		int[] y = new int[50];
		int count=0;
		
		try {
            File file = new File("demand.txt");
            Scanner scanner1 = new Scanner(file);

            
            int index = 0;
            while (scanner1.hasNextLine()) {
            	String line = scanner1.nextLine();
            	if(index!=0) {
                
                String[] parts = line.split("\t"); // divide line with tab

                int demand = Integer.parseInt(parts[1]);
                y[index-1] = demand;
                
            	}
            	
                index++;
            }

            scanner1.close();

            

        } catch (FileNotFoundException e) {
            System.out.println("Dosya bulunamadý.");
        }
		
		
		
		try {
            File file = new File("salary.txt");
            Scanner scanner2 = new Scanner(file);

            
            int index = 0;
            while (scanner2.hasNextLine()) {
            	String line = scanner2.nextLine();
            	if(index!=0) {
                
                String[] parts = line.split("\t"); 
                int salary = Integer.parseInt(parts[1]); 
                salaries[index-1] = salary;
                
            	}
            	
                index++;
            }

            scanner2.close();

           
        } catch (FileNotFoundException e) {
            System.out.println("Dosya bulunamadý.");
        }
		Plan(p, n, y, c, salaries);
		
	}

}

