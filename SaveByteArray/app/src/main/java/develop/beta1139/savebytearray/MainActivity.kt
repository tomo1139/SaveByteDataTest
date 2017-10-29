package develop.beta1139.savebytearray

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Button
import java.io.*

class MainActivity : AppCompatActivity() {

    private val FileName = "FileName"
    private var mData = Something(123, "abc")

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        findViewById<Button>(R.id.saveButton).setOnClickListener {
            val bos = ByteArrayOutputStream()
            var  out: ObjectOutput? = null
            try {
                out = ObjectOutputStream(bos)
                out.writeObject(mData)
                out.flush()
                val bytes = bos.toByteArray()
                writeByteData(bytes)
            } finally {
                try {
                    bos.close();
                } catch (ex: IOException) {
                }
            }
        }

        findViewById<Button>(R.id.loadButton).setOnClickListener {
            val bytes = readByteData()

            val byteArrayInputStream = ByteArrayInputStream(bytes)
            val objectInputStream = ObjectInputStream(byteArrayInputStream)
            val something = objectInputStream.readObject() as Something
            objectInputStream.close()
            byteArrayInputStream.close()

            Log.e("dbg", "IntData: ${something.mIntData}, StrData: ${something.mStrData}")
        }
    }

    private fun writeByteData(bytes: ByteArray) {
        val fileOutput = openFileOutput(FileName, MODE_PRIVATE)
        fileOutput.write(bytes)
        fileOutput.close()
    }

    private fun readByteData(): ByteArray {
        val fileInput = openFileInput(FileName)
        val buffer = ByteArray(fileInput.channel.size().toInt())
        fileInput.read(buffer)
        return buffer
    }
}

