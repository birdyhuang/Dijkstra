/**
 * Created by peng on 6/29/15.
 */
import java.io.*;
import java.util.Random;


class MyPrint {
    public static void myPrint(LinkedListNode[] list) {
        for(int i=0; i < list.length; i++) {
            Neighbour it;
            for(it=list[i].head; it.next!=null; it=it.next) {
                System.out.println("vs:"+i+" -- vd:"+it.id+" -- weight:"+it.w);
            }
            System.out.println("vs:"+i+" -- vd:"+list[i].tail.id+" -- weight:"+list[i].tail.w);
        }
    }

    public static void myPrint(LeftistTreeNode root) {
        // id, dist, rank, left, right, parent
        System.out.print("id:" + root.id + " -- rank:" + root.rank + " -- dist:" + root.dist);
        if(root.parent != null) System.out.print(" -- parent:" + root.parent.id);
        else System.out.print(" -- parent: null");
        System.out.println();
        if(root.left != null) {
            System.out.println(" -- left:" + root.left.id);
            if(root.right != null) System.out.println(" -- right:"+root.right.id);
            myPrint(root.left);
        }
        if(root.right != null) {
            myPrint(root.right);
        }
    }
    public static void myPrint(LeftistTreeNode[] tree, int id) {
        System.out.println("id:"+id+" -- rank:"+tree[id].rank+" -- dist:"+tree[id].dist+
        " -- parent:"+tree[id].parent+" -- left:"+tree[id].left+" -- right:"+tree[id].right);
    }
}

class LinkedListNode {
//    int dist;
    Neighbour head;
    Neighbour tail;

    public LinkedListNode() {
//        dist = Dijkstra.INFINITE;
        head = null;
        tail = null;
    }
//    public boolean contains(int id) {
//        if(this.head != null) {
//            Neighbour it = this.head;
//            while(it.next != null) {
//                if(it.id == id) return true;
//                it = it.next;
//            }
//            if(it != null)
//                if(it.id == id) return true;
//        }
//        return false;
//    }

//    public void setDist(int newDist) {
//        dist = newDist;
//    }
//    public void setLeftChild(int newLeft) {
//        leftChild = newLeft;
//    }
//    public void setRightChild(int newRight) {
//        rightChild = newRight;
//    }
//    public int getD() {
//        return dist;
//    }
//    public int getLeftChild() {
//        return leftChild;
//    }
//    public int getRightChild() {
//        return rightChild;
//    }
}

class Neighbour {

    int id;
    long w;
    Neighbour next = null;

//    public Neighbour() {
//        this.id = Dijkstra.INFINITE;
//        this.w = Dijkstra.INFINITE;
//    }

    public Neighbour(int id, int w) {
        this.id = id;
        this.w = w;
    }

//    public boolean isNull() {
//        return id==Dijkstra.INFINITE ? true:false;
//    }
}

class MyBST_Node {
    MyBST_Node left, right;
    int id;

    public MyBST_Node(int id) {
        this.id = id;
        left = null;
        right = null;
    }
    public boolean biggerThan(MyBST_Node node) {
        if(node == null) return true;
        else return this.id > node.id ? true:false;
    }
}

class MyBST {
    MyBST_Node root;

    public MyBST() {
        root = null;
    }
    public boolean contains(MyBST_Node node) {
        return contains(root, node);
    }

    private boolean contains(MyBST_Node subRoot, MyBST_Node node) {
        if(root == null)
            return false;
        if(subRoot == null)
            return false;
        if(node.biggerThan(subRoot))
            return contains(subRoot.right, node);
        else
            return contains(subRoot.left, node);
    }

    public void insert(MyBST_Node node) {
        root = insert(root, node);
    }
    private MyBST_Node insert(MyBST_Node subRoot, MyBST_Node node) {
        // the ltree is empty
        if(root == null) {
            root = node;
            return root;
        }
        if(subRoot == null)
            return node;
        // otherwise
        if(node.biggerThan(subRoot))
            subRoot.right = insert(subRoot.right, node);
        else
            subRoot.left = insert(subRoot.left, node);

        return subRoot;
    }
}

class MyGraph {

    int numVertex;  // number of nodes
    int numEdge;  // number of edges
    int x;  // source node
    float d;  // density of edges
    LinkedListNode[] adjList;

