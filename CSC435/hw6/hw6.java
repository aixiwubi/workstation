package project1;
import java.util.ArrayList;

public class Solution {
	
	
    public static ArrayList<String> solve(ArrayList<String> data) {
    	double [][] reference;
    	ArrayList<String> arraylist = new ArrayList();
    	//parse input into a 2d matrix
    	parseInput parse = new parseInput();
    	reference = parse.parseInput(data);
    	//call the solve function to do calculations
    	p6 solve = new p6(data.size(),data.size(),data.size(),reference);
    	ArrayList<Integer> result = solve.findPath();
    	//if list is empty, then return none;
    	if(result.isEmpty()){
    		arraylist.add("none");
    		return arraylist;
    	}
    	//or return the path//
    	else{
    	String stringResult = "";
		
    	for(int i = 0;i<result.size();i++){
			String temp = result.get(i).toString();
			stringResult = stringResult.concat(temp+" ");
			
		}
    	arraylist.add(stringResult);
    	return arraylist;
    	}
    }
}
