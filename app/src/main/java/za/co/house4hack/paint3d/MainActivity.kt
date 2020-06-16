package za.co.house4hack.paint3d

import android.content.Intent
import android.content.pm.PackageManager
import android.content.pm.ResolveInfo
import android.os.Bundle
import android.os.Environment
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import org.json.JSONArray
import org.json.JSONObject
import za.co.house4hack.paint3d.stl.Layer
import za.co.house4hack.paint3d.stl.Point
import za.co.house4hack.paint3d.stl.Polygon


class MainActivity : AppCompatActivity() {

    val TEST_PATH1 =
        "[0.0, 95.0, 0.0, 0.23040606, 5.0, 0.0, 0.24020302, 1.464466, 1.464466, 0.24999999, 0.0, 5.0, 0.48040605, 0.0, 95.0, 0.490203, 1.464466, 98.53553, 0.49999997, 5.0, 100.0, 0.73040605, 95.0, 100.0, 0.740203, 98.53553, 98.53553, 0.75, 100.0, 95.0, 0.98040605, 100.0, 5.0, 0.990203, 98.53553, 1.464466, 1.0, 95.0, 0.0]"
    val PATH1 =
        "[0,70.4,86.427925,0.062500045,75.09411,85.53255,0.12500003,79.04646,82.846436,0.18750001,81.732574,78.89408,0.24999996,82.62795,74.19998,0.3125,81.732574,69.505875,0.37499997,79.04646,65.55352,0.43749988,75.09411,62.867416,0.4999999,70.4,61.97205,0.5625,65.70589,62.867416,0.625,61.75353,65.55352,0.68749994,59.06742,69.505875,0.74999994,58.17205,74.19998,0.8124999,59.06742,78.89408,0.87499976,61.75353,82.846436,0.9374998,65.70589,85.53255,1,70.4,86.427925]"
    val PATH2 =
        "[0.0, 25.800001, 34.05, 0.0625001, 30.50258, 33.15301, 0.12500013, 34.46207, 30.462055, 0.1875001, 37.15303, 26.502575, 0.25000006, 38.05002, 21.800007, 0.3125001, 37.15303, 17.097433, 0.3750001, 34.46207, 13.137947, 0.43750012, 30.50258, 10.446991, 0.5000002, 25.800001, 9.550005, 0.5625002, 21.09743, 10.446991, 0.6250002, 17.137943, 13.137947, 0.6875001, 14.446989, 17.097433, 0.7500002, 13.550005, 21.800007, 0.81250006, 14.446989, 26.502575, 0.87500006, 17.137943, 30.462059, 0.9375, 21.09743, 33.15301, 1.0, 25.800001, 34.05]\n"
    val PATH3 =
        "[0.0, 95.0, 0.0, 0.23040606, 5.0, 0.0, 0.24020302, 1.464466, 1.464466, 0.24999999, 0.0, 5.0, 0.48040605, 0.0, 95.0, 0.490203, 1.464466, 98.53553, 0.49999997, 5.0, 100.0, 0.73040605, 95.0, 100.0, 0.740203, 98.53553, 98.53553, 0.75, 100.0, 95.0, 0.98040605, 100.0, 5.0, 0.990203, 98.53553, 1.464466, 1.0, 95.0, 0.0]\n"

    val testJson = "{\"path\":[[0,84,82,0.23128992,178.613,82,0.24064496,182.14854,83.46446,0.25,183.613,87,0.48128992,183.613,181.613,0.49064493,182.14854,185.14853,0.5,178.613,186.613,0.7312899,84,186.613,0.740645,80.46446,185.14853,0.74999994,79,181.613,0.98128986,79,87,0.99064493,80.46446,83.46446,1,84,82],[0,163,128,0.062499955,161.47757,135.65366,0.12499993,157.14212,142.14212,0.1875,150.65364,146.47757,0.24999984,143,148,0.3124999,135.34633,146.47757,0.37499988,128.85786,142.14212,0.4375001,124.52239,135.65364,0.49999985,123,128,0.5625,124.52239,120.34631,0.62500006,128.85786,113.85785,0.6875,135.34633,109.5224,0.75,143,108,0.81249976,150.65364,109.5224,0.8749999,157.14212,113.85785,0.9374998,161.47757,120.34631,1,163,128]],\"depth\":3}"

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val pathJsonStr = intent.getStringExtra(INTENT_EX_3D_PATH) ?: null
        if (pathJsonStr == null) {
            return
        }
//        val pathJsonStr = testJson
        val pathsJson = JSONObject(pathJsonStr)

        val layer = Layer()

        val pathArray = pathsJson.getJSONArray("path")

        val json0 = pathArray[0] as JSONArray
        val polygon0 = Polygon()
        for (i in 0..(json0.length() / 3 - 1)) {
            polygon0.add(Point(json0.getDouble(i * 3 + 1), json0.getDouble(i * 3 + 2)))
        }
        layer.set(0, polygon0)

        for (j in 1..pathArray.length()-1) {
            val polygonIn1 = Polygon()
            val json1 = pathArray[j] as JSONArray
            for (i in 0..(json1.length() / 3 - 1)) {
                polygonIn1.add(Point(json1.getDouble(i * 3 + 1), json1.getDouble(i * 3 + 2)))
            }
            layer.add(polygonIn1)
        }

        val vectorPaint = VectorPaint(this)
        vectorPaint.layers.set(0, layer)
        vectorPaint.preview(this,
            Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_DOCUMENTS)
                .toString() + "/VIVITA/test.stl"
        )

    }

    companion object {
        val INTENT_EX_3D_PATH = "intent_ex_3d_path_json"

        private val TAG = "MainActivity"
        val REQUEST_CODE_GENERATE_3D = 3
    }
}