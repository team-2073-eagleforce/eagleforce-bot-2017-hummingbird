package org.usfirst.frc.team2073.robot;

import edu.wpi.cscore.UsbCamera;
import edu.wpi.first.wpilibj.CameraServer;
import edu.wpi.first.wpilibj.SampleRobot;

public class Robot extends SampleRobot {
	Shoot launch = new Shoot();
	Drive dear = new Drive();
	Climb lift = new Climb();
	
	
	public Robot() {
		
	}

	
	
	public void robotInit() {
		UsbCamera camera = CameraServer.getInstance().startAutomaticCapture();
		camera.setFPS(20);
		camera.setResolution(320, 240);
		dear.Crossiant.start();
		launch.mid.Baguette.start();
		launch.mid.in.Jim.start();
		launch.Setup();
		launch.Shoot.start();
		launch.mid.in.gea.Nathan.start();
		lift.Joe.start();
	}

	
	public void autonomous() {
		
	}

	public void operatorControl() {
		
	}

	public void test() {
	}
}
