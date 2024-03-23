package tree

import tree.node.BinaryTreeNode

abstract class SearchTree<K: Comparable<K>, V, Node: BinaryTreeNode<K, V, Node>> ()
    {
        protected var root: Node? = null
        var size: Long = 0
            private set
        var recentlyKey: K? = null
            private set
        constructor(key: K, value:V): this(){
            set(key, value)
        }
        constructor(pairs: Array<Pair<K, V>>): this(){
            set(pairs)
        }
        protected fun insertNode(node: Node){
            TODO("Insert node to tree")
        }
        protected fun removeNode(node: Node){
            TODO("Remove node in tree")
        }
        protected fun createNode(key: K, value: V): Node{
            TODO("Creating a tree node")
        }
        protected fun searchNode(key: K): Node?{
            TODO("Finding a node in a tree by key")
        }
        fun set(key: K, value: V): V?{
            TODO("Adding a node in a tree if there is no such node in the tree")
        }
        fun set(pairs: Array<Pair<K, V>>): Array<V?>{
            TODO("Adding multiple node in a tree if there is no such node in the tree")
        }
        fun setIfEmpty(key: K, value: V): V?{
            TODO("Adding a node if such a node already exists in the tree")
        }
        fun setIfEmpty(pairs: Array<Pair<K, V>>): Array<V?> {
            TODO("Adding several nodes if such a node already exists in the tree")
        }
        fun remove(key: K){
            TODO("Adding multiple nodes if such nodes already exist in the tree")
        }
        fun remove(keys: Array<K>): Array<V?> {
            TODO("Removing multiple catches by keys")
        }
        fun search(key: K){
            TODO("Search by key")
        }
        fun getKeys(): Array<K> {
            TODO("Returns a complete list of keys")
        }
        fun getValues(): Array<V> {
            TODO("Returns a complete list of values")
        }
        fun getEntities(): Array<Pair<K, V>> {
            TODO("Returns a complete list of pair key value")
        }
        fun getMin(): Pair<K?, V?>{
            TODO("Returns nodes with the minimum key")
        }
        fun getMax(): Pair<K?, V?>{
            TODO("Returns nodes with the maximum key")
        }
        fun successor(key: K): Pair<K?, V?>{
            TODO("Returns the next ascending key")
        }
        fun predeccessor(key: K): Pair<K?, V?>{
            TODO("Returns the previous ascending key")
        }
        fun clear(){
            TODO("Removing all nodes in a tree")
        }
        fun preOrderTraversal(action: Pair<K, V>){
            TODO("Prefix tree traversal")
        }
        fun inOrderTraversal(action: Pair<K, V>){
            TODO("Symmetrical tree traversal")
        }
        fun postOrderTraversal(action: Pair<K, V>){
            TODO("Postfix tree traversal")
        }
}

