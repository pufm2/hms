HEALTH-CARE MANAGEMENT SYSTEM

I. Introduction
This zip file contains:
   * An Eclispe project (folder hms)
   * Installation guide and user manual of the program

II. Structure of the Eclipse project
    ¦   .classpath
    ¦   .project
    ¦   assembly.xml (packaging configuration of maven)
    ¦   HMS-test-1.db3 (test database for running unit test)
    ¦   HMS-test.db3 (test database for running unit test)
    ¦   HMS.db3 (sampe databse for the program)
    ¦   HMS.sql (sample sql script for the program)
    ¦   pom.xml (project object model of maven)
    +---.settings
    +---dist (contains start-up scripts for the program)
    +---lib (contains required libraries)
    +---src
        +---main (source code of the program)
        ¦   +---java
        ¦   ¦   +---puf
        ¦   ¦       +---m2
        ¦   ¦           +---hms
        ¦   ¦               +---db
        ¦   ¦               +---exception
        ¦   ¦               +---model
        ¦   ¦               ¦   +---support
        ¦   ¦               +---run
        ¦   ¦               +---utils
        ¦   ¦               +---view
        ¦   ¦                   +---datechooser
        ¦   ¦                       +---images
        ¦   +---resource
        +---test (junit tests of the program)
            +---java
                +---puf
                    +---m2
                        +---hms
                            +---db
                            +---model
                            ¦   +---support
                            +---view
III. How to import the Eclipse project
   * Unzip hms-dist-final.zip
   * Start Eclipse
   * Menu File >> Import >> Existing Projects into Workspace
   * Browse to the folder where you unzipped the archive hms-dist-final.zip
   * Check project: hms, then Finish
   
III. How to launch the program form Eclipse
   * Make sure the project is buildable
   * Open class puf.m2.hms.run.RunHMS
   * Run this class as a Java application
