import java.util.ArrayList;
import java.util.Stack;

/**
 * Created by marne on 10/30/2016.
 */

class Node{
    int data;
    double fdata;
    Node leftChild;
    Node rightChild;

}//end of node class
public class BasicTree { //with tree operations

    private static Node root; // this holds all the nodes in the tree. a node variable that holds the root. only one data field

    public Node find(int key) {
        Node current = root;

        while (current.data != key) {
            if (key < current.data) {
                current = current.leftChild;

            } else {
                current = current.rightChild;
            }

            if(current == null)
                return  null;
        }



        return current;
    }

    public void insert(int id, double data){ //we need to check for the right place to insert it and adjust the tree
        //which means that we also have to employ the process of finding a node
        Node newNode = new Node();
        newNode.data = id;
        newNode.fdata=data;
        //System.out.println("New root is " + root.data);
        if(root == null){
            root = newNode;
            System.out.println("New root is " + root.data);
        }
        else{

            System.out.println("inside else root value " +root.data);
            Node current = root;
            Node parent;
            System.out.println("inside else current value " +current.data);
            while(true){
                parent = current;
                if(newNode.data < current.data){

                    current = current.leftChild;
                    if(current == null){
                        parent.leftChild = newNode;
                        System.out.println("left "+parent.leftChild.data);
                        return;
                    }


                }
                else{
                    current = current.rightChild;
                    if(current == null){
                        parent.rightChild = newNode;
                        System.out.println("right "+parent.rightChild.data);
                        return;

                    }


                }//end else
            }// end while
        }//end else

    }//end insert


    void inOrderTraversal(Node localRoot){
        if(localRoot != null) {



            inOrderTraversal(localRoot.leftChild);
            System.out.print(localRoot.data + " ");
            inOrderTraversal(localRoot.rightChild);
        }
    }

    //iterative inorder traversal

    ArrayList<Integer> inOrderIterative(Node localRoot){
        ArrayList<Integer> result = new ArrayList<>();
        Stack<Node> stack = new Stack<>();
        Node current = localRoot;
        Node temp = null;
        // logic is push all the current nodes while going to left from root to extreme left child, while popping it access the rights and print them
        // or add the mto result

        while (current != null){
            stack.push(current);
            current = current.leftChild;
        }

        while (!stack.isEmpty()){

            current = stack.pop();
            result.add(current.data);
            if(current.rightChild != null){

                current = current.rightChild;
                while (current!= null){
                    stack.push(current);
                    current = current.leftChild;
                }
            }
        }

        return  result;

    }



    //preorder traversal
    void preOrderTraversal(Node localRoot){

        if(localRoot != null){
            System.out.print(localRoot.data+" ");
            preOrderTraversal(localRoot.leftChild);
            preOrderTraversal(localRoot.rightChild);
        }
        //System.out.println();
    }

    //iterative preOrder traversal

    ArrayList<Integer> preOrderIterative(Node localRoot){

        ArrayList<Integer> result = new ArrayList<>();

        if(root == null)
            return result;
        Stack<Node> stack = new Stack<>();

        stack.push(root);

        while (!stack.isEmpty()){

            Node temp = stack.pop();
            result.add(temp.data);

            if(temp.rightChild != null){
                stack.push(temp.rightChild);
            }

            if(temp.leftChild != null)
                stack.push(temp.leftChild);


        }

        return result;
    }

    //post order traversal

    void postOrderTraversal(Node localRoot){
        if(localRoot != null){
            postOrderTraversal(localRoot.leftChild);
            postOrderTraversal(localRoot.rightChild);
            System.out.print(localRoot.data+" ");


        }

        //System.out.println();
    }

    //iterative postOrder traversal

    ArrayList<Integer> postOrderIterative(Node localRoot){ // incomplete
        ArrayList<Integer> result = new ArrayList<>();
        Stack<Node> stack = new Stack<Node>();

        if(root == null)
            return result;

        else{
            stack.push(root.rightChild);

        }

        return result;

    }

    public Node minimum(){ //Finding the minimum in the tree
        Node current = root;
        Node min = root;

        while(current != null){
            min = current;
            current = current.leftChild;

        }
        return min;


    }

    public Node maximum(){
        Node current = root;
        Node max =null;

        while(current != null){
            max = current;
            current = current.rightChild;
        }
        return max;
    }

