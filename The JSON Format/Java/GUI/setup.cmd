@echo off

mvn archetype:generate ^
    -DgroupId=com.dosomedev ^
    -DartifactId=api-gui-app ^
    -DarchetypeArtifactId=maven-archetype-quickstart ^
    -DarchetypeVersion=1.1 ^
    -DinteractiveMode=false
