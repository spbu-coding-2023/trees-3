package tree

import org.junit.jupiter.api.Test

import org.junit.jupiter.api.Assertions.*
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Nested

class SearchTreeTest {
    private lateinit var bst: BSTree<Int, String>
    private val bstWithoutNodes = BSTree<Int, String>()

    @BeforeEach
    fun setup() {
        bst = BSTree()
        bst.set(5, "A")
        bst.set(8, "A")
        bst.set(6, "A")
        bst.set(1, "A")
        bst.set(2, "A")
        bst.set(4, "A")
        bst.set(3, "A")
        bst.set(10, "A")
    }

    @Nested
    inner class `Constructor tests` {
        @Test
        fun `tree without args`() {
            val bst = BSTree<Int, String>()
            assertEquals(0, bst.size)
        }

        @Test
        fun `tree with one arg`() {
            val bst = BSTree(1, "A")
            assertEquals(listOf(Pair(1, "A")), bst.getEntities())
        }

        @Test
        fun `tree with some args`() {
            val bst: BSTree<Int, String> = BSTree(
                arrayOf(
                    Pair(1, "A"),
                    Pair(2, "B"),
                    Pair(3, "C"),
                )
            )

            assertEquals(3, bst.size)
            assertEquals(listOf(Pair(1, "A"), Pair(2, "B"), Pair(3, "C")), bst.getEntities())
        }
    }

    @Nested
    inner class `Min and max tests` {
        @Test
        fun `getMin() return right pair`() {
            assertEquals(Pair(1, "A"), bst.getMin())
        }

        @Test
        fun `getMax() return right pair`() {
            assertEquals(Pair(10, "A"), bst.getMax())
        }

        @Test
        fun `getMin() return pair of nulls on empty tree`() {
            assertEquals(Pair(null, null), bstWithoutNodes.getMin())
        }

        @Test
        fun `getMax() return pair of nulls on empty tree`() {
            assertEquals(Pair(null, null), bstWithoutNodes.getMax())
        }
    }

    @Nested
    inner class `Clear tests` {
        @Test
        fun `clear() removes all nodes from tree`() {
            bst.clear()
            assertEquals(0, bst.size)
            assertEquals(emptyList<String>(), bst.getValues())
        }

        @Test
        fun `clear() works fine on empty tree`() {
            bst.clear()
            bst.clear()
            assertEquals(0, bst.size)
            assertEquals(emptyList<String>(), bst.getValues())
        }
    }

    @Nested
    inner class `Successor and predecessor tests`() {
        @Test
        fun `successor() test with existed keys`() {
            assertEquals(Pair(2, "A"), bst.successor(1))
            assertEquals(Pair(3, "A"), bst.successor(2))
            assertEquals(Pair(4, "A"), bst.successor(3))
            assertEquals(Pair(5, "A"), bst.successor(4))
            assertEquals(Pair(6, "A"), bst.successor(5))
            assertEquals(Pair(8, "A"), bst.successor(6))
            assertEquals(Pair(10, "A"), bst.successor(8))
            assertEquals(Pair(null, null), bst.successor(10))
        }

        @Test
        fun `successor() if key don't exists it return next key`() {
            assertEquals(Pair(8, "A"), bst.successor(7))
        }

        @Test
        fun `successor() in empty tree`() {
            assertEquals(Pair(null, null), bstWithoutNodes.successor(7))
        }

        @Test
        fun `predecessor() in empty tree`() {
            assertEquals(Pair(null, null), bstWithoutNodes.predecessor(7))
        }

        @Test
        fun `predecessor() test with existed keys`() {
            assertEquals(Pair(null, null), bst.predecessor(1))
            assertEquals(Pair(1, "A"), bst.predecessor(2))
            assertEquals(Pair(2, "A"), bst.predecessor(3))
            assertEquals(Pair(3, "A"), bst.predecessor(4))
            assertEquals(Pair(4, "A"), bst.predecessor(5))
            assertEquals(Pair(5, "A"), bst.predecessor(6))
            assertEquals(Pair(6, "A"), bst.predecessor(8))
            assertEquals(Pair(8, "A"), bst.predecessor(10))
        }
    }

    @Nested
    inner class `Getting test` {
        @Test
        fun `tree with node`() {
            assertEquals(listOf(1, 2, 3, 4, 5, 6, 8, 10), bst.getKeys())
            assertEquals(listOf("A", "A", "A", "A", "A", "A", "A", "A"), bst.getValues())
            assertEquals(
                listOf(
                    Pair(1, "A"), Pair(2, "A"), Pair(3, "A"), Pair(4, "A"),
                    Pair(5, "A"), Pair(6, "A"), Pair(8, "A"), Pair(10, "A")
                ),
                bst.getEntities()
            )
        }

        @Test
        fun `empty tree`() {
            assertEquals(listOf<Int>(), bstWithoutNodes.getKeys())
            assertEquals(listOf<String>(), bstWithoutNodes.getValues())
            assertEquals(listOf<Pair<Int, String>>(), bstWithoutNodes.getEntities())
        }
    }

