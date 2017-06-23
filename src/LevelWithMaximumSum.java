import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Stack;

/**
 * Created by marne on 1/16/2017.
 */
public class LevelWithMaximumSum {

    public int levelSum(TNode node){
        Queue<TNode> queue = new LinkedList<>();
        //Stack<ArrayList<TNode>> stack = new Stack<>();
        //ArrayList<TNode> al = new ArrayList<>();
        int sum = node.data;
        int tempSum = 0;

        queue.add(node);
        queue.add(null);

        while (!queue.isEmpty()){

            TNode temp = queue.poll();

            if(temp!= null){

                if(temp.left != null){
                    tempSum = tempSum + temp.left.data;
                    queue.add(temp.left);
                }

                if(temp.right != null){
                    tempSum = tempSum + temp.right.data;
                    queue.add(temp.right);
                }

            }
            else{

                if(!queue.isEmpty()){
                    if(tempSum > sum)
                        sum = tempSum;

                    tempSum = 0;
                    queue.add(null);
                }
            }

        }

        return sum;
    }


    public static void main(String[] args){
        LevelWithMaximumSum levelWithMaximumSum = new LevelWithMaximumSum();

        BTree bTree = new BTree();

        bTree.addNode(1);
        bTree.addNode(bTree.root,2);
        bTree.addNode(bTree.root,3);
        bTree.addNode(bTree.root,4);
        bTree.addNode(bTree.root,5);
        bTree.addNode(bTree.root,6);
        bTree.addNode(bTree.root,7);
        bTree.addNode(bTree.root,8);

        System.out.println("The level order traversal of the binary tree is :");
        bTree.levelOrderTraversal(bTree.root);

        System.out.println("The inorder traversal");
        bTree.inOrder(bTree.root);

        System.out.println("Max sum in all levels is "+levelWithMaximumSum.levelSum(bTree.root));



    }
}
