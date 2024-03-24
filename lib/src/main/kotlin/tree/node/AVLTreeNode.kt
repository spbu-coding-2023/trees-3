package tree.node

class AVLTreeNode<K : Comparable<K>, V>(key: K, value: V, var height: Int = 1) :
    BinaryTreeNode<K, V, AVLTreeNode<K, V>>(key, value)