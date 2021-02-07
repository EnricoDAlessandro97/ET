package test;

import static org.junit.Assert.*;
import org.junit.Test;
import logic.controllers.ProfileController;
import logic.exceptions.DuplicateUsernameException;
import logic.exceptions.SystemException;
import logic.exceptions.UsernameException;

public class TestProfileController {
	
	/* Autor: Enrico D'Alessandro - Test 3 */
	@Test
	public void TestUsernameChangeAlreadyExist() {
		ProfileController profileController = new ProfileController();
		int result = 0;
		try {
			profileController.changeUsername("Erik97", "Erik97");
			result = 1;
		} catch (SystemException|UsernameException|DuplicateUsernameException e) {
			result = 0;
		}
		
		assertEquals(0, result);
	}
	
}
