import java.io.*;
import java.util.Random;

/**
 * Created by peng on 6/29/15.
 */

class MyPrint {
    public static void myPrint(Node[] list) {
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
//            System.out.println("id:"+root.id+" -- left:"+root.left.id);
            System.out.println(" -- left:" + root.left.id);
            if(root.right != null) System.out.println(" -- right:"+root.right.id);
            myPrint(root.left);
        }
        if(root.right != null) {
//            System.out.println("id:"+root.id+" -- right:" + root.right.id);
            myPrint(root.right);
        }
    }
    public static void myPrint(LeftistTreeNode[] tree, int id) {
        System.out.println("id:"+id+" -- rank:"+tree[id].rank+" -- dist:"+tree[id].dist+
        " -- parent:"+tree[id].parent+" -- left:"+tree[id].left+" -- right:"+tree[id].right);
    }
}

class Node {
//    int dist;
    Neighbour head;
    Neighbour tail;

    public Node() {
//        dist = Dijkstra.INFINITE;
        head = null;
        tail = null;
    }
    public boolean contains(int id) {
        if(this.head != null) {
            Neighbour it = this.head;
            while(it.next != null) {
                if(it.id == id) return true;
                it = it.next;
            }
            if(it != null)
                if(it.id == id) return true;
        }
        return false;
    }

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

class MyGraph {

    int numVertex;  // number of nodes
    int numEdge;  // number of edges
    int x;  // source node
    float d;  // density of edges

    Node[] adjList;

    public MyGraph(int n, float d, int x) {
        this.x = x;
        this.numVertex = n;
        this.d = d;
        numEdge = (int)(n*(n-1)/2.0 * d);

        System.out.println("SourceNode:" + x + " -- # of Node:" +
                numVertex + " -- # of Edge:" + numEdge);
        adjList = new Node[numVertex];
        // initialize the adjacency list
        adjList = new Node[numVertex];
        for(int i=0; i < numVertex; i++) {
            adjList[i] = new Node();
        }

        // generate edges
        for(int i=0; i < numEdge; i++){
            Random random = new Random(System.currentTimeMillis()*i);
            int u = random.nextInt(n);
            int v = random.nextInt(n);
            // skip this if u & v already connected
            if(u==v || adjList[u].contains(v)) {
                i --;
                continue;
            }
            int w = random.nextInt(1000) + 1;
            putAdjList(u, v, w);
            putAdjList(v, u, w);
        }
        MyPrint.myPrint(adjList);
        System.out.println();
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
        adjList = new Node[numVertex];
        for(int i=0; i < numVertex; i++) {
            adjList[i] = new Node();
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
        System.out.println("---------------------- THE GRAPH ----------------------");
        MyPrint.myPrint(adjList);
        System.out.println();
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
}

class LeftistTreeNode {
    int rank;
    int id;
    long dist;
    // tree structure
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
    private int capacity;   // number of nodes in tree
    private Node[] adjList;

    public LeftistTree(Node[] inputList, int sid) {
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
    public LeftistTreeNode getRoot() {
        return root;
    }
    public long getDist(int id) {
        return leftistTree[id].dist;
    }
    // Empty the tree info of this node
    private void empty(int id) {
        leftistTree[id].rank = Dijkstra.INFINITE;
        leftistTree[id].left = null;
        leftistTree[id].right = null;
        leftistTree[id].parent = null;
        capacity --;
    }
    // Remove from the tree structure, add to the path
    public int removeMin() {
        int id = root.id;
        prevRoot = root;
        // get the new root
        root = merge(root.left, root.right);
        // the tree is not empty
        if(root != null) {
            root.parent = null;
            empty(id);
            // add the new root to the pathprevRoot.next = root;

            root.prev = prevRoot;
        }
        // otherwise, the tree is empty
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
        // Use dist as the comparable key of the tree
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

        // To make it a leftist tree, keep left with bigger rank
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

    public void reinsertNode(int id) {
        LeftistTreeNode parent = leftistTree[id].parent;
        // the to be reinserted node is a child node
        if(parent != null) {
            // left child of its parent
            if(parent.left.id == id)
                parent.left = null;
            // right child of its parent
            else if(parent.right.id == id)
                parent.right = null;
            cascadingCheck(parent);
            root = merge(root, leftistTree[id]);
            root.parent = null;
        }
        // else, it is the root, do nothing
    }
    public void decreaseKey(int id, long newDist) {
        leftistTree[id].dist = newDist;
        reinsertNode(id);
    }
}

public class Dijkstra {
    public final static int INFINITE = 999999;
    private MyGraph myGraph;
    private LeftistTree tree;

    private boolean traversal[];
    // tree the the Q, when root is null -> Q is empty

    public Dijkstra(MyGraph myGraph, int type) {
        this.myGraph = myGraph;
        switch (type) {
            case 0: // Leftist
                tree = new LeftistTree(myGraph.adjList, myGraph.x);
                break;
            case 1: // Fibonnaci
                break;
            case 2: // Both
                tree = new LeftistTree(myGraph.adjList, myGraph.x);
                break;
        }
    }

    public void relax(int u, Neighbour vNeighbour) {
        int v = vNeighbour.id;
        if(tree.getDist(u) + vNeighbour.w < tree.getDist(v)) {
            long newDist = tree.getDist(u) + vNeighbour.w;
            tree.decreaseKey(v, newDist);
//            tree.leftistTree[u].next = tree.leftistTree[vid];
//            tree.leftistTree[vid].prev = tree.leftistTree[u];
        }
    }

    public void DijkstraAlg(MyGraph myGraph, int x) {
        int u;

        traversal = new boolean[myGraph.numVertex];
        for(int i=0; i < myGraph.numVertex; i++) traversal[i] = false;

        // main for loop
        while(tree.getRoot() != null) {
            u = tree.removeMin();
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
            if(tree.getRoot() != null) {
                MyPrint.myPrint(tree.getRoot());
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
//        String[] cmd = ("-r 1000 0.01 3").split(" ");
        if(cmd.length != 4 && cmd.length != 2) {
            System.err.println("Usage: [-r n d x] or [-f/-l filename]");
            System.exit(0);
        }
        if(cmd[0].equals("-r")){    // -r n d x
            type = 2;
            int n = Integer.valueOf(cmd[1]);
            float d = Float.valueOf(cmd[2]);
            int x = Integer.valueOf(cmd[3]);
            myGraph = new MyGraph(n, d, x);
        }else if(cmd[0].equals("-l") || cmd[0].equals("-f")){ // -lor-f filename
            if(cmd[0].equals("-l")) {
                type = 0;
            }else{
                type = 1;
            }
            System.out.println(cmd[0]);
            myGraph = new MyGraph(cmd[1]);
        }else{
            myGraph = null;
            type = Dijkstra.INFINITE;
            System.err.println("Usage: [-r n d x] or [-f/-l filename]");
            System.exit(0);
        }

        // Initialize the tree structure
        dijkstra = new Dijkstra(myGraph, type);
        System.out.println("------------------- ORIGINAL TREE ------------------------");
        MyPrint.myPrint(dijkstra.tree.getRoot());

        // Run the Dijkstra algorithm
        dijkstra.DijkstraAlg(myGraph, myGraph.x);
    }
}