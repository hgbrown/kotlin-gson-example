package problem

import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.assertThrows

internal class PersonTest {

    @Test
    internal fun `should be able to convert from string`() {
        val personString = """
        {
            "name": null,
            "age": 25
        }
    """.trimIndent()

        val person = personFromJson(personString)

        val e = assertThrows<TypeCastException> {
            person.name.trim()
        }

        assertThat(e.message).isEqualTo("null cannot be cast to non-null type kotlin.CharSequence")
    }

}