# Command line client, cht.sh

**Collection:** [[Topics/Collections/Command Line and Operations]]  
**Original section:** [Command Line and Operations source section](<../Resources/cheat.sh/Cheat.sh Guide.md>) — [jump to section](<../Resources/cheat.sh/Cheat.sh Guide.md#command-line-client-chtsh>)

## Related topics

- [[Topics/Collections/command-line-cheat-sheets-sources|Cheat Sheets Sources]]
- [[Topics/Collections/command-line-contents|Contents]]

## Source content

## Command line client, cht.sh

The cheat.sh service has its own command line client (`cht.sh`) that
has several useful features compared to querying the service directly with `curl`:

* Special shell mode with a persistent queries context and readline support.
* Queries history.
* Clipboard integration.
* Tab completion support for shells (bash, fish, zsh).
* Stealth mode.

### Installation

To install the client:

```bash
PATH_DIR="$HOME/bin"  # or another directory on your $PATH
mkdir -p "$PATH_DIR"
curl https://cht.sh/:cht.sh > "$PATH_DIR/cht.sh"
chmod +x "$PATH_DIR/cht.sh"
```

or to install it globally (for all users):

```bash
curl -s https://cht.sh/:cht.sh | sudo tee /usr/local/bin/cht.sh && sudo chmod +x /usr/local/bin/cht.sh
```

Note: The package "rlwrap" is a required dependency to run in shell mode. Install this using `sudo apt install rlwrap`

### Client usage

Now, you can use `cht.sh` instead of `curl`, and write your queries in more natural way,
with spaces instead of `+`:

```
    $ cht.sh go reverse a list
    $ cht.sh python random list elements
    $ cht.sh js parse json
```

It is even more convenient to start the client in a special shell mode:
```
    $ cht.sh --shell
    cht.sh> go reverse a list
```

If all your queries are about the same language, you can change the context
and spare repeating the programming language name:
```
    $ cht.sh --shell
    cht.sh> cd go
    cht.sh/go> reverse a list
```
or even start the client in this context:
```
    $ cht.sh --shell go
    cht.sh/go> reverse a list
    ...
    cht.sh/go> join a list
    ...
```

If you want to change the context, you can do it with the `cd` command,
or if you want do a single query for some other language, just prepend it with `/`:

```
    $ cht.sh --shell go
    ...
    cht.sh/go> /python dictionary comprehension
    ...
```

If you want to copy the last answer into the clipboard, you can
use the `c` (`copy`) command, or `C` (`ccopy`, without comments).

```
    cht.sh/python> append file
    #  python - How do you append to a file?

    with open("test.txt", "a") as myfile:
        myfile.write("appended text")
    cht.sh/python> C
    copy: 2 lines copied to the selection
```

Type `help` for other internal `cht.sh` commands.

```
	cht.sh> help
	help    - show this help
	hush    - do not show the 'help' string at start anymore
	cd LANG - change the language context
	copy    - copy the last answer in the clipboard (aliases: yank, y, c)
	ccopy   - copy the last answer w/o comments (cut comments; aliases: cc, Y, C)
	exit    - exit the cheat shell (aliases: quit, ^D)
	id [ID] - set/show an unique session id ("reset" to reset, "remove" to remove)
	stealth - stealth mode (automatic queries for selected text)
	update  - self update (only if the scriptfile is writeable)
	version - show current cht.sh version
	/:help  - service help
	QUERY   - space separated query staring (examples are below)
				  cht.sh> python zip list
				  cht.sh/python> zip list
				  cht.sh/go> /python zip list
```

The `cht.sh` client has its configuration file which is located at `~/.cht.sh/cht.sh.conf`
(location of the file can be overridden by the environment variable `CHTSH_CONF`).
Use it to specify query options that you would use with each query.
For example, to switch syntax highlighting off create the file with the following
content:

```bash
CHTSH_QUERY_OPTIONS="T"
```

Or if you want to use a special syntax highlighting theme:

```bash
CHTSH_QUERY_OPTIONS="style=native"
```

(`curl cht.sh/:styles-demo` to see all supported styles).

Other cht.sh configuration parameters:

```bash
CHTSH_CURL_OPTIONS="-A curl"        # curl options used for cht.sh queries
CHTSH_URL=https://cht.sh            # URL of the cheat.sh server
```

### Tab completion


#### Bash Tab completion

To activate tab completion support for `cht.sh`, add the `:bash_completion` script to your `~/.bashrc`:

```bash
    curl https://cheat.sh/:bash_completion > ~/.bash.d/cht.sh
    . ~/.bash.d/cht.sh
    # and add . ~/.bash.d/cht.sh to ~/.bashrc
```

#### ZSH Tab completion

To activate tab completion support for `cht.sh`, add the `:zsh` script to the *fpath* in your `~/.zshrc`:

```zsh
    curl https://cheat.sh/:zsh > ~/.zsh.d/_cht
    echo 'fpath=(~/.zsh.d/ $fpath)' >> ~/.zshrc
    # Open a new shell to load the plugin
```

----

### Stealth mode

Being used fully unnoticed is one of the most important property of any cheat sheet.

cheat.sh can be used completely unnoticed too. The cheat.sh client, `cht.sh`, has
a special mode, called **stealth mode**. Using that, you don't even need to touch your
keyboard to open a cheat sheet.

In this mode, as soon as you select some text with the mouse (and thus adding it
into the selection buffer of X Window System or into the clipboard) it's used
as a query string for cheat.sh, and the correspondent cheat sheet is automatically shown.

Let's imagine, that you are having an online interview, where your interviewer asks you
some questions using a shared document (say Google Docs) and you are supposed
to write your coding answers there (it's possible too that you'll type in the questions
on your own, just to show to the interviewer that you've heard it right).

When using the stealth mode of `cht.sh`, the only thing you need to do in order to see
a cheat sheet for some question, is to select the question using the mouse.
If you don't want any text in the answers and the only thing you need is code,
use the `Q` option when starting the stealth mode.

<p align="center">
  <img src='https://cheat.sh/files/stealth-mode.gif'/>
</p>

```
You: Hi!                                            | $ cht.sh --shell python
She: Hi!                                            | cht.sh/python> stealth Q
She: Are you ready for a small interview?           | stealth: you are in the stealth mode; select any text
She: Just a couple of questions                     | stealth: selections longer than 5 words are ignored
She: We will talk about python                      | stealth: query arguments: ?Q
She: Let's start from something simple.             | stealth: use ^C to leave this mode
She: Do you know how to reverse a list in python?   |
You: Sure                                           |
You: (selecting "reverse a list")                   | stealth: reverse a list
                                                    | reverse_lst = lst[::-1]
You: lst[::-1]?                                     |
She: Good.                                          |
She: Do you know how to chain a list of lists?      |
You: (selecting "chain a list of lists")            | stealth: chain a list of lists
                                                    | import itertools
                                                    | a = [["a","b"], ["c"]]
                                                    | print list(itertools.chain.from_iterable(a))
You: May I use external modules?                    |
She: What module do you want to use?                |
You: itertools                                      |
She: Yes, you may use it                            |
You: Ok, then:                                      |
You: itertools.chain.from_iterable(a)               |
She: Good. Let's try something harder.              |
She: What about quicksort implementation?           |
You: (selecting "quicksort implementation")         | stealth: quicksort implementation
You: Let me think about it.                         | (some big and clumsy lowlevel implementation shown)
You: Well...(starting typing it in)                 | def sort(array=[12,4,5,6,7,3,1,15]):
                                                    |     less = []
She: (seeing your ugly pascal style)                |     equal = []
She: Could you write it more concise?               |     greater = []
                                                    |     if len(array) > 1:
You: What do you mean?                              |         pivot = array[0]
                                                    |         for x in array:
She: I mean,                                        |             if x < pivot: less.append(x)
She: do you really need all these ifs and fors?     |             if x == pivot: equal.append(x)
She: Could you maybe just use filter instead?       |             if x > pivot: greater.append(x)
                                                    |         return sort(less)+equal+sort(greater)
You: quicksort with filter?                         |     else:
                                                    |         return array
She: Yes                                            |
You: (selecting "quicksort with filter")            | stealth: quicksort with filter
You: Ok, I will try.                                | return qsort(filter(lt, L[1:]))+[pivot] \
You: Something like this?                           |     +qsort(filter(ge, L[1:]))
You: qsort(filter(lt, L[1:]))+[pivot] \             |
       + qsort(filter(ge, L[1:]))                   |
                                                    |
She: Yes! Perfect! Exactly what I wanted to see!    |
                                                    |

```

Of course, this is just for fun, and you should never cheat in your coding interviews,
because you know what happens when you do.

![when you lie in your interview](http://cheat.sh/files/when-you-lie-katze.png)

### Windows command line client

You can access cheat.sh from Windows command line too.

Use cheat.sh command line client for that: [`cht.exe`](https://github.com/tpanj/cht.exe).
It supports:

* output colorization;
* command line options;
* its own configuration file.

You can also use [`scoop`](https://github.com/lukesampson/scoop) command-line installer for Windows to get it:
```batch
scoop install cht
```

----
