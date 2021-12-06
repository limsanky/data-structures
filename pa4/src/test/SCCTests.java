import java.time.Duration;

import org.junit.jupiter.api.*;

import static org.junit.jupiter.api.Assertions.*;
import static org.hamcrest.CoreMatchers.*;
import static org.hamcrest.MatcherAssert.assertThat;

import testrunner.annotation.Score;

import java.util.List;
import java.util.ArrayList;
import java.util.Arrays;

public class SCCTests {
    @Test
    @Score(1)
    void testCreation() {
        assertTimeoutPreemptively(Duration.ofSeconds(1), () -> {
            ISCC scc = new SCC();
        });
    }

    @Test
    @Score(1)
    void testpath() {
        assertTimeoutPreemptively(Duration.ofSeconds(1), () -> {
            IGraph G = new Graph("./src/test/graph2.txt");
            ISCC scc = new SCC();
            assertThat(scc.path(G, 1, 9), is(true));
        });
    }

    @Test
    @Score(9)
    void customTestPath1() {
        assertTimeoutPreemptively(Duration.ofSeconds(1), () -> {
            IGraph G = new Graph("./src/test/graph2.txt");
            ISCC scc = new SCC();
            assertThat(scc.path(G, 0, 5), is(true));
            assertThat(scc.path(G, 0, 2), is(true));
            assertThat(scc.path(G, 0, 3), is(true));
            assertThat(scc.path(G, 0, 4), is(true));
            assertThat(scc.path(G, 0, 6), is(true));
            assertThat(scc.path(G, 0, 7), is(true));
            assertThat(scc.path(G, 1, 6), is(true));
            assertThat(scc.path(G, 2, 5), is(true));
            assertThat(scc.path(G, 6, 9), is(true));
        });
    }

    @Test
    @Score(5)
    void customTestPath2() {
        assertTimeoutPreemptively(Duration.ofSeconds(1), () -> {
            IGraph G = new Graph("./src/test/graph5.txt");
            ISCC scc = new SCC();
            assertThat(scc.path(G, 0, 1), is(true));
            assertThat(scc.path(G, 3, 0), is(true));
            assertThat(scc.path(G, 1, 0), is(true));
            assertThat(scc.path(G, 4, 3), is(true));
        });
    }

    @Test
    @Score(8)
    void customTestPath3() {
        assertTimeoutPreemptively(Duration.ofSeconds(1), () -> {
            IGraph G = new Graph("./src/test/graph4.txt");
            ISCC scc = new SCC();
            assertThat(scc.path(G, 3, 0), is(false));
            assertThat(scc.path(G, 3, 1), is(false));
            assertThat(scc.path(G, 4, 1), is(false));
            assertThat(scc.path(G, 2, 1), is(false));
            assertThat(scc.path(G, 0, 4), is(false));
            assertThat(scc.path(G, 0, 2), is(false));

            assertThat(scc.path(G, 1, 0), is(true));
            assertThat(scc.path(G, 3, 4), is(true));
        });
    }

    @Test
    @Score(1)
    void testconnectivity() {
        assertTimeoutPreemptively(Duration.ofSeconds(1), () -> {
            IGraph G2 = new Graph("./src/test/graph2.txt");
            IGraph G3 = new Graph("./src/test/graph3.txt");
            IGraph G4 = new Graph("./src/test/graph4.txt");
            ISCC scc = new SCC();
            assertThat(scc.connectivity(G2), is(10));
            assertThat(scc.connectivity(G3), is(1));
            assertThat(scc.connectivity(G4), is(2));
        });
    }

    @Test
    @Score(2)
    void customTestConnectivity1() {
        assertTimeoutPreemptively(Duration.ofSeconds(1), () -> {
            IGraph G1 = new Graph("./src/test/graph1.txt");
            IGraph G5 = new Graph("./src/test/graph5.txt");

            ISCC scc = new SCC();

            assertThat(scc.connectivity(G1), is(1));
            assertThat(scc.connectivity(G5), is(1));
        });
    }
}
