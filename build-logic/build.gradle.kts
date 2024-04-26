plugins {
    `kotlin-dsl`
    `kotlin-dsl-precompiled-script-plugins`
}

dependencies {
    implementation(libs.android.gradlePlugin)
    implementation(libs.kotlin.gradlePlugin)
    implementation(libs.verify.detektPlugin)
}

gradlePlugin {
    plugins {
        register("androidHilt") {
            id = "android.hilt"
            implementationClass = "com.image.jm.HiltAndroidPlugin"
        }
        register("kotlinHilt") {
            id = "kotlin.hilt"
            implementationClass = "com.image.jm.HiltKotlinPlugin"
        }
    }
}
