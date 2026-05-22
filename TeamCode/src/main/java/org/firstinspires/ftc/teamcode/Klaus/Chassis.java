package org.firstinspires.ftc.teamcode.Klaus;

import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorSimple;

@TeleOp
public class Chassis extends OpMode {
    // Declaração dos membros OpMode
    private DcMotor rightFrontMotor = null;
    private DcMotor leftFrontMotor = null;
    private DcMotor rightBackMotor = null;
    private DcMotor leftBackMotor = null;
    private DcMotor rightClaw = null;
    private DcMotor leftClaw = null;

    @Override
    public void init() {
        // Connection with physical motors
        rightFrontMotor = hardwareMap.get(DcMotor.class, "rf");
        rightBackMotor = hardwareMap.get(DcMotor.class, "rb");
        leftFrontMotor = hardwareMap.get(DcMotor.class, "lf");
        leftBackMotor = hardwareMap.get(DcMotor.class, "lb");
        rightClaw = hardwareMap.get(DcMotor.class, "r");
        leftClaw = hardwareMap.get(DcMotor.class, "l");

        rightFrontMotor.setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);
        rightBackMotor.setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);
        leftFrontMotor.setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);
        leftBackMotor.setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);
        rightClaw.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        leftClaw.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);

        rightFrontMotor.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
        rightBackMotor.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
        leftFrontMotor.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
        leftBackMotor.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);

        rightFrontMotor.setDirection(DcMotor.Direction.REVERSE);
        rightBackMotor.setDirection(DcMotor.Direction.REVERSE);
        leftFrontMotor.setDirection(DcMotor.Direction.FORWARD);
        leftBackMotor.setDirection(DcMotor.Direction.REVERSE);
        rightClaw.setDirection(DcMotor.Direction.FORWARD);
        leftClaw.setDirection(DcMotor.Direction.REVERSE);

        rightClaw.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
        leftClaw.setMode(DcMotor.RunMode.RUN_USING_ENCODER);

        rightClaw.setTargetPosition(0);
        leftClaw.setTargetPosition(0);

        rightClaw.setMode(DcMotor.RunMode.RUN_TO_POSITION);
        leftClaw.setMode(DcMotor.RunMode.RUN_TO_POSITION);

        rightClaw.setPower(0.2);
        leftClaw.setPower(0.2);

        telemetry.addData("status", "initialized");
    }

    public void loop() {
        telemetry.addData("status", "running");
        telemetry.addData("clawPosition", rightClaw.getCurrentPosition());

        if (gamepad1.dpad_up) {
            rightClaw.setTargetPosition(100);
            leftClaw.setTargetPosition(100);
        } else if (gamepad1.dpad_down) {
            rightClaw.setTargetPosition(0);
            leftClaw.setTargetPosition(0);
        }

        double leftStickX = gamepad1.left_stick_x * 0.5;

        rightFrontMotor.setPower(gamepad1.right_trigger - gamepad1.left_trigger - leftStickX);
        rightBackMotor.setPower(gamepad1.right_trigger - gamepad1.left_trigger - leftStickX);
        leftFrontMotor.setPower(gamepad1.right_trigger - gamepad1.left_trigger + leftStickX);
        leftBackMotor.setPower(gamepad1.right_trigger - gamepad1.left_trigger + leftStickX);
    }
}
