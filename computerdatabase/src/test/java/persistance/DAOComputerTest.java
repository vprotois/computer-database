package persistance;

import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.support.GenericApplicationContext;

import app.AppConfig;
import exception.CreateComputerError;
import exception.UpdateComputerError;
import junit.framework.TestCase;
import model.Company;
import model.Computer;
import model.QComputer;

@ExtendWith(MockitoExtension.class)
public class DAOComputerTest extends TestCase {
	
	private DAOComputer dao;
	GenericApplicationContext context;
	
	@BeforeAll
	public void setUp() throws UpdateComputerError {
		context = new AnnotationConfigApplicationContext(AppConfig.class);
		dao = context.getBean(DAOComputer.class);
		
	}
	
	@AfterAll
	public void end() throws UpdateComputerError {
		context.close();
	}
	
	
	@Test
	public void testcreateUpdateDelete() throws CreateComputerError, UpdateComputerError {
		Computer c = new Computer(-6L,"neg",null,null,null,null);
		dao.createComputer(c);
		//assertEquals(Optional.of(c).toString(),dao.getCompDetails(-6L).toString());
		c = new Computer(-6L,"neg",null,null,null,null);
		dao.updateComputer(c);
		//assertEquals(Optional.of(c).toString(),dao.getCompDetails(-6L).toString());
		dao.deleteComputer(-6L);
		//assertEquals(Optional.empty(),dao.getCompDetails(-6L));
	}
	
	public void testFailedCreate() {
		Computer c = new Computer(14L,"neg",null,null,null,null);
		try {
			dao.createComputer(c);
		} catch (Exception e) {
			assertEquals(CreateComputerError.class,e.getClass());
		}
	}
	
	
	
	@Test
	public void testCompDetails() {
		Computer c1 = new Computer(14L,"CM-2",new Company(2L,"name"),null,null,null);
		Optional<Computer> c2 = dao.getCompDetails(14L);
		if(!c2.isPresent()) {
			fail("tested Computer isn't in the base");
		}
		assertEquals(c1.toString(),c2.get().toString());
	}
	
	@Test
	public void testListComputers() {
		Optional<List<Computer>> list = dao.listComputers(QComputer.computer.id.desc());
		assertNotNull(list.get());
	}
	

	
	
}
