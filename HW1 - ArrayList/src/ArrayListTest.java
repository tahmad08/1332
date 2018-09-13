import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

public class ArrayListTest {
    private ArrayListInterface<String> list;

    public static final int TIMEOUT = 200;

    @Before
    public void setUp() {
        list = new ArrayList<String>();
    }

    @Test(timeout = TIMEOUT)
    public void testAddStrings() {
        assertEquals(0, list.size());

        list.addAtIndex(0, "0a"); //0a
        list.addAtIndex(1, "1a"); //0a 1a
        list.addAtIndex(2, "2a"); //0a 1a 2a
        list.addAtIndex(3, "3a"); //0a 1a 2a 3a

        assertEquals(4, list.size());

        Object[] expected = new Object[ArrayListInterface.INITIAL_CAPACITY];
        expected[0] = "0a";
        expected[1] = "1a";
        expected[2] = "2a";
        expected[3] = "3a";
        assertArrayEquals(expected, list.getBackingArray());
    }

    @Test(timeout = TIMEOUT)
    public void testAddStringsOutofOrder(){
        assertEquals(0, list.size());

        list.addAtIndex(0, "0a"); //0a
        list.addAtIndex(1, "1a"); //0a 1a
        list.addAtIndex(2, "2a"); //0a 1a 2a
        list.addAtIndex(3, "3a"); //0a 1a 2a 3a

        assertEquals(4, list.size());

        list.addAtIndex(2, "blah");
        list.addAtIndex(1, "InfiniteJest");

        Object[] expected = new Object[ArrayListInterface.INITIAL_CAPACITY];
        expected[0] = "0a";
        expected[1] = "InfiniteJest";
        expected[2] = "1a";
        expected[3] = "blah";
        expected[4] = "2a";
        expected[5] = "3a";
        assertArrayEquals(expected, list.getBackingArray());

    }

    @Test(timeout = TIMEOUT)
    public void testAddStringsOverSize() {
        assertEquals(0, list.size());

        String testStr = "";
        for(int i = 0; i<14; i++){
            testStr = i+"a";
            list.addAtIndex(i, testStr);
            testStr = "";
        }

        assertEquals(14, list.size());

        Object[] expected = new Object[22];
        for(int i = 0; i<14; i++){
            testStr = i+"a";
            expected[i] = testStr;
            testStr = "";
        }
        assertArrayEquals(expected, list.getBackingArray());
    }

    @Test(timeout = TIMEOUT)
    public void testAddStringsFront() {
        assertEquals(0, list.size());

        Object[] temp = new Object[ArrayListInterface.INITIAL_CAPACITY];
        temp[0]="2a";
        temp[1]="1a";
        temp[2]="0a";
        list.addToFront("0a");
        list.addToFront("1a");
        list.addToFront("2a");
        assertArrayEquals(temp, list.getBackingArray());
        list.addToFront("3a");
        list.addToFront("4a"); //4a 3a 2a 1a 0a

        assertEquals(5, list.size());

        Object[] expected = new Object[ArrayListInterface.INITIAL_CAPACITY];
        expected[0] = "4a";
        expected[1] = "3a";
        expected[2] = "2a";
        expected[3] = "1a";
        expected[4] = "0a";
        assertArrayEquals(expected, list.getBackingArray());
    }

    @Test(timeout = TIMEOUT)
    public void testAddStringsFrontOverSize() {
        assertEquals(0, list.size());

        String testStr = "";
        for(int i = 0; i<14; i++){
            testStr = i+"a";
            list.addToFront(testStr);
            testStr = "";
        }

        assertEquals(14, list.size());

        Object[] expected = new Object[22];
        int count = 0;
        for(int i = 13; i>=0; i--){
            testStr = count+"a";
            expected[i] = testStr;
            count++;
            testStr = "";
        }
        assertArrayEquals(expected, list.getBackingArray());
    }

