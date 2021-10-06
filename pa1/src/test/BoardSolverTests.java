import java.time.Duration;

import org.junit.jupiter.api.*;

import static org.junit.jupiter.api.Assertions.*;
import static org.hamcrest.CoreMatchers.*;
import static org.hamcrest.MatcherAssert.assertThat;

import testrunner.annotation.Score;

public class BoardSolverTests {
    private final CellState f = CellState.FILLED;
    private final CellState e = CellState.EMPTY;
    
    @Test
    @Score(1)
    public void customTest1() {
        IBoard board = new Board(5, 5,
                new int[][] {
                        {3},
                        {4},
                        {1, 2},
                        {1, 1},
                        {1}
                },
                new int[][] {
                        {3},
                        {1},
                        {2},
                        {3},
                        {4}
                });

        CellState[][] answer = new CellState[][] {
                { e, e, f, f, f },
                { e, f, e, e, e },
                { f, f, e, e, e },
                { f, f, f, e, e },
                { f, f, f, f, e }
        };

        assertTimeoutPreemptively(Duration.ofSeconds(5), () -> {
            IBoardSolver solver = new BoardSolver();
            IBoard solved = null;
            solved = solver.solve(board);

            for (int r = 0; r < board.getHeight(); r++) {
                for (int c = 0; c < board.getWidth(); c++) {
                    assertThat(solved.getCellState(r, c), is(answer[r][c]));
                }
            }
        });
    }

    @Test
    @Score(1)
    public void customTest2() {
        IBoard board = new Board(5, 5,
                new int[][] {
                        {2},
                        {3, 1},
                        {1, 1},
                        {1, 2},
                        {2}
                },
                new int[][] {
                        {4},
                        {2},
                        {1},
                        {2},
                        {4}
                });

        CellState[][] answer = new CellState[][] {
                { f, f, f, f, e },
                { f, f, e, e, e },
                { e, f, e, e, e },
                { e, e, e, f, f },
                { e, f, f, f, f }
        };

        assertTimeoutPreemptively(Duration.ofSeconds(5), () -> {
            IBoardSolver solver = new BoardSolver();
            IBoard solved = null;
            solved = solver.solve(board);

            for (int r = 0; r < board.getHeight(); r++) {
                for (int c = 0; c < board.getWidth(); c++) {
                    assertThat(solved.getCellState(r, c), is(answer[r][c]));
                }
            }
        });
    }

    @Test
    @Score(1)
    public void customTest3() {
        IBoard board = new Board(10, 10,
                new int[][] {
                        {3},
                        {2},
                        {1, 2, 4},
                        {3, 4},
                        {3, 3},
                        {1, 1},
                        {4},
                        {5},
                        {5, 2},
                        {4, 3}
                },
                new int[][] {
                        {3, 3},
                        {3, 3},
                        {5, 3},
                        {3, 4},
                        {1, 3},
                        {1},
                        {5},
                        {3, 1},
                        {3, 2},
                        {2, 2}
                });

        CellState[][] answer = new CellState[][] {
                { e, e, f, f, f, e, e ,f, f, f },
                { e, e, e, f, f, f, e, f, f, f },
                { f, f, f, f, f, e, e, f, f, f },
                { f, f, f, e, e, e, f, f, f, f },
                { f, e, e, e, e, e, f, f, f, e },
                { e, e, e, e, e, e, f, e, e, e },
                { e, e, f, f, f, f, f, e, e, e },
                { e, e, f, f, f, e, e, e, e, f },
                { e, e, f, f, f, e, e, e, f, f },
                { e, e, f, f, e, e, e, e, f, f },
        };

        assertTimeoutPreemptively(Duration.ofSeconds(5), () -> {
            IBoardSolver solver = new BoardSolver();
            IBoard solved = null;
            solved = solver.solve(board);

            for (int r = 0; r < board.getHeight(); r++) {
                for (int c = 0; c < board.getWidth(); c++) {
                    assertThat(solved.getCellState(r, c), is(answer[r][c]));
                }
            }
        });
    }

