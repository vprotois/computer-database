package model;

import static org.junit.Assert.*;

import java.util.ArrayList;
import java.util.List;

import org.junit.Before;
import org.junit.Test;

public class PagesTest {
	
	List<String> list1;
	List<String> list2;
	
	@Before
	public void setup() {
		list1 = new ArrayList<String>();
		list2 = new ArrayList<String>();
		list1.add("aaa");list1.add("bbb");list1.add("ccc");
		list2.add("aaa");
	}
	
	
	@Test
	public void testSmallData() {
		Pages<String> page = new Pages<String>(list1,0,5);
		assertTrue(page.getData().equals(list1));
	}
	
	@Test
	public void testEquals() {
		Pages<String> page = new Pages<String>(list1,0,5);
		Pages<String> page2 = new Pages<String>(list2,0,5);
		assertTrue(page.equals(page));
		assertFalse(page.equals(page2));
	}

	@Test
	public void testEqualsSize() {
		Pages<String> page1 = new Pages<String>(list1,0,5);
		Pages<String> page2 = new Pages<String>(list1,0,10);
		assertFalse(page1.equals(page2));
	}
	
	@Test
	public void testTurnPages() {
		Pages<String> page = new Pages<String>(list1,0,2);
		page.nextPage();
		assertEquals(new Integer(2),page.getIndex());
		page.nextPage();
		assertEquals(new Integer(2),page.getIndex());
		page.previousPage();
		page.previousPage();
		page.previousPage();
		assertEquals(new Integer(0),page.getIndex());
	}
	
	@Test
	public void testIndex() {
		Pages<String> page1 = new Pages<String>(list1,0,1);
		assertEquals(page1.getData(),list2);
	}
	
	
}
