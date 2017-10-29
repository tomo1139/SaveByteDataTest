package develop.beta1139.savebytearray

import android.content.Context
import android.content.Context.MODE_PRIVATE
import java.io.FileInputStream
import java.io.FileNotFoundException
import java.io.FileOutputStream
import java.io.IOException

/**
 * Created by tomo on 2017/10/29.
 */
object Storage {

    @Throws(FileNotFoundException::class, IOException::class)
    fun writeByteData(context: Context, fileName: String, bytes: ByteArray) {
        var fos: FileOutputStream? = null
        try {
            fos = context.openFileOutput(fileName, MODE_PRIVATE)
            fos.write(bytes)
        } catch (e: FileNotFoundException) {
            throw e
        } catch (e: IOException) {
            throw e
        } finally {
            fos?.close()
        }
    }

    @Throws(FileNotFoundException::class, IOException::class)
    fun readByteData(context: Context, fileName: String): ByteArray {
        var fis: FileInputStream? = null
        try {
            fis = context.openFileInput(fileName)
            val buffer = ByteArray(fis.channel.size().toInt())
            fis.read(buffer)
            return buffer
        } catch (e: FileNotFoundException) {
            throw e
        } catch (e: IOException) {
            throw e
        } finally {
            fis?.close()
        }
    }
}