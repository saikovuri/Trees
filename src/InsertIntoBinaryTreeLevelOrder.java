import java.util.LinkedList;
import java.util.Queue;

/**
 * Created by marne on 1/14/2017.
 */
public class InsertIntoBinaryTreeLevelOrder {

    public static boolean leftNode, rightNode;

    public static TNode root = null;
    public static TNode parent = null;

    public boolean insert(TNode node, int value){

        if(root == null){
            root = new TNode(value);
            return true;
        }
        Queue<TNode> queue = new LinkedList<>();

        queue.add(node);
        while (!queue.isEmpty()){
            TNode node1 = queue.poll();

            if(node1.left != null){
                queue.add(node1.left);
            }
            else{
                node1.left = new TNode(value);
                return true;
            }
            if(node1.right != null){
                queue.add(node1.right);
            }
            else{
                node1.right = new TNode(value);
                return true;
            }



        }

        return false;
    }

    public boolean insertRecursive(TNode node,  int value){


        if(root == null){
            root = new TNode(value);
            return true;
        }

        if(node == null){
            if(leftNode) {
                node = new TNode(value);
                parent.left = node;
                return true;
            }
            else{
                node = new TNode(value);
                parent.right = node;
                return true;
            }
        }

        if(node.left == null){
            node.left = new TNode(value);
            return true;
        }
        else if(node.left != null) {
            leftNode = false;
            rightNode = true;
            parent = node;
           return insertRecursive(node.right,value);
        }
        else if(node.right == null){
            node.right = new TNode(value);
            return true;
        }
        else{
            rightNode = false;
            leftNode = true;
            parent = node;
           return insertRecursive(node.left,value);
        }



        //return false;
    }

    public static void main(String[] args){
        InsertIntoBinaryTreeLevelOrder io = new InsertIntoBinaryTreeLevelOrder();
        BTree bTree = new BTree();

        bTree.addNode(1);
        bTree.addNode(bTree.root,2);
        bTree.addNode(bTree.root,3);
        bTree.addNode(bTree.root,4);
        bTree.addNode(bTree.root,5);
        bTree.addNode(bTree.root,6);

        System.out.println();

       /* io.insert(root,11);
        io.insert(root,12);
        io.insert(root,13);
        io.insert(root,14);
        io.insert(root,15);
        io.insert(root,16); */

        System.out.println("Inorder of elements :");
        bTree.inOrder(root);

        io.insertRecursive(root,01);
        io.insertRecursive(root,02);
        io.insertRecursive(root,03);
        io.insertRecursive(root,04);
        io.insertRecursive(root,05);
        io.insertRecursive(root,06);

        System.out.println("Iorder with recursive print :");

        bTree.inOrder(root);


    }
}