    @Test
    @Score(1)
    public void customTest4() {
        IBoard board = new Board(20, 20,
                new int[][] {
                        { 1, 2, 2, 2 },
                        { 1, 2, 2, 3, 3 },
                        { 1, 1, 3, 5, 1, 1 },
                        { 7, 1, 3, 3 },
                        { 5, 1, 1, 1, 4 },
                        { 1, 2, 1, 1, 5 },
                        { 5,4,1 },
                        { 1,1,2,1,5 },
                        { 2,1,1,1,4,4 },
                        { 2,1,1,2,4,1 },
                        { 1,1,1,1,1,6 },
                        { 1,2,1,1,1,2,2 },
                        { 1,2,8,2,2 },
                        { 2,3,2,5,1 },
                        { 2,7,1,2,1 },
                        { 1,6,2,1,1 },
                        { 1,1,1,3 },
                        { 1,2,2,3,3 },
                        { 2,2,1,2,1,2,1 },
                        { 5,2,1,1,2 }
                },
                new int[][] {
                        {1,2,4,2,1},
                        {1,1,2,3,2},
                        {7,1,2},
                        {1,4,1,2,2,1},
                        {3,2,3,2},
                        {3,2,2,3},
                        {3,6,1},
                        {4,2,4},
                        {1,2,5,3},
                        {1,2,3,1,2,1,1},
                        {3,2,5,1},
                        {6,3,3,2},
                        {1,5,1,2},
                        {5,4,2},
                        {2,7,1,3,1},
                        {11},
                        {2,1,4,3,1},
                        {2,2,5,6},
                        {2,2,1,3,2,1},
                        {2,1,2,8}
                });

        CellState[][] answer = new CellState[][] {
                {e,f,e,e,e,f,f,e,f,f,f,f,e,f,f,e,e,f,e,e},
                {e,e,e,e,f,e,f,e,f,f,e,e,f,f,f,e,e,e,f,f},
                {f,f,f,f,f,f,f,e,e,e,f,e,e,e,e,e,e,e,f,f},
                {e,f,e,f,f,f,f,e,f,e,e,f,f,e,e,f,f,e,e,f},
                {e,e,f,f,f,e,f,f,e,e,f,f,f,e,e,e,e,e,f,f},

                {e,e,e,f,f,f,e,e,f,f,e,e,e,e,f,f,e,f,f,f},
                {e,f,f,f,e,e,e,e,e,e,f,f,f,f,f,f,e,f,e,e},
                {f,f,f,f,e,e,e,f,f,e,e,e,f,f,f,f,e,e,e,e},
                {f,e,f,f,e,e,e,e,e,e,e,f,f,f,f,f,e,f,f,f},
                {e,f,e,e,f,f,e,f,f,f,e,e,f,e,f,f,e,f,e,f},

                {f,f,f,e,e,e,e,f,f,e,e,f,f,f,f,f,e,e,f,e},
                {f,f,f,f,f,f,e,e,f,f,f,e,f,f,f,e,e,e,f,f},
                {e,e,f,e,e,f,f,f,f,f,e,e,f,e,e,e,f,f,e,e},
                {e,e,f,f,f,f,f,e,e,e,e,e,f,f,f,f,e,f,f,e},
                {e,e,f,f,e,f,f,f,f,f,f,f,e,f,e,f,f,f,e,f},

                {e,e,e,f,f,f,f,f,f,f,f,f,f,f,e,e,e,e,e,e},
                {e,f,f,e,f,e,e,f,f,f,f,e,f,f,f,e,e,e,f,e},
                {f,f,e,f,f,e,e,f,f,f,f,f,e,f,f,f,f,f,f,e},
                {f,f,e,f,f,e,e,f,e,e,f,f,f,e,e,e,f,f,e,f},
                {e,e,f,f,e,e,f,e,e,f,f,e,f,f,f,f,f,f,f,f}
        };

        assertTimeoutPreemptively(Duration.ofSeconds(5), () -> {
            IBoardSolver solver = new BoardSolver();
            IBoard solved = solver.solve(board);
            for (int r = 0; r < board.getHeight(); r++) {
                for (int c = 0; c < board.getWidth(); c++) {
                    assertThat(solved.getCellState(r, c), is(answer[r][c]));
                }
            }
        });
    }

