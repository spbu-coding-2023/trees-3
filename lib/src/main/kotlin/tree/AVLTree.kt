package tree

import tree.node.AVLTreeNode

class AVLTree<K : Comparable<K>, V> : SearchTree<K, V, AVLTreeNode<K, V>>() {
    override fun insertNode(node: AVLTreeNode<K, V>) {
        TODO("Insert node to tree")
    }

    override fun removeNode(node: AVLTreeNode<K, V>) {
        TODO("Remove node in tree")
    }

    override fun createNode(key: K, value: V): AVLTreeNode<K, V> {
        TODO("Create a node")
    }
}