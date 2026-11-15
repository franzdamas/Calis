# App UI Icons Catalog (Documentation)

This directory contains high-resolution PNG exports of all **69 icons** utilized in the Calis Android application UI.
All icons are exported with transparent backgrounds for seamless embedding into reports, user manuals, presentations, and design specifications.

---

## Directory Structure

- **`docs/icons/compose/`**: 53 Jetpack Compose Material Icons rendered at native resolution with transparent backgrounds.
- **`docs/icons/drawables/`**: 16 Custom App & Notification Vector Drawables and brand assets.

---

## 1. Custom Vector Drawables & Brand Assets (`docs/icons/drawables/`)

| File Name | Asset Resource | Description & UI Usage |
| :--- | :--- | :--- |
| `logo_calis.png` | `R.drawable.logo_calis` | Full-color Calis brand emblem |
| `logo_calis_inverted.png` | `R.drawable.logo_calis_inverted` | Inverted white logo used on startup splash & dark navigation drawers |
| `ic_launcher_foreground.png` | `R.drawable.ic_launcher_foreground` | Adaptive launcher icon foreground layer |
| `ic_launcher_background.png` | `R.drawable.ic_launcher_background` | Adaptive launcher icon background layer |
| `ic_notification_logo.png` | `R.drawable.ic_notification_logo` | Foreground notification brand icon for `CalisBleService` |
| `ic_stat_calis_logo.png` | `R.drawable.ic_stat_calis_logo` | Android status-bar notification monochrome icon |
| `ic_noti_app.png` | `R.drawable.ic_noti_app` | Notification small icon fallback |
| `ic_noti_bluetooth.png` | `R.drawable.ic_noti_bluetooth` | Bluetooth indicator in expanded notification toolbar |
| `ic_noti_esp32.png` | `R.drawable.ic_noti_esp32` | ESP32 Calis Device hardware vector in notification toolbar |
| `ic_noti_waves.png` | `R.drawable.ic_noti_waves` | Active wireless BLE signal transmission waves in notification |
| `ic_noti_dot.png` | `R.drawable.ic_noti_dot` | Live connection state indicator dot |
| `ic_noti_play.png` | `R.drawable.ic_noti_play` | "Start Session" quick action button in notification toolbar |
| `ic_noti_stop.png` | `R.drawable.ic_noti_stop` | "End Session" quick action button in notification toolbar |
| `ic_noti_connect.png` | `R.drawable.ic_noti_connect` | Quick-connect action in notification expanded view |
| `ic_noti_disconnect.png` | `R.drawable.ic_noti_disconnect` | Quick-disconnect action in notification expanded view |
| `ic_google_ai_studio.png` | `R.drawable.ic_google_ai_studio` | Google AI Studio platform logo in About Screen |

---

## 2. Compose Material Icons (`docs/icons/compose/`)

### Navigation & Directional
| File Name | Compose Symbol | Usage in App |
| :--- | :--- | :--- |
| `menu.png` | `Icons.Default.Menu` | Top app bar hamburger menu (opens side navigation drawer) |
| `arrow_back.png` | `Icons.Default.ArrowBack` | Registration & setup screen back button |
| `arrow_back_automirrored.png` | `Icons.AutoMirrored.Filled.ArrowBack` | Top app bar back button for sub-screens |
| `arrow_forward.png` | `Icons.Default.ArrowForward` | Step progression forward button |
| `arrow_forward_automirrored.png` | `Icons.AutoMirrored.Filled.ArrowForward` | Session details and step navigation forward arrow |
| `exit_to_app.png` | `Icons.AutoMirrored.Filled.ExitToApp` | Logout and leave classroom buttons |
| `keyboard_arrow_left.png` | `Icons.AutoMirrored.Filled.KeyboardArrowLeft` | Previous day/date pagination |
| `keyboard_arrow_right.png` | `Icons.AutoMirrored.Filled.KeyboardArrowRight` | Next day/date pagination and settings navigation chevron |
| `keyboard_arrow_down.png` | `Icons.Default.KeyboardArrowDown` | Debug console collapse/expand handle |
| `arrow_drop_down.png` | `Icons.Default.ArrowDropDown` | Dropdown selector for sort order and filter menus |

