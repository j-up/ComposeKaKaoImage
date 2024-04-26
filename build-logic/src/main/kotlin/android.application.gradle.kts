import com.image.jm.configureHiltAndroid
import com.image.jm.configureKotestAndroid
import com.image.jm.configureKotlinAndroid

plugins {
    id("com.android.application")
}

configureKotlinAndroid()
configureHiltAndroid()
configureKotestAndroid()
