import java.util.LinkedList;
import java.util.Queue;

/**
 * Created by marne on 1/17/2017.
 */
public class CreateAMirror {

    public BTree createMirror(TNode node){
        if(node == null)
            return null;
        Queue<TNode> queue1 = new LinkedList<>();
        Queue<TNode> queue2 = new LinkedList<>();

        BTree bTree2 = new BTree();
        bTree2.addNode(node.data);
        queue1.add(node);
        queue2.add(bTree2.root);

        while (!queue1.isEmpty()){
            TNode temp = queue1.poll();
            TNode temp2 = queue2.poll();

            if(temp.left != null){
                queue1.add(temp.left);
                temp2.right = new TNode(temp.left.data);
                queue2.add(temp2.right);
            }
            if(temp.right != null){
                queue1.add(temp.right);
                temp2.left = new TNode(temp.right.data);
                queue2.add(temp2.left);
            }



        }


        return bTree2;
    }

    //Recursive approach
    public TNode getMirrorRecursive(TNode node){

        if(node == null)
            return null;

        TNode bTreeNew ;
        getMirrorRecursive(node.left);
        getMirrorRecursive(node.right);
        //swapping the pointers

        bTreeNew = node.left;
        node.left = node.right;
        node.right = bTreeNew;


        return bTreeNew;
    }

    public boolean checkIfMirrors(TNode node1, TNode node2){

        if(node1 == null && node2 == null)
            return true;
        if(node1== null || node2 == null)
            return false;


        Queue<TNode> queue1 = new LinkedList<>();
        Queue<TNode> queue2 = new LinkedList<>();
        queue1.add(node1);
        queue2.add(node2);

        if(node1.data != node2.data)
            return false;

        while (!queue1.isEmpty() && !queue2.isEmpty()){
            TNode temp1 = queue1.poll();
            TNode temp2 = queue2.poll();

            if(temp1.left!= null && temp2.right!= null ){

                if( temp1.left.data== temp2.right.data) {
                    queue1.add(temp1.left);
                    queue2.add(temp2.right);
                }
                else
                    return false;

            }


            if(temp1.right!= null && temp2.left != null ){

                if( temp1.right.data== temp2.left.data) {
                    queue1.add(temp1.right);
                    queue2.add(temp2.left);
                }
                else
                    return false;


            }


        }
        if(queue1.isEmpty() && queue2.isEmpty())
        return true;
        else
            return false;


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

        CreateAMirror createMirror = new CreateAMirror();
        BTree bTreeResult = createMirror.createMirror(bTree.root);
        //bTreeResult.addNode(bTreeResult.root,9);

        System.out.println("After mirroring");
        bTreeResult.levelOrderTraversal(bTreeResult.root);
        System.out.println();
        bTreeResult.inOrder(bTreeResult.root);

        System.out.println("using recursion");
        TNode newRoot = createMirror.getMirrorRecursive(bTree.root);
        System.out.println("level order");
        BTree bTreeRecursion = new BTree();
        bTreeRecursion.root = newRoot;
        bTreeRecursion.levelOrderTraversal(newRoot);


        System.out.println("Checking the mirrors");

        System.out.println("The result for checking "+createMirror.checkIfMirrors(bTree.root,bTreeResult.root));

    }
}
