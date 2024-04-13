package tree.node

abstract class BinaryTreeNode<K: Comparable<K>, V, Node>(
    val key: K,
    var value: V,
    var right: Node? = null,
    var left: Node? = null
)