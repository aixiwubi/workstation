package project1;
import java.util.ArrayList;

public class Solution {
	
	
    public static ArrayList<String> solve(ArrayList<String> data) {
    
    	ArrayList<String> a = new ArrayList();
    	P7HashTable b = new P7HashTable(data);
    	p7 c = new p7();
    	String x =c.findPath(data);
    	
    	a.add(x);
    	return a;
    }
}
