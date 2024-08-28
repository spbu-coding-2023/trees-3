package tree.node

class TreapNode<K : Comparable<K>, V>(key: K, value: V, var prior: Int = 0) :
    BinaryTreeNode<K, V, TreapNode<K, V>>(key, value)