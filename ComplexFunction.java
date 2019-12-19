package Ex1;

import javax.xml.ws.FaultAction;

public class ComplexFunction implements complex_function {
	Operation OP;
	function left;
	function right;
	function data;
	public static void main(String[] args) {
		
		
		
		
		
		System.out.println("f");
		Polynom p=new Polynom("3x^5+4x^7+3+3x");
		
		System.out.println("p="+p);
		Monom m=new Monom("3x^5");
		Polynom p1=new Polynom("x +3");
		Monom m1=new Monom("3x");
		ComplexFunction a=new ComplexFunction(Operation.Plus,p,m);
		System.out.println("a="+a);
		ComplexFunction d=(ComplexFunction)a.copy();
		System.out.println("d="+d);
		System.out.println(a+"6");
		//ComplexFunction d=(ComplexFunction)a.copy();
		//System.out.println("d="+d);
		System.out.println(a);
		ComplexFunction b=new ComplexFunction(Operation.Max,p1,m1);
		a.mul(p);
		System.out.println("abcdefg="+a);
		a.min(b);
		a.div(b);
		a.comp(b);
		//ComplexFunction c=(ComplexFunction)a.copy();

		System.out.println("a="+a);
		System.out.println(a.f(9));
		//System.out.println("c="+c);
		ComplexFunction c=(ComplexFunction)a.initFromString("comp(div(min(plus(plus(4.0x^7+3.0x^5+3.0x+3.0,3.0x^5),max(1.0x+3.0,3.0x)),max(1.0x+3.0,3.0x)),max(1.0x+3.0,3.0x)),max(1.0x+3.0,3.0x))");

		System.out.println("c="+c);
		ComplexFunction w=(ComplexFunction)c.copy();
		System.out.println("w="+w);
		ComplexFunction z=(ComplexFunction)a.initFromString("mul(x,plus(x,x))");
	}


