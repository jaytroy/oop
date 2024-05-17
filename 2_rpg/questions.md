# Question 1

In step 1, you were asked to create a `Room` class with a description, which will be printed if inspected. Two software developers proposed two different implementations for the `Room` class.

The first developer proposed one implementation:

```java
public class Room {

    private String description;

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}
```

The second developer proposed another implementation:

```java
public class Room {
    public String description;
}
```

Both developers are discussing which implementation is better and why. Please answer the following question:

Which of these two implementations would you select? And why?

Justify your answer in at least 250 words. Please explain the consequences, benefits and drawbacks of each implementation and support it with an example.

___

Answer:
The implementation of the first developer is superior. By assigning the description of the room as a private variable they are abiding by the fundamental principle of encapsulation, 
while the second developer completely disregards it. This gives the first developers code the following three advantages. 

Firstly, privacy, by restricting access to possible sensitive information it protects the data and ensures that unwanted changes are not made. 

Secondly, extendability, with this design it is easier for additional logic to be implemented on the Room class without altering the current functionality.

Lastly this design makes the code more readable and easier for its functions to be understood, especially for other developers.
This consequently also helps increasing the ease of debugging.

The only possible drawback the first approach might have is that it requires more lines of code to write, so in an extremely niche situation where speed is the only factor under consideration it could be considered the superior choice. However, considering all the previously mentioned benefits in almost all other cases such a drawback is well worth it.

___

# Question 2

In step 2, you are asked to create two interfaces: `Inspectable` and `Interactable`.
Interfaces by definition do not have implementations. They consist of method signatures:

```java
interface Inspectable {
    void inspect();
}
```

A software developer claims that interfaces are not useful, because they do not contain implementations. Thus, we should just use classes, and we do not need to define empty interfaces.

What do you think about this opinion? Do you agree or disagree with this opinion?

Please justify your answer in at least 250 words, and support your justifications with an example.

___

Answer:
This claim disregards the fundamental role interfaces play in OOP and the benefits they offer in terms of code
organization, flexibility, and maintainability. Interfaces serve as contracts that define a set of methods that 
a class must implement. While it's true that interfaces themselves do not provide implementations for these methods,
their purpose lies in establishing a common protocol or behavior that can be implemented by multiple classes. 
Thus they offer a great deal of benefits to the programmers that chose to implement them.

First of all because of abstraction: Interfaces hide away the implementation details and focus on defining the contract
or behavior that classes must adhere to. This abstraction allows for more flexible and loosely coupled designs, as classes 
can interact with objects based on their interfaces rather than their concrete implementations. This makes the code easier 
to organize and maintain.

Secondly because of the power of polymorphism: Through its use objects of different classes can implement the same interface
in their own way while being treated uniformly, allowing us for greater flexibility while coding. If we were to just make all
of our interfaces classes we would be severely limiting ourselves since Java allows a class to inherit behavior from multiple
interfaces, while on the other hand they can extend only one other class.

Lastly , interfaces help enforce the "contract" between classes, ensuring that implementing classes provide the necessary functionality
as specified by the interface. This promotes consistency and helps prevent errors by providing a clear expectation of behavior.


___

# Question 3

To save your game state, you were asked to use Java classes `FileOutputStream` and `ObjectOutputStream`.
These two classes are part of the Java libraries, and they are designed based on a specific design pattern.

Which design pattern do `FileOutputStream` and `ObjectOutputStream` implement?

Explain the roles of this design pattern and how `FileOutputStream` and `ObjectOutputStream` implement it. Also explain the benefit of implementing this design pattern.
``
___

Answer:
The `FileOutputStream` and `ObjectOutputStream` classes in Java implement the Decorator Pattern.
This design pattern is used to dynamically add behavior to objects without modifying their structure. 
Here `FileOutputStream` is the class that provides basic functionality to write raw bytes to a file. 
`ObjectOutputStream` is used to write objects to an output stream, including a `FileOutputStream`.

The Decorator Pattern involves components and decorators.
The `OutputStream` abstract class serves as the component interface, defining the basic operations for writing data.
`FileOutputStream` is a concrete component that implements this interface to write data to files. 
`ObjectOutputStream` is a concrete decorator that extends `OutputStream` and adds the ability to serialize objects and write them to any `OutputStream` instance, such as a `FileOutputStream`.

By using the Decorator Pattern, `ObjectOutputStream` can extend the capabilities of `FileOutputStream` without altering its code.
This pattern provides flexibility by allowing additional behaviors to be added to objects at runtime, ensuring that each class adheres to the single responsibility principle.
The client code remains unaware of the specific decorators used, interacting only with the component interface, which promotes transparency and ease of maintenance.


___
