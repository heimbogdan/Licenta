package clase2;

public class GuillotineThread extends Thread {
	private FinalElement result;
	private ElementList elements;
	private Element root;
	private boolean horizaontal;

	public FinalElement getResult() {
		return result;
	}

	@Override
	public void run() {
		GuillotineMain.addThread();
		final GuillotineCut gCut = new GuillotineCut(this.horizaontal);
		gCut.beginCutting(this.elements, this.root);
		result = gCut.getCutElement();
		GuillotineMain.addResult(this.result);
		GuillotineMain.endThread();
	}

	public GuillotineThread(final ElementList elementList,final Element root,final boolean h) {
		this.elements = elementList;
		this.root = root;
		this.horizaontal = h;
	}
}
