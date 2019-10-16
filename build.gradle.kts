import org.jetbrains.kotlin.gradle.tasks.KotlinCompile

plugins {
    application
    kotlin("jvm") version "1.3.50"
    kotlin("kapt") version "1.3.50"
    id("org.jetbrains.kotlin.plugin.allopen") version "1.3.50"
    id("com.google.cloud.tools.jib") version "1.6.1"
}

repositories {
    mavenLocal()
    mavenCentral()
    jcenter()
}

dependencies {
    implementation(enforcedPlatform("io.micronaut:micronaut-bom:1.2.4"))
    compileOnly(enforcedPlatform("io.micronaut:micronaut-bom:1.2.4"))
    annotationProcessor(enforcedPlatform("io.micronaut:micronaut-bom:1.2.4"))

    kapt(enforcedPlatform("io.micronaut:micronaut-bom:1.2.4"))
    kapt("io.micronaut:micronaut-inject-java")

    implementation(kotlin("stdlib"))
    implementation(kotlin("reflect"))
    implementation("io.micronaut:micronaut-runtime")
    implementation("io.micronaut:micronaut-http-server-netty")
    implementation("com.github.haifengl:smile-interpolation:1.5.3")
    implementation("com.github.haifengl:smile-netlib:1.5.3")
    //implementation("com.github.fommil.netlib:all:1.1.2")

    runtimeOnly("ch.qos.logback:logback-classic:1.2.3")
}

java {
    sourceCompatibility = JavaVersion.VERSION_1_8
}

tasks.withType<KotlinCompile> {
    kotlinOptions {
        jvmTarget = JavaVersion.VERSION_1_8.toString()
        javaParameters = true
    }
}

application {
    mainClassName = "hello.WebAppKt"
}

allOpen {
    annotation("io.micronaut.aop.Around")
}

tasks.all {
    when(this) {
        is JavaForkOptions -> {
            jvmArgs("-noverify")
            jvmArgs("-XX:TieredStopAtLevel=1")
        }
    }
}

jib {
    container {
        mainClass = application.mainClassName
        ports = listOf("8080")
    }
}
