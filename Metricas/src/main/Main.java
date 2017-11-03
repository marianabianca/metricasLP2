package main;

import controller.ControllerProjetos;

public class Main {
	
	static private ControllerProjetos controllerAlunos = new ControllerProjetos();
	
	public static void main(String[] args) {
		System.out.println(controllerAlunos.detectarOutliers());
	}

}
