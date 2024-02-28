package frc.robot.commands;

import edu.wpi.first.wpilibj2.command.Command;
import frc.robot.subsystems.intake.IntakeMotorSubsystem;
import frc.robot.subsystems.storage.BeamBreakSubsystem;

public class SpinIntakeCmd extends Command {
    private BeamBreakSubsystem m_BeamBreakSubsystem;
    private IntakeMotorSubsystem m_IntakeMotorSubsystem;

    public SpinIntakeCmd(BeamBreakSubsystem beam_break_subsystem, IntakeMotorSubsystem intake_motor_subsystem) {
        this.m_BeamBreakSubsystem = beam_break_subsystem;
        this.m_IntakeMotorSubsystem = intake_motor_subsystem;
    }

    @Override
    public void execute() {
        if (!m_BeamBreakSubsystem.isBeamBroken()){
            m_IntakeMotorSubsystem.spinMotor();
        }
    }

    @Override
    public void end(boolean interrupted){
         m_IntakeMotorSubsystem.stopMotor();
    }
}
