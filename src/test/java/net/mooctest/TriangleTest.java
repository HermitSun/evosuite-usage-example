package net.mooctest;

import static org.evosuite.runtime.EvoAssertions.verifyException;
import static org.junit.Assert.*;

import org.junit.Test;

public class TriangleTest {
	// 不规则三角形
	Triangle T1 = new Triangle(2, 3, 4);
	Triangle T2 = new Triangle(2, 4, 3);
	Triangle T3 = new Triangle(4, 2, 3);
	// 两边之和等于第三边的三角形
	Triangle T4 = new Triangle(2, 3, 5);
	Triangle T5 = new Triangle(2, 5, 3);
	Triangle T6 = new Triangle(5, 2, 3);
	// 两边之和小于第三边的三角形
	Triangle T7 = new Triangle(2, 3, 6);
	Triangle T8 = new Triangle(2, 6, 3);
	Triangle T9 = new Triangle(6, 2, 3);
	// 某条边为0的三角形
	Triangle T10 = new Triangle(2, 3, 0);
	Triangle T11 = new Triangle(2, 0, 3);
	Triangle T12 = new Triangle(0, 2, 3);
	Triangle T31 = new Triangle(0, 0, 0);
	// 某条边为负数的三角形
	Triangle T13 = new Triangle(2, 3, -1);
	Triangle T14 = new Triangle(2, -1, 3);
	Triangle T15 = new Triangle(-1, 2, 3);
	Triangle T32 = new Triangle(-Long.MAX_VALUE / 2, -Long.MAX_VALUE / 2, -Long.MAX_VALUE / 2);
	// 某条边接近long上限的三角形
	Triangle T16 = new Triangle(2, Long.MAX_VALUE - 2, Long.MAX_VALUE - 1);
	Triangle T17 = new Triangle(Long.MAX_VALUE - 2, Long.MAX_VALUE - 1, 2);
	Triangle T18 = new Triangle(Long.MAX_VALUE - 1, 2, Long.MAX_VALUE - 2);
	// 某条边是long上限的三角形
	Triangle T19 = new Triangle(2, Long.MAX_VALUE - 1, Long.MAX_VALUE);
	Triangle T20 = new Triangle(Long.MAX_VALUE - 1, Long.MAX_VALUE, 2);
	Triangle T21 = new Triangle(Long.MAX_VALUE, 2, Long.MAX_VALUE - 1);
	// 某条边超过long上限的三角形
	Triangle T22 = new Triangle(2, Long.MAX_VALUE, Long.MAX_VALUE + 1);
	Triangle T23 = new Triangle(Long.MAX_VALUE, Long.MAX_VALUE + 1, 2);
	Triangle T24 = new Triangle(Long.MAX_VALUE + 1, 2, Long.MAX_VALUE);
	// 等腰三角形
	Triangle T25 = new Triangle(2, Long.MAX_VALUE, Long.MAX_VALUE);
	Triangle T26 = new Triangle(Long.MAX_VALUE, Long.MAX_VALUE, 2);
	Triangle T27 = new Triangle(Long.MAX_VALUE, 2, Long.MAX_VALUE);
	Triangle T33 = new Triangle(2, 2, 3);
	// 等边三角形
	Triangle T28 = new Triangle(1, 1, 1);
	Triangle T29 = new Triangle(Long.MAX_VALUE / 2 + 1, Long.MAX_VALUE / 2 + 1, Long.MAX_VALUE / 2 + 1);
	Triangle T30 = new Triangle(Long.MAX_VALUE, Long.MAX_VALUE, Long.MAX_VALUE);

	// 不规则三角形
	@Test(timeout = 4000)
	public void testIsTriangle() throws Throwable {
		assertEquals(true, T1.isTriangle(T1));
		assertNotNull(T1.getBorders());
		assertEquals(T1.lborderA, T1.getBorders()[0]);
		assertEquals(T1.lborderB, T1.getBorders()[1]);
		assertEquals(T1.lborderC, T1.getBorders()[2]);
		assertEquals("Scalene", T1.getType(T1));
	}

