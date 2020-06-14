package za.co.house4hack

import android.app.Application
import android.os.Environment
import androidx.test.platform.app.InstrumentationRegistry
import org.json.JSONArray
import org.json.JSONObject
import org.junit.Assert
import org.junit.Test

import org.junit.runner.RunWith
import org.robolectric.RobolectricTestRunner
import za.co.house4hack.paint3d.VectorPaint
import za.co.house4hack.paint3d.stl.Layer
import za.co.house4hack.paint3d.stl.Point
import za.co.house4hack.paint3d.stl.Polygon
import java.util.Locale

@RunWith(RobolectricTestRunner::class)
class ModuleUtilsTest {

    val PATH1 = "[0,70.4,86.427925,0.062500045,75.09411,85.53255,0.12500003,79.04646,82.846436,0.18750001,81.732574,78.89408,0.24999996,82.62795,74.19998,0.3125,81.732574,69.505875,0.37499997,79.04646,65.55352,0.43749988,75.09411,62.867416,0.4999999,70.4,61.97205,0.5625,65.70589,62.867416,0.625,61.75353,65.55352,0.68749994,59.06742,69.505875,0.74999994,58.17205,74.19998,0.8124999,59.06742,78.89408,0.87499976,61.75353,82.846436,0.9374998,65.70589,85.53255,1,70.4,86.427925]"

    lateinit var app: Application
    lateinit var vectorPaint: VectorPaint

    @Test
    fun createModuleFromJson() {

    }
}