	public ComplexFunction(function f) {
		if(f instanceof Polynom || f instanceof Monom){
			//System.out.println("e");
			this.OP=Operation.None;
			this.data=f;
		}
		else{
			if(((ComplexFunction)f).OP==Operation.None){
				this.OP=Operation.None;
				this.data=((ComplexFunction)f).data;
			}
			else if(((ComplexFunction)f).OP==Operation.Plus){
				OP=Operation.Plus;
				left=new ComplexFunction(((ComplexFunction)f).left);
				right=new ComplexFunction(((ComplexFunction)f).right);
			}
			else if(((ComplexFunction)f).OP==Operation.Divid){
				OP=Operation.Divid;
				left=new ComplexFunction(((ComplexFunction)f).left);
				right=new ComplexFunction(((ComplexFunction)f).right);
			}
			else if(((ComplexFunction)f).OP==Operation.Times){
				OP=Operation.Times;
				left=new ComplexFunction(((ComplexFunction)f).left);
				right=new ComplexFunction(((ComplexFunction)f).right);
			}
			else if(((ComplexFunction)f).OP==Operation.Max){
				OP=Operation.Max;
				left=new ComplexFunction(((ComplexFunction)f).left);
				right=new ComplexFunction(((ComplexFunction)f).right);
			}
			else if(((ComplexFunction)f).OP==Operation.Min){
				OP=Operation.Min;
				left=new ComplexFunction(((ComplexFunction)f).left);
				right=new ComplexFunction(((ComplexFunction)f).right);
			}
			else{
				OP=Operation.Comp;
				left=new ComplexFunction(((ComplexFunction)f).left);
				right=new ComplexFunction(((ComplexFunction)f).right);
			}
		}
	}
	public ComplexFunction(Operation op,function left,function right) {
		if(op==Operation.Plus){
			OP=Operation.Plus;
			this.left=new ComplexFunction(left);
			this.right=new ComplexFunction(right);
			//System.out.println(left+"qqaws");
			
		}
		else if(op==Operation.Divid){
			OP=Operation.Divid;
			this.left=new ComplexFunction(left);
			this.right=new ComplexFunction(right);
		}
		else if(op==Operation.Times){
			OP=Operation.Times;
			this.left=new ComplexFunction(left);
			this.right=new ComplexFunction(right);
		}
		else if(op==Operation.Max){
			OP=Operation.Max;
			this.left=new ComplexFunction(left);
			this.right=new ComplexFunction(right);
		}
		else if(op==Operation.Min){
			OP=Operation.Min;
			this.left=new ComplexFunction(left);
			this.right=new ComplexFunction(right);
		}
		else{
			OP=Operation.Comp;
			this.left=new ComplexFunction(left);
			this.right=new ComplexFunction(right);
		}
	}
	public ComplexFunction(String str,function left,function right) {
		if(str.equals("div")){
			OP=Operation.Divid;
		}
		else if(str.equals("plus")){

			OP=Operation.Plus;
		}
		else if(str.equals("max")){
			OP=Operation.Max;
		}
		else if(str.equals("min")){
			OP=Operation.Min;
		}
		else if(str.equals("mul")){
			OP=Operation.Times;
		}
		else if(str.equals("comp")){
			//System.out.println("33");
			OP=Operation.Comp;
		}
		else {
			throw new Error("call operator no exsist");
		}
		if(left instanceof Polynom || left instanceof Monom){
			this.left=new ComplexFunction(left);
			//System.out.println("ze");
		}
		else{
			this.left=left;
		}
		if(right instanceof Polynom || right instanceof Monom){
			this.right=new ComplexFunction(right);
		}
		else{
			this.right=right;
			//System.out.println("qe");
		}
	}
	@Override
	public double f(double x) {
		if(OP==Operation.None){
			return data.f(x);
		}
		else if(OP==Operation.Plus){
			return left.f(x)+right.f(x);
		}
		else if(OP==Operation.Comp){
			double rx=right.f(x);
			return left.f(rx);
		}
		else if(OP==Operation.Divid){
			double r=right.f(x);
			if(r==0){
				if(left.f(x)>0){
					return 15;
				}
				else{
					return -5;
				}
			}
			else{
				return left.f(x)/right.f(x);
			}
		}
		else if(OP==Operation.Max){
			return Math.max(left.f(x), right.f(x));
		}
		else if(OP==Operation.Min){
			return Math.min(left.f(x), right.f(x));
		}
		return 0;
	}
	@Override
	public function initFromString(String s) {
		if(!s.contains("(")){
			//System.out.println("1:"+s);
			return new ComplexFunction(new Polynom(s));
		}
		else{
			int i=s.indexOf(",");
			int start=s.indexOf("(");
			if(!s.substring(start+1,i).contains("(")){
				//System.out.println("2:"+s.substring(start+1,i));
				return new ComplexFunction(s.substring(0, start),new Polynom(s.substring(start+1,i)),initFromString(s.substring(i+1,s.length()-1)));
			}
			int count=0;
			for (int j = 0; j < s.length(); j++) {
				if(s.charAt(j)=='('){
					count++;
				}
				if(s.charAt(j)==')'){
					count--;
				}
				if(count==1&&s.charAt(j)==','){
					//System.out.println("3:"+s.substring(0, start)+"->"+s.substring(start+1, j)+";"+s.substring(j+1,s.length()-1));
					return new ComplexFunction(s.substring(0, start),initFromString(s.substring(start+1, j)),initFromString(s.substring(j+1,s.length()-1)));
				}
			}
		}
		return null;
	}
	@Override
	public function copy() {
		if(OP==Operation.None){
			return new ComplexFunction(data);
		}
		return new ComplexFunction(OP,left,right);
		/*if(OP==Operation.None){
			//System.out.println(data+"8");
			//System.out.println(data);
			if (data instanceof Polynom) {
				//System.out.println("yes");
			}
			//System.out.println(data.copy());
			return new ComplexFunction(data.copy());
		}
		else if(OP==Operation.Plus){
			//System.out.println(left);
			if(left instanceof ComplexFunction){
				//System.out.println("1");
			}
			else{
				//System.out.println("2");
			}
			return new ComplexFunction(Operation.Plus,left.copy(),right.copy());
		}
		else if(OP==Operation.Comp){
			return new ComplexFunction(Operation.Comp,left.copy(),right.copy());
		}
		else if(OP==Operation.Divid){
			return new ComplexFunction(Operation.Divid,left.copy(),right.copy());
		}
		else if(OP==Operation.Max){
			return new ComplexFunction(Operation.Max,left.copy(),right.copy());
		}
		else if(OP==Operation.Min){
			return new ComplexFunction(Operation.Min,left.copy(),right.copy());
		}
		else{
			return null;
		}*/
	}

