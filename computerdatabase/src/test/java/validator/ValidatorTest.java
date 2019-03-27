package validator;

import static org.junit.jupiter.api.Assertions.*;

import java.sql.Timestamp;

import org.junit.jupiter.api.Test;

import exception.ValidatorException;
import model.Computer;
import model.builders.ComputerBuilder;

class ValidatorTest {

	@Test
	public void NameOnlyTest() {
		Computer testedComputer = new ComputerBuilder()
									.withName("name")
									.build();
		try {
			Validator.computerValidator(testedComputer);
		} catch (ValidatorException e) {
			fail(e.getMessage());
		}
	}
	
	@Test
	public void NoName() {
		Computer testedComputer = new ComputerBuilder()
										.build();
		try {
			Validator.computerValidator(testedComputer);
		} catch (ValidatorException e) {
			assertEquals(Validator.ERROR_COMPUTER_NAME,e.getMessage());
		}
	}
	
	@Test
	public void InvalidName() {
		Computer testedComputer = new ComputerBuilder()
										.withName("")
										.build();
		try {
			Validator.computerValidator(testedComputer);
		} catch (ValidatorException e) {
			assertEquals(Validator.ERROR_COMPUTER_NAME,e.getMessage());
		}
	}

	@Test
	public void IntroducedTest() {
		Computer testedComputer = new ComputerBuilder()
									.withName("name")
									.withIntroduced(new Timestamp(4000))
									.build();
		try {
			Validator.computerValidator(testedComputer);
		} catch (ValidatorException e) {
			fail(e.getMessage());
		}
	}
	
	@Test
	public void IntroducedDiscontinuedTest() {
		Computer testedComputer = new ComputerBuilder()
									.withName("name")
									.withIntroduced(new Timestamp(4000))
									.withDiscontinued(new Timestamp(8000))
									.build();
		try {
			Validator.computerValidator(testedComputer);
		} catch (ValidatorException e) {
			fail(e.getMessage());
		}
	}
	
	@Test
	public void InvalidDiscontinuedTest() {
		Computer testedComputer = new ComputerBuilder()
									.withName("name")
									.withIntroduced(new Timestamp(4000))
									.withDiscontinued(new Timestamp(2000))
									.build();
		try {
			Validator.computerValidator(testedComputer);
		} catch (ValidatorException e) {
			assertEquals(Validator.ERROR_COMPUTER_DICS_INFERIOR,e.getMessage());
		}
	}
	
	@Test
	public void NoIntroducedWithDiscontinuedTest() {
		Computer testedComputer = new ComputerBuilder()
									.withName("name")
									.withDiscontinued(new Timestamp(2000))
									.build();
		try {
			Validator.computerValidator(testedComputer);
		} catch (ValidatorException e) {
			assertEquals(Validator.ERROR_COMPUTER_DISC_WITHOUT_INTR,e.getMessage());
		}
	}
	
}
