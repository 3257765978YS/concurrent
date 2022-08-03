package com.ys.example.avl;

/**
 * @Description 平衡二叉树
 * @Author 杨帅
 * @Date 2022/6/14 16:05
 * @Version 1.0
 **/
public class AVLTreeDemo {
    public static void main(String[] args) {
//        int[] arr = {4,3,6,5,7,8};
//        int[] arr = {10,12,8,9,7,6};
        int[] arr = {10,11,7,6,8,9};
        //创建一个avltree对象
        AVLTree avlTree = new AVLTree();
        //添加节点
        for (int i = 0; i < arr.length; i++) {
            avlTree.add(new Node(arr[i]));
        }
        System.out.println("中序遍历～");
        avlTree.infixOrder();

        System.out.println("平衡树立后～");
//        System.out.println("在没有做平衡处理前～");
        System.out.println("树的高度="+ avlTree.getRoot().height());
        System.out.println("树的左子树高度="+ avlTree.getRoot().leftHeight());
        System.out.println("树的右子树高度="+ avlTree.getRoot().rightHeight());
        System.out.println("当前树的根节点="+ avlTree.getRoot()); //8
        System.out.println("根节点的左子节点="+ avlTree.getRoot().left); //7
        System.out.println("根节点的右子节点="+ avlTree.getRoot().right);//10
    }
}

//创建AVLTree
class AVLTree {
    private Node root;

    public Node getRoot() {
        return root;
    }

    public void add(Node node) {
        //为空，直接放入root
        if (root == null) {
            root = node;
        } else {
            root.add(node);
        }
    }

    public void infixOrder() {
        if (root != null) {
            root.infixOrder();
        } else {
            System.out.println("二叉排序树为空，无法遍历");
        }
    }

    //查找要删除的节点
    public Node search(int value) {
        return root == null ? null : root.search(value);
    }

    /**
     * @param node 根节点
     * @Description: 查询出以node为根节点的 最小节点的值
     * @Author: 杨帅
     * @Date: 2022/6/13
     * @return: int
     */
    public int delRightTreeMin(Node node) {
        //复制node给target，为了找出最小节点
        Node target = node;
        while (target.left != null) {
            target = target.left;
        }
        //这时target就指向了最小节点
        delNode(target.value);
        return target.value;
    }

    //删除节点
    public void delNode(int value) {
        if (root == null) {
            return;
        } else {
            //1.需求先去找到要删除的节点 targetNode
            Node targetNode = root.search(value);
            //如果没有找到要删除的节点
            if (targetNode == null) {
                return;
            }
            //如果我们发现这个二叉树只有一个节点
            if (root.left == null && root.right == null) {
                root = null;
                return;
            }

            //去找到targetNode的父节点
            Node parent = searchParent(value);
            //如果要删除的节点是叶子节点
            if (targetNode.left == null && targetNode.right == null) {
                //判断targetNode是父节点的左子节点还是右子节点
                if (parent.left != null && parent.left.value == value) {
                    parent.left = null;
                    return;
                } else if (parent.right != null && parent.right.value == value) {
                    parent.right = null;
                    return;
                }
            } else if (targetNode.left != null && targetNode.right != null) {
                //有左右子树
                targetNode.value = delRightTreeMin(targetNode.right);
            } else { //只有一个子节点
                //如果targetNode有左子节点
                if (targetNode.left != null) {
                    if (parent != null) {
                        //分情况，如果targetNode是parent的左子节点
                        if (parent.left.value == value) {
                            parent.left = targetNode.left;
                        } else {//targetNode是parent的右子节点
                            parent.right = targetNode.left;
                        }
                    } else {
                        root = targetNode.left;
                    }
                } else { //如果targetNode有右子节点
                    if (parent != null) {
                        //targetNode是parent的左子节点
                        if (parent.left.value == value) {
                            parent.left = targetNode.right;
                        } else {//targetNode是parent的右子节点
                            parent.right = targetNode.right;
                        }
                    } else {
                        root = targetNode.right;
                    }
                }
            }
        }
    }

    //查找父节点
    public Node searchParent(int value) {
        return root == null ? null : root.searchParent(value);
    }
}

//创建Node节点
class Node {
    int value;
    Node left;
    Node right;

    public Node(int value) {
        this.value = value;
    }

    @Override
    public String toString() {
        return "Node{" +
                "value=" + value +
                '}';
    }

    //返回左子树的高度
    public int leftHeight() {
        if (left == null) {
            return 0;
        }
        return left.height();
    }

