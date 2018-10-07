package a3;

public class MonochromePicture implements Picture {
	public int width;
	public int height;
	public Pixel value;
	private Pixel[][] pixel_array;

	public MonochromePicture(int width, int height, Pixel value) {
		if (width != 0 && height != 0 && value != null) {
			this.width = width;
			this.height = height;
			this.value = value;
		} else {
			throw new IllegalArgumentException("Cant be 0");
		}
		this.pixel_array = helper(width, height, value);
	}

	public Picture paint(int ax, int ay, int bx, int by, Pixel p) {
		MutablePixelArrayPicture frog = new MutablePixelArrayPicture(width, height, value);
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
		MutablePixelArrayPicture frog = new MutablePixelArrayPicture(width, height, value);
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

	@Override
	public Picture paint(int cx, int cy, double radius, Pixel p) {
		MutablePixelArrayPicture frog = new MutablePixelArrayPicture(width, height, value);
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

	@Override
	public Picture paint(int cx, int cy, double radius, Pixel p, double factor) {
		MutablePixelArrayPicture frog = new MutablePixelArrayPicture(width, height, value);
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
		Pixel[][] frog = helper(width, height, value);
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

	@Override
	public Picture paint(int x, int y, Pixel p) {
		// checking to see if it is in bounds
		MutablePixelArrayPicture frog = new MutablePixelArrayPicture(width, height, value);
		if (x >= 0 || x < width) {
			if (y >= 0 || y < height) {
				frog.pixel_array[x][y] = p;
			} else {
				throw new IllegalArgumentException("Too large or small");
			}
		} else {
			throw new IllegalArgumentException("Too large or small");
		}
		return frog;
	}

	@Override
	public Picture paint(int x, int y, Pixel p, double factor) {
		// checking to see if it is in bounds
		MutablePixelArrayPicture frog = new MutablePixelArrayPicture(width, height, value);
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
}
