package a3;

public class GammaCorrect implements PixelTransformation {

	double gamma;
	
	public GammaCorrect (double gamma) {
		this.gamma = gamma;
	}
	
	public Pixel transform(Pixel p) {
		double b = Math.pow(p.getBlue(), (1.0/gamma));
		double r = Math.pow(p.getRed(), (1.0/gamma));
		double g = Math.pow(p.getGreen(), (1.0/gamma));
		return new ColorPixel(r, g, b);
	}

}