	@Test(timeout = 4000)
	public void testIsTriangle2() {
		assertEquals(true, T2.isTriangle(T2));
		assertEquals("Scalene", T1.getType(T1));
	}

	@Test(timeout = 4000)
	public void testIsTriangle3() {
		assertEquals(true, T3.isTriangle(T3));
		assertEquals("Scalene", T1.getType(T1));
	}

	// 两边之和等于第三边的三角形
	@Test(timeout = 4000)
	public void testIsTriangle4() {
		assertEquals(false, T4.isTriangle(T4));
		assertEquals("Illegal", T4.getType(T4));
	}

	@Test(timeout = 4000)
	public void testIsTriangle5() {
		assertEquals(false, T5.isTriangle(T5));
		assertEquals("Illegal", T5.getType(T5));
	}

	@Test(timeout = 4000)
	public void testIsTriangle6() {
		assertEquals(false, T6.isTriangle(T6));
		assertEquals("Illegal", T6.getType(T6));
	}

	// 两边之和小于第三边的三角形
	@Test(timeout = 4000)
	public void testIsTriangle7() {
		assertEquals(false, T7.isTriangle(T7));
		assertEquals("Illegal", T7.getType(T7));
	}

	@Test(timeout = 4000)
	public void testIsTriangle8() {
		assertEquals(false, T8.isTriangle(T8));
		assertEquals("Illegal", T8.getType(T8));
	}

	@Test(timeout = 4000)
	public void testIsTriangle9() {
		assertEquals(false, T9.isTriangle(T9));
		assertEquals("Illegal", T9.getType(T9));
	}

	// 某条边为0的三角形
	@Test(timeout = 4000)
	public void testIsTriangle10() {
		assertEquals(false, T10.isTriangle(T10));
		assertEquals("Illegal", T10.getType(T10));
	}

	@Test(timeout = 4000)
	public void testIsTriangle11() {
		assertEquals(false, T11.isTriangle(T11));
		assertEquals("Illegal", T11.getType(T11));
	}

	@Test(timeout = 4000)
	public void testIsTriangle12() {
		assertEquals(false, T12.isTriangle(T12));
		assertEquals("Illegal", T12.getType(T12));
	}

	@Test(timeout = 4000)
	public void testIsTriangle31() {
		assertEquals(false, T31.isTriangle(T31));
		assertEquals("Illegal", T31.getType(T31));
	}

	// 某条边为负数的三角形
	@Test(timeout = 4000)
	public void testIsTriangle13() {
		assertEquals(false, T13.isTriangle(T13));
		assertEquals("Illegal", T13.getType(T13));
	}

	@Test(timeout = 4000)
	public void testIsTriangle14() {
		assertEquals(false, T14.isTriangle(T14));
		assertEquals("Illegal", T14.getType(T14));
	}

	@Test(timeout = 4000)
	public void testIsTriangle15() {
		assertEquals(false, T15.isTriangle(T15));
		assertEquals("Illegal", T15.getType(T15));
	}

	@Test(timeout = 4000)
	public void testIsTriangle32() {
		assertEquals(false, T32.isTriangle(T32));
		assertEquals("Illegal", T32.getType(T32));
	}

	// 某条边接近long上限的三角形
	@Test(timeout = 4000)
	public void testIsTriangle16() {
		assertEquals(true, T16.isTriangle(T16));
		assertEquals("Scalene", T16.getType(T16));
	}

	@Test(timeout = 4000)
	public void testIsTriangle17() {
		assertEquals(true, T17.isTriangle(T17));
		assertEquals("Scalene", T17.getType(T17));
	}

	@Test(timeout = 4000)
	public void testIsTriangle18() {
		assertEquals(true, T18.isTriangle(T18));
		assertEquals("Scalene", T18.getType(T18));
	}

