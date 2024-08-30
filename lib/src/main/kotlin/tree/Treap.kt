package tree

import tree.node.BSTreeNode
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

    private fun merge(nodeL: TreapNode<K, V>?, nodeR: TreapNode<K, V>?) : TreapNode<K, V>? {
        if (nodeL == null && nodeR == null ) return null
        if (nodeL == null) return nodeR
        if (nodeR == null) return nodeL

        if (nodeL.prior > nodeR.prior){
            nodeL.right = merge(nodeL.right, nodeR)
            return nodeL.right
        } else {
            nodeR.left = merge(nodeL, nodeR.left)
            return nodeR.left
        }
    }

    private fun split(node: TreapNode<K, V>?, key: K) : Pair<TreapNode<K, V>?, TreapNode<K, V>?> {
        if (node == null) return Pair(null,null)

        else if (key > node.key) {
            val splitRezult: Pair<TreapNode<K, V>?, TreapNode<K, V>?> = split(node.right, key)
            node.right = splitRezult.first

            return Pair(node, splitRezult.second)
        } else {
            val splitRezult: Pair<TreapNode<K, V>?, TreapNode<K, V>?> = split(node.left, key)
            node.left = splitRezult.second

            return Pair(splitRezult.first, node)
        }
    }

    private fun searchParentNode(node: TreapNode<K, V>, parentNode: TreapNode<K, V>): TreapNode<K, V>? {
        if (parentNode.left == node || parentNode.right == node) {
            return parentNode
        }

        if (node.key < parentNode.key) {
            return parentNode.left?.let { searchParentNode(node, it) }
        } else {
            return parentNode.right?.let { searchParentNode(node, it) }
        }
    }

    override fun insertNode(node: TreapNode<K, V>) {
        generatePrior(node)

        val splitTrees = split(root, node.key)
        merge(merge(splitTrees.first, node), splitTrees.second)
    }

    override fun removeNode(node: TreapNode<K, V>) {}

    override fun createNode(key: K, value: V): TreapNode<K, V> {
        return TreapNode(key, value)
    }
}