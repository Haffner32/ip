Arvee 🤖



Arvee is a simple personal task manager chatbot built in Java.  

It helps you keep track of todos, deadlines, and events, directly from the command line or a graphical user interface (GUI) built with JavaFX.



---



&nbsp;Features



\- \*\*Task Management\*\*

&nbsp; - `todo`: Add a todo task

&nbsp; - `deadline`: Add a deadline with a due date/time

&nbsp; - `event`: Add an event with start/end times

&nbsp; - `list`: View all tasks in the list

&nbsp; - `mark` / `unmark`: Mark tasks as done or not done

&nbsp; - `delete`: Remove tasks

&nbsp; - `find`: Search tasks by keyword



\- \*\*Persistent Storage\*\*

&nbsp; - Tasks are automatically saved to `./data/duke.txt`

&nbsp; - Saved tasks are reloaded the next time you start Arvee



\- \*\*Date \& Time Support\*\*

&nbsp; - Deadlines and events accept dates in `yyyy-MM-dd` or `yyyy-MM-dd HHmm` format

&nbsp; - Displayed in a human-friendly format, e.g. `Oct 15 2019`



\- \*\*GUI (JavaFX)\*\*

&nbsp; - Interactive chatbot interface

&nbsp; - Messages appear as chat bubbles (user on right, Arvee on left)

&nbsp; - Auto-scrolling and input disabled on exit command (`bye`)



---



\##  Getting Started



\### Prerequisites

\- Java \*\*17\*\* or above

\- Gradle (wrapper included, no need to install separately)



\### Build \& Run (CLI)

```bash

./gradlew run



