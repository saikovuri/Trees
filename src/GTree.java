import com.sun.org.apache.xpath.internal.operations.Gt;
import org.omg.CosNaming.NamingContextPackage.NotEmpty;

import java.util.*;
import java.util.concurrent.ArrayBlockingQueue;

/**
 * Created by marne on 2/5/2017.
 */

public class GTree {

    Node root;

    class Node{
        private int value;
        private Node left;
        private Node right;

        public Node(int item){
            this.value = item;
            this.left = null;
            this.right = null;
        }
    }



    GTree(){
        root = null;
    }

    public int size(Node node){
        if(node == null)
            return 0;
        else{
            return 1 + size(node.left)+size(node.right);
        }

    }

    public int maxDepth(Node node){
        if(node == null){
            return 0;
        }

        return 1 + Math.max(maxDepth(node.left),maxDepth(node.right));
    }

    public void printTree(Node node){
        if(node == null)
            return;
        printTree(node.left);
        System.out.print(node.value+" ");
        printTree(node.right);


    }

    public int getSize(Node node){
        if(node==null){
            return 0;
        }
        else{
           return getSize(node.left) + getSize(node.right) +1;
        }

    }

    //given a sum we need to find if a path is there such that the sum of values of the nodes = sum
    public boolean hasPathSum(Node node,int sum){ // how to print the nodes which fall in the way of node
        //check if possible to get in the order from root
        if(node == null)
            return (sum==0);
        //otherwise check both subtrees
        else{
            int subSum = sum-node.value;


            boolean statusLeft = hasPathSum(node.left,subSum);
            boolean statusRight = hasPathSum(node.right,subSum);

            if(statusLeft && node.left!=null)
                System.out.print(node.left.value+" ");
            if(statusRight && node.right!=null)
                System.out.print(node.right.value+" ");


            if(node == root && (statusLeft || statusRight))
                System.out.print(node.value+" ");

            return (statusLeft || statusRight);

        }


    }

    public void printPaths(){
        int[] path = new int[1000];
        printPaths(root,path,0); //initial path length is 0 - and this is a helper recursive method

    }

    public void printPaths(Node node,int[] path,int pathLength){
        if(node==null)
            return;

        //append this node to the patharray;
        path[pathLength] = node.value; //1
        pathLength++; //2

        if(node.left == null && node.right == null){ //3
            printArray(path,pathLength); //4
        }
        else{
            printPaths(node.left,path,pathLength); //5
            printPaths(node.right,path,pathLength);//6
        }


    }

    //for printing the nodes stored in an array until now
    public void printArray(int[] path,int pathLength){
        for(int i=0;i<pathLength;i++)
            System.out.print(path[i]);

        System.out.println();
    }

    //change the tree into its mirror image
    public void getMirror(Node node){

        if(node!= null) {
            getMirror(node.left); //1
            getMirror(node.right);//2


            //swap the left and right pointers
            Node temp = node.left;//3
            node.left = node.right;//4
            node.right = temp;//5
        }

    }

    //change the tree by inserting a duplicate node on each of node.left
    public void doubleTree(Node node){
        if(node == null)
            return;

        doubleTree(node.left);
        doubleTree(node.right);

       // if(node.left != null){
            Node temp = node.left;
            node.left = new Node(node.value);
            node.left.left = temp;
            //doubleTree(node.left.left);
       // }

    }


    //check if two trees are identical
    public boolean checkSame(Node node1, Node node2){
        if(node1 == null && node2 == null)
            return true;

        else if(node1!= null && node2!=null){
            return (node1.value==node2.value) && checkSame(node1.left,node2.left) && checkSame(node1.right,node2.right);
        }
        else{
            return false;
        }

    }

    //check if two trees are structurally similar
    public boolean checkStructure(Node node1,Node node2){
        if(node1 == null && node2 == null)
            return true;
        else if(node1 != null && node2 != null)
            return checkStructure(node1.left,node2.left) && checkStructure(node1.right,node2.right);
        else
            return false;
    }

    //count the number of binary search trees possible with the given unique set of keys
    //also it is a dynamic programming problem
    public int countTrees(int numkeys){
        //int sum=0;
        int[] T = new int[numkeys+1];
        T[0] =1;
        T[1] = 1;

            for(int i=2;i<=numkeys;i++){

                for(int j=0;j<i;j++){
                    T[i] += T[j]*T[i-j-1];

                }

            }

        return T[numkeys];
    }

    //recursive approach
    public int countTreesRec(int numKeys){

        if(numKeys<=1)
            return 1;
        else{
            int sum=0;
            int left,right,root;
            for(root=1;root<=numKeys;root++){
                left = countTreesRec(root-1);
                right = countTreesRec(numKeys-root);
                sum=sum+left*right;
            }

            return sum;
        }
    }

