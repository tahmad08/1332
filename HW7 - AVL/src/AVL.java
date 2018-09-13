import java.util.Collection;
import java.util.ArrayList;
import java.util.List;
import java.util.LinkedList;
import java.util.Queue;
import java.util.NoSuchElementException;

/**
 * Your implementation of an AVL Tree.
 *
 * @author YOUR NAME HERE
 * @userid YOUR USER ID HERE (i.e. gburdell3)
 * @GTID YOUR GT ID HERE (i.e. 900000000)
 * @version 1.0
 */
public class AVL<T extends Comparable<? super T>> implements AVLInterface<T> {
    // DO NOT ADD OR MODIFY INSTANCE VARIABLES.
    private AVLNode<T> root;
    private int size;

    /**
     * A no argument constructor that should initialize an empty AVL tree.
     * DO NOT IMPLEMENT THIS CONSTRUCTOR!
     */
    public AVL() {
    }

    /**
     * Initializes the AVL tree with the data in the Collection. The data
     * should be added in the same order it is in the Collection.
     *
     * @param data the data to add to the tree
     * @throws IllegalArgumentException if data or any element in data is null
     */
    public AVL(Collection<T> data) {
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
            throw new IllegalArgumentException("Data entered cannot be null");
        } else if (root == null && size == 0) {
            AVLNode<T> node = new AVLNode<T>(data);
            root = node;
        } else {
            root = recAdd(root, data);
        }
        size++;

    }

    private AVLNode<T> recAdd(AVLNode<T> node, T data) {

        if (node == null) {
            AVLNode<T> newNode = new AVLNode<T>(data);
            return newNode;
        } else {
            if (data.compareTo(node.getData()) == 0) {
                size--;
                return node;
            } else if (data.compareTo(node.getData()) < 0) {
                node.setLeft(recAdd(node.getLeft(), data));
            } else {
                node.setRight(recAdd(node.getRight(), data));
            }
        }
        return reBal(node);
    }

    private AVLNode<T> reBal(AVLNode<T> node) {
        if (node.getRight() != null && node.getLeft() != null) {
            if (node.getBalanceFactor() <= 2) {
                System.out.println("in reBal with Node: " + node.getData());
                AVLNode<T> righto = node.getRight();
                System.out.println("in reBal with rightNode: " + righto.getData());

                if (righto.getBalanceFactor() <= 0) {
                    leftRot(node);
                } else if (righto.getBalanceFactor() == 1) {
                    rightLeftRot(node);
                } else {
                    //should not reach this statement
                    return node;
                }
            } else if (node.getBalanceFactor() >= 2) {
                System.out.println("in reBal with Node: " + node.getData());
                AVLNode<T> lefto = node.getLeft();
                System.out.println("in reBal with leftNode: " + lefto.getData());
                if (lefto.getBalanceFactor() == 0
                        || lefto.getBalanceFactor() == -1) {

                    rightRot(node);
                } else if (lefto.getBalanceFactor() == 1) {
                    leftRightRot(node);
                } else {
                    //should not reach this statement
                    return node;
                }
            }
        }
        return updateHaBF(node);
    }

    private AVLNode<T> updateHaBF(AVLNode<T> node) {
        if (node != null) {
            int lH = retHeight(node.getLeft());
            int rH = retHeight(node.getRight());
            node.setHeight(Math.max(lH, rH) + 1);
            node.setBalanceFactor(lH - rH);
        }
        return node;
    }

    private int retHeight(AVLNode<T> node) {
        if (node == null) {
            return -1;
        } else {
            return node.getHeight();
        }
    }

    private AVLNode<T> leftRot(AVLNode<T> node) {
        AVLNode<T> par = node.getRight();
        node.setRight(par.getLeft());
        par.setLeft(node);
        updateHaBF(node);
        updateHaBF(par);
        return par;
    }
    private AVLNode<T> rightRot(AVLNode<T> node) {
        AVLNode<T> par = node.getLeft();
        node.setLeft(par.getRight());
        par.setRight(node);
        updateHaBF(node);
        updateHaBF(par);
        return par;
    }

    private AVLNode<T> leftRightRot(AVLNode<T> node) {
        node.setLeft(leftRot(node.getLeft())); //rotate the child
        return rightRot(node);
    }

    private AVLNode<T> rightLeftRot(AVLNode<T> node) {
        node.setRight(rightRot(node.getRight()));
        return leftRot(node);
    }

    @Override
    public T remove(T data) {
        return (T) "hey";

    }

    @Override
    public T get(T data) {
        return (T) "hey";

    }

    @Override
    public boolean contains(T data) {
        return false;

    }

    @Override
    public int size() {
        // DO NOT MODIFY THIS METHOD!
        return size;
    }

    @Override
    public T getSecondLargest() {
        return (T) "hey";

    }

    @Override
    public boolean equals(Object obj) {
        return false;
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
    private int recHeight(AVLNode<T> node) {
        if (node == null) {
            return -1;
        }
        int max = Math.max(recHeight(node.getLeft()),
            recHeight(node.getRight()));
        return max + 1;
    }

    @Override
    public AVLNode<T> getRoot() {
        // DO NOT MODIFY THIS METHOD!
        return root;
    }

    public List<T> levelorder() {
        LinkedList<T> listo = new LinkedList<T>();
        Queue<AVLNode<T>> queue = new LinkedList<AVLNode<T>>();
        AVLNode<T> node = root;
        if (node != null) {
            queue.add(node);
        }
        while (!(queue.isEmpty())) {
            AVLNode<T> tempo = queue.remove();
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

    public static void main(String[] args) {
        ArrayList<Integer> listo = new ArrayList<>();
        listo.add(40);
        listo.add(20);
        listo.add(60);
        listo.add(30);
        listo.add(10);
        listo.add(50);
        listo.add(70);
        AVL<Integer> tree = new AVL<Integer>(listo);
        List<Integer> levelO = tree.levelorder();
        for (Integer data: levelO) {
            System.out.println(data);
        }

    }


}
