package Tests;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import Exceptions.IDOutOfRangeException;
import Exceptions.InvalidEmailException;
import Exceptions.InvalidNamingException;
import Objects.Project.Owner;
import Objects.SQLObjects.OwnerSQL;

class OwnerTest {

	@BeforeEach
	void setUp() throws Exception {
	}

	@Test
	void testAddPrefix() throws IDOutOfRangeException, InvalidNamingException, InvalidEmailException {
			Owner own = new Owner(1, "John Smith", "teacher", "john@indy.com", "Pr12", "C1");
			assertEquals("C1",own.getCompanyId());
	}

	@Test
	void testOwnerStringStringStringStringStringStringString() {
		Owner own = new Owner("C1", "John", "Smith", "teacher", "john@indy.com", "Pr12", "C1");
		assertEquals("C1",own.getCompanyId());
	}

	@Test
	void testOwnerIntStringStringStringStringString() throws IDOutOfRangeException, InvalidNamingException, InvalidEmailException {
		Owner own = new Owner(1, "John Smith", "teacher", "john@indy.com", "Pr12", "C1");
		assertEquals("C1",own.getCompanyId());
	}

	@Test
	void testGetFirstName() throws IDOutOfRangeException, InvalidNamingException, InvalidEmailException {
		Owner own = new Owner(1, "John Smith", "teacher", "john@indy.com", "Pr12", "C1");
		assertEquals("John",own.getFirstName());
	}

	@Test
	void testSetFirstName() throws IDOutOfRangeException, InvalidNamingException, InvalidEmailException {
		Owner own = new Owner(1, "John Smith", "teacher", "john@indy.com", "Pr12", "C1");
		String s1 = "Tim";
		own.setFirstName(s1);
		assertEquals(s1,own.getFirstName());
	}

	@Test
	void testSetSurName() throws IDOutOfRangeException, InvalidNamingException, InvalidEmailException {
		Owner own = new Owner(1, "John Smith", "teacher", "john@indy.com", "Pr12", "C1");
		String s1 = "Bond";
		own.setSurName(s1);
		assertEquals(s1,own.getSurName());
	}
	@Test
	void testSetRole() throws IDOutOfRangeException, InvalidNamingException, InvalidEmailException {
		Owner own = new Owner(1, "John Smith", "teacher", "john@indy.com", "Pr12", "C1");
		String s1 = "Telly handler";
		own.setRole(s1);
		assertEquals(s1,own.getRole());
	}

	@Test
	void testSetEmail() throws IDOutOfRangeException, InvalidNamingException, InvalidEmailException {
		Owner own = new Owner(1, "John Smith", "teacher", "john@indy.com", "Pr12", "C1");
		String s1 = "john@smith.com.au";
		own.setEmail(s1);
		assertEquals(s1,own.getEmail());
	}

	@Test
	void testToString() throws IDOutOfRangeException, InvalidNamingException, InvalidEmailException {
		Owner own = new Owner(1, "John Smith", "teacher", "john@indy.com", "Pr12", "C1");
		String st = "Own1;John;Smith;teacher;john@indy.com;Pr12;C1";
		assertEquals(st,own.toString());
	}



	@Test
	void testSetCompanyId() throws IDOutOfRangeException, InvalidNamingException, InvalidEmailException {
		Owner own = new Owner(1, "John Smith", "teacher", "john@indy.com", "Pr12", "C1");
		own.setCompanyId("Own2");
		assertEquals("Own2",own.getCompanyId());
	}

	@Test
	void testGetSQL() throws IDOutOfRangeException, InvalidNamingException, InvalidEmailException {
		OwnerSQL own = new OwnerSQL(new Owner(1, "John Smith", "teacher", "john@indy.com", "Pr12", "C1"));
		String st = "(1,'John','Smith','teacher','john@indy.com',12,1)";
		assertEquals(st,own.getSQL());
	}

}
