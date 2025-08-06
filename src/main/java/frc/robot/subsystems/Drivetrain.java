// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.subsystems;

import com.revrobotics.spark.SparkMax;
import com.revrobotics.spark.SparkBase.PersistMode;
import com.revrobotics.spark.SparkBase.ResetMode;
import com.revrobotics.spark.SparkLowLevel.MotorType;
import com.revrobotics.spark.config.SparkBaseConfig;
import com.revrobotics.spark.config.SparkMaxConfig;

import edu.wpi.first.wpilibj.motorcontrol.Spark;
import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.Constants;

public class Drivetrain extends SubsystemBase {

  private final SparkMax leftFront = new SparkMax(Constants.DrivetrainConstants.leftFrontMotorID, MotorType.kBrushed);//4 for other bot
  private final SparkMax leftBack = new SparkMax(Constants.DrivetrainConstants.leftBackMotorID, MotorType.kBrushed);//3 for wide bot
  private final SparkMax rightFront = new SparkMax(Constants.DrivetrainConstants.rightFrontMotorID, MotorType.kBrushed);//2 for wide bot
  private final SparkMax rightBack = new SparkMax(Constants.DrivetrainConstants.rightBackMotorID, MotorType.kBrushed);//1 for wide bot

  /** Creates a new Drivetrain. */
  public Drivetrain() {
    SparkMaxConfig configLeftFront = new SparkMaxConfig();
    SparkMaxConfig configLeftBack = new SparkMaxConfig();
    SparkMaxConfig configRightFront = new SparkMaxConfig();
    SparkMaxConfig configRightBack = new SparkMaxConfig();

    configRightBack.follow(Constants.DrivetrainConstants.rightFrontMotorID);
    configLeftBack.follow(Constants.DrivetrainConstants.leftFrontMotorID);
    
    leftFront.configure(configLeftFront, ResetMode.kResetSafeParameters, PersistMode.kPersistParameters);
    leftBack.configure(configLeftBack, ResetMode.kResetSafeParameters, PersistMode.kPersistParameters);
    rightFront.configure(configRightFront, ResetMode.kResetSafeParameters, PersistMode.kPersistParameters);
    rightBack.configure(configRightBack, ResetMode.kResetSafeParameters, PersistMode.kPersistParameters);

    //rightBack.follow(rightFront);
    //leftBack.follow(leftFront);
  }

  public void drive(double speed, double turn){
    double left = (speed + turn) * 0.3;
    double right = (speed - turn) * 0.3;

    leftFront.set(left);
    rightFront.set(-right);
  }

  public void stop(){
    drive(0, 0);
  }


  @Override
  public void periodic() {
    // This method will be called once per scheduler run
  }
}