    //to check if a given tree is a binary search tree
    public boolean isBinarySearchTree(Node node){
        if(node == null)
            return true;
        if(node.left.value <= node.value  && node.value < node.right.value){
             return true;
        }
        isBinarySearchTree(node.left);
        isBinarySearchTree(node.right);

        return false;
    }

    //get the height of the tree
    public int getHeight(Node node){
        if(node==null)
            return 0;

        int lheight = getHeight(node.left);
        int rheight = getHeight(node.right);

        return Math.max(lheight,rheight)+1;
    }

    //level order traversal recursive
    public void printLevelOrder(Node node){
        int h = getHeight(node);
        for(int i=0;i<h;i++){
            printGivenLevel(node,i);
            System.out.println();
        }
    }

    public void printGivenLevel(Node node,int h){
        if(node ==null)
            return;

        if(h==0)
            System.out.print(node.value+" ");

        else if(h>0){
            printGivenLevel(node.left,h-1);
            printGivenLevel(node.right,h-1);
        }


    }

    //diameter of a binary tree
    //diameter of a binary tree is the max of (diameter of left subtree, diameter of right subtree, longest path between leaves that passes through root)
    public int getDiameter(Node node){

        if(node==null)
            return 0;

        int lheight = getHeight(node.left);
        int rheight = getHeight(node.right);

        int longestPath = 1 + lheight + rheight;

        int ldiameter = getDiameter(node.left);
        int rdiameter = getDiameter(node.right);

        return Math.max(longestPath, Math.max(ldiameter,rdiameter));

    }

    //iterative inorder traversal
    public void inOrderIterative(Node node){
        if(node==null) {
            System.out.println("no nodes in the tree");
            return;
        }

        Stack<Node> stack = new Stack<>();
        stack.push(node);
        while (!stack.isEmpty()) {
            if(node!=null)
            node=node.left;
            while (node != null) {
                stack.push(node);
                node = node.left;
            }
            Node popped = stack.pop();
            System.out.print(popped.value + " ");
            node = popped.right;
            if(node!= null)
            stack.push(node);
        }

    }

    //inorder traversal without recursion and without stack - Morris Traversal
    //this is based on finding the inorder successor using the pointers
    public void morrisInroderTraversal(Node node){

        Node current = node;
        Node pre = null;

        while (current!= null){

            if(current.left == null){
                System.out.print(current.value+" ");
                current = current.right;
            }
            else{
                pre = current.left;
                //find the predecessor of current here. which is the last node whose node.right = null and that right should point back to the current
                while (pre.right != null && pre.right != current){
                    pre = pre.right;
                }
                //now predecessor is found
                if(pre.right== null){
                    pre.right = current;
                    current = current.left;
                }
                else {
                    pre.right = null;
                    System.out.print(current.value+" ");
                    current = current.right;
                }

            }
        }

    }


    //the below one is not working
    public void inOrderIterativeM2(Node node){
        if(node==null)
            return;
        Stack<Node> stack = new Stack<>();

        while (true){
            if(node!=null){
                stack.push(node);
                node=node.left;
            }
            else{
                if(stack.isEmpty())
                    break;
                Node popped = stack.pop();
                System.out.print(popped.value+" ");
                node=node.right;
            }
        }
    }

    public void getLevelOrderTraversalRec(Node node){

        int height = getHeight(node);

        int width = 0;
        int maxWidth =0;
        for(int i=0;i<height;i++){

        }

    }

    public void getLevelOrderTraversalIterative(Node node){

        Queue<Node> queue = new LinkedList<>();
        queue.add(node);
        queue.add(null);

        while (!queue.isEmpty()){
            Node temp = queue.poll();
            if(temp!= null) {
                System.out.print(temp.value + " ");
                if (temp.left != null) {
                    queue.add(temp.left);
                }
                if (temp.right != null)
                    queue.add(temp.right);
            }
            else{
                System.out.println();
                if(!queue.isEmpty()){
                    queue.add(null);
                }
            }
        }
    }

    public int getMaxWidthUsingIterativeLO(Node node){
        Queue<Node> queue = new LinkedList<>();
        int width=0;
        int maxWidth = 0;
        queue.add(node);
        queue.add(null);
        width++;
        while (!queue.isEmpty()){

            Node temp = queue.poll();
            if(temp!= null) {
                System.out.print(temp.value + " ");
                if (temp.left != null) {
                    queue.add(temp.left);
                    width++;
                }
                if (temp.right != null) {
                    queue.add(temp.right);
                    width++;
                }
            }
            else{
                System.out.println();
                if(!queue.isEmpty()){
                    queue.add(null);
                    if(width > maxWidth)
                        maxWidth = width;
                    width=0;
                }
            }
        }
        return maxWidth;
    }


