package tree

import tree.node.TreapNode

class Treap <K : Comparable<K>, V> : SearchTree<K, V, TreapNode<K, V>> {
    constructor() : super()
    constructor(key: K, value: V) : super(key, value)
    constructor(pairs: Array<Pair<K, V>>) : super(pairs)

    override fun insertNode(node: TreapNode<K, V>) {}

    override fun removeNode(node: TreapNode<K, V>) {}

    override fun createNode(key: K, value: V): TreapNode<K, V> {
        return TreapNode(key, value)
    }
}