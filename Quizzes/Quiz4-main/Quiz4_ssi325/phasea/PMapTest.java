/*
 * Phase A <studentA EID><studentB EID>
 * Phase B <studentB EID><studentA EID>
 */

package pmap.phasea;

import org.junit.Test;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

public class PMapTest {

    @Test
    public void testPut() {
        PMap m = new PMap();
        assertTrue(m.isEmpty());
        assertEquals(null, m.put(1, 2));
        assertEquals(1, m.size().intValue());
        assertEquals(2, m.put(1, 3).intValue());
        assertEquals(1, m.size().intValue());
    }

    @Test
    public void testGet() {
        PMap m = new PMap();
        m.put(1, 2);
        assertEquals(2, m.get(1).intValue());
        assertEquals(null, m.get(2));
    }

    // TODO add more test cases to test all implemented methods
}
