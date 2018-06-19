import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import sun.rmi.runtime.Log;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Vector;
import java.util.logging.Logger;

public class SamplwTest {
    ConcurrentMultiHashMap concurrentMultiHashMap;

    Logger logger = Logger.getLogger(SamplwTest.class.getName());

    @Before
    public void setup() {
        concurrentMultiHashMap = new ConcurrentMultiHashMap<String, ArrayList>();


    }

    @Test
    public void run() {
        Thread thread1 = new Thread(new Thread1(concurrentMultiHashMap)) {
            public void run() {
                Vector<Integer> vector1 = new Vector<Integer>();

                for (int i = 0; i < 100; i++) {
                    vector1.add(i);
                }
                concurrentMultiHashMap.putAll("collection1", vector1);
            }

        };

        thread1.start();
        logger.info("collection1" + concurrentMultiHashMap.getValue("collection1"));

        Thread thread2 = new Thread(new Thread1(concurrentMultiHashMap)) {
            public void run() {
                Vector<String> vector = new Vector<>();

                for (int i = 0; i < 100; i++) {
                    vector.add("Data " + i);
                }
                concurrentMultiHashMap.putAll("collection2", vector);
            }

        };

        thread2.start();


    }
}
