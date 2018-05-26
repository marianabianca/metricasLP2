package main;

import controller.ControllerProjetos;

public class Main {
	
	private static ControllerProjetos controllerProjetos = new ControllerProjetos();
	
	public static void main(String[] args) {
		System.out.println(controllerProjetos.detectarOutliers());
	}

}