    @Test(timeout = TIMEOUT)
    public void testAddStringsBack() {
        assertEquals(0, list.size());

        Object[] temp = new Object[ArrayListInterface.INITIAL_CAPACITY];
        temp[0]="0a";
        list.addToBack("0a");
        assertArrayEquals(temp, list.getBackingArray());
        list.addToBack("1a");
        list.addToBack("2a");
        list.addToBack("3a");
        list.addToBack("4a"); //4a 3a 2a 1a 0a

        assertEquals(5, list.size());

        Object[] expected = new Object[ArrayListInterface.INITIAL_CAPACITY];
        expected[4] = "4a";
        expected[3] = "3a";
        expected[2] = "2a";
        expected[1] = "1a";
        expected[0] = "0a";
        assertArrayEquals(expected, list.getBackingArray());
    }

    @Test(timeout = TIMEOUT)
    public void testAddStringsBackOverSize() {
        assertEquals(0, list.size());

        String testStr = "";
        for(int i = 0; i<14; i++){
            testStr = i+"a";
            list.addToBack(testStr);
            testStr = "";
        }

        assertEquals(14, list.size());

        Object[] expected = new Object[22];
        int count = 0;
        for(int i = 0; i<14; i++){
            testStr = count+"a";
            expected[i] = testStr;
            count++;
            testStr = "";
        }
        assertArrayEquals(expected, list.getBackingArray());
    }

    @Test(timeout = TIMEOUT)
    public void testRemoveStrings() {
        assertEquals(0, list.size());

        list.addAtIndex(0, "0a");
        list.addAtIndex(1, "1a");
        list.addAtIndex(2, "2a");
        list.addAtIndex(3, "3a");
        list.addAtIndex(4, "4a");
        list.addAtIndex(5, "5a"); //0a 1a 2a 3a 4a 5a

        assertEquals(6, list.size());

        assertEquals("2a", list.removeAtIndex(2)); //0a 1a 3a 4a 5a

        assertEquals(5, list.size());
        Object[] expected = new Object[ArrayListInterface.INITIAL_CAPACITY];
        expected[0] = "0a";
        expected[1] = "1a";
        expected[2] = "3a";
        expected[3] = "4a";
        expected[4] = "5a";
        assertArrayEquals(expected, list.getBackingArray());
    }

    @Test(timeout = TIMEOUT)
    public void testRemoveStringsAtCapacity() {
        assertEquals(0, list.size());

        String testStr = "";
        for(int i=0; i<11; i++){
            testStr=i+"a";
            list.addAtIndex(i, testStr);
        }

        assertEquals(11, list.size());

        assertEquals("2a", list.removeAtIndex(2)); //0a 1a 3a 4a 5a

        assertEquals(10, list.size());
        Object[] expected = new Object[ArrayListInterface.INITIAL_CAPACITY];
        for(int i=0; i<11; i++){
            if(i<2){
                testStr=i+"a";
                expected[i] = testStr;
            }
            else if(i>2){
                testStr=i+"a";
                expected[i-1] = testStr;
            }

        }
        assertArrayEquals(expected, list.getBackingArray());
    }

    @Test(timeout = TIMEOUT)
    public void testRemoveStringsFront() {
        assertEquals(0, list.size());

        list.addAtIndex(0, "0a");
        list.addAtIndex(1, "1a");
        list.addAtIndex(2, "2a");
        list.addAtIndex(3, "3a");
        list.addAtIndex(4, "4a");
        list.addAtIndex(5, "5a"); //0a 1a 2a 3a 4a 5a

        assertEquals(6, list.size());

        assertEquals("0a", list.removeFromFront()); //0a 1a 3a 4a 5a

        assertEquals(5, list.size());
        Object[] expected = new Object[ArrayListInterface.INITIAL_CAPACITY];
        expected[0] = "1a";
        expected[1] = "2a";
        expected[2] = "3a";
        expected[3] = "4a";
        expected[4] = "5a";
        assertArrayEquals(expected, list.getBackingArray());
    }

