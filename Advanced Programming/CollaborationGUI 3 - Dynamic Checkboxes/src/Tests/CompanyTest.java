package Tests;

import static org.junit.jupiter.api.Assertions.*;

import java.sql.SQLException;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import Data.HashMaps;
import Exceptions.ABNLengthIncorrectException;
import Exceptions.ABNValuesIncorrectException;
import Exceptions.DuplicatePreferenceException;
import Exceptions.IDOutOfRangeException;
import Exceptions.IdAlreadyUsedException;
import Objects.Project.Company;
import Objects.Student.Preferences;

class CompanyTest {
	HashMaps hm;
	@BeforeEach
	void setUp() throws Exception {
	hm = HashMaps.getSingleton();
	}

	@Test
	void testAddPrefix() {
		fail("Not yet implemented");
	}

	@Test
	void testCompanyString() throws ABNValuesIncorrectException, ABNLengthIncorrectException, IdAlreadyUsedException {
		Company comp = new Company("C1","Telly","122334455","telly.com","Telly rd, Mariybinong");
		assertEquals("C1",comp.getID());
	}

	@Test
	void testCompanyInt() throws SQLException, IDOutOfRangeException, ABNValuesIncorrectException, ABNLengthIncorrectException, IdAlreadyUsedException {
		Company comp = new Company(1,"Telly","122334455","telly.com","Telly rd, Mariybinong");
		assertEquals("C1",comp.getID());
	}

	@Test
	void testIdOutOFRange() throws SQLException, IDOutOfRangeException, ABNValuesIncorrectException, ABNLengthIncorrectException {
		assertThrows(IDOutOfRangeException.class,() -> {
			Company comp = new Company(50,"Telly","122334455","telly.com","Telly rd, Mariybinong");
		});
	}
	
	@Test
	void testABNValuesIncorrectException() throws SQLException, IDOutOfRangeException, ABNValuesIncorrectException, ABNLengthIncorrectException {
		assertThrows(ABNValuesIncorrectException.class,() -> {
			Company comp = new Company(1,"Telly","smartsmarty","telly.com","Telly rd, Mariybinong");
		});
	}
	
	@Test
	void testABNLengthIncorrectException() throws SQLException, IDOutOfRangeException, ABNValuesIncorrectException, ABNLengthIncorrectException {
		assertThrows(ABNLengthIncorrectException.class,() -> {
			Company comp = new Company(1,"Telly","12","telly.com","Telly rd, Mariybinong");
		});
	}

	@Test
	void testSetABN() throws SQLException, IDOutOfRangeException, ABNValuesIncorrectException, ABNLengthIncorrectException {
		Company comp = new Company(1,"Telly","12","telly.com","Telly rd, Mariybinong");
		String st = "12345678901";
		comp.setABN("st");
		assertEquals(st,comp.getABN());
	}

	@Test
	void testSetURL() throws SQLException, IDOutOfRangeException, ABNValuesIncorrectException, ABNLengthIncorrectException {
		Company comp = new Company(1,"Telly","12","telly.com","Telly rd, Mariybinong");
		String st = "john.com";
		comp.setURL("st");
		assertEquals(st,comp.getURL());
	}

	@Test
	void testSetAddress() {
		fail("Not yet implemented");
	}

	@Test
	void testToString() {
		fail("Not yet implemented");
	}

	@Test
	void testGetSQL() {
		fail("Not yet implemented");
	}

	@Test
	void testGetName() {
		fail("Not yet implemented");
	}

	@Test
	void testSetName() {
		fail("Not yet implemented");
	}

}
