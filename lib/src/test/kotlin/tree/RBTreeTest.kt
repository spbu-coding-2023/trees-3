package tree

import org.junit.jupiter.api.Test

import org.junit.jupiter.api.Assertions.*
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Nested
import tree.node.RBTreeNode

class RBTreeTest {
    private lateinit var rbt: RBTree<Int, String>

    @BeforeEach
    fun setup() {
        rbt = RBTree()
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