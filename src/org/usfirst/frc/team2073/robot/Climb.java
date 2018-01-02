package org.usfirst.frc.team2073.robot;

import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj.RobotState;
import edu.wpi.first.wpilibj.Timer;
import edu.wpi.first.wpilibj.Victor;



public class Climb {
	Victor RopeController = new Victor(5);//5 on main bot 
	Joystick cause = new Joystick(2);
	
	public void RopeUp(){
		RopeController.set(-1);
	}
	public void RopeStop(){
		RopeController.set(0);
	}
	public void RopeDown(){
		RopeController.set(1);
	}
	Thread Joe = new Thread(){
		public void run(){
			while (RobotState.isEnabled()||RobotState.isDisabled()){
				if(RobotState.isAutonomous()){
				}else{
					if(cause.getRawButton(4)){
						RopeDown();
					}else{
						RopeStop();
					}
				}
				Timer.delay(.005);
			}
		}
	};
}
