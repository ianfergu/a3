package a3;

public class TransformedPicture implements Picture {
	Picture source;
	PixelTransformation xform;
	int width;
	int height;
	
	public TransformedPicture (Picture source, PixelTransformation xform) {
		this.source = source;
		this.xform = xform;
		this.height = source.getHeight();
		this.width = source.getWidth();
	}


	public int getWidth() {
		return width;
	}

	public int getHeight() {
		return height;
	}

	
	public Pixel getPixel(int x, int y) {
		return xform.transform(source.getPixel(x, y));
		
		
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
			frog.pixel_array[x][y] = source.getPixel(x, y).blend(p, factor);
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
	
	public MutablePixelArrayPicture helper() {
		Pixel[][] frog = new Pixel[width][height];
		for (int x = 0; x < width; x++) {
			for (int y = 0; y < height; y++) {
				frog[x][y] = getPixel(x, y);
			}
		}
		return new MutablePixelArrayPicture(frog);
	} 
	
}
	
