package develop.beta1139.savebytearray

import android.util.Log
import java.io.*

/**
 * Created by tomo on 2017/10/29.
 */
object Serializer {

    @Throws(IOException::class)
    fun objectToBytes(any: Any): ByteArray {
        var bos: ByteArrayOutputStream? = null
        var oos: ObjectOutputStream? = null
        try {
            bos = ByteArrayOutputStream()
            oos = ObjectOutputStream(bos).apply {
                writeObject(any)
                flush()
                reset()
            }
            return bos.toByteArray()
        } catch (e: IOException) {
            throw e
        } finally {
            bos?.close()
            oos?.close()
        }
    }

    @Throws(IOException::class, ClassNotFoundException::class)
    fun <T> bytesToObject(bytes: ByteArray): T {
        var bis: ByteArrayInputStream? = null
        var ois: ObjectInputStream? = null
        try {
            bis = ByteArrayInputStream(bytes)
            ois = ObjectInputStream(bis)
            return ois.readObject() as T
        } catch (e: IOException) {
            throw e
        } catch (e: ClassNotFoundException) {
            throw e
        } finally {
            bis?.close()
            ois?.close()
        }
    }

}