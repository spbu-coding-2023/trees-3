package tree

import tree.node.RBTreeNode

class RBTree<K : Comparable<K>, V> : SearchTree<K, V, RBTreeNode<K, V>>() {
    override fun insertNode(node: RBTreeNode<K, V>) {
        TODO("Insert node to tree")
    }

    override fun removeNode(node: RBTreeNode<K, V>) {
        TODO("Remove node in tree")
    }

    override fun createNode(key: K, value: V): RBTreeNode<K, V> {
        return RBTreeNode(key, value)
    }
}