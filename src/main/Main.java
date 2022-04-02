package main;

import java.io.FileWriter;
import java.io.IOException;

import main.controller.distributor.ControllerDistributor;

public class Main {
	private static final ControllerDistributor controllerDistributor = new ControllerDistributor();

	public static void main(String[] args) {
		createInventoryFile();

		controllerDistributor.doAction();
	}

	private static void createInventoryFile() {
		try {
			FileWriter myWriter = new FileWriter("src/inventoryFile.txt");

			for (int i = 0; i < 100; i++) {
				myWriter.write("9971xStroLLer 25 134.78");
				myWriter.write("\t");
			}

			/*for (int i = 0; i < Integer.MAX_VALUE; i++) {
				myWriter.write("9971xStroLLer 25 134.78");
			}*/
			myWriter.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}
