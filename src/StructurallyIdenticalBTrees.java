import javax.net.ssl.SSLContext;
import java.util.LinkedList;
import java.util.Queue;

/**
 * Created by marne on 1/16/2017.
 */
public class StructurallyIdenticalBTrees {

    public boolean structureSame(TNode node1, TNode node2){

        Queue<TNode> queue1 = new LinkedList<>();
        Queue<TNode> queue2 = new LinkedList<>();

        queue1.add(node1);
        queue2.add(node2);

        while (!queue1.isEmpty() && !queue2.isEmpty()){

            TNode temp1 = queue1.poll();
            TNode temp2 = queue2.poll();

            if(temp1.getLeft() != null && temp2.getLeft() !=null){
                queue1.add(temp1.getLeft());
                queue2.add(temp2.getLeft());
            }
            else if((temp1.getLeft() != null && temp2.getLeft() == null) || (temp1.getLeft()==null && temp2.getLeft()!= null))
                return false;
            if(temp1.getRight() != null && temp2.getRight() != null){
                queue1.add(temp1.getRight());
                queue2.add(temp2.getRight());
            }
            else if((temp1.getRight() != null && temp2.getRight() == null) || (temp1.getRight()==null && temp2.getRight()!= null))
                return false;

        }

        if(!queue1.isEmpty() || !queue2.isEmpty())
            return  false;

        return true;


    }

    //Recursive approach

    public boolean getStructureSimilarityRecursive(TNode node1, TNode node2){

        if(node1 == null && node2 == null)
            return true;
        else if((node1 == null && node2 != null) || (node1 != null && node2 == null))
            return false;

        else{
            return (getStructureSimilarityRecursive(node1.left,node2.left) && getStructureSimilarityRecursive(node1.right,node2.right));
        }




        //return false;
    }



    public static void main(String[] args){

        StructurallyIdenticalBTrees structurallySimilar = new StructurallyIdenticalBTrees();
        BTree bTree1 = new BTree();
        BTree bTree2 = new BTree();

        bTree1.addNode(1);
        bTree1.addNode(bTree1.root,2);
        bTree1.addNode(bTree1.root,3);
        bTree1.addNode(bTree1.root,4);
        //bTree1.addNode(bTree1.root,5);
        bTree1.addNode(bTree1.root,6);
        bTree1.addNode(bTree1.root,7);
        bTree1.addNode(bTree1.root,8);


        bTree2.addNode(11);
        bTree2.addNode(bTree2.root,22);
        bTree2.addNode(bTree2.root,33);
        bTree2.addNode(bTree2.root,44);
        bTree2.addNode(bTree2.root,55);
        bTree2.addNode(bTree2.root,66);
        bTree2.addNode(bTree2.root,77);
        bTree2.addNode(bTree2.root,88);
//        bTree2.addNode(bTree2.root,99);
//        bTree2.addNode(bTree2.root,100);

        System.out.println("the root nodes are "+bTree1.root.data+" ,"+bTree2.root.data);
        System.out.println("The trees  are structurally similar "+structurallySimilar.structureSame(bTree1.root,bTree2.root));

        System.out.println("structural similarity with recursive calls "+structurallySimilar.getStructureSimilarityRecursive(bTree1.root,bTree2.root));
    }
}
