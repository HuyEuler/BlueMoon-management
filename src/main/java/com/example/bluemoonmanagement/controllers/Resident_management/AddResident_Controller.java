package com.example.bluemoonmanagement.controllers.Resident_management;

import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.stage.Stage;
import com.example.bluemoonmanagement.models.Resident;
import com.example.bluemoonmanagement.models.Apartment;
import com.example.bluemoonmanagement.api.ApartmentAPI;
import com.example.bluemoonmanagement.api.ResidentAPI;

import java.util.List;
import java.util.Optional;
import javafx.scene.control.Alert;
import javafx.scene.control.DialogPane;
import javafx.scene.text.Text;
import javafx.scene.text.TextFlow;


public class AddResident_Controller {

    @FXML private ChoiceBox<String> residentRoom;
    @FXML private TextField residentName;
    @FXML private DatePicker residentDOB;
    @FXML private ChoiceBox<String> residentGender;
    @FXML private TextField residentPhoneNumber;
    @FXML private TextField residentNation;
    @FXML private TextField residentRelationship;
    @FXML private ChoiceBox<String> residentIsOwner;
    @FXML private ChoiceBox<String> residentStatus;
    @FXML private TextArea residentNote;

    private List<String> availableRooms; // Rooms from apartmentList
    private int currentCount;

    private List<String> roomNameUniqueList;
    private List<String> roomOwnerUniqueList;

    private Resident newResident;

    public void setCurrentCount(int currentCount){
        this.currentCount = currentCount;
    }

    public void setAvailableRooms(List<String> availableRooms) {
        this.availableRooms = availableRooms;
        residentRoom.getItems().addAll(availableRooms);
    }

    public void setRoomNameUniqueList(List<String> roomNameUniqueList){
        this.roomNameUniqueList = roomNameUniqueList;
    }

    public void setRoomOwnerUniqueList(List<String> roomOwnerUniqueList){
        this.roomOwnerUniqueList = roomOwnerUniqueList;
    }

    public Resident getNewResident() {
        return newResident;
    }

    @FXML private void initialize(){
        residentGender.getItems().addAll("Nam", "Nữ");
        residentIsOwner.getItems().addAll("Có", "Không");
        residentStatus.getItems().addAll("Thường trú", "Tạm trú", "Tạm vắng");

        residentIsOwner.setOnAction(event -> {
            String isOwner = residentIsOwner.getValue();
            if (isOwner.equals("Có")) {
                residentRelationship.setText("Chủ sở hữu");
                residentRelationship.setEditable(false);
            } else {
                residentRelationship.setEditable(true);
            }
        });
    }

