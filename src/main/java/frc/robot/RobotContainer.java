package frc.robot;
import frc.robot.subsystems.DriveBase;
import frc.robot.subsystems.DriveShifter;
import frc.robot.subsystems.Elevator;
import frc.robot.subsystems.ElevatorShifter;
import frc.robot.constants.IDs;
import frc.robot.subsystems.Intake;
import edu.wpi.first.wpilibj.XboxController;
import edu.wpi.first.wpilibj2.command.button.JoystickButton;
import edu.wpi.first.wpilibj2.command.InstantCommand;
import edu.wpi.first.wpilibj2.command.ParallelCommandGroup;
import edu.wpi.first.wpilibj2.command.RunCommand;
import edu.wpi.first.wpilibj2.command.SequentialCommandGroup;
import frc.robot.Commands.EndIntake;
import frc.robot.Commands.StartIntake;
import frc.robot.Commands.toggleFlippyDippy;

public class RobotContainer {

    public final DriveBase driveBase = new DriveBase();
    public final Intake intake = new Intake();
    public final Elevator elevator = new Elevator();
    public final DriveShifter dShifter = new DriveShifter();
    public final ElevatorShifter eShifter = new ElevatorShifter();

    private XboxController driverController = new XboxController(IDs.DriverController);
    private XboxController everythingElserController = new XboxController(IDs.EverythingElserController);

    public RobotContainer() {
        // Configure the button bindings
        buildBindings();

        //Main drive code, left joystick for speed and right for angle
        driveBase.setDefaultCommand(
          new RunCommand(
            () -> driveBase.arcadeDrive(-driverController.getLeftX(), driverController.getRightY()), driveBase));
        
        elevator.setDefaultCommand(
          new RunCommand(
          () -> elevator.moveElevator(everythingElserController.getLeftY()), elevator));
      }

      /** 
       * check {@link XboxController} for buttons/values
       */ 
      private void buildBindings() {

        var aButton = new JoystickButton(everythingElserController, 1);  
        aButton.whileActiveOnce(
          new SequentialCommandGroup(
            new toggleFlippyDippy(intake, true), 
            new StartIntake(intake)
          )); 

        var bButton = new JoystickButton(everythingElserController, 2);
        bButton.whileActiveOnce(
          new SequentialCommandGroup(
            new EndIntake(intake),
            new toggleFlippyDippy(intake, false)
          ));

        new JoystickButton(driverController, 9) //left joystick button
        .whenPressed(new InstantCommand(dShifter::setSpeed, dShifter));

        new JoystickButton(driverController, 10) //right joystick button
        .whenPressed(new InstantCommand(dShifter::setTorque, dShifter));

        new JoystickButton(everythingElserController, 9) //left joystick button
        .whenPressed(new InstantCommand(eShifter::setSpeed, eShifter));

        new JoystickButton(everythingElserController, 10) //right joystick button
        .whenPressed(new InstantCommand(eShifter::setTorque, eShifter));
      }
}
