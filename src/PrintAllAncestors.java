import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;

/**
 * Created by marne on 1/22/2017.
 */
public class PrintAllAncestors {

    public static TNode rootMain = null;

    public static void printAncestors(TNode root,TNode node){

        TNode parent = null;
        Queue<TNode> queue = new LinkedList<>();
        if(root.data == node.data){
            System.out.println("Ancestor "+root.data);
            return;
        }

        queue.add(root);
        while (!queue.isEmpty()){
            TNode temp = queue.poll();
            parent = temp;
            if(temp.left != null){
                if(temp.left.data == node.data){
                    System.out.println("Ancestor is "+parent.data);
                    printAncestors(root,parent);
                }
                else{
                    queue.add(temp.left);
                }
            }

            if(temp.right!= null){
                if(temp.right.data == node.data){
                    System.out.println("ancestor is : "+parent.data);
                    printAncestors(root,parent);
                }
                else {
                    queue.add(temp.right);
                }
            }

        }

        //System.out.println("Searched the whole tree");

    }

    //using recursion
    public static void printAncestorsRecursion(TNode root, TNode node){


        if(root == null)
            return;
//        if(root.left == null && root.right == null && root.data == node.data){
//            System.out.println("ancestor :"+root.data);
//            return;
//        }

        if((root.left!=null && root.left.data == node.data) ||(root.right!= null && root.right.data == node.data)){
            System.out.println("ancestor :"+root.data);
            printAncestorsRecursion(rootMain,root);
            return;
        }
        else{
            printAncestorsRecursion(root.left,node);
            printAncestorsRecursion(root.right,node);
            //System.out.println("ancestor :"+root.data);
        }




    }

    public static boolean printElementsRecursion2(TNode root, TNode node){
        if(root == null)
            return false;
        if((root.left != null && root.left.data == node.data) || (root.right != null && root.right.data == node.data) || printElementsRecursion2(root.left,node) || printElementsRecursion2(root.right,node)){
            System.out.println("ancestor :"+root.getData());
            return true;
        }
        return false;
    }

    public static void main(String[] args) throws IOException{
        BTree bTree = new BTree();
        bTree.addNode(1);
        bTree.addNode(bTree.root,2);
        bTree.addNode(bTree.root,3);
        bTree.addNode(bTree.root,4);
        bTree.addNode(bTree.root,5);
        bTree.addNode(bTree.root,6);
        bTree.addNode(bTree.root,7);
        bTree.addNode(bTree.root,8);

        System.out.println("Enter the node for which you want to find the ancestors");
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        TNode node = new TNode(Integer.parseInt(br.readLine()));
        PrintAllAncestors printAllAncestors = new PrintAllAncestors();
        PrintAllAncestors.printAncestors(bTree.root,node);

        System.out.println("Using recursion");
        rootMain = bTree.root;
        PrintAllAncestors.printAncestorsRecursion(bTree.root,node);

        System.out.println("Recursion 2");
        PrintAllAncestors.printElementsRecursion2(bTree.root,node);
    }
}
