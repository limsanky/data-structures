import java.time.Duration;

import org.junit.jupiter.api.*;

import static org.junit.jupiter.api.Assertions.*;
import static org.hamcrest.CoreMatchers.*;
import static org.hamcrest.MatcherAssert.assertThat;

import testrunner.annotation.Score;

class HeapTests {

    @Test
    @Score(1)
    void testConstruction() {
        assertTimeoutPreemptively(
            Duration.ofSeconds(1), () -> {
                IHeap<String> heap = new Heap<String>();
                heap.bottomUp(new int[]{ 1, 2 }, new String[]{ "hello", "world" });

                assertThat(heap.size(), is(2));
                assertThat(heap.min(), is("hello"));
            });
    }

    @Test
    @Score(1)
    void customTest1() {
        IHeap<Integer> heap = new Heap<>();

        heap.bottomUp(new int[]{ 52435,34,0,13,1, 5,3, 12, 4, 2 }, new Integer[]{ 134, 2, -2, 4, 32, 5,43,1,0, -1 });

        assertThat(heap.size(), is(10));
        assertThat(heap.min(), is(-2));
    }

    @Test
    @Score(1)
    void customTest2() {
        IHeap<Integer> heap = new Heap<>();

        heap.bottomUp(new int[]{ 1 }, new Integer[]{ 62 });

        assertThat(heap.size(), is(1));
        assertThat(heap.min(), is(62));
    }

    @Test
    @Score(1)
    void customTest3() {
        IHeap<String> heap = new Heap<>();

        heap.bottomUp(new int[]{ 234, 342, 0 }, new String[]{ "hi", "lol", "a" });

        assertThat(heap.size(), is(3));
        assertThat(heap.min(), is("a"));
    }

    @Test
    @Score(1)
    void testInsert() {
        assertTimeoutPreemptively(
            Duration.ofSeconds(1), () -> {
                IHeap<String> heap = new Heap<>();

                heap.insert(1, "hello");
                heap.insert(2, "world");

                assertThat(heap.size(), is(2));
            });
    }

    @Test
    @Score(1)
    void customInsert1() {
        assertTimeoutPreemptively(
                Duration.ofSeconds(1), () -> {
                    IHeap<String> heap = new Heap<>();

                    heap.insert(1, "hello");
                    heap.insert(2, "world");
                    heap.insert(3, "!");

                    assertThat(heap.size(), is(3));
                });
    }

    @Test
    @Score(1)
    void customInsert2() {
        assertTimeoutPreemptively(
                Duration.ofSeconds(1), () -> {
                    IHeap<String> heap = new Heap<>();

                    heap.insert(1, "hello");
                    heap.insert(2, "world");
                    heap.insert(0, "!");
                    heap.insert(6, "How");
                    heap.insert(7, "Are");
                    heap.insert(5, "You");
                    heap.insert(4, "Doing");
                    heap.insert(-1, "?");
                    heap.insert(43, "\n");

                    assertThat(heap.size(), is(9));
                    assertThat(heap.min(), is("?"));
                });
    }

    @Test
    @Score(1)
    void customInsert3() {
        assertTimeoutPreemptively(
                Duration.ofSeconds(1), () -> {
                    IHeap<String> heap = new Heap<>();

                    heap.insert(1, "hello");
                    heap.insert(2, "world");
                    heap.insert(0, "!");

                    assertThat(heap.size(), is(3));
                    assertThat(heap.min(), is("!"));
                });
    }

    @Test
    @Score(1)
    void testGetRoot() {
        assertTimeoutPreemptively(
            Duration.ofSeconds(1), () -> {
                IHeap<String> heap = new Heap<>();

                heap.insert(1, "hello");
                heap.insert(2, "world");

                assertThat(heap.getRoot().getValue(), is("hello"));
            });
    }

    @Test
    @Score(1)
    void customRootTest1() {
        assertTimeoutPreemptively(
                Duration.ofSeconds(1), () -> {
                    IHeap<String> heap = new Heap<>();

                    heap.insert(2, "world");
                    assertThat(heap.getRoot().getValue(), is("world"));

                    heap.insert(1, "hello");
                    assertThat(heap.getRoot().getValue(), is("hello"));

                    heap.insert(14, "!");
                    assertThat(heap.getRoot().getValue(), is("hello"));

                    heap.insert(0, "???");
                    assertThat(heap.getRoot().getValue(), is("???"));
                });
    }

