import java.util.LinkedList;
import java.util.Queue;

/**
 * Created by marne on 2/12/2017.
 */
public class BTreeWithNext {

    public Node root;

    class Node{
        private int value;
        private Node left;
        private Node right;
        private Node next;

        public Node(int value){
            this.value = value;
        }


    }

    //create next pointers to pointe to its next right node
    public void createNextPointers(Node node){
        Queue<Node> queue = new LinkedList<>();
        queue.add(node);
        queue.add(null);

        while (!queue.isEmpty()){
            Node temp = queue.poll();

            if(temp!= null) {
             if(queue.peek() != null)
                 temp.next = queue.peek();
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


    //printing the elements using next pointers
    public void printingUsingNext(Node node){//this is working only for full binary tree or complete binary tree where leaves fall to extreme left
        //Otherwise the leaves starting somewhere in the middle are being left out
        if(node == null)
            return;

        Node tempLeft = node;
        //Node someWhereLeaf = null;
        while (node != null){
            System.out.print(node.value+" ");
            if(node.next == null) {
                tempLeft = tempLeft.left;

                node = tempLeft;
            }
            else{
                node = node.next;
//                if(node.left!= null)
//                    someWhereLeaf = node.left;
            }

        }

//        if(someWhereLeaf!= null){
//           while (someWhereLeaf !=null){
//               System.out.print(someWhereLeaf.value+" ");
//               someWhereLeaf = someWhereLeaf.next;
//           }
//        }
    }

    //printing using another arbitrary queue
    public void printTreeWithNexts(Node node){ //again works for only balanced trees

        if(node == null)
            return;
        Queue<Node> queue = new LinkedList<>();
        Node tempLeft = node;
        Node someWhereLeaf = null;
        while (node!= null){
            System.out.print(node.value+" ");
            if(node.next == null){
                tempLeft = tempLeft.left;
                node  = tempLeft;

            }
            else{
                node = node.next;
                if(node.left != null) {
                    someWhereLeaf = node.left;
                    if( tempLeft.left == null)
                    queue.add(someWhereLeaf);
                }

            }
        }
        //Node temp = queue.poll();
        while (!queue.isEmpty()){
            Node temp = queue.poll();
            System.out.print(temp.value+" ");
            if(temp.left!= null)
                queue.add(temp.left);
            if (temp.right != null)
                queue.add(temp.right);
        }
    }

    //recursive approach - again for full binary tree
    public void connectNext(Node node){
        if(node == null)
            return;

        if(node.left != null)
            node.left.next = node.right;

        if(node.right != null) {
            if(node.next != null)
            node.right.next = node.next.left;
            else
                node.right.next = null;
        }

        connectNext(node.left);

        connectNext(node.right);
    }

    //modified recursion format
    public void connectNeighbours(Node node){

        if(node == null)
            return;

        if(node.right != null){
            node.left.next = node.right;
        }
        else{
            Node parentNeighbour = node.next;
            Node neighbour ;
            while (parentNeighbour!= null){

                if(parentNeighbour.left!= null){
                    neighbour = parentNeighbour.left;
                }
                else{
                    neighbour = parentNeighbour.right;
                }

                if(neighbour != null){
                    node.left.next = neighbour;
                    break;
                }
                else{
                    parentNeighbour = parentNeighbour.next;
                }

            }

        }

    }

    public static void main(String[] args){
        BTreeWithNext bTreeWithNext = new BTreeWithNext();
        bTreeWithNext.root = new BTreeWithNext().new Node(1);
        bTreeWithNext.root.left = new BTreeWithNext().new Node(2);
        bTreeWithNext.root.right = new BTreeWithNext().new Node(3);
        bTreeWithNext.root.left.left = new BTreeWithNext().new Node(4);
        bTreeWithNext.root.left.right = new BTreeWithNext().new Node(5);
        bTreeWithNext.root.right.left = new BTreeWithNext().new Node(6);
        bTreeWithNext.root.right.right = new BTreeWithNext().new Node(7);
        bTreeWithNext.root.left.right.left = new BTreeWithNext().new Node(8);
        bTreeWithNext.root.left.right.left.left = new BTreeWithNext().new Node(10);

        bTreeWithNext.root.right.right.left = new BTreeWithNext().new Node(9);

        bTreeWithNext.createNextPointers(bTreeWithNext.root);
        bTreeWithNext.printingUsingNext(bTreeWithNext.root);
        System.out.println();
        System.out.println("Printing using second approach");
        bTreeWithNext.printTreeWithNexts(bTreeWithNext.root);
        System.out.println();

        System.out.println("using second approach with recursion");
        bTreeWithNext.connectNext(bTreeWithNext.root);
        bTreeWithNext.printingUsingNext(bTreeWithNext.root);
        System.out.println();
        System.out.println("printng recursion with second approach");
        bTreeWithNext.printTreeWithNexts(bTreeWithNext.root);
        System.out.println();



        BTreeWithNext btreeNext = new BTreeWithNext();
        btreeNext.root = new BTreeWithNext().new Node(1);
        btreeNext.root.left = new BTreeWithNext().new Node(2);
        btreeNext.root.right = new BTreeWithNext().new Node(3);
        btreeNext.root.left.left = new BTreeWithNext().new Node(4);
        btreeNext.root.left.right = new BTreeWithNext().new Node(5);
        btreeNext.root.right.right = new BTreeWithNext().new Node(7);
        btreeNext.root.left.left.left = new BTreeWithNext().new Node(6);
        btreeNext.root.right.right.right = new BTreeWithNext().new Node(8);

        System.out.println();
        System.out.println();
        System.out.println("For second tree");

        btreeNext.createNextPointers(btreeNext.root);
        btreeNext.printingUsingNext(btreeNext.root);
        System.out.println();
        System.out.println("Printing using second approach");
        btreeNext.printTreeWithNexts(btreeNext.root);
        System.out.println();


        System.out.println("using second approach with recursion");
        //btreeNext.connectNext(btreeNext.root);
        btreeNext.connectNeighbours(btreeNext.root);
        btreeNext.printingUsingNext(btreeNext.root);
        System.out.println();
        System.out.println("printng recursion with second approach");
        btreeNext.printTreeWithNexts(btreeNext.root);
        System.out.println();


    }

}
