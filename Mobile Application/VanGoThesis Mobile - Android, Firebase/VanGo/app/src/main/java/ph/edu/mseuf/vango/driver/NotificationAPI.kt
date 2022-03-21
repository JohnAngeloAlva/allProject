package ph.edu.vangodriver


import okhttp3.ResponseBody
import ph.edu.vangodriver.Constants.Companion.CONTENT_TYPE
import ph.edu.vangodriver.Constants.Companion.SERVER_KEY
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