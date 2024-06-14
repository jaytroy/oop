# Report

Pana (s4339959) & Jay (s5069890)

## Introduction

> *The program is a simulation game designed to model and visualize interactions between different armies on a map. It utilizes the Model-View-Controller (MVC) architectural pattern to separate concerns and ensure a modular and maintainable codebase. Users can interact with the game through a graphical user interface, which allows them to manage armies, nodes, and edges, control events, and observe the outcomes of their actions in a dynamic and engaging way.
*

>Expected length: ~100 words

## Program design

> *Here you go over the structure of the program. Try not to go too in-depth here implementation-wise, but rather discuss the important components and relations between them.
> If you think it can help, feel free to add a simple diagram here. The design of the program should be clear to the reader.
>
> In particular, describe the model of the program. How is it structured? How did you make sure to separate the different aspects of the program?
> How do the `model`, `view` and `controller` interact with each other?
> Additionally, you should include some design decisions in here. There is no need to provide an explanation for every single thing,
> but there are often multiple ways of implementing a feature and in those cases it makes sense to state why you chose one over the other.*

>The program follows several design patterns to ensure modularity, flexibility, and maintainability. Key design patterns used include MVC, Observer, Factory, Facade, and Composite.

> Model-View-Controller (MVC):
> Model: The core data and logic are encapsulated in the Model. Classes like Army, Node, Edge, and Event manage the game's state and behaviors. They are responsible for maintaining the game's data, ensuring that all business logic and rules are enforced.
> View: The View comprises the UI components, primarily implemented using Swing's JPanel. Examples include MainPanel, NavBar, ArmyMenu, and NodeMenu. These components are responsible for displaying the data and providing the user interface for interaction. They listen to changes in the Model to update the UI accordingly.
> Controller: The Controller mediates between the Model and the View. It processes user inputs, invokes changes in the Model, and updates the View. Examples include ArmyActions, NodeActions, EdgeActions, and SaveActions. The Controller ensures that user actions are handled appropriately and the state changes are reflected in the UI.

> Observer Pattern
The Observer pattern is implemented to facilitate communication between the Model and the View. The Model components act as subjects that notify their observers (the View components) about state changes. This ensures that the UI stays synchronized with the underlying data without the need for constant polling.
Example:
When a Node or Army changes state, it notifies the associated UI components, which then update their display to reflect the new state. This decouples the data management from the presentation logic, promoting a more modular design.

>Factory Pattern
The Factory pattern is used to create various events in the game. This pattern abstracts the instantiation process, allowing the creation of different event types through a common interface.
Example: The EventFactory class is responsible for creating different Event objects. By centralizing the event creation logic, the Factory pattern promotes consistency and scalability, allowing new event types to be added with minimal changes to the existing codebase.

> Facade Pattern
The Facade pattern is applied to simplify interaction with complex subsystems. In this program, it is used for handling button actions in the controller.
Example:
A ButtonAction class can encapsulate the complexity of different button actions, providing a unified interface for the buttons to trigger specific actions. This reduces the coupling between the UI components and the underlying logic, making the code easier to manage and extend.
Composite Pattern

> The Composite pattern is utilized in the UI design (because we are using swing) to create a hierarchical structure of components. This pattern allows individual UI elements and their compositions to be treated uniformly.
Example:
The MainPanel contains multiple nested JPanel components like NavBar, ArmyMenu, and NodeMenu. Each of these panels can further contain buttons, labels, and other UI elements. This structure allows the UI to be built and managed in a modular way, where each panel can be developed and tested independently.

> These design patterns collectively enhance the program's structure by promoting separation of concerns, reducing code duplication, and increasing flexibility. The MVC pattern ensures a clear division between data, presentation, and control logic. The Observer pattern helps in that goal by keeping the UI responsive to data changes. The Factory pattern abstracts the creation of complex objects. The Facade pattern simplifies interactions with different button actions, and the Composite pattern enables a scalable and modular UI design. These design decisions contribute to a robust, maintainable, and extensible application.


## Evaluation of the program

> We are quite happy with our code and we think that most things work smoothly. The view is quite large and has quite a few lines of code. On future implementations we could create a view-model, housing all the logic of the view. This design technique essentially creates an MVC pattern inside the MVC pattern and it makes it easier for programs with a large front end to be managed . Additionally a lot of extra, such as textures since the program looks rather ugly right now. Additional features such as special units for each function could also be implemented to make the game feel more complete.
Lessons learned
Developing this program highlighted the importance of clear separation of concerns in complex applications. The MVC pattern proved invaluable in managing the program's complexity and facilitating collaboration between team members. The experience also underscored the need for comprehensive testing and iterative development to ensure stability and usability.


## Questions

Please answer the following questions:

1. In this assignment, the program should follow the Model View Controller (MVC) pattern. Please explain the design of the program in terms of the MVC pattern. Specifically try to answer the following questions:
   - MVC consists of three components: Model, view and controller. Can you please explain the role of each component? Please provide examples of these roles from the assignment. How are these three roles (i.e. Model, view and controller) are implemented in the assignment?
   - MVC enforces special constraints on the dependencies between its three components: Model, view and controller. Please explain these constraints, and why are they important?

