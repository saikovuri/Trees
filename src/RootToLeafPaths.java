import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;

/**
 * Created by marne on 1/16/2017.
 */
public class RootToLeafPaths {




    ArrayList<TNode> al = new ArrayList<>();
    public void rootLeafPathsRecur(TNode node){



        if(node == null){
            return ;
        }


        if(node.left == null && node.right == null) {

            al.add(node);
            for (TNode node1    :
                    al) {
                System.out.print(node1.data+" ");
            }


            al.remove(al.size()-1);
            System.out.println();

        }if(node.left != null && node.right!= null) {

            al.add(node);
            rootLeafPathsRecur(node.left);
            rootLeafPathsRecur(node.right);
        }
        else if(node.right != null) {
            al.add(node);
            rootLeafPathsRecur(node.right);
        }
        else if(node.left!= null){
            al.add(node);
            rootLeafPathsRecur(node.left);
        }


        //else
           return ;

    }

    public void printPath(ArrayList<TNode> arrayList){
        for (TNode node :
                arrayList) {
            System.out.print(node.data +"--");
        }
        System.out.println();
    }


    public static void main(String[] args){
        RootToLeafPaths roots = new RootToLeafPaths();
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

        System.out.println("Trying to print all the paths");
         roots.rootLeafPathsRecur(bTree.root);


    }
}
