# UML Basics — Exam Prep & Learning Journal

## 1. What is UML?

- **UML = Unified Modeling Language** — a standard way to *draw* software systems.
- Purpose: plan and communicate a design **before** coding, and document it after.
- It is a *language*: it has agreed symbols, so everyone reads the same meaning into a diagram.

## 2. The two families of diagrams

| Family | Shows | Examples |
|---|---|---|
| **Structural** (static) | What the system *is* — building blocks and their links | **Class diagram**, object diagram, component diagram |
| **Behavioral** (dynamic) | What the system *does* over time | Use case, activity, sequence, state diagram |

For M320 the important one is the **class diagram**: the static structure of classes.

## 3. Anatomy of a class box

A class box has three compartments:

```
+--------------------------+
|        ClassName         |   <- name (PascalCase)
+--------------------------+
| - toBeGuessed: int       |   <- attributes (name: type)
+--------------------------+
| + gameLoop(): void       |   <- methods (name(params): returnType)
+--------------------------+
```

**Visibility markers:**

| Symbol | Meaning | Example |
|---|---|---|
| `-` | private | `- guess: int` |
| `+` | public | `+ gameLoop(): void` |
| `#` | protected (rare in our exercises) | `# name: String` |

## 4. Relationships

| Relationship | Line in diagram | Meaning | Test question |
|---|---|---|---|
| **Association** | plain line | "knows about / uses" — both sides independent | Does A just *know* B, without owning it? |
| **Aggregation** | hollow diamond ◇ at the **whole** | HAS-A, weak: part **survives** the whole dying | Can the part exist without the whole? → yes |
| **Composition** | filled diamond ◆ at the **whole** | HAS-A, strong: part **dies with** the whole | Is the part created/deleted together with the whole? → yes |
| Generalization | hollow triangle at the parent | inheritance ("is-a") | not part of my two exercises |

**Rule of thumb:** the diamond always sits at the **whole** (the owner) side, never at the part side.

## 5. Multiplicities

Written at the **ends** of a relationship line — how many instances on *that* side relate to *one* instance on the other side.

| Notation | Meaning |
|---|---|
| `1` | exactly one |
| `0..1` | zero or one (optional) |
| `0..*` (or `*`) | zero or many |
| `1..*` | at least one, possibly many |
| `m..n` | a range, e.g. `2..5` |

Read it out loud to check: "one **Hotel** has **1..\*** **Zimmer**" — and the reverse direction "**each** Zimmer belongs to **exactly 1** Hotel".

## 6. My decision method in the exam

1. **Find the classes** — nouns in the text (Hotel, Zimmer, Mitarbeiter…). Verbs become relationship names (beschäftigt, besitzt).
2. **List attributes** — the properties mentioned per noun, with types (String, int, double, boolean, Date).
3. **Find the "whole"** for every has-a pair and ask the lifecycle question:
   - deleted together → **composition**
   - part can be moved away / survives → **aggregation**
   - just a reference, no ownership → **association**
4. **Add multiplicities** on both ends, from the text ("mehrere", "genau ein", "höchstens eine").
5. **Justify** each composition in one sentence — the task explicitly asks for reasons.

## 7. My example

**Online shop** (`docs/klassendiagramm-online-shop.drawio`)

| Relationship | Type | Multiplicity | Why |
|---|---|---|---|
| Kunde → Bestellung | association | 1 ↔ 0..* | customer independent, many orders over time |
| Bestellung → Bestellposition | composition ◆ | 1 ↔ 1..* | positions die with the order |
| Bestellposition → Produkt | association | 0..* ↔ 1 | product stays when order is deleted |
| Bestellung → Lieferadresse | composition ◆ | 1 ↔ 1 | stored copy, part of the order |
| Bestellung → Zahlung | composition ◆ | 1 ↔ 0..1 | optional now (0..1), but bound to the order if it exists |

Note how multiplicities encode the story: `0..1` Zahlung = "may not exist yet, at most one later";


## 8. One-line summary

> Class diagram = classes (name, attributes, methods) + relationships (association /
> aggregation ◇ / composition ◆) + multiplicities — and every diamond decision is a
