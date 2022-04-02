package main.controller.distributor;

import java.util.ArrayList;
import java.util.List;

import main.controller.ChangeStatusController;
import main.controller.Controller;
import main.controller.InputMenuController;
import main.controller.MainMenuController;
import main.controller.PrintByStatusController;
import main.controller.RecordMenuController;
import main.controller.SearchController;
import main.service.FileReaderService;
import main.service.InventoryFileBuilderService;
import main.service.InventoryService;
import main.service.Service;
import main.service.dao.Repository;

public class ControllerDistributor {

	private final List<Service> registryService;
	private final List<Controller> registryController;
	private Class controllerIndex;

	public ControllerDistributor() {
		this.controllerIndex = InputMenuController.class;
		this.registryService = new ArrayList<>();
		this.registryController = new ArrayList<>();

		initServices();
		initController();
	}

	public void doAction() {
		createFile();

		while (controllerIndex != null) {
			controllerIndex = getController().process();
		}
	}

	private void createFile() {
		InventoryFileBuilderService inventoryFileBuilderService = getService(InventoryFileBuilderService.class);
		inventoryFileBuilderService.createFile();
	}

	private Controller getController() {
		for (Controller controller : registryController) {
			if (controller.getClass() == controllerIndex) {
				return controller;
			}
		}
		throw new RuntimeException("Unregistered controller " + controllerIndex);
	}

	private <E extends Service> E getService(Class<E> clazz) {
		for (Service service : registryService) {
			if (service.getClass() == clazz) {
				return clazz.cast(service);
			}
		}
		throw new RuntimeException("Unregistered Service " + clazz);
	}

	private void initServices() {
		registryService.add(new FileReaderService());
		registryService.add(new InventoryFileBuilderService());
		registryService.add(new InventoryService(new Repository()));
		registryService.add(new InventoryFileBuilderService());
	}

	private void initController() {
		InventoryService inventoryService = getService(InventoryService.class);
		FileReaderService fileReaderService = getService(FileReaderService.class);

		registryController.add(new InputMenuController(fileReaderService, inventoryService));
		registryController.add(new MainMenuController(inventoryService));
		registryController.add(new SearchController(inventoryService));
		registryController.add(new RecordMenuController(inventoryService));
		registryController.add(new ChangeStatusController(inventoryService));
		registryController.add(new SearchController(inventoryService));
		registryController.add(new PrintByStatusController(inventoryService));
	}
}
