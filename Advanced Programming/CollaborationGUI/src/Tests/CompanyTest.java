package Tests;

import static org.junit.jupiter.api.Assertions.*;

import java.sql.SQLException;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import Data.HashMaps;
import Exceptions.ABNLengthIncorrectException;
import Exceptions.ABNValuesIncorrectException;
import Exceptions.IDOutOfRangeException;
import Exceptions.IdAlreadyUsedException;
import Objects.Project.Company;
import Objects.SQLObjects.CompanySQL;

class CompanyTest {
	HashMaps hm;
	@BeforeEach
	void setUp() throws Exception {
	hm = HashMaps.getSingleton();
	}

	@Test
	void testAddPrefix() throws SQLException, IDOutOfRangeException, ABNValuesIncorrectException, ABNLengthIncorrectException {
		Company comp = new Company(1,"Telly","12222222222","telly.com","Telly rd, Mariybinong");
		assertEquals("C1",comp.getID());
	}

	@Test
	void testCompanyString() throws ABNValuesIncorrectException, ABNLengthIncorrectException, IdAlreadyUsedException {
		Company comp = new Company("C1","Telly","12233445555","telly.com","Telly rd, Mariybinong");
		assertEquals("C1",comp.getID());
	}

	@Test
	void testCompanyInt() throws SQLException, IDOutOfRangeException, ABNValuesIncorrectException, ABNLengthIncorrectException, IdAlreadyUsedException {
		Company comp = new Company(1,"Telly","12233445555","telly.com","Telly rd, Mariybinong");
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
		Company comp = new Company(1,"Telly","12222222222","telly.com","Telly rd, Mariybinong");
		String st = "12345678901";
		comp.setABN(st);
		assertEquals(st,comp.getABN());
	}

	@Test
	void testSetURL() throws SQLException, IDOutOfRangeException, ABNValuesIncorrectException, ABNLengthIncorrectException {
		Company comp = new Company(1,"Telly","12222222222","telly.com","Telly rd, Mariybinong");
		String st = "john.com";
		comp.setURL(st);
		assertEquals(st,comp.getURL());
	}

	@Test
	void testSetAddress() throws SQLException, IDOutOfRangeException, ABNValuesIncorrectException, ABNLengthIncorrectException {
		Company comp = new Company(1,"Telly","12222222222","telly.com","Telly rd, Mariybinong");
		String st = "NewStreet";
		comp.setAddress(st);
		assertEquals(st,comp.getAddress());
	}

	@Test
	void testToString() throws SQLException, IDOutOfRangeException, ABNValuesIncorrectException, ABNLengthIncorrectException {
		Company comp = new Company(1,"Telly","12222222222","telly.com","Telly rd, Mariybinong");
		String st1 = "C1;Telly;12222222222;telly.com;Telly rd, Mariybinong";
		String st2 = comp.getID() + ";" + comp.getName() + ";" + comp.getABN() + ";" + comp.getURL() + ";" + comp.getAddress();
		assertEquals(st1,st2);
	}

	@Test
	void testGetSQL() throws ABNValuesIncorrectException, ABNLengthIncorrectException, SQLException, IDOutOfRangeException {
		CompanySQL comp = new CompanySQL(new Company(1,"Telly","12222222222","telly.com","Telly rd, Mariybinong"));
		String st1 = "('1','Telly','12222222222','telly.com','Telly rd, Mariybinong')";
		assertEquals(st1,comp.getSQL());
	}


	@Test
	void testSetName() throws SQLException, IDOutOfRangeException, ABNValuesIncorrectException, ABNLengthIncorrectException {
		Company comp = new Company(1,"Telly","12222222222","telly.com","Telly rd, Mariybinong");
		String st = "NewName";
		comp.setName(st);
		assertEquals(st,comp.getName());
	}

}