    public MyGraph(int n, float d, int x) {
        this.x = x;
        this.numVertex = n;
        this.d = d;
        numEdge = (int)(n*(n-1)/2.0 * d);

        System.out.println("SourceNode:" + x + " -- # of Node:" +
                numVertex + " -- # of Edge:" + numEdge);
        // initialize the adjacency list
        adjList = new LinkedListNode[numVertex];
        for(int i=0; i < numVertex; i++) {
            adjList[i] = new LinkedListNode();
        }

        // generate edges
        MyBST[] bst = new MyBST[numVertex];
        for(int i=0; i < numEdge; i++){
            Random random = new Random(System.currentTimeMillis()*i);
            int u = random.nextInt(numVertex);
            int v = random.nextInt(numVertex);
            MyBST_Node node = new MyBST_Node(v);
            if(bst[u] == null) bst[u] = new MyBST();
            // skip this if u & v already connected
            if(u==v || bst[u].contains(node)) {
                i --;
                continue;
            }
            int w = random.nextInt(1000) + 1;
            putAdjList(u, v, w);
            putAdjList(v, u, w);
            bst[u].insert(node);
        }
//        System.out.println("---------------------- THE GRAPH ----------------------");
//        MyPrint.myPrint(adjList);
//        System.out.println();
    }
    public MyGraph(String filename) throws IOException {
        BufferedReader br = new BufferedReader(new FileReader(filename));
        // x
        this.x = Integer.valueOf(br.readLine());
        // n m
        String[] tmp = br.readLine().split(" ");
        this.numVertex = Integer.valueOf(tmp[0]);
        this.numEdge = Integer.valueOf(tmp[1]);
        System.out.println("SourceNode:" + x + " -- # of Node:" + numVertex + " -- # of Edge:" + numEdge);

        // initialize the adjacency list
        adjList = new LinkedListNode[numVertex];
        for(int i=0; i < numVertex; i++) {
            adjList[i] = new LinkedListNode();
        }

        // v1 v2 c1
        String edge;
        while((edge = br.readLine()) != null) {
            tmp = edge.split(" ");
            putAdjList(Integer.valueOf(tmp[0]),
                    Integer.valueOf(tmp[1]),
                    Integer.valueOf(tmp[2]));
            putAdjList(Integer.valueOf(tmp[1]),
                    Integer.valueOf(tmp[0]),
                    Integer.valueOf(tmp[2]));
        }
//        System.out.println("---------------------- THE GRAPH ----------------------");
//        MyPrint.myPrint(adjList);
//        System.out.println();
    }

    public void putAdjList(int vs, int vd, int w) {
        // neighbour list of vs is now empty
//        if(adjList[vs].head.isNull()) {
        if(adjList[vs].head == null) {
            adjList[vs].tail = new Neighbour(vd, w);
            adjList[vs].head = adjList[vs].tail;
        }else{
            adjList[vs].tail.next = new Neighbour(vd, w);
            adjList[vs].tail = adjList[vs].tail.next;
        }
    }

    public int traversal() {
        int count = 0;
        boolean[] counted = new boolean[adjList.length];
        for(int i=0; i < counted.length; i++) counted[i] = false;
        for(int i=0; i < adjList.length; i++) {
            if(!counted[i]) {
                counted[i] = true;
                count ++;
            }
            Neighbour it = adjList[i].head;
            while(it.next != null) {
                if(!counted[it.id]) {
                    counted[it.id] = true;
                    count ++;
                }
                it = it.next;
            }
            if(it != null) {
                if(!counted[it.id]) {
                    counted[it.id] = true;
                    count ++;
                }
            }
        }
        return count;
    }
}

class LeftistTreeNode {
    int rank;
    int id;
    long dist;
    // ltree structure
    LeftistTreeNode left;
    LeftistTreeNode right;
    LeftistTreeNode parent;
    // path structure
    LeftistTreeNode next;
    LeftistTreeNode prev;

    public LeftistTreeNode(int id) {
        this.id = id;
        this.dist = Dijkstra.INFINITE;
        this.left = null;
        this.right = null;
        this.parent = null;
        this.next = null;
        this.prev = null;
        this.rank = 1;
    }
}

class LeftistTree {
    private LeftistTreeNode prevRoot;
    private LeftistTreeNode root;
    private LeftistTreeNode[] leftistTree;
    private int capacity;   // number of nodes in ltree
    private LinkedListNode[] adjList;

    public LeftistTree(LinkedListNode[] inputList, int sid) {
        adjList = inputList;
        capacity = 0;
        leftistTree = new LeftistTreeNode[adjList.length];

        // initialize the root
        leftistTree[sid] = new LeftistTreeNode(sid);
        root = leftistTree[sid];
        root.dist = 0;

        // initialize other nodes
        for(int i=0; i < adjList.length; i++) {
            if(i == sid) continue;
            leftistTree[i] = new LeftistTreeNode(i);
            insert(i);
        }
    }
    public LeftistTreeNode getMin() {
        return root;
    }
    public long getDist(int id) {
        return leftistTree[id].dist;
    }
    // Empty the ltree info of this node
    private void empty(int id) {
        leftistTree[id].rank = Dijkstra.INFINITE;
        leftistTree[id].left = null;
        leftistTree[id].right = null;
        leftistTree[id].parent = null;
        capacity --;
    }
    // Remove from the ltree structure, add to the path
    public int removeMin() {
        int id = root.id;
        prevRoot = root;
        // get the new root
        root = merge(root.left, root.right);
        // the ltree is not empty
        if(root != null) {
            root.parent = null;
            empty(id);
            // add the new root to the pathprevRoot.next = root;

            root.prev = prevRoot;
        }
        // otherwise, the ltree is empty
        return id;
    }
    public void insert(int id) {
        root = merge(root, leftistTree[id]);
        root.parent = null;
        capacity ++;
    }

//    private void swap(LeftistTreeNode left, LeftistTreeNode right) {
//        LeftistTreeNode tmp = left;
//        left = right;
//        right = tmp;
//    }
    private void swapChild(LeftistTreeNode root){
        LeftistTreeNode tmp = root.left;
        root.left = root.right;
        root.right = tmp;
    }

