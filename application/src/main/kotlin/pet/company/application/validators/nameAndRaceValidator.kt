package pet.company.application.validators

fun nameAndRaceValidator(rawName: String) : String{
    val regex = Regex("[0-9!@#\$%^&*()_+\\-=\\[\\]{};':\"\\\\|,.<>\\/?]")
    val nameFormated = regex.replace(rawName, "")

    return nameFormated
}