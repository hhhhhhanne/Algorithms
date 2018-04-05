import org.junit.Test;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;

public class UnitTest {
    @Test
    public void testGetKey(){
        HashTable hashTable = new HashTable(10);
        assertEquals(0,hashTable.getValue(0));
    }

    @Test
    public void testInsertKey1(){
        HashTable hashTable = new HashTable(10);
        hashTable.insert(1);
        assertEquals(1,hashTable.getValue(1));
    }

    @Test
    public void testInsertKey2(){
        HashTable hashTable = new HashTable(10);
        hashTable.insert(1);
        hashTable.insert(1);
        assertEquals(2,hashTable.getValue(1));
    }

    @Test
    public void testFull(){
        HashTable hashTable = new HashTable(10);
        for (int i = 0; i < 10; i++){
            hashTable.insert(i);
            assertEquals(1,hashTable.getValue(i));
        }
    }
}
