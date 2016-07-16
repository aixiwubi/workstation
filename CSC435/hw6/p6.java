package project1;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;

public class p6 {
	private int i; //correspond to row
	private int j;//correspond to column
	private int l;// correspond to number of exchanges
	private double bestChoice[][]; //this will hold the best choices for i to j from l-1 to l;
	private double reference[][];// this holds the basic exchange data
	
	private double answer[][][]; //this will contain the best values
	private int choice[][][] ;// this will hold the currency exchange path choices
	//constructor
	public p6(int x, int y ,int z,double data[][]){
		this.l = x;
		this.i = y;
		this.j = z;
		this.reference = data;
		this.bestChoice = new double[i][j];
		this.answer = new double[l][i][j];
		this.choice = new int [l][i][j];
		
	}
	// main algorithm: has 3 loops, on the second loop it will calculate the values from l-1 to l
	public ArrayList<Integer> findPath(){
		ArrayList<Integer> result = new ArrayList<Integer>();
		for(int m = 0; m<l;m++){
			for(int n = 0; n<i;n++){
				if(m>0){
				int temp = 0;
				for(int a = 0;a<i;a++){
					for(int b = 0 ; b<i; b++){
						bestChoice[a][b]= answer[m-1][n][temp]*reference[a][b];
						
					}
					temp++;
				}
				}
				
				for(int k = 0;k<j;k++){
					if(m <1){
					//at the first exchange, the optimal values will be the reference table	
					
						answer[m][n][k] = reference [n][k];
					}
					else{
						
					/*exchanges after first will call findbestchoices methods. it will simply return the best value
					 *  from i to j after l exchanges*/
						
					
						answer[m][n][k] = findBestChoice(n,k,m);
						
						}
					/*only when i == j (n==k), in another word: currency i back to i, will give the answer to the problem.
					 * if it is > 1.0l, it will call getPath to retrieve the choices made*/
					if(n==k&&answer[m][n][k]>1.01){
						
						result = getPath(m,n,k);
						//becasue the path is backwards, i need to reverse it.
						Collections.reverse(result);
						return result;
					}
				}
			}
		}
		
		return result;
	}
	//helper function to find the best value from i to j, the bestchoice[][] will be overwritten after one i loop;
	public double findBestChoice(int n, int k,int m){
		double best = 0;
		
		for(int a = 0; a< i;a++){
			if(bestChoice[a][k]>best){
				best = bestChoice[a][k];
				choice[m][n][k]= a;
			}
		}
		return best;
	}
	//helper function to retrieve which best choice was picked.
	public ArrayList<Integer> getPath(int l,int i,int j){
		ArrayList<Integer> path = new ArrayList<Integer>();
		path.add(j+1);
		int bridge = choice[l][i][j];
		while(l>=1){
			
			path.add(bridge+1);
			l--;
			bridge = choice[l][i][bridge];
		
		}
		path.add(i+1);
		return path;
		
	}
	
}