	@Override
	public void plus(function f1) {
		System.out.println(left+"k");
		if(f1 instanceof Polynom || f1 instanceof Monom) {
			Operation OP=this.OP;
			if(OP==Operation.None) {
                 left=new ComplexFunction(data);
                 
                 right=new ComplexFunction(f1);
                 this.OP=Operation.Plus;
			}
			else {
				function l=new ComplexFunction(left);
				function r=new ComplexFunction(right);
				this.OP=Operation.Plus;
				left=new ComplexFunction(OP, l, r);
				right=new ComplexFunction(f1);
			}
			
		}
		else {
			Operation OP=this.OP;
			function l=this.left;
			function r=this.right;
			this.OP=Operation.Plus;

			ComplexFunction ans=new ComplexFunction(OP,l,r);
			this.left=ans;
			this.right=f1;
		}
	}
	@Override
	public void mul(function f1) {
		if(f1 instanceof Polynom || f1 instanceof Monom) {
			Operation OP=this.OP;
			if(OP==Operation.None) {
                 left=new ComplexFunction(data);
                 right=new ComplexFunction(f1);
                 this.OP=Operation.Times;
			}
			else {
				function l=this.left;
				function r=this.right;
				this.OP=Operation.Times;
				left=new ComplexFunction(OP, l, r);
				right=new ComplexFunction(f1);
			}
			
		}
		else {
			Operation OP=this.OP;
			function l=this.left;
			function r=this.right;
			this.OP=Operation.Times;

			ComplexFunction ans=new ComplexFunction(OP,l,r);
			this.left=ans;
			this.right=f1;
		}
	}
	@Override
	public void div(function f1) {
		if(f1 instanceof Polynom || f1 instanceof Monom) {
			Operation OP=this.OP;
			if(OP==Operation.None) {
                 left=new ComplexFunction(data);
                 right=new ComplexFunction(f1);
                 this.OP=Operation.Divid;
			}
			else {
				function l=this.left;
				function r=this.right;
				this.OP=Operation.Divid;
				left=new ComplexFunction(OP, l, r);
				right=new ComplexFunction(f1);
			}
			
		}
		else {
			Operation OP=this.OP;
			function l=this.left;
			function r=this.right;
			this.OP=Operation.Divid;

			ComplexFunction ans=new ComplexFunction(OP,l,r);
			this.left=ans;
			this.right=f1;
		}
	}
	@Override
	public void max(function f1) {
		if(f1 instanceof Polynom || f1 instanceof Monom) {
			Operation OP=this.OP;
			if(OP==Operation.None) {
                 left=new ComplexFunction(data);
                 right=new ComplexFunction(f1);
                 this.OP=Operation.Max;
			}
			else {
				function l=this.left;
				function r=this.right;
				this.OP=Operation.Max;
				left=new ComplexFunction(OP, l, r);
				right=new ComplexFunction(f1);
			}
			
		}
		else {
			Operation OP=this.OP;
			function l=this.left;
			function r=this.right;
			this.OP=Operation.Max;

			ComplexFunction ans=new ComplexFunction(OP,l,r);
			this.left=ans;
			this.right=f1;
		}
	}
	@Override
	public void min(function f1) {
		if(f1 instanceof Polynom || f1 instanceof Monom) {
			Operation OP=this.OP;
			if(OP==Operation.None) {
                 left=new ComplexFunction(data);
                 right=new ComplexFunction(f1);
                 this.OP=Operation.Min;
			}
			else {
				function l=this.left;
				function r=this.right;
				this.OP=Operation.Min;
				left=new ComplexFunction(OP, l, r);
				right=new ComplexFunction(f1);
			}
			
		}
		else {
			Operation OP=this.OP;
			function l=this.left;
			function r=this.right;
			this.OP=Operation.Min;

			ComplexFunction ans=new ComplexFunction(OP,l,r);
			this.left=ans;
			this.right=f1;
		}
	}
	@Override
	public void comp(function f1) {
		if(f1 instanceof Polynom || f1 instanceof Monom) {
			Operation OP=this.OP;
			if(OP==Operation.None) {
                 left=new ComplexFunction(data);
                 right=new ComplexFunction(f1);
                 this.OP=Operation.Comp;
			}
			else {
				function l=this.left;
				function r=this.right;
				this.OP=Operation.Comp;
				left=new ComplexFunction(OP, l, r);
				right=new ComplexFunction(f1);
			}
			
		}
		else {
			Operation OP=this.OP;
			function l=this.left;
			function r=this.right;
			this.OP=Operation.Comp;

			ComplexFunction ans=new ComplexFunction(OP,l,r);
			this.left=ans;
			this.right=f1;
		}
	}
	@Override
	public function left() {
		return this.left;
	}
	@Override
	public function right(){ 
		return this.right;
	}
	@Override
	public Operation getOp() {

		return OP;
	}
	public String toString() {
		if(OP==Operation.None){
			return data.toString();
		}
		else if(OP==Operation.Plus){
			//System.out.println(left+"23444");
			if(left instanceof ComplexFunction){
				//System.out.println("WWW");
			}
			else{
				//System.out.println("ff");
				if(left==null){
					//System.out.println("qq");
				}
			}
			return "plus("+left.toString()+","+right.toString()+")";
		}
		else if(OP==Operation.Comp){
			return "comp("+left.toString()+","+right.toString()+")";
		}
		else if(OP==Operation.Times){
			return "mul("+left.toString()+","+right.toString()+")";
		}
		else if(OP==Operation.Divid){
			return "div("+left.toString()+","+right.toString()+")";
		}
		else if(OP==Operation.Max){
			return "max("+left.toString()+","+right.toString()+")";
		}
		else if(OP==Operation.Min){
			return "min("+left.toString()+","+right.toString()+")";
		}
		
		else{
			return "" ;
		}
	}

}
