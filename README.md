## NamelessKitty.kt - library that appreciate your data ✨

**NamelessKitty.kt** is library that help you work with your data by simple interface, using binary search trees under the hood.

<img src="https://github.com/spbu-coding-2023/trees-3/assets/39369841/2384c62a-a6c7-4fba-a2dc-02ee91b57d79" width="128"/>

## ⚡️Quick start

```kotlin
import tree.RBTree

fun main() {
    val rbt = RBTree<Int, String>()
    
    rbt.set(1, "Hello world")
    
    println(rbt.search(1))
}
```

Yes, that is really simple. \
We Love KISS.

## ✨ Features
- **Null-safety code** - We didn't use anything that could break your code
- **18 public methods** - Thanks to Dima
- **Simple interface** - Our library is easy to use and we proud of it
- **Easy to expand** - We provide simple abstact class that allow you create new type of trees and use it for your tasks
- **Generic types** - Use whatever you want types. We handle with it.

## 👀 Examples

#### 📖 **Add array of data**
```kotlin
import tree.RBTree

fun main() {
    val data = arrayOf(122 to "Homka", 21 to "Dima", 25 to "Nastya")
    val dataKeys = data.map { it.first }.toTypedArray()
    
    val rbt = RBTree<Int, String>(data)
    rbt.set(data)
    rbt.remove(dataKeys)
}
```

#### 📖 **Print all data in ascending order**
```kotlin
import tree.RBTree

fun main() {
    val data = arrayOf(122 to "Homka", 21 to "Dima", 25 to "Nastya")

    val rbt = RBTree(data)

    rbt.inOrderTraversal {
        println("key: ${it.first}, value: ${it.second}")
    }
}
```

#### 📖 **Put only first incoming data**
```kotlin
import tree.RBTree

fun main() {
    val data = arrayOf(1 to "Homka", 2 to "Dima", 3 to "Nastya", 1 to "Homka")

    val rbt = RBTree(data)

    data.forEach {
        val previousValue = rbt.setIfEmpty(it.first, it.second)
        
        if (previousValue != null) {
            println("Data with key: ${it.first} already exists and contain: ${previousValue}")
        }
    }
}
```

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

## 🐹 Gitbub stars
We love stars (ok, just me). I would be very pleased if you put a star on our project. \
![image](https://github.com/spbu-coding-2023/trees-3/assets/39369841/bb81c1b0-5d69-4b63-9a9a-49b952625ad7)

## Contacts
- [Dmitry Sheiko] (https://github.com/Demon32123)
- [Anastasiia Kuzmina] (https://github.com/far1yg)
- [Homa Kombaev] (https://github.com/homka122)
