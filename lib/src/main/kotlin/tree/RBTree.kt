package tree

import tree.node.RBTreeColor
import tree.node.RBTreeNode

class RBTree<K : Comparable<K>, V> : SearchTree<K, V, RBTreeNode<K, V>> {
    constructor() : super()
    constructor(key: K, value: V) : super(key, value)
    constructor(pairs: Array<Pair<K, V>>) : super(pairs)

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
            node.color = RBTreeColor.BLACK
            return
        }

        if (node.parent?.parent == null) {
            return
        }

        insertFix(node)
    }

    private fun insertFix(newNode: RBTreeNode<K, V>) {
        var node = newNode

        while (node.parent?.color === RBTreeColor.RED) {
            var parent = node.parent ?: return
            var grandpa = parent.parent ?: return

            if (parent == grandpa.right) {
                val uncle = grandpa.left ?: return

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
                        rightRotate(node)
                    }
                    parent.color = RBTreeColor.BLACK
                    grandpa.color = RBTreeColor.RED
                    leftRotate(grandpa)
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
                        leftRotate(node)
                    }
                    parent.color = RBTreeColor.BLACK
                    grandpa.color = RBTreeColor.RED
                    rightRotate(grandpa)
                }
            }

            if (node == root) {
                break
            }
        }

        root?.color = RBTreeColor.BLACK
    }

    override fun removeNode(node: RBTreeNode<K, V>) {
        val transplantedNode: RBTreeNode<K, V>?

        var originalColor = node.color
        val leftNode = node.left
        val rightNode = node.right

        if (leftNode == null) {
            transplantedNode = rightNode
            rbTransplant(node, rightNode)
        } else if (rightNode == null) {
            transplantedNode = leftNode
            rbTransplant(node, leftNode)
        } else {
            val minNode = getMinNode(rightNode)

            originalColor = minNode.color
            transplantedNode = minNode.right
            if (minNode.parent == node) {
                transplantedNode?.parent = minNode
            } else {
                rbTransplant(minNode, minNode.right)
                minNode.right = node.right
                minNode.right?.parent = minNode
            }

            rbTransplant(node, minNode)
            minNode.left = node.left
            minNode.left?.parent = minNode
            minNode.color = node.color
        }

        if (originalColor == RBTreeColor.BLACK && transplantedNode != null) {
            deleteFix(transplantedNode)
        }
    }

    override fun createNode(key: K, value: V): RBTreeNode<K, V> {
        return RBTreeNode(key, value)
    }

    private fun deleteFix(transplantedNode: RBTreeNode<K, V>) {
        var node: RBTreeNode<K, V>? = transplantedNode

        while (node != root && node?.color == RBTreeColor.BLACK) {
            val parentNode = node.parent ?: return

            if (node == parentNode.left) {
                var uncle = parentNode.right ?: return

                if (uncle.color == RBTreeColor.RED) {
                    uncle.color = RBTreeColor.BLACK
                    parentNode.color = RBTreeColor.RED
                    leftRotate(parentNode)
                    uncle = parentNode.right ?: return
                }

                if (uncle.left?.color == RBTreeColor.BLACK && uncle.right?.color == RBTreeColor.BLACK) {
                    uncle.color = RBTreeColor.RED
                    node = parentNode
                } else {
                    if (uncle.right?.color == RBTreeColor.BLACK) {
                        uncle.left?.color = RBTreeColor.BLACK
                        uncle.color = RBTreeColor.RED
                        rightRotate(uncle)
                        uncle = parentNode.right ?: return
                    }

                    uncle.color = parentNode.color
                    parentNode.color = RBTreeColor.BLACK
                    uncle.right?.color = RBTreeColor.BLACK
                    leftRotate(parentNode)
                    node = root
                }
            } else {
                var uncle = parentNode.left ?: return

                if (uncle.color == RBTreeColor.RED) {
                    uncle.color = RBTreeColor.BLACK
                    parentNode.color = RBTreeColor.RED
                    rightRotate(parentNode)
                    uncle = parentNode.left ?: return
                }

                if (uncle.right?.color == RBTreeColor.BLACK && uncle.right?.color == RBTreeColor.BLACK) {
                    uncle.color = RBTreeColor.RED
                    node = parentNode
                } else {
                    if (uncle.left?.color == RBTreeColor.BLACK) {
                        uncle.right?.color = RBTreeColor.BLACK
                        uncle.color = RBTreeColor.RED
                        leftRotate(uncle)
                        uncle = parentNode.left ?: return
                    }

                    uncle.color = parentNode.color
                    parentNode.color = RBTreeColor.BLACK
                    uncle.left?.color = RBTreeColor.BLACK
                    rightRotate(parentNode)
                    node = root
                }
            }
        }
    }

    private tailrec fun getMinNode(node: RBTreeNode<K, V>): RBTreeNode<K, V> {
        val leftNode = node.left

        if (leftNode == null) {
            return node
        } else {
            return getMinNode(leftNode)
        }
    }

    private fun rbTransplant(previous: RBTreeNode<K, V>, curr: RBTreeNode<K, V>?) {
        val parent = previous.parent

        if (parent == null) {
            root = curr
        } else if (previous == parent.left) {
            parent.left = curr
        } else {
            parent.right = curr
        }

        if (curr != null) {
            curr.parent = parent
        }
    }

    private fun leftRotate(node: RBTreeNode<K, V>) {
        val rightNode = node.right ?: return

        node.right = rightNode.left
        rightNode.left?.parent = node
        rightNode.parent = node.parent

        val parent = node.parent
        if (parent == null) {
            root = rightNode
        } else if (parent.left == node) {
            parent.left = rightNode
        } else {
            parent.right = rightNode
        }

        rightNode.left = node
        node.parent = rightNode
    }

    private fun rightRotate(node: RBTreeNode<K, V>) {
        val leftNode = node.left ?: return

        node.left = leftNode.right
        leftNode.right?.parent = node
        leftNode.parent = node.parent

        val parent = node.parent
        if (parent == null) {
            root = leftNode
        } else if (parent.left == node) {
            parent.left = leftNode
        } else {
            parent.right = leftNode
        }

        leftNode.right = node
        node.parent = leftNode
    }
}
