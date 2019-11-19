package unsw.dungeon.EnemyStrategy;

import java.awt.geom.Point2D;
import java.util.ArrayList;


import unsw.dungeon.Dungeon;
import unsw.dungeon.EntityList.Portal;

public abstract class PathFind {
	private static int i = 0;
	static ArrayList <Integer>finalPath = new ArrayList<>();
	static int Up = -1;
	static int Down = -1;
	static int Right = -1;
	static int Left = -1;
	static Point2D UpPoint = null;
	static Point2D DownPoint = null;
	static Point2D RightPoint = null;
	static Point2D LeftPoint = null;
	static ArrayList<Integer> portalPoints;
	  
    static final int NO_PARENT = -1; 
  
    // Function that implements Dijkstra's 
    // single source shortest path 
    // algorithm for a graph represented  
    // using adjacency matrix 
    // representation 
    public static void dijkstra(int[][] adjacencyMatrix, int startVertex, int target, Dungeon dungeon) 
    { 
    	
    	finalPath = new ArrayList<>();
        int nVertices = adjacencyMatrix[0].length; 
  
        // shortestDistances[i] will hold the 
        // shortest distance from src to i 
        int[] shortestDistances = new int[nVertices]; 
  
        // added[i] will true if vertex i is 
        // included / in shortest path tree 
        // or shortest distance from src to  
        // i is finalized 
        boolean[] added = new boolean[nVertices]; 
  
        // Initialize all distances as  
        // INFINITE and added[] as false 
        for (int vertexIndex = 0; vertexIndex < nVertices; vertexIndex++) 
        { 
            shortestDistances[vertexIndex] = Integer.MAX_VALUE; 
            added[vertexIndex] = false; 
        } 
          
        // Distance of source vertex from 
        // itself is always 0 
        shortestDistances[startVertex] = 0; 
  
        // Parent array to store shortest 
        // path tree 
        int[] parents = new int[nVertices]; 
  
        // The starting vertex does not  
        // have a parent 
        parents[startVertex] = NO_PARENT; 
        
        //System.out.println(nVertices);
        // Find shortest path for all  
        // vertices 
        for (int i = 1; i < nVertices; i++) { 
  
            // Pick the minimum distance vertex 
            // from the set of vertices not yet 
            // processed. nearestVertex is  
            // always equal to startNode in  
            // first iteration. 
            int nearestVertex = -1; 
            int shortestDistance = Integer.MAX_VALUE; 
            for (int vertexIndex = 0; vertexIndex < nVertices;  vertexIndex++) { 
                if (!added[vertexIndex] && shortestDistances[vertexIndex] <  shortestDistance)  { 
                    nearestVertex = vertexIndex; 
                    shortestDistance = shortestDistances[vertexIndex]; 
                } 
            } 
            
            if (nearestVertex != -1) {
	            // Mark the picked vertex as 
	            // processed 
	            added[nearestVertex] = true;
	            
	            
	            
	           
	            // Update dist value of the 
	            // adjacent vertices of the 
	            // picked vertex. 
	            Point2D portalPoint = null;
	            for (int vertexIndex = 0; vertexIndex < nVertices;  vertexIndex++) {
//	            	if (portalPoints.contains(nearestVertex)) {
//	            		portalPoint = dungeon.convertInteger(nearestVertex);
//	            		int entityUpX = (int) portalPoint.getX();
//		            	int entityUpY = (int) (portalPoint.getY() - 1);
//		            	Up = entityUpX + (entityUpY * dungeon.getWidth());
//		            	UpPoint = new Point2D.Double(entityUpX, entityUpY);
//		            	
//		            	int entityLeftX = (int) (portalPoint.getX() - 1);
//		            	int entityLeftY = (int) portalPoint.getY();
//		            	Left = entityLeftX + (entityLeftY * dungeon.getWidth());
//		            	LeftPoint = new Point2D.Double(portalPoint.getX() - 1, portalPoint.getY());
//		            	
//		            	int entityRightX = (int) (portalPoint.getX() + 1);
//		            	int entityRightY = (int) portalPoint.getY();
//		            	Right = entityRightX + (entityRightY * dungeon.getWidth());
//		            	RightPoint = new Point2D.Double(portalPoint.getX() + 1, portalPoint.getY());
//		            	
//		            	int entityDownX = (int) portalPoint.getX();
//		            	int entityDownY = (int) (portalPoint.getY() + 1);
//		            	Down = entityDownX + (entityDownY * dungeon.getWidth());
//		            	DownPoint = new Point2D.Double(portalPoint.getX(), portalPoint.getY() + 1);
//		            	
//		            	if (dungeon.check(portalPoint, UpPoint)) {
//		            		adjacencyMatrix[nearestVertex][Up] = 1;
//		            	} else if (dungeon.check(portalPoint, LeftPoint)) {
//		            		adjacencyMatrix[nearestVertex][Left] = 1;
//		            	} else if (dungeon.check(portalPoint, RightPoint)) {
//		            		adjacencyMatrix[nearestVertex][Right] = 1;
//		            	}	else if (dungeon.check(portalPoint, DownPoint)) {
//		            		adjacencyMatrix[nearestVertex][Down] = 1;
//		            	}
//	            	}
//	            	Point2D portalPoint = dungeon.convertInteger(nearestVertex);
//	        		Portal portal = dungeon.checkPortal((int)portalPoint.getX(),(int)portalPoint.getY());
//		        	if (portal != null) {
////		        		System.out.println("=================PORTAL FOUND======================");
////		        		System.out.println("portal found from: " + (int)portal.getX() + " : "+ (int)portal.getY());
//		        		int entityUpX = portal.getX();
//		            	int entityUpY = portal.getY() - 1;
//		            	Up = entityUpX + (entityUpY * dungeon.getWidth());
//		            	UpPoint = new Point2D.Double(entityUpX, entityUpY);
//		            	
//		            	int entityLeftX = portal.getX() - 1;
//		            	int entityLeftY = portal.getY();
//		            	Left = entityLeftX + (entityLeftY * dungeon.getWidth());
//		            	LeftPoint = new Point2D.Double(portal.getX() - 1, portal.getY());
//		            	
//		            	int entityRightX = portal.getX() + 1;
//		            	int entityRightY = portal.getY();
//		            	Right = entityRightX + (entityRightY * dungeon.getWidth());
//		            	RightPoint = new Point2D.Double(portal.getX() + 1, portal.getY());
//		            	
//		            	int entityDownX = portal.getX();
//		            	int entityDownY = portal.getY() + 1;
//		            	Down = entityDownX + (entityDownY * dungeon.getWidth());
//		            	DownPoint = new Point2D.Double(portal.getX(), portal.getY() + 1);
//		            	
//		            	if (dungeon.check(portalPoint, UpPoint)) {
//		            		adjacencyMatrix[nearestVertex][Up] = 1;
//		            	} else if (dungeon.check(portalPoint, LeftPoint)) {
//		            		adjacencyMatrix[nearestVertex][Left] = 1;
//		            	} else if (dungeon.check(portalPoint, RightPoint)) {
//		            		adjacencyMatrix[nearestVertex][Right] = 1;
//		            	}	else if (dungeon.check(portalPoint, DownPoint)) {
//		            		adjacencyMatrix[nearestVertex][Down] = 1;
//		            	}
//		        	}
	                int edgeDistance = adjacencyMatrix[nearestVertex][vertexIndex]; 
	                  
	                if (edgeDistance > 0 && ((shortestDistance + edgeDistance) <  shortestDistances[vertexIndex])) { 
	                    parents[vertexIndex] = nearestVertex; 
	                    shortestDistances[vertexIndex] = shortestDistance + edgeDistance; 
	                }
	                
	                if (portalPoint != null) {
		            		adjacencyMatrix[nearestVertex][Up] = 0;
		            		adjacencyMatrix[nearestVertex][Left] = 0;
		            		adjacencyMatrix[nearestVertex][Right] = 0;
		            		adjacencyMatrix[nearestVertex][Down] = 0;
	                }
	            }
        	}
        } 
        calculatePath(target, parents);
        //System.out.println(finalPath + "starting from " + startVertex + " to: " + target);
    } 
  
