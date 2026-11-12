# Java Basics — Repetition & Learning Journal

> Notes based on my `guessTheNumberGame.java` (M319 Repetition).

## 1. Class + object = the blueprint idea

- A **class** is the blueprint. An **object** is one concrete thing built from it.
- My program: one class `guessTheNumberGame` — `new guessTheNumberGame()` builds one object.

```java
public class guessTheNumberGame {
    private int toBeGuessed;   // field (attribute)
    ...
}
```

## 2. The parts of my class

| Part | In my code | Job |
|---|---|---|
| **Fields** | `toBeGuessed`, `guess`, `scan` | the object's data (state) |
| **Constructor** | `guessTheNumberGame()` | runs at `new` — sets the random number + Scanner, then starts the loop |
| **Methods** | `gameLoop()`, `userGuess()`, `isGuessCorrect()`, getters/setter | the object's behavior |
| **main** | `public static void main(String[] args)` | the entry point Java launches — creates the object |

## 3. Entry point

```java
public static void main(String[] args) {
    new guessTheNumberGame();   // constructor runs -> game starts
}
```

- `main` must live **inside a class** (my first attempt had it floating outside — doesn't compile).
- File name must match the class name: `guessTheNumberGame.java`.

## 4. Data types I used

| Type | Kind | Where |
|---|---|---|
| `int` | primitive | `toBeGuessed`, `guess` |
| `boolean` | primitive | `gameOver` flag |
| `Scanner` | reference (class) | reading input |
| `String` | reference | parameter `args`, return of `nextLine()` |

Primitives hold the value itself; reference types point to an object.

## 5. TUI — input & output

```java
this.scan = new Scanner(System.in);              // set up once
this.guess = Integer.parseInt(this.scan.nextLine());  // read line -> String -> int
System.out.println("Guess a number: ");          // output
```

- `Scanner(System.in)` = listen to keyboard input.
- `nextLine()` reads text; `Integer.parseInt(...)` converts text to an int.
- No validation needed per the exercise (input is assumed correct).

## 6. Loop + decision = the game flow

```java
boolean gameOver = false;
while (!gameOver) {          // loop
    this.userGuess();        // method call (abstraction)
    if (this.isGuessCorrect()) {   // decision
        gameOver = true;     // flag flips -> loop ends
    }
}
```

The `!` means "not" — loop runs as long as the game is **not** over.

## 7. Random number

```java
(int)(Math.random() * 101)   // 0..100
```

`Math.random()` gives 0.0 .. <1.0 (double) — multiply, then cast to int (the cast **cuts off**, it doesn't round).

## 8. `this`, visibility, getters/setters

- `this` = "this object here" — makes it clear whose field/method it is.
- `private` = only the class itself may touch it (encapsulation). Outside code goes through getters/setters.
- Getter returns a field; setter takes a parameter and stores it (`this.guess = newGuess;`).

## 9. Compile & run (terminal)

```bash
javac guessTheNumberGame.java   # .java -> .class (bytecode)
java guessTheNumberGame         # JVM runs the bytecode
```

`.class` files are machine output — regenerate anytime, don't commit them.

## 10. One-line summary

> A class holds fields + methods, `main` starts the program, `new` builds the object —
> then it's just loop + decision + method calls on `this`.
