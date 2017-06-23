import java.util.LinkedList;
import java.util.Queue;

/**
 * Created by marne on 1/14/2017.
 */
public class SearchIterative {

    public boolean iterativeSearch(TNode node , int value){

        Queue<TNode> queue = new LinkedList<>();

        queue.add(node);

        while (!queue.isEmpty()){
            TNode temp = queue.poll();
            if(temp.data == value)
                return true;
            if(temp.getLeft() != null){
                queue.add(temp.getLeft());
            }
            if(temp.getRight() != null)
                queue.add(temp.getRight());
        }
        return false;

    }

    public static void main(String[] args){
        SearchIterative si = new SearchIterative();
       BTree bTree = new BTree();
        bTree.addNode(5);
        bTree.addNode(bTree.root,7);
        bTree.addNode(bTree.root,8);
        bTree.addNode(bTree.root,9);
        bTree.addNode(bTree.root,10);
        bTree.addNode(bTree.root,11);
        bTree.addNode(bTree.root,12);
        bTree.addNode(bTree.root,13);

        System.out.println("The recursive search on 10 : "  + bTree.search(bTree.root,10));

        System.out.println("The iterative search on 13 : "+si.iterativeSearch(bTree.root,13));



    }
}
