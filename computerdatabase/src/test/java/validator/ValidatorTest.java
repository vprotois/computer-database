package validator;

import static org.junit.jupiter.api.Assertions.*;

import java.sql.Timestamp;

import org.junit.jupiter.api.Test;

import model.Computer;
import model.builders.ComputerBuilder;

class ValidatorTest {

	@Test
	public void NameOnlyTest() {
		Computer testedComputer = new ComputerBuilder()
									.withName("name")
									.build();
		assertTrue(Validator.computerValidator(testedComputer));
	}
	
	@Test
	public void NoName() {
		Computer testedComputer = new ComputerBuilder()
										.build();
		assertFalse(Validator.computerValidator(testedComputer));
	}
	
	@Test
	public void InvalidName() {
		Computer testedComputer = new ComputerBuilder()
										.withName("")
										.build();
		assertFalse(Validator.computerValidator(testedComputer));
	}

	@Test
	public void IntroducedTest() {
		Computer testedComputer = new ComputerBuilder()
									.withName("name")
									.withIntroduced(new Timestamp(4000))
									.build();
		assertTrue(Validator.computerValidator(testedComputer));
	}
	
	@Test
	public void IntroducedDiscontinuedTest() {
		Computer testedComputer = new ComputerBuilder()
									.withName("name")
									.withIntroduced(new Timestamp(4000))
									.withDiscontinued(new Timestamp(8000))
									.build();
		assertTrue(Validator.computerValidator(testedComputer));
	}
	
	@Test
	public void InvalidDiscontinuedTest() {
		Computer testedComputer = new ComputerBuilder()
									.withName("name")
									.withIntroduced(new Timestamp(4000))
									.withDiscontinued(new Timestamp(2000))
									.build();
		assertFalse(Validator.computerValidator(testedComputer));
	}
	
	@Test
	public void NoIntroducedWithDiscontinuedTest() {
		Computer testedComputer = new ComputerBuilder()
									.withName("name")
									.withDiscontinued(new Timestamp(2000))
									.build();
		assertFalse(Validator.computerValidator(testedComputer));
	}
	
}
