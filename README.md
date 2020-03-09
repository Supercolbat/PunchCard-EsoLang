# PunchCard Esoteric Language
An esoteric programming language.

I programmed this in [repl.it](https://repl.it) but if you want to use this on your machine, just copy `punchcard.java`

[![Run on Repl.it](https://repl.it/badge/github/Supercolbat/PunchCard-PL)](https://repl.it/github/Supercolbat/PunchCard-PL)

## How it works
There are only 8 "data spots" in each line.
---

| Command     | What it does           |
| --- | --- |
|&nbsp;.&nbsp;&nbsp;&nbsp;.&nbsp;&nbsp;.&nbsp;&nbsp;.&nbsp;| push next line to cell |
|&nbsp;&nbsp;&nbsp;..&nbsp;&nbsp;.&nbsp;&nbsp;.&nbsp;&nbsp;| pop cell               |
|&nbsp;.&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;| ascii to chr           |
|&nbsp;&nbsp;&nbsp;.&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;| ascii to dec           |
|&nbsp;.&nbsp;&nbsp;.&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;| add next line to cell  |
|&nbsp;&nbsp;&nbsp;.&nbsp;&nbsp;.&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;| sub next line to cell  |
|&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;.&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;| output data in cell    |
|&nbsp;..&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;| go back one cell       |
|&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;..&nbsp;&nbsp;&nbsp;&nbsp;| go forward one cell    |