package tree

import tree.node.TreapNode
import kotlin.random.Random

class Treap <K : Comparable<K>, V> : SearchTree<K, V, TreapNode<K, V>> {
    constructor() : super()
    constructor(key: K, value: V) : super(key, value)
    constructor(pairs: Array<Pair<K, V>>) : super(pairs)

    private var allPriors = mutableListOf(0)

    private fun generatePrior(node: TreapNode<K, V>){

        if (node.prior == 0){
            node.prior = Random.nextInt()
        }
        if (allPriors.contains(node.prior)){
            generatePrior(node)
        } else {
            allPriors.add(node.prior)
        }
    }

    override fun insertNode(node: TreapNode<K, V>) {}

    override fun removeNode(node: TreapNode<K, V>) {}

    override fun createNode(key: K, value: V): TreapNode<K, V> {
        return TreapNode(key, value)
    }
}