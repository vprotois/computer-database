package model;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import model.builders.PagesBuilder;

public class PagesBuilderTest {

	List<String> list;
	
	@BeforeAll
	public void setup() {
		list = new ArrayList<String>();
		list.add("aaa");list.add("bbb");list.add("ccc");
	}
	
	
	@Test
	public void testBuild() {		
		Pages<String> page1 = new Pages<String>(list,1,5); 
		Pages<String> page2 = new PagesBuilder<String>()
								.withData(list)
								.withIndex(1)
								.withSize(5)
								.build();
		assertTrue(page1.equals(page2));
	}
	
	@Test
	public void testDiffIndex() {
		Pages<String> page1 = new Pages<String>(list,0,50); 
		Pages<String> page2 = new PagesBuilder<String>()
								.withData(list)
								.withIndex(1)
								.withSize(50)
								.build();
		assertTrue(page1.equals(page2));
	}
	
	@Test
	public void testIndexOutofBound() {
		Pages<String> page1 = new Pages<String>(list,0,50);
		Pages<String> page2 = new PagesBuilder<String>()
								.withData(list)
								.withIndex(10)
								.withSize(50)
								.build();
		assertTrue(page1.equals(page2));
	}
	
	public void testNoData() {
		Pages<String> page = new PagesBuilder<String>()
									.build();
		assertNull(page);
	}

}
