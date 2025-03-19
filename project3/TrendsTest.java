import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;
import org.junit.Before;
import org.junit.Test;

/**
 * Testing students implmentation using JUnit Ver 4
 * @author amjadm
 */
public class TrendsTest {

	Trends test;

	@Before
	public void initiate() {
		test = new StudentTrends();
	}

	/**
	 * Testing construction of the object
	 */
	@Test
	public void dtest01() {
		assertEquals(test.getCount("bogus"), 0);
	}
	
	@Test
	public void test02(){
		assertEquals(test.numEntries(), 0);
	}
	
	@Test 
	public void test03() {
		test.increaseCount("one", 1);
		assertEquals(test.getCount("zero"), 0);
		assertEquals(test.getCount("one"), 1);
	}
	
	@Test 
	public void test04() {
		test.increaseCount("one", 2);
		assertEquals(test.getCount("zero"), 0);
		assertEquals(test.getCount("one"), 2);
	}
	
	@Test
	public void test05() {
		test.increaseCount("one", 1);
		test.increaseCount("two", 2);
		assertEquals(test.getCount("one"), 1);
		assertEquals(test.getCount("two"), 2);
	}
	
	@Test
	public void test06() {
		test.increaseCount("one", 1);
		test.increaseCount("two", 1);
		test.increaseCount("one", 1);
		assertEquals(test.getCount("one"), 2);
		assertEquals(test.getCount("two"), 1);
	}
	
	@Test
	public void test07() {
		test.increaseCount("one", 1);
		test.increaseCount("two", 2);
		test.increaseCount("three",  3);
		assertEquals(test.getNthPopular(0), "three");
		assertEquals(test.getNthPopular(1), "two");
		assertEquals(test.getNthPopular(2), "one");
	}
	
	@Test
	public void test08() {
		test.increaseCount("one", 3);
		test.increaseCount("two", 2);
		test.increaseCount("three",  1);
		assertEquals(test.getNthPopular(2), "three");
		assertEquals(test.getNthPopular(1), "two");
		assertEquals(test.getNthPopular(0), "one");
	}
	
	@Test
	public void test09() {
		test.increaseCount("one", 1);
		test.increaseCount("two", 3);
		test.increaseCount("three",  2);
		assertEquals(test.getNthPopular(1), "three");
		assertEquals(test.getNthPopular(0), "two");
		assertEquals(test.getNthPopular(2), "one");
	}
	
	@Test
	public void test10() {
		test.increaseCount("one", 1);
		test.increaseCount("two", 1);
		test.increaseCount("three",  1);
		test.increaseCount("two", 1);
		test.increaseCount("three",  1);
		test.increaseCount("two", 1);
		assertEquals(test.getNthPopular(1), "three");
		assertEquals(test.getNthPopular(0), "two");
		assertEquals(test.getNthPopular(2), "one");
	}
	
	@Test
	public void test11() {
		test.increaseCount("BBB", 1);
		test.increaseCount("AAA",  1);
		assertTrue(test.getNthPopular(1).equals("BBB"));
		assertEquals(test.getNthPopular(0), "AAA");
		assertEquals(test.getNthPopular(1), "BBB");
	}
	
	@Test
	public void test12() {
		test.increaseCount("AAA", 1);
		test.increaseCount("BBB", 1);
		assertTrue(test.getNthPopular(1).equals("BBB"));
		assertEquals(test.getNthPopular(0), "AAA");
		assertEquals(test.getNthPopular(1), "BBB");
	}
	
	@Test
	public void test13() {
		test.increaseCount("AAA", 1);
		test.increaseCount("BBB", 2);
		test.increaseCount("CCC", 1);
		assertTrue(test.getNthPopular(1).equals("AAA"));
		assertEquals(test.getNthPopular(0), "BBB");
		assertEquals(test.getNthPopular(1), "AAA");
		assertEquals(test.getNthPopular(2), "CCC");
	}
	
	@Test
	public void test14() {
		Trends test = new StudentTrends();
		test.increaseCount("AAA", 1);
		test.increaseCount("BBB", 1);
		test.increaseCount("CCC", 1);
		test.increaseCount("AAA", 1);
		test.increaseCount("CCC", 1);
		test.increaseCount("AAA", 1);
		test.increaseCount("CCC", 1);
		assertTrue(test.getNthPopular(1).equals("CCC"));
		assertEquals(test.getNthPopular(0), "AAA");
		assertEquals(test.getNthPopular(1), "CCC");
		assertEquals(test.getNthPopular(2), "BBB");
	}
	
	@Test
	public void test15() {
		test.increaseCount("AAA", 1);
		test.increaseCount("BBB", 1);
		assertTrue(test.numEntries() == 2);
	}
	
	@Test
	public void test16() {
		test.increaseCount("AAA", 2);
		test.increaseCount("BBB", 2);
		assertTrue(test.numEntries() == 2);
	}
	
	@Test
	public void test17() {
		test.increaseCount("AAA", 1);
		test.increaseCount("BBB", 1);
		test.increaseCount("AAA", 1);
		test.increaseCount("BBB", 1);
		test.increaseCount("AAA", 1);
		test.increaseCount("BBB", 1);
		assertTrue(test.numEntries() == 2);
	}
    
}
