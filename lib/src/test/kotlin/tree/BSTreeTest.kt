package tree

import org.junit.jupiter.api.Assertions.*
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Nested
import org.junit.jupiter.api.Test

class BSTreeTest {
    private lateinit var bst: BSTree<Int, String>
    private val bstEmpty = BSTree<Int, String>()

    @BeforeEach
    fun setup() {
        bst = BSTree()
        bst.set(2, "B")
        bst.set(3, "C")
        bst.set(4, "D")
        bst.set(6, "F")
        bst.set(7, "G")
        bst.set(8, "H")
    }

    @Nested
    inner class `Insert tests` {
        @Test
        fun `set new min key`() {
            assertEquals(null, bst.set(1, "A"))
            assertEquals(Pair(1, "A"), bst.getMin())
            assertEquals(7, bst.size)
        }

        @Test
        fun `set new max key`() {
            assertEquals(null, bst.set(9, "I"))
            assertEquals(Pair(9, "I"), bst.getMax())
            assertEquals(7, bst.size)
        }

        @Test
        fun `set new key inside tree`() {
            assertEquals(null, bst.set(5, "E"))
            assertEquals("E", bst.search(5))
            assertEquals(Pair(5, "E"), bst.predecessor(6))
            assertEquals(Pair(5, "E"), bst.successor(4))
            assertEquals(7, bst.size)
        }

        @Test
        fun `set the same key`() {
            assertEquals("G", bst.set(7, "g"))
            assertEquals("g", bst.search(7))
            assertEquals(Pair(7, "g"), bst.predecessor(8))
            assertEquals(Pair(7, "g"), bst.successor(6))
            assertEquals(6, bst.size)
        }

        @Test
        fun `set key in empty tree`() {
            bstEmpty.set(1, "A")
            assertEquals(listOf(Pair(1, "A")), bstEmpty.getEntities())
            assertEquals(Pair(null, null), bstEmpty.predecessor(1))
            assertEquals(Pair(null, null), bstEmpty.successor(1))
            assertEquals(1, bstEmpty.size)
        }
    }

    @Nested
    inner class `Remove tests` {
        @Test
        fun `remove key without child`() {
            assertEquals("B", bst.remove(2))
            assertEquals(Pair(3, "C"), bst.getMin())
            assertEquals(Pair(null, null), bst.predecessor(3))
            assertEquals(5, bst.size)
        }

        @Test
        fun `remove key with one child`() {
            assertEquals("C", bst.remove(3))
            assertEquals(Pair(2, "B"), bst.predecessor(4))
            assertEquals(Pair(4, "D"), bst.successor(2))
            assertEquals(5, bst.size)
        }

        @Test
        fun `remove key that is not in the tree`() {
            assertEquals("A", bst.remove(1))
            //
        }

        @Test
        fun `remove key from empty tree`() {
            assertEquals("A", bstEmpty.remove(1))
            //
        }
    }

    @Nested
    inner class `Create tests` {
        @Test
        fun `insert new node`() {
            assertEquals(null, bst.set(1, "A"))
            assertEquals("A", bst.search(1))
        }
    }
}