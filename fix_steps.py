import os

with open("app/src/main/java/com/example/ui/screens/StudentMainScreen.kt", "r") as f:
    content = f.read()

step3_old = """                        val step3State = when {
                            registrationStatus == "APPROVED_WAITING_TASKS" -> StepState.WAITING
                            registrationStatus == "RECEIVING_TASKS" || registrationStatus == "APPROVED_WAITING_DONE" -> StepState.ACTIVE
                            registrationStatus == "APPROVED" -> StepState.COMPLETED
                            else -> StepState.WAITING
                        }"""
step3_new = """                        val step3State = when {
                            registrationStatus == "APPROVED_WAITING_TASKS" || registrationStatus == "RECEIVING_TASKS" || registrationStatus == "APPROVED_WAITING_DONE" -> StepState.ACTIVE
                            registrationStatus == "APPROVED" -> StepState.COMPLETED
                            else -> StepState.WAITING
                        }"""
content = content.replace(step3_old, step3_new)

step4_old = """                        val step4State = when {
                            registrationStatus == "APPROVED" -> StepState.COMPLETED
                            registrationStatus == "RECEIVING_TASKS" || registrationStatus == "APPROVED_WAITING_DONE" || registrationStatus == "APPROVED_WAITING_TASKS" -> StepState.ACTIVE
                            else -> StepState.WAITING
                        }"""
step4_new = """                        val step4State = when {
                            registrationStatus == "APPROVED" -> StepState.COMPLETED
                            registrationStatus == "APPROVED_WAITING_DONE" -> StepState.ACTIVE
                            else -> StepState.WAITING
                        }"""
content = content.replace(step4_old, step4_new)

with open("app/src/main/java/com/example/ui/screens/StudentMainScreen.kt", "w") as f:
    f.write(content)
