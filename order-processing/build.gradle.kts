import org.jetbrains.kotlin.gradle.tasks.KotlinCompile

plugins {
    id("org.springframework.boot") version "3.2.2"
    id("io.spring.dependency-management") version "1.1.4"
    kotlin("jvm") version "1.9.22"
    kotlin("plugin.spring") version "1.9.22"
}

group = "com.example"
version = "0.0.1-SNAPSHOT"

java {
    sourceCompatibility = JavaVersion.VERSION_17
}

repositories {
    mavenCentral()
}


configurations {
    create("mybatisGenerator")
}

dependencies {
    add("mybatisGenerator", "org.mybatis.generator:mybatis-generator-core:1.4.2")
    add("mybatisGenerator", "com.mysql:mysql-connector-j:8.3.0")

    implementation("org.springframework.boot:spring-boot-starter")
    implementation("org.jetbrains.kotlin:kotlin-reflect")
    implementation("com.mysql:mysql-connector-j:8.3.0")
    implementation("net.devh:grpc-server-spring-boot-starter:3.0.0.RELEASE")
    implementation("io.grpc:grpc-kotlin-stub:1.4.1")
    implementation(project(":grpc-interface"))
    implementation("org.mybatis.spring.boot:mybatis-spring-boot-starter:3.0.3")
    implementation("org.mybatis.dynamic-sql:mybatis-dynamic-sql:1.5.0")
    testImplementation("org.springframework.boot:spring-boot-starter-test")
    testImplementation("org.jetbrains.kotlin:kotlin-test:1.9.23")
    testImplementation("org.assertj:assertj-core:3.25.3")
    testImplementation("io.mockk:mockk:1.13.10")
    implementation("org.mybatis.spring.boot:mybatis-spring-boot-starter-test:3.0.3")
}

tasks.register("mybatisGenerator") {
    ant.withGroovyBuilder {
        "taskdef"(
            "name" to "mbgenerator",
            "classname" to "org.mybatis.generator.ant.GeneratorAntTask",
            "classpath" to configurations.getByName("mybatisGenerator").asPath
        )
    }
    ant.invokeMethod(
        "mbgenerator",
        mapOf(
            "overwrite" to true,
            "configfile" to "${project.rootDir}/src/main/resources/generatorConfig.xml",
            "verbose" to true
        )
    )
}
tasks.getByName("mybatisGenerator").group = "mybatis"

tasks.withType<KotlinCompile> {
    kotlinOptions {
        freeCompilerArgs += "-Xjsr305=strict"
        jvmTarget = "17"
    }
}

tasks.withType<Test> {
    useJUnitPlatform()
}