    //method 1 - using level order traversal
    public int getMaxWidth(Node node){

        int maxWidth=0;
        int width;
        int height = getHeight(node);
        for(int i=1;i<=height;i++){
            width = getWidth(node,i);
            if(width > maxWidth)
                maxWidth = width;
        }

        return maxWidth;
    }

    public int getWidth(Node node,int level){
        if(node==null)
            return 0;
        if(level==1)
            return 1;

        else if(level>1){
            return getWidth(node.left,level-1)+getWidth(node.right,level-1);
        }
        return 0;
    }

    public void printNodesAtKDistanceFromRoot(Node node,int k){
        if(node == null)
            return;
        if(k==1){
            System.out.print(node.value+" ");
        }

        else{
            printNodesAtKDistanceFromRoot(node.left,k-1);
            printNodesAtKDistanceFromRoot(node.right,k-1);
        }
    }

    public boolean printAncestorsGivenNode(Node node, Node givenNode){
        if(node == null || givenNode == null)
            return false;

        if(givenNode.value == node.value){
            System.out.print(node.value+"->");
            return true;
        }
        if(printAncestorsGivenNode(node.left,givenNode) || printAncestorsGivenNode(node.right,givenNode)){
            System.out.print(node.value+"->");
            return true;
        }
        else{

           return false;

        }
    }

    //check if the
    public boolean checkIfGivenTreeisSubOfAnother(Node sub, Node sup){ // the worst case complexity of this is O(|sup|*|sub|)

        if(sub == null || sup == null)
            return false;
        if(sub.value == sup.value){
            return checkingHelper(sub,sup);

        }

        return checkIfGivenTreeisSubOfAnother(sub,sup.left) || checkIfGivenTreeisSubOfAnother(sub,sup.right);


    }

    public boolean checkingHelper(Node sub,Node sup){

        if(sub == null || sup == null){
            return true;
        }
        if(sub.value == sup.value) {
            return checkingHelper(sub.left, sup.left) && checkingHelper(sub.right, sup.right);
        }
        else{
            return false;
        }


    }

    public int[] getInOrderArray(Node node){
        int[] inOrd = new int[getSize(node)];
        int[] newInOrd = getInOrderArrayHelper(node,inOrd);

        return newInOrd;
    }


    public static int inCount = -1;
    public int[]  getInOrderArrayHelper(Node node,int[] inOrd){
        //get size of a tree
        if(node.left!= null)
        getInOrderArrayHelper(node.left,inOrd);
//        else
//        return null;
        inOrd[++inCount] = node.value;
        if(node.right != null)
        getInOrderArrayHelper(node.right,inOrd);
//        else
//        return null;


        return inOrd;
    }

    public static int preCount=0;
    public int[] getPreorder(Node node, int[] preOrd){

        preOrd[preCount++] = node.value;
        if(node.left != null)
            getPreorder(node.left,preOrd);
        if(node.right != null)
            getPreorder(node.right,preOrd);

        return preOrd;

    }



    //improvement over O(mn) solution above
    //find the inOrder and preOrder of both trees and if the sub tree inOrder and preOrder of subarrays of sup then sub is a sub tree of super
    public boolean checkIfTreeisSubOfSuper(Node sub,Node sup){
        int[] inSub = getInOrderArray(sub);
        int[] inSup = getInOrderArray(sub);

        int[] preSub = getPreorder(sub,new int[getSize(sub)]);
        int[] preSup = getPreorder(sup, new int[getSize(sup)]);

        String strInSub = toString(inSub);
        String strInSup = toString(inSup);
        String strPreSub = toString(preSub);
        String strPreSup = toString(preSup);

        if(strInSup.contains(strInSub) && strPreSup.contains(strPreSub))
            return true;


        return false;
    }


    public String toString(int[] arr){
        String str = "";
        for (int ele :
                arr) {
            str = str+ele;
        }
        return str;
    }

    public Node getParent(Node node,Node n1){
        if(node == null || n1 == null)
            return null;
        if(node.left == n1 || node.right == n1)
            return node;
        else {
            Node parent = getParent(node.left,n1);
            if(parent == null){
                parent = getParent(node.right,n1);
            }
            return parent;

        }

    }

