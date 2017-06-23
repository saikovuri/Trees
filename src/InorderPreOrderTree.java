import java.util.Arrays;

/**
 * Created by marne on 1/22/2017.
 */
public class InorderPreOrderTree {

    BTree bTree = new BTree();
    //TNode current = null;
    public TNode constructTree(int[] inOrder, int[] preOrder,boolean left,TNode current){

        int indexToSplit = 0;
        int[] inOrderNewL ;
        int[] preOrderNewL ;

        int[] inOrderNewR ;
        int[] preOrderNewR ;

        if(inOrder.length > 0) {
             indexToSplit = Arrays.binarySearch(inOrder, preOrder[0]);
            if(indexToSplit >= 0) {
                inOrderNewL = new int[indexToSplit];
                preOrderNewL = new int[indexToSplit];
                inOrderNewR = new int[inOrder.length - indexToSplit - 1];
                preOrderNewR = new int[inOrder.length - indexToSplit - 1];
            }

            else {
                return null;
            }


            for(int i=0;i<indexToSplit;i++){
                inOrderNewL[i] = inOrder[i];
                preOrderNewL[i] = preOrder[i+1];

            }


            for(int i=0;i<inOrder.length-indexToSplit-1;i++){
                inOrderNewR[i] = inOrder[inOrder.length-indexToSplit+i];
                preOrderNewR[i] = preOrder[preOrder.length-indexToSplit+i];
            }
        }
        else {
            return null;
        }

        TNode node = new TNode(preOrder[0]);
        if(bTree.root == null){
            bTree.root = node;
            node = bTree.root;
            current = node;
        }
        else if(left){
            current.left = node;
            //current = node;
        }
        else {
            current.right = node;
            //current = node;
        }

        constructTree(inOrderNewL,preOrderNewL,true,node);
        constructTree(inOrderNewR,preOrderNewR,false,node);

        return bTree.root;
    }


    public int  getIndexSearch(int[] array, int from, int end,int key){
        for(int i=from;i<=end; i++){
            if(array[i] == key)
                return i;
        }

        return -1;
    }


    public static int preIndex=0;
    public TNode constructTreeMethod2(int[] inOrder, int[] preOrder, int inStart, int inEnd,int preStart, int preEnd){ //not working - need to check
        if(inStart > inEnd || preStart > preEnd)
            return null;

//        if(inStart == inEnd)
//            return node;

        TNode node = new TNode(preOrder[preStart]); // create a node with start index of preOrder

        //int splitIndex = Arrays.binarySearch(inOrder,inStart,inEnd,node.data);
        int splitIndex = getIndexSearch(inOrder,inStart,inEnd,node.data);

        int leftSubTSize = splitIndex - inStart;
        int rigthSubTSize = inEnd - splitIndex;

        node.left = constructTreeMethod2(inOrder,preOrder,inStart,splitIndex-1,preStart+1,preStart+leftSubTSize);
        node.right = constructTreeMethod2(inOrder,preOrder,splitIndex+1,inEnd, preStart+leftSubTSize+1,preStart+leftSubTSize+rigthSubTSize);




        return node;

    }




    public static void main(String[] args){



        int[] inOrder = {4,2,5,1,6,3,7};
        int[] preOrder = {1,2,4,5,3,6,7};

        InorderPreOrderTree inorderPreOrderTree = new InorderPreOrderTree();
        TNode node = inorderPreOrderTree.constructTree(inOrder,preOrder,false,null);
        BTree bTree = new BTree();
        System.out.println("The tree after construction");
        bTree.inOrder(node);
        System.out.println();
        bTree.preOrder(node);

        System.out.println("With method 2");

        TNode node1 = inorderPreOrderTree.constructTreeMethod2(inOrder,preOrder,0,inOrder.length-1,0,preOrder.length-1);
        bTree.inOrder(node1);
        System.out.println();




    }
}
