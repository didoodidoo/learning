import org.jetbrains.kotlin.gradle.tasks.KotlinCompile


plugins {
    id("org.springframework.boot") version "2.3.7.RELEASE"
    id("io.spring.dependency-management") version "1.0.10.RELEASE"
    kotlin("jvm") version "1.3.72"
    kotlin("plugin.spring") version "1.3.72"
}


group = "cn.code.odin"
version = "1.0.0"
java.sourceCompatibility = JavaVersion.VERSION_1_8

dependencies {
    implementation("org.springframework.boot:spring-boot-starter-web")
    implementation("com.alibaba.cloud:spring-cloud-starter-alibaba-nacos-discovery")
    implementation("org.jetbrains.kotlin:kotlin-reflect")
    implementation("org.jetbrains.kotlin:kotlin-stdlib-jdk8")
    testImplementation("org.springframework.boot:spring-boot-starter-test") {
        exclude(group = "org.junit.vintage", module = "junit-vintage-engine")
    }
}

repositories {
    mavenLocal()
    maven { setUrl("http://artifact.canway.net/maven-public/") }
    maven { setUrl("http://maven.aliyun.com/nexus/content/groups/public/") }
    mavenCentral()
}

extra["springCloudAlibabaVersion"] = "2.2.2.RELEASE"

dependencyManagement {
    imports {
        mavenBom("com.alibaba.cloud:spring-cloud-alibaba-dependencies:${property("springCloudAlibabaVersion")}")
    }
}

tasks.withType<Test> {
    useJUnitPlatform()
}

tasks.withType<KotlinCompile> {
    kotlinOptions {
        freeCompilerArgs = listOf("-Xjsr305=strict")
        jvmTarget = "1.8"
    }
}