    //deleting a node
    public boolean delete(int key){
        Node current = root;
        Node parent = root;

        boolean isLeftChild = true;

        while (current.data != key){

            parent = current;

            if(key < current.data){
                current = current.leftChild;
                isLeftChild = true;
            }
            else{
                current = current.rightChild;
                isLeftChild = false;
            }

            if(current == null)
                return false;

        } // the node to be deleted is current and we set the parent. left or parent.right to null
        // here three cases arise, the node has no children or node has only one child or node has two children

        //case 1 : if the node found has no children i.e current has no children, then simply set the left or right pointer of parent to null

        if(current.leftChild == null && current.rightChild == null){

            if(current == root)
                root = null;
            else if(isLeftChild){
                parent.leftChild = null;
            }
            else
                parent.rightChild = null;
        }
        //case 2 : the node to be deleted has one child
        else if(current.rightChild == null){
            //if no right child replce with left subtree
            if(current == root){
                root = current.leftChild;
            }
            else if(isLeftChild)
                parent.leftChild = current.leftChild;
            else
                parent.rightChild = current.leftChild;

        }

        else if(current.leftChild == null){
            //if no leftchild replace with right child
            if(current == root)
                root = current.rightChild;
            else if(isLeftChild){
                parent.leftChild = current.rightChild;
            }
            else
                parent.rightChild = current.rightChild;
        }
        //case 3 : when the node has two children, the trick is to replace the node with its inorder successor
        //this keeps the tree  again a binary tree with all conditions satisfied
        // the successor is the next largest number following the key value of the node to be deleted
        // the last left child on the right tree of the current element is the inorder successor
        //if the right child has no left children then right is the one with which we replace the current node
        //code for finding the successor getSuccessor
        else{

            Node successor = getSuccessor(current);

            if(current == root)
                root = successor;
            else if(isLeftChild){
                parent.leftChild = successor;
            }
            else
                parent.rightChild = successor;

            successor.leftChild = current.leftChild; // in getSuccessor right child is formed, here we attach the left child of the node
            //being deleted as left child of the successor


        }// end of else i.e case 3 - with two children
        return true;
    }//end of delete

    public Node getSuccessor(Node node){ // also adjust the connections by making parent of the successor to point to null
        Node successor = node;
        Node successorParent = node;
        Node rt = node.rightChild;

        while (rt != null){
            successorParent = successor;
            successor = rt;
            rt = rt.leftChild;
        }

        //if(successor == node.rightChild){
            //return the successor directly
        //}
        if(successor != node.rightChild){
            successorParent.leftChild = successor.rightChild;
            successor.rightChild = node.rightChild; // attaching the right child of node to be delete to the successor => replaceing the node ot be deleted with the successor

        }
        return successor;


    }



    public static void main(String[] args){
        BasicTree bt = new BasicTree();
        bt.insert(10,20.5);
        bt.insert(20,30.5);
        bt.insert(15,20.1);
        bt.insert(25,11.2);
        bt.insert(5,30.2);


        Node found = bt.find(10);

        if(found != null)
            System.out.println("found and hte values is "+found.data);
        else
            System.out.println("Not found");

        System.out.println("In order traversal for the tree");
        bt.inOrderTraversal(bt.root);
        System.out.println();

        System.out.println("in order iterative");
        ArrayList<Integer> arrayList = bt.inOrderIterative(bt.root);
        for (Integer ele :
                arrayList) {
            System.out.print(ele+" ");
        }
        System.out.println();

        System.out.println("Preorder traversal");
        bt.preOrderTraversal(bt.root);
        System.out.println();

        System.out.println("in order iterative");
        ArrayList<Integer> arrayListPre = bt.preOrderIterative(bt.root);
        for (Integer ele :
                arrayListPre) {
            System.out.print(ele+" ");
        }
        System.out.println();
        System.out.println("Post order traversal");
        bt.postOrderTraversal(bt.root);
        System.out.println();
        System.out.println("The min node is " +bt.minimum().data);
        System.out.println("The max node is "+bt.maximum().data);


        System.out.println("Delete the node with key "+20);
        bt.delete(20);

        System.out.println("In order traversal for the tree");
        bt.inOrderTraversal(bt.root);
        System.out.println();
        System.out.println("Preorder traversal");
        bt.preOrderTraversal(bt.root);
        System.out.println();
        System.out.println("Post order traversal");
        bt.postOrderTraversal(bt.root);
        System.out.println();

    }//end of main class
}//end of tree class





