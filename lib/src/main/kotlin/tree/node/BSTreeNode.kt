package tree.node

class BSTreeNode<K : Comparable<K>, V>(key: K, value: V) : BinaryTreeNode<K, V, BSTreeNode<K, V>>(key, value)