import Versions.navigationVersion

/**
 * Contains the versions of the dependencies being used
 */
object Versions {
    const val compileSdkVersion = 28
    const val minSdkVersion = 19
    const val targetSdkVersion = 28
    const val versionCode = 1
    const val versionName = "1"
    const val gradleBuildTool = "3.4.0"
    const val kotlinVersion = "1.3.30"
    const val supportLibraryVersion = "1.1.0-alpha04"
    const val constraintLayout = "1.1.3"
    const val junit = "4.12"
    const val testRunner = "1.1.0"
    const val espresso = "3.1.0"


    // App dependencies
    const val constraintLayoutVersion = "2.0.0-alpha3"
    const val navigationVersion = "1.0.0"
    const val roomVersion = "2.1.0-alpha06"
    const val glideVersion = "4.9.0"
    const val workVersion = "1.0.1"
    const val  ktxVersion = "1.0.1"
    const val lifecycleVersion = "2.1.0-alpha04"
    const val recyclerViewVersion = "1.1.0-alpha04"
    const val pagingVersion = "2.1.0"
    const val materialVersion = "1.0.0"
    const val gsonVersion = "2.8.2"
    const val coroutinesVersion = "1.1.1"


}

/**
 * Contains the dependencies being used by the project
 */
object Dependencies {
    const val gradle_build_tool = "com.android.tools.build:gradle:${Versions.gradleBuildTool}"
    const val kotlin_gradle_plugin = "org.jetbrains.kotlin:kotlin-gradle-plugin:${Versions.kotlinVersion}"
    const val kotlin_stdlib = "org.jetbrains.kotlin:kotlin-stdlib-jdk7:${Versions.kotlinVersion}"
    const val appComptact = "androidx.appcompat:appcompat:${Versions.supportLibraryVersion}"
    const val constraint_layout = "androidx.constraintlayout:constraintlayout:${Versions.constraintLayout}"
    const val junit = "junit:junit:${Versions.junit}"
    const val test_runner = "androidx.test:runner:${Versions.testRunner}"
    const val espresso = "androidx.test.espresso:espresso-core:${Versions.espresso}"

    const val navigation_gradle_plugin =
        "android.arch.navigation:navigation-safe-args-gradle-plugin:${Versions.navigationVersion}"
    const val room_kept = "androidx.room:room-compiler:${Versions.roomVersion}"
    const val glide_kept = "com.github.bumptech.glide:compiler:${Versions.glideVersion}"
    const val navigation_fragment = "android.arch.navigation:navigation-fragment-ktx:${Versions.navigationVersion}"
    const val navigtion_ui = "android.arch.navigation:navigation-ui-ktx:${Versions.navigationVersion}"
    const val work_manager = "android.arch.work:work-runtime-ktx:${Versions.workVersion}"
    const val core_kt = "androidx.core:core-ktx:${Versions.ktxVersion}"
    const val lifecycle_extensions = "androidx.lifecycle:lifecycle-extensions:${Versions.lifecycleVersion}"
    const val lifecycle_viewmodel = "androidx.lifecycle:lifecycle-viewmodel-ktx:${Versions.lifecycleVersion}"
    const val recyclerview = "androidx.recyclerview:recyclerview:${Versions.recyclerViewVersion}"
    const val paging =  "androidx.paging:paging-runtime-ktx:${Versions.pagingVersion}"

    const val room = "androidx.room:room-runtime:${Versions.roomVersion}"
    const val glide = "com.github.bumptech.glide:glide:${Versions.glideVersion}"
    const val material = "com.google.android.material:material:${Versions.materialVersion}"
    const val gson = "com.google.code.gson:gson:${Versions.gsonVersion}"
    const val coroutines_android = "org.jetbrains.kotlinx:kotlinx-coroutines-android:${Versions.coroutinesVersion}"
    const val coroutines_core = "org.jetbrains.kotlinx:kotlinx-coroutines-core:${Versions.coroutinesVersion}"
}


