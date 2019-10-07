package phoneBook;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.NoSuchElementException;
import java.util.Scanner;

import javafx.application.Platform;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.geometry.HPos;
import javafx.geometry.Insets;
import javafx.geometry.Orientation;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.MenuBar;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.ColumnConstraints;

import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.StackPane;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import javafx.stage.Stage;
/** 
 * This is a phonebook application which has three scenes.
 * Scene 1 is a login page which has preset credentials 
 * username- java
 * password- pass
 * Scene 2 is the view contact list page which consists of delete, search and contacts features.
 * Scene 3 is the add contact page which takes user input like name,phone number and email.
 * This phoneBook class is the main class which handles all the scenes and functionality.
 * 
 * @author samandeep singh, manpreet kaur and divjot chawla.
 * @since 2 April,2019
 * @version 1.3
 *
 */
public class phoneBook extends javafx.application.Application {
	private ListView<String> list;
	File contactList = new File("Contacts.txt");
	ArrayList<Contact> contactArray = new ArrayList<Contact>();


	public static void main(String[] args) {
		launch(args);
	}
	/**
	 * A method to read data from the text file
	 * @throws NoSuchElementException
	 */
	public void readFile() {
		try {
			FileReader fr = new FileReader(contactList);
			BufferedReader input = new BufferedReader(fr);
			String contDetails = null;
			while((contDetails = input.readLine()) != null) {
			String[] contDetailsParts = contDetails.split(";");
			String userName = contDetailsParts[0];
			String phoneNum = contDetailsParts[1];
			String eMail = contDetailsParts[2];
			Long phoneNumLong = Long.parseLong(phoneNum);
			contactArray.add(new Contact(userName, phoneNumLong, eMail));
			String completeOut = userName + "        " + phoneNum + "        "  + eMail;
			list.getItems().addAll(completeOut);
			}
			input.close();
		    }
		catch(NoSuchElementException n) {
			Alert al = new Alert(AlertType.INFORMATION, "No Contacts Found");
			al.showAndWait();
			}
			catch(Exception e) {
				e.printStackTrace();
			}
	}
	
/** 
 * This include all the three scenes.
 */
	
