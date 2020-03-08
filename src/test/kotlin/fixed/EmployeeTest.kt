package fixed

import com.google.gson.JsonParseException
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.assertThrows

internal class EmployeeTest {

    @Test
    internal fun `should be able to convert from string`() {
        val employeeString = """
        {
            "name": null,
            "age": 25
        }
    """.trimIndent()

        val e = assertThrows<JsonParseException> {
            employeeFromJson(employeeString)
        }

        assertThat(e.message).isEqualTo("Value of non-nullable member [name] cannot be null")

    }
}