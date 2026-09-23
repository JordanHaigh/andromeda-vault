# CSS Basics: Margins

## Table of contents:

[1. Setting individual side margin](https://github.com/OpenGenus/cosmos/blob/master/code/html/css/Margin/src/Individual.html)

[2. Shorthand property for CSS Margins](https://github.com/OpenGenus/cosmos/blob/master/code/html/css/Margin/src/Shorthand_4.html)

[3. Auto-Keyword](https://github.com/OpenGenus/cosmos/blob/master/code/html/css/Margin/src/Auto_keyword.html)

[4. Margin Inherit](https://github.com/OpenGenus/cosmos/blob/master/code/html/css/Margin/src/Inherit_keyword.html)

[5. Margin Collapse](https://github.com/OpenGenus/cosmos/blob/master/code/html/css/Margin/src/Margin_collapse.html)

CSS Margin property is used to create space between webpage border and HTML Element's border (if defined using CSS Border property).

**Syntax:**
```html
<style>
    #idvalue
    {
        border: {width} {style} {color};
        margin: {value}
    }
</style>
<{html_element} id="idvalue"> {content} </{html_element}>
```

**Example:**
```html
<style>
    #margin1 
    {
    border: 2px solid black;
    text-align:center;
    margin: 100px;
    }
</style>
<h1 id="margin1"> Generic Heading with defined margin</h1>
<h1> Normal heading </h1>
```
The Margin property can be defined in following ways:

1. Length
2. Percentage
3. Auto 
4. Inherit
