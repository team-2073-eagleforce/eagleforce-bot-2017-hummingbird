package org.usfirst.frc.team2073.robot;

import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj.Relay;
import edu.wpi.first.wpilibj.RobotState;
import edu.wpi.first.wpilibj.Timer;
import edu.wpi.first.wpilibj.Victor;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

public class Intermediate {
	Victor Motor1 = new Victor(7);//7 on the main bot
	Joystick kappa = new Joystick(0);
	Joystick KPA = new Joystick(2);
	Relay pop = new Relay(0);
	Intake in = new Intake();
	public void Spin(){
		
		Motor1.set(-SmartDashboard.getNumber("inter",.6));
		
	}
	
	public void Load(){
		Motor1.set(.5);
	}
	
	public void Stop(){
		
		Motor1.set(0);
		
	}
	
	Thread Baguette = new Thread(){
		public void run(){
			SmartDashboard.putNumber("inter",.6);
			while (RobotState.isEnabled()||RobotState.isDisabled()){
				if(RobotState.isAutonomous()){
					if(SmartDashboard.getNumber("Spiced",0)==1){
						Spin();
						pop.set(Relay.Value.kReverse);
					}else{
						Stop();
						pop.set(Relay.Value.kOff);
					}
				}else{
					if(kappa.getRawButton(2)){
						Spin();
						pop.set(Relay.Value.kReverse);
					}else if(KPA.getRawButton(1)){
						Load();
					}else{
						Stop();
						pop.set(Relay.Value.kOff);
					}
				}
				Timer.delay(.005);
			}
		}
	};
	
}


