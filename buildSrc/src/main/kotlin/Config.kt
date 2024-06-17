
object Config {
    object Versions {
        const val hiltVersion = "2.48"
        const val androidGradleVersion = "8.1.2"
        const val kotlinVersion = "1.9.10"
        const val kspVersion = "1.9.10-1.0.13"
    }

    object PluginIds {
        const val android = "com.android.application"
        const val androidLibrary = "com.android.library"
        const val kotlin = "org.jetbrains.kotlin.android"
        const val hilt = "com.google.dagger.hilt.android"

        const val ksp = "com.google.devtools.ksp"
        const val kotlinJvm = "org.jetbrains.kotlin.jvm"
        const val javaLibrary = "java-library"
    }
}