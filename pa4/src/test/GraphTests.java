import java.time.Duration;

import org.junit.jupiter.api.*;

import static org.junit.jupiter.api.Assertions.*;
import static org.hamcrest.CoreMatchers.*;
import static org.hamcrest.MatcherAssert.assertThat;

import testrunner.annotation.Score;

public class GraphTests {
    @Test
    @Score(1)
    void testCreation() {
        assertTimeoutPreemptively(Duration.ofSeconds(1), () -> {
            IGraph G = new Graph("./src/test/graph1.txt");
        });
    }

    @Test
    @Score(1)
    void customTestCreation1() {
        assertTimeoutPreemptively(Duration.ofSeconds(1), () -> {
            IGraph G = new Graph("./src/test/graph3.txt");
            assertThat(G.size(), is(5));
        });
    }

    @Test
    @Score(1)
    void customTestCreation2() {
        assertTimeoutPreemptively(Duration.ofSeconds(1), () -> {
            IGraph G = new Graph("./src/test/graph2.txt");
            assertThat(G.size(), is(10));
        });
    }

    @Test
    @Score(1)
    void testNodeInsertDelete() {
        assertTimeoutPreemptively(Duration.ofSeconds(1), () -> {
            IGraph G = new Graph("./src/test/graph1.txt");
            G.insertVertex();
            G.insertVertex();
            G.insertVertex();
            G.insertVertex();
            G.insertVertex();
            assertThat(G.size(), is(6));
            G.deleteVertex(3);
            G.deleteVertex(3);
            G.deleteVertex(3);
            assertThat(G.size(), is(3));
        });
    }

    @Test
    @Score(3)
    void customTestNodeInsertDelete1() {
        assertTimeoutPreemptively(Duration.ofSeconds(1), () -> {
            IGraph G = new Graph("./src/test/graph3.txt");

            // Deletion should not occur here!
            G.deleteVertex(6);
            assertThat(G.size(), is(5));

            // Deletion should occur from here onwards
            G.insertVertex();
            assertThat(G.size(), is(6));
            G.insertVertex();
            G.insertVertex();
            assertThat(G.size(), is(8));
            G.deleteVertex(3);
            G.deleteVertex(2);
            assertThat(G.size(), is(6));

            // Deletion should not occur here!
            G.deleteVertex(6);
            assertThat(G.size(), is(6));

            // Deletion should occur from here onwards
            G.deleteVertex(5);
            assertThat(G.size(), is(5));

            G.deleteVertex(3);
            assertThat(G.size(), is(4));

            G.deleteVertex(2);
            assertThat(G.size(), is(3));

            // Deletion should not occur here!
            G.deleteVertex(3);
            assertThat(G.size(), is(3));
        });
    }

    @Test
    @Score(1)
    void testConstruction() {
        assertTimeoutPreemptively(Duration.ofSeconds(1), () -> {
            IGraph G = new Graph("./src/test/graph2.txt");
            assertThat(G.matrix(),
                    is(new int[][] { { 0, 1, 1, 1, 1, 1, 1, 1, 1, 1 }, { 0, 0, 1, 0, 0, 0, 0, 0, 0, 0 },
                            { 0, 0, 0, 1, 0, 0, 0, 0, 0, 1 }, { 0, 0, 0, 0, 1, 0, 0, 0, 0, 0 },
                            { 0, 0, 0, 0, 0, 1, 0, 0, 0, 1 }, { 0, 0, 0, 0, 0, 0, 1, 0, 0, 0 },
                            { 0, 0, 0, 0, 0, 0, 0, 1, 0, 1 }, { 0, 0, 0, 0, 0, 0, 0, 0, 1, 0 },
                            { 0, 0, 0, 0, 0, 0, 0, 0, 0, 1 }, { 0, 0, 0, 0, 0, 0, 0, 0, 0, 0 } }));
        });
    }

