package irs1318_2014Fall_robot;

import edu.wpi.first.wpilibj.*;
import edu.wpi.first.wpilibj.DoubleSolenoid.Value;

public class ShooterComponent
{
    private DoubleSolenoid shooterAngle;
    private DoubleSolenoid middlePiston;
    private DoubleSolenoid innerLeftPiston;
    private DoubleSolenoid innerRightPiston;
    private DoubleSolenoid outerLeftPiston;
    private DoubleSolenoid outerRightPiston;

    public ShooterComponent()
    {
        this.shooterAngle = new DoubleSolenoid(
            ElectronicsConstants.SOLENOID_MODULE_PORT_2,
            ElectronicsConstants.SHOOTER_ANGLE_EXTENDER_SOLENOID_PORT,
            ElectronicsConstants.SHOOTER_ANGLE_RETRACTOR_SOLENOID_PORT);

        this.middlePiston = new DoubleSolenoid(
            ElectronicsConstants.SOLENOID_MODULE_PORT_1,
            ElectronicsConstants.SHOOTER_MIDDLE_SOLENOID_EXTENDER_PORT,
            ElectronicsConstants.SHOOTER_MIDDLE_SOLENOID_RETRACTOR_PORT);

        this.innerLeftPiston = new DoubleSolenoid(
            ElectronicsConstants.SOLENOID_MODULE_PORT_1,
            ElectronicsConstants.SHOOTER_INNER_LEFT_SOLENOID_EXTENDER_PORT,
            ElectronicsConstants.SHOOTER_INNER_LEFT_SOLENOID_RETRACTOR_PORT);

        this.innerRightPiston = new DoubleSolenoid(
            ElectronicsConstants.SOLENOID_MODULE_PORT_1,
            ElectronicsConstants.SHOOTER_INNER_RIGHT_SOLENOID_EXTENDER_PORT,
            ElectronicsConstants.SHOOTER_INNER_RIGHT_SOLENOID_RETRACTOR_PORT);

        this.outerLeftPiston = new DoubleSolenoid(
            ElectronicsConstants.SOLENOID_MODULE_PORT_2,
            ElectronicsConstants.SHOOTER_OUTER_LEFT_SOLENOID_EXTENDER_PORT,
            ElectronicsConstants.SHOOTER_OUTER_LEFT_SOLENOID_RETRACTOR_PORT);

        this.outerRightPiston = new DoubleSolenoid(
            ElectronicsConstants.SOLENOID_MODULE_PORT_2,
            ElectronicsConstants.SHOOTER_OUTER_RIGHT_SOLENOID_EXTENDER_PORT,
            ElectronicsConstants.SHOOTER_OUTER_RIGHT_SOLENOID_RETRACTOR_PORT);
    }

    public void setShooterSolenoids(boolean middle, boolean innerLeft, boolean innerRight, boolean outerLeft, boolean outerRight)
    {
        this.middlePiston.set(middle ? Value.kForward : Value.kReverse);
        this.innerLeftPiston.set(innerLeft ? Value.kForward : Value.kReverse);
        this.innerRightPiston.set(innerRight ? Value.kForward : Value.kReverse);
        this.outerLeftPiston.set(outerLeft ? Value.kForward : Value.kReverse);
        this.outerRightPiston.set(outerRight ? Value.kForward : Value.kReverse);
    }

    public void setShooterAngle(boolean extend)
    {
        this.shooterAngle.set(extend ? Value.kForward : Value.kReverse);
    }
}