    @Nested
    inner class `Setting tests` {

        @Test
        fun `set new nodes`() {
            assertEquals(null, bst.set(19, "B"))
            assertEquals(9, bst.size)
            assertEquals(listOf(1, 2, 3, 4, 5, 6, 8, 10, 19), bst.getKeys())

            assertEquals(null, bst.setIfEmpty(11, "B"))
            assertEquals(10, bst.size)
            assertEquals(listOf(1, 2, 3, 4, 5, 6, 8, 10, 11, 19), bst.getKeys())

            assertEquals(listOf(null, null, null), bst.set(arrayOf(Pair(20, "B"), Pair(-5, "B"), Pair(7, "B"))))
            assertEquals(7, bst.recentlyKey)
            assertEquals(13, bst.size)
            assertEquals(listOf(-5, 1, 2, 3, 4, 5, 6, 7, 8, 10, 11, 19, 20), bst.getKeys())

            assertEquals(listOf(null, null, null), bst.setIfEmpty(arrayOf(Pair(9, "B"), Pair(0, "B"), Pair(22, "B"))))
            assertEquals(22, bst.recentlyKey)
            assertEquals(16, bst.size)
            assertEquals(listOf(-5, 0, 1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 19, 20, 22), bst.getKeys())
        }

        @Test
        fun `set the same nodes`() {
            assertEquals("A", bst.set(10, "B"))
            assertEquals(8, bst.size)
            assertEquals(
                listOf(
                    Pair(1, "A"), Pair(2, "A"), Pair(3, "A"), Pair(4, "A"),
                    Pair(5, "A"), Pair(6, "A"), Pair(8, "A"), Pair(10, "B")
                ),
                bst.getEntities()
            )

            assertEquals("A", bst.setIfEmpty(8, "B"))
            assertEquals(8, bst.size)
            assertEquals(
                listOf(
                    Pair(1, "A"), Pair(2, "A"), Pair(3, "A"), Pair(4, "A"),
                    Pair(5, "A"), Pair(6, "A"), Pair(8, "A"), Pair(10, "B")
                ),
                bst.getEntities()
            )

            assertEquals(listOf("A", "A", "A"), bst.set(arrayOf(Pair(2, "B"), Pair(6, "B"), Pair(4, "B"))))
            assertEquals(4, bst.recentlyKey)
            assertEquals(8, bst.size)
            assertEquals(
                listOf(
                    Pair(1, "A"), Pair(2, "B"), Pair(3, "A"), Pair(4, "B"),
                    Pair(5, "A"), Pair(6, "B"), Pair(8, "A"), Pair(10, "B")
                ),
                bst.getEntities()
            )

            assertEquals(listOf("A", "A", "A"), bst.setIfEmpty(arrayOf(Pair(1, "B"), Pair(5, "B"), Pair(3, "B"))))
            assertEquals(4, bst.recentlyKey)
            assertEquals(8, bst.size)
            assertEquals(
                listOf(
                    Pair(1, "A"), Pair(2, "B"), Pair(3, "A"), Pair(4, "B"),
                    Pair(5, "A"), Pair(6, "B"), Pair(8, "A"), Pair(10, "B")
                ),
                bst.getEntities()
            )
        }

        @Test
        fun `set nodes in empty tree`() {
            assertEquals(null, bstWithoutNodes.set(19, "B"))
            assertEquals(1, bstWithoutNodes.size)
            assertEquals(listOf(Pair(19, "B")), bstWithoutNodes.getEntities())

            bstWithoutNodes.clear()

            assertEquals(null, bstWithoutNodes.setIfEmpty(19, "B"))
            assertEquals(1, bstWithoutNodes.size)
            assertEquals(listOf(Pair(19, "B")), bstWithoutNodes.getEntities())

            bstWithoutNodes.clear()

            assertEquals(
                listOf(null, null, null),
                bstWithoutNodes.set(arrayOf(Pair(12, "B"), Pair(0, "B"), Pair(7, "B")))
            )
            assertEquals(7, bstWithoutNodes.recentlyKey)
            assertEquals(3, bstWithoutNodes.size)
            assertEquals(listOf(Pair(0, "B"), Pair(7, "B"), Pair(12, "B")), bstWithoutNodes.getEntities())

            bstWithoutNodes.clear()

            assertEquals(
                listOf(null, null, null),
                bstWithoutNodes.setIfEmpty(arrayOf(Pair(12, "B"), Pair(0, "B"), Pair(7, "B")))
            )
            assertEquals(7, bstWithoutNodes.recentlyKey)
            assertEquals(3, bstWithoutNodes.size)
            assertEquals(listOf(Pair(0, "B"), Pair(7, "B"), Pair(12, "B")), bstWithoutNodes.getEntities())
        }
    }

