import com.image.jm.configureCoroutineAndroid
import com.image.jm.configureHiltAndroid
import com.image.jm.configureKotest
import com.image.jm.configureKotlinAndroid

plugins {
    id("com.android.library")
    id("verify.detekt")
}

configureKotlinAndroid()
configureKotest()
configureCoroutineAndroid()
configureHiltAndroid()
