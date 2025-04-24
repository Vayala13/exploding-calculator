Here is how you will be running this on windows

Download Java:

https://adoptium.net/temurin/releases/?variant=openjdk21&jvmVariant=hotspot&os=windows&arch=x64

Install it:

Install path should look like this 
    C:\Program Files\Java\jdk-21.x.x

Set the environment
(this part i recommend you call me to walk you through it and ill make a yt vid for you in a bit I did this on my mac)

Open the start menu and go to "environment variables" and click it
Under the "system variables"
    find path, click edit
    add:
    C:\Program Files\Java\jdk-21.x.x\bin
click new and add a new variable
    Name:JAVA_HOME
    Value: C:\Program Files\Java\jdk-21.x.x

Verify Install
in powershell
    java -version

to run a Program
javac Cal.java // compiles
java Cal      // runs

