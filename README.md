# as_UIAutomation_QA
## Background
At AlphaSights we pride ourselves in having a fast-paced work environment and delivering working software quickly.  AlphaSights Quality Assurance created this project to help do this more effectively.  We seek to automate Integration and End-to-end (UI) test cases for our various applications, including but not limited to the Client Platform, PTO, and Delivery.  As it stands, our Development Teams do rigorous unit testing, but there are a lot of opportunities to create automated tests for end-to-end and integration test scenarios.  The QA Team will continually contribute to this project—adding new tests, page objects, and utility classes.  Several tools were used in as_UIAutomation_QA.  

This Project was composed in [Java](https://www.w3schools.com/java/) programming language.  It uses [Selenide](https://selenide.org/)—a framework for test automation powered by Selenium WebDriver.  Selenide is leveraged to automate the browser, implement waits, and handle most test assertions in this project.  We also use [TestNG](https://testng.org/doc/index.html)–an open-source test automation framework for Java.  It allows you to control test cases and their execution order.  TestNG is also great for running cross-browser tests and running tests in parallel.  [Maven](https://maven.apache.org/) is the build automation tool used for this project.  It is a popular open-source build tool that the Apache Group developed for building, publishing, and deploying several projects.

## Setup
There are different ways to go about installing Java and your IDE of choice, but we will include a common reliable approach.

### Install JDK & IntelliJ/Eclipse/VSCode(choose one)
#### Install JDK on Mac
1. Download the JDK .dmg file, jdk-15.interim.update.patch_osx-x64_bin.dmg from Java SE Downloads page.
Click Accept License Agreement.

2. From either the browser Downloads window or from the file browser, double-click the .dmg file to start it.
A Finder window appears that contains an icon of an open box and the name of the .pkg file.

3. Double-click the JDK 15.pkg icon to start the installation application.
The installation application displays the Introduction window.

4. Click Continue.
The Installation Type window appears.

5. Click Install.
A window appears that displays the message: Installer is trying to install new software. Enter your password to allow this.

6. Enter the Administrator user name and password and click Install Software.
The software is installed and a confirmation window is displayed.

For More Information, including System Requirements, Uninstallation and more [Click Here](https://docs.oracle.com/en/java/javase/15/install/installation-jdk-macos.html#GUID-2FE451B0-9572-4E38-A1A5-568B77B146DE)

#### Install IntelliJ
1. Navigate to the [disk images](https://www.jetbrains.com/idea/download/#section=mac) and select one for download.
By default the Application downloads to your Downloads folder.

2. Mount the image and drag the IntelliJ IDEA app to the Applications folder.

Now IntelliJ should be able to be run from Applications, Launchpad or Spotlight.  For more info: [Click Here](https://www.jetbrains.com/help/idea/installation-guide.html#silent)

#### Install Eclipse
1. Navigate to the [disk images](https://download.eclipse.org/eclipse/downloads/) and select one for download.  By default the Application downloads to your Downloads folder.

2. Mount the image and drag the Eclipse app to the Applications folder.

#### Install VS Code
1. Navigate to the [disk images](https://code.visualstudio.com/download) and select one for download.  By default the Application downloads to your Downloads folder.

2. Mount the image and drag the Eclipse app to the Applications folder.


### Set JAVAHOME path in MAC
In order to set the JAVAHOME path on your Mac you must open Terminal or whatever command line interface use like to use.  Then you enter the code below, following the action steps in the comments

'''
vim ~/.bash_profile
//Press Enter
//Once you're within .bash_profile, hit I, INSERT will display at the bottom of your Terminal.  Enter the text below.

export JAVA_HOME=$(/usr/libexec/java_home)
//Hit Esc, then :wq, and then ENTER.  You will have written that to .bash_profile and closed the file.

//In order to update your .bash_profile with the latest changes
source~/.bash_profile

//To verify JAVA_HOME has been set enter the following
echo $JAVA_HOME
'''


### Set Up your resources folder locally
In order to handle a lot of configuration details that are passed locally to your tests, you will need to create a resources directoy within the github repo, which is to be excluded from versioning.  This is because everyone can manage config details differently to meet their personal needs.  The 'resources' directory should be in /Users/user/Documents/GitHub/as_UIAutomation_QA or whatever path you have the automation project downloaded to.  

[Here](https://drive.google.com/drive/folders/1rfoAITEkdpXka1dYQXpapx4paT7O41i9) are example files to add to that directory.  You can customize as you see fit.  **This shared drive folder is Restricted**.  If you are unable to access it, please reach out to the QA Manager or a more Senior QA Team member.

## Tips, Tricks & Troubleshooting
### Ensure resources folder setup correctly
See [Set Up your resources folder locally](#Set Up your resources folder locally)
#### System name issue
Potentially the dynamic paths within various packages in this project may not work for you.  This would most likely be due to your system having had a custom hostname added tyo your Mac.  To resolve this issue you can do 1 of 2 things.  You can change general references to User in paths to match your custom hostname, or you could [change you computer's name](https://support.apple.com/guide/mac-help/change-computers-local-hostname-mac-mchlp2322/mac#:~:text=You%20can%20view%20and%20change,may%20need%20to%20scroll%20down.).

### AWS VPN IPv6 Issue & resolution
Various Internet Service Providers such as Verizon Fios have been rolling out IPv6 to their customers.  Currently, AWS VPN, which we use to access the UI Automation tester account credentials (for Google authentication) does not fully support IPv6: [Read Here](https://docs.aws.amazon.com/vpn/latest/s2svpn/ipv4-ipv6.html).  To resolve this, go to Advanced wifi settings and change the Configure IPv6 setting to Link-local only



