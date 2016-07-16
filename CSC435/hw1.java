package project1;
import java.util.ArrayList;

public class Solution {

    public static ArrayList<String> solve(ArrayList<String> data) {
        ArrayList<String> a = new ArrayList();
        for(int i = data.size() -1; i >= 0; i--){
        	a.add(data.get(i));
        	
        }
        return a;
    }
}