    @Test
    @Score(1)
    public void customTest5() {
        IBoard board = new Board(5, 5,
                new int[][] {
                        {3},
                        {4},
                        {4},
                        {1, 1},
                        {1}
                },
                new int[][] {
                        {3},
                        {},
                        {2},
                        {3},
                        {4}
                });

        assertTimeoutPreemptively(Duration.ofSeconds(5), () -> {
            IBoardSolver solver = new BoardSolver();
            IBoard solved = null;
            solved = solver.solve(board);

            assertThat(solved, is(nullValue()));
        });
    }

    @Test
    @Score(1)
    public void customTest6() {
        IBoard board = new Board(5, 5,
                new int[][] {
                        {3},
                        {1, 1},
                        {1, 3},
                        {2},
                        {2}
                },
                new int[][] {
                        {3},
                        {1},
                        {3},
                        {1, 1, 1},
                        {1, 1, 1}
                });

        CellState[][] answer = new CellState[][] {
                { e, f, f, f, e },
                { e, e, e, f, e },
                { f, f, f, e, e },
                { f, e, f, e, f },
                { f, e, f, e, f }
        };

        assertTimeoutPreemptively(Duration.ofSeconds(5), () -> {
            IBoardSolver solver = new BoardSolver();
            IBoard solved = null;
            solved = solver.solve(board);

            for (int r = 0; r < board.getHeight(); r++) {
                for (int c = 0; c < board.getWidth(); c++) {
                    assertThat(solved.getCellState(r, c), is(answer[r][c]));
                }
            }
        });
    }

    @Test
    @Score(1)
    public void testSolver1() {
        IBoard board = new Board(2, 2,
            new int[][] {
                { },
                { }
            },
            new int[][] {
                { 1 },
                { }
            });
        assertTimeoutPreemptively(Duration.ofSeconds(5), () -> {
            IBoardSolver solver = new BoardSolver();
            IBoard solved = null;
            solved = solver.solve(board);
            assertThat(solved, is(nullValue()));
        });
    }

    @Test
    @Score(1)
    public void testSolver2() {
        IBoard board = new Board(2, 2,
            new int[][] {
                { 1 },
                { 1 },
            },
            new int[][] {
                { 1 },
                { 1 },
            });

        CellState[][] answer1 = new CellState[][] {
            { e , f },
            { f , e },
        };

        CellState[][] answer2 = new CellState[][] {
            { f , e },
            { e , f },
        };
        IBoardSolver solver = new BoardSolver();

        assertTimeoutPreemptively(Duration.ofSeconds(5), () -> {
            IBoard solved;
            solved = solver.solve(board);

            boolean isAnswer1;
            boolean isAnswer2;

            isAnswer1 = true;
            for (int r = 0; r < board.getHeight(); r++) {
                for (int c = 0; c < board.getWidth(); c++) {
                    if (solved.getCellState(r, c) != answer1[r][c]) { isAnswer1 = false; break; }
                }
            }

            isAnswer2 = true;
            for (int r = 0; r < board.getHeight(); r++) {
                for (int c = 0; c < board.getWidth(); c++) {
                    if (solved.getCellState(r, c) != answer2[r][c]) { isAnswer2 = false; break; }
                }
            }

            assertThat(isAnswer1 || isAnswer2, is(true));
        });
    }

    @Test
    @Score(1)
    public void testSolver3() {
        IBoard board = new Board(7, 7, 
            new int[][] {
                {},
                { 2 },
                { 1, 1 },
                { 1, 1 },
                { 1, 1 },
                { 2 },
                {}
            },
            new int[][] {
                {},
                { 1, 1 },
                { 1, 1, 1 },
                { 1, 1 },
                { 1, 1 },
                { 1 },
                {},
            });

        CellState[][] answer = new CellState[][] {
            { e , e , e , e , e , e , e },
            { e , e , f, e , f, e , e },
            { e , f, e , f, e , f, e },
            { e , f, e , e , e , f, e },
            { e , e , f, e , f, e , e },
            { e , e , e , f, e , e , e },
            { e , e , e , e , e , e , e },
        };
        IBoardSolver solver = new BoardSolver();

        assertTimeoutPreemptively(Duration.ofSeconds(5), () -> {
            IBoard solved;
            solved = solver.solve(board);
            for (int r = 0; r < board.getHeight(); r++) {
                for (int c = 0; c < board.getWidth(); c++) {
                    assertThat(solved.getCellState(r, c), is(answer[r][c]));
                }
            }
        });
    }
}
