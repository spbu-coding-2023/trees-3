package tree

import org.junit.jupiter.api.Assertions.*
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Nested
import org.junit.jupiter.api.Test
import kotlin.random.Random

class AVLTreeTest {
    private val tree = AVLTree<Int, Int>()

    @Nested
    inner class `set test` {

        @Test
        fun `base test`(){
            tree.set(1, 1)
            assertEquals(1, tree.size)
            assertEquals(listOf(1), tree.getKeys())
        }

        @Test
        fun `many node`() {
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

            tree.set(entities)
            assertEquals(10, tree.size)
            assertEquals(listOf(
                12, 21, 25, 35, 62, 121, 122, 362, 523, 621
            ), tree.getKeys())
        }

        @Test
        fun `test right`() {
            tree.set(arrayOf(
                Pair(2, 1),
                Pair(4, 1),
                Pair(6, 1),
                Pair(8, 1),
                Pair(3, 1),
                Pair(9, 1)))

            assertEquals(6, tree.size)
            assertEquals(
                listOf(
                    2, 3, 4, 6, 8, 9
                ), tree.getKeys()
            )
        }

        @Test
        fun `test left`() {
            tree.set(5, 1)
            tree.set(4, 1)
            tree.set(2, 1)
            tree.set(1, 1)
            assertEquals(4, tree.size)
            assertEquals(listOf(
                1, 2, 4, 5
            ), tree.getKeys())
        }

        @Test
        fun `stress test`() {
            val tree = AVLTree<Int, Int?>()
            val generator = Random(7)
            val setWithNull: (Int) -> (Unit) = { key -> tree.set(key, null) }

            val randomKeys = mutableListOf<Int>()
            for (i in 1..1000000) {
             val randomValue = generator.nextInt()
             randomKeys.add(randomValue)
             setWithNull(randomValue)
            }

            val randomKeysDistinct = randomKeys.distinct()
            assertEquals(randomKeysDistinct.sorted(), tree.getKeys())
            assertEquals(randomKeysDistinct.size.toLong(), tree.size)
        }
    }

    @Nested
    inner class `remove test`{
        @Test
        fun `base test`() {
            tree.set(1, 1)
            tree.remove(1)
            assertEquals(0, tree.size)
        }

        @Test
        fun `many test`(){
            val entities = arrayOf(
                Pair(35, 1),
                Pair(21, 1),
                Pair(25, 1),
                Pair(62, 1),
                Pair(12, 1),
                Pair(122, 1),
                Pair(621, 1),
                Pair(121, 1),
                Pair(362, 1),
                Pair(523, 1),
            )

            tree.set(entities)
            tree.remove(arrayOf(
                35, 12, 523
            ))
            assertEquals(7, tree.size)
            assertEquals(listOf(
                21, 25, 62, 121, 122, 362, 621
            ), tree.getKeys())
        }
    }
}