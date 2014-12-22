package irs1318_2014Fall_robot;

import edu.wpi.first.wpilibj.*;

public class DriveTrainComponent implements IDriveTrainComponent
{
    private Talon leftTalon;
    private Talon rightTalon;

    public DriveTrainComponent()
    {
        this.leftTalon = new Talon(
            ElectronicsConstants.SIDECAR_SLOT,
            ElectronicsConstants.DRIVETRAIN_LEFT_TALON_CHANNEL);
        
        this.rightTalon = new Talon(
            ElectronicsConstants.SIDECAR_SLOT,
            ElectronicsConstants.DRIVETRAIN_RIGHT_TALON_CHANNEL);
    }

    public void setDriveTrainPower(double leftPower, double rightPower)
    {
        this.leftTalon.set(-leftPower); // note: left motors are oriented facing "backwards"
        this.rightTalon.set(rightPower);
    }
}
