import java.util.LinkedList;
import java.util.Queue;

/**
 * Created by marne on 1/15/2017.
 */
public class FindSize {

    //public static TNode root = null;

    public int getSizeIterative(TNode root){ // same queue logic as in level order traversal

        Queue<TNode> queue = new LinkedList<>();
        int count =0;
        queue.add(root);

        while (!queue.isEmpty()){
            TNode temp = queue.poll();
            count++;

            if(temp.getLeft() != null){
                queue.add(temp.getLeft());
            }
            if(temp.getRight()!= null){
                queue.add(temp.getRight());
            }
        }

        return count;

    }

    public int getSizeRecursive(TNode node){
        int leftCount=0, rightCount=0;

        if(node==null){
            return 0;
        }
        if(node.left != null){
             leftCount = getSizeRecursive(node.left);
        }
        else{
             leftCount = 0;
        }
        if(node.right != null){
             rightCount = getSizeRecursive(node.right);
        }
        else
             rightCount = 0;

        return 1 + leftCount+rightCount;
    }

    public static void main(String[] args){

        FindSize fs = new FindSize();
        BTree bTree = new BTree();
        bTree.addNode(1);
        bTree.addNode(bTree.root,2);
        bTree.addNode(bTree.root,3);
        bTree.addNode(bTree.root,4);
        bTree.addNode(bTree.root,5);
        bTree.addNode(bTree.root,6);
        bTree.addNode(bTree.root,7);

        System.out.println("The size is "+fs.getSizeIterative(bTree.root));


        System.out.println("The size recursive is "+fs.getSizeRecursive(bTree.root));



    }
}