    public LeftistTreeNode merge(LeftistTreeNode subRoot, LeftistTreeNode theOther) {
        // All input are themselves leftist trees, thus no need to check
        if(subRoot == null) return theOther;
        if(theOther == null) return subRoot;

        // Make sure the node with smaller key is the subRoot
        // Use dist as the comparable key of the ltree
        if(subRoot.dist > theOther.dist) {
//            swap(subRoot, theOther);
            LeftistTreeNode tmp = subRoot;
            subRoot = theOther;
            theOther = tmp;
        }

        // Recursive merge the rightChild of the subRoot
        // with theOther, make the theOther the rightChild
        // of the subRoot
        subRoot.right = merge(subRoot.right, theOther);
        subRoot.right.parent = subRoot;

        // To make it a leftist ltree, keep left with bigger rank
        if(subRoot.left == null) {
//            swap(subRoot.left, subRoot.right);
            swapChild(subRoot);
//            subRoot.left = subRoot.right;
//            subRoot.right = null;
        }else {
            if (subRoot.left.rank < subRoot.right.rank) {
//                swap(subRoot.left, subRoot.right);
                swapChild(subRoot);
//                LeftistTreeNode tmp = subRoot.left;
//                subRoot.left = subRoot.right;
//                subRoot.right = tmp;
            }
            subRoot.rank = subRoot.right.rank + 1;
        }

        // Return the root of the subtree
        return subRoot;
    }

    // Check whether the parents are balanced or not
    private int cascadingCheck(LeftistTreeNode node) {
        long oldRank = node.rank;
        // the node has no left child
        if(node.left == null) {
            // has right child
            if(node.right != null) {
                swapChild(node);
            }// else, do nothing
            node.rank = 1;
        }
        // the node has left child
        else {
            // has right child
            if(node.right != null) {
                if (node.left.rank < node.right.rank) {
                    swapChild(node);
                    node.rank = node.right.rank + 1;
                }
            }else node.rank = 1;
        }
        // stop the recursion
        // 1.this is the root
        if(node.parent == null) return 0;
        // 2.this node doesn't change
        // otherwise, continue recursion
        if(oldRank != node.rank) cascadingCheck(node.parent);

        return 0;
    }

    public void takeOut(int id) {
        LeftistTreeNode parent = leftistTree[id].parent;
        // the to be reinserted node is a child node
        if(parent != null) {
            // left child of its parent
            if (parent.left.id == id)
                parent.left = null;
                // right child of its parent
            else if (parent.right.id == id)
                parent.right = null;
            cascadingCheck(parent);
        }
        leftistTree[id].parent = null;
    }
    public void decreaseKey(int id, long newDist) {
        takeOut(id);
        leftistTree[id].dist = newDist;
//        reinsertNode(id);
        if(root.id != id)
            insert(id);
    }
}

class FibonnaciTreeNode {
    private int id;
    private int degree;
    private long dist;  // element

    private FibonnaciTreeNode child;
    private FibonnaciTreeNode left, right;
    private FibonnaciTreeNode parent;

    private boolean childCut;

    public FibonnaciTreeNode(int id){
        this.id = id;
        degree = Dijkstra.INFINITE;
        dist = Dijkstra.INFINITE;
        child = null;
        left = null;
        right = null;
        parent = null;
    }
    public void setDist(long dist) {
        this.dist = dist;
    }
    public void setChildCut(boolean TF) {
        this.childCut = TF;
    }
    public void setDegree(int degree) {
        this.degree = degree;
    }
    public void setParent(FibonnaciTreeNode parent) {
        this.parent = parent;
    }
    public void setChild(FibonnaciTreeNode node) {
        this.child = node;
    }
    public void setLeft(FibonnaciTreeNode left) {
        this.left = left;
    }
    public void setRight(FibonnaciTreeNode right) {
        this.right = right;
    }
}

class FibonacciTree {
    private FibonnaciTreeNode root;
    private FibonnaciTreeNode[] fiboArray;

