package trees;

import java.util.*;
import graph.EdgeComparator;
import graph.*;

public class SpanningTree {
    
    public static Collection<Edge> kruskal(UnionFind u, EuclideanGraph g){
    List <Edge> mst = new LinkedList <Edge>();
    List <Edge> edges= g.getAllEdges();
    Collections.sort(edges, new EdgeComparator());
    Place x;
    Place y;
    for (Edge e : edges){
    	x=e.source;
    	y=e.target;
    	if (u.find(x)!=u.find(y)){
    		mst.add(e);
    		u.union(x, y);
    	}
    }
    return mst;
    	
    }
    
    public static Collection<Collection<Edge>> kruskal(EuclideanGraph g){
    	// Q3
    	return null;
    }
    
    public static Collection<Edge> primTree(HashSet<Place> nonVisited, Place start, EuclideanGraph g){
    	// Q4
    	return null;
    }
    
    public static Collection<Collection<Edge>> primForest(EuclideanGraph g){
    	// Q5
    	return null;
    }
    
   
}
