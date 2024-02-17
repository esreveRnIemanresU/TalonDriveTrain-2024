// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.subsystems;

import edu.wpi.first.wpilibj.XboxController;
import edu.wpi.first.wpilibj.drive.DifferentialDrive;
import edu.wpi.first.wpilibj2.command.SubsystemBase;
import com.ctre.phoenix.motorcontrol.ControlMode;
import com.ctre.phoenix.motorcontrol.can.WPI_TalonSRX;


public class DriveTrain extends SubsystemBase {
  private final WPI_TalonSRX m_leftDrive = new WPI_TalonSRX(0);
  private final WPI_TalonSRX m_leftFollower = new WPI_TalonSRX(1);
  private final WPI_TalonSRX m_rightDrive = new WPI_TalonSRX(2);
  private final WPI_TalonSRX m_rightFollower = new WPI_TalonSRX(3);
  private final DifferentialDrive m_drive;
  private final XboxController m_controller;

  public DriveTrain() {
    m_leftDrive.setInverted(false);
    m_leftFollower.setInverted(false);
    m_rightDrive.setInverted(true);
    m_rightFollower.setInverted(true);
    m_leftFollower.set(ControlMode.Follower, 0);
    m_rightFollower.set(ControlMode.Follower, 2);
    m_drive = new DifferentialDrive(m_leftDrive, m_rightDrive);
    m_controller = new XboxController(0);
  }

  public void arcadeDrive(double fwd, double rot, boolean squaredInput) {
    // Swap the next two lines for filtered/raw input
    m_drive.arcadeDrive(-m_controller.getLeftY(), -m_controller.getRightX(), squaredInput);
    //m_drive.arcadeDrive(OI.deadZone(f_fwd), OI.deadZone(f_rot), squaredInput);
    }

  @Override
  public void periodic() {
    // This method will be called once per scheduler run
    m_drive.arcadeDrive(-m_controller.getLeftY(), -m_controller.getRightX(), true);
  }
  
  @Override
  public void simulationPeriodic() {
    // This method will be called once per scheduler run during simulation
  }
}