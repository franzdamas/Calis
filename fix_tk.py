import os

with open("app/src/main/java/com/example/ui/viewmodel/StudentViewModel.kt", "r") as f:
    content = f.read()

old_tk_block = """                        if (_registrationStatus.value == "APPROVED_WAITING_TASKS" || _registrationStatus.value == "RECEIVING_TASKS" || _registrationStatus.value == "APPROVED_WAITING_DONE") {
                            _registrationStatus.value = "RECEIVING_TASKS"
                            _registrationTasks.value = _registrationTasks.value + task
                        }"""
new_tk_block = """                        if (_registrationStatus.value == "PENDING" || _registrationStatus.value == "APPROVED_WAITING_TASKS" || _registrationStatus.value == "RECEIVING_TASKS" || _registrationStatus.value == "APPROVED_WAITING_DONE") {
                            _registrationStatus.value = "RECEIVING_TASKS"
                            _registrationTasks.value = _registrationTasks.value + task
                        }"""
content = content.replace(old_tk_block, new_tk_block)

with open("app/src/main/java/com/example/ui/viewmodel/StudentViewModel.kt", "w") as f:
    f.write(content)

