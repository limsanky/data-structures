import java.time.Duration;

import org.junit.jupiter.api.*;

import static org.junit.jupiter.api.Assertions.*;
import static org.hamcrest.CoreMatchers.*;
import static org.hamcrest.MatcherAssert.assertThat;

import testrunner.annotation.Score;

import java.util.Arrays;
import java.util.List;

public class HashTests {
    private class HashFn1 implements IHashFunction<Integer> {
        public int hash(Integer i, int length) {
            return (i % length);
        }
    }

    private class ResizeFn1 implements IResizeFunction {
        public boolean checkResize(int tablesize, int numItems) {
            if (numItems > tablesize * 0.8) {
                return true;
            }
            return false;
        }

        public int extendTable(int tablesize) {
            return tablesize * 2;
        }
    }

    @Test
    @Score(1)
    public void testCreation() {
        assertTimeoutPreemptively(Duration.ofSeconds(1), () -> {
            IHash<Integer> h = new Hash<Integer>(10, new HashFn1(), new ResizeFn1());
            assertThat(h.tablesize(), is(10));
        });
    }

    @Test
    @Score(1)
    public void customTestCreation1() {
        assertTimeoutPreemptively(Duration.ofSeconds(1), () -> {
            IHash<Integer> h = new Hash<Integer>(100, new HashFn1(), new ResizeFn1());
            assertThat(h.tablesize(), is(100));
        });
    }

    @Test
    @Score(1)
    public void customTestCreation2() {
        assertTimeoutPreemptively(Duration.ofSeconds(1), () -> {
            IHash<Integer> h = new Hash<Integer>(3, new HashFn1(), new ResizeFn1());
            assertThat(h.tablesize(), is(3));
            assertThat(h.show(), is(Arrays.asList(null, null, null)));
        });
    }

    @Test
    @Score(1)
    public void customTestCreation3() {
        assertTimeoutPreemptively(Duration.ofSeconds(1), () -> {
            IHash<Integer> h = new Hash<Integer>(3, new HashFn1(), new ResizeFn1());
            assertThat(h.tablesize(), is(3));
            assertThat(h.size(), is(0));
        });
    }

    @Test
    @Score(1)
    public void customTestCreation4() {
        assertTimeoutPreemptively(Duration.ofSeconds(1), () -> {
            IHash<Integer> h = new Hash<Integer>(14, new HashFn1(), new ResizeFn1());
            assertThat(h.tablesize(), is(14));
            assertThat(h.exists(2), is(false));
            assertThrows(IllegalStateException.class, () -> h.remove(8));
        });
    }

    @Test
    @Score(1)
    public void customTestCreation5() {
        assertTimeoutPreemptively(Duration.ofSeconds(1), () -> {
            IHash<Integer> h = new Hash<Integer>(3, new HashFn1(), new ResizeFn1());
            assertThat(h.tablesize(), is(3));
            assertThrows(IllegalStateException.class, () -> h.remove(38));
        });
    }

    @Test
    @Score(1)
    public void testHashingOrigin() {
        assertTimeoutPreemptively(Duration.ofSeconds(1), () -> {
            IHash<Integer> h = new Hash<Integer>(10);
            assertThat(h.tablesize(), is(10));
            
            h.put(6);
            h.put(1);
            assertThat(h.size(), is(2));
            assertThat(h.exists(6), is(true));
            assertThat(h.exists(2), is(false));

            assertThrows(IllegalStateException.class, () -> h.remove(8));
        });
    }

    @Test
    @Score(1)
    public void customTestHashingOrigin1() {
        assertTimeoutPreemptively(Duration.ofSeconds(1), () -> {
            IHash<Integer> h = new Hash<Integer>(8);
            assertThat(h.tablesize(), is(8));

            h.put(6);
            h.put(1);

            assertThat(h.size(), is(2));
            assertThat(h.exists(6), is(true));
            assertThat(h.exists(100), is(false));

            h.put(14);
            h.put(9);
            h.put(100);

            assertThat(h.size(), is(5));
            assertThat(h.exists(9), is(true));
            assertThat(h.exists(2), is(false));

            assertThrows(IllegalStateException.class, () -> h.remove(8));
        });
    }

    @Test
    @Score(1)
    public void customTestHashingOrigin2() {
        assertTimeoutPreemptively(Duration.ofSeconds(1), () -> {
            IHash<Integer> h = new Hash<Integer>(3);
            assertThat(h.tablesize(), is(3));

            h.put(6);
            h.put(1);

            assertThat(h.size(), is(2));
            assertThat(h.exists(6), is(true));
            assertThat(h.exists(100), is(false));

            h.put(14);

            assertThat(h.size(), is(3));
            assertThat(h.exists(14), is(true));
            assertThat(h.exists(2), is(false));

            assertThrows(IllegalStateException.class, () -> h.remove(8));
        });
    }

    @Test
    @Score(1)
    public void testHashingSimple() {
        assertTimeoutPreemptively(Duration.ofSeconds(1), () -> {
            IHash<Integer> h = new Hash<Integer>(4, new HashFn1(), new ResizeFn1());
            assertThat(h.tablesize(), is(4));

            h.put(3);
            h.put(2);
            assertThat(h.size(), is(2));
            assertThat(h.exists(3), is(true));

            h.remove(2);
            assertThat(h.exists(2), is(false));

            assertThat(h.show(), is(Arrays.asList(null, null, null, 3)));
        });
    }