	// 某条边是long上限的三角形
	@Test(timeout = 4000)
	public void testIsTriangle19() {
		assertEquals(true, T19.isTriangle(T19));
		assertEquals("Scalene", T19.getType(T19));
	}

	@Test(timeout = 4000)
	public void testIsTriangle20() {
		assertEquals(true, T20.isTriangle(T20));
		assertEquals("Scalene", T20.getType(T20));
	}

	@Test(timeout = 4000)
	public void testIsTriangle21() {
		assertEquals(true, T21.isTriangle(T21));
		assertEquals("Scalene", T21.getType(T21));
	}

	// 某条边超过long上限的三角形
	@Test(timeout = 4000)
	public void testIsTriangle22() throws Throwable {
		assertEquals(false, T22.isTriangle(T22));
		assertNotNull(T22.getBorders());
		assertEquals(T22.lborderA, T22.getBorders()[0]);
		assertEquals(T22.lborderB, T22.getBorders()[1]);
		assertEquals(T22.lborderC, T22.getBorders()[2]);
		assertEquals("Illegal", T22.getType(T22));
	}

	@Test(timeout = 4000)
	public void testIsTriangle23() {
		assertEquals(false, T23.isTriangle(T23));
		assertEquals("Illegal", T23.getType(T23));
	}

	@Test(timeout = 4000)
	public void testIsTriangle24() {
		assertEquals(false, T24.isTriangle(T24));
		assertEquals("Illegal", T24.getType(T24));
	}

	// 等腰三角形
	@Test(timeout = 4000)
	public void testIsTriangle25() {
		assertEquals(true, T25.isTriangle(T25));
		assertEquals("Isosceles", T25.getType(T25));
	}

	@Test(timeout = 4000)
	public void testIsTriangle26() {
		assertEquals(true, T26.isTriangle(T26));
		assertEquals("Isosceles", T26.getType(T26));
	}

	@Test(timeout = 4000)
	public void testIsTriangle27() {
		assertEquals(true, T27.isTriangle(T27));
		assertEquals("Isosceles", T27.getType(T27));
	}

	@Test(timeout = 4000)
	public void testIsTriangle33() {
		assertEquals(true, T33.isTriangle(T33));
		assertEquals("Isosceles", T33.getType(T33));
	}

	// 等边三角形
	@Test(timeout = 4000)
	public void testIsTriangle28() {
		assertEquals(true, T28.isTriangle(T28));
		assertEquals("Regular", T28.getType(T28));
	}

	@Test(timeout = 4000)
	public void testIsTriangle29() {
		assertEquals(true, T29.isTriangle(T29));
		assertEquals("Regular", T29.getType(T29));
	}

	@Test(timeout = 4000)
	public void testIsTriangle30() {
		assertEquals(true, T30.isTriangle(T30));
		assertEquals("Regular", T30.getType(T30));
	}

	@Test(timeout = 4000)
	public void teste00() throws Throwable {
		Triangle triangle0 = new Triangle(9223372036854775807L, 9223372036854775807L, 9223372036854775807L);
		triangle0.lborderB = 9223372036854775807L;
		triangle0.lborderB = 1140L;
		long[] longArray0 = triangle0.getBorders();
		assertArrayEquals(new long[] { 9223372036854775807L, 1140L, 9223372036854775807L }, longArray0);
	}

	@Test(timeout = 4000)
	public void teste01() throws Throwable {
		Triangle triangle0 = new Triangle(2531L, 2531L, 4308L);
		Triangle triangle1 = new Triangle(2531L, 2306L, 1957L);
		String string0 = triangle0.getType(triangle1);
		assertEquals("Scalene", string0);
	}

	@Test(timeout = 4000)
	public void teste02() throws Throwable {
		Triangle triangle0 = new Triangle(542L, 1934L, 2343L);
		Triangle triangle1 = new Triangle(2343L, 2343L, 3074L);
		String string0 = triangle0.getType(triangle1);
		assertEquals("Isosceles", string0);
	}

