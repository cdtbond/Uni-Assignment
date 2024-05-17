package Tests;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import Exceptions.DuplicatePreferenceException;
import Exceptions.IDOutOfRangeException;
import Objects.Student.Preferences;

class PreferencesTest {

	@BeforeEach
	void setUp() throws Exception {
	}

	@Test //check that prefix of "Pref" is added
	void testAddPrefix() throws DuplicatePreferenceException, IDOutOfRangeException {
		Preferences pref = new Preferences(1, 5, 12, 13, 1);
		assertEquals("S1",pref.getID());
	}

	@Test //check that id is not out of the allowed range
	void testOutOfRange() throws DuplicatePreferenceException, IDOutOfRangeException {
		assertThrows(IDOutOfRangeException.class,
				()->{
		Preferences pref = new Preferences(50, 5, 12, 13, 1);
				});
	}
	
	@Test //check if a project preference is entered twice or more for  the one project
	void testDuplicatePref() throws DuplicatePreferenceException, IDOutOfRangeException {
		assertThrows(DuplicatePreferenceException.class,
				()->{
		Preferences pref = new Preferences(1, 1, 12, 13, 1);
				});
	}

	@Test
	void testPreferencesStrings() throws DuplicatePreferenceException {
		Preferences pref = new Preferences("S1", "Pr1", "Pr12", "Pr13", "Pr10");
		assertEquals("Pr1",pref.getProjectPreferences().get(0));
	}

	@Test // check that the int version evaluates
	void testPreferencesInts() throws DuplicatePreferenceException, IDOutOfRangeException {
		Preferences pref = new Preferences(1, 1, 12, 13, 10);
		assertEquals("Pr1",pref.getProjectPreferences().get(0));
	}

	@Test
	void testToString() throws DuplicatePreferenceException, IDOutOfRangeException {
		Preferences pref = new Preferences(1, 1, 12, 13, 10);
		assertEquals("S1;Pr1 4;Pr12 3;Pr13 2;Pr10 1",pref.toString());

	}

}
