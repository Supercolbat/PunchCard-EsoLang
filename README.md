# PunchCard Esoteric Language
An esoteric programming language that will be used in a punch card reader machine.

I programmed this in [repl.it](https://repl.it) but if you want to use this on your machine, just copy `punchcard.java`
If you are viewing this on repl.it, please note that this README file doesn't load correctly.

## How it works

Head over to the [wiki](https://github.com/Supercolbat/PunchCard-EsoLang/wiki) to find out!


| Command     | What it does           |
| :-: | --- |
|<pre>`.  . . .`</pre>| push next line to cell in stack|
|<pre>` .. . . `</pre>| pop cell |
|<pre>`..      `</pre>| go back one cell |
|<pre>`      ..`</pre>| go forward one cell |
|<pre>` .      `</pre>| ascii to chr |
|<pre>`  .     `</pre>| ascii to dec |
|<pre>`. .     `</pre>| sum next line to cell |
|<pre>` . .    `</pre>| sub next line to cell |
|<pre>`.       `</pre>| output data in cell |
|<pre>`.  .. ..`</pre>| merge all cells after pointer to pointer (inclusive)|
|<pre>`.. ..  .`</pre>| merge all cells before pointer to pointer (inclusive)|
|<pre>`.  ..  .`</pre>| merge all cells to pointer|
|<pre>`.    . .`</pre>| for loop |
|<pre>`;comment ...`</pre>| comment |

## Examples
### Add 1 and 2
    .  . . .   ;push
           .   ;1
          ..   ;move 1 cell right
    . .        ;add
          .    ;2
    .          ;output
### Output "ABC"
    .  . . .   ;push
         ..    ;6
          ..   ;move 1 cell right
    .  . . .   ;push
         ...   ;7
    ..         ;move 1 cell left
    .. .. ..   ;merge all cells to pointer
          ..   ;move 1 cell right
    .  . . .   ;push
         . .   ;6
          ..   ;move 1 cell right
    .  . . .   ;push
         ..    ;6
    ..         ;move 1 cell left
    .. .. ..   ;merge all cells to pointer
          ..   ;move 1 cell right
    .  . . .   ;push
         ..    ;6
    .  . . .   ;push
         . .   ;5
    ..         ;move 1 cell left
    .. .. ..   ;merge all cells to pointer
     .         ;ascii to char
    .          ;output A
    ..         ;move 1 cell left
     .         ;ascii to char
    .          ;output B
    ..         ;move 1 cell left
     .         ;ascii to char
    .          ;output C
This will be simplified and shortened by a *lot* when loops are added.
[![Run on Repl.it](https://repl.it/badge/github/Supercolbat/PunchCard-PL)](https://repl.it/github/Supercolbat/PunchCard-PL)
