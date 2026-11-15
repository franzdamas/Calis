# Calis – Digital Classroom Management System

**Calis** is a Digital Classroom Management System (DCMS) developed for Senior High School at Bagong Nayon II National High School (BNIINHS).

The system aims to support classroom management through digital attendance monitoring and task management using a dedicated ESP32-based device and an Android mobile application.

## Project Overview

Calis consists of two main components:

- **Calis Device (CD):** An ESP32-based hardware device designed to facilitate classroom attendance and task-related operations.
- **Calis Mobile (CM):** An Android application that serves as the user interface for teachers and students.

The two components communicate through Bluetooth Low Energy (BLE), allowing classroom operations without requiring continuous internet connectivity.

## Features

- Digital attendance management
- Classroom task management
- Bluetooth Low Energy communication
- Offline classroom record storage
- Teacher and student interfaces
- Local database management

## Technologies Used

### Hardware and Firmware
- ESP32
- Arduino Framework
- Bluetooth Low Energy (BLE)

### Mobile Application
- Kotlin
- Android SDK
- Jetpack Compose
- Material Design 3
- MVVM Architecture
- Kotlin Coroutines and Flow
- Room Database
- Android Bluetooth GATT
- AndroidX Biometric
- WorkManager

## Repository Structure

The repository contains the source-code files and related resources for the Calis system.

- `Firmware/` – ESP32 firmware source code.
- `Mobile-App/` – Reconstructed Android application source code and associated resources.

*Refer to the repository folders for the available source files.*

## Source Code Notice

The Calis Device firmware is provided from the project's firmware sketch.

The Calis Mobile source files were reconstructed from the last stable APK using decompilation tools. Consequently, the recovered files may differ from the original Kotlin source code, including formatting, naming, and project organization.

The reconstructed source code is provided for documentation, technical reference, and research purposes.

## Research Project

**Project Title:** Development and Evaluation of a Digital Classroom Management System for Senior High School at Bagong Nayon II National High School

**Project:** Calis – Digital Classroom Management System

**Institution:** Bagong Nayon II National High School (BNIINHS)

---

*Developed as part of a Grade 12 research project.*
