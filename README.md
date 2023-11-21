# Read Me First
* This project demonstrates the SpEL argument reference issue with Spring Boot 3.2-RC2. Argument references in expressions are evaluated as null and throws the below exception:
	* ```IllegalArgumentException at org.springframework.security.authorization.method.ReactiveExpressionUtils.lambda$evaluateAsBoolean$0(ReactiveExpressionUtils.java:41)```
* This issue doesn't exist in Spring Boot 3.1.5. 
* Adding the ```-parameters``` compile-time flag resolves this issue. However, this isn't required in Spring Boot 3.1.5.

### Reproducing the issue using Gradle
* Clone the repository to a local directory.
* Run ```./gradlew clean test```

### Reproducing the issue using STS
* Import the project as an "Existing Gradle Project" into STS.
* Ensure that the below setting is unchecked in STS. (This should already be unchecked by default.)
	* Preferences -> Java -> Compiler -> Store information about method parameters (usable via reflection)
* Right-click on the project and "Run As -> JUnit Test"
	* Alternatively, "Run As -> Spring Boot App", and log in to the endpoint at http://localhost:8080 with "user" and the password copied from the console log to view the stack trace.

### Workarounds for this issue:
* Downgrade to Spring Boot 3.1.5.
* Apply the Spring Boot Gradle plugin by changing the "false" to "true" on line #3 in build.gradle. This adds the ```-parameters``` compile-time flag on the Gradle compile tasks.
	* This doesn't fix the issue on STS and isn't an option for libraries or other scenarios where the plugin shouldn't be applied to the project. 
* Configure STS and the Gradle JavaCompile task to include the ```-parameters``` compiler flag.