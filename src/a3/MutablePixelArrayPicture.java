package a3;

public class MutablePixelArrayPicture implements Picture {

	public Pixel[][] pixel_array;
	public int width;
	public int height;

	public MutablePixelArrayPicture(Pixel[][] pixel_array) {
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

	public MutablePixelArrayPicture(int width, int height, Pixel initial_value) {
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

	public MutablePixelArrayPicture(int width, int height) {
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

	public int getWidth() {
		return width;
	}

	public int getHeight() {
		return height;
	}

	public Pixel getPixel(int x, int y) {
		// checking to see if it is in bounds
		if (x >= 0 && x < width) {
			if (y >= 0 && y < height) {
				return pixel_array[x][y];
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
		if (x > 0 || x < width) {
			if (y > 0 || y < height) {
				pixel_array[x][y] = p;
			} else {
				throw new IllegalArgumentException("Too large or small");
			}
		} else {
			throw new IllegalArgumentException("Too large or small");
		}
		return this;
	}

	@Override
	public Picture paint(int x, int y, Pixel p, double factor) {
		// checking to see if it is in bounds
		if (x > 0 && x < width) {
			if (y > 0 && y < height) {
				pixel_array[x][y].blend(p, factor);
			} else {
				throw new IllegalArgumentException("Too large or small");
			}
		} else {
			throw new IllegalArgumentException("Too large or small");
		}
		return this;
	}

	public Picture paint(int ax, int ay, int bx, int by, Pixel p) {
		// checking to see if it is in bounds
		if (ax > 0 && bx < width) {
			if (ay > 0 && by < height) {
				for (int x = ax; x < bx; x++) {
					for (int y = ay; y < by; y++) {
						paint(x, y, p);
					}
				}
			} else {
				throw new IllegalArgumentException("Too large or small");
			}
		} else {
			throw new IllegalArgumentException("Too large or small");
		}
		return this;
	}

	public Picture paint(int ax, int ay, int bx, int by, Pixel p, double factor) {
		// checking to see if it is in bounds
		if (ax < 0 && bx < width) {
			if (ay < 0 && by < height - 1) {
				for (int x = ax; x < bx; x++) {
					for (int y = ay; y < by; y++) {
						paint(x, y, p, factor);
					}
				}
			}
		} else {
			throw new IllegalArgumentException("Too large or small");
		}
		return this;
	}

	public Picture paint(int cx, int cy, double radius, Pixel p) {
		if (radius > 0.0) {
			for (int x = 0; x < width; x++) {
				for (int y = 0; y < height; y++) {
					double distance = Math.sqrt((x - cx) * (x - cx) + (y - cy) * (y - cy));
					if (distance <= radius) {
						paint(x, y, p);
					} else {
						continue;
					}
				}
			}
		} else {
			throw new IllegalArgumentException("Must be a non-negative #");
		}
		return this;
	}

	public Picture paint(int cx, int cy, double radius, Pixel p, double factor) {
		if (radius > 0.0) {
			for (int x = 0; x < width; x++) {
				for (int y = 0; y < height; y++) {
					double distance = Math.sqrt((x - cx) * (x - cx) + (y - cy) * (y - cy));
					if (distance <= radius) {
						paint(x, y, p, factor);
					} else {
						continue;
					}
				}
			}
		} else {
			throw new IllegalArgumentException("Must be a non-negative #");
		}
		return this;
	}
}