    @Test
    @Score(1)
    void customRootTest2() {
        assertTimeoutPreemptively(
                Duration.ofSeconds(1), () -> {
                    IHeap<String> heap = new Heap<>();

                    heap.insert(2, "world");
                    assertThat(heap.getRoot().getValue(), is("world"));

                    heap.insert(1, "hello");
                    assertThat(heap.getRoot().getValue(), is("hello"));

                    heap.insert(14, "!");
                    assertThat(heap.getRoot().getValue(), is("hello"));

                    heap.insert(0, "???");
                    assertThat(heap.getRoot().getValue(), is("???"));

                    heap.removeMin();
                    assertThat(heap.getRoot().getValue(), is("hello"));

                    heap.removeMin();
                    assertThat(heap.getRoot().getValue(), is("world"));

                    heap.removeMin();
                    assertThat(heap.getRoot().getValue(), is("!"));
                });
    }

    @Test
    @Score(1)
    void testRemoveMin() {
        assertTimeoutPreemptively(
            Duration.ofSeconds(1), () -> {
                IHeap<String> heap = new Heap<>();

                heap.insert(1, "hello");
                heap.insert(2, "world");

                assertThat(heap.size(), is(2));

                assertThat(heap.removeMin(), is("hello"));
                assertThat(heap.removeMin(), is("world"));

                assertThat(heap.isEmpty(), is(true));
            });
    }

    @Test
    @Score(1)
    void customRemoveMin1() {
        assertTimeoutPreemptively(
                Duration.ofSeconds(1), () -> {
                    IHeap<String> heap = new Heap<>();

                    heap.insert(1, "hello");
                    heap.insert(2, "world");
                    heap.insert(0, "!");

                    assertThat(heap.size(), is(3));

                    assertThat(heap.removeMin(), is("!"));
                    assertThat(heap.removeMin(), is("hello"));

                    assertThat(heap.isEmpty(), is(false));
                    assertThat(heap.min(), is("world"));

                    assertThat(heap.isEmpty(), is(false));
                    assertThat(heap.size(), is(1));

                    assertThat(heap.removeMin(), is("world"));
                    assertThat(heap.isEmpty(), is(true));
                });
    }

    @Test
    @Score(1)
    void customRemoveMin2() {
        assertTimeoutPreemptively(
                Duration.ofSeconds(1), () -> {
                    IHeap<Integer> heap = new Heap<>();

                    heap.insert(1, 4);
                    heap.insert(2, 5);
                    heap.insert(0, 66);
                    heap.insert(4, 23452);
                    heap.insert(5, 54);
                    heap.insert(-1, 342);
                    heap.insert(8, 0);

                    assertThat(heap.size(), is(7));

                    assertThat(heap.removeMin(), is(342));
                    assertThat(heap.removeMin(), is(66));

                    assertThat(heap.isEmpty(), is(false));
                    assertThat(heap.min(), is(4));

                    assertThat(heap.isEmpty(), is(false));
                    assertThat(heap.size(), is(5));

                    assertThat(heap.removeMin(), is(4));

                    assertThat(heap.isEmpty(), is(false));
                    assertThat(heap.removeMin(), is(5));


                    assertThat(heap.isEmpty(), is(false));
                    assertThat(heap.removeMin(), is(23452));


                    assertThat(heap.isEmpty(), is(false));
                    assertThat(heap.removeMin(), is(54));


                    assertThat(heap.isEmpty(), is(false));
                    assertThat(heap.min(), is(0));

                    assertThat(heap.isEmpty(), is(false));
                    assertThat(heap.removeMin(), is(0));

                    assertThat(heap.isEmpty(), is(true));
                });
    }

    @Test
    @Score(1)
    void testMin() {
        assertTimeoutPreemptively(
            Duration.ofSeconds(1), () -> {
                IHeap<String> heap = new Heap<>();

                heap.insert(1, "hello");
                heap.insert(2, "world");

                assertThat(heap.size(), is(2));

                assertThat(heap.min(), is("hello"));
                assertThat(heap.min(), is("hello"));

                assertThat(heap.size(), is(2));
            });
    }

