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
}