package com.skeqi.mes.util;

public class BinarySearchTree {
    // 定义节点类
    public static class node {
        node leftnode;//左孩子
        node rightnode;//右孩子
        int data;

        public node(int data) {
            this.data = data;
            leftnode = null;
            rightnode = null;
        }
    }

    // 保存树的头节点
    private node head = null;

    // 创建树
    public node createBST(node n, int[] array) {
        if (array == null || array.length == 0){
        	return null;
        }
        //循环保存每个元素
        for (int i = 0; i < array.length; i++) {
            //每添加一个元素就重新返回新的头节点
            n = create(n, array[i]);
        }
        return n;
    }

    public node create(node n, int temp) {
        //如果节点为空就直接保存
        if (n == null) {
            n = new node(temp);
        } else {
            /*
             * 如果不为空就判断要插入的元素跟该节点的值比较，
             * 如果要插入元素的值比该元素的值大，说明应该插入到右子树上，如果小就插入到左子树，如果等于就说明该值已存在就不做操作
             * 以此类推，递归调用
             */
            if (n.data > temp) {
                n.leftnode = create(n.leftnode, temp);
            } else if (n.data < temp) {
                n.rightnode = create(n.rightnode, temp);
            } else {
                System.out.println(n.data + "已存在");
            }
        }
        return n;
    }

    // 找到最大元素
    public node findMax(node n) {
        //最大元素一定在数最右边 ，所以一直找右子树，找到最后一个
        if (n.rightnode != null) {
            return findMax(n.rightnode);
        } else {
            return n;
        }
    }

    // 找到最小元素
    public node findMin(node n) {
        if (n.leftnode != null) {
            return findMin(n.leftnode);
        } else {
            return n;
        }
    }

    // 删除指定元素
    public node delete(node n, int type) {
        //如果树为空就返回空，没找到指定元素。
        if (n == null){
        	return null;
        }

        if (type < n.data){
        	//去左子树找
        	delete(n.leftnode, type);
        }
        else if (type > n.data){
        	//去右子树找
        	delete(n.rightnode, type);
        }
        else {
            //找到要删除的节点
            //分三种情况
            //1.右子树不为空  就把该节点的值变为其有孩子的值，再把自己的右孩子变成原来右孩子的右孩子 - - ！其实是删除了自己的右孩子
            //2.左右孩子都为空 那就直接删除吧。
            //3.右孩子空，左孩子不空，就按一的方法赋值后删除自己的左孩子
            if (n.rightnode != null) {
                n.data = n.rightnode.data;
                n.rightnode = n.rightnode.rightnode;
            } else if (n.leftnode == null) {
                n = null;
            } else {
                n.data = n.leftnode.data;
                n.leftnode = n.leftnode.leftnode;
            }
        }
        return n;
    }

    // 添加指定元素
    //其实就是上面的create方法，创建树的方法就是循环添加元素。
    public node add(node n, int type) {
        if (n == null) {
            n = new node(type);
        } else if (n.data > type) {
            n.leftnode = add(n.leftnode, type);
        } else if (n.data < type) {
            n.rightnode = add(n.rightnode, type);
        } else {
            System.out.println("添加元素已存在");
        }
        return n;
    }

    public void inorder(node n){
        if(n!=null){
            inorder(n.leftnode);
            System.out.println(n.data);
            inorder(n.rightnode);
        }
    }

    public static void main(String[] args) {
        BinarySearchTree tree = new BinarySearchTree();
        int[] array = { 5, 4, 9, 8, 2, 6, 7, 2, 5, 6 };
        tree.head = tree.createBST(tree.head, array);
        tree.head = tree.add(tree.head, 9);
        tree.head = tree.delete(tree.head, 9);
        tree.head = tree.add(tree.head, 9);
        node n = tree.findMax(tree.head);
        System.out.println(n.data);
        tree.inorder(tree.head);
    }

}