    @Test
    @Score(1)
    void customTestMin1() {
        assertTimeoutPreemptively(
                Duration.ofSeconds(1), () -> {
                    IHeap<Integer> heap = new Heap<>();

                    heap.insert(1, 1);
                    heap.insert(2, 2);
                    heap.insert(3, 3);
                    heap.insert(4, 4);
                    heap.insert(5, 5);

                    assertThat(heap.size(), is(5));

                    assertThat(heap.min(), is(1));
                    assertThat(heap.min(), is(1));

                    assertThat(heap.size(), is(5));
                });
    }

    @Test
    @Score(1)
    void customTestMin2() {
        assertTimeoutPreemptively(
                Duration.ofSeconds(1), () -> {
                    IHeap<String> heap = new Heap<>();

                    assertThat(heap.size(), is(0));

                    heap.insert(-1, "hello");
                    heap.insert(2, "world");

                    assertThat(heap.size(), is(2));

                    assertThat(heap.min(), is("hello"));
                    assertThat(heap.min(), is("hello"));
                    assertThat(heap.min(), is("hello"));
                    assertThat(heap.removeMin(), is("hello"));
                    assertThat(heap.min(), is("world"));

                    assertThat(heap.size(), is(1));
                });
    }

    @Test
    @Score(1)
    void customErrorTests1() {
        IHeap<Integer> heap = new Heap<>();

        assertThrows(IllegalStateException.class,
                () -> heap.bottomUp(new int[]{ 1, 2, 5, 13, 0, 12, 4 }, new Integer[]{ 62, 4, 32, 5, 43, 1, 0, -1 }));
    }

    @Test
    @Score(1)
    void customErrorTests2() {
        IHeap<Integer> heap = new Heap<>();

        assertThrows(IllegalStateException.class,
                heap::min);
    }

    @Test
    @Score(1)
    void customErrorTests3() {
        IHeap<Integer> heap = new Heap<>();

        assertThrows(IllegalStateException.class,
                heap::getRoot);
    }

    @Test
    @Score(1)
    void customErrorTests4() {
        IHeap<Integer> heap = new Heap<>();

        assertThrows(IllegalStateException.class,
                heap::removeMin);
    }

    @Test
    @Score(5)
    void customHeapSortTest() {
        assertTimeoutPreemptively(
                Duration.ofSeconds(1), () -> {
                    IHeap<Integer> heap = new Heap<>();

                    heap.insert(34, 34);
                    heap.insert(5, 5);
                    heap.insert(4, 4);
                    heap.insert(6, 6);
                    heap.insert(8, 8);
                    heap.insert(6357, 6357);
                    heap.insert(0, 0);
                    heap.insert(62, 62);
                    heap.insert(1, 1);
                    heap.insert(432, 432);
                    heap.insert(35, 35);
                    heap.insert(236546, 236546);
                    heap.insert(5243, 5243);
                    heap.insert(1321, 1321);
                    heap.insert(5456, 5456);
                    heap.insert(3421, 3421);
                    heap.insert(2346, 2346);


                    assertThat(heap.size(), is(17));

                    assertThat(heap.min(), is(0));
                    assertThat(heap.min(), is(0));

                    assertThat(heap.size(), is(17));

                    int[] sorted = new int[heap.size()];

                    for(int i = 0; i < sorted.length; i++)
                        sorted[i] = heap.removeMin();

                    assertThat(heap.size(), is(0));
                    int largest = sorted[0];

                    for(int i = 1; i < sorted.length; i++) {
                        assertThat(sorted[i] > largest, is(true));
                        largest = sorted[i];
                    }

                    heap.insert(51,51);
                    heap.insert(41,41);
                    heap.insert(31,31);
                    heap.insert(21,21);
                    heap.insert(11,11);
                    heap.insert(1,1);
                    heap.insert(0,0);

                    sorted = new int[heap.size()];

                    for(int i = 0; i < sorted.length; i++)
                        sorted[i] = heap.removeMin();

                    assertThat(heap.size(), is(0));
                    largest = sorted[0];

                    for(int i = 1; i < sorted.length; i++) {
                        assertThat(sorted[i] > largest, is(true));
                        largest = sorted[i];
                    }
                });
    }
}