	@Test(timeout = 4000)
	public void teste03() throws Throwable {
		Triangle triangle0 = new Triangle(9223372036854775807L, 9223372036854775807L, 9223372036854775807L);
		Triangle triangle1 = new Triangle(9223372036854775807L, 9223372036854775807L, 9223372036854775807L);
		triangle1.lborderA = 9223372036854775807L;
		triangle1.lborderA = 3700L;
		String string0 = triangle1.getType(triangle0);
		assertEquals("Regular", string0);
	}

	@Test(timeout = 4000)
	public void teste04() throws Throwable {
		Triangle triangle0 = new Triangle(1L, 1L, 1L);
		Triangle triangle1 = new Triangle(1L, 2297L, 0L);
		String string0 = triangle1.getType(triangle0);
		assertEquals("Regular", string0);
	}

	@Test(timeout = 4000)
	public void teste05() throws Throwable {
		Triangle triangle0 = new Triangle(542L, 1934L, 2343L);
		Triangle triangle1 = new Triangle(2343L, 2343L, 3074L);
		triangle1.lborderB = 731L;
		String string0 = triangle0.getType(triangle1);
		assertEquals("Illegal", string0);
	}

	@Test(timeout = 4000)
	public void teste06() throws Throwable {
		Triangle triangle0 = new Triangle((-1L), 0L, 0L);
		Triangle triangle1 = new Triangle(2174L, 1L, 0L);
		String string0 = triangle0.getType(triangle1);
		assertEquals("Illegal", string0);
	}

	@Test(timeout = 4000)
	public void teste07() throws Throwable {
		Triangle triangle0 = new Triangle(2095L, 2095L, 2095L);
		Triangle triangle1 = new Triangle(2095L, 0L, 0L);
		String string0 = triangle0.getType(triangle1);
		assertEquals("Illegal", string0);
	}

	@Test(timeout = 4000)
	public void teste08() throws Throwable {
		Triangle triangle0 = new Triangle(0L, 0L, (-2161L));
		boolean boolean0 = triangle0.isTriangle(triangle0);
		assertFalse(boolean0);
	}

	@Test(timeout = 4000)
	public void teste09() throws Throwable {
		Triangle triangle0 = new Triangle(3558L, 3558L, 3558L);
		long long0 = triangle0.diffOfBorders((-1L), 9223372036854775807L);
		assertEquals((-9223372036854775808L), long0);
	}

	@Test(timeout = 4000, expected = NullPointerException.class)
	public void teste10() throws Throwable {
		Triangle triangle0 = new Triangle(0L, 676L, 0L);
		triangle0.isTriangle((Triangle) null);
	}

	@Test(timeout = 4000, expected = NullPointerException.class)
	public void teste11() throws Throwable {
		Triangle triangle0 = new Triangle((-791L), (-791L), 2830L);
		triangle0.getType((Triangle) null);
	}

	@Test(timeout = 4000)
	public void teste12() throws Throwable {
		Triangle triangle0 = new Triangle(9223372036854775807L, 1867L, 9223372036854775807L);
		long long0 = triangle0.diffOfBorders(0L, (-303L));
		assertEquals(303L, long0);
	}

	@Test(timeout = 4000)
	public void teste13() throws Throwable {
		Triangle triangle0 = new Triangle(9223372036854775807L, 9223372036854775807L, 9223372036854775807L);
		long long0 = triangle0.diffOfBorders(3700L, 3700L);
		assertEquals(0L, long0);
	}

	@Test(timeout = 4000)
	public void teste14() throws Throwable {
		Triangle triangle0 = new Triangle(0L, 0L, 1774L);
		Triangle triangle1 = new Triangle(1780L, 1L, 377L);
		boolean boolean0 = triangle0.isTriangle(triangle1);
		assertFalse(boolean0);
	}

	@Test(timeout = 4000)
	public void teste15() throws Throwable {
		Triangle triangle0 = new Triangle(672L, 672L, 672L);
		Triangle triangle1 = new Triangle(672L, (-3137L), (-3137L));
		boolean boolean0 = triangle0.isTriangle(triangle1);
		assertFalse(boolean0);
	}

