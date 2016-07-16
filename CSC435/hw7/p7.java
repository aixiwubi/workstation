package project1;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.LinkedList;
import java.util.Queue;

public class p7 {
	private Queue<Integer> xlist = new LinkedList();
	private Queue<Integer> ylist = new LinkedList();
	public String findPath(ArrayList<String> input){
		int size = Integer.parseInt(input.get(0));
		
		Map map = new Map(size);
		
        String[] start = input.get(1).split(",");
        String[] end = input.get(2).split(",");
        map.setXY(Integer.parseInt(start[0]), Integer.parseInt(start[1]), 1);
		map.setXdirection(Integer.parseInt(start[0]), Integer.parseInt(start[1]), Integer.parseInt(start[0]));
		map.setYdirection(Integer.parseInt(start[0]), Integer.parseInt(start[1]), Integer.parseInt(start[1]));
		map.setXY(Integer.parseInt(end[0]), Integer.parseInt(end[1]), 4);
		map.setXdirection(Integer.parseInt(end[0]), Integer.parseInt(end[1]), Integer.parseInt(end[0]));
		map.setYdirection(Integer.parseInt(end[0]), Integer.parseInt(end[1]), Integer.parseInt(end[1]));
		
	
		for(int i = 3; i<input.size();i++){
			String[] black = input.get(i).split(",");
			map.setXY(Integer.parseInt(black[0]), Integer.parseInt(black[1]), 1);
		}
		
		String[] now =  input.get(1).split(",");
		xlist.add(Integer.parseInt(now[0]));
		ylist.add(Integer.parseInt(now[1]));
		
		
		while(!xlist.isEmpty()&&!ylist.isEmpty()){
			
			int x = xlist.remove();
			int y = ylist.remove();
			//System.out.println(x+","+ y);
			//System.out.println("current" + current);
	
		
			if(map.isValid(x, y+1)&&map.getXYCondition(x, y+1)!=1){
				if(map.getXYCondition(x, y+1)==4){
					map.setXdirection(x, y+1, x);
					map.setYdirection(x, y+1, y);
					break;
				}else{
				//System.out.println(map.getXYCondition(x+1,y));
				map.setXY(x, y+1, 1);
				map.setXdirection(x, y+1, x);
				map.setYdirection(x, y+1, y);
				xlist.add(x);
				ylist.add(y+1);
				//System.out.println(map.toString(x+1, y));
				}
				
			}
			if(map.isValid(x+1, y)&&map.getXYCondition(x+1, y)!=1){
				if(map.getXYCondition(x+1, y)==4){
					map.setXdirection(x+1, y, x);
					map.setYdirection(x+1, y, y);
				
					break;
				}else{
					map.setXY(x+1, y, 1);
					map.setXdirection(x+1, y, x);
					map.setYdirection(x+1, y, y);
					xlist.add(x+1);
					ylist.add(y);
				}
			}
			if(map.isValid(x, y-1)&&map.getXYCondition(x, y-1)!=1){
				if(map.getXYCondition(x, y-1)==4){
					map.setXdirection(x, y-1, x);
					map.setYdirection(x, y-1, y);
					break;
				}else{
					map.setXY(x, y-1, 1);
					map.setXdirection(x, y-1, x);
					map.setYdirection(x, y-1, y);
					xlist.add(x);
					ylist.add(y-1);
				}
			}
			if(map.isValid(x-1, y)&&map.getXYCondition(x-1, y)!=1){
				if(map.getXYCondition(x-1, y)==4){
					map.setXdirection(x-1, y, x);
					map.setYdirection(x-1, y, y);
					break;
				}else{
					map.setXY(x-1, y, 1);
					map.setXdirection(x-1, y, x);
					map.setYdirection(x-1, y, y);
					xlist.add(x-1);
					ylist.add(y);
				}
			}
			
			
		
		}
		ArrayList<String> result = new ArrayList();
		String answer="";
		
		int nowx = Integer.parseInt(end[0]);
		int nowy = Integer.parseInt(end[1]);
		//System.out.println(nowx+","+ nowy);
		//System.out.println(map.getXdirection(nowx, nowy)+","+ map.getYdirection(nowx, nowy));
		while(map.getXdirection(nowx, nowy)!=nowx||map.getYdirection(nowx, nowy)!=nowy){
			//System.out.println("in loop");
			int tempx= nowx;
			//System.out.println(map.getUDLR(nowx,nowy,map.getXdirection(nowx, nowy),map.getYdirection(nowx, nowy)));
		
			result.add((map.getUDLR(nowx,nowy,map.getXdirection(nowx, nowy),map.getYdirection(nowx, nowy))));
			nowx = map.getXdirection(nowx, nowy);
			nowy= map.getYdirection(tempx, nowy);
			
		}
		Collections.reverse(result);
		
		StringBuffer sb = new StringBuffer("");
		for(String a: result){
			sb.append(a);
		}
		answer =sb.toString();
		return answer;
	}
	public static void main(String [] args){
		p7 a = new p7();
		ArrayList<String> x = new ArrayList();
		x.add("1000");
		x.add("339,339");
		x.add("0,0");
		String result =a.findPath(x);
		System.out.println(result);
		
	}
	
}
