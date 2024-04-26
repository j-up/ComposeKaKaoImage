plugins {
    id("android.library")
}

android {
    namespace = "com.image.jm.domain"
}

dependencies {
    implementation(projects.core.data)
    implementation(projects.core.model)
    implementation(libs.androidx.paging.compose)

    implementation(libs.inject)
}
