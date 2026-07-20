/*
 * Copyright 2026 FRCSoftware
 *
 * SPDX-License-Identifier: BSD-3-Clause
 */
public class HardwareIntro {
    // [intakeSubsystem]
    public class IntakeSubsystem extends SubsystemBase {
    private TalonFX motor = new TalonFX(0);

    public void runIntake() {
        motor.set(0.8);
    }

    public void stopIntake() {
        motor.set(0.0);
    }
    }
    // [/intakeSubsystem]

    // [turret]
    public class Turret extends SubsystemBase {
        CANcoder encoder = new CANcoder(1, "rio");
        
        public Rotation2d getEncoderPosition() {
            double position = encoder.getAbsolutePosition().getValue().in(Rotations) * ENCODER_MECHANISM_RATIO;
            return Rotation2d.fromRotations(position);
        }
    }
    // [/turret]

    // [chassisSubsystem]
    public class ChassisSubsystem {
        private final Pigeon2 pigeon = new Pigeon2(2, "rio");

        public Rotation2d getHeading() {
            return Rotation2d.fromDegrees(pigeon.getYaw());
        }
    }
    // [/chassisSubsystem]
}