    @Test
    @Score(5)
    void customTestConstruction1() {
        assertTimeoutPreemptively(Duration.ofSeconds(1), () -> {
            IGraph G = new Graph("./src/test/graph3.txt");

            assertThat(G.matrix(),
                    is(new int[][] {
                            { 0, 1, 0, 0, 0 }, { 0, 0, 1, 0, 0 },
                            { 0, 0, 0, 1, 0 }, { 0, 0, 0, 0, 1 },
                            { 1, 0, 0, 0, 0 }
                    }));

            G.insertVertex();
            assertThat(G.matrix(),
                    is(new int[][] {
                            { 0, 1, 0, 0, 0, 0 }, { 0, 0, 1, 0, 0, 0 },
                            { 0, 0, 0, 1, 0, 0 }, { 0, 0, 0, 0, 1, 0 },
                            { 1, 0, 0, 0, 0, 0 }, { 0, 0, 0, 0, 0, 0 }
                    }));

            G.insertVertex();
            assertThat(G.matrix(),
                    is(new int[][] {
                            { 0, 1, 0, 0, 0, 0, 0 }, { 0, 0, 1, 0, 0, 0, 0 },
                            { 0, 0, 0, 1, 0, 0, 0 }, { 0, 0, 0, 0, 1, 0, 0 },
                            { 1, 0, 0, 0, 0, 0, 0 }, { 0, 0, 0, 0, 0, 0, 0 },
                            { 0, 0, 0, 0, 0, 0, 0 }
                    }));

            G.deleteVertex(5);
            assertThat(G.matrix(),
                    is(new int[][] {
                            { 0, 1, 0, 0, 0, 0 }, { 0, 0, 1, 0, 0, 0 },
                            { 0, 0, 0, 1, 0, 0 }, { 0, 0, 0, 0, 1, 0 },
                            { 1, 0, 0, 0, 0, 0 }, { 0, 0, 0, 0, 0, 0 }
                    }));

            G.deleteVertex(5);
            assertThat(G.matrix(),
                    is(new int[][] {
                            { 0, 1, 0, 0, 0 }, { 0, 0, 1, 0, 0 },
                            { 0, 0, 0, 1, 0 }, { 0, 0, 0, 0, 1 },
                            { 1, 0, 0, 0, 0 }
                    }));
        });
    }

    @Test
    @Score(5)
    void customTestConstruction2() {
        assertTimeoutPreemptively(Duration.ofSeconds(1), () -> {
            IGraph G = new Graph("./src/test/graph4.txt");

            assertThat(G.matrix(),
                    is(new int[][] {
                            { 0, 1, 0, 0, 0 }, { 1, 0, 0, 0, 0 },
                            { 0, 0, 0, 1, 1 }, { 0, 0, 1, 0, 0 },
                            { 0, 0, 1, 0, 0 }
                    }));

            G.deleteVertex(3);
            assertThat(G.matrix(),
                    is(new int[][] {
                            { 0, 1, 0, 0 }, { 1, 0, 0, 0 },
                            { 0, 0, 0, 1 }, { 0, 0, 1, 0 }
                    }));

            G.insertVertex();
            assertThat(G.matrix(),
                    is(new int[][] {
                            { 0, 1, 0, 0, 0 }, { 1, 0, 0, 0, 0 },
                            { 0, 0, 0, 1, 0 }, { 0, 0, 1, 0, 0 },
                            { 0, 0, 0, 0, 0 }
                    }));

            G.deleteVertex(4);
            assertThat(G.matrix(),
                    is(new int[][] {
                            { 0, 1, 0, 0 }, { 1, 0, 0, 0 },
                            { 0, 0, 0, 1 }, { 0, 0, 1, 0 }
                    }));

            G.insertVertex();
            G.deleteVertex(3);
            assertThat(G.matrix(),
                    is(new int[][] {
                            { 0, 1, 0, 0 }, { 1, 0, 0, 0 },
                            { 0, 0, 0, 0 }, { 0, 0, 0, 0 }
                    }));
        });
    }

    @Test
    @Score(1)
    void testEdgeInsertDelete() {
        assertTimeoutPreemptively(Duration.ofSeconds(1), () -> {
            IGraph G = new Graph("./src/test/graph2.txt");
            G.insertEdge(9, 0, 2);
            G.insertEdge(9, 2, 1);
            G.deleteEdge(0, 9);
            G.deleteEdge(1, 9);
            G.insertEdge(9, 4, 1);
            G.insertEdge(9, 6, 1);
            G.insertEdge(9, 8, 1);
            assertThat(G.matrix(),
                    is(new int[][] { { 0, 1, 1, 1, 1, 1, 1, 1, 1, 0 }, { 0, 0, 1, 0, 0, 0, 0, 0, 0, 0 },
                            { 0, 0, 0, 1, 0, 0, 0, 0, 0, 1 }, { 0, 0, 0, 0, 1, 0, 0, 0, 0, 0 },
                            { 0, 0, 0, 0, 0, 1, 0, 0, 0, 1 }, { 0, 0, 0, 0, 0, 0, 1, 0, 0, 0 },
                            { 0, 0, 0, 0, 0, 0, 0, 1, 0, 1 }, { 0, 0, 0, 0, 0, 0, 0, 0, 1, 0 },
                            { 0, 0, 0, 0, 0, 0, 0, 0, 0, 1 }, { 2, 0, 1, 0, 1, 0, 1, 0, 1, 0 } }));
        });
    }
}