    public boolean checkCousins(Node node,Node node1, Node node2){

        //checking if the nodes are at same level, if so check if they have common parent(false). no common parent - true
        Queue<Node> queue = new LinkedList<>();
        queue.add(node);
        queue.add(null);

        while (!queue.isEmpty()){
            Node temp = queue.poll();
            if(temp != null){
                if(temp.left != null)
                    queue.add(temp.left);
                if(temp.right != null)
                    queue.add(temp.right);
            }
            else{
                if (!queue.isEmpty())
                    queue.add(null);

                if(queue.contains(node1) && queue.contains(node2)){
                    if(getParent(node,node1) != getParent(node,node2))
                        return true;

                }


            }
        }
        return false;

    }

    public Node findNode(Node node, int value){

        if(node == null)
            return node;
        if(node.value == value)
            return node;
        else{
            Node found = findNode(node.left,value);

            if(found==null){
                found = findNode(node.right,value);
            }
            return found;

        }

    }

    public void printVerticalOrderTraversal(Node node){//width * O(n)
        Map<Integer,Integer> hashMap2 = constructHash(node); //O(n)


        //get min and get max to find the extremes
        int min = Integer.MAX_VALUE;
        int max = Integer.MIN_VALUE;

        for (int value :
                hashMap2.values()) {
            if (value < min) {
                min = value;
            }
            if(value > max)
                max = value;
        }

        for(int i=min;i<=max;i++){
            for (int ele :
                    hashMap2.keySet()) {
                if(hashMap2.get(ele) == i)
                    System.out.print(ele+" ");

            }
            System.out.println();
        }






    }
    public Map<Integer,Integer> hashMap = new HashMap<>();
    public Map<Integer,Integer> constructHash(Node node){
        if(node==null)
            return hashMap;

        if(hashMap.isEmpty())
            hashMap.put(node.value,0);

        if(node.left!= null){
            hashMap.put(node.left.value,hashMap.get(node.value)-1);
            constructHash(node.left);
        }
        if(node.right!= null){
            hashMap.put(node.right.value,hashMap.get(node.value)+1);
            constructHash(node.right);
        }

        return hashMap;
    }


    //vertical order hashMap based appraoch

    public void printRightViewRecursive(Node node){
        int heigth = getHeight(node);

        for(int i=0;i<heigth;i++){

        }

    }

    //print all nodes of a binary tree that do not have a sibling
    public void printNoSiblingNodes(Node node){
        if(node==null)
            return;

        if(node.left != null && node.right != null) {
            printNoSiblingNodes(node.left);
            printNoSiblingNodes(node.right);
        }

        else if(node.left != null && node.right==null){
            System.out.print(node.left.value + " ");
            printNoSiblingNodes(node.left);
        }
        else if(node.left == null && node.right!= null){
            System.out.print(node.right.value+" ");
            printNoSiblingNodes(node.right);
        }
        else
            return;

    }

    public void printRightRecursive(Node node, int h){

    }

    public void printLeftView(Node node){
        //when ever you encounter a null - take the next value after null and that belongs to the left view

    }

    public int sum=0;
    public int getSumOfAllLeaves(Node node){
        if(node == null)
            return 0;

        if(node.left == null && node.right == null){
            sum = sum+node.value;
            return  sum;
        }

        if(node.left!=null)
            getSumOfAllLeaves(node.left);
        if(node.right != null)
           getSumOfAllLeaves(node.right);

        return sum;

    }

    public boolean checkIfFullOrNot(Node node){
        if(node == null)
            return true;
        if(node.left == null && node.right == null)
            return true;
        if(node.left == null && node.right != null)
            return false;
        if(node.left != null && node.right == null)
            return false;
        if(node.left != null && node.right != null)
            return checkIfFullOrNot(node.left) && checkIfFullOrNot(node.right);

        return false;
    }


    public boolean checkIfCompleteOrNot(Node node){

        int size = getSize(node);
        return checkCompleteHelper(node,size,0);

    }

    public boolean checkCompleteHelper(Node node, int count, int index){
        if(node == null)
            return true;

        if(index >= count){
            return false;
        }

        return checkCompleteHelper(node.left,count,2*index + 1) && checkCompleteHelper(node.right,count,2*index + 2);

    }

    public void printTopViewBinaryTree(Node node){//incorrect as it will miss the extreme right nodes in the left tree that cross the right subtree

        if(node == null)
            return;

        System.out.print(node.value+" ");

        Node left = node.left;
        Node right = node.right;

        while (left!= null){
            System.out.print(left.value+" ");
            left = left.left;
        }

        while (right!= null){
            System.out.print(right.value+" ");
            right = right.right;
        }
    }

