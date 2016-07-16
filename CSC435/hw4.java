package project1;
import java.util.ArrayList;

public class Solution {
	
	public static ArrayList merge(ArrayList<Integer> a, ArrayList<Integer> b,ArrayList<Integer> c){
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
    public static ArrayList<String> solve(ArrayList<String> data) {
    	ArrayList<Integer> a = new ArrayList();
    	ArrayList<Integer> b = new ArrayList();
    	ArrayList<Integer> c = new ArrayList();
    	ArrayList<String> result = new ArrayList();
    	

    	

    	for(int i = 0; i<data.size();i++){
    		if(!data.get(i).equals("*****")){
    			a.add(Integer.parseInt(data.get(i).split("\n")[0]));
    		}
    		else{
    			
    			for(int j = i+1;j<data.size();j++){
    				b.add(Integer.parseInt(data.get(j).split("\n")[0]));
    			}
    			break;
    		}
    		
    	}
    	
    	c = merge(a,b,c);
    		
    	for(int k = 0; k<c.size();k++){
    		result.add(c.get(k).toString());
    	}
		return result;
    	
    	}
}
