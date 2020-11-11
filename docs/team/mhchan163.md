# Chan Meng Han - Project Portfolio Page

## Overview

PlaNUS is a desktop application for NUS students to manage their tasks and schedule.
With this system, you can add your upcoming tasks into the list, and then display
them in a table view or in a calendar view, so that you may easily see and plan your
schedule ahead. This application is optimized for use via Command Line Interface (CLI),
this means that you operate the application by typing commands into the command box.

### Summary of Contributions

Given below are my contributions to the project.

* **New Feature**: Added the ability to add reminders to a task. (Pull Requests [\#71](https://github.com/AY2021S1-CS2113T-W12-1/tp/pull/71), [\#110](https://github.com/AY2021S1-CS2113T-W12-1/tp/pull/110))
  * What it does: Allows the user to set up a reminder for any specific task that they chose, for any specific time within the date of the task.
  * Justification: This feature is helpful for our application as part of a planner's purpose is to help the user keep on a fixed schedule. The reminder function helps forgetful or extremely people keep track of their tasks.
  * Highlights: This feature has been integrated into the add task command, so instead of having a separate command for reminder, it is embedded into the add command for easier use. It was an intricate implementation as the Task object has many interactions to account for, and it's integration required careful consideration of how it could affect the other parts of the code negatively.

* **Code contributed**: [RepoSense link](https://nus-cs2113-ay2021s1.github.io/tp-dashboard/#breakdown=true&search=mhchan163&sort=groupTitle&sortWithin=title&since=2020-09-27&timeframe=commit&mergegroup=&groupSelect=groupByRepos&checkedFileTypes=docs~functional-code~test-code~other)

* **Enhancement to existing features**:  
   * Restructured the Parser class to extract the parameters from the user input instead of leaving it to be done in the command classes, with the aim of making it more in accordance with OOP. (Pull Request [\#69](https://github.com/AY2021S1-CS2113T-W12-1/tp/pull/69))
   * Wrote test for Reminder class after integration to Task object. (Pull Request [\#110](https://github.com/AY2021S1-CS2113T-W12-1/tp/pull/110))
   
* **Documentation**:
    * User Guide: 
        * Added documentation for reminder feature and edit the format for add and edit according (Pull Request [\#115](CS2113T-W12-1/tp/pull/115))
        * Standardised expected outcome photos (Pull request [\#115](CS2113T-W12-1/tp/pull/115))
    * Developer Guide: 
        * Added manual testing section (Done on a 3rd party platform by me and transferred and committed by my teammate Qixing. Pull Request [\#55](https://github.com/AY2021S1-CS2113T-W12-1/tp/pull/55))
        * Added documentation for reminder feature into design and implementation section (Pull request [\#126](https://github.com/AY2021S1-CS2113T-W12-1/tp/pull/126))
        * Added reminder class to the task class diagram (Pull request [\#126](https://github.com/AY2021S1-CS2113T-W12-1/tp/pull/126))
        
 