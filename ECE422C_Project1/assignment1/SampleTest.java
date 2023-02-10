// package assignment1;

// import static org.junit.Assert.*;

// import org.junit.Test;
// import org.junit.Rule;
// import org.junit.rules.Timeout;

// public class SampleTest {
//     @Rule
//     public Timeout globalTimeout = Timeout.seconds(2);

//     @Test
//     public void sampleTest() {
//         int[] x = new int[]{-5, -4, -3, -2, -1, 0};
//         int[] original = x.clone();
//         int n = x.length;

//         assertEquals(2, SortTools.find(x, n, -3));
//         assertArrayEquals(original, x);
//     }

//     @Test
//     public void sampleTest1() {
//         int[] x = new int[]{-5, -4, -3, -2, -1, 0};
//         int[] original = x.clone();
//         int n = 1;

//         assertEquals(-1, SortTools.find(x, n, -3));
//         assertArrayEquals(original, x);
//     }
// }
