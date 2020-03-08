package fixed

import com.google.gson.GsonBuilder

data class Employee(
    val name: String,
    val age: Int
)

fun employeeFromJson(employeeString: String): Employee {
    val gson = GsonBuilder()
        .registerTypeAdapterFactory(NullableTypAdapterFactory())
        .create()
    return gson.fromJson(employeeString, Employee::class.java)
}