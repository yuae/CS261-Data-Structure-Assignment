//B class acts as an added layer of obfuscation
//if you did super.x here you'd get A's x
public class B extends A {
    
    protected int x; 

    public B () {
        //let's again just assign a value to x
        x = 2;
    }

    public int getBX() {
        return x;
    }   

}
