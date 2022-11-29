package frc.robot.subsystems;

import com.ctre.phoenix.motorcontrol.ControlMode;
import com.ctre.phoenix.motorcontrol.can.TalonFX;
import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.constants;
import frc.robot.constants.IDs;

public class Elevator extends SubsystemBase{
    public TalonFX climbCultLeader = new TalonFX(IDs.Elevator.climbCultLeaderMotor);
    public TalonFX climbCultist = new TalonFX(IDs.Elevator.climbCultistMotor);
    public double encoderBottom;
    public Elevator(){
        climbCultist.follow(climbCultLeader);

        //MAKE SURE ELEVATOR AT BOTTOM... OR ELSE!!!
        
        encoderBottom = climbCultLeader.getSelectedSensorPosition();
    }

    public void moveElevator(double speed){
        var sensorPos = climbCultLeader.getSelectedSensorPosition();

        //2048 is cpr for talon
        if(sensorPos <= encoderBottom || (Math.abs(sensorPos-encoderBottom)/2048) >= constants.elevatorRotationsToTravel)
        {
            climbCultLeader.set(ControlMode.PercentOutput, 0);
        }
        else{
            climbCultLeader.set(ControlMode.PercentOutput, speed);
        }
    }
}
