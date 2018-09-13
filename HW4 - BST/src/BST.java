import java.util.Collection;
import java.util.List;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;
import java.util.NoSuchElementException;

/**
 * Your implementation of a binary search tree.
 *
 * @author Tahirah Ahmad
 * @userid tahmad8
 * @GTID 903175115
 * @version 1.0
 */
public class BST<T extends Comparable<? super T>> implements BSTInterface<T> {
    // DO NOT ADD OR MODIFY INSTANCE VARIABLES.
    private BSTNode<T> root;
    private int size;
    private T data;

    /**
     * A no-argument constructor that should initialize an empty BST.
     * DO NOT IMPLEMENT THIS CONSTRUCTOR!
     */
    public BST() {
    }

    /**
     * Initializes the BST with the data in the Collection. The data
     * should be added in the same order it is in the Collection.
     *
     * @param data the data to add to the tree
     * @throws IllegalArgumentException if data or any element in data is null
     */
    public BST(Collection<T> data) {
        if (data == null) {
            throw new IllegalArgumentException("Data entered cannot be null");
        }
        for (T element : data) {
            add(element);
        }
    }

    @Override
    public void add(T data) {
        if (data == null) {
            throw new IllegalArgumentException("Error: cannot enter null data");
        } else if (root == null) {
            root = new BSTNode<T>(data);
            size++;
        } else {
            helperAdd(data, root);
        }
    }
    /**
    * A method to help add recursively
    * @param data is the data to add
    * @param node is the node that traverses for the spot to add
    *
     */
    private void helperAdd(T data, BSTNode<T> node) {
        //first, compare the data to the currData in root
        //if its bigger, it should go to the right child
        //if the right child is full, it should go to the right child's
        //child.
        if (data.compareTo(node.getData()) > 0) {
            //right child empty, can add here
            if (node.getRight() == null) {
                BSTNode<T> newNode = new BSTNode<T>(data);
                node.setRight(newNode);
                size++;
                return;
            } else {
                //current nodes right child is full. compare data to right childs data (recursive call) and go through process again
                helperAdd(data, node.getRight());
            }
        //data is less than currData, so go to left child
        } else if (data.compareTo(node.getData()) < 0) {
            //left child empty, can add here
            if (node.getLeft() == null) {
                BSTNode<T> newNode = new BSTNode<T>(data);
                node.setLeft(newNode);
                size++;
                return;
            //left child full,
            } else {
                //current nodes left child is full. compare data to left childs data (recursive call) and go through process again
                helperAdd(data, node.getLeft());
            }
        } else if (data.compareTo(node.getData()) == 0) {
            return;
        }
    }

    @Override
    public T remove(T data) {
        if (data == null) {
            throw new IllegalArgumentException("Cannot add null data");
        } else if (root == null) {
            throw new NoSuchElementException("Data not in tree");
        }
        BSTNode<T> dummy = new BSTNode<T>(null);
        root = remover(data, dummy, root);
        T removed = dummy.getData();
        return removed;
    }
    /**
     * A method to help remove recursively
     * @param data is the data to remove
     * @param dummy is the node that will hold the deleted data
     * @param node is the node that traverses for the spot to add
     * @return the node that was removed
     *
     */
    private BSTNode<T> remover(T data, BSTNode<T> dummy, BSTNode<T> node) {
        if (node == null) {
            throw new NoSuchElementException("element does not exist");
        }
        int comp = (data.compareTo(node.getData()));
        if (comp < 0) {
            node.setLeft(remover(data, dummy, node.getLeft()));
        } else if (comp > 0) {
            node.setRight(remover(data, dummy, node.getRight()));
        } else if (comp == 0) {
            T remData = node.getData();
            dummy.setData(remData);
            if (node.getLeft() == null) {
                size--;
                return node.getRight();
            } else if (node.getRight() == null) {
                size--;
                return node.getLeft();
            } else {
                boiGotTWOKidsDamn(data, dummy, node);
            }
        }
        return node;
    }
    /**
     * A method for removing a node with two children
     * @param data is the data to remove
     * @param dummy is the node that will hold the deleted data
     * @param node is the node that traverses for the spot to add
     *
     */
    private void boiGotTWOKidsDamn(T data, BSTNode<T> dummy, BSTNode<T> node) {
        BSTNode<T> nodeLeft = node.getLeft();
        BSTNode<T> pred = (findPred(nodeLeft));
        node.setData(pred.getData());
        BSTNode<T> newDummy = new BSTNode<T>(null);
        node.setLeft(remover(node.getData(), newDummy, nodeLeft));
    }
    /**
     * A method to help find the predescessor
     * @param node is the left node
     * @return the predescessor
     *
     */
    private BSTNode<T> findPred(BSTNode<T> node) {
        if (node.getRight() == null) {
            return node;
        } else {
            return findPred(node.getRight());
        }
    }


