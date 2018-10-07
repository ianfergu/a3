package a3;

public class VerticalStackPicture implements Picture{
	
	public Picture top;
	public Picture bottom;
	public Pixel[][] pixel_array;
	public int width;
	public int height;

	public VerticalStackPicture(Picture top, Picture bottom) {
		if (top != null && bottom != null && top.getWidth() == bottom.getWidth()) {
			this.top = top;
			this.bottom = bottom; 
			this.pixel_array = new Pixel[top.getWidth()][top.getHeight()+bottom.getHeight()];
			this.width = pixel_array.length;
			this.height = pixel_array[0].length;
			for (int x = 0; x < width; x++) {
				for (int y = 0; y < height; y++ ) {
					if (y >= top.getHeight()) {
						pixel_array[x][y] = bottom.getPixel(x, y-(top.getHeight()));
					} else {
						pixel_array[x][y] = top.getPixel(x, y);
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
