# Assignment 3

## Pixels and Pictures

Digital pictures are usually represented as a two-dimensional grid of "picture elements" or "pixels". Each pixel represents the color of a particular spot on the picture and the resolution of a picture can be expressed in terms of how wide and tall the picture is in pixel units.  

### Pixels

A color (to a computer) is a specific formula of three components: red, green, and blue. We will be using values in the range of 0.0 up to 1.0 for each of these components. A 0.0 value represents no amount of that component and 1.0 is the maximum amount of that component. When the red, green, and blue components all equal each other, you get a color on the "grayscale" spectrum from black (all 0.0’s) to white (all 1.0’s). The chromatic colors are formed when the values for red, green, and blue differ from each other.

You can read more about the RGB color model here if you want to learn more: http://en.wikipedia.org/wiki/Red_green_blue

In this assignment, I have provided classes for both color pixels and grayscale pixels (i.e., pixels where the red, green, and blue components by definition are aways equal to each other) as two possible implementations of a common Pixel interface. Instances of these classes will be immutable, so they won’t change once created. 

You should read through the code for Pixel, ColorPixel, and GrayPixel to understand what the methods of the interface do and how these two classes implement that interface.

### Pictures

The Picture interface defines an abstraction for representing a 2-dimensional frame of pixels. This abstraction will provide a number of methods to query and possibly set various properties including individual pixel values. Individual pixel values are addressed by their position (i.e., x and y coordinates) within the frame. The x-coordinate represents the column of the pixel and the y-coordinate represents the row of the pixel. The top of the picture is the first (i.e., 0th row) and the bottom is the last row. This means the upper left corner of the picture has the (x,y) coordinate (0,0) and the lower right picture has the coordinate (w-1, h-1) where w and h are the width and height of the picture.

Read through the code for Picture and be sure you understand what each of the methods is supposed to do. In particular:

 * The various forms of the paint method should generally return a new Picture object with the described changes. In other words, a Picture object is expected to be immutable.
 * The form of the paint method that paints a region specifies two opposite corners, namely (ax, ay) and (bx, by). Depending on the values of ax, ay, bx, and by these might represent the upper left and lower right corners or these might represent the lower left and upper right corners. Which situation is in effect is determined by the values provided and you should not make any assumptions about which is which.
 * All parameters should be checked for being within their legal values (i.e., coordinates are all non-negative and within the picture's dimensions, pixel values are non-null, factor values are between 0.0 and 1.0, etc.). Any illegal values should result in an IllegalArgumentException. 
 
## Novice

Create three implementations of Picture as follows.

 * PixelArrayPicture
   * The PixelArrayPicture should implement Picture by encapsulating a 2D array of pixels. It should have the following constructor:
   
   ```
   public PixelArrayPicture(Pixel[][] pixel_array)
   ```
   
   