    @Override
    public T get(T data) {
        if (data == null) {
            throw new IllegalArgumentException("Error: data cannot be null");
        }
        T ans = helperGet(data, root);
        return ans;
    }

    /**
     * A method to help find the the node
     * @param data is the data to be found
     * @param node is the node to traverse
     * @return the data
     *
     */
    private T helperGet(T data, BSTNode<T> node) {
        if (node == null) {
            throw new NoSuchElementException("Data does not exist in tree");
        } else if (data.compareTo(node.getData()) > 0) {
            return helperGet(data, node.getRight());
        } else if (data.compareTo(node.getData()) < 0) {
            return helperGet(data, node.getLeft());
        } else if (node.getData() == null) {
            throw new NoSuchElementException("Data does not exist in tree");
        } else {
            return node.getData();
        }
    }

    @Override
    public boolean contains(T data) {
        if (data == null) {
            throw new IllegalArgumentException("Error: data cannot be null");
        }
        boolean ans = helperContains(data, root);
        return ans;
    }
    /**
     * A method to help find the predescessor
     * @param data is the data to be found
     * @param node is the node
     * @return whether data is in there or naw
     *
     */
    private boolean helperContains(T data, BSTNode<T> node) {
        if (node == null) {
            return false;
        } else if (data.compareTo(node.getData()) > 0) {
            return helperContains(data, node.getRight());
        } else if (data.compareTo(node.getData()) < 0) {
            return helperContains(data, node.getLeft());
        } else if ((data.compareTo(node.getData()) == 0)) {
            return true;
        }
        return false;
    }

    @Override
    public List<T> preorder() {
        ArrayList<T> preList = new ArrayList<T>();
        if (root == null && size == 0) {
            return preList;
        }
        preRec(root, preList);
        return preList;
    }

    /**
     * This is a helper preorser recursive method
     * @param node is the node we start at
     * @param listo is the list we create
     */
    private void preRec(BSTNode<T> node, ArrayList<T> listo) {
        if (node == null) {
            return;
        }
        listo.add(node.getData());
        preRec(node.getLeft(), listo);
        preRec(node.getRight(), listo);

    }

    @Override
    public List<T> postorder() {
        ArrayList<T> postList = new ArrayList<T>();
        if (root == null && size == 0) {
            return postList;
        }
        postRec(root, postList);
        return postList;
    }

    /**
     * A postorder recursive helper method
     * @param node the traverser
     * @param listo the list to return
     */
    private void postRec(BSTNode<T> node, ArrayList<T> listo) {
        if (node == null) {
            return;
        }
        postRec(node.getLeft(), listo);
        postRec(node.getRight(), listo);
        listo.add(node.getData());
    }

    @Override
    public List<T> inorder() {
        ArrayList<T> inList = new ArrayList<T>();
        if (root == null && size == 0) {
            return inList;
        }
        inRec(root, inList);
        return inList;
    }

    /**
     * the inorder recusrive helper
     * @param node the node to traverse
     * @param listo the list to return
     */
    private void inRec(BSTNode<T> node, ArrayList<T> listo) {
        if (node == null) {
            return;
        }
        inRec(node.getLeft(), listo);
        listo.add(node.getData());
        inRec(node.getRight(), listo);
    }

