# Java 8 Streams - Notes

---

## What is a Stream?

A Stream is a sequence of elements that supports aggregate operations. It does NOT store data — it processes data from a source (List, Set, Array, etc.).

```java
List<Employee> employees = Arrays.asList(...);
employees.stream()   // creates a Stream<Employee>
         .filter(...)
         .collect(...);
```

---

## Stream Pipeline

Every stream has 3 parts:

```
Source → Intermediate Operations → Terminal Operation
```

| Part | Example | Returns |
|------|---------|---------|
| Source | `list.stream()` | `Stream<T>` |
| Intermediate | `filter()`, `map()`, `sorted()` | `Stream<T>` (lazy) |
| Terminal | `collect()`, `forEach()`, `count()` | Result / void |

Intermediate operations are **lazy** — they don't execute until a terminal operation is called.

---

## map() vs mapToDouble() vs mapToInt()

### `map()` — Stream\<T\> to Stream\<R\> (object stream)

Use when transforming one object type to another.

```java
// Employee -> String (name)
employees.stream()
    .map(a -> a.getName())        // Stream<String>
    .collect(Collectors.toList());

// Employee -> Double (salary) — boxed object
employees.stream()
    .map(a -> a.getSalary())      // Stream<Double> — uses autoboxing
    .collect(Collectors.toList());
```

### `mapToDouble()` — Stream\<T\> to DoubleStream (primitive)

Use when doing numeric calculations (average, sum, min, max).

```java
employees.stream()
    .mapToDouble(a -> a.getSalary())   // DoubleStream — no boxing overhead
    .average()
    .orElse(0.0);
```

### `mapToInt()` — Stream\<T\> to IntStream (primitive)

Use when working with int values for numeric operations.

```java
employees.stream()
    .mapToInt(a -> a.getAge())    // IntStream
    .sum();
```

---

## Comparison Table

| Method | Returns | Boxing | Built-in Math Ops |
|--------|---------|--------|-------------------|
| `map()` | `Stream<T>` | Yes (autoboxing) | No |
| `mapToDouble()` | `DoubleStream` | No | `average()`, `sum()`, `min()`, `max()` |
| `mapToInt()` | `IntStream` | No | `average()`, `sum()`, `min()`, `max()` |

**Rule of thumb:**
- Transforming objects → use `map()`
- Numeric calculations → use `mapToDouble()` / `mapToInt()`

---

## boxed()

`.boxed()` converts a **primitive stream** into an **object stream**:

| Primitive Stream | After `.boxed()` |
|-----------------|-----------------|
| `IntStream` | `Stream<Integer>` |
| `LongStream` | `Stream<Long>` |
| `DoubleStream` | `Stream<Double>` |

### Why use it?

Primitive streams (`DoubleStream`, `IntStream`) have limited operations. `.boxed()` unlocks all `Stream<T>` operations — most commonly `sorted(Comparator)`.

```java
// DoubleStream.sorted() — ascending only, no Comparator overload
.mapToDouble(Employees::getSalary)
.sorted()                           // always ascending

// Stream<Double> after .boxed() — accepts Comparator
.mapToDouble(Employees::getSalary)
.boxed()
.sorted(Comparator.reverseOrder())  // ✓ descending
```

### Real example — Second Highest Salary

```java
double result = employees.stream()
    .mapToDouble(Employees::getSalary)
    .distinct()
    .boxed()                              // DoubleStream → Stream<Double>
    .sorted(Comparator.reverseOrder())    // [75000, 60000, 55000, 45000]
    .skip(1)                              // skip highest → [60000, 55000, 45000]
    .findFirst()                          // Optional<Double> → 60000
    .orElse(0.0);                         // unwrap to double
```

### Trade-off

| | Primitive Stream | After `.boxed()` |
|---|---|---|
| Memory | Efficient (no boxing) | Slight overhead (wraps in objects) |
| Operations | Limited (`sum`, `average`, etc.) | Full `Stream<T>` API |

Use `.boxed()` only when you need an operation not available on the primitive stream.

---

## Common Terminal Operations

| Operation | Description | Example |
|-----------|-------------|---------|
| `collect()` | Collects into collection or string | `collect(Collectors.toList())` |
| `forEach()` | Iterates each element | `forEach(System.out::println)` |
| `count()` | Count of elements | `stream.count()` |
| `average()` | Average (only on DoubleStream/IntStream) | `.mapToInt(...).average()` |
| `sum()` | Sum (only on DoubleStream/IntStream) | `.mapToDouble(...).sum()` |
| `min()` / `max()` | Min or max element | `.min(Comparator.comparing(...))` |
| `findFirst()` | First matching element | `.filter(...).findFirst()` |
| `anyMatch()` | Returns true if any element matches | `.anyMatch(a -> a.getAge() > 30)` |
| `allMatch()` | Returns true if all elements match | `.allMatch(a -> a.getAge() > 18)` |

---

## Common Intermediate Operations

| Operation | Description | Example |
|-----------|-------------|---------|
| `filter()` | Keeps elements matching condition | `filter(a -> a.getAge() > 25)` |
| `map()` | Transforms each element | `map(a -> a.getName())` |
| `sorted()` | Sorts elements | `sorted(Comparator.comparing(...))` |
| `distinct()` | Removes duplicates | `distinct()` |
| `limit()` | Limits number of elements | `limit(5)` |
| `skip()` | Skips first N elements | `skip(2)` |

---

## Collectors

### toList()
```java
List<String> names = employees.stream()
    .map(a -> a.getName())
    .collect(Collectors.toList());
```

### joining() — concatenate strings
```java
// Basic
String result = employees.stream()
    .map(a -> a.getName())
    .collect(Collectors.joining(", "));
// Output: "Alice, Bob, Carol"

// With prefix and suffix
String result = employees.stream()
    .map(a -> a.getName())
    .collect(Collectors.joining(", ", "[", "]"));
// Output: "[Alice, Bob, Carol]"
```

### groupingBy()
```java
// Group employees by department
Map<String, List<Employee>> byDept = employees.stream()
    .collect(Collectors.groupingBy(a -> a.getDepartment()));
```

### counting()
```java
// Count employees per department
Map<String, Long> countByDept = employees.stream()
    .collect(Collectors.groupingBy(
        a -> a.getDepartment(),
        Collectors.counting()
    ));
```

---

## Practice Problems Solved

### P1 - Concatenate all names
```java
String result = employees.stream()
    .map(a -> a.getName())
    .collect(Collectors.joining(", "));
// Output: "Alice, Bob, Carol"
```

### P14 - Average Age
```java
double avgAge = employees.stream()
    .mapToDouble(a -> a.getAge())
    .average()
    .orElse(0.0);
// Output: 27.67
```

---

## Common Mistakes

1. **Missing space in joining separator**
   ```java
   // Wrong — produces "Alice,Bob,Carol"
   Collectors.joining(",")

   // Correct — produces "Alice, Bob, Carol"
   Collectors.joining(", ")
   ```

2. **Using getSalary() instead of getAge() for age problems** — double-check the field name matches the problem.

3. **Using getAsDouble() instead of orElse()** — `getAsDouble()` throws `NoSuchElementException` on empty stream.
   ```java
   // Risky
   .average().getAsDouble()

   // Safe
   .average().orElse(0.0)
   ```

4. **Calling map() and expecting average()** — `Stream<Double>` does not have `average()`. Use `mapToDouble()` to get `DoubleStream`.
