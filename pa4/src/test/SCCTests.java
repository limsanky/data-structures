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
}
