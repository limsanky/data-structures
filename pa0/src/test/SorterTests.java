import java.time.Duration;

import org.junit.jupiter.api.*;

import static org.junit.jupiter.api.Assertions.*;
import static org.hamcrest.CoreMatchers.*;
import static org.hamcrest.MatcherAssert.assertThat;

import testrunner.annotation.Score;

public class SorterTests {
	@Test
	@Score(1)
	public void testBasic1() {
		assertTimeoutPreemptively(Duration.ofSeconds(1), () -> {
			ISorter s = new Sorter();
			int[] a = new int[] {};
			assertThat(s.ascending(a.clone()), is(new int[] {}));
			assertThat(s.descending(a.clone()), is(new int[] {}));
		});
	}

	@Test
	@Score(1)
	public void testBasic2() {
		assertTimeoutPreemptively(Duration.ofSeconds(1), () -> {
			ISorter s = new Sorter();
			int[] a = new int[] { 2, 1, 4, 3 };
			assertThat(s.ascending(a.clone()), is(new int[] { 1, 2, 3, 4 }));
			assertThat(s.descending(a.clone()), is(new int[] { 4, 3, 2, 1 }));
		});
	}

	@Test
	@Score(1)
	public void testBasic3() {
		assertTimeoutPreemptively(Duration.ofSeconds(1), () -> {
			ISorter s = new Sorter();
			int[] a = new int[] { 7, 5, 4, 9, 13 };
			assertThat(s.ascending(a.clone()), is(new int[] { 4, 5, 7, 9, 13 }));
			assertThat(s.descending(a.clone()), is(new int[] { 13, 9, 7, 5, 4 }));
		});
	}

	@Test
	@Score(1)
	public void testBasic4() {
		assertTimeoutPreemptively(Duration.ofSeconds(1), () -> {
			ISorter s = new Sorter();
			int[] a = new int[] { -1, -2, -3, 123, 5, -13, 10000000, 0, 1};
			assertThat(s.ascending(a.clone()), is(new int[] { -13, -3, -2, -1, 0, 1, 5, 123, 10000000 }));
			assertThat(s.descending(a.clone()), is(new int[] { 10000000, 123, 5, 1, 0, -1, -2, -3, -13 }));
		});
	}

	@Test
	@Score(1)
	public void testBasic5() {
		assertTimeoutPreemptively(Duration.ofSeconds(1), () -> {
			ISorter s = new Sorter();
			int[] a = new int[] { 64, 34, 25, 12, 22, 11, 90 };
			assertThat(s.ascending(a.clone()), is(new int[] { 11, 12, 22, 25, 34, 64, 90 }));
			assertThat(s.descending(a.clone()), is(new int[] { 90, 64, 34, 25, 22, 12, 11 }));
		});
	}

	@Test
	@Score(1)
	public void testBasic6() {
		assertTimeoutPreemptively(Duration.ofSeconds(1), () -> {
			ISorter s = new Sorter();
			int[] a = new int[] { 1 };
			assertThat(s.ascending(a.clone()), is(new int[] { 1 }));
			assertThat(s.descending(a.clone()), is(new int[] { 1 }));
		});
	}

	@Test
	@Score(1)
	public void testBasic7() {
		assertTimeoutPreemptively(Duration.ofSeconds(1), () -> {
			ISorter s = new Sorter();
			int[] a = new int[] { -1, 100, 1 };
			assertThat(s.ascending(a.clone()), is(new int[] { -1, 1, 100 }));
			assertThat(s.descending(a.clone()), is(new int[] { 100, 1, -1 }));
		});
	}

	@Test
	@Score(1)
	public void testBasic8() {
		assertTimeoutPreemptively(Duration.ofSeconds(1), () -> {
			ISorter s = new Sorter();
			int[] a = new int[] { 0, -1 };
			assertThat(s.ascending(a.clone()), is(new int[] { -1, 0 }));
			assertThat(s.descending(a.clone()), is(new int[] { 0, -1 }));
		});
	}
}