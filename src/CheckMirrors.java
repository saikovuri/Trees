import java.util.LinkedList;
import java.util.Queue;

/**
 * Created by marne on 1/17/2017.
 */
public class CheckMirrors {


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

    public boolean checkMirrorsRecursive(TNode node1, TNode node2){

        if(node1 == null && node2 == null)
            return true;
        if(node1 == null || node2 == null)
            return false;
        if(node1.data != node2.data)
            return false;
        return checkMirrorsRecursive(node1.left,node2.right) && checkMirrorsRecursive(node1.right,node2.left);
    }

    public static void main(String[] args){

        CheckMirrors checkMirrors = new CheckMirrors();
        CreateAMirror createAMirror = new CreateAMirror();
        BTree bTree = new BTree();
        BTree bTree1 = new BTree();

        bTree.addNode(1);
        bTree.addNode(bTree.root,2);
        bTree.addNode(bTree.root,3);
        bTree.addNode(bTree.root,4);
        bTree.addNode(bTree.root,5);
        bTree.addNode(bTree.root,6);
        bTree.addNode(bTree.root,7);
        bTree.addNode(bTree.root,8);

        bTree1.addNode(1);
        bTree1.addNode(bTree1.root,2);
        bTree1.addNode(bTree1.root,3);
        bTree1.addNode(bTree1.root,4);
        bTree1.addNode(bTree1.root,5);
        bTree1.addNode(bTree1.root,6);
        bTree1.addNode(bTree1.root,7);
        bTree1.addNode(bTree1.root,8);


        BTree bTreeResult = createAMirror.createMirror(bTree.root);

        System.out.println("Level order traversal of btree ");
        bTree.levelOrderTraversal(bTree.root);

        System.out.println("level order traversal of mirror");
        bTree.levelOrderTraversal(bTreeResult.root);

        System.out.println("they are mirrors "+checkMirrors.checkIfMirrors(bTree.root,bTreeResult.root));


        System.out.println("Second set they are mirrors "+checkMirrors.checkIfMirrors(bTree.root,bTree1.root));

        System.out.println("First check with recursion "+ checkMirrors.checkMirrorsRecursive(bTree.root,bTreeResult.root));
        System.out.println("Second  check with recursion "+ checkMirrors.checkMirrorsRecursive(bTree.root,bTree1.root));


    }
}
