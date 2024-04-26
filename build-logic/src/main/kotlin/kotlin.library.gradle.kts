import com.image.jm.configureKotest
import com.image.jm.configureKotlin

plugins {
    kotlin("jvm")
    id("verify.detekt")
}

configureKotlin()
configureKotest()
