import java.util.LinkedList;
import java.util.Queue;

/**
 * Created by marne on 1/16/2017.
 */

//maximum number of nodes at any level

public class WidthOfBinaryTree {

    public int widthIterative(TNode node){

        Queue<TNode> queue = new LinkedList<>();
        int width = 1;
        int levelNodes =0;


        queue.add(node);
        queue.add(null);

        while (!queue.isEmpty()){

            TNode temp = queue.poll();

            if(temp!= null){
                if(temp.left != null) {
                    queue.add(temp.left);
                    levelNodes++;
                }
                if(temp.right != null) {
                    queue.add(temp.left);
                    levelNodes++;
                }


            }
            else{
                if(!queue.isEmpty()){
                    queue.add(null);
                    if(levelNodes > width)
                        width = levelNodes;
                    levelNodes = 0;
                }
            }
        }



        return width;
    }

    //find width recursively

    public int getWidthRecursive(TNode node){


        return 0;
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

        System.out.println("The level order traversal of the binary tree is :");
        bTree.levelOrderTraversal(bTree.root);

        WidthOfBinaryTree widthOfBinaryTree = new WidthOfBinaryTree();
        int width = widthOfBinaryTree.widthIterative(bTree.root);

        System.out.println("The widthe is "+width);

    }
}
