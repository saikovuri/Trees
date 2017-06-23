import java.util.LinkedList;
import java.util.Queue;

/**
 * Created by marne on 1/16/2017.
 */

//if a node has no left child and right child then increment the count
//full nodes - condition left!= null && right != null then increment the counter for full nodes
// number of half nodes - either of the nodes is not null then increment the counter
public class NumOfLeavesIterative {

    public int getNumOfLeaves(TNode node){

        int countLeaves = 0;
        int countFull = 0;
        int countHalf = 0;
        Queue<TNode> queue = new LinkedList<>();
        queue.add(node);

        while (!queue.isEmpty()){
            TNode temp = queue.poll();

            if(temp.left == null && temp.right == null)
                countLeaves++;

            if(temp.left != null && temp.right != null)
                countFull++;

            if((temp.left != null && temp.right == null) || (temp.left == null && temp.right!=null))
                countHalf++;

            if(temp.left != null)
                queue.add(temp.left);
            if(temp.right != null)
                queue.add(temp.right);
        }


        System.out.println("Half nodes "+countHalf);
        System.out.println("Full nodes "+countFull);
        System.out.println("Leaves "+countLeaves);
        return countLeaves;

    }

    public int getLeavesCountRecursive(TNode node){

        if(node == null){
            return 0;
        }

        if(node.left == null && node.right == null){
            return 1;
        }

        return getLeavesCountRecursive(node.left) + getLeavesCountRecursive(node.right);
    }


    public static void main(String[] args){

        NumOfLeavesIterative leaves = new NumOfLeavesIterative();
        BTree bTree = new BTree();
        bTree.addNode(1);
        bTree.addNode(bTree.root,2);
        bTree.addNode(bTree.root,3);
        bTree.addNode(bTree.root,4);
        bTree.addNode(bTree.root,5);
        bTree.addNode(bTree.root,6);
        bTree.addNode(bTree.root,7);
        bTree.addNode(bTree.root,8);

        System.out.println("Elelements in elvel order traversal");
        bTree.levelOrderTraversal(bTree.root);

        System.out.println();

        int leafNodes = leaves.getNumOfLeaves(bTree.root);
        System.out.println("THe count is "+leafNodes);

        System.out.println("using recursion");
        System.out.println("Count using method 2 "+leaves.getLeavesCountRecursive(bTree.root));
    }
}
