import os

with open("app/src/main/java/com/example/ui/viewmodel/StudentViewModel.kt", "r") as f:
    content = f.read()

old_block = """        viewModelScope.launch {
            repository.activeBoundClassroom.collect { classroom ->
                _registeredClassroom.value = classroom
                if (classroom != null && _registrationStatus.value != "APPROVED_WAITING_DONE") {
                    _registrationStatus.value = "APPROVED"
                }
            }
        }"""
new_block = """        viewModelScope.launch {
            repository.activeBoundClassroom.collect { classroom ->
                _registeredClassroom.value = classroom
                // Only auto-approve if we are booting up (NOT_REGISTERED) and we found a classroom.
                // Do not interrupt an active registration flow.
                if (classroom != null && _registrationStatus.value == "NOT_REGISTERED") {
                    _registrationStatus.value = "APPROVED"
                }
            }
        }"""
content = content.replace(old_block, new_block)

with open("app/src/main/java/com/example/ui/viewmodel/StudentViewModel.kt", "w") as f:
    f.write(content)

