package project1;

import java.util.ArrayList;

public class parseInput {
	public double[][] parseInput(ArrayList<String> a){
		
		double [][] result = new double[a.size()][a.size()];
		for(int i = 0; i<a.size();i++){
			for(int j = 0; j<a.size(); j++){
				String[] alist = a.get(i).split(" ");
				result[i][j] = Double.parseDouble(alist[j]);
				
			}
			
		}
		return result;
		
		
	}

}
