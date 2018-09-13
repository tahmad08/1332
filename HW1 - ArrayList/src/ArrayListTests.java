import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertArrayEquals;

public class ArrayListTests {

    private ArrayListInterface<String> list;

    public static final int TIMEOUT = 200;

    @Before
    public void setUp() {
        list = new ArrayList<String>();
    }

    @Test(timeout = TIMEOUT)
    public void addAtIndex() {
        assertEquals(0, list.size());

        list.addToFront("!");
        list.addToFront("!");
        list.addToFront("!");

        assertEquals(3, list.size());

        list.addAtIndex(0,"S");
        list.addAtIndex(1, "O");
        list.addAtIndex(2,"P");
        list.addAtIndex(3, "H");
        list.addAtIndex(4,"I");
        list.addAtIndex(5,"E");

        Object[] name = new Object[ArrayListInterface.INITIAL_CAPACITY];

        name[0] = "S";
        name[1] = "O";
        name[2] = "P";
        name[3] = "H";
        name[4] = "I";
        name[5] = "E";
        name[6] = "!";
        name[7] = "!";
        name[8] = "!";

        assertArrayEquals(name, list.getBackingArray());

        assertFalse(list.isEmpty());
        list.clear();
        assertEquals(0,list.size());

        assertTrue(list.isEmpty());

    }

    @Test(timeout = TIMEOUT)
    public void addToFront() {
        assertEquals(0, list.size());

        list.addToFront("!");
        list.addToFront("E");
        list.addToFront("I");
        list.addToFront("H");
        list.addToFront("P");
        list.addToFront("O");
        list.addToFront("S");

        assertEquals(7, list.size());

        Object[] name = new Object[ArrayListInterface.INITIAL_CAPACITY];

        name[0] = "S";
        name[1] = "O";
        name[2] = "P";
        name[3] = "H";
        name[4] = "I";
        name[5] = "E";
        name[6] = "!";

        assertArrayEquals(name, list.getBackingArray());

        assertFalse(list.isEmpty());
        list.clear();
        assertEquals(0,list.size());

        assertTrue(list.isEmpty());

    }

    @Test(timeout = TIMEOUT)
    public void addToBack() {
        assertEquals(0, list.size());

        list.addToBack("S");
        list.addToBack("O");
        list.addToBack("P");
        list.addToBack("H");
        list.addToBack("I");
        list.addToBack("E");
        list.addToBack("!");

        assertEquals(7, list.size());

        Object[] name = new Object[ArrayListInterface.INITIAL_CAPACITY];

        name[0] = "S";
        name[1] = "O";
        name[2] = "P";
        name[3] = "H";
        name[4] = "I";
        name[5] = "E";
        name[6] = "!";

        assertArrayEquals(name, list.getBackingArray());

        assertFalse(list.isEmpty());
        list.clear();
        assertEquals(0,list.size());

        assertTrue(list.isEmpty());
    }

    @Test(timeout = TIMEOUT)
    public void removeAtIndex() {
        assertEquals(0, list.size());

        list.addToFront("!");
        list.addToFront("E");
        list.addToFront("I");
        list.addToFront("H");
        list.addToFront("*");
        list.addToFront("P");
        list.addToFront("O");
        list.addToFront("S");

        assertEquals(8, list.size());

        assertEquals("*",list.removeAtIndex(3));
        assertEquals(7, list.size());

        Object[] name = new Object[ArrayListInterface.INITIAL_CAPACITY];

        name[0] = "S";
        name[1] = "O";
        name[2] = "P";
        name[3] = "H";
        name[4] = "I";
        name[5] = "E";
        name[6] = "!";

        assertArrayEquals(name, list.getBackingArray());

        assertFalse(list.isEmpty());
        list.clear();
        assertEquals(0,list.size());

        assertTrue(list.isEmpty());
    }

