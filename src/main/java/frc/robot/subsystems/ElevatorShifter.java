package frc.robot.subsystems;
import edu.wpi.first.wpilibj.DoubleSolenoid;
import edu.wpi.first.wpilibj.PneumaticsModuleType;
import edu.wpi.first.wpilibj.DoubleSolenoid.Value;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.constants.IDs;

public class ElevatorShifter extends SubsystemBase {
    private DoubleSolenoid shift = 
    new DoubleSolenoid(IDs.PneumaticsHub, PneumaticsModuleType.REVPH, IDs.Elevator.elevatorShiftForwardChannel, IDs.Elevator.elevatorShiftReverseChannel);
  
  public ElevatorShifter() {
    shift.set(Value.kReverse);
  }
  
  public void setSpeed(){
    shift.set(Value.kReverse);
    //SmartDashboard.putBoolean("InSpeed?", true);
  }
  
  public void setTorque(){
    shift.set(Value.kForward);
    //SmartDashboard.putBoolean("InSpeed?", false);
  
  }
  
  public void setOff(){
    shift.set(Value.kOff);
  }
  
  public boolean isInSpeed(){
    if(shift.get().equals(Value.kForward)){
      return true;
    }
    else{
      return false;
    }
  }
  
  @Override
  public void periodic() {
    // This method will be called once per scheduler run
    SmartDashboard.putBoolean("InSpeed?", isInSpeed());
  }
}
