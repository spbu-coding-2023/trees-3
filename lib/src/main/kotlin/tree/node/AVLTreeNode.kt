package tree.node

internal class AVLTreeNode<K : Comparable<K>, V>(key: K, value: V, var height: Int = 1) :
    BinaryTreeNode<K, V, BSTreeNode<K, V>>(key, value)