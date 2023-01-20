package bel.lisoveliy.selector.logic

import android.util.Log
import java.net.URL

object Checks {
    fun checkm3u8url(url: String): Boolean{
        val Url: URL
        try{
            Url = URL(url)
                val answer = Url.readText()
            if(answer.contains("#EXTM3U"))
                return true
        }catch (e: Exception)
        {
            Log.e("Checks: m3u8URL", "Exception on check URL: $e")
            return false
        }
        return false
    }
}