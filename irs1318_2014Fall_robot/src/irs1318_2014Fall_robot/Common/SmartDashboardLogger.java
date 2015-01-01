package irs1318_2014Fall_robot.Common;

import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj.networktables.NetworkTableKeyNotDefined;

public class SmartDashboardLogger
{
    /**
     * Write a boolean to the smart dashboard
     * @param key to write to
     * @param value to write
     */
	public static void putBoolean(String key, boolean value)
	{
		SmartDashboardLogger.putBoolean(key, new Boolean(value));
	}

	/**
	 * Write a Boolean to the smart dashboard
	 * @param key to write to
	 * @param value to write
	 */
	public static void putBoolean(String key, Boolean value)
	{
		if (value == null)
	    {
		    return;
	    }

		try
		{
			if (SmartDashboard.getBoolean(key) != value.booleanValue())
			{
				SmartDashboard.putBoolean(key, value.booleanValue());
			}
		}
		catch (NetworkTableKeyNotDefined ex)
		{
			SmartDashboard.putBoolean(key, value.booleanValue());
		}
	}

	/**
	 * Write a number (double) to the smart dashboard
	 * @param key to write to
	 * @param value to write
	 */
	public static void putNumber(String key, double value)
	{
	    SmartDashboardLogger.putNumber(key, new Double(value));
	}

    /**
     * Write a number (Double) to the smart dashboard
     * @param key to write to
     * @param value to write
     */
	public static void putNumber(String key, Double value)
	{
		if (value == null)
	    {
		    return;
	    }

		try
		{
			if (SmartDashboard.getNumber(key) != value.doubleValue())
			{
				SmartDashboard.putNumber(key, value.doubleValue());
			}
		}
		catch (NetworkTableKeyNotDefined ex)
		{
			SmartDashboard.putNumber(key, value.doubleValue());
		}
	}

    /**
     * Write a string to the smart dashboard
     * @param key to write to
     * @param value to write
     */
	public static void putString(String key, String value)
	{
		try
		{
			if (SmartDashboard.getString(key) != value)
			{
				SmartDashboard.putString(key, value);
			}
		}
		catch (NetworkTableKeyNotDefined ex)
		{
			SmartDashboard.putString(key, value);
		}
	}

	/**
	 * Get a boolean from the smart dashboard
	 * @param key to retrieve
	 * @return value from smart dashboard
	 */
	public static boolean getBoolean(String key)
	{
		return SmartDashboard.getBoolean(key);
	}

   /**
     * Get a number (double) from the smart dashboard
     * @param key to retrieve
     * @return value from smart dashboard
     */
	public static double getNumber(String key)
	{
		return SmartDashboard.getNumber(key);
	}

	/**
     * Get a string from the smart dashboard
     * @param key to retrieve
     * @return value from smart dashboard
     */
	public static String getString(String key)
	{
		return SmartDashboard.getString(key);
	}
}