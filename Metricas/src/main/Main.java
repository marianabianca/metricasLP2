package main;

import controller.ControllerProjetos;

public class Main {
	
	static private ControllerProjetos controllerProjetos = new ControllerProjetos();
	
	public static void main(String[] args) {
		System.out.println(controllerProjetos.detectarOutliers());
	}

}
