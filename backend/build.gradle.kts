plugins {
	java
	id("org.springframework.boot") version "2.7.13"
	id("io.spring.dependency-management") version "1.0.15.RELEASE"
}

group = "com.sparklemotion"
version = "0.0.1-SNAPSHOT"

java {
	sourceCompatibility = JavaVersion.VERSION_11
}

repositories {
	mavenCentral()
}

dependencies {
	implementation("org.springframework.boot:spring-boot-starter-data-jpa")
	implementation("org.springframework.boot:spring-boot-starter-security")
	implementation("org.springframework.boot:spring-boot-starter-web")
	annotationProcessor ("org.projectlombok:lombok")
	compileOnly ("org.projectlombok:lombok")
	developmentOnly("org.springframework.boot:spring-boot-devtools")
	runtimeOnly("org.postgresql:postgresql")
	implementation ("javax.persistence:javax.persistence-api")
	testImplementation("org.springframework.boot:spring-boot-starter-test")
	testImplementation("org.springframework.security:spring-security-test")
	implementation ("org.springdoc:springdoc-openapi-ui:1.7.0")
	implementation("io.jsonwebtoken:jjwt-api:0.11.2")
	implementation("io.jsonwebtoken:jjwt-impl:0.11.2")
	runtimeOnly("io.jsonwebtoken:jjwt-jackson:0.11.2")
	implementation ("org.springframework.boot:spring-boot-starter-validation")
	testImplementation ("org.mockito:mockito-core:3.12.4")
	testImplementation ("org.junit.jupiter:junit-jupiter-api:5.7.2")
	testRuntimeOnly ("org.junit.jupiter:junit-jupiter-engine:5.7.2")

}

tasks.withType<Test> {
	useJUnitPlatform()
}
