# Assignment 3

## Pixels and Pictures

Digital pictures are usually represented as a two-dimensional grid of "picture elements" or "pixels". Each pixel represents the color of a particular spot on the picture and the resolution of a picture can be expressed in terms of how wide and tall the picture is in pixel units.  

### Pixels

A color (to a computer) is a specific formula of three components: red, green, and blue. We will be using values in the range of 0.0 up to 1.0 for each of these components. A 0.0 value represents no amount of that component and 1.0 is the maximum amount of that component. When the red, green, and blue components all equal each other, you get a color on the "grayscale" spectrum from black (all 0.0’s) to white (all 1.0’s). The chromatic colors are formed when the values for red, green, and blue differ from each other.

You can read more about the RGB color model here if you want to learn more: http://en.wikipedia.org/wiki/Red_green_blue

In this assignment, I have provided classes for both color pixels and grayscale pixels (i.e., pixels where the red, green, and blue components by definition are aways equal to each other) as two possible implementations of a common Pixel interface. Instances of these classes will be immutable, so they won’t change once created. 

You should read through the code for Pixel, ColorPixel, and GrayPixel to understand what the methods of the interface do and how these two classes implement that interface.