    @Override
    public List<T> levelorder() {
        LinkedList<T> listo = new LinkedList<T>();
        Queue<BSTNode<T>> queue = new LinkedList<BSTNode<T>>();
        BSTNode<T> node = root;
        if (node != null) {
            queue.add(node);
        }
        while (!(queue.isEmpty())) {
            BSTNode<T> tempo = queue.remove();
            T data = tempo.getData();
            listo.add(data);
            if (tempo.getLeft() != null) {
                queue.add(tempo.getLeft());
            }
            if (tempo.getRight() != null) {
                queue.add(tempo.getRight());
            }
        }
        return listo;
    }

    @Override
    public int distanceBetween(T data1, T data2) {
        if (data1 == null || data2 == null) {
            throw new IllegalArgumentException("no");
        }
        BSTNode<T> ancestor = dca(root, data1, data2);

        int distA = distance(ancestor, data1);
        int distB = distance(ancestor, data2);
        return distA + distB;
    }

    /**
     * find the distance between data and a given node
     * @param node given node
     * @param data given data
     * @return distance between node and node with given data
     */
    private int distance(BSTNode<T> node, T data) {
        if (node == null) {
            throw new NoSuchElementException("err");
        }
        int cmp = node.getData().compareTo(data);
        if (cmp == 0) {
            return 0;
        } else if (cmp > 0) {
            return 1 + distance(node.getLeft(), data);
        } else {
            return 1 + distance(node.getRight(), data);
        }
    }

    /**
     * FInd deepest common ancestor recursively
     * @param node node given
     * @param data1 data1
     * @param data2 data2
     * @return deepest common ancestor
     */
    private BSTNode<T> dca(BSTNode<T> node, T data1, T data2) {
        if (node == null) {
            return null;
        }
        int cmp1 = data1.compareTo(node.getData());
        int cmp2 = data2.compareTo(node.getData());

        // if one is ancestor of another
        if (cmp1 == 0 ||  cmp2 == 0) {
            return node;
        }

        // if it splits one left and one right then it is ancestor
        if (cmp1 > 0 && cmp2 < 0) {
            return node;
        } else if (cmp1 < 0 && cmp2 > 0) {
            return node;
        }
        if (cmp1 < 0 && cmp2 < 0) {
            return dca(node.getLeft(), data1, data2);
        }
        if (cmp1 > 0 && cmp2 > 0) {
            return dca(node.getRight(), data1, data2);
        }
        return node;
    }

    public List<T> anc(T data) {
        ArrayList<T> listo = new ArrayList<T>(size);
        ancRec(root, listo, data);
        return listo;
    }

    private void ancRec(BSTNode<T> node, ArrayList<T> listo, T data) {
        if (node == null) {
            throw new NoSuchElementException("not here");
        }
        int comp = data.compareTo(node.getData());
        if (comp < 0) {
            ancRec(node.getLeft(), listo, data);
            listo.add(node.getData());
            return;
        } else if (comp > 0) {
            ancRec(node.getRight(), listo, data);
            listo.add(node.getData());
            return;
        } else {
            listo.add(node.getData());
            return;
        }
    }

    @Override
    public void clear() {
        root = null;
        size = 0;
    }

    @Override
    public int height() {
        if (root == null && size == 0) {
            return -1;
        }
        int ans = recHeight(root);
        return ans;
    }

    /**
     * a recursive method for height
     * @param node the node to traverse with
     * @return the height
     */
    private int recHeight(BSTNode<T> node) {
        if (node == null) {
            return -1;
        }
        int max = Math.max(recHeight(node.getLeft()),
            recHeight(node.getRight()));
        return max + 1;
    }

    @Override
    public int size() {
        // DO NOT MODIFY THIS METHOD
        return size;
    }

    @Override
    public BSTNode<T> getRoot() {
        // DO NOT MODIFY THIS METHOD!
        return root;
    }

    public static void main(String[] args) {
        // ArrayList<Integer> listo = new ArrayList<>();
        // listo.add(4);
        // listo.add(2);
        // listo.add(6);
        // listo.add(3);
        // listo.add(1);
        // listo.add(5);
        // listo.add(7);
        // BST<Integer> tree = new BST<Integer>(listo);
        // List<Integer> levelO = tree.levelorder();
        // for (Integer data: levelO) {
        //     System.out.println(data);
        // }
        // System.out.println("now ancestors");
        // List<Integer> ancL = tree.anc(3);
        // for (Integer data: ancL) {
        //     System.out.println(data);
        // }
    }

}
