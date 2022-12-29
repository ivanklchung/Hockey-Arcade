android studio final project for mobile university course

Functional description

Arcade is a Android mobile app that has 3 minigames that include “Mystery Jersey”,
“Stick and Puck '', and “Tap Tap Puck”. Mystery Jersey is a guessing game, Stick and
Puck is an interactive game that involves hitting a puck and Tap Tap Puck is an
interactive game that involves tapping pucks to score. Targeted users are for kids or
anyone that enjoys hockey. For further releases, there will be multiplayer and different
design options. The budget is free for this project. This app will compete with other
mobile games on the Google Play Store. Currently, due to budget constraints, the
application is only published to Github. The design constraints is that I was not able to
test on many Android devices physically. The deadline is due December 12, 2022 and
the project has been completed in two weeks.

Market survey and comparison with similar apps
Similar game applications include Plato and The Jackbox Party Box as they include a
package of games in an application. There is yet to be a functional multi-sport game
application on the Play Store.

Introduction (Splash Screen + Main Page)
The app begins with a splash screen that uses Animation, a Handler, and Intents.
Within the splash screen, copyright text is added to the bottom of the screen as a
disclaimer for when the app starts for the very first time. The image from Splash screen
has been generated and imported from within Android Studio. The main menu page will
consist of a linear layout of buttons that will lead the user to each of the minigame
activity that is clicked on by using onClickListeners to open Activities and will display
a Toast Message to state what game is being opened. All games will include services
such as the onCreate bundle. There will also be a contact button to open a contact
window that allows the user to report bugs via Gmail. Every settings page will have the
option to go back to the Main Page and handle the life cycle. Games can be played
and accessed all through the Main Activity and can be played seamlessly in any order
for as many times the user wishes to play for. Each game has its own theme, but the
overall app has the same theme which is purple and blue by use of consistent buttons
and layouts. Images and Audio files have been imported from stickpng.com and
freesound.org respectively and png files are edited through the use of
onlinepngtool.com. Puck and Stick images png artwork have been edited and
designed specifically to fit this application. Throughout the project
developer.android.com was used for guidance and inspiration. The app has been tested
on multiple Android phones available on emulator and a Lenovo Tablet. Attached in a
separate zip folder are videos of the app working and running. Final Copy of Game will
be uploaded to Github instead of Google Play Store.

Splash Screen Test Case
- Test duration
- Test fade animation
- Test copyright stays for whole duration
- Test never appear again once completed
- Test navigation throughout app using buttons to access all games and modes
- Test size of screen
- Test copyright message appears

Main Page Test Case
- Test visual works for multiple devices
- Test each button opens each game successfully
- Test all home buttons lead back to Main Page

Game 1 - Mystery Jersey
Mystery Jersey is a guessing game where the user must guess the random jersey
number in a limited number of guesses. After each guess, the user will see their last
guess attempt, a hint, and how many tries left they have. There are 3 difficulty modes
which include jersey number options of two, three, and four digit jerseys that serve as
“settings” screen. The modes are created by a radioGroup of 3 radio buttons.
There will be a transition between activities from the main page to the actual guessing
game. There is use of Constraint, Relative and Linear Layouts in the design. The
game will choose a random number using a random number generator and create an
ArrayList of all the guesses. It will compare the user guess with the chosen random
number and check if it is empty, if the user guess has the same number of digits,
greater or less than, and in each scenario, will post a Toast Message. If the user guess
fits all requirements, setVisibility is set to visible and the game begins by recording all
data and updating it after each guess. Once the game is over, an AlertDialog will be
present and display all stats from the game. If user selection is “yes”, lead to Game
Main Page Activity and if “no”, lead to Arcade Main Page for the user to play again. This
game is inspired by the HiLo card game.

Game 1 Test Cases
- Test all 3 modes selections work
- Switching between selections
- And all modes run smoothly
- Test valid numbers of integers such as two digit, three digit, and four digit.
- Test keyboard only allow integers input
- Test all number responses for higher and lower
- Test winning scenario.
- Test losing scenario
- Test using same number
- Test 0 integer
- Test using decimal numbers
- Test Play Again Button (Yes Option)
- Test Home Button (No Option)

Game 1 Known bugs
- No radio selection, should not start game (Fixed)
- Toast message should alert user to select an option
- Toast message sometimes doesn’t appear after back to back numbers (Fixed)
- Limit number of digit inputs (Fixed)

