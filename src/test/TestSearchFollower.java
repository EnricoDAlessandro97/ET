package test;

import static org.junit.Assert.assertEquals;

import org.junit.Test;

import logic.controllers.SearchFollowController;
import logic.exceptions.AddFollowerException;
import logic.exceptions.FollowRequestException;
import logic.exceptions.SystemException;

public class TestSearchFollower {
	
	/* Autor: Enrico D'Alessandro - Test 1 */
	@Test
	public void TestSendFollowRequestToUserNotFollowed() {
		SearchFollowController searchFollowController = new SearchFollowController();
		int result = 0;
		try {
			searchFollowController.sendFollowRequest("Cecco", "Erik97");
			result = 1;
		} catch (SystemException | FollowRequestException | AddFollowerException e) {
			result = 0;
		}
		
		assertEquals(1, result);
	}
	
	/* Autor: Enrico D'Alessandro - Test 2 */
	@Test
	public void TestSendFollowRequestToUserAlreadyFollowed() {
		SearchFollowController searchFollowController = new SearchFollowController();
		int result = 0;
		try {
			searchFollowController.sendFollowRequest("Cecco", "Erik97");
			result = 1;
		} catch (SystemException | FollowRequestException | AddFollowerException e) {
			result = 0;
		}
		
		assertEquals(0, result);
	}
	
}