    //top view of a binary tree using pre-order traversal
    //the idea is to use vertical level order traversal such that the first element in each vertical level becomes the top view

    class MapEntry{
        Node node;
        int nodeLevel;
        MapEntry(Node node, int nodeLevel){
            this.node = node;
            this.nodeLevel = nodeLevel;
        }
    }



    public void printTopViewLevelOrder(Node node){
        Set<Integer> horizontalDistancesVisited = new HashSet<>();
        if(node == null)
            return;

        Queue<MapEntry> queue = new LinkedList<>();
        queue.add(new MapEntry(node,0)); //horizontal distance of root is 0
        horizontalDistancesVisited.add(0);
        System.out.print(node.value+" ");

        while (!queue.isEmpty()){
            MapEntry dequeued = queue.poll();
            int hd = dequeued.nodeLevel;
            Node temp = dequeued.node;

            if(!horizontalDistancesVisited.contains(hd)){
                horizontalDistancesVisited.add(hd);
                System.out.print(temp.value+" ");
            }

            if(temp.left != null){
                queue.add(new MapEntry(temp.left,hd-1));
            }
            if(temp.right != null){
                queue.add(new MapEntry(temp.right,hd+1));
            }

        }




    }

    //print bottom view of a binary tree
    public void getBottomView(Node node){

        Map<Integer,LinkedList<Node>> map = new HashMap<Integer,LinkedList<Node>>();
        // no need to add a list - the previous values will be updated and you will get the last one obviously
        Map<Integer,Node> mapNodes = new HashMap<>();

        Queue<MapEntry> queue = new LinkedList<>();
        queue.add(new MapEntry(node,0));
        LinkedList<Node> list = new LinkedList<>();
        list.add(node);
        mapNodes.put(0,node);

        while (!queue.isEmpty()){

            MapEntry mapEntry = queue.poll();
            int hd = mapEntry.nodeLevel;
            Node temp = mapEntry.node;

            if(temp.left!= null){
                queue.add(new MapEntry(temp.left,hd-1));

                mapNodes.put(hd-1,temp.left);
            }

            if(temp.right != null){

                queue.add(new MapEntry(temp.right,hd+1));

                mapNodes.put(hd+1,temp.right);
            }




        }


        for (Integer level :
                mapNodes.keySet()) {

            System.out.print(mapNodes.get(level).value+" ");
        }


    }

    //remove all nodes in a path having sum less than k
    public Node deleteNodesInPath(Node node,int k){
        int pathSum = 0;
        Node rootNode = deleteNodesInPath(node,k,pathSum);

        return rootNode;
    }


    public Node deleteNodesInPath(Node node,int k, int sum){

        if(node == null || k == 0)
            return null;

        node.left = deleteNodesInPath(node.left,k,sum+node.value);
        node.right = deleteNodesInPath(node.right,k,sum + node.value);

        if(node.left == null && node.right == null){
            if(sum + node.value >= k)
                return node;
            else{
                node = null;
                return node;
            }
        }



        return node;
    }

    //remove all the half nodes  from a binary tree
    public Node removeHalfNodes(Node node){

        if(node == null)
            return null;
        if(node.left != null && node.right != null){
            node.left = removeHalfNodes(node.left);
            node.right = removeHalfNodes(node.right);

        }
        else if(node.left == null && node.right == null){
            return node;
        }

        else if(node.left == null){
            node = node.right;
        }
        else if(node.right == null){
            node = node.left;
        }

        return node;
    }


    //lowest common ancestor again
    public Node getLowestCommonAncestor(Node node, Node n1, Node n2){

        if(node == null)
            return null;
        if(node== n1 || node == n2)
            return node;

        node.left = getLowestCommonAncestor(node.left,n1,n2);
        node.right = getLowestCommonAncestor(node.right,n1,n2);

        if(node.left == null && node.right == null)
            return null;
        if(node.left != null && node.right != null){
            return node;
        }
        else if(node.left == null)
            return node.right;
        else if(node.right == null)
            return node.left;
        return null;
    }

    public void printPreOrderWithOutRecursion(Node node){
        Stack<Node> stack = new Stack<>();
        stack.add(node);

        while (!stack.isEmpty()){
            Node temp = stack.pop();
            System.out.print(temp.value+" ");

            if(temp.right !=null)
                stack.add(temp.right);
            if(temp.left!=null)
                stack.add(temp.left);
        }
    }

