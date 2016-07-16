package shortestpath;
import java.util.*;

import facility.Facility;
import facility.FacilityImpl;


public class Network {
	private List<Vertex> nodes = new ArrayList<Vertex>();
	private List<Edge> edges = new ArrayList<Edge>();
	private Graph aGraph;
	private Hashtable<String, Facility> network;
	public Network(Hashtable<String,Facility> inNetwork){
		this.network = inNetwork;
		
	}
	
	public void createNetwork(){
		
		Set<String> facilities = new HashSet<String>();
		facilities = network.keySet();
	//	System.out.println(facilities);
		for(String facility: facilities){
			Vertex node = new Vertex(facility,facility);
			this.nodes.add(node);
		
			Set<String> neighbors = new HashSet<String>();
			
			neighbors = network.get(facility).getNeighbor().keySet();
		//	System.out.println(neighbors);
			for(String neighbor: neighbors){
				Vertex neighbornode = new Vertex(neighbor,neighbor);
				Edge edge = new Edge(facility+"to "+ neighbor,node,neighbornode,network.get(facility).getDistance(neighbor));
				//System.out.println(edge);
				this.edges.add(edge);
			}
		}
		this.aGraph = new Graph(this.nodes,this.edges);
		
	}
	
	public double findPath(String start,String destination){
		DijkstraAlgorithm dijkstra = new DijkstraAlgorithm(this.aGraph);
		LinkedList<Vertex> path = new LinkedList();
		DistanceToDay calc = new DistanceToDay();
		double totalDistance = 0;
		double day;
		for(int i = 0; i< nodes.size();i++){
			
			if(start.equals(nodes.get(i).getName())){
				
				dijkstra.execute(this.nodes.get(i));
				}
			
		}
		for(int j = 0; j< nodes.size();j++){
			
			if(destination.equals(nodes.get(j).getName())){
				
			   path = dijkstra.getPath(nodes.get(j));
				}
			
		}
		//System.out.println(start+ " to " + destination +": ");
		//for(Vertex vertex: path){
			//System.out.println(vertex.getName());
		//}
		for(int k = 0; k<path.size()-1;k++){
			
			totalDistance = totalDistance + network.get(path.get(k).getName()).getDistance(path.get(k+1).getName());
		}
		//System.out.println("The total Distance is: " + totalDistance + " miles. ");
		day = calc.toDay(totalDistance);
		//System.out.println(String.format("The travel time is %1$.2f days. ", day));
		return day;
		
	}
}
