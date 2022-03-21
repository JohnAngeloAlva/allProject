package ph.edu.mseuf.vango

import okhttp3.ResponseBody
import ph.edu.mseuf.vango.Constants.Companion.CONTENT_TYPE
import ph.edu.mseuf.vango.Constants.Companion.SERVER_KEY
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.Headers
import retrofit2.http.POST

interface NotificationAPI {

    @Headers("Authorization: key=$SERVER_KEY", "Content-Type:$CONTENT_TYPE")
    @POST("fcm/send")
    suspend fun postNotification(
        @Body notification: PushNotification
    ): Response<ResponseBody>
}