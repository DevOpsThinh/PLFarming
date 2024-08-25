import org.gradle.api.tasks.testing.logging.TestLogEvent.*
import org.jetbrains.kotlin.gradle.dsl.JvmTarget
import org.jetbrains.kotlin.gradle.tasks.KotlinCompile
// import com.github.jengelman.gradle.plugins.shadow.tasks.ShadowJar

plugins {
  alias(libs.plugins.jetbrains.kotlin.jvm)
  application
  alias(libs.plugins.github.johnrengelman.shadow)
}

group = "com.dev4ever"
version = "1.0.0-SNAPSHOT"

repositories {
  mavenCentral()
}

//val mainVerticleName = "com.dev4ever.plfarm.PLFram"
//val launcherClassName = "io.vertx.core.Launcher"

val watchForChange = "src/**/*"
val doOnChange = "${projectDir}/gradlew classes"

//application {
//  mainClass.set(launcherClassName)
//}

dependencies {
  implementation(platform(libs.io.vertx.stack.depchain))
  implementation(libs.io.vertx.config)
  implementation(libs.io.vertx.camel.bridge)
  implementation(libs.io.vertx.circuit.breaker)
  implementation(libs.io.vertx.junit5)
  implementation(libs.io.vertx.lang.kotlin)
  implementation(libs.io.vertx.lang.kotlin.coroutines)
  implementation(libs.io.vertx.pg.client)
  implementation(libs.io.vertx.service.discovery)
  implementation(libs.io.vertx.web)
  implementation(libs.io.vertx.web.client)
  implementation(libs.kotlin.stdlib)
  implementation(libs.org.postgresql)
  implementation(libs.ongres.scram.client)
  // Testing
  testImplementation(libs.jetbrains.coroutines.test)
  testImplementation(libs.junit.jupiter)
}

val compileKotlin: KotlinCompile by tasks
compileKotlin.compilerOptions.jvmTarget = JvmTarget.JVM_21

//tasks.withType<ShadowJar> {
//  archiveClassifier.set("fat")
//  manifest {
//    attributes(mapOf("Main-Verticle" to mainVerticleName))
//  }
//  mergeServiceFiles()
//}

tasks.withType<Test> {
  useJUnitPlatform()
  testLogging {
    events = setOf(PASSED, SKIPPED, FAILED)
  }
}

//tasks.withType<JavaExec> {
//  args = listOf("run", mainVerticleName, "--redeploy=$watchForChange", "--launcher-class=$launcherClassName", "--on-redeploy=$doOnChange")
//}
