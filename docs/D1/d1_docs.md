# Garage Simulation — Exercise Doc (D1)

## 1. The exercise in one line

Vehicles can be registered for repair, repair costs get listed, and the user can query which vehicles are already repaired and what the costs were.

## 2. Class structure

| Class | Job | Fields |
|---|---|---|
| `garageSim` | application shell + TUI | its `garage`, one `Scanner` |
| `garage` | manages the vehicle list | `ArrayList<car>` |
| `car` | one vehicle + its repair state | `String model`, `Status status`, `float repairCost` |

Ownership: garageSim **holds** a garage, the garage **holds** cars — the HAS-A chains from the UML journal, in code.

## 3. State = one enum dial

Every car carries its state in a single `enum` nested inside `car`:

```java
public enum Status {
    NOT_STARTED,
    IN_REPAIR,
    DONE
}
```

Why not three booleans (my first attempt): three flags allow 8 combinations, only 3 make sense, and every transition means flipping two flags in order — a "half in two states" bug waiting to happen. The enum is the three-position dial: exactly the legal states, one assignment per transition. Java's version of a TS union type.

## 4. Data types used

| Type | Where |
|---|---|
| `int` | menu choice, status constants |
| `float` | repair costs (decimals), running total |
| `boolean` | game-over-style `running` flag |
| `String` | car model, Scanner input |
| `ArrayList<car>` | the dynamic vehicle list |
| `Status` (enum) | repair state |

`ArrayList<String>`-style fixed arrays would have forced a size decision + manual counter; the list grows as cars get registered.

## 5. Program flow

```
main -> new garageSim()
garageSim constructor: Scanner ready -> menuLoop()

menuLoop (while running):
  print options -> read choice -> if/else if
  1: read model -> garage.registerCar() -> car added to list (status NOT_STARTED, random cost)
  2: read model -> find car (equalsIgnoreCase) -> set cost + status DONE
  3: for-each over list, filter DONE, print model + cost, sum -> total
  4: running = false -> loop ends
```

## 6. Patterns worth keeping

- **Menu loop** = the guessing-game loop, rebadged: `boolean running` + `while (running)` + `if/else if` on the choice.
- **Construction = configuration**: `main` only builds the shell; the constructor wires Scanner and starts the program. Same as guessTheNumberGame.
- **Layers**: shell (dialogue) -> manager (list, register) -> data (car). The shell never touches the list directly; it goes through the garage. That's why `garage` can swap its internals without the menu noticing.
- **Finding in a list**: for-each + an `if` on identity, `return` out with the first hit. The base pattern for "search".
- **Aggregation in practice**: `registerCar` = `new car(model)` + `add` — the garage holds objects built elsewhere.

## 7. One-line summary

> Shell asks, garage stores, car knows its state — menu loop on top, enum dial inside,
> for-each to find and to list.
