/**
 * Created by marne on 1/15/2017.
 */
//All the nodes have to be deleted from leaves to the root. It is better delete children first and then delete the parent bottom up fashion.
//This kind of order is given by postOrder Traversal
public class DeleteABinaryTree {

    public boolean deleteTree(TNode node){
        if(node == null)
            return true;

        deleteTree(node.getLeft());
        deleteTree(node.getRight());
        System.out.println("The node that is deleted now is "+node.data);
        node = null;

        return true;


    }


    public static void main(String[] args){
        DeleteABinaryTree delete = new DeleteABinaryTree();
        BTree bTree = new BTree();
        bTree.addNode(1);
        bTree.addNode(bTree.root,2);
        bTree.addNode(bTree.root,3);
        bTree.addNode(bTree.root,4);
        bTree.addNode(bTree.root,5);
        bTree.addNode(bTree.root,6);
        bTree.addNode(bTree.root,7);
        bTree.addNode(bTree.root,8);

        System.out.println("Post order traversal");
        bTree.postOrder(bTree.root);
        System.out.println();

        System.out.println("Deleting the tree");
        delete.deleteTree(bTree.root);
        System.out.println();


        bTree.root = null;
        System.out.println("Post order after deleting ");
        bTree.postOrder(bTree.root);



    }

}
