# PunchCard Esoteric Language
An esoteric programming language that will be used in a punch card reader machine.

I programmed this in [repl.it](https://repl.it) but if you want to use this on your machine, just copy `punchcard.java`

## How it works

Head over to the [wiki](https://github.com/Supercolbat/PunchCard-EsoLang/wiki) to find out!


| Command     | What it does           |
| :-: | --- |
|.&nbsp;&nbsp;.&nbsp;.&nbsp;.| push next line to cell in stack|
|&nbsp;&nbsp;..&nbsp;.&nbsp;.&nbsp;| pop cell |
|..&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;| go back one cell |
|&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;..| go forward one cell |
|.&nbsp;.&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;| sum next line to cell |
|&nbsp;.&nbsp;.&nbsp;&nbsp;&nbsp;&nbsp;| sub next line to cell |
|.&nbsp;&nbsp;..&nbsp;..| merge all cells after pointer to pointer (inclusive)|
|..&nbsp;..&nbsp;&nbsp;.| merge all cells before pointer to pointer (inclusive)|
|..&nbsp;..&nbsp;..| merge all cells to pointer|
|&nbsp;.&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;| ascii to chr |
|&nbsp;&nbsp;.&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;| ascii to dec |
|.&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;| output data in cell |
|| for loop|

## Examples
&nbsp;
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
