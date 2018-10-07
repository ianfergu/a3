package a3;

public class HorizontalStackPicture implements Picture{
	
	public Picture left;
	public Picture right;
	public Pixel[][] pixel_array;
	public int width;
	public int height;

	public HorizontalStackPicture(Picture left, Picture right) {
		if (left != null && right != null && left.getHeight() == right.getHeight()) {
			this.left = left;
			this.right = right; 
			this.pixel_array = new Pixel[left.getWidth()+right.getWidth()][left.getHeight()];
			this.width = pixel_array.length;
			this.height = pixel_array[0].length;
			for (int x = 0; x < width; x++) {
				for (int y = 0; y < height; y++ ) {
					if (x >= left.getWidth()) {
						pixel_array[x][y] = right.getPixel(x-(left.getWidth()), y);
					} else {
						pixel_array[x][y] = left.getPixel(x, y);
					}
				}
			}
			
		}
		else {
			throw new IllegalArgumentException();
		}
	}
	
	public int getWidth() {
		return width;
	}

	public int getHeight() {
		return height;
	}


	public Pixel getPixel(int x, int y) {
		if (x >= 0 && x < width && y >= 0 && y < height) {
			return pixel_array[x][y];
		} else {
			throw new IllegalArgumentException();
		}
	}


	public Picture paint(int x, int y, Pixel p) {
		if (x >= 0 && x < width && y >= 0 && y < height && p != null) {
			pixel_array[x][y] = p;
			return this;
		} else {
			throw new IllegalArgumentException();
		}
	}

	
	public Picture paint(int x, int y, Pixel p, double factor) {
		if (x >= 0 && x < width && y >= 0 && y < height && p != null) {
			pixel_array[x][y] = pixel_array[x][y].blend(p, factor);
			return this;
		} else {
			throw new IllegalArgumentException();
		}
	}


	public Picture paint(int ax, int ay, int bx, int by, Pixel p) {
		for (int x = ax; x <= bx; x++) {
			for (int y = ay; y <= by; y++) {
				if (x < 0 || y < 0) {
					continue;
				} else {
					paint(x, y, p);
				}
			}
		}
		return this;
	}

	
	public Picture paint(int ax, int ay, int bx, int by, Pixel p, double factor) {
		for (int x = ax; x <= bx; x++) {
			for (int y = ay; y <= by; y++) {
				if (x < 0 || y < 0) {
					continue;
				} else {
					paint(x, y, p, factor);
				}
			}
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
