package puf.m2.hms.model;

import static org.junit.Assert.*;

import java.util.Map;

import org.junit.Test;

public class CacheAwareMapTest {

    @Test
    public void testPut() {
        Map<Integer, String> map = new CacheAwareMap<Integer, String>();
        HmsEntity.setCached(false);
        map.put(1, "one");
        assertEquals(0, map.size());
        HmsEntity.setCached(true);
        map.put(2, "two");
        assertEquals(1, map.size());
        assertEquals("two", map.get(2));
        
    }

}
