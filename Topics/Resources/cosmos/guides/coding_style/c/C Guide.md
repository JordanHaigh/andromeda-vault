# C

## Scope and conventions

This page documents the C style used by this Cosmos collection. It identifies Kernel Normal Form (KNF) as its basis and covers braces, indentation, conditionals, function declarations, and comments. Treat it as a repository-specific convention; adapt it only when the surrounding project follows the same style.

## C Code style

C is a general-purpose mid level, procedural computer programming language originally developed between 1969 and 1973 and founded by Dennis Ritchie in AT&T Labs in 1972.

### C Programming Style Guide

This code style is known as Kernel Normal Form (KNF).

## Index
- [Braces](#braces)
- [Indentation](#indentation)
- [Conditionals](#conditionals)
- [Functions](#functions)
- [Comments](#comments)

## Braces

All braces should go on the same line as whatever the braces are delimiting, with the only exception being functions. For if/else statements, braces should only be used as required.

```C
int
main(int argc, char *argv[])
{
	if (!some_function()) {
		puts("Something happened!");
		do_a_thing();
	} 
	else
	{
		some_other_thing();
        }
	return (0);
}
```

*argc stands for arguments count(ARGument count)
*argv stands for arguments values(ARGument values)
*argv[0] is the name of the program 

## Indentation

Indentation is done with a single tab character (Hard Tab). For code split across multiple lines a helper indent of 4 spaces is used.

```C
int
some_really_long_function(int a, int b, int c, int d,
    int e, int f)
{
	do_something();
```

## Conditionals

```C
if (a == 9) {
```

```C
for (;;)
```

Each case in a switch statement should not be indented but the code for each should be. Any case fallthroughs should be commented.
Every case should be terminated with a break statement.

```C
switch (ch) {
case 'a':
	a_count++;
	/* FALLTHROUGH */
case 'b':
	do_something();
	break;
case 'c'
	other_thing();
	break;
default:
	def_thing();
}
```

## Functions

Functions should have the type on a seperate line proceeding the rest of the function definition.

```C
int
main(int argc, char *argv[])
{
//Block of Code
//" " "
//" " "
}
```

Return statements should have the value wrapped in parenthesis.

```C
return (0);
```

## Comments

Always use C style comments (`/* */`) and not C++ style comments (`//`). A sample is shown below.

```C

/* One line comment */ 

/*
 * Multiline comment. Fill it out like it were
 * a paragraph.
 */

```

## Applying this guide in a current C project

KNF is a recognizable historical C convention, not a universal modern standard. Follow the formatting and review rules of the repository you are contributing to. If the project uses an automated formatter, treat its configuration as the source of truth so code review focuses on behavior rather than personal whitespace preferences.

The examples above show the guide's intent, but a few are inconsistent or incomplete: brace placement changes between branches, one `switch` case omits a colon, and the function example omits a prototype context. Resolve such examples by choosing one consistent project convention and compiling the exact code. Do not copy malformed examples as production code.

For reliable C code, also agree on these practices:

- Declare functions through headers and include the header that owns each declaration. Keep declarations and definitions type-compatible.
- Use `const` when a function does not modify an input, and make pointer ownership and lifetime explicit in APIs.
- Check return values from I/O, allocation, parsing, and system calls. Report errors with enough context and release owned resources on every exit path.
- Keep array lengths and buffer capacities explicit. Validate lengths before indexing or copying; prefer bounded operations and clear ownership over implicit conventions.
- Avoid undefined behavior, data races, uninitialized reads, and unchecked integer overflow. Use compiler warnings and sanitizers during development, with stricter settings in CI where feasible.
- Keep functions focused and make invariants visible. Use comments to explain constraints and non-obvious intent rather than restating each statement.
- Test boundary conditions, failure paths, and resource cleanup in addition to successful examples.

## Review checklist

1. Does the code compile with the project compiler and warning settings?
2. Are braces, indentation, naming, and declarations consistent with the repository formatter and style guide?
3. Are pointer ownership, buffer sizes, return values, and error paths clear?
4. Are shared data and concurrent access safe?
5. Do tests cover boundary values, invalid inputs, and failures?

Related pages: [[Topics/Resources/cosmos/guides/coding_style/Coding Style Guide]], [[Topics/Resources/cosmos/code/languages/c/C Guide]], [[Topics/Resources/cosmos/code/languages/c/dynamic_memory_allocation/Dynamic Memory Allocation Guide]], and [[Topics/Testing and Quality]].
