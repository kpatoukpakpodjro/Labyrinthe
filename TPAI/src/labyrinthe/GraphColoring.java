package labyrinthe;

import java.util.*;
import java.util.LinkedList;
class GraphColoring
{
	private int V; // nb de noeud
	private LinkedList<Integer> adj[]; //Liste d'adjacence
	GraphColoring(int v)
	{
		V = v;
		adj = new LinkedList[v];
		for (int i=0; i<v; ++i)
			adj[i] = new LinkedList();
	}
	void addEdge(int v,int w)
	{
		adj[v].add(w);
		adj[w].add(v); //Graph is undirected
	}
	
	void greedyColoring()
	{
		int result[] = new int[V];
		// Initialize all vertices as unassigned
		Arrays.fill(result, -1);
		// Assign the first color to first vertex
		result[0] = 0;
		boolean available[] = new boolean[V];
		Arrays.fill(available, true);

		for (int u = 1; u < V; u++)
		{
			Iterator<Integer> it = adj[u].iterator() ;
			while (it.hasNext())
			{
				int i = it.next();
				if (result[i] != -1)
					available[result[i]] = false;
			}

			int cr;
			for (cr = 0; cr < V; cr++){
				if (available[cr])
					break;
			}

			result[u] = cr; 
			Arrays.fill(available, true);
		}
		for (int u = 0; u < V; u++)
			System.out.println("Noeud  " + (u +1)+ " ---> couleur "+ result[u]);
	}

	public static void main(String[] args){
		GraphColoring g1 = new GraphColoring(12);
		g1.addEdge(0, 1);
		g1.addEdge(0, 2);
		g1.addEdge(0, 3);
		g1.addEdge(1, 2);
		g1.addEdge(1,7);
		g1.addEdge(2,3);
		g1.addEdge(2,7);
		g1.addEdge(2,4);
		g1.addEdge(3,4);
		g1.addEdge(3,5);
		g1.addEdge(4,5);
		g1.addEdge(4,6);
		g1.addEdge(4,7);
		g1.addEdge(5,6);
		g1.addEdge(6,7);
		g1.addEdge(6,8);
		g1.addEdge(7,8);
		g1.addEdge(8,9);
		g1.addEdge(9,10);
		g1.addEdge(10,11);


		System.out.println("Coloriage du graphe");
		g1.greedyColoring();


   }
}

