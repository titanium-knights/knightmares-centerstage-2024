package org.firstinspires.ftc.teamcode.pipelines

import com.acmerobotics.dashboard.config.Config
import org.firstinspires.ftc.robotcore.external.Telemetry
import org.opencv.core.Core
import org.opencv.core.Mat
import org.opencv.core.Point
import org.opencv.core.Rect
import org.opencv.core.Scalar
import org.opencv.imgproc.Imgproc
import org.openftc.easyopencv.OpenCvPipeline

@Config
class TwoSplitColor @JvmOverloads constructor(
    private val telemetry: Telemetry,
    color: String = "blue"
) : OpenCvPipeline() {
    // store the color we're looking for (i.e. team color) as its index in the channels array
    var colorNum = 0

    // top left and bottom right points for the three rectangles we will crop into
    var rect_points = ArrayList<Point>()

    // the three places we might end up
    enum class Locations {
        TWO,
        THREE,
        ONE
    }

    // the location we actually want to go to
    @Volatile
    var location = Locations.ONE

    // for the 2 sections
    private val croppedSections = arrayOfNulls<Mat>(2)

    //instantiate the object with telemetry and a color, which defaults to blue
    // this is the constructor that will be used by easy openCV sim
    init {
        if (color.equals("red", ignoreCase = true)) colorNum = 0
        else if (color.equals("blue", ignoreCase = true)) colorNum = 2
        else {
            colorNum = 0
            telemetry.addLine("Invalid color, defaulting to red")
            telemetry.update()
        }
    }

//    fun getLocation(): Locations {
//        telemetry.addLine(location.name)
//        return location
//    }

    fun getScore(rgb: DoubleArray): Int {
        val want = (rgb[colorNum] * WANT_WEIGHT).toInt()
        val unWanted = (rgb[oppColor] * UNWANT_WEIGHT).toInt()
        val green = (rgb[1] * GREEN_WEIGHT).toInt()
        return want - green - unWanted
    }

    @OptIn(ExperimentalStdlibApi::class)
    override fun processFrame(input: Mat): Mat {
        var maxIndex = 0
        val scores = DoubleArray(2)
        for (i in croppedSections.indices) {
            val section = croppedSections[i]
            val avg = Core.mean(section).`val`
            scores[i] = getScore(avg).toDouble()
        }
        for (i in scores.indices) {
            telemetry.addData("Score for section $i", scores[i])
            telemetry.update()
            if (scores[i] > scores[maxIndex]) maxIndex = i
        }
        if (scores[maxIndex] < minScore) {
            telemetry.addLine("min score = " + minScore)
            maxIndex = 2
        }
        location = Locations.values()[maxIndex]
        telemetry.addData("Chose position: ", location.name)
        telemetry.update()
        val THICKNESS = 7

//        Imgproc.rectangle(
//                input,
//                rect_points.get(maxIndex * 2),
//                rect_points.get(2 * maxIndex + 1),
//                new Scalar(35, 123, 113),
//                THICKNESS);
        Imgproc.putText(
            input,
            location.name,
            Point(50.0, 230.0),
            3,
            10.0,
            Scalar(136.0, 125.0, 39.0),
            67
        )
        return input
    }

    override fun init(input: Mat) {
        // Rectangle coordinates (upper left [A] and bottom right [B]) for the three cropped sections
        //this code just currently splits into two equal sections

        //TODO: consider tuning these values based on where the cube appears
        // rather than a straight split
        rect_points.add(Point(0.0, 0.5 * input.rows()))
        rect_points.add(Point(input.cols() / 2.0, input.rows().toDouble()))
        rect_points.add(Point(input.cols() / 2.0, 0.5 * input.rows()))
        rect_points.add(Point(input.cols().toDouble(), input.rows().toDouble()))

        // initialize the dos cropped sections
        var i = 0
        while (i < rect_points.size) {
            val A = rect_points[i]
            val B = rect_points[i + 1]
            croppedSections[i / 2] = input.submat(Rect(A, B))
            i += 2
        }
    }

    val oppColor: Int
        /**
         * me when im tired of retyping this ternary statement over and over again
         */
        get() = if (colorNum == 0) 2 else 0
    private val minScore: Double
        get() = if (colorNum == 0) min_red_score else min_blue_score

    companion object {
        var RED_HIGH = Scalar(255.0, 0.0, 0.0)
        var BLUE_HIGH = Scalar(0.0, 0.0, 255.0)
        var RED_LOW = Scalar(200.0, 0.0, 0.0)
        var BLUE_LOW = Scalar(0.0, 0.0, 200.0)
        var min_blue_score = 26.0
        var min_red_score = 25.0
        // weights for scoring
        /**
         *
         *
         * the following explanation will assume that this pipeline is running
         * for red, although it will work the same way for either color. WANT
         * indicates red in this case, and UNWANT will indicate blue. If the
         * pipeline were running for blue, WANT => blueness, and UNWANT => redness.
         *  <br></br>
         *
         *
         * The weights below can be used to change how scoring occurs. For example,
         * WANT_WEIGHT = 1 ensures that 100% of the redness is factored into the score
         * It is likely that you should not need to adjust the WANT weight.
         * <br></br>
         *
         *
         * UNWANT_WEIGHT = 0.35 implies that 35% of the blue value will be subtracted
         * from the score. Raise this value if you find that colors with a higher
         * blue value are also triggering the red pipeline (i.e., purples, whites, etc.)
         * You can also lower this value if you find that scores are going too low in areas
         * that should be red. If all scores are going to low values, then you can just
         * lower the minimum_red_score and minimum_blue_score
         * <br></br>
         *
         *
         * GREEN_WEIGHT = 0.15 means that 15% of the green value will be subtracted from
         * the score. This value can probably be changed a bit more liberally than the
         * UNWANT_WEIGHT. Raise it if you find that colors with a mix of red and green
         * (e.g. white, brown, look up "rgb color picker" on google) are triggering the
         * system with a higher score. Lower it if scores are becoming abnormally low for
         * some sections which visually appear red.
         *
         */
        var WANT_WEIGHT = 1.0
        var UNWANT_WEIGHT = 0.35
        var GREEN_WEIGHT = 0.15
    }
}