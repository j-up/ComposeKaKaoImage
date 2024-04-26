
plugins {
    id("android.library")
}

android {
    namespace = "com.image.jm.domain"
}

dependencies {
    testImplementation(libs.junit4)
    testImplementation(libs.kotlin.test)
    implementation(libs.androidx.datastore)
}
