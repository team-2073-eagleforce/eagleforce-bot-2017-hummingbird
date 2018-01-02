package org.usfirst.frc.team2073.robot;

import com.ctre.CANTalon;
import com.ctre.CANTalon.TalonControlMode;

import edu.wpi.first.wpilibj.DigitalInput;
import edu.wpi.first.wpilibj.GenericHID;
import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj.RobotBase;
import edu.wpi.first.wpilibj.RobotState;
import edu.wpi.first.wpilibj.Timer;
import edu.wpi.first.wpilibj.Victor;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

public class Gear {
	Victor Out = new Victor(6);// 6 on the main bot
	Joystick something = new Joystick(2);
	Joystick rum = new Joystick(1);
	CANTalon Falcon = new CANTalon(4);
	DigitalInput zero = new DigitalInput(6);// Main = 0
	DigitalInput stop = new DigitalInput(0);//Main are 5 and 6
//	DigitalInput stop1 = new DigitalInput(5);
	double Position;
	int lol=0;
	
	public void GearSetup(){
		int absPosition = Falcon.getPulseWidthPosition() & 0xFFF;
		Falcon.setEncPosition(absPosition);
		SmartDashboard.putNumber("Position", Position);
		SmartDashboard.putNumber("Error", 4);
		Falcon.reverseSensor(true);
		Falcon.configEncoderCodesPerRev(3);
		Falcon.configNominalOutputVoltage(+0f, -0f);
		Falcon.configPeakOutputVoltage(+5f, -5f);
		Falcon.setAllowableClosedLoopErr(4);
		Falcon.setP(30);
		Falcon.setI(SmartDashboard.getNumber("Set i", 0));
		Falcon.setD(100);
		Falcon.setProfile(0);
		
	}
	
	public void Intake(){
//		Position = 25;
//		Falcon.changeControlMode(TalonControlMode.Position);
//		Falcon.set(Position);
	}
	
	public void Reset(){
//		Position = 0;
//		Falcon.changeControlMode(TalonControlMode.Position);
//		Falcon.set(Position);
	}
	
	public void Outtake(){
//		Position = 12.5;
//		Falcon.changeControlMode(TalonControlMode.Position);
//		Falcon.set(Position);
	}
		
	
	public void VStop(){
		Out.set(0);
	}
	public void VForward(){
		Out.set(1);//Positive
	}
	public void VReverse(){
		Out.set(-1);//Negative
	}
	Thread Nathan = new Thread(){
		public void run(){
			GearSetup();
			int j=0;
			while (RobotState.isEnabled()||RobotState.isDisabled()){
				if(RobotState.isAutonomous()){
					
					if(zero.get()==false){
						Falcon.setEncPosition(0);
					}
					if(SmartDashboard.getNumber("Pineapple", 0)==1&&stop.get()==false&&SmartDashboard.getNumber("Brownie", 0)==0){
						
						SmartDashboard.putNumber("Martha", 0);
						Falcon.changeControlMode(TalonControlMode.Position);
						Falcon.setP(30);
						Falcon.set(20);//negative
						VForward();
						j=1;
					}else if(SmartDashboard.getNumber("Brownie", 0)==1){
						
						Falcon.changeControlMode(TalonControlMode.Position);
						Falcon.setP(30);
						
						Falcon.set(7);//negative
						Out.set(-1);//negative
					}else{
						VStop();
						SmartDashboard.putNumber("Martha", 1);
						Falcon.changeControlMode(TalonControlMode.Position);
//						Out.set(.3);
						Falcon.setP(10);
						Falcon.set(0);
					}
					SmartDashboard.putNumber("Position", Falcon.getPosition());
				}else{
					if(something.getRawButton(3)&&stop.get()==false){//&&stop1.get()==false){
						VForward();
					}else if(something.getRawButton(2)){
						VReverse();
					}else if(stop.get()) {
						Out.set(.3);//positive on main
					}else{
						VStop();
					}
					if(stop.get()){
						something.setRumble(GenericHID.RumbleType.kLeftRumble, 1);
						something.setRumble(GenericHID.RumbleType.kRightRumble, 1);
						rum.setRumble(GenericHID.RumbleType.kLeftRumble, 1);
						rum.setRumble(GenericHID.RumbleType.kRightRumble, 1);
						
					}else{
						something.setRumble(GenericHID.RumbleType.kLeftRumble, 0);
						something.setRumble(GenericHID.RumbleType.kRightRumble, 0);
						rum.setRumble(GenericHID.RumbleType.kLeftRumble, 0);
						rum.setRumble(GenericHID.RumbleType.kRightRumble, 0);
					}
					SmartDashboard.putNumber("EncoderG", Falcon.getEncPosition());
					if(something.getPOV()==180||something.getPOV()==135){
						Falcon.changeControlMode(TalonControlMode.Position);
						Falcon.setP(30);
						Falcon.set(22);//20.5 //Negative on the main bot
					}else if(something.getPOV()==90){
						Falcon.changeControlMode(TalonControlMode.Position);
						Falcon.setP(30);
						Falcon.set(7);//Negative on the main bot
					}else if(zero.get()==false&&something.getPOV()==0){
						Falcon.changeControlMode(TalonControlMode.Position);
						Falcon.setP(10);
						Falcon.set(-100);//Positive on the main bot
					}else{
						Falcon.changeControlMode(TalonControlMode.Position);
						Falcon.setP(20);
						Falcon.set(0);
					}
					if(zero.get()==false){
						Falcon.setEncPosition(0);
					}
//					Falcon.setP(SmartDashboard.getNumber("Set p", 30));
//					Falcon.setI(SmartDashboard.getNumber("Set i", 0));
//					Falcon.setD(SmartDashboard.getNumber("Set d", 100));
//					Falcon.setAllowableClosedLoopErr((int) SmartDashboard.getNumber("Error", 4));
					SmartDashboard.putNumber("Position", Falcon.getPosition());
				}
				Timer.delay(.005);
			}
		}
	};
}
