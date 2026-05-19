package training;

import java.util.Scanner;

public class ZigzagPattern {
    public static void main(String[] args) {
    	
        Scanner sc = new Scanner(System.in);
        System.out.print("Enter grid size: ");
        int grid = sc.nextInt();
     
        for (int i = 0; i < grid; i++) {	
            for (int j = 0; j < grid; j++){
       
            	int num;
            	
            	if (i % 2 == 0) {
            		num = grid * i + j + 1;
            	}
            	else {
            		num = grid * i + (grid - j);
            	}	
            	System.out.print(num + " ");
            }
           System.out.println();
            
        }
    }
}
