package frc.robot.commands;

import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj2.command.Command;
import frc.robot.subsystems.DriveSubsystem;
import org.photonvision.*;

public class CameraIntakeCmd extends Command {
    private final DriveSubsystem driveSubsystem;
    private PhotonCamera camera;
    

    public CameraIntakeCmd(DriveSubsystem driveSubsystem, PhotonCamera camera) {
        this.driveSubsystem = driveSubsystem;
        this.camera = camera;
        addRequirements(driveSubsystem);
    }

    @Override
    public void initialize() {
        System.out.println("CameraIntakeCmd started!");
    }

    @Override
    public void execute() {
        var result = this.camera.getLatestResult();
        boolean hasTargets = result.hasTargets();
        SmartDashboard.putBoolean("Approaching Note", hasTargets);

        if (!hasTargets) {
            this.driveSubsystem.setMotors(0, 0);
            return;
        }

        double yaw = (result.getBestTarget().getYaw());
        double pitch = (Math.toDegrees(result.getBestTarget().getPitch()) + 360);
        //double speed = Math.min(0, (pitch + 7) / 5);

        if (Math.abs(yaw) > .05) {
            this.driveSubsystem.setMotors(0, .6 * Math.signum(yaw));
            return;
        } else if (Math.abs(yaw) > .5) {
            this.driveSubsystem.setMotors(0, .7 * Math.signum(yaw));
            return;
        }
        this.driveSubsystem.setMotors(-.7, 0);
        return;
    }

    @Override
    public void end(boolean interrupted) {
        this.driveSubsystem.setMotors(0, 0);
        SmartDashboard.putBoolean("Approaching Note", false);
        System.out.println("CameraIntakeCmd ended!");
    }

    @Override
    public boolean isFinished() {
        
        return false;
    }
}