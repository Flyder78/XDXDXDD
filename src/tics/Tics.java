
package tics;

import java.util.*;



public class Tics {
    static class Persona{
        int MINprod;
        int MAXprod;
        int tSelec;
        int tDespa;
        int Random;
        
        
        public Persona(int MINprod, int MAXprod, int promSelec , int promDespa , int promCaja ){
            this.Random=(int) Math.floor(Math.random()*(MAXprod - MINprod + 1) + MINprod);
            this.MAXprod = MAXprod;
            this.MINprod = MINprod;
            this.tSelec = promSelec * Random;
            this.tDespa = promDespa * Random+promCaja;
            
            
        }
             
    }
    static class intervalo{
    int cant_clientes;
    int client_des; 
    int prom_prod;
    int prom_cola;
    int colalarga;
    public intervalo(int cant_clientes,int client_des,int prom_prod,int prom_cola,int colalarga){
        this.cant_clientes=cant_clientes;
        this.client_des=client_des;
        this.prom_prod=prom_prod;
        this.prom_cola=prom_cola;
        this.colalarga=colalarga;
    }
    
        }


    public static void main(String[] args) {
        String texto="";
        int horas=10;
        int clientes=0;
        int[] distri=new int[10];
        int[] cajas=new int[10];
        int minpro=0;
        int maxpro=0;
        int prom_se=0;
        int prom_des=0;
        int prom_pago=0;
        Scanner a = new Scanner(System.in);
           /*System.out.println("Identificación del “proceso de simulación”: ");
            texto=a.nextLine();
            System.out.println("Ingrese horas de atencion del local: ");
            horas=a.nextInt();*/
            System.out.println("Ingrese clientes esperados en el dia: ");
            clientes=a.nextInt();
            System.out.println("Ingrese la distribucion de los clientes en cada intervalo: ");
            for(int i=0;i<distri.length;i++){
                System.out.println("Distribucion del Intervalo "+(i+1)+": ");
                distri[i]=a.nextInt();
            }
            System.out.println("Cajas abiertas en cada intervalo");
            for(int i=0;i<cajas.length;i++){
                System.out.println("Cajas abiertas en el Intervalo "+(i+1)+": ");
                cajas[i]=a.nextInt();
            }/*
            System.out.println("Cantidad mínima de productos por cliente: ");
            minpro=a.nextInt();
            System.out.println("Cantidad máxima de productos por cliente");
            maxpro=a.nextInt();
            System.out.println("Tiempo promedio de selección de cada producto por parte del cliente antes de ir a Cajas, en segundos: ");
            prom_se=a.nextInt();
            System.out.println("Tiempo promedio de despacho (marcado) por producto en la Caja, en segundos: ");
            prom_des=a.nextInt();
            System.out.println("Tiempo promedio de pago en la Caja por cliente, en segundos: ");
            prom_pago=a.nextInt();
            /*----------------------------------SIMULACION-----------------------------------------------------------*/
            int tiempo=horas*3600;
            int t_inter=tiempo/10;
            int clientes_ingresados=0;
            int clientes_despachados=0;
            LinkedList<Persona> compranding=new LinkedList();
            LinkedList<Queue<Persona>> c=new LinkedList();

            int intervaux=-1;
            boolean paractm=false;
            double clientesmax=0;
            int colamax=0;
            int i=0;
            while(i<tiempo){
                int interv=i/t_inter;
                double probclientes=0;
                System.out.println("xddddddddddd "+distri[interv] );
                System.out.println("Clientes en el intervalo: "+clientesmax);
                probclientes=((clientes*((double)distri[interv]/100))/t_inter)*100;
                System.out.println("probabilidad cliente: "+probclientes);
                System.out.println("Tiempo: "+i);
                
                if(interv!=intervaux){
                    intervaux=interv;
                    clientesmax=clientesmax+clientes*((double)distri[interv]/100);
                    paractm=false;
                    for(int j=0;j<c.size();j++){//vacia cajas
                        if(c.get(j).size()>0){
                            while(c.get(j).peek()!=null){
                                c.get(j).peek().tSelec=1;
                                compranding.add(c.get(j).poll());
                            }
                        }
                    }
                    c.clear();
                    for(int j=0;j<cajas[interv];j++){
                        Queue caja1= new LinkedList();
                        c.add(caja1);
                    }
                    System.out.println("Ahora hay "+c.size()+" cajas");
                }
                
                int Random = (int) Math.floor(Math.random()*(100));
                if(clientes_ingresados>=clientesmax){
                    paractm=true;
                    System.out.println("AAAAAAAAAAAAAAAAHHHH");
                }
                if(Random<probclientes+2 && paractm==false){
                    
                   Persona cliente1=new Persona(1,10,30,1,1);
                   compranding.add(cliente1);
                    System.out.println("genero un wn");
                    clientes_ingresados=clientes_ingresados+1;
                    
                }
                
                restartiempocompra(compranding,c,colamax);
                clientes_despachados=restartiempocaja(c,clientes_despachados);
                System.out.println("hay "+compranding.size()+" wns comprando");
                i++;
            }
            System.out.println("Clientes despachados: "+clientes_despachados);
            System.out.println("Clientes ingresados: "+clientes_ingresados);
            System.out.println("Clientes comprando: "+compranding.size());
            for(int x=0;x<c.size();x++){
                System.out.println("Gente en caja "+x+" fue de "+c.get(x).size());
            }
        }
    static void restartiempocompra(LinkedList<Persona> xd,LinkedList<Queue<Persona>> xd2,int colamax){
        
        LinkedList<Integer> borrar=new LinkedList();
        for(int i=0;i<xd.size();i++){
            
            xd.get(i).tSelec=xd.get(i).tSelec-1;
            if(xd.get(i).tSelec==0){
              //  System.out.println("wn termino de comprar");
                int menor=100000000;
                int pos=0;
                for(int j=0;j<xd2.size();j++){
                    if(xd2.get(j).size()<menor){
                        if(xd2.get(j).size()>colamax){
                            colamax=xd2.get(j).size();
                        }
                        menor=xd2.get(j).size();
                        pos=j;
                    }
                    
                }
                
                xd2.get(pos).add(xd.get(i));
                xd.remove(i);
                
            }
        }
        Iterator<Integer> dios=borrar.iterator();
        //System.out.println("wns para borrar "+borrar.size());
        while (dios.hasNext()) {
            Integer next = dios.next();
            
            //System.out.println("wn "+xd.size());
            
        }
    }
        static int restartiempocaja(LinkedList<Queue<Persona>> xd2,int client){
        for(int i=0;i<xd2.size();i++){
            if(xd2.get(i).peek()!=null){
            xd2.get(i).peek().tDespa=xd2.get(i).peek().tDespa-1;
            if(xd2.get(i).peek().tDespa==0){
               // System.out.println("salio un wn");
                xd2.get(i).poll();
                client++;
            }
        }
        }
        return client;
    }
        
    }
             
    
    
       
    
