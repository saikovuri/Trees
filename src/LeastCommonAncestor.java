/**
 * Created by marne on 1/22/2017.
 */
public class LeastCommonAncestor {

    public static TNode getCommonAncestor(TNode root,TNode node1, TNode node2){

        if(root == null)
            return null;


        if(( root != null ) && (root.data == node1.data || root.data == node2.data))
            return root;



          TNode left = getCommonAncestor(root.left,node1,node2);
          TNode right = getCommonAncestor(root.right,node1,node2);

        if(left != null && right != null)
            return root;
        if(left == null)
            return right;
        else
            return left;


        //return left!=null?left:right;

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

        LeastCommonAncestor leastCommonAncestor = new LeastCommonAncestor();
        TNode node = LeastCommonAncestor.getCommonAncestor(bTree.root,new TNode(6),new TNode(7));
        System.out.println("lease common ancestor is "+node.data);

    }
}
