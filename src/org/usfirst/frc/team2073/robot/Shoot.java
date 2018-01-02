package org.usfirst.frc.team2073.robot;

import com.ctre.CANTalon;
import com.ctre.CANTalon.FeedbackDevice;
import com.ctre.CANTalon.TalonControlMode;

import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj.RobotState;
import edu.wpi.first.wpilibj.Timer;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

public class Shoot {
	CANTalon fire = new CANTalon(3);//Swap the fires for comp 1 is main
	CANTalon fire2 = new CANTalon(1);
	Joystick fun = new Joystick(0);
	Intermediate mid = new Intermediate();
	StringBuilder _sb = new StringBuilder();
	double f=4.0,p=30,i,d=170,RPM=4000;
	
	public void Setup(){
		fire.setFeedbackDevice(FeedbackDevice.QuadEncoder);
        fire.reverseSensor(true);
        fire.configEncoderCodesPerRev(3);
        SmartDashboard.putNumber("Set F", f);
        SmartDashboard.putNumber("Set p", p);
        SmartDashboard.putNumber("Set i", i);
        SmartDashboard.putNumber("Set d", d);
        fire.configNominalOutputVoltage(+0.0f, -0.0f);
        fire.configPeakOutputVoltage(+12.0f, 0.0f);
        fire.setProfile(0);
        fire.setF(4.0);
        fire.setP(10);
        fire.setI(0);
        fire.setD(170);
        SmartDashboard.putNumber("RPM",RPM);
    	fire2.changeControlMode(TalonControlMode.Follower);
    	fire2.set(fire.getDeviceID());
	}
	
	public void Shot(){
		fire.changeControlMode(TalonControlMode.Speed);
		double targetSpeed = SmartDashboard.getNumber("RPM", RPM);
		fire.set(targetSpeed);//In RPM
		fire.setF(SmartDashboard.getNumber("Set F", f));
        fire.setP(SmartDashboard.getNumber("Set p", p));
        fire.setI(SmartDashboard.getNumber("Set i", i));
        fire.setD(SmartDashboard.getNumber("Set d", d));
        
//		fire.changeControlMode(TalonControlMode.PercentVbus);
//		fire.set(.1);
		
	}
//	1.389:1
	 public void ss(){
		fire.changeControlMode(TalonControlMode.Speed);
		fire.set(0);
		fire.setF(SmartDashboard.getNumber("Set F", f));
        fire.setP(SmartDashboard.getNumber("Set p", p));
        fire.setI(SmartDashboard.getNumber("Set i", i));
        fire.setD(SmartDashboard.getNumber("Set d", d));
        
//		fire.changeControlMode(TalonControlMode.PercentVbus);
//		fire.set(0);
	 }
	 
	Thread Shoot = new Thread(){
		public void run(){
			Setup();
			while (RobotState.isEnabled()||RobotState.isDisabled()){
				if(RobotState.isAutonomous()){
					if(SmartDashboard.getNumber("Spice",0)==1){
						fire.changeControlMode(TalonControlMode.Speed);
						fire.set(4250);//In RPM
					}else if(SmartDashboard.getNumber("Spice",0)==2){
						fire.changeControlMode(TalonControlMode.Speed);
						fire.set(4450);//In RPM
					}else{
						ss();
					}
				}else{
					fire.setF(SmartDashboard.getNumber("Set F", f));
			        fire.setP(SmartDashboard.getNumber("Set p", p));
			        fire.setI(SmartDashboard.getNumber("Set i", i));
			        fire.setD(SmartDashboard.getNumber("Set d", d));
//			        
		        	SmartDashboard.putNumber("RPMTheory", fire.getSpeed());
		        	SmartDashboard.putNumber("RPMreal", fire.getSpeed()/1.38888888889);
					if(fun.getRawButton(1)){
						Shot();
					}else{
						ss();
					}
				}
				Timer.delay(.005);
			}
		}
	};
	
	public void ShootS(){
		Shoot.start();
	}
}

