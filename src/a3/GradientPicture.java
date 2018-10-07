package a3;

public class GradientPicture implements Picture{

	public int width;
	public int height;
	public Pixel upper_left;
	public Pixel upper_right;
	public Pixel lower_left;
	public Pixel lower_right;
	public Pixel initial;
	private Pixel[][] pixel_array;
	
	public GradientPicture(int width, int height, Pixel upper_left, 
			Pixel upper_right, Pixel lower_left, Pixel lower_right) {
		if (width > 0) {
			this.width = width; }
		else {
			throw new IllegalArgumentException("one of the values is incorrect or null"); }
		if (height > 0) {
			this.height = height; }
		else {
			throw new IllegalArgumentException("one of the values is incorrect or null"); }
			
		if (upper_left != null) {
			this.upper_left = upper_left; }
		if (upper_right != null) {
			this.upper_right = upper_right; }
		if (lower_left != null) {
			this.lower_left = lower_left; }
		if (lower_right != null) {
			this.lower_right = lower_right; }
		else {
			throw new IllegalArgumentException("Null pixels"); }
		}
	
	public MutablePixelArrayPicture helper() {
		Pixel[][] frog = new Pixel[width][height];
		for (int x = 0; x < width; x++) {
			for (int y = 0; y < height; y++) {
				frog[x][y] = getPixel(x, y);
			}
		}
		return new MutablePixelArrayPicture(frog);
	} 
	
	public int getWidth() {
		return width;
	}

	public int getHeight() {
		return height;
	}

	public Pixel getPixel(int x, int y) {
		if (x >= 0 && y >= 0) {
			Pixel[][] frog = new Pixel[width][height];
			double y1 = y;
			double x1 = x;
			double factor1 = y1/(height-1);
			frog[0][y] = upper_left.blend(lower_left, factor1);
			frog[width-1][y] = upper_right.blend(lower_right, factor1);
			double factor2 = x1/(width-1);
			return frog[0][y].blend(frog[width-1][y], factor2);
		}
		else {
			throw new IllegalArgumentException();
		}
	}

	public Picture paint(int x, int y, Pixel p) {
		if (x >= 0 && y >= 0 && p != null) { 
			MutablePixelArrayPicture frog = helper();
			frog.pixel_array[x][y] = p;
			return frog; }
		else {
			throw new IllegalArgumentException(); }
		}
	

	public Picture paint(int x, int y, Pixel p, double factor) {
		if (x >= 0 && x <= width-1 && y >= 0 && y <= height-1 &&
				p != null && factor >= 0.0 && factor <= 1.0) { 
			MutablePixelArrayPicture frog = helper();
			frog.pixel_array[x][y] = pixel_array[x][y].blend(p, factor);
			return frog; }
		else {
			throw new IllegalArgumentException(); }
		}
	

	public Picture paint(int ax, int ay, int bx, int by, Pixel p) {
		MutablePixelArrayPicture frog = helper();
		for (int x = ax; x <= bx; x++) {
			for (int y = ay; y <= by; y++) {
				if (x < 0 || y < 0) {
					continue;
				} else {
					frog.paint(x, y, p);
				}
			}
		}
		return frog;
	}
	
	public Picture paint(int ax, int ay, int bx, int by, Pixel p, double factor) {
		MutablePixelArrayPicture frog = helper();
		for (int x = ax; x <= bx; x++) {
			for (int y = ay; y <= by; y++) {
				if (x < 0 || y < 0) {
					continue;
				} else {
					frog.paint(x, y, p, factor);
				}
			}
		}
		return frog;
	}
	
	public Picture paint(int cx, int cy, double radius, Pixel p) {
		MutablePixelArrayPicture frog = helper();
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
		MutablePixelArrayPicture frog = helper();
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
	
}
