package tree

import tree.node.BinaryTreeNode

abstract class SearchTree<K : Comparable<K>, V, Node : BinaryTreeNode<K, V, Node>>() {
    protected var root: Node? = null
    var size: Long = 0
        private set
    var recentlyKey: K? = null
        private set

    constructor(key: K, value: V) : this() {
        set(key, value)
    }

    constructor(pairs: Array<Pair<K, V>>) : this() {
        set(pairs)
    }

    protected abstract fun insertNode(node: Node)
    protected abstract fun removeNode(node: Node)
    protected abstract fun createNode(key: K, value: V): Node

    /**
     * Finding a node in a tree by key
     */
    protected fun searchNode(key: K): Node? {
        var node = root

        while (node != null) {
            if (key < node.key) {
                node = node.left
            } else if (key > node.key) {
                node = node.right
            } else {
                return node
            }
        }

        return null
    }

    /**
     * Stores the value for the given key. Return previous value.
     */
    fun set(key: K, value: V): V? {
        recentlyKey = key
        val node = searchNode(key)

        if (node == null) {
            insertNode(createNode(key, value))
            size++
            return null
        }

        val result = node.value
        node.value = value
        return result
    }

    /**
     * Stores the values for the given keys. Return previous values.
     */
    fun set(pairs: Array<Pair<K, V>>): Array<V?> {
        TODO("Adding multiple node in a tree if there is no such node in the tree")
    }

    /**
     * Stores the value for the given key if there is no pair with that key. Return previous value.
     */
    fun setIfEmpty(key: K, value: V): V? {
        TODO("Adding a node if such a node already exists in the tree")
    }

    /**
     * Stores the values for the given keys if there is no pair with that key. Return previous values.
     */
    fun setIfEmpty(pairs: Array<Pair<K, V>>): Array<V?> {
        TODO("Adding several nodes if such a node already exists in the tree")
    }

    /**
     * Remove the value for the given key. Return previous value.
     */
    fun remove(key: K): V? {
        TODO("Adding multiple nodes if such nodes already exist in the tree")
    }

    /**
     * Remove the values for the given keys. Return previous values.
     */
    fun remove(keys: Array<K>): Array<V?> {
        TODO("Removing multiple catches by keys")
    }

    /**
     * Return the value for the given key.
     */
    fun search(key: K): V? {
        TODO("Search by key")
    }

    /**
     * Returns a complete list of keys.
     */
    fun getKeys(): Array<K> {
        TODO("Returns a complete list of keys")
    }

    /**
     * Returns a complete list of values.
     */
    fun getValues(): Array<V> {
        TODO("Returns a complete list of values")
    }

    /**
     * Returns a complete list of pairs key value.
     */
    fun getEntities(): Array<Pair<K, V>> {
        TODO("Returns a complete list of pair key value")
    }

    /**
     * Returns pair with the minimum key.
     */
    fun getMin(): Pair<K?, V?> {
        TODO("Returns nodes with the minimum key")
    }

    /**
     * Returns pair with the maximum key.
     */
    fun getMax(): Pair<K?, V?> {
        TODO("Returns nodes with the maximum key")
    }

    /**
     * Returns the pair with next ascending key
     */
    fun successor(key: K): Pair<K?, V?> {
        TODO("Returns the next ascending key")
    }

    /**
     * Returns the pair with previous ascending key
     */
    fun predecessor(key: K): Pair<K?, V?> {
        TODO("Returns the previous ascending key")
    }

    /**
     * Remove all keys in a tree.
     */
    fun clear() {
        TODO("Removing all nodes in a tree")
    }

    /**
     * Apply [action] on all pairs by preorder tree traversal.
     */
    fun preOrderTraversal(action: (Pair<K, V>) -> (Unit)) {
        TODO("Prefix tree traversal")
    }

    /**
     * Apply [action] on all pairs by inorder tree traversal.
     */
    fun inOrderTraversal(action: (Pair<K, V>) -> (Unit)) {
        TODO("Symmetrical tree traversal")
    }

    /**
     * Apply [action] on all pairs by postorder tree traversal.
     */
    fun postOrderTraversal(action: (Pair<K, V>) -> (Unit)) {
        val root = this.root ?: return

        fun helper(node: Node?) {
            if (node == null) return

            helper(node.left)
            helper(node.right)
            action(Pair(node.key, node.value))
        }

        helper(root)
    }
}

