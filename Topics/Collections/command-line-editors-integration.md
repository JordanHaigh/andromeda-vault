# Editors integration

**Collection:** [[Topics/Collections/Command Line and Operations]]  
**Original section:** [Command Line and Operations source section](<../Resources/cheat.sh/Cheat.sh Guide.md>) — [jump to section](<../Resources/cheat.sh/Cheat.sh Guide.md#editors-integration>)

## Related topics

- [[Topics/Collections/command-line-contents|Contents]]
- [[Topics/Collections/command-line-features|Features]]

## Source content

## Editors integration

You can use *cheat.sh* directly from the editor
(*Emacs*, *Sublime*, *Vim*, and *Visual Studio Code* are currently supported;
not all features are supported by all plugins though; see below).
Instead of opening your browser, googling, browsing Stack Overflow
and eventually copying the code snippets you need into the clipboard
and later pasting them into the editor,
you can achieve the same instantly and without leaving the editor at all!

Here is what it looks like in Vim:

1. If you have a question while editing a program, you can just type
your question directly in the buffer and press `<leader>KK`. You will get
the answer to your question in pager. (with `<leader>KB` you'll get the answer
in a separate buffer).

2. If you like the answer, you can manually paste it from the buffer or
the pager, or if you are lazy you can use `<leader>KP` to paste it below/under
your question (or replace you question using `<leader>KR`). If you want the
answer without the comments, `<leader>KC` replays the last query
toggling them.

If you use some static analysis plugin such as *syntastic* (for Vim), you can use
its warning and error messages as cheat.sh queries: place the cursor on the problem line
and press `<leader>KE`: explanation for the warning will be opened in a new buffer.

Features supported by cheat.sh plugins for different editors:

|Feature            |Emacs|Sublime|Vim|VSCode|IDEA|QtCreator|
|-------------------|-----|-------|---|------|----|---------|
|Command queries    |✓    |✓      |✓  |✓     |✓   |✓        |
|Queries from buffer|     |       |✓  |✓     |    |✓        |
|Toggle comments    |     |       |✓  |✓     |✓   |✓        |
|Prev/next answer   |     |       |✓  |✓     |✓   |✓        |
|Multiple answers   |     |✓      |   |      |✓   |         |
|Warnings as queries|     |       |✓  |      |    |         |
|Queries history    |     |       |✓  |✓     |    |         |
|Session id         |     |       |✓  |      |    |         |
|Configurable server|✓    |       |✓  |✓     |    |✓        |

### Vim

* [cheat.sh-vim](https://github.com/dbeniamine/cheat.sh-vim) — Vim support

Here is Vim configuration example:

```vim
" some configuration above ...

let mapleader=" "

call vundle#begin()
Bundle 'gmarik/vundle'
Bundle 'scrooloose/syntastic'
Bundle 'dbeniamine/cheat.sh-vim'
call vundle#end()

let g:syntastic_javascript_checkers = [ 'jshint' ]
let g:syntastic_ocaml_checkers = ['merlin']
let g:syntastic_python_checkers = ['pylint']
let g:syntastic_shell_checkers = ['shellcheck']

" some configuration below ...
```

In this example, several Vim plugins are used:

* [gmarik/vundle](https://github.com/VundleVim/Vundle.vim) — Vim plugin manager
* [scrooloose/syntastic](https://github.com/vim-syntastic/syntastic) — Syntax checking plugin
* [cheat.sh-vim](https://github.com/dbeniamine/cheat.sh-vim) — Vim support

Syntastic shows warnings and errors (found by code analysis tools: `jshint`, `merlin`, `pylint`, `shellcheck` etc.),
and `cheat.sh-vim` shows you explanations for the errors and warnings
and answers on programming languages queries written in the editor.

Watch a demo, where the most important features of the cheat.sh Vim plugin are shown (5 Min):

<p align="center">
  <img src='https://cheat.sh/files/vim-demo.gif'/>
</p>

Or, if you want to scroll and/or pause, the same on YouTube:

<p align="center">
  <a href="http://www.youtube.com/watch?feature=player_embedded&v=xyf6MJ0y-z8
  " target="_blank"><img src="http://img.youtube.com/vi/xyf6MJ0y-z8/0.jpg" 
  alt="cheat.sh-vim: Using cheat.sh from vim" width="700" height="490" border="10" /></a>
</p>

<!-- [![asciicast](https://asciinema.org/a/c6QRIhus7np2OOQzmQ2RNXzRZ.png)](https://asciinema.org/a/c6QRIhus7np2OOQzmQ2RNXzRZ) -->

### Emacs

* [cheat-sh.el](https://github.com/davep/cheat-sh.el) — Emacs support (available also at cheat.sh/:emacs)
* cheat.sh/:emacs-ivy — Emacs support for ivy users

[![asciicast](https://asciinema.org/a/3xvqwrsu9g4taj5w526sb2t35.png)](https://asciinema.org/a/3xvqwrsu9g4taj5w526sb2t35)


### Visual Studio Code

* [vscode-snippet](https://github.com/mre/vscode-snippet)
* Install it from [VSCode Marketplace](https://marketplace.visualstudio.com/items?itemName=vscode-snippet.Snippet)

Usage: 

1. Hit <kbd>⌘ Command</kbd> + <kbd>⇧ Shift</kbd> + <kbd>p</kbd>
2. Run `Snippet: Find`.
3. Type your query and hit enter.

[![vscode-snippet](https://cheat.sh/files/vscode-snippet-demo.gif)](https://github.com/mre/vscode-snippet)

*(GIF courtesy: Matthias Endler, @mre)*

### Sublime

* [cheat.sh-sublime-plugin](https://github.com/gauravk-in/cheat.sh-sublime-plugin/)

Usage:

1.  Write your query string.
2.  Select the query string.
3.  Press <kbd>Cmd</kbd> + <kbd>⇧ Shift</kbd> + <kbd>B</kbd> to replace the selected query string by the answer generated from `cht.sh`.

[![cheat.sh-sublime-plugin-demo](https://cheat.sh/files/demo-sublime.gif)](https://github.com/gauravk-in/cheat.sh-sublime-plugin)

*(GIF courtesy: Gaurav Kukreja, @gauravk-in)*

### IntelliJ IDEA 

* [idea-cheatsh-plugin](https://github.com/szymonprz/idea-cheatsh-plugin)
* Install from [idea plugins marketplace](https://plugins.jetbrains.com/plugin/11942-cheat-sh-code-snippets) 

Usage: 

1. Write query string
2. Select the query string
3. Press keyboard shortcut <kbd>Alt</kbd> + <kbd>C</kbd> , <kbd>S</kbd> to replace the selected query string by the answer

[![idea-cheatsh-plugin](https://cheat.sh/files/idea-demo.gif)](https://github.com/szymonprz/idea-cheatsh-plugin)

*(GIF courtesy: Szymon Przebierowski, @szymonprz)*

### QtCreator

* [cheatsh-qtcreator](https://github.com/pozemka/cheatsh-qtcreator)

Current features:

*    search word under cursor
*    search selected
*    query search
*    disable comments
*    paste answer (?TQ version)
*    custom server URL
*    custom search context (default is cpp)
*    hotkeys and menu

[![cheatsh-qtcreator](https://user-images.githubusercontent.com/1259724/73876361-ecce5d00-4867-11ea-9f75-c5b127a9739c.gif)](https://github.com/pozemka/cheatsh-qtcreator)

*(GIF courtesy: Pozemka, @pozemka)*
