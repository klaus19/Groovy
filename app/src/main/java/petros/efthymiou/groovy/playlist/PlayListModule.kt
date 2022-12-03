package petros.efthymiou.groovy.playlist

import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.FragmentComponent
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory


@Module
@InstallIn(FragmentComponent::class)
class PlayListModule {

    @Provides
    fun playListAPI(retrofit: Retrofit)=
        retrofit.create(PlayListAPI::class.java)


    @Provides
    fun retrofit()= Retrofit.Builder()
        .baseUrl("http://192.168.1.5.2999")//please check if ut matches your current local ip.
        .client(OkHttpClient())
        .addConverterFactory(GsonConverterFactory.create())
        .build()


}