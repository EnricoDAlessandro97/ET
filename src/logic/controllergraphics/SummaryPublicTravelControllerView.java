package logic.controllergraphics;

import java.awt.Desktop;
import java.io.IOException;
import java.net.URISyntaxException;
import java.net.URL;
import java.util.Optional;

import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonBar.ButtonData;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.input.MouseEvent;
import javafx.scene.paint.Color;
import javafx.stage.Stage;
import logic.bean.PublicTravelBean;
import logic.controllers.PlanController;
import logic.exceptions.BookGroupTravelException;
import logic.exceptions.DatesException;
import logic.exceptions.DescriptionSyntaxException;
import logic.exceptions.SaveTravelException;
import logic.exceptions.SystemException;
import logic.exceptions.TravRoomException;

public class SummaryPublicTravelControllerView {
	@FXML
    private Label lblUsernameTravel;

    @FXML
    private Label lblDest;

    @FXML
    private Label lblData;

    @FXML
    private Label lblPosti;

    @FXML
    private Label lblDataEnd;

    @FXML
    private Label lblPrice;

    @FXML
    private Label lblStars;

    @FXML
    private Label lblTravellers;

    @FXML
    private Label lblRooms;

    @FXML
    private Label lblBreakfast;

    @FXML
    private Label lblHotelName;
    
    @FXML
    private Label lblDescriptionError;
    
    @FXML
    private TextArea txtDescription;

    @FXML
    private Button btnContinue;
     
    
    private PublicTravelBean vgr = null;
    
    @FXML
    void onMouseHotelNameClicked(MouseEvent event) {
    	openLinkHotel(vgr.getHotelInfoBean().getHotelLink());
    }

   
    @FXML
    void onMouseHotelNameEntered(MouseEvent event) {
    	lblHotelName.setTextFill(Color.web("#153bc2"));
    }

    @FXML
    void onMouseHotelNameExited(MouseEvent event) {
    	lblHotelName.setTextFill(Color.web("#f9ab51"));
    }    
    
    @FXML
    void onMouseContinueEntered(MouseEvent event) {
    	btnContinue.setStyle("-fx-background-color: #d16002");
    }

    @FXML
    void onMouseContinueExited(MouseEvent event) {
    	btnContinue.setStyle("-fx-background-color: #f9ab51");
    }
    
    private void showAlertInformation(String message) {
    	Alert alert = new Alert(AlertType.INFORMATION);
		alert.setTitle("INFO");
		alert.setHeaderText(null);
		alert.setContentText(message);
		alert.showAndWait();
    }
    
    @FXML
    void onMouseContinueClicked(MouseEvent event) {
    	    	
    	if(txtDescription.getText().equalsIgnoreCase("")) {
    		lblDescriptionError.setVisible(true);
    	}
    	else {
    		descriptionAvailable();
    	}
    }
    
    private void descriptionAvailable() {
    	Stage stage = (Stage)btnContinue.getScene().getWindow();
    	PlanController planController = new PlanController();
		lblDescriptionError.setVisible(false);
		Alert alert = new Alert(AlertType.CONFIRMATION);
		alert.setTitle("Easy Travel");
		alert.setHeaderText("Book your travel now or save it and book it later!");
		alert.setContentText("Choose your option...");
		ButtonType buttonTypeSave = new ButtonType("Save it");
		ButtonType buttonTypeBook = new ButtonType("Book it now");
		ButtonType buttonTypeCancel = new ButtonType("Cancel", ButtonData.CANCEL_CLOSE);
		alert.getButtonTypes().setAll(buttonTypeSave, buttonTypeBook, buttonTypeCancel);
		Optional<ButtonType> result = alert.showAndWait();
		
		if(result.isPresent()) {
			if(result.get() == buttonTypeSave) {
    			// save
    			try {
    				vgr.setAndValidateDescription(txtDescription.getText());
					planController.saveGroupTravel(vgr);
					this.showAlertInformation("Saved... you will be redirected to the homepage!");
					stage.close();
				} catch (SaveTravelException|SystemException|DatesException|TravRoomException|DescriptionSyntaxException e) {
					this.showAlertError(e.getMessage());
				} 
    		}
    		else if(result.get() == buttonTypeBook) {
    			// book
    			try {
        			vgr.setAndValidateDescription(txtDescription.getText());
					planController.bookAndSaveGroupTravel(vgr);
					this.showAlertInformation("Booked... you will be redirected to the homepage!");
					stage.close();
				} catch (BookGroupTravelException|SystemException|DatesException|TravRoomException|DescriptionSyntaxException e) {
					this.showAlertError(e.getMessage());
				} 
    		}
		}
    }
    
    private void showAlertError(String cause) {
    	Alert alert = new Alert(AlertType.ERROR);
		alert.setHeaderText(cause);
		alert.setTitle("Error");
		alert.setResizable(false);
		alert.showAndWait();
    }

    private void openLinkHotel(String link) {
		try {
			Desktop.getDesktop().browse(new URL(link).toURI());
		} catch (IOException|URISyntaxException e) {
			e.printStackTrace();
		} 
	}
    
	public void setPublicTravelInfo(PublicTravelBean vgr) {
		this.vgr = vgr;
		this.lblUsernameTravel.setText(vgr.getCreatorBean());
		this.lblDest.setText(vgr.getDestinationBean());
		this.lblHotelName.setText(vgr.getHotelInfoBean().getHotelName());
		this.lblStars.setText(vgr.getHotelInfoBean().getStars());
		String price = vgr.getHotelInfoBean().getPrice();
    	price = price.replace("euro", "�");
		this.lblPrice.setText(price);
		this.lblBreakfast.setText(vgr.getHotelInfoBean().getBreakfast());
		this.lblData.setText(vgr.getStartDateBean());
		this.lblDataEnd.setText(vgr.getEndDateBean());	
		this.lblRooms.setText(vgr.getHotelInfoBean().getNumRooms());
		this.lblTravellers.setText(String.valueOf(vgr.getNumTravelersBean()));
	}
}
