/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package calculadoraracionales;

/**
 *
 * @author alexi
 */
public class Racional {
    private int den, num;
    
     public Racional(int n,int d){
         den = d;
         num = n;
     }
    
    public Racional sumar(Racional r){
        int mD= mcd(r.den, den);
        Racional rp = new Racional((mD/r.den)*num + (mD/den)*r.num, mD);
        rp.reducir();
        return rp;
    }
    
    public Racional restar(Racional r){
        int mD= mcd(r.den, den);
        Racional rp = new Racional((mD/r.den)*num - (mD/den)*r.num, mD);
        rp.reducir();
        return rp;
    }
    
    public Racional multiplicar(Racional r){
        Racional rp = new Racional(num*r.num, den*r.den);
        rp.reducir();
        return rp;
    }
    
    public Racional multiplicar(int n){
        return multiplicar(new Racional(n, 1));
    }
    
    public Racional cuadrado(){
        return multiplicar(this);
    }
    
    public Racional dividir(Racional r){
        Racional rp = new Racional(num*r.den, r.num * den);
        rp.reducir();
        return rp;
    }
    
    public Racional dividir(int n){
        Racional rp =  new Racional(num, den*n);
        rp.reducir();
        return rp;
    }
    
    public boolean comparar(Racional r){
        return den*r.num==r.den*num;
    }
    
    public Racional potencia(){
        return new Racional(this.den,this.num);
    }
    
    
    private int gcd(int a, int b){
        int gc=1;
        for(int i = 1; i <= a && i <= b; i++)
            if(a%i==0 && b%i==0)
                gc = i;
        return gc;
    }
    
    
    private int mcd(int a, int b){
        return (a*b)/gcd(a, b);
    }
    @Override
    public String toString(){
        return num + "/" + den;
    }
    
    boolean esPositivo(){
        return (den>=0 && num>=0) || (den<0 && num<0);
    }
    
    double aDecimales(){
        return Double.parseDouble(num+"")/Double.parseDouble(den+"");
    }
    
    void reducir(){
        int gc = gcd(num, den);
        num/=gc;
        den/=gc;
    }
        
}