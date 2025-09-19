# Arvee Chatbot 🤖

Arvee is a personal task management chatbot. It helps you keep track of your todos, deadlines, and events — all from a simple chat-style interface.  

---

## ✨ Features

- **Add tasks**:  
  - `todo read book`  
  - `deadline return book /by 2025-10-15`  
  - `event project meeting /from 2025-10-10 1400 /to 2025-10-10 1600`  

- **List tasks**:  
  ```
  list
  ```

- **Mark and unmark tasks**:  
  ```
  mark 2
  unmark 3
  ```

- **Delete tasks**:  
  ```
  delete 1
  ```

- **Find tasks by keyword**:  
  ```
  find book
  ```

- **Sort tasks** chronologically (by deadline or start time):  
  ```
  sort asc
  sort desc
  ```

- **Save and load tasks** automatically between sessions.  

- **Graphical User Interface (GUI)** built with JavaFX.  

---


## 📸 Screenshots

Here’s a preview of Arvee’s GUI:  

![Screenshot of Arvee chatbot](./docs/Ui.png)  

---

## 🚀 Getting Started

### Prerequisites
- [Java 17](https://adoptium.net/) or later  
- [Gradle](https://gradle.org/) (wrapper included)

### Running the App
```bash
./gradlew run
```

---

## 🧪 Running Tests
JUnit tests are included. To run them:  
```bash
./gradlew test
```

---

## 🏗️ Project Structure
```
src
 ├─ main
 │   ├─ java
 │   │   ├─ arvee
 │   │   │   ├─ core
 │   │   │   ├─ logic
 │   │   │   ├─ model
 │   │   │   ├─ storage
 │   │   │   └─ ui
 │   └─ resources
 │       └─ images
 └─ test
     └─ java
```

---

## 📝 Roadmap

- [x] Add GUI  
- [x] Add ability to sort tasks  
- [ ] Enhance error handling with more descriptive feedback  
- [ ] Add recurring tasks support  

---

## 🤝 Contributing
Pull requests are welcome. For major changes, please open an issue first to discuss what you would like to change.  

---

