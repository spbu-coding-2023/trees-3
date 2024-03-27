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
            var parent = node.parent ?: return
            var grandpa = parent.parent ?: return

            if (parent == grandpa.right) {
                val uncle = grandpa.right ?: return

                if (uncle.color == RBTreeColor.RED) {
                    uncle.color = RBTreeColor.BLACK
                    parent.color = RBTreeColor.BLACK
                    grandpa.color = RBTreeColor.RED
                    node = grandpa
                } else {
                    if (node == parent.left) {
                        node = parent
                        parent = node.parent ?: return
                        grandpa = parent.parent ?: return
                        rightRotation(node)
                    }
                    parent.color = RBTreeColor.BLACK
                    grandpa.color = RBTreeColor.RED;
                    leftRotation(grandpa)
                }
            } else {
                val uncle = grandpa.right ?: return

                if (uncle.color == RBTreeColor.RED) {
                    uncle.color = RBTreeColor.BLACK
                    parent.color = RBTreeColor.BLACK
                    grandpa.color = RBTreeColor.RED
                    node = grandpa
                } else {
                    if (node == parent.right) {
                        node = parent
                        parent = node.parent ?: return
                        grandpa = parent.parent ?: return
                        leftRotation(node)
                    }
                    parent.color = RBTreeColor.BLACK
                    grandpa.color = RBTreeColor.RED
                    rightRotation(grandpa)
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

    private fun throwError() {
        throw UnknownError("Bug. Please contact with developer of library");
    }
}
