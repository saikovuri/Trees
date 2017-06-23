import java.util.LinkedList;
import java.util.Queue;

/**
 * Created by marne on 2/19/2017.
 */
public class PrintRightView {

    class Node{
        int value;
        Node left;
        Node right;
        Node(int value){
            this.value = value;
        }
    }

    Node root;

    private Node create(){ // for creating a tree
        this.root = new Node(1);
        this.root.left = new Node(2);
        this.root.right = new Node(3);
        this.root.left.left = new Node(4);
        this.root.left.right = new Node(5);

        this.root.right.left = new Node(6);
        this.root.right.right = new Node(7);


        return root;
    }

    public void inOrder(Node node){
        if(node == null)
            return;

        inOrder(node.left);
        System.out.print(node.value+" ");
        inOrder(node.right);
    }

    public void printRightView(Node node){

        Queue<Node> queue = new LinkedList<>();
        queue.add(node);
        queue.add(null);

        while (!queue.isEmpty()){
            Node temp = queue.poll();
            Node next = queue.peek();

            if(temp!=null) {
                if (next == null) {
                    System.out.print(temp.value + " "); //this will print the last element in the current level
                }

                if (temp.left != null)
                    queue.add(temp.left);
                if (temp.right != null)
                    queue.add(temp.right);
            }
            else{
                if(!queue.isEmpty())
                    queue.add(null);
            }

        }
    }


    public void printRightViewOnlyRights(Node node){ //improvement visiting only right nodes - will work only for complete binary tree

        while (node!= null){
            System.out.print(node.value+" ");
            node = node.right;
        }

    }





    public static void main(String[] args){
        PrintRightView printRightView = new PrintRightView();
        Node node = printRightView.create();

        printRightView.inOrder(node);
        System.out.println();

        printRightView.printRightView(node);
        System.out.println();

        //mehtod 2
        printRightView.printRightViewOnlyRights(node);
        System.out.println();

        System.out.println("Print right view - recursive appraoch");






    }
}
