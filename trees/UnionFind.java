package trees;

import graph.Place;

import java.util.HashMap;

// Q1

public class UnionFind {
	// parent relation, parent.put(src,dst) indicates that src points to dst
	private HashMap<Place, Place> parent;

	public UnionFind() {
		this.parent = new HashMap<Place, Place>();
	}

	public Place find(Place src) {
		Place father = this.parent.get(src);
		Place root;
		if (father == null) {
			return src;
		}
		
		root = find(father);
		parent.put(src, root);
		return (root);

	}

	public void union(Place v0, Place v1) {

		if (find(v0).equals(find(v1))) {
			return;
		}

		parent.put(find(v1), find(v0));
	}
}
