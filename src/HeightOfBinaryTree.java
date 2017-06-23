/**
 * Created by marne on 1/15/2017.
 */
public class HeightOfBinaryTree {

    public int getHeightRecursive(TNode node){
        int leftCount = 0, rightCount =0;
        if(node == null)
            return 0;
        if(node.left != null)
            leftCount = getHeightRecursive(node.left);

        if(node.right != null)
            rightCount = getHeightRecursive(node.right);


        return 1 + Math.max(leftCount,rightCount);
    }


    public static void main(String[] args){

        HeightOfBinaryTree ht = new HeightOfBinaryTree();
        BTree bTree = new BTree();
        bTree.addNode(1);
        bTree.addNode(bTree.root,2);
        bTree.addNode(bTree.root,3);
        bTree.addNode(bTree.root,4);
        bTree.addNode(bTree.root,5);
        bTree.addNode(bTree.root,6);

        System.out.println("The level order traversal of the tree is ");

        bTree.levelOrderTraversal(bTree.root);

        System.out.println("The height of the tree is "+ht.getHeightRecursive(bTree.root));



    }
}
