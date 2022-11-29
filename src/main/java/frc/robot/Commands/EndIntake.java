package frc.robot.Commands;
import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.subsystems.Intake;

public class EndIntake extends CommandBase{
private final Intake intake;

  public EndIntake(Intake subsystem) {
    // Use addRequirements() here to declare subsystem dependencies.
    intake = subsystem;
    addRequirements(intake);
  }

  // Called every time the scheduler runs while the command is scheduled.
  @Override
  public void execute() {
    intake.runMotors(-0.45);
  }

  // Called once the command ends or is interrupted.
  @Override
  public void end(boolean interrupted) {
    intake.runMotors(0);
    intake.setClamped(false);
  }


}
