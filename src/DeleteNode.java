import java.util.LinkedList;
import java.util.Queue;
import java.util.Stack;

/**
 * Created by marne on 1/16/2017.
 */
public class DeleteNode {

    public TNode getDeepestNode(TNode node){

        Queue<TNode> queue = new LinkedList<>();
        queue.add(node);
        queue.add(null);
        TNode temp = null;
        TNode result = null;

        while (!queue.isEmpty()){
            temp = queue.poll();
            if(temp != null)
                result = temp;
            if(temp != null) {
                if (temp.left != null) {
                    queue.add(temp.left);
                }
                if(temp.right != null){
                    queue.add(temp.right);
                }
            }
            else{
                if(!queue.isEmpty())
                    queue.add(null);
            }
        }

        return result;

    }

    public TNode findParent(TNode parent, TNode node,int value){

        //TNode result=null;
        //TNode parent = node;
        if(node == null)
            return null;

        if(node.data == value){
            //result = parent;
            return parent;
        }
        if(node.left != null)
            return  findParent(node,node.left,value);
        if(node.right != null)
            return  findParent(node,node.right,value);
            //return  null;
       // }

        //return node;
        return null;

    }

    public void deleteNode(TNode node, int value){

        //get the deepest node in the tree so that we can replace the node being deleted with the deepest node

        TNode deepestNode = getDeepestNode(node);
        //get the parent of deepest node so that we can point the parent. left or right to null and detach the deepest node

        TNode parentNodeDeepest = findParent(node, node,deepestNode.data);
        System.out.println("The parnent node in delete block is  "+parentNodeDeepest.data);

        //get parent of node to be deleted
        TNode parentNode = findParent(node,node,value); //findng the parent of  node to be deleted

        if((parentNodeDeepest.left != null) &&  parentNodeDeepest.left.data == deepestNode.data){
            parentNodeDeepest.left = null;
        }
        else
        parentNodeDeepest.right = null;


        if(parentNode.left.data == value){

            TNode tempL = parentNode.left.left;
            parentNode.left.left = null;
            TNode tempR = parentNode.left.right;
            parentNode.left.right =null;

            parentNode.left = deepestNode;
            deepestNode.left = tempL;
            deepestNode.right = tempR;

        }
        else{

            TNode tempL = parentNode.right.left;
            parentNode.right.left = null;
            TNode tempR = parentNode.right.right;
            parentNode.right.right = null;

            parentNode.right = deepestNode;
            deepestNode.left = tempL;
            deepestNode.right = tempR;

        }

    }



    public static void main(String[] args) {
        BTree bTree = new BTree();
        bTree.addNode(1);
        bTree.addNode(bTree.root, 2);
        bTree.addNode(bTree.root, 3);
        bTree.addNode(bTree.root, 4);
        bTree.addNode(bTree.root, 5);
        bTree.addNode(bTree.root, 6);
        bTree.addNode(bTree.root,7);
        bTree.addNode(bTree.root,8);

        System.out.println("The level order traversal of the tree is ");

        bTree.levelOrderTraversal(bTree.root);
        System.out.println();

        DeleteNode dn = new DeleteNode();

        TNode parent8 = dn.findParent(bTree.root,bTree.root,8);
        System.out.println("The parent of 8 is "+parent8.data);

        TNode parent3 = dn.findParent(bTree.root,bTree.root,3);
        System.out.println("The parent of 3 is "+parent3.data);

        TNode deepestNode = dn.getDeepestNode(bTree.root);
        System.out.println("The deepest node is "+deepestNode.data);

        System.out.println("Enter the vlaue to be deleted "+3);
        dn.deleteNode(bTree.root,3);

        System.out.println("The tree after deleting 3 is ");
        bTree.levelOrderTraversal(bTree.root);



    }

}