    @Test
    @Score(1)
    public void customTestHashingSimple1() {
        assertTimeoutPreemptively(Duration.ofSeconds(1), () -> {
            IHash<Integer> h = new Hash<Integer>(2, new HashFn1(), new ResizeFn1());
            assertThat(h.tablesize(), is(2));

            h.put(3);
            h.put(2);
            assertThat(h.size(), is(2));
            assertThat(h.exists(3), is(true));

            h.remove(2);
            assertThat(h.exists(2), is(false));

            assertThat(h.show(), is(Arrays.asList(null, null, null, 3)));

            h.put(2);
            assertThat(h.exists(2), is(true));
            assertThat(h.show(), is(Arrays.asList(null, null, 2, 3)));
        });
    }

    @Test
    @Score(1)
    public void customTestHashingSimple2() {
        assertTimeoutPreemptively(Duration.ofSeconds(1), () -> {
            IHash<Integer> h = new Hash<Integer>(5, new HashFn1(), new ResizeFn1());
            assertThat(h.tablesize(), is(5));

            h.put(332);
            h.put(20);

            assertThat(h.size(), is(2));
            assertThat(h.exists(3), is(false));
            assertThat(h.exists(332), is(true));

            h.put(230);
            assertThat(h.size(), is(3));

            h.remove(20);
            assertThat(h.exists(2), is(false));
            assertThat(h.exists(20), is(false));

            assertThat(h.show(), is(Arrays.asList(null, 230, 332, null, null)));
        });
    }

    @Test
    @Score(1)
    public void testHashingCollision() {
        assertTimeoutPreemptively(Duration.ofSeconds(1), () -> {
            IHash<Integer> h = new Hash<Integer>(5, new HashFn1(), new ResizeFn1());
            assertThat(h.tablesize(), is(5));

            h.put(0);
            h.put(5);
            h.put(3);

            assertThat(h.size(), is(3));
            assertThat(h.show(), is(Arrays.asList(0, 5, null, 3, null)));

        });
    }

    @Test
    @Score(1)
    public void customTestHashingCollision1() {
        assertTimeoutPreemptively(Duration.ofSeconds(1), () -> {
            IHash<Integer> h = new Hash<Integer>(5, new HashFn1(), new ResizeFn1());
            assertThat(h.tablesize(), is(5));

            h.put(10);
            h.put(5);
            assertThat(h.show(), is(Arrays.asList(10, 5, null, null, null)));
            h.put(23);
            assertThat(h.show(), is(Arrays.asList(10, 5, null, 23, null)));
            h.put(20);
            assertThat(h.show(), is(Arrays.asList(10, 5, 20, 23, null)));
            h.put(30);
            assertThat(h.show(), is(Arrays.asList(10, 20, 30, 23, null, 5, null, null, null, null)));

            assertThat(h.size(), is(5));
            assertThat(h.tablesize(), is(10));

            h.remove(10);
            h.remove(5);
            assertThat(h.size(), is(3));
            assertThat(h.show(), is(Arrays.asList(null, 20, 30, 23, null, null, null, null, null, null)));
            h.put(5);
            h.put(10);
            assertThat(h.show(), is(Arrays.asList(10, 20, 30, 23, null, 5, null, null, null, null)));
        });
    }

    @Test
    @Score(1)
    public void customTestHashingCollision2() {
        assertTimeoutPreemptively(Duration.ofSeconds(1), () -> {
            IHash<Integer> h = new Hash<Integer>(100, new HashFn1(), new ResizeFn1());
            assertThat(h.tablesize(), is(100));

            h.put(10);
            h.put(5);
            h.put(23);

            assertThat(h.exists(20), is(false));
            assertThat(h.exists(5), is(true));

            h.put(20);
            h.put(30);

            assertThat(h.size(), is(5));
            assertThat(h.tablesize(), is(100));

            assertThat(h.exists(23), is(true));
            assertThat(h.exists(31), is(false));
            assertThat(h.exists(30), is(true));

            h.remove(10);
            assertThat(h.exists(10), is(false));

            h.remove(5);
            assertThat(h.exists(5), is(false));
            assertThat(h.size(), is(3));

            h.put(5);
            assertThat(h.exists(5), is(true));

            h.put(10);
        });
    }

    @Test
    @Score(1)
    public void testHashingRehashing() {
        assertTimeoutPreemptively(Duration.ofSeconds(1), () -> {
            IHash<Integer> h = new Hash<Integer>(5, new HashFn1(), new ResizeFn1());
            assertThat(h.tablesize(), is(5));

            h.put(0);
            h.put(5);
            h.put(3);
            h.put(13);
            h.put(2);

            assertThat(h.tablesize(), is(10));
            assertThat(h.size(), is(5));

            h.put(7);
            h.put(9);
            assertThat(h.size(), is(7));
            assertThat(h.show(), is(Arrays.asList(new Integer[] { 0, null, 2, 3, 13, 5, null, 7, null, 9 })));

        });
    }
}
