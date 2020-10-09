import com.company.HashTable;


import java.util.HashSet;
import java.util.List;
import java.util.ArrayList;
import java.util.Random;
import java.util.Set;

import org.junit.Test;

import static org.junit.Assert.*;

/**
 * Testing for implemented HashTable
 *
 * Author: Farrukh Karimov
 * Last modification: 05/10/2020
 */
public class HashTableTest {
    private final int INT_LEFT_LIMIT = -1000000000;
    private final int INT_RIGHT_LIMIT = 1000000000;
    private final int COEFFICIENT = 175;

    private static List<Integer> generateRandomInts(final int elementsNumber, int leftLimit, int rightLimit) {
        assertTrue("wrong parameter for number generator function",
                leftLimit <= rightLimit && elementsNumber > 0);

        final Set<Integer> generatedElements = new HashSet<>();
        final Random random = new Random();

        while (generatedElements.size() < elementsNumber) {
            int x = leftLimit + ((random.nextInt() ^ random.nextInt()) % (rightLimit - leftLimit + 1));
            generatedElements.add(x);
        }

        return new ArrayList<>(generatedElements);
    }

    @Test(expected = NullPointerException.class)
    public void cantPutNullValue() {
        final HashTable<String> hashTable = new HashTable<>();
        hashTable.put(null);
    }

    @Test(expected = NullPointerException.class)
    public void cantRemoveNullValue() {
        final HashTable<String> hashTable = new HashTable<>();
        hashTable.remove(null);
    }

    @Test(expected = NullPointerException.class)
    public void cantCheckForNullValue() {
        final HashTable<String> hashTable = new HashTable<>();
        hashTable.contains(null);
    }

    @Test
    public void addingElementsIsCorrect() {
        final HashTable<Integer> hashTable = new HashTable<>();

        final int elementsNumber = hashTable.getBucketsNumber() * COEFFICIENT;
        final List<Integer> generatedNumbers = generateRandomInts(elementsNumber, INT_LEFT_LIMIT, INT_RIGHT_LIMIT);

        assertEquals(0, hashTable.size());
        for (int i = 0; i < elementsNumber; i++) {
            hashTable.put(generatedNumbers.get(i));
            assertEquals(hashTable.size(), i + 1);
        }

        assertEquals(elementsNumber, hashTable.size());
        for (int i = 0; i < elementsNumber; i++) {
            assertTrue("element " + generatedNumbers.get(i) + "not found ", hashTable.contains(generatedNumbers.get(i)));
        }

        List<Integer> hashTableElements = hashTable.elements();
        hashTableElements.sort(Integer::compareTo);
        generatedNumbers.sort(Integer::compareTo);
        for (int i = 0; i < elementsNumber; i++) {
            assertEquals(hashTableElements.get(i), generatedNumbers.get(i));
        }
    }

    @Test
    public void removingElementsIsCorrect() {
        final HashTable<Integer> hashTable = new HashTable<>();

        final int elementsNumber = hashTable.getBucketsNumber() * COEFFICIENT;
        final List<Integer> generatedNumbers = generateRandomInts(elementsNumber, INT_LEFT_LIMIT, INT_RIGHT_LIMIT);
        for (int i = 0; i < elementsNumber; i++) {
            hashTable.put(generatedNumbers.get(i));
        }

        assertEquals(elementsNumber, hashTable.size());
        for (int i = 0; i < elementsNumber; i++) {
            assertTrue("can't delete number from table",
                    hashTable.remove(generatedNumbers.get(i)));
            assertEquals(elementsNumber - i - 1, hashTable.size());

            final List<Integer> hashTableElements = hashTable.elements();
            assertFalse(hashTableElements.contains(generatedNumbers.get(i)));
        }
    }

    @Test
    public void clearingElementsIsCorrect() {
        final HashTable<Integer> hashTable = new HashTable<>();

        final int elementsNumber = hashTable.getBucketsNumber() * COEFFICIENT;
        final List<Integer> generatedNumbers = generateRandomInts(elementsNumber, INT_LEFT_LIMIT, INT_RIGHT_LIMIT);

        assertEquals(hashTable.size(), 0);
        for (int i = 0; i < elementsNumber; i++) {
            hashTable.put(generatedNumbers.get(i));
        }

        assertEquals(hashTable.size(), elementsNumber);

        hashTable.clear();

        assertEquals(hashTable.size(), 0);

        final List<Integer> hashTableElements = hashTable.elements();

        assertEquals(hashTableElements.size(), 0);
    }
}
