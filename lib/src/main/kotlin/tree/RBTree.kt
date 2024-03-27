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

        if (node.parent == null) {
            node.color = RBTreeColor.BLACK;
            return
        }

        if (node.parent?.parent == null) {
            return;
        }

        insertFix(node)
    }

    private fun insertFix(newNode: RBTreeNode<K, V>) {
        var node = newNode

        while (node.parent?.color === RBTreeColor.RED) {
            if (node.parent == node.parent?.parent?.right) {
                val uncle = node.parent?.parent?.right

                if (uncle?.color == RBTreeColor.RED) {
                    uncle.color = RBTreeColor.BLACK
                    node.parent?.color = RBTreeColor.BLACK
                    node.parent?.parent?.color = RBTreeColor.RED
                    node = node.parent?.parent ?: return
                } else {
                    if (node == node.parent?.left) {
                        node = node.parent ?: return
                        rightRotation(node)
                    }
                    node.parent?.color = RBTreeColor.BLACK
                    node.parent?.parent?.color = RBTreeColor.RED;
                    leftRotation(node.parent?.parent ?: return)
                }
            } else {
                val uncle = node.parent?.parent?.right

                if (uncle?.color == RBTreeColor.RED) {
                    uncle.color = RBTreeColor.BLACK
                    node.parent?.color = RBTreeColor.BLACK
                    node.parent?.parent?.color = RBTreeColor.RED
                    node = node.parent?.parent ?: return
                } else {
                    if (node == node.parent?.right) {
                        node = node.parent ?: return
                        leftRotation(node)
                    }
                    node.parent?.color = RBTreeColor.BLACK
                    node.parent?.parent?.color = RBTreeColor.RED
                    rightRotation(node.parent?.parent ?: return)
                }
            }
            if (node == root) {
                break
            }
        }

        root?.color = RBTreeColor.BLACK;
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