package logic.controllers.manage;

import java.util.ArrayList;
import java.util.List;

import logic.bean.HotelBean;
import logic.bean.PublicTravelBean;
import logic.dao.PublicTravelDao;
import logic.exceptions.BookGroupTravelException;
import logic.exceptions.DeleteGroupTravelException;
import logic.exceptions.SystemException;
import logic.model.PublicTravel;

public class ManagePublicTravelController {
	
	public List<PublicTravelBean> loadMyUpcomingTGR(String username){
		
		List<PublicTravel> travels = new ArrayList<>();
		List<PublicTravelBean> travelsBean = new ArrayList<>();
		
		try {
			travels = PublicTravelDao.retrieveMySavedGrTravels(username);
			for(PublicTravel vg : travels) {
				
				PublicTravelBean viaggioGruppoBean = new PublicTravelBean();
				HotelBean hotelBean = new HotelBean();
				hotelBean.setBreakfast(vg.getHotelInfo().getBreakfast());
                hotelBean.setHotelLink(vg.getHotelInfo().getHotelLink());
                hotelBean.setHotelName(vg.getHotelInfo().getHotelName());
                hotelBean.setNumRooms(String.valueOf(vg.getHotelInfo().getNumRooms()));
                hotelBean.setPrice(vg.getHotelInfo().getPrice());
                hotelBean.setStars(String.valueOf(vg.getHotelInfo().getStars()));

                viaggioGruppoBean.setCreator(vg.getCreator());
                viaggioGruppoBean.setDestination(vg.getDestination());
                viaggioGruppoBean.setDescription(vg.getDescription());
                viaggioGruppoBean.setStartDate(vg.getStartDate());
                viaggioGruppoBean.setEndDate(vg.getEndDate());
                viaggioGruppoBean.setHotelInfo(hotelBean);
                viaggioGruppoBean.setAvailableSeats(String.valueOf(vg.getAvailableSeats()));
                viaggioGruppoBean.setNumMaxUt(String.valueOf(vg.getNumMaxUt()));
                viaggioGruppoBean.setIdTravel(String.valueOf(vg.getIdTravel()));
                viaggioGruppoBean.setTravelName(vg.getTravelName());
                
				travelsBean.add(viaggioGruppoBean);
			}
			return travelsBean;
		} catch (Exception e) {
			return travelsBean;
		}
	}
	
	public void bookTravelGr(String idV) throws BookGroupTravelException, SystemException {
		PublicTravelDao.bookGroupTravel(Integer.valueOf(idV));
	}
	
	public void deleteTravelGr(String idV) throws DeleteGroupTravelException, SystemException {
		PublicTravelDao.deleteTravelGr(Integer.valueOf(idV));
	}
	
}
