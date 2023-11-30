package nl.bramjanssens

import org.junit.jupiter.api.Assertions.assertTrue
import org.junit.jupiter.api.Test
import kotlin.test.assertEquals

class KnotTest {

    @Test
    fun isRightFrom() {
        val head = Knot(2, 1)
        val tail = Knot(1, 1)
        assertTrue(head.isRightFrom(tail))
    }

    @Test
    fun orientationTowards() {
        val k = Knot(1, 1)

        val right = Knot(2, 1)
        val left = Knot(0, 1)
        val up = Knot(1, 2)
        val down = Knot(1, 0)

        assertTrue(right.orientationTowards(k) == "R")
        assertTrue(left.orientationTowards(k) == "L")
        assertTrue(up.orientationTowards(k) == "U")
        assertTrue(down.orientationTowards(k) == "D")

        val same = Knot(1, 1)
        assertTrue(same.orientationTowards(k) == "SAME")

        val ne = Knot(2, 3)
        assertTrue(ne.orientationTowards(k) == "NE")

        val nw = Knot(0, 3)
        assertTrue(nw.orientationTowards(k) == "NW")

        val sw = Knot(0, -1)
        assertTrue(sw.orientationTowards(k) == "SW")

        val se = Knot(2, -1)
        assertTrue(se.orientationTowards(k) == "SE")
    }

    @Test
    fun moveTowards() {
        val right = Knot(5, 3)
        val left = Knot(1, 3)
        val up = Knot(3, 5)
        val down = Knot(3, 1)

        val moveTowardsR = Knot(3, 3).moveTowards(right)
        val moveTowardsL = Knot(3, 3).moveTowards(left)
        val moveTowardsU = Knot(3, 3).moveTowards(up)
        val moveTowardsD = Knot(3, 3).moveTowards(down)

        assertEquals(Point(4, 3), moveTowardsR)
        assertEquals(Point(2, 3), moveTowardsL)
        assertEquals(Point(3, 4), moveTowardsU)
        assertEquals(Point(3, 2), moveTowardsD)

        val nne = Knot(4, 5)
        val ene = Knot(5,4)
        val nnw = Knot(2, 5)
        val wnw = Knot(1, 4)
        val sse = Knot(4, 1)
        val ese = Knot(5, 2)
        val ssw = Knot(2, 1)
        val wsw = Knot(1, 2)

        val moveTowardsNne = Knot(3, 3).moveTowards(nne)
        val moveTowardsEne = Knot(3, 3).moveTowards(ene)
        val moveTowardsNnw = Knot(3, 3).moveTowards(nnw)
        val moveTowardsWnw = Knot(3, 3).moveTowards(wnw)
        val moveTowardsSse = Knot(3, 3).moveTowards(sse)
        val moveTowardsEse = Knot(3, 3).moveTowards(ese)
        val moveTowardsSsw = Knot(3, 3).moveTowards(ssw)
        val moveTowardsWsw = Knot(3, 3).moveTowards(wsw)

        assertEquals(Point(4, 4), moveTowardsNne)
        assertEquals(Point(4, 4), moveTowardsEne)
        assertEquals(Point(2, 4), moveTowardsNnw)
        assertEquals(Point(2, 4), moveTowardsWnw)
        assertEquals(Point(4, 2), moveTowardsSse)
        assertEquals(Point(4, 2), moveTowardsEse)
        assertEquals(Point(2, 2), moveTowardsSsw)
        assertEquals(Point(2, 2), moveTowardsWsw)

    }
}
