package fixed

import com.google.gson.Gson
import com.google.gson.JsonParseException
import com.google.gson.TypeAdapter
import com.google.gson.TypeAdapterFactory
import com.google.gson.reflect.TypeToken
import com.google.gson.stream.JsonReader
import com.google.gson.stream.JsonWriter
import kotlin.jvm.internal.Reflection
import kotlin.reflect.KClass
import kotlin.reflect.full.memberProperties

class NullableTypAdapterFactory : TypeAdapterFactory {

    override fun <T : Any?> create(gson: Gson, type: TypeToken<T>): TypeAdapter<T>? {

        // Get the adapter Gson would usually use
        val delegate = gson.getDelegateAdapter(this, type)

        // If it is not a Kotlin class then we don't use this adapter
        if (type.rawType.declaredAnnotations.none { it.annotationClass.qualifiedName == "kotlin.Metadata" }) {
            return null
        }

        return object : TypeAdapter<T>() {

            override fun write(out: JsonWriter, value: T?) = delegate.write(out, value)

            override fun read(input: JsonReader): T? {
                val value: T? = delegate.read(input)

                if (value != null) {
                    val kotlinClass: KClass<Any> = Reflection.createKotlinClass(type.rawType)

                    // Ensure none of its non-nullable fields were deserialised to null
                    kotlinClass.memberProperties.forEach {
                        if (!it.returnType.isMarkedNullable && it.get(value) == null) {
                            throw JsonParseException("Value of non-nullable member [${it.name}] cannot be null")
                        }
                    }
                }
                return value
            }
        }

    }

}