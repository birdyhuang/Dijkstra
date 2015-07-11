1.use bst rather than linked list to generate the edges
2.add a "parent" field to boost the random delete operation
3.How to organize the graph? 
	- class Graph, (what can it do?) 
	- class Node, used to store the adjacency list
	- Node[] array, id as index, to boost the decreaseKey operation(get 
		the reference of any node)
4.What's the relationship between graph and the tree structure?
	- The graph is the whole topo info. The tree is just a functional 
		structure related the Dijkstra. For example, the graph store 
		the edges info(neighbours) while the tree just store the dist 
		info of the min node, which cares only about the node with min 
		dist(and its neighbours).  