	@Override
	public void start(Stage ps) throws Exception {

		// ----Scene 1 (login page) starts here -----------

		Label lblLogin = new Label("Welcome");
		lblLogin.setFont(Font.font("Cursive", 20));
		Label lblUsername = new Label("Username:");
		Label lblPassword = new Label("Password:");

		TextField txtUsername = new TextField();
		txtUsername.setPromptText("UserName");
		PasswordField txtPassword = new PasswordField();
		txtPassword.setPromptText("Password");
		//TextField txtPassword = new TextField();

		Button btnLogin = new Button("Login");
		btnLogin.setMaxWidth(Double.MAX_VALUE);
		btnLogin.setMnemonicParsing(true);

		Button btnLogout1 = new Button("Exit");
		btnLogout1.setMaxWidth(Double.MAX_VALUE);
		btnLogout1.setMnemonicParsing(true);
		
		Button btnClear = new Button("Clear");
		btnClear.setMaxWidth(Double.MAX_VALUE);
		btnClear.setMnemonicParsing(true);

		GridPane gp1 = new GridPane();
		gp1.setPadding(new Insets(15, 20, 20, 20));
		gp1.setHgap(5);
		gp1.setVgap(5);

		GridPane btnGrid = new GridPane();
		btnGrid.setPadding(new Insets(0, 6, 0, 0));
		btnGrid.setHgap(5);
		btnGrid.setVgap(5);
		
		ColumnConstraints bl = new ColumnConstraints();
		bl.setPercentWidth(50);
		ColumnConstraints bl1 = new ColumnConstraints();
		bl1.setPercentWidth(50);

		btnGrid.getColumnConstraints().addAll(bl, bl1);

		// ROW 1
		gp1.add(lblLogin, 0, 0, 2, 1);
		GridPane.setHalignment(lblLogin, HPos.CENTER);

		// ROW 2
		gp1.add(lblUsername, 0, 1);
		gp1.add(txtUsername, 1, 1);

		// ROW 3
		gp1.add(lblPassword, 0, 2);
		gp1.add(txtPassword, 1, 2);

		// ROW 4
		gp1.add(btnGrid, 1, 3, 2, 1);
		btnGrid.add(btnLogin, 0, 0);
		btnGrid.add(btnLogout1, 1, 0);
		gp1.setAlignment(Pos.CENTER);

		// -----------------Scene 2( contact list) starts here-------------------------------

		TextField txtSearch = new TextField();
		txtSearch.setMaxWidth(Double.MAX_VALUE);
		txtSearch.setPromptText("Search by Name");

		Button btnSearch = new Button("Search");
		btnSearch.setMaxWidth(Double.MAX_VALUE);
		btnSearch.setMnemonicParsing(true);

		Button btnAdd = new Button("Add Contact");
		btnAdd.setMaxWidth(Double.MAX_VALUE);
		btnAdd.setMnemonicParsing(true);

		Button btnEdit = new Button("Log Out");
		btnEdit.setMaxWidth(Double.MAX_VALUE);
		btnEdit.setMnemonicParsing(true);

		Button btnDelete = new Button("Delete Contact");
		btnDelete.setMaxWidth(Double.MAX_VALUE);
		btnDelete.setMnemonicParsing(true);

		Button btnLogout2 = new Button("Back");
		btnLogout2.setMaxWidth(Double.MAX_VALUE);
		btnLogout2.setMnemonicParsing(true);

		GridPane gp2 = new GridPane();

		// FIRST PART
		
		HBox hBox1 = new HBox();

		// Setting the margin to the nodes
		HBox.setMargin(txtSearch, new Insets(10, 0, 10, 10));
		HBox.setMargin(btnSearch, new Insets(10, 0, 10, 10));
		HBox.setMargin(btnLogout2, new Insets(10, 10, 10, 10));

		// retrieving the observable list of the VBox
		ObservableList list1 = hBox1.getChildren();

		// Adding all the nodes to the observable list
		list1.addAll(txtSearch, btnSearch, btnLogout2);

		// MIDDLE PART

		list = new ListView<String>();
		list.setOrientation(Orientation.VERTICAL);

		StackPane listPane = new StackPane();
	
		readFile();

		listPane.getChildren().add(list);
		
		
		

		// LAST PART
		HBox hBox2 = new HBox();

		// Setting the margin to the buttons
		HBox.setMargin(btnAdd, new Insets(10, 0, 10, 10));
		HBox.setMargin(btnEdit, new Insets(10, 0, 10, 10));
		HBox.setMargin(btnDelete, new Insets(10, 10, 10, 10));

		// retrieving the observable list of the VBox
		ObservableList <Node>list2 = hBox2.getChildren();

		// Adding all the nodes to the observable list
		list2.addAll(btnAdd, btnEdit, btnDelete);

		// ROW 1
		gp2.add(hBox1, 0, 0, 4, 1);

		// ROW 2
		gp2.add(listPane, 0, 1);
		GridPane.setColumnSpan(listPane, 4);

		// ROW 3
		gp2.add(hBox2, 0, 2, 4, 1);

		gp2.setAlignment(Pos.CENTER);;
		Scene scene2 = new Scene(gp2);


		// -------------------------Scene 3( add contact page) Starts------------------------------------

		Label lblNewContact = new Label("Add New Contact");
		lblNewContact.setFont(Font.font("Cursive", 20));
		Label lblName = new Label("Name");
		Label lblPhone = new Label("Phone");
		Label lblEmail = new Label("Email");

		TextField txtName = new TextField();
		TextField txtPhone = new TextField();
		TextField txtEmail = new TextField();

		txtName.setPromptText("Enter the contact's full name");
		txtPhone.setPromptText("Enter the phone number");
		txtEmail.setPromptText("Enter the E-mail Address");

		Button btnAddContact = new Button("Add Contact");
		btnAddContact.setMaxWidth(Double.MAX_VALUE);
		btnAddContact.setMnemonicParsing(true);
		
		Button btnBack = new Button("Go Back");
		btnBack.setMaxWidth(Double.MAX_VALUE);
		btnBack.setMnemonicParsing(true);
		
		Button btnLogout3 = new Button("Logout");
		btnLogout3.setMaxWidth(Double.MAX_VALUE);
		btnLogout3.setMnemonicParsing(true);

		GridPane gp3 = new GridPane();

		gp3.setPadding(new Insets(13, 20, 20, 20));
		gp3.setHgap(7);
		gp3.setVgap(7);

		HBox hBox3 = new HBox();

		// Setting the space between the nodes of a HBox pane
		hBox3.setSpacing(7);


		// retrieving the observable list of the VBox
		ObservableList<Node> list3 = hBox3.getChildren();

		// Adding all the nodes to the observable list
		list3.addAll(btnAddContact, btnBack, btnLogout3);

		// ROW 0
		gp3.add(lblNewContact, 0, 0, 2, 1);

		// ROW 1
		gp3.add(lblName, 0, 1);
		gp3.add(txtName, 1, 1);

		// ROW 2
		gp3.add(lblPhone, 0, 2);
		gp3.add(txtPhone, 1, 2);

		// ROW 3
		gp3.add(lblEmail, 0, 3);
		gp3.add(txtEmail, 1, 3);

		// ROW 4
		gp3.add(hBox3, 0, 4, 2, 1);
		
		gp3.setAlignment(Pos.CENTER);
		
		/**
		 * All the buttons events from all the scenes are handled here
		 */

		btnAddContact.setOnAction(e -> {
			try {
				try {
					Long.parseLong(txtPhone.getText().trim());
				}
				catch(Exception ee) {
					//System.out.println("Please enter Valid Input");
					Alert al = new Alert(AlertType.ERROR, "Please enter a Valid Number");
					al.showAndWait();
					System.exit(0);
				}
				if(txtName.getText().isEmpty() || txtPhone.getText().isEmpty() || txtEmail.getText().isEmpty()) {
					Alert al = new Alert(AlertType.ERROR, "Please fill all the fields");
					al.showAndWait();
				}
				else if(txtPhone.getText().trim().length() != 10) {
					Alert al = new Alert(AlertType.INFORMATION, "Please enter a ten digit phone number");
					al.showAndWait();
				}
				else {
				contactArray.add(new Contact(txtName.getText().trim(), Long.parseLong(txtPhone.getText().trim()), txtEmail.getText().trim()));
				
				PrintWriter out = new PrintWriter(contactList);
				for (Contact c : contactArray) {
				out.print(c.getName());
				out.print(";");
				out.print(c.getPhone() + "");
				out.print(";");
				out.print(c.getEmail());
				out.println(";");
				}
				out.close();
				txtName.setText("");
				txtPhone.setText("");
				txtEmail.setText("");
				}
			}
			
			 catch (FileNotFoundException e1) {
				e1.printStackTrace();
			} catch (IOException e1) {
				e1.printStackTrace();
			}
			
		});

		GridPane.setHalignment(btnAddContact, HPos.CENTER);
		GridPane.setHalignment(lblNewContact, HPos.CENTER);
		Scene scene3 = new Scene(gp3);
		


		
		btnLogin.setOnAction(e -> {
			

			if(txtUsername.getText().trim().equalsIgnoreCase("java") && txtPassword.getText().trim().equalsIgnoreCase("pass")) {
				ps.setScene(scene2);	
			}
			else {
				Alert alert = new Alert(AlertType.WARNING, "Wrong Input, Please Try Again"); 
				 alert.showAndWait();
			}
		});
	
		btnSearch.setOnAction( e -> {
			boolean matchFound = false;
			for(Contact c : contactArray) {
				if(c.getName().equalsIgnoreCase(txtSearch.getText().trim())){
					String userName = c.getName();
				    String phoneNum = c.getPhone() + "";
				    String eMail = c.getEmail();
				    String completeOut = userName + "        " + phoneNum + "        "  + eMail;
				    list.getItems().clear();
					list.getItems().add(completeOut);
					matchFound = true;
				}
			}
			if(matchFound == false) {
				Alert al = new Alert(AlertType.INFORMATION, "No results to display");
				al.showAndWait();
			}
		});
		
		btnAdd.setOnAction(e -> {

			ps.setScene(scene3);
		});
		
		btnDelete.setOnAction(e -> {
			if(contactArray.size() == 0) {
				Alert alr = new Alert(AlertType.INFORMATION, "No Contacts Available to Delete");
				alr.showAndWait();
			}
			else if(list.getSelectionModel().getSelectedIndex() == -1) {
				Alert alr = new Alert(AlertType.INFORMATION, "Please select a contact first");
				alr.showAndWait();
			}
			else {
			contactArray.remove(list.getSelectionModel().getSelectedIndex());
			list.getItems().removeAll(list.getSelectionModel().getSelectedItems());
			try {
			PrintWriter out = new PrintWriter(contactList);
			for (Contact c : contactArray) {
			out.print(c.getName());
			out.print(";");
			out.print(c.getPhone() + "");
			out.print(";");
			out.print(c.getEmail());
			out.println(";");
			}
			out.close();
			}
			catch(FileNotFoundException e3) {
				e3.printStackTrace();
			}
			}
			
		});

		btnBack.setOnAction(e -> {
			list.getItems().clear();
		    for(Contact cc : contactArray) {
		    String userName = cc.getName();
		    String phoneNum = cc.getPhone() + "";
		    String eMail = cc.getEmail();
		    String completeOut = userName + "        " + phoneNum + "        "  + eMail;
			list.getItems().addAll(completeOut);	
		    }
			ps.setScene(scene2);
		});
		
		btnEdit.setOnAction(e ->{
			Platform.exit();
		});


		// close the interface with exit button
		btnLogout1.setOnAction(actionEvent -> Platform.exit());

		// logout any time with logout buttons
		btnLogout2.setOnAction(actionEvent -> {
			list.getItems().clear();
		    for(Contact cc : contactArray) {
		    String userName = cc.getName();
		    String phoneNum = cc.getPhone() + "";
		    String eMail = cc.getEmail();
		    String completeOut = userName + "        " + phoneNum + "        "  + eMail;
			list.getItems().addAll(completeOut);
		    }
		});
		btnLogout3.setOnAction(actionEvent -> Platform.exit());
		
		Scene scene1 = new Scene(gp1);
		
		scene1.getStylesheets().add("style.css");
		scene2.getStylesheets().add("style.css");
		scene3.getStylesheets().add("style.css");

		ps.setScene(scene1);
		ps.sizeToScene();
		ps.setTitle("Phone Book App");
		ps.show();
	}
}
