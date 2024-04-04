package tree

import org.junit.jupiter.api.Test

import org.junit.jupiter.api.Assertions.*
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Nested
import kotlin.random.Random

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
    inner class `Set tests` {
        fun setTest(data: Array<Pair<Int, String>>) {
            rbt.set(data)
            checkValues(data)
            checkSize(data.size.toLong())
        }

        @Test
        fun `set one key on empty tree`() {
            val data = arrayOf(1 to "Homka")
            setTest(data)
        }

        @Test
        fun `set second key as right child`() {
            val data = arrayOf(1 to "Homka", 2 to "Dima")
            setTest(data)
        }

        @Test
        fun `set second key as left child`() {
            val data = arrayOf(2 to "Homka", 1 to "Dima")
            setTest(data)
        }

        @Test
        fun `set third key as left child`() {
            val data = arrayOf(2 to "Homka", 3 to "Dima", 1 to "Nastya")
            setTest(data)
        }

        @Test
        fun `insert 1`() {
            val rbt = RBTree<Int, Int?>()
            val setWithNull: (Int) -> (Unit) = { key -> rbt.set(key, null) }

            listOf(35, 21, 25, 62, 12, 62, 122, 621, 121, 362, 35, 523).forEach(setWithNull)
        }

        @Test
        fun `stress test`() {
            val rbt = RBTree<Int, Int?>()
            val generator = Random(5)
            val setWithNull: (Int) -> (Unit) = { key -> rbt.set(key, null) }

            val randomKeys = mutableListOf<Int>()
            for (i in 1..1000000) {
                val randomValue = generator.nextInt()
                randomKeys.add(randomValue)
                setWithNull(randomValue)
            }

            val randomKeysDistinct = randomKeys.distinct()
            assertEquals(randomKeysDistinct.sorted(), rbt.getKeys())
            assertEquals(randomKeysDistinct.size.toLong(), rbt.size)
        }
    }
}