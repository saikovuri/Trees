/**
 * Created by marne on 1/16/2017.
 */

//find the diameter of left subtree and find the diameter of right subtree
public class FindDiameterOfBinaryTree {

    public int getDiameter(TNode node){

        if(node == null)
            return 0;

        int lheight = getHeight(node.left);
        int rheight = getHeight(node.right);

        int ldiameter = getDiameter(node.left);
        int rdiameter = getDiameter(node.right);

        return Math.max((lheight+rheight+1),Math.max(ldiameter,rdiameter));


    }

    public int getHeight(TNode node){

        int leftHeight = 0, rightHeight = 0;
        if(node == null){
            return 0;
        }

        leftHeight = getHeight(node.left);
        rightHeight = getHeight(node.right);

        return 1 + Math.max(leftHeight,rightHeight);

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

        FindDiameterOfBinaryTree diameter = new FindDiameterOfBinaryTree();
        System.out.println("The diameter is "+diameter.getDiameter(bTree.root));
    }


}
