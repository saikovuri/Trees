import java.util.LinkedList;
import java.util.Queue;
import java.util.Stack;

/**
 * Created by marne on 1/15/2017.
 */

//Approach 1 - to use level order traversal. as in print level order in reverse. for each level we increment a counter and we return the count
//approach  2
public class FindHeightUsingStack {

    public int getHeightIterative(TNode node){
        Stack<TNode> stackLeft = new Stack<>();
        Stack<TNode> stackRight = new Stack<>();

        Queue<TNode> queue = new LinkedList<>();

        queue.add(node);

        while (!queue.isEmpty()){
            TNode temp = queue.poll();
            if(temp.left != null){
                stackLeft.push(temp.left);
                queue.add(temp.left);
            }

            if(temp.right != null){
                stackRight.push(temp.right);
                queue.add(temp.right);
            }
        }

        int height = 1 + Math.max(stackLeft.size(),stackRight.size());
        return height;
    }


    public static void main(String[] args){

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

        FindHeightUsingStack fs = new FindHeightUsingStack();
        System.out.println("Height is "+fs.getHeightIterative(bTree.root));


    }


}
