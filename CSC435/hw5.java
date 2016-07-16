package project1;
import java.util.ArrayList;

public class Solution {
	//merge function to combine two silhouette
	public static ArrayList merge(ArrayList<Integer> a, ArrayList<Integer> b){
		ArrayList<Integer> c = new ArrayList();
		int acrt =0;
		int bcrt =0;
		int acnt = 0;
		int bcnt =0;
		
		while(acnt<a.size()||bcnt<b.size()){
		if(acnt<a.size()&&bcnt<b.size()){
			if(a.get(acnt)<b.get(bcnt)){
				if(bcrt==0){
					c.add(a.get(acnt));
					c.add(a.get(acnt+1));
					acrt = a.get(acnt+1);
					acnt =acnt+2;
				}
				else{
					if(a.get(acnt+1)>bcrt){
						c.add(a.get(acnt));
						c.add(a.get(acnt+1));
						acrt = a.get(acnt+1);
						acnt =acnt+2;
					}
					else{
						if(acrt>bcrt){
						c.add(a.get(acnt));
						c.add(bcrt);
						acrt = a.get(acnt+1);
						acnt =acnt+2;
					}
					else{
						acrt = a.get(acnt+1);
						acnt =acnt+2;
					}
						}
				}
			}
			else if(a.get(acnt)>b.get(bcnt))
			{
				if(acrt==0){
					c.add(b.get(bcnt));
					c.add(b.get(bcnt+1));
					bcrt = b.get(bcnt+1);
					bcnt =bcnt+2;
				}
				else{
					if(b.get(bcnt+1)>acrt){
						c.add(b.get(bcnt));
						c.add(b.get(bcnt+1));
						bcrt = b.get(bcnt+1);
						bcnt =bcnt+2;
					}
					else
						{
						if(bcrt>acrt){
						
						c.add(b.get(bcnt));
						c.add(acrt);
						bcrt = b.get(bcnt+1);
						bcnt =bcnt+2;
					}
					else{
						bcrt = b.get(bcnt+1);
						bcnt =bcnt+2;
					}
						}
				}
				
			}
			else if(a.get(acnt).equals(b.get(bcnt)))
			{
				if((a.get(acnt+1)>b.get(bcnt+1))){
					if(a.get(acnt+1).equals(bcrt)&&acrt<bcrt){
					
						acrt = a.get(acnt+1);
						bcrt = b.get(bcnt+1);
						acnt = acnt+2;
						bcnt = bcnt+2;
					}
					else{
						c.add(a.get(acnt));
						c.add(a.get(acnt+1));
						acrt = a.get(acnt+1);
						bcrt = b.get(bcnt+1);
						acnt = acnt+2;
						bcnt = bcnt+2;
					}
				}
				else if((b.get(bcnt+1)>a.get(acnt+1))){
					if(b.get(bcnt+1).equals(acrt)&&bcrt<acrt){
					
					acrt = a.get(acnt+1);
					bcrt = b.get(bcnt+1);
					acnt = acnt+2;
					bcnt = bcnt+2;
					}
					else{
						c.add(b.get(bcnt));
						c.add(b.get(bcnt+1));
						acrt = a.get(acnt+1);
						bcrt = b.get(bcnt+1);
						acnt = acnt+2;
						bcnt = bcnt+2;
					}
				}
				else if(a.get(acnt+1).equals(b.get(bcnt+1))){
					c.add(b.get(bcnt));
					c.add(b.get(bcnt+1));
					acrt = a.get(acnt+1);
					bcrt = b.get(bcnt+1);
					acnt = acnt+2;
					bcnt = bcnt+2;
				}
			}
		}
		else{
				if(acnt<a.size()){
					c.add(a.get(acnt));
					acnt++;
				}
				else if(bcnt<b.size()){
					c.add(b.get(bcnt));
					bcnt++;
				}
			}
		}
		
		
		return c;
	}
	//change input strings into integers with [x1,y1,x2,y2] format;
	public static ArrayList<Integer> parseString(String a){
		ArrayList<Integer> ints = new ArrayList<Integer>();
		String list[] = a.split(" ");
		ints.add(Integer.parseInt(list[0]));
		ints.add(Integer.parseInt(list[2].split(" ")[0]));
		ints.add(Integer.parseInt(list[1].split(" ")[0])+Integer.parseInt(list[0]));
		ints.add(0);
		
		return ints;
	}
	
	/* divide one list of ints into 2 lists and calling divide again until only 4 elements left in arraylist,
	then starts to merge*/
	public static ArrayList<Integer> divide(ArrayList<Integer> listofints){
		ArrayList<Integer> result = new ArrayList<Integer>();
		ArrayList<Integer> listofints1 = new ArrayList<Integer>();
		ArrayList<Integer> listofints2 = new ArrayList<Integer>();

		int numberofblocks = listofints.size()/4;
		int mid = (numberofblocks/2)*4;
		if(listofints.size()==4){
			return listofints;
		}
		else{
			listofints1.addAll(listofints.subList(0,mid));
			listofints2.addAll(listofints.subList(mid, listofints.size()));
			
			result= merge(divide(listofints1),divide(listofints2));
			return result;
		}
		
	
	/*solve function called by testclient*/	
		
	}
    public static ArrayList<String> solve(ArrayList<String> data) {
    	ArrayList<Integer> a = new ArrayList();
    	ArrayList<String> result = new ArrayList();
    	ArrayList<Integer> intresult = new ArrayList();
    	//format strings into desired integer pattern
    	for(String strings : data){
    		a.addAll(parseString(strings));
    	}
    	//divide into smaller problems
    	intresult = divide(a);
    	
    	// convert integers into strings
    	for(int ints:intresult){
    		result.add(Integer.toString(ints));
    		
    	}
		return result;
    	
    	}
}