    public FibonacciTree(LinkedListNode[] adjlist, int sid) {
        root = new FibonnaciTreeNode(sid);

        for(int i=0; i < adjlist.length; i++) {
            if(i == sid) {
                fiboArray[i] = root;
                fiboArray[i].setDist(0);
                continue;
            }
            fiboArray[i] = new FibonnaciTreeNode(i);
        }
    }
    public FibonnaciTreeNode getRoot() {
        return root;
    }

    public void insert(FibonnaciTreeNode node) {
        merge(root, node);
    }
    public void delete(FibonnaciTreeNode node) {}
    public void decreaseKey(FibonnaciTreeNode node, long newKey) {
        node.setDist(newKey);
    }
    public FibonnaciTreeNode merge(FibonnaciTreeNode subRoot, FibonnaciTreeNode theOther) {
        return null;
    }
}


public class Dijkstra {
    public final static int INFINITE = 999999;
    private MyGraph myGraph;
    private LeftistTree ltree;
    private FibonacciTree ftree;

    private boolean traversal[];
    // ltree the the Q, when root is null -> Q is empty

    public Dijkstra(MyGraph myGraph, int type) {
        this.myGraph = myGraph;
        switch (type) {
            case 0: // Leftist
                ltree = new LeftistTree(myGraph.adjList, myGraph.x);
                break;
            case 1: // Fibonnaci
                ftree = new FibonacciTree(myGraph.adjList, myGraph.x);
                break;
            case 2: // Both
                ltree = new LeftistTree(myGraph.adjList, myGraph.x);
                ftree = new FibonacciTree(myGraph.adjList, myGraph.x);
                break;
        }
    }

    public void relax(int u, Neighbour vNeighbour) {
        int v = vNeighbour.id;
        if(ltree.getDist(u) + vNeighbour.w < ltree.getDist(v)) {
            long newDist = ltree.getDist(u) + vNeighbour.w;
            ltree.decreaseKey(v, newDist);
//            ltree.leftistTree[u].next = ltree.leftistTree[vid];
//            ltree.leftistTree[vid].prev = ltree.leftistTree[u];
        }
    }

    public void DijkstraAlg(MyGraph myGraph) {
        int u;

        traversal = new boolean[myGraph.numVertex];
        for(int i=0; i < myGraph.numVertex; i++) traversal[i] = false;

        // main for loop
        while(ltree.getMin() != null) {
            u = ltree.removeMin();
            traversal[u] = true;
            // Walk through the adjacency list of node u
            Neighbour it = myGraph.adjList[u].head;
            while(it.next != null) {
                if(!traversal[it.id]) relax(u, it);
                it = it.next;
            }
            if(it != null && !traversal[it.id])
                relax(u, it);
            System.out.println("--------------------------------------------------");
            if(ltree.getMin() != null) {
                MyPrint.myPrint(ltree.getMin());
            }
        }
    }

    public static void main(String[] args) throws IOException {
        Dijkstra dijkstra;
        MyGraph myGraph;
        int type;   // 0:Leftist, 1:Fibonnaci 2:Both

        System.out.print("Please input the command: ");
//        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
//        String[] cmd = br.toString().split(" ");
        String[] cmd = ("-l topo.txt").split(" ");
//        String[] cmd = ("-r 5000 1 3").split(" ");

        if(cmd.length != 4 && cmd.length != 2) {
            System.err.println("Usage: [-r n d x] or [-f/-l filename]");
            System.exit(0);
        }

        // split the command
        if(cmd[0].equals("-r")){    // -r n d x
            type = 2;
            int n = Integer.valueOf(cmd[1]);
            float d = Float.valueOf(cmd[2]);
            int x = Integer.valueOf(cmd[3]);
            myGraph = new MyGraph(n, d, x);
            while(myGraph.adjList.length != myGraph.traversal())
                myGraph = new MyGraph(n, d, x);
        }else if(cmd[0].equals("-l") || cmd[0].equals("-f")){ // -lor-f filename
            if(cmd[0].equals("-l")) {
                type = 0;
            }else{
                type = 1;
            }
            System.out.println(cmd[0]);
            myGraph = new MyGraph(cmd[1]);
            while(myGraph.adjList.length != myGraph.traversal())
                myGraph = new MyGraph(cmd[1]);
        }else{
            myGraph = null;
            type = Dijkstra.INFINITE;
            System.err.println("Usage: [-r n d x] or [-f/-l filename]");
            System.exit(0);
        }

        // Initialize the ltree structure
        dijkstra = new Dijkstra(myGraph, type);
        System.out.println("------------------- ORIGINAL TREE ------------------------");
        MyPrint.myPrint(dijkstra.ltree.getMin());

        // Run the Dijkstra algorithm
        dijkstra.DijkstraAlg(myGraph);
    }
}
