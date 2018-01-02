package org.usfirst.frc.team2073.robot;

import edu.wpi.first.wpilibj.*;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

public class Drive {
	Victor left_motor =  new Victor(0);
	Victor left_motor2 =  new Victor(1);
	Victor right_motor =  new Victor(2);
	Victor right_motor2 =  new Victor(3);
	Joystick joystick = new Joystick(0);
	Joystick wheel = new Joystick(1);
	Encoder right_enc = new Encoder(2,3);//Main uses 1,2 and 3,4
	Encoder left_enc = new Encoder(4,5);
	Solenoid S1 = new Solenoid(0);
	Solenoid S2 = new Solenoid (1);
	ADXRS450_Gyro gyro = new ADXRS450_Gyro();
	double pre;
	
	public Drive(){
		left_enc.setDistancePerPulse(.01227);
		right_enc.setDistancePerPulse(.01227);
	}
	public void move (double left, double right){
//		left_motor2.set(speed);  GYRO CALIBRATIONS .08 and .04
		right_motor.set(right-gyro.getAngle()*.07);
		left_motor.set(-left-gyro.getAngle()*.07);
		left_motor2.set(-left-gyro.getAngle()*.07);
		right_motor2.set(right-gyro.getAngle()*.07);
		SmartDashboard.putNumber("Left Enc", left_enc.getDistance());
		SmartDashboard.putNumber("Right Enc", right_enc.getDistance());
		SmartDashboard.putNumber("gyro", gyro.getAngle());
	}
	public void turn(double speed){
		right_motor.set(speed);
		left_motor.set(speed);
		left_motor2.set(speed);
		right_motor2.set(speed);
	}
	public double TurnSense(double Ptart){
		double carp;
		carp = SmartDashboard.getNumber("Sense", .25)*Ptart*Ptart*Ptart + Ptart*(1-SmartDashboard.getNumber("Sense", .25));
		return carp;
	}
	public double Inverse(double Sstart){
		double inert;
		inert = (Sstart-pre)*SmartDashboard.getNumber("Inverse", .2)+Sstart;
		pre=Sstart;
		return inert;
	}
	
	public void point(double turn){
		right_motor.set(-turn);
		left_motor.set(-turn);
		right_motor2.set(-turn);
		left_motor2.set(-turn);
	}
	
