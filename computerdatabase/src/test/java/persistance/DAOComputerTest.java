package persistance;

import java.util.List;

import org.junit.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;

import junit.framework.TestCase;
import model.Computer;
import model.Pages;

@ExtendWith(MockitoExtension.class)
public class DAOComputerTest extends TestCase {

	
	
	
	@Test
	public void testcreateUpdateDelete() {
		DAOComputer dao = DAOFactory.createDAOcomputer();
		Computer c = new Computer(-3L,"neg",null,null,null,2L);
		dao.createComputer(c);
		assertEquals(c,dao.getCompDetails(-3L));
		c = new Computer(-3L,"neg",null,null,null,2L);
		dao.updateComputer(c);
		assertEquals(c,dao.getCompDetails(-3L));
		dao.deleteComputer(-3L);
		assertNull(dao.getCompDetails(-3L));
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
		Pages<Computer> p = dao.pageListComputer();
		assertNotNull(p);
	}
	
	
	
}
