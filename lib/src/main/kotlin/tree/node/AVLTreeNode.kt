package tree.node

internal class AVLTreeNode<K : Comparable<K>, V>(var height: Int = 1, key: K, value: V) :
    BinaryTreeNode<K, V, BSTreeNode<K, V>>(key, value)