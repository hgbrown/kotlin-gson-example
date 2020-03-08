package problem

import com.google.gson.GsonBuilder

data class Person(
    val name: String,
    val age: Int
) {

}

fun personFromJson(personString: String): Person {
    val gson = GsonBuilder().create()
    return gson.fromJson(personString, Person::class.java)
}
