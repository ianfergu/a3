package a3;

public class Threshold implements PixelTransformation {
	
	double threshold;
	
	public Threshold(double threshold) {
		this.threshold = threshold;
	}
	
	
	public Pixel transform(Pixel p) {
		if (p.getIntensity() > threshold) {
			return new GrayPixel(1.0);
		} else {
			return new GrayPixel(0.0);
		}
	}

}
