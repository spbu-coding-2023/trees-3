package tree

import tree.node.RBTreeColor
import tree.node.RBTreeNode

class RBTree<K : Comparable<K>, V> : SearchTree<K, V, RBTreeNode<K, V>>() {
    override fun insertNode(node: RBTreeNode<K, V>) {
        var tmpNode = root
        var tmpNodeParent: RBTreeNode<K, V>? = null
        while (tmpNode != null) {
            tmpNodeParent = tmpNode
            tmpNode = if (node.key < tmpNode.key) tmpNode.left else tmpNode.right
        }
        node.parent = tmpNodeParent

        if (tmpNodeParent == null) {
            root = node
        } else if (node.key < tmpNodeParent.key) {
            tmpNodeParent.left = node
        } else {
            tmpNodeParent.right = node
        }

        node.left = null
        node.right = null
        node.color = RBTreeColor.RED

        insertFixup(node)
    }

    private fun insertFixup(node: RBTreeNode<K, V>) {
        TODO()
    }
    override fun removeNode(node: RBTreeNode<K, V>) {
        TODO("Remove node in tree")
    }

    override fun createNode(key: K, value: V): RBTreeNode<K, V> {
        return RBTreeNode(key, value)
    }

    private fun leftRotation(x: RBTreeNode<K, V>): Boolean {
        val y = x.right ?: return false

        x.right = y.left
        y.left?.parent = x;
        y.parent = x.parent

        val parent = x.parent
        if (parent == null) {
            root = y
        } else if (parent.left == x) {
            parent.left = y
        } else {
            parent.right = y
        }

        y.left = x
        x.parent = y

        return true
    }

    private fun rightRotation(y: RBTreeNode<K, V>): Boolean {
        val x = y.left ?: return false

        y.left = x.right
        x.right?.parent = y
        x.parent = y.parent

        val parent = y.parent
        if (parent == null) {
            root = x
        } else if (parent.left == y) {
            parent.left = x
        } else {
            parent.right = x
        }

        x.right = y
        y.parent = x

        return true
    }

    private fun setNewRoot(root: RBTreeNode<K, V>) {
        this.root = root
    }
}