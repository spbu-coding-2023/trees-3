package tree

import org.junit.jupiter.api.Assertions.*
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Nested
import org.junit.jupiter.api.Test

class BSTreeTest {
    private lateinit var bst: BSTree<Int, String>
    private val bstEmpty = BSTree<Int, String>()

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
        fun `remove single root`() {
            bst = BSTree()
            bst.set(2, "B")
            assertEquals("B", bst.remove(2))
            assertEquals(0, bst.size)
        }

        @Test
        fun `remove root with two children(list)`() {
            bst = BSTree()
            bst.set(2, "B")
            bst.set(3, "C")
            bst.set(1, "A")
            bst.set(0, "Z")
            bst.set(5, "E")
            bst.set(4, "D")
            assertEquals("B", bst.remove(2))
            assertEquals(5, bst.size)
            assertEquals(listOf(0, 1, 3, 4, 5), bst.getKeys())
        }

        @Test
        fun `remove root with two children`() {
            bst = BSTree()
            bst.set(2, "B")
            bst.set(1, "A")
            bst.set(0, "Z")
            bst.set(6, "F")
            bst.set(4, "D")
            bst.set(5, "E")
            assertEquals("B", bst.remove(2))
            assertEquals(5, bst.size)
            assertEquals(listOf(0, 1, 4, 5, 6), bst.getKeys())
        }

        @Test
        fun `remove root with two children2`() {
            bst = BSTree()
            bst.set(2, "B")
            bst.set(1, "A")
            bst.set(0, "Z")
            bst.set(6, "F")
            bst.set(7, "G")
            bst.set(4, "D")
            bst.set(5, "E")
            assertEquals("B", bst.remove(2))
            assertEquals(6, bst.size)
            assertEquals(listOf(0, 1, 4, 5, 6, 7), bst.getKeys())
        }

        @Test
        fun `remove root with two children3`() {
            bst = BSTree()
            bst.set(2, "B")
            bst.set(1, "A")
            bst.set(0, "Z")
            bst.set(8, "K")
            bst.set(3, "C")
            bst.set(5, "E")
            bst.set(4, "D")
            bst.set(6, "F")
            assertEquals("B", bst.remove(2))
            assertEquals(7, bst.size)
            assertEquals(listOf(0, 1, 3, 4, 5, 6, 8), bst.getKeys())
        }

        @Test
        fun `remove root without left child`() {
            bst = BSTree()
            bst.set(2, "B")
            bst.set(3, "C")
            bst.set(5, "E")
            bst.set(4, "D")
            assertEquals("B", bst.remove(2))
            assertEquals(3, bst.size)
            assertEquals(listOf(3, 4, 5), bst.getKeys())
        }

        @Test
        fun `remove root without right child`() {
            bst = BSTree()
            bst.set(6, "F")
            bst.set(3, "C")
            bst.set(5, "E")
            bst.set(4, "D")
            assertEquals("F", bst.remove(6))
            assertEquals(3, bst.size)
            assertEquals(listOf(3, 4, 5), bst.getKeys())
        }

        @Test
        fun `remove key without left child`() {
            bst = BSTree()
            bst.set(1, "A")
            bst.set(2, "B")
            bst.set(3, "C")
            assertEquals("B", bst.remove(2))
            assertEquals(2, bst.size)
            assertEquals(listOf(Pair(1, "A"), Pair(3, "C")), bst.getEntities())
        }

        @Test
        fun `remove key without right child`() {
            bst = BSTree()
            bst.set(3, "C")
            bst.set(2, "B")
            bst.set(1, "A")
            assertEquals("B", bst.remove(2))
            assertEquals(2, bst.size)
            assertEquals(listOf(Pair(1, "A"), Pair(3, "C")), bst.getEntities())
        }

        @Test
        fun `remove key with two children`() {
            bst = BSTree()
            bst.set(1, "A")
            bst.set(3, "C")
            bst.set(2, "B")
            bst.set(4, "D")
            assertEquals("C", bst.remove(3))
            assertEquals(3, bst.size)
            assertEquals(listOf(Pair(1, "A"), Pair(2, "B"), Pair(4, "D")), bst.getEntities())
        }

        @Test
        fun `remove key that is not in the tree`() {
            bst = BSTree()
            bst.set(2, "B")
            bst.set(3, "C")
            bst.set(0, "Z")
            assertEquals(null, bst.remove(1))
            assertEquals(3, bst.size)
            assertEquals(listOf(Pair(0, "Z"), Pair(2, "B"), Pair(3, "C")), bst.getEntities())
        }

        @Test
        fun `remove key from empty tree`() {
            assertEquals(null, bstEmpty.remove(1))
            assertEquals(0, bstEmpty.size)
        }
    }

    @Nested
    inner class `Create tests` {
        @Test
        fun `insert new node`() {
            bst = BSTree(1, "A")
            assertEquals(listOf(Pair(1, "A")), bst.getEntities())
        }
    }
}