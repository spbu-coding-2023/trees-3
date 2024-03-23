package tree.node

internal class BSTreeNode<K : Comparable<K>, V>(key: K, value: V) : BinaryTreeNode<K, V, BSTreeNode<K, V>>(key, value)