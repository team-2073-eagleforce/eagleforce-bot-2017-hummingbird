package org.usfirst.frc.team2073.robot;

import java.net.DatagramPacket;
import java.net.DatagramSocket;

import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

public class Camera {
	private static Camera singleton_cw = new Camera();
	
	Camera(){}
	
	public static Camera getInstance(){
		return Camera.singleton_cw;
	}
	float turn;
	float turn1;
	float x1, x2, x3, x5;
	float pixelWidth, targetWidth, rectWidth;
//	float VIEW_ANGLE = (float) 34.25;
	float Distance;
	float VIEW_ANGLE=30;
	float pixelAngle=(float) .1875;
	double x4 = Math.tan(VIEW_ANGLE);
//	DigitalOutput CamLight = new DigitalOutput(8);
	
	
	public float getCameraNumbers(){
		try{
			DatagramSocket socket = new DatagramSocket(2073);
			//320 pixels 
			String s_coor = new String(); //receivePacket.getData());
			
			byte[] receiveData = new byte[24];
			DatagramPacket receivePacket = new DatagramPacket(receiveData, receiveData.length);
			socket.receive(receivePacket);
			s_coor = new String( receivePacket.getData());
			SmartDashboard.putString("s_coor", s_coor);
		  	SmartDashboard.putString("0, 3", s_coor.substring(0, 3));	
		  	x1 = Integer.parseInt(s_coor.substring(0, 3));
		  	x2 = Integer.parseInt(s_coor.substring(3, 6));
		  	SmartDashboard.putNumber("x1", x1);
		  	SmartDashboard.putNumber("x2", x2);
		  	socket.close();
		}catch(Exception ex){
			ex.printStackTrace();
		}
		
		return x1;
	}
	
	
	public float computeDistance() {
		try{
			DatagramSocket socket = new DatagramSocket(2073);
			//320 pixels 
			String s_coor = new String(); //receivePacket.getData());
			
			byte[] receiveData = new byte[24];
			DatagramPacket receivePacket = new DatagramPacket(receiveData, receiveData.length);
			socket.receive(receivePacket);
			s_coor = new String( receivePacket.getData());
			SmartDashboard.putString("s_coor", s_coor);
		  	SmartDashboard.putString("3, 6", s_coor.substring(3, 6));	
		  	x1 = Integer.parseInt(s_coor.substring(0, 3));
		  	x2 = Integer.parseInt(s_coor.substring(3, 6));
		  	SmartDashboard.putNumber("x1", x1);
		  	SmartDashboard.putNumber("x2", x2);
		  	socket.close();
		}catch(Exception ex){
			ex.printStackTrace();
		}
		
		return x2;
	}
	
//	Make New Aim with a center value of 165
	public float Aim(float CameraNumbers){
		turn = (CameraNumbers - 150)*pixelAngle;
		return turn;
	}
	
	public float AimOver(float CameraNumbers){
		turn1 = (CameraNumbers - 170)*pixelAngle;
		return turn1;
	}
}