	@Test(timeout = 4000)
	public void teste16() throws Throwable {
		Triangle triangle0 = new Triangle(927L, 927L, 927L);
		Triangle triangle1 = new Triangle((-2343L), 927L, 927L);
		boolean boolean0 = triangle0.isTriangle(triangle1);
		assertFalse(boolean0);
	}

	@Test(timeout = 4000)
	public void teste17() throws Throwable {
		Triangle triangle0 = new Triangle(542L, 1915L, 2343L);
		String string0 = triangle0.getType(triangle0);
		assertEquals("Scalene", string0);
	}

	@Test(timeout = 4000)
	public void teste18() throws Throwable {
		Triangle triangle0 = new Triangle(26L, 26L, 26L);
		Triangle triangle1 = new Triangle(26L, 26L, 1984L);
		triangle1.lborderB = 1984L;
		String string0 = triangle0.getType(triangle1);
		assertEquals("Isosceles", string0);
	}

	@Test(timeout = 4000)
	public void teste19() throws Throwable {
		Triangle triangle0 = new Triangle(542L, 1915L, 2343L);
		Triangle triangle1 = new Triangle(2343L, 2343L, 542L);
		String string0 = triangle0.getType(triangle1);
		assertEquals("Isosceles", string0);
	}

	@Test(timeout = 4000)
	public void teste20() throws Throwable {
		Triangle triangle0 = new Triangle(1131L, 1131L, 1131L);
		Triangle triangle1 = new Triangle(1L, 1131L, 1131L);
		triangle1.lborderB = 1131L;
		triangle1.lborderB = 1131L;
		triangle1.lborderA = 1131L;
		triangle1.lborderB = 1L;
		String string0 = triangle0.getType(triangle1);
		assertEquals("Isosceles", string0);
	}

	@Test(timeout = 4000)
	public void teste21() throws Throwable {
		Triangle triangle0 = new Triangle(9223372036854775807L, 9223372036854775807L, 9223372036854775807L);
		Triangle triangle1 = new Triangle(9223372036854775807L, 9223372036854775807L, 9223372036854775807L);
		triangle1.lborderB = 9223372036854775807L;
		triangle1.lborderA = 9223372036854775807L;
		triangle1.lborderB = 1140L;
		triangle1.lborderA = 1140L;
		boolean boolean0 = triangle0.isTriangle(triangle1);
		assertFalse(boolean0);
	}

	@Test(timeout = 4000)
	public void teste22() throws Throwable {
		Triangle triangle0 = new Triangle(9223372036854775807L, 9223372036854775807L, 9223372036854775807L);
		boolean boolean0 = triangle0.isTriangle(triangle0);
		assertTrue(boolean0);
	}

	@Test(timeout = 4000)
	public void teste23() throws Throwable {
		Triangle triangle0 = new Triangle(9223372036854775807L, (-733L), (-733L));
		Triangle triangle1 = new Triangle(9223372036854775807L, 1L, 9223372036854775807L);
		triangle1.lborderC = (-733L);
		boolean boolean0 = triangle0.isTriangle(triangle1);
		assertFalse(boolean0);
	}

	@Test(timeout = 4000)
	public void teste24() throws Throwable {
		Triangle triangle0 = new Triangle(9223372036854775807L, (-733L), (-733L));
		Triangle triangle1 = new Triangle(9223372036854775807L, 1L, 9223372036854775807L);
		String string0 = triangle0.getType(triangle1);
		assertEquals("Isosceles", string0);
	}

	@Test(timeout = 4000)
	public void teste25() throws Throwable {
		Triangle triangle0 = new Triangle(9223372036854775807L, (-733L), (-733L));
		long[] longArray0 = triangle0.getBorders();
		assertArrayEquals(new long[] { 9223372036854775807L, (-733L), (-733L) }, longArray0);
	}

}
