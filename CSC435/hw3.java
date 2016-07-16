package project1;
import java.util.ArrayList;

public class Solution {

	public static String findRoot(String preoder,String inoder,String answer){
		
		answer = findLeft(preoder,inoder,answer);
		answer = findRight(preoder,inoder,answer);
		
		answer = answer + preoder.substring(0, 1);
	
		return answer;
		

		
		
	}
	public static String findLeft(String preoder,String inoder,String answer){
		if(inoder.length()==1){
			return answer ;
		}
		
	
		for(int i = 0; i < inoder.length();i++){
			if(preoder.charAt(0)== inoder.charAt(i)){
				if(i!=0){
					answer = findRoot(preoder.substring(1,i+1),inoder.substring(0, i),answer);
				}
				
				
			}
		
		}
		return answer;
		
	}
	public static String findRight(String preoder,String inoder,String answer){
	
		if(inoder.length()==1){
			return answer;
		}
		for(int i = 0; i < inoder.length();i++){
			if(preoder.charAt(0)== inoder.charAt(i)){
				if(i!= inoder.length()-1){
				answer = findRoot(preoder.substring(i+1, preoder.length()),inoder.substring(i+1, inoder.length()),answer);
		
				}
				
				
			}
			
		}
		return answer;
	
	}
    public static ArrayList<String> solve(ArrayList<String> data) {
    	ArrayList<String> result = new ArrayList();
    	String preoder = data.get(0);
    	String inoder = data.get(1);
    	String answer = "";
    	answer = findRoot(preoder,inoder,answer);
    	result.add(answer);
    	return result;
    }
}
