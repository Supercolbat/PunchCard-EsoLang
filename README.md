# PunchCard Esoteric Language
An esoteric programming language that will be used in a punch card reader machine.

I programmed this in [repl.it](https://repl.it) but if you want to use this on your machine, just copy `punchcard.java`

## How it works
There are only 8 "data spots" in each line.
---

| Command     | What it does           |
| :-: | --- |
|.&nbsp;&nbsp;.&nbsp;.&nbsp;.| push next line to cell in stack|
|&nbsp;&nbsp;..&nbsp;.&nbsp;.&nbsp;| pop cell |
|&nbsp;.&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;| ascii to chr |
|&nbsp;&nbsp;.&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;| ascii to dec           |
|.&nbsp;.&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;| sum next line to cell |
|&nbsp;.&nbsp;.&nbsp;&nbsp;&nbsp;&nbsp;| sub next line to cell |
|.&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;| output data in cell |
|..&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;| go back one cell |
|&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;..| go forward one cell |
|.&nbsp;&nbsp;..&nbsp;..| merge all cells after pointer to pointer|
|..&nbsp;..&nbsp;&nbsp;.| merge all cells before pointer to pointer|
|..&nbsp;..&nbsp;..| merge all cells to pointer|

[![Run on Repl.it](https://repl.it/badge/github/Supercolbat/PunchCard-PL)](https://repl.it/github/Supercolbat/PunchCard-PL)