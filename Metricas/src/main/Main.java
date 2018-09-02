package main;

import java.io.IOException;

import controller.ControllerProjetos;

public class Main {
	
	private static ControllerProjetos controllerProjetos = new ControllerProjetos();
	
	public static void main(String[] args) throws IOException {
		System.out.println(controllerProjetos.detectarOutliers());
	}

}