Game 2 - Stick and Puck
Stick and Puck is a game designed to shoot the puck as many times without missing.
The user will have 3 lives to try and shoot the puck as many times as possible. The
puck will increase speed as the game progresses. If a highscore is beat, a medal will
then appear. If not, highscore and score will show up as normal. The user will have the
option to play again or return to the Main Activity. The design of this activity uses Linear
Layout and Canvas class to draw the rink where the game is played. Audio and Image
files have been imported from stickpng.com and freesound.org and placed in the
Drawable and Raw folder respectively. All buttons are created using ImageButton and
onClick Method to navigate starting the game, controlling the volume, restarting the
game, and returning to the Main Menu. The game's contents such as scores are
designed using the combination Shared Preferences and Intents. A custom theme
was used to design the full screen of the game. Stick and Puck Activity was created
using a custom view class by extending View that controls all animations, collisions,
and tracks all point systems. In addition, Paint was used to draw the rink and overall
game attributes. Bitmap was used to store the image file of both the stick and puck.
MediaPlayer was used to control the sound of puck hitting and missing the stick. A
TableLayout was used for the end credit scores. This game is inspired by a mix of
Pong and BrickBreaker games.

Game 2 Test Cases
- Test home button, play button, and sound button all work as image buttons
- Test sound button works and syncs with audio when on and off
- Play game multiple times
- Test if a medal image appears when high score is beaten
- Test refresh and home buttons at end of game
- Test dragging of hockey stick
- Test different devices to see if hockey stick will drag to end of screen
- Test different angles the puck hits the stick
- Test different devices for velocity and hit edge of screen responses
- Puck should increase as game goes along
- Test after 3 lives game must end
- Test health bar size and color changes
- Test score is updating beginning each game and scores are correctly stored

Game 2 Known Bugs
- Puck sometimes same x and y coordinate stuck (Fixed)
- Fix all conditions of hitting the stick against puck (Fixed)
- Audio doesn’t last full duration(Fixed)
- Audio OFF button but keeps playing (Fixed)
- Speed of puck varies between devices

Game 3 - Tap Tap Puck
Tap Tap Puck is a game where you have to try to touch as many pucks on the ice rink in
a certain amount of time to score. Every time the puck is tapped, a goal is scored and a
goal horn light will appear. In addition, the game will keep track of score and highscore
after the match. The game layout is created using a gridLayout that will house 9 pucks
and appear at random times on a sheet of ice for the user to tap. The game starts with a
countdown screen that uses a CountDown Timer to control the time before the match
begins. Furthermore, an additional Countdown Timer will be used to control the amount
of time the user has to tap pucks. Pucks will appear at random with the use of Handler
and Runnable classes as service. The images of pucks and the goal light are used via
imageView. Once the game is over, a screen will appear with the user’s score and
highscore and this is dealt using Shared Preferences.The user will then have an option
to play again and attempt at beating the high score or return back to the Main Activity
and these transitions are dealt with the Intent and onClickListener. The game will
speed up depending on the user’s previous score and will adjust after every match
which is dealt by with the Handler. The game is inspired by Whack- a-mole and Bubble
Burst games.

Game 3 Test Cases
- Test Countdown timer to begin game starts immediately
- Tests second Countdown timer starts right after and does not interfere and is
separate
- Tests Pucks spawn randomly AND on the gridlayout only
- Test score is being updated and increase only by 1 puck at a time
- Test sizing image of both the puck and light
- Test when puck is tapped, full goal horn image shows up
- Test tapping on rink with no pucks and see any reaction
- Test score high score is updated at the end
- Test different game score scenarios puck speed
- Test touching services of different devices
- Test Play Again button and Go Home button
- Test different devices, rink size can hold all 9 possible puck locations

Game 3 Known Bugs
- Countdown timer overlaps, need a fresh start when game starts (Fixed)
- Clicking rink counts as a point (Fixed)
- Goal horn image disappear too fast (Fixed)
- Clicking puck sometimes doesn’t register or counts as two (Fixed)
- Gold medal does not appear sometimes when beat high score (Fixed)
- Rink size varies between devices

Contact Developer Activity
To contact myself, the developer, I created a Button on the Main page that displays
access to my email and uses an Action View actions and specific content with an URI
as the intent data. This will lead the user to whatever mail account is associated with
their device.

Contact Developer Activity Tests
- Tests button leads to contact page
- Tested using Gmail and Outlook.
- Tests logging in and developer email and subject appear automatically
- Test still can access game and all highscores are still saved

Contact Developer Activity Bugs
- Glitches if there is not an Internet connection
Performance evaluation
All apps run smoothly and all games seamlessly transition between each other.
Attached is a zip folder of how each game is run.

Future Updates
If given more time, I would have liked to sync all the games into one leaderboard and
add customization features that allow the user to select different color and image
options. A multiplayer feature would be also added for each game that can be played on
separate devices at the same time over a network. In addition, adding at least 10 levels
would make the app more competitive. Some additional games I would have liked to
add include a quiz game and a goalie simulation if allocated additional time. I did not
have the budget to upload onto Google Play Store, but will purchase a Google
Developer Account on the future releases.
