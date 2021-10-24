import java.time.Duration;

import org.junit.jupiter.api.*;

import static org.junit.jupiter.api.Assertions.*;
import static org.hamcrest.CoreMatchers.*;
import static org.hamcrest.MatcherAssert.assertThat;

import testrunner.annotation.Score;

class DequeTests {

    @Test
    @Score(2)
    void testInsert()
    {
        assertTimeoutPreemptively(
            Duration.ofSeconds(1), () -> {
                IDeque<Integer> dq = new Deque<>();

                dq.insertFirst(1);
                assertThat(dq.first(), is(1));

                dq.insertLast(2);
                assertThat(dq.last(), is(2));
            });
    }

    @Test
    @Score(2)
    void customInsertTest1()
    {
        assertTimeoutPreemptively(
                Duration.ofSeconds(1), () -> {
                    IDeque<Integer> dq = new Deque<>();

                    dq.insertFirst(193);
                    assertThat(dq.first(), is(193));

                    dq.insertLast(212);
                    assertThat(dq.last(), is(212));

                    dq.insertLast(2132);
                    assertThat(dq.last(), is(2132));

                    dq.insertFirst(22);
                    assertThat(dq.first(), is(22));
                });
    }

    @Test
    @Score(2)
    void customInsertTest2()
    {
        assertTimeoutPreemptively(
                Duration.ofSeconds(1), () -> {
                    IDeque<Integer> dq = new Deque<>();

                    dq.insertFirst(193);
                    assertThat(dq.first(), is(193));

                    dq.insertFirst(22);
                    assertThat(dq.first(), is(22));
                });
    }

    @Test
    @Score(2)
    void customInsertTest3()
    {
        assertTimeoutPreemptively(
                Duration.ofSeconds(1), () -> {
                    IDeque<Integer> dq = new Deque<>();

                    dq.insertLast(193);
                    assertThat(dq.last(), is(193));

                    dq.insertFirst(22);
                    assertThat(dq.first(), is(22));
                    assertThat(dq.last(), is(193));
                });
    }

    @Test
    @Score(2)
    void customInsertTest4()
    {
        assertTimeoutPreemptively(
                Duration.ofSeconds(1), () -> {
                    IDeque<Integer> dq = new Deque<>();

                    dq.insertLast(193);
                    assertThat(dq.last(), is(193));

                    dq.insertLast(22);
                    assertThat(dq.last(), is(22));

                    dq.insertFirst(222);
                    assertThat(dq.first(), is(222));
                });
    }

    @Test
    @Score(4)
    void customInsertTest5()
    {
        assertTimeoutPreemptively(
                Duration.ofSeconds(1), () -> {
                    IDeque<Integer> dq = new Deque<>();

                    dq.insertLast(22);
                    assertThat(dq.first(), is(22));

                    dq = new Deque<>();

                    dq.insertFirst(22);
                    assertThat(dq.last(), is(22));
                });
    }

    @Test
    @Score(2)
    void testDelete()
    {
        assertTimeoutPreemptively(
            Duration.ofSeconds(1), () -> {
                IDeque<Integer> dq = new Deque<>();

                dq.insertFirst(1);
                dq.insertLast(2);

                dq.deleteLast();
                assertThat(dq.last(), is(1));

                dq.deleteFirst();
                assertThat(dq.isEmpty(), is(true));
            });
    }

    @Test
    @Score(2)
    void customDeleteTest1()
    {
        assertTimeoutPreemptively(
                Duration.ofSeconds(1), () -> {
                    IDeque<Integer> dq = new Deque<>();

                    dq.insertFirst(1);
                    dq.insertLast(2);

                    dq.deleteLast();
                    assertThat(dq.first(), is(1));

                    dq.deleteLast();
                    assertThat(dq.isEmpty(), is(true));
                });
    }

    @Test
    @Score(2)
    void customDeleteTest2()
    {
        assertTimeoutPreemptively(
                Duration.ofSeconds(1), () -> {
                    IDeque<Integer> dq = new Deque<>();

                    dq.insertFirst(1);

                    dq.deleteLast();
                    assertThat(dq.isEmpty(), is(true));
                });
    }

    @Test
    @Score(2)
    void customDeleteTest3()
    {
        assertTimeoutPreemptively(
                Duration.ofSeconds(1), () -> {
                    IDeque<Integer> dq = new Deque<>();

                    dq.insertFirst(1);
                    dq.insertFirst(21);
                    dq.insertLast(2);

                    assertThat(dq.first(), is(21));
                    dq.deleteFirst();
                    assertThat(dq.first(), is(1));

                    dq.deleteLast();
                    assertThat(dq.isEmpty(), is(false));
                    assertThat(dq.last(), is(1));
                });
    }

    @Test
    @Score(2)
    void customDeleteTest4()
    {
        assertTimeoutPreemptively(
                Duration.ofSeconds(1), () -> {
                    IDeque<Integer> dq = new Deque<>();

                    assertThat(dq.isEmpty(), is(true));
                    dq.insertFirst(1);
                    assertThat(dq.last(), is(1));
                    dq.deleteLast();
                    dq.insertLast(12);
                    assertThat(dq.first(), is(12));
                    dq.deleteFirst();

                    assertThat(dq.isEmpty(), is(true));
                });
    }

    @Test
    @Score(2)
    void customDeleteTest5()
    {
        assertTimeoutPreemptively(
                Duration.ofSeconds(1), () -> {
                    IDeque<Integer> dq = new Deque<>();

                    assertThat(dq.isEmpty(), is(true));
                    dq.insertLast(1);
                    dq.insertLast(11);
                    dq.insertFirst(123);

                    assertThat(dq.last(), is(11));
                    dq.deleteFirst();

                    dq.insertLast(115);
                    assertThat(dq.first(), is(1));
                    dq.deleteFirst();

                    assertThat(dq.isEmpty(), is(false));
                    dq.deleteFirst();
                    dq.deleteLast();
                    assertThat(dq.isEmpty(), is(true));
                });
    }

    @Test
    @Score(1)
    void testEmpty()
    {
        assertTimeoutPreemptively(
            Duration.ofSeconds(1), () -> {
                IDeque<Integer> dq = new Deque<>();

                assertThrows(
                    IllegalStateException.class, () -> dq.deleteFirst(),
                    "deleteFirst()");
                assertThrows(
                    IllegalStateException.class, () -> dq.deleteLast(),
                    "deleteLast()");
                assertThrows(
                    IllegalStateException.class, () -> dq.first(),
                    "first().");
                assertThrows(
                    IllegalStateException.class, () -> dq.last(),
                    "last()");
                assertThat("Deque size", dq.size(), is(0));
                assertThat(dq.isEmpty(), is(true));
            });
    }
}
