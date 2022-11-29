package frc.robot;

public class constants {
    public static final class IDs {
        public static final class Drive{
            //motors
            public static final int leftLineLeaderMotor = 0;
            public static final int leftSheepMotor = 1; // follows the line leader
            public static final int rightLineLeaderMotor = 2;
            public static final int rightSheepMotor = 3; //follows right line leader

            //PH
            public static final int driveShiftForwardChannel = 0;            
            public static final int driveShiftReverseChannel = 1; 
        }
        public static final class Elevator {
            //motors
            public static final int climbCultLeaderMotor = 4;
            public static final int climbCultistMotor = 5; // follows the line leader
            
            //ph
            public static final int elevatorShiftForwardChannel = 3; //skipped one so its easier to see which are climb and drive
            public static final int elevatorShiftReverseChannel = 4;
        }
        public static final class Intake {
            //motors
            public static final int spinnerMainMotor = 6;
            public static final int spinnerSidekickMotor = 7;           
            public static final int flippyDippyMotor = 8;

            //ph
            public static final int clamperOpenChannel = 6;
            public static final int clamperCloseChannel = 7; 
        }
        
        public static final int DriverController = 0;
        public static final int EverythingElserController = 1;
        public static final int PneumaticsHub = 9;
    }

    public static final double elevatorRotationsToTravel = 1200;

    //31.25 rotations is one full turn of the dropper, dont want to to reach exactly 180 degrees so we increase division.
    public static final double dropperRotationsToTravel = 31.25/2.1;

    public static final int DRIVE_ENCODER_CPR = 2048;
    public static final double DRIVE_WHEEL_DIAMETER = 6;
    public static final double ENCODER_DISTANCE_PER_PULSE = 
                (DRIVE_WHEEL_DIAMETER * Math.PI) / (double) DRIVE_ENCODER_CPR;
}
