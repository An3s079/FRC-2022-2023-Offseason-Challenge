package frc.robot.Commands;
import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.subsystems.Intake;
import frc.robot.constants;
public class toggleIntakeDrop extends CommandBase{
    /** Creates a new DriveDistance. */
  private final Intake intake;
  private double initPos;
  public toggleIntakeDrop(Intake subsystem) {
    // Use addRequirements() here to declare subsystem dependencies.
    intake = subsystem;
    addRequirements(subsystem);
  }
// Called when the command is initially scheduled.
@Override
public void initialize() {
  initPos = intake.dropperMotor.getEncoder().getPosition();
}
  // Called every time the scheduler runs while the command is scheduled.
  @Override
  public void execute() {
    if(intake.intakeOut) {
        intake.dropperMotor.set(-.4); 
    }
    else{
        intake.dropperMotor.set(.4);
    }
  }

  // Called once the command ends or is interrupted.
  @Override
  public void end(boolean interrupted) {
    intake.dropperMotor.set(0);
    intake.intakeOut = !intake.intakeOut;
  }

  // Returns true when the command should end.
  @Override
  public boolean isFinished() {
    return (Math.abs(intake.dropperMotor.getEncoder().getPosition()-initPos) / intake.dropperMotor.getEncoder().getCountsPerRevolution()) >= constants.dropperRotationsToTravel;
  }
    
}
