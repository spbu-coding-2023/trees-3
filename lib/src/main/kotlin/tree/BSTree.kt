package tree

import tree.node.BSTreeNode

class BSTree<K : Comparable<K>, V> : SearchTree<K, V, BSTreeNode<K, V>>() {
    override fun insertNode(node: BSTreeNode<K, V>) {
        TODO("Insert node to tree")
    }

    override fun removeNode(node: BSTreeNode<K, V>) {
        TODO("Remove node in tree")
    }

    override fun createNode(key: K, value: V): BSTreeNode<K, V> {
        TODO("Create a node")
    }
}