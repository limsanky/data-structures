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
            assertThat(mst.shortestPath(G, 2, 3), is(12));
        });
    }

    @Test
    @Score(1)
    void testMST() {
        assertTimeoutPreemptively(Duration.ofSeconds(1), () -> {
            IGraph G = new Graph("./src/test/graph5.txt");
            IMST mst = new MST();
            assertThat(mst.findMST(G), is(4));
        });
    }
}
