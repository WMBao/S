# SecuPatrol
--- A remote monitoring system based on an Android APP and an Arduino smart car. 

### Demo Video

[![mulACS-demo-desk-en](http://img.youtube.com/vi/5D_G2qRYl18/0.jpg)](http://www.youtube.com/watch?v=5D_G2qRYl18)

## Introduction
**SecuPatrol** is a remote monitoring system based on an Android APP and an Arduino smart car.
It is a course project for *(EI312) Science and Technology Innovation (Part 3-C)* (Autumn 2016). 


This project realizes an electronic patrol system involving Arduino patrol cars and an Android monitor and control terminal. 

### Prototype System

In the prototype system, the patrol car contains a Arduino Mega2560 v3 microcontroller and an Android smartphone. 
The smartphone is connected to the control terminal (another Android smartphone) via WiFi.
The control codes are sent to the Arduino microcontroller via Bluetooth.

### 4 Control Modes
* Key Mode -- The patrol car is controlled by the buttons displayed on screen.
* Gravity Mode -- The gravity sensor and accelerometer are utilized to detect the attitude of the smartphone so as to control the patrol car.
* Gesture Mode -- The car is controlled by swipes (in different directions) and taps on the screen.
* Voice Mode -- The car is controlled by human voice (Chinese). The control terminal can detect specific keywords by calling third-party APIs provided by [XUNFEI](http://www.xfyun.cn/sdk/dispatcher/).


### 5 Additional Function
* NFC Easy Connect -- The connection between two Android smartphones can be easily configured via NFC.
* Human Facial Detection -- Human faces can be detected in the live video streams received by the control terminal.
* Obstacle Detection -- Obstacles in the way of the patrol car can be detected via the infra-red sensor of the onboard smartphone.
* Snapshot -- Snapshots of the video streams can be captured and saved on the control terminal.
* Alarm -- The control side APP provides a button to trigger the alarm and call the police.



## Webpage

[http://eelab.sjtu.edu.cn/kc/2016-12/C34/](http://eelab.sjtu.edu.cn/kc/2016-12/C34/ "SecuPatrol Report Webpage (in Chinese)")

The link above points to the report webpage (in Chinese) with implementation details. 


## Contribution
Accomplish Android APP coding, related part of the report and the report webpage.



## Details(selected)

* The Android APP involved in this project is developed by Android Studio.

* The following figure illustrates the connections among Arduino system modules.

![Missing image](https://github.com/WMBao/SecuPatrol/tree/gh-pages/images/6.png)


