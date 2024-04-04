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
        data.distinctBy { it.first }.forEach {
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
        fun `set third key as right child`() {
            val data = arrayOf(2 to "Homka", 1 to "Dima", 3 to "Nastya")
            setTest(data)
        }

        @Test
        fun `set third key as root from left`() {
            val data = arrayOf(3 to "Homka", 2 to "Dima", 1 to "Nastya")
            setTest(data)
        }

        @Test
        fun `set third key as root from right`() {
            val data = arrayOf(1 to "Homka", 2 to "Dima", 3 to "Nastya")
            setTest(data)
        }

        @Test
        fun `set key to the left when uncle is red`() {
            val data = arrayOf(5 to "Homka", 10 to "Dima", 15 to "Nastya", 7 to "Rodion")
            setTest(data)
        }

        @Test
        fun `set key to the right when uncle is red`() {
            val data = arrayOf(5 to "Homka", 10 to "Dima", 15 to "Nastya", 17 to "Rodion")
            setTest(data)
        }

        @Test
        fun `set key to the left when uncle is black and parent red`() {
            val data = arrayOf(15 to "Homka", 10 to "Dima", 5 to "Nastya", 1 to "Rodion", 6 to "spisladqo")
            setTest(data)
        }

        @Test
        fun `set key to the left when uncle is null and parent red`() {
            val data = arrayOf(
                5 to "Homka", 10 to "Dima", 15 to "Nastya", 20 to "Rodion",
                25 to "spisladqo", 30 to "Vichislav Zorich", 27 to "Sibiri4ok"
            )
            setTest(data)
        }

        @Test
        fun `set key to the right when uncle is black and parent red`() {
            val data = arrayOf(1 to "Homka", 5 to "Dima", 10 to "Nastya", 15 to "Rodion", 14 to "spisladqo")
            setTest(data)
        }

        @Test
        fun `set key to the right when uncle is null and parent red`() {
            val data = arrayOf(
                30 to "Homka", 25 to "Dima", 20 to "Nastya", 15 to "Rodion",
                10 to "spisladqo", 5 to "Vichislav Zorich", 6 to "Sibiri4ok"
            )
            setTest(data)
        }

        @Test
        fun `set some random keys`() {
            val data = arrayOf(
                35 to "Homka", 21 to "Dima", 25 to "Nastya", 622 to "Rodion",
                12 to "spisladqo", 62 to "Vichislav Zorich", 122 to "Sibiri4ok",
                621 to "kotenok-barista", 121 to "vlad zavtra v zal", 362 to "karim",
                36 to "seriy cardinal", 523 to "katya", 251 to "sonechka"
            )
            setTest(data)
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

    @Nested
    inner class `Remove Tests` {
        fun removeTest(data: Array<Pair<Int, String>>, key: Int) {
            rbt.set(data)
            rbt.remove(key)
            checkValues(data.filter { it.first != key }.toTypedArray())
            checkSize((data.size - 1).toLong())

        }

        @Test
        fun `remove one key`() {
            val data = arrayOf(1 to "Homka")
            removeTest(data, 1)
        }

        @Test
        fun `remove leaf key from right`() {
            val data = arrayOf(2 to "Homka", 3 to "Dima", 1 to "Nastya")
            removeTest(data, 3)
        }

        @Test
        fun `remove single leaf key from right`() {
            val data = arrayOf(2 to "Homka", 3 to "Dima")
            removeTest(data, 3)
        }

        @Test
        fun `remove leaf key from left`() {
            val data = arrayOf(2 to "Homka", 3 to "Dima", 1 to "Nastya")
            removeTest(data, 1)
        }

        @Test
        fun `remove single leaf key from left`() {
            val data = arrayOf(2 to "Homka", 1 to "Nastya")
            removeTest(data, 1)
        }

        @Test
        fun `remove root in tree with 3 nodes`() {
            val data = arrayOf(2 to "Homka", 3 to "Dima", 1 to "Nastya")
            removeTest(data, 2)
        }

        @Test
        fun `remove root in tree with right node`() {
            val data = arrayOf(2 to "Homka", 3 to "Dima")
            removeTest(data, 2)
        }

        @Test
        fun `remove root in tree with left node`() {
            val data = arrayOf(2 to "Homka", 1 to "Dima")
            removeTest(data, 2)
        }

        @Test
        fun `remove node that is right subtree with nodes`() {
            val data = arrayOf(
                35 to "Homka", 21 to "Dima", 25 to "Nastya", 622 to "Rodion",
                12 to "spisladqo", 62 to "Vichislav Zorich", 122 to "Sibiri4ok",
                621 to "kotenok-barista", 121 to "vlad zavtra v zal", 362 to "karim",
                36 to "seriy cardinal", 523 to "katya", 251 to "sonechka",
                6422 to "dinozavrik", 4621 to "ruslan", 2093 to "islam",
                235 to "miss mi", 682 to "liya", 2058 to "cold water", 2391 to "azamat",
                2269 to "graphblas"
            )
            removeTest(data, 682)
        }

        @Test
        fun `remove node that is left subtree with nodes`() {
            val data = arrayOf(
                35 to "Homka", 21 to "Dima", 25 to "Nastya", 622 to "Rodion",
                12 to "spisladqo", 62 to "Vichislav Zorich", 122 to "Sibiri4ok",
                621 to "kotenok-barista", 121 to "vlad zavtra v zal", 362 to "karim",
                36 to "seriy cardinal", 523 to "katya", 251 to "sonechka",
                6422 to "dinozavrik", 4621 to "ruslan", 2093 to "islam",
                235 to "miss mi"
            )
            removeTest(data, 21)
        }

        @Test
        fun `stress test`() {
            val rbt = RBTree<Int, String>()
            val generator = Random(5)
            val setWithDefaultValue: (Int) -> (Unit) = { key -> rbt.set(key, "") }

            val randomKeys = mutableListOf<Int>()
            for (i in 1..1000000) {
                val randomValue = generator.nextInt()
                randomKeys.add(randomValue)
                setWithDefaultValue(randomValue)
            }

            val len = randomKeys.size
            for (index in 1..len) {
                val value: Int = randomKeys.removeLast()
                rbt.remove(value)
            }

            checkSize(0, rbt)
            checkKeys(arrayOf(), rbt)
        }
    }
}