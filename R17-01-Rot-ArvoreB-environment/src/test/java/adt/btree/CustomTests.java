package adt.btree;
 
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;
 
import java.util.Arrays;
import java.util.LinkedList;
 
import org.junit.Before;
import org.junit.Test;
 
public class CustomTests {
 
    protected BTree<Integer> tree1;
 
    private void insertSomeNodes() {
        for (int i = 0; i < 20; i++) {
            tree1.insert(i);
        }
    }
 
    // Insert nodes used on preOrder test
    private void insertCustomNodes() {
        tree1 = new BTreeImpl<Integer>(5);
        Integer[] arr = new Integer[] { 1, 8, 14, 22, 26, 30, 34, 16,
                20, 18, 19 };
        for (Integer i : arr) {
            tree1.insert(i);
        }
    }
 
    @Before
    public void setUp() throws Exception {
        tree1 = new BTreeImpl<Integer>(4);
    }
 
    @Test
    public void testIsEmpty() {
        assertTrue(tree1.isEmpty());
        insertSomeNodes();
        assertTrue(!tree1.isEmpty());
    }
 
    @Test
    public void testHeight() {
        assertEquals(-1, tree1.height());
        tree1.insert(2);
        assertEquals(0, tree1.height());
 
        insertCustomNodes();
        try {
            assertEquals(2, tree1.height());
        } catch (AssertionError e) {
            assertEquals(3, tree1.height());
        }
 
    }
 
    @Test
    public void testSearch() {
        // Test search of an element that isn't in tree
        LinkedList<Integer> expectedElements = null;
        assertEquals(expectedElements, tree1.search(1).node);
        assertEquals(0, tree1.search(1).position);
       
        insertCustomNodes();
 
        // Test search of element 1
        try {
            expectedElements = new LinkedList<Integer>();
            expectedElements.add(1);
            expectedElements.add(8);
 
            assertEquals(expectedElements,
                    tree1.search(1).node.getElements());
            assertEquals(0, tree1.search(1).position);
           
        } catch (AssertionError e) {
            expectedElements = new LinkedList<Integer>();
            expectedElements.add(1);
 
            assertEquals(expectedElements,
                    tree1.search(1).node.getElements());
            assertEquals(0, tree1.search(1).position);
        }
       
        // Test search of element 34
        try {
            expectedElements = new LinkedList<Integer>();
            expectedElements.add(34);
           
            assertEquals(expectedElements,
                    tree1.search(34).node.getElements());
            assertEquals(0, tree1.search(34).position);
           
        } catch (AssertionError e) {
            expectedElements = new LinkedList<Integer>();
            expectedElements.add(26);
            expectedElements.add(30);
            expectedElements.add(34);
 
            assertEquals(expectedElements,
                    tree1.search(34).node.getElements());
            assertEquals(2, tree1.search(34).position);
        }
       
        // Test search of element 18
        try {
            expectedElements = new LinkedList<Integer>();
            expectedElements.add(16);
            expectedElements.add(18);
           
            assertEquals(expectedElements,
                    tree1.search(18).node.getElements());
            assertEquals(1, tree1.search(18).position);
           
        } catch (AssertionError e) {
            expectedElements = new LinkedList<Integer>();
            expectedElements.add(18);
            expectedElements.add(19);
            expectedElements.add(20);
 
            assertEquals(expectedElements,
                    tree1.search(18).node.getElements());
            assertEquals(0, tree1.search(18).position);
        }
 
        // Test search of an element that isn't in tree
        expectedElements = null;
        assertEquals(expectedElements, tree1.search(999).node);
        assertEquals(0, tree1.search(999).position);
 
    }
 
    @Test
    public void testDepthLeftOrder() {
       
        assertEquals("[]", Arrays.toString(tree1.depthLeftOrder()));
       
        tree1.insert(2);
        tree1.insert(4);
        tree1.insert(6);
        tree1.insert(8);
        try {
            assertEquals("[[6], [2, 4], [8]]",
                    Arrays.toString(tree1.depthLeftOrder()));
        } catch (AssertionError e) {
            assertEquals("[[4], [2], [6, 8]]",
                    Arrays.toString(tree1.depthLeftOrder()));
        }
    }
 
    @Test
    public void testSize() {
        assertEquals(0, tree1.size());
        tree1.insert(18);
        assertEquals(1, tree1.size());
    }
 
    // Compares the return of depthLeftOrder() with the expected
    // values for the floor and ceil implementation of median value
    // i.e. floor ~ floor(node_size / 2.0) and
    // ceil ~ ceil(node_size / 2.0)
    private void comparison(String floor, String ceil) {
        try {
            assertEquals(floor,
                    Arrays.toString(tree1.depthLeftOrder()));
        } catch (AssertionError e) {
            assertEquals(ceil,
                    Arrays.toString(tree1.depthLeftOrder()));
        }
    }
 
    @Test
    public void insertionTest() {
        tree1 = new BTreeImpl<Integer>(5);
 
        tree1.insert(1);
        tree1.insert(8);
        tree1.insert(14);
        tree1.insert(22);
 
        comparison("[[8], [1], [14, 22]]", "[[14], [1, 8], [22]]");
 
        tree1.insert(26);
 
        comparison("[[8], [1], [14, 22, 26]]",
                "[[14], [1, 8], [22, 26]]");
 
        tree1.insert(30);
 
        comparison("[[8, 22], [1], [14], [26, 30]]",
                "[[14], [1, 8], [22, 26, 30]]");
 
        tree1.insert(34);
 
        comparison("[[8, 22], [1], [14], [26, 30, 34]]",
                "[[14, 30], [1, 8], [22, 26], [34]]");
 
        tree1.insert(16);
 
        comparison("[[8, 22], [1], [14, 16], [26, 30, 34]]",
                "[[14, 30], [1, 8], [16, 22, 26], [34]]");
 
        tree1.insert(20);
 
        comparison("[[8, 22], [1], [14, 16, 20], [26, 30, 34]]",
                "[[14, 22, 30], [1, 8], [16, 20], [26], [34]]");
 
        tree1.insert(18);
 
        comparison("[[8, 16, 22], [1], [14], [18, 20], [26, 30, 34]]",
                "[[14, 22, 30], [1, 8], [16, 18, 20], [26], [34]]");
 
        tree1.insert(19);
 
        comparison(
                "[[8, 16, 22], [1], [14], [18, 19, 20], [26, 30, 34]]",
                "[[22], [14, 19], [1, 8], [16, 18], [20], [30], [26], [34]]");
    }
 
}