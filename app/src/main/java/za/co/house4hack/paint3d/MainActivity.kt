package za.co.house4hack.paint3d

import android.os.Bundle
import android.os.Environment
import androidx.appcompat.app.AppCompatActivity
import org.json.JSONArray
import za.co.house4hack.paint3d.stl.Layer
import za.co.house4hack.paint3d.stl.Point
import za.co.house4hack.paint3d.stl.Polygon

class MainActivity : AppCompatActivity() {

    val TEST_PATH1 = "[0.0, 95.0, 0.0, 0.23040606, 5.0, 0.0, 0.24020302, 1.464466, 1.464466, 0.24999999, 0.0, 5.0, 0.48040605, 0.0, 95.0, 0.490203, 1.464466, 98.53553, 0.49999997, 5.0, 100.0, 0.73040605, 95.0, 100.0, 0.740203, 98.53553, 98.53553, 0.75, 100.0, 95.0, 0.98040605, 100.0, 5.0, 0.990203, 98.53553, 1.464466, 1.0, 95.0, 0.0]"
    val PATH1 = "[0,70.4,86.427925,0.062500045,75.09411,85.53255,0.12500003,79.04646,82.846436,0.18750001,81.732574,78.89408,0.24999996,82.62795,74.19998,0.3125,81.732574,69.505875,0.37499997,79.04646,65.55352,0.43749988,75.09411,62.867416,0.4999999,70.4,61.97205,0.5625,65.70589,62.867416,0.625,61.75353,65.55352,0.68749994,59.06742,69.505875,0.74999994,58.17205,74.19998,0.8124999,59.06742,78.89408,0.87499976,61.75353,82.846436,0.9374998,65.70589,85.53255,1,70.4,86.427925]"
    val PATH2 = "[0.0, 25.800001, 34.05, 0.0625001, 30.50258, 33.15301, 0.12500013, 34.46207, 30.462055, 0.1875001, 37.15303, 26.502575, 0.25000006, 38.05002, 21.800007, 0.3125001, 37.15303, 17.097433, 0.3750001, 34.46207, 13.137947, 0.43750012, 30.50258, 10.446991, 0.5000002, 25.800001, 9.550005, 0.5625002, 21.09743, 10.446991, 0.6250002, 17.137943, 13.137947, 0.6875001, 14.446989, 17.097433, 0.7500002, 13.550005, 21.800007, 0.81250006, 14.446989, 26.502575, 0.87500006, 17.137943, 30.462059, 0.9375, 21.09743, 33.15301, 1.0, 25.800001, 34.05]\n"
    val PATH3 = "[0.0, 95.0, 0.0, 0.23040606, 5.0, 0.0, 0.24020302, 1.464466, 1.464466, 0.24999999, 0.0, 5.0, 0.48040605, 0.0, 95.0, 0.490203, 1.464466, 98.53553, 0.49999997, 5.0, 100.0, 0.73040605, 95.0, 100.0, 0.740203, 98.53553, 98.53553, 0.75, 100.0, 95.0, 0.98040605, 100.0, 5.0, 0.990203, 98.53553, 1.464466, 1.0, 95.0, 0.0]\n"

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val json = JSONArray(PATH3)
        val jsonIn1 = JSONArray(PATH1)
        val jsonIn2 = JSONArray(PATH2)
        //Assert.assertEquals(70.4f, json.getDouble(1))
        val layer = Layer()
        val polygon = Polygon()
        val polygonIn1 = Polygon()
        val polygonIn2 = Polygon()
        for (i in 0..(json.length()/3-1)) {
            polygon.add(Point(json.getDouble(i*3+1), json.getDouble(i*3+2)))
        }
        for (i in 0..(jsonIn1.length()/3-1)) {
            polygonIn1.add(Point(jsonIn1.getDouble(i*3+1), jsonIn1.getDouble(i*3+2)))
        }
        for (i in 0..(jsonIn2.length()/3-1)) {
            polygonIn2.add(Point(jsonIn2.getDouble(i*3+1), jsonIn2.getDouble(i*3+2)))
        }
        layer.set(0, polygon)
        layer.add(polygonIn1)
        layer.add(polygonIn2)

        val vectorPaint = VectorPaint(this)
        vectorPaint.layers.set(0, layer)
        vectorPaint.preview(Environment.getExternalStorageDirectory().absolutePath.toString()+"/VIVITA/test.stl")

    }
}