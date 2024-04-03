package tree

import org.junit.jupiter.api.Assertions.*
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Nested
import org.junit.jupiter.api.Test

class BSTreeTest {
    private lateinit var bst: BSTree<Int, String>
    private lateinit var bstTwoNode: BSTree<Int, String>
    private val bstEmpty = BSTree<Int, String>()

    @Nested
    inner class `Insert tests` {
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

        @Test
        fun `set new min key`() {
            bst.set(1, "A")
            assertEquals(7, bst.size)
            assertEquals(
                listOf(
                    Pair(1, "A"), Pair(2, "B"), Pair(3, "C"), Pair(4, "D"),
                    Pair(6, "F"), Pair(7, "G"), Pair(8, "H")
                ), bst.getEntities()
            )
        }

        @Test
        fun `set new max key`() {
            bst.set(9, "I")
            assertEquals(7, bst.size)
            assertEquals(
                listOf(
                    Pair(2, "B"), Pair(3, "C"), Pair(4, "D"),
                    Pair(6, "F"), Pair(7, "G"), Pair(8, "H"), Pair(9, "I")
                ), bst.getEntities()
            )
        }

        @Test
        fun `set new key inside tree`() {
            bst.set(5, "E")
            assertEquals(7, bst.size)
            assertEquals(
                listOf(
                    Pair(2, "B"), Pair(3, "C"), Pair(4, "D"), Pair(5, "E"),
                    Pair(6, "F"), Pair(7, "G"), Pair(8, "H")
                ), bst.getEntities()
            )
        }

        @Test
        fun `set the same key`() {
            bst.set(7, "g")
            assertEquals(6, bst.size)
            assertEquals(
                listOf(
                    Pair(2, "B"), Pair(3, "C"), Pair(4, "D"),
                    Pair(6, "F"), Pair(7, "g"), Pair(8, "H")
                ), bst.getEntities()
            )
        }

        @Test
        fun `set key in empty tree`() {
            bstEmpty.set(1, "A")
            assertEquals(listOf(Pair(1, "A")), bstEmpty.getEntities())
            assertEquals(1, bstEmpty.size)
        }
    }

    @Nested
    inner class `Remove tests` {

        @Test
        fun `remove key without child`() {
            bstTwoNode = BSTree()
            bstTwoNode.set(2, "B")
            bstTwoNode.set(3, "C")
            assertEquals("B", bstTwoNode.remove(2))
            assertEquals(1, bstTwoNode.size)
            assertEquals(Pair(3, "C"), bstTwoNode.getMin())
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
            assertEquals(null, bst.remove(1))
        }

        @Test
        fun `remove key from empty tree`() {
            assertEquals(null, bstEmpty.remove(1))
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