import sun.awt.windows.ThemeReader;

/**
 * Created by marne on 1/17/2017.
 */
public class SumAllBTree {

    public int getSum(TNode node){

        int leftSum = 0, rightSum = 0;
        if(node == null){
            return 0;
        }

        if(node.left != null)
            leftSum =   getSum(node.left);
        if(node.right!= null)
            rightSum =  getSum(node.right);

        return leftSum + rightSum + node.data;
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
        //bTree.addNode(bTree.root,8);

        System.out.println("The level order traversal of the binary tree is :");
        bTree.levelOrderTraversal(bTree.root);

        System.out.println("The inorder traversal");
        bTree.inOrder(bTree.root);

        System.out.println();

        SumAllBTree sumAllBTree = new SumAllBTree();
        System.out.println("Sum= "+sumAllBTree.getSum(bTree.root));
    }
}
