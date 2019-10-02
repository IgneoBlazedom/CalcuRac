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
    private double dop;
    
     public Racional(int n,int d){
         den = d;
         num = n;
     }
    
    public Racional sumar(Racional r){
        int mD= gcd(r.den, den);
        
        return new Racional((den/mD)*num + (r.den/mD)*r.num, r.den*den*mD);
    }
    
    public Racional restar(Racional r){
        int mD= gcd(r.den, den);
        
        return new Racional((den/mD)*num - (r.den/mD)*r.num, r.den*den*mD);
    }
    
    public Racional multiplicar(Racional r){
        return new Racional(num*r.num, mcd(den, r.den));
    }
    
    public Racional multiplicar(int n){
        return multiplicar(new Racional(n, 1));
    }
    
    public Racional cuadrado(){
        return new Racional(this.num*this.num, this.den*this.den);
    }
    
    public Racional dividir(Racional r){
        return new Racional(den*r.num, num * r.den);
    }
    
    public boolean comparar(Racional r){
        return den*r.num==r.den*num;
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
        return new Double(den/num);
    }
    
    
}
