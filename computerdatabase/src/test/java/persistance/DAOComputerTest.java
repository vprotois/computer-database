package persistance;

import java.util.List;

import org.junit.Test;

import junit.framework.TestCase;
import model.Computer;
import model.Page;

public class DAOComputerTest extends TestCase {

	
	@Test
	public void createComputer() {
		fail("Not implemented");
	}
	
	@Test
	public void deleteComputer() {
		fail("Not implemented");
	}
	
	@Test
	public void testCompDetails() {
		DAOComputer dao = DAOFactory.createDAOcomputer();
		Computer c1 = new Computer(3L,"name",null,null,null,2L);
		Computer c2 = dao.getCompDetails(3L);
		assertEquals(c1,c2);
	}
	
	@Test
	public void testListComputers() {
		DAOComputer dao = DAOFactory.createDAOcomputer();
		List<Computer> list = dao.listComputers();
		assertNotNull(list);
	}
	
	@Test
	public void pageListComputer() {
		DAOComputer dao = DAOFactory.createDAOcomputer();
		Page<Computer> p = dao.pageListComputer();
		assertNotNull(p);
	}
	
	@Test
	public void updateComputer() {
		fail("Not implemented");
	}
	
}
