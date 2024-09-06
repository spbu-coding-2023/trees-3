package tree

import org.junit.jupiter.api.Assertions.*
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Nested
import org.junit.jupiter.api.Test

class TreapTest{
    private lateinit var treap: Treap<Int, String>
    private val treapEmpty = Treap<Int, String>()

    @Nested
    inner class `Constructor tests` {
        @Test
        fun `tree without args`() {
            val treap = Treap<Int, String>()
            assertEquals(0, treap.size)
        }

        @Test
        fun `tree with one arg`() {
            val treap = Treap(1, "A")
            assertEquals("A", treap.search(1))
            assertEquals(listOf(1 to "A"), treap.getEntities())
        }

        @Test
        fun `tree with some args`() {
            val treap: Treap<Int, String> = Treap(arrayOf(1 to "A", 2 to "B", 3 to "C"))

            assertEquals(3, treap.size)
            assertEquals(listOf(1 to "A", 2 to "B", 3 to "C"), treap.getEntities())
        }
    }

    @Nested
    inner class `Insert tests` {

        @BeforeEach
        fun setup() {
            treap = Treap(arrayOf(1 to "A", 2 to "B", 3 to "C", 4 to "D", 5 to "E", 8 to "H"))
        }

        @Test
        fun `set new min key`() {
            treap.set(0, "Z")

            assertEquals(7, treap.size)
            assertEquals(listOf(0, 1, 2, 3, 4, 5, 8), treap.getKeys())
        }


        @Test
        fun `set new max key`() {
            treap.set(9, "I")

            assertEquals(7, treap.size)
            assertEquals(listOf(1, 2, 3, 4, 5, 8, 9), treap.getKeys())
        }

        @Test
        fun `set new key inside tree`() {
            treap.set(6, "F")

            assertEquals(7, treap.size)
            assertEquals(listOf(1, 2, 3, 4, 5, 6, 8), treap.getKeys())
        }

        @Test
        fun `set the same key`() {
            treap.set(5, "E")

            assertEquals(7, treap.size)
            assertEquals(listOf(1, 2, 3, 4, 5, 6, 8), treap.getKeys())
        }

        @Test
        fun `set key in empty tree`() {
            treapEmpty.set(1, "A")

            assertEquals(listOf(1 to "A"), treapEmpty.getEntities())
            assertEquals(1, treapEmpty.size)
        }
    }

    @Nested
    inner class `Remove tests` {

        @Test
        fun `remove root without children`() {
            treap = Treap(arrayOf(2 to "B"))

            assertEquals("B", treap.remove(2))
            assertEquals(0, treap.size)
        }

        @Test
        fun `remove min key`() {

            treap = Treap(arrayOf(1 to "A", 2 to "B", 3 to "C", 4 to "D", 5 to "E", 6 to "F"))

            assertEquals("A", treap.remove(1))
            assertEquals(5, treap.size)
            assertEquals(listOf(2, 3, 4, 5, 6), treap.getKeys())
        }

        @Test
        fun `remove max key`() {

            treap = Treap(arrayOf(1 to "A", 2 to "B", 3 to "C", 4 to "D", 5 to "E", 6 to "F"))

            assertEquals("F", treap.remove(6))
            assertEquals(5, treap.size)
            assertEquals(listOf(1, 2, 3, 4, 5), treap.getKeys())
        }

        @Test
        fun `remove key that is not in the tree`() {

            treap = Treap(arrayOf(1 to "A", 2 to "B", 3 to "C", 4 to "D", 5 to "E", 6 to "F"))

            assertEquals(null, treap.remove(0))
            assertEquals(6, treap.size)
            assertEquals(listOf(1, 2, 3, 4, 5, 6), treap.getKeys())
        }

        @Test
        fun `remove key from empty tree`() {
            assertEquals(null, treapEmpty.remove(1))
            assertEquals(0, treapEmpty.size)
        }
    }

    @Nested
    inner class `Create tests` {
        @Test
        fun `insert new node`() {
            treap = Treap(arrayOf(1 to "A"))
            assertEquals(listOf(1 to "A"), treap.getEntities())
        }
    }
}