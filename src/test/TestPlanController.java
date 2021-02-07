package test;

import static org.junit.Assert.assertEquals;

import org.junit.Test;

import logic.controllers.PlanController;
import logic.exceptions.DatesException;
import logic.exceptions.TravRoomException;

public class TestPlanController {
	
	/* Autor: Enrico D'Alessandro - Test 4 */
	@Test
	public void TestValidateDatesFirstLessThanSecond() {
		PlanController planController = new PlanController();
		int result = 0;
		try {
			planController.validateDates("2021/02/12", "2021/02/15");
			result = 1;
		} catch (DatesException e) {
			result = 0;
		}
		
		assertEquals(1, result);
	}
	
	/* Autor: Enrico D'Alessandro - Test 5 */
	@Test
	public void TestValidateDatesFirstGreaterThanSecond() {
		PlanController planController = new PlanController();
		int result = 0;
		try {
			planController.validateDates("2021/02/20", "2021/02/15");
			result = 1;
		} catch (DatesException e) {
			result = 0;
		}
		
		assertEquals(0, result);
	}
	
	/* Autor: Enrico D'Alessandro - Test 6 */
	@Test
	public void TestValidateTravelersAndRoomsPositive() {
		PlanController planController = new PlanController();
		int result = 0;
		try {
			planController.validateTravelersAndRooms("5", "2");
			result = 1;
		} catch (TravRoomException e) {
			result = 0;
		}
		
		assertEquals(1, result);
	}	
	
	/* Autor: Enrico D'Alessandro - Test 9 */
	@Test
	public void TestValidateTravelersAndRoomsNegative() {
		PlanController planController = new PlanController();
		int result = 0;
		try {
			planController.validateTravelersAndRooms("2", "5");
			result = 1;
		} catch (TravRoomException e) {
			result = 0;
		}
		
		assertEquals(0, result);
	}	
	
}
