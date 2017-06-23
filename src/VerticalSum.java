import java.util.*;

/**
 * Created by marne on 1/23/2017.
 */
public class VerticalSum {
    public HashMap<Integer,Integer> hashMap = new HashMap<>();
    //in a binary tree, the horizontal distance of root is 0. the right subtree is +1, the left subtree is -1.
    //in such manner we can find what all nodes are in same level, develop a map and then find the vertical sum
    public Hashtable<Integer,Integer> getVerticalSum(TNode node){


        HashMap<Integer,Integer> hashing = getHashing(node);
        Set<Integer> setUnique = new HashSet<>();
        Hashtable<Integer,Integer> hashTable = new Hashtable<>();
        for (int value :
                hashing.values()) {
            hashTable.put(value,0);
        }
        int[] arrResult = new int[hashTable.size()];
        System.out.println("Size of array is "+arrResult.length);

        for (int value :
                hashing.keySet()) {
            hashTable.put(hashing.get(value),hashTable.get(hashing.get(value))+value);
        }





        return hashTable;
    }

    public HashMap<Integer,Integer> getHashing(TNode node){


        if (hashMap.isEmpty()) {
            hashMap.put(node.data, 0);
        }
        if(node == null)
            return null;
        if(node.left!=null) {
            hashMap.put(node.left.data, hashMap.get(node.data) - 1);
            getHashing(node.left);
        }

        if(node.right != null) {
            hashMap.put(node.right.data, hashMap.get(node.data) + 1);
            getHashing(node.right);
        }



        return hashMap;




    }

    public static void main(String[]  args){
        BTree bTree = new BTree();
        bTree.addNode(1);
        bTree.addNode(bTree.root,2);
        bTree.addNode(bTree.root,3);
        bTree.addNode(bTree.root.left,4);
        bTree.addNode(bTree.root.right,5);
        bTree.addNode(bTree.root,6);
        bTree.addNode(bTree.root,7);
        bTree.addNode(bTree.root.right,8);
        bTree.addNode(bTree.root.right.left,9);


        System.out.println("inorder");

        bTree.inOrder(bTree.root);
        System.out.println();

        System.out.println("level order");
        bTree.levelOrderTraversal(bTree.root);
        System.out.println();
        System.out.println("post order");
        bTree.postOrder(bTree.root);
        System.out.println();

        VerticalSum verticlSum = new VerticalSum();
        Hashtable<Integer,Integer> hashTable = verticlSum.getVerticalSum(bTree.root);

        for (int key :
                hashTable.keySet()) {
            System.out.println(key+"-"+hashTable.get(key));
        }
        System.out.println();
    }
}
