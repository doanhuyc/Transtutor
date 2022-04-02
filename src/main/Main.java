package main;

import main.controller.distributor.ControllerDistributor;

public class Main {
	private static final ControllerDistributor controllerDistributor = new ControllerDistributor();

	public static void main(String[] args) {
		controllerDistributor.doAction();
	}
}
