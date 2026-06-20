# JavaFX Multi-Window Web Browser

**Author:** Sayali Lohokare
**Type:** Personal project

## What this project is about

A multi-window web browser built using JavaFX. The application can open and manage several
independent browser windows at once, each with its own navigation controls, status display, and
window menu. It was built to deepen practical understanding of JavaFX's `WebView`/`WebEngine`
components, event-driven UI updates, and managing multiple application windows within a single
running program.

## What's included here

- `Sayali_BrowserWindow.java` — defines a single browser window: the embedded `WebView`/`WebEngine`
  used to load pages, the URL input bar with Load/Cancel controls, a status/location display that
  updates based on page-load state, and a "Window" menu for opening new windows or switching between
  currently open ones.
- `Sayali_WebBrowser.java` — the application entry point. Manages the list of currently open windows,
  positions new windows on screen, and ends the program once all windows are closed.
- `SimpleDialogs.java` — a small utility class providing reusable dialog boxes (message, prompt,
  yes/no/cancel confirmation, and a color chooser) used by the browser for JavaScript `alert()`,
  `prompt()`, and `confirm()` calls from loaded web pages.

## Key features

- **Multi-window browsing** — open and switch between several independent browser windows via the
  Window menu, which dynamically lists all currently open windows.
- **Live status updates** — the status label reflects the page-load state (idle, loading, succeeded,
  failed, cancelled) by listening to the `WebEngine`'s load worker state.
- **Cancellable page loads** — the Cancel button is enabled only while a page is actively loading,
  and cancels the in-progress load via the `WebEngine`'s `Worker`.
- **JavaScript dialog handling** — page-triggered `alert()`, `prompt()`, and `confirm()` calls are
  routed through `SimpleDialogs` so they appear as native JavaFX dialogs rather than being silently
  ignored.
- **Dynamic window menu** — the Window menu rebuilds itself each time it's opened, listing all open
  windows by title and disabling the entry for the currently focused window.

## Tools and technologies

Java, JavaFX (`WebView`, `WebEngine`, `Stage`, `Scene`, `MenuBar`/`Menu`/`MenuItem`, `Dialog`,
`TextInputDialog`), event-driven programming, property listeners.

## How to run

1. Set up a JDK with JavaFX available (JavaFX is a separate module from JDK 11 onwards — you'll need
   the JavaFX SDK on your module path, or use a JDK distribution that bundles it).
2. Open the project in an IDE with JavaFX support (e.g. IntelliJ IDEA, Eclipse with e(fx)clipse).
3. Run `Sayali_WebBrowser.java` as the main class.
4. Use the URL bar to load a page, or the Window menu to open additional browser windows.

## Note

This was a personal project built to practise JavaFX application structure, multi-window management,
and event-driven UI design.
