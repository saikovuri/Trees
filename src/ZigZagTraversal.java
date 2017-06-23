import java.util.LinkedList;
import java.util.Queue;
import java.util.Stack;

/**
 * Created by marne on 1/23/2017.
 */
public class ZigZagTraversal {

    //nodes at different level should be printed in alternating order
    public void printZigZag(TNode node){
        Queue<TNode> queue = new LinkedList<>();
        Stack<TNode> stackEven = new Stack<>();
        Stack<TNode> stackOdd = new Stack<>();
        int level = 1;
        queue.add(node);
        System.out.println(node.data+" ");
        queue.add(null);

        while (!queue.isEmpty()){
            TNode temp = queue.poll();

            if(temp != null) {

                if (temp.left != null) {
                    if(level%2 != 0)
                        stackEven.push(temp.left);
                    else
                        stackOdd.push(temp.left);

                    queue.add(temp.left);
                }
                if (temp.right != null) {
                    if(level%2 != 0)
                        stackEven.push(temp.right);
                    else
                        stackOdd.push(temp.right);
                    queue.add(temp.right);
                }
            }
            else{
                level ++;
                if(!queue.isEmpty())
                    queue.add(null);

                if(level%2 == 0) {
                    while (!stackEven.isEmpty()) {
                        System.out.print(stackEven.pop().data + " ");
                    }
                }
                else{
                    while (!stackOdd.isEmpty())
                        System.out.print(stackOdd.pop().data+" ");
                }

                System.out.println();

            }


        }
    }


    public void zigzagTraversalReverse(TNode node){

        boolean evenLevel;
        Stack<TNode> stackEven = new Stack<>();
        Stack<TNode> stackOdd = new Stack<>();

        stackEven.add(node);
        evenLevel = true;

        while ((evenLevel && !stackEven.isEmpty()) || !stackOdd.isEmpty()){

            if(evenLevel) { //push right element first in the even stacks
                while (!stackEven.isEmpty()) {
                    TNode current = stackEven.pop();
                    System.out.print(current.data + " ");

                    if (current.right != null)
                        stackOdd.push(current.right);

                    if (current.left != null) {
                        stackOdd.push(current.left);
                    }

                }
                //evenLevel = false;
            }
            else{
                while (!stackOdd.isEmpty()){
                    TNode current = stackOdd.pop();
                    System.out.print(current.data+" ");

                    if(current.left != null)
                        stackEven.push(current.left);
                    if(current.right!= null)
                        stackEven.push(current.right);
                }
            }

            evenLevel = !evenLevel;
            System.out.println();

        }

    }

    public static void main(String[] args){
        BTree bTree = new BTree();
        bTree.addNode(1);
        bTree.addNode(bTree.root,2);
        bTree.addNode(bTree.root,3);
        bTree.addNode(bTree.root.left,4);
        bTree.addNode(bTree.root.right,5);
        bTree.addNode(bTree.root,6);
        bTree.addNode(bTree.root,7);
        bTree.addNode(bTree.root.right,8);
        bTree.addNode(bTree.root.right.left,9);

        bTree.inOrder(bTree.root);


        System.out.println("inorder");

        bTree.inOrder(bTree.root);
        System.out.println("level order");
        bTree.levelOrderTraversal(bTree.root);
        System.out.println("post order");
        bTree.postOrder(bTree.root);
        System.out.println();
        System.out.println("Zigzag traversal");

        ZigZagTraversal zigZagTraversal = new ZigZagTraversal();
        zigZagTraversal.printZigZag(bTree.root);


        zigZagTraversal.zigzagTraversalReverse(bTree.root);
    }
}