    // Function to print shortest path 
    // from source to currentVertex 
    // using parents array 
    private static void calculatePath(int currentVertex, 
                                  int[] parents) 
    { 
        // Base case : Source node has 
        // been processed 
        if (currentVertex == NO_PARENT) 
        { 
            return; 
        } 
    	
        calculatePath(parents[currentVertex], parents); 
        finalPath.add(currentVertex);
        
    }
    
    static int V;
    
    int minDistance(int dist[], Boolean sptSet[]) 
    { 
        // Initialize min value 
        int min = Integer.MAX_VALUE, min_index = -1; 
  
        for (int v = 0; v < V; v++) 
            if (sptSet[v] == false && dist[v] <= min) { 
                min = dist[v]; 
                min_index = v; 
            } 
  
        return min_index; 
    } 
  
    // A utility function to print the constructed distance array 
    int printSolution(int dist[], int destination) 
    { 
        System.out.println("Vertex \t\t Distance from Source"); 
        for (int i = 0; i < V; i++) 
        	if (i == destination) {
        		System.out.println(i + " \t\t " + dist[i]); 
        		return dist[i];
        	}
        
        return 0;
    } 
  
    // Function that implements Dijkstra's single source shortest path 
    // algorithm for a graph represented using adjacency matrix 
    // representation 
    int calculateDistance(int graph[][], int src, int destination, Dungeon dungeon) { 
        int dist[] = new int[V]; // The output array. dist[i] will hold 
        // the shortest distance from src to i 
        


  
        // sptSet[i] will true if vertex i is included in shortest 
        // path tree or shortest distance from src to i is finalized 
        Boolean sptSet[] = new Boolean[V]; 
  
        // Initialize all distances as INFINITE and stpSet[] as false 
        for (int i = 0; i < V; i++) { 
            dist[i] = Integer.MAX_VALUE; 
            sptSet[i] = false; 
        } 
  
        // Distance of source vertex from itself is always 0 
        dist[src] = 0; 
  
        // Find shortest path for all vertices 
        for (int count = 0; count < V - 1; count++) { 
            // Pick the minimum distance vertex from the set of vertices 
            // not yet processed. u is always equal to src in first 
            // iteration. 
            int u = minDistance(dist, sptSet); 
  
            // Mark the picked vertex as processed 
            sptSet[u] = true; 
            
            Point2D sourcePortal = dungeon.convertInteger(u);
            if (portalPoints.contains(u)) {
            	//System.out.println("source portal at" + (int) sourcePortal.getX() + ":" + (int) sourcePortal.getY());
            }
  
            // Update dist value of the adjacent vertices of the 
            // picked vertex. 
            for (int v = 0; v < V; v++) {
            	Point2D portalPoint = dungeon.convertInteger(v);
            	
            	
            	
            	//paths OUT of portal
            	if (portalPoints.contains(v)) {         		
            		//System.out.println("source portal at" + (int) sourcePortal.getX() + ":" + (int) sourcePortal.getY());
            		//System.out.println("end at" + (int) portalPoint.getX() + ":" + (int) portalPoint.getY());
            		int entityUpX = (int) portalPoint.getX();
	            	int entityUpY = (int) (portalPoint.getY() - 1);
	            	Up = entityUpX + (entityUpY * dungeon.getWidth());
	            	UpPoint = new Point2D.Double(entityUpX, entityUpY);
	            	
	            	
	            	int entityLeftX = (int) (portalPoint.getX() - 1);
	            	int entityLeftY = (int) portalPoint.getY();
	            	Left = entityLeftX + (entityLeftY * dungeon.getWidth());
	            	LeftPoint = new Point2D.Double(portalPoint.getX() - 1, portalPoint.getY());
	            	
	            	
	            	int entityRightX = (int) (portalPoint.getX() + 1);
	            	int entityRightY = (int) portalPoint.getY();
	            	Right = entityRightX + (entityRightY * dungeon.getWidth());
	            	RightPoint = new Point2D.Double(portalPoint.getX() + 1, portalPoint.getY());
	            	
	            	
	            	int entityDownX = (int) portalPoint.getX();
	            	int entityDownY = (int) (portalPoint.getY() + 1);
	            	Down = entityDownX + (entityDownY * dungeon.getWidth());
	            	DownPoint = new Point2D.Double(portalPoint.getX(), portalPoint.getY() + 1);
	            	
	            	
	            	if (dungeon.check(portalPoint, UpPoint)) {
	            		graph[v][Up] = 1;
	            		//System.out.println("move Up" + (int) UpPoint.getX() + ":" + (int) UpPoint.getY());
	            	} 
	            	
	            	if (dungeon.check(portalPoint, LeftPoint)) {
	            		graph[v][Left] = 1;
	            		//System.out.println("move left" + (int) LeftPoint.getX() + ":" + (int) LeftPoint.getY());
	            	} 
	            	
	            	if (dungeon.check(portalPoint, RightPoint)) {
	            		graph[v][Right] = 1;
	            		//System.out.println("move Right" + (int) RightPoint.getX() + ":" + (int) RightPoint.getY());
	            	}	
	            	
	            	if (dungeon.check(portalPoint, DownPoint)) {
	            		graph[v][Down] = 1;
	            		//System.out.println("move Down" + (int) DownPoint.getX() + ":" + (int) DownPoint.getY());
	            	}
            	}
	        	
                // Update dist[v] only if is not in sptSet, there is an 
                // edge from u to v, and total weight of path from src to 
                // v through u is smaller than current value of dist[v] 
                if (!sptSet[v] && graph[u][v] != 0 && dist[u] != Integer.MAX_VALUE && dist[u] + graph[u][v] < dist[v]) {
                    dist[v] = dist[u] + graph[u][v]; 
                }
                
            } 
        }
        
        // print the constructed distance array 
        return printSolution(dist, destination); 
    } 
    
}
