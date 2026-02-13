# ArrayManager - REPORT OF ASSIGNMENT 1 
## Overview & Launch Notes.
This program works as an "Array Manager" so project has been named ArrayManager
To run the program - run ArrayManager.java in the /src folder.
This program will read from "A1input.txt" as given by the instructor. Every instance that this program launches will either create or overwrite "output.txt" for the output. Both of these files are held in /bin.

!!NOTE!! - I will test each task in a new instance, meaning changes made in one screenshotted task on this document will not appear in another part of this document. That is expected, to see it all play together, try it yourself, it works.

Following assignment instructions, on launch, the program will read & load the given input file in the project folder.
After that, a menu screen will be displayed with 5 choices (user inputs an integer from 0-4).

If the input is not valid, it will prompt you to try again.
!["invalidinput"](/Assignment1/images/failedinput.png)

## Task 1: Check if a user given value is present in the array.
On the main menu upon launch, if the user types 1 into the console, the console will prompt the user for the value they want found. Once given, if the value is present in the array, the program will return the location and be ready for the next command/task. Because all elements are in sequential from 1-100, the index will be 1 less than the value. (Index starts at 0, Values start at 1)
!["inbounds1"](/Assignment1/images/inbounds1.png)

If it is out of bounds, it will return that it was not found and the main menu screen will appear.
!["oob1"](/Assignment1/images/outofbounds1.png)


## Task 2: Modify a value at a given index, prints ("returns") new value and old value.
In the main menu, if the user types 2, the console will prompt the user for an index of the value to be modified. If the index given is in bounds and the new value is valid, it will element at the index and print both the old value and new value to user.

If there is any error, there is an exception handler that will print to the user that the modification failed and will return them to the main menu, if they want to try again.
!["task2"](/Assignment1/images/2all.png)
Modified index 33 to be 999.
!["task2out"](/Assignment1/images/2out.png)

## Task 3: Add an integer to the end of the array.
If the user types 3 in the main menu, the console will prompt the user for a value to append to the end of the array. 
There are 2 exceptions to this method. If the user input is invalid, the program will handle and return user to main menu. If the array has run out of space, this method will double the array size.
The default array size is 100. There are 100 elements by default. As seen in Task 2's first image.

The image below shows the expanded array size after just appending an element. An abstract variable (primitive in abstract) was made as the array size variable to get access across all methods and viewable to the class. Meaning the array size doubles if there is no room. 
!["task3"](/Assignment1/images/task3.png)
These commands appended 700 & 800 to the end.
!["task3out"](/Assignment1/images/3out.png)


## Task 4: Delete an integer at a given index.
If the user types 4 in the main menu, the console will prompt the user to enter an index to delete the respective value from the array. If there is a successful removal, all elements to the right of the value in the array will shift left. 

It will delete nothing & return to main menu selection if the given index is invalid.
!["task4"](/Assignment1/images/task4.png)

The removed element then becomes free for the "new" element to the right to move in and after it has moved, the "new" element's old index will be changed to null for the element to the right of it to move into that index and so on.
!["task4out](/Assignment1/images/4out.png)

# END OF REPORT - DEFAULT README CONTINUES
## Folder Structure
The workspace contains two folders by default, where:
- `src`: the folder to maintain sources
- `lib`: the folder to maintain dependencies
Meanwhile, the compiled output files will be generated in the `bin` folder by default.

> If you want to customize the folder structure, open `.vscode/settings.json` and update the related settings there.

## Dependency Management
The `JAVA PROJECTS` view allows you to manage your dependencies. More details can be found [here](https://github.com/microsoft/vscode-java-dependency#manage-dependencies).