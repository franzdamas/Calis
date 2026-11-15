package com.example.ble

/**
 * Backward compatibility facade for BleManager.
 *
 * Dedicated BLE managers are separated into:
 * - [TeacherBleManager] for TC account (RID 0, persistent GATT connection, Teacher commands & session lifecycle)
 * - [StudentBleManager] for ST account (RIDs 1-5, temporary GATT connections per transaction, Student requests)
 */
typealias BleManager = TeacherBleManager
