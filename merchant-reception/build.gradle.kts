import org.jetbrains.kotlin.gradle.tasks.KotlinCompile

plugins {
    id("org.springframework.boot") version "3.2.2"
    id("io.spring.dependency-management") version "1.1.4"
    kotlin("jvm") version "1.9.22"
    kotlin("plugin.spring") version "1.9.22"
    id("org.openapi.generator") version "7.2.0"
    id("org.springdoc.openapi-gradle-plugin") version "1.6.0"
}

group = "com.example"
version = "0.0.1-SNAPSHOT"

java {
    sourceCompatibility = JavaVersion.VERSION_17
}

repositories {
    mavenCentral()
}

dependencies {
    implementation("org.springframework.boot:spring-boot-starter-web")
    implementation("org.springframework.boot:spring-boot-starter-validation")
    implementation("com.fasterxml.jackson.module:jackson-module-kotlin")
    implementation("org.jetbrains.kotlin:kotlin-stdlib-jdk8")
    implementation("io.swagger.core.v3:swagger-annotations:2.2.20")
    implementation("io.swagger.core.v3:swagger-models:2.2.19")
    implementation("org.springdoc:springdoc-openapi-starter-webmvc-ui:2.3.0")
    implementation("org.jetbrains.kotlin:kotlin-reflect")
    implementation("org.openapitools:openapi-generator-gradle-plugin:7.1.0") {
        exclude(group = "org.slf4j", module = "slf4j-simple")
    }
    developmentOnly("org.springframework.boot:spring-boot-devtools")
    testImplementation("org.springframework.boot:spring-boot-starter-test")
}

tasks.withType<KotlinCompile> {
    kotlinOptions {
        freeCompilerArgs += "-Xjsr305=strict"
        jvmTarget = "17"
    }
}

tasks.withType<Test> {
    useJUnitPlatform()
}

val basePackage = "com.example.merchantreception"

openApiGenerate {
    generatorName = "kotlin-spring"
    inputSpec = "$rootDir/specs/openapi.yaml"
    outputDir = "$buildDir/generated"
    apiPackage = "${basePackage}.api"
    invokerPackage = "${basePackage}.api.invoker"
    modelPackage = "${basePackage}.model"
    configOptions = mapOf(
        "useSpringBoot3" to "true",
        "interfaceOnly" to "true",
        "useTags" to "true",
        "skipDefaultInterface" to "true"
    )
    globalProperties = mapOf("modelDocs" to "false")
}

kotlin.sourceSets.main {
    kotlin.srcDir(openApiGenerate.outputDir)
}

openApi {
    outputDir.set(layout.projectDirectory.dir("tools/redoc/api/"))
    outputFileName.set("openapi.json")
}