___

Answer: The three main components are:
- Model: The Model is responsible for the core data and logic of the application. It manages the state and behavior of game entities such as armies, nodes, edges, and events. For example, the Army class stores information about individual armies, while the Node and Edge classes manage the graph structure representing the map. The Event classes and all its subclasses handle different occurrences within the game. Additionally the “logic” of the game can be said to be stored inside the simulation class, as it is responsible for performing the necessary actions of the game and updating all the parameters of the nodes, edges and armies affected by it.
-
- View: The View handles the presentation layer of the application. It includes various JPanel components that render the game's user interface. Specialized elements like the indicators packages and the graph panel class display specific aspects of the game state to the user. Similarly other elements such as the menu bar and again the graph panel serve as interfaces for the user to be able to interfere (through the controller) on the model and change the state of the program (eg. add an event (event menu), select a node (graph panel), run a simulation (simulation menu) ect). The View components update in response to changes in the Model to ensure the UI accurately reflects the current game state.
-
- Controller: The Controller acts as an intermediary between the Model and the View. It handles user input, processes commands, and updates the Model and View accordingly. For example, the NodeActions will be called from the view when the user presses the “add a new node” button. Node Actions will in turn signal to the model to add a new node, the model will then inform the view to update to display the newly created Node. The Controller ensures that user interactions trigger the appropriate changes in the Model and that the View is updated to reflect these changes.
- Dependencies and constraints:
-
- Model constraints: The Model should not have dependencies on the View or Controller. This ensures the core logic and data management of the application are isolated from the presentation and control logic, promoting reusability and maintainability.
- View constraints: The View depends on the Model to retrieve the data it needs to display. However, the View should not modify the Model directly. Instead, it should rely on the Controller to handle user actions that require changes to the Model. This separation ensures that the View remains focused on presentation.
-
- Controller constraints: The Controller interacts with both the Model and the View. It processes user input, updates the Model, and triggers updates in the View. By acting as a mediator, the Controller maintains the separation between the Model and the View, ensuring that changes in user input are appropriately reflected in the application's state and presentation.


___

2. The Swing library provides the ability to create nested user interface components. In this assignment, you created multiple JPanel components on the user interface. These contain other user interface components to build-up a tree of user interface components.
   Which design pattern does Swing implement to create a hierarchy of user interface components? Please explain this pattern and how it is implemented in Swing.

___

Answer:
- The Composite pattern allows swing to create the nested interfaces use in the program. It allows individual objects and compositions of objects to be treated uniformly. This pattern is particularly useful for creating tree structures where individual components and groups of components are managed in a consistent manner.
- Swing's implementation of the Composite pattern is evident in its component hierarchy. For instance, a JPanel can contain other JPanel components, each with its own child components. This allows developers to build complex user interfaces by nesting components, treating each component as a part of the whole. For example, the MainPanel can contain multiple subpanels like NavBar, ArmyMenu, and NodeMenu, each of which can further contain buttons, labels, and other UI elements. This hierarchical structure makes it easy to manage and update the UI as a cohesive unit.
___

3. The Observer pattern is useful to implement the MVC pattern. Can you please explain the relationship between the Observer pattern and the MVC pattern?
   Please provide an example from the assignment on how the Observer pattern supports implementing the MVC pattern.

___

Answer:
- The Observer pattern is integral to the MVC architecture as it facilitates the automatic updating of the View in response to changes in the Model. In the Observer pattern, objects (Observers) subscribe to receive updates from another object (Subject) when its state changes. This decouples the objects, allowing them to interact without tight dependencies.
- Example in the assignment: In the program, the Model components act as Subjects, while the View components act as Observers. For example, when a Node changes state (e.g., its status or properties are updated), it notifies the View components displaying its data. These View components then update their display to reflect the new state of the Node.
  This ensures that the UI is always in sync with the underlying data without requiring the View to continuously poll the Model for updates. The Observer pattern thus supports the MVC structure by enabling responsive and dynamic UI updates.

___

## Process evaluation

> *Describe shortly the process that led to the final code and the report. What was easy, what was difficult? Did you make interesting mistakes? What have you learned from this assignment?*

> Because the cooperation among the members was quite streamlined and efficient. We started with a rough design of the program's structure, we then proceeded to break up the project into smaller more manageable tasks.
> Finally, a necessary debugging of the final program was conducted. One thing that we did notice and we reflect on is the unnecessary design refactoring of the code we had to undergo.
> Even though a general outline of the program was collectively conceived before coding commenced, upon finishing the first functional version of our code we realized that some parts of our design inherently violated the MVC pattern.
> This led to some minor restructuring of our program's design. The take-away here is that a lot of very careful planning should come before the actual coding to ensure that all the design patterns are understood and implemented correctly.
> Nevertheless, this need for minor refactoring was not a major hurdle and served more as a learning experience if anything as we are both quiet happy with the final result

## Conclusions

> *Overall, we are quite happy with our result. We believe the code adheres to the MVC pattern, the rule of separation of concerns and the basic principle of Object Oriented Programming. The workflow was efficient and the cooperation among the group mates was smooth and pleasant. It was an instructive project and a great implementation of the concepts presented to us throughout the course.*

