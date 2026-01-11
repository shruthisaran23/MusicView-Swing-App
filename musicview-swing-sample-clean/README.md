# MusicView Swing Component (sample)

This repo is a **small, self-contained Java Swing GUI sample** from earlier coursework. I'm sharing it as a quick code sample (kept intentionally small/unchanged) to show how I structure UI state, event handling, and custom drawing.

## What it does
- Renders a music staff UI and musical symbols (notes/rests)
- Supports basic interaction (placing/moving symbols; some selection behavior)

## How to run (JDK 17)
From the repo root:

```bash
javac -cp src -d out $(find src -name "*.java")
java -cp out HelloWorldSwing
```

> Note: This is older academic code; if I were productionizing it today I'd modularize the UI/event model, add tests, and remove global/static state.

## Code pointers
- `src/HelloWorldSwing.java` — main UI + event handling
- `src/MusicViewer.java` — custom component drawing
- `src/Symbol.java` / `src/Staff.java` — model objects
