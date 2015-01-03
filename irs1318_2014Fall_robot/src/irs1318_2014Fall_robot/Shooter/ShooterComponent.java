package irs1318_2014Fall_robot.Shooter;

import irs1318_2014Fall_robot.ElectronicsConstants;
import irs1318_2014Fall_robot.Common.SmartDashboardLogger;
import edu.wpi.first.wpilibj.*;
import edu.wpi.first.wpilibj.DoubleSolenoid.Value;

/**
 * The shooter component class describes the electronics of the shooter and defines the abstract way to control it.
 * The shooter electronics include five shooting solenoids, and one angle solenoid. 
 *  
 * @author Will
 *
 */
public class ShooterComponent
{
    // logging constants
    private static final String SHOOT_LOG_KEY = "s.se";
    private static final String ANGLE_LOG_KEY = "s.ae";

    private DoubleSolenoid shooterAngle;
    private DoubleSolenoid middlePiston;
    private DoubleSolenoid innerLeftPiston;
    private DoubleSolenoid innerRightPiston;
    private DoubleSolenoid outerLeftPiston;
    private DoubleSolenoid outerRightPiston;

    /**
     * Initializes a new ShooterComponent
     */
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

    /**
     * Set shooter solenoids to forward (true, shoot) or reverse (false, end shot)
     * @param middle solenoid choice
     * @param innerLeft solenoid choice
     * @param innerRight solenoid choice
     * @param outerLeft solenoid choice
     * @param outerRight solenoid choice
     */
    public void setShooterSolenoids(boolean middle, boolean innerLeft, boolean innerRight, boolean outerLeft, boolean outerRight)
    {
        this.middlePiston.set(middle ? Value.kForward : Value.kReverse);
        this.innerLeftPiston.set(innerLeft ? Value.kForward : Value.kReverse);
        this.innerRightPiston.set(innerRight ? Value.kForward : Value.kReverse);
        this.outerLeftPiston.set(outerLeft ? Value.kForward : Value.kReverse);
        this.outerRightPiston.set(outerRight ? Value.kForward : Value.kReverse);

        boolean shooting = middle || innerLeft || innerRight || outerLeft || outerRight;
        SmartDashboardLogger.putBoolean(ShooterComponent.SHOOT_LOG_KEY, shooting);
    }

    /**
     * Sets a value indicating whether we should extend or retract the shooter
     * @param extend the shooter
     */
    public void setShooterAngle(boolean extend)
    {
        this.shooterAngle.set(extend ? Value.kForward : Value.kReverse);

        SmartDashboardLogger.putBoolean(ShooterComponent.ANGLE_LOG_KEY, extend);
    }
}
