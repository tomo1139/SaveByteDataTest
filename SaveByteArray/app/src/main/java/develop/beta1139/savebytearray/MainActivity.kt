package develop.beta1139.savebytearray

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Button

class MainActivity : AppCompatActivity() {

    private val FileName = "FileName"
    private var mData = Something(123, "abc")

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        findViewById<Button>(R.id.saveButton).setOnClickListener {
            try {
                val bytes = Serializer.objectToBytes(mData)
                Storage.writeByteData(this, FileName, bytes)
            } catch (e: Exception) {
                Log.e("dbg", "write fail e: $e")
            }
        }

        findViewById<Button>(R.id.loadButton).setOnClickListener {
            try {
                val bytes = Storage.readByteData(this, FileName)
                val obj = Serializer.bytesToObject<Something>(bytes)
                Log.e("dbg", "IntData: ${obj.mIntData}, StrData: ${obj.mStrData}")
            } catch (e: Exception) {
                Log.e("dbg", "read fail e: $e")
            }
        }
    }
}
