package view;

import controller.Controller;
import javafx.collections.FXCollections;
import javafx.collections.ListChangeListener;
import javafx.collections.ObservableList;
import javafx.scene.control.CustomMenuItem;
import javafx.scene.control.Menu;
import javafx.scene.control.MenuBar;
import javafx.scene.control.MenuItem;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

public class Menus {

	TextField projectNameField;
	
	MenuBar menuBar;

	ObservableList<MenuItem> modelList;
	ObservableList<MenuItem> viewList;
	ObservableList<MenuItem> controllerList;

	Controller controller;
	Stage window;

	public Menus(Controller controller, Stage window) {
		this.controller = controller;
		this.window = window;

		menuBar = new MenuBar();

		Menu projectMenu = new Menu("Project");
		
		projectNameField = new TextField("Projectname");
		projectNameField.textProperty().addListener((observable, oldValue, newValue) -> {
		    controller.refactorProject(newValue);
		});
		
		CustomMenuItem projectName = new CustomMenuItem();
		projectName.setContent(projectNameField);
		projectName.setHideOnClick(false);
		
		projectMenu.getItems().add(projectName);
		
		Menu modelMenu = new Menu("Model");

		MenuItem newModel = new MenuItem("New...");
		newModel.setOnAction(e -> newModelHandler());

		modelList = FXCollections.observableArrayList();

		modelMenu.getItems().add(newModel);

		modelList.addListener((ListChangeListener.Change<? extends MenuItem> change) -> {
			modelMenu.getItems().removeAll(modelMenu.getItems());
			modelMenu.getItems().addAll(modelList);
			modelMenu.getItems().add(newModel);
		});

		Menu viewMenu = new Menu("View");

		MenuItem newView = new MenuItem("XNOT ADDEDX New...");
		newView.setOnAction(e -> newViewHandler());

		viewList = FXCollections.observableArrayList();

		viewMenu.getItems().add(newView);

		viewList.addListener((ListChangeListener.Change<? extends MenuItem> change) -> {
			viewMenu.getItems().removeAll(modelMenu.getItems());
			viewMenu.getItems().addAll(viewList);
			viewMenu.getItems().add(newView);
		});

		Menu controllerMenu = new Menu("Controller");

		MenuItem newController = new MenuItem("XNOT ADDEDX New...");
		newController.setOnAction(e -> newControllerHandler());

		controllerList = FXCollections.observableArrayList();

		controllerMenu.getItems().add(newController);

		controllerList.addListener((ListChangeListener.Change<? extends MenuItem> change) -> {
			controllerMenu.getItems().removeAll(controllerMenu.getItems());
			controllerMenu.getItems().addAll(controllerList);
			controllerMenu.getItems().add(newController);
		});

		menuBar.getMenus().addAll(projectMenu, modelMenu, viewMenu, controllerMenu);
	}

	public MenuBar getMenuBar() {
		return menuBar;
	}
	
	public TextField getProjectNameField() {
		return projectNameField;
	}
	
	public boolean classExists(String name) {
		boolean exists = false;
		for(MenuItem item : modelList)
			if(item.getText().equals(name))
				exists = false;
		return exists;
	}

	public void newModelHandler() {
		MenuItem model = new MenuItem();
		ModelArea mArea = new ModelArea(controller, this);
		model.setOnAction(e -> window.setScene(mArea.getArea()));

		model.setText(mArea.getNameField().getText());

		modelList.add(model);

		window.setScene(mArea.getArea());
	}
	
	public void deleteModel(String name) {
		boolean deleted = false;
		for (int i = 0; i < modelList.size() && !deleted; i++) {
			MenuItem item = modelList.get(i);
			if (name.equals(item.getText())) {
					modelList.remove(item);
					deleted = true;
			}
		}
		
		if(modelList.size() == 0)
			newModelHandler();
		
		modelList.get(0).fire();;
	}

	private void newViewHandler() {
		ViewArea vArea = new ViewArea(controller, this);

		MenuItem view = new MenuItem("Unnamed class");
		view.setOnAction(e -> window.setScene(vArea.getArea()));
	}

	private void newControllerHandler() {
		ControllerArea cArea = new ControllerArea(controller, this);

		MenuItem controller = new MenuItem("Unnamed class");
		controller.setOnAction(e -> window.setScene(cArea.getArea()));
	}

	public void updateMenus(String oldVal, String newVal) {
		for (MenuItem item : modelList)
			if (oldVal.equals(item.getText()))
					item.setText(newVal);
		for (MenuItem item : viewList)
			if (oldVal.equals(item.getText()))
					item.setText(newVal);
		for (MenuItem item : controllerList)
			if (oldVal.equals(item.getText()))
					item.setText(newVal);
	}

}