    @FXML
    private void handleSave() {
        String room = residentRoom.getValue();
        String name = residentName.getText();
        String dob = residentDOB.getValue() != null ? residentDOB.getValue().toString() : null;
        String gender = residentGender.getValue();
        String phoneNumber = residentPhoneNumber.getText();
        String nationality = residentNation.getText();
        String relationship = residentRelationship.getText();
        String isOwner = residentIsOwner.getValue();
        String status = residentStatus.getValue();
        String note = residentNote.getText();

        if (phoneNumber.length() > 11) {
            showAlert2(Alert.AlertType.ERROR, "Lỗi", "Số điện thoại chỉ được điền tối đa 11 ký tự.");
            return;
        }

        if (!isOwner.equals("Có") && relationship.equals("Chủ sở hữu")){
            showAlert2(Alert.AlertType.ERROR, "Lỗi", "Có mâu thuẫn giữa quan hệ với chủ hộ và việc có là chủ hộ hay không.");
            return;
        }

        if (room == null || room.isEmpty() || name == null || name.isEmpty() || dob == null || dob.isEmpty() ||
                gender == null || gender.isEmpty() || phoneNumber == null || phoneNumber.isEmpty() ||
                isOwner == null || isOwner.isEmpty() || status == null || status.isEmpty()) {
            showAlert(Alert.AlertType.ERROR, "Lỗi", "Chỉ được bỏ trống 'Quốc tịch', 'Quan hệ với chủ hộ' và 'Lý do'.");
            return;
        }

        int roomId = ApartmentAPI.getApartmentIdByRoom(room);

        int statusInt = switch (status) {
            case "Thường trú" -> 1;
            case "Tạm trú" -> 2;
            case "Tạm vắng" -> 3;
            default -> -1;
        };

        String roomNameUniqueCheck = roomId + "|" + name;
        String roomOwnerUniqueCheck = roomId + "|" + true;

        if (roomNameUniqueList.contains(roomNameUniqueCheck)) {
            showAlert2(Alert.AlertType.ERROR, "Lỗi", "Phòng " + room + " đã có người có tên " + name + ".");
            return;
        }

        if (isOwner.equals("Có") && roomOwnerUniqueList.contains(roomOwnerUniqueCheck)){
            showAlert2(Alert.AlertType.ERROR, "Lỗi", "Phòng " + room + " đã có chủ sở hữu.");
            return;
        }

        newResident = new Resident(currentCount+1, roomId, name, dob, gender.equals("Nam"), phoneNumber,
                nationality, relationship, isOwner.equals("Có"), statusInt);

        if (isOwner.equals("Có")) {
            Apartment roomOfResident = ApartmentAPI.getApartmentById(newResident.getApartmentId());
            if (roomOfResident.getOwnerId() != null) {
                Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
                alert.setTitle("Cảnh báo");
                alert.setHeaderText("Phòng này đang có chủ sở hữu");
                alert.setContentText("Bạn có muốn thay thế chủ sở hữu mới không?");

                ButtonType buttonYes = new ButtonType("Có");
                ButtonType buttonNo = new ButtonType("Không", ButtonBar.ButtonData.CANCEL_CLOSE);

                alert.getButtonTypes().setAll(buttonYes, buttonNo);

                Optional<ButtonType> result = alert.showAndWait();

                if (result.isPresent() && result.get() == buttonYes) {
                    ResidentAPI.addResident(room, name, dob, gender.equals("Nam"), phoneNumber,
                          nationality, relationship, true, statusInt, note);
                } else {
                    return;
                }

            } else {
                ResidentAPI.addResident(room, name, dob, gender.equals("Nam"), phoneNumber,
                        nationality, relationship, true, statusInt, note);
                ApartmentAPI.updateOwnerApartment(roomId, currentCount+1);
            }
        } else {
            ResidentAPI.addResident(room, name, dob, gender.equals("Nam"), phoneNumber,
                    nationality, relationship, false, statusInt, note);
        }

        Stage stage = (Stage) residentRoom.getScene().getWindow();
        stage.close();
    }


    @FXML
    private void handleCancel() {
        newResident = null;
        Stage stage = (Stage) residentRoom.getScene().getWindow();
        stage.close();
    }

    private void showAlert2(Alert.AlertType alertType, String title, String content) {
        Alert alert = new Alert(alertType);
        alert.setTitle(title);
        alert.setContentText(content);
        alert.showAndWait();
    }

    private void showAlert(Alert.AlertType alertType, String title, String content) {
        Alert alert = new Alert(alertType);
        alert.setTitle(title);

        TextFlow textFlow = new TextFlow();

        Text normalText1 = new Text("Chỉ được bỏ trống ");
        Text boldText1 = new Text("Quốc tịch");
        boldText1.setStyle("-fx-font-weight: bold;");
        Text normalText2 = new Text(", ");
        Text boldText2 = new Text("Quan hệ với chủ hộ");
        boldText2.setStyle("-fx-font-weight: bold;");
        Text normalText3 = new Text(", và ");
        Text boldText3 = new Text("Lý do");
        boldText3.setStyle("-fx-font-weight: bold;");
        Text normalText4 = new Text(".");

        textFlow.getChildren().addAll(normalText1, boldText1, normalText2, boldText2, normalText3, boldText3, normalText4);
        DialogPane dialogPane = alert.getDialogPane();
        dialogPane.setContent(textFlow);

        alert.showAndWait();
    }
}