    @Nested
    inner class `Search tests` {
        @Test
        fun `search exist node`() {
            assertEquals("A", bst.search(2))
        }

        @Test
        fun `search don't exist node`() {
            assertEquals(null, bst.search(0))
        }

        @Test
        fun `empty tree`() {
            assertEquals(null, bstWithoutNodes.search(2))
        }
    }

    @Nested
    inner class `Remove tests` {
        @Test
        fun `remove exist nodes`() {
            assertEquals(8, bst.size)
            assertEquals("A", bst.remove(2))
            assertEquals(listOf(1, 3, 4, 5, 6, 8, 10), bst.getKeys())
            assertEquals(7, bst.size)

            assertEquals(listOf("A", "A", "A"), bst.remove(arrayOf(1, 5, 10)))
            assertEquals(listOf(3, 4, 6, 8), bst.getKeys())
            assertEquals(4, bst.size)
        }

        @Test
        fun `remove don't exist nodes`() {
            assertEquals(8, bst.size)
            assertEquals(null, bst.remove(0))
            assertEquals(listOf(1, 2, 3, 4, 5, 6, 8, 10), bst.getKeys())
            assertEquals(8, bst.size)

            assertEquals(listOf(null, null, null), bst.remove(arrayOf(11, 35, 100)))
            assertEquals(listOf(1, 2, 3, 4, 5, 6, 8, 10), bst.getKeys())
            assertEquals(8, bst.size)
        }

        @Test
        fun `remove nodes from empty tree`() {
            assertEquals(0, bstWithoutNodes.size)
            assertEquals(null, bstWithoutNodes.remove(2))
            assertEquals(listOf<Int>(), bstWithoutNodes.getKeys())
            assertEquals(0, bstWithoutNodes.size)

            assertEquals(listOf(null, null, null), bstWithoutNodes.remove(arrayOf(1, 3, 10)))
            assertEquals(listOf<Int>(), bstWithoutNodes.getKeys())
            assertEquals(0, bstWithoutNodes.size)
        }
    }

    @Nested
    inner class `Traversal methods` {
        @Test
        fun `inOrderTraversal return empty list on empty tree`() {
            val rbt = BSTree<Int, String>()
            val result = mutableListOf<Int>()

            rbt.inOrderTraversal { result.add(it.first) }

            assertEquals(listOf<Int>(), result)
        }

        @Test
        fun `inOrderTraversal return keys in inOrder order`() {
            val bst = BSTree<Int, String>()
            bst.set(arrayOf(1 to "Homka", 2 to "Dima", 3 to "Nastya"))

            val result = mutableListOf<Int>()
            bst.inOrderTraversal { result.add(it.first) }

            assertEquals(listOf<Int>(1, 2, 3), bst.getKeys())
        }

        @Test
        fun `preOrderTraversal return empty list on empty tree`() {
            val rbt = RBTree<Int, String>()
            val result = mutableListOf<Int>()

            rbt.preOrderTraversal { result.add(it.first) }

            assertEquals(listOf<Int>(), result)
        }

        @Test
        fun `preOrderTraversal return keys in preorder order`() {
            val bst = BSTree<Int, String>()
            bst.set(arrayOf(2 to "Homka", 1 to "Dima", 3 to "Nastya"))

            val result = mutableListOf<Int>()
            bst.preOrderTraversal { result.add(it.first) }

            assertEquals(listOf<Int>(2, 1, 3), result)
        }

        @Test
        fun `postOrderTraversal return empty list on empty tree`() {
            val rbt = RBTree<Int, String>()
            val result = mutableListOf<Int>()

            rbt.preOrderTraversal { result.add(it.first) }

            assertEquals(listOf<Int>(), result)
        }

        @Test
        fun `postOrderTraversal return keys in preorder order`() {
            val bst = BSTree<Int, String>()
            bst.set(arrayOf(2 to "Homka", 1 to "Dima", 3 to "Nastya"))

            val result = mutableListOf<Int>()
            bst.postOrderTraversal { result.add(it.first) }

            assertEquals(listOf<Int>(1, 3, 2), result)
        }
    }
}


