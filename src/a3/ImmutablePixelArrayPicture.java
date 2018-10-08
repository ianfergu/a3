package a3;

public class ImmutablePixelArrayPicture implements Picture {

	public int width;
	public int height;
	public Pixel initial;
	protected Pixel[][] pixel_array;

	public ImmutablePixelArrayPicture(Pixel[][] pixel_array) {
		if (pixel_array != null) {
			this.pixel_array = pixel_array;
		} else {
			throw new IllegalArgumentException();
		}

		if (pixel_array.length != 0) {
			this.width = pixel_array.length;
		} else {
			throw new IllegalArgumentException();
		}
		if (pixel_array[0].length != 0) {
			this.height = pixel_array[0].length;
		} else {
			throw new IllegalArgumentException();
		}

		for (int x = 0; x < width; x++) {
			if (pixel_array[x] == null) {
				throw new IllegalArgumentException();
			} else {
				continue;
			}
		}

		for (int x = 0; x < width; x++) {
			if (pixel_array[x].length != height) {
				throw new IllegalArgumentException();
			} else {
				for (int y = 0; y < height; y++) {
					if (pixel_array[x][y] == null) {
						throw new IllegalArgumentException();
					} else {
						continue;
					}
				}
			}
		}
	}

	public ImmutablePixelArrayPicture(int width, int height, Pixel initial_value) {
		if (width > 0) {
			this.width = width;
		} else {
			throw new IllegalArgumentException();
		}
		if (height > 0) {
			this.height = height;
		} else {
			throw new IllegalArgumentException();
		}
		Pixel[][] pixel_array = new Pixel[width][height];
		for (int x = 0; x < width; x++) {
			for (int y = 0; y < height; y++) {
				pixel_array[x][y] = initial_value;
			}
		}
		this.pixel_array = pixel_array;
	}

	public ImmutablePixelArrayPicture(int width, int height) {

		if (width > 0) {
			this.width = width;
		} else {
			throw new IllegalArgumentException();
		}
		if (height > 0) {
			this.height = height;
		} else {
			throw new IllegalArgumentException();
		}
		Pixel[][] pixel_array = new Pixel[width][height];
		for (int x = 0; x < width; x++) {
			for (int y = 0; y < height; y++) {
				pixel_array[x][y] = new GrayPixel(.5);
			}
		}
		this.pixel_array = pixel_array;
	}

	public Picture paint(int ax, int ay, int bx, int by, Pixel p) {
		MutablePixelArrayPicture frog = new MutablePixelArrayPicture(width, height);
		if (ax > 0 && bx < width) {
			if (ay > 0 && by < height) {
				for (int x = ax; x <= bx; x++) {
					for (int y = ay; y <= by; y++) {
						frog.paint(x, y, p);
					}
				}
			} else {
				throw new IllegalArgumentException("Too large or small");
			}
		} else {
			throw new IllegalArgumentException("Too large or small");
		}
		return frog;
	}

	public Picture paint(int ax, int ay, int bx, int by, Pixel p, double factor) {
		MutablePixelArrayPicture frog = new MutablePixelArrayPicture(width, height);
		if (ax > 0 && bx < width) {
			if (ay > 0 && by < height) {
				for (int x = ax; x <= bx; x++) {
					for (int y = ay; y <= by; y++) {
						frog.paint(x, y, p, factor);
					}
				}
			} else {
				throw new IllegalArgumentException("Too large or small");
			}
		} else {
			throw new IllegalArgumentException("Too large or small");
		}
		return frog;
	}


	public Picture paint(int cx, int cy, double radius, Pixel p) {
		MutablePixelArrayPicture frog = new MutablePixelArrayPicture(width, height);
		if (radius > 0.0) {
			for (int x = 0; x < width; x++) {
				for (int y = 0; y < height; y++) {
					double distance = Math.sqrt((x - cx) * (x - cx) + (y - cy) * (y - cy));
					if (distance <= radius) {
						frog.paint(x, y, p);
					} else {
						continue;
					}
				}
			}
		} else {
			throw new IllegalArgumentException("Must be a non-negative #");
		}
		return frog;
	}


	public Picture paint(int cx, int cy, double radius, Pixel p, double factor) {
		MutablePixelArrayPicture frog = new MutablePixelArrayPicture(width, height);
		if (radius > 0.0) {
			for (int x = 0; x < width; x++) {
				for (int y = 0; y < height; y++) {
					double distance = Math.sqrt((x - cx) * (x - cx) + (y - cy) * (y - cy));
					if (distance <= radius) {
						frog.paint(x, y, p, factor);
					} else {
						continue;
					}
				}
			}
		} else {
			throw new IllegalArgumentException("Must be a non-negative #");
		}
		return frog;
	}

	public int getWidth() {
		return width;
	}

	public int getHeight() {
		return height;
	}

	public Pixel getPixel(int x, int y) {
		// checking to see if it is in bounds
		Pixel[][] frog = pixel_array.clone();
		if (x >= 0 && x < width) {
			if (y >= 0 && y < height) {
				return frog[x][y];
			} else {
				throw new IllegalArgumentException("Too large or small");
			}
		} else {
			throw new IllegalArgumentException("Too large or small");
		}
	}


	public Picture paint(int x, int y, Pixel p) {
		// checking to see if it is in bounds
	
		MutablePixelArrayPicture frog = new MutablePixelArrayPicture(clonePixelArray(pixel_array));
		if (x >= 0 && x < width) {
			if (y >= 0 && y < height) {
				frog.paint(x, y, p);
			} else {
				throw new IllegalArgumentException("Too large or small");
			}
		} else {
			throw new IllegalArgumentException("Too large or small");
		}
		return frog;
	}


	public Picture paint(int x, int y, Pixel p, double factor) {
		// checking to see if it is in bounds
		MutablePixelArrayPicture frog = new MutablePixelArrayPicture(clonePixelArray(pixel_array));
		if (x >= 0 && x < width) {
			if (y >= 0 && y < height) {
				frog.pixel_array[x][y].blend(p, factor);
			} else {
				throw new IllegalArgumentException("Too large or small");
			}
		} else {
			throw new IllegalArgumentException("Too large or small");
		}
		return frog;
	}

	public Pixel[][] helper(int x, int y, Pixel p) {
		Pixel[][] frog = new Pixel[x][y];
		for (int g = 0; g < x; g++) {
			for (int h = 0; h < y; h++) {
				frog[g][h] = p;
			}
		} 
		return frog;
	}
	
	private Pixel[][] clonePixelArray(Pixel[][] pixel_array) {
		Pixel[][] new_pixel_array = new Pixel[pixel_array.length][];
		for (int i=0; i<pixel_array.length; i++) {
			new_pixel_array[i] = pixel_array[i].clone();
		}
		return new_pixel_array;
	}
	
}
	
	