    //返回右子树的高度
    public int rightHeight() {
        if (right == null) {
            return 0;
        }
        return right.height();
    }


    //返回当前节点的高度，以该节点为根节点的高度
    public int height() {
        return Math.max(left == null ? 0 : left.height(), right == null ? 0 : right.height()) + 1;
    }

    //平衡二叉树——RR型单向左旋
    private void leftRotate(){
        //1.创建新的节点，以当前根节点的值
        Node newNode = new Node(value);
        //2.将新建节点的左子树设为当前节点的左子树
        newNode.left = left;
        //3.将新建节点的右子树设为当前节点右子树的左子树
        newNode.right = right.left;
        //4.把当前节点的值替换为右子树的值
        value = right.value;
        //5.当前节点的右子树设为当前节点右子树的右子树
        right = right.right;
        //6.将当前节点的左子数（节点）设为新建节点
        left = newNode;
    }

    //平衡二叉树——LL型单向右旋
    private void rightRotate(){
        //1.创建新的节点，以当前根节点的值
        Node newNode = new Node(value);
        //2.将新建节点的右子树设为当前节点的右子树
        newNode.right = right;
        //3.将新建节点的左子树设为当前节点左子树的右子树
        newNode.left = left.right;
        //4.把当前节点的值替换为左子树的值
        value = left.value;
        //5.当前节点的左子树设为当前节点左子树的左子树
        left = left.left;
        //6.将当前节点的左子数（节点）设为新建节点
        right = newNode;
    }


    /**
     * @param value 要删除的节点的值
     * @Description: 查找要删除的节点
     * @Author: 杨帅
     * @Date: 2022/6/13
     * @return: com.ys.example.binarySortTree.Node
     */
    public Node search(int value) {
        if (value == this.value) {
            return this;
        } else if (value < this.value) { //查找的值小于当前节点，向左子树递归查找
            if (this.left == null) { //判断左子树是否为null
                return null;
            }
            return this.left.search(value);
        } else { //若查找的值不小于当前节点，则向右子树递归查找
            if (this.right == null) {
                return null;
            }
            return this.right.search(value);
        }
    }

    /**
     * @param value 要找的节点的值
     * @Description: 查找要删除节点的父节点
     * @Author: 杨帅
     * @Date: 2022/6/13
     * @return: com.ys.example.binarySortTree.Node 要删除的节点的父节点，没有返回Null
     */
    public Node searchParent(int value) {
        if ((this.left != null && this.left.value == value) ||
                (this.right != null && this.right.value == value)) {
            return this;
        } else {
            if (this.left != null && value < this.value) {
                //查找节点小于当前节点的左子节点
                //向左子树递归查找
                return this.left.searchParent(value);
            } else if (this.right != null && value >= this.value) {
                //查找节点大于当前节点的左子节点
                //向右子树递归查找
                return this.right.searchParent(value);
            } else {
                //没有找到父节点
                return null;
            }
        }
    }

    //添加节点的方法
    //递归的方式添加，注意需要满足二叉排序树的要求
    public void add(Node node) {
        if (node == null) {
            return;
        }
        //添加的节点值小于当前节点的值
        if (node.value < this.value) {
            //如果当前节点的左子节点为空，直接放入
            if (this.left == null) {
                this.left = node;
            } else {
                //否则进行递归加入
                this.left.add(node);
            }
        } else { //添加的节点值大于当前节点的值
            if (this.right == null) {
                this.right = node;
            } else {
                this.right.add(node);
            }
        }
        //当添加完一个节点后，若|右子树的高度-左子树的高度|>1，要进行左旋转
        if(rightHeight()-leftHeight()>1){
            //分情况，如果它的右子树的左子树比右子树的右子树高，则先右转再左转
            if(right!=null&& right.leftHeight()>right.rightHeight()){
                right.rightRotate();
                leftRotate();
            }else{
                leftRotate();
            }
        }else if(leftHeight()-rightHeight()>1){
            //分情况，如果它的左子树的右子树比左子树的左子树高，则先左转再右转
            if(left!=null&& left.rightHeight()>left.leftHeight()){
                left.leftRotate();
                rightRotate();
            }else{
                rightRotate();
            }
        }
    }

    public void infixOrder() {
        if (this.left != null) {
            this.left.infixOrder();
        }
        System.out.println(this);
        if (this.right != null) {
            this.right.infixOrder();
        }
    }
}