    @Test(timeout = TIMEOUT)
    public void testRemoveStringsFrontAtCapacity() {
        assertEquals(0, list.size());

        String testStr = "";
        for(int i=0; i<11; i++){
            testStr=i+"a";
            list.addAtIndex(i, testStr);
        }

        assertEquals(11, list.size());

        assertEquals("0a", list.removeFromFront()); //0a 1a 3a 4a 5a

        assertEquals(10, list.size());
        Object[] expected = new Object[ArrayListInterface.INITIAL_CAPACITY];
        for(int i=0; i<11; i++){
            if(i!=0){
                testStr=i+"a";
                expected[i-1] = testStr;
            }
        }
        assertArrayEquals(expected, list.getBackingArray());
    }

    @Test(timeout = TIMEOUT)
    public void testRemoveStringsFrontOnEmpty() {
        assertEquals(0, list.size());


        assertEquals(0, list.size());

        assertEquals(null, list.removeFromFront()); //0a 1a 3a 4a 5a

        assertEquals(0, list.size());
        Object[] expected = new Object[ArrayListInterface.INITIAL_CAPACITY];

        assertArrayEquals(expected, list.getBackingArray());
    }

    @Test(timeout = TIMEOUT)
    public void testRemoveStringsBack() {
        assertEquals(0, list.size());

        list.addAtIndex(0, "0a");
        list.addAtIndex(1, "1a");
        list.addAtIndex(2, "2a");
        list.addAtIndex(3, "3a");
        list.addAtIndex(4, "4a");
        list.addAtIndex(5, "5a"); //0a 1a 2a 3a 4a 5a

        assertEquals(6, list.size());

        assertEquals("5a", list.removeFromBack()); //0a 1a 3a 4a 5a

        assertEquals(5, list.size());
        Object[] expected = new Object[ArrayListInterface.INITIAL_CAPACITY];

        String testStr="";
        for(int i=0; i<5; i++){
            testStr=i+"a";
            expected[i]=testStr;
        }

        assertArrayEquals(expected, list.getBackingArray());
    }

    @Test(timeout = TIMEOUT)
    public void testRemoveStringsBackAtCapacity() {
        assertEquals(0, list.size());

        String testStr = "";
        for(int i=0; i<11; i++){
            testStr=i+"a";
            list.addAtIndex(i, testStr);
        }

        assertEquals(11, list.size());

        assertEquals("10a", list.removeFromBack()); //0a 1a 3a 4a 5a

        assertEquals(10, list.size());
        Object[] expected = new Object[ArrayListInterface.INITIAL_CAPACITY];
        for(int i=0; i<10; i++){
            testStr=i+"a";
            expected[i] = testStr;

        }
        assertArrayEquals(expected, list.getBackingArray());
    }

    @Test(timeout = TIMEOUT)
    public void testRemoveStringsBackOnEmpty() {
        assertEquals(0, list.size());

        assertEquals(null, list.removeFromBack()); //0a 1a 3a 4a 5a

        assertEquals(0, list.size());
        Object[] expected = new Object[ArrayListInterface.INITIAL_CAPACITY];

        assertArrayEquals(expected, list.getBackingArray());
    }

    @Test(timeout = TIMEOUT)
    public void testClear() {
        assertEquals(0, list.size());

        list.addToFront("hello");
        list.removeFromFront();

        assertEquals(true, list.isEmpty());
    }



    @Test(timeout = TIMEOUT)
    public void testGetGeneral() {
        list.addAtIndex(0, "0a");
        list.addAtIndex(1, "1a");
        list.addAtIndex(2, "2a");
        list.addAtIndex(3, "3a");
        list.addAtIndex(4, "4a"); //0a 1a 2a 3a 4a

        assertEquals("0a", list.get(0));
        assertEquals("1a", list.get(1));
        assertEquals("2a", list.get(2));
        assertEquals("3a", list.get(3));
        assertEquals("4a", list.get(4));
    }
}