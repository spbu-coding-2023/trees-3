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

    private fun insertRecursive(root: AVLTreeNode<K, V>?, node: AVLTreeNode<K, V>?): AVLTreeNode<K, V>? {
        if (root == null) {
            return node
        } else if (node != null) {
            if (root.key > node.key) {
                root.left = insertRecursive(root.left, node)
            } else  if (root.key < node.key) {
                    root.right = insertRecursive(root.right, node)
            }
        }

        updateHeight(root)
        val balance = getBalance(root)
        val rootLeft = root.left
        val rootRight = root.right

        if (node != null){
            if (balance > 1) {
                if (rootLeft != null) {
                    if (node.key < rootLeft.key) {
                        return rotateRight(root)
                    } else {
                        root.left = root.left?.let { rotateLeft(it) }
                        return rotateRight(root)
                    }
                }
            }
            if (balance < -1) {
                if (rootRight != null) {
                    if (node.key > rootRight.key) {
                        return rotateLeft(root)
                    } else {
                        root.right = root.right?.let { rotateRight(it) }
                        return rotateLeft(root)
                    }
                }
            }
        }
        return root
    }

    override fun insertNode(node: AVLTreeNode<K, V>) {
        root = insertRecursive(root, node)
    }

    override fun removeNode(node: AVLTreeNode<K, V>) {
        TODO("Remove node in tree")
    }

    override fun createNode(key: K, value: V): AVLTreeNode<K, V> {
        return AVLTreeNode(key, value)
    }
}
