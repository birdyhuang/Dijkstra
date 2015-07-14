1.use bst rather than linked list to generate the edges
2.add a "parent" field to boost the random delete operation
3.How to organize the graph? 
	- class Graph, (what can it do?) 
	- class LinkedListNode, to store the head and tail of neighbours 
		of each node
	- class Neighbour, used to store the adjacency list
	- LinkedListNode[] array, id as index, to boost the decreaseKey 
		operation(get the reference of any node)
4.What's the relationship between graph and the tree structure?
	- The graph is the whole topo info. The tree is just a functional 
		structure related the Dijkstra. For example, the graph store 
		the edges info(neighbours) while the tree just store the dist 
		info of the min node, which cares only about the node with min 
		dist(and its neighbours).  
5.When will be min be updated to a new node?
	- When a node is inserted to the root list
		a.initialization
		b.removeMin
		c.decreaseKey
	- All above operations make use of "add" operation. 
6.When can the min be updated to null?
	- Only removeMin and the min is the only node in the heap.
7.What does the "add(theNode, head)" do?
	- Just connects the theNode with head.
	- Notice that the head cannot be null. If null, need different 
		operations corresponding to the circunstance(a.b.c above).
8.How to traverse the root list of a Fibonacci Heap?
	- Originally, try to use a pointer to the head to remember the position 
		of the beginning, while "iterator == head" it stops. But when 
		doing the combine, the head might not be in the root list anymore.
	- Make use of the characteristic of the Fibonacci Heap. In this type 
		of heap, we can only find the "min" as its representative. Use a 
		"takeOutMin" function, which remove the min but do not combine.
	- While "min==null", all roots in the root list are visited.
9.How to update the min pointer in the removeMin operation?
	- Once we pair-wise combine the roots, we need to reconstruct the heap 
		by inserting them one by one into the root list.
10.How to reduce the posibility of generating an already existed edge?
	- The possibility of the existence of an edge is y(# of edges)/x(max)
	- So use an credit(100 for instance), split it into 100 units. 
		y/x*100=z units(digits) in this 100 units represent that the edge 
		exists. The rest (100 - y/x*100) units(digits) represent the 
		non-existence of the edge.
	- Then randomly generates a number(1~100), if this number is one of 
		those z units, then it means this edge exists, so add it into the 
		graph. Otherwise(the rest units), this edge doesn't exist.
	- We traverse all the edges, not matter it is added to the graph or
		not. When the un-traversed # of edges equals to the actually 
		needed, add all the rest of un-traversed edges into the graph. 


* Generic(similar to template in C++)
* Initialization of class array: 
	Animals[] a = new Animals[5]; // this is just the declaration of the 
							 // array, it doesn't call on the constructor
	a[i] = new Animals();	// this instantiates the object in side the 
							// class array
