import java.time.Duration;

import org.junit.jupiter.api.*;

import static org.junit.jupiter.api.Assertions.*;
import static org.hamcrest.CoreMatchers.*;
import static org.hamcrest.MatcherAssert.assertThat;

import testrunner.annotation.Score;

class ReconstructorTests {

    @Test
    @Score(1)
    void testInsert()
    {
        assertTimeoutPreemptively(
            Duration.ofSeconds(1), () -> {
                IReconstructor tb = new Reconstructor();

                ITreeNode root = tb.reconstruct(new Integer[] {4,5,2,6,3,1},new Integer[] {4,2,5,1,3,6});
                assertThat(root.val(), is(1));
                assertThat(root.left().val(), is(2));
                assertThat(root.right().val(), is(3));
                assertThat(root.left().left().val(), is(4));
                assertThat(root.left().right().val(), is(5));
                assertThat(root.right().right().val(), is(6));
                assertThat(root.right().left(), is(nullValue()));
                assertThat(root.parent(), is(nullValue()));
                assertThat(root.right().parent(), is(root));
            });
    }

    @Test
    @Score(1)
    void customTest1()
    {
        assertTimeoutPreemptively(
                Duration.ofSeconds(1), () -> {
                    IReconstructor tb = new Reconstructor();
                    assertThrows(
                            IllegalStateException.class, () -> tb.reconstruct(new Integer[] {1, 2, 4, 3},
                                    new Integer[] {4,3,1,2}),
                            "reconstruct()");
                });
    }

    @Test
    @Score(1)
    void customTest2()
    {
        IReconstructor tb = new Reconstructor();

        ITreeNode root = tb.reconstruct(new Integer[] {1},new Integer[] {1});
        assertThat(root.val(), is(1));
        assertNull(root.left());
        assertNull(root.right());
    }

    @Test
    @Score(1)
    void customTest3()
    {
        assertTimeoutPreemptively(
                Duration.ofSeconds(1), () -> {
                    IReconstructor tb = new Reconstructor();
                    assertThrows(
                            IllegalStateException.class, () -> tb.reconstruct(new Integer[] {1, 5},
                                    new Integer[] {1,2}),
                            "reconstruct()");
                });
    }

    @Test
    @Score(1)
    void customTest4()
    {
        IReconstructor tb = new Reconstructor();

        ITreeNode root = tb.reconstruct(new Integer[] {9,15,7,20,3},new Integer[] {9,3,15,20,7});
        assertThat(root.val(), is(3));
        assertThat(root.left().val(), is(9));
        assertThat(root.right().val(), is(20));
        assertThat(root.right().right().val(), is(7));
        assertThat(root.right().left().val(), is(15));
    }
}
