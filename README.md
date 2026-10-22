# SwiftSearch

A Java program for the **SwiftBot**, a Raspberry Pi robot. The robot wanders
around, uses its ultrasound sensor to find objects, and reacts to them according
to the mode you choose. You choose the mode by showing it a QR code.

<u>**Note:**</u> This program forms part of the Year-1 Group Project of Brunel University London's Computer Science course.

## Modes

| Mode | What the robot does |
|---|---|
| **Curious Swiftbot** | Moves toward an object, stays about 15 cm away from it, takes a photo, then watches it for 5 seconds. If the object moves, the robot follows it and keeps watching. |
| **Scaredy Swiftbot** | When an object comes within 100 cm, the robot takes a photo, flashes red, turns around and runs away. It then waits for 5 seconds and runs again if anything comes close. |
| **Dubious Swiftbot** | Picks Curious or Scaredy at random. |

Underlights: blue while wandering, green in Curious mode, red in Scaredy mode.
Photos are saved on the robot as `/home/pi/Documents/Image_<n>.jpg`.

## Requirements

- A SwiftBot (Raspberry Pi) with its camera and ultrasound sensor
- Java 17
- Two libraries that are **not included in this repository**. Put both in a
  `lib/` folder at the top of the project:
  - `SwiftBotAPI-5.1.0.jar`: Brunel University's proprietary SwiftBot API.
  - `jsch-0.1.55.jar`: only needed to copy the program to the robot with Ant.
    Download it from
    [Maven Central](https://repo1.maven.org/maven2/com/jcraft/jsch/0.1.55/jsch-0.1.55.jar).

## Build and deploy

1. Compile the source into `bin/`:

   ```bash
   javac -d bin -cp lib/SwiftBotAPI-5.1.0.jar $(find src -name '*.java')
   ```

   Opening the project in Eclipse also compiles it into `bin/` automatically.

2. Package it as `SwiftSearchRefined.jar` (with the SwiftBot API inside) and
   copy it to `~/Documents` on the robot:

   ```bash
   ant -lib lib/jsch-0.1.55.jar \
       -Dip_from_user=<robot-ip-address> \
       -Dpassword_from_user=<robot-password>
   ```

   The robot's username is set to `pi` in `build.xml`.

## Run

On the robot:

```bash
cd ~/Documents
java -cp SwiftSearchRefined.jar main.program.Main
```

## Controls

1. On the welcome screen, type `q` and press Enter. The camera turns on.
2. Hold a QR code in front of the camera. It must decode to exactly
   `Curious Swiftbot`, `Scaredy Swiftbot` or `Dubious Swiftbot`.
3. Type `1` to start that mode or `2` to go back.
4. To stop, press the **X** button on the robot. To see a summary (photos
   taken, objects detected, how long it ran and the mode), press **Y**.
   Otherwise, press **X** again.

## Project structure

```
src/
├── main/program/Main.java          Entry point; starts the chosen mode
├── cli/                            Terminal menu and messages
└── swift/search/modes/             The three modes and their shared base class
build.xml                           Ant script that builds the jar and copies it to the robot
```
