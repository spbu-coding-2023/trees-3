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
            bst = BSTree()
            bst.set(2, "B")
            bst.set(3, "C")
            bst.set(4, "D")
            bst.set(6, "F")
            bst.set(7, "G")
            bst.set(8, "H")

            assertEquals(listOf(2, 3, 4, 6, 7, 8), bst.getKeys())
            assertEquals(listOf("B", "C", "D", "F", "G", "H"), bst.getValues())
            assertEquals(
                listOf(Pair(2, "B"), Pair(3, "C"), Pair(4, "D"), Pair(6, "F"), Pair(7, "G"), Pair(8, "H")),
                bst.getEntities()
            )
        }

        @Test
        fun `empty tree`() {
            val bstEmpty = BSTree<Int, String>()

            assertEquals(listOf<Int>(), bstEmpty.getKeys())
            assertEquals(listOf<String>(), bstEmpty.getValues())
            assertEquals(listOf<Pair<Int, String>>(), bstEmpty.getEntities())
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

}


