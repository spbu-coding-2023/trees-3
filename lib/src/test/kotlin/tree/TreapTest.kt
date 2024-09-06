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
            assertEquals(listOf(Pair(1, "A")), treap.getEntities())
        }

        @Test
        fun `tree with some args`() {
            val treap: Treap<Int, String> = Treap(arrayOf(Pair(1, "A"), Pair(2, "B"), Pair(3, "C")))

            assertEquals(3, treap.size)
            assertEquals(listOf(Pair(1, "A"), Pair(2, "B"), Pair(3, "C")), treap.getEntities())
        }
    }

    @Nested
    inner class `Insert tests` {

        @Test
        fun `set new min key`() {
            treap = Treap(
                arrayOf(
                    Pair(2, "B"), Pair(3, "C"), Pair(4, "D"),
                    Pair(6, "F"), Pair(7, "G"), Pair(8, "H")
                )
            )

            treap.set(1, "A")
            assertEquals(7, treap.size)
            assertEquals(
                listOf(
                    Pair(1, "A"), Pair(2, "B"), Pair(3, "C"), Pair(4, "D"),
                    Pair(6, "F"), Pair(7, "G"), Pair(8, "H")
                ), treap.getEntities()
            )
        }

        @Test
        fun `set key in empty tree`() {
            treapEmpty.set(1, "A")
            assertEquals(listOf(Pair(1, "A")), treapEmpty.getEntities())
            assertEquals(1, treapEmpty.size)
        }
    }

    @Nested
    inner class `Remove tests` {

        @Test
        fun `remove root without children`() {
            treap = Treap(2, "B")

            assertEquals("B", treap.remove(2))
            assertEquals(0, treap.size)
        }

        @Test
        fun `remove the smallest key`() {

            treap = Treap(
                arrayOf(
                    Pair(1, "A"), Pair(2, "B"), Pair(3, "C"),
                    Pair(4, "D"), Pair(5, "E"), Pair(6, "F")
                )
            )

            assertEquals("A", treap.remove(1))
            assertEquals(5, treap.size)
            assertEquals(listOf(2, 3, 4, 5, 6), treap.getKeys())
        }

        @Test
        fun `remove the biggest key`() {

            treap = Treap(
                arrayOf(
                    Pair(1, "A"), Pair(2, "B"), Pair(3, "C"),
                    Pair(4, "D"), Pair(5, "E"), Pair(6, "F")
                )
            )

            assertEquals("F", treap.remove(6))
            assertEquals(5, treap.size)
            assertEquals(listOf(1, 2, 3, 4, 5), treap.getKeys())
        }

        @Test
        fun `remove key that is not in the tree`() {

            treap = Treap(
                arrayOf(
                    Pair(1, "A"), Pair(2, "B"), Pair(3, "C"),
                    Pair(4, "D"), Pair(5, "E"), Pair(6, "F")
                )
            )

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
            treap = Treap(1, "A")
            assertEquals(listOf(Pair(1, "A")), treap.getEntities())
        }
    }
}