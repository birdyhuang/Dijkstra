/**
 * Created by peng on 6/29/15.
 */
import java.io.*;
import java.util.Map;
import java.util.Random;


class MyPrint {
    public static void myPrint(LinkedListNode[] list) {
        for(int i=0; i < list.length; i++) {
            if(list[i].head != null) {
                Neighbour it;
                for (it = list[i].head; it.next != null; it = it.next) {
                    System.out.println("vs:" + i + " -- vd:" + it.id + " -- weight:" + it.w);
                }
                System.out.println("vs:" + i + " -- vd:" + list[i].tail.id + " -- weight:" + list[i].tail.w);
            }
        }
    }

    public static void myPrint(LeftistTreeNode root) {
        // id, dist, rank, left, right, parent
        System.out.print("id:" + root.id + " -- rank:" + root.rank + " -- dist:" + root.dist);
        if(root.parent != null)
            System.out.print(" -- parent:" + root.parent.id);
        else
            System.out.print(" -- parent: null");
        System.out.println();

        if(root.left != null) {
            System.out.println(" -- left:" + root.left.id);
            if(root.right != null)
                System.out.println(" -- right:"+root.right.id);
            myPrint(root.left);
        }
        if(root.right != null)
            myPrint(root.right);
    }
    public static void myPrint(FibonacciHeap t) {
        for(int i=0; i < t.fiboArray.length; i++) {
            FibonacciHeapNode node = t.fiboArray[i];
            System.out.print("id:" + node.id + " -- degree:" + node.degree + " -- dist:" + node.dist);
            if(node.parent != null)
                System.out.print(" -- parent:"+ node.parent.id);
            else System.out.print(" -- parent: null");
            if(node.child != null)
                System.out.print(" -- child:" + node.child.id);
            else System.out.print(" -- child: null");
            System.out.print(" -- left:"+node.left.id+" -- right:"+ node.right.id);
            System.out.println();
        }
    }
//    public static void myPrint(FibonacciHeapNode min) {
////        System.out.print("id:" + min.id + " -- degree:" + min.degree + " -- dist:" + min.dist);
////        if(min.parent != null)
////            System.out.print(" -- parent:"+ min.parent.id);
////        else System.out.print(" -- parent: null");
////        System.out.println();
//
//        FibonacciHeapNode head = min;
//        FibonacciHeapNode it = min;
//        while(it.right != head) {
//            System.out.print("id:"+it.id+" -- degree:"+it.degree+" -- dist:"+it.dist);
//            if(it.parent != null)
//                System.out.print(" -- parent:"+it.parent.id);
//            else
//                System.out.print(" -- parent: null");
//            if(it.right != null)
//                System.out.println(" -- left sib: "+it.right.id);
////            if(it.left != null)
////                System.out.println(" -- left sib: "+it.right.id);
//            it = it.right;
//        }
//        System.out.println(" -- left sib: "+it.right.id);
//        if(head.c)
//    }
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
}

class MyBST {
    MyBST_Node root;

    public MyBST(MyBST_Node root) {
        this.root = root;
    }
    public boolean contains(int id) {
        if(id > root.id)
            return contains(root.right, id);
        else
            return contains(root.left, id);
    }

    private boolean contains(MyBST_Node subRoot, int id) {
        if(subRoot == null)
            return false;
        if(id == subRoot.id)
            return true;
        else if(id > subRoot.id)
            return contains(subRoot.right, id);
        else
            return contains(subRoot.left, id);
    }

