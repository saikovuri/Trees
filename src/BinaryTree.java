import java.io.BufferedReader;
import java.io.CharArrayReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;

/**
 * Created by marne on 1/7/2017.
 */

class TNode{

    int data;
    TNode left;
    TNode right;

    public TNode(int data){
        this.data = data;
        this.left = null;
        this.right = right;
    }

    public int getData() {
        return data;
    }

    public void setData(int data) {
        this.data = data;
    }

    public TNode getLeft() {
        return left;
    }

    public void setLeft(TNode left) {
        this.left = left;
    }

    public TNode getRight() {
        return right;
    }

    public void setRight(TNode right) {
        this.right = right;
    }
}
class BTree {

    public TNode root;

    public BTree() {
        this.root = null;
    }

    public void addNode(int value) {
        root = addNode(root, value);
    }

    public TNode addNode(TNode node, int value) {
        if (node == null) {
            node = new TNode(value);
            //root = node;
        } else {

            if (node.getLeft() == null) {
                node.left = addNode(node.left, value);
            } else { // with this logic all nodes get inserted at left of root and only one node goes to right of the root
                node.right = addNode(node.right, value);
            }

        }

        return node;

    }

    public int countNodes(TNode node) {

        if (node == null) {
            return 0;
        } else {

            int c = 1;
            c = c + countNodes(node.getLeft());
            c = c + countNodes(node.getRight());
            return c;
        }
    }

    public boolean search(TNode node, int value) {
        //TNode node = root;

        if (node.data == value)
            return true;

        else if (node.getLeft() != null) {
            if (search(node.getLeft(), value))
                return true;
        } else if (node.getRight() != null) {
            if (search(node.getRight(), value))
                return true;
        } else
            return false;

        return false;

    }

    public int findMax(TNode node) {
        int max = 0;
        if (node != null) {
            int maxLeft = findMax(node.left);
            int maxRight = findMax(node.right);

            if (maxLeft < maxRight)
                max = maxRight;
            else
                max = maxLeft;
            if (root.data > max)
                max = root.data;

        }

        return max;


    }

    //findMax iterative
    public int findMaxIterative() {
        if (root == null)
            return -1;

        Queue<TNode> queue = new LinkedList<>();
        int max = 0;

        queue.add(root);
        //max = root.data;

        while (!queue.isEmpty()) {
            TNode node = queue.poll();

            if (node.data > max) {
                max = node.data;
                //System.out.println("max is "+max);
            }
            if (node.getLeft() != null) {
                queue.add(node.getLeft());
            }
            if (node.getRight() != null)
                queue.add(node.getRight());


        }


        return max;

    }

    //inorder traversal

    public void inOrder(TNode node) {
        if (node != null) {
            inOrder(node.getLeft());
            System.out.print(node.data + " ");
            inOrder(node.getRight());
        }
    }

    //preOrder traversal

    public void preOrder(TNode node) {
        if (node != null) {
            System.out.print(node.data + " ");
            preOrder(node.getLeft());
            preOrder(node.getRight());
        }
    }

    //postOrder traversal

    public void postOrder(TNode node) {
        if (node != null) {
            postOrder(node.getLeft());
            postOrder(node.getRight());
            System.out.print(node.data + " ");
        }
    }

    public ArrayList<Integer> levelOrderTraversal(TNode node) {
        ArrayList<Integer> result = new ArrayList<>();

        Queue<TNode> queue = new LinkedList<TNode>();
        if (root == null)
            return null;
        queue.offer(root);


        while (!queue.isEmpty()) {
            TNode temp = queue.poll();
            System.out.print(temp.data + " ");
            result.add(temp.data);


            if (temp.getLeft() != null)
                queue.offer(temp.getLeft());
            if (temp.getRight() != null)
                queue.offer(temp.getRight());

        }


        return result;


    }

}

public class BinaryTree{

    public static void main(String[] args) throws IOException{
        BTree bt = new BTree();

        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));

        System.out.println("The options for binary tree");

        char ch;

//        do{
//            System.out.println("\nBinary Tree Operations\n");
//            System.out.println("1. insert ");
//            System.out.println("2. search");
//            System.out.println("3. count nodes");
//            System.out.println("4. check empty");
//
//            int choice = Integer.parseInt(bufferedReader.readLine());
//
//            switch (choice){
//                case 1 : System.out.println("Enter the node to insert");
//                    int value = Integer.parseInt(bufferedReader.readLine());
//                    bt.addNode(value);
//                    break;
//
//                case 2 : System.out.println("Enter the value to search");
//                    int search = Integer.parseInt(bufferedReader.readLine());
//                    boolean result = bt.search(root,search);
//                    if(result)
//                        System.out.println("The vlaue is found");
//                    else
//                        System.out.println("unable to find the value");
//                    break;
//                case 3 : System.out.println("The number of nodes is "+bt.countNodes(root));
//                    break;
//
//                default: System.out.println("wrong entry");
//                    break;
//            }
//            System.out.print("\nPost order : ");
//            bt.postOrder(root);
//            System.out.print("\nPre order : ");
//            bt.preOrder(root);
//            System.out.print("\nIn order : ");
//            bt.inOrder(root);
//
//            System.out.println("Do you want to continue?");
//            ch = bufferedReader.readLine().charAt(0);
//        }while (ch == 'Y'|| ch=='y');


        bt.addNode(5);
        bt.addNode(bt.root,10);
        bt.addNode(bt.root,-10);
        bt.addNode(bt.root,9);
        bt.addNode(bt.root,11);
        bt.addNode(bt.root,16);
        bt.addNode(bt.root,19);
        bt.addNode(bt.root,15);

        System.out.println("Level order traversal");

        bt.levelOrderTraversal(bt.root);
        System.out.println();

        System.out.println("The max "+bt.findMaxIterative());
    }
}