	public void movewheel(double speed, double turn){
		right_motor.set(-(Inverse(speed) - (Inverse(speed)*TurnSense(turn))));
		left_motor.set(Inverse(speed) + (Inverse(speed)*TurnSense(turn)));
		right_motor2.set(-(Inverse(speed) - (Inverse(speed)*TurnSense(turn))));
		left_motor2.set(Inverse(speed) + (Inverse(speed)*TurnSense(turn)));
//		right_motor.set(speed);
//		left_motor.set(speed);
//		right_motor2.set(speed);
//		left_motor2.set(speed);  GYRO CALIBRATIONS .08 and .04
//		right_motor.set(-speed-gyro.getAngle()*SmartDashboard.getNumber("p",0));
//		left_motor.set(speed-gyro.getAngle()*SmartDashboard.getNumber("p",0));
//		left_motor2.set(speed-gyro.getAngle()*SmartDashboard.getNumber("p",0));
//		right_motor2.set(-speed-gyro.getAngle()*SmartDashboard.getNumber("p",0));

	}
	Thread Crossiant = new Thread(){
		public void run(){
			gyro.calibrate();
			gyro.reset();
			int i=1; 
			double peg=0;
			double direction=0;
			SmartDashboard.putNumber("Pineapple", 0);
			SmartDashboard.putNumber("Brownie", 0);
			SmartDashboard.putNumber("Spice", 0);
			SmartDashboard.putNumber("Spiced", 0);
			SmartDashboard.putNumber("Right or Left: 1,2",0);
			SmartDashboard.putNumber("Straight or Side: 1,2",0);
			SmartDashboard.putNumber("Inverse", .2);
			SmartDashboard.putNumber("Sense", .25);
			while (RobotState.isEnabled()||RobotState.isDisabled()){
				while(RobotState.isDisabled()&&RobotState.isAutonomous()){
					gyro.reset(); 
					SmartDashboard.putNumber("Pineapple", 0);
					SmartDashboard.putNumber("Brownie", 0);
					SmartDashboard.putNumber("Spice", 0);
					SmartDashboard.putNumber("Spiced", 0);
					SmartDashboard.putNumber("Left Enc", left_enc.getDistance());
					SmartDashboard.putNumber("Right Enc", right_enc.getDistance());
					SmartDashboard.putNumber("gyro", gyro.getAngle());
					left_enc.reset();
					right_enc.reset();
					gyro.reset();
					i=1;
					direction=SmartDashboard.getNumber("Right or Left: 1,2",0);
					peg=SmartDashboard.getNumber("Straight or Side: 1,2",0);
					Timer.delay(.005);
				}
				if (RobotState.isAutonomous()){
					
					// Move 12 inches forward
//					while(left_enc.getDistance()>=-12&&right_enc.getDistance()<=12&&i!=0){
//						move(.9,.9);
//						Timer.delay(.005);
//					}
//					move(0,0);
//					
//					left_enc.reset();
//					right_enc.reset();
//					Timer.delay(.01);
//					
//					// Move 9 inches back to drop gear
//					while(left_enc.getDistance()<=8&&right_enc.getDistance()>=-8&&i!=0){
//						move(-.9,-.9);
//						Timer.delay(.005);
//					}
//					
//					// Tell Gear class that the gear should be dropped now
//					SmartDashboard.putNumber("Pineapple", 1);
//					
//					// Stop moving
//					move(0,0);
//					left_enc.reset();
//					right_enc.reset();
//					Timer.delay(.30);
					
					
//					Start of Left side Auto
					if(direction==2&&peg==2){
					while(left_enc.getDistance()>=-82&&right_enc.getDistance()<=82&&i!=0){
						if(SmartDashboard.getNumber("Martha", 1)==0){
							move(.9,.9);
						}else{
							move(.9,.9);
						}
						
						Timer.delay(.005);
					}
					
					move(0,0);
					left_enc.reset();
					right_enc.reset();
					Timer.delay(.05);
					
					gyro.reset();
					while(gyro.getAngle()>=-45&&i!=0){
						turn(-.5);
						Timer.delay(.005);
						
					}
					
					gyro.reset();
					move(0,0);
					left_enc.reset();
					right_enc.reset();
					Timer.delay(.05);
					while(left_enc.getDistance()>=-10&&right_enc.getDistance()<=10&&i!=0){
						move(.9,.9);
						Timer.delay(.005);
					}
					move(0,0);
					
					left_enc.reset();
					right_enc.reset();
					Timer.delay(.01);
					int t = 0;
					while(t<=20&&i!=0){
						SmartDashboard.putNumber("Brownie", 1);
						t++;
						Timer.delay(.005);
					}
					Timer.delay(.01);
					
					// Move 7 inches back
					while(left_enc.getDistance()<=15&&right_enc.getDistance()>=-15&&i!=0){
						move(-.5,-.5);
						Timer.delay(.005);
						SmartDashboard.putNumber("Brownie", 2);
					}
					Timer.delay(.05);
					move(0,0);
					
					gyro.reset();
					
					while(gyro.getAngle()>=-180&&i!=0){
						turn(-.5);
						Timer.delay(.005);
						
					}
					gyro.reset();
					move(0,0);
					left_enc.reset();
					right_enc.reset();
					Timer.delay(.01);
					
					while(left_enc.getDistance()>=-62&&right_enc.getDistance()<=62&&i!=0){
						SmartDashboard.putNumber("Spice", 1);
						if(SmartDashboard.getNumber("Martha", 1)==0){
							move(.9,.9);
						}else{
							move(.9,.9);
						}
						
						Timer.delay(.005);
					}
					move(0,0);
					left_enc.reset();
					right_enc.reset();
					Timer.delay(.01);
					if(i!=0){
						SmartDashboard.putNumber("Spiced", 1);
						Timer.delay(4);
					}
					gyro.reset();
					move(0,0);
					left_enc.reset();
					right_enc.reset();
					gyro.reset();
					Timer.delay(.05);
					
					// Stop shooting and turn to prpare for backup
					while(gyro.getAngle()<=35&&i!=0){
						SmartDashboard.putNumber("Spice", 0);
						SmartDashboard.putNumber("Spiced", 0);
						turn(.5);
						Timer.delay(.005);
						
					}
					move(0,0);
					left_enc.reset();
					right_enc.reset();
					gyro.reset();
					
					// Move backwards to cross line
					while(left_enc.getDistance()<=40&&right_enc.getDistance()>=-40&&i!=0){
						move(-.9,-.9);
						Timer.delay(.005);
					}
					move(0,0);
					left_enc.reset();
					right_enc.reset();
					gyro.reset();
					
					
//					Start of Straight left Auto
			}else if(direction==2&&peg==1){		
				while(left_enc.getDistance()>=-66&&right_enc.getDistance()<=66&&i!=0){
					if(SmartDashboard.getNumber("Martha", 1)==0){
						move(.9,.9);
					}else{
						move(.9,.9);
					}
				Timer.delay(.005);
			}
			move(0,0);
			left_enc.reset();
			right_enc.reset();
			Timer.delay(.05);
			
			// Pauses so gear can be placed on peg
			int t = 0;
			while(t<=20&&i!=0){
				SmartDashboard.putNumber("Brownie", 1);
				t++;
				Timer.delay(.005);
			}
			Timer.delay(.01);
			
			// Move 7 inches back
			while(left_enc.getDistance()<=23&&right_enc.getDistance()>=-23&&i!=0){
				move(-.5,-.5);
				Timer.delay(.005);
				SmartDashboard.putNumber("Brownie", 2);
			}
			Timer.delay(.05);
			move(0,0);
			
			// Turn right 90 degrees
			gyro.reset();
			while(gyro.getAngle()<=85&&i!=0){
				turn(.5);
				Timer.delay(.005);
				
			}
			left_enc.reset();
			move(0,0);
			right_enc.reset();
			gyro.reset();
			Timer.delay(.01);
			
			
			// Move forward 106 inches
			gyro.reset();
			while(left_enc.getDistance()>=-106&&right_enc.getDistance()<=106&&i!=0){
				move(.8,.8);
				Timer.delay(.005);
			}
			move(0,0);
			
			// Turn to face basket and tell Shoot class to start spinning up the shooter wheel
			gyro.reset();
			Timer.delay(.05);
			while(gyro.getAngle()<=30&&i!=0){
				SmartDashboard.putNumber("Spice", 2);
				turn(.5);
				Timer.delay(.005);
				
			}
			
			gyro.reset();
			move(0,0);
			left_enc.reset();
			right_enc.reset();
			Timer.delay(.01);
			// Tell Shoot class to start loading balls into the shooter
			if(i!=0){
				SmartDashboard.putNumber("Spiced", 1);
				Timer.delay(4);
			}
			gyro.reset();
			move(0,0);
			left_enc.reset();
			right_enc.reset();
			gyro.reset();
			Timer.delay(.05);
			
			// Stop shooting and turn to prpare for backup
//			while(gyro.getAngle()<=35&&i!=0){
				SmartDashboard.putNumber("Spice", 0);
				SmartDashboard.putNumber("Spiced", 0);
				turn(.5);
				Timer.delay(.005);
				
//			}
			move(0,0);
			left_enc.reset();
			right_enc.reset();
			gyro.reset();
			
			// Move backwards to cross line
//			while(left_enc.getDistance()<=50&&right_enc.getDistance()>=-50&&i!=0){
//				move(-.9,-.9);
//				Timer.delay(.005);
//			}
			move(0,0);
			left_enc.reset();
			right_enc.reset();
			gyro.reset();
					
			}else if(direction==1&&peg==2){
//					Start of Right side Auto
					while(left_enc.getDistance()>=-84&&right_enc.getDistance()<=84&&i!=0){
						if(SmartDashboard.getNumber("Martha", 1)==0){
							move(.9,.9);
						}else{
							move(.9,.9);
						}
						
						Timer.delay(.005);
					}
					
					move(0,0);
					left_enc.reset();
					right_enc.reset();
					Timer.delay(.05);
					
					gyro.reset();
					while(gyro.getAngle()<=45&&i!=0){
						turn(.5);
						Timer.delay(.005);
						
					}
					
					gyro.reset();
					move(0,0);
					left_enc.reset();
					right_enc.reset();
					Timer.delay(.05);
					while(left_enc.getDistance()>=-10&&right_enc.getDistance()<=10&&i!=0){
						move(.9,.9);
						Timer.delay(.005);
					}
					move(0,0);
					
					left_enc.reset();
					right_enc.reset();
					Timer.delay(.01);
					int t = 0;
					while(t<=20&&i!=0){
						SmartDashboard.putNumber("Brownie", 1);
						t++;
						Timer.delay(.005);
					}
					Timer.delay(.01);
					
					// Move 7 inches back
					while(left_enc.getDistance()<=15&&right_enc.getDistance()>=-15&&i!=0){
						move(-.5,-.5);
						Timer.delay(.005);
						SmartDashboard.putNumber("Brownie", 2);
					}
					Timer.delay(.05);
					move(0,0);
					gyro.reset();
					
					while(gyro.getAngle()<=172&&i!=0){
						turn(.5);
						Timer.delay(.005);
						
					}
					gyro.reset();
					move(0,0);
					left_enc.reset();
					right_enc.reset();
					Timer.delay(.05);
					
					while(left_enc.getDistance()>=-56&&right_enc.getDistance()<=56&&i!=0){
						SmartDashboard.putNumber("Spice", 1);
						if(SmartDashboard.getNumber("Martha", 1)==0){
							move(.9,.9);
						}else{
							move(.9,.9);
						}
						
						Timer.delay(.005);
					}
					move(0,0);
					left_enc.reset();
					right_enc.reset();
					Timer.delay(.01);
					if(i!=0){
						SmartDashboard.putNumber("Spiced", 1);
						Timer.delay(4);
					}
					gyro.reset();
					move(0,0);
					left_enc.reset();
					right_enc.reset();
					gyro.reset();
					Timer.delay(.05);
					
					// Stop shooting and turn to prpare for backup
//					while(gyro.getAngle()>=-35&&i!=0){
						SmartDashboard.putNumber("Spice", 0);
						SmartDashboard.putNumber("Spiced", 0);
//						turn(-.5);
//						Timer.delay(.005);
//						
//					}
					move(0,0);
					left_enc.reset();
					right_enc.reset();
					gyro.reset();
					
					// Move backwards to cross line
//					while(left_enc.getDistance()<=40&&right_enc.getDistance()>=-40&&i!=0){
//						move(-.9,-.9);
//						Timer.delay(.005);
//					}
					move(0,0);
					left_enc.reset();
					right_enc.reset();
					gyro.reset();
					
					
					
					
					
//					 Move 43 inches forward
//					Start of Straight Right Auto
			}else if(direction==1 && peg==1){
					while(left_enc.getDistance()>=-66&&right_enc.getDistance()<=66&&i!=0){
						if(SmartDashboard.getNumber("Martha", 1)==0){
							move(.9,.9);
						}else{
							move(.9,.9);
						}
						
						Timer.delay(.005);
					}
					move(0,0);
					left_enc.reset();
					right_enc.reset();
					Timer.delay(.05);
					
					// Pauses so gear can be placed on peg
					int t = 0;
					while(t<=20&&i!=0){
						SmartDashboard.putNumber("Brownie", 1);
						t++;
						Timer.delay(.005);
					}
					Timer.delay(.01);
					
					// Move 7 inches back
					while(left_enc.getDistance()<=23&&right_enc.getDistance()>=-23&&i!=0){
						move(-.5,-.5);
						Timer.delay(.005);
						SmartDashboard.putNumber("Brownie", 2);
					}
					Timer.delay(.05);
					move(0,0);
					
					// Turn right 90 degrees
					gyro.reset();
					while(gyro.getAngle()>=-90&&i!=0){
						turn(-.5);
						Timer.delay(.005);
						
					}
					left_enc.reset();
					move(0,0);
					right_enc.reset();
					gyro.reset();
					Timer.delay(.01);
					
					
					// Move forward 106 inches
					gyro.reset();
					while(left_enc.getDistance()>=-106&&right_enc.getDistance()<=106&&i!=0){
						move(.8,.8);
						Timer.delay(.005);
					}
					move(0,0);
					
					// Turn to face basket and tell Shoot class to start spinning up the shooter wheel
					gyro.reset();
					Timer.delay(.05);
					while(gyro.getAngle()>=-27.5&&i!=0){
						SmartDashboard.putNumber("Spice", 1);
						turn(-.5);
						Timer.delay(.005);
						
					}
					
					gyro.reset();
					move(0,0);
					left_enc.reset();
					right_enc.reset();
					
					// Tell Shoot class to start loading balls into the shooter
					if(i!=0){
						SmartDashboard.putNumber("Spiced", 1);
						Timer.delay(4);
					}
					gyro.reset();
					move(0,0);
					left_enc.reset();
					right_enc.reset();
					gyro.reset();
					Timer.delay(.05);
					
//					while(left_enc.getDistance()<=24&&right_enc.getDistance()>=-24&&i!=0){
//						move(-.5,-.5);
//						Timer.delay(.005);
//						SmartDashboard.putNumber("Brownie", 2);
//					}
					
					gyro.reset();
					move(0,0);
					left_enc.reset();
					right_enc.reset();
					gyro.reset();
					Timer.delay(.05);
					// Stop shooting and turn to prpare for backup
//					while(gyro.getAngle()>=-35&&i!=0){
						SmartDashboard.putNumber("Spice", 0);
						SmartDashboard.putNumber("Spiced", 0);
//						turn(-.5);
//						Timer.delay(.005);
//						
//					}
					move(0,0);
					left_enc.reset();
					right_enc.reset();
					gyro.reset();
					
					// Move backwards to cross line
//					while(left_enc.getDistance()<=30&&right_enc.getDistance()>=-30&&i!=0){
//						move(-.9,-.9);
//						Timer.delay(.005);
//					}
			}
					move(0,0);
					left_enc.reset();
					right_enc.reset();
					gyro.reset();
					i=0;
					//TODO: Auto
				}else{
					if(wheel.getRawButton(9)){
						point(wheel.getX());
					}else{
						movewheel(joystick.getY(), wheel.getX());
					}
					if(joystick.getRawButton(4)){
						S1.set(true);
						S2.set(false);
					}else{
						S1.set(false);
						S2.set(true);
					}
					
					SmartDashboard.putNumber("Gyro", gyro.getAngle());
					SmartDashboard.putNumber("Right Abs",right_enc.get());
					SmartDashboard.putNumber("Left Rel",left_enc.getDistance());
				}
				Timer.delay(.005);
			}
		
		}
	};

}




	
