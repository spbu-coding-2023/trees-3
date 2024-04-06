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
        fun `predecessor() if key don't exists it return previous key`() {
            assertEquals(Pair(6, "A"), bst.predecessor(7))
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
    inner class `Search test` {
        @Test
        fun `tree with node`() {
            bst = BSTree()
            bst.set(2, "B")
            bst.set(3, "C")
            bst.set(4, "D")

            assertEquals("B", bst.search(2))
        }

        @Test
        fun `empty tree`() {
            val bstEmpty = BSTree<Int, String>()

            assertEquals(null, bstEmpty.search(2))
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