    @Test(timeout = TIMEOUT)
    public void removeFromFront() {
        assertEquals(0, list.size());

        list.addToFront("!");
        list.addToFront("E");
        list.addToFront("I");
        list.addToFront("H");
        list.addToFront("P");
        list.addToFront("O");
        list.addToFront("S");
        list.addToFront("*");

        assertEquals(8, list.size());

        assertEquals("*",list.removeFromFront());
        assertEquals(7, list.size());

        Object[] name = new Object[ArrayListInterface.INITIAL_CAPACITY];

        name[0] = "S";
        name[1] = "O";
        name[2] = "P";
        name[3] = "H";
        name[4] = "I";
        name[5] = "E";
        name[6] = "!";

        assertArrayEquals(name, list.getBackingArray());

        assertFalse(list.isEmpty());
        list.clear();
        assertEquals(0,list.size());

        assertTrue(list.isEmpty());

        assertEquals(null, list.removeFromFront());
    }

    @Test(timeout = TIMEOUT)
    public void removeFromBack() {
        assertEquals(0, list.size());

        list.addToFront("*");
        list.addToFront("!");
        list.addToFront("E");
        list.addToFront("I");
        list.addToFront("H");
        list.addToFront("P");
        list.addToFront("O");
        list.addToFront("S");

        assertEquals(8, list.size());

        assertEquals("*",list.removeFromBack());
        assertEquals(7, list.size());

        Object[] name = new Object[ArrayListInterface.INITIAL_CAPACITY];

        name[0] = "S";
        name[1] = "O";
        name[2] = "P";
        name[3] = "H";
        name[4] = "I";
        name[5] = "E";
        name[6] = "!";

        assertArrayEquals(name, list.getBackingArray());

        assertFalse(list.isEmpty());
        list.clear();
        assertEquals(0,list.size());

        assertTrue(list.isEmpty());

        assertEquals(null, list.removeFromBack());
    }

    @Test(timeout = TIMEOUT, expected = IndexOutOfBoundsException.class)
    public void addIndexNegative() {
        assertEquals(0, list.size());

        list.addToFront("!");
        list.addToFront("!");
        list.addToFront("!");

        assertEquals(3, list.size());

        list.addAtIndex(-1,"S");
    }

    @Test(timeout = TIMEOUT, expected = IndexOutOfBoundsException.class)
    public void addIndexLarge() {
        assertEquals(0, list.size());

        list.addToFront("!");
        list.addToFront("!");
        list.addToFront("!");

        assertEquals(3, list.size());

        list.addAtIndex(4,"S");
    }

    @Test(timeout = TIMEOUT, expected = IllegalArgumentException.class)
    public void addIndexIllegal() {
        assertEquals(0, list.size());

        list.addToFront("!");
        list.addToFront("!");
        list.addToFront("!");

        assertEquals(3, list.size());

        list.addAtIndex(2,null);
    }

    @Test(timeout = TIMEOUT, expected = IllegalArgumentException.class)
    public void addFrontIllegal() {
        assertEquals(0, list.size());

        list.addToFront("!");
        list.addToFront("!");
        list.addToFront("!");

        assertEquals(3, list.size());

        list.addToFront(null);
    }

    @Test(timeout = TIMEOUT, expected = IllegalArgumentException.class)
    public void addBackIllegal() {
        assertEquals(0, list.size());

        list.addToFront("!");
        list.addToFront("!");
        list.addToFront("!");

        assertEquals(3, list.size());

        list.addToBack(null);
    }

    @Test(timeout = TIMEOUT, expected = IndexOutOfBoundsException.class)
    public void removeIndexNegative() {
        assertEquals(0, list.size());

        list.addToFront("!");
        list.addToFront("!");
        list.addToFront("!");

        assertEquals(3, list.size());

        list.removeAtIndex(-1);
    }

    @Test(timeout = TIMEOUT, expected = IndexOutOfBoundsException.class)
    public void removeIndexLarge() {
        assertEquals(0, list.size());

        list.addToFront("!");
        list.addToFront("!");
        list.addToFront("!");

        assertEquals(3, list.size());

        list.removeAtIndex(3);
    }

    @Test(timeout = TIMEOUT, expected = IndexOutOfBoundsException.class)
    public void getNegative() {
        list.addToFront("!");
        list.addToFront("!");
        list.addToFront("!");

        assertEquals(3, list.size());

        list.get(-1);
    }

    @Test(timeout = TIMEOUT, expected = IndexOutOfBoundsException.class)
    public void getLarge() {
        list.addToFront("!");
        list.addToFront("!");
        list.addToFront("!");

        assertEquals(3, list.size());

        list.get(3);
    }

}