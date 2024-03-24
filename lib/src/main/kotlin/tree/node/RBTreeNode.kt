package tree.node

enum class RBTreeColor {
    BLACK, RED
}

class RBTreeNode<K : Comparable<K>, V>(
    key: K,
    value: V,
    var color: RBTreeColor = RBTreeColor.RED,
    var parent: RBTreeNode<K, V>? = null
) : BinaryTreeNode<K, V, RBTreeNode<K, V>>(key, value)