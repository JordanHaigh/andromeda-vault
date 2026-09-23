# Search

**Collection:** [[Topics/Collections/Command Line and Operations]]  
**Original section:** [Command Line and Operations source section](<../Resources/cheat.sh/Cheat.sh Guide.md>) — [jump to section](<../Resources/cheat.sh/Cheat.sh Guide.md#search>)

## Related topics

- [[Topics/Collections/command-line-programming-languages-cheat-sheets|Programming Languages Cheat Sheets]]
- [[Topics/Collections/command-line-self-hosting|Self Hosting]]

## Source content

## Search

To search for a keyword, use the query:

```
    /~keyword
```

In this case search is not recursive — it is conducted only in a page of the specified level.
For example:

```
    /~snapshot          look for snapshot in the first level cheat sheets
    /scala/~currying     look for currying in scala cheat sheets
```

For a recursive search in all cheat sheets, use double slash:

```
    /~snapshot/r         look for snapshot in all cheat sheets
```

You can use special search options after the closing slash:

```
    /~shot/bi           case insensitive (i), word boundaries (b)
```

List of search options:

```
    i   case insensitive search
    b   word boundaries
    r   recursive search
```
