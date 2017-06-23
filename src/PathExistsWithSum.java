/**
 * Created by marne on 1/17/2017.
 */
public class PathExistsWithSum {
    public boolean pathWithSum(TNode node,int sum){

        if(node == null && sum == 0)
            return true;

        else if(node == null)
            return false;

        else{
            return pathWithSum(node.left,sum-node.data) || pathWithSum(node.right,sum-node.data);
        }



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

        System.out.println("The inorder traversal");
        bTree.inOrder(bTree.root);

        System.out.println();

        PathExistsWithSum pathExistsWithSum = new PathExistsWithSum();
        System.out.println("The result is "+pathExistsWithSum.pathWithSum(bTree.root,8));
    }
}
