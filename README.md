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
[docs.md](https://github.com/spbu-coding-2023/trees-3/blob/main/docs.md)

## 🐹 Gitbub stars
We love stars (ok, just me). I would be very pleased if you put a star on our project. \
![image](https://github.com/spbu-coding-2023/trees-3/assets/39369841/bb81c1b0-5d69-4b63-9a9a-49b952625ad7)

## Contacts
- [Dmitry Sheiko](https://github.com/Demon32123)
- [Anastasiia Kuzmina](https://github.com/far1yg)
- [Homa Kombaev](https://github.com/homka122)
