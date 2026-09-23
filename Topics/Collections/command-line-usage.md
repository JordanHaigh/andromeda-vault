# Usage

**Collection:** [[Topics/Collections/Command Line and Operations]]  
**Original section:** [Command Line and Operations source section](<../Resources/cheat.sh/Cheat.sh Guide.md>) — [jump to section](<../Resources/cheat.sh/Cheat.sh Guide.md#usage>)

## Related topics

- [[Topics/Collections/command-line-special-pages|Special Pages]]

## Source content

## Usage

To get a cheat sheet for a UNIX/Linux command from a command line, query the service using `curl` or any other HTTP/HTTPS client
specifying the name of the command in the query:

```
    curl cheat.sh/tar
    curl cht.sh/curl
    curl https://cheat.sh/rsync
    curl https://cht.sh/tr
```
As you can see, you can use both HTTPS and HTTP to access the service, and both the long (cheat.sh) and the short (cht.sh) service names.

Here `tar`, `curl`, `rsync`, and `tr` are names of the UNIX/Linux commands you want to get cheat sheets for.

If you don't know the name of the command you need, you can search for it using the `~KEYWORD` notation.
For example, to see how you can make `snapshots` of a filesystem/volume/something else:
```
    curl cht.sh/~snapshot
```

<p align="center">
  <img src='https://cheat.sh/files/cht.sh-url-structure.png'/>
</p>

The programming language cheat sheets are located in special namespaces dedicated to them.

```
    curl cht.sh/go/Pointers
    curl cht.sh/scala/Functions
    curl cht.sh/python/lambda
```

To get the list of available programming language cheat sheets, use the special query `:list`:

```
    curl cht.sh/go/:list
```

Almost each programming language has a special page named `:learn`
that describes the language basics (that's a direct mapping from the *"Learn X in Y"* project).
It could be a good starting point if you've just started learning a language.

If there is no cheat sheet for a programming language query (and it is almost always the case),
it is generated on the fly, based on available cheat sheets and answers on StackOverflow.
Of course, there is no guarantee that the returned cheat sheet will be a 100% hit, but it is almost always exactly what you are looking for.

Try these (and your own) queries to get the impression of that, what the answers look like:
```
    curl cht.sh/go/reverse+a+list
    curl cht.sh/python/random+list+elements
    curl cht.sh/js/parse+json
    curl cht.sh/lua/merge+tables
    curl cht.sh/clojure/variadic+function
```

If you don't like an answer for your queries, you can pick another one. For that, repeat the query with an additional parameter `/1`, `/2` etc. appended:

```
    curl cht.sh/python/random+string
    curl cht.sh/python/random+string/1
    curl cht.sh/python/random+string/2
```

Cheat sheets are formatted as code of the queried programming language (at least we are trying our best to do so)
so they can be pasted into a program in this language directly. Text comments, if there are any, are formatted according to the language syntax.

```lua
    $ curl cht.sh/lua/table+keys
    -- lua: retrieve list of keys in a table

    local keyset={}
    local n=0

    for k,v in pairs(tab) do
      n=n+1
      keyset[n]=k
    end

    --[[
       [ Note that you cannot guarantee any order in keyset. If you want the
       [ keys in sorted order, then sort keyset with table.sort(keyset).
       [ 
       [ [lhf] [so/q/12674345] [cc by-sa 3.0]
       ]]

```

If you don't need text comments in the answer, you can eliminate them
using a special option `\?Q`:
```lua
    $ curl cht.sh/lua/table+keys\?Q
    local keyset={}
    local n=0

    for k,v in pairs(tab) do
      n=n+1
      keyset[n]=k
    end
```

And if you don't need syntax highlighting, switch it off using `\?T`.
You can combine the options together:

```
    curl cht.sh/go/reverse+a+list\?Q
    curl cht.sh/python/random+list+elements\?Q
    curl cht.sh/js/parse+json\?Q
    curl cht.sh/lua/merge+tables\?QT
    curl cht.sh/clojure/variadic+function\?QT
```

Full list of all options described below and in `/:help`.

Try your own queries. Follow these rules:

1. Try to be more specific (`/python/append+file` is better than `/python/file` and `/python/append`).
2. Ask practical question if possible (yet theoretical question are possible too).
3. Ask programming language questions only; specify the name of the programming language as the section name.
4. Separate words with `+` instead of spaces.
5. Do not use special characters, they are ignored anyway.
6. If you want to eliminate cheat sheets containing some word, add it to the query with `+-`: `python/multiply+matrices+-numpy`

Read more about the programming languages queries below.

----
