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

Create two implementations of Picture as follows.

 * PixelArrayPicture
   * PixelArrayPicture should implement Picture by encapsulating a 2D array of pixels. It should have the following constructor:   
   ```
   public PixelArrayPicture(Pixel[][] pixel_array)
   ```
   The first dimension of pixel_array is the width and the second is the height. In other words, pixel_array.length will be the width of the picture and pixel_array[0].length will be the height of the picture. The pixel at coordinate (x,y) is located at pixel_array[x][y]. 
   
 * MonochromePicture
   * MonochromePicture should implement a Picture that has the same value for Pixel in every position. This value is provided to the constructor along with the width and height of the picture. The constructor should have the following form:
   ```
   public MonochromePicture(int width, int height, Pixel value)
   ```
   
 ## Adept
 Create three more implementations of Picture as follows.
 
 * GradientPicture
   * GradientPicture should implement a Picture that is a smooth blend of pixel values specified for its four corners. In other words, any pixel in the middle of the picture is a proportional blend of the pixel values associated with its corners in proportion to the distance of the pixel to those corners. The constructor fo GradientPicture should have the form:
   ```
   public GradientPicture(int width, int height, Pixel upper_left, Pixel upper_right, Pixel lower_left, Pixel lower_right)
   ```
   
 * HorizontalStackPicture and VerticalStackPicture
   * These implementations will encapsulate references to two Picture objects and will represent them as if they were a larger Picture object that resulted from "stacking" them either horizontally or vertically. The constructors for these new classes should have the following form:
   
   ```
   public HorizontalStackPicture(Picture left, Picture right)
   public VerticalStackPicture(Picture top, Picture bottom)
   ```
   
   For example, if I have two Picture objects that are 5 pixels tall and 10 pixels wide (call them A and B), and then create a new HorizontalStackPicture object using A as my "left" and B as my "right", then the resulting object will be a Picture object that is 20 pixels wide and 5 pixels tall. In the new object, the upper left pixel at (0,0) will correspond to the pixel at (0,0) in A and the lower right pixel at (19,4) will correspond to the pixel at (9,4) in B. The following picture may help illustrate the situation:
   
   ![Horizontal Stack Picture Example](http://www.cs.unc.edu/~kmp/comp401fall18/assignments/a3/horiz-stack-example.png "Horizontal Stack Picture Example")

   The constructors should throw an IllegalArgumentException if any of the parameters are null or if the geometry of the objects provided are not compatible (i.e., if the heights of left and right differ for HorizontalStackPicture or if the widths of top and bottom differ for VerticalStackPicture).
   
## Jedi

Create an interface called PixelTransformation as follows:

```
public interface PixelTransformation {
   Pixel transform(Pixel p);
}
```

Now create two implementations of PixelTransformation called: Threshold and GammaCorrect

The constructor for Threshold should have this signature:

```
public Threshold (double threshold)
```

The transform method of a Threshold object should produce either a white pixel or a black pixel depending on the intensity (i.e., brightness) of the pixel p passed to it. If p’s brightness is strictly above the threshold value specified in the constructor, then a white pixel is returned. Otherwise, a black pixel is returned.

The constructor for GammaCorrect should have this signature:

```
public GammaCorrect (double gamma)
```

The transform method of a GammaCorrect object should produce a "gamma corrected" version of pixel p passed to it. The components (i.e., red, green, and blue) of the gamma corrected pixel are the result of raising them to the (1.0/gamma) power. In other words, if "old" is the original component value in the range from 0.0 to 1.0, then "new" can be calculated as:

```
new = Math.pow(old, (1.0/gamma));
```

Create a new implementation of Picture called TransformedPicture. The constructor should have the following signature:

```
public TransformedPicture (Picture source, PixelTransformation xform)
```

A TransformedPicture should encapsulate the provided source Picture object and PixelTransformation object. A TransformedPicture object is expected to transform the pixel values of the source frame on demand when getPixel is called using the pixel transformation object provided to the constructor.

# Grading

Submit to the autograder by pushing a "submit" branch to github. All code should be in the package a3.
* 2 points for Novice
* 6 points for Adept
* 2 points for Jedi