    public void printPostOrderWithOutRecursion(Node node){
        Stack<Node> stack = new Stack<>();
        Node current = node;

        while (current!= null || !stack.isEmpty()){

            if(current!= null){
                stack.push(current);
                current = current.left;
            }
            else{

                Node temp =stack.peek().right;
                //current = current.right;
                if(temp == null){
                    temp = stack.pop();
                    //int val = stack.pop().value;
                    System.out.print(temp.value+" ");
                }
                else{
                    stack.push(current);
                }
            }



        }
    }

    //compute the sum of the diagonal elements in a tree
    public void computeDiagSum(Node node){

        HashMap<Node,Integer> hashMap = new HashMap<>();
        hashMap = computeHashMap(node,hashMap);
        HashMap<Integer,Integer> result = new HashMap<>();

        for (Node n :
                hashMap.keySet()) {
            if(result.containsKey(hashMap.get(n))) {
                int val = result.get(hashMap.get(n));
                result.put(hashMap.get(n), val + n.value);
            }
            else
                result.put(hashMap.get(n),n.value);
        }

        for (Integer key :
                result.keySet()) {
            System.out.println("Diagonal "+key+" : "+result.get(key));
        }

    }


    public HashMap<Node,Integer> computeHashMap(Node node,HashMap<Node,Integer> hashMap){

        if(hashMap.isEmpty()){

            hashMap.put(node,0);
        }

        if(node.right!= null){
            hashMap.put(node.right,hashMap.get(node));
            computeHashMap(node.right,hashMap);
        }

        if(node.left!= null){
            hashMap.put(node.left,hashMap.get(node)+1);
            computeHashMap(node.left,hashMap);
        }

        return hashMap;
    }

    //recursive version of calculating diagonal sum without using computation of hashing
    public void computeDiagSumRec(Node node){
        HashMap<Integer,Integer> hash = new HashMap<>();
        //int currDiagonal = 0;

        computeDiagonalRecHelper(node,0,hash);

        for (Integer key :
                hash.keySet()) {
            System.out.println("Diagonal : "+key+" : "+hash.get(key));
        }


    }

    public void computeDiagonalRecHelper(Node node, int diag, HashMap<Integer,Integer> hash){

        if(node == null)
            return;

        int prevSum = 0;

        if(hash.containsKey(diag)){
            prevSum = hash.get(diag);
        }

        hash.put(diag,prevSum+node.value);

        if(node.left!=null)
        computeDiagonalRecHelper(node.left,diag+1,hash);
        if(node.right!= null)
        computeDiagonalRecHelper(node.right,diag,hash);
    }

    public Node getPredecessor(Node node){
        //node = node.left;
        while (node.right!= null){
            node = node.right;
        }

        return node;
    }

    public Node getSuccessor(Node node){
        //node = node.right;
        while (node.left!= null)
            node = node.left;
        return node;
    }

    public Node convertBTToDLL(Node node){
        if(node == null)
            return null;

        node = convertBinaryTreeToDLL(node);

        while (node.left != null)
            node = node.left;
        return node;
    }

    public Node convertBinaryTreeToDLL(Node node){

        if(node.left!= null) {
            Node left = convertBinaryTreeToDLL(node.left);
            while (left.right != null){
                left = left.right;
            }
            //Node predecessor = getPredecessor(left);
            left.right = node;
            node.left = left;
        }

        if(node.right != null) {
            Node right = convertBinaryTreeToDLL(node.right);
            //Node successor = getSuccessor(right);
            while (right.left != null)
                right = right.left;
            right.left = node;
            node.right = right;
        }


        return node;
    }

    public void printDLL(Node root){

        Node node  = root;
        while (node!= null){
            System.out.print(node.value+" ");
            node=node.right;
        }
    }

