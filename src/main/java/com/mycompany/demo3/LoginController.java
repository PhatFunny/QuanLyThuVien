/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.demo3;


import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ResourceBundle;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.image.ImageView;


/**
 * FXML Controller class
 *
 * @author PHAT NGUYEN
 */
public class LoginController extends InfoController implements Initializable {

    /**
     * Initializes the controller class.
     * @param url
     * @param rb
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {

    }    
    
    @FXML
    private Button cancelButton;
    @FXML
    private Label loginMessageLabel;
    @FXML
    private ImageView brandingImageView;
    @FXML
    private ImageView bookImageView;
    @FXML
    private ImageView lockImageView;
    @FXML
    private TextField usernameTextField;
    @FXML
    private PasswordField passwordTextField;
    
    @FXML
    private void switchToHomePageLogout() throws IOException {
        App.setRoot("homePageLogout");
    }
    
    @FXML
    private void switchToHomePage() throws IOException {
        
        App.setRoot("homePage");
    }
    

    //
    public void loginButtonOnAction(ActionEvent event) throws SQLException, IOException, InterruptedException{   
        if(usernameTextField.getText().isBlank() == false //Kiem tra username va password co rong hay khong
                && passwordTextField.getText().isBlank() == false){
//             static_MaDocGia.setText(usernameTextField.getText());
             validateLogin();
        } else {
            loginMessageLabel.setText("H??y nh???p t??i kho???n ho???c m???t kh???u");
        }
    }
    
    //
    public void cancelButtonOnAction(ActionEvent event) throws IOException{
          switchToHomePage();
    }

    //
    public void validateLogin() throws SQLException, IOException, InterruptedException{
        DatabaseConnection conn = new DatabaseConnection();//
        Connection connectDB = conn.getConnection();
        String veryfyLogin = "SELECT Count(1) FROM qlthuvien.user_account WHERE username = '" + 
                usernameTextField.getText() + "'AND password ='" + passwordTextField.getText() + "'";
        //?????
        Statement statement = connectDB.createStatement();//
        ResultSet queryResult = statement.executeQuery(veryfyLogin);
        
        //n???u c?? trong database th?? tr??? v??? 1 ng?????c l???i l?? 0
        while(queryResult.next()){
            if(queryResult.getInt(1) == 1){
                loginMessageLabel.setText("Ch??c m???ng, B???n ????ng nh???p th??nh c??ng!");
                
                switchToHomePageLogout();
            } else{
                loginMessageLabel.setText("L???i ????ng nh???p, h??y th??? l???i!");
            }
        }
    }
    
    
}

