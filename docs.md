## 🐤 Docs
- We have three types of tree: BST, AVL, RBT
- Each tree has constructor that accept:
  - Nothing (Empty tree)
  - Key and Value
  - Array of Pairs of Key\Value
- Each tree has these methods:
  - `set(key: K, value: V): V?` \
  Stores the value for the given key. Return previous value.

  - `set(pairs: Array<Pair<K, V>>): MutableList<V?>` \
  Stores the values for the given keys. Return previous values.

  - `setIfEmpty(key: K, value: V): V?` \
  Stores the value for the given key if there is no pair with that key. Return previous value.

  - `setIfEmpty(pairs: Array<Pair<K, V>>): MutableList<V?>` \
  Stores the values for the given keys if there is no pair with that key. Return previous values.

  - `remove(key: K): V?` \
  Remove the value for the given key. Return previous value.

  - `remove(keys: Array<K>): MutableList<V?>` \
  Remove the values for the given keys. Return previous values.

  - `search(key: K): V?` \
  Return the value for the given key.

  - `getKeys(): List<K>` \
  Returns a complete list of keys.
 
  - `getValues(): List<V>` \
  Returns a complete list of values.

  - `getEntities(): List<Pair<K, V>>` \
  Returns a complete list of pairs key value.
 
  - `getMin(): Pair<K?, V?>` \
  Returns pair with the minimum key.

  - `getMax(): Pair<K?, V?>` \
  Returns pair with the maximum key.
 
  - `successor(key: K): Pair<K?, V?>` \
  Returns the pair with next ascending key.

  - `predecessor(key: K): Pair<K?, V?>` \
  Returns the pair with previous ascending key
  
  - `clear()` \
  Remove all keys in a tree.

  - `preOrderTraversal(action: (Pair<K, V>) -> (Unit))` \
  Apply [action] on all pairs by preorder tree traversal.
 
  - `inOrderTraversal(action: (Pair<K, V>) -> (Unit))` \
  Apply [action] on all pairs by inorder tree traversal.

  - `postOrderTraversal(action: (Pair<K, V>) -> (Unit))` \
  Apply [action] on all pairs by postorder tree traversal.
