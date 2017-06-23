import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Stack;

/**
 * Created by marne on 1/15/2017.
 */
public class PrintLevelOrderInReverse {

    //level by level we need to pring in reverse
    //eg: 1--- 2,3---4,5,6,7 are three levels then we need to print them in 4,5,6,7---2,3--1
    //logic  create an array list and add the ones in each level to it.
    //create a stack of array lists. one dequeue push into stack and in the ending pop them one by one and print

    public Stack<ArrayList<TNode>> levelOrderInReverse(TNode root){

        ArrayList<TNode> al = new ArrayList<>();
        Stack<ArrayList<TNode>> stack = new Stack<>();

        Queue<TNode> queue = new LinkedList<>();
        queue.add(root);
        al.add(root);
        stack.push(al);
        while (!queue.isEmpty()){
            TNode temp = queue.poll();
            al = new ArrayList<>();
            if(temp.getLeft() != null) {
                queue.add(temp.getLeft());
                al.add(temp.getLeft());
            }
            if(temp.getRight() != null) {
                queue.add(temp.getRight());
                al.add(temp.getRight());
            }

            stack.push(al);

        }
        return stack;

    }


    public static void main(String[] args){

        PrintLevelOrderInReverse pl = new PrintLevelOrderInReverse();
        BTree bTree = new BTree();
        bTree.addNode(1);
        bTree.addNode(bTree.root,2);
        bTree.addNode(bTree.root,3);
        bTree.addNode(bTree.root,4);
        bTree.addNode(bTree.root,5);
        bTree.addNode(bTree.root,6);

        System.out.println("Elelements in elvel order traversal");
        bTree.levelOrderTraversal(bTree.root);

        System.out.println("Elelements in reverse level order");
        Stack<ArrayList<TNode>> stack = pl.levelOrderInReverse(bTree.root);
        ArrayList<TNode> arrayList;
        while (!stack.isEmpty()){
             arrayList = new ArrayList<>();
            arrayList = stack.pop();

            for (TNode node :
                 arrayList  ) {
                System.out.print(node.data+" ");
            }

        }
        System.out.println();


    }


}
