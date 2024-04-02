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
        return searchNode(key)?.value
    }

    /**
     * Returns a complete list of keys.
     */
    fun getKeys(): List<K> {
        val result = mutableListOf<K>()

        inOrderTraversal {
            result.add(it.first)
        }

        return result
    }

    /**
     * Returns a complete list of values.
     */
    fun getValues(): List<V> {
        val result = mutableListOf<V>()

        inOrderTraversal {
            result.add(it.second)
        }

        return result
    }

    /**
     * Returns a complete list of pairs key value.
     */
    fun getEntities(): List<Pair<K, V>> {
        val result = mutableListOf<Pair<K, V>>()

        inOrderTraversal { result.add(it) }

        return result
    }

    /**
     * Returns pair with the minimum key.
     */
    fun getMin(): Pair<K?, V?> {
        var node = root
        while (node?.left != null) {
            node = node.left
        }

        return Pair(node?.key, node?.value)
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
        var node = root
        var successor: Node? = null

        while (node != null) {
            if (node.key > key) {
                successor = node
                node = node.left
            } else {
                node = node.right
            }
        }

        return Pair(successor?.key, successor?.value)
    }

    /**
     * Returns the pair with previous ascending key
     */
    fun predecessor(key: K): Pair<K?, V?> {
        var node = root
        var predecessor: Node? = root?.left

        while (node != null) {
            if (node.key < key) {
                predecessor = node
                node = node.right
            } else {
                node = node.left
            }
        }

        return Pair(predecessor?.key, predecessor?.value)
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
        fun inOrder(node: Node?) {
            if (node != null) {
                inOrder(node.left)
                action(Pair(node.key, node.value))
                inOrder(node.right)
            }
        }

        inOrder(this.root)
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
