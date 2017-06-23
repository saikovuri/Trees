import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

/**
 * Created by marne on 1/23/2017.
 */
public class PreOrderSpecialPropertyTree {

    BTree bTree = new BTree();
    public static int i = 0;
    public boolean left = true;
    public TNode getBinaryTree(int[] nodeValues, char[] preOrder, int n, TNode temp){
//        if(str.length()==0)
//            return null;
//        if(str.substring(0,1).equals("I"))
//        {
//            if(bTree.root == null)
//                bTree.root = new TNode(i++);
//            else{
//                if()
//            }
//        }


        return null;
    }

    public static void main(String[] args) throws IOException{
        int[] nodeValues = {10,20,30,40,50};
        char[] preOrder = {'I','I','L','L','L'};
        int n = nodeValues.length;

        PreOrderSpecialPropertyTree preTree = new PreOrderSpecialPropertyTree();
        BTree bTree = new BTree();
        bTree.root = preTree.getBinaryTree(nodeValues,preOrder,n,bTree.root);



    }
}
