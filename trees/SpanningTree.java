package trees;

import java.util.*;
import graph.EdgeComparator;
import graph.*;

public class SpanningTree {

	public static Collection<Edge> kruskal(UnionFind u, EuclideanGraph g) {
		List<Edge> mst = new LinkedList<Edge>();
		List<Edge> edges = g.getAllEdges();
		Collections.sort(edges, new EdgeComparator());
		Place x;
		Place y;
		for (Edge e : edges) {
			x = e.source;
			y = e.target;
			if (u.find(x) != u.find(y)) {
				mst.add(e);
				u.union(x, y);
			}
		}
		return mst;

	}

	public static Collection<Collection<Edge>> kruskal(EuclideanGraph g) {
		HashMap<Place, Collection<Edge>> dico = new HashMap<>();
		UnionFind u = new UnionFind();
		Collection<Edge> mst = kruskal(u, g);
		Collection<Edge> list1;
		for (Edge e : mst) {
			list1 = dico.get(u.find(e.source));
			if (list1 == null) {
				list1 = new LinkedList<Edge>();
			}
			list1.add(e);
			dico.put(u.find(e.source), list1);
		}
		
		return (dico.values());
	}

	public static Collection<Edge> primTree(HashSet<Place> nonVisited, Place start, EuclideanGraph g) {
		PriorityQueue<Edge> edges = new PriorityQueue<Edge>(new EdgeComparator());
		Collection<Edge> mst = new LinkedList<Edge>();
		Collection<Edge> exitingEdges = g.edgesOut(start);
		Edge currentEdge;
		for (Edge e : exitingEdges){
			edges.add(e);
		}
		nonVisited.remove(start);
		while (!edges.isEmpty()){
			currentEdge=edges.poll();
			if (!nonVisited.contains(currentEdge.target)){
				continue;
			}
			
			if (nonVisited.contains(currentEdge.target))
			{
				mst.add(currentEdge);
				nonVisited.remove(currentEdge.target);
				exitingEdges=g.edgesOut(currentEdge.target);
				for (Edge e : exitingEdges){
					edges.add(e);
				}
			}
		}
		return mst;
	}

	public static Collection<Collection<Edge>> primForest(EuclideanGraph  g) {
		Collection<Collection<Edge>> allMst = new LinkedList <Collection<Edge>> ();
		HashSet<Place> nonVisited = new HashSet<Place>();
		Set<Place> toPut = g.places();
		Place start;
		for (Place p : toPut){
			nonVisited.add(p);
		}
		while (!nonVisited.isEmpty()){
			start=nonVisited.iterator().next();
			if(g.edgesOut(start).isEmpty()){
				nonVisited.remove(start);
				continue;
			}
			allMst.add(primTree(nonVisited, start, g));
		}
		for (Collection<Edge> c : allMst){
			if (c.isEmpty()){
				System.out.println("False !!!");
			}
		}
		return allMst;
	}

}
