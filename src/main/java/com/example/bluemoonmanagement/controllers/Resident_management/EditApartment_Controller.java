package com.example.bluemoonmanagement.controllers.Resident_management;
import com.example.bluemoonmanagement.models.Apartment;
import com.example.bluemoonmanagement.api.ApartmentAPI;
import com.example.bluemoonmanagement.api.ResidentAPI;
import com.example.bluemoonmanagement.models.Resident;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.stage.Stage;

public class EditApartment_Controller {

    @FXML private ChoiceBox<String> roomOwnerID;
    @FXML private TextField roomOwnerName;
    @FXML private TextField roomArea;
    @FXML private TextField roomFloor;
    @FXML private TextField roomLabel;

    private Apartment updatedApartment; // Holds the updated resident
    private int apartmentId;
    private Resident residentNeedToBeAdded;
    private Resident residentNeedToBeUpdated;

    private Integer currentOwnerId;

    public void setCurrentOwnerId(Integer currentOwnerId){
        this.currentOwnerId = currentOwnerId;
    }

    public Integer getCurrentOwnerId(){
        return currentOwnerId;
    }

    public Resident getResidentNeedToBeAdded(){
        return residentNeedToBeAdded;
    }

    public Resident getResidentNeedToBeUpdated(){
        return residentNeedToBeUpdated;
    }

//    private List<String> availableRooms; // Rooms from apartmentList
//    private List<Resident> residentList;

    @FXML private void initialize(){
        roomOwnerID.getItems().addAll("N: Chưa có chủ");
        roomOwnerID.getItems().addAll("A: Cư dân mới");

        for (Resident resident : ResidentAPI.getAllResidents()) {
            roomOwnerID.getItems().add(String.valueOf(resident.getResidentId()) + ": " + resident.getName());
        }

//        roomOwnerID.setOnAction(event -> {
//            String selectedId = roomOwnerID.getValue().substring(0, 1);
//            if (!selectedId.equals("N")) {
//                Resident resident = ResidentAPI.getResidentById(Integer.parseInt(selectedId));
//                if (resident != null) {
//                    roomOwnerName.setText(resident.getName());
//                }
//            } else {
//                roomOwnerName.setText("");
//            }
//            roomOwnerName.setEditable(false);
//        });

        roomOwnerID.setOnAction(event -> {
            String selectedId = roomOwnerID.getValue().substring(0, 1);
            if (!selectedId.equals("N") && !selectedId.equals("A")) {
                Resident resident = ResidentAPI.getResidentById(
                        parseNumber(roomOwnerID.getValue())
                );
                if (resident != null) {
                    roomOwnerName.setText(resident.getName());
                    roomOwnerName.setEditable(false);
                }
            } else {
                roomOwnerName.setText("");
                if (selectedId.equals("N")){
                    roomOwnerName.setEditable(false);
                }
            }
        });
    }

    public Apartment getUpdatedApartment() {
        return updatedApartment;
    }

    public void setApartmentData(Apartment apartment, int apartmentId) {
        this.apartmentId = apartmentId;

        if (apartment.getOwnerId() != null) {
            roomOwnerID.setValue(apartment.getOwnerId() + ": " +
                    ResidentAPI.getResidentById(apartment.getOwnerId()).getName()
                    );

            roomOwnerName.setText(
                    ResidentAPI.getResidentById(apartment.getOwnerId()).getName()
            );

        } else {
            roomOwnerID.setValue(null); // Or set to some default value if needed
            roomOwnerName.setText(null);
        }

        roomArea.setText(String.valueOf(apartment.getArea()));
        roomArea.setEditable(false);

        roomFloor.setText(String.valueOf(apartment.getFloor()));
        roomFloor.setEditable(false);

        roomLabel.setText(apartment.getRoom());
        roomLabel.setEditable(false);
    }


    @FXML
    private void handleSave() {
        Integer ownerID;
        String index =  roomOwnerID.getValue().substring(0, 1);

        float area = Float.parseFloat(roomArea.getText());
        int floor = Integer.parseInt(roomFloor.getText());
        String room = roomLabel.getText();

        if (index.equals("N")){
            ownerID = null;

            residentNeedToBeUpdated = ResidentAPI.getResidentById(currentOwnerId);
            if (residentNeedToBeUpdated != null){
                residentNeedToBeUpdated.setIsOwner(false);
            }

            ResidentAPI.updateResidentById(currentOwnerId, apartmentId,
                    residentNeedToBeUpdated.getName(),
                    residentNeedToBeUpdated.getBirthday(), residentNeedToBeUpdated.getGender(),
                    residentNeedToBeUpdated.getPhoneNumber(), residentNeedToBeUpdated.getNationality(),
                    residentNeedToBeUpdated.getRelationshipWithOwner(), false,
                    residentNeedToBeUpdated.getStatus(), null);

        }
        else if (index.equals("A")){
            ownerID = ResidentAPI.getAllDataFromResidentTableInDB().size()+1;

            ResidentAPI.addResident(room, roomOwnerName.getText(), null, true,
            null, null, "Chủ hộ", true, 1, null);

            residentNeedToBeAdded = new Resident(ownerID, apartmentId, roomOwnerName.getText(), null, true, null,
                    null, "Chủ hộ", true, 1);

        }
        else {
            ownerID = parseNumber(roomOwnerID.getValue());
            residentNeedToBeUpdated = ResidentAPI.getResidentById(ownerID);
            if (residentNeedToBeUpdated != null && residentNeedToBeUpdated.getApartmentId() == apartmentId){
                residentNeedToBeUpdated.setIsOwner(true);
                ResidentAPI.updateResidentById(residentNeedToBeUpdated, null);
            }

        }

        updatedApartment = new Apartment(apartmentId, ownerID, area, floor, room);
        ApartmentAPI.updateOwnerApartment(apartmentId, ownerID);

        Stage stage = (Stage) roomOwnerID.getScene().getWindow();
        stage.close();
    }

    @FXML
    private void handleCancel() {
        updatedApartment = null;
        Stage stage = (Stage) roomOwnerID.getScene().getWindow();
        stage.close();
    }

    private void showAlert(Alert.AlertType alertType, String title, String content) {
        Alert alert = new Alert(alertType);
        alert.setTitle(title);
        alert.setContentText(content);
        alert.showAndWait();
    }

    private int parseNumber(String input){
        String[] parts = input.split(": ");
        return Integer.parseInt(parts[0]);
    }
}
