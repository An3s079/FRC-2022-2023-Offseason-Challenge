package frc.robot.subsystems;
import com.ctre.phoenix.motorcontrol.can.WPI_TalonFX;
import frc.robot.constants.IDs;
import frc.robot.constants;
import edu.wpi.first.wpilibj2.command.SubsystemBase;
import edu.wpi.first.wpilibj.drive.DifferentialDrive;
import com.ctre.phoenix.motorcontrol.TalonFXInvertType;
import com.ctre.phoenix.motorcontrol.FeedbackDevice;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

public class DriveBase extends SubsystemBase{
private WPI_TalonFX m_rightLeader = new WPI_TalonFX(IDs.Drive.rightLineLeaderMotor);
  private WPI_TalonFX m_rightSheep = new WPI_TalonFX(IDs.Drive.rightSheepMotor);
  private WPI_TalonFX m_leftLeader = new WPI_TalonFX(IDs.Drive.leftLineLeaderMotor);
  private WPI_TalonFX m_leftSheep = new WPI_TalonFX(IDs.Drive.leftSheepMotor);

  private final DifferentialDrive m_drive = new DifferentialDrive(m_rightLeader, m_leftLeader);
    
  public DriveBase() {
    
    m_rightSheep.follow(m_rightLeader);
    m_leftSheep.follow(m_leftLeader);

    // run opposite on each side and sheep to run opposite their leader
    m_rightLeader.setInverted(TalonFXInvertType.Clockwise);
    m_leftLeader.setInverted(TalonFXInvertType.Clockwise);
    m_leftSheep.setInverted(true);
    m_rightSheep.setInverted(true);

    //Setup Integrated Encoders
    m_leftLeader.configSelectedFeedbackSensor(FeedbackDevice.IntegratedSensor);
    m_rightLeader.configSelectedFeedbackSensor(FeedbackDevice.IntegratedSensor);
    m_leftLeader.setSelectedSensorPosition(0);
    m_rightLeader.setSelectedSensorPosition(0);
  }

   //Differential tankDrive function for calling in Container
   public void arcadeDrive(double speed, double angle){
    m_drive.arcadeDrive(speed, angle);
  }

  public double getRightEncoderPos(){
    return m_rightLeader.getSelectedSensorPosition();
  }
  public double getLeftEncoderPos(){
    return m_leftLeader.getSelectedSensorPosition();
  }

  public void resetEncoderPos(){
    m_leftLeader.setSelectedSensorPosition(0);
    m_rightLeader.setSelectedSensorPosition(0);
  }

  public double getAverageEncoderDistance(){
    return (m_rightLeader.getSelectedSensorPosition() + m_leftLeader.getSelectedSensorPosition()) / 2;
  }

  @Override
  public void periodic() {
    // This method will be called once per scheduler run
    SmartDashboard.putNumber("right leader", m_rightLeader.get());
    SmartDashboard.putNumber("right follow", m_rightSheep.get());
    SmartDashboard.putNumber("left  follow", m_leftSheep.get());
    SmartDashboard.putNumber("left leader", m_leftLeader.get());
    
    SmartDashboard.putNumber("right encoder", m_rightLeader.getSelectedSensorPosition());
    SmartDashboard.putNumber("left encoder", m_leftLeader.getSelectedSensorPosition());

    SmartDashboard.putNumber("distance per pulse", constants.ENCODER_DISTANCE_PER_PULSE);

    SmartDashboard.putNumber("distance driven", m_rightLeader.getSelectedSensorPosition() * constants.ENCODER_DISTANCE_PER_PULSE);
  }


}
