package frc.robot.Commands;
import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.subsystems.Intake;
import frc.robot.constants;

public class toggleFlippyDippy extends CommandBase{
    /** Creates a new DriveDistance. */
  private final Intake intake;
  private boolean IwantItOut = false;
  private double initPos;
  public toggleFlippyDippy(Intake subsystem, boolean _out) {
    // Use addRequirements() here to declare subsystem dependencies.
    intake = subsystem;
    IwantItOut = _out;
    addRequirements(subsystem);
  }
// Called when the command is initially scheduled.
@Override
public void initialize() {
  intake.flippyDippyMotor.getEncoder().setPosition(0);
}
  // Called every time the scheduler runs while the command is scheduled.
  @Override
  public void execute() {
    if(!IwantItOut) {
        intake.flippyDippyMotor.set(-.4); //pulls it in
    }
    else{
        intake.flippyDippyMotor.set(.4);
    }
  }

  // Called once the command ends or is interrupted.
  @Override
  public void end(boolean interrupted) {
    intake.flippyDippyMotor.set(0);
    intake.intakeOut = IwantItOut;
  }

  // Returns true when the command should end.
  @Override
  public boolean isFinished() {
    return 
     (intake.flippyDippyMotor.getEncoder().getPosition() <= intake.topLimitEncoderFlippy)  || (intake.flippyDippyMotor.getEncoder().getPosition() >= intake.botLimitEncoderFlippy)|| IwantItOut == intake.intakeOut;
  }
    
}
