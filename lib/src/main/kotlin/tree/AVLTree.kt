package tree

import tree.node.AVLTreeNode


class AVLTree<K : Comparable<K>, V> : SearchTree<K, V, AVLTreeNode<K, V>>() {

    private fun height(node: AVLTreeNode<K, V>?): Int {
        return node?.height ?: 0
    }

    private fun updateHeight(node: AVLTreeNode<K, V>?) {
        if (node != null) {
            node.height = 1 + maxOf(height(node.left), height(node.right))
        }
    }

    private fun getBalance(node: AVLTreeNode<K, V>): Int {
        return height(node.left) - height(node.right)
    }

    private fun rotateRight(node: AVLTreeNode<K, V>): AVLTreeNode<K, V>? {
        val nodeLeft = node.left
        val nodeLeftRight = nodeLeft?.right

        nodeLeft?.right = node
        node.left = nodeLeftRight

        updateHeight(node)
        updateHeight(nodeLeft)

        return nodeLeft
    }

    private fun rotateLeft(node: AVLTreeNode<K, V>): AVLTreeNode<K, V>? {
        val nodeRight = node.right
        val nodeRightLeft = nodeRight?.left

        nodeRight?.left = node
        node.right = nodeRightLeft

        updateHeight(node)
        updateHeight(nodeRight)

        return nodeRight
    }

    private fun rebalanced(root: AVLTreeNode<K, V>?, node: AVLTreeNode<K, V>?): AVLTreeNode<K, V>?{
        updateHeight(root)
        val balance = root?.let { getBalance(it) }
        val rootLeft = root?.left
        val rootRight = root?.right

        if (node != null && balance != null){
            if (balance > 1 && rootLeft != null) {
                if (node.key < rootLeft.key) {

                    return rotateRight(root)
                }

                root.left = root.left?.let { rotateLeft(it) }
                return rotateRight(root)
            }

            if (balance < -1 && rootRight != null) {
                if (node.key > rootRight.key) {
                    return rotateLeft(root)
                }

                    root.right = root.right?.let { rotateRight(it) }

                    return rotateLeft(root)
                }
            }

        return root
    }

    private fun insertRecursive(root: AVLTreeNode<K, V>?, node: AVLTreeNode<K, V>?): AVLTreeNode<K, V>? {
        if (root == null) {
            return node
        }

        if (node != null) {
            if (root.key > node.key) {
                root.left = insertRecursive(root.left, node)
            } else  if (root.key < node.key) {
                root.right = insertRecursive(root.right, node)
            }
        }

        return rebalanced(root, node)
    }

    override fun insertNode(node: AVLTreeNode<K, V>) {
        root = insertRecursive(root, node)
    }

    private fun minValueNode(node: AVLTreeNode<K, V>): AVLTreeNode<K, V> {
        var current: AVLTreeNode<K, V> = node
        var leftNode = current.left

        while (leftNode != null) {
            current = leftNode
            leftNode = current.left
        }

        return current
    }

    private fun removeRecursive(root: AVLTreeNode<K, V>?, node: AVLTreeNode<K, V>): AVLTreeNode<K, V>? {
        var rootNode = root

        if (rootNode == null) {
            return root
        }

        if (rootNode.key > node.key) {
            rootNode.left = removeRecursive(rootNode.left, node)
            return rebalanced(rootNode, node)
        } else if (rootNode.key < node.key) {
            rootNode.right = removeRecursive(rootNode.right, node)
            return rebalanced(rootNode, node)
        }

        val rootNodeRight = rootNode.right
        val rootNodeLeft = rootNode.left
        if (rootNodeLeft == null || rootNodeRight == null) {
            rootNode = rootNodeLeft ?: rootNodeRight
        } else {
            val mostLeftChild: AVLTreeNode<K, V> = minValueNode(rootNodeRight)
            rootNode = mostLeftChild
            rootNode.right = removeRecursive(rootNode.right, rootNode)
        }

        if (rootNode == null) {
            return root
        }

        return rebalanced(rootNode, node)
    }

    override fun removeNode(node: AVLTreeNode<K, V>) {
        root = removeRecursive(root, node)
    }

    override fun createNode(key: K, value: V): AVLTreeNode<K, V> {
        return AVLTreeNode(key, value)
    }
}
