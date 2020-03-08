# Kotlin GSON Example

GSON and Kotlin are most compatible even though there are some rough edges.

For one thing, GSON doesn't quite understand the Kotlin nullable types.
And since GSON uses reflection for accessing properties it can bypass the usual Kotlin type checks.
This can lead to surprising results.
For instance have a look at: `Person` where both properties are expected not to be `null`.
However, `PersonTest` clearly demonstrates that it is possible to get a person where the `name` property is not only `null`
but which fails with a `TypeCastException` when we attempt to access it.

## Fixing this problem

We can fix this problem by using Kotlin reflection library.
To do so, we use the `com.google.gson.TypeAdapterFactory`

Now as can be seen with the `Employee` and `EmployeeTest` classes, the much more expected `JsonParseException` exception
is thrown on the line that is expected.

## Downsides

Of course, there are some downsides to this approach.

- Firstly, you have the dependency on the Kotlin reflection library.
- Secondly, the deserialisation process now has the overhead of having to type check all the fields while perform the deserialisation.

## Alternatives

However, there are other libraries that support Kotlin nullable types out-of-the-box, such as Jackson or Moshi. 
Jackson is one of the most widely-used JSON libraries for Java, and Moshi is a particularly modern framework that 
specifically supports Kotlin.