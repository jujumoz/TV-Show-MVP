package sierra.com.data.extensions

import android.app.Application
import java.io.BufferedReader
import java.io.InputStreamReader
import java.io.Reader

fun Application.getTextFromFileAssets(fileName: String): String {
    val fileInputStream = this.assets.open(fileName)
    val buf = BufferedReader(InputStreamReader(fileInputStream) as Reader?)

    var line = buf.readLine()
    val sb = StringBuilder()

    while (line != null) {
        sb.append(line).append("\n")
        line = buf.readLine()
    }

    return sb.toString()
}
