package tree

import org.junit.jupiter.api.Test

import org.junit.jupiter.api.Assertions.*
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Nested

class RBTreeTest {
    private lateinit var rbt: RBTree<Int, String>

    @BeforeEach
    fun setup() {
        rbt = RBTree()
    }

    @Nested
    inner class `Constructor tests` {
        @Test
        fun `without args`() {
            assertEquals(0, RBTree<Int, String>().size)
            assertEquals(0, RBTree<Int, String>().getEntities().size)
        }

        @Test
        fun `one entity`() {
            val rbt = RBTree(1, "Homka")
            assertEquals("Homka", rbt.search(1))
        }

        @Test
        fun `array of entities`() {
            val entities = arrayOf(
                Pair(35, 1),
                Pair(21, 1),
                Pair(25, 1),
                Pair(62, 1),
                Pair(12, 1),
                Pair(62, 1),
                Pair(122, 1),
                Pair(621, 1),
                Pair(121, 1),
                Pair(362, 1),
                Pair(35, 1),
                Pair(523, 1),
            )

            val rbt = RBTree(entities)
            assertEquals(10, rbt.size)
            assertEquals(listOf(12, 21, 25, 35, 62, 121, 122, 362, 523, 621), rbt.getKeys())
        }
    }

    @Nested
    inner class `Insertion tests` {
        @Test
        fun `set one key on empty tree`() {
            rbt.set(1, "Homka")
            assertEquals(1, rbt.size)
            assertEquals("Homka", rbt.search(1))
        }
    }
}