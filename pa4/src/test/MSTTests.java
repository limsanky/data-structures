import java.time.Duration;

import org.junit.jupiter.api.*;

import static org.junit.jupiter.api.Assertions.*;
import static org.hamcrest.CoreMatchers.*;
import static org.hamcrest.MatcherAssert.assertThat;

import testrunner.annotation.Score;

public class MSTTests {
    @Test
    @Score(1)
    void testCreation() {
        assertTimeoutPreemptively(Duration.ofSeconds(1), () -> {
            IGraph G = new Graph("./src/test/graph5.txt");
            IMST mst = new MST();
        });
    }

    @Test
    @Score(1)
    void testPath() {
        assertTimeoutPreemptively(Duration.ofSeconds(1), () -> {
            IGraph G = new Graph("./src/test/graph5.txt");
            IMST mst = new MST();
            assertThat(mst.shortestPath(G, 3, 2), is(4));
            assertThat(mst.shortestPath(G, 2, 3), is(12));});
    }

    @Test
    @Score(1)
    void customTestPath1() {
        assertTimeoutPreemptively(Duration.ofSeconds(1), () -> {
            IGraph G = new Graph("./src/test/customGraph1.txt");
            IMST mst = new MST();
            assertThat(mst.shortestPath(G, 0, 4), is(3));
            assertThat(mst.shortestPath(G, 2, 3), is(8));
            assertThat(mst.shortestPath(G, 4, 2), is(5));});
    }

    @Test
    @Score(2)
    void customTestPath2() {
        assertTimeoutPreemptively(Duration.ofSeconds(1), () -> {
            IGraph G = new Graph("./src/test/customGraph2.txt");
            IMST mst = new MST();
            assertThat(mst.shortestPath(G, 0, 1), is(4));
            assertThat(mst.shortestPath(G, 1, 0), is(2));
            assertThat(mst.shortestPath(G, 2, 0), is(4));
            assertThat(mst.shortestPath(G, 1, 2), is(6));});
    }

    @Test
    @Score(1)
    void customTestPath3() {
        assertTimeoutPreemptively(Duration.ofSeconds(1), () -> {
            IGraph G = new Graph("./src/test/customGraph3.txt");
            IMST mst = new MST();
            assertThat(mst.shortestPath(G, 3, 1), is(5));
            assertThat(mst.shortestPath(G, 2, 1), is(4));});
    }

    @Test
    @Score(3)
    void customTestPath4() {
        assertTimeoutPreemptively(Duration.ofSeconds(1), () -> {
            IGraph G = new Graph("./src/test/customGraph4.txt");
            IMST mst = new MST();
            assertThat(mst.shortestPath(G, 0, 3), is(3));
            assertThat(mst.shortestPath(G, 3, 0), is(-1));
            assertThat(mst.shortestPath(G, 3, 1), is(-1));
            assertThat(mst.shortestPath(G, 2, 1), is(-1));
            assertThat(mst.shortestPath(G, 0, 2), is(1));});
    }

    @Test
    @Score(1)
    void testMST() {
        assertTimeoutPreemptively(Duration.ofSeconds(1), () -> {
            IGraph G = new Graph("./src/test/graph5.txt");
            IMST mst = new MST();
            assertThat(mst.findMST(G), is(4));});
    }

    @Test
    @Score(1)
    void customTestMST1() {
        assertTimeoutPreemptively(Duration.ofSeconds(1), () -> {
            IGraph G = new Graph("./src/test/graph4.txt");
            IMST mst = new MST();
            assertThat(mst.findMST(G), is(-1));});
    }

    @Test
    @Score(1)
    void customTestMST2() {
        assertTimeoutPreemptively(Duration.ofSeconds(1), () -> {
            IGraph G = new Graph("./src/test/customGraph1.txt");
            IMST mst = new MST();
            assertThat(mst.findMST(G), is(14));
        });
    }

    @Test
    @Score(1)
    void customTestMST3() {
        assertTimeoutPreemptively(Duration.ofSeconds(1), () -> {
            IGraph G = new Graph("./src/test/customGraph2.txt");
            IMST mst = new MST();
            assertThat(mst.findMST(G), is(14));});
    }

    @Test
    @Score(1)
    void customTestMST4() {
        assertTimeoutPreemptively(Duration.ofSeconds(1), () -> {
            IGraph G = new Graph("./src/test/customGraph3.txt");
            IMST mst = new MST();
            assertThat(mst.findMST(G), is(8));
        });
    }

    @Test
    @Score(1)
    void customTestMST5() {
        assertTimeoutPreemptively(Duration.ofSeconds(1), () -> {
            IGraph G = new Graph("./src/test/customGraph4.txt");
            IMST mst = new MST();
            assertThat(mst.findMST(G), is(3));
        });
    }
}
