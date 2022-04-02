package main.controller.distributor;

import java.util.ArrayList;
import java.util.List;

import main.controller.Controller;
import main.controller.InputMenuController;
import main.controller.MainMenuController;
import main.controller.QuitController;
import main.controller.SearchController;
import main.model.Result;

public class ControllerDistributor {

	private final List<Controller> registryController = new ArrayList<>();
	private Class controllerIndex;
	private Result result;

	public ControllerDistributor() {
		this.controllerIndex = InputMenuController.class;
		this.result = new Result();

		registryController.add(new InputMenuController());
		registryController.add(new MainMenuController());
		registryController.add(new SearchController());
	}

	public void doAction() {
		while (controllerIndex != QuitController.class) {
			controllerIndex = getController().process(result);
		}
	}

	private Controller getController() {
		for (Controller controller : registryController) {
			if (controller.getClass() == controllerIndex) {
				return controller;
			}
		}
		throw new RuntimeException("Unregistered controller " + controllerIndex);
	}
}
