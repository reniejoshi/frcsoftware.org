/*
 * Copyright 2026 FRCSoftware
 *
 * SPDX-License-Identifier: BSD-3-Clause
 */
import org.wpilib.command3.Mechanism;
import org.wpilib.hardware.imu.OnboardIMU;
import org.wpilib.hardware.imu.OnboardIMU.MountOrientation;
import org.wpilib.math.geometry.Rotation2d;

public class HardwareIntro {
    // [intake]
    public class Intake implements Mechanism {
        // Placeholder for TalonFX, SparkMax or SparkFlex
        private final ExampleMotor motor = new ExampleMotor(0, CANBus.systemcore(0));

        public Command runIntake() {
            return run(coroutine -> {
                while (true) {
                    motor.set(0.8);
                    coroutine.yield();
                }
            })
            .named("Run Intake");
        }

        public Command stopIntake() {
            return run(coroutine -> {
                while (true) {
                    motor.set(0.0);
                    coroutine.yield();
                }
            })
            .named("Stop Intake");
        }
    }
    // [/intake]

    // [turret]
    public class Turret extends Mechanism {
        // Placeholder for magnetic encoder
        private final ExampleEncoder encoder = new ExampleEncoder(1, CANBus.systemcore(0));

        public Rotation2d getEncoderPosition() {
            double position = encoder.getAbsolutePosition().getValue().in(Rotations) * ENCODER_MECHANISM_RATIO;
            return Rotation2d.fromRotations(position);
        }
    }
    // [/turret]

    // [drivetrain]
    public class Drivetrain implements Mechanism {
        private final OnboardIMU imu = new OnboardIMU(MountOrientation.FLAT);

        public Rotation2d getHeading() {
            return imu.getRotation2d();
        }
    }
    // [/drivetrain]
}