    public static void main(String[] args){
        GTree tree =  new GTree();


        //GTree.Node node = new tree.Node(1);
        tree.root = new GTree().new Node(1);
        tree.root.left = new GTree().new Node(2);
        tree.root.right = new GTree().new Node(3);
        tree.root.left.left = new GTree().new Node(4);
        tree.root.left.right = new GTree().new Node(5);
        tree.root.right.left = new GTree().new Node(6);
        tree.root.right.right = new GTree().new Node(7);
        tree.root.left.left.left = new GTree().new Node(8);
        tree.root.left.left.right = new GTree().new Node(9);
        tree.root.left.right.left = new GTree().new Node(10);
        tree.root.left.right.right = new GTree().new Node(11);

        System.out.println("the size is "+tree.size(tree.root));
        System.out.println("the max depth is "+tree.maxDepth(tree.root));
        tree.printTree(tree.root);
        System.out.println();
        System.out.println("check for sum 10 :"+tree.hasPathSum(tree.root,10));
        System.out.println();
        System.out.println("check for sum 8 :"+tree.hasPathSum(tree.root,8));
        System.out.println();
        System.out.println("Printing the paths from root to all leaves");
        tree.printPaths();
        System.out.println();



        tree.printTree(tree.root);
        System.out.println();

        System.out.println("This is tree 2");
        GTree tree2 = new GTree();
        tree2.root = new GTree().new Node(1);
        tree2.root.left = new GTree().new Node(2);
        tree2.root.right = new GTree().new Node(3);
        tree2.root.left.left = new GTree().new Node(4);
        tree2.root.left.right = new GTree().new Node(8);//not same but structurally similar
        tree2.root.right.left = new GTree().new Node(6);
        tree2.root.right.right = new GTree().new Node(7);

        tree2.printTree(tree2.root);
        System.out.println();

        System.out.println("Checking if two trees are similar");
        System.out.println("the result is "+tree.checkSame(tree.root,tree2.root));


        //tree2.root.right.right.left = new GTree().new Node(8);
        System.out.println("checking the structure");
        System.out.println("The answer is "+tree.checkStructure(tree.root,tree2.root));

        System.out.println("Counting the number of binary search trees possible");
        System.out.println("With dp technique "+tree.countTrees(5));
        System.out.println("with rec technique "+tree.countTreesRec(5));

        System.out.println("new tree - a bst to check condition");
        GTree treeBst = new GTree();
        treeBst.root = new GTree().new Node(5);
        treeBst.root.left = new GTree().new Node(3);
        treeBst.root.right = new GTree().new Node(7);
        treeBst.root.left.left = new GTree().new Node(2);
        treeBst.root.left.right = new GTree().new Node(4);
        treeBst.root.right.left = new GTree().new Node(6);
        treeBst.root.left.left.left = new GTree().new Node(8);

        System.out.println("in order of bst");
        treeBst.printTree(treeBst.root);
        System.out.println();
        System.out.println("This is binary tree or not "+treeBst.isBinarySearchTree(treeBst.root));

        System.out.println("Printing a given level ");
        tree.printLevelOrder(tree.root);
        System.out.println();

        System.out.println("The diameter of the tree is :");
        System.out.println("diameter = "+tree.getDiameter(tree.root));

        System.out.println("Printing the inorder traversal of nodes");
        tree.inOrderIterative(tree.root);
        System.out.println();

        //System.out.println("Iterative with method 2 effective ");
        //tree.inOrderIterativeM2(tree.root);
        //System.out.println();

        System.out.println("Morris inorder traversal");
        tree.morrisInroderTraversal(tree.root);
        System.out.println();

        System.out.println("Level order traversal");
        tree.getLevelOrderTraversalIterative(tree.root);
        System.out.println();

        System.out.println("The width of the tree is "+tree.getMaxWidth(tree.root));

        System.out.println("Get max width using iterative level order "+tree.getMaxWidthUsingIterativeLO(tree.root));

        tree.printNodesAtKDistanceFromRoot(tree.root,3);
        //System.out.println("The nodes at distance k =2 are "+tree.printNodesAtKDistanceFromRoot(tree.root,2));
        System.out.println();
        System.out.println("Printing ancestors of a given node");
        tree.printAncestorsGivenNode(tree.root,tree.root.right.left);
        System.out.println();

        GTree treeSub = new GTree();
        treeSub.root = new GTree().new Node(3);
        treeSub.root.left = new GTree().new Node(6);
        treeSub.root.right = new GTree().new Node(7);

        GTree treeSub2 = new GTree();
        treeSub2.root = new GTree().new Node(2);
        treeSub2.root.left = new GTree().new Node(4);
        treeSub2.root.right = new GTree().new Node(6);

        System.out.println("checking if treeSub is subtree of tree");
        System.out.println("The result is "+tree.checkIfGivenTreeisSubOfAnother(treeSub.root,treeBst.root));
//        System.out.println("Check if treeSub is subtree of tree. Result = "+tree.checkIfTreeisSubOfSuper(treeSub.root,treeBst.root));

        System.out.println("The size of this tree is "+tree.getSize(tree.root));
        System.out.println("The size of the sub tree is "+tree.getSize(treeSub.root));

       //System.out.println("The inorder array is "+tree.getInOrderArray(tree.root));
        System.out.println("Trying to get the inorder array");
        int[] inOrd = tree.getInOrderArray(tree.root);
        for (int ele :
                inOrd) {
            System.out.print(ele+" ");
        }

        System.out.println();

        System.out.println("Trying to get preorder array");
        int[] preOrd = tree.getPreorder(tree.root,new int[tree.size(tree.root)]);

        for (int ele :
                preOrd) {
            System.out.print(ele+" ");
        }
        System.out.println();


        System.out.println("Checkign if the nodes are cousins (checking true)"+tree.checkCousins(tree.root,tree.findNode(tree.root,7),tree.findNode(tree.root,5)));
        System.out.println("Checkign if the nodes are cousins (checking false) "+tree.checkCousins(tree.root,tree.findNode(tree.root,4),tree.findNode(tree.root,5)));

        System.out.println("Checking parent node "+tree.getParent(tree.root,tree.findNode(tree.root,4)).value);


        System.out.println("Vertical level priting");
        tree.printVerticalOrderTraversal(tree.root);


        GTree gTree = new GTree();
        gTree.root = new GTree().new Node(1);
        gTree.root.left = new GTree().new Node(2);
        gTree.root.right = new GTree().new Node(3);
        //gTree.root.right.left = new GTree().new Node(4);
        gTree.root.right.right = new GTree().new Node(5);
        gTree.root.right.right.left = new GTree().new Node(6);

        System.out.println("no siblings");
        gTree.printNoSiblingNodes(gTree.root);

        System.out.println("Get sum of all leaves");
        System.out.println("Sum of all leaves="+gTree.getSumOfAllLeaves(gTree.root));


        System.out.println("Checking full or not");

        GTree gTree1 = new GTree();
        gTree1.root = new GTree().new Node(1);
        gTree1.root.left = new GTree().new Node(2);
        gTree1.root.right = new GTree().new Node(3);
        gTree1.root.left.left = new GTree().new Node(4);
        gTree1.root.left.right = new GTree().new Node(5);
        gTree1.root.right.left = new GTree().new Node(6);
        gTree1.root.right.right = new GTree().new Node(7);

        System.out.println("Full or not true case : "+gTree1.checkIfFullOrNot(gTree1.root));
        System.out.println("Full or not false case : "+treeBst.checkIfFullOrNot(treeBst.root));

        System.out.println("Complete or not true case "+tree.checkIfCompleteOrNot(tree.root));
        System.out.println("Complete or not false case "+treeBst.checkIfCompleteOrNot(treeBst.root));


        System.out.println("the top view of a binary tree");
        tree.printTopViewBinaryTree(tree.root);

        System.out.println();
        System.out.println("using queues");
        tree.printTopViewLevelOrder(tree.root);
        System.out.println();

        System.out.println("Bottom view of tree");
        tree.getBottomView(tree.root);
        System.out.println();


        System.out.println("Deleting the path sums");

//        System.out.println("This should give a null");
//        Node node = gTree1.deleteNodesInPath(gTree1.root,6);
//        System.out.println("In order traversal");
//        gTree1.printTree(node);

        System.out.println("This should give a modified tree");
        gTree1.printTree(gTree1.root);
        Node node = gTree1.deleteNodesInPath(gTree1.root,9);
        System.out.println("In order traversal");
        gTree1.printTree(node);


        GTree gT = new GTree();
        gT.root = new GTree().new Node(1);
        gT.root.left = new GTree().new Node(2);
        gT.root.right = new GTree().new Node(3);
        gT.root.right.left = new GTree().new Node(7);
        //gT.root = new GTree().new Node(1);

        System.out.println("nodes before removal");
        gT.printTree(gT.root);
        Node gtRoot = gT.removeHalfNodes(gT.root);
        System.out.println("After removal printing the nodes");
        gT.printTree(gtRoot);


        System.out.println("Getting lowest common ancestor");
        tree.printTree(tree.root);
        System.out.println("For 4 and 7 "+tree.getLowestCommonAncestor(tree.root,tree.root.left.left,tree.root.right.right).value);
        //System.out.println("For 4 and 7 "+tree.getLowestCommonAncestor(tree.root,tree.root.left.left,tree.root.right.right).value);
        System.out.println("For 8 and 2 "+tree.getLowestCommonAncestor(tree.root,tree.root.left.left.left,tree.root.left).value);




        System.out.println("In order");
        treeBst.printTree(treeBst.root);
        System.out.println("Print preorder without recursion");
        treeBst.printPreOrderWithOutRecursion(treeBst.root);
        System.out.println();


        System.out.println("Compute diagonal sum");
        treeBst.computeDiagSum(treeBst.root);


        System.out.println("using recursion");
        treeBst.computeDiagSumRec(treeBst.root);


        System.out.println("Convert binary tree to a DLL(taking treeBST");
        treeBst.printTree(treeBst.root);

        System.out.println();
        System.out.println("DLL IS :");
        Node llNode = treeBst.convertBTToDLL(treeBst.root);

        treeBst.printDLL(llNode);






    }





}
