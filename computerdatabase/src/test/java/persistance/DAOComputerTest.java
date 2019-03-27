package persistance;

import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;

import exception.CreateComputerError;
import exception.UpdateComputerError;
import junit.framework.TestCase;
import model.Computer;

@ExtendWith(MockitoExtension.class)
public class DAOComputerTest extends TestCase {

	
	
	
	@Test
	public void testcreateUpdateDelete() throws CreateComputerError, UpdateComputerError {
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
	
	public void testFailedCreate() {
		DAOComputer dao = DAOFactory.createDAOcomputer();
		//this id is already taken in the base
		Computer c = new Computer(14L,"neg",null,null,null,2L);
		try {
			dao.createComputer(c);
		} catch (Exception e) {
			assertEquals(e.getClass(),CreateComputerError.class);
		}
	}
	
//	public void testFailedUpdate() {
//		DAOComputer dao = DAOFactory.createDAOcomputer();
//		//this computer isn't in the base
//		Computer c = new Computer(-3L,"neg",null,null,null,2L);
//		assertFalse(dao.updateComputer(c));
//	}
	
//	public void testFailedDelete() {
//		DAOComputer dao = DAOFactory.createDAOcomputer();
//		//this computer isn't in the base
//		assertFalse(dao.deleteComputer(-3L));
//	}
	
	
	@Test
	public void testCompDetails() {
		DAOComputer dao = DAOFactory.createDAOcomputer();
		Computer c1 = new Computer(3L,"name",null,null,null,2L);
		Optional<Computer> c2 = dao.getCompDetails(3L);
		if(!c2.isPresent()) {
			fail("tested Computer isn't in the base");
		}
		assertEquals(c1,c2.get());
	}
	
	@Test
	public void testListComputers() {
		DAOComputer dao = DAOFactory.createDAOcomputer();
		Optional<List<Computer>> list = dao.listComputers();
		assertNotNull(list.get());
	}
	

	
	
}
