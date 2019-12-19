package Ex1;
import java.awt.Font;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.io.File;
import java.io.PrintWriter;
public class Functions_GUI implements functions {
	
	public ArrayList<function> ARRAY_function =new ArrayList<function>();
	@Override
	public boolean add(function arg0) {
		return ARRAY_function.add(arg0);	
	}

	@Override
	public boolean addAll(Collection<? extends function> arg0) {
		return ARRAY_function.addAll(arg0);
	}

	@Override
	public void clear() {
		ARRAY_function.clear();
	}

	@Override
	public boolean contains(Object arg0) {
		return ARRAY_function.contains(arg0);
	}

	@Override
	public boolean containsAll(Collection<?> arg0) {
		return ARRAY_function.containsAll(arg0);
	}

	@Override
	public boolean isEmpty() {
		return ARRAY_function.isEmpty();
	}
	@Override
	public Iterator<function> iterator() {
		return ARRAY_function.iterator();
	}

	@Override
	public boolean remove(Object arg0) {
		return ARRAY_function.remove(arg0);
	}

	@Override
	public boolean removeAll(Collection<?> arg0) {
		return ARRAY_function.removeAll(arg0);
	}

	@Override
	public boolean retainAll(Collection<?> arg0) {
		return ARRAY_function.retainAll(arg0);
	}

	@Override
	public int size() {
		return ARRAY_function.size();
	}

	@Override
	public Object[] toArray() {
		return ARRAY_function.toArray();
	}

	@Override
	public <T> T[] toArray(T[] arg0) {
		return ARRAY_function.toArray(arg0);
	}

	@Override
	public void initFromFile(String file) throws IOException {


	}

	@Override
	public void saveToFile(String file) throws IOException {
		File f=new File(file);

		PrintWriter pw=new PrintWriter(f);
		for (int i = 0; i < ARRAY_function.size(); i++) {
            //System.out.println(ARRAY_function.get(i)+"444444444444444tshi daniel");
            //System.out.println(i);
			pw.println(i+") f(x)= "+ARRAY_function.get(i));
		}
		pw.close();
	}

	@Override
	public void drawFunctions(int width, int height, Range rx, Range ry, int resolution) {
		StdDraw.setXscale(rx.get_min(), rx.get_max());
		StdDraw.setYscale(ry.get_min(), ry.get_max());
		//Polynom p=new Polynom("x^2");
		for(int i=0;i<ARRAY_function.size();i++){
			ComplexFunction p=(ComplexFunction)ARRAY_function.get(i);
			//System.out.println(p+"zzzzzzzzzzzzz");
			int n=100;
			double[] x = new double[n+1];
			double[] y = new double[n+1];
			for (int j = 0; j <= n; j++) {
				x[j] = rx.get_min()+j*((rx.get_max()-rx.get_min())/n);
				y[j] = p.f(x[j]);
			}
			//////// vertical lines
			//StdDraw.setPenColor(Color.LIGHT_GRAY);
			//StdDraw.setPenColor(Color.RED);
			StdDraw.setPenRadius(0.01);
			for (int j = 0; j < n; j++) {
				//System.out.println("66");
				//System.out.println(x[j]+","+y[j]);
				StdDraw.line(x[j], y[j], x[j+1], y[j+1]);
				//StdDraw.show();
			}
		}
		//StdDraw.line(x[i], minY, x[i], maxY);
		StdDraw.show();
	}

	@Override
	public void drawFunctions(String json_file) {
		

	}

}
