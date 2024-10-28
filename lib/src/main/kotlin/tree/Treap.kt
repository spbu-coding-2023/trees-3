package tree

import tree.node.TreapNode
import kotlin.random.Random

class Treap <K : Comparable<K>, V> : SearchTree<K, V, TreapNode<K, V>> {

    constructor() : super()
    constructor(key: K, value: V) {
        set(key,value)
    }
    constructor(pairs: Array<Pair<K, V>>) : this() {
        set(pairs)
    }

    private var allPriors = mutableListOf(0)

    private fun generatePrior(node: TreapNode<K, V>) {
        if (node.prior == 0) {
            node.prior = Random.nextInt()
        }

        if (allPriors.contains(node.prior)){
            generatePrior(node)
        } else {
            allPriors.add(node.prior)
        }
    }

    private fun merge(nodeL: TreapNode<K, V>?, nodeR: TreapNode<K, V>?) : TreapNode<K, V>? {
        if (nodeL == null || nodeR == null) {
            return nodeL ?: nodeR
        }

        if (nodeL.prior > nodeR.prior){
            nodeL.right = merge(nodeL.right, nodeR)

            return nodeL

        } else {
            nodeR.left = merge(nodeL, nodeR.left)

            return nodeR
        }
    }

    private fun split(node: TreapNode<K, V>?, key: K) : Pair<TreapNode<K, V>?, TreapNode<K, V>?> {
        if (node == null) return Pair(null,null)

        if (key > node.key) {
            val splitRezult = split(node.right, key)
            node.right = splitRezult.first

            return Pair(node, splitRezult.second)

        } else {
            val splitRezult = split(node.left, key)
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

    private fun identifyChild(parentNode: TreapNode<K, V>?, node: TreapNode<K, V>, value: TreapNode<K, V>?) {
        when {
            parentNode == null -> root = value
            parentNode.left == node -> parentNode.left = value
            else -> parentNode.right = value
        }
    }

    override fun insertNode(node: TreapNode<K, V>) {
        generatePrior(node)

        val splitTrees = split(root, node.key)
        root = merge(merge(splitTrees.first, node), splitTrees.second)
    }

    override fun removeNode(node: TreapNode<K, V>) {
        allPriors.remove(node.prior)

        val mergeTree = merge(node.left, node.right)
        val parentNode = root?.let { searchParentNode(node, it) }

        identifyChild(parentNode, node, mergeTree)
    }

    override fun createNode(key: K, value: V): TreapNode<K, V> = TreapNode(key, value)
}