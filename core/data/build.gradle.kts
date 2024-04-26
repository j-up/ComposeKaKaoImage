import org.jetbrains.kotlin.konan.properties.Properties
import java.io.FileInputStream

plugins {
    id("android.library")
    id("android.hilt")
    id("kotlinx-serialization")
}

val properties = Properties().apply {
    load(FileInputStream(File(rootProject.rootDir, "config.properties")))
}

android {
    namespace = "com.image.jm.data"

    buildFeatures {
        buildConfig = true
    }

    buildTypes {
        getByName("debug") {
            buildConfigField("String", "ACCESS_KEY", properties.getProperty("KAKAO_ACCESS_KEY"))
            buildConfigField("String", "BASE_URL", properties.getProperty("KAKAO_BASE_URL"))

        }
        getByName("release") {
            buildConfigField("String", "ACCESS_KEY", properties.getProperty("KAKAO_ACCESS_KEY"))
            buildConfigField("String", "BASE_URL", properties.getProperty("KAKAO_BASE_URL"))
        }
    }


}

dependencies {
    implementation(projects.core.model)
    implementation(projects.core.datastore)

    implementation(libs.retrofit.core)
    implementation(libs.retrofit.kotlin.serialization)
    implementation(libs.okhttp.logging)
    implementation(libs.kotlinx.serialization.json)
    implementation(libs.kotlinx.datetime)
    testImplementation(libs.turbine)
}