    public void insert(MyBST_Node node) {
        insert(root, node);
    }
    private MyBST_Node insert(MyBST_Node subRoot, MyBST_Node node) {
        if(subRoot == null)
            return node;

        if(node.id > subRoot.id)
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
    MyBST[] bst;

    public MyGraph(int n, float d, int x) {
        this.x = x;
        this.numVertex = n;
        this.d = d;
        numEdge = (int)Math.ceil(n * (n - 1) / 2.0 * d);

        while(true) {
            System.out.println("SourceNode:" + x + " -- # of Node:" + numVertex + " -- # of Edge:" + numEdge);
            adjList = new LinkedListNode[numVertex];
            for(int i=0; i < numVertex; i++) {
                adjList[i] = new LinkedListNode();
            }

            bst = new MyBST[numVertex];
            for(int i=0; i < numVertex; i++) {
                MyBST_Node mid = new MyBST_Node(numVertex/2);
                bst[i] = new MyBST(mid);
            }
            // generate edges
            for (int i = 0; i < numEdge; i++) {
                Random random = new Random(System.currentTimeMillis() * i);
                int u = random.nextInt(numVertex);
                int v = random.nextInt(numVertex);
//                if (bst[u] == null)
//                    bst[u] = new MyBST();
//                if (bst[v] == null)
//                    bst[v] = new MyBST();
                // skip this if u & v already connected
                if (u == v || bst[u].contains(v)) {
                    i--;
                    continue;
                }
                int w = random.nextInt(1000) + 1;
                putAdjList(u, v, w);
                putAdjList(v, u, w);
                bst[u].insert(new MyBST_Node(v));
                bst[v].insert(new MyBST_Node(u));
            }
            if(traversal() == adjList.length)
                break;
            else
                System.out.println("not all connected");
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
        System.out.println("SourceNode:" + x + " -- # of Node:" + numVertex + " -- # of Edge:" + numEdge);
        System.out.println("---------------------- THE GRAPH ----------------------");
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

//    public int traversal() {
//        int count = 0;
//        boolean[] counted = new boolean[adjList.length];
//        for(int i=0; i < counted.length; i++)
//            counted[i] = false;
//
//        for(int i=0; i < adjList.length; i++) {
//            // if the node is not connected to others -> not all connected
//            if (adjList[i].head == null) {
//                return -1;
//            }
//            // add this node to record
//            if (!counted[i]) {
//                counted[i] = true;
//                count++;
//            }
//            Neighbour it = adjList[i].head;
//            while (it.next != null) {
//                if (!counted[it.id]) {
//                    counted[it.id] = true;
//                    count++;
//                }
//                it = it.next;
//            }
//            if (it != null) {
//                if (!counted[it.id]) {
//                    counted[it.id] = true;
//                    count++;
//                }
//            }
//        }
//        return count;
//    }
    private int DFS(int id, boolean[] counted, int count) {
        Neighbour it = adjList[id].head;
        while(it != null) {
            // count only when it is a neighbour of another node rather than in the adjacency list
            if(!counted[it.id]) {
                counted[it.id] = true;
                count ++;
                count = DFS(it.id, counted, count);
            }else
                it = it.next;
        }
        return count;
    }
    public int traversal() {
        int count = 0;
        boolean[] counted = new boolean[adjList.length];
        for(int i=0; i < counted.length; i++)
            counted[i] = false;
        counted[x] = true;
        count ++;

        count = DFS(x, counted, count);

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

class FibonacciHeapNode {
    int id;
    int degree;
    long dist;  // element

    FibonacciHeapNode child;
    FibonacciHeapNode left, right;  // siblings
    FibonacciHeapNode parent;

    boolean childCut;

    public FibonacciHeapNode(int id){
        this.id = id;
        degree = 0;
        dist = Dijkstra.INFINITE;
        child = null;
        left = this;
        right = this;
        parent = null;
        childCut = false;
    }
//    public int getId() {
//        return id;
//    }
//    public long getDist() {
//        return dist;
//    }
//    public int getDegree() {
//        return degree;
//    }
//    public FibonacciTreeNode getLeft() {
//        return left;
//    }
//    public FibonacciTreeNode getRight() {
//        return right;
//    }
//    public void setDist(long dist) {
//        this.dist = dist;
//    }
//    public void setChildCut(boolean TF) {
//        this.childCut = TF;
//    }
//    public void setDegree(int degree) {
//        this.degree = degree;
//    }
//    public void setParent(FibonacciTreeNode parent) {
//        this.parent = parent;
//    }
//    public void setChild(FibonacciTreeNode node) {
//        this.child = node;
//    }
//    public void setLeft(FibonacciTreeNode left) {
//        this.left = left;
//    }
//    public void setRight(FibonacciTreeNode right) {
//        this.right = right;
//    }
}

class FibonacciHeap {
    FibonacciHeapNode min;
    FibonacciHeapNode[] fiboArray;
    private int n;
    private int numVertex;

    public FibonacciHeap(LinkedListNode[] adjlist, int sid) {
        n = 0;
        numVertex = adjlist.length;
        fiboArray = new FibonacciHeapNode[adjlist.length];
        fiboArray[sid] = new FibonacciHeapNode(sid);
//        fiboArray[sid].setDist(0);
        fiboArray[sid].dist = 0;
        min = fiboArray[sid];

        for(int i=0; i < adjlist.length; i++) {
            if(i == sid) continue;
            fiboArray[i] = new FibonacciHeapNode(i);
            insert(fiboArray[i]);
        }
    }
    public long getDist(int id) {
        return fiboArray[id].dist;
    }

    public FibonacciHeapNode getMin() {
        return min;
    }

    private void link(FibonacciHeapNode y, FibonacciHeapNode x) {
        // remove y from the root list
        removeNode(y);
        y.left = y.right = y;

        // make y a child of x
        FibonacciHeapNode child = x.child;
        if(child == null)
            x.child = y;
        else
            add(y, child);
        y.parent = x;
        x.degree++;
        y.childCut = false;
    }
    private FibonacciHeapNode takeOutMin() {
        FibonacciHeapNode oldMin = min;

        // this is the last node
        if(oldMin == oldMin.right)
            min = null;
        // have other nodes
        else {
            removeNode(oldMin);
            min = oldMin.right;
        }

        oldMin.left = oldMin.right = oldMin;

        return oldMin;
    }
    private void consolidate() {
        // initialize the degree array to record all different degrees in the root list
//        int maxDegree = (int)Math.floor(Math.log(n)/Math.log(2.0)) + 1;
//        int maxDegree = (int)Math.ceil(Math.log((long)numVertex)/Math.log(2.0));
        int maxDegree = n;
        FibonacciHeapNode[] degreeArray = new FibonacciHeapNode[maxDegree];
//        int maxDegree = n;
        for(int i=0; i < maxDegree; i++) {
            degreeArray[i] = null;
        }

        while(min != null) {
            FibonacciHeapNode x = takeOutMin();
            int d = x.degree;
            if(degreeArray[d] == null)
                degreeArray[d] = x;
            else {
                while(degreeArray[d] != null) {
                    FibonacciHeapNode y = degreeArray[d];
                    if(x.dist > y.dist) {
                        FibonacciHeapNode tmp = x;
                        x = y;
                        y = tmp;
                    }
                    link(y, x);
                    degreeArray[d] = null;
                    d ++;
                }
                degreeArray[d] = x;
            }
        }

        // reset the root list
        min = null;
        n = 0;
        for(int i=0; i < maxDegree; i++,n++) {
            if(degreeArray[i] != null) {
                if(min == null) {
                    min = degreeArray[i];
                    continue;
                }
                insert(degreeArray[i]);
                if(degreeArray[i].dist < min.dist)
                    min = degreeArray[i];
            }
        }
    }
    private void removeNode(FibonacciHeapNode node) {
        node.left.right = node.right;
        node.right.left = node.left;
//        node.left = node.right = node;
    }
    public FibonacciHeapNode removeMin() {
        FibonacciHeapNode oldMin = this.min;
        if(oldMin != null) {
            // for each child of min, add it to the root list
            while(oldMin.child != null) {
                FibonacciHeapNode child = min.child;

                removeNode(child);
                if(child.right == child)
                    oldMin.child = null;
                else
                    oldMin.child = child.right;

                add(child, oldMin);
                child.parent = null;
            }

            // remove oldMin from the root list
            removeNode(oldMin);
            // the heap has only one node, it has no children
            if(oldMin == oldMin.right)
                min = null;
            else {
                min = oldMin.right;
                consolidate();
            }
            n --;
        }

        return oldMin;
    }

    private void add(FibonacciHeapNode node, FibonacciHeapNode head) {
        node.left = head.left;
        node.right = head;
        head.left.right = node;
        head.left = node;
    }
    public void insert(FibonacciHeapNode node) {
        add(node, min);
        if(min.dist > node.dist)
            min = node;
        n ++;
    }
    private void cut(FibonacciHeapNode node, FibonacciHeapNode parent) {
        // remove node from the child list of parent
        removeNode(node);
        // update the child field of the parent
        if(parent.child == node)
            parent.child = node.right;
        parent.degree --;

        // update the child pointer of parent
        if(node == node.right)
            parent.child = null;
        else
            parent.child = node.right;

        node.parent = null;
        node.childCut = false;

        // add the node to the root list
        add(node, min);

    }
    private void cascadingCut(FibonacciHeapNode node) {
        FibonacciHeapNode parent = node.parent;
        // this cannot be a root
        if(parent != null) {
            if(node.childCut == false)
                node.childCut = true;
            else {
                cut(node, parent);
                cascadingCut(parent);
            }
        }
    }
    public void decreaseKey(int id, long newKey) {
        decreaseKey(fiboArray[id], newKey);
    }
    private void decreaseKey(FibonacciHeapNode node, long newKey) {
//        if(min == null) return;
        FibonacciHeapNode parent = node.parent;
        node.dist = newKey;
        // it is a child of other node
        if(parent != null && parent.dist > node.dist) {
            cut(node, parent);
            cascadingCut(parent);
        }// else, it is a root node

        // update the min if necessary
        if(min.dist > node.dist)
            min = node;
    }
    private FibonacciHeapNode concatenate(FibonacciHeapNode min1, FibonacciHeapNode min2) {
//        FibonacciTreeNode tmp = min1.getLeft();
//        min1.setLeft(min2.getLeft());
//        min2.getLeft().setRight(min1);
//        tmp.setRight(min2);
//        min1.setLeft(tmp);

//        return min1.getDist() < min2.getDist() ? min1:min2;
        FibonacciHeapNode tmp = min1.left;
        min1.left = min2.left;
        min2.left.right = min1;
        tmp.right = min2;
        min1.left = tmp;

        return min1.dist < min2.dist ? min1 : min2;
    }
    public void union(FibonacciHeap nt) {
        if(nt.min == null) return;
        // this tree is empty
        if(this.min == null) {
            this.min = nt.min;
        }else {
            this.min = concatenate(this.min, nt.min);
            this.n += nt.n;
        }
    }
}


public class Dijkstra {
    public final static int INFINITE = 99999999;
    private MyGraph myGraph;
    private LeftistTree ltree;
    private FibonacciHeap ftree;
    private int type;

    private boolean traversal[];

    public Dijkstra(MyGraph myGraph, int type) {
        this.myGraph = myGraph;
        this.type = type;
        switch (type) {
            case 0: // Leftist
                ltree = new LeftistTree(myGraph.adjList, myGraph.x);
                break;
            case 1: // Fibonnaci
                ftree = new FibonacciHeap(myGraph.adjList, myGraph.x);
                break;
            case 2: // Both
                ltree = new LeftistTree(myGraph.adjList, myGraph.x);
                ftree = new FibonacciHeap(myGraph.adjList, myGraph.x);
                break;
        }
    }

    public void relax(int u, Neighbour vNeighbour) {
        int v = vNeighbour.id;
        switch(type) {
            case 0:// leftist
                if(ltree.getDist(u) + vNeighbour.w < ltree.getDist(v)) {
                    long newDist = ltree.getDist(u) + vNeighbour.w;
                    ltree.decreaseKey(v, newDist);
//            ltree.leftistTree[u].next = ltree.leftistTree[vid];
//            ltree.leftistTree[vid].prev = ltree.leftistTree[u];
                }
                break;
            case 1:// fibonnaci
                if(ftree.getDist(u) + vNeighbour.w < ftree.getDist(v)) {
                    long newDist = ftree.getDist(u) + vNeighbour.w;
                    ftree.decreaseKey(v, newDist);
                }
                break;
            case 2:// both
                if(ftree.getDist(u) + vNeighbour.w < ftree.getDist(v)) {
                    long newDist = ftree.getDist(u) + vNeighbour.w;
                    ftree.decreaseKey(v, newDist);
                }
                break;
        }
    }

    public void DijkstraAlg(MyGraph myGraph) {
        int u;

        // main for loop
        switch (type) {
            case 0:
                traversal = new boolean[myGraph.numVertex];
                for(int i=0; i < myGraph.numVertex; i++) traversal[i] = false;

                while(ltree.getMin() != null) {
                    u = ltree.removeMin();
                    traversal[u] = true;
                    // Walk through the adjacency list of node u
                    Neighbour it = myGraph.adjList[u].head;
                    while(it.next != null) {
                        if(!traversal[it.id])
                            relax(u, it);
                        it = it.next;
                    }
                    if(it != null && !traversal[it.id])
                        relax(u, it);
                    System.out.println("--------------------------------------------------");
                    if(ltree.getMin() != null) {
                        MyPrint.myPrint(ltree.getMin());
                    }
                }
                break;
            case 1:
                traversal = new boolean[myGraph.numVertex];
                for(int i=0; i < myGraph.numVertex; i++) traversal[i] = false;

                while(ftree.getMin() != null) {
                    u = ftree.removeMin().id;
                    traversal[u] = true;
                    Neighbour it = myGraph.adjList[u].head;
                    while(it.next != null) {
                        if(!traversal[it.id])
                            relax(u, it);
                        it = it.next;
                    }
                    if(it != null && !traversal[it.id])
                        relax(u, it);
//                    System.out.println("--------------------------------------------------");
//                    if(ftree.getMin() != null) {
//                        System.out.println("min:" + ftree.getMin().id);
//                        MyPrint.myPrint(ftree);
//                    }
                }
                break;
            case 2:
                long start, stop, time;
                // leftist
                type = 0;
                traversal = new boolean[myGraph.numVertex];
                for(int i=0; i < myGraph.numVertex; i++) traversal[i] = false;

                start = System.currentTimeMillis();
                System.out.println("leftist: \nstart:"+start);
                while(ltree.getMin() != null) {
                    u = ltree.removeMin();
                    traversal[u] = true;
                    // Walk through the adjacency list of node u
                    Neighbour it = myGraph.adjList[u].head;
                    while(it.next != null) {
                        if(!traversal[it.id])
                            relax(u, it);
                        it = it.next;
                    }
                    if(it != null && !traversal[it.id])
                        relax(u, it);
//                    System.out.println("--------------------------------------------------");
//                    if(ltree.getMin() != null) {
//                        MyPrint.myPrint(ltree.getMin());
//                    }
                }
                stop = System.currentTimeMillis();
                System.out.println("stop:"+stop);
                time = stop - start;
                System.out.println("time:"+time);

                // fibonacci
                type = 1;
                for(int i=0; i < myGraph.numVertex; i++) traversal[i] = false;

                start = System.currentTimeMillis();
                System.out.println("fibonacci: \nstart:"+start);
                while(ftree.getMin() != null) {
                    u = ftree.removeMin().id;
                    traversal[u] = true;
                    Neighbour it = myGraph.adjList[u].head;
                    while(it.next != null) {
                        if(!traversal[it.id])
                            relax(u, it);
                        it = it.next;
                    }
                    if(it != null && !traversal[it.id])
                        relax(u, it);
//                    System.out.println("--------------------------------------------------");
//                    if(ftree.getMin() != null) {
//                        System.out.println("min:" + ftree.getMin().id);
//                        MyPrint.myPrint(ftree);
//                    }
                }
                stop = System.currentTimeMillis();
                System.out.println("stop:"+stop);
                time = stop - start;
                System.out.println("time:"+time);
                break;
        }
    }

    public static void main(String[] args) throws IOException {
        Dijkstra dijkstra;
        MyGraph myGraph;
        int type;   // 0:Leftist, 1:Fibonnaci 2:Both

        System.out.print("Please input the command: ");
//        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
//        String[] cmd = br.toString().split("\\s");
//        String[] cmd = ("-l topo.txt").split(" ");
        String[] cmd = ("-r big.txt").split(" ");
//        String[] cmd = ("-r 5000 1 3").split(" ");
//        String[] cmd = ("-r 3000 0.2 3").split(" ");

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

            System.out.println("-r");
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
//        System.out.println("------------------- ORIGINAL TREE ------------------------");
//        switch (dijkstra.type) {
//            case 0:
//                MyPrint.myPrint(dijkstra.ltree.getMin());
//                break;
//            case 1:
//                System.out.println(dijkstra.ftree.getMin().id);
//                break;
//            case 2:
//                break;
//        }

        // Run the Dijkstra algorithm
        dijkstra.DijkstraAlg(myGraph);

    }
}
