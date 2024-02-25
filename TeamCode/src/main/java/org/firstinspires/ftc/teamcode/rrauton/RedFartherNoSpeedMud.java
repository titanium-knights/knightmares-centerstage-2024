package org.firstinspires.ftc.teamcode.rrauton;

import com.acmerobotics.roadrunner.geometry.Pose2d;
import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.hardware.HardwareMap;

import org.firstinspires.ftc.teamcode.rr.drive.SampleMecanumDrive;
import org.firstinspires.ftc.teamcode.utilities.Bay;
import org.firstinspires.ftc.teamcode.utilities.Intake;
import org.firstinspires.ftc.teamcode.utilities.Slides;
import org.firstinspires.ftc.teamcode.utilities.Stick;
import org.firstinspires.ftc.teamcode.utilities.InitialVision;

@Autonomous(name="REDFarthernospeed", group="Autonomous")
public class RedFartherNoSpeedMud extends ergo {

    public SampleMecanumDrive drive;
    public Stick stick;
    public InitialVision vision;
    public Slides slides;
    public Intake intake;
    public Bay bay;
    public int rot = 76; // intended to be 90 but the turn overturns it
    // 76 if full battery
    // 110 if at very low battery
    //TODO etc. etc., and add to the createHardware method

    public void createHardware(HardwareMap hmap) {
        drive = new SampleMecanumDrive(hmap);
        stick = new Stick(hmap);
        vision = new InitialVision(hmap, telemetry, "blue"); //TODO: remember to change to blue for blue side
        slides = new Slides(hmap);
        bay = new Bay(hmap);
        intake = new Intake(hmap);
    }

    @Override
    public void runOpMode() {
        createHardware(hardwareMap);
        stick.lock();
        slides.tozero();
        waitForStart();

        //TODO check this value
        Pose2d startPose = new Pose2d(11.5, 60, Math.toRadians(90));
        drive.setPoseEstimate(startPose);

        if(isStopRequested()) return;

        int pos = vision.getPosition();

        switch (pos) {
            case 1:
                drive.followTrajectory(backOnee);
                drive.turn(Math.toRadians(rot));
                drive.followTrajectory(backOnee);
                drive.followTrajectory(forwardFromPixel);
                drive.followTrajectory(forwardOne);
                drive.followTrajectory(dropPixel);
                drive.followTrajectory(leftOne);
                drive.turn(Math.toRadians(rot));
                drive.turn(Math.toRadians(rot));
                drive.followTrajectory(backThree);
                drive.followTrajectory(backOne);
                break;
            case 3:
                drive.followTrajectory(backOnee);
                drive.turn(Math.toRadians(rot));
                drive.followTrajectory(backOnee);
                drive.followTrajectory(forwardFromPixel);
                drive.followTrajectory(forwardOne);
                drive.turn(Math.toRadians(rot));
                drive.turn(Math.toRadians(rot));
                drive.followTrajectory(dropPixel);
                drive.followTrajectory(rightOne);
                drive.followTrajectory(backThree);
                drive.followTrajectory(backOne);
                break;
            case 2:
            default:
                drive.followTrajectory(backOnee);
                drive.turn(Math.toRadians(rot));
                drive.followTrajectory(backOnee);
                drive.followTrajectory(forwardFromPixel);
                drive.turn(Math.toRadians(-rot));
                drive.followTrajectory(leftOne);
                drive.followTrajectory(backToDropPixel);
                drive.followTrajectory(dropPixel);
                drive.turn(Math.toRadians(-rot));
                drive.followTrajectory(forwardOne);
                drive.followTrajectory(rightOne);
                drive.followTrajectory(backThree);
                drive.followTrajectory(backOne);
                drive.followTrajectory(backOne);
                break;
        }

    }

}
