package tree

import tree.node.BSTreeNode

class BSTree<K : Comparable<K>, V> : SearchTree<K, V, BSTreeNode<K, V>> {

    constructor() : super()
    constructor(key: K, value: V) : super(key, value)
    constructor(pairs: Array<Pair<K, V>>) : super(pairs)

    override fun insertNode(node: BSTreeNode<K, V>) {
        var parentNode = root
        var treeNode = root

        while (treeNode != null) {
            parentNode = treeNode
            if (node.key < treeNode.key) {
                treeNode = treeNode.left
            } else {
                treeNode = treeNode.right
            }
        }
        
        if (parentNode == null) {
            root = node
        } else if (node.key < parentNode.key) {
            parentNode.left = node
        } else {
            parentNode.right = node
        }
    }

    private fun searchParentNode(node: BSTreeNode<K, V>, parentNode: BSTreeNode<K, V>): BSTreeNode<K, V>? {
        if (parentNode.left == node || parentNode.right == node) {
            return parentNode
        }

        if (node.key < parentNode.key) {
            return parentNode.left?.let { searchParentNode(node, it) }
        } else {
            return parentNode.right?.let { searchParentNode(node, it) }
        }
    }

    private fun getMinSubtree(node: BSTreeNode<K, V>): BSTreeNode<K, V> {
        val nodeN = node.left
        if (nodeN == null) {
            return node
        } else {
            return getMinSubtree(nodeN)
        }
    }

    override fun removeNode(node: BSTreeNode<K, V>) {
        val parentNode = root?.let { searchParentNode(node, it) }

        if (parentNode == null) {
            root = null
            return
        }

        if (node.left == null && node.right == null) {
            if (parentNode.left == node) {
                parentNode.left = null
            } else {
                parentNode.right = null
            }

        } else if (node.left == null || node.right == null) {
            if (node.left == null) {

                if (parentNode.left == node) {
                    parentNode.left = node.right
                } else {
                    parentNode.right = node.right
                }

            } else {
                if (parentNode.left == node) {
                    parentNode.left = node.left
                } else {
                    parentNode.right = node.left
                }
            }
        } else {
            val successor = getMinSubtree(node);
            removeNode(getMinSubtree(node));
            successor.left = node.left
            successor.right = node.right

            if (parentNode.left == node) {
                parentNode.left = successor
            } else {
                parentNode.right = successor
            }
        }
    }

    override fun createNode(key: K, value: V): BSTreeNode<K, V> {
        return BSTreeNode(key, value)
    }
}