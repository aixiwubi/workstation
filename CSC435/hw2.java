package project1;
import java.util.ArrayList;

public class Solution {

	public static String toString(ArrayList words){
		String word = (String) words.get(0);
		for(int i = 1; i< words.size(); i++){
			word = word+ ","+ words.get(i);
			
		}
		return word;
	}
	public static void swap(ArrayList a, int x, int y)
	{
		String temp = (String) a.get(x);
		a.add(x, a.get(y));
		a.remove(x+1);
		a.add(y,temp);
		a.remove(y+1);
		
	}
	public static ArrayList<String> Combine(ArrayList a,ArrayList result, int x, int y){
		if(x==y){
			
			result.add(toString(a));
		}
		else{
			for(int i = x; i<=y; i++){
				swap(a,x,i);
				Combine(a,result,x+1,y);
				swap(a,x,i);
				
			}
		}
		return result;
	}
    public static ArrayList<String> solve(ArrayList<String> data) {
    	ArrayList<String> result = new ArrayList();
    	result = Combine(data,result,0,data.size()-1);
    	return result;
    }
}
