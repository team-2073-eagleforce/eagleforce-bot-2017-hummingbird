package org.usfirst.frc.team2073.robot;

import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj.Relay;
import edu.wpi.first.wpilibj.RobotState;
import edu.wpi.first.wpilibj.Solenoid;
import edu.wpi.first.wpilibj.Timer;
import edu.wpi.first.wpilibj.Victor;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

public class Intake {
	Victor In = new Victor(4);//Main robot uses 4
	Solenoid P1 = new Solenoid(2);
	Solenoid P2 = new Solenoid (3);
	Joystick everything = new Joystick(2);
	Joystick everything2 = new Joystick(0);
	Gear gea = new Gear();
	public void PistonOut (){
		P1.set(true);
		P2.set(false);
		
	}
	
	public void PistonIn (){
		P1.set(false);
		P2.set(true);
	}
	
	public void RStop (){
		In.set(0);
	}
	public void RStart (){
		In.set(-1);
	}
	public void RReverse (){
		In.set(1);
	}
	Thread Jim = new Thread(){
		public void run(){
			while (RobotState.isEnabled()||RobotState.isDisabled()){
				if (RobotState.isAutonomous()){
					if(SmartDashboard.getNumber("Spiced",0)==1){
						RStart();
					}else{
						RStop();
					}
				}else{ 
					if(everything.getRawButton(1)){
						RStart();
					}else if(everything2.getRawButton(2)){
						RStart();
					}else if(everything.getRawButton(8)){
						RReverse();
					}else{
						RStop();
					}
					if(everything.getRawButton(6)){
						PistonIn();
					}else{
						PistonOut();
					}
				}
				Timer.delay(.005);
			}
		}
	};
}
