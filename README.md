# BCS Schedule Sharer

## Project overview

At the start of every semester in the BCS Facebook group, a poll is created so that everyone can share which courses 
they are taking. A poll must be manually created with various courses inputted as options. Students then "vote" on
the poll with their courses and they are able to see their peers' courses for the semester. There are a few problems
with this method:
- it is not the most user friendly UI as students must scroll through the many options to find courses
- the poll must be manually created every semester with each course inputted
- the poll options do not include information about course sections as it would make for too many options

The goal of this project is to create an application with a **user friendly** interface that allows students to 
share their courses and see which courses their peers are taking. The hope is that it will streamline the whole
process and provide granularity in the information provided by the users.

## User stories

- As a user, I want to be able to create an account to store my info (name, email, course schedule) 
- As a user, I want to be able to input my courses and share it with everyone
- As a user, I want to be able to view my courses 
- As a user, I want to be able to browse courses and their sections and see who is taking them this semester
- As a user, I want to be able to search for a peer and view their course selections for the semester

Project Phase 2 (2/26/2020):
- As a user, I want to be able to save my course schedule to a text file
- As a user, I want to be able log in and reload my info including my course schedule and other course/peer info

## Instructions for grader - Phase 3

Project Phase 3 (3/22/2020)

**NOTE: I had started this project using a local database on my laptop and always demoed from my local database. 
My instructor said that this was fine, but now that grading is done remotely, the TA will find that they cannot 
get past the login page due to connection issues. To circumvent this issue, I have recorded myself demoing the app
and you can find the video at  https://www.youtube.com/channel/UCrG319uC30WUrl220JMgW_A  (my Youtube channel).
Please contact me at sunhochung53@gmail.com if you have any questions or concerns.**

- First, please register an account. Click "Register" on the login page. On the register page fill out details, and 
click "Register." You now have an account and this should take you to the main app page.
- 1st Required Event: In the "View/Edit Your Schedule" box, please enter a course (e.g. CPSC, 210, 201, 2019W) and 
click "Add course." This should add a course to your account and you will see it in the table.
- 2nd Required Event: Alternatively, you can delete a course by entering the course and clicking "Delete course."
This removes the course from your account and refreshes your schedule.
- Other functions: In the other boxes, you can search for a peer's schedule or search for peers in a certain course.
Try searching James Chung's (cohort 2019) schedule. Try searching for peers in CPSC 210 201 2019W.
- Visual component: On each page of the app, you can see the UBC logo in blue and app name label on the top. 
Additionally, the scroll pane tables act as visual representations of schedules and peers.
- You can save your schedule to a text file by clicking "Save to file" in the "View/Edit Your Schedule" box. 
- To reload the state of the app, you simply need to log in. Try exiting the app and logging back in. The app should
start up with your course schedule visible and you have full access to the database. 