### Classroom Sessions & Attendance
| File Name | Compose Symbol | Usage in App |
| :--- | :--- | :--- |
| `play_arrow.png` | `Icons.Default.PlayArrow` | **Start Session** button in Teacher dashboard |
| `stop.png` | `Icons.Default.Stop` | **End Session** button in Teacher dashboard |
| `check.png` | `Icons.Default.Check` | Present attendance status pill, checked-in indicator |
| `check_circle.png` | `Icons.Default.CheckCircle` | Successful check-in / task submission confirmation |
| `close.png` | `Icons.Default.Close` | Absent attendance status pill, dismiss dialog |
| `cancel.png` | `Icons.Default.Cancel` | Cancel registration request, reject student |
| `clear.png` | `Icons.Default.Clear` | Clear search queries and text input fields |
| `access_time.png` | `Icons.Default.AccessTime` | Class schedule time, start/end time, grace period |
| `schedule.png` | `Icons.Default.Schedule` | Classroom schedule timing summary |
| `calendar_today.png` | `Icons.Default.CalendarToday` | Active class meeting days, date posted, deadlines |
| `table_chart.png` | `Icons.Default.TableChart` | Master attendance ledger / table view |
| `history.png` | `Icons.Default.History` | Attendance history logs modal |

### Task & Assignment Management
| File Name | Compose Symbol | Usage in App |
| :--- | :--- | :--- |
| `assignment.png` | `Icons.Default.Assignment` | Tasks navigation tab, task item card header |
| `add.png` | `Icons.Default.Add` | Floating Action Button (FAB) to create a new task |
| `send.png` | `Icons.Default.Send` | Student **Turn In / Submit Task** button, check-in transmission |
| `drag_handle.png` | `Icons.Default.DragHandle` | Reordering handles in custom export / task lists |
| `download.png` | `Icons.Default.Download` | **Export CSV** buttons (attendance & task submission data) |
| `sort.png` | `Icons.Default.Sort` | Sort tasks and students (by date, name, score) |
| `search.png` | `Icons.Default.Search` | Search bar for filtering students and tasks |

### Bluetooth, Hardware & Connectivity
| File Name | Compose Symbol | Usage in App |
| :--- | :--- | :--- |
| `bluetooth.png` | `Icons.Default.Bluetooth` | Bluetooth status badge, Calis Device discovery cards, scanning animations |
| `devices.png` | `Icons.Default.Devices` | Hardware specifications, Calis Device status cards |
| `smart_toy.png` | `Icons.Default.SmartToy` | Calis hardware bridge indicator in About screen |
| `science.png` | `Icons.Default.Science` | Prototype disclaimer & experimental BLE notice dialog |

### Profiles, Roles & Security
| File Name | Compose Symbol | Usage in App |
| :--- | :--- | :--- |
| `person.png` | `Icons.Default.Person` | User profile avatar, student name row, teacher name display |
| `group.png` | `Icons.Default.Group` | Students masterlist navigation tab, class roster summary |
| `group_add.png` | `Icons.Default.GroupAdd` | Pending student registrations approval badge |
| `groups.png` | `Icons.Default.Groups` | Team & credits listing in About screen |
| `school.png` | `Icons.Default.School` | Student mode indicator, classroom name tag |
| `badge.png` | `Icons.Default.Badge` | Student ID (SID) and DepEd Learner Reference Number (LRN) cards |
| `lock.png` | `Icons.Default.Lock` | PIN authentication entry, master PIN lock dialog |
| `fingerprint.png` | `Icons.Default.Fingerprint` | PIN keypad authentication, biometric/security visual cue |
| `backspace.png` | `Icons.Default.Backspace` | Numeric keypad backspace button for PIN entry |

### System & Utility
| File Name | Compose Symbol | Usage in App |
| :--- | :--- | :--- |
| `settings.png` | `Icons.Default.Settings` | Settings tab & configuration dialog |
| `notifications.png` | `Icons.Default.Notifications` | Notification permissions card, session reminder settings |
| `info.png` | `Icons.Default.Info` | Information dialogs, sync state banners, connection guidance |
| `help_outline.png` | `Icons.Outlined.HelpOutline` | Help guide tooltip buttons ("How connection works", "BLE Guide") |
| `warning.png` | `Icons.Default.Warning` | Bluetooth disabled warning, disconnect alert banners |
| `delete.png` | `Icons.Default.Delete` | Delete classroom, reset database, delete account danger actions |
| `share.png` | `Icons.Default.Share` | Share and export debug console logs |
| `auto_awesome.png` | `Icons.Default.AutoAwesome` | System feature highlights in About screen |
| `code.png` | `Icons.Default.Code` | Technical architecture & protocol details in About screen |
| `verified.png` | `Icons.Default.Verified` | Official developer & version validation tag |
