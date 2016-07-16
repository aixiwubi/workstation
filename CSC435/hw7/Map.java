package project1;

public class Map {
	private int[][] map;
	private int[][] xdirection ;
	private int[][] ydirection;
	int size;
	public Map(int x){
		this.map = new int[x][x];
		this.xdirection = new int[x][x];
		this.ydirection = new int[x][x];
		this.size = x;
		
	}

	public int getXdirection(int x,int y){
		return this.xdirection[x][y];
	}
	public int getYdirection(int x , int y){
		return this.ydirection[x][y];
	}
	public void setXdirection(int x,int y ,int z){
		this.xdirection[x][y]=z;
	}
	public void setYdirection(int x,int y ,int z){
		this.ydirection[x][y]=z;
	}
	public int getXYCondition(int x, int y){
		return this.map[x][y];
	}
	public void setXY(int x, int y,int condition){
		//System.out.println();
		
		this.map[x][y]= condition;
		/*for(int i =0; i<size;i++){
			System.out.println();
			for(int j =0; j<size; j++){
				System.out.print(this.map[i][j]);
			}
		}*/
	}
	
	public boolean isValid(int x, int y){
		
		if(x>=0&&x<size){
			if(y>=0&&y<size){
				return true;
			}
			else{
				return false;
			}
		}
		else{
		return false;}
	}
	
	public String getUDLR(int cx, int cy,int px,int py){
		
		
		
		if(cx==px){
			if(cy<py){
				return  "L";
			}
			else{
				return "R";
			}
		}
		else{
			if(cx<px){
				return "U";
			}
			else{
				return "D";
			}
		}
		
	}


	
}
