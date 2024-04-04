package tree

import org.junit.jupiter.api.Test

import org.junit.jupiter.api.Assertions.*
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Nested

class RBTreeTest {
    private lateinit var rbt: RBTree<Int, String>

    private fun checkSize(size: Long, rbt: RBTree<Int, String> = this.rbt) {
        assertEquals(size, rbt.size)
        checkCountNodes(size.toInt(), rbt)
    }

    private fun checkCountNodes(count: Int, rbt: RBTree<Int, String> = this.rbt) {
        assertEquals(count, rbt.getEntities().size)
    }

    private fun checkValue(key: Int, value: String, rbt: RBTree<Int, String> = this.rbt) {
        assertEquals(value, rbt.search(key))
    }

    private fun checkValues(data: Array<Pair<Int, String>>) {
        data.forEach {
            checkValue(it.first, it.second)
        }
    }

    private fun checkKeys(keys: List<Int>, rbt: RBTree<Int, String> = this.rbt) {
        assertEquals(keys.distinct().sorted(), rbt.getKeys())
    }

    private fun checkKeys(keys: Array<Pair<Int, String>>, rbt: RBTree<Int, String> = this.rbt) {
        assertEquals(keys.map { it.first }.distinct().sorted(), rbt.getKeys())
    }

    @BeforeEach
    fun setup() {
        rbt = RBTree()
    }

    @Nested
    inner class `Constructor tests` {
        @Test
        fun `without args`() {
            val rbt = RBTree<Int, String>()
            checkSize(0, rbt)
        }

        @Test
        fun `one entity`() {
            val rbt = RBTree(1, "Homka")
            assertEquals("Homka", rbt.search(1))
        }

        @Test
        fun `array of entities`() {
            val entities = arrayOf(
                Pair(35, "A"),
                Pair(21, "A"),
                Pair(25, "A"),
                Pair(62, "A"),
                Pair(12, "A"),
                Pair(62, "A"),
                Pair(122, "A"),
                Pair(621, "A"),
                Pair(121, "A"),
                Pair(362, "A"),
                Pair(35, "A"),
                Pair(523, "A"),
            )

            val rbt: RBTree<Int, String> = RBTree(entities)
            checkSize(10, rbt)
            checkKeys(entities.map { it.first }.distinct().sorted(), rbt)
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