package frc.robot.subsystems;
import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.constants.IDs;

import com.revrobotics.CANSparkMax;
import com.revrobotics.CANSparkMaxLowLevel.MotorType;
import edu.wpi.first.wpilibj.DoubleSolenoid;
import edu.wpi.first.wpilibj.PneumaticsModuleType;
import edu.wpi.first.wpilibj.DoubleSolenoid.Value;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

public class Intake extends SubsystemBase{

    public CANSparkMax grabberMain = new CANSparkMax(IDs.Intake.spinnerMainMotor, MotorType.kBrushless);
    public CANSparkMax grabberSidekick = new CANSparkMax(IDs.Intake.spinnerMainMotor, MotorType.kBrushless);

    public CANSparkMax dropperMotor = new CANSparkMax(IDs.Intake.intakedropperMotor, MotorType.kBrushless);

    private DoubleSolenoid clamper = 
      new DoubleSolenoid(IDs.PneumaticsHub, PneumaticsModuleType.REVPH, IDs.Intake.clamperOpenChannel, IDs.Intake.clamperCloseChannel);

    public boolean clamperClosed = true;
    public boolean intakeOut = false;
    
    public Intake(){
        grabberSidekick.follow(grabberMain);
    }

    public void runMotors(double speed){
        grabberMain.set(speed);
    }

    public void setClamped(boolean clamped){
        clamper.set(clamped? Value.kReverse : Value.kForward);
        clamperClosed = clamped;
    }
    
    @Override
    public void periodic() {
      // This method will be called once per scheduler run
      SmartDashboard.putBoolean("Clamped?", clamperClosed);
      SmartDashboard.putBoolean("Intake Out?", intakeOut);
      SmartDashboard.putNumber("IntakeSpinner", grabberMain.get());
    }
}
