## Basic Info

There are two types of controller inputs: **binary** and **analog**. A **binary** input is either on or off. For example, the *A button* is either pressed or not pressed. An **analog** input outputs a number from -1 to 1. For example, a *joystick* pressed all the way forwards will output 1, but if it's only pressed half way up, it will output .5.

There are two types of **analog** inputs that we use: *joysticks* and *triggers*.
  * A *joystick* has two axes, X and Y, X being horizontal and Y being vertical. Right on the X axis is positive, left is negative; Up on the Y axis is positive, down is negative. If a *joystick* is not being pushed in any direction, the output will be 0 on both axes.

  * A *trigger* only has one axis, and does not have negative inputs. A *trigger* is only registered as an **analog** input when the switch on the back of the controller is in the X position. When the switch is in the D position, the *triggers* will be registered as **binary** inputs.

There are two ways that a **binary** is used: "while held" and "when pressed":

* If a button does something "while held" that means that the button will continue doing that thing only when the button is being held down, and it will stop doing that thing when the button is released. For example, while holding the *Left Trigger*, the intake will run, and when the *Left Trigger* gets released, the intake will stop running.

  * Often we don't want an **analog** input to always be active, so while a button is held, it will activate an analog input. This will be denoted as "while held, *Example Button* qualifies *Example Joystick* to run motor."
  <br> 
  <br> 

* If a button does something "when pressed", that means that the button will do a thing as soon as the button is pressed, then stop doing that thing immediately. For example, the *Back Button* is pressed, the intake will retract. Holding the *Back Button* will do nothing.
  * 

## Driver's Controller

The driver's controller has yellow tape around the right handle with the text "driver" written on it. The driver's controller's switch should always be in the X position, so the *triggers* will register as **analog** inputs.

In general, the Driver controls the drivetrain and the climber

Bindings: 
* *Left Stick Y axis* controls speed of the drivetrain
* *Right Stick X axis* controls turning of the drivetrain

* while held, *Right Bumper* increases the speed of the drivetrain
* while held, *Left Bumper* decreases the speed of the drivetrain

* *Right Trigger* controls the positive speed of the climber
* *Left Trigger* controls the negative speed of the climber

* when pressed, *A Button* pivots the climber perpendicular to the robot
* when pressed, *B Button* pivots the climber angled to the robot
* when pressed, *X Button* sets the climber hook down
* when pressed, *Y Button* sets the climber hook up

* while held, *Start Button* runs an automatic climb sequence
* when pressed, *Back Button* prepares for the climb


## Co Driver's Controller

The Co Driver's controller's switch should always be in D position, so the *triggers* register as **binary** inputs

In general, the Co Driver controls everything that the driver doesn't. The Co Driver also has a few buttons that do multiple things as well.

Bindings:

* while held, *Right Trigger* spins the shooter to the goal RPM
* while held, *Right Trigger* pushes cargo to the shooter when the shooter is at the goal RPM
* when pressed, 