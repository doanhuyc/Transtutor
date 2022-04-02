package main.controller.distributor;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

import main.controller.Controller;
import main.controller.InputMenuController;
import main.controller.MainMenuController;
import main.controller.SearchController;
import main.model.Result;

public class ControllerDistributor {

	private final Map<String, Controller> registryController = new ConcurrentHashMap<>();
	private String controllerIndex;
	private Result result;

	public ControllerDistributor() {
		this.controllerIndex = InputMenuController.INDEX;
		this.result = new Result();

		registryController.put(InputMenuController.INDEX, new InputMenuController());
		registryController.put(MainMenuController.INDEX, new MainMenuController());
		registryController.put(SearchController.INDEX, new SearchController());
	}

	public void doAction() {
		while (!controllerIndex.equals(Controller.END)) {

			Controller controller = registryController.get(controllerIndex);

			if (controller == null) {
				throw new RuntimeException("Unregistered controller " + controllerIndex);
			}

			controllerIndex = controller.process(result);
		}
	